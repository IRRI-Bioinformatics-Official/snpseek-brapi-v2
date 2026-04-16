package org.irri.snpseek.brapi.dto;

import org.irri.snpseek.brapi.domain.Germplasm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BrAPI v2.1 Germplasm response object.
 *
 * <p>Mapped from {@link Germplasm} ({@code v_allstock_basicprop}):
 * <ul>
 *   <li>{@code germplasmDbId}      ← String.valueOf(stockId)</li>
 *   <li>{@code germplasmName}      ← name</li>
 *   <li>{@code defaultDisplayName} ← name</li>
 *   <li>{@code accessionNumber}    ← accessionNumber</li>
 *   <li>{@code commonCropName}     = "Oryza sativa"</li>
 *   <li>{@code countryOfOriginCode}← country</li>
 *   <li>{@code subtaxa}            ← subpopulation</li>
 *   <li>{@code synonyms}           = empty list</li>
 *   <li>{@code additionalInfo}     ← map of non-null irisId, boxCode, dataset</li>
 * </ul>
 */
public record GermplasmDto(
        String germplasmDbId,
        String germplasmName,
        String defaultDisplayName,
        String accessionNumber,
        String commonCropName,
        String countryOfOriginCode,
        String subtaxa,
        List<String> synonyms,
        Map<String, String> additionalInfo
) {

    public static GermplasmDto from(Germplasm g) {
        Map<String, String> info = new HashMap<>();
        if (g.getIrisId()  != null) info.put("irisId",  g.getIrisId());
        if (g.getBoxCode() != null) info.put("boxCode", g.getBoxCode());
        if (g.getDataset() != null) info.put("dataset", g.getDataset());

        return new GermplasmDto(
                String.valueOf(g.getStockId()),
                g.getName(),
                g.getName(),
                g.getAccessionNumber(),
                "Oryza sativa",
                g.getCountry(),
                g.getSubpopulation(),
                List.of(),
                Map.copyOf(info)
        );
    }
}
