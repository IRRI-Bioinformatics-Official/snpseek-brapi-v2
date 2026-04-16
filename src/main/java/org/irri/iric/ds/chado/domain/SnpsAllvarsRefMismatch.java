package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Container entity for allele mismatch count between variety and reference The
 * actual query should be constrained by chromosome and position range
 * 
 * @author lmansueto
 *
 */
public interface SnpsAllvarsRefMismatch {

	/**
	 * Variety Id
	 * 
	 * @return
	 */
	public BigDecimal getVar();

	/**
	 * Number of allele mismatches with the reference
	 * 
	 * @return
	 */
	public BigDecimal getMismatch();

	public void setMismatch(BigDecimal mismatch);

}
