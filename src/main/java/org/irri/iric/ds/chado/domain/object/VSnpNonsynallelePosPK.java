package org.irri.iric.ds.chado.domain.object;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VSnpNonsynallelePosPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VSnpNonsynallelePosPK() {
	}

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "TYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal typeId;

	@Column(name = "ALLELE_NONSYN")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String alleleNonsyn;

	/**
	 */
	public void setSnpFeatureId(BigDecimal snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
	}

	/**
	 */
	public BigDecimal getSnpFeatureId() {
		return this.snpFeatureId;
	}

	/**
	 */
	public void setTypeId(BigDecimal typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public BigDecimal getTypeId() {
		return this.typeId;
	}

	/**
	 */
	public void setAlleleNonsyn(String alleleNonsyn) {
		this.alleleNonsyn = alleleNonsyn;
	}

	/**
	 */
	public String getAlleleNonsyn() {
		return this.alleleNonsyn;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		result = (int) (prime * result + ((typeId == null) ? 0 : typeId.hashCode()));
		result = (int) (prime * result + ((alleleNonsyn == null) ? 0 : alleleNonsyn.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpNonsynallelePosPK))
			return false;
		VSnpNonsynallelePosPK equalCheck = (VSnpNonsynallelePosPK) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null)
				|| (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		if ((typeId == null && equalCheck.typeId != null) || (typeId != null && equalCheck.typeId == null))
			return false;
		if (typeId != null && !typeId.equals(equalCheck.typeId))
			return false;
		if ((alleleNonsyn == null && equalCheck.alleleNonsyn != null)
				|| (alleleNonsyn != null && equalCheck.alleleNonsyn == null))
			return false;
		if (alleleNonsyn != null && !alleleNonsyn.equals(equalCheck.alleleNonsyn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VSnpNonsynallelePosPK");
		sb.append(" snpFeatureId: ").append(getSnpFeatureId());
		sb.append(" typeId: ").append(getTypeId());
		sb.append(" alleleNonsyn: ").append(getAlleleNonsyn());
		return sb.toString();
	}
}
