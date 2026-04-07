package org.irri.snpseek.brapi.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.irri.snpseek.brapi.domain.SnpMetadata;
import org.irri.snpseek.brapi.dto.Variant;
import org.irri.snpseek.brapi.dto.VariantSearchRequest;
import org.irri.snpseek.brapi.repository.SnpMetadataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VariantSearchServiceTest {

    @Mock
    SnpMetadataRepository repository;

    @Mock
    CriteriaBuilder cb;

    @Mock
    CriteriaQuery<?> query;

    @Mock
    Root<SnpMetadata> root;

    @Mock
    Predicate predicate;

    VariantSearchService service;

    @BeforeEach
    void setUp() {
        service = new VariantSearchService(repository);
    }

    // -------------------------------------------------------------------------
    // Specification builder
    // -------------------------------------------------------------------------

    @Test
    void buildSpec_emptyRequest_returnsConjunction() {
        VariantSearchRequest req = new VariantSearchRequest(null, null, null, null, null, null, null);

        when(cb.conjunction()).thenReturn(predicate);

        Specification<SnpMetadata> spec = service.buildSpec(req);
        Predicate result = spec.toPredicate(root, query, cb);

        verify(cb).conjunction();
        assertThat(result).isEqualTo(predicate);
    }

    @Test
    void buildSpec_withVariantDbIds_addsInPredicate() {
        VariantSearchRequest req = new VariantSearchRequest(
                List.of("101", "202"), null, null, null, null, null, null);

        var path = mock(jakarta.persistence.criteria.Path.class);
        when(root.get("snpFeatureId")).thenReturn(path);
        when(path.in(List.of(101, 202))).thenReturn(predicate);
        when(cb.and(any(Predicate[].class))).thenReturn(predicate);

        Specification<SnpMetadata> spec = service.buildSpec(req);
        spec.toPredicate(root, query, cb);

        verify(root).get("snpFeatureId");
    }

    @Test
    void buildSpec_withReferenceNames_parsesChromosomeAsInteger() {
        VariantSearchRequest req = new VariantSearchRequest(
                null, null, List.of("1", "2"), null, null, null, null);

        var path = mock(jakarta.persistence.criteria.Path.class);
        when(root.get("chromosome")).thenReturn(path);
        when(path.in(List.of(1, 2))).thenReturn(predicate);
        when(cb.and(any(Predicate[].class))).thenReturn(predicate);

        Specification<SnpMetadata> spec = service.buildSpec(req);
        spec.toPredicate(root, query, cb);

        verify(root).get("chromosome");
    }

    @Test
    void buildSpec_withStartAndEnd_addsRangePredicates() {
        VariantSearchRequest req = new VariantSearchRequest(
                null, null, null, 1000L, 2000L, null, null);

        var posPath = mock(jakarta.persistence.criteria.Path.class);
        when(root.<Integer>get("position")).thenReturn(posPath);
        when(cb.greaterThanOrEqualTo(any(), eq(1000))).thenReturn(predicate);
        when(cb.lessThan(any(), eq(2000))).thenReturn(predicate);
        when(cb.and(any(Predicate[].class))).thenReturn(predicate);

        Specification<SnpMetadata> spec = service.buildSpec(req);
        spec.toPredicate(root, query, cb);

        verify(cb).greaterThanOrEqualTo(any(), eq(1000));
        verify(cb).lessThan(any(), eq(2000));
    }

    // -------------------------------------------------------------------------
    // Pagination defaults
    // -------------------------------------------------------------------------

    @Test
    void effectivePage_defaultsToZero() {
        VariantSearchRequest req = new VariantSearchRequest(null, null, null, null, null, null, null);
        assertThat(req.effectivePage()).isZero();
    }

    @Test
    void effectivePageSize_defaultsToOneThousand() {
        VariantSearchRequest req = new VariantSearchRequest(null, null, null, null, null, null, null);
        assertThat(req.effectivePageSize()).isEqualTo(1000);
    }

    // -------------------------------------------------------------------------
    // Variant mapping
    // -------------------------------------------------------------------------

    @Test
    void variant_from_mapsAllFields() {
        SnpMetadata m = buildSnpMetadata(42, 1, 500, "VS1", "A", "T");

        Variant v = Variant.from(m);

        assertThat(v.variantDbId()).isEqualTo("42");
        assertThat(v.referenceName()).isEqualTo("1");
        assertThat(v.start()).isEqualTo(500L);
        assertThat(v.end()).isEqualTo(501L);
        assertThat(v.referenceBases()).isEqualTo("A");
        assertThat(v.alternateBases()).containsExactly("T");
        assertThat(v.variantSetDbIds()).containsExactly("VS1");
    }

    @Test
    void variant_from_splitsMultipleAltAlleles() {
        SnpMetadata m = buildSnpMetadata(1, 1, 100, "VS2", "C", "A/G");

        Variant v = Variant.from(m);

        assertThat(v.alternateBases()).containsExactly("A", "G");
    }

    @Test
    void variant_from_handlesNullAltcall() {
        SnpMetadata m = buildSnpMetadata(1, 1, 100, "VS1", "A", null);

        Variant v = Variant.from(m);

        assertThat(v.alternateBases()).isEmpty();
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /** Reflectively create a SnpMetadata for testing (no public constructor). */
    private SnpMetadata buildSnpMetadata(
            int snpFeatureId, int chromosome, int position,
            String variantset, String refcall, String altcall) {
        try {
            SnpMetadata m = new SnpMetadata();
            setField(m, "snpFeatureId", snpFeatureId);
            setField(m, "chromosome",   chromosome);
            setField(m, "position",     position);
            setField(m, "variantset",   variantset);
            setField(m, "refcall",      refcall);
            setField(m, "altcall",      altcall);
            return m;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        var field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
