package org.irri.iric.ds.chado.domain;

/**
 * Genomic sequence
 * 
 * @author LMansueto
 *
 */
public interface Sequence {

	/**
	 * Sequence from position start to end
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public String getSequenceInPosition(long start, long end);

}
