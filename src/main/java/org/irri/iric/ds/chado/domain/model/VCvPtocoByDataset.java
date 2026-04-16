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

import org.irri.iric.ds.chado.domain.CvTermDataset;


/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVCvPtocosDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.dataset=?1 order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findAllVCvPtocosAllDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco order by myVCvPtoco.cvterm"),

		@NamedQuery(name = "findVCvPtocoByAccessionDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.accession = ?1  order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findVCvPtocoByAccessionContainingDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.accession like ?1 order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findVCvPtocoByCvtermDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.cvterm = ?1  order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findVCvPtocoByCvtermContainingDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.cvterm like ?1 order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findVCvPtocoByCvtermIdDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.cvtermId = ?1  order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findVCvPtocoByDbDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.db = ?1  order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findVCvPtocoByDbContainingDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.db like ?1  order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findVCvPtocoByDefinitionDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.definition = ?1  order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findVCvPtocoByDefinitionContainingDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.definition like ?1  order by myVCvPtoco.cvterm"),
		@NamedQuery(name = "findVCvPtocoByPrimaryKeyDataset", query = "select myVCvPtoco from VCvPtocoByDataset myVCvPtoco where myVCvPtoco.cvtermId = ?1  order by myVCvPtoco.cvterm") })
// @Table( name = "V_CV_PTOCO_PATH")
@Table(name = "V_CV_PTOCO_PATH_ALLSTOCKS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VCvPtocoByDataset")
public class VCvPtocoByDataset implements Serializable, CvTermDataset {
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
	// @Column(name = "CVTERM", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String definition;

	@Column(name = "DATASET", length = 4000)
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

	// /**
	// */
	// public void setDefinition(String definition) {
	// this.definition = definition;
	// }
	//
	/**
	 */
	public String getDefinition() {
		// return this.definition;
		return this.cvterm;
	}

	/**
	 */
	public VCvPtocoByDataset() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VCvPtocoByDataset that) {
		setCvtermId(that.getCvtermId());
		setDb(that.getDb());
		setAccession(that.getAccession());
		setCvterm(that.getCvterm());
		// setDefinition(that.getDefinition());
		setDataset(that.getDataset());
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
		// buffer.append("definition=[").append(definition).append("] ");
		buffer.append("dataset=[").append(dataset).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((cvtermId == null) ? 0 : cvtermId.hashCode()));
		result = (int) (prime * result + ((dataset == null) ? 0 : dataset.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VCvPtocoByDataset))
			return false;
		VCvPtocoByDataset equalCheck = (VCvPtocoByDataset) obj;
		if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null && equalCheck.cvtermId == null))
			return false;
		if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
			return false;
		if ((dataset == null && equalCheck.dataset != null) || (dataset != null && equalCheck.dataset == null))
			return false;
		if (dataset != null && !dataset.equals(equalCheck.dataset))
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
