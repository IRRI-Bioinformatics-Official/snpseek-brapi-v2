package org.irri.snpseek.brapi.repository;

import org.irri.snpseek.brapi.domain.Germplasm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository for the {@code v_allstock_basicprop} view.
 * {@link JpaSpecificationExecutor} enables dynamic query building in
 * {@link org.irri.snpseek.brapi.service.GermplasmService}.
 */
public interface GermplasmRepository
        extends JpaRepository<Germplasm, Long>,
                JpaSpecificationExecutor<Germplasm> {
}
