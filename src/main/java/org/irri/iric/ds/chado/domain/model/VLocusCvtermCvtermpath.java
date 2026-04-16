package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.irri.iric.ds.chado.domain.CvTerm;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.object.VLocusCvtermCvtermpathPK;
import org.irri.iric.ds.utils.DbUtils;

/**
 */
@IdClass(VLocusCvtermCvtermpathPK.class)
@Entity(name = "VLocusCvtermCvtermpath")
@NamedQueries({
		@NamedQuery(name = "findAllVLocusCvtermCvtermpaths", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByCommonName", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.commonName = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByCvOrganism", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.cvId = ?1 and myVLocusCvtermCvtermpath.organismId = ?2"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByCvOrganismInFeatureId", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.cvId = ?1 and myVLocusCvtermCvtermpath.organismId = ?2 and myVLocusCvtermCvtermpath.featureId in (?3)"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByObjCvtermCvOrg", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.objCvterm = ?1 and myVLocusCvtermCvtermpath.cvId=?2 and myVLocusCvtermCvtermpath.organismId=?3 order by myVLocusCvtermCvtermpath.contigName, myVLocusCvtermCvtermpath.fmin, myVLocusCvtermCvtermpath.pathdistance"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByCvOrgInFeatureId", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.cvId=?1 and myVLocusCvtermCvtermpath.organismId=?2 and myVLocusCvtermCvtermpath.featureId in (?3) order by myVLocusCvtermCvtermpath.contigName, myVLocusCvtermCvtermpath.fmin, myVLocusCvtermCvtermpath.pathdistance"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByCommonNameContaining", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.commonName like ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByContigId", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.contigId = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByContigName", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.contigName = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByContigNameContaining", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.contigName like ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByCvName", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.cvName = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByCvNameContaining", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.cvName like ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByCvtermId", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.cvtermId = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByFeatureId", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.featureId = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByFmax", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.fmax = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByFmin", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.fmin = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByName", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.name = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByNameContaining", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.name like ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByNotes", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.notes = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByNotesContaining", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.notes like ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByObjAcc", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.objAcc = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByObjAccContaining", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.objAcc like ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByObjCvterm", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.objCvterm = ?1 order by myVLocusCvtermCvtermpath.contigName, myVLocusCvtermCvtermpath.fmin, myVLocusCvtermCvtermpath.pathdistance"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByObjCvtermContaining", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.objCvterm like ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByOrganismId", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.organismId = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByPrimaryKey", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.featureId = ?1 and myVLocusCvtermCvtermpath.cvtermId = ?2 and myVLocusCvtermCvtermpath.organismId = ?3"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathByStrand", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.strand = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathBySubjAcc", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.subjAcc = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathBySubjAccContaining", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.subjAcc like ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathBySubjCvterm", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.subjCvterm = ?1"),
		@NamedQuery(name = "findVLocusCvtermCvtermpathBySubjCvtermContaining", query = "select myVLocusCvtermCvtermpath from VLocusCvtermCvtermpath myVLocusCvtermCvtermpath where myVLocusCvtermCvtermpath.subjCvterm like ?1") })
@Table(name = "V_LOCUS_CVTERM_CVTERMPATH")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VLocusCvtermCvtermpath")
public class VLocusCvtermCvtermpath implements Serializable, Locus, CvTerm, Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "FEATURE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal featureId;
	/**
	 */

	@Column(name = "NAME", length = 4000)
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

	@Column(name = "CONTIG_NAME", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contigName;
	/**
	 */

	@Column(name = "NOTES", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;
	/**
	 */

	@Column(name = "CV_NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvName;

	@Column(name = "DB", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String db;

	@Column(name = "CV_ID", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cvId;

	/**
	 */

	@Column(name = "CVTERM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal cvtermId;
	/**
	 */

	@Column(name = "SUBJ_ACC", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjAcc;
	/**
	 */

	@Column(name = "SUBJ_CVTERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjCvterm;
	/**
	 */

	@Column(name = "OBJ_ACC", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objAcc;
	/**
	 */

	@Column(name = "OBJ_CVTERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objCvterm;
	/**
	 */

	@Column(name = "PATHDISTANCE", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pathdistance;
	/**
	 */

	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "COMMON_NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;

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
	public void setName(String cvterm) {
		// this.name = name;
		this.subjCvterm = name;
	}

	/**
	 */
	public String getName() {
		return this.subjCvterm;
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
		this.strand = strand;
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
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 */
	public String getNotes() {
		// return this.notes;
		return this.notes;

	}

	/**
	 */
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	/**
	 */
	public String getCvName() {
		return this.cvName;
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
	public void setSubjAcc(String subjAcc) {
		this.subjAcc = subjAcc;
	}

	/**
	 */
	public String getSubjAcc() {
		return this.subjAcc;
	}

	/**
	 */
	public void setSubjCvterm(String subjCvterm) {
		this.subjCvterm = subjCvterm;
	}

	/**
	 */
	public String getSubjCvterm() {
		return this.subjCvterm;
	}

	/**
	 */
	public void setObjAcc(String objAcc) {
		this.objAcc = objAcc;
	}

	/**
	 */
	public String getObjAcc() {
		return this.objAcc;
	}

	/**
	 */
	public void setObjCvterm(String objCvterm) {
		this.objCvterm = objCvterm;
	}

	/**
	 */
	public String getObjCvterm() {
		return this.objCvterm;
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
	public VLocusCvtermCvtermpath() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusCvtermCvtermpath that) {
		setFeatureId(that.getFeatureId());
		setName(that.getName());
		setFmin(BigDecimal.valueOf(that.getFmin()));
		setFmax(BigDecimal.valueOf(that.getFmax()));
		setStrand(that.getStrand());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		setNotes(that.getNotes());
		setCvName(that.getCvName());
		setCvtermId(that.getCvtermId());
		setSubjAcc(that.getSubjAcc());
		setSubjCvterm(that.getSubjCvterm());
		setObjAcc(that.getObjAcc());
		setObjCvterm(that.getObjCvterm());
		setOrganismId(that.getOrganismId());
		setCommonName(that.getCommonName());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
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
		buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("subjAcc=[").append(subjAcc).append("] ");
		buffer.append("subjCvterm=[").append(subjCvterm).append("] ");
		buffer.append("objAcc=[").append(objAcc).append("] ");
		buffer.append("objCvterm=[").append(objCvterm).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((featureId == null) ? 0 : featureId.hashCode()));
		result = (int) (prime * result + ((cvtermId == null) ? 0 : cvtermId.hashCode()));
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		return compareTo(obj) == 0;

		/*
		 * if (!(obj instanceof VLocusCvtermCvtermpath)) return false;
		 * VLocusCvtermCvtermpath c= (VLocusCvtermCvtermpath)obj; return
		 * c.getUniquename().equals(this.getUniquename()) &&
		 * c.getOrganismId().equals(this.getOrganismId());
		 */
	}

	/**
	 */
	// public boolean equals(Object obj) {
	// if (obj == this)
	// return true;
	// if (!(obj instanceof VLocusCvtermCvtermpath))
	// return false;
	// VLocusCvtermCvtermpath equalCheck = (VLocusCvtermCvtermpath) obj;
	// if ((featureId == null && equalCheck.featureId != null) || (featureId != null
	// && equalCheck.featureId == null))
	// return false;
	// if (featureId != null && !featureId.equals(equalCheck.featureId))
	// return false;
	// if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null &&
	// equalCheck.cvtermId == null))
	// return false;
	// if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
	// return false;
	// if ((organismId == null && equalCheck.organismId != null) || (organismId !=
	// null && equalCheck.organismId == null))
	// return false;
	// if (organismId != null && !organismId.equals(equalCheck.organismId))
	// return false;
	// return true;
	// }

	@Override
	public BigDecimal getCvTermId() {

		return this.cvtermId;
	}

	@Override
	public int compareTo(Object o) {

		VLocusCvtermCvtermpath l1 = (VLocusCvtermCvtermpath) this;
		VLocusCvtermCvtermpath l2 = (VLocusCvtermCvtermpath) o;
		int ret = l1.getContig().compareTo(l2.getContig());
		if (ret != 0)
			return ret;
		ret = l1.getFmin().compareTo(l2.getFmin());
		if (ret != 0)
			return ret;
		ret = l1.getFmax().compareTo(l2.getFmax());
		return 0;
	}

	@Override
	public String getDefinition() {

		return this.subjCvterm;
	}

	@Override
	public String getAccession() {

		return this.subjAcc;
	}

	@Override
	public String getUniquename() {

		return this.name;
	}

	@Override
	public Long getChr() {

		// return Long.valueOf(getContig());
		return Long.valueOf(DbUtils.guessChrFromString(getContig()));
	}

	@Override
	public String getContig() {

		return this.contigName;
	}

	@Override
	public String getDescription() {

		String mynotes = "no description";
		if (this.notes != null)
			mynotes = this.notes;
		String strdb = "";
		if (db == null || db.isEmpty()) {

		} else
			strdb = db.toUpperCase() + ":";
		if (pathdistance > 10000)
			return strdb + this.subjAcc + " " + this.subjCvterm + " -*-> " + strdb + this.objAcc + " " + this.objCvterm
					+ " (" + mynotes + ")";
		else if (pathdistance == 0)
			return strdb + this.subjAcc + " " + this.subjCvterm + " (" + mynotes + ")";
		else
			return strdb + this.subjAcc + " " + this.subjCvterm + " --" + pathdistance + "-> " + strdb + this.objAcc
					+ " " + this.objCvterm + " (" + mynotes + ")";
	}

	public Integer getPathdistance() {
		return pathdistance;
	}

	public void setPathdistance(Integer pathdistance) {
		this.pathdistance = pathdistance;
	}

	@Override
	public String getFeatureType() {
		return null;
	}

}
