package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Interface for variety/germplasm
 * 
 * @author LMansueto
 *
 */
public interface Variety extends Comparable {

	/**
	 * Variety Id
	 * 
	 * @return
	 */
	public BigDecimal getVarietyId();

	
	/**
	 * Variety Id
	 * 
	 * @return
	 */
	public BigDecimal getStockSampleId();
	
	/**
	 * Variety name from source
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * IRIS Unique ID
	 * 
	 * @return
	 */
	public String getIrisId();

	/**
	 * Country of origin
	 * 
	 * @return
	 */
	public String getCountry();

	/**
	 * Subpopulation (indica, japonica, aus, .. etc )
	 * 
	 * @return
	 */
	public String getSubpopulation();

	/**
	 * Accession, from whatever source
	 * 
	 * @return
	 */
	public String getAccession();

	/**
	 * Used for query by example
	 * 
	 * @param country
	 */
	public void setCountry(String country);

	public void setSubpopulation(String subpopulation);

	public void setName(String name);

	public void setAccession(String accession);

	/**
	 * Print fields using delimiter
	 * 
	 * @param delimiter
	 * @return
	 */
	public String printFields(String delimiter);

	/**
	 * box code
	 * 
	 * @return
	 */
	public String getBoxCode();

	/**
	 * dataset, defined in VarietyFacade.DATASET_*
	 * 
	 * @return
	 */
	public String getDataset();

}
