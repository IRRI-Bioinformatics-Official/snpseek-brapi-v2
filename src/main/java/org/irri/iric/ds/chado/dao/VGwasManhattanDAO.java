package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VGwasManhattan;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VGwasManhattan entities.
 * 
 */
public interface VGwasManhattanDAO extends JpaDao<VGwasManhattan>, ManhattanPlotDAO {

	/**
	 * JPQL Query - findVGwasManhattanByPrimaryKey
	 *
	 */
	public VGwasManhattan findVGwasManhattanByPrimaryKey(BigDecimal gwasMarkerId) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByPrimaryKey
	 *
	 */
	public VGwasManhattan findVGwasManhattanByPrimaryKey(BigDecimal gwasMarkerId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulationContaining
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulationContaining(String subpopulation)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulationContaining
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulationContaining(String subpopulation, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByTrait
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByTrait(String trait) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByTrait
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByTrait(String trait, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByChromosome
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByChromosome(Long chromosome) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByChromosome
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByChromosome(Long chromosome, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByPosition
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByPosition(BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByPosition
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByTraitContaining
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByTraitContaining(String trait_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByTraitContaining
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByTraitContaining(String trait_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulation
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulation(String subpopulation_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulation
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulation(String subpopulation_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByGwasMarkerId
	 *
	 */
	public VGwasManhattan findVGwasManhattanByGwasMarkerId(BigDecimal gwasMarkerId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByGwasMarkerId
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByGwasRunId(BigDecimal gwasRunId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByGwasMarkerId
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByGwasRunId(BigDecimal gwasRunId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByGwasMarkerId
	 *
	 */
	public VGwasManhattan findVGwasManhattanByGwasMarkerId(BigDecimal gwasMarkerId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByMinusLogp
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByMinusLogp(java.math.BigDecimal minusLogp) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByMinusLogp
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByMinusLogp(BigDecimal minusLogp, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVGwasManhattans
	 *
	 */
	public Set<VGwasManhattan> findAllVGwasManhattans() throws DataAccessException;

	/**
	 * JPQL Query - findAllVGwasManhattans
	 *
	 */
	public Set<VGwasManhattan> findAllVGwasManhattans(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByTraitId
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByTraitId(java.math.BigDecimal traitId) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanByTraitId
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByTraitId(BigDecimal traitId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulationId
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulationId(java.math.BigDecimal subpopulationId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulationId
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulationId(BigDecimal subpopulationId, int startResult,
			int maxRows) throws DataAccessException;

}