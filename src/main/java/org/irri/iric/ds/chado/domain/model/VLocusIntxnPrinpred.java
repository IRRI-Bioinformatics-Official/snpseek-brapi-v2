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
		@NamedQuery(name = "findAllVLocusIntxnPrinpreds", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByCoexpDenserank", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.coexpDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByCoexpRank", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.coexpRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByCoexpScore", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.coexpScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByCommonName", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.commonName = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByCommonNameContaining", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.commonName like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByContigId", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.contigId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByContigName", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.contigName = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByContigNameContaining", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.contigName like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByFeatureId", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.featureId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByFmax", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.fmax = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByFmin", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.fmin = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByName", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.name = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByNameContaining", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.name like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByNotes", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.notes = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByNotesContaining", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.notes like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByOrganismId", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.organismId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByPrimaryKey", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.featureId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByQfeatureId", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.qfeatureId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByQfeatureName", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.qfeatureName = ?1"),

		@NamedQuery(name = "findVLocusIntxnPrinpredByQfeatureIdIn", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.qfeatureId in (?1) order by  myVLocusIntxnPrinpred.coexpScore desc"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByQfeatureNameIn", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.qfeatureName in (?1) order by  myVLocusIntxnPrinpred.coexpScore desc"),

		@NamedQuery(name = "findVLocusIntxnPrinpredByQfeatureNameContaining", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.qfeatureName like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByRssbpDenserank", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.rssbpDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByRssbpRank", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.rssbpRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByRssbpScore", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.rssbpScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByRssccDenserank", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.rssccDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByRssccRank", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.rssccRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByRssccScore", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.rssccScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByRssmfDenserank", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.rssmfDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByRssmfRank", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.rssmfRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByRssmfScore", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.rssmfScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinpredByStrand", query = "select myVLocusIntxnPrinpred from VLocusIntxnPrinpred myVLocusIntxnPrinpred where myVLocusIntxnPrinpred.strand = ?1") })
@Table(name = "V_LOCUS_INTXN_PRINPRED")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VLocusIntxnPrinpred")
public class VLocusIntxnPrinpred implements Serializable, Locus {
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

