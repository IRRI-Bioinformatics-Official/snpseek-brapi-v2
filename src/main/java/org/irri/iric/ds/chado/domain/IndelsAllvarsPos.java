package org.irri.iric.ds.chado.domain;

/**
 * Indel feature at position
 * 
 * @author LMansueto
 *
 */
public interface IndelsAllvarsPos extends SnpsAllvarsPos, Comparable {

	/**
	 * Maximum deletion length in all varieties
	 * 
	 * @return
	 */
	public Integer getMaxDellength();

	/**
	 * Insertion string in all varieties format: IUPAC base sequence for insertion,
	 * deletion length for deletion
	 * 
	 * @return
	 */
	public String getInsString();

	/**
	 * Maximum insertion length in all varieties
	 * 
	 * @return
	 */
	public Integer getMaxInsLength();

	// public Integer getDellength();
}
