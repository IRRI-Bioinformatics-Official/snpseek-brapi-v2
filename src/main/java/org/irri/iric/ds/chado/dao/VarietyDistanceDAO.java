package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.ds.chado.domain.VarietyDistance;

public interface VarietyDistanceDAO {

	/**
	 * Get distance between varieties for variety IDs in germplasms
	 * 
	 * @param germplasms
	 * @return
	 */
	List<VarietyDistance> findVarieties(Set<BigDecimal> germplasms);

	/**
	 * Get distance between varieties for all varieties
	 * 
	 * @param germplasms
	 * @return
	 */
	List<VarietyDistance> findAllVarieties();

	List<VarietyDistance> findAllVarietiesTopN(Integer topN);

	void setRequestId(String requestid);

}
