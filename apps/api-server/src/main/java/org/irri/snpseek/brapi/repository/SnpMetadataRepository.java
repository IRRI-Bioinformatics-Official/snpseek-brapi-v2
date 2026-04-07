package org.irri.snpseek.brapi.repository;

import org.irri.snpseek.brapi.domain.SnpMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository for the {@code v_snp_refposindex_v2} view.
 * {@link JpaSpecificationExecutor} enables dynamic query building
 * in {@link org.irri.snpseek.brapi.service.VariantSearchService}.
 */
public interface SnpMetadataRepository
        extends JpaRepository<SnpMetadata, Integer>,
                JpaSpecificationExecutor<SnpMetadata> {
}
