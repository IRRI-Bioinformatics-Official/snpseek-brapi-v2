package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.irri.iric.ds.chado.domain.GWASRun;

/**
 */

@Entity
@NamedQueries({ @NamedQuery(name = "findAllVGwasRuns", query = "select myVGwasRun from VGwasRun myVGwasRun"),
		@NamedQuery(name = "findVGwasRunByGwasRunId", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.gwasRunId = ?1"),
		@NamedQuery(name = "findVGwasRunByMethod", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.method = ?1"),
		@NamedQuery(name = "findVGwasRunByMethodContaining", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.method like ?1"),
		@NamedQuery(name = "findVGwasRunByPrimaryKey", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.gwasRunId = ?1"),
		@NamedQuery(name = "findVGwasRunByQqplot", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.qqplot = ?1"),
		@NamedQuery(name = "findVGwasRunByQqplotContaining", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.qqplot like ?1"),
		@NamedQuery(name = "findVGwasRunByRundate", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.rundate = ?1"),
		@NamedQuery(name = "findVGwasRunBySubpopulation", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.subpopulation = ?1"),
		@NamedQuery(name = "findVGwasRunBySubpopulationContaining", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.subpopulation like ?1"),
		@NamedQuery(name = "findVGwasRunBySubpopulationId", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.subpopulationId = ?1"),
		@NamedQuery(name = "findVGwasRunByTrait", query = "select myVGwasRun from VGwasRun myVGwasRun where upper(myVGwasRun.trait) = upper(?1)"),
		@NamedQuery(name = "findVGwasRunByCoterm", query = "select myVGwasRun from VGwasRun myVGwasRun where upper(myVGwasRun.coterm) = upper(?1)"),
		@NamedQuery(name = "findVGwasRunByCodefinition", query = "select myVGwasRun from VGwasRun myVGwasRun where upper(myVGwasRun.codefinition) = upper(?1)"),
		@NamedQuery(name = "findVGwasRunByTraitContaining", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.trait like ?1"),
		@NamedQuery(name = "findVGwasRunByTraitId", query = "select myVGwasRun from VGwasRun myVGwasRun where myVGwasRun.traitId = ?1") })
@Table(name = "V_GWAS_RUN")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VGwasRun")
public class VGwasRun implements Serializable, GWASRun {
	@Override
	public String getCodefinition() {

		return codefinition;
	}

	public BigDecimal getCotermId() {
		return cotermId;
	}

	public String getCoterm() {
		return coterm;
	}

	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "GWAS_RUN_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal gwasRunId;
	/**
	 */

	@Column(name = "TRAIT_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal traitId;
	/**
	 */

	@Column(name = "DEFINITION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String trait;
	/**
	 */

	@Column(name = "COTERM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cotermId;

	@Column(name = "COTERM", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String coterm;

	@Column(name = "CODEFINITION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String codefinition;

	@Column(name = "SUBPOPULATION_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal subpopulationId;
	/**
	 */

	@Column(name = "SUBPOPULATION", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subpopulation;
	/**
	 */

	@Column(name = "METHOD", length = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String method;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RUNDATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar rundate;
	/**
	 */

	@Column(name = "QQPLOT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qqplot;

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
	public void setTraitId(BigDecimal traitId) {
		this.traitId = traitId;
	}

	/**
	 */
	public BigDecimal getTraitId() {
		return this.traitId;
	}

	/**
	 */
	public void setTrait(String trait) {
		this.trait = trait;
	}

	/**
	 */
	public String getTrait() {
		return this.trait;
	}

	/**
	 */
	public void setSubpopulationId(BigDecimal subpopulationId) {
		this.subpopulationId = subpopulationId;
	}

	/**
	 */
	public BigDecimal getSubpopulationId() {
		return this.subpopulationId;
	}

	/**
	 */
	public void setSubpopulation(String subpopulation) {
		this.subpopulation = subpopulation;
	}

	/**
	 */
	public String getSubpopulation() {
		return this.subpopulation;
	}

	/**
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 */
	public String getMethod() {
		return this.method;
	}

	/**
	 */
	public void setRundate(Calendar rundate) {
		this.rundate = rundate;
	}

	/**
	 */
	public Calendar getRundate() {
		return this.rundate;
	}

	/**
	 */
	public void setQqplot(String qqplot) {
		this.qqplot = qqplot;
	}

	/**
	 */
	public String getQqplot() {
		return this.qqplot;
	}

	/**
	 */
	public VGwasRun() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VGwasRun that) {
		setGwasRunId(that.getGwasRunId());
		setTraitId(that.getTraitId());
		setTrait(that.getTrait());
		setSubpopulationId(that.getSubpopulationId());
		setSubpopulation(that.getSubpopulation());
		setMethod(that.getMethod());
		setRundate(that.getRundate());
		setQqplot(that.getQqplot());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("gwasRunId=[").append(gwasRunId).append("] ");
		buffer.append("traitId=[").append(traitId).append("] ");
		buffer.append("trait=[").append(trait).append("] ");
		buffer.append("subpopulationId=[").append(subpopulationId).append("] ");
		buffer.append("subpopulation=[").append(subpopulation).append("] ");
		buffer.append("method=[").append(method).append("] ");
		buffer.append("rundate=[").append(rundate).append("] ");
		buffer.append("qqplot=[").append(qqplot).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((gwasRunId == null) ? 0 : gwasRunId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VGwasRun))
			return false;
		VGwasRun equalCheck = (VGwasRun) obj;
		if ((gwasRunId == null && equalCheck.gwasRunId != null) || (gwasRunId != null && equalCheck.gwasRunId == null))
			return false;
		if (gwasRunId != null && !gwasRunId.equals(equalCheck.gwasRunId))
			return false;
		return true;
	}
}
