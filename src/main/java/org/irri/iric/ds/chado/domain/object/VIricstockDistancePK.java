package org.irri.iric.ds.chado.domain.object;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VIricstockDistancePK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VIricstockDistancePK() {
	}

	/**
	 */

	@Column(name = "VAR1", nullable = false)
	// @Column(name = "IRIC_STOCK_ID1", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal var1;
	/**
	 */

	@Column(name = "VAR2", nullable = false)
	// @Column(name = "IRIC_STOCK_ID2", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal var2;

	/**
	 */
	public void setVar1(BigDecimal var1) {
		this.var1 = var1;
	}

	/**
	 */
	public BigDecimal getVar1() {
		return this.var1;
	}

	/**
	 */
	public void setVar2(BigDecimal var2) {
		this.var2 = var2;
	}

	/**
	 */
	public BigDecimal getVar2() {
		return this.var2;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((var1 == null) ? 0 : var1.hashCode()));
		result = (int) (prime * result + ((var2 == null) ? 0 : var2.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockDistancePK))
			return false;
		VIricstockDistancePK equalCheck = (VIricstockDistancePK) obj;
		if ((var1 == null && equalCheck.var1 != null) || (var1 != null && equalCheck.var1 == null))
			return false;
		if (var1 != null && !var1.equals(equalCheck.var1))
			return false;
		if ((var2 == null && equalCheck.var2 != null) || (var2 != null && equalCheck.var2 == null))
			return false;
		if (var2 != null && !var2.equals(equalCheck.var2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VIricstockDistancePK");
		sb.append(" var1: ").append(getVar1());
		sb.append(" var2: ").append(getVar2());
		return sb.toString();
	}
}
