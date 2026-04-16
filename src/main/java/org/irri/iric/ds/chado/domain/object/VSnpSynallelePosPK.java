package org.irri.iric.ds.chado.domain.object;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VSnpSynallelePosPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VSnpSynallelePosPK() {
	}

	/**
	 */

	@Column(name = "CHR")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal chr;
	/**
	 */

	@Column(name = "POSITION")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal position;
	/**
	 */

	@Column(name = "ALLELE_SYN")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String alleleSyn;

	/**
	 */
	public void setChr(BigDecimal chr) {
		this.chr = chr;
	}

	/**
	 */
	public BigDecimal getChr() {
		return this.chr;
	}

	/**
	 */
	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	/**
	 */
	public BigDecimal getPosition() {
		return this.position;
	}

	/**
	 */
	public void setAlleleSyn(String alleleSyn) {
		this.alleleSyn = alleleSyn;
	}

	/**
	 */
	public String getAlleleSyn() {
		return this.alleleSyn;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((chr == null) ? 0 : chr.hashCode()));
		result = (int) (prime * result + ((position == null) ? 0 : position.hashCode()));
		result = (int) (prime * result + ((alleleSyn == null) ? 0 : alleleSyn.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpSynallelePosPK))
			return false;
		VSnpSynallelePosPK equalCheck = (VSnpSynallelePosPK) obj;
		if ((chr == null && equalCheck.chr != null) || (chr != null && equalCheck.chr == null))
			return false;
		if (chr != null && !chr.equals(equalCheck.chr))
			return false;
		if ((position == null && equalCheck.position != null) || (position != null && equalCheck.position == null))
			return false;
		if (position != null && !position.equals(equalCheck.position))
			return false;
		if ((alleleSyn == null && equalCheck.alleleSyn != null) || (alleleSyn != null && equalCheck.alleleSyn == null))
			return false;
		if (alleleSyn != null && !alleleSyn.equals(equalCheck.alleleSyn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VSnpSynallelePosPK");
		sb.append(" chr: ").append(getChr());
		sb.append(" position: ").append(getPosition());
		sb.append(" alleleSyn: ").append(getAlleleSyn());
		return sb.toString();
	}
}
