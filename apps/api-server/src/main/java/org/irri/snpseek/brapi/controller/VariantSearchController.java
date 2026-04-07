package org.irri.snpseek.brapi.controller;

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

    @GetMapping("/{searchResultsDbId}")
    public ResponseEntity<?> getSearchResult(@PathVariable String searchResultsDbId) {
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
