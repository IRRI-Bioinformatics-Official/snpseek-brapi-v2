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

import org.irri.iric.ds.chado.domain.CvTerm;



/**
 */

@Entity
@NamedQueries({ @NamedQuery(name = "findAllVCvPtocos", query = "select myVCvPtoco from VCvPtoco myVCvPtoco"),
		@NamedQuery(name = "findVCvPtocoByAccession", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.accession = ?1"),
		@NamedQuery(name = "findVCvPtocoByAccessionContaining", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.accession like ?1"),
		@NamedQuery(name = "findVCvPtocoByCvterm", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.cvterm = ?1"),
		@NamedQuery(name = "findVCvPtocoByCvtermContaining", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.cvterm like ?1"),
		@NamedQuery(name = "findVCvPtocoByCvtermId", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.cvtermId = ?1"),
		@NamedQuery(name = "findVCvPtocoByDb", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.db = ?1"),
		@NamedQuery(name = "findVCvPtocoByDbContaining", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.db like ?1"),
		@NamedQuery(name = "findVCvPtocoByDefinition", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.definition = ?1"),
		@NamedQuery(name = "findVCvPtocoByDefinitionContaining", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.definition like ?1"),
		@NamedQuery(name = "findVCvPtocoByPrimaryKey", query = "select myVCvPtoco from VCvPtoco myVCvPtoco where myVCvPtoco.cvtermId = ?1") })
@Table(name = "V_CV_PTOCO_PATH")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VCvPtoco")
public class VCvPtoco implements Serializable, CvTerm {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "CVTERM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal cvtermId;
	/**
	 */

	@Column(name = "DB", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String db;
	/**
	 */

	@Column(name = "ACCESSION", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accession;
	/**
	 */

	@Column(name = "CVTERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvterm;
	/**
	 */

	@Column(name = "DEFINITION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String definition;

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
	public void setDb(String db) {
		this.db = db;
	}

	/**
	 */
	public String getDb() {
		return this.db;
	}

	/**
	 */
	public void setAccession(String accession) {
		this.accession = accession;
	}

	/**
	 */
	public String getAccession() {
		return this.accession;
	}

	/**
	 */
	public void setCvterm(String cvterm) {
		this.cvterm = cvterm;
	}

	/**
	 */
	public String getCvterm() {
		return this.cvterm;
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
	public VCvPtoco() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VCvPtoco that) {
		setCvtermId(that.getCvtermId());
		setDb(that.getDb());
		setAccession(that.getAccession());
		setCvterm(that.getCvterm());
		setDefinition(that.getDefinition());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("db=[").append(db).append("] ");
		buffer.append("accession=[").append(accession).append("] ");
		buffer.append("cvterm=[").append(cvterm).append("] ");
		buffer.append("definition=[").append(definition).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((cvtermId == null) ? 0 : cvtermId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VCvPtoco))
			return false;
		VCvPtoco equalCheck = (VCvPtoco) obj;
		if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null && equalCheck.cvtermId == null))
			return false;
		if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getCvTermId() {
		
		return this.cvtermId;
	}

	@Override
	public String getName() {
		
		return this.cvterm + " (" + this.db + ":" + this.accession + ")";
	}

}
