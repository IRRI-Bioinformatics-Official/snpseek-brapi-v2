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
@IdClass(VIricstockPhenotypeQualvalPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPhenotypeQualvals", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByPhenotypeId", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.phenotypeId = ?1 and  myVIricstockPhenotypeQualval.dataset in ( ?2)"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByPhenotypeIdDataset", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.phenotypeId = ?1 and  myVIricstockPhenotypeQualval.dataset in (?2)"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByPrimaryKey", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.qualValue = ?1 and myVIricstockPhenotypeQualval.phenotypeId = ?2"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByQualValue", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.qualValue = ?1  and  myVIricstockPhenotypeQualval.dataset in ( ?2)"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByQualValueContaining", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.qualValue like ?1  and  myVIricstockPhenotypeQualval.dataset in (?2)") })
@Table(name = "V_STOCK_PHENOTYPE_QUALVAL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockPhenotypeQualval")
public class VIricstockPhenotypeQualval implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "QUAL_VALUE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String qualValue;
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
	public void setQualValue(String qualValue) {
		this.qualValue = qualValue;
	}

	/**
	 */
	public String getQualValue() {
		return this.qualValue;
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
	public VIricstockPhenotypeQualval() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPhenotypeQualval that) {
		setQualValue(that.getQualValue());
		setPhenotypeId(that.getPhenotypeId());
		setDataset(that.getDataset());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("qualValue=[").append(qualValue).append("] ");
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
		result = (int) (prime * result + ((qualValue == null) ? 0 : qualValue.hashCode()));
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		result = (int) (prime * result + ((dataset == null) ? 0 : dataset.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotypeQualval))
			return false;
		VIricstockPhenotypeQualval equalCheck = (VIricstockPhenotypeQualval) obj;
		if ((qualValue == null && equalCheck.qualValue != null) || (qualValue != null && equalCheck.qualValue == null))
			return false;
		if (qualValue != null && !qualValue.equals(equalCheck.qualValue))
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

	@Override
	public String getValue() {

		return this.getQualValue();
	}

}
