package org.irri.snpseek.brapi.repository;

import org.irri.snpseek.brapi.domain.Germplasm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for the {@code v_allstock_basicprop} view.
 * {@link JpaSpecificationExecutor} enables dynamic query building in
 * {@link org.irri.snpseek.brapi.service.GermplasmService}.
 */
public interface GermplasmRepository
        extends JpaRepository<Germplasm, Integer>,
                JpaSpecificationExecutor<Germplasm> {

    @Query(value = "SELECT * FROM public.v_allstock_basicprop WHERE stock_sample_id = :stockSampleId",
           nativeQuery = true)
    Optional<Germplasm> findByStockSampleId(@Param("stockSampleId") Integer stockSampleId);
}
