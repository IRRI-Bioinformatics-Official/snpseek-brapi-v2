package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;

import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.MultiReferenceLocus;

//import org.irri.iric.portal.chado.oracle.domain.VLocusNotes;

/**
 * Implementation of MultiReferenceLocus
 * 
 * @author LMansueto
 *
 */
public class MultiReferenceLocusImpl implements MultiReferenceLocus {

	private String organism;
	private String contig;
	private Integer start;
	private Integer end;
	private Integer strand;
	private String uniquename;

	public MultiReferenceLocusImpl(String organism, String contig, Integer start, Integer end, Integer strand) {
		super();
		this.organism = organism;
		this.contig = contig;
		this.start = start;
		this.end = end;
		this.strand = strand;

	}

	public MultiReferenceLocusImpl(String organism, String contig, Integer start, Integer end, Integer strand,
			String name) {
		super();
		this.organism = organism;
		this.contig = contig;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.uniquename = name;

	}

	@Override
	public String getOrganism() {
		
		return organism;
	}

	@Override
	public String getContig() {
		
		return contig;
	}

	@Override
	public Integer getFmin() {
		
		return start;
	}

	@Override
	public Integer getFmax() {
		
		return end;
	}

	@Override
	public Integer getStrand() {
		
		return strand;
	}

	@Override
	public String toString() {
		
		return "(" + organism + " " + contig + " " + start + "-" + end + " " + strand + ")";
	}

	@Override
	public String getUniquename() {
		
		return this.uniquename;
	}

	@Override
	public Long getChr() {
		
		return Long.valueOf(getContig());
	}

	@Override
	public String getDescription() {
		
		return null;
	}

	@Override
	public BigDecimal getFeatureId() {
		
		return null;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = (int) (prime * result + ((featureId == null) ? 0 :
		// featureId.hashCode()));
		result = (int) (prime * result + ((organism == null) ? 0 : organism.hashCode()));
		result = (int) (prime * result + ((contig == null) ? 0 : contig.hashCode()));
		result = (int) (prime * result + ((start == null) ? 0 : start.hashCode()));
		result = (int) (prime * result + ((end == null) ? 0 : end.hashCode()));
		return result;
	}

	@Override
	public int compareTo(Object o) {
		
		Locus l1 = (Locus) this;
		Locus l2 = (Locus) o;
		// int ret = l1.getOrganismId().compareTo(l2.getOrganismId());
		// if(ret!=0) return ret;
		int ret = l1.getContig().compareTo(l2.getContig());
		if (ret != 0)
			return ret;
		ret = l1.getFmin().compareTo(l2.getFmin());
		if (ret != 0)
			return ret;
		ret = l1.getFmax().compareTo(l2.getFmax());
		return ret;

	}

	@Override
	public boolean equals(Object obj) {
		
		return compareTo(obj) == 0;
	}

	/*
	 * @Override public String printFields(String delimiter) { // TODO
	 * Auto-generated method stub= return getUniquename() + delimiter + getContig()
	 * + delimiter + getFmin() + delimiter + getFmax() + delimiter + getStrand() +
	 * delimiter + getOrganism() + delimiter + getDescription(); }
	 */
	@Override
	public String getFeatureType() {
		return null;
	}

}
