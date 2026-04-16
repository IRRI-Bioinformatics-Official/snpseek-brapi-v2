package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VScaffoldsOrganismDAO;
import org.irri.iric.ds.chado.domain.Scaffold;
import org.irri.iric.ds.chado.domain.model.VScaffoldsOrganism;
import org.irri.iric.ds.utils.DbConstant;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VScaffoldsOrganism entities.
 * 
 */
//@Repository("VScaffoldsOrganismDAO")
@Repository("ScaffoldDAO")
@Transactional
public class VScaffoldsOrganismDAOImpl extends AbstractJpaDao<VScaffoldsOrganism> implements VScaffoldsOrganismDAO {

	private Logger log = Logger.getLogger(VScaffoldsOrganismDAOImpl.class.getName());
	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VScaffoldsOrganism.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VScaffoldsOrganismDAOImpl
	 *
	 */
	public VScaffoldsOrganismDAOImpl() {
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
	 * JPQL Query - findVScaffoldsOrganismByUniquename
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquename(String uniquename) throws DataAccessException {

		return findVScaffoldsOrganismByUniquename(uniquename, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquename
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquename(String uniquename, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByUniquename", startResult, maxRows, uniquename);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonName
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonName(String commonName) throws DataAccessException {

		return findVScaffoldsOrganismByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonName(String commonName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByOrganismId
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByOrganismId(java.math.BigDecimal organismId)
			throws DataAccessException {

		return findVScaffoldsOrganismByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByOrganismId(java.math.BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquenameContaining
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquenameContaining(String uniquename)
			throws DataAccessException {

		return findVScaffoldsOrganismByUniquenameContaining(uniquename, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquenameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquenameContaining(String uniquename, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByUniquenameContaining", startResult, maxRows,
				uniquename);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByFeatureId
	 *
	 */
	@Transactional
	public VScaffoldsOrganism findVScaffoldsOrganismByFeatureId(BigDecimal featureId) throws DataAccessException {

		return findVScaffoldsOrganismByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByFeatureId
	 *
	 */

	@Transactional
	public VScaffoldsOrganism findVScaffoldsOrganismByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVScaffoldsOrganismByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.ds.chado.domain.model.VScaffoldsOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeContaining
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeContaining(String type) throws DataAccessException {

		return findVScaffoldsOrganismByTypeContaining(type, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeContaining(String type, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByTypeContaining", startResult, maxRows, type);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByType
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByType(String type) throws DataAccessException {

		return findVScaffoldsOrganismByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByType(String type, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByType", startResult, maxRows, type);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByPrimaryKey
	 *
	 */
	@Transactional
	public VScaffoldsOrganism findVScaffoldsOrganismByPrimaryKey(BigDecimal featureId) throws DataAccessException {

		return findVScaffoldsOrganismByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByPrimaryKey
	 *
	 */

	@Transactional
	public VScaffoldsOrganism findVScaffoldsOrganismByPrimaryKey(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVScaffoldsOrganismByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.ds.chado.domain.model.VScaffoldsOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByNameContaining
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByNameContaining(String name) throws DataAccessException {

		return findVScaffoldsOrganismByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVScaffoldsOrganisms
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findAllVScaffoldsOrganisms() throws DataAccessException {

		return findAllVScaffoldsOrganisms(-1, -1);
	}

	/**
	 * JPQL Query - findAllVScaffoldsOrganisms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findAllVScaffoldsOrganisms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVScaffoldsOrganisms", startResult, maxRows);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonNameContaining(String commonName)
			throws DataAccessException {

		return findVScaffoldsOrganismByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByCommonNameContaining", startResult, maxRows,
				commonName);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeId
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeId(java.math.BigDecimal typeId)
			throws DataAccessException {

		return findVScaffoldsOrganismByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeId(java.math.BigDecimal typeId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByName
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByName(String name) throws DataAccessException {

		return findVScaffoldsOrganismByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismByName", startResult, maxRows, name);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismBySeqlen
	 *
	 */
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismBySeqlen(java.math.BigDecimal seqlen)
			throws DataAccessException {

		return findVScaffoldsOrganismBySeqlen(seqlen, -1, -1);
	}

	/**
	 * JPQL Query - findVScaffoldsOrganismBySeqlen
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismBySeqlen(java.math.BigDecimal seqlen, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVScaffoldsOrganismBySeqlen", startResult, maxRows, seqlen);
		return new LinkedHashSet<VScaffoldsOrganism>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VScaffoldsOrganism entity) {
		return true;
	}

	@Override
	public List<Scaffold> getScaffolds(String organism) {

		List list = new ArrayList();

		/*
		 * Iterator<VScaffoldsOrganism> itScaff =
		 * findVScaffoldsOrganismByCommonName(organism).iterator();
		 * while(itScaff.hasNext()) { list }
		 */

		list.addAll(findVScaffoldsOrganismByCommonName(organism));
		return list;
	}

	@Override
	public Long getScaffoldLength(String scaffold, String organism) {

		// AppContext.debug("scafold=" + scaffold + ", organism=" + organism);
		if (organism.equals(DbConstant.DEFAULT_ORGANISM)) {
			scaffold = scaffold.replace("r0", "r");
		}

		Query query = createNamedQuery("findVScaffoldsOrganismByNameCommonName", -1, -1, scaffold, organism);
		return ((VScaffoldsOrganism) query.getResultList().get(0)).getSeqlen().longValue();
	}

	@Override
	public List<Scaffold> getScaffolds(BigDecimal organism) {

		List list = new ArrayList();
		list.addAll(findVScaffoldsOrganismByOrganismId(organism));
		return list;
	}

	@Override
	public Long getScaffoldLength(String scaffold, BigDecimal organism) {

		/*
		 * if(organism.equals(BigDecimal.valueOf(9))) {
		 * scaffold=scaffold.replace("r0","r"); try { int
		 * chrval=Integer.valueOf(scaffold); if(chrval>9) scaffold="chr" + chrval; else
		 * scaffold="chr0" + chrval; } catch(Exception ex) {} }
		 */

		if (organism.equals(BigDecimal.valueOf(9))) {
			scaffold = scaffold.replaceAll("r0", "r");
		}

		log.info("scafold=" + scaffold + ", organism=" + organism);

		Query query = createNamedQuery("findVScaffoldsOrganismByNameOrganismId", -1, -1, scaffold, organism);
		return ((VScaffoldsOrganism) query.getResultList().get(0)).getSeqlen().longValue();
	}

	@Override
	public Scaffold getScaffold(String scaffold, BigDecimal organism) {

		if (organism.equals(BigDecimal.valueOf(9))) {
			scaffold = scaffold.replace("r0", "r");
		}

		Query query = createNamedQuery("findVScaffoldsOrganismByNameOrganismId", -1, -1, scaffold, organism);
		List result = query.getResultList();
		if (result.size() == 1)
			return (Scaffold) result.get(0);
		else if (result.size() == 0)
			return null;
		else
			throw new RuntimeException(
					"findVScaffoldsOrganismByNameOrganismId " + scaffold + " " + organism + " size=" + result.size());
	}

	@Override
	public Scaffold getScaffold(String scaffold, String organism) {

		if (organism.equals(DbConstant.DEFAULT_ORGANISM)) {
			scaffold = scaffold.replace("r0", "r");
		}

		Query query = createNamedQuery("findVScaffoldsOrganismByNameCommonName", -1, -1, scaffold, organism);
		List result = query.getResultList();
		if (result.size() == 1)
			return (Scaffold) result.get(0);
		else if (result.size() == 0)
			return null;
		else
			throw new RuntimeException(
					"findVScaffoldsOrganismByNameCommonName " + scaffold + " " + organism + " size=" + result.size());
	}

	@Override
	public Scaffold getScaffold(BigDecimal scaffoldId) {

		return findVScaffoldsOrganismByFeatureId(scaffoldId);
	}

}
