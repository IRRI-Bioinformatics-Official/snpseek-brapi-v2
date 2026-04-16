package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VLocusIntxnPrinexpt;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusIntxnPrinexpt entities.
 * 
 */
public interface VLocusIntxnPrinexptDAO extends JpaDao<VLocusIntxnPrinexpt>, FeatureInteractionDAO {

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigNameContaining(String contigName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigNameContaining(String contigName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfRank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfRank(Integer rssmfRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfRank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfRank(Integer rssmfRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNotes
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNotes(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNotes
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByStrand
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByStrand(Integer strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByStrand
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByStrand(Integer strand, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfScore
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfScore(BigDecimal rssmfScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfScore
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfScore(BigDecimal rssmfScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusIntxnPrinexpts
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findAllVLocusIntxnPrinexpts() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusIntxnPrinexpts
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findAllVLocusIntxnPrinexpts(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPrimaryKey
	 *
	 */
	public VLocusIntxnPrinexpt findVLocusIntxnPrinexptByPrimaryKey(BigDecimal featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPrimaryKey
	 *
	 */
	public VLocusIntxnPrinexpt findVLocusIntxnPrinexptByPrimaryKey(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfDenserank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfDenserank(Integer rssmfDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfDenserank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfDenserank(Integer rssmfDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccDenserank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccDenserank(Integer pccDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccDenserank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccDenserank(Integer pccDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccScore
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccScore(BigDecimal pccScore) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccScore
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccScore(BigDecimal pccScore, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByOrganismId
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByOrganismId(BigDecimal organismId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByOrganismId
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureNameContaining(String qfeatureName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureNameContaining(String qfeatureName,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpRank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpRank(Integer rssbpRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpRank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpRank(Integer rssbpRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccRank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccRank(Integer pccRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccRank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccRank(Integer pccRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccRank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccRank(Integer rssccRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccRank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccRank(Integer rssccRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFmax
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByFmax(Integer fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFmax
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByFmax(Integer fmax, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByCommonNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByCommonNameContaining(String commonName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByCommonNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByName
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByName
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccScore
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccScore(BigDecimal rssccScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccScore
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccScore(BigDecimal rssccScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFeatureId
	 *
	 */
	public VLocusIntxnPrinexpt findVLocusIntxnPrinexptByFeatureId(BigDecimal featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFeatureId
	 *
	 */
	public VLocusIntxnPrinexpt findVLocusIntxnPrinexptByFeatureId(BigDecimal featureId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureName
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureName(String qfeatureName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureName
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureName(String qfeatureName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFmin
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByFmin(Integer fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFmin
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByFmin(Integer fmin, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpScore
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpScore(BigDecimal rssbpScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpScore
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpScore(BigDecimal rssbpScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigName
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigName(String contigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigName
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigName(String contigName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureId
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureId(BigDecimal qfeatureId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureId
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureId(BigDecimal qfeatureId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByCommonName
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByCommonName(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByCommonName
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByCommonName(String commonName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpDenserank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpDenserank(Integer rssbpDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpDenserank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpDenserank(Integer rssbpDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNotesContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNotesContaining(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNotesContaining
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNotesContaining(String notes_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccDenserank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccDenserank(Integer rssccDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccDenserank
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccDenserank(Integer rssccDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigId
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigId(BigDecimal contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigId
	 *
	 */
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigId(BigDecimal contigId, int startResult, int maxRows)
			throws DataAccessException;

}