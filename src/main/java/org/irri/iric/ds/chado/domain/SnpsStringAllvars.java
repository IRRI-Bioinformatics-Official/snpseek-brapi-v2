package org.irri.iric.ds.chado.domain;

import java.util.Map;
import java.util.Set;

public interface SnpsStringAllvars extends SnpsAllvarsRefMismatch { // , SnpsAllvars {

	/**
	 * Variety allele at the position
	 * 
	 * @return
	 */
	public String getVarnuc();

	public String getDataset();

	/**
	 * Map of position to allele2 for heterozygous SNPs
	 * 
	 * @return
	 */
	public Map<Position, Character> getMapPos2Allele2();

	/**
	 * Positions with non-synonymous alleles
	 * 
	 * @return
	 */
	public Set getNonsynPosset();

	/**
	 * Positions with splice donor site SNP
	 * 
	 * @return
	 */
	public Set getDonorPosset();

	/**
	 * Positions with splice acceptor site SNP
	 * 
	 * @return
	 */
	public Set getAcceptorPosset();

	/**
	 * contig name
	 * 
	 * @return
	 */
	public String getContig();

	/**
	 * chromosome no.
	 * 
	 * @return
	 */
	public Long getChr();

	/**
	 * 
	 * @return duplicatepublic
	 */
	public SnpsStringAllvars copy();

	void setVarnuc(String varnuc);

	public Set getSynPosset();

}
