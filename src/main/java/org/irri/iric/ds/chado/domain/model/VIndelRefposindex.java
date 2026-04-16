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

import org.irri.iric.ds.chado.domain.IndelsAllvarsPos;
import org.irri.iric.ds.chado.domain.Position;



/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllMvIndelRefposindexs", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex"),
		@NamedQuery(name = "findMvIndelRefposindexByAlleleIndex", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.alleleIndex = ?1  order by myMvIndelRefposindex.chromosome, myMvIndelRefposindex.position"),
		@NamedQuery(name = "findMvIndelRefposindexByAltcall", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.altcall = ?1"),
		@NamedQuery(name = "findMvIndelRefposindexByAltcallContaining", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.altcall like ?1"),
		@NamedQuery(name = "findMvIndelRefposindexByChromosome", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.chromosome = ?1"),
		@NamedQuery(name = "findMvIndelRefposindexByMaxDeleteLen", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.maxDeleteLen = ?1"),
		@NamedQuery(name = "findMvIndelRefposindexByMaxInsertLen", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.maxInsertLen = ?1"),
		@NamedQuery(name = "findMvIndelRefposindexByPosition", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.position = ?1"),
		@NamedQuery(name = "findMvIndelRefposindexByIndelFeatureId", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.indelFeatureId = ?1  order by myMvIndelRefposindex.chromosome, myMvIndelRefposindex.position"),

		@NamedQuery(name = "findMvIndelRefposindexByChromosomePosBetween", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.chromosome = ?1 and myMvIndelRefposindex.position between ?2 and ?3 order by myMvIndelRefposindex.chromosome, myMvIndelRefposindex.position"),
		@NamedQuery(name = "findMvIndelRefposindexByChromosomePosIn", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.chromosome = ?1 and myMvIndelRefposindex.position in (?2)  order by myMvIndelRefposindex.chromosome, myMvIndelRefposindex.position"),
		@NamedQuery(name = "findMvIndelRefposindexByIndelFeatureIdIn", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.indelFeatureId in (?1)  order by myMvIndelRefposindex.chromosome, myMvIndelRefposindex.position"),

		@NamedQuery(name = "findMvIndelRefposindexByChromosomePosBetweenVS", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.chromosome = ?1 and myMvIndelRefposindex.position between ?2 and ?3 and  myMvIndelRefposindex.variantset in (?4) order by myMvIndelRefposindex.chromosome, myMvIndelRefposindex.position"),
		@NamedQuery(name = "findMvIndelRefposindexByChromosomePosInVS", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.chromosome = ?1 and myMvIndelRefposindex.position in (?2) and  myMvIndelRefposindex.variantset in (?3) order by myMvIndelRefposindex.chromosome, myMvIndelRefposindex.position"),

		@NamedQuery(name = "findMvIndelRefposindexByPrimaryKey", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.indelFeatureId = ?1"),
		@NamedQuery(name = "findMvIndelRefposindexByRefcall", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.refcall = ?1"),
		@NamedQuery(name = "findMvIndelRefposindexByRefcallContaining", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.refcall like ?1"),
		@NamedQuery(name = "findMvIndelRefposindexByTypeId", query = "select myMvIndelRefposindex from VIndelRefposindex myMvIndelRefposindex where myMvIndelRefposindex.typeId = ?1") })
@Table(name = "V_INDEL_REFPOSINDEX")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VIndelRefposindex")
public class VIndelRefposindex implements Serializable, IndelsAllvarsPos {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "INDEL_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal indelFeatureId;
	/**
	 */

	@Column(name = "CHROMOSOME", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal chromosome;
	/**
	 */

	@Column(name = "POSITION", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal position;
	/**
	 */

	@Column(name = "REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String refcall;
	/**
	 */

	@Column(name = "ALTCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String altcall;
	/**
	 */

	@Column(name = "TYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;
	/**
	 */

	@Column(name = "VARIANTSET", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String variantset;
	/**
	 */

	@Column(name = "ALLELE_INDEX", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal alleleIndex;
	/**
	 */

	@Column(name = "MAX_INSERT_LEN")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer maxInsertLen;
	/**
	 */

	@Column(name = "MAX_DELETE_LEN")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer maxDeleteLen;

	/**
	 */
	public void setIndelFeatureId(BigDecimal indelFeatureId) {
		this.indelFeatureId = indelFeatureId;
	}

	/**
	 */
	public BigDecimal getIndelFeatureId() {
		return this.indelFeatureId;
	}

	/**
	 */
	public void setChromosome(BigDecimal chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 */
	public BigDecimal getChromosome() {
		return this.chromosome;
	}

	/**
	 */
	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	/**
	 */
	public String getRefcall() {
		return this.refcall;
	}

	/**
	 */
	public void setAltcall(String altcall) {
		this.altcall = altcall;
	}

	/**
	 */
	public String getAltcall() {
		return this.altcall;
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
	public void setAlleleIndex(BigDecimal alleleIndex) {
		this.alleleIndex = alleleIndex;
	}

	/**
	 */
	public BigDecimal getAlleleIndex() {
		return this.alleleIndex;
	}
	
	

	public String getVariantset() {
		return variantset;
	}

	public void setVariantset(String variantset) {
		this.variantset = variantset;
	}

	public void setRefcall(String refcall) {
		this.refcall = refcall;
	}

	/**
	 */
	public void setMaxInsertLen(Integer maxInsertLen) {
		this.maxInsertLen = maxInsertLen;
	}

	/**
	 */
	public Integer getMaxInsertLen() {
		return this.maxInsertLen;
	}

	/**
	 */
	public void setMaxDeleteLen(Integer maxDeleteLen) {
		this.maxDeleteLen = maxDeleteLen;
	}

	/**
	 */
	public Integer getMaxDeleteLen() {
		return this.maxDeleteLen;
	}

	/**
	 */
	public VIndelRefposindex() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIndelRefposindex that) {
		setIndelFeatureId(that.getIndelFeatureId());
		setChromosome(that.getChromosome());
		setPosition(that.getPosition());

		setAltcall(that.getAltcall());
		setTypeId(that.getTypeId());
		setAlleleIndex(that.getAlleleIndex());
		setMaxInsertLen(that.getMaxInsertLen());
		setMaxDeleteLen(that.getMaxDeleteLen());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append(getClass() + ": ");
		buffer.append("indelFeatureId=[").append(indelFeatureId).append("] ");
		buffer.append("chromosome=[").append(chromosome).append("] ");
		buffer.append("position=[").append(position).append("] ");
		buffer.append("refcall=[").append(refcall).append("] ");
		buffer.append("altcall=[").append(altcall).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("alleleIndex=[").append(alleleIndex).append("] ");
		buffer.append("maxInsertLen=[").append(maxInsertLen).append("] ");
		buffer.append("maxDeleteLen=[").append(maxDeleteLen).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		/*
		 * final int prime = 31; int result = 1; result = (int) (prime * result +
		 * ((indelFeatureId == null) ? 0 : indelFeatureId.hashCode())); return result;
		 */

		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((getContig() == null) ? 0 : getContig().hashCode()));
		result = (int) (prime * result + ((position == null) ? 0 : position.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		/*
		 * if (obj == this) return true; if (!(obj instanceof VIndelRefposindex)) return
		 * false; VIndelRefposindex equalCheck = (VIndelRefposindex) obj; if
		 * ((indelFeatureId == null && equalCheck.indelFeatureId != null) ||
		 * (indelFeatureId != null && equalCheck.indelFeatureId == null)) return false;
		 * if (indelFeatureId != null &&
		 * !indelFeatureId.equals(equalCheck.indelFeatureId)) return false; return true;
		 */
		return compareTo(obj) == 0;
	}

	@Override
	public BigDecimal getPosition() {

		return this.position;
	}

	@Override
	public String getRefnuc() {

		return this.refcall;
	}

	@Override
	public void setRefnuc(String refnuc) {

		this.refcall = refnuc;

	}

	@Override
	public String getContig() {

		if (chromosome.intValue() > 9)
			return "chr" + chromosome;
		else
			return "chr0" + chromosome;
	}

	@Override
	public BigDecimal getSnpFeatureId() {

		return this.indelFeatureId;
	}

	@Override
	public Integer getMaxDellength() {

		return this.maxDeleteLen;
	}

	@Override
	public String getInsString() {

		return this.altcall;
	}

	@Override
	public Integer getMaxInsLength() {

		return this.maxInsertLen;
	}

	// @Override
	// public Integer getDellength() {
	//
	// return null;
	// }

	@Override
	public int compareTo(Object o) {

		// IndelsAllvarsPos obj=(IndelsAllvarsPos)o;
		Position obj = (Position) o;
		int ret = getContig().compareTo(obj.getContig());
		if (ret == 0)
			ret = getPosition().compareTo(obj.getPosition());

		if (o instanceof VIndelRefposindex) {
			if (ret == 0)
				ret = indelFeatureId.compareTo(((VIndelRefposindex) o).getIndelFeatureId());
		}

		return ret;
	}

	@Override
	public Long getChr() {

		return this.chromosome.longValue();
	}

	@Override
	public void setAltnuc(String altnuc) {

		this.altcall = altnuc;
	}

	@Override
	public String getAltnuc() {

		return altcall;
	}

}
