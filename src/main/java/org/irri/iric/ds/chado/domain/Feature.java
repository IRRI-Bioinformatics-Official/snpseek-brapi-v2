package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;
import java.util.Calendar;

public interface Feature {

	/**
	 */
	public void setFeatureId(BigDecimal featureId);

	/**
	 */
	public BigDecimal getFeatureId();

	/**
	 */
	public void setDbxrefId(BigDecimal dbxrefId);

	/**
	 */
	public BigDecimal getDbxrefId();

	/**
	 */
	public void setOrganismId(BigDecimal organismId);

	/**
	 */
	public BigDecimal getOrganismId();

	/**
	 */
	public void setName(String name);

	/**
	 */
	public String getName();

	/**
	 */
	public void setResidues(String residues);

	/**
	 */
	public String getResidues();

	/**
	 */
	public void setSeqlen(BigDecimal seqlen);

	/**
	 */
	public BigDecimal getSeqlen();

	/**
	 */
	public void setMd5checksum(String md5checksum);

	/**
	 */
	public String getMd5checksum();

	/**
	 */
	public void setTypeId(BigDecimal typeId);

	/**
	 */
	public BigDecimal getTypeId();

	/**
	 */
	public void setIsAnalysis(Integer isAnalysis);

	/**
	 */
	public Integer getIsAnalysis();

	/**
	 */
	public void setIsObsolete(Integer isObsolete);

	/**
	 */
	public Integer getIsObsolete();

	/**
	 */
	public void setTimeaccessioned(Calendar timeaccessioned);

	/**
	 */
	public Calendar getTimeaccessioned();

	/**
	 */
	public void setTimelastmodified(Calendar timelastmodified);

	/**
	 */
	public Calendar getTimelastmodified();

	/**
	 */
	public void setUniquename(String uniquename);

	/**
	 */
	public String getUniquename();

}
