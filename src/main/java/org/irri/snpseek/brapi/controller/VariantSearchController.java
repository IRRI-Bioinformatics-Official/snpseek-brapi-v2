package org.irri.snpseek.brapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.irri.snpseek.brapi.domain.SnpMetadata;
import org.irri.snpseek.brapi.dto.*;
import org.irri.snpseek.brapi.service.VariantSearchService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * BrAPI v2.1 Variant search endpoints.
 *
 * <pre>
 * POST /brapi/v2/search/variants
 *   → 200 with results if the search completes before the response is assembled
 *   → 202 with searchResultsDbId if still processing
 *
 * GET  /brapi/v2/search/variants/{searchResultsDbId}
 *   → 200 with results when ready
 *   → 202 with searchResultsDbId while still processing
 *   → 404 if the ID is unknown
 * </pre>
 *
 * Security: both endpoints require the {@code BRAPI_USER} role
 * (enforced via {@code /brapi/v2/search/variants/**} in
 * {@link org.irri.snpseek.brapi.security.SecurityConfig}).
 */
@Tag(name = "Variants", description = "Search and retrieve SNP variant metadata (BrAPI v2.1)")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/brapi/v2/search/variants")
public class VariantSearchController {

    private final VariantSearchService searchService;

    public VariantSearchController(VariantSearchService searchService) {
        this.searchService = searchService;
    }

    // -------------------------------------------------------------------------
    // POST /brapi/v2/search/variants
    // -------------------------------------------------------------------------

    @Operation(
        summary = "Submit a variant search",
        description = """
            Submit a search query for SNP variant metadata. All filter fields are optional.

            - Returns **200** with results immediately for fast queries.
            - Returns **202** with a `searchResultsDbId` for large/slow queries — poll the GET endpoint to retrieve results.

            Requires `BRAPI_USER` Keycloak realm role.
            """
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Results returned immediately",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiListResponse.class))),
        @ApiResponse(responseCode = "202", description = "Search accepted, still processing — use GET with the returned searchResultsDbId",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiSearchResponse.class))),
        @ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "JWT valid but BRAPI_USER role missing")
    })
    @PostMapping
    public ResponseEntity<?> submitSearch(@RequestBody VariantSearchRequest request) {
        String searchId = UUID.randomUUID().toString();
        CompletableFuture<Page<SnpMetadata>> future = searchService.executeAsync(request);
        searchService.cacheSearch(searchId, future);

        // Return results immediately if the async task finished synchronously
        // (common for small result sets / fast queries).
        if (future.isDone()) {
            return ResponseEntity.ok(toListResponse(future, request.effectivePage(), request.effectivePageSize()));
        }

        return ResponseEntity.accepted().body(BrapiSearchResponse.of(searchId));
    }

    // -------------------------------------------------------------------------
    // GET /brapi/v2/search/variants/{searchResultsDbId}
    // -------------------------------------------------------------------------

    @Operation(
        summary = "Retrieve async search results",
        description = """
            Retrieve results of a previously submitted search.

            - **200** — results are ready.
            - **202** — still processing, retry after a moment.
            - **404** — unknown `searchResultsDbId`.

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
    @GetMapping("/{searchResultsDbId}")
    public ResponseEntity<?> getSearchResult(
            @Parameter(description = "The search ID returned by a prior POST", required = true)
            @PathVariable String searchResultsDbId) {
        CompletableFuture<Page<SnpMetadata>> future = searchService.getResult(searchResultsDbId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No search found for searchResultsDbId: " + searchResultsDbId));

        if (!future.isDone()) {
            return ResponseEntity.accepted().body(BrapiSearchResponse.of(searchResultsDbId));
        }

        // Page/size are fixed to whatever was specified in the original POST.
        Page<SnpMetadata> page = getPage(future);
        return ResponseEntity.ok(toListResponse(page));
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private BrapiListResponse<Variant> toListResponse(
            CompletableFuture<Page<SnpMetadata>> future, int page, int pageSize) {
        return toListResponse(getPage(future));
    }

    private BrapiListResponse<Variant> toListResponse(Page<SnpMetadata> page) {
        return BrapiListResponse.of(
                page.getContent().stream().map(Variant::from).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }

    private Page<SnpMetadata> getPage(CompletableFuture<Page<SnpMetadata>> future) {
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
