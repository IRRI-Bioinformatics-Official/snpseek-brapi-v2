package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VSnpNonsynallelePos;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpNonsynallelePos entities.
 * 
 */
public interface VSnpNonsynallelePosDAO extends JpaDao<VSnpNonsynallelePos>, SnpsNonsynAllvarsDAO {

	/**
	 * JPQL Query - findVSnpNonsynallelePosBySnpFeatureId
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosBySnpFeatureId(BigDecimal snpFeatureId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosBySnpFeatureId
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosBySnpFeatureId(BigDecimal snpFeatureId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByPrimaryKey
	 *
	 */
	public VSnpNonsynallelePos findVSnpNonsynallelePosByPrimaryKey(BigDecimal snpFeatureId_1, BigDecimal typeId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByPrimaryKey
	 *
	 */
	public VSnpNonsynallelePos findVSnpNonsynallelePosByPrimaryKey(BigDecimal snpFeatureId_1, BigDecimal typeId,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByPosition
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByPosition(BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByPosition
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpNonsynallelePoss
	 *
	 */
	public Set<VSnpNonsynallelePos> findAllVSnpNonsynallelePoss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpNonsynallelePoss
	 *
	 */
	public Set<VSnpNonsynallelePos> findAllVSnpNonsynallelePoss(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByTypeId
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByTypeId(BigDecimal typeId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByTypeId
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByTypeId(BigDecimal typeId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByAlleleNonsynContaining
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByAlleleNonsynContaining(String alleleNonsyn)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByAlleleNonsynContaining
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByAlleleNonsynContaining(String alleleNonsyn,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByAlleleNonsyn
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByAlleleNonsyn(String alleleNonsyn_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByAlleleNonsyn
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByAlleleNonsyn(String alleleNonsyn_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByChr
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByChr(BigDecimal chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynallelePosByChr
	 *
	 */
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByChr(BigDecimal chr, int startResult, int maxRows)
			throws DataAccessException;

}