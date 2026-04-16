package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
import org.irri.iric.ds.chado.domain.LocusPromoter;


/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVLocusPromoters", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter"),
		@NamedQuery(name = "findVLocusPromoterByCommonName", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.commonName = ?1"),
		@NamedQuery(name = "findVLocusPromoterByCommonNameContaining", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.commonName like ?1"),
		@NamedQuery(name = "findVLocusPromoterByContigId", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.contigId = ?1"),
		@NamedQuery(name = "findVLocusPromoterByContigName", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.contigName = ?1"),
		@NamedQuery(name = "findVLocusPromoterByContigNameContaining", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.contigName like ?1"),
		@NamedQuery(name = "findVLocusPromoterByFeatureId", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.featureId = ?1"),
		@NamedQuery(name = "findVLocusPromoterByFmax", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.fmax = ?1"),
		@NamedQuery(name = "findVLocusPromoterByFmin", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.fmin = ?1"),
		@NamedQuery(name = "findVLocusPromoterByGeneOverlaps", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.geneOverlaps = ?1"),
		@NamedQuery(name = "findVLocusPromoterByGeneOverlapsContaining", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.geneOverlaps like ?1"),
		@NamedQuery(name = "findVLocusPromoterByName", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.name = ?1"),
		@NamedQuery(name = "findVLocusPromoterByNameContaining", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.name like ?1"),
		@NamedQuery(name = "findVLocusPromoterByNotes", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.notes = ?1"),
		@NamedQuery(name = "findVLocusPromoterByNotesContaining", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.notes like ?1"),
		@NamedQuery(name = "findVLocusPromoterByOrganismId", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.organismId = ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureChr", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureChr = ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureChrContaining", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureChr like ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureDb", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureDb = ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureEnd", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureEnd = ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureId", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureId = ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureName", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureName = ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureNameContaining", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureName like ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureStart", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureStart = ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureType", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureType = ?1"),
		@NamedQuery(name = "findVLocusPromoterByPfeatureTypeContaining", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.pfeatureType like ?1"),
		@NamedQuery(name = "findVLocusPromoterByPrimaryKey", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.featureId = ?1"),
		@NamedQuery(name = "findVLocusPromoterByStrand", query = "select myVLocusPromoter from VLocusPromoter myVLocusPromoter where myVLocusPromoter.strand = ?1") })
@Table(name = "V_LOCUS_PROMOTER")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VLocusPromoter")
public class VLocusPromoter implements Serializable, LocusPromoter {
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

	@Column(name = "PFEATURE_START")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pfeatureStart;
	/**
	 */

	@Column(name = "PFEATURE_END")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pfeatureEnd;
	/**
	 */

	@Column(name = "PFEATURE_CHR", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String pfeatureChr;
	/**
	 */

	@Column(name = "PFEATURE_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pfeatureId;
	/**
	 */

	@Column(name = "PFEATURE_NAME", length = 250)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String pfeatureName;
	/**
	 */

	@Column(name = "PFEATURE_DB")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pfeatureDb;
	/**
	 */

	@Column(name = "PFEATURE_TYPE", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String pfeatureType;
	/**
	 */

	@Column(name = "GENE_OVERLAPS", length = 1000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String geneOverlaps;

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
		return notes;
	}

	/**
	 */
	public void setPfeatureStart(Integer pfeatureStart) {
		this.pfeatureStart = pfeatureStart;
	}

	/**
	 */
	public Integer getPfeatureStart() {
		return this.pfeatureStart;
	}

	/**
	 */
	public void setPfeatureEnd(Integer pfeatureEnd) {
		this.pfeatureEnd = pfeatureEnd;
	}

	/**
	 */
	public Integer getPfeatureEnd() {
		return this.pfeatureEnd;
	}

	/**
	 */
	public void setPfeatureChr(String pfeatureChr) {
		this.pfeatureChr = pfeatureChr;
	}

	/**
	 */
	public String getPfeatureChr() {
		return this.pfeatureChr;
	}

	/**
	 */
	public void setPfeatureId(BigDecimal pfeatureId) {
		this.pfeatureId = pfeatureId;
	}

	/**
	 */
	public BigDecimal getPfeatureId() {
		return this.pfeatureId;
	}

	/**
	 */
	public void setPfeatureName(String pfeatureName) {
		this.pfeatureName = pfeatureName;
	}

	/**
	 */
	public String getPfeatureName() {
		return this.pfeatureName;
	}

	/**
	 */
	public void setPfeatureDb(BigDecimal pfeatureDb) {
		this.pfeatureDb = pfeatureDb;
	}

	/**
	 */
	public BigDecimal getPfeatureDb() {
		return this.pfeatureDb;
	}

	/**
	 */
	public void setPfeatureType(String pfeatureType) {
		this.pfeatureType = pfeatureType;
	}

	/**
	 */
	public String getPfeatureType() {
		return this.pfeatureType;
	}

	/**
	 */
	public void setGeneOverlaps(String geneOverlaps) {
		this.geneOverlaps = geneOverlaps;
	}

	/**
	 */
	public String getGeneOverlaps() {
		return this.geneOverlaps;
	}

	/**
	 */
	public VLocusPromoter() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusPromoter that) {
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
		setPfeatureStart(that.getPfeatureStart());
		setPfeatureEnd(that.getPfeatureEnd());
		setPfeatureChr(that.getPfeatureChr());
		setPfeatureId(that.getPfeatureId());
		setPfeatureName(that.getPfeatureName());
		setPfeatureDb(that.getPfeatureDb());
		setPfeatureType(that.getPfeatureType());
		setGeneOverlaps(that.getGeneOverlaps());
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
		buffer.append("pfeatureStart=[").append(pfeatureStart).append("] ");
		buffer.append("pfeatureEnd=[").append(pfeatureEnd).append("] ");
		buffer.append("pfeatureChr=[").append(pfeatureChr).append("] ");
		buffer.append("pfeatureId=[").append(pfeatureId).append("] ");
		buffer.append("pfeatureName=[").append(pfeatureName).append("] ");
		buffer.append("pfeatureDb=[").append(pfeatureDb).append("] ");
		buffer.append("pfeatureType=[").append(pfeatureType).append("] ");
		buffer.append("geneOverlaps=[").append(geneOverlaps).append("] ");

		return buffer.toString();
	}
	//
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
	// if (!(obj instanceof VLocusPromoter))
	// return false;
	// VLocusPromoter equalCheck = (VLocusPromoter) obj;
	// if ((featureId == null && equalCheck.featureId != null) || (featureId != null
	// && equalCheck.featureId == null))
	// return false;
	// if (featureId != null && !featureId.equals(equalCheck.featureId))
	// return false;
	// return true;
	// }
	//
	//

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
	public String getIRICName() {
		return null;
	}

	@Override
	public String getMSU7Name() {
		return null;
	}

	@Override
	public String getRAPPredName() {
		return null;
	}

	@Override
	public String getRAPRepName() {
		return null;
	}

	@Override
	public String getFGeneshName() {
		return null;
	}

	@Override
	public Set<String> getOverlappingGenes() {
		
		Set setnames = new HashSet();
		if (geneOverlaps != null) {
			String genenames[] = geneOverlaps.split("\\s+|,");
			for (int i = 0; i < genenames.length; i++)
				setnames.add(genenames[i].trim());
			setnames.remove("");
		}
		return setnames;

	}

	@Override
	public String getUniquename() {
		
		return this.name;
	}

	@Override
	public Long getChr() {
		
		return Long.valueOf(getContig());
	}

	@Override
	public String getContig() {
		
		return this.contigName;
	}

	@Override
	public String getDescription() {
		
		return this.getPromoterType() + " " + this.getPromoterName() + "[" + this.getPromoterStart() + "-"
				+ this.getPromoterEnd() + "]" + " at -" + this.getDistanceFromTSS() + " " + getNotes() + " "
				+ this.getOverlappingGenes();
	}

	@Override
	public String getPromoterName() {
		
		return this.pfeatureName;
	}

	@Override
	public String getPromoterType() {
		
		return this.pfeatureType;
	}

	@Override
	public String getPromoterDB() {
		
		if (pfeatureDb.equals(BigDecimal.ONE))
			return "PlantPromDB";
		else if (pfeatureDb.equals(BigDecimal.valueOf(2L)))
			return "FGenesh++";
		else
			return pfeatureDb.toString();
	}

	@Override
	public int getPromoterStart() {
		
		return this.pfeatureStart;
	}

	@Override
	public int getPromoterEnd() {
		
		return this.pfeatureEnd;
	}

	@Override
	public int getDistanceFromTSS() {
		
		if (this.strand > 0)
			return this.fmin - this.pfeatureEnd;
		else
			return this.pfeatureStart - this.fmax;

	}

	@Override
	public String getFeatureType() {
		return null;
	}

}
