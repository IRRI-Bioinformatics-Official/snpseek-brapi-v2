
package org.irri.iric.ds.chado.domain.model;

import java.io.Serializable;
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

import org.irri.iric.ds.chado.domain.GenotypeRunPlatform;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVGenotypeRuns", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun"),
		@NamedQuery(name = "findVGenotypeRunByDataLocation", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.dataLocation = ?1"),
		@NamedQuery(name = "findVGenotypeRunByDataLocationContaining", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.dataLocation like ?1"),
		@NamedQuery(name = "findVGenotypeRunByDataset", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.dataset = ?1"),
		@NamedQuery(name = "findVGenotypeRunByDatasetContaining", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.dataset like ?1"),
		@NamedQuery(name = "findVGenotypeRunByDatasetId", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.datasetId = ?1"),
		@NamedQuery(name = "findVGenotypeRunByDatePerformed", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.datePerformed = ?1"),
		@NamedQuery(name = "findVGenotypeRunByDatePerformedAfter", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.datePerformed > ?1"),
		@NamedQuery(name = "findVGenotypeRunByDatePerformedBefore", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.datePerformed < ?1"),
		@NamedQuery(name = "findVGenotypeRunByDsDescription", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.dsDescription = ?1"),
		@NamedQuery(name = "findVGenotypeRunByDsDescriptionContaining", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.dsDescription like ?1"),
		@NamedQuery(name = "findVGenotypeRunByGenotypeRunId", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.genotypeRunId = ?1"),
		@NamedQuery(name = "findVGenotypeRunByPlatformId", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.platformId = ?1"),
		@NamedQuery(name = "findVGenotypeRunByPrimaryKey", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.genotypeRunId = ?1"),
		@NamedQuery(name = "findVGenotypeRunByVariantTypeId", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.variantTypeId = ?1"),
		@NamedQuery(name = "findVGenotypeRunByVariantset", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.variantset = ?1"),
		@NamedQuery(name = "findVGenotypeRunByVariantsetContaining", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.variantset like ?1"),
		@NamedQuery(name = "findVGenotypeRunByVariantsetId", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.variantsetId = ?1"),
		@NamedQuery(name = "findVGenotypeRunByVsDescription", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.vsDescription = ?1"),
		@NamedQuery(name = "findVGenotypeRunByVsDescriptionContaining", query = "select myVGenotypeRun from VGenotypeRun myVGenotypeRun where myVGenotypeRun.vsDescription like ?1") })

@Table(name = "v_genotype_run")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VGenotypeRun")

public class VGenotypeRun implements Serializable, GenotypeRunPlatform {
	@Override
	public String getVariantType() {

		return variantType;
	}

	@Override
	public boolean useRDBMS() {

		return !useHDF5();
	}

	@Override
	public boolean useHDF5() {

		return getLocation() != null && !getLocation().isEmpty();
	}

	@Override

	/*
	 * public int getVaridOffset() { return varid_offset; }
	 */

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "genotype_run_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)

	@Id
	@XmlElement
	Integer genotypeRunId;
	/**
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_performed")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	Calendar datePerformed;
	/**
	 */

	@Column(name = "data_location")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String dataLocation;
	/**
	 */

	@Column(name = "platform_id")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	Integer platformId;
	/**
	 */

	@Column(name = "variantset_id")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	Integer variantsetId;
	/**
	 */

	@Column(name = "variantset")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String variantset;
	/**
	 */

	@Column(name = "vs_description")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String vsDescription;
	/**
	 */

	@Column(name = "variant_type_id")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	Integer variantTypeId;
	/**
	 */

	@Column(name = "dataset_id")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	Integer datasetId;
	/**
	 */

	@Column(name = "dataset")
	@Basic(fetch = FetchType.EAGER)

	@XmlElement
	String dataset;
	/**
	 */
	
	@Column(name = "common_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String common_name;

	@Column(name = "ds_description")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String dsDescription;

	@Column(name = "method")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String method;

	@Column(name = "variant_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String variantType;
	/**
	 */

	/*
	 * @Column(name = "sampleid_offset")
	 * 
	 * @Basic(fetch = FetchType.EAGER)
	 * 
	 * @XmlElement int varid_offset;
	 */

	/**
	 */
	public void setGenotypeRunId(Integer genotypeRunId) {
		this.genotypeRunId = genotypeRunId;
	}

	/**
	 */
	public Integer getGenotypeRunId() {
		return this.genotypeRunId;
	}

	/**
	 */
	public void setDatePerformed(Calendar datePerformed) {
		this.datePerformed = datePerformed;
	}

	/**
	 */
	public Calendar getDatePerformed() {
		return this.datePerformed;
	}

	/**
	 */
	public void setDataLocation(String dataLocation) {
		this.dataLocation = dataLocation;
	}

	/**
	 */
	public String getDataLocation() {
		return this.dataLocation;
	}

	/**
	 */
	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	/**
	 */
	public Integer getPlatformId() {
		return this.platformId;
	}

	/**
	 */
	public void setVariantsetId(Integer variantsetId) {
		this.variantsetId = variantsetId;
	}

	/**
	 */
	public Integer getVariantsetId() {
		return this.variantsetId;
	}

	/**
	 */
	public void setVariantset(String variantset) {
		this.variantset = variantset;
	}

	/**
	 */
	public String getVariantset() {
		return this.variantset;
	}

	/**
	 */
	public void setVsDescription(String vsDescription) {
		this.vsDescription = vsDescription;
	}

	/**
	 */
	public String getVsDescription() {
		return this.vsDescription;
	}

	/**
	 */
	public void setVariantTypeId(Integer variantTypeId) {
		this.variantTypeId = variantTypeId;
	}

	/**
	 */
	public Integer getVariantTypeId() {
		return this.variantTypeId;
	}

	/**
	 */
	public void setDatasetId(Integer datasetId) {
		this.datasetId = datasetId;
	}

	/**
	 */
	public Integer getDatasetId() {
		return this.datasetId;
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
	public void setDsDescription(String dsDescription) {
		this.dsDescription = dsDescription;
	}

	/**
	 */
	public String getDsDescription() {
		return this.dsDescription;
	}

	/**
	 */
	public VGenotypeRun() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VGenotypeRun that) {
		setGenotypeRunId(that.getGenotypeRunId());
		setDatePerformed(that.getDatePerformed());
		setDataLocation(that.getDataLocation());
		setPlatformId(that.getPlatformId());
		setVariantsetId(that.getVariantsetId());
		setVariantset(that.getVariantset());
		setVsDescription(that.getVsDescription());
		setVariantTypeId(that.getVariantTypeId());
		setDatasetId(that.getDatasetId());
		setDataset(that.getDataset());
		setDsDescription(that.getDsDescription());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("genotypeRunId=[").append(genotypeRunId).append("] ");
		buffer.append("datePerformed=[").append(datePerformed).append("] ");
		buffer.append("dataLocation=[").append(dataLocation).append("] ");
		buffer.append("platformId=[").append(platformId).append("] ");
		buffer.append("variantsetId=[").append(variantsetId).append("] ");
		buffer.append("variantset=[").append(variantset).append("] ");
		buffer.append("vsDescription=[").append(vsDescription).append("] ");
		buffer.append("variantTypeId=[").append(variantTypeId).append("] ");
		buffer.append("datasetId=[").append(datasetId).append("] ");
		buffer.append("dataset=[").append(dataset).append("] ");
		buffer.append("dsDescription=[").append(dsDescription).append("] ");
		buffer.append("common_name=[").append(common_name).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((genotypeRunId == null) ? 0 : genotypeRunId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VGenotypeRun))
			return false;
		VGenotypeRun equalCheck = (VGenotypeRun) obj;
		if ((genotypeRunId == null && equalCheck.genotypeRunId != null)
				|| (genotypeRunId != null && equalCheck.genotypeRunId == null))
			return false;
		if (genotypeRunId != null && !genotypeRunId.equals(equalCheck.genotypeRunId))
			return false;
		return true;
	}

	@Override
	public String getLocation() {

		return this.getDataLocation();
	}
	
	public String getCommonname() {
		// TODO Auto-generated method stub
		return common_name;
	}

}
