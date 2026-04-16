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

import org.irri.iric.ds.chado.domain.CvTermLocusCount;
import org.irri.iric.ds.chado.domain.object.VCvtermLocuscountPK;



//import org.irri.iric.portal.chado.oracle.domain.CvTermLocusCount;

/**
 */
// @IdClass(org.irri.iric.portal.chado.domain.VCvtermLocuscountPK.class)
@IdClass(VCvtermLocuscountPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVCvtermLocuscounts", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount"),
		@NamedQuery(name = "findVCvtermLocuscountByCvAcc", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.cvAcc = ?1"),
		@NamedQuery(name = "findVCvtermLocuscountByCvAccContaining", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.cvAcc like ?1"),
		@NamedQuery(name = "findVCvtermLocuscountByCvName", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.cvName = ?1"),
		@NamedQuery(name = "findVCvtermLocuscountByCvNameContaining", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.cvName like ?1"),
		@NamedQuery(name = "findVCvtermLocuscountByCvTerm", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.cvTerm = ?1"),
		@NamedQuery(name = "findVCvtermLocuscountByCvTermContaining", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.cvTerm like ?1"),
		@NamedQuery(name = "findVCvtermLocuscountByCvtermId", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.cvtermId = ?1"),
		@NamedQuery(name = "findVCvtermLocuscountByLocusCount", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.locusCount = ?1"),
		@NamedQuery(name = "findVCvtermLocuscountByOrganismId", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.organismId = ?1"),
		@NamedQuery(name = "findVCvtermLocuscountByPrimaryKey", query = "select myVCvtermLocuscount from VCvtermLocuscount myVCvtermLocuscount where myVCvtermLocuscount.organismId = ?1 and myVCvtermLocuscount.cvtermId = ?2") })
@Table(name = "V_CVTERM_LOCUSCOUNT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VCvtermLocuscount")
public class VCvtermLocuscount implements Serializable, CvTermLocusCount {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "CVTERM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal cvtermId;
	/**
	 */

	@Column(name = "CV_NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvName;
	/**
	 */

	@Column(name = "CV_ACC", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvAcc;
	/**
	 */

	@Column(name = "CV_TERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvTerm;
	/**
	 */

	@Column(name = "LOCUS_COUNT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal locusCount;

	/**
	 */
	public void setOrganismId(BigDecimal organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public BigDecimal getOrganismId() {
		return this.organismId;
	}

	/**
	 */
	public void setCvtermId(BigDecimal cvtermId) {
		this.cvtermId = cvtermId;
	}

	/**
	 */
	public BigDecimal getCvtermId() {
		return this.cvtermId;
	}

	/**
	 */
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	/**
	 */
	public String getCvName() {
		return this.cvName;
	}

	/**
	 */
	public void setCvAcc(String cvAcc) {
		this.cvAcc = cvAcc;
	}

	/**
	 */
	public String getCvAcc() {
		return this.cvAcc;
	}

	/**
	 */
	public void setCvTerm(String cvTerm) {
		this.cvTerm = cvTerm;
	}

	/**
	 */
	public String getCvTerm() {
		return this.cvTerm;
	}

	/**
	 */
	public void setLocusCount(BigDecimal locusCount) {
		this.locusCount = locusCount;
	}

	/**
	 */
	public BigDecimal getLocusCount() {
		return this.locusCount;
	}

	/**
	 */
	public VCvtermLocuscount() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VCvtermLocuscount that) {
		setOrganismId(that.getOrganismId());
		setCvtermId(that.getCvtermId());
		setCvName(that.getCvName());
		setCvAcc(that.getCvAcc());
		setCvTerm(that.getCvTerm());
		setLocusCount(that.getLocusCount());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("cvAcc=[").append(cvAcc).append("] ");
		buffer.append("cvTerm=[").append(cvTerm).append("] ");
		buffer.append("locusCount=[").append(locusCount).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		result = (int) (prime * result + ((cvtermId == null) ? 0 : cvtermId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VCvtermLocuscount))
			return false;
		VCvtermLocuscount equalCheck = (VCvtermLocuscount) obj;
		if ((organismId == null && equalCheck.organismId != null)
				|| (organismId != null && equalCheck.organismId == null))
			return false;
		if (organismId != null && !organismId.equals(equalCheck.organismId))
			return false;
		if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null && equalCheck.cvtermId == null))
			return false;
		if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
			return false;
		return true;
	}

	@Override
	public String getAccession() {

		return "GO:" + this.cvAcc;
	}

	@Override
	public String getName() {

		return this.cvTerm;
	}

	@Override
	public BigDecimal getCount() {

		return this.locusCount;
	}

	@Override
	public String getCV() {

		return this.cvName;
	}

}
