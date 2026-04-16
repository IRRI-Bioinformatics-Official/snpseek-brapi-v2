package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;

import org.irri.iric.ds.chado.domain.IndelsAllvarsPos;
import org.irri.iric.ds.utils.DbUtils;

/**
 * Implementation for IndelsAllvarsPos
 * 
 * @author LMansueto
 *
 */
public class IndelsAllvarsPosAlleleImpl implements IndelsAllvarsPos {

	private BigDecimal pos;
	private BigDecimal alleleIndex;
	private BigDecimal snpFeatureId;
	// private Integer dellength;
	private Integer maxdellength;
	private Integer maxinslength;

	private String refnuc;
	private String contig;
	private String insString;

	public IndelsAllvarsPosAlleleImpl(BigDecimal pos, BigDecimal alleleIndex, BigDecimal snpFeatureId,
			Integer maxdellength, String refnuc, String contig, String insString, Integer maxinslength) {
		super();
		this.pos = pos;
		this.alleleIndex = alleleIndex;
		this.snpFeatureId = snpFeatureId;
		// this.dellength = dellength;
		this.refnuc = refnuc;
		this.contig = contig;
		this.insString = insString;
		this.maxinslength = maxinslength;
		this.maxdellength = maxdellength;
	}

	// @Override
	// public BigDecimal getPosition() {
	//
	// return pos;
	// }

	@Override
	public String getRefnuc() {

		return refnuc;
	}

	@Override
	public void setRefnuc(String refnuc) {

		this.refnuc = refnuc;
	}

	@Override
	public String getContig() {

		return contig;
	}

	@Override
	public BigDecimal getAlleleIndex() {

		return alleleIndex;
	}

	@Override
	public BigDecimal getSnpFeatureId() {

		return snpFeatureId;
	}

	@Override
	public Integer getMaxDellength() {

		return maxdellength;
	}

	// @Override
	// public Integer getDellength() {
	//
	// try {
	// int dellen=Integer.valueOf(insString);
	// return dellen;
	// } catch(Exception ex) {}
	// return 0;
	// }

	@Override
	public String getInsString() {

		return insString;
	}

	@Override
	public boolean equals(Object obj) {

		/*
		 * IndelsAllvarsPosAlleleImpl o=(IndelsAllvarsPosAlleleImpl)obj;
		 * 
		 * //boolean equalinsert = (insString==null && o.getInsString()==null) ||
		 * insString.equals(o.getInsString()); //boolean equaldel = (dellength==null &&
		 * o.getDellength()==null) || dellength.equals(o.getDellength()); //return
		 * pos.equals(o.getPos()) && contig.equals(o.getContig()) && equalinsert &&
		 * equaldel; //return toString().equals(o.toString());
		 * 
		 * if (obj == this) return true; if (!(obj instanceof
		 * IndelsAllvarsPosAlleleImpl)) return false; IndelsAllvarsPosAlleleImpl
		 * equalCheck = (IndelsAllvarsPosAlleleImpl) obj; if ((contig == null &&
		 * equalCheck.contig != null) || (contig != null && equalCheck.contig == null))
		 * return false; if (contig != null && !contig.equals(equalCheck.contig)) return
		 * false; if ((pos == null && equalCheck.pos != null) || (pos != null &&
		 * equalCheck.pos == null)) return false; if (pos != null &&
		 * !pos.equals(equalCheck.pos))
		 * 
		 * if ((insString == null && equalCheck.insString != null) || (insString != null
		 * && equalCheck.insString == null)) return false; if (insString != null &&
		 * !insString.equals(equalCheck.insString)) return false;
		 * 
		 * return true;
		 */
		return compareTo(obj) == 0;
	}

	@Override
	public String toString() {

		return getClass() + ": " + contig + "-" + pos + "-" + insString.toLowerCase();

	}

	@Override
	public int hashCode() {

		// return toString().hashCode();
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((contig == null) ? 0 : contig.hashCode()));
		result = (int) (prime * result + ((pos == null) ? 0 : pos.hashCode()));
		result = (int) (prime * result + ((insString == null) ? 0 : insString.hashCode()));
		return result;
	}

	@Override
	public Integer getMaxInsLength() {

		return maxinslength;
	}

	@Override
	public BigDecimal getPosition() {

		return this.pos;
	}

	// @Override
	// public String getRefnuc() {
	//
	// return this.refnuc;
	// }

	@Override
	public int compareTo(Object o) {

		IndelsAllvarsPos obj = (IndelsAllvarsPos) o;
		int ret = getContig().compareTo(obj.getContig());
		if (ret == 0)
			ret = getPosition().compareTo(obj.getPosition());

		if (o instanceof IndelsAllvarsPosAlleleImpl) {
			if (ret == 0)
				ret = insString.compareTo(((IndelsAllvarsPosAlleleImpl) o).getInsString());
		}

		return ret;
	}

	@Override
	public Long getChr() {

		return Long.valueOf(DbUtils.guessChrFromString(contig));
	}

	@Override
	public void setAltnuc(String altnuc) {

	}

	@Override
	public String getAltnuc() {
		return null;
	}

}
