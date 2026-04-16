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

import org.irri.iric.ds.chado.domain.FeatureSynonym;



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVLocusSynonymss", query = "select myVLocusSynonyms from VLocusSynonyms myVLocusSynonyms"),
		@NamedQuery(name = "findVLocusSynonymsByFeatureId", query = "select myVLocusSynonyms from VLocusSynonyms myVLocusSynonyms where myVLocusSynonyms.featureId = ?1"),
		@NamedQuery(name = "findVLocusSynonymsByLocus", query = "select myVLocusSynonyms from VLocusSynonyms myVLocusSynonyms where myVLocusSynonyms.locus = ?1"),
		@NamedQuery(name = "findVLocusSynonymsByLocusContaining", query = "select myVLocusSynonyms from VLocusSynonyms myVLocusSynonyms where myVLocusSynonyms.locus like ?1"),
		@NamedQuery(name = "findVLocusSynonymsByOrganismId", query = "select myVLocusSynonyms from VLocusSynonyms myVLocusSynonyms where myVLocusSynonyms.organismId = ?1"),
		@NamedQuery(name = "findVLocusSynonymsByPrimaryKey", query = "select myVLocusSynonyms from VLocusSynonyms myVLocusSynonyms where myVLocusSynonyms.synonymId = ?1"),
		@NamedQuery(name = "findVLocusSynonymsBySynonym", query = "select myVLocusSynonyms from VLocusSynonyms myVLocusSynonyms where myVLocusSynonyms.synonym = ?1"),
		@NamedQuery(name = "findVLocusSynonymsBySynonymContaining", query = "select myVLocusSynonyms from VLocusSynonyms myVLocusSynonyms where myVLocusSynonyms.synonym like ?1"),
		@NamedQuery(name = "findVLocusSynonymsBySynonymId", query = "select myVLocusSynonyms from VLocusSynonyms myVLocusSynonyms where myVLocusSynonyms.synonymId = ?1") })
@Table(name = "V_LOCUS_SYNONYMS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VLocusSynonyms")
public class VLocusSynonyms implements Serializable, FeatureSynonym {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SYNONYM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal synonymId;
	/**
	 */

	@Column(name = "SYNONYM", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String synonym;
	/**
	 */

	@Column(name = "FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal featureId;
	/**
	 */

	@Column(name = "LOCUS", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String locus;
	/**
	 */

	@Column(name = "ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;

	/**
	 */
	public void setSynonymId(BigDecimal synonymId) {
		this.synonymId = synonymId;
	}

	/**
	 */
	public BigDecimal getSynonymId() {
		return this.synonymId;
	}

	/**
	 */
	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	/**
	 */
	public String getSynonym() {
		return this.synonym;
	}

	/**
	 */
	public void setFeatureId(BigDecimal featureId) {
		this.featureId = featureId;
	}

	/**
	 */
	public BigDecimal getFeatureId() {
		return this.featureId;
	}

	/**
	 */
	public void setLocus(String locus) {
		this.locus = locus;
	}

	/**
	 */
	public String getLocus() {
		return this.locus;
	}

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
	public VLocusSynonyms() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusSynonyms that) {
		setSynonymId(that.getSynonymId());
		setSynonym(that.getSynonym());
		setFeatureId(that.getFeatureId());
		setLocus(that.getLocus());
		setOrganismId(that.getOrganismId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("synonymId=[").append(synonymId).append("] ");
		buffer.append("synonym=[").append(synonym).append("] ");
		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("locus=[").append(locus).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((synonymId == null) ? 0 : synonymId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VLocusSynonyms))
			return false;
		VLocusSynonyms equalCheck = (VLocusSynonyms) obj;
		if ((synonymId == null && equalCheck.synonymId != null) || (synonymId != null && equalCheck.synonymId == null))
			return false;
		if (synonymId != null && !synonymId.equals(equalCheck.synonymId))
			return false;
		return true;
	}
}
