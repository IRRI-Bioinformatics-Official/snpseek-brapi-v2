package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VSnpSpliceacceptor;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpSpliceacceptor entities.
 * 
 */
// public interface VSnpSpliceacceptorDAO extends JpaDao<VSnpSpliceacceptor>,
// SnpsSpliceAcceptorDAO {
public interface VSnpSpliceacceptorDAO extends SnpsSpliceAcceptorDAO {

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Integer snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChrContaining
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChrContaining(String chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChrContaining
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChrContaining(String chr, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Integer snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Integer snpFeatureId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(java.math.BigDecimal organismId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(String chr_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(String chr_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors(int startResult, int maxRows) throws DataAccessException;

}