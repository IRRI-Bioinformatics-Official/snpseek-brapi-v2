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

import org.irri.iric.ds.chado.domain.CvTermUniqueValues;


/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVCvPhenotypeByPtocos", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjAcc", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objAcc = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjAccContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objAcc like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvid", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objCvid = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvname", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objCvname = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvnameContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objCvname like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvterm", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objCvterm = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvtermContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objCvterm like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvtermId", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objCvtermId = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjDb", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objDb = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjDbContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objDb like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByPathdistance", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.pathdistance = ?1"),

		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvtermPosMaxdist", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objCvname=?1 and  myVCvPhenotypeByPtoco.objCvterm = ?2 and ((myVCvPhenotypeByPtoco.pathdistance>0 and myVCvPhenotypeByPtoco.pathdistance<=?3) or myVCvPhenotypeByPtoco.pathdistance is null) order by myVCvPhenotypeByPtoco.pathdistance, myVCvPhenotypeByPtoco.subjCvterm"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvtermNegMaxdist", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where  myVCvPhenotypeByPtoco.objCvname=?1 and  myVCvPhenotypeByPtoco.objCvterm = ?2 and myVCvPhenotypeByPtoco.pathdistance<0 and myVCvPhenotypeByPtoco.pathdistance>=?3 order by myVCvPhenotypeByPtoco.pathdistance desc, myVCvPhenotypeByPtoco.subjCvterm"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvtermPosDist", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.objCvname=?1 and  myVCvPhenotypeByPtoco.objCvterm = ?2 and ( myVCvPhenotypeByPtoco.pathdistance>0  or  myVCvPhenotypeByPtoco.pathdistance is null) order by myVCvPhenotypeByPtoco.pathdistance, myVCvPhenotypeByPtoco.subjCvterm"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByObjCvtermNegDist", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where  myVCvPhenotypeByPtoco.objCvname=?1 and myVCvPhenotypeByPtoco.objCvterm = ?2 and myVCvPhenotypeByPtoco.pathdistance<0  order by myVCvPhenotypeByPtoco.pathdistance desc, myVCvPhenotypeByPtoco.subjCvterm"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByPrimaryKey", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjCvtermId = ?1"),
		
		
		@NamedQuery(name = "findVCvPhenotypeByPtocoByQualValue", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.qualValue = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByQualValueContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.qualValue like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoByQuanValue", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.quanValue = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjAcc", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjAcc = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjAccContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjAcc like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjCvid", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjCvid = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjCvname", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjCvname = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjCvnameContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjCvname like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjCvterm", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjCvterm = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjCvtermContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjCvterm like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjCvtermId", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjCvtermId = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjDb", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjDb = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjDbContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjDb like ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjDefinition", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjDefinition = ?1"),
		@NamedQuery(name = "findVCvPhenotypeByPtocoBySubjDefinitionContaining", query = "select myVCvPhenotypeByPtoco from VCvPhenotypeByPtoco myVCvPhenotypeByPtoco where myVCvPhenotypeByPtoco.subjDefinition like ?1") })
@Table( name = "V_CV_PHENOTYPE_BY_PTOCO_PATH")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VCvPhenotypeByPtoco")
public class VCvPhenotypeByPtoco implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "OBJ_CVTERM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal objCvtermId;
	/**
	 */

	@Column(name = "SUBJ_CVID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal subjCvid;
	/**
	 */

	@Column(name = "SUBJ_CVNAME", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjCvname;
	/**
	 */

	@Column(name = "SUBJ_DB", length = 1020, nullable = false)
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

	@Column(name = "SUBJ_CVTERM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal subjCvtermId;
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

	@Column(name = "OBJ_CVID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal objCvid;
	/**
	 */

	@Column(name = "OBJ_CVNAME", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objCvname;
	/**
	 */

	@Column(name = "OBJ_DB", length = 1020, nullable = false)
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
	public VCvPhenotypeByPtoco() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VCvPhenotypeByPtoco that) {
		setObjCvtermId(that.getObjCvtermId());
		setSubjCvid(that.getSubjCvid());
		setSubjCvname(that.getSubjCvname());
		setSubjDb(that.getSubjDb());
		setSubjAcc(that.getSubjAcc());
		setSubjCvtermId(that.getSubjCvtermId());
		setSubjCvterm(that.getSubjCvterm());
		setSubjDefinition(that.getSubjDefinition());
		setObjCvid(that.getObjCvid());
		setObjCvname(that.getObjCvname());
		setObjDb(that.getObjDb());
		setObjAcc(that.getObjAcc());
		setObjCvterm(that.getObjCvterm());
		setPathdistance(that.getPathdistance());
		setQuanValue(that.getQuanValue());
		setQualValue(that.getQualValue());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("objCvtermId=[").append(objCvtermId).append("] ");
		buffer.append("subjCvid=[").append(subjCvid).append("] ");
		buffer.append("subjCvname=[").append(subjCvname).append("] ");
		buffer.append("subjDb=[").append(subjDb).append("] ");
		buffer.append("subjAcc=[").append(subjAcc).append("] ");
		buffer.append("subjCvtermId=[").append(subjCvtermId).append("] ");
		buffer.append("subjCvterm=[").append(subjCvterm).append("] ");
		buffer.append("subjDefinition=[").append(subjDefinition).append("] ");
		buffer.append("objCvid=[").append(objCvid).append("] ");
		buffer.append("objCvname=[").append(objCvname).append("] ");
		buffer.append("objDb=[").append(objDb).append("] ");
		buffer.append("objAcc=[").append(objAcc).append("] ");
		buffer.append("objCvterm=[").append(objCvterm).append("] ");
		buffer.append("pathdistance=[").append(pathdistance).append("] ");
		buffer.append("quanValue=[").append(quanValue).append("] ");
		buffer.append("qualValue=[").append(qualValue).append("] ");

		return buffer.toString();
	}

//	
//	/**
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = (int) (prime * result + ((objCvtermId == null) ? 0 : objCvtermId.hashCode()));
//		return result;
//	}
//
//	/**
//	 */
//	public boolean equals(Object obj) {
//		if (obj == this)
//			return true;
//		if (!(obj instanceof VCvPhenotypeByPtoco))
//			return false;
//		VCvPhenotypeByPtoco equalCheck = (VCvPhenotypeByPtoco) obj;
//		if ((objCvtermId == null && equalCheck.objCvtermId != null) || (objCvtermId != null && equalCheck.objCvtermId == null))
//			return false;
//		if (objCvtermId != null && !objCvtermId.equals(equalCheck.objCvtermId))
//			return false;
//		return true;
//	}
	
	
	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((subjCvtermId == null) ? 0 : subjCvtermId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VCvPhenotypeByPtoco))
			return false;
		VCvPhenotypeByPtoco equalCheck = (VCvPhenotypeByPtoco) obj;
		if ((subjCvtermId == null && equalCheck.subjCvtermId != null) || (subjCvtermId != null && equalCheck.subjCvtermId == null))
			return false;
		if (subjCvtermId != null && !subjCvtermId.equals(equalCheck.subjCvtermId))
			return false;
		return true;
	}

	@Override
	public String getValue() {
		return this.subjDefinition;
	}

	
	
}
