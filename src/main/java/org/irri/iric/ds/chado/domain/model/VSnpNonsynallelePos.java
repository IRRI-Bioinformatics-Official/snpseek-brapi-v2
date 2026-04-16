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

import org.irri.iric.ds.chado.domain.SnpsNonsynAllele;
import org.irri.iric.ds.chado.domain.object.VSnpNonsynallelePosPK;



/**
 */
@IdClass(VSnpNonsynallelePosPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpNonsynallelePoss", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos"),
		@NamedQuery(name = "findVSnpNonsynallelePosByAlleleNonsyn", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.alleleNonsyn = ?1"),
		@NamedQuery(name = "findVSnpNonsynallelePosByAlleleNonsynContaining", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.alleleNonsyn like ?1"),
		@NamedQuery(name = "findVSnpNonsynallelePosByChr", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.chr = ?1"),
		@NamedQuery(name = "findVSnpNonsynallelePosByPosition", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.position = ?1"),
		@NamedQuery(name = "findVSnpNonsynallelePosByPrimaryKey", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.snpFeatureId = ?1 and myVSnpNonsynallelePos.typeId = ?2"),
		@NamedQuery(name = "findVSnpNonsynallelePosBySnpFeatureId", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.snpFeatureId = ?1"),

		@NamedQuery(name = "findVSnpNonsynallelePosByPositionBetween", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.chr = ?1 and myVSnpNonsynallelePos.position between ?2 and ?3 and myVSnpNonsynallelePos.typeId=?4 order by  myVSnpNonsynallelePos.chr , myVSnpNonsynallelePos.position"),
		@NamedQuery(name = "findVSnpNonsynallelePosByPositionBetweenSnpset", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.chr = ?1 and myVSnpNonsynallelePos.position between ?2 and ?3 and myVSnpNonsynallelePos.typeId=?4 and  myVSnpNonsynallelePos.variantset in (?5) order by  myVSnpNonsynallelePos.chr , myVSnpNonsynallelePos.position"),

		@NamedQuery(name = "findVSnpNonsynallelePosByPositionIn", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.chr = ?1 and  myVSnpNonsynallelePos.position in(?2)  and myVSnpNonsynallelePos.typeId=?3 order by  myVSnpNonsynallelePos.chr , myVSnpNonsynallelePos.position"),
		@NamedQuery(name = "findVSnpNonsynallelePosBySnpFeatureIdBetween", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.snpFeatureId between ?1 and ?2 and myVSnpNonsynallelePos.typeId=?3 order by  myVSnpNonsynallelePos.chr , myVSnpNonsynallelePos.position"),
		@NamedQuery(name = "findVSnpNonsynallelePosBySnpFeatureIdBetweenSnpset", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.snpFeatureId between ?1 and ?2 and myVSnpNonsynallelePos.typeId=?3 and myVSnpNonsynallelePos.variantset in (?4) order by  myVSnpNonsynallelePos.chr , myVSnpNonsynallelePos.position"),

		@NamedQuery(name = "findVSnpNonsynallelePosBySnpFeatureIdIn", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.snpFeatureId in (?1) and myVSnpNonsynallelePos.typeId=?2 order by  myVSnpNonsynallelePos.chr , myVSnpNonsynallelePos.position"),

		@NamedQuery(name = "findVSnpNonsynallelePosByTypeId", query = "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where myVSnpNonsynallelePos.typeId = ?1") })
@Table(name = "V_SNP_NONSYNALLELE_POS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VSnpNonsynallelePos")
public class VSnpNonsynallelePos implements Serializable, SnpsNonsynAllele, Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "TYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	BigDecimal typeId;

	@Column(name = "VARIANTSET", nullable = false)
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String variantset;

	/**
	 */

	@Column(name = "CHROMOSOME")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal chr;
	/**
	 */

	@Column(name = "POSITION")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal position;
	/**
	 */

	@Column(name = "ALLELE_NONSYN")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String alleleNonsyn;

	/**
	 */
	public void setSnpFeatureId(BigDecimal snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
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
	public void setChr(BigDecimal chr) {
		this.chr = chr;
	}

	/**
	 */
	public BigDecimal getChr() {
		return this.chr;
	}

	/**
	 */
	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	/**
	 */
	public BigDecimal getPosition() {
		return this.position;
	}

	/**
	 */
	public void setAlleleNonsyn(String alleleNonsyn) {
		this.alleleNonsyn = alleleNonsyn;
	}

	/**
	 */
	public String getAlleleNonsyn() {
		return this.alleleNonsyn;
	}

	/**
	 */
	public VSnpNonsynallelePos() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpNonsynallelePos that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setTypeId(that.getTypeId());
		setChr(that.getChr());
		setPosition(that.getPosition());
		setAlleleNonsyn(that.getAlleleNonsyn());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append(getClass() + ": ");
		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("position=[").append(position).append("] ");
		buffer.append("alleleNonsyn=[").append(alleleNonsyn).append("] ");

		return buffer.toString();
	}

	// /**
	// */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = (int) (prime * result + ((snpFeatureId == null) ? 0 :
	// snpFeatureId.hashCode()));
	// result = (int) (prime * result + ((typeId == null) ? 0 : typeId.hashCode()));
	// return result;
	// }
	//
	// /**
	// */
	// public boolean equals(Object obj) {
	// if (obj == this)
	// return true;
	// if (!(obj instanceof VSnpNonsynallelePos))
	// return false;
	// VSnpNonsynallelePos equalCheck = (VSnpNonsynallelePos) obj;
	// if ((snpFeatureId == null && equalCheck.snpFeatureId != null) ||
	// (snpFeatureId != null && equalCheck.snpFeatureId == null))
	// return false;
	// if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
	// return false;
	// if ((typeId == null && equalCheck.typeId != null) || (typeId != null &&
	// equalCheck.typeId == null))
	// return false;
	// if (typeId != null && !typeId.equals(equalCheck.typeId))
	// return false;
	// return true;
	// }
	//
	// @Override
	// public int compareTo(Object o) {
	//
	// return 0;
	// }
	//
	// @Override
	// public BigDecimal getSnp() {
	//
	// return null;
	// }
	//
	// @Override
	// public char getAllele() {
	//
	// return 0;
	// }
	//
	//
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = (int) (prime * result + ((snpFeatureId == null) ? 0 :
		// snpFeatureId.hashCode()));
		// result = (int) (prime * result + ((typeId == null) ? 0 : typeId.hashCode()));
		result = (int) (prime * result + ((chr == null) ? 0 : chr.hashCode()));
		result = (int) (prime * result + ((position == null) ? 0 : position.hashCode()));
		result = (int) (prime * result + ((alleleNonsyn == null) ? 0 : alleleNonsyn.hashCode()));
		return result;
	}

	@Override
	public BigDecimal getSnpFeatureId() {

		return this.snpFeatureId;
	}

	@Override
	public boolean equals(Object obj) {

		return compareTo(obj) == 0;
	}

	@Override
	public char getAllele() {

		return this.getAlleleNonsyn().charAt(0);
	}

	@Override
	public int compareTo(Object o) {

		// return this.snpFeatureId.compareTo( ((VSnpNonsynAllele)o).snpFeatureId );

		/*
		 * int ret= this.snpFeatureId.compareTo( ((VSnpNonsynallelePos)o).snpFeatureId
		 * ); if(ret==0) this.alleleNonsyn.compareTo(
		 * ((VSnpNonsynallelePos)o).alleleNonsyn ); if(ret==0) this.typeId.compareTo(
		 * ((VSnpNonsynallelePos)o).typeId ); if(ret==0)
		 * AppContext.debug("VSnpNonsynallelePos.compareTo this=" + toString() + "; o="
		 * + o);
		 * 
		 */

		int ret = this.chr.compareTo(((VSnpNonsynallelePos) o).chr);
		if (ret == 0)
			ret = this.position.compareTo(((VSnpNonsynallelePos) o).position);
		if (ret == 0)
			ret = this.alleleNonsyn.compareTo(((VSnpNonsynallelePos) o).alleleNonsyn);

		return ret;
	}

}
