package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.irri.iric.ds.chado.domain.Sequence;


/**
 */

@Entity
@NamedQueries({ @NamedQuery(name = "findPGAllFeatures", query = "select myFeature from FeaturePostgres myFeature"),
		@NamedQuery(name = "findPGFeatureByDbxrefId", query = "select myFeature from FeaturePostgres myFeature where myFeature.dbxrefId = ?1"),
		@NamedQuery(name = "findPGFeatureByFeatureId", query = "select myFeature from FeaturePostgres myFeature where myFeature.featureId = ?1"),
		@NamedQuery(name = "findPGFeatureByIsAnalysis", query = "select myFeature from FeaturePostgres myFeature where myFeature.isAnalysis = ?1"),
		@NamedQuery(name = "findPGFeatureByIsObsolete", query = "select myFeature from FeaturePostgres myFeature where myFeature.isObsolete = ?1"),
		@NamedQuery(name = "findPGFeatureByMd5checksum", query = "select myFeature from FeaturePostgres myFeature where myFeature.md5checksum = ?1"),
		@NamedQuery(name = "findPGFeatureByMd5checksumContaining", query = "select myFeature from FeaturePostgres myFeature where myFeature.md5checksum like ?1"),
		@NamedQuery(name = "findPGFeatureByName", query = "select myFeature from FeaturePostgres myFeature where myFeature.name = ?1"),
		@NamedQuery(name = "findPGFeatureByNameContaining", query = "select myFeature from FeaturePostgres myFeature where myFeature.name like ?1"),
		@NamedQuery(name = "findPGFeatureByOrganismId", query = "select myFeature from FeaturePostgres myFeature where myFeature.organismId = ?1"),
		@NamedQuery(name = "findPGFeatureByPrimaryKey", query = "select myFeature from FeaturePostgres myFeature where myFeature.featureId = ?1"),
		@NamedQuery(name = "findPGFeatureBySeqlen", query = "select myFeature from FeaturePostgres myFeature where myFeature.seqlen = ?1"),
		@NamedQuery(name = "findPGFeatureByTimeaccessioned", query = "select myFeature from FeaturePostgres myFeature where myFeature.timeaccessioned = ?1"),
		@NamedQuery(name = "findPGFeatureByTimelastmodified", query = "select myFeature from FeaturePostgres myFeature where myFeature.timelastmodified = ?1"),
		@NamedQuery(name = "findPGFeatureByTypeId", query = "select myFeature from FeaturePostgres myFeature where myFeature.typeId = ?1"),
		@NamedQuery(name = "findPGFeatureByUniquename", query = "select myFeature from FeaturePostgres myFeature where myFeature.uniquename = ?1"),
		@NamedQuery(name = "findPGFeatureByUniquenameContaining", query = "select myFeature from FeaturePostgres myFeature where myFeature.uniquename like ?1") })
@Table(name = "FEATURE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "FeaturePostgres")
public class FeaturePostgres implements Serializable, org.irri.iric.ds.chado.domain.Feature, Sequence {
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

	@Column(name = "DBXREF_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal dbxrefId;
	/**
	 */

	@Column(name = "ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "RESIDUES")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	// byte[] residues;
	// Clob residues;
	String residues;
	/**
	 */

	@Column(name = "SEQLEN", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal seqlen;
	/**
	 */

	@Column(name = "MD5CHECKSUM", length = 128)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String md5checksum;
	/**
	 */

	@Column(name = "TYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;
	/**
	 */

	@Column(name = "IS_ANALYSIS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isAnalysis;
	/**
	 */

	@Column(name = "IS_OBSOLETE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isObsolete;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMEACCESSIONED", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar timeaccessioned;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMELASTMODIFIED", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar timelastmodified;
	/**
	 */

	@Column(name = "UNIQUENAME", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String uniquename;

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
	public void setDbxrefId(BigDecimal dbxrefId) {
		this.dbxrefId = dbxrefId;
	}

	/**
	 */
	public BigDecimal getDbxrefId() {
		return this.dbxrefId;
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
	public void setResidues(String residues) {
		this.residues = residues;
	}

	/**
	 */
	public String getResidues() {
		return this.residues;
	}

	/**
	 */
	public void setSeqlen(BigDecimal seqlen) {
		this.seqlen = seqlen;
	}

	/**
	 */
	public BigDecimal getSeqlen() {
		return this.seqlen;
	}

	/**
	 */
	public void setMd5checksum(String md5checksum) {
		this.md5checksum = md5checksum;
	}

	/**
	 */
	public String getMd5checksum() {
		return this.md5checksum;
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
	public void setIsAnalysis(Integer isAnalysis) {
		this.isAnalysis = isAnalysis;
	}

	/**
	 */
	public Integer getIsAnalysis() {
		return this.isAnalysis;
	}

	/**
	 */
	public void setIsObsolete(Integer isObsolete) {
		this.isObsolete = isObsolete;
	}

	/**
	 */
	public Integer getIsObsolete() {
		return this.isObsolete;
	}

	/**
	 */
	public void setTimeaccessioned(Calendar timeaccessioned) {
		this.timeaccessioned = timeaccessioned;
	}

	/**
	 */
	public Calendar getTimeaccessioned() {
		return this.timeaccessioned;
	}

	/**
	 */
	public void setTimelastmodified(Calendar timelastmodified) {
		this.timelastmodified = timelastmodified;
	}

	/**
	 */
	public Calendar getTimelastmodified() {
		return this.timelastmodified;
	}

	/**
	 */
	public void setUniquename(String uniquename) {
		this.uniquename = uniquename;
	}

	/**
	 */
	public String getUniquename() {
		return this.uniquename;
	}

	/**
	 */
	public FeaturePostgres() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(FeaturePostgres that) {
		setFeatureId(that.getFeatureId());
		setDbxrefId(that.getDbxrefId());
		setOrganismId(that.getOrganismId());
		setName(that.getName());
		setResidues(that.getResidues());
		setSeqlen(that.getSeqlen());
		setMd5checksum(that.getMd5checksum());
		setTypeId(that.getTypeId());
		setIsAnalysis(that.getIsAnalysis());
		setIsObsolete(that.getIsObsolete());
		setTimeaccessioned(that.getTimeaccessioned());
		setTimelastmodified(that.getTimelastmodified());
		setUniquename(that.getUniquename());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("dbxrefId=[").append(dbxrefId).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("residues=[").append(residues).append("] ");
		buffer.append("seqlen=[").append(seqlen).append("] ");
		buffer.append("md5checksum=[").append(md5checksum).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("isAnalysis=[").append(isAnalysis).append("] ");
		buffer.append("isObsolete=[").append(isObsolete).append("] ");
		buffer.append("timeaccessioned=[").append(timeaccessioned).append("] ");
		buffer.append("timelastmodified=[").append(timelastmodified).append("] ");
		buffer.append("uniquename=[").append(uniquename).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((featureId == null) ? 0 : featureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof FeaturePostgres))
			return false;
		FeaturePostgres equalCheck = (FeaturePostgres) obj;
		if ((featureId == null && equalCheck.featureId != null) || (featureId != null && equalCheck.featureId == null))
			return false;
		if (featureId != null && !featureId.equals(equalCheck.featureId))
			return false;
		return true;
	}

	@Override
	public String getSequenceInPosition(long start, long end) {
		
		try {
			// return AppContext.clobStringConversion(this.residues);
			return residues;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
