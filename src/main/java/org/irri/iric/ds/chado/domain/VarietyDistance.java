package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Distance between two varieties for use in phylogenetic tree calculation
 * 
 * @author lmansueto
 *
 */
public interface VarietyDistance {

	/**
	 * Variety 1 Id
	 * 
	 * @return
	 */
	public BigDecimal getVar1();

	/**
	 * Variety 2 Id
	 * 
	 * @return
	 */
	public BigDecimal getVar2();

	/**
	 * Distance [0..1]
	 * 
	 * @return
	 */
	public BigDecimal getDist();
}
