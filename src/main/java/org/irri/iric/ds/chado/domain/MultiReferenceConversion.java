package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Position conversion between two reference genomes
 * 
 * @author LMansueto
 *
 */
public interface MultiReferenceConversion {

	/**
	 * Source reference genome
	 * 
	 * @return
	 */
	String getFromOrganism();

	String getFromContig();

	BigDecimal getFromPosition();

	String getFromRefcall();

	/**
	 * Target reference genome
	 * 
	 * @return
	 */
	String getToOrganism();

	String getToContig();

	BigDecimal getToPosition();

	String getToRefcall();

}
