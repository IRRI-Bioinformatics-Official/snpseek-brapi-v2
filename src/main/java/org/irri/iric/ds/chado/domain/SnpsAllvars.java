package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Container entity for SNP-Genotype data
 * 
 * @author lmansueto
 *
 */
public interface SnpsAllvars extends Position {

	/**
	 * Variety Id
	 * 
	 * @return
	 */
	public BigDecimal getVar();

	/**
	 * Variety allele at the position
	 * 
	 * @return
	 */
	public String getVarnuc();

}
