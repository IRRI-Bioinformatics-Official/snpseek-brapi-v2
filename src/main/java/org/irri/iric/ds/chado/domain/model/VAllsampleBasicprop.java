
package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;

import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import org.irri.iric.ds.chado.domain.StockSample;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
	
		@NamedQuery(name = "findAllVAllsampleBasicprops", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop"),
		@NamedQuery(name = "findVAllsampleBasicpropByAssay", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.assay = ?1"),
		@NamedQuery(name = "findVAllsampleBasicpropByAssayContaining", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.assay like ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByBoxCode", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.boxCode = ?1"),
		@NamedQuery(name = "findVAllsampleBasicpropByBoxCodeContaining", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.boxCode like ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByDataset", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.dataset = ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByDatasetIn", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.dataset in (?1) order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByDatasetInAndSampleId", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.stockSampleId in (?1) and myVAllsampleBasicprop.dataset in (?2) order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByDatasetContaining", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.dataset like ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByGsAccession", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.gsAccession = ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByGsAccessionContaining", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.gsAccession like ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByHdf5Index", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.hdf5Index = ?1"),
		@NamedQuery(name = "findVAllsampleBasicpropByName", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.name = ?1"),
		@NamedQuery(name = "findVAllsampleBasicpropByNameContaining", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.name like ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByOriCountry", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.oriCountry = ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByOriCountryContaining", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.oriCountry like ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByPrimaryKey", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.stockSampleId = ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByStockId", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.stockId = ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropByStockSampleId", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.stockSampleId = ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropBySubpopulation", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.subpopulation = ?1 order by myVAllsampleBasicprop.hdf5Index"),
		@NamedQuery(name = "findVAllsampleBasicpropBySubpopulationContaining", query = "select myVAllsampleBasicprop from VAllsampleBasicprop myVAllsampleBasicprop where myVAllsampleBasicprop.subpopulation like ?1 order by myVAllsampleBasicprop.hdf5Index") })

@Table(schema = "public", name = "v_allsample_basicprop")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VAllsampleBasicprop")

public class VAllsampleBasicprop implements Serializable, StockSample {
	@Override
	public BigDecimal getVarietyId() {
		return stockId;
	}

	@Override
	public String getIrisId() {
		return assay;
	}

	@Override
	public String getCountry() {
		return oriCountry;
	}

	@Override
	public String getAccession() {
		return gsAccession;
	}

	@Override
	public void setCountry(String country) {
		oriCountry = country;
	}

	@Override
	public void setAccession(String accession) {
		gsAccession = accession;
	}

	@Override
	public String printFields(String delimiter) {
		return null;
	}

	@Override
	public int compareTo(Object o) {
		StockSample s = (StockSample) o;
		return this.getStockSampleId().compareTo(s.getStockSampleId());
	}

	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "sample_varietyset_id", nullable = false)
	// @Column(name = "stock_sample_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal sampleVarietySetId;
	/**
	 */

	@Column(name = "stock_sample_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal stockSampleId;
	/**
	 */

	@Column(name = "assay")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String assay;
	/**
	 */

	@Column(name = "dataset")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String dataset;
	/**
	 */

	@Column(name = "hdf5_index")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	Integer hdf5Index;
	/**
	 */

	@Column(name = "stock_id")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	BigDecimal stockId;
	/**
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String name;
	/**
	 */

	@Column(name = "ori_country", length = 4000)
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String oriCountry;
	/**
	 */

	@Column(name = "subpopulation", length = 4000)
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String subpopulation;
	/**
	 */

	@Column(name = "box_code", length = 4000)
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String boxCode;
	/**
	 */

	@Column(name = "gs_accession", length = 4000)
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String gsAccession;

	/**
	 */
	public void setStockSampleId(BigDecimal stockSampleId) {
		this.stockSampleId = stockSampleId;
	}

	/**
	 */
	public BigDecimal getStockSampleId() {
		return this.stockSampleId;
	}

	/**
	 */
	public void setAssay(String assay) {
		this.assay = assay;
	}

	/**
	 */
	public String getAssay() {
		return this.assay;
	}

	/**
	 */
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	/**
	 */
	public String getDataset() {
		return this.dataset;
	}

	/**
	 */
	public void setHdf5Index(Integer hdf5Index) {
		this.hdf5Index = hdf5Index;
	}

	/**
	 */
	public Integer getHdf5Index() {
		return this.hdf5Index;
	}

	/**
	 */
	public void setStockId(BigDecimal stockId) {
		this.stockId = stockId;
	}

	/**
	 */
	public BigDecimal getStockId() {
		return this.stockId;
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
	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}

	/**
	 */
	public String getBoxCode() {
		return this.boxCode;
	}

	/**
	 */
	public void setGsAccession(String gsAccession) {
		this.gsAccession = gsAccession;
	}

	/**
	 */
	public String getGsAccession() {
		return this.gsAccession;
	}

	/**
	 */
	public VAllsampleBasicprop() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VAllsampleBasicprop that) {
		setStockSampleId(that.getStockSampleId());
		setAssay(that.getAssay());
		setDataset(that.getDataset());
		setHdf5Index(that.getHdf5Index());
		setStockId(that.getStockId());
		setName(that.getName());
		setOriCountry(that.getOriCountry());
		setSubpopulation(that.getSubpopulation());
		setBoxCode(that.getBoxCode());
		setGsAccession(that.getGsAccession());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("stockSampleId=[").append(stockSampleId).append("] ");
		buffer.append("assay=[").append(assay).append("] ");
		buffer.append("dataset=[").append(dataset).append("] ");
		buffer.append("hdf5Index=[").append(hdf5Index).append("] ");
		buffer.append("stockId=[").append(stockId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("oriCountry=[").append(oriCountry).append("] ");
		buffer.append("subpopulation=[").append(subpopulation).append("] ");
		buffer.append("boxCode=[").append(boxCode).append("] ");
		buffer.append("gsAccession=[").append(gsAccession).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((stockSampleId == null) ? 0 : stockSampleId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VAllsampleBasicprop))
			return false;
		VAllsampleBasicprop equalCheck = (VAllsampleBasicprop) obj;
		if ((stockSampleId == null && equalCheck.stockSampleId != null)
				|| (stockSampleId != null && equalCheck.stockSampleId == null))
			return false;
		if (stockSampleId != null && !stockSampleId.equals(equalCheck.stockSampleId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getSampleVarietySetId() {
		return sampleVarietySetId;
		// return stockSampleId;
	}

}
