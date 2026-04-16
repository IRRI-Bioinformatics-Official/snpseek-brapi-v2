package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VCvPassportDAO;
import org.irri.iric.ds.chado.domain.model.VCvPassport;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VCvPassport entities.
 * 
 */
@Repository("VCvPassportDAO")
@Transactional
public class VCvPassportDAOImpl extends AbstractJpaDao<VCvPassport> implements VCvPassportDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VCvPassport.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VCvPassportDAOImpl
	 *
	 */
	public VCvPassportDAOImpl() {
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
	 * JPQL Query - findVCvPassportByPrimaryKey
	 *
	 */
	@Transactional
	public VCvPassport findVCvPassportByPrimaryKey(Integer cvTermId) throws DataAccessException {

		return findVCvPassportByPrimaryKey(cvTermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByPrimaryKey
	 *
	 */

	@Transactional
	public VCvPassport findVCvPassportByPrimaryKey(Integer cvTermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPassportByPrimaryKey", startResult, maxRows, cvTermId);
			return (VCvPassport) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPassportByDefinition
	 *
	 */
	@Transactional
	public Set<VCvPassport> findVCvPassportByDefinition(String definition) throws DataAccessException {

		return findVCvPassportByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPassport> findVCvPassportByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPassportByDefinition", startResult, maxRows, definition);
		return new LinkedHashSet<VCvPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPassportByNameContaining
	 *
	 */
	@Transactional
	public Set<VCvPassport> findVCvPassportByNameContaining(String name) throws DataAccessException {

		return findVCvPassportByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPassport> findVCvPassportByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPassportByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VCvPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPassportByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VCvPassport> findVCvPassportByDefinitionContaining(String definition) throws DataAccessException {

		return findVCvPassportByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPassport> findVCvPassportByDefinitionContaining(String definition, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPassportByDefinitionContaining", startResult, maxRows, definition);
		return new LinkedHashSet<VCvPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVCvPassports
	 *
	 */
	@Transactional
	public List<VCvPassport> findAllVCvPassports() throws DataAccessException {

		return findAllVCvPassports(-1, -1);
	}

	/**
	 * JPQL Query - findAllVCvPassports
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public List<VCvPassport> findAllVCvPassports(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVCvPassports", startResult, maxRows);

		return query.getResultList();
	}

	/**
	 * JPQL Query - findVCvPassportByName
	 *
	 */
	@Transactional
	public Set<VCvPassport> findVCvPassportByName(String name) throws DataAccessException {

		return findVCvPassportByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPassport> findVCvPassportByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPassportByName", startResult, maxRows, name);
		return new LinkedHashSet<VCvPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPassportByCvTermId
	 *
	 */
	@Transactional
	public VCvPassport findVCvPassportByCvTermId(Integer cvTermId) throws DataAccessException {

		return findVCvPassportByCvTermId(cvTermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByCvTermId
	 *
	 */

	@Transactional
	public VCvPassport findVCvPassportByCvTermId(Integer cvTermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPassportByCvTermId", startResult, maxRows, cvTermId);
			return (VCvPassport) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VCvPassport entity) {
		return true;
	}

	@Override
	public List getAllTerms() {

		return this.findAllVCvPassports();
	}
	//
	// @Override
	// public List getAllTerms(String organism) {
	//
	// return null;
	// }
	//
	// @Override
	// public List getAllTerms(String cv, String organism) {
	//
	// return null;
	// }

	@Override
	public List getAllTerms(BigDecimal cvByName, BigDecimal organismByName) {
		return null;
	}

}
