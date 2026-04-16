package org.irri.iric.ds.chado.domain.object;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VLocusCvtermCvtermpathPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VLocusCvtermCvtermpathPK() {
	}

	/**
	 */

	@Column(name = "FEATURE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal featureId;
	/**
	 */

	@Column(name = "CVTERM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal cvtermId;
	/**
	 */

	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal organismId;

	/**
	 */
	public void setFeatureId(BigDecimal featureId) {
		this.featureId = featureId;
	}

	/**
	 */
	public BigDecimal getFeatureId() {
		return this.featureId;
	}

	/**
	 */
	public void setCvtermId(BigDecimal cvtermId) {
		this.cvtermId = cvtermId;
	}

	/**
	 */
	public BigDecimal getCvtermId() {
		return this.cvtermId;
	}

	/**
	 */
	public void setOrganismId(BigDecimal organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public BigDecimal getOrganismId() {
		return this.organismId;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((featureId == null) ? 0 : featureId.hashCode()));
		result = (int) (prime * result + ((cvtermId == null) ? 0 : cvtermId.hashCode()));
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VLocusCvtermCvtermpathPK))
			return false;
		VLocusCvtermCvtermpathPK equalCheck = (VLocusCvtermCvtermpathPK) obj;
		if ((featureId == null && equalCheck.featureId != null) || (featureId != null && equalCheck.featureId == null))
			return false;
		if (featureId != null && !featureId.equals(equalCheck.featureId))
			return false;
		if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null && equalCheck.cvtermId == null))
			return false;
		if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
			return false;
		if ((organismId == null && equalCheck.organismId != null)
				|| (organismId != null && equalCheck.organismId == null))
			return false;
		if (organismId != null && !organismId.equals(equalCheck.organismId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VLocusCvtermCvtermpathPK");
		sb.append(" featureId: ").append(getFeatureId());
		sb.append(" cvtermId: ").append(getCvtermId());
		sb.append(" organismId: ").append(getOrganismId());
		return sb.toString();
	}
}
