package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VGwasManhattanDAO;
import org.irri.iric.ds.chado.domain.GWASRun;
import org.irri.iric.ds.chado.domain.ManhattanPlot;
import org.irri.iric.ds.chado.domain.model.VGwasManhattan;
import org.irri.iric.ds.chado.domain.model.VGwasManhattanBasic;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VGwasManhattan entities.
 * 
 */
@Repository("VGwasManhattanDAOImpl")
@Transactional
public class VGwasManhattanDAOImpl extends AbstractJpaDao<VGwasManhattan> implements VGwasManhattanDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VGwasManhattanBasic.class })); // VGwasManhattan.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VGwasManhattanDAOImpl
	 *
	 */
	public VGwasManhattanDAOImpl() {
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
	 * JPQL Query - findVGwasManhattanByPrimaryKey
	 *
	 */
	@Transactional
	public VGwasManhattan findVGwasManhattanByPrimaryKey(BigDecimal gwasMarkerId) throws DataAccessException {

		return findVGwasManhattanByPrimaryKey(gwasMarkerId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanByPrimaryKey
	 *
	 */

	@Transactional
	public VGwasManhattan findVGwasManhattanByPrimaryKey(BigDecimal gwasMarkerId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGwasManhattanByPrimaryKey", startResult, maxRows, gwasMarkerId);
			return (VGwasManhattan) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulationContaining
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulationContaining(String subpopulation)
			throws DataAccessException {

		return findVGwasManhattanBySubpopulationContaining(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulationContaining(String subpopulation, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBySubpopulationContaining", startResult, maxRows,
				subpopulation);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanByTrait
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByTrait(String trait) throws DataAccessException {

		return findVGwasManhattanByTrait(trait, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanByTrait
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByTrait(String trait, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanByTrait", startResult, maxRows, trait);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanByChromosome
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByChromosome(Long chromosome) throws DataAccessException {

		return findVGwasManhattanByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByChromosome(Long chromosome, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanByPosition
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByPosition(BigDecimal position) throws DataAccessException {

		return findVGwasManhattanByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanByTraitContaining
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByTraitContaining(String trait) throws DataAccessException {

		return findVGwasManhattanByTraitContaining(trait, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanByTraitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByTraitContaining(String trait, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanByTraitContaining", startResult, maxRows, trait);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulation
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulation(String subpopulation) throws DataAccessException {

		return findVGwasManhattanBySubpopulation(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulation(String subpopulation, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBySubpopulation", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanByGwasMarkerId
	 *
	 */
	@Transactional
	public VGwasManhattan findVGwasManhattanByGwasMarkerId(BigDecimal gwasMarkerId) throws DataAccessException {

		return findVGwasManhattanByGwasMarkerId(gwasMarkerId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanByGwasMarkerId
	 *
	 */

	@Transactional
	public VGwasManhattan findVGwasManhattanByGwasMarkerId(BigDecimal gwasMarkerId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGwasManhattanByGwasMarkerId", startResult, maxRows, gwasMarkerId);
			return (VGwasManhattan) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGwasManhattanByMinusLogp
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByMinusLogp(java.math.BigDecimal minusLogp)
			throws DataAccessException {

		return findVGwasManhattanByMinusLogp(minusLogp, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanByMinusLogp
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByMinusLogp(java.math.BigDecimal minusLogp, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanByMinusLogp", startResult, maxRows, minusLogp);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVGwasManhattans
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findAllVGwasManhattans() throws DataAccessException {

		return findAllVGwasManhattans(-1, -1);
	}

	/**
	 * JPQL Query - findAllVGwasManhattans
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findAllVGwasManhattans(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVGwasManhattans", startResult, maxRows);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanByTraitId
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByTraitId(java.math.BigDecimal traitId) throws DataAccessException {

		return findVGwasManhattanByTraitId(traitId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanByTraitId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanByTraitId(java.math.BigDecimal traitId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanByTraitId", startResult, maxRows, traitId);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulationId
	 *
	 */
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulationId(java.math.BigDecimal subpopulationId)
			throws DataAccessException {

		return findVGwasManhattanBySubpopulationId(subpopulationId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBySubpopulationId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattan> findVGwasManhattanBySubpopulationId(java.math.BigDecimal subpopulationId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBySubpopulationId", startResult, maxRows, subpopulationId);
		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanByGwasMarkerId
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByGwasRunId(BigDecimal gwasRunId_1, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanByGwasBasicRunId", startResult, maxRows, gwasRunId_1);
		// Query query = createNamedQuery("findVGwasManhattanBasicByGwasRunId",
		// startResult, maxRows, gwasRunId_1);

		return new LinkedHashSet<VGwasManhattan>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanByGwasMarkerId
	 *
	 */
	public Set<VGwasManhattan> findVGwasManhattanByGwasRunId(BigDecimal gwasRunId_1) throws DataAccessException {
		return findVGwasManhattanByGwasRunId(gwasRunId_1, -1, -1);
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VGwasManhattan entity) {
		return true;
	}

	@Override
	public List<ManhattanPlot> getMinusPValues(GWASRun run) {
		
		List list = new ArrayList();
		list.addAll(findVGwasManhattanByGwasRunId(run.getGwasRunId()));
		return list;
	}

	@Override
	public List<ManhattanPlot> getMinusPValues(GWASRun run, Double minlogP, String region) {
		return null;
	}

}
