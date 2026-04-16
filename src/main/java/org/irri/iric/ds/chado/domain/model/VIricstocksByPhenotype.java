package org.irri.iric.ds.chado.domain.model;

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

import org.irri.iric.ds.chado.domain.StockByPhenotype;
import org.irri.iric.ds.chado.domain.Variety;


/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstocksByPhenotypes", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByIricStockId", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.iricStockId = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByIricStockPhenotypeId", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.iricStockPhenotypeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByIrisUniqueId", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.irisUniqueId = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByIrisUniqueIdContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.irisUniqueId like ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByName", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.name = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByNameContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.name like ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByOriCountry", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.oriCountry = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByOriCountryContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.oriCountry like ?1"),

		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQuanLessthan", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and "
				+ " myVIricstocksByPhenotype.quanValue <= ?2 order by myVIricstocksByPhenotype.quanValue, myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQuanGreaterthan", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and "
				+ " myVIricstocksByPhenotype.quanValue >= ?2 order by myVIricstocksByPhenotype.quanValue, myVIricstocksByPhenotype.name"),

		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQualLessthan", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and "
				+ " myVIricstocksByPhenotype.qualValue <= ?2 order by myVIricstocksByPhenotype.qualValue, myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQualGreaterthan", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and "
				+ " myVIricstocksByPhenotype.qualValue >= ?2 order by myVIricstocksByPhenotype.qualValue, myVIricstocksByPhenotype.name"),

		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQuanEquals", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and "
				+ " myVIricstocksByPhenotype.quanValue = ?2 order by  myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQualEquals", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and "
				+ " myVIricstocksByPhenotype.qualValue = ?2 order by  myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeId", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1"),

		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQuanLessthanDataset", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and  myVIricstocksByPhenotype.dataset in (?2) and "
				+ " myVIricstocksByPhenotype.quanValue <= ?3 order by myVIricstocksByPhenotype.quanValue, myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQuanGreaterthanDataset", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and  myVIricstocksByPhenotype.dataset in (?2) and "
				+ " myVIricstocksByPhenotype.quanValue >= ?3 order by myVIricstocksByPhenotype.quanValue, myVIricstocksByPhenotype.name"),

		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQualLessthanDataset", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and  myVIricstocksByPhenotype.dataset in (?2) and "
				+ " myVIricstocksByPhenotype.qualValue <= ?3 order by myVIricstocksByPhenotype.qualValue, myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQualGreaterthanDataset", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and  myVIricstocksByPhenotype.dataset in (?2) and "
				+ " myVIricstocksByPhenotype.qualValue >= ?3 order by myVIricstocksByPhenotype.qualValue, myVIricstocksByPhenotype.name"),

		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQuanEqualsDataset", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and  myVIricstocksByPhenotype.dataset in (?2) and "
				+ " myVIricstocksByPhenotype.quanValue = ?3  order by  myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQualEqualsDataset", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and  myVIricstocksByPhenotype.dataset in (?2) and "
				+ " myVIricstocksByPhenotype.qualValue = ?3 order by  myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdDataset", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1  and myVIricstocksByPhenotype.dataset in (?2)"),

		@NamedQuery(name = "findVIricstocksByPhenotypeByPrimaryKey", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.iricStockPhenotypeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByQualValue", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.qualValue = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByQualValueContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.qualValue like ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByQuanValue", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.quanValue = ?1"),

		@NamedQuery(name = "findVIricstocksByPhenotypeBySubpopulation", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.subpopulation = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeBySubpopulationContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.subpopulation like ?1") })

// @Table( name = "V_IRICSTOCKS_BY_PHENOTYPE")
@Table(name = "V_STOCK_BY_PHENOTYPE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstocksByPhenotype")
public class VIricstocksByPhenotype implements StockByPhenotype { // Serializable, VarietyPlus {
	private static final long serialVersionUID = 1L;

	// private String valuename;

	/**
	 */

	@Column(name = "IRIC_STOCK_PHENOTYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockPhenotypeId;
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

	@Column(name = "QUAN_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal quanValue;
	/**
	 */

	@Column(name = "PHENOTYPE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal phenotypeId;

	@Column(name = "DATASET")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String dataset;

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

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	/**
	 */
	public VIricstocksByPhenotype() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstocksByPhenotype that) {
		setIricStockPhenotypeId(that.getIricStockPhenotypeId());
		setIricStockId(that.getIricStockId());
		setName(that.getName());
		setIrisUniqueId(that.getIrisUniqueId());
		setOriCountry(that.getOriCountry());
		setSubpopulation(that.getSubpopulation());
		setQualValue(that.getQualValue());
		setQuanValue(that.getQuanValue());
		setPhenotypeId(that.getPhenotypeId());
		setDataset(that.getDataset());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("iricStockPhenotypeId=[").append(iricStockPhenotypeId).append("] ");
		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("irisUniqueId=[").append(irisUniqueId).append("] ");
		buffer.append("oriCountry=[").append(oriCountry).append("] ");
		buffer.append("subpopulation=[").append(subpopulation).append("] ");
		buffer.append("qualValue=[").append(qualValue).append("] ");
		buffer.append("quanValue=[").append(quanValue).append("] ");
		buffer.append("phenotypeId=[").append(phenotypeId).append("] ");
		buffer.append("dataset=[").append(dataset).append("] ");

		return buffer.toString();
	}

	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = (int) (prime * result + ((iricStockPhenotypeId == null) ? 0 :
	 * iricStockPhenotypeId.hashCode())); return result; } public boolean
	 * equals(Object obj) { if (obj == this) return true; if (!(obj instanceof
	 * VIricstocksByPhenotype)) return false; VIricstocksByPhenotype equalCheck =
	 * (VIricstocksByPhenotype) obj; if ((iricStockPhenotypeId == null &&
	 * equalCheck.iricStockPhenotypeId != null) || (iricStockPhenotypeId != null &&
	 * equalCheck.iricStockPhenotypeId == null)) return false; if
	 * (iricStockPhenotypeId != null &&
	 * !iricStockPhenotypeId.equals(equalCheck.iricStockPhenotypeId)) return false;
	 * return true; }
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((getVarietyId() == null) ? 0 : getVarietyId().hashCode()));
		result = (int) (prime * result + ((getDataset() == null) ? 0 : getDataset().hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Variety)) // VIricstockBasicprop))
			return false;
		// VIricstockBasicprop equalCheck = (VIricstockBasicprop) obj;
		Variety equalCheck = (Variety) obj;

		// return iricStockId.equals(equalCheck.getIricStockId());

		if ((getVarietyId() == null && equalCheck.getVarietyId() != null)
				|| (getVarietyId() != null && equalCheck.getVarietyId() == null))
			return false;
		if (getVarietyId() != null && !getVarietyId().equals(equalCheck.getVarietyId()))
			return false;
		if ((getDataset() == null && equalCheck.getDataset() != null)
				|| (getDataset() != null && equalCheck.getDataset() == null))
			return false;
		if (getDataset() != null && !getDataset().equals(equalCheck.getDataset()))
			return false;
		return true;

	}

	@Override
	public BigDecimal getVarietyId() {
		
		return this.getIricStockId();
	}

	@Override
	public String getIrisId() {
		
		if (this.getIrisUniqueId() == null || this.getIrisUniqueId().isEmpty())
			return this.getBoxCode();
		else
			return this.getIrisUniqueId();
	}

	@Override
	public String getCountry() {
		
		return this.getOriCountry();
	}

	@Override
	public void setCountry(String country) {
		
		this.setOriCountry(country);
	}

	@Override
	public Object getValue() {
		
		if (this.quanValue != null)
			return this.quanValue;
		else if (this.qualValue != null)
			return this.qualValue;
		return null;
	}

	/*
	 * @Override public String getValueName() { 
	 * return valuename; }
	 * 
	 * @Override public void setValueName(String valuename) { // TODO Auto-generated
	 * method stub this.valuename=valuename;
	 * 
	 * }
	 */

	@Override
	public int compareTo(Object o) {
		
		int ret = getName().compareTo(((Variety) o).getName());
		if (ret == 0)
			ret = getVarietyId().compareTo(((Variety) o).getVarietyId());
		if (ret == 0)
			ret = getDataset().compareTo(((Variety) o).getDataset());

		return ret;
	}

	@Override
	public String printFields(String delimiter) {
		
		
		String irisid = getIrisId();
		if (irisid == null)
			irisid = "";
		String subpop = getSubpopulation();
		if (subpop == null)
			subpop = "";
		String cntr = getCountry();
		if (cntr == null)
			cntr = "";
		String strvalue = qualValue;
		if (strvalue == null)
			strvalue = quanValue.toString();
		String acc = this.getAccession();
		if (acc == null)
			acc = "";

		// return this.getName() + delimiter + irisid + delimiter + subpop + delimiter +
		// cntr + delimiter + strvalue;
		return "\"" + this.getName() + "\"" + delimiter + "\"" + irisid + "\"" + delimiter + "\"" + acc + "\""
				+ delimiter + "\"" + subpop + "\"" + delimiter + "\"" + cntr + "\"" + delimiter + strvalue;

	}

	@Override
	public String getBoxCode() {
		
		return boxCode;
	}

	@Override
	public void setValue(Object value) {

	}

	@Override
	public String getAccession() {
		
		try {
			Integer.valueOf(gsAccession);
			return "IRGC" + this.gsAccession;
		} catch (Exception ex) {
			return gsAccession;
		}
	}

	@Override
	public String getDataset() {
		
		// return VarietyFacade.DATASET_SNPINDELV2_IUPAC;
		return dataset;
	}

	@Override
	public void setAccession(String accession) {
		
		this.gsAccession = accession;
	}

	@Override
	public BigDecimal getStockId() {
		return getIricStockId();
	}
	
	@Override
	public BigDecimal getStockSampleId() {
		// TODO Auto-generated method stub
		return null;
	}

}
