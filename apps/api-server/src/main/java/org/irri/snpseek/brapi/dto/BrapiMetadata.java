package org.irri.snpseek.brapi.dto;

import java.util.List;

/**
 * BrAPI standard metadata envelope included in every response.
 */
public record BrapiMetadata(BrapiPagination pagination, List<Object> status, List<String> datafiles) {

    /** Metadata carrying pagination info (for list responses). */
    public static BrapiMetadata of(BrapiPagination pagination) {
        return new BrapiMetadata(pagination, List.of(), List.of());
    }

    /** Metadata without pagination (for search-accepted / single-item responses). */
    public static BrapiMetadata empty() {
        return new BrapiMetadata(null, List.of(), List.of());
    }
}
