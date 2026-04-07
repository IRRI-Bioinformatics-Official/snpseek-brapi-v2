package org.irri.snpseek.brapi.repository;

import org.irri.snpseek.brapi.domain.VariantSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VariantSetRepository extends JpaRepository<VariantSet, Integer> {

    /**
     * Load a VariantSet with its full run graph
     * ({@code variantset → platform → genotype_run}) in two SQL joins,
     * avoiding N+1 when the service resolves a {@code data_location}.
     *
     * <p>Only one query is issued because both joins use {@code LEFT JOIN FETCH}.
     * Hibernate deduplicates the cartesian product automatically.
     */
    @Query("""
        SELECT DISTINCT vs FROM VariantSet vs
        LEFT JOIN FETCH vs.platforms p
        LEFT JOIN FETCH p.genotypeRuns
        WHERE vs.variantSetId = :id
        """)
    Optional<VariantSet> findByIdWithGenotypeRuns(@Param("id") Integer id);
}
