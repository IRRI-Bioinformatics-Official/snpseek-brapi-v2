package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VLocusSynonymsDAO;
import org.irri.iric.ds.chado.domain.model.VLocusSynonyms;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusSynonyms entities.
 * 
 */
@Repository("VLocusSynonymsDAO")
@Transactional
public class VLocusSynonymsDAOImpl extends AbstractJpaDao<VLocusSynonyms> implements VLocusSynonymsDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VLocusSynonyms.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusSynonymsDAOImpl
	 *
	 */
	public VLocusSynonymsDAOImpl() {
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
	 * JPQL Query - findVLocusSynonymsByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsByOrganismId(BigDecimal organismId) throws DataAccessException {

		return findVLocusSynonymsByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusSynonymsByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusSynonymsByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusSynonyms>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusSynonymsBySynonymContaining
	 *
	 */
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsBySynonymContaining(String synonym) throws DataAccessException {

		return findVLocusSynonymsBySynonymContaining(synonym, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusSynonymsBySynonymContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsBySynonymContaining(String synonym, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusSynonymsBySynonymContaining", startResult, maxRows, synonym);
		return new LinkedHashSet<VLocusSynonyms>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusSynonymss
	 *
	 */
	@Transactional
	public Set<VLocusSynonyms> findAllVLocusSynonymss() throws DataAccessException {

		return findAllVLocusSynonymss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusSynonymss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusSynonyms> findAllVLocusSynonymss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusSynonymss", startResult, maxRows);
		return new LinkedHashSet<VLocusSynonyms>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusSynonymsBySynonymId
	 *
	 */
	@Transactional
	public VLocusSynonyms findVLocusSynonymsBySynonymId(BigDecimal synonymId) throws DataAccessException {

		return findVLocusSynonymsBySynonymId(synonymId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusSynonymsBySynonymId
	 *
	 */

	@Transactional
	public VLocusSynonyms findVLocusSynonymsBySynonymId(BigDecimal synonymId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusSynonymsBySynonymId", startResult, maxRows, synonymId);
			return (VLocusSynonyms) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusSynonymsBySynonym
	 *
	 */
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsBySynonym(String synonym) throws DataAccessException {

		return findVLocusSynonymsBySynonym(synonym, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusSynonymsBySynonym
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsBySynonym(String synonym, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusSynonymsBySynonym", startResult, maxRows, synonym);
		return new LinkedHashSet<VLocusSynonyms>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusSynonymsByFeatureId
	 *
	 */
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsByFeatureId(BigDecimal featureId) throws DataAccessException {

		return findVLocusSynonymsByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusSynonymsByFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusSynonymsByFeatureId", startResult, maxRows, featureId);
		return new LinkedHashSet<VLocusSynonyms>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusSynonymsByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusSynonyms findVLocusSynonymsByPrimaryKey(BigDecimal synonymId) throws DataAccessException {

		return findVLocusSynonymsByPrimaryKey(synonymId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusSynonymsByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusSynonyms findVLocusSynonymsByPrimaryKey(BigDecimal synonymId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusSynonymsByPrimaryKey", startResult, maxRows, synonymId);
			return (VLocusSynonyms) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusSynonymsByLocus
	 *
	 */
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsByLocus(String locus) throws DataAccessException {

		return findVLocusSynonymsByLocus(locus, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusSynonymsByLocus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsByLocus(String locus, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusSynonymsByLocus", startResult, maxRows, locus);
		return new LinkedHashSet<VLocusSynonyms>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusSynonymsByLocusContaining
	 *
	 */
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsByLocusContaining(String locus) throws DataAccessException {

		return findVLocusSynonymsByLocusContaining(locus, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusSynonymsByLocusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusSynonyms> findVLocusSynonymsByLocusContaining(String locus, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusSynonymsByLocusContaining", startResult, maxRows, locus);
		return new LinkedHashSet<VLocusSynonyms>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusSynonyms entity) {
		return true;
	}

	@Override
	public Set<String> getSynonyms(String name) {
		
		Set names = new LinkedHashSet();
		Iterator<VLocusSynonyms> itSyn = findVLocusSynonymsByLocus(name).iterator();
		while (itSyn.hasNext()) {
			names.add(itSyn.next());
		}
		return names;
	}

	@Override
	public Set<String> getSynonyms(BigDecimal feature_id) {
		
		Set names = new LinkedHashSet();
		Iterator<VLocusSynonyms> itSyn = findVLocusSynonymsByFeatureId(feature_id).iterator();
		while (itSyn.hasNext()) {
			names.add(itSyn.next());
		}
		return names;
	}

}
