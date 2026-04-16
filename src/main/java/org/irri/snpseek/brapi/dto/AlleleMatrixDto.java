package org.irri.snpseek.brapi.dto;

import java.util.List;

/**
 * BrAPI v2.1 AlleleMatrix result object.
 *
 * <p>Wraps a rectangular slice of the genotype matrix together with the
 * two-dimensional pagination metadata required by the BrAPI spec.
 */
public record AlleleMatrixDto(
        List<String>       callSetDbIds,
        List<String>       variantDbIds,
        List<String>       variantSetDbIds,
        List<DataMatrix>   dataMatrices,
        boolean            expandHomozygotes,
        String             sepPhased,
        String             sepUnphased,
        String             unknownString,
        AlleleMatrixPagination pagination
) {

    /**
     * A single data layer in the allele matrix response (typically one layer
     * carrying the GT genotype strings).
     */
    public record DataMatrix(
            String             dataType,
            List<List<String>> dataMatrix,
            String             dataMatrixAbbreviation,
            boolean            phased
    ) {}

    /**
     * Two-dimensional pagination metadata for the allele matrix.
     * Each dimension is paged independently: variants (rows) and call sets (columns).
     */
    public record AlleleMatrixPagination(
            int  dimensionVariantPage,
            int  dimensionVariantPageSize,
            long dimensionVariantTotal,
            int  dimensionCallSetPage,
            int  dimensionCallSetPageSize,
            long dimensionCallSetTotal
    ) {}
}
