package org.irri.iric.ds.chado.dao;

import java.util.List;

public interface VarietyCvTermDAO extends VarietyByPhenotypeDAO {

	/**
	 * Get phenotypes with CV term in CV
	 * 
	 * @param desc
	 * @param cvId
	 * @return
	 */

	List getPhenotypeByDescription(String desc, Integer cvId);

}
