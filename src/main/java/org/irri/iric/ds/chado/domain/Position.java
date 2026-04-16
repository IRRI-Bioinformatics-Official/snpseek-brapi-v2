package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Interface for genome position
 * 
 * @author LMansueto
 *
 */
public interface Position extends Comparable {

	/**
	 * contig name
	 * 
	 * @return
	 */
	public String getContig();

	/**
	 * bp position
	 * 
	 * @return
	 */
	public BigDecimal getPosition();

	/**
	 * reference nuc
	 * 
	 * @return
	 */
	public String getRefnuc();

	/**
	 * chromosome no.
	 * 
	 * @return
	 */
	public Long getChr();

}
