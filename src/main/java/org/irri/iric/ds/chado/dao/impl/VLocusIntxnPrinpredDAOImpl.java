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

import org.irri.iric.ds.chado.dao.VLocusIntxnPrinpredDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VLocusIntxnPrinpred;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusIntxnPrinpred entities.
 * 
 */
@Repository("VLocusIntxnPrinpredDAO")
@Transactional
public class VLocusIntxnPrinpredDAOImpl extends AbstractJpaDao<VLocusIntxnPrinpred> implements VLocusIntxnPrinpredDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VLocusIntxnPrinpred.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusIntxnPrinpredDAOImpl
	 *
	 */
	public VLocusIntxnPrinpredDAOImpl() {
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
	 * JPQL Query - findVLocusIntxnPrinpredByName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByName(String name) throws DataAccessException {

		return findVLocusIntxnPrinpredByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigId(BigDecimal contigId) throws DataAccessException {

		return findVLocusIntxnPrinpredByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigId(BigDecimal contigId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFmax
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByFmax(Integer fmax) throws DataAccessException {

		return findVLocusIntxnPrinpredByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByFmax(Integer fmax, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpDenserank(Integer rssbpDenserank)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByRssbpDenserank(rssbpDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpDenserank(Integer rssbpDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByRssbpDenserank", startResult, maxRows, rssbpDenserank);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFmin
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByFmin(Integer fmin) throws DataAccessException {

		return findVLocusIntxnPrinpredByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByFmin(Integer fmin, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccScore(BigDecimal rssccScore)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByRssccScore(rssccScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccScore(BigDecimal rssccScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByRssccScore", startResult, maxRows, rssccScore);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfScore(BigDecimal rssmfScore)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByRssmfScore(rssmfScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfScore(BigDecimal rssmfScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByRssmfScore", startResult, maxRows, rssmfScore);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFeatureId
	 *
	 */
	@Transactional
	public VLocusIntxnPrinpred findVLocusIntxnPrinpredByFeatureId(BigDecimal featureId) throws DataAccessException {

		return findVLocusIntxnPrinpredByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByFeatureId
	 *
	 */

	@Transactional
	public VLocusIntxnPrinpred findVLocusIntxnPrinpredByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusIntxnPrinpredByFeatureId", startResult, maxRows, featureId);
			return (VLocusIntxnPrinpred) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCommonNameContaining(String commonName)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByCommonNameContaining", startResult, maxRows,
				commonName);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNotes
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNotes(String notes) throws DataAccessException {

		return findVLocusIntxnPrinpredByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccDenserank(Integer rssccDenserank)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByRssccDenserank(rssccDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccDenserank(Integer rssccDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByRssccDenserank", startResult, maxRows, rssccDenserank);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpRank(Integer coexpRank) throws DataAccessException {

		return findVLocusIntxnPrinpredByCoexpRank(coexpRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpRank(Integer coexpRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByCoexpRank", startResult, maxRows, coexpRank);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpRank(Integer rssbpRank) throws DataAccessException {

		return findVLocusIntxnPrinpredByRssbpRank(rssbpRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpRank(Integer rssbpRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByRssbpRank", startResult, maxRows, rssbpRank);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNameContaining(String name) throws DataAccessException {

		return findVLocusIntxnPrinpredByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusIntxnPrinpreds
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findAllVLocusIntxnPrinpreds() throws DataAccessException {

		return findAllVLocusIntxnPrinpreds(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusIntxnPrinpreds
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findAllVLocusIntxnPrinpreds(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusIntxnPrinpreds", startResult, maxRows);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureNameContaining(String qfeatureName)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByQfeatureNameContaining(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureNameContaining(String qfeatureName,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByQfeatureNameContaining", startResult, maxRows,
				qfeatureName);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByStrand
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByStrand(Integer strand) throws DataAccessException {

		return findVLocusIntxnPrinpredByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByStrand(Integer strand, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigNameContaining(String contigName)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigNameContaining(String contigName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByContigNameContaining", startResult, maxRows,
				contigName);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByOrganismId(BigDecimal organismId)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpScore(BigDecimal coexpScore)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByCoexpScore(coexpScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpScore(BigDecimal coexpScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByCoexpScore", startResult, maxRows, coexpScore);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpScore(BigDecimal rssbpScore)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByRssbpScore(rssbpScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssbpScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssbpScore(BigDecimal rssbpScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByRssbpScore", startResult, maxRows, rssbpScore);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusIntxnPrinpred findVLocusIntxnPrinpredByPrimaryKey(BigDecimal featureId) throws DataAccessException {

		return findVLocusIntxnPrinpredByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusIntxnPrinpred findVLocusIntxnPrinpredByPrimaryKey(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusIntxnPrinpredByPrimaryKey", startResult, maxRows, featureId);
			return (VLocusIntxnPrinpred) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNotesContaining(String notes) throws DataAccessException {

		return findVLocusIntxnPrinpredByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByNotesContaining(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigName(String contigName) throws DataAccessException {

		return findVLocusIntxnPrinpredByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByContigName(String contigName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureName(String qfeatureName)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByQfeatureName(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureName(String qfeatureName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByQfeatureName", startResult, maxRows, qfeatureName);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureId(BigDecimal qfeatureId)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByQfeatureId(qfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByQfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByQfeatureId(BigDecimal qfeatureId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByQfeatureId", startResult, maxRows, qfeatureId);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfRank(Integer rssmfRank) throws DataAccessException {

		return findVLocusIntxnPrinpredByRssmfRank(rssmfRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfRank(Integer rssmfRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByRssmfRank", startResult, maxRows, rssmfRank);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCommonName(String commonName) throws DataAccessException {

		return findVLocusIntxnPrinpredByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCommonName(String commonName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccRank(Integer rssccRank) throws DataAccessException {

		return findVLocusIntxnPrinpredByRssccRank(rssccRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssccRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssccRank(Integer rssccRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByRssccRank", startResult, maxRows, rssccRank);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpDenserank(Integer coexpDenserank)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByCoexpDenserank(coexpDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByCoexpDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByCoexpDenserank(Integer coexpDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByCoexpDenserank", startResult, maxRows, coexpDenserank);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfDenserank(Integer rssmfDenserank)
			throws DataAccessException {

		return findVLocusIntxnPrinpredByRssmfDenserank(rssmfDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnPrinpredByRssmfDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnPrinpred> findVLocusIntxnPrinpredByRssmfDenserank(Integer rssmfDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnPrinpredByRssmfDenserank", startResult, maxRows, rssmfDenserank);
		return new LinkedHashSet<VLocusIntxnPrinpred>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusIntxnPrinpred entity) {
		return true;
	}

	@Override
	public List<Locus> getInteractingFeatures(Collection<Locus> colLocus, int type, Integer max) {
		
		Set locisId = new TreeSet();
		Iterator<Locus> itLoc = colLocus.iterator();
		while (itLoc.hasNext())
			locisId.add(itLoc.next().getFeatureId());
		Query query = createNamedQuery("findVLocusIntxnPrinpredByQfeatureIdIn", -1, -1, locisId);
		if (max != null)
			query.setMaxResults(max);
		return query.getResultList();

	}

}
