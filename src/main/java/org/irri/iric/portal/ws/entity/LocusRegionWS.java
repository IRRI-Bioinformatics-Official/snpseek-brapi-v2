package org.irri.iric.portal.ws.entity;

import org.irri.iric.ds.chado.domain.MergedLoci;

public class LocusRegionWS {

	private MergedLoci loc;

	public LocusRegionWS(MergedLoci loc) {
		super();
		this.loc = loc;
	}

	public String getUniquename() {
		return loc.getUniquename();
	}

	public Integer getFmin() {
		return loc.getFmin();
	}

	public Integer getFmax() {
		return loc.getFmax();
	}

	public Integer getStrand() {
		return loc.getStrand();
	}

	public String getContig() {
		return loc.getContig();
	}

	public String getDescription() {
		return loc.getDescription();
	}

	/*
	 * public Set<String> getOverlappingGenes() { return loc.getOverlappingGenes();
	 * }
	 */

	public String getIRICName() {
		return loc.getIRICName();
	}

	public String getMSU7Name() {
		return loc.getMSU7Name();
	}

	public String getRAPPredName() {
		return loc.getRAPPredName();
	}

	public String getRAPRepName() {
		return loc.getRAPRepName();
	}

	public String getFGeneshName() {
		return loc.getFGeneshName();
	}

}
