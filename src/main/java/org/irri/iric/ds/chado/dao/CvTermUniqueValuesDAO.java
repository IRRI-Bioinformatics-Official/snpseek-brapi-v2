package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;

import java.util.Set;

public interface CvTermUniqueValuesDAO {

	/**
	 * Unique values of cvterm
	 * 
	 * @param object
	 * @param dataset
	 * @return
	 */
	// public Set getUniqueValues(Object object, Set dataset);

	public Set getUniqueValues(BigDecimal typeId, Set dataset);

}
