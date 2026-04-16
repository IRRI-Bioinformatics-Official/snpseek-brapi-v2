package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VSnpRefposindex;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpRefposindex entities.
 * 
 */
public interface VSnpRefposindexDAO extends JpaDao<VSnpRefposindex>, SnpsAllvarsPosDAO {

	/**
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */
	public Set<VSnpRefposindex> findAllVSnpRefposindexs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */
	public Set<VSnpRefposindex> findAllVSnpRefposindexs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(BigDecimal typeId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(BigDecimal typeId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(java.math.BigDecimal chromosome)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(BigDecimal chromosome, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(java.math.BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(BigDecimal snpFeatureId_1, BigDecimal typeId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(BigDecimal snpFeatureId_1, BigDecimal typeId_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(java.math.BigDecimal alleleIndex)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(BigDecimal alleleIndex, int startResult, int maxRows)
			throws DataAccessException;

}