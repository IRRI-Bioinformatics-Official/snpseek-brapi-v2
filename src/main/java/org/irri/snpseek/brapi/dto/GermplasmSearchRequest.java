package org.irri.snpseek.brapi.dto;

import java.util.List;

/**
 * Request body for {@code POST /brapi/v2/search/germplasm}.
 *
 * <p>All fields are optional; omitting a filter means "no restriction on that field".
 * {@code page} and {@code pageSize} follow BrAPI 0-based page conventions.
 */
public record GermplasmSearchRequest(
        List<String> germplasmDbIds,
        List<String> germplasmNames,
        List<String> accessionNumbers,
        String       commonCropName,
        List<String> countries,
        List<String> subpopulations,
        List<String> variantSetDbIds,
        Integer      page,
        Integer      pageSize
) {
    public int effectivePage()     { return page     != null ? page     : 0;    }
    public int effectivePageSize() { return pageSize != null ? pageSize : 1000; }
}
