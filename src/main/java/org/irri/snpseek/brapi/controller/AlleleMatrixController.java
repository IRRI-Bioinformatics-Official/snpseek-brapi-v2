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
import org.irri.snpseek.brapi.dto.AlleleMatrixDto;
import org.irri.snpseek.brapi.dto.AlleleMatrixSearchRequest;
import org.irri.snpseek.brapi.dto.BrapiResponse;
import org.irri.snpseek.brapi.dto.BrapiSearchResponse;
import org.irri.snpseek.brapi.service.AlleleMatrixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * BrAPI v2.1 AlleleMatrix endpoints.
 *
 * <pre>
 * GET  /brapi/v2/allelematrix                          → allele matrix (public)
 * POST /brapi/v2/search/allelematrix                   → submit async search (auth required)
 * GET  /brapi/v2/search/allelematrix/{searchResultsDbId} → retrieve async result (auth required)
 * </pre>
 */
@Tag(name = "Allele Matrix", description = "Retrieve genotype call matrices (BrAPI v2.1)")
@RestController
public class AlleleMatrixController {

    private final AlleleMatrixService alleleMatrixService;

    /** In-memory cache for async search futures: searchResultsDbId → future. */
    private final ConcurrentHashMap<String, CompletableFuture<AlleleMatrixDto>> cache =
            new ConcurrentHashMap<>();

    public AlleleMatrixController(AlleleMatrixService alleleMatrixService) {
        this.alleleMatrixService = alleleMatrixService;
    }

    // =========================================================================
    // GET /brapi/v2/allelematrix  — publicly accessible
    // =========================================================================

    @Operation(
        summary = "Get allele matrix",
        description = """
            Retrieve a paginated slice of the genotype matrix for a variant set.
            Results are organised in two independent dimensions: variants (rows) and
            call sets (columns).  Both dimensions support independent pagination via
            `dimensionVariantPage` / `dimensionCallSetPage` parameters.
            """
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Allele matrix returned",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiResponse.class)))
    })
    @SecurityRequirements
    @GetMapping("/brapi/v2/allelematrix")
    public BrapiResponse<AlleleMatrixDto> getAlleleMatrix(
            @Parameter(description = "VariantSet ID to query")
            @RequestParam(required = false) String variantSetDbId,
            @Parameter(description = "Filter by specific variant IDs")
            @RequestParam(required = false) List<String> variantDbId,
            @Parameter(description = "Filter by specific call set IDs")
            @RequestParam(required = false) List<String> callSetDbId,
            @Parameter(description = "Reference / chromosome name")
            @RequestParam(required = false) String referenceName,
            @Parameter(description = "Start position (inclusive, 0-based)")
            @RequestParam(required = false) Long start,
            @Parameter(description = "End position (exclusive)")
            @RequestParam(required = false) Long end,
            @Parameter(description = "Variant page number (0-based)")
            @RequestParam(required = false) Integer dimensionVariantPage,
            @Parameter(description = "Variants per page")
            @RequestParam(required = false) Integer dimensionVariantPageSize,
            @Parameter(description = "CallSet page number (0-based)")
            @RequestParam(required = false) Integer dimensionCallSetPage,
            @Parameter(description = "CallSets per page")
            @RequestParam(required = false) Integer dimensionCallSetPageSize) {

        List<String> vsIds = variantSetDbId != null ? List.of(variantSetDbId) : List.of();

        AlleleMatrixSearchRequest req = new AlleleMatrixSearchRequest(
                vsIds,
                variantDbId,
                callSetDbId,
                referenceName,
                start,
                end,
                dimensionVariantPage,
                dimensionVariantPageSize,
                dimensionCallSetPage,
                dimensionCallSetPageSize
        );

        return BrapiResponse.of(alleleMatrixService.query(req));
    }

    // =========================================================================
    // POST /brapi/v2/search/allelematrix  — auth required
    // =========================================================================

    @Operation(
        summary = "Submit an allele matrix search",
        description = """
            Submit an allele matrix query. Returns 200 immediately if the query is
            fast enough, or 202 with a `searchResultsDbId` for asynchronous polling.

            Requires `BRAPI_USER` Keycloak realm role.
            """
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Results returned immediately",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiResponse.class))),
        @ApiResponse(responseCode = "202", description = "Search accepted — poll GET with searchResultsDbId",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiSearchResponse.class))),
        @ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "JWT valid but BRAPI_USER role missing")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/brapi/v2/search/allelematrix")
    public ResponseEntity<?> submitSearch(@RequestBody AlleleMatrixSearchRequest req) {
        String searchId = UUID.randomUUID().toString();
        CompletableFuture<AlleleMatrixDto> future = executeAsync(req);
        cache.put(searchId, future);

        if (future.isDone()) {
            return ResponseEntity.ok(BrapiResponse.of(getResult(future)));
        }
        return ResponseEntity.accepted().body(BrapiSearchResponse.of(searchId));
    }

    // =========================================================================
    // GET /brapi/v2/search/allelematrix/{searchResultsDbId}  — auth required
    // =========================================================================

    @Operation(
        summary = "Retrieve async allele matrix results",
        description = """
            Retrieve the result of a previously submitted allele matrix search.
            Returns 200 when ready, 202 while still processing, 404 for unknown IDs.

            Requires `BRAPI_USER` Keycloak realm role.
            """
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Results ready",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiResponse.class))),
        @ApiResponse(responseCode = "202", description = "Still processing — retry shortly",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiSearchResponse.class))),
        @ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "JWT valid but BRAPI_USER role missing"),
        @ApiResponse(responseCode = "404", description = "Unknown searchResultsDbId")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/brapi/v2/search/allelematrix/{searchResultsDbId}")
    public ResponseEntity<?> getSearchResult(
            @Parameter(description = "The searchResultsDbId returned by a prior POST", required = true)
            @PathVariable String searchResultsDbId) {
        CompletableFuture<AlleleMatrixDto> future =
                Optional.ofNullable(cache.get(searchResultsDbId))
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "No search found for searchResultsDbId: " + searchResultsDbId));

        if (!future.isDone()) {
            return ResponseEntity.accepted().body(BrapiSearchResponse.of(searchResultsDbId));
        }
        return ResponseEntity.ok(BrapiResponse.of(getResult(future)));
    }

    // =========================================================================
    // Helpers
    // =========================================================================

    /**
     * Run the allele matrix query asynchronously on the shared task executor.
     * The {@link Async} annotation here is intentionally on a private helper
     * that delegates through the Spring AOP proxy via self-invocation avoidance:
     * we use {@link CompletableFuture#supplyAsync} explicitly so that the call
     * from within the same bean works correctly without a self-proxy.
     */
    private CompletableFuture<AlleleMatrixDto> executeAsync(AlleleMatrixSearchRequest req) {
        return CompletableFuture.supplyAsync(() -> alleleMatrixService.query(req));
    }

    private AlleleMatrixDto getResult(CompletableFuture<AlleleMatrixDto> future) {
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
