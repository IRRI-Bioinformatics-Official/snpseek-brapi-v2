package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Locus interface
 * 
 * @author LMansueto
 *
 */
public interface Locus extends Comparable {

	/**
	 * Locus name
	 * 
	 * @return
	 */
	public String getUniquename();

	/**
	 * Chromosome name
	 * 
	 * @return
	 */
	public Long getChr();

	/**
	 * Locus start position
	 * 
	 * @return
	 */
	public Integer getFmin();

	/**
	 * Locus stop position
	 * 
	 * @return
	 */
	public Integer getFmax();

	/**
	 * Strand
	 * 
	 * @return
	 */
	public Integer getStrand();

	/**
	 * contig name
	 * 
	 * @return
	 */
	public String getContig();

	/**
	 * Decription
	 * 
	 * @return
	 */
	public String getDescription();

	public BigDecimal getFeatureId();

	// public String printFields(String delimiter);

	public String getFeatureType();

}
