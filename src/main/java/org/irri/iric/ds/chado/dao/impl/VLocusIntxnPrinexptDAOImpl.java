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

import org.irri.iric.ds.chado.dao.VLocusIntxnPrinexptDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VLocusIntxnPrinexpt;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusIntxnPrinexpt entities.
 * 
 */
@Repository("VLocusIntxnPrinexptDAO")
@Transactional
public class VLocusIntxnPrinexptDAOImpl extends AbstractJpaDao<VLocusIntxnPrinexpt> implements VLocusIntxnPrinexptDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VLocusIntxnPrinexpt.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusIntxnPrinexptDAOImpl
	 *
	 */
	public VLocusIntxnPrinexptDAOImpl() {
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
	 * JPQL Query - findVLocusIntxnPrinexptByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigNameContaining(String contigName)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigNameContaining(String contigName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByContigNameContaining", startResult, maxRows,
				contigName);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfRank(Integer rssmfRank) throws DataAccessException {

		return findVLocusIntxnPrinexptByRssmfRank(rssmfRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfRank(Integer rssmfRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByRssmfRank", startResult, maxRows, rssmfRank);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNotes
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNotes(String notes) throws DataAccessException {

		return findVLocusIntxnPrinexptByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByStrand
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByStrand(Integer strand) throws DataAccessException {

		return findVLocusIntxnPrinexptByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByStrand(Integer strand, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfScore(BigDecimal rssmfScore)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByRssmfScore(rssmfScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfScore(BigDecimal rssmfScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByRssmfScore", startResult, maxRows, rssmfScore);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusIntxnPrinexpts
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findAllVLocusIntxnPrinexpts() throws DataAccessException {

		return findAllVLocusIntxnPrinexpts(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusIntxnPrinexpts
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findAllVLocusIntxnPrinexpts(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusIntxnPrinexpts", startResult, maxRows);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusIntxnPrinexpt findVLocusIntxnPrinexptByPrimaryKey(BigDecimal featureId) throws DataAccessException {

		return findVLocusIntxnPrinexptByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusIntxnPrinexpt findVLocusIntxnPrinexptByPrimaryKey(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusIntxnPrinexptByPrimaryKey", startResult, maxRows, featureId);
			return (VLocusIntxnPrinexpt) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfDenserank(Integer rssmfDenserank)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByRssmfDenserank(rssmfDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssmfDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssmfDenserank(Integer rssmfDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByRssmfDenserank", startResult, maxRows, rssmfDenserank);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccDenserank(Integer pccDenserank)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByPccDenserank(pccDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccDenserank(Integer pccDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByPccDenserank", startResult, maxRows, pccDenserank);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccScore(BigDecimal pccScore) throws DataAccessException {

		return findVLocusIntxnPrinexptByPccScore(pccScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccScore(BigDecimal pccScore, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByPccScore", startResult, maxRows, pccScore);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByOrganismId(BigDecimal organismId)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureNameContaining(String qfeatureName)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByQfeatureNameContaining(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureNameContaining(String qfeatureName,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByQfeatureNameContaining", startResult, maxRows,
				qfeatureName);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpRank(Integer rssbpRank) throws DataAccessException {

		return findVLocusIntxnPrinexptByRssbpRank(rssbpRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpRank(Integer rssbpRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByRssbpRank", startResult, maxRows, rssbpRank);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccRank(Integer pccRank) throws DataAccessException {

		return findVLocusIntxnPrinexptByPccRank(pccRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByPccRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByPccRank(Integer pccRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByPccRank", startResult, maxRows, pccRank);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccRank(Integer rssccRank) throws DataAccessException {

		return findVLocusIntxnPrinexptByRssccRank(rssccRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccRank(Integer rssccRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByRssccRank", startResult, maxRows, rssccRank);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFmax
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByFmax(Integer fmax) throws DataAccessException {

		return findVLocusIntxnPrinexptByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByFmax(Integer fmax, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNameContaining(String name) throws DataAccessException {

		return findVLocusIntxnPrinexptByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByCommonNameContaining(String commonName)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByCommonNameContaining", startResult, maxRows,
				commonName);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByName(String name) throws DataAccessException {

		return findVLocusIntxnPrinexptByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccScore(BigDecimal rssccScore)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByRssccScore(rssccScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccScore(BigDecimal rssccScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByRssccScore", startResult, maxRows, rssccScore);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFeatureId
	 *
	 */
	@Transactional
	public VLocusIntxnPrinexpt findVLocusIntxnPrinexptByFeatureId(BigDecimal featureId) throws DataAccessException {

		return findVLocusIntxnPrinexptByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFeatureId
	 *
	 */

	@Transactional
	public VLocusIntxnPrinexpt findVLocusIntxnPrinexptByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusIntxnPrinexptByFeatureId", startResult, maxRows, featureId);
			return (VLocusIntxnPrinexpt) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureName(String qfeatureName)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByQfeatureName(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureName(String qfeatureName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByQfeatureName", startResult, maxRows, qfeatureName);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFmin
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByFmin(Integer fmin) throws DataAccessException {

		return findVLocusIntxnPrinexptByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByFmin(Integer fmin, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpScore(BigDecimal rssbpScore)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByRssbpScore(rssbpScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpScore(BigDecimal rssbpScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByRssbpScore", startResult, maxRows, rssbpScore);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigName(String contigName) throws DataAccessException {

		return findVLocusIntxnPrinexptByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigName(String contigName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureId(BigDecimal qfeatureId)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByQfeatureId(qfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByQfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByQfeatureId(BigDecimal qfeatureId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByQfeatureId", startResult, maxRows, qfeatureId);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByCommonName(String commonName) throws DataAccessException {

		return findVLocusIntxnPrinexptByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByCommonName(String commonName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpDenserank(Integer rssbpDenserank)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByRssbpDenserank(rssbpDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssbpDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssbpDenserank(Integer rssbpDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByRssbpDenserank", startResult, maxRows, rssbpDenserank);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNotesContaining(String notes) throws DataAccessException {

		return findVLocusIntxnPrinexptByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByNotesContaining(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccDenserank(Integer rssccDenserank)
			throws DataAccessException {

		return findVLocusIntxnPrinexptByRssccDenserank(rssccDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByRssccDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByRssccDenserank(Integer rssccDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByRssccDenserank", startResult, maxRows, rssccDenserank);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigId(BigDecimal contigId) throws DataAccessException {

		return findVLocusIntxnPrinexptByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinexptByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinexpt> findVLocusIntxnPrinexptByContigId(BigDecimal contigId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinexptByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusIntxnPrinexpt>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusIntxnPrinexpt entity) {
		return true;
	}

	@Override
	public List<Locus> getInteractingFeatures(Collection<Locus> colLocus, int type, Integer max) {

		Set locisId = new TreeSet();
		Iterator<Locus> itLoc = colLocus.iterator();
		while (itLoc.hasNext())
			locisId.add(itLoc.next().getFeatureId());
		Query query = createNamedQuery("findVLocusIntxnPrinexptByQfeatureIdIn", -1, -1, locisId);
		if (max != null)
			query.setMaxResults(max);
		return query.getResultList();
	}

}
