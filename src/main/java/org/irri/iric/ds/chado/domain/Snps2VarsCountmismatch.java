package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Container entity for allele mismatch count. The actual query should be
 * constrained by chromosome and position range
 * 
 * @author lmansueto
 *
 */
public interface Snps2VarsCountmismatch extends VarietyDistance {

	/**
	 * Variety1 Id
	 * 
	 * @return
	 */
	BigDecimal getVar1();

	/**
	 * Variety2 Id
	 * 
	 * @return
	 */
	BigDecimal getVar2();

	/**
	 * Allele mismatches between varieties 1 and 2
	 * 
	 * @return
	 */
	BigDecimal getMismatch();
}
