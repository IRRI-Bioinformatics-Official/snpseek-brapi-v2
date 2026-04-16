package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VLocusInteractionDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VLocusInteraction;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusInteraction entities.
 * 
 */
@Repository("VLocusInteractionDAO")
// @Repository("Ricenetv2InteractionDAO")
@Transactional
public class VLocusInteractionDAOImpl extends AbstractJpaDao<VLocusInteraction> implements VLocusInteractionDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VLocusInteraction.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusInteractionDAOImpl
	 *
	 */
	public VLocusInteractionDAOImpl() {
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
	 * JPQL Query - findVLocusInteractionByQfeatureId
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureId(BigDecimal qfeatureId) throws DataAccessException {

		return findVLocusInteractionByQfeatureId(qfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureId(BigDecimal qfeatureId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByQfeatureId", startResult, maxRows, qfeatureId);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByOrganismId(BigDecimal organismId) throws DataAccessException {

		return findVLocusInteractionByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByNameContaining(String name) throws DataAccessException {

		return findVLocusInteractionByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByFmax
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByFmax(Integer fmax) throws DataAccessException {

		return findVLocusInteractionByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByFmax(Integer fmax, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByNotesContaining(String notes) throws DataAccessException {

		return findVLocusInteractionByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByNotesContaining(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByFmin
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByFmin(Integer fmin) throws DataAccessException {

		return findVLocusInteractionByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByFmin(Integer fmin, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByNotes
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByNotes(String notes) throws DataAccessException {

		return findVLocusInteractionByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureName
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureName(String qfeatureName) throws DataAccessException {

		return findVLocusInteractionByQfeatureName(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureName(String qfeatureName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByQfeatureName", startResult, maxRows, qfeatureName);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusInteraction findVLocusInteractionByPrimaryKey(BigDecimal featureId) throws DataAccessException {

		return findVLocusInteractionByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusInteraction findVLocusInteractionByPrimaryKey(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusInteractionByPrimaryKey", startResult, maxRows, featureId);
			return (VLocusInteraction) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusInteractionByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByCommonName(String commonName) throws DataAccessException {

		return findVLocusInteractionByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByCommonName(String commonName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByContigId
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByContigId(BigDecimal contigId) throws DataAccessException {

		return findVLocusInteractionByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByContigId(BigDecimal contigId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByContigName
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByContigName(String contigName) throws DataAccessException {

		return findVLocusInteractionByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByContigName(String contigName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByContigNameContaining(String contigName)
			throws DataAccessException {

		return findVLocusInteractionByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByContigNameContaining(String contigName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByContigNameContaining", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusInteractions
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findAllVLocusInteractions() throws DataAccessException {

		return findAllVLocusInteractions(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusInteractions
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findAllVLocusInteractions(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusInteractions", startResult, maxRows);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByCommonNameContaining(String commonName)
			throws DataAccessException {

		return findVLocusInteractionByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByStrand
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByStrand(Integer strand) throws DataAccessException {

		return findVLocusInteractionByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByStrand(Integer strand, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByName
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByName(String name) throws DataAccessException {

		return findVLocusInteractionByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByFeatureId
	 *
	 */
	@Transactional
	public VLocusInteraction findVLocusInteractionByFeatureId(BigDecimal featureId) throws DataAccessException {

		return findVLocusInteractionByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByFeatureId
	 *
	 */

	@Transactional
	public VLocusInteraction findVLocusInteractionByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusInteractionByFeatureId", startResult, maxRows, featureId);
			return (VLocusInteraction) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusInteractionByScore
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByScore(java.math.BigDecimal score) throws DataAccessException {

		return findVLocusInteractionByScore(score, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByScore(java.math.BigDecimal score, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByScore", startResult, maxRows, score);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureNameContaining(String qfeatureName)
			throws DataAccessException {

		return findVLocusInteractionByQfeatureNameContaining(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusInteractionByQfeatureNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusInteraction> findVLocusInteractionByQfeatureNameContaining(String qfeatureName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusInteractionByQfeatureNameContaining", startResult, maxRows,
				qfeatureName);
		return new LinkedHashSet<VLocusInteraction>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusInteraction entity) {
		return true;
	}

	@Override
	public List<Locus> getInteractingFeatures(Collection<Locus> colLocus, int type, Integer max) {
		
		Set locisId = new TreeSet();
		Iterator<Locus> itLoc = colLocus.iterator();
		while (itLoc.hasNext())
			locisId.add(itLoc.next().getFeatureId());
		Query query = createNamedQuery("findVLocusInteractionByQfeatureIdIn", -1, -1, locisId);
		if (max != null)
			query.setMaxResults(max);
		return query.getResultList();
	}

}
