package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;

import org.irri.iric.ds.chado.domain.IndelsAllvarsStr;

public class IndelsAllvarsStrImpl implements IndelsAllvarsStr {

	private BigDecimal varId;
	private BigDecimal pos;
	private String refnuc;
	private String varnuc;
	private String contig;
	private Long chr;
	private String allele1str;
	private String allele2str;
	private BigDecimal allele1;
	private BigDecimal allele2;

	public IndelsAllvarsStrImpl(BigDecimal varId, BigDecimal pos, String refnuc, String varnuc, String contig, Long chr,
			String allele1, String allele2) {
		super();
		this.varId = varId;
		this.pos = pos;
		this.refnuc = refnuc;
		this.varnuc = varnuc;
		this.contig = contig;
		this.chr = chr;
		this.allele1str = allele1;
		this.allele2str = allele2;
	}

	public IndelsAllvarsStrImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public BigDecimal getVar() {
		
		return varId;
	}

	@Override
	public Long getChr() {
		
		return chr;
	}

	// @Override
	// public BigDecimal getPosition() {
	// 
	// return pos;
	// }
	//
	// @Override
	// public String getRefnuc() {
	// 
	// return refnuc;
	// }

	@Override
	public String getVarnuc() {
		
		return varnuc;
	}

	@Override
	public String getContig() {
		
		return contig;
	}

	@Override
	public BigDecimal getAllele1() {
		
		return allele1;
	}

	@Override
	public BigDecimal getAllele2() {
		
		return allele2;
	}

	@Override
	public String getAllele1Str() {
		
		return allele1str;
	}

	@Override
	public String getAllele2Str() {
		
		return allele2str;
	}

	@Override
	public void setAllele1(BigDecimal allele1) {
		
		this.allele1 = allele1;

	}

	@Override
	public void setAllele2(BigDecimal allele2) {
		
		this.allele2 = allele2;
	}

	@Override
	public String toString() {
		
		return contig + " " + chr + " " + pos + " " + allele1str + " " + allele2str;
	}

	@Override
	public BigDecimal getPosition() {
		
		return this.pos;
	}

	@Override
	public String getRefnuc() {
		
		return this.refnuc;
	}

	@Override
	public boolean equals(Object obj) {
		
		/*
		 * if (obj == this) return true; if (!(obj instanceof IndelsAllvarsStrImpl))
		 * return false; IndelsAllvarsStrImpl equalCheck = (IndelsAllvarsStrImpl) obj;
		 * if ((contig == null && equalCheck.contig != null) || (contig != null &&
		 * equalCheck.contig == null)) return false; if (contig != null &&
		 * !contig.equals(equalCheck.contig)) return false; if ((pos == null &&
		 * equalCheck.pos != null) || (pos != null && equalCheck.pos == null)) return
		 * false; if (pos != null && !pos.equals(equalCheck.pos)) return false; return
		 * true;
		 */

		return compareTo(obj) == 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((contig == null) ? 0 : contig.hashCode()));
		result = (int) (prime * result + ((pos == null) ? 0 : pos.hashCode()));
		result = (int) (prime * result + ((varId == null) ? 0 : varId.hashCode()));
		return result;
	}

	@Override
	public int compareTo(Object o) {
		
		IndelsAllvarsStrImpl obj = (IndelsAllvarsStrImpl) o;
		int ret = this.getContig().compareTo(obj.getContig());
		if (ret == 0)
			ret = this.getPosition().compareTo(obj.getPosition());
		if (ret == 0)
			ret = this.getVar().compareTo(obj.getVar());

		return ret;
	}

}
