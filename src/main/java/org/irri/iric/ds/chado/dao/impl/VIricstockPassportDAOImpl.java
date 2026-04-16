package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VIricstockPassportDAO;
import org.irri.iric.ds.chado.domain.model.VIricstockPassport;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockPassport entities.
 * 
 */
@Repository("IricstockPassportDAO")
@Transactional
public class VIricstockPassportDAOImpl extends AbstractJpaDao<VIricstockPassport> implements VIricstockPassportDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VIricstockPassport.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockPassportDAOImpl
	 *
	 */
	public VIricstockPassportDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findVIricstockPassportByName
	 *
	 */
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByName(String name) throws DataAccessException {

		return findVIricstockPassportByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportByName", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportByDefinition
	 *
	 */
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByDefinition(String definition) throws DataAccessException {

		return findVIricstockPassportByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportByDefinition", startResult, maxRows, definition);
		return new LinkedHashSet<VIricstockPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportByValue
	 *
	 */
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByValue(String value) throws DataAccessException {

		return findVIricstockPassportByValue(value, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportByValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByValue(String value, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportByValue", startResult, maxRows, value);
		return new LinkedHashSet<VIricstockPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportByIricStockpropId
	 *
	 */
	@Transactional
	public VIricstockPassport findVIricstockPassportByIricStockpropId(Integer iricStockpropId)
			throws DataAccessException {

		return findVIricstockPassportByIricStockpropId(iricStockpropId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportByIricStockpropId
	 *
	 */

	@Transactional
	public VIricstockPassport findVIricstockPassportByIricStockpropId(Integer iricStockpropId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPassportByIricStockpropId", startResult, maxRows,
					iricStockpropId);
			return (VIricstockPassport) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPassportByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockPassport findVIricstockPassportByPrimaryKey(BigDecimal iricStockpropId)
			throws DataAccessException {

		return findVIricstockPassportByPrimaryKey(iricStockpropId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockPassport findVIricstockPassportByPrimaryKey(BigDecimal iricStockpropId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPassportByPrimaryKey", startResult, maxRows, iricStockpropId);
			return (VIricstockPassport) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPassportByIricStockId
	 *
	 */
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByIricStockId(java.math.BigDecimal iricStockId, String dataset)
			throws DataAccessException {

		return findVIricstockPassportByIricStockId(iricStockId, dataset, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportByIricStockId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByIricStockId(java.math.BigDecimal iricStockId, String dataset,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportByIricStockId", startResult, maxRows, iricStockId);
		return new LinkedHashSet<VIricstockPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportByNameContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByNameContaining(String name) throws DataAccessException {

		return findVIricstockPassportByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByDefinitionContaining(String definition)
			throws DataAccessException {

		return findVIricstockPassportByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByDefinitionContaining(String definition, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportByDefinitionContaining", startResult, maxRows,
				definition);
		return new LinkedHashSet<VIricstockPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportByValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByValueContaining(String value) throws DataAccessException {

		return findVIricstockPassportByValueContaining(value, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportByValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassport> findVIricstockPassportByValueContaining(String value, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportByValueContaining", startResult, maxRows, value);
		return new LinkedHashSet<VIricstockPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstockPassports
	 *
	 */
	@Transactional
	public Set<VIricstockPassport> findAllVIricstockPassports() throws DataAccessException {

		return findAllVIricstockPassports(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockPassports
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassport> findAllVIricstockPassports(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockPassports", startResult, maxRows);
		return new LinkedHashSet<VIricstockPassport>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockPassport entity) {
		return true;
	}

	@Override
	public Set getPassportByStockId(BigDecimal id) {
		
		// return null;
		return findVIricstockPassportByIricStockId(id);
	}
	@Override
	public Set getPassportByAccession(String acc) {
		
		// return null;
		return null;
	}

	@Override
	public Set<VIricstockPassport> findVIricstockPassportByIricStockId(BigDecimal iricStockId)
			throws DataAccessException {
		
		return findVIricstockPassportByIricStockId(iricStockId, "");
	}

	@Override
	public Set<VIricstockPassport> findVIricstockPassportByIricStockId(BigDecimal iricStockId, int startResult,
			int maxRows) throws DataAccessException {
		return null;
	}

	// @Override
	// public Set findIricstockPassportByIricStockId(BigDecimal id) {
	// 
	// return null;
	// }

	// @Override
	// public Set<VIricstockPassport> findVIricstockPassportByIricStockId(
	// BigDecimal iricStockId) throws DataAccessException {
	// 
	// return null;
	// }

}
