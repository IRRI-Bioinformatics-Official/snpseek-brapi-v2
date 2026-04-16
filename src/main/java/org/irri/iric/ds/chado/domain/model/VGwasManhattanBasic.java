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

import org.irri.iric.ds.chado.domain.ManhattanPlot;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVGwasManhattanBasics", query = "select myVGwasManhattanBasic from VGwasManhattanBasic myVGwasManhattanBasic"),
		@NamedQuery(name = "findVGwasManhattanBasicByChromosome", query = "select myVGwasManhattanBasic from VGwasManhattanBasic myVGwasManhattanBasic where myVGwasManhattanBasic.chromosome = ?1"),
		@NamedQuery(name = "findVGwasManhattanBasicByGwasMarkerId", query = "select myVGwasManhattanBasic from VGwasManhattanBasic myVGwasManhattanBasic where myVGwasManhattanBasic.gwasMarkerId = ?1"),
		@NamedQuery(name = "findVGwasManhattanBasicByGwasRunId", query = "select myVGwasManhattanBasic from VGwasManhattanBasic myVGwasManhattanBasic where myVGwasManhattanBasic.gwasRunId = ?1  order by  myVGwasManhattanBasic.chromosome, myVGwasManhattanBasic.position, myVGwasManhattanBasic.gwasMarkerId"),
		@NamedQuery(name = "findVGwasManhattanBasicByMinusLogp", query = "select myVGwasManhattanBasic from VGwasManhattanBasic myVGwasManhattanBasic where myVGwasManhattanBasic.minusLogp = ?1"),

		@NamedQuery(name = "findVGwasManhattanBasicByGwasRunIdMinusLogp", query = "select myVGwasManhattanBasic from VGwasManhattanBasic myVGwasManhattanBasic where myVGwasManhattanBasic.gwasRunId = ?1 and myVGwasManhattanBasic.minusLogp >= ?2 order by  myVGwasManhattanBasic.chromosome, myVGwasManhattanBasic.position, myVGwasManhattanBasic.gwasMarkerId"),

		@NamedQuery(name = "findVGwasManhattanBasicByPosition", query = "select myVGwasManhattanBasic from VGwasManhattanBasic myVGwasManhattanBasic where myVGwasManhattanBasic.position = ?1"),
		@NamedQuery(name = "findVGwasManhattanBasicByPrimaryKey", query = "select myVGwasManhattanBasic from VGwasManhattanBasic myVGwasManhattanBasic where myVGwasManhattanBasic.gwasMarkerId = ?1") })
// @Table( name = "V_GWAS_MANHATTAN_BASIC_")
@Table(name = "V_GWAS_MANHATTAN_BASIC")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VGwasManhattanBasic")
public class VGwasManhattanBasic implements Serializable, ManhattanPlot {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "GWAS_MARKER_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal gwasMarkerId;
	/**
	 */

	@Column(name = "GWAS_RUN_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal gwasRunId;
	/**
	 */

	@Column(name = "MINUS_LOGP", scale = 5, precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal minusLogp;
	/**
	 */

	@Column(name = "CHROMOSOME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Long chromosome;
	/**
	 */

	@Column(name = "POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal position;

	/**
	 */

	/**
	 */
	public void setGwasMarkerId(BigDecimal gwasMarkerId) {
		this.gwasMarkerId = gwasMarkerId;
	}

	/**
	 */
	public BigDecimal getGwasMarkerId() {
		return this.gwasMarkerId;
	}

	/**
	 */
	public void setGwasRunId(BigDecimal gwasRunId) {
		this.gwasRunId = gwasRunId;
	}

	/**
	 */
	public BigDecimal getGwasRunId() {
		return this.gwasRunId;
	}

	/**
	 */
	public void setMinusLogp(BigDecimal minusLogp) {
		this.minusLogp = minusLogp;
	}

	/**
	 */
	public BigDecimal getMinusLogp() {
		return this.minusLogp;
	}

	/**
	 */
	public void setChromosome(Long chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 */
	public Long getChromosome() {
		return this.chromosome;
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
	public VGwasManhattanBasic() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VGwasManhattanBasic that) {
		setGwasMarkerId(that.getGwasMarkerId());
		setGwasRunId(that.getGwasRunId());
		setMinusLogp(that.getMinusLogp());
		setChromosome(that.getChromosome());
		setPosition(that.getPosition());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("gwasMarkerId=[").append(gwasMarkerId).append("] ");
		buffer.append("gwasRunId=[").append(gwasRunId).append("] ");
		buffer.append("minusLogp=[").append(minusLogp).append("] ");
		buffer.append("chromosome=[").append(chromosome).append("] ");
		buffer.append("position=[").append(position).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((gwasMarkerId == null) ? 0 : gwasMarkerId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VGwasManhattanBasic))
			return false;
		VGwasManhattanBasic equalCheck = (VGwasManhattanBasic) obj;
		if ((gwasMarkerId == null && equalCheck.gwasMarkerId != null)
				|| (gwasMarkerId != null && equalCheck.gwasMarkerId == null))
			return false;
		if (gwasMarkerId != null && !gwasMarkerId.equals(equalCheck.gwasMarkerId))
			return false;
		return true;
	}

	@Override
	public String getContig() {
		
		if (this.chromosome > 9)
			return "chr" + chromosome;
		else
			return "chr0" + chromosome;
	}

	@Override
	public String getRefnuc() {
		return null;
	}

	@Override
	public Long getChr() {
		
		return this.chromosome;
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	@Override
	public BigDecimal getMarkerId() {
		
		return this.gwasMarkerId;
	}

	@Override
	public Double getMinusLogPvalue() {
		
		return minusLogp.doubleValue();
	}

	@Override
	public void setMinusLogPvalue(Double pval) {
		
		minusLogp = BigDecimal.valueOf(pval);
	}

}
