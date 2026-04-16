package org.irri.snpseek.brapi.dto;

import java.util.List;

/**
 * Request for BrAPI v2.1 AlleleMatrix endpoints (GET query params or POST body).
 *
 * <p>The allele matrix supports independent pagination along two dimensions:
 * <ul>
 *   <li><em>variant dimension</em>  — rows (SNP positions), default page size 1000</li>
 *   <li><em>call set dimension</em> — columns (samples),     default page size 1000</li>
 * </ul>
 *
 * <p>All filter fields are optional; omitting one means "no restriction".
 */
public record AlleleMatrixSearchRequest(
        List<String> variantSetDbIds,
        List<String> variantDbIds,
        List<String> callSetDbIds,
        String       referenceName,
        Long         start,
        Long         end,
        Integer      dimensionVariantPage,
        Integer      dimensionVariantPageSize,
        Integer      dimensionCallSetPage,
        Integer      dimensionCallSetPageSize
) {
    public int effectiveVariantPage()     { return dimensionVariantPage     != null ? dimensionVariantPage     : 0;    }
    public int effectiveVariantPageSize() { return dimensionVariantPageSize != null ? dimensionVariantPageSize : 1000; }
    public int effectiveCallSetPage()     { return dimensionCallSetPage     != null ? dimensionCallSetPage     : 0;    }
    public int effectiveCallSetPageSize() { return dimensionCallSetPageSize != null ? dimensionCallSetPageSize : 1000; }
}
