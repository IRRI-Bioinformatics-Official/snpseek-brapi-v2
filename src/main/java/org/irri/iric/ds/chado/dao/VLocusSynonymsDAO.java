package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VLocusSynonyms;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusSynonyms entities.
 * 
 */
public interface VLocusSynonymsDAO extends JpaDao<VLocusSynonyms>, FeatureSynonymDAO {

	/**
	 * JPQL Query - findVLocusSynonymsByOrganismId
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsByOrganismId(BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsByOrganismId
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsBySynonymContaining
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsBySynonymContaining(String synonym) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsBySynonymContaining
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsBySynonymContaining(String synonym, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusSynonymss
	 *
	 */
	public Set<VLocusSynonyms> findAllVLocusSynonymss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusSynonymss
	 *
	 */
	public Set<VLocusSynonyms> findAllVLocusSynonymss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsBySynonymId
	 *
	 */
	public VLocusSynonyms findVLocusSynonymsBySynonymId(BigDecimal synonymId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsBySynonymId
	 *
	 */
	public VLocusSynonyms findVLocusSynonymsBySynonymId(BigDecimal synonymId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsBySynonym
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsBySynonym(String synonym_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsBySynonym
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsBySynonym(String synonym_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsByFeatureId
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsByFeatureId(BigDecimal featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsByFeatureId
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsByPrimaryKey
	 *
	 */
	public VLocusSynonyms findVLocusSynonymsByPrimaryKey(BigDecimal synonymId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsByPrimaryKey
	 *
	 */
	public VLocusSynonyms findVLocusSynonymsByPrimaryKey(BigDecimal synonymId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsByLocus
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsByLocus(String locus) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsByLocus
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsByLocus(String locus, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsByLocusContaining
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsByLocusContaining(String locus_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusSynonymsByLocusContaining
	 *
	 */
	public Set<VLocusSynonyms> findVLocusSynonymsByLocusContaining(String locus_1, int startResult, int maxRows)
			throws DataAccessException;

}