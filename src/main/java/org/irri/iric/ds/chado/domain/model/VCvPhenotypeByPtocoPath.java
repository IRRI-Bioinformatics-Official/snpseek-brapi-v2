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

import org.irri.iric.ds.chado.domain.CvTermDataset;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVCvPhenotypeByPtocoPaths", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjAcc", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objAcc = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjAccContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objAcc like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvid", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objCvid = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvname", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objCvname = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvnameContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objCvname like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvterm", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objCvterm = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvtermContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objCvterm like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvtermId", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objCvtermId = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjDb", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objDb = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjDbContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objDb like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByPathdistance", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.pathdistance = ?1"),

		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvtermPosMaxdist", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objCvname=?1 and  myVCvPhenotypeByPtocoPath.objCvterm = ?2 and ((myVCvPhenotypeByPtocoPath.pathdistance>0 and myVCvPhenotypeByPtocoPath.pathdistance<=?3) or myVCvPhenotypeByPtocoPath.pathdistance is null)  and myVCvPhenotypeByPtocoPath.dataset=?4 order by myVCvPhenotypeByPtocoPath.pathdistance, myVCvPhenotypeByPtocoPath.subjCvterm"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvtermNegMaxdist", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where  myVCvPhenotypeByPtocoPath.objCvname=?1 and  myVCvPhenotypeByPtocoPath.objCvterm = ?2 and myVCvPhenotypeByPtocoPath.pathdistance<0 and myVCvPhenotypeByPtocoPath.pathdistance>=?3  and myVCvPhenotypeByPtocoPath.dataset=?4 order by myVCvPhenotypeByPtocoPath.pathdistance desc, myVCvPhenotypeByPtocoPath.subjCvterm"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvtermPosDist", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.objCvname=?1 and  myVCvPhenotypeByPtocoPath.objCvterm = ?2 and ( myVCvPhenotypeByPtocoPath.pathdistance>0  or  myVCvPhenotypeByPtocoPath.pathdistance is null)  and myVCvPhenotypeByPtocoPath.dataset=?3 order by myVCvPhenotypeByPtocoPath.pathdistance, myVCvPhenotypeByPtocoPath.subjCvterm"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByObjCvtermNegDist", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where  myVCvPhenotypeByPtocoPath.objCvname=?1 and myVCvPhenotypeByPtocoPath.objCvterm = ?2 and myVCvPhenotypeByPtocoPath.pathdistance<0  and myVCvPhenotypeByPtocoPath.dataset=?3 order by myVCvPhenotypeByPtocoPath.pathdistance desc, myVCvPhenotypeByPtocoPath.subjCvterm"),

		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByPrimaryKey", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjCvtermId = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByQualValue", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.qualValue = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByQualValueContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.qualValue like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathByQuanValue", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.quanValue = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjAcc", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjAcc = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjAccContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjAcc like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjCvid", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjCvid = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjCvname", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjCvname = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjCvnameContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjCvname like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjCvterm", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjCvterm = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjCvtermContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjCvterm like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjCvtermId", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjCvtermId = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjDb", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjDb = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjDbContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjDb like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjDefinition", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjDefinition = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoPathBySubjDefinitionContaining", query = "select myVCvPhenotypeByPtocoPath from VCvPhenotypeByPtocoPath myVCvPhenotypeByPtocoPath where myVCvPhenotypeByPtocoPath.subjDefinition like ?1") })
// @Table( name = "V_CV_PHENOTYPE_BY_PTOCO_PATH")
@Table(name = "V_CV_PHEN_BY_PTOCO_PATH_ALL")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VCvPhenotypeByPtocoPath")
public class VCvPhenotypeByPtocoPath implements Serializable, CvTermDataset {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SUBJ_CVTERM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal subjCvtermId;
	/**
	 */

	@Column(name = "SUBJ_CVID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal subjCvid;
	/**
	 */

	@Column(name = "SUBJ_CVNAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjCvname;
	/**
	 */

	@Column(name = "SUBJ_DB", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjDb;
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

	@Column(name = "SUBJ_DEFINITION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjDefinition;
	/**
	 */

	@Column(name = "OBJ_CVID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal objCvid;
	/**
	 */

	@Column(name = "OBJ_CVNAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objCvname;
	/**
	 */

	@Column(name = "OBJ_DB", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objDb;
	/**
	 */

	@Column(name = "OBJ_ACC", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objAcc;
	/**
	 */

	@Column(name = "OBJ_CVTERM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal objCvtermId;
	/**
	 */

	@Column(name = "OBJ_CVTERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objCvterm;
	/**
	 */

	@Column(name = "PATHDISTANCE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pathdistance;
	/**
	 */

	@Column(name = "QUAN_VALUE", scale = 10, precision = 15)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal quanValue;
	/**
	 */

	@Column(name = "QUAL_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qualValue;

	@Column(name = "DATASET")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String dataset;

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	/**
	 */
	public void setSubjCvtermId(BigDecimal subjCvtermId) {
		this.subjCvtermId = subjCvtermId;
	}

	/**
	 */
	public BigDecimal getSubjCvtermId() {
		return this.subjCvtermId;
	}

	/**
	 */
	public void setSubjCvid(BigDecimal subjCvid) {
		this.subjCvid = subjCvid;
	}

	/**
	 */
	public BigDecimal getSubjCvid() {
		return this.subjCvid;
	}

	/**
	 */
	public void setSubjCvname(String subjCvname) {
		this.subjCvname = subjCvname;
	}

	/**
	 */
	public String getSubjCvname() {
		return this.subjCvname;
	}

	/**
	 */
	public void setSubjDb(String subjDb) {
		this.subjDb = subjDb;
	}

	/**
	 */
	public String getSubjDb() {
		return this.subjDb;
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
	public void setSubjDefinition(String subjDefinition) {
		this.subjDefinition = subjDefinition;
	}

	/**
	 */
	public String getSubjDefinition() {
		return this.subjDefinition;
	}

	/**
	 */
	public void setObjCvid(BigDecimal objCvid) {
		this.objCvid = objCvid;
	}

	/**
	 */
	public BigDecimal getObjCvid() {
		return this.objCvid;
	}

	/**
	 */
	public void setObjCvname(String objCvname) {
		this.objCvname = objCvname;
	}

	/**
	 */
	public String getObjCvname() {
		return this.objCvname;
	}

	/**
	 */
	public void setObjDb(String objDb) {
		this.objDb = objDb;
	}

	/**
	 */
	public String getObjDb() {
		return this.objDb;
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
	public void setObjCvtermId(BigDecimal objCvtermId) {
		this.objCvtermId = objCvtermId;
	}

	/**
	 */
	public BigDecimal getObjCvtermId() {
		return this.objCvtermId;
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
	public void setPathdistance(BigDecimal pathdistance) {
		this.pathdistance = pathdistance;
	}

	/**
	 */
	public BigDecimal getPathdistance() {
		return this.pathdistance;
	}

	/**
	 */
	public void setQuanValue(BigDecimal quanValue) {
		this.quanValue = quanValue;
	}

	/**
	 */
	public BigDecimal getQuanValue() {
		return this.quanValue;
	}

	/**
	 */
	public void setQualValue(String qualValue) {
		this.qualValue = qualValue;
	}

	/**
	 */
	public String getQualValue() {
		return this.qualValue;
	}

	/**
	 */
	public VCvPhenotypeByPtocoPath() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VCvPhenotypeByPtocoPath that) {
		setSubjCvtermId(that.getSubjCvtermId());
		setSubjCvid(that.getSubjCvid());
		setSubjCvname(that.getSubjCvname());
		setSubjDb(that.getSubjDb());
		setSubjAcc(that.getSubjAcc());
		setSubjCvterm(that.getSubjCvterm());
		setSubjDefinition(that.getSubjDefinition());
		setObjCvid(that.getObjCvid());
		setObjCvname(that.getObjCvname());
		setObjDb(that.getObjDb());
		setObjAcc(that.getObjAcc());
		setObjCvtermId(that.getObjCvtermId());
		setObjCvterm(that.getObjCvterm());
		setPathdistance(that.getPathdistance());
		setQuanValue(that.getQuanValue());
		setQualValue(that.getQualValue());
		setDataset(that.getDataset());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("subjCvtermId=[").append(subjCvtermId).append("] ");
		buffer.append("subjCvid=[").append(subjCvid).append("] ");
		buffer.append("subjCvname=[").append(subjCvname).append("] ");
		buffer.append("subjDb=[").append(subjDb).append("] ");
		buffer.append("subjAcc=[").append(subjAcc).append("] ");
		buffer.append("subjCvterm=[").append(subjCvterm).append("] ");
		buffer.append("subjDefinition=[").append(subjDefinition).append("] ");
		buffer.append("objCvid=[").append(objCvid).append("] ");
		buffer.append("objCvname=[").append(objCvname).append("] ");
		buffer.append("objDb=[").append(objDb).append("] ");
		buffer.append("objAcc=[").append(objAcc).append("] ");
		buffer.append("objCvtermId=[").append(objCvtermId).append("] ");
		buffer.append("objCvterm=[").append(objCvterm).append("] ");
		buffer.append("pathdistance=[").append(pathdistance).append("] ");
		buffer.append("quanValue=[").append(quanValue).append("] ");
		buffer.append("qualValue=[").append(qualValue).append("] ");
		buffer.append("dataset=[").append(dataset).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((subjCvtermId == null) ? 0 : subjCvtermId.hashCode()));
		result = (int) (prime * result + ((dataset == null) ? 0 : dataset.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VCvPhenotypeByPtocoPath))
			return false;
		VCvPhenotypeByPtocoPath equalCheck = (VCvPhenotypeByPtocoPath) obj;
		if ((subjCvtermId == null && equalCheck.subjCvtermId != null)
				|| (subjCvtermId != null && equalCheck.subjCvtermId == null))
			return false;
		if (subjCvtermId != null && !subjCvtermId.equals(equalCheck.subjCvtermId))
			return false;
		if ((dataset == null && equalCheck.dataset != null) || (dataset != null && equalCheck.dataset == null))
			return false;
		if (dataset != null && !dataset.equals(equalCheck.dataset))
			return false;
		return true;
	}

	@Override
	public BigDecimal getCvTermId() {

		return this.subjCvid;
	}

	@Override
	public String getName() {

		return this.subjCvterm;
	}

	@Override
	public String getDefinition() {

		return this.subjDefinition;
	}

	@Override
	public String getAccession() {

		return this.subjDb + ":" + this.subjAcc;
	}

}
