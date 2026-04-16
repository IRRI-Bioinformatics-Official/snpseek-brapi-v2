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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.LocusCvTermDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VLocusCvtermpathRap;
import org.irri.iric.ds.utils.DbUtils;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusCvtermpathIric entities.
 * 
 */
@Repository("VLocusCvtermpathRapDAO")
@Transactional
public class VLocusCvtermpathRapDAOImpl extends AbstractJpaDao<VLocusCvtermpathRap>
		implements JpaDao<VLocusCvtermpathRap>, LocusCvTermDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VLocusCvtermpathRap.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusCvtermpathIricDAOImpl
	 *
	 */
	public VLocusCvtermpathRapDAOImpl() {
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

	// /**
	// * JPQL Query - findVLocusCvtermpathIricByContigName
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByContigName(String
	// contigName) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByContigName(contigName, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByContigName
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByContigName(String
	// contigName, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByContigName",
	// startResult, maxRows, contigName);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByNotes
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByNotes(String
	// notes) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByNotes(notes, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByNotes
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByNotes(String
	// notes, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByNotes",
	// startResult, maxRows, notes);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByDb
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByDb(String db)
	// throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByDb(db, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByDb
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByDb(String db, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByDb", startResult,
	// maxRows, db);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByName
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByName(String name)
	// throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByName(name, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByName
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByName(String name,
	// int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByName", startResult,
	// maxRows, name);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByOrganismId
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByOrganismId(java.math.BigDecimal organismId) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByOrganismId(organismId, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByOrganismId
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByOrganismId(java.math.BigDecimal organismId, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByOrganismId",
	// startResult, maxRows, organismId);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCvName
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvName(String
	// cvName) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByCvName(cvName, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCvName
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvName(String
	// cvName, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByCvName",
	// startResult, maxRows, cvName);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFmax
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByFmax(java.math.BigDecimal fmax) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByFmax(fmax, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFmax
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByFmax(java.math.BigDecimal fmax, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByFmax", startResult,
	// maxRows, fmax);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCommonNameContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByCommonNameContaining(String commonName) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByCommonNameContaining(commonName, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCommonNameContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByCommonNameContaining(String commonName, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query =
	// createNamedQuery("findVLocusCvtermpathIricByCommonNameContaining",
	// startResult, maxRows, commonName);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByRapRepContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByRapRepContaining(String rapRep) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByRapRepContaining(rapRep, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByRapRepContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByRapRepContaining(String rapRep, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByRapRepContaining",
	// startResult, maxRows, rapRep);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByIric
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByIric(String iric)
	// throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByIric(iric, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByIric
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByIric(String iric,
	// int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByIric", startResult,
	// maxRows, iric);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByObjCvterm
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjCvterm(String
	// objCvterm) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByObjCvterm(objCvterm, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByObjCvterm
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjCvterm(String
	// objCvterm, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByObjCvterm",
	// startResult, maxRows, objCvterm);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByContigNameContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByContigNameContaining(String contigName) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByContigNameContaining(contigName, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByContigNameContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByContigNameContaining(String contigName, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query =
	// createNamedQuery("findVLocusCvtermpathIricByContigNameContaining",
	// startResult, maxRows, contigName);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByMsu7Containing
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByMsu7Containing(String msu7) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByMsu7Containing(msu7, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByMsu7Containing
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByMsu7Containing(String msu7, int startResult, int
	// maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByMsu7Containing",
	// startResult, maxRows, msu7);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByStrand
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByStrand(java.math.BigDecimal strand) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByStrand(strand, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByStrand
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByStrand(java.math.BigDecimal strand, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByStrand",
	// startResult, maxRows, strand);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByDbContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByDbContaining(String db) throws DataAccessException
	// {
	//
	// return findVLocusCvtermpathIricByDbContaining(db, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByDbContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByDbContaining(String db, int startResult, int
	// maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByDbContaining",
	// startResult, maxRows, db);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFgenesh
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFgenesh(String
	// fgenesh) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByFgenesh(fgenesh, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFgenesh
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFgenesh(String
	// fgenesh, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByFgenesh",
	// startResult, maxRows, fgenesh);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByRapRep
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapRep(String
	// rapRep) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByRapRep(rapRep, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByRapRep
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapRep(String
	// rapRep, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByRapRep",
	// startResult, maxRows, rapRep);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCvNameContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByCvNameContaining(String cvName) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByCvNameContaining(cvName, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCvNameContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByCvNameContaining(String cvName, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByCvNameContaining",
	// startResult, maxRows, cvName);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCommonName
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCommonName(String
	// commonName) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByCommonName(commonName, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCommonName
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCommonName(String
	// commonName, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByCommonName",
	// startResult, maxRows, commonName);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByRapPredContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByRapPredContaining(String rapPred) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByRapPredContaining(rapPred, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByRapPredContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByRapPredContaining(String rapPred, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByRapPredContaining",
	// startResult, maxRows, rapPred);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCvtermId
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByCvtermId(java.math.BigDecimal cvtermId) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByCvtermId(cvtermId, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCvtermId
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByCvtermId(java.math.BigDecimal cvtermId, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByCvtermId",
	// startResult, maxRows, cvtermId);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFgeneshContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByFgeneshContaining(String fgenesh) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByFgeneshContaining(fgenesh, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFgeneshContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByFgeneshContaining(String fgenesh, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByFgeneshContaining",
	// startResult, maxRows, fgenesh);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByNameContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByNameContaining(String name) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByNameContaining(name, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByNameContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByNameContaining(String name, int startResult, int
	// maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByNameContaining",
	// startResult, maxRows, name);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByPrimaryKey
	// *
	// */
	// @Transactional
	// public VLocusCvtermpathIric findVLocusCvtermpathIricByPrimaryKey(Integer
	// featureId) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByPrimaryKey(featureId, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByPrimaryKey
	// *
	// */
	//
	// @Transactional
	// public VLocusCvtermpathIric findVLocusCvtermpathIricByPrimaryKey(Integer
	// featureId, int startResult, int maxRows) throws DataAccessException {
	// try {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByPrimaryKey",
	// startResult, maxRows, featureId);
	// return (org.irri.iric.portal.chado.oracle.domain.VLocusCvtermpathIric)
	// query.getSingleResult();
	// } catch (NoResultException nre) {
	// return null;
	// }
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByNotesContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByNotesContaining(String notes) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByNotesContaining(notes, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByNotesContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByNotesContaining(String notes, int startResult, int
	// maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByNotesContaining",
	// startResult, maxRows, notes);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFeatureId
	// *
	// */
	// @Transactional
	// public VLocusCvtermpathIric findVLocusCvtermpathIricByFeatureId(Integer
	// featureId) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByFeatureId(featureId, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFeatureId
	// *
	// */
	//
	// @Transactional
	// public VLocusCvtermpathIric findVLocusCvtermpathIricByFeatureId(Integer
	// featureId, int startResult, int maxRows) throws DataAccessException {
	// try {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByFeatureId",
	// startResult, maxRows, featureId);
	// return (org.irri.iric.portal.chado.oracle.domain.VLocusCvtermpathIric)
	// query.getSingleResult();
	// } catch (NoResultException nre) {
	// return null;
	// }
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByIricContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByIricContaining(String iric) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByIricContaining(iric, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByIricContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByIricContaining(String iric, int startResult, int
	// maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByIricContaining",
	// startResult, maxRows, iric);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricBySubjCvterm
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjCvterm(String
	// subjCvterm) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricBySubjCvterm(subjCvterm, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricBySubjCvterm
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjCvterm(String
	// subjCvterm, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricBySubjCvterm",
	// startResult, maxRows, subjCvterm);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricBySubjAccContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricBySubjAccContaining(String subjAcc) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricBySubjAccContaining(subjAcc, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricBySubjAccContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricBySubjAccContaining(String subjAcc, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricBySubjAccContaining",
	// startResult, maxRows, subjAcc);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByObjAccContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByObjAccContaining(String objAcc) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByObjAccContaining(objAcc, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByObjAccContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByObjAccContaining(String objAcc, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByObjAccContaining",
	// startResult, maxRows, objAcc);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricBySubjCvtermContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricBySubjCvtermContaining(String subjCvterm) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricBySubjCvtermContaining(subjCvterm, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricBySubjCvtermContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricBySubjCvtermContaining(String subjCvterm, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query =
	// createNamedQuery("findVLocusCvtermpathIricBySubjCvtermContaining",
	// startResult, maxRows, subjCvterm);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCvId
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByCvId(java.math.BigDecimal cvId) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByCvId(cvId, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByCvId
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByCvId(java.math.BigDecimal cvId, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByCvId", startResult,
	// maxRows, cvId);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByMsu7
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByMsu7(String msu7)
	// throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByMsu7(msu7, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByMsu7
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByMsu7(String msu7,
	// int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByMsu7", startResult,
	// maxRows, msu7);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByContigId
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByContigId(java.math.BigDecimal contigId) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByContigId(contigId, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByContigId
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByContigId(java.math.BigDecimal contigId, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByContigId",
	// startResult, maxRows, contigId);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findAllVLocusCvtermpathIrics
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findAllVLocusCvtermpathIrics() throws
	// DataAccessException {
	//
	// return findAllVLocusCvtermpathIrics(-1, -1);
	// }
	//
	// /**
	// * JPQL Query - findAllVLocusCvtermpathIrics
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findAllVLocusCvtermpathIrics(int
	// startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findAllVLocusCvtermpathIrics", startResult,
	// maxRows);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByRapPred
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapPred(String
	// rapPred) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByRapPred(rapPred, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByRapPred
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapPred(String
	// rapPred, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByRapPred",
	// startResult, maxRows, rapPred);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByPathdistance
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByPathdistance(Integer pathdistance) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByPathdistance(pathdistance, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByPathdistance
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByPathdistance(Integer pathdistance, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByPathdistance",
	// startResult, maxRows, pathdistance);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricBySubjAcc
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjAcc(String
	// subjAcc) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricBySubjAcc(subjAcc, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricBySubjAcc
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjAcc(String
	// subjAcc, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricBySubjAcc",
	// startResult, maxRows, subjAcc);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByObjAcc
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjAcc(String
	// objAcc) throws DataAccessException {
	//
	// return findVLocusCvtermpathIricByObjAcc(objAcc, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByObjAcc
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjAcc(String
	// objAcc, int startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByObjAcc",
	// startResult, maxRows, objAcc);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFmin
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByFmin(java.math.BigDecimal fmin) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByFmin(fmin, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByFmin
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByFmin(java.math.BigDecimal fmin, int startResult,
	// int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVLocusCvtermpathIricByFmin", startResult,
	// maxRows, fmin);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByObjCvtermContaining
	// *
	// */
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByObjCvtermContaining(String objCvterm) throws
	// DataAccessException {
	//
	// return findVLocusCvtermpathIricByObjCvtermContaining(objCvterm, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVLocusCvtermpathIricByObjCvtermContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VLocusCvtermpathIric>
	// findVLocusCvtermpathIricByObjCvtermContaining(String objCvterm, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query =
	// createNamedQuery("findVLocusCvtermpathIricByObjCvtermContaining",
	// startResult, maxRows, objCvterm);
	// return new LinkedHashSet<VLocusCvtermpathIric>(query.getResultList());
	// }
	//

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpathRap> findVLocusCvtermpathRapByObjCvtermCvOrg(String objCvterm, BigDecimal cvId,
			BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathRapByObjCvtermCvOrg", startResult, maxRows, objCvterm, cvId,
				organismId);
		return new LinkedHashSet<VLocusCvtermpathRap>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusCvtermpathRap entity) {
		return true;
	}

	@Override
	public List getLocusByDescription(String goterm, Integer organismId, Integer cvId) {
		Set setLocus = new TreeSet(findVLocusCvtermpathRapByObjCvtermCvOrg(goterm, BigDecimal.valueOf(cvId),
				BigDecimal.valueOf(organismId), -1, -1));
		List list = new ArrayList();
		list.addAll(setLocus);
		return list;
	}

	@Override
	public List<Locus> filterLocusWithCvterm(Collection<Locus> colLoc, Integer organismId, Integer cvId) {
		Set locisId = new TreeSet();
		Iterator<Locus> itLoc = colLoc.iterator();
		while (itLoc.hasNext())
			locisId.add(itLoc.next().getFeatureId());

		Set featureIds[] = DbUtils.setSlicer(locisId);

		List filtered = new ArrayList();
		for (int i = 0; i < featureIds.length; i++) {
			Query query = createNamedQuery("findVLocusCvtermpathRapByCvOrgInFeatureId", -1, -1,
					BigDecimal.valueOf(cvId), BigDecimal.valueOf(organismId), featureIds[i]);
			filtered.addAll(query.getResultList());
		}

		return filtered;
	}

}
