package org.irri.snpseek.brapi.dto;

import org.irri.snpseek.brapi.domain.Germplasm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BrAPI v2.1 CallSet response object.
 *
 * <p>A CallSet represents a single sample's genotype calls within one or more
 * VariantSets.  In SNPseek the HDF5 column index ({@code stock_sample_id}) is
 * the stable identifier used to address a sample inside the genotype matrix.
 *
 * <p>Mapped from {@link Germplasm} ({@code v_allstock_basicprop}):
 * <ul>
 *   <li>{@code callSetDbId}  ← String.valueOf(stockSampleId) (HDF5 column index)</li>
 *   <li>{@code callSetName}  ← name</li>
 *   <li>{@code germplasmDbId}← String.valueOf(stockId)</li>
 *   <li>{@code variantSetIds}← provided by caller (resolved from dataset)</li>
 *   <li>{@code additionalInfo} ← map with dataset</li>
 * </ul>
 */
public record CallSetDto(
        String callSetDbId,
        String callSetName,
        String germplasmDbId,
        List<String> variantSetIds,
        Map<String, String> additionalInfo
) {

    public static CallSetDto from(Germplasm g, List<String> variantSetIds) {
        Map<String, String> info = new HashMap<>();
        if (g.getDataset() != null) info.put("dataset", g.getDataset());

        return new CallSetDto(
                String.valueOf(g.getStockSampleId()),
                g.getName(),
                String.valueOf(g.getStockId()),
                List.copyOf(variantSetIds),
                Map.copyOf(info)
        );
    }
}
