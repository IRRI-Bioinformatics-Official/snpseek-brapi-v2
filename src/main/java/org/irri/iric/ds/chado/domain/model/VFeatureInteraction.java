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

import org.irri.iric.ds.chado.domain.FeatureInteraction;



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVFeatureInteractions", query = "select myVFeatureInteraction from VFeatureInteraction myVFeatureInteraction"),
		@NamedQuery(name = "findVFeatureInteractionByEvidenceId", query = "select myVFeatureInteraction from VFeatureInteraction myVFeatureInteraction where myVFeatureInteraction.evidenceId = ?1"),
		@NamedQuery(name = "findVFeatureInteractionByFeatureInteractionId", query = "select myVFeatureInteraction from VFeatureInteraction myVFeatureInteraction where myVFeatureInteraction.featureInteractionId = ?1"),
		@NamedQuery(name = "findVFeatureInteractionByGene1Id", query = "select myVFeatureInteraction from VFeatureInteraction myVFeatureInteraction where myVFeatureInteraction.gene1Id = ?1"),
		@NamedQuery(name = "findVFeatureInteractionByGene2Id", query = "select myVFeatureInteraction from VFeatureInteraction myVFeatureInteraction where myVFeatureInteraction.gene2Id = ?1"),

		@NamedQuery(name = "findVFeatureInteractionByGeneIn", query = "select distinct myVFeatureInteraction from VFeatureInteraction myVFeatureInteraction where  myVFeatureInteraction.typeId = ?1 and (myVFeatureInteraction.gene1Id in(?2) or  myVFeatureInteraction.gene2Id in(?2))"),

		@NamedQuery(name = "findVFeatureInteractionByPrimaryKey", query = "select myVFeatureInteraction from VFeatureInteraction myVFeatureInteraction where myVFeatureInteraction.featureInteractionId = ?1"),
		@NamedQuery(name = "findVFeatureInteractionByScore", query = "select myVFeatureInteraction from VFeatureInteraction myVFeatureInteraction where myVFeatureInteraction.score = ?1"),
		@NamedQuery(name = "findVFeatureInteractionByTypeId", query = "select myVFeatureInteraction from VFeatureInteraction myVFeatureInteraction where myVFeatureInteraction.typeId = ?1") })
@Table(name = "V_FEATURE_INTERACTION")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VFeatureInteraction")
public class VFeatureInteraction implements Serializable, FeatureInteraction {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "FEATURE_INTERACTION_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal featureInteractionId;
	/**
	 */

	@Column(name = "GENE1_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal gene1Id;
	/**
	 */

	@Column(name = "GENE2_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal gene2Id;
	/**
	 */

	@Column(name = "SCORE", scale = 15, precision = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal score;
	/**
	 */

	@Column(name = "EVIDENCE_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal evidenceId;
	/**
	 */

	@Column(name = "TYPE_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;

	/**
	 */
	public void setFeatureInteractionId(BigDecimal featureInteractionId) {
		this.featureInteractionId = featureInteractionId;
	}

	/**
	 */
	public BigDecimal getFeatureInteractionId() {
		return this.featureInteractionId;
	}

	/**
	 */
	public void setGene1Id(BigDecimal gene1Id) {
		this.gene1Id = gene1Id;
	}

	/**
	 */
	public BigDecimal getGene1Id() {
		return this.gene1Id;
	}

	/**
	 */
	public void setGene2Id(BigDecimal gene2Id) {
		this.gene2Id = gene2Id;
	}

	/**
	 */
	public BigDecimal getGene2Id() {
		return this.gene2Id;
	}

	/**
	 */
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	/**
	 */
	public BigDecimal getScore() {
		return this.score;
	}

	/**
	 */
	public void setEvidenceId(BigDecimal evidenceId) {
		this.evidenceId = evidenceId;
	}

	/**
	 */
	public BigDecimal getEvidenceId() {
		return this.evidenceId;
	}

	/**
	 */
	public void setTypeId(BigDecimal typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public BigDecimal getTypeId() {
		return this.typeId;
	}

	/**
	 */
	public VFeatureInteraction() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VFeatureInteraction that) {
		setFeatureInteractionId(that.getFeatureInteractionId());
		setGene1Id(that.getGene1Id());
		setGene2Id(that.getGene2Id());
		setScore(that.getScore());
		setEvidenceId(that.getEvidenceId());
		setTypeId(that.getTypeId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureInteractionId=[").append(featureInteractionId).append("] ");
		buffer.append("gene1Id=[").append(gene1Id).append("] ");
		buffer.append("gene2Id=[").append(gene2Id).append("] ");
		buffer.append("score=[").append(score).append("] ");
		buffer.append("evidenceId=[").append(evidenceId).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((featureInteractionId == null) ? 0 : featureInteractionId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VFeatureInteraction))
			return false;
		VFeatureInteraction equalCheck = (VFeatureInteraction) obj;
		if ((featureInteractionId == null && equalCheck.featureInteractionId != null)
				|| (featureInteractionId != null && equalCheck.featureInteractionId == null))
			return false;
		if (featureInteractionId != null && !featureInteractionId.equals(equalCheck.featureInteractionId))
			return false;
		return true;
	}
}
