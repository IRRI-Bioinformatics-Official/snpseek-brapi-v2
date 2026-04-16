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

import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.VarietyPlus;
import org.irri.iric.ds.utils.DbConstant;


/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstocksByPtocos", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco"),
		@NamedQuery(name = "findVIricstocksByPtocoByAccession", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.accession = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByAccessionContaining", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.accession like ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByCvterm", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.cvterm = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByCvtermContaining", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.cvterm like ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByCvtermId", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.cvtermId = ?1"),

		@NamedQuery(name = "findVIricstocksByPtocoByPhenotypeIdQuanLessthan", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.cvtermId = ?1 and "
				+ " myVIricstocksByPtoco.quanValue < ?2 order by myVIricstocksByPtoco.quanValue, myVIricstocksByPtoco.name"),
		@NamedQuery(name = "findVIricstocksByPtocoByPhenotypeIdQuanGreaterthan", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.cvtermId = ?1 and "
				+ " myVIricstocksByPtoco.quanValue > ?2 order by myVIricstocksByPtoco.quanValue, myVIricstocksByPtoco.name"),
		@NamedQuery(name = "findVIricstocksByPtocoByPhenotypeIdQuanEquals", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.cvtermId = ?1 and "
				+ " myVIricstocksByPtoco.quanValue = ?2 order by  myVIricstocksByPtoco.name"),
		@NamedQuery(name = "findVIricstocksByPtocoByPhenotypeIdQualEquals", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.cvtermId = ?1 and "
				+ " myVIricstocksByPtoco.qualValue = ?2 order by  myVIricstocksByPtoco.name"),

		@NamedQuery(name = "findVIricstocksByPtocoByDb", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.db = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByDbContaining", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.db like ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByDefinition", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.definition = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByDefinitionContaining", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.definition like ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByIricStockId", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.iricStockId = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByIricStockPhenotypeId", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.iricStockPhenotypeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByIrisUniqueId", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.irisUniqueId = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByIrisUniqueIdContaining", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.irisUniqueId like ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByName", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.name = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByNameContaining", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.name like ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByOriCountry", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.oriCountry = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByOriCountryContaining", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.oriCountry like ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByPhenotypeId", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.phenotypeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByPrimaryKey", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.iricStockPhenotypeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByQualValue", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.qualValue = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByQualValueContaining", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.qualValue like ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoByQuanValue", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.quanValue = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoBySubpopulation", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.subpopulation = ?1"),
		@NamedQuery(name = "findVIricstocksByPtocoBySubpopulationContaining", query = "select myVIricstocksByPtoco from VIricstocksByPtoco myVIricstocksByPtoco where myVIricstocksByPtoco.subpopulation like ?1") })
@Table(name = "V_IRICSTOCKS_BY_PTOCO")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VIricstocksByPtoco")
public class VIricstocksByPtoco implements Serializable, VarietyPlus {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "IRIC_STOCK_PHENOTYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockPhenotypeId;
	/**
	 */