	@Column(name = "COEXP_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal coexpScore;
	/**
	 */

	@Column(name = "COEXP_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer coexpRank;
	/**
	 */

	@Column(name = "COEXP_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer coexpDenserank;
	/**
	 */

	@Column(name = "RSSCC_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssccScore;
	/**
	 */

	@Column(name = "RSSCC_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssccRank;
	/**
	 */

	@Column(name = "RSSCC_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssccDenserank;
	/**
	 */

	@Column(name = "RSSBP_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssbpScore;
	/**
	 */

	@Column(name = "RSSBP_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssbpRank;
	/**
	 */

	@Column(name = "RSSBP_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssbpDenserank;
	/**
	 */

	@Column(name = "RSSMF_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssmfScore;
	/**
	 */

	@Column(name = "RSSMF_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssmfRank;
	/**
	 */

	@Column(name = "RSSMF_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssmfDenserank;

	@Column(name = "COEXP_MAXSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal coexpMaxscore;
	/**
	 */

	@Column(name = "COEXP_MAXRANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer coexpMaxrank;

	@Column(name = "RSSCC_MAXSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssccMaxscore;
	/**
	 */

	@Column(name = "RSSCC_MAXRANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssccMaxrank;

	@Column(name = "RSSBP_MAXSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssbpMaxscore;
	/**
	 */

	@Column(name = "RSSBP_MAXRANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssbpMaxrank;

	@Column(name = "RSSMF_MAXSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssmfMaxscore;
	/**
	 */

	@Column(name = "RSSMF_MAXRANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssmfMaxrank;

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
	public void setCoexpScore(BigDecimal coexpScore) {
		this.coexpScore = coexpScore;
	}

	/**
	 */
	public BigDecimal getCoexpScore() {
		return this.coexpScore;
	}

	/**
	 */
	public void setCoexpRank(Integer coexpRank) {
		this.coexpRank = coexpRank;
	}

	/**
	 */
	public Integer getCoexpRank() {
		return this.coexpRank;
	}

	/**
	 */
	public void setCoexpDenserank(Integer coexpDenserank) {
		this.coexpDenserank = coexpDenserank;
	}

	/**
	 */
	public Integer getCoexpDenserank() {
		return this.coexpDenserank;
	}

	/**
	 */
	public void setRssccScore(BigDecimal rssccScore) {
		this.rssccScore = rssccScore;
	}

	/**
	 */
	public BigDecimal getRssccScore() {
		return this.rssccScore;
	}

	/**
	 */
	public void setRssccRank(Integer rssccRank) {
		this.rssccRank = rssccRank;
	}

	/**
	 */
	public Integer getRssccRank() {
		return this.rssccRank;
	}

	/**
	 */
	public void setRssccDenserank(Integer rssccDenserank) {
		this.rssccDenserank = rssccDenserank;
	}

	/**
	 */
	public Integer getRssccDenserank() {
		return this.rssccDenserank;
	}

	/**
	 */
	public void setRssbpScore(BigDecimal rssbpScore) {
		this.rssbpScore = rssbpScore;
	}

	/**
	 */
	public BigDecimal getRssbpScore() {
		return this.rssbpScore;
	}

	/**
	 */
	public void setRssbpRank(Integer rssbpRank) {
		this.rssbpRank = rssbpRank;
	}

	/**
	 */
	public Integer getRssbpRank() {
		return this.rssbpRank;
	}

	/**
	 */
	public void setRssbpDenserank(Integer rssbpDenserank) {
		this.rssbpDenserank = rssbpDenserank;
	}

	/**
	 */
	public Integer getRssbpDenserank() {
		return this.rssbpDenserank;
	}

	/**
	 */
	public void setRssmfScore(BigDecimal rssmfScore) {
		this.rssmfScore = rssmfScore;
	}

	/**
	 */
	public BigDecimal getRssmfScore() {
		return this.rssmfScore;
	}

	/**
	 */
	public void setRssmfRank(Integer rssmfRank) {
		this.rssmfRank = rssmfRank;
	}

	/**
	 */
	public Integer getRssmfRank() {
		return this.rssmfRank;
	}

	/**
	 */
	public void setRssmfDenserank(Integer rssmfDenserank) {
		this.rssmfDenserank = rssmfDenserank;
	}

	/**
	 */
	public Integer getRssmfDenserank() {
		return this.rssmfDenserank;
	}

	/**
	 */
	public VLocusIntxnPrinpred() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusIntxnPrinpred that) {
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
		setCoexpScore(that.getCoexpScore());
		setCoexpRank(that.getCoexpRank());
		setCoexpDenserank(that.getCoexpDenserank());
		setRssccScore(that.getRssccScore());
		setRssccRank(that.getRssccRank());
		setRssccDenserank(that.getRssccDenserank());
		setRssbpScore(that.getRssbpScore());
		setRssbpRank(that.getRssbpRank());
		setRssbpDenserank(that.getRssbpDenserank());
		setRssmfScore(that.getRssmfScore());
		setRssmfRank(that.getRssmfRank());
		setRssmfDenserank(that.getRssmfDenserank());
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
		buffer.append("coexpScore=[").append(coexpScore).append("] ");
		buffer.append("coexpRank=[").append(coexpRank).append("] ");
		buffer.append("coexpDenserank=[").append(coexpDenserank).append("] ");
		buffer.append("rssccScore=[").append(rssccScore).append("] ");
		buffer.append("rssccRank=[").append(rssccRank).append("] ");
		buffer.append("rssccDenserank=[").append(rssccDenserank).append("] ");
		buffer.append("rssbpScore=[").append(rssbpScore).append("] ");
		buffer.append("rssbpRank=[").append(rssbpRank).append("] ");
		buffer.append("rssbpDenserank=[").append(rssbpDenserank).append("] ");
		buffer.append("rssmfScore=[").append(rssmfScore).append("] ");
		buffer.append("rssmfRank=[").append(rssmfRank).append("] ");
		buffer.append("rssmfDenserank=[").append(rssmfDenserank).append("] ");

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
	// if (!(obj instanceof VLocusIntxnPrinpred))
	// return false;
	// VLocusIntxnPrinpred equalCheck = (VLocusIntxnPrinpred) obj;
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
		
		return this.contigName;
	}

	@Override
	public String getDescription() {
		
		StringBuffer buff = new StringBuffer();

		if (coexpScore != null)
			buff.append(" COEXP:" + String.format("%.2f", coexpScore) + "/" + String.format("%.2f", coexpMaxscore) + " "
					+ coexpRank + "/" + coexpMaxrank);
		if (rssccScore != null)
			buff.append(" CC" + String.format("%.2f", rssccScore) + "/" + String.format("%.2f", rssccMaxscore) + " "
					+ rssccRank + "/" + rssccMaxrank);
		if (rssbpScore != null)
			buff.append(" BP" + String.format("%.2f", rssbpScore) + "/" + String.format("%.2f", rssbpMaxscore) + " "
					+ rssbpRank + "/" + rssbpMaxrank);
		if (rssmfScore != null)
			buff.append(" MF" + String.format("%.2f", rssmfScore) + "/" + String.format("%.2f", rssmfMaxscore) + " "
					+ rssmfRank + "/" + rssmfMaxrank);

		return buff + " " + this.notes;
	}

	@Override
	public String getFeatureType() {
		return null;
	}

}
