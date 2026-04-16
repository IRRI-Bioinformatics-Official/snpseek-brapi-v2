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

import org.irri.iric.ds.chado.domain.Snp;

/**
 */

@Entity
@NamedQueries({ @NamedQuery(name = "findAllVSnpInExons", query = "select myVSnpInExon from VSnpInExon myVSnpInExon"),
		@NamedQuery(name = "findVSnpInExonByPrimaryKey", query = "select myVSnpInExon from VSnpInExon myVSnpInExon where myVSnpInExon.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpInExonBySnpFeatureIdIn", query = "select myVSnpInExon from VSnpInExon myVSnpInExon where myVSnpInExon.snpFeatureId in (?1)"),
		@NamedQuery(name = "findVSnpInExonBySnpFeatureIdBetween", query = "select myVSnpInExon from VSnpInExon myVSnpInExon where myVSnpInExon.snpFeatureId between ?1 and ?2"),
		@NamedQuery(name = "findVSnpInExonBySnpFeatureId", query = "select myVSnpInExon from VSnpInExon myVSnpInExon where myVSnpInExon.snpFeatureId = ?1") })
@Table(name = "V_SNP_IN_EXON")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpInExon")
public class VSnpInExon implements Serializable, Snp, Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal snpFeatureId;

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
	public VSnpInExon() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpInExon that) {
		setSnpFeatureId(that.getSnpFeatureId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		return result;
	}

	/**
	 */
	/*
	 * public boolean equals(Object obj) { if (obj == this) return true; if (!(obj
	 * instanceof VSnpInExon)) return false; VSnpInExon equalCheck = (VSnpInExon)
	 * obj; if ((snpFeatureId == null && equalCheck.snpFeatureId != null) ||
	 * (snpFeatureId != null && equalCheck.snpFeatureId == null)) return false; if
	 * (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
	 * return false; return true; }
	 */

	@Override
	public int compareTo(Object o) {

		return this.getSnpFeatureId().compareTo(((VSnpInExon) o).getSnpFeatureId());

	}

	@Override
	public boolean equals(Object obj) {

		return compareTo(obj) == 0;
	}

}
