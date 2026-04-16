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

import org.irri.iric.ds.chado.dao.VGwasManhattanBasicDAO;
import org.irri.iric.ds.chado.domain.GWASRun;
import org.irri.iric.ds.chado.domain.ManhattanPlot;
import org.irri.iric.ds.chado.domain.model.VGwasManhattanBasic;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VGwasManhattanBasic entities.
 * 
 */
// @Repository("VGwasManhattanBasicDAO")
@Repository("ManhattanPlotDAO")
@Transactional
public class VGwasManhattanBasicDAOImpl extends AbstractJpaDao<VGwasManhattanBasic> implements VGwasManhattanBasicDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VGwasManhattanBasic.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VGwasManhattanBasicDAOImpl
	 *
	 */
	public VGwasManhattanBasicDAOImpl() {
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
	 * JPQL Query - findVGwasManhattanBasicByTraitContaining
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByTraitContaining(String trait) throws DataAccessException {

		return findVGwasManhattanBasicByTraitContaining(trait, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByTraitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByTraitContaining(String trait, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicByTraitContaining", startResult, maxRows, trait);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVGwasManhattanBasics
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findAllVGwasManhattanBasics() throws DataAccessException {

		return findAllVGwasManhattanBasics(-1, -1);
	}

	/**
	 * JPQL Query - findAllVGwasManhattanBasics
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findAllVGwasManhattanBasics(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVGwasManhattanBasics", startResult, maxRows);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByTraitId
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByTraitId(java.math.BigDecimal traitId)
			throws DataAccessException {

		return findVGwasManhattanBasicByTraitId(traitId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByTraitId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByTraitId(java.math.BigDecimal traitId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicByTraitId", startResult, maxRows, traitId);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByPrimaryKey
	 *
	 */
	@Transactional
	public VGwasManhattanBasic findVGwasManhattanBasicByPrimaryKey(BigDecimal gwasMarkerId) throws DataAccessException {

		return findVGwasManhattanBasicByPrimaryKey(gwasMarkerId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByPrimaryKey
	 *
	 */

	@Transactional
	public VGwasManhattanBasic findVGwasManhattanBasicByPrimaryKey(BigDecimal gwasMarkerId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGwasManhattanBasicByPrimaryKey", startResult, maxRows, gwasMarkerId);
			return (VGwasManhattanBasic) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByMinusLogp
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByMinusLogp(java.math.BigDecimal minusLogp)
			throws DataAccessException {

		return findVGwasManhattanBasicByMinusLogp(minusLogp, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByMinusLogp
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByMinusLogp(java.math.BigDecimal minusLogp, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicByMinusLogp", startResult, maxRows, minusLogp);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByGwasRunIdMinusLogp(java.math.BigDecimal gwasRunId,
			java.math.BigDecimal minusLogp) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicByGwasRunIdMinusLogp", -1, -1, gwasRunId, minusLogp);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByGwasRunId
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByGwasRunId(java.math.BigDecimal gwasRunId)
			throws DataAccessException {

		return findVGwasManhattanBasicByGwasRunId(gwasRunId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByGwasRunId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByGwasRunId(java.math.BigDecimal gwasRunId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicByGwasRunId", startResult, maxRows, gwasRunId);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByTrait
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByTrait(String trait) throws DataAccessException {

		return findVGwasManhattanBasicByTrait(trait, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByTrait
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByTrait(String trait, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicByTrait", startResult, maxRows, trait);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByGwasMarkerId
	 *
	 */
	@Transactional
	public VGwasManhattanBasic findVGwasManhattanBasicByGwasMarkerId(BigDecimal gwasMarkerId)
			throws DataAccessException {

		return findVGwasManhattanBasicByGwasMarkerId(gwasMarkerId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByGwasMarkerId
	 *
	 */

	@Transactional
	public VGwasManhattanBasic findVGwasManhattanBasicByGwasMarkerId(BigDecimal gwasMarkerId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGwasManhattanBasicByGwasMarkerId", startResult, maxRows, gwasMarkerId);
			return (VGwasManhattanBasic) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicBySubpopulation
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicBySubpopulation(String subpopulation)
			throws DataAccessException {

		return findVGwasManhattanBasicBySubpopulation(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicBySubpopulation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicBySubpopulation(String subpopulation, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicBySubpopulation", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByPosition
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByPosition(BigDecimal position) throws DataAccessException {

		return findVGwasManhattanBasicByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicBySubpopulationId
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicBySubpopulationId(java.math.BigDecimal subpopulationId)
			throws DataAccessException {

		return findVGwasManhattanBasicBySubpopulationId(subpopulationId, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicBySubpopulationId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicBySubpopulationId(java.math.BigDecimal subpopulationId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicBySubpopulationId", startResult, maxRows,
				subpopulationId);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicBySubpopulationContaining
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicBySubpopulationContaining(String subpopulation)
			throws DataAccessException {

		return findVGwasManhattanBasicBySubpopulationContaining(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicBySubpopulationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicBySubpopulationContaining(String subpopulation,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicBySubpopulationContaining", startResult, maxRows,
				subpopulation);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByChromosome
	 *
	 */
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByChromosome(Long chromosome) throws DataAccessException {

		return findVGwasManhattanBasicByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findVGwasManhattanBasicByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGwasManhattanBasic> findVGwasManhattanBasicByChromosome(Long chromosome, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGwasManhattanBasicByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<VGwasManhattanBasic>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VGwasManhattanBasic entity) {
		return true;
	}

	@Override
	public List<ManhattanPlot> getMinusPValues(GWASRun run) {
		
		List list = new ArrayList();
		list.addAll(findVGwasManhattanBasicByGwasRunId(run.getGwasRunId()));
		return list;
	}

	@Override
	public List<ManhattanPlot> getMinusPValues(GWASRun run, Double minlogP, String region) {
		
		List list = new ArrayList();
		list.addAll(findVGwasManhattanBasicByGwasRunIdMinusLogp(run.getGwasRunId(), BigDecimal.valueOf(minlogP)));
		return list;
	}

	/*
	 * select gr.gwas_run_id, go.minus_logp, gm.gwas_marker_id,
	 * variant.srcfeature_id-2 chromosome, variant.position+1 position from
	 * tmp_gwas_output go, tmp_gwas_marker gm, tmp_gwas_run gr, mv_snp_refposindex2
	 * variant where go.gwas_run_id=gr.gwas_run_id and
	 * go.gwas_marker_id=gm.gwas_marker_id and variant.snp_feature_id=gm.variant_id
	 * and variant.type_id=5 AND go.minus_logp>5 union select gr.gwas_run_id,
	 * go.minus_logp, gm.gwas_marker_id, variant.chromosome, variant.position+1
	 * position from tmp_gwas_output go, tmp_gwas_marker gm, tmp_gwas_run gr,
	 * mv_indel_refposindex3 variant where go.gwas_run_id=gr.gwas_run_id and
	 * go.gwas_marker_id=gm.gwas_marker_id and
	 * variant.indel_feature_id=gm.variant_id and variant.type_id=7 AND
	 * go.minus_logp>5
	 */

	/*
	 * private Session getSession() { return entityManager.unwrap(Session.class); }
	 * 
	 * private List<Locus> executeSQL(String sql) {
	 * //System.out.println("executing :" + sql); //log.info("executing :" + sql);
	 * AppContext.debug("executing " + sql);
	 * 
	 * 
	 * List listResult = null; try { listResult=
	 * getSession().createSQLQuery(sql).addEntity(VGwasManhattanBasic.class).list();
	 * } catch(Exception ex) { AppContext.debug(ex.getMessage());
	 * ex.printStackTrace(); throw new RuntimeException(ex); }
	 * AppContext.debug("result " + listResult.size() + " cvterms"); return
	 * listResult; }
	 */
}
