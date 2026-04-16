package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface VarietyByPassportDAO {

	/**
	 * Get varieties with passport equal to value
	 * 
	 * @param object
	 * @param value
	 * @param value2
	 * @return
	 */
	// List findVarietyByPassportEquals(Object object, Set dataset, String value);

	/**
	 * Get passport values for all varieties
	 * 
	 * @param sPassId
	 * @return
	 */
	List findVarietyByPassport(String sPassId);

	List findVarietyByPassportEquals(BigDecimal type_id, Set dataset, String value);

	// List findVIricstocksByPassportByTypeIdValueContaining(BigDecimal type_id,
	// String value);

}
