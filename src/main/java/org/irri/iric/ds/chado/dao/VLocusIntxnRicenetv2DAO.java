package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VLocusIntxnRicenetv2;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusIntxnRicenetv2 entities.
 * 
 */
public interface VLocusIntxnRicenetv2DAO extends JpaDao<VLocusIntxnRicenetv2>, FeatureInteractionDAO {

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigNameContaining(String contigName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigNameContaining(String contigName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureId
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureId(Integer qfeatureId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureId
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureId(Integer qfeatureId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByCommonNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByCommonNameContaining(String commonName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByCommonNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFmax
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByFmax(java.math.BigDecimal fmax)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFmax
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByFmax(BigDecimal fmax, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByName
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByName
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByOrganismId
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByOrganismId(Integer organismId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByOrganismId
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByOrganismId(Integer organismId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFmin
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByFmin(java.math.BigDecimal fmin)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFmin
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByFmin(BigDecimal fmin, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByScore
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByScore(java.math.BigDecimal score)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByScore
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByScore(BigDecimal score, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNotes
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNotes(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNotes
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByStrand
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByStrand(java.math.BigDecimal strand)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByStrand
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByStrand(BigDecimal strand, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusIntxnRicenetv2s
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findAllVLocusIntxnRicenetv2s() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusIntxnRicenetv2s
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findAllVLocusIntxnRicenetv2s(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigName
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigName(String contigName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigName
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigName(String contigName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByCommonName
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByCommonName(String commonName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByCommonName
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByCommonName(String commonName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNotesContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNotesContaining(String notes_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNotesContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNotesContaining(String notes_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNameContaining(String name_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigId
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigId(Integer contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigId
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigId(Integer contigId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFeatureId
	 *
	 */
	public VLocusIntxnRicenetv2 findVLocusIntxnRicenetv2ByFeatureId(Integer featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFeatureId
	 *
	 */
	public VLocusIntxnRicenetv2 findVLocusIntxnRicenetv2ByFeatureId(Integer featureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureNameContaining(String qfeatureName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureNameContaining(String qfeatureName,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByPrimaryKey
	 *
	 */
	public VLocusIntxnRicenetv2 findVLocusIntxnRicenetv2ByPrimaryKey(Integer featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByPrimaryKey
	 *
	 */
	public VLocusIntxnRicenetv2 findVLocusIntxnRicenetv2ByPrimaryKey(Integer featureId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByRank
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByRank(Integer rank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByRank
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByRank(Integer rank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByDenserank(Integer denserank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByDenserank(Integer denserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureName
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureName(String qfeatureName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureName
	 *
	 */
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureName(String qfeatureName_1, int startResult,
			int maxRows) throws DataAccessException;

}