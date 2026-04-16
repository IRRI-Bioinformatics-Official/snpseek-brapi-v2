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

import org.irri.iric.ds.chado.domain.SnpsSpliceAcceptor;



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpSpliceacceptors", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor"),
		@NamedQuery(name = "findVSnpSpliceacceptorByChr", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.chr = ?1"),
		@NamedQuery(name = "findVSnpSpliceacceptorByChrContaining", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.chr like ?1"),
		@NamedQuery(name = "findVSnpSpliceacceptorByPosition", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.position = ?1"),
		@NamedQuery(name = "findVSnpSpliceacceptorByChrPositionBetween", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.chr=?1 and myVSnpSpliceacceptor.position-1 between ?2 and ?3"),
		@NamedQuery(name = "findVSnpSpliceacceptorByChrPositionIn", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.chr=?1 and myVSnpSpliceacceptor.position-1 in (?2)"),
		@NamedQuery(name = "findVSnpSpliceacceptorByChrPositionBetweenSnpset", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.chr=?1 and myVSnpSpliceacceptor.position-1 between ?2 and ?3 and myVSnpSpliceacceptor.variantset in (?4)"),
		@NamedQuery(name = "findVSnpSpliceacceptorByChrPositionInSnpset", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.chr=?1 and myVSnpSpliceacceptor.position-1 in (?2) and myVSnpSpliceacceptor.variantset in (?3)"),
		@NamedQuery(name = "findVSnpSpliceacceptorBySnpFeatureIdIn", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.snpFeatureId in (?1) order by  myVSnpSpliceacceptor.chr , myVSnpSpliceacceptor.position"),
		@NamedQuery(name = "findVSnpSpliceacceptorByPrimaryKey", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpSpliceacceptorBySnpFeatureId", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.snpFeatureId = ?1")
})
@Table(name = "V_SNP_SPLICEACCEPTOR_V2")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpSpliceacceptor")
public class VSnpSpliceacceptor implements Serializable, SnpsSpliceAcceptor, Comparable {
	@Override
	public String getContig() {
		return null;
	}

	@Override
	public String getRefnuc() {
		return null;
	}

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

	// @Column(name = "SRCFEATURE_ID")
	// @Basic(fetch = FetchType.EAGER)
	// @XmlElement
	// BigDecimal srcfeatureId;
	// /**
	// */

	@Column(name = "POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal position;
	/**
	 */

	@Column(name = "CHROMOSOME", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Long chr;
	/**
	 */
	//
	// @Column(name = "ORGANISM_ID", precision = 10)
	// @Basic(fetch = FetchType.EAGER)
	// @XmlElement
	// BigDecimal organismId;

	@Column(name = "VARIANTSET", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String variantset;

	/**
	 */
	public void setSnpFeatureId(BigDecimal snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
	}

	//
	// /**
	// */
	// public void setSrcfeatureId(BigDecimal srcfeatureId) {
	// this.srcfeatureId = srcfeatureId;
	// }
	//
	// /**
	// */
	// public BigDecimal getSrcfeatureId() {
	// return this.srcfeatureId;
	// }

	/**
	 */
	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	/**
	 */
	public BigDecimal getPosition() {
		return this.position;
	}

	/**
	 */
	public void setChr(Long chr) {
		this.chr = chr;
	}

	/**
	 */
	public Long getChr() {
		return this.chr;
	}

	// /**
	// */
	// public void setOrganismId(BigDecimal organismId) {
	// this.organismId = organismId;
	// }
	//
	// /**
	// */
	// public BigDecimal getOrganismId() {
	// return this.organismId;
	// }

	/**
	 */
	public VSnpSpliceacceptor() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpSpliceacceptor that) {
		setSnpFeatureId(that.getSnpFeatureId());
		// setSrcfeatureId(that.getSrcfeatureId());
		setPosition(that.getPosition());
		setChr(that.getChr());
		// setOrganismId(that.getOrganismId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		// buffer.append("srcfeatureId=[").append(srcfeatureId).append("] ");
		buffer.append("position=[").append(position).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		// buffer.append("organismId=[").append(organismId).append("] ");

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
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpSpliceacceptor))
			return false;
		VSnpSpliceacceptor equalCheck = (VSnpSpliceacceptor) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null)
				|| (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getSnpFeatureId() {

		return snpFeatureId;
	}

	@Override
	public int compareTo(Object o) {

		VSnpSpliceacceptor acc = (VSnpSpliceacceptor) o;
		int ret = this.getChr().compareTo(acc.getChr());
		if (ret == 0)
			ret = this.getPosition().compareTo(acc.getPosition());
		return ret;
	}

}
