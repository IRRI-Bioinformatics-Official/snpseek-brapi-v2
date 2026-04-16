package org.irri.iric.ds.chado.domain;

import java.util.Map;

/**
 * Variety plus multiple Passport Value and/or Phenotype Value
 * 
 * @author LMansueto
 *
 */
public interface VarietyPlusPlus extends VarietyPlus {

	/**
	 * Get map of passport/phenotype name to values
	 * 
	 * @return
	 */
	Map getValueMap();

	/**
	 * Add field name with value
	 * 
	 * @param name
	 * @param value
	 */
	void addValue(String name, Object value);

}
