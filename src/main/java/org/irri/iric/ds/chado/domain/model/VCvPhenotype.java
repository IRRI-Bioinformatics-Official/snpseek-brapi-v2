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
@NamedQueries({
		@NamedQuery(name = "findAllVCvPhenotypes", query = "select myVCvPhenotype from VCvPhenotype myVCvPhenotype order by myVCvPhenotype.definition"),
		@NamedQuery(name = "findVCvPhenotypeByCvTermId", query = "select myVCvPhenotype from VCvPhenotype myVCvPhenotype where myVCvPhenotype.cvTermId = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByDefinition", query = "select myVCvPhenotype from VCvPhenotype myVCvPhenotype where myVCvPhenotype.definition = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByDefinitionContaining", query = "select myVCvPhenotype from VCvPhenotype myVCvPhenotype where myVCvPhenotype.definition like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByName", query = "select myVCvPhenotype from VCvPhenotype myVCvPhenotype where myVCvPhenotype.name = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByNameContaining", query = "select myVCvPhenotype from VCvPhenotype myVCvPhenotype where myVCvPhenotype.name like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPrimaryKey", query = "select myVCvPhenotype from VCvPhenotype myVCvPhenotype where myVCvPhenotype.cvTermId = ?1") })
@Table(name = "V_CV_PHENOTYPE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VCvPhenotype")
public class VCvPhenotype implements Serializable, CvTerm {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "CVTERM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal cvTermId;
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
	public void setCvTermId(BigDecimal cvTermId) {
		this.cvTermId = cvTermId;
	}

	/**
	 */
	public BigDecimal getCvTermId() {
		return this.cvTermId;
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
	public VCvPhenotype() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VCvPhenotype that) {
		setCvTermId(that.getCvTermId());
		setName(that.getName());
		setDefinition(that.getDefinition());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("cvTermId=[").append(cvTermId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("definition=[").append(definition).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((cvTermId == null) ? 0 : cvTermId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VCvPhenotype))
			return false;
		VCvPhenotype equalCheck = (VCvPhenotype) obj;
		if ((cvTermId == null && equalCheck.cvTermId != null) || (cvTermId != null && equalCheck.cvTermId == null))
			return false;
		if (cvTermId != null && !cvTermId.equals(equalCheck.cvTermId))
			return false;
		return true;
	}

	@Override
	public String getAccession() {
		return null;
	}

}
