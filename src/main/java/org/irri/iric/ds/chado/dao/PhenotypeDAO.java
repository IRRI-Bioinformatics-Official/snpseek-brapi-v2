package org.irri.iric.ds.chado.dao;

import java.util.List;
import java.util.Set;

import org.irri.iric.ds.chado.domain.Phenotype;
import org.irri.iric.ds.chado.domain.Variety;

public interface PhenotypeDAO {

	/**
	 * Get list of phenotypes/values for variety
	 * 
	 * @param var
	 * @param dataset
	 * @return
	 */
	public List findPhenotypesByVariety(Variety var, Set dataset); // , String dataset );

	/**
	 * Get list of phenotypes/values for variety with name like (used to bridge
	 * legacy and new schema)
	 * 
	 * @param var
	 * @return
	 */
	public List findPhenotypesByVarietyNameLike(String name);

	/**
	 * Get phenotype values for variety
	 * 
	 * @param var
	 * @param phenId2
	 * @return
	 */
	public Phenotype findPhenotypesByVariety(Variety var, String dataset, String phenId);

	/**
	 * Get phenotype values for all varieties
	 * 
	 * @param var
	 * @return
	 */
	// public List findPhenotypesByVariety(String phenId);

	public List findPhenotypesByVariety(String phenId, String dataset);

}
