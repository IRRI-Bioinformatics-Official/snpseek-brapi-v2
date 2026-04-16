package org.irri.iric.ds.chado.dao.access;

import java.util.Map;

import org.irri.iric.ds.chado.domain.Organism;

public interface OrganismDAO {

	/**
	 * Get map of organism name to organism object
	 * 
	 * @return
	 */
	public Map<String, org.irri.iric.ds.chado.domain.model.Organism> getMapName2Organism();

	/**
	 * Get organism with ID
	 * 
	 * @param id
	 * @return
	 */
	public org.irri.iric.ds.chado.domain.model.Organism getOrganismByID(Integer id);

}
