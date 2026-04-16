package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VGwasRun;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VGwasRun entities.
 * 
 */
public interface VGwasRunDAO extends JpaDao<VGwasRun>, GWASRunDAO {

	/**
	 * JPQL Query - findVGwasRunBySubpopulationId
	 *
	 */
	public Set<VGwasRun> findVGwasRunBySubpopulationId(BigDecimal subpopulationId) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunBySubpopulationId
	 *
	 */
	public Set<VGwasRun> findVGwasRunBySubpopulationId(BigDecimal subpopulationId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByGwasRunId
	 *
	 */
	public VGwasRun findVGwasRunByGwasRunId(BigDecimal gwasRunId) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByGwasRunId
	 *
	 */
	public VGwasRun findVGwasRunByGwasRunId(BigDecimal gwasRunId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByRundate
	 *
	 */
	public Set<VGwasRun> findVGwasRunByRundate(java.util.Calendar rundate) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByRundate
	 *
	 */
	public Set<VGwasRun> findVGwasRunByRundate(Calendar rundate, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByQqplot
	 *
	 */
	public Set<VGwasRun> findVGwasRunByQqplot(String qqplot) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByQqplot
	 *
	 */
	public Set<VGwasRun> findVGwasRunByQqplot(String qqplot, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByTraitId
	 *
	 */
	public Set<VGwasRun> findVGwasRunByTraitId(java.math.BigDecimal traitId) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByTraitId
	 *
	 */
	public Set<VGwasRun> findVGwasRunByTraitId(BigDecimal traitId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunBySubpopulation
	 *
	 */
	public Set<VGwasRun> findVGwasRunBySubpopulation(String subpopulation) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunBySubpopulation
	 *
	 */
	public Set<VGwasRun> findVGwasRunBySubpopulation(String subpopulation, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByMethodContaining
	 *
	 */
	public Set<VGwasRun> findVGwasRunByMethodContaining(String method) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByMethodContaining
	 *
	 */
	public Set<VGwasRun> findVGwasRunByMethodContaining(String method, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByTraitContaining
	 *
	 */
	public Set<VGwasRun> findVGwasRunByTraitContaining(String trait) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByTraitContaining
	 *
	 */
	public Set<VGwasRun> findVGwasRunByTraitContaining(String trait, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunBySubpopulationContaining
	 *
	 */
	public Set<VGwasRun> findVGwasRunBySubpopulationContaining(String subpopulation_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunBySubpopulationContaining
	 *
	 */
	public Set<VGwasRun> findVGwasRunBySubpopulationContaining(String subpopulation_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByMethod
	 *
	 */
	public Set<VGwasRun> findVGwasRunByMethod(String method_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByMethod
	 *
	 */
	public Set<VGwasRun> findVGwasRunByMethod(String method_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVGwasRuns
	 *
	 */
	public Set<VGwasRun> findAllVGwasRuns() throws DataAccessException;

	/**
	 * JPQL Query - findAllVGwasRuns
	 *
	 */
	public Set<VGwasRun> findAllVGwasRuns(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByTrait
	 *
	 */
	public Set<VGwasRun> findVGwasRunByTrait(String trait_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByTrait
	 *
	 */
	public Set<VGwasRun> findVGwasRunByTrait(String trait_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByPrimaryKey
	 *
	 */
	public VGwasRun findVGwasRunByPrimaryKey(BigDecimal gwasRunId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByPrimaryKey
	 *
	 */
	public VGwasRun findVGwasRunByPrimaryKey(BigDecimal gwasRunId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByQqplotContaining
	 *
	 */
	public Set<VGwasRun> findVGwasRunByQqplotContaining(String qqplot_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasRunByQqplotContaining
	 *
	 */
	public Set<VGwasRun> findVGwasRunByQqplotContaining(String qqplot_1, int startResult, int maxRows)
			throws DataAccessException;

}