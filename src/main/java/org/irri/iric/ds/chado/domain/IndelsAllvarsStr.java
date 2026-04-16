package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Indel allele String
 * 
 * @author LMansueto
 *
 */
public interface IndelsAllvarsStr extends IndelsAllvars {

	/**
	 * Allele1
	 * 
	 * @return
	 */
	public String getAllele1Str();

	/**
	 * Allele2
	 * 
	 * @return
	 */
	public String getAllele2Str();

	public void setAllele1(BigDecimal allele1);

	public void setAllele2(BigDecimal allele2);

}
