package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.irri.iric.ds.chado.domain.VarietyDistance;
import org.irri.iric.ds.chado.domain.object.VIricstockDistancePK;

/**
 */
@IdClass(VIricstockDistancePK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockDistances", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 <= myVIricstockDistance.var2 "),
		@NamedQuery(name = "findVIricstockDistanceByDist", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.dist = ?1"),
		@NamedQuery(name = "findVIricstockDistanceByPrimaryKey", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 = ?1 and myVIricstockDistance.var2 = ?2"),
		@NamedQuery(name = "findVIricstockDistanceByVar1", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 = ?1"),
		@NamedQuery(name = "findAllVIricstockDistancesTopN", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 <= myVIricstockDistance.var2 order by myVIricstockDistance.dist desc"),
		@NamedQuery(name = "findVIricstockDistanceByVarieties", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 <= myVIricstockDistance.var2 and myVIricstockDistance.var1 in (?1) and  myVIricstockDistance.var2 in (?1)"),
		@NamedQuery(name = "findVIricstockDistanceByVarieties2", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 <= myVIricstockDistance.var2 and (myVIricstockDistance.var1 in (?1) or myVIricstockDistance.var1 in (?2)) and  (myVIricstockDistance.var2 in (?1) or myVIricstockDistance.var1 in (?2))"),
		@NamedQuery(name = "findVIricstockDistanceByVarieties3", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 <= myVIricstockDistance.var2 and (myVIricstockDistance.var1 in (?1) or myVIricstockDistance.var1 in (?2) or myVIricstockDistance.var1 in (?3)) and  (myVIricstockDistance.var2 in (?1) or myVIricstockDistance.var2 in (?2) or myVIricstockDistance.var2 in (?3))"),
		@NamedQuery(name = "findVIricstockDistanceByVar2", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var2 = ?1") })
@Table(name = "V_IRICSTOCK_DISTANCE")
// @Table( name = "IRIC_STOCK_DISTANCE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockDistance")
public class VIricstockDistance implements Serializable, VarietyDistance {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VAR1", nullable = false)
	// @Column(name = "IRIC_STOCK_ID1", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal var1;
	/**
	 */

	@Column(name = "VAR2", nullable = false)
	// @Column(name = "IRIC_STOCK_ID2", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal var2;
	/**
	 */

	// @Column(name = "DIST")
	@Column(name = "DISTANCE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal dist;

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
	public void setDist(BigDecimal dist) {
		this.dist = dist;
	}

	/**
	 */
	public BigDecimal getDist() {
		return this.dist;
	}

	/**
	 */
	public VIricstockDistance() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockDistance that) {
		setVar1(that.getVar1());
		setVar2(that.getVar2());
		setDist(that.getDist());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("var1=[").append(var1).append("] ");
		buffer.append("var2=[").append(var2).append("] ");
		buffer.append("dist=[").append(dist).append("] ");

		return buffer.toString();
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
		if (!(obj instanceof VIricstockDistance))
			return false;
		VIricstockDistance equalCheck = (VIricstockDistance) obj;
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
}
