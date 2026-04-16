package org.irri.iric.ds.chado.dao.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.ds.chado.dao.FeatureDAO;
import org.irri.iric.ds.chado.domain.Feature;
import org.irri.iric.ds.chado.domain.model.FeaturePostgres;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Feature entities.
 * 
 */
//@Repository("FeatureDAOOracle")
//@Repository("FeatureDAOOld")
@Repository("FeatureDAO")
//@Transactional("transactionManagerIRICProduction_Oracle")
@Transactional
public class FeatureDAOImpl extends AbstractJpaDao<Feature> implements FeatureDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { org.irri.iric.ds.chado.domain.Feature.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	private boolean alwaysOracle = false;
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new FeatureDAOImpl
	 *
	 */
	public FeatureDAOImpl() {
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
	 * JPQL Query - findFeatureByName
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByName(String name) throws DataAccessException {

		return findFeatureByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findFeatureByName", startResult, maxRows, name);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByNameContaining
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByNameContaining(String name) throws DataAccessException {

		return findFeatureByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByIsObsolete
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByIsObsolete(Integer isObsolete) throws DataAccessException {

		return findFeatureByIsObsolete(isObsolete, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByIsObsolete
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByIsObsolete(Integer isObsolete, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByIsObsolete", startResult, maxRows, isObsolete);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByFeatureId
	 *
	 */
	@Transactional
	public Feature findFeatureByFeatureId(Integer featureId) throws DataAccessException {

		return findFeatureByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByFeatureId
	 *
	 */

	@Transactional
	public Feature findFeatureByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findFeatureByFeatureId", startResult, maxRows, featureId);
			return (Feature) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findFeatureByDbxrefId
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByDbxrefId(java.math.BigDecimal dbxrefId) throws DataAccessException {

		return findFeatureByDbxrefId(dbxrefId, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByDbxrefId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByDbxrefId(java.math.BigDecimal dbxrefId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByDbxrefId", startResult, maxRows, dbxrefId);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByUniquenameContaining
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByUniquenameContaining(String uniquename) throws DataAccessException {

		return findFeatureByUniquenameContaining(uniquename, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByUniquenameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByUniquenameContaining(String uniquename, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByUniquenameContaining", startResult, maxRows, uniquename);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByMd5checksum
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByMd5checksum(String md5checksum) throws DataAccessException {

		return findFeatureByMd5checksum(md5checksum, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByMd5checksum
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByMd5checksum(String md5checksum, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByMd5checksum", startResult, maxRows, md5checksum);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByTimelastmodified
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByTimelastmodified(java.util.Calendar timelastmodified) throws DataAccessException {

		return findFeatureByTimelastmodified(timelastmodified, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByTimelastmodified
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByTimelastmodified(java.util.Calendar timelastmodified, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByTimelastmodified", startResult, maxRows, timelastmodified);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByTimeaccessioned
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByTimeaccessioned(java.util.Calendar timeaccessioned) throws DataAccessException {

		return findFeatureByTimeaccessioned(timeaccessioned, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByTimeaccessioned
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByTimeaccessioned(java.util.Calendar timeaccessioned, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByTimeaccessioned", startResult, maxRows, timeaccessioned);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByTypeId
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByTypeId(Integer typeId) throws DataAccessException {

		return findFeatureByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findFeatureByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllFeatures
	 *
	 */
	@Transactional
	public Set<Feature> findAllFeatures() throws DataAccessException {

		return findAllFeatures(-1, -1);
	}

	/**
	 * JPQL Query - findAllFeatures
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findAllFeatures(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllFeatures", startResult, maxRows);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByMd5checksumContaining
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByMd5checksumContaining(String md5checksum) throws DataAccessException {

		return findFeatureByMd5checksumContaining(md5checksum, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByMd5checksumContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByMd5checksumContaining(String md5checksum, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByMd5checksumContaining", startResult, maxRows, md5checksum);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByIsAnalysis
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByIsAnalysis(Integer isAnalysis) throws DataAccessException {

		return findFeatureByIsAnalysis(isAnalysis, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByIsAnalysis
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByIsAnalysis(Integer isAnalysis, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByIsAnalysis", startResult, maxRows, isAnalysis);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByPrimaryKey
	 *
	 */
	@Transactional
	public Feature findFeatureByPrimaryKey(Integer featureId) throws DataAccessException {

		return findFeatureByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByPrimaryKey
	 *
	 */

	@Transactional
	public Feature findFeatureByPrimaryKey(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findFeatureByPrimaryKey", startResult, maxRows, featureId);
			return (Feature) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findFeatureByUniquename
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByUniquename(String uniquename) throws DataAccessException {

		return findFeatureByUniquename(uniquename, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByUniquename
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByUniquename(String uniquename, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByUniquename", startResult, maxRows, uniquename);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureByOrganismId
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureByOrganismId(Integer organismId) throws DataAccessException {

		return findFeatureByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureByOrganismId(Integer organismId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	/**
	 * JPQL Query - findFeatureBySeqlen
	 *
	 */
	@Transactional
	public Set<Feature> findFeatureBySeqlen(java.math.BigDecimal seqlen) throws DataAccessException {

		return findFeatureBySeqlen(seqlen, -1, -1);
	}

	/**
	 * JPQL Query - findFeatureBySeqlen
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Feature> findFeatureBySeqlen(java.math.BigDecimal seqlen, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findFeatureBySeqlen", startResult, maxRows, seqlen);
		return new LinkedHashSet<Feature>(query.getResultList());
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	private List<Feature> executeSQL(String sql) {
		// System.out.println("executing :" + sql);
		// AppContext.debug("executing :" + sql);

		// TODO: there is a session and Removed Oracle option
		// if(AppContext.isOracle())
		// return getSession().createSQLQuery(sql).addEntity( Feature.class ).list();
		// else
		return getSession().createSQLQuery(sql).addEntity(FeaturePostgres.class).list();

		// return getSession().get
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Feature entity) {
		return true;
	}

	@Override
	public String getSubSequence(String featurename, long start, long stop, int organismId) throws Exception {

		// return getSubSequenceOracle( featurename, start, stop, organismId);

		// TODO: removed oracle option
		// if (AppContext.isOracle() || alwaysOracle)
		// return getSubSequenceOracle(featurename, start, stop, organismId);
		// else if (AppContext.isPostgres())
		return getSubSequencePostgres(featurename, start, stop, organismId);

	}

	private String getSubSequenceOracle(String featurename, long start, long stop, int organismId) throws Exception {

		String sql = "select feature_id, dbxref_id, organism_id, name, " + "substr(residues," + start + ", "
				+ (stop - start + 1) + ") residues, "
				+ " seqlen, md5checksum, type_id, is_analysis, is_obsolete, timeaccessioned, timelastmodified, uniquename "
				+ " from iric.feature where upper(uniquename)= '" + featurename.toUpperCase() + "'"
				+ " and organism_id=" + organismId;
		List listFeature = executeSQL(sql);

		if (listFeature.size() == 0)
			throw new RuntimeException("Empty feature with name " + featurename);
		else if (listFeature.size() > 1)
			throw new RuntimeException("Non-unique feature with name " + featurename);
		Feature feature = (Feature) listFeature.get(0);
		// return AppContext.clobStringConversion(feature.getResidues());
		return feature.getResidues();

	}

	private String getSubSequencePostgres(String featurename, long start, long stop, int organismId) throws Exception {

		String sql = "select feature_id, dbxref_id, organism_id, name, " + "substring(residues," + start + ", "
				+ (stop - start + 1) + ") residues, "
				+ " seqlen, md5checksum, type_id, is_analysis, is_obsolete, timeaccessioned, timelastmodified, uniquename "
				+ " from public.feature where upper(uniquename)= '" + featurename.toUpperCase() + "'"
				+ " and organism_id=" + organismId;
		List listFeature = executeSQL(sql);

		// Does it really to be an exception? DAGS TODO
		if (listFeature.size() == 0)
			throw new RuntimeException("Empty feature with name " + featurename);
		else if (listFeature.size() > 1)
			throw new RuntimeException("Non-unique feature with name " + featurename);
		FeaturePostgres feature = (FeaturePostgres) listFeature.get(0);
		// return AppContext.clobStringConversion(feature.getResidues());
		return feature.getResidues();

	}
	/*
	 * @Override public String getSubSequence(BigDecimal featureid, long start, long
	 * stop) throws Exception {
	 * 
	 * String sql = "select feature_id, dbxref_id, organism_id, name, " +
	 * "substr(residues," + start + ", " + (stop-start+1) + ") residues, " +
	 * " seqlen, md5checksum, type_id, is_analysis, is_obsolete, timeaccessioned, timelastmodified, uniquename "
	 * + " from " + AppContext.getDefaultSchema() + ".feature where feature_id=" +
	 * featureid ; List listFeature=executeSQL(sql);
	 * 
	 * if(listFeature.size()==0) throw new RuntimeException("Empty feature with id "
	 * + featureid); else if(listFeature.size()>1) throw new
	 * RuntimeException("Non-unique feature with id " + featureid); Feature feature
	 * = (Feature)listFeature.get(0); return
	 * AppContext.clobStringConversion(feature.getResidues()); }
	 */

}
