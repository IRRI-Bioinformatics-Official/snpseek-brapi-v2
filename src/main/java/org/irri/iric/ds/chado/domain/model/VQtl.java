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
@NamedQueries({ @NamedQuery(name = "findAllVQtls", query = "select myVQtl from VQtl myVQtl"),
		@NamedQuery(name = "findVQtlByChromosome", query = "select myVQtl from VQtl myVQtl where myVQtl.chromosome = ?1"),
		@NamedQuery(name = "findVQtlByDbId", query = "select myVQtl from VQtl myVQtl where myVQtl.dbId = ?1"),
		@NamedQuery(name = "findVQtlByEnd", query = "select myVQtl from VQtl myVQtl where myVQtl.end = ?1"),
		@NamedQuery(name = "findVQtlByName", query = "select myVQtl from VQtl myVQtl where myVQtl.name = ?1"),
		@NamedQuery(name = "findVQtlByNameContaining", query = "select myVQtl from VQtl myVQtl where myVQtl.name like ?1"),
		@NamedQuery(name = "findVQtlByNotes", query = "select myVQtl from VQtl myVQtl where myVQtl.notes = ?1"),
		@NamedQuery(name = "findVQtlByNotesContaining", query = "select myVQtl from VQtl myVQtl where myVQtl.notes like ?1"),
		@NamedQuery(name = "findVQtlByPrimaryKey", query = "select myVQtl from VQtl myVQtl where myVQtl.qtlId = ?1"),
		@NamedQuery(name = "findVQtlByQtlId", query = "select myVQtl from VQtl myVQtl where myVQtl.qtlId = ?1"),
		@NamedQuery(name = "findVQtlByStart", query = "select myVQtl from VQtl myVQtl where myVQtl.start = ?1"),
		@NamedQuery(name = "findVQtlByTraitName", query = "select myVQtl from VQtl myVQtl where myVQtl.traitName = ?1"),
		@NamedQuery(name = "findVQtlByTraitNameContaining", query = "select myVQtl from VQtl myVQtl where myVQtl.traitName like ?1") })
@Table(name = "V_QTL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VQtl")
public class VQtl implements Serializable, Locus {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "QTL_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal qtlId;
	/**
	 */

	@Column(name = "NAME", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "CHROMOSOME", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chromosome;
	/**
	 */

	@Column(name = "QUERYPOS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal queryPos;
	/**
	 */

	@Column(name = "STARTPOS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal start;
	/**
	 */

	@Column(name = "ENDPOS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal end;
	/**
	 */

	@Column(name = "TRAIT_NAME", length = 254, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String traitName;
	/**
	 */

	@Column(name = "NOTES", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;
	/**
	 */

	@Column(name = "DB_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal dbId;

	/**
	 */
	public void setQtlId(BigDecimal qtlId) {
		this.qtlId = qtlId;
	}

	/**
	 */
	public BigDecimal getQtlId() {
		return this.qtlId;
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
	public void setChromosome(Integer chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 */
	public Integer getChromosome() {
		return this.chromosome;
	}

	/**
	 */
	public void setStart(BigDecimal start) {
		this.start = start;
	}

	/**
	 */
	public BigDecimal getStart() {
		return this.start;
	}

	/**
	 */
	public void setEnd(BigDecimal end) {
		this.end = end;
	}

	/**
	 */
	public BigDecimal getEnd() {
		return this.end;
	}

	/**
	 */
	public void setTraitName(String traitName) {
		this.traitName = traitName;
	}

	/**
	 */
	public String getTraitName() {
		return this.traitName;
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
	public void setDbId(BigDecimal dbId) {
		this.dbId = dbId;
	}

	/**
	 */
	public BigDecimal getDbId() {
		return this.dbId;
	}

	/**
	 */
	public VQtl() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VQtl that) {
		setQtlId(that.getQtlId());
		setName(that.getName());
		setChromosome(that.getChromosome());
		setStart(that.getStart());
		setEnd(that.getEnd());
		setTraitName(that.getTraitName());
		setNotes(that.getNotes());
		setDbId(that.getDbId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("qtlId=[").append(qtlId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("chromosome=[").append(chromosome).append("] ");
		buffer.append("start=[").append(start).append("] ");
		buffer.append("end=[").append(end).append("] ");
		buffer.append("traitName=[").append(traitName).append("] ");
		buffer.append("notes=[").append(notes).append("] ");
		buffer.append("dbId=[").append(dbId).append("] ");

		return buffer.toString();
	}

	// /**
	// */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = (int) (prime * result + ((qtlId == null) ? 0 : qtlId.hashCode()));
	// return result;
	// }
	//
	// /**
	// */
	// public boolean equals(Object obj) {
	// if (obj == this)
	// return true;
	// if (!(obj instanceof VQtl))
	// return false;
	// VQtl equalCheck = (VQtl) obj;
	// if ((qtlId == null && equalCheck.qtlId != null) || (qtlId != null &&
	// equalCheck.qtlId == null))
	// return false;
	// if (qtlId != null && !qtlId.equals(equalCheck.qtlId))
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
		// result = (int) (prime * result + ((organismId == null) ? 0 :
		// organismId.hashCode()));
		result = (int) (prime * result + ((getContig() == null) ? 0 : getContig().hashCode()));
		result = (int) (prime * result + ((getFmin() == null) ? 0 : getFmin().hashCode()));
		result = (int) (prime * result + ((getFmax() == null) ? 0 : getFmax().hashCode()));
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

		return this.name;
	}

	@Override
	public Long getChr() {

		return Long.valueOf(this.chromosome);
	}

	@Override
	public Integer getFmin() {

		return this.start.intValue();
	}

	@Override
	public Integer getFmax() {

		return this.end.intValue();
	}

	@Override
	public Integer getStrand() {
		return null;
	}

	@Override
	public String getContig() {

		if (chromosome > 9)
			return "chr" + chromosome;
		else
			return "chr0" + chromosome;
	}

	@Override
	public String getDescription() {

		if (this.dbId.intValue() == 1)
			return "qtaro-qtl:" + this.traitName + " (" + this.notes + ")";
		else if (this.dbId.intValue() == 2)
			return "gramene-qtl:" + this.traitName;
		return null;
	}

	@Override
	public BigDecimal getFeatureId() {
		return null;
	}

	@Override
	public String getFeatureType() {
		return null;
	}

}