	@Column(name = "CVTERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvterm;
	/**
	 */

	@Column(name = "ACCESSION", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accession;
	/**
	 */

	@Column(name = "DB", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String db;
	/**
	 */

	@Column(name = "DEFINITION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String definition;
	/**
	 */

	@Column(name = "CVTERM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cvtermId;
	/**
	 */

	@Column(name = "IRIC_STOCK_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal iricStockId;
	/**
	 */

	@Column(name = "NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "IRIS_UNIQUE_ID", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String irisUniqueId;
	/**
	 */

	@Column(name = "BOX_CODE", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String boxCode;

	@Column(name = "ORI_COUNTRY", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String oriCountry;
	/**
	 */

	@Column(name = "SUBPOPULATION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subpopulation;
	/**
	 */

	@Column(name = "GS_ACCESSION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String gsAccession;

	@Column(name = "QUAL_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qualValue;
	/**
	 */

	@Column(name = "QUAN_VALUE", scale = 10, precision = 15)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal quanValue;
	/**
	 */

	@Column(name = "PHENOTYPE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal phenotypeId;

	/**
	 */
	public void setIricStockPhenotypeId(BigDecimal iricStockPhenotypeId) {
		this.iricStockPhenotypeId = iricStockPhenotypeId;
	}

	/**
	 */
	public BigDecimal getIricStockPhenotypeId() {
		return this.iricStockPhenotypeId;
	}

	/**
	 */
	public void setCvterm(String cvterm) {
		this.cvterm = cvterm;
	}

	/**
	 */
	public String getCvterm() {
		return this.cvterm;
	}

	/**
	 */
	public void setAccession(String accession) {
		this.accession = accession;
	}

	/**
	 */
	public String getAccession() {
		return this.accession;
	}

	/**
	 */
	public void setDb(String db) {
		this.db = db;
	}

	/**
	 */
	public String getDb() {
		return this.db;
	}

	/**
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 */
	public String getDefinition() {
		return this.definition;
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
	public void setIricStockId(BigDecimal iricStockId) {
		this.iricStockId = iricStockId;
	}

	/**
	 */
	public BigDecimal getIricStockId() {
		return this.iricStockId;
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
	public void setIrisUniqueId(String irisUniqueId) {
		this.irisUniqueId = irisUniqueId;
	}

	/**
	 */
	public String getIrisUniqueId() {
		return this.irisUniqueId;
	}

	/**
	 */
	public void setOriCountry(String oriCountry) {
		this.oriCountry = oriCountry;
	}

	/**
	 */
	public String getOriCountry() {
		return this.oriCountry;
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
	public void setPhenotypeId(BigDecimal phenotypeId) {
		this.phenotypeId = phenotypeId;
	}

	/**
	 */
	public BigDecimal getPhenotypeId() {
		return this.phenotypeId;
	}

	/**
	 */
	public VIricstocksByPtoco() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstocksByPtoco that) {
		setIricStockPhenotypeId(that.getIricStockPhenotypeId());
		setCvterm(that.getCvterm());
		setAccession(that.getAccession());
		setDb(that.getDb());
		setDefinition(that.getDefinition());
		setCvtermId(that.getCvtermId());
		setIricStockId(that.getIricStockId());
		setName(that.getName());
		setIrisUniqueId(that.getIrisUniqueId());
		setOriCountry(that.getOriCountry());
		setSubpopulation(that.getSubpopulation());
		setQualValue(that.getQualValue());
		setQuanValue(that.getQuanValue());
		setPhenotypeId(that.getPhenotypeId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("iricStockPhenotypeId=[").append(iricStockPhenotypeId).append("] ");
		buffer.append("cvterm=[").append(cvterm).append("] ");
		buffer.append("accession=[").append(accession).append("] ");
		buffer.append("db=[").append(db).append("] ");
		buffer.append("definition=[").append(definition).append("] ");
		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("irisUniqueId=[").append(irisUniqueId).append("] ");
		buffer.append("oriCountry=[").append(oriCountry).append("] ");
		buffer.append("subpopulation=[").append(subpopulation).append("] ");
		buffer.append("qualValue=[").append(qualValue).append("] ");
		buffer.append("quanValue=[").append(quanValue).append("] ");
		buffer.append("phenotypeId=[").append(phenotypeId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((iricStockPhenotypeId == null) ? 0 : iricStockPhenotypeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstocksByPtoco))
			return false;
		VIricstocksByPtoco equalCheck = (VIricstocksByPtoco) obj;
		if ((iricStockPhenotypeId == null && equalCheck.iricStockPhenotypeId != null)
				|| (iricStockPhenotypeId != null && equalCheck.iricStockPhenotypeId == null))
			return false;
		if (iricStockPhenotypeId != null && !iricStockPhenotypeId.equals(equalCheck.iricStockPhenotypeId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getVarietyId() {
		
		return this.iricStockId;
	}

	@Override
	public String getIrisId() {
		
		if (irisUniqueId != null)
			return irisUniqueId;
		return boxCode;
	}

	@Override
	public String getCountry() {
		
		return this.oriCountry;
	}

	@Override
	public void setCountry(String country) {
		
		this.oriCountry = country;

	}

	@Override
	public String printFields(String delimiter) {
		
		return this.toString();
	}

	@Override
	public String getBoxCode() {
		
		return this.boxCode;
	}

	@Override
	public String getDataset() {
		
		return DbConstant.DEFAULT_DATASET;
	}

	@Override
	public int compareTo(Object arg0) {
		
		Variety v = (Variety) arg0;
		return getVarietyId().compareTo(v.getVarietyId());
	}

	@Override
	public void setValue(Object value) {
		
		if (value instanceof String)
			this.qualValue = (String) value;
		else
			this.quanValue = BigDecimal.valueOf(Long.valueOf(value.toString()));

	}

	@Override
	public Object getValue() {
		
		if (qualValue != null)
			return qualValue;
		return quanValue;
	}
	
	@Override
	public BigDecimal getStockSampleId() {
		// TODO Auto-generated method stub
		return null;
	}

}
