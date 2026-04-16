package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Interface for phenotype values
 * 
 * @author LMansueto
 *
 */
public interface Phenotype {

	/**
	 * Phenotype ID
	 * 
	 * @return
	 */
	public BigDecimal getPhenotypeId();

	/**
	 * Phenotype controlled term name
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Human readable description
	 * 
	 * @return
	 */
	public String getDefinition();

	/**
	 * Quantitative value
	 * 
	 * @return
	 */
	public BigDecimal getQuanValue();

	/**
	 * Qualitative value
	 * 
	 * @return
	 */
	public String getQualValue();
}
