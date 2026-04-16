package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VLocusIntxnRicenetv1;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusIntxnRicenetv1 entities.
 * 
 */
public interface VLocusIntxnRicenetv1DAO extends JpaDao<VLocusIntxnRicenetv1>, FeatureInteractionDAO {

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFmin
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByFmin(Integer fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFmin
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByFmin(Integer fmin, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFeatureId
	 *
	 */
	public VLocusIntxnRicenetv1 findVLocusIntxnRicenetv1ByFeatureId(BigDecimal featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFeatureId
	 *
	 */
	public VLocusIntxnRicenetv1 findVLocusIntxnRicenetv1ByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureId
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureId(BigDecimal qfeatureId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureId
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureId(BigDecimal qfeatureId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgRank(Integer ospgRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgRank(Integer ospgRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgScore(BigDecimal ospgScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgScore(BigDecimal ospgScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxRank(Integer oscxRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxRank(Integer oscxRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetScore(BigDecimal intnetScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetScore(BigDecimal intnetScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxScore(BigDecimal atcxScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxScore(BigDecimal atcxScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnRank(Integer osgnRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnRank(Integer osgnRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusIntxnRicenetv1s
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findAllVLocusIntxnRicenetv1s() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusIntxnRicenetv1s
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findAllVLocusIntxnRicenetv1s(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetRank(Integer intnetRank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetRank(Integer intnetRank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureName
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureName(String qfeatureName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureName
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureName(String qfeatureName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnDenserank(Integer osgnDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnDenserank(Integer osgnDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByCommonName
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByCommonName(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByCommonName
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByCommonName(String commonName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetDenserank(Integer intnetDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetDenserank(Integer intnetDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigName
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigName(String contigName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigName
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigName(String contigName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOrganismId
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOrganismId(BigDecimal organismId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOrganismId
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcScore(BigDecimal atdcScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcScore(BigDecimal atdcScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnScore(BigDecimal osgnScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnScore(BigDecimal osgnScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigId
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigId(BigDecimal contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigId
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigId(BigDecimal contigId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureNameContaining(String qfeatureName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureNameContaining(String qfeatureName_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxRank(Integer atcxRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxRank(Integer atcxRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxScore(BigDecimal oscxScore)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxScore
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxScore(BigDecimal oscxScore, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNotes
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNotes(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNotes
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNotesContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNotesContaining(String notes_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNotesContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNotesContaining(String notes_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByPrimaryKey
	 *
	 */
	public VLocusIntxnRicenetv1 findVLocusIntxnRicenetv1ByPrimaryKey(BigDecimal featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByPrimaryKey
	 *
	 */
	public VLocusIntxnRicenetv1 findVLocusIntxnRicenetv1ByPrimaryKey(BigDecimal featureId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgDenserank(Integer ospgDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgDenserank(Integer ospgDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxDenserank(Integer oscxDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxDenserank(Integer oscxDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigNameContaining(String contigName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigNameContaining(String contigName_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcRank(Integer atdcRank) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcRank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcRank(Integer atdcRank, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxDenserank(Integer atcxDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxDenserank(Integer atcxDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcDenserank(Integer atdcDenserank)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcDenserank
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcDenserank(Integer atdcDenserank, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByCommonNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByCommonNameContaining(String commonName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByCommonNameContaining
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByCommonNameContaining(String commonName_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByStrand
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByStrand(Integer strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByStrand
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByStrand(Integer strand, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFmax
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByFmax(Integer fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFmax
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByFmax(Integer fmax, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByName
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByName
	 *
	 */
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

}