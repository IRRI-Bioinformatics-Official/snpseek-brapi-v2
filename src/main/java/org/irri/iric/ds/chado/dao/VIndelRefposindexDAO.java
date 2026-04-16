package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VIndelRefposindex;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage MvIndelRefposindex entities.
 * 
 */
public interface VIndelRefposindexDAO extends JpaDao<VIndelRefposindex>, IndelsAllvarsPosDAO {

	/**
	 * JPQL Query - findMvIndelRefposindexByPosition
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByPosition(BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByPosition
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByMaxInsertLen
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByMaxInsertLen(Integer maxInsertLen) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByMaxInsertLen
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByMaxInsertLen(Integer maxInsertLen, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllMvIndelRefposindexs
	 *
	 */
	public Set<VIndelRefposindex> findAllMvIndelRefposindexs() throws DataAccessException;

	/**
	 * JPQL Query - findAllMvIndelRefposindexs
	 *
	 */
	public Set<VIndelRefposindex> findAllMvIndelRefposindexs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByIndelFeatureId
	 *
	 */
	public VIndelRefposindex findMvIndelRefposindexByIndelFeatureId(BigDecimal indelFeatureId)
			throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByIndelFeatureId
	 *
	 */
	public VIndelRefposindex findMvIndelRefposindexByIndelFeatureId(BigDecimal indelFeatureId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByRefcall
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByRefcall(String refcall) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByRefcall
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByRefcall(String refcall, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByPrimaryKey
	 *
	 */
	public VIndelRefposindex findMvIndelRefposindexByPrimaryKey(BigDecimal indelFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByPrimaryKey
	 *
	 */
	public VIndelRefposindex findMvIndelRefposindexByPrimaryKey(BigDecimal indelFeatureId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByRefcallContaining
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByRefcallContaining(String refcall_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByRefcallContaining
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByRefcallContaining(String refcall_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByMaxDeleteLen
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByMaxDeleteLen(Integer maxDeleteLen) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByMaxDeleteLen
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByMaxDeleteLen(Integer maxDeleteLen, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByAltcall
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByAltcall(String altcall) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByAltcall
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByAltcall(String altcall, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByChromosome
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByChromosome(BigDecimal chromosome) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByChromosome
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByChromosome(BigDecimal chromosome, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByAlleleIndex
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByAlleleIndex(BigDecimal alleleIndex)
			throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByAlleleIndex
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByAlleleIndex(BigDecimal alleleIndex, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByTypeId
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByTypeId(BigDecimal typeId) throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByTypeId
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByTypeId(BigDecimal typeId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByAltcallContaining
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByAltcallContaining(String altcall_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findMvIndelRefposindexByAltcallContaining
	 *
	 */
	public Set<VIndelRefposindex> findMvIndelRefposindexByAltcallContaining(String altcall_1, int startResult,
			int maxRows) throws DataAccessException;

}