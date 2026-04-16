package org.irri.iric.ds.chado.dao;

import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VCvtermLocuscount;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VCvtermLocuscount entities.
 * 
 */
public interface VCvtermLocuscountDAO extends JpaDao<VCvtermLocuscount>, CvTermLocusCountDAO {

	/**
	 * JPQL Query - findVCvtermLocuscountByCvTermContaining
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvTermContaining(String cvTerm) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvTermContaining
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvTermContaining(String cvTerm, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvtermId
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvtermId(Integer cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvtermId
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvtermId(Integer cvtermId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvTerm
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvTerm(String cvTerm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvTerm
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvTerm(String cvTerm_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvName
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvName(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvName
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvName(String cvName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvtermLocuscounts
	 *
	 */
	public Set<VCvtermLocuscount> findAllVCvtermLocuscounts() throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvtermLocuscounts
	 *
	 */
	public Set<VCvtermLocuscount> findAllVCvtermLocuscounts(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByLocusCount
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByLocusCount(Integer locusCount) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByLocusCount
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByLocusCount(Integer locusCount, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvAccContaining
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvAccContaining(String cvAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvAccContaining
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvAccContaining(String cvAcc, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvNameContaining
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvNameContaining(String cvName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvNameContaining
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvNameContaining(String cvName_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByPrimaryKey
	 *
	 */
	public VCvtermLocuscount findVCvtermLocuscountByPrimaryKey(Integer organismId, Integer cvtermId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByPrimaryKey
	 *
	 */
	public VCvtermLocuscount findVCvtermLocuscountByPrimaryKey(Integer organismId, Integer cvtermId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvAcc
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvAcc(String cvAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByCvAcc
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvAcc(String cvAcc_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByOrganismId
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByOrganismId(Integer organismId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermLocuscountByOrganismId
	 *
	 */
	public Set<VCvtermLocuscount> findVCvtermLocuscountByOrganismId(Integer organismId_1, int startResult, int maxRows)
			throws DataAccessException;

}