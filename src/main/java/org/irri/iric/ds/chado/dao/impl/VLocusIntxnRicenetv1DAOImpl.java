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

import org.irri.iric.ds.chado.dao.VLocusIntxnRicenetv1DAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VLocusIntxnRicenetv1;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusIntxnRicenetv1 entities.
 * 
 */
@Repository("VLocusIntxnRicenetv1DAO")
@Transactional
public class VLocusIntxnRicenetv1DAOImpl extends AbstractJpaDao<VLocusIntxnRicenetv1>
		implements VLocusIntxnRicenetv1DAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VLocusIntxnRicenetv1.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusIntxnRicenetv1DAOImpl
	 *
	 */
	public VLocusIntxnRicenetv1DAOImpl() {
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
	 * JPQL Query - findVLocusIntxnRicenetv1ByFmin
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByFmin(Integer fmin) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByFmin(Integer fmin, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFeatureId
	 *
	 */
	@Transactional
	public VLocusIntxnRicenetv1 findVLocusIntxnRicenetv1ByFeatureId(BigDecimal featureId) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFeatureId
	 *
	 */

	@Transactional
	public VLocusIntxnRicenetv1 findVLocusIntxnRicenetv1ByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusIntxnRicenetv1ByFeatureId", startResult, maxRows, featureId);
			return (VLocusIntxnRicenetv1) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureId(BigDecimal qfeatureId)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByQfeatureId(qfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureId(BigDecimal qfeatureId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByQfeatureId", startResult, maxRows, qfeatureId);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgRank(Integer ospgRank) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOspgRank(ospgRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgRank(Integer ospgRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOspgRank", startResult, maxRows, ospgRank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgScore(BigDecimal ospgScore)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOspgScore(ospgScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgScore(BigDecimal ospgScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOspgScore", startResult, maxRows, ospgScore);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxRank(Integer oscxRank) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOscxRank(oscxRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxRank(Integer oscxRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOscxRank", startResult, maxRows, oscxRank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetScore(BigDecimal intnetScore)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByIntnetScore(intnetScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetScore(BigDecimal intnetScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByIntnetScore", startResult, maxRows, intnetScore);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxScore(BigDecimal atcxScore)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByAtcxScore(atcxScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxScore(BigDecimal atcxScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByAtcxScore", startResult, maxRows, atcxScore);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnRank(Integer osgnRank) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOsgnRank(osgnRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnRank(Integer osgnRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOsgnRank", startResult, maxRows, osgnRank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusIntxnRicenetv1s
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findAllVLocusIntxnRicenetv1s() throws DataAccessException {

		return findAllVLocusIntxnRicenetv1s(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusIntxnRicenetv1s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findAllVLocusIntxnRicenetv1s(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusIntxnRicenetv1s", startResult, maxRows);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetRank(Integer intnetRank)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByIntnetRank(intnetRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetRank(Integer intnetRank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByIntnetRank", startResult, maxRows, intnetRank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureName(String qfeatureName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByQfeatureName(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureName(String qfeatureName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByQfeatureName", startResult, maxRows, qfeatureName);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnDenserank(Integer osgnDenserank)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOsgnDenserank(osgnDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnDenserank(Integer osgnDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOsgnDenserank", startResult, maxRows, osgnDenserank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNameContaining(String name) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByCommonName(String commonName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByCommonName(String commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetDenserank(Integer intnetDenserank)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByIntnetDenserank(intnetDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByIntnetDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByIntnetDenserank(Integer intnetDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByIntnetDenserank", startResult, maxRows,
				intnetDenserank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigName(String contigName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigName(String contigName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOrganismId(BigDecimal organismId)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcScore(BigDecimal atdcScore)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByAtdcScore(atdcScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcScore(BigDecimal atdcScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByAtdcScore", startResult, maxRows, atdcScore);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnScore(BigDecimal osgnScore)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOsgnScore(osgnScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOsgnScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOsgnScore(BigDecimal osgnScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOsgnScore", startResult, maxRows, osgnScore);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigId(BigDecimal contigId)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigId(BigDecimal contigId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureNameContaining(String qfeatureName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByQfeatureNameContaining(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByQfeatureNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByQfeatureNameContaining(String qfeatureName,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByQfeatureNameContaining", startResult, maxRows,
				qfeatureName);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxRank(Integer atcxRank) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByAtcxRank(atcxRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxRank(Integer atcxRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByAtcxRank", startResult, maxRows, atcxRank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxScore(BigDecimal oscxScore)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOscxScore(oscxScore, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxScore(BigDecimal oscxScore, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOscxScore", startResult, maxRows, oscxScore);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNotes
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNotes(String notes) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNotesContaining(String notes)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByNotesContaining(String notes, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusIntxnRicenetv1 findVLocusIntxnRicenetv1ByPrimaryKey(BigDecimal featureId) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusIntxnRicenetv1 findVLocusIntxnRicenetv1ByPrimaryKey(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusIntxnRicenetv1ByPrimaryKey", startResult, maxRows, featureId);
			return (VLocusIntxnRicenetv1) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgDenserank(Integer ospgDenserank)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOspgDenserank(ospgDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOspgDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOspgDenserank(Integer ospgDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOspgDenserank", startResult, maxRows, ospgDenserank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxDenserank(Integer oscxDenserank)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByOscxDenserank(oscxDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByOscxDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByOscxDenserank(Integer oscxDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByOscxDenserank", startResult, maxRows, oscxDenserank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigNameContaining(String contigName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByContigNameContaining(String contigName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByContigNameContaining", startResult, maxRows,
				contigName);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcRank(Integer atdcRank) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByAtdcRank(atdcRank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcRank(Integer atdcRank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByAtdcRank", startResult, maxRows, atdcRank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxDenserank(Integer atcxDenserank)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByAtcxDenserank(atcxDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtcxDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtcxDenserank(Integer atcxDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByAtcxDenserank", startResult, maxRows, atcxDenserank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcDenserank(Integer atdcDenserank)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByAtdcDenserank(atdcDenserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByAtdcDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByAtdcDenserank(Integer atdcDenserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByAtdcDenserank", startResult, maxRows, atdcDenserank);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByCommonNameContaining(String commonName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv1ByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByCommonNameContaining", startResult, maxRows,
				commonName);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByStrand
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByStrand(Integer strand) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByStrand(Integer strand, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFmax
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByFmax(Integer fmax) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByFmax(Integer fmax, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByName(String name) throws DataAccessException {

		return findVLocusIntxnRicenetv1ByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv1ByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv1> findVLocusIntxnRicenetv1ByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusIntxnRicenetv1>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusIntxnRicenetv1 entity) {
		return true;
	}

	@Override
	public List<Locus> getInteractingFeatures(Collection<Locus> colLocus, int type, Integer max) {
		
		Set locisId = new TreeSet();
		Iterator<Locus> itLoc = colLocus.iterator();
		while (itLoc.hasNext())
			locisId.add(itLoc.next().getFeatureId());
		Query query = createNamedQuery("findVLocusIntxnRicenetv1ByQfeatureIdIn", -1, -1, locisId);
		if (max != null)
			query.setMaxResults(max);
		return query.setMaxResults(10).getResultList();
	}

}
