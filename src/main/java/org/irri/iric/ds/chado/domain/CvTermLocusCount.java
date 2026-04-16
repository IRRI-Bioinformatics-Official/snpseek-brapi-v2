package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Count loci linked with CV term (no implementation yet)
 * 
 * @author LMansueto
 *
 */
public interface CvTermLocusCount {

	/**
	 * Term accession
	 * 
	 * @return
	 */
	public String getAccession();

	/**
	 * Term
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Loci count
	 * 
	 * @return
	 */
	public BigDecimal getCount();

	/**
	 * CV
	 * 
	 * @return
	 */
	public String getCV();
}
