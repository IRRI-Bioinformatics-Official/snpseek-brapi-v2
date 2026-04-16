package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.dao.LocusPromoterDAO;
import org.irri.iric.ds.chado.domain.model.VLocusPromoter;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusPromoter entities.
 * 
 */
public interface VLocusPromoterDAO extends JpaDao<VLocusPromoter>, LocusPromoterDAO {

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureNameContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureNameContaining(String pfeatureName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureNameContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureNameContaining(String pfeatureName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByContigId
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByContigId(BigDecimal contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByContigId
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByContigId(BigDecimal contigId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusPromoters
	 *
	 */
	public Set<VLocusPromoter> findAllVLocusPromoters() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusPromoters
	 *
	 */
	public Set<VLocusPromoter> findAllVLocusPromoters(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByCommonNameContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByCommonNameContaining(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByCommonNameContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByCommonNameContaining(String commonName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByNameContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByNameContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByNotesContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByNotesContaining(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByNotesContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByNotesContaining(String notes, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureName
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureName(String pfeatureName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureName
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureName(String pfeatureName_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureTypeContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureTypeContaining(String pfeatureType)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureTypeContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureTypeContaining(String pfeatureType, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureEnd
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureEnd(Integer pfeatureEnd) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureEnd
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureEnd(Integer pfeatureEnd, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByNotes
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByNotes(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByNotes
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByNotes(String notes_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByGeneOverlaps
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByGeneOverlaps(String geneOverlaps) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByGeneOverlaps
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByGeneOverlaps(String geneOverlaps, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByOrganismId
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByOrganismId(BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByOrganismId
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByFmax
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByFmax(java.math.BigDecimal fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByFmax
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByFmax(BigDecimal fmax, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByName
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByName
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureChrContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureChrContaining(String pfeatureChr) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureChrContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureChrContaining(String pfeatureChr, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureStart
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureStart(Integer pfeatureStart) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureStart
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureStart(Integer pfeatureStart, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByFeatureId
	 *
	 */
	public VLocusPromoter findVLocusPromoterByFeatureId(BigDecimal featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByFeatureId
	 *
	 */
	public VLocusPromoter findVLocusPromoterByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByContigName
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByContigName(String contigName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByContigName
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByContigName(String contigName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByCommonName
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByCommonName(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByCommonName
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByCommonName(String commonName_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByFmin
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByFmin(java.math.BigDecimal fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByFmin
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByFmin(BigDecimal fmin, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByStrand
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByStrand(java.math.BigDecimal strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByStrand
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByStrand(BigDecimal strand, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPrimaryKey
	 *
	 */
	public VLocusPromoter findVLocusPromoterByPrimaryKey(BigDecimal featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPrimaryKey
	 *
	 */
	public VLocusPromoter findVLocusPromoterByPrimaryKey(BigDecimal featureId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByContigNameContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByContigNameContaining(String contigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByContigNameContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByContigNameContaining(String contigName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByGeneOverlapsContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByGeneOverlapsContaining(String geneOverlaps_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByGeneOverlapsContaining
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByGeneOverlapsContaining(String geneOverlaps_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureDb
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureDb(BigDecimal pfeatureDb) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureDb
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureDb(BigDecimal pfeatureDb, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureChr
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureChr(String pfeatureChr_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureChr
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureChr(String pfeatureChr_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureId
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureId(BigDecimal pfeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureId
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureId(BigDecimal pfeatureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureType
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureType(String pfeatureType_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureType
	 *
	 */
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureType(String pfeatureType_1, int startResult, int maxRows)
			throws DataAccessException;

}