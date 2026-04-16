package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.List;

public interface CvTermDAO {

	/**
	 * Get all CV terms for all CVs
	 * 
	 * @return
	 */
	public List getAllTerms();

	/**
	 * Get all CV terms for CV and Organism Only terms with linked loci to organism
	 * is returned
	 * 
	 * @return
	 */
	public List getAllTerms(BigDecimal cvByName, BigDecimal organismByName);
}
