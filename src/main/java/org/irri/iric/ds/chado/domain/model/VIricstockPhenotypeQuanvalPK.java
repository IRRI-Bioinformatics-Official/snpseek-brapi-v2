package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VIricstockPhenotypeQuanvalPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VIricstockPhenotypeQuanvalPK() {
	}

	/**
	 */

	@Column(name = "QUAN_VALUE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal quanValue;
	/**
	 */

	@Column(name = "PHENOTYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal phenotypeId;

	/**
	 */
	public void setQuanValue(BigDecimal quanValue) {
		this.quanValue = quanValue;
	}

	/**
	 */
	public BigDecimal getQuanValue() {
		return this.quanValue;
	}

	/**
	 */
	public void setPhenotypeId(BigDecimal phenotypeId) {
		this.phenotypeId = phenotypeId;
	}

	/**
	 */
	public BigDecimal getPhenotypeId() {
		return this.phenotypeId;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((quanValue == null) ? 0 : quanValue.hashCode()));
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotypeQuanvalPK))
			return false;
		VIricstockPhenotypeQuanvalPK equalCheck = (VIricstockPhenotypeQuanvalPK) obj;
		if ((quanValue == null && equalCheck.quanValue != null) || (quanValue != null && equalCheck.quanValue == null))
			return false;
		if (quanValue != null && !quanValue.equals(equalCheck.quanValue))
			return false;
		if ((phenotypeId == null && equalCheck.phenotypeId != null)
				|| (phenotypeId != null && equalCheck.phenotypeId == null))
			return false;
		if (phenotypeId != null && !phenotypeId.equals(equalCheck.phenotypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VIricstockPhenotypeQuanvalPK");
		sb.append(" quanValue: ").append(getQuanValue());
		sb.append(" phenotypeId: ").append(getPhenotypeId());
		return sb.toString();
	}
}
