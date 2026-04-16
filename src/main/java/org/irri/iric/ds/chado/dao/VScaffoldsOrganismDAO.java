package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VScaffoldsOrganism;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VScaffoldsOrganism entities.
 * 
 */
public interface VScaffoldsOrganismDAO extends JpaDao<VScaffoldsOrganism>, ScaffoldDAO {

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquename
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquename(String uniquename) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquename
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquename(String uniquename, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonName
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonName(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonName
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonName(String commonName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByOrganismId
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByOrganismId(java.math.BigDecimal organismId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByOrganismId
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquenameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquenameContaining(String uniquename_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquenameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquenameContaining(String uniquename_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByFeatureId
	 *
	 */
	public VScaffoldsOrganism findVScaffoldsOrganismByFeatureId(BigDecimal featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByFeatureId
	 *
	 */
	public VScaffoldsOrganism findVScaffoldsOrganismByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeContaining(String type) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeContaining(String type, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByType
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByType(String type_1) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByType
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByType(String type_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByPrimaryKey
	 *
	 */
	public VScaffoldsOrganism findVScaffoldsOrganismByPrimaryKey(BigDecimal featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByPrimaryKey
	 *
	 */
	public VScaffoldsOrganism findVScaffoldsOrganismByPrimaryKey(BigDecimal featureId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByNameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByNameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVScaffoldsOrganisms
	 *
	 */
	public Set<VScaffoldsOrganism> findAllVScaffoldsOrganisms() throws DataAccessException;

	/**
	 * JPQL Query - findAllVScaffoldsOrganisms
	 *
	 */
	public Set<VScaffoldsOrganism> findAllVScaffoldsOrganisms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonNameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonNameContaining(String commonName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonNameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonNameContaining(String commonName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeId
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeId(java.math.BigDecimal typeId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeId
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeId(BigDecimal typeId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByName
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByName
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismBySeqlen
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismBySeqlen(java.math.BigDecimal seqlen)
			throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismBySeqlen
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismBySeqlen(BigDecimal seqlen, int startResult, int maxRows)
			throws DataAccessException;

}