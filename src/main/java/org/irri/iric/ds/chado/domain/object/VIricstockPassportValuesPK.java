package org.irri.iric.ds.chado.domain.object;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VIricstockPassportValuesPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VIricstockPassportValuesPK() {
	}

	/**
	 */

	@Column(name = "VALUE", length = 4000, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String value;
	/**
	 */

	@Column(name = "TYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal typeId;

	/**
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 */
	public String getValue() {
		return this.value;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((value == null) ? 0 : value.hashCode()));
		result = (int) (prime * result + ((typeId == null) ? 0 : typeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPassportValuesPK))
			return false;
		VIricstockPassportValuesPK equalCheck = (VIricstockPassportValuesPK) obj;
		if ((value == null && equalCheck.value != null) || (value != null && equalCheck.value == null))
			return false;
		if (value != null && !value.equals(equalCheck.value))
			return false;
		if ((typeId == null && equalCheck.typeId != null) || (typeId != null && equalCheck.typeId == null))
			return false;
		if (typeId != null && !typeId.equals(equalCheck.typeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VIricstockPassportValuesPK");
		sb.append(" value: ").append(getValue());
		sb.append(" typeId: ").append(getTypeId());
		return sb.toString();
	}
}
