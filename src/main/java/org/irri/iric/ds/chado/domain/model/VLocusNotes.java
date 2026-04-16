package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
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

import org.irri.iric.ds.AppContext;
import org.irri.iric.ds.chado.domain.MultiReferenceLocus;

/**
 */

@Entity(name = "VLocusNotes")
@NamedQueries({
		@NamedQuery(name = "findAllVLocusNotess", query = "select myVLocusNotes from VLocusNotes myVLocusNotes"),
		@NamedQuery(name = "findVLocusNotesByCommonName", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.commonName = ?1"),
		@NamedQuery(name = "findVLocusNotesByCommonNameContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.commonName like ?1"),
		@NamedQuery(name = "findVLocusNotesByContigId", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.contigId = ?1"),
		@NamedQuery(name = "findVLocusNotesByContigName", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.contigName = ?1"),
		@NamedQuery(name = "findVLocusNotesByContigNameContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.contigName like ?1"),

		@NamedQuery(name = "findVLocusNotesByNotesContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.notes like ?1"),
		@NamedQuery(name = "findVLocusNotesByNameIn", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.name in (?1)"),

		@NamedQuery(name = "findVLocusNotesByFeatureId", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.featureId = ?1"),
		@NamedQuery(name = "findVLocusNotesByFmax", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.fmax = ?1"),
		@NamedQuery(name = "findVLocusNotesByFmin", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.fmin = ?1"),
		@NamedQuery(name = "findVLocusNotesByName", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where lower(myVLocusNotes.name) = lower(?1)"),
		@NamedQuery(name = "findVLocusNotesByNameContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where lower(myVLocusNotes.name) like lower(?1)"),
		@NamedQuery(name = "findVLocusNotesByOrganismId", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.organismId = ?1"),
		@NamedQuery(name = "findVLocusNotesByPrimaryKey", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.featureId = ?1"),
		@NamedQuery(name = "findVLocusNotesByStrand", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.strand = ?1") })
// @Table( name = "V_LOCUS_NOTES")
@Table(name = "V_LOCUS_FEATURETYPE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VLocusNotes")
@DiscriminatorValue("0")
public class VLocusNotes implements Serializable, MultiReferenceLocus {
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

	@Column(name = "NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "FMIN", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fmin;
	/**
	 */

	@Column(name = "FMAX", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fmax;
	/**
	 */

	@Column(name = "STRAND", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strand;
	/**
	 */

	@Column(name = "CONTIG_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal contigId;
	/**
	 */

	@Column(name = "CONTIG_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contigName;
	/**
	 */

	/*
	 * @Column(name = "NOTES")
	 * 
	 * @Basic(fetch = FetchType.EAGER)
	 * 
	 * @Lob
	 * 
	 * @XmlElement //byte[] notes; Clob notes;
	 */
	@Column(name = "NOTES")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;

	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "COMMON_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;

	@Column(name = "FEATURE_TYPE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String featureType;

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
	public void setFmin(BigDecimal fmin) {
		this.fmin = fmin;
	}

	/**
	 */
	public Integer getFmin() {
		return this.fmin.intValue();
	}

	/**
	 */
	public void setFmax(BigDecimal fmax) {
		this.fmax = fmax;
	}

	/**
	 */
	public Integer getFmax() {
		return this.fmax.intValue();
	}

	/**
	 */
	public void setStrand(Integer strand) {
		this.strand = strand.intValue();
	}

	/**
	 */
	public Integer getStrand() {
		return this.strand;
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
	// public void setNotes(byte[] notes) {
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 */
	/*
	 * public byte[] getNotes() { return this.notes; }
	 */
	public String getNotes() {
		return this.notes;
		/*
		 * try { return AppContext.clobStringConversion( this.notes ); } catch(Exception
		 * ex) { ex.printStackTrace(); } return null;
		 */
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
	public VLocusNotes() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusNotes that) {
		setFeatureId(that.getFeatureId());
		setName(that.getName());
		setFmin(BigDecimal.valueOf(that.getFmin()));
		setFmax(BigDecimal.valueOf(that.getFmax()));
		setStrand(that.getStrand());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		// setNotes( new Clob( that.getNotes() ));
		setNotes(that.getNotes());
		setOrganismId(that.getOrganismId());
		setCommonName(that.getCommonName());
		setFeatureType(that.getFeatureType());
	}

	/**
	 */
	/*
	 * public boolean equals(Object obj) { if (obj == this) return true; if (!(obj
	 * instanceof VLocusNotes)) return false; VLocusNotes equalCheck = (VLocusNotes)
	 * obj; if ((featureId == null && equalCheck.featureId != null) || (featureId !=
	 * null && equalCheck.featureId == null)) return false; if (featureId != null &&
	 * !featureId.equals(equalCheck.featureId)) return false; return true; }
	 */

	@Override
	public String getUniquename() {

		return this.name;
	}

	@Override
	public Long getChr() {

		// return Long.valueOf(getContig());
		return Long.valueOf(AppContext.guessChrFromString(getContig()));
		/*
		 * try { return Integer.valueOf(
		 * this.getContig().toLowerCase().replace("chr0","").replace("chr","") ); }
		 * catch(Exception ex) { ex.printStackTrace();
		 * AppContext.debug("can't convert contig " + getContig() + " to chromosome"); }
		 * return null;
		 */
	}

	@Override
	public String getContig() {

		return this.contigName;
	}

	@Override
	public String getDescription() {

		return this.getNotes();
	}

	@Override
	public String getOrganism() {

		return this.organismId.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = (int) (prime * result + ((featureId == null) ? 0 :
		// featureId.hashCode()));
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		result = (int) (prime * result + ((contigName == null) ? 0 : contigName.hashCode()));
		result = (int) (prime * result + ((fmin == null) ? 0 : fmin.hashCode()));
		result = (int) (prime * result + ((fmax == null) ? 0 : fmax.hashCode()));
		result = (int) (prime * result + ((featureType == null) ? 0 : featureType.hashCode()));
		return result;
	}

	@Override
	public int compareTo(Object o) {

		VLocusNotes l1 = (VLocusNotes) this;
		VLocusNotes l2 = (VLocusNotes) o;
		int ret = l1.getOrganismId().compareTo(l2.getOrganismId());
		if (ret != 0)
			return ret;
		ret = l1.getContig().compareTo(l2.getContig());
		if (ret != 0)
			return ret;
		ret = l1.getFmin().compareTo(l2.getFmin());
		if (ret != 0)
			return ret;
		ret = l1.getFmax().compareTo(l2.getFmax());
		if (ret != 0)
			return ret;
		ret = l1.getFeatureType().compareTo(l2.getFeatureType());
		return ret;

	}

	@Override
	public boolean equals(Object obj) {

		return compareTo(obj) == 0;
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("strand=[").append(strand).append("] ");
		buffer.append("contigId=[").append(contigId).append("] ");
		buffer.append("contigName=[").append(contigName).append("] ");
		buffer.append("notes=[").append(notes).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("featureType=[").append(featureType).append("] ");

		return buffer.toString();
	}

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

}
