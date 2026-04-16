package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import org.irri.iric.ds.chado.dao.VLocusCvtermCvtermpathDAO;
import org.irri.iric.ds.chado.domain.CvTerm;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VLocusCvtermCvtermpath;
import org.irri.iric.ds.utils.TextSearchOptions;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusCvtermCvtermpath entities.
 * 
 */
@Repository("LocusCvtermCvtermpathDAO")
@Transactional
public class VLocusCvtermCvtermpathDAOImpl extends AbstractJpaDao<VLocusCvtermCvtermpath>
		implements VLocusCvtermCvtermpathDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VLocusCvtermCvtermpath.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusCvtermCvtermpathDAOImpl
	 *
	 */
	public VLocusCvtermCvtermpathDAOImpl() {
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
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonName(String commonName)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonName(String commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvCommonName(String cv, String commonName,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCvCommonName", startResult, maxRows, cv,
				commonName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNameContaining(String name)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNameContaining(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvtermContaining(String objCvterm)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByObjCvtermContaining(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvtermContaining(String objCvterm,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByObjCvtermContaining", startResult, maxRows,
				objCvterm);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByPrimaryKey(java.math.BigDecimal featureId,
			java.math.BigDecimal cvtermId, java.math.BigDecimal organismId) throws DataAccessException {

		return findVLocusCvtermCvtermpathByPrimaryKey(featureId, cvtermId, organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByPrimaryKey(java.math.BigDecimal featureId,
			java.math.BigDecimal cvtermId, java.math.BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusCvtermCvtermpathByPrimaryKey", startResult, maxRows, featureId,
					cvtermId, organismId);
			return (org.irri.iric.ds.chado.domain.model.VLocusCvtermCvtermpath) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvName(String cvName) throws DataAccessException {

		return findVLocusCvtermCvtermpathByCvName(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvName(String cvName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCvName", startResult, maxRows, cvName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonNameContaining(String commonName)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonNameContaining(String commonName,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCommonNameContaining", startResult, maxRows,
				commonName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmin
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmin(java.math.BigDecimal fmin)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmin(java.math.BigDecimal fmin, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByName(String name) throws DataAccessException {

		return findVLocusCvtermCvtermpathByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigName(String contigName)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigName(String contigName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAccContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAccContaining(String subjAcc)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathBySubjAccContaining(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAccContaining(String subjAcc, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathBySubjAccContaining", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotesContaining(String notes)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotesContaining(String notes, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotes
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotes(String notes) throws DataAccessException {

		return findVLocusCvtermCvtermpathByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusCvtermCvtermpaths
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findAllVLocusCvtermCvtermpaths() throws DataAccessException {

		return findAllVLocusCvtermCvtermpaths(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusCvtermCvtermpaths
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findAllVLocusCvtermCvtermpaths(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusCvtermCvtermpaths", startResult, maxRows);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigId(java.math.BigDecimal contigId)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigId(java.math.BigDecimal contigId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByStrand
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByStrand(java.math.BigDecimal strand)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByStrand(java.math.BigDecimal strand, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigNameContaining(String contigName)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigNameContaining(String contigName,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByContigNameContaining", startResult, maxRows,
				contigName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvterm
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvterm(String subjCvterm)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathBySubjCvterm(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvterm(String subjCvterm, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathBySubjCvterm", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAccContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAccContaining(String objAcc)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByObjAccContaining(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAccContaining(String objAcc, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByObjAccContaining", startResult, maxRows, objAcc);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByOrganismId(java.math.BigDecimal organismId)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByOrganismId(java.math.BigDecimal organismId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvtermContaining(String subjCvterm)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathBySubjCvtermContaining(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvtermContaining(String subjCvterm,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathBySubjCvtermContaining", startResult, maxRows,
				subjCvterm);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAcc
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAcc(String subjAcc) throws DataAccessException {

		return findVLocusCvtermCvtermpathBySubjAcc(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAcc(String subjAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathBySubjAcc", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvterm
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvterm(String objCvterm)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByObjCvterm(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvterm(String objCvterm, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByObjCvterm", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Transactional public Set<VLocusCvtermCvtermpath>
	 * findVLocusCvtermCvtermpathByObjCvtermOrg(String objCvterm, String organism,
	 * int startResult, int maxRows) throws DataAccessException { Query query =
	 * createNamedQuery("findVLocusCvtermCvtermpathByObjCvtermOrg", startResult,
	 * maxRows, objCvterm,organism); return new
	 * LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList()); }
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvtermCvOrg(String objCvterm, BigDecimal cvId,
			BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByObjCvtermCvOrg", startResult, maxRows, objCvterm,
				cvId, organismId);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAcc
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAcc(String objAcc) throws DataAccessException {

		return findVLocusCvtermCvtermpathByObjAcc(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAcc(String objAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByObjAcc", startResult, maxRows, objAcc);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFeatureId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFeatureId(java.math.BigDecimal featureId)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFeatureId(java.math.BigDecimal featureId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByFeatureId", startResult, maxRows, featureId);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmax
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmax(java.math.BigDecimal fmax)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmax(java.math.BigDecimal fmax, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvNameContaining(String cvName)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByCvNameContaining(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvNameContaining(String cvName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCvNameContaining", startResult, maxRows, cvName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvtermId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvtermId(java.math.BigDecimal cvtermId)
			throws DataAccessException {

		return findVLocusCvtermCvtermpathByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvtermId(java.math.BigDecimal cvtermId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCvtermId", startResult, maxRows, cvtermId);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvOrganism(java.math.BigDecimal cvId,
			BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCvOrganism", startResult, maxRows, cvId,
				organismId);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusCvtermCvtermpath entity) {
		return true;
	}

	@Override
	public List getAllTerms() {
		return null;
	}

	/*
	 * @Override public List getAllTerms(String organism) { // TODO Auto-generated
	 * method stub Set setlocusterms =
	 * findVLocusCvtermCvtermpathByCommonName(organism); Iterator<CvTerm>
	 * itlocusterms = setlocusterms.iterator(); Set setTerms=new TreeSet();
	 * while(itlocusterms.hasNext()) { setTerms.add( itlocusterms.next().getName());
	 * } List list=new ArrayList(); list.addAll(setTerms);
	 * 
	 * AppContext.debug("VLocusCvtermCvtermpathDAO getAllTerms " + organism +
	 * " result=" + list.size() + "; setlocusterms=" + setlocusterms.size() );
	 * 
	 * return list;
	 * 
	 * }
	 */

	@Override
	public List getAllTerms(BigDecimal cv, BigDecimal organism) {

		// Set setlocusterms =
		// findVLocusCvtermCvtermpathByCvCommonName(cv,organism,-1,-1);
		Set setlocusterms = findVLocusCvtermCvtermpathByCvOrganism(cv, organism, -1, -1);
		Iterator<CvTerm> itlocusterms = setlocusterms.iterator();
		Set setTerms = new TreeSet();
		while (itlocusterms.hasNext()) {
			setTerms.add(itlocusterms.next().getName());
		}
		List list = new ArrayList();
		list.addAll(setTerms);

		return list;
	}

	//
	// @Override
	// public List getAllTerms(String cv, String organism) {
	//
	//
	//
	// listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao,
	// "ListItemsDAO");
	//
	//
	// //Set setlocusterms =
	// findVLocusCvtermCvtermpathByCvCommonName(cv,organism,-1,-1);
	// Set setlocusterms =
	// findVLocusCvtermCvtermpathByCvCommonName(cv,listitemsdao.getOrganismByName(organism).getOrganismId(),-1,-1);
	// Iterator<CvTerm> itlocusterms = setlocusterms.iterator();
	// Set setTerms=new TreeSet();
	// while(itlocusterms.hasNext()) {
	// setTerms.add( itlocusterms.next().getName());
	// }
	// List list=new ArrayList();
	// list.addAll(setTerms);
	//
	// AppContext.debug("VLocusCvtermCvtermpathDAO getAllTerms " + cv + ", " +
	// organism + " result=" + list.size() + "; setlocusterms=" +
	// setlocusterms.size() );
	//
	// return list;
	// }

	@Override
	public List<Locus> getLocusByName(String name) {
		return null;
	}

	@Override
	public Collection getLocusByName(Collection<String> name) {
		return null;
	}

	@Override
	public List getLocusByDescription(String goterm, Integer organismId, Integer cvId) {

		Set setLocus = new TreeSet(findVLocusCvtermCvtermpathByObjCvtermCvOrg(goterm, BigDecimal.valueOf(cvId),
				BigDecimal.valueOf(organismId), -1, -1));
		List list = new ArrayList();
		list.addAll(setLocus);
		return list;
		// return null;
	}

	@Override
	public List<Locus> getLocusByDescription(TextSearchOptions goterm, String organism) {

		return null;
		/*
		 * Set setLocus = new TreeSet(
		 * this.findVLocusCvtermCvtermpathByObjCvtermOrg(goterm,organism,-1,-1) ); List
		 * list = new ArrayList(); list.addAll(setLocus); return list;
		 */
	}

	/*
	 * @Override public List<Locus> getLocusByRegion(String contig, Long start, Long
	 * end, String organism) { return null; }
	 */
	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus) {
		return null;
	}

	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel) {
		return null;
	}

	@Override
	public List<Locus> getLocusByDescription(TextSearchOptions description, String organism, String genemodel) {
		return null;
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel) {
		return null;
	}

	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel,
			String featuretype) {
		return null;
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel, String featuretype) {
		return null;
	}

	@Override
	public List<Locus> getLocusBySynonyms(TextSearchOptions synonym, String organism) {
		return null;
	}

	@Override
	public List<Locus> getLocusBySynonyms(TextSearchOptions synonym, String organism, String genemodel) {
		return null;
	}

	@Override
	public List<Locus> filterLocusWithCvterm(Collection<Locus> colLoc, Integer organismId, Integer cvId) {

		Set locisId = new TreeSet();
		Iterator<Locus> itLoc = colLoc.iterator();
		while (itLoc.hasNext())
			locisId.add(itLoc.next().getFeatureId());

		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCvOrgInFeatureId", -1, -1, BigDecimal.valueOf(cvId),
				BigDecimal.valueOf(organismId), locisId);
		return query.getResultList();

		/*
		 * Set featureIds[] = AppContext.setSlicer(locisId); List filtered=new
		 * ArrayList(); for(int i=0; i<featureIds.length; i++) { Query query =
		 * createNamedQuery("findVLocusCvtermCvtermpathByCvOrgInFeatureId", -1,-1,
		 * BigDecimal.valueOf(cvId), BigDecimal.valueOf(organismId), featureIds[i]);
		 * filtered.addAll(query.getResultList()); } return filtered;
		 */
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel, Set featuretype) {
		return null;
	}

	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel,
			Set featuretype) {
		return null;
	}

}
