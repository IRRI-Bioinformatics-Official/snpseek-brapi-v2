package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;

import org.irri.iric.ds.chado.domain.MultiReferenceConversion;

/**
 * Implementation of MultiReferenceConversion
 * 
 * @author LMansueto
 *
 */
public class MultiReferenceConversionImpl implements MultiReferenceConversion, Comparable {

	// private String organism;
	private String organism;
	private String contig;
	private BigDecimal position;
	private String refcall;

	public MultiReferenceConversionImpl(String organism, String contig, BigDecimal position) {
		super();
		this.organism = organism;
		this.contig = contig;
		this.position = position;
	}

	public MultiReferenceConversionImpl(String organism, String contig, BigDecimal position, String refcall) {
		super();
		this.organism = organism;
		this.contig = contig;
		this.position = position;
		this.refcall = refcall;
	}

	@Override
	public String getToOrganism() {
		
		return organism;
	}

	@Override
	public String getToContig() {
		
		return contig;
	}

	@Override
	public BigDecimal getToPosition() {
		
		return position;
	}

	@Override
	public String toString() {
		
		return "(" + organism + " " + contig + " " + position + ")";
	}

	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof MultiReferenceConversion))
			return false;

		MultiReferenceConversion posobj = (MultiReferenceConversion) obj;
		return this.contig.equals(posobj.getToContig()) && this.organism.equals(posobj.getToOrganism())
				&& this.position.equals(posobj.getToPosition());
	}

	@Override
	public int compareTo(Object o) {
		
		MultiReferenceConversion posobj = (MultiReferenceConversion) o;
		int ret = this.organism.compareToIgnoreCase(posobj.getToOrganism());
		if (ret == 0)
			ret = this.contig.compareToIgnoreCase(posobj.getToContig());
		if (ret == 0)
			ret = this.position.compareTo(posobj.getToPosition());
		return ret;
	}

	@Override
	public int hashCode() {
		
		return toString().hashCode();
	}

	@Override
	public String getFromOrganism() {
		return null;
	}

	@Override
	public String getFromContig() {
		return null;
	}

	@Override
	public BigDecimal getFromPosition() {
		return null;
	}

	@Override
	public String getFromRefcall() {
		return null;
	}

	@Override
	public String getToRefcall() {
		
		return this.refcall;
	}

}
