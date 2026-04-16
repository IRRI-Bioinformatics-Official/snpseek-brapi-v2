package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.irri.iric.ds.chado.domain.Variety;

public interface VarietyDAO {

	/**
	 * Get all varieties
	 * 
	 * @return
	 */
	public Set findAllVariety();

	public Set findAllVariety(Set dataset);

	/**
	 * Get varieties from country
	 * 
	 * @param country
	 * @return
	 */
	public Set findAllVarietyByCountry(String country);

	public Set findAllVarietyByCountry(String country, Set dataset);

	/**
	 * Get varieties in subpopulation
	 * 
	 * @param subpopulation
	 * @return
	 */
	public Set findAllVarietyBySubpopulation(String subpopulation);

	public Set findAllVarietyBySubpopulation(String subpopulation, Set dataset);

	public Set findAllVarietyByExample(Variety germplasm);

	public Set findAllVarietyByExample(Variety germplasm, Set dataset);

	/**
	 * Getg varieties from country and in subpopulation
	 * 
	 * @param country
	 * @param subpopulation
	 * @param dataset
	 * @return
	 */
	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation, Set dataset);

	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation);

	/**
	 * Get variety with name
	 * 
	 * @param name
	 * @return
	 */
	public Variety findVarietyByName(String name);

	public List<Variety> findVarietyByName(String name, Set dataset);

	/**
	 * Get variety with name like
	 * 
	 * @param name
	 * @return
	 */
	public Variety findVarietyByNameLike(String name);

	public Variety findVarietyByNameLike(String name, Set dataset);

	/**
	 * Get variety with IRIS Id
	 * 
	 * @param name
	 * @return
	 */
	public Variety findVarietyByIrisId(String name);

	public Variety findVarietyByIrisId(String name, Set dataset);

	/**
	 * Get variety with Variety ID
	 * 
	 * @param id
	 * @return
	 */
	public Variety findVarietyById(BigDecimal id);

	public Variety findVarietyById(BigDecimal id, Set dataset);

	/**
	 * Get variety with accession
	 * 
	 * @param name
	 * @return
	 */
	public Variety findVarietyByAccession(String name);

	public Variety findVarietyByAccession(String name, Set dataset);

	public List<Variety> findVarietyByAccession(Collection names, Set dataset);

	public List<Variety> findVarietyByNamesLike(Collection names);

	public List<Variety> findVarietyByNamesLike(Collection names, Set dataset);

	public List<Variety> findVarietyByNames(Collection names);

	public List<Variety> findVarietyByNames(Collection names, Set dataset);

	public List<Variety> findVarietyByIrisIds(Collection names);

	public List<Variety> findVarietyByIrisIds(Collection names, Set dataset);

	public Set<Variety> findVarietiesByIrisId(String irisid);

	public Set<Variety> findVarietiesByIrisId(String irisid, Set dataset);

	public Set<Variety> findVarietiesByAccession(String accession);
	// public Variety findVarietiesByAccession(String accession, Set dataset);

	public Set<Variety> findVarietiesByName(String names);

	public Set<Variety> findVarietiesByName(String names, Set dataset);

	public Set<Variety> findVarietiesByNameAccession(String varname, String accession);

	public Set<Variety> findVarietiesByNameAccession(String varname, String accession, Set dataset);

	public Collection<Variety> findVarietyByIds(Set setQueryVarobj);

	public Collection<Variety> findVarietyByIds(Set setQueryVarobj, Set dataset);

	public Collection<Variety> findVarietiesByDataset(String dataset);

	public Collection<Variety> findVarietiesByDatasets(Set dataset);

	public Set findAllVarietyByCountryAndSubpopulationDatasets(String country, String subpopulation, Set dataset);

	public Set<Variety> getIRGCStocks();
	
	public void setEntityManager(EntityManager entityManager);

	public List<Variety> findVarietyByNamesWithSpace(Collection names, Set dataset);

	/*
	 * public List getVarietyNames(Set dataset); public Variety
	 * getGermplasmByAccession(String trim, Set dataset); public Variety
	 * getGermplasmByName(String var1, Set dataset); public Map<String, Variety>
	 * getMapVarname2Variety(Set dataset); public Collection
	 * getGermplasmsByName(String varname, Set dataset); public Collection
	 * getGermplasmsByAccession(String varname, Set dataset); public Map
	 * getMapId2Variety(Set dataset); public Collection getSubpopulations(Set
	 * dataset); public List getAccessions(Set dataset); public Set
	 * getGermplasmBySubpopulation(String subpopulation, Set dataset); public List
	 * getVarietyAccessions(Set dataset); public List getIrisIds(Set dataset);
	 * public List getCountries(Set dataset); public Set checkVariety(String varstr,
	 * Set dataset); public Variety getGermplasmByIrisId(String name, Set dataset);
	 * public Collection getGermplasmsByNameAccession(String varname, String
	 * accession, Set dataset); public Collection
	 * getVarietyByCountryAndSubpopulation(String country, String subpopulation, Set
	 * dataset);
	 */

}
