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

import org.irri.iric.ds.chado.domain.CvTermUniqueValues;
import org.irri.iric.ds.chado.domain.object.VGoCvtermpathPK;

/**
 */
@IdClass(VGoCvtermpathPK.class)
@Entity(name = "VGoCvtermpath")
@NamedQueries({
		@NamedQuery(name = "findAllVGoCvtermpaths", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath"),
		@NamedQuery(name = "findVGoCvtermpathByCvName", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvName = ?1"),
		@NamedQuery(name = "findVGoCvtermpathByCvNameContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvName like ?1"),
		@NamedQuery(name = "findVGoCvtermpathByCvtermId", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvtermId = ?1"),
		@NamedQuery(name = "findVGoCvtermpathByObjAcc", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.objAcc = ?1"),
		@NamedQuery(name = "findVGoCvtermpathByObjAccContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.objAcc like ?1"),

		@NamedQuery(name = "findVGoCvtermpathByObjCvtermPosMaxdist", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvName=?1 and  myVGoCvtermpath.objCvterm = ?2 and ((myVGoCvtermpath.pathdistance>0 and myVGoCvtermpath.pathdistance<=?3) or myVGoCvtermpath.pathdistance is null) order by myVGoCvtermpath.pathdistance, myVGoCvtermpath.subjCvterm"),
		@NamedQuery(name = "findVGoCvtermpathByObjCvtermNegMaxdist", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where  myVGoCvtermpath.cvName=?1 and  myVGoCvtermpath.objCvterm = ?2 and myVGoCvtermpath.pathdistance<0 and myVGoCvtermpath.pathdistance>=?3 order by myVGoCvtermpath.pathdistance desc, myVGoCvtermpath.subjCvterm"),
		@NamedQuery(name = "findVGoCvtermpathByObjCvtermPosDist", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvName=?1 and  myVGoCvtermpath.objCvterm = ?2 and ( myVGoCvtermpath.pathdistance>0  or  myVGoCvtermpath.pathdistance is null) order by myVGoCvtermpath.pathdistance, myVGoCvtermpath.subjCvterm"),
		@NamedQuery(name = "findVGoCvtermpathByObjCvtermNegDist", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where  myVGoCvtermpath.cvName=?1 and myVGoCvtermpath.objCvterm = ?2 and myVGoCvtermpath.pathdistance<0  order by myVGoCvtermpath.pathdistance desc, myVGoCvtermpath.subjCvterm"),

		@NamedQuery(name = "findVGoCvtermpathByObjCvterm", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.objCvterm = ?1"),
		@NamedQuery(name = "findVGoCvtermpathByObjCvtermContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.objCvterm like ?1"),
		@NamedQuery(name = "findVGoCvtermpathByPathdistance", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.pathdistance = ?1"),
		@NamedQuery(name = "findVGoCvtermpathByPrimaryKey", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.subjAcc = ?1 and myVGoCvtermpath.objAcc = ?2"),
		@NamedQuery(name = "findVGoCvtermpathBySubjAcc", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.subjAcc = ?1"),
		@NamedQuery(name = "findVGoCvtermpathBySubjAccContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.subjAcc like ?1"),
		@NamedQuery(name = "findVGoCvtermpathBySubjCvterm", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.subjCvterm = ?1"),
		@NamedQuery(name = "findVGoCvtermpathBySubjCvtermContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.subjCvterm like ?1") })
@Table(name = "V_GO_CVTERMPATH")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VGoCvtermpath")
public class VGoCvtermpath implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "CV_NAME", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvName;
	/**
	 */

	@Column(name = "CVTERM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer cvtermId;
	/**
	 */

	@Column(name = "SUBJ_ACC", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
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

	@Column(name = "OBJ_ACC", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
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
	BigDecimal pathdistance;

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
	public void setCvtermId(Integer cvtermId) {
		this.cvtermId = cvtermId;
	}

	/**
	 */
	public Integer getCvtermId() {
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
	public VGoCvtermpath() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VGoCvtermpath that) {
		setCvName(that.getCvName());
		setCvtermId(that.getCvtermId());
		setSubjAcc(that.getSubjAcc());
		setSubjCvterm(that.getSubjCvterm());
		setObjAcc(that.getObjAcc());
		setObjCvterm(that.getObjCvterm());
		setPathdistance(that.getPathdistance());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("subjAcc=[").append(subjAcc).append("] ");
		buffer.append("subjCvterm=[").append(subjCvterm).append("] ");
		buffer.append("objAcc=[").append(objAcc).append("] ");
		buffer.append("objCvterm=[").append(objCvterm).append("] ");
		buffer.append("pathdistance=[").append(pathdistance).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((subjAcc == null) ? 0 : subjAcc.hashCode()));
		result = (int) (prime * result + ((objAcc == null) ? 0 : objAcc.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VGoCvtermpath))
			return false;
		VGoCvtermpath equalCheck = (VGoCvtermpath) obj;
		if ((subjAcc == null && equalCheck.subjAcc != null) || (subjAcc != null && equalCheck.subjAcc == null))
			return false;
		if (subjAcc != null && !subjAcc.equals(equalCheck.subjAcc))
			return false;
		if ((objAcc == null && equalCheck.objAcc != null) || (objAcc != null && equalCheck.objAcc == null))
			return false;
		if (objAcc != null && !objAcc.equals(equalCheck.objAcc))
			return false;
		return true;
	}

	@Override
	public String getValue() {

		return this.getSubjCvterm();
	}

}
