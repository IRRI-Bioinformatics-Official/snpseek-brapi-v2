package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VGwasManhattanBasic;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VGwasManhattanBasic entities.
 * 
 */
public interface VGwasManhattanBasicDAO extends JpaDao<VGwasManhattanBasic>, ManhattanPlotDAO {

	/**
	 * JPQL Query - findAllVGwasManhattanBasics
	 *
	 */
	public Set<VGwasManhattanBasic> findAllVGwasManhattanBasics() throws DataAccessException;

	/**
	 * JPQL Query - findAllVGwasManhattanBasics
	 *
	 */
	public Set<VGwasManhattanBasic> findAllVGwasManhattanBasics(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByPrimaryKey
	 *
	 */
	public VGwasManhattanBasic findVGwasManhattanBasicByPrimaryKey(BigDecimal gwasMarkerId) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByPrimaryKey
	 *
	 */
	public VGwasManhattanBasic findVGwasManhattanBasicByPrimaryKey(BigDecimal gwasMarkerId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByMinusLogp
	 *
	 */
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByMinusLogp(java.math.BigDecimal minusLogp)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByMinusLogp
	 *
	 */
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByMinusLogp(BigDecimal minusLogp, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByGwasRunId
	 *
	 */
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByGwasRunId(java.math.BigDecimal gwasRunId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByGwasRunId
	 *
	 */
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByGwasRunId(BigDecimal gwasRunId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByGwasMarkerId
	 *
	 */
	public VGwasManhattanBasic findVGwasManhattanBasicByGwasMarkerId(BigDecimal gwasMarkerId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByGwasMarkerId
	 *
	 */
	public VGwasManhattanBasic findVGwasManhattanBasicByGwasMarkerId(BigDecimal gwasMarkerId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByPosition
	 *
	 */
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByPosition(BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByPosition
	 *
	 */
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByChromosome
	 *
	 */
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByChromosome(Long chromosome) throws DataAccessException;

	/**
	 * JPQL Query - findVGwasManhattanBasicByChromosome
	 *
	 */
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByChromosome(Long chromosome, int startResult, int maxRows)
			throws DataAccessException;

}