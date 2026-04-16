package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.irri.iric.ds.chado.domain.Passport;



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPassports", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport"),
		@NamedQuery(name = "findVIricstockPassportByDefinition", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport where myVIricstockPassport.definition = ?1"),
		@NamedQuery(name = "findVIricstockPassportByDefinitionContaining", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport where myVIricstockPassport.definition like ?1"),
		@NamedQuery(name = "findVIricstockPassportByIricStockId", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport where myVIricstockPassport.iricStockId = ?1"),

		@NamedQuery(name = "findVIricstockPassportByIricStockpropId", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport where myVIricstockPassport.iricStockpropId = ?1"),
		@NamedQuery(name = "findVIricstockPassportByName", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport where myVIricstockPassport.name = ?1"),
		@NamedQuery(name = "findVIricstockPassportByNameContaining", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport where myVIricstockPassport.name like ?1"),
		@NamedQuery(name = "findVIricstockPassportByPrimaryKey", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport where myVIricstockPassport.iricStockpropId = ?1"),
		@NamedQuery(name = "findVIricstockPassportByValue", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport where myVIricstockPassport.value = ?1"),
		@NamedQuery(name = "findVIricstockPassportByValueContaining", query = "select myVIricstockPassport from VIricstockPassport myVIricstockPassport where myVIricstockPassport.value like ?1") })
// @Table( name = "V_IRICSTOCK_PASSPORT")
@Table(name = "V_STOCK_PASSPORT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockPassport")
public class VIricstockPassport implements Serializable, Passport {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "STOCKPROP_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockpropId;
	/**
	 */

	@Column(name = "STOCK_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal iricStockId;
	/**
	 */

	@Column(name = "NAME", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "DEFINITION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String definition;
	/**
	 */

	@Column(name = "VALUE", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String value;

	@Column(name = "DATASET", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String dataset;

	/**
	 */
	public void setIricStockpropId(BigDecimal iricStockpropId) {
		this.iricStockpropId = iricStockpropId;
	}

	/**
	 */
	public BigDecimal getIricStockpropId() {
		return this.iricStockpropId;
	}

	/**
	 */
	public void setIricStockId(BigDecimal iricStockId) {
		this.iricStockId = iricStockId;
	}

	/**
	 */
	public BigDecimal getIricStockId() {
		return this.iricStockId;
	}

	/**
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 */
	public String getDefinition() {
		return this.definition;
	}

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

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	/**
	 */
	public VIricstockPassport() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPassport that) {
		setIricStockpropId(that.getIricStockpropId());
		setIricStockId(that.getIricStockId());
		setName(that.getName());
		setDefinition(that.getDefinition());
		setValue(that.getValue());
		setDataset(that.getDataset());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("iricStockpropId=[").append(iricStockpropId).append("] ");
		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("definition=[").append(definition).append("] ");
		buffer.append("value=[").append(value).append("] ");
		buffer.append("dataset=[").append(dataset).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((iricStockpropId == null) ? 0 : iricStockpropId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPassport))
			return false;
		VIricstockPassport equalCheck = (VIricstockPassport) obj;
		if ((iricStockpropId == null && equalCheck.iricStockpropId != null)
				|| (iricStockpropId != null && equalCheck.iricStockpropId == null))
			return false;
		if (iricStockpropId != null && !iricStockpropId.equals(equalCheck.iricStockpropId))
			return false;
		return true;
	}
}
