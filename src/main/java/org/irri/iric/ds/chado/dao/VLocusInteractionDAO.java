package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VLocusInteraction;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusInteraction entities.
 * 
 */
public interface VLocusInteractionDAO extends JpaDao<VLocusInteraction>, FeatureInteractionDAO {

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureId
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureId(BigDecimal qfeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureId
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureId(BigDecimal qfeatureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByOrganismId
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByOrganismId(BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByOrganismId
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByNameContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByNameContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByFmax
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByFmax(Integer fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByFmax
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByFmax(Integer fmax, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByNotesContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByNotesContaining(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByNotesContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByNotesContaining(String notes, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByFmin
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByFmin(Integer fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByFmin
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByFmin(Integer fmin, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByNotes
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByNotes(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByNotes
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByNotes(String notes_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureName
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureName(String qfeatureName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureName
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureName(String qfeatureName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByPrimaryKey
	 *
	 */
	public VLocusInteraction findVLocusInteractionByPrimaryKey(BigDecimal featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByPrimaryKey
	 *
	 */
	public VLocusInteraction findVLocusInteractionByPrimaryKey(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByCommonName
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByCommonName(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByCommonName
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByCommonName(String commonName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByContigId
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByContigId(BigDecimal contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByContigId
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByContigId(BigDecimal contigId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByContigName
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByContigName(String contigName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByContigName
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByContigName(String contigName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByContigNameContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByContigNameContaining(String contigName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByContigNameContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByContigNameContaining(String contigName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusInteractions
	 *
	 */
	public Set<VLocusInteraction> findAllVLocusInteractions() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusInteractions
	 *
	 */
	public Set<VLocusInteraction> findAllVLocusInteractions(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByCommonNameContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByCommonNameContaining(String commonName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByCommonNameContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByCommonNameContaining(String commonName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByStrand
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByStrand(Integer strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByStrand
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByStrand(Integer strand, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByName
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByName
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByFeatureId
	 *
	 */
	public VLocusInteraction findVLocusInteractionByFeatureId(BigDecimal featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByFeatureId
	 *
	 */
	public VLocusInteraction findVLocusInteractionByFeatureId(BigDecimal featureId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByScore
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByScore(java.math.BigDecimal score) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByScore
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByScore(BigDecimal score, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureNameContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureNameContaining(String qfeatureName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureNameContaining
	 *
	 */
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureNameContaining(String qfeatureName_1, int startResult,
			int maxRows) throws DataAccessException;

}