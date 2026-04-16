package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface VarietyByPhenotypeDAO {

	/**
	 * Get varieties with numeric phenotype value less than value
	 * 
	 * @param object
	 * @param value
	 * @return
	 */
	List findVarietyByQuanPhenotypeLessThan(BigDecimal object, Set dataset, double value);

	/**
	 * Get varieties with numeric phenotype value greater than value
	 * 
	 * @param object
	 * @param value
	 * @return
	 */
	List findVarietyByQuanPhenotypeGreaterThan(BigDecimal object, Set dataset, double value);

	/**
	 * Get varieties with numeric phenotype value equal to value
	 * 
	 * @param object
	 * @param value
	 * @return
	 */
	List findVarietyByQuanPhenotypeEquals(BigDecimal object, Set dataset, double value);

	/**
	 * Get varieties with qualitative phenotype value equal to value
	 * 
	 * @param object
	 * @param value
	 * @param value
	 * @return
	 */
	List findVarietyByQualPhenotypeEquals(BigDecimal object, Set dataset, String value);

	/**
	 * Get phenotype values for all varieties
	 * 
	 * @param phen
	 * @param dataset
	 * @return
	 */
	// List findVarietyByPhenotype(BigDecimal phen, String dataset);

	List findVarietyByQualPhenotypeLessThan(BigDecimal object, Set dataset, String value);

	List findVarietyByQualPhenotypeGreaterThan(BigDecimal object, Set dataset, String value);

	List findVarietyByPhenotype(BigDecimal phen, Set dataset);

}
