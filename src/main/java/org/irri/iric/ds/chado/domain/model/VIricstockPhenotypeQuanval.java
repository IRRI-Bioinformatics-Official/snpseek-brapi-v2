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

import org.irri.iric.ds.chado.domain.CvTermUniqueValues;



/**
 */
@IdClass(VIricstockPhenotypeQuanvalPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPhenotypeQuanvals", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByPhenotypeId", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.phenotypeId = ?1 and myVIricstockPhenotypeQuanval.dataset in (?2)"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByPhenotypeIdDataset", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.phenotypeId = ?1 and myVIricstockPhenotypeQuanval.dataset in (?2)"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByPrimaryKey", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.quanValue = ?1 and myVIricstockPhenotypeQuanval.phenotypeId = ?2"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByQuanValue", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.quanValue = ?1") })
// @Table( name = "V_IRICSTOCK_PHENOTYPE_QUANVAL")
@Table(name = "V_STOCK_PHENOTYPE_QUANVAL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockPhenotypeQuanval")
public class VIricstockPhenotypeQuanval implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "QUAN_VALUE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal quanValue;
	/**
	 */

	@Column(name = "PHENOTYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal phenotypeId;

	@Column(name = "DATASET", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String dataset;

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

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
	public VIricstockPhenotypeQuanval() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPhenotypeQuanval that) {
		setQuanValue(that.getQuanValue());
		setPhenotypeId(that.getPhenotypeId());
		setDataset(that.getDataset());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("quanValue=[").append(quanValue).append("] ");
		buffer.append("phenotypeId=[").append(phenotypeId).append("] ");
		buffer.append("dataset=[").append(dataset).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((quanValue == null) ? 0 : quanValue.hashCode()));
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		result = (int) (prime * result + ((dataset == null) ? 0 : dataset.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotypeQuanval))
			return false;
		VIricstockPhenotypeQuanval equalCheck = (VIricstockPhenotypeQuanval) obj;
		if ((quanValue == null && equalCheck.quanValue != null) || (quanValue != null && equalCheck.quanValue == null))
			return false;
		if (quanValue != null && !quanValue.equals(equalCheck.quanValue))
			return false;
		if ((phenotypeId == null && equalCheck.phenotypeId != null)
				|| (phenotypeId != null && equalCheck.phenotypeId == null))
			return false;
		if (phenotypeId != null && !phenotypeId.equals(equalCheck.phenotypeId))
			return false;
		if ((dataset == null && equalCheck.dataset != null) || (dataset != null && equalCheck.dataset == null))
			return false;
		if (dataset != null && !dataset.equals(equalCheck.dataset))
			return false;
		return true;
	}

	// private static DecimalFormat twoDForm = new DecimalFormat("#.##");

	@Override
	public String getValue() {

		// return this.getQuanValue().toString();

		// this.getQuanValue().doubleValue()
		// twoDForm.format( getQuanValue().doubleValue() ).replaceAll(target,
		// replacement);

		return String.format("%.2f", getQuanValue()).replaceAll("\\.0+$", "");

		/*
		 * double roundTwoDecimals(double d) { DecimalFormat twoDForm = new
		 * DecimalFormat("#.##"); return Double.valueOf(twoDForm.format(d)); }
		 */

	}

}
