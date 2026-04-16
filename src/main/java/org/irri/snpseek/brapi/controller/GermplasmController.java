package org.irri.snpseek.brapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.irri.snpseek.brapi.domain.Germplasm;
import org.irri.snpseek.brapi.dto.*;
import org.irri.snpseek.brapi.service.GermplasmService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * BrAPI v2.1 Germplasm endpoints.
 *
 * <pre>
 * GET  /brapi/v2/germplasm                      → paginated list (public)
 * GET  /brapi/v2/germplasm/{germplasmDbId}       → single germplasm (public)
 * POST /brapi/v2/search/germplasm                → submit async search (auth required)
 * GET  /brapi/v2/search/germplasm/{searchId}     → retrieve async results (auth required)
 * </pre>
 */
@Tag(name = "Germplasm", description = "Retrieve and search germplasm accessions (BrAPI v2.1)")
@RestController
public class GermplasmController {

    private final GermplasmService germplasmService;

    public GermplasmController(GermplasmService germplasmService) {
        this.germplasmService = germplasmService;
    }

    // =========================================================================
    // GET /brapi/v2/germplasm  — publicly accessible list
    // =========================================================================

    @Operation(
        summary = "List germplasm accessions",
        description = "Return a paginated list of germplasm accessions. All query parameters are optional filters."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Germplasm list returned",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiListResponse.class)))
    })
    @SecurityRequirements
    @GetMapping("/brapi/v2/germplasm")
    public BrapiListResponse<GermplasmDto> listGermplasm(
            @Parameter(description = "Filter by germplasmDbId (numeric stockId)")
            @RequestParam(required = false) String germplasmDbId,
            @Parameter(description = "Filter by germplasm name")
            @RequestParam(required = false) String germplasmName,
            @Parameter(description = "Filter by accession number")
            @RequestParam(required = false) String accessionNumber,
            @Parameter(description = "commonCropName filter (ignored — always 'Oryza sativa')")
            @RequestParam(required = false) String commonCropName,
            @Parameter(description = "0-based page number")
            @RequestParam(required = false) Integer page,
            @Parameter(description = "Number of results per page")
            @RequestParam(required = false) Integer pageSize) {

        GermplasmSearchRequest req = new GermplasmSearchRequest(
                germplasmDbId    != null ? java.util.List.of(germplasmDbId)    : null,
                germplasmName    != null ? java.util.List.of(germplasmName)    : null,
                accessionNumber  != null ? java.util.List.of(accessionNumber)  : null,
                commonCropName,
                null, null, null,
                page,
                pageSize
        );

        // Execute synchronously for simple GET (no async caching needed)
        Page<Germplasm> result;
        try {
            result = germplasmService.search(req).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Search interrupted");
        } catch (ExecutionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Search failed: " + e.getCause().getMessage());
        }

        return BrapiListResponse.of(
                result.getContent().stream().map(GermplasmDto::from).toList(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements()
        );
    }

    // =========================================================================
    // GET /brapi/v2/germplasm/{germplasmDbId}  — publicly accessible single item
    // =========================================================================

    @Operation(
        summary = "Get a single germplasm accession",
        description = "Return details for a single germplasm accession identified by its germplasmDbId."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Germplasm found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiResponse.class))),
        @ApiResponse(responseCode = "404", description = "Germplasm not found")
    })
    @SecurityRequirements
    @GetMapping("/brapi/v2/germplasm/{germplasmDbId}")
    public BrapiResponse<GermplasmDto> getGermplasm(
            @Parameter(description = "Numeric germplasmDbId (stock_id)", required = true)
            @PathVariable String germplasmDbId) {
        long stockId;
        try {
            stockId = Long.parseLong(germplasmDbId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Germplasm not found for germplasmDbId: " + germplasmDbId);
        }
        Germplasm g = germplasmService.findByGermplasmDbId(stockId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Germplasm not found for germplasmDbId: " + germplasmDbId));
        return BrapiResponse.of(GermplasmDto.from(g));
    }

    // =========================================================================
    // POST /brapi/v2/search/germplasm  — auth required
    // =========================================================================

    @Operation(
        summary = "Submit a germplasm search",
        description = """
            Submit a germplasm search. Returns 200 with results if the query completes
            quickly, or 202 with a searchResultsDbId for asynchronous retrieval.

            Requires `BRAPI_USER` Keycloak realm role.
            """
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Results returned immediately",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiListResponse.class))),
        @ApiResponse(responseCode = "202", description = "Search accepted — poll GET with searchResultsDbId",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiSearchResponse.class))),
        @ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "JWT valid but BRAPI_USER role missing")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/brapi/v2/search/germplasm")
    public ResponseEntity<?> submitSearch(@RequestBody GermplasmSearchRequest req) {
        String searchId = UUID.randomUUID().toString();
        CompletableFuture<Page<Germplasm>> future = germplasmService.search(req);
        germplasmService.cacheSearch(searchId, future);

        if (future.isDone()) {
            return ResponseEntity.ok(toListResponse(future, req.effectivePage(), req.effectivePageSize()));
        }
        return ResponseEntity.accepted().body(BrapiSearchResponse.of(searchId));
    }

    // =========================================================================
    // GET /brapi/v2/search/germplasm/{searchResultsDbId}  — auth required
    // =========================================================================

    @Operation(
        summary = "Retrieve async germplasm search results",
        description = """
            Retrieve results of a previously submitted germplasm search.
            Returns 200 when ready, 202 while still processing, 404 for unknown IDs.

            Requires `BRAPI_USER` Keycloak realm role.
            """
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Results ready",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiListResponse.class))),
        @ApiResponse(responseCode = "202", description = "Still processing — retry shortly",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiSearchResponse.class))),
        @ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "JWT valid but BRAPI_USER role missing"),
        @ApiResponse(responseCode = "404", description = "Unknown searchResultsDbId")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/brapi/v2/search/germplasm/{searchResultsDbId}")
    public ResponseEntity<?> getSearchResult(
            @Parameter(description = "The searchResultsDbId returned by a prior POST", required = true)
            @PathVariable String searchResultsDbId) {
        CompletableFuture<Page<Germplasm>> future = germplasmService.getResult(searchResultsDbId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No search found for searchResultsDbId: " + searchResultsDbId));

        if (!future.isDone()) {
            return ResponseEntity.accepted().body(BrapiSearchResponse.of(searchResultsDbId));
        }
        return ResponseEntity.ok(toListResponse(getPage(future)));
    }

    // =========================================================================
    // Helpers
    // =========================================================================

    private BrapiListResponse<GermplasmDto> toListResponse(
            CompletableFuture<Page<Germplasm>> future, int page, int pageSize) {
        return toListResponse(getPage(future));
    }

    private BrapiListResponse<GermplasmDto> toListResponse(Page<Germplasm> page) {
        return BrapiListResponse.of(
                page.getContent().stream().map(GermplasmDto::from).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }

    private Page<Germplasm> getPage(CompletableFuture<Page<Germplasm>> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Search interrupted");
        } catch (ExecutionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Search failed: " + e.getCause().getMessage());
        }
    }
}
