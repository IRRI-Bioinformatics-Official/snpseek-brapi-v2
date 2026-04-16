package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VSnpSplicedonor;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpSplicedonor entities.
 * 
 */
public interface VSnpSplicedonorDAO extends JpaDao<VSnpSplicedonor>, SnpsSpliceDonorDAO {

	/**
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(java.math.BigDecimal organismId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Integer snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Integer snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Integer snpFeatureId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByChrContaining
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChrContaining(String chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByChrContaining
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChrContaining(String chr, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(String chr_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(String chr_1, int startResult, int maxRows)
			throws DataAccessException;

}