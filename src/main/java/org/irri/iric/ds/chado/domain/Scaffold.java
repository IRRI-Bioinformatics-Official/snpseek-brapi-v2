package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Interface for scaffold/contig
 * 
 * @author LMansueto
 *
 */
public interface Scaffold {

	/**
	 * DB Id
	 * 
	 * @return
	 */
	public BigDecimal getFeatureId();

	/**
	 * Scaffold length
	 * 
	 * @return
	 */
	public long getLength();

	/**
	 * scaffold/contig Name
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * scaffold/contig unique name
	 * 
	 * @return
	 */
	public String getUniquename();
}
