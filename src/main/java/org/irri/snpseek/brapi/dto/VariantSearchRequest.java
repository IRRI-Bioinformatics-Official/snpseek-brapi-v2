package org.irri.snpseek.brapi.dto;

import java.util.List;

/**
 * Request body for {@code POST /brapi/v2/search/variants}.
 *
 * <p>All fields are optional; omitting a filter means "no restriction on that field".
 * {@code page} and {@code pageSize} follow BrAPI 0-based page conventions.
 */
public record VariantSearchRequest(
        List<String> variantDbIds,
        List<String> variantSetDbIds,
        List<String> referenceNames,
        Long         start,
        Long         end,
        Integer      page,
        Integer      pageSize
) {
    public int effectivePage()     { return page     != null ? page     : 0;    }
    public int effectivePageSize() { return pageSize != null ? pageSize : 1000; }
}
