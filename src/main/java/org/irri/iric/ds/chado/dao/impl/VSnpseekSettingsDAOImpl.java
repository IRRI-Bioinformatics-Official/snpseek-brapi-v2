
package org.irri.iric.ds.chado.dao.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VSnpseekSettingsDAO;
import org.irri.iric.ds.chado.domain.model.VSnpseekSettings;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpseekSettings entities.
 * 
 */
@Repository("VSnpseekSettingsDAO")

@Transactional
public class VSnpseekSettingsDAOImpl extends AbstractJpaDao<VSnpseekSettings> implements VSnpseekSettingsDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VSnpseekSettings.class }));

	/**
	 * EntityManager injected by Spring for persistence unit snpseekv3
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpseekSettingsDAOImpl
	 *
	 */
	public VSnpseekSettingsDAOImpl() {
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
	 * JPQL Query - findAllVSnpseekSettingss
	 *
	 */
	@Transactional
	public Set<VSnpseekSettings> findAllVSnpseekSettingss() throws DataAccessException {

		return findAllVSnpseekSettingss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpseekSettingss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpseekSettings> findAllVSnpseekSettingss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpseekSettingss", startResult, maxRows);
		return new LinkedHashSet<VSnpseekSettings>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByValueContaining
	 *
	 */
	@Transactional
	public Set<VSnpseekSettings> findVSnpseekSettingsByValueContaining(String value) throws DataAccessException {

		return findVSnpseekSettingsByValueContaining(value, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpseekSettings> findVSnpseekSettingsByValueContaining(String value, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpseekSettingsByValueContaining", startResult, maxRows, value);
		return new LinkedHashSet<VSnpseekSettings>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByNameContaining
	 *
	 */
	@Transactional
	public Set<VSnpseekSettings> findVSnpseekSettingsByNameContaining(String name) throws DataAccessException {

		return findVSnpseekSettingsByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpseekSettings> findVSnpseekSettingsByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpseekSettingsByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VSnpseekSettings>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpseekSettings findVSnpseekSettingsByPrimaryKey(String name) throws DataAccessException {

		return findVSnpseekSettingsByPrimaryKey(name, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpseekSettings findVSnpseekSettingsByPrimaryKey(String name, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpseekSettingsByPrimaryKey", startResult, maxRows, name);
			return (VSnpseekSettings) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByName
	 *
	 */
	@Transactional
	public VSnpseekSettings findVSnpseekSettingsByName(String name) throws DataAccessException {

		return findVSnpseekSettingsByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByName
	 *
	 */

	@Transactional
	public VSnpseekSettings findVSnpseekSettingsByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpseekSettingsByName", startResult, maxRows, name);
			return (VSnpseekSettings) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByValue
	 *
	 */
	@Transactional
	public Set<VSnpseekSettings> findVSnpseekSettingsByValue(String value) throws DataAccessException {

		return findVSnpseekSettingsByValue(value, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpseekSettingsByValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpseekSettings> findVSnpseekSettingsByValue(String value, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpseekSettingsByValue", startResult, maxRows, value);
		return new LinkedHashSet<VSnpseekSettings>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpseekSettings entity) {
		return true;
	}
}
