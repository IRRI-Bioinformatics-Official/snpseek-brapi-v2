package org.irri.snpseek.brapi.dto;

/**
 * BrAPI standard pagination block included in every list-response metadata.
 */
public record BrapiPagination(int currentPage, int pageSize, long totalCount, int totalPages) {

    public static BrapiPagination of(int page, int size, long total) {
        int pages = size == 0 ? 0 : (int) Math.ceil((double) total / size);
        return new BrapiPagination(page, size, total, pages);
    }
}
