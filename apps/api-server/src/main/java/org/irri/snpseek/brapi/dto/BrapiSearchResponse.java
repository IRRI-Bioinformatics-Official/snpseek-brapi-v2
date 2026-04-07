package org.irri.snpseek.brapi.dto;

/**
 * BrAPI 202-Accepted response returned when a search has been submitted but
 * results are not yet available:
 * <pre>
 * { "metadata": {...}, "result": { "searchResultsDbId": "uuid" } }
 * </pre>
 */
public record BrapiSearchResponse(BrapiMetadata metadata, SearchResult result) {

    public record SearchResult(String searchResultsDbId) {}

    public static BrapiSearchResponse of(String searchResultsDbId) {
        return new BrapiSearchResponse(BrapiMetadata.empty(), new SearchResult(searchResultsDbId));
    }
}
