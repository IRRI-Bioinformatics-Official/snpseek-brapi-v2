package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VLocusIntxnPrinpred;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusIntxnPrinpred entities.
 * 
 */
public interface VLocusIntxnPrinpredDAO extends JpaDao<VLocusIntxnPrinpred>, FeatureInteractionDAO {

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByName
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByName
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigId
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigId(BigDecimal contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigId
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigId(BigDecimal contigId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFmax
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByFmax(Integer fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFmax
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByFmax(Integer fmax, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpDenserank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpDenserank(Integer rssbpDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpDenserank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpDenserank(Integer rssbpDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFmin
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByFmin(Integer fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFmin
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByFmin(Integer fmin, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccScore
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccScore(BigDecimal rssccScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccScore
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccScore(BigDecimal rssccScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfScore
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfScore(BigDecimal rssmfScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfScore
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfScore(BigDecimal rssmfScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFeatureId
	 *
	 */
	public VLocusIntxnPrinpred findVLocusIntxnPrinpredByFeatureId(BigDecimal featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFeatureId
	 *
	 */
	public VLocusIntxnPrinpred findVLocusIntxnPrinpredByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCommonNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCommonNameContaining(String commonName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCommonNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNotes
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNotes(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNotes
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccDenserank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccDenserank(Integer rssccDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccDenserank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccDenserank(Integer rssccDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpRank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpRank(Integer coexpRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpRank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpRank(Integer coexpRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpRank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpRank(Integer rssbpRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpRank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpRank(Integer rssbpRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNameContaining(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusIntxnPrinpreds
	 *
	 */
	public Set<VLocusIntxnPrinpred> findAllVLocusIntxnPrinpreds() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusIntxnPrinpreds
	 *
	 */
	public Set<VLocusIntxnPrinpred> findAllVLocusIntxnPrinpreds(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureNameContaining(String qfeatureName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureNameContaining(String qfeatureName,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByStrand
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByStrand(Integer strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByStrand
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByStrand(Integer strand, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigNameContaining(String contigName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigNameContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigNameContaining(String contigName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByOrganismId
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByOrganismId(BigDecimal organismId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByOrganismId
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpScore
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpScore(BigDecimal coexpScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpScore
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpScore(BigDecimal coexpScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpScore
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpScore(BigDecimal rssbpScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpScore
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpScore(BigDecimal rssbpScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByPrimaryKey
	 *
	 */
	public VLocusIntxnPrinpred findVLocusIntxnPrinpredByPrimaryKey(BigDecimal featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByPrimaryKey
	 *
	 */
	public VLocusIntxnPrinpred findVLocusIntxnPrinpredByPrimaryKey(BigDecimal featureId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNotesContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNotesContaining(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNotesContaining
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNotesContaining(String notes_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigName
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigName(String contigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigName
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigName(String contigName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureName
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureName(String qfeatureName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureName
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureName(String qfeatureName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureId
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureId(BigDecimal qfeatureId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureId
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureId(BigDecimal qfeatureId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfRank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfRank(Integer rssmfRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfRank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfRank(Integer rssmfRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCommonName
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCommonName(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCommonName
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCommonName(String commonName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccRank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccRank(Integer rssccRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccRank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccRank(Integer rssccRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpDenserank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpDenserank(Integer coexpDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpDenserank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpDenserank(Integer coexpDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfDenserank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfDenserank(Integer rssmfDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfDenserank
	 *
	 */
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfDenserank(Integer rssmfDenserank, int startResult,
			int maxRows) throws DataAccessException;

}