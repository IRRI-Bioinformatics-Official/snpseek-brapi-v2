package org.irri.snpseek.brapi.dto;

import org.irri.snpseek.brapi.domain.VariantSet;

import java.util.Arrays;
import java.util.List;

/**
 * BrAPI v2.1 VariantSet response object.
 *
 * <p>Mapped from {@link VariantSet}:
 * <ul>
 *   <li>{@code variantSetDbId}     ← String.valueOf(variantSetId)</li>
 *   <li>{@code variantSetName}     ← name</li>
 *   <li>{@code variantSetDescription} ← description</li>
 *   <li>{@code referenceSetDbId}   ← null (not modelled)</li>
 *   <li>{@code availableFormats}   ← provided by caller via GenotypeStorageService</li>
 *   <li>{@code variantCount}       ← provided by caller (count from SnpMetadataRepository)</li>
 *   <li>{@code callSetCount}       ← provided by caller (count from GermplasmRepository)</li>
 * </ul>
 */
public record VariantSetDto(
        String       variantSetDbId,
        String       variantSetName,
        String       variantSetDescription,
        String       referenceSetDbId,
        List<String> availableFormats,
        Long         variantCount,
        Long         callSetCount
) {

    public static VariantSetDto from(VariantSet vs, Long variantCount, Long callSetCount, String[] formats) {
        // organism_id maps to referenceSetDbId (organism = genome assembly in this schema)
        String refSetDbId = vs.getOrganismId() != null ? String.valueOf(vs.getOrganismId()) : null;
        return new VariantSetDto(
                String.valueOf(vs.getVariantSetId()),
                vs.getName(),
                vs.getDescription(),
                refSetDbId,
                formats != null ? Arrays.asList(formats) : List.of(),
                variantCount,
                callSetCount
        );
    }
}
