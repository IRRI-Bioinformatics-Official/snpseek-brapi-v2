package org.irri.iric.portal.genotype;

import java.util.Set;

/**
 * Phylogenetic tree construction parameters
 * 
 * @author LMansueto
 *
 */
public class PhylotreeQueryParams {

	// genotype query params
	private GenotypeQueryParams genotype;
	// tree construction method
	// values = PhylotreeService.PHYLOTREE_METHOD_TOPN,
	// PhylotreeService.PHYLOTREE_METHOD_MINDIST
	private String method;
	// create tree only for the top N nodes
	private int topN;
	// merge nodes below this distance
	private double minDist;
	// multiple distance by this value before tree calculation
	private double scale;

	public PhylotreeQueryParams(GenotypeQueryParams genotype, String method, int topN, double minDist, double scale) {
		super();
		this.genotype = genotype;
		this.method = method;
		this.topN = topN;
		this.minDist = minDist;
		this.scale = scale;
	}

	public GenotypeQueryParams getGenotype() {
		return genotype;
	}

	public String getMethod() {
		return method;
	}

	public int getTopN() {
		return topN;
	}

	public double getMinDist() {
		return minDist;
	}

	public double getScale() {
		return scale;
	}

	/*
	 * public String getDataset() { return genotype.getDataset(); }
	 */

	public Set getDataset() {
		return genotype.getDataset();
	}

}
