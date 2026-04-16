package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.List;

public interface CvTermByDatasetDAO extends CvTermDAO {

	/**
	 * Get all CV terms for all CVs
	 * 
	 * @return
	 */
	public List getAllTermsByDataset(String dataset);

	/**
	 * Get all CV terms for CV and Organism Only terms with linked loci to organism
	 * is returned
	 * 
	 * @return
	 */
	public List getAllTermsByDataset(BigDecimal cvByName, BigDecimal organismByName, String dataset);

}
