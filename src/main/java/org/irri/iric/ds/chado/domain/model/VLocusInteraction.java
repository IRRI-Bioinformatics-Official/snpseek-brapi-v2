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

import org.irri.iric.ds.chado.domain.Locus;



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVLocusInteractions", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction"),
		@NamedQuery(name = "findVLocusInteractionByCommonName", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.commonName = ?1"),
		@NamedQuery(name = "findVLocusInteractionByCommonNameContaining", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.commonName like ?1"),
		@NamedQuery(name = "findVLocusInteractionByContigId", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.contigId = ?1"),
		@NamedQuery(name = "findVLocusInteractionByContigName", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.contigName = ?1"),
		@NamedQuery(name = "findVLocusInteractionByContigNameContaining", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.contigName like ?1"),
		@NamedQuery(name = "findVLocusInteractionByFeatureId", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.featureId = ?1"),
		@NamedQuery(name = "findVLocusInteractionByFmax", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.fmax = ?1"),
		@NamedQuery(name = "findVLocusInteractionByFmin", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.fmin = ?1"),
		@NamedQuery(name = "findVLocusInteractionByName", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.name = ?1"),
		@NamedQuery(name = "findVLocusInteractionByNameContaining", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.name like ?1"),
		@NamedQuery(name = "findVLocusInteractionByNotes", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.notes = ?1"),
		@NamedQuery(name = "findVLocusInteractionByNotesContaining", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.notes like ?1"),
		@NamedQuery(name = "findVLocusInteractionByOrganismId", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.organismId = ?1"),
		@NamedQuery(name = "findVLocusInteractionByPrimaryKey", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.featureId = ?1"),
		@NamedQuery(name = "findVLocusInteractionByQfeatureId", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.qfeatureId = ?1"),
		@NamedQuery(name = "findVLocusInteractionByQfeatureName", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.qfeatureName = ?1"),

		@NamedQuery(name = "findVLocusInteractionByQfeatureIdIn", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.qfeatureId in (?1) order by myVLocusInteraction.score desc"),
		@NamedQuery(name = "findVLocusInteractionByQfeatureNameIn", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.qfeatureName in (?1)  order by myVLocusInteraction.score desc"),

		@NamedQuery(name = "findVLocusInteractionByQfeatureNameContaining", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.qfeatureName like ?1"),
		@NamedQuery(name = "findVLocusInteractionByScore", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.score = ?1"),
		@NamedQuery(name = "findVLocusInteractionByStrand", query = "select myVLocusInteraction from VLocusInteraction myVLocusInteraction where myVLocusInteraction.strand = ?1") })
@Table(name = "V_LOCUS_INTERACTION")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VLocusInteraction")
public class VLocusInteraction implements Serializable, Locus {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal featureId;
	/**
	 */

	@Column(name = "COMMON_NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;
	/**
	 */

	@Column(name = "CONTIG_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal contigId;
	/**
	 */

	@Column(name = "CONTIG_NAME", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contigName;
	/**
	 */

	@Column(name = "NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "FMIN", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmin;
	/**
	 */

	@Column(name = "FMAX", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmax;
	/**
	 */

	@Column(name = "STRAND", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strand;
	/**
	 */

	@Column(name = "ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "NOTES", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;
	/**
	 */

	@Column(name = "QFEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal qfeatureId;
	/**
	 */

	@Column(name = "QFEATURE_NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qfeatureName;
	/**
	 */

	@Column(name = "SCORE", scale = 15, precision = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal score;

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
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	/**
	 */
	public String getCommonName() {
		return this.commonName;
	}

	/**
	 */
	public void setContigId(BigDecimal contigId) {
		this.contigId = contigId;
	}

	/**
	 */
	public BigDecimal getContigId() {
		return this.contigId;
	}

	/**
	 */
	public void setContigName(String contigName) {
		this.contigName = contigName;
	}

	/**
	 */
	public String getContigName() {
		return this.contigName;
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
	public void setFmin(Integer fmin) {
		this.fmin = fmin;
	}

	/**
	 */
	public Integer getFmin() {
		return this.fmin;
	}

	/**
	 */
	public void setFmax(Integer fmax) {
		this.fmax = fmax;
	}

	/**
	 */
	public Integer getFmax() {
		return this.fmax;
	}

	/**
	 */
	public void setStrand(Integer strand) {
		this.strand = strand;
	}

	/**
	 */
	public Integer getStrand() {
		return this.strand;
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
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 */
	public void setQfeatureId(BigDecimal qfeatureId) {
		this.qfeatureId = qfeatureId;
	}

	/**
	 */
	public BigDecimal getQfeatureId() {
		return this.qfeatureId;
	}

	/**
	 */
	public void setQfeatureName(String qfeatureName) {
		this.qfeatureName = qfeatureName;
	}

	/**
	 */
	public String getQfeatureName() {
		return this.qfeatureName;
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
	public VLocusInteraction() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusInteraction that) {
		setFeatureId(that.getFeatureId());
		setCommonName(that.getCommonName());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		setName(that.getName());
		setFmin(that.getFmin());
		setFmax(that.getFmax());
		setStrand(that.getStrand());
		setOrganismId(that.getOrganismId());
		setNotes(that.getNotes());
		setQfeatureId(that.getQfeatureId());
		setQfeatureName(that.getQfeatureName());
		setScore(that.getScore());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("contigId=[").append(contigId).append("] ");
		buffer.append("contigName=[").append(contigName).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("strand=[").append(strand).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("notes=[").append(notes).append("] ");
		buffer.append("qfeatureId=[").append(qfeatureId).append("] ");
		buffer.append("qfeatureName=[").append(qfeatureName).append("] ");
		buffer.append("score=[").append(score).append("] ");

		return buffer.toString();
	}

	// /**
	// */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = (int) (prime * result + ((featureId == null) ? 0 :
	// featureId.hashCode()));
	// return result;
	// }
	//
	// /**
	// */
	// public boolean equals(Object obj) {
	// if (obj == this)
	// return true;
	// if (!(obj instanceof VLocusInteraction))
	// return false;
	// VLocusInteraction equalCheck = (VLocusInteraction) obj;
	// if ((featureId == null && equalCheck.featureId != null) || (featureId != null
	// && equalCheck.featureId == null))
	// return false;
	// if (featureId != null && !featureId.equals(equalCheck.featureId))
	// return false;
	// return true;
	// }

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = (int) (prime * result + ((featureId == null) ? 0 :
		// featureId.hashCode()));
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		result = (int) (prime * result + ((contigId == null) ? 0 : contigId.hashCode()));
		result = (int) (prime * result + ((fmin == null) ? 0 : fmin.hashCode()));
		result = (int) (prime * result + ((fmax == null) ? 0 : fmax.hashCode()));
		return result;
	}

	@Override
	public int compareTo(Object o) {
		
		Locus l1 = (Locus) this;
		Locus l2 = (Locus) o;
		int ret = l1.getContig().compareTo(l2.getContig());
		if (ret != 0)
			return ret;
		ret = l1.getFmin().compareTo(l2.getFmin());
		if (ret != 0)
			return ret;
		ret = l1.getFmax().compareTo(l2.getFmax());
		return ret;

	}

	@Override
	public boolean equals(Object obj) {
		
		return compareTo(obj) == 0;
	}

	@Override
	public String getUniquename() {
		
		return name;
	}

	@Override
	public Long getChr() {
		
		return Long.valueOf(getContig());
	}

	@Override
	public String getContig() {
		
		return contigName;
	}

	@Override
	public String getDescription() {
		
		return "Score:" + String.format("%.2f", score) + " " + this.notes;
	}

	@Override
	public String getFeatureType() {
		return null;
	}

}
