package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import org.irri.iric.ds.chado.domain.Feature;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Feature entities.
 * 
 */
public interface FeatureDAO extends JpaDao<Feature>,  SequenceDAO {

	/**
	 * JPQL Query - findFeatureByName
	 *
	 */
	public Set<Feature> findFeatureByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByName
	 *
	 */
	public Set<Feature> findFeatureByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByNameContaining
	 *
	 */
	public Set<Feature> findFeatureByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByNameContaining
	 *
	 */
	public Set<Feature> findFeatureByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByIsObsolete
	 *
	 */
	public Set<Feature> findFeatureByIsObsolete(Integer isObsolete) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByIsObsolete
	 *
	 */
	public Set<Feature> findFeatureByIsObsolete(Integer isObsolete, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByFeatureId
	 *
	 */
	public Feature findFeatureByFeatureId(Integer featureId) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByFeatureId
	 *
	 */
	public Feature findFeatureByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByDbxrefId
	 *
	 */
	public Set<Feature> findFeatureByDbxrefId(java.math.BigDecimal dbxrefId) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByDbxrefId
	 *
	 */
	public Set<Feature> findFeatureByDbxrefId(BigDecimal dbxrefId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByUniquenameContaining
	 *
	 */
	public Set<Feature> findFeatureByUniquenameContaining(String uniquename) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByUniquenameContaining
	 *
	 */
	public Set<Feature> findFeatureByUniquenameContaining(String uniquename, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByMd5checksum
	 *
	 */
	public Set<Feature> findFeatureByMd5checksum(String md5checksum) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByMd5checksum
	 *
	 */
	public Set<Feature> findFeatureByMd5checksum(String md5checksum, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByTimelastmodified
	 *
	 */
	public Set<Feature> findFeatureByTimelastmodified(java.util.Calendar timelastmodified) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByTimelastmodified
	 *
	 */
	public Set<Feature> findFeatureByTimelastmodified(Calendar timelastmodified, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByTimeaccessioned
	 *
	 */
	public Set<Feature> findFeatureByTimeaccessioned(java.util.Calendar timeaccessioned) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByTimeaccessioned
	 *
	 */
	public Set<Feature> findFeatureByTimeaccessioned(Calendar timeaccessioned, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByTypeId
	 *
	 */
	public Set<Feature> findFeatureByTypeId(Integer typeId) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByTypeId
	 *
	 */
	public Set<Feature> findFeatureByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllFeatures
	 *
	 */
	public Set<Feature> findAllFeatures() throws DataAccessException;

	/**
	 * JPQL Query - findAllFeatures
	 *
	 */
	public Set<Feature> findAllFeatures(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByMd5checksumContaining
	 *
	 */
	public Set<Feature> findFeatureByMd5checksumContaining(String md5checksum_1) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByMd5checksumContaining
	 *
	 */
	public Set<Feature> findFeatureByMd5checksumContaining(String md5checksum_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByIsAnalysis
	 *
	 */
	public Set<Feature> findFeatureByIsAnalysis(Integer isAnalysis) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByIsAnalysis
	 *
	 */
	public Set<Feature> findFeatureByIsAnalysis(Integer isAnalysis, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByPrimaryKey
	 *
	 */
	public Feature findFeatureByPrimaryKey(Integer featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByPrimaryKey
	 *
	 */
	public Feature findFeatureByPrimaryKey(Integer featureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByUniquename
	 *
	 */
	public Set<Feature> findFeatureByUniquename(String uniquename_1) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByUniquename
	 *
	 */
	public Set<Feature> findFeatureByUniquename(String uniquename_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByOrganismId
	 *
	 */
	public Set<Feature> findFeatureByOrganismId(Integer organismId) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureByOrganismId
	 *
	 */
	public Set<Feature> findFeatureByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureBySeqlen
	 *
	 */
	public Set<Feature> findFeatureBySeqlen(java.math.BigDecimal seqlen) throws DataAccessException;

	/**
	 * JPQL Query - findFeatureBySeqlen
	 *
	 */
	public Set<Feature> findFeatureBySeqlen(BigDecimal seqlen, int startResult, int maxRows) throws DataAccessException;

	

}