package org.irri.iric.ds.chado.domain;

/**
 * BLAST result
 * 
 * @author LMansueto
 *
 */
public interface LocalAlignment {

	/**
	 * Query accession
	 * 
	 * @return
	 */
	public String getQacc();

	/**
	 * Subject accession
	 * 
	 * @return
	 */
	public String getSacc();

	/**
	 * E-value
	 * 
	 * @return
	 */
	public Double getEvalue();

	/**
	 * Query start
	 * 
	 * @return
	 */
	public Long getQstart();

	/**
	 * Query end
	 * 
	 * @return
	 */
	public Long getQend();

	/**
	 * Subject start
	 * 
	 * @return
	 */
	public Long getSstart();

	/**
	 * Subject end
	 * 
	 * @return
	 */
	public Long getSend();

	/**
	 * Subject strand
	 * 
	 * @return
	 */
	public Integer getSstrand();

	/**
	 * Raw score
	 * 
	 * @return
	 */
	public Double getRawScore();

	/**
	 * Alignment length
	 * 
	 * @return
	 */
	public Long getAlignmentLength();

	/**
	 * Percent matches
	 * 
	 * @return
	 */
	public Double getPercentMatches();

	/**
	 * Number of matches
	 * 
	 * @return
	 */
	public Long getMatches();

	/**
	 * Number of mismatches
	 * 
	 * @return
	 */
	public Long getMismatches();

	public String getQSequence();

	public String getSSequence();

	public void setQSequence(String seq);

	public void setSSequence(String seq);

	/*
	 * blast result tabular result columns score Raw score length means Alignment
	 * length pident means Percentage of identical matches nident means Number of
	 * identical matches mismatch means Number of mismatches
	 */

}
