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

import org.irri.iric.ds.chado.dao.VGwasRunDAO;
import org.irri.iric.ds.chado.domain.GWASRun;
import org.irri.iric.ds.chado.domain.model.VGwasRun;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VGwasRun entities.
 * 
 */
@Repository("GWASRunDAO")
@Transactional
public class VGwasRunDAOImpl extends AbstractJpaDao<VGwasRun> implements VGwasRunDAO {

	@Override
	public GWASRun getGWASRunByTrait(String trait) {

		Query query = createNamedQuery("findVGwasRunByTrait", -1, -1, trait);
		return (GWASRun) query.getResultList().iterator().next();
	}

	@Override
	public GWASRun getGWASRunByCoterm(String coterm) {

		Query query = createNamedQuery("findVGwasRunByCoterm", -1, -1, coterm);
		return (GWASRun) query.getResultList().iterator().next();
	}

	@Override
	public GWASRun getGWASRunByCodefinition(String codefinition) {

		Query query = createNamedQuery("findVGwasRunByCodefinition", -1, -1, codefinition);
		return (GWASRun) query.getResultList().iterator().next();
	}

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VGwasRun.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VGwasRunDAOImpl
	 *
	 */
	public VGwasRunDAOImpl() {
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
	 * JPQL Query - findVGwasRunBySubpopulationId
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunBySubpopulationId(BigDecimal subpopulationId) throws DataAccessException {

		return findVGwasRunBySubpopulationId(subpopulationId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunBySubpopulationId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunBySubpopulationId(BigDecimal subpopulationId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunBySubpopulationId", startResult, maxRows, subpopulationId);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunByGwasRunId
	 *
	 */
	@Transactional
	public VGwasRun findVGwasRunByGwasRunId(BigDecimal gwasRunId) throws DataAccessException {

		return findVGwasRunByGwasRunId(gwasRunId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByGwasRunId
	 *
	 */

	@Transactional
	public VGwasRun findVGwasRunByGwasRunId(BigDecimal gwasRunId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGwasRunByGwasRunId", startResult, maxRows, gwasRunId);
			return (VGwasRun) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGwasRunByRundate
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunByRundate(java.util.Calendar rundate) throws DataAccessException {

		return findVGwasRunByRundate(rundate, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByRundate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunByRundate(java.util.Calendar rundate, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunByRundate", startResult, maxRows, rundate);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunByQqplot
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunByQqplot(String qqplot) throws DataAccessException {

		return findVGwasRunByQqplot(qqplot, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByQqplot
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunByQqplot(String qqplot, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunByQqplot", startResult, maxRows, qqplot);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunByTraitId
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunByTraitId(java.math.BigDecimal traitId) throws DataAccessException {

		return findVGwasRunByTraitId(traitId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByTraitId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunByTraitId(java.math.BigDecimal traitId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunByTraitId", startResult, maxRows, traitId);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunBySubpopulation
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunBySubpopulation(String subpopulation) throws DataAccessException {

		return findVGwasRunBySubpopulation(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunBySubpopulation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunBySubpopulation(String subpopulation, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunBySubpopulation", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunByMethodContaining
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunByMethodContaining(String method) throws DataAccessException {

		return findVGwasRunByMethodContaining(method, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByMethodContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunByMethodContaining(String method, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunByMethodContaining", startResult, maxRows, method);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunByTraitContaining
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunByTraitContaining(String trait) throws DataAccessException {

		return findVGwasRunByTraitContaining(trait, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByTraitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunByTraitContaining(String trait, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunByTraitContaining", startResult, maxRows, trait);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunBySubpopulationContaining
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunBySubpopulationContaining(String subpopulation) throws DataAccessException {

		return findVGwasRunBySubpopulationContaining(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunBySubpopulationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunBySubpopulationContaining(String subpopulation, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunBySubpopulationContaining", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunByMethod
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunByMethod(String method) throws DataAccessException {
		
		

		return findVGwasRunByMethod(method, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByMethod
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunByMethod(String method, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunByMethod", startResult, maxRows, method);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVGwasRuns
	 *
	 */
	@Transactional
	public Set<VGwasRun> findAllVGwasRuns() throws DataAccessException {
		

		return findAllVGwasRuns(-1, -1);
	}

	/**
	 * JPQL Query - findAllVGwasRuns
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findAllVGwasRuns(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVGwasRuns", startResult, maxRows);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunByTrait
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunByTrait(String trait) throws DataAccessException {

		return findVGwasRunByTrait(trait, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByTrait
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunByTrait(String trait, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunByTrait", startResult, maxRows, trait);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasRunByPrimaryKey
	 *
	 */
	@Transactional
	public VGwasRun findVGwasRunByPrimaryKey(BigDecimal gwasRunId) throws DataAccessException {

		return findVGwasRunByPrimaryKey(gwasRunId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByPrimaryKey
	 *
	 */

	@Transactional
	public VGwasRun findVGwasRunByPrimaryKey(BigDecimal gwasRunId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGwasRunByPrimaryKey", startResult, maxRows, gwasRunId);
			return (VGwasRun) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGwasRunByQqplotContaining
	 *
	 */
	@Transactional
	public Set<VGwasRun> findVGwasRunByQqplotContaining(String qqplot) throws DataAccessException {

		return findVGwasRunByQqplotContaining(qqplot, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasRunByQqplotContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasRun> findVGwasRunByQqplotContaining(String qqplot, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasRunByQqplotContaining", startResult, maxRows, qqplot);
		return new LinkedHashSet<VGwasRun>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VGwasRun entity) {
		return true;
	}

	@Override
	public List<GWASRun> getGWASRuns() {

		List list = new ArrayList();
		list.addAll(findAllVGwasRuns());
		return list;
	}

}
