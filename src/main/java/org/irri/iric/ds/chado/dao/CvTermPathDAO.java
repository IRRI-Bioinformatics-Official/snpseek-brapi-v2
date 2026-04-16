package org.irri.iric.ds.chado.dao;

import java.util.List;
import java.util.Set;

public interface CvTermPathDAO extends CvTermUniqueValuesDAO {
	/**
	 * Ancestors of cv term
	 * 
	 * @param cv
	 * @param term
	 * @return
	 */
	List getAncestors(String cv, String term);

	/**
	 * Decendants of cv tern
	 * 
	 * @param cv
	 * @param term
	 * @param dataset
	 * @return
	 */
	List getDescendants(String cv, String term, Set dataset);

	// List getDescendants(String cv, String term, String dataset);

}
