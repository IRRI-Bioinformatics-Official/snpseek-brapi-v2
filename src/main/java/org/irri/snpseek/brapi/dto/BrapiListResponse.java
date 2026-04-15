package org.irri.snpseek.brapi.dto;

import java.util.List;

/**
 * Generic BrAPI list response envelope:
 * <pre>
 * { "metadata": { "pagination": {...}, ... }, "result": { "data": [...] } }
 * </pre>
 *
 * @param <T> element type of the {@code data} array
 */
public record BrapiListResponse<T>(BrapiMetadata metadata, DataResult<T> result) {

    public record DataResult<T>(List<T> data) {}

    public static <T> BrapiListResponse<T> of(List<T> data, int page, int size, long total) {
        return new BrapiListResponse<>(
            BrapiMetadata.of(BrapiPagination.of(page, size, total)),
            new DataResult<>(data)
        );
    }
}
