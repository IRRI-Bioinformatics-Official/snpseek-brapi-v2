package org.irri.snpseek.brapi.dto;

import org.irri.snpseek.brapi.domain.SnpMetadata;

import java.util.Arrays;
import java.util.List;

/**
 * BrAPI v2.1 Variant object.
 *
 * <p>Mapped from {@link SnpMetadata} ({@code v_snp_refposindex_v2}) as follows:
 * <ul>
 *   <li>{@code variantDbId}    ← {@code snp_feature_id} (stringified)</li>
 *   <li>{@code referenceName}  ← {@code chromosome} (stringified)</li>
 *   <li>{@code start}          ← {@code position} (0-based, inclusive)</li>
 *   <li>{@code end}            ← {@code position + 1} (exclusive — SNV default)</li>
 *   <li>{@code referenceBases} ← {@code refcall}</li>
 *   <li>{@code alternateBases} ← {@code altcall} split on "/" or ","</li>
 *   <li>{@code variantSetDbIds}← {@code variantset} as a single-element list</li>
 * </ul>
 */
public record Variant(
        String       variantDbId,
        String       referenceName,
        Long         start,
        Long         end,
        String       referenceBases,
        List<String> alternateBases,
        List<String> variantSetDbIds
) {

    public static Variant from(SnpMetadata m) {
        List<String> altBases = (m.getAltcall() == null || m.getAltcall().isBlank())
                ? List.of()
                : Arrays.asList(m.getAltcall().split("[/,]"));

        return new Variant(
                String.valueOf(m.getSnpFeatureId()),
                String.valueOf(m.getChromosome()),
                m.getPosition() == null ? null : m.getPosition().longValue(),
                m.getPosition() == null ? null : m.getPosition().longValue() + 1L,
                m.getRefcall(),
                altBases,
                m.getVariantset() == null ? List.of() : List.of(m.getVariantset())
        );
    }
}
