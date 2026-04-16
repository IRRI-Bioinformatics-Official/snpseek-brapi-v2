package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VGoCvtermpathDAO;
import org.irri.iric.ds.chado.domain.model.VGoCvtermpath;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VGoCvtermpath entities.
 * 
 */
@Repository("VGoCvtermpathDAO")
// @Repository("CvTermPathDAO")

@Transactional
public class VGoCvtermpathDAOImpl extends AbstractJpaDao<VGoCvtermpath> implements VGoCvtermpathDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VGoCvtermpath.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VGoCvtermpathDAOImpl
	 *
	 */
	public VGoCvtermpathDAOImpl() {
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
	 * JPQL Query - findVGoCvtermpathBySubjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjCvtermContaining(String subjCvterm) throws DataAccessException {

		return findVGoCvtermpathBySubjCvtermContaining(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathBySubjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjCvtermContaining(String subjCvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathBySubjCvtermContaining", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathByObjAccContaining
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByObjAccContaining(String objAcc) throws DataAccessException {

		return findVGoCvtermpathByObjAccContaining(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathByObjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByObjAccContaining(String objAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathByObjAccContaining", startResult, maxRows, objAcc);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathBySubjAcc
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjAcc(String subjAcc) throws DataAccessException {

		return findVGoCvtermpathBySubjAcc(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathBySubjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjAcc(String subjAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathBySubjAcc", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVGoCvtermpaths
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findAllVGoCvtermpaths() throws DataAccessException {

		return findAllVGoCvtermpaths(-1, -1);
	}

	/**
	 * JPQL Query - findAllVGoCvtermpaths
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findAllVGoCvtermpaths(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVGoCvtermpaths", startResult, maxRows);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathBySubjAccContaining
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjAccContaining(String subjAcc) throws DataAccessException {

		return findVGoCvtermpathBySubjAccContaining(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathBySubjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjAccContaining(String subjAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathBySubjAccContaining", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathByCvName
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByCvName(String cvName) throws DataAccessException {

		return findVGoCvtermpathByCvName(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathByCvName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByCvName(String cvName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathByCvName", startResult, maxRows, cvName);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathByPrimaryKey
	 *
	 */
	@Transactional
	public VGoCvtermpath findVGoCvtermpathByPrimaryKey(String subjAcc, String objAcc) throws DataAccessException {

		return findVGoCvtermpathByPrimaryKey(subjAcc, objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathByPrimaryKey
	 *
	 */

	@Transactional
	public VGoCvtermpath findVGoCvtermpathByPrimaryKey(String subjAcc, String objAcc, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGoCvtermpathByPrimaryKey", startResult, maxRows, subjAcc, objAcc);
			return (VGoCvtermpath) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGoCvtermpathByObjAcc
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByObjAcc(String objAcc) throws DataAccessException {

		return findVGoCvtermpathByObjAcc(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathByObjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByObjAcc(String objAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathByObjAcc", startResult, maxRows, objAcc);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathByObjCvterm
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByObjCvterm(String objCvterm) throws DataAccessException {

		return findVGoCvtermpathByObjCvterm(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathByObjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByObjCvterm(String objCvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathByObjCvterm", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathByCvNameContaining
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByCvNameContaining(String cvName) throws DataAccessException {

		return findVGoCvtermpathByCvNameContaining(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathByCvNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByCvNameContaining(String cvName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathByCvNameContaining", startResult, maxRows, cvName);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathByPathdistance
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByPathdistance(java.math.BigDecimal pathdistance)
			throws DataAccessException {

		return findVGoCvtermpathByPathdistance(pathdistance, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathByPathdistance
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByPathdistance(java.math.BigDecimal pathdistance, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathByPathdistance", startResult, maxRows, pathdistance);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathByCvtermId
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByCvtermId(Integer cvtermId) throws DataAccessException {

		return findVGoCvtermpathByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathByCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByCvtermId(Integer cvtermId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathByCvtermId", startResult, maxRows, cvtermId);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathByObjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByObjCvtermContaining(String objCvterm) throws DataAccessException {

		return findVGoCvtermpathByObjCvtermContaining(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathByObjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathByObjCvtermContaining(String objCvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathByObjCvtermContaining", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoCvtermpathBySubjCvterm
	 *
	 */
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjCvterm(String subjCvterm) throws DataAccessException {

		return findVGoCvtermpathBySubjCvterm(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVGoCvtermpathBySubjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjCvterm(String subjCvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoCvtermpathBySubjCvterm", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VGoCvtermpath>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VGoCvtermpath entity) {
		return true;
	}

	@Override
	public Set getUniqueValues(BigDecimal typeId, Set dataset) {
		return null;
	}

	/*
	 * @Override public List getDescendants(String cv, String term, String dataset)
	 * {  return null; }
	 */
	@Override
	public List getAncestors(String cv, String term) {
		

		Query query = createNamedQuery("findVGoCvtermpathByObjCvtermNegDist", -1, -1, cv, term);
		List list = new ArrayList();
		Set set = new LinkedHashSet();
		Iterator<VGoCvtermpath> itGO = query.getResultList().iterator();
		while (itGO.hasNext())
			set.add(itGO.next().getSubjCvterm());
		// list.add(itGO.next().getSubjCvterm() );
		list.addAll(set);
		return list;
	}

	@Override
	public List getDescendants(String cv, String term, Set dataset) {
		
		Query query = createNamedQuery("findVGoCvtermpathByObjCvtermPosDist", -1, -1, cv, term);
		List list = new ArrayList();
		Set set = new LinkedHashSet();
		Iterator<VGoCvtermpath> itGO = query.getResultList().iterator();
		while (itGO.hasNext())
			set.add(itGO.next().getSubjCvterm());
		// list.add(itGO.next().getSubjCvterm() );
		list.addAll(set);
		return list;
	}

}
