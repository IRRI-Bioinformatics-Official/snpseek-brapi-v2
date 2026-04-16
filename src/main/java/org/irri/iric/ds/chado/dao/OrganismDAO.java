package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.Organism;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Organism entities.
 * 
 */
public interface OrganismDAO extends JpaDao<Organism> , org.irri.iric.ds.chado.dao.access.OrganismDAO{

	/**
	 * JPQL Query - findOrganismByGenusContaining
	 *
	 */
	public Set<Organism> findOrganismByGenusContaining(String genus) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByGenusContaining
	 *
	 */
	public Set<Organism> findOrganismByGenusContaining(String genus, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByGenus
	 *
	 */
	public Set<Organism> findOrganismByGenus(String genus_1) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByGenus
	 *
	 */
	public Set<Organism> findOrganismByGenus(String genus_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismBySpeciesContaining
	 *
	 */
	public Set<Organism> findOrganismBySpeciesContaining(String species) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismBySpeciesContaining
	 *
	 */
	public Set<Organism> findOrganismBySpeciesContaining(String species, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByAbbreviationContaining
	 *
	 */
	public Set<Organism> findOrganismByAbbreviationContaining(String abbreviation) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByAbbreviationContaining
	 *
	 */
	public Set<Organism> findOrganismByAbbreviationContaining(String abbreviation, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByCommonNameContaining
	 *
	 */
	public Set<Organism> findOrganismByCommonNameContaining(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByCommonNameContaining
	 *
	 */
	public Set<Organism> findOrganismByCommonNameContaining(String commonName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllOrganisms
	 *
	 */
	public Set<Organism> findAllOrganisms() throws DataAccessException;

	/**
	 * JPQL Query - findAllOrganisms
	 *
	 */
	public Set<Organism> findAllOrganisms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByOrganismId
	 *
	 */
	public Organism findOrganismByOrganismId(BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByOrganismId
	 *
	 */
	public Organism findOrganismByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByAbbreviation
	 *
	 */
	public Set<Organism> findOrganismByAbbreviation(String abbreviation_1) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByAbbreviation
	 *
	 */
	public Set<Organism> findOrganismByAbbreviation(String abbreviation_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findOrganismBySpecies
	 *
	 */
	public Set<Organism> findOrganismBySpecies(String species_1) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismBySpecies
	 *
	 */
	public Set<Organism> findOrganismBySpecies(String species_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByPrimaryKey
	 *
	 */
	public Organism findOrganismByPrimaryKey(BigDecimal organismId_1) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByPrimaryKey
	 *
	 */
	public Organism findOrganismByPrimaryKey(BigDecimal organismId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByCommonName
	 *
	 */
	public Set<Organism> findOrganismByCommonName(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findOrganismByCommonName
	 *
	 */
	public Set<Organism> findOrganismByCommonName(String commonName_1, int startResult, int maxRows)
			throws DataAccessException;

}