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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpGenotypeRDBMSs", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS"),
		@NamedQuery(name = "findVSnpGenotypeRDBMSByAllele1", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS where myVSnpGenotypeRDBMS.allele1 = ?1"),
		@NamedQuery(name = "findVSnpGenotypeRDBMSByAllele1Containing", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS where myVSnpGenotypeRDBMS.allele1 like ?1"),
		@NamedQuery(name = "findVSnpGenotypeRDBMSByAllele2", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS where myVSnpGenotypeRDBMS.allele2 = ?1"),
		@NamedQuery(name = "findVSnpGenotypeRDBMSByAllele2Containing", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS where myVSnpGenotypeRDBMS.allele2 like ?1"),
		@NamedQuery(name = "findVSnpGenotypeRDBMSByRDBMSStockId", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS where myVSnpGenotypeRDBMS.RDBMSStockId = ?1"),
		@NamedQuery(name = "findVSnpGenotypeRDBMSByMismatch", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS where myVSnpGenotypeRDBMS.mismatch = ?1"),
		@NamedQuery(name = "findVSnpGenotypeRDBMSByPrimaryKey", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS where myVSnpGenotypeRDBMS.RDBMSStockId = ?1"),
		@NamedQuery(name = "findVSnpGenotypeRDBMSByRefcall", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS where myVSnpGenotypeRDBMS.refcall = ?1"),
		@NamedQuery(name = "findVSnpGenotypeRDBMSByRefcallContaining", query = "select myVSnpGenotypeRDBMS from VSnpGenotypeRDBMS myVSnpGenotypeRDBMS where myVSnpGenotypeRDBMS.refcall like ?1") })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VSnpGenotypeRDBMS")
public class VSnpGenotypeRDBMS implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "STOCK_SAMPLE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal RDBMSStockId;
	/**
	 */

	@Column(name = "MISMATCH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal mismatch;
	/**
	 */

	// @Column(name = "REFCALL", length = 1000)
	@Column(name = "REFCALL")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String refcall;
	/**
	 */

	@Column(name = "ALLELE1")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String allele1;
	/**
	 */

	@Column(name = "ALLELE2")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String allele2;

	/**
	 */
	public void setRDBMSStockId(BigDecimal RDBMSStockId) {
		this.RDBMSStockId = RDBMSStockId;
	}

	/**
	 */
	public BigDecimal getRDBMSStockId() {
		return this.RDBMSStockId;
	}

	/**
	 */
	public void setMismatch(BigDecimal mismatch) {
		this.mismatch = mismatch;
	}

	/**
	 */
	public BigDecimal getMismatch() {
		return this.mismatch;
	}

	/**
	 */
	public void setRefcall(String refcall) {
		this.refcall = refcall;
	}

	/**
	 */
	public String getRefcall() {
		return this.refcall;
	}

	/**
	 */
	public void setAllele1(String allele1) {
		this.allele1 = allele1;
	}

	/**
	 */
	public String getAllele1() {
		return this.allele1;
	}

	/**
	 */
	public void setAllele2(String allele2) {
		this.allele2 = allele2;
	}

	/**
	 */
	public String getAllele2() {
		return this.allele2;
	}

	/**
	 */
	public VSnpGenotypeRDBMS() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpGenotypeRDBMS that) {
		setRDBMSStockId(that.getRDBMSStockId());
		setMismatch(that.getMismatch());
		setRefcall(that.getRefcall());
		setAllele1(that.getAllele1());
		setAllele2(that.getAllele2());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("RDBMSStockId=[").append(RDBMSStockId).append("] ");
		buffer.append("mismatch=[").append(mismatch).append("] ");
		buffer.append("refcall=[").append(refcall).append("] ");
		buffer.append("allele1=[").append(allele1).append("] ");
		buffer.append("allele2=[").append(allele2).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((RDBMSStockId == null) ? 0 : RDBMSStockId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpGenotypeRDBMS))
			return false;
		VSnpGenotypeRDBMS equalCheck = (VSnpGenotypeRDBMS) obj;
		if ((RDBMSStockId == null && equalCheck.RDBMSStockId != null)
				|| (RDBMSStockId != null && equalCheck.RDBMSStockId == null))
			return false;
		if (RDBMSStockId != null && !RDBMSStockId.equals(equalCheck.RDBMSStockId))
			return false;
		return true;
	}

}
