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

/**
 */

@Entity
@NamedQueries({ @NamedQuery(name = "findAllCvs", query = "select myCv from Cv myCv"),
		@NamedQuery(name = "findCvByCvId", query = "select myCv from Cv myCv where myCv.cvId = ?1"),
		@NamedQuery(name = "findCvByName", query = "select myCv from Cv myCv where myCv.name = ?1"),
		@NamedQuery(name = "findCvByNameContaining", query = "select myCv from Cv myCv where myCv.name like ?1"),
		@NamedQuery(name = "findCvByPrimaryKey", query = "select myCv from Cv myCv where myCv.cvId = ?1") })
@Table(name = "CV")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "Cv")
public class Cv implements Serializable, org.irri.iric.ds.chado.domain.Cv {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "CV_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal cvId;
	/**
	 */

	@Column(name = "NAME", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	/*
	 * @Column(name = "DEFINITION")
	 * 
	 * @Basic(fetch = FetchType.EAGER)
	 * 
	 * @Lob
	 * 
	 * @XmlElement byte[] definition;
	 */

	@Column(name = "DEFINITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String definition;

	/**
	 */
	public void setCvId(BigDecimal cvId) {
		this.cvId = cvId;
	}

	/**
	 */
	public BigDecimal getCvId() {
		return this.cvId;
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
	public Cv() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Cv that) {
		setCvId(that.getCvId());
		setName(that.getName());
		setDefinition(that.getDefinition());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("cvId=[").append(cvId).append("] ");
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
		result = (int) (prime * result + ((cvId == null) ? 0 : cvId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Cv))
			return false;
		Cv equalCheck = (Cv) obj;
		if ((cvId == null && equalCheck.cvId != null) || (cvId != null && equalCheck.cvId == null))
			return false;
		if (cvId != null && !cvId.equals(equalCheck.cvId))
			return false;
		return true;
	}

}
