package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.LocusCvTermDAO;
import org.irri.iric.ds.chado.dao.VGoOrganismDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VGoOrganism;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VGoOrganism entities.
 * 
 */
@Repository("VGoOrganismDAO")
@Transactional
public class VGoOrganismDAOImpl extends AbstractJpaDao<VGoOrganism> implements VGoOrganismDAO, LocusCvTermDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VGoOrganism.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VGoOrganismDAOImpl
	 *
	 */
	public VGoOrganismDAOImpl() {
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
	 * JPQL Query - findVGoOrganismByCommonName
	 *
	 */
	// @Transactional
	// public Set<VGoOrganism> findVGoOrganismByCommonName(String commonName) throws
	// DataAccessException {
	//
	// return findVGoOrganismByCommonName(commonName, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVGoOrganismByCommonName
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VGoOrganism> findVGoOrganismByCommonName(String commonName, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVGoOrganismByCommonName", startResult,
	// maxRows, commonName);
	// return new LinkedHashSet<VGoOrganism>(query.getResultList());
	// }

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvOrganism(BigDecimal cv, BigDecimal commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGoOrganismByCvOrganism", startResult, maxRows, cv, commonName);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByCvterm
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvterm(String cvterm) throws DataAccessException {

		return findVGoOrganismByCvterm(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvterm(String cvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoOrganismByCvterm", startResult, maxRows, cvterm);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByPrimaryKey
	 *
	 */
	@Transactional
	public VGoOrganism findVGoOrganismByPrimaryKey(Integer cvtermId) throws DataAccessException {

		return findVGoOrganismByPrimaryKey(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByPrimaryKey
	 *
	 */

	@Transactional
	public VGoOrganism findVGoOrganismByPrimaryKey(Integer cvtermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGoOrganismByPrimaryKey", startResult, maxRows, cvtermId);
			return (VGoOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	// /**
	// * JPQL Query - findVGoOrganismByCvName
	// *
	// */
	// @Transactional
	// public Set<VGoOrganism> findVGoOrganismByCvName(String cvName) throws
	// DataAccessException {
	//
	// return findVGoOrganismByCvName(cvName, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVGoOrganismByCvName
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VGoOrganism> findVGoOrganismByCvName(String cvName, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVGoOrganismByCvName", startResult,
	// maxRows, cvName);
	// return new LinkedHashSet<VGoOrganism>(query.getResultList());
	// }
	//
	// /**
	// * JPQL Query - findVGoOrganismByCvNameContaining
	// *
	// */
	// @Transactional
	// public Set<VGoOrganism> findVGoOrganismByCvNameContaining(String cvName)
	// throws DataAccessException {
	//
	// return findVGoOrganismByCvNameContaining(cvName, -1, -1);
	// }
	//
	// /**
	// * JPQL Query - findVGoOrganismByCvNameContaining
	// *
	// */
	//
	// @SuppressWarnings("unchecked")
	// @Transactional
	// public Set<VGoOrganism> findVGoOrganismByCvNameContaining(String cvName, int
	// startResult, int maxRows) throws DataAccessException {
	// Query query = createNamedQuery("findVGoOrganismByCvNameContaining",
	// startResult, maxRows, cvName);
	// return new LinkedHashSet<VGoOrganism>(query.getResultList());
	// }

	/**
	 * JPQL Query - findAllVGoOrganisms
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findAllVGoOrganisms() throws DataAccessException {

		return findAllVGoOrganisms(-1, -1);
	}

	/**
	 * JPQL Query - findAllVGoOrganisms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findAllVGoOrganisms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVGoOrganisms", startResult, maxRows);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByAccessionContaining
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByAccessionContaining(String accession) throws DataAccessException {

		return findVGoOrganismByAccessionContaining(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByAccessionContaining(String accession, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoOrganismByAccessionContaining", startResult, maxRows, accession);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByAccession
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByAccession(String accession) throws DataAccessException {

		return findVGoOrganismByAccession(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByAccession(String accession, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoOrganismByAccession", startResult, maxRows, accession);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByOrganismId
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByOrganismId(java.math.BigDecimal organismId) throws DataAccessException {

		return findVGoOrganismByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByOrganismId(java.math.BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoOrganismByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCommonNameContaining(String commonName) throws DataAccessException {

		return findVGoOrganismByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCommonNameContaining(String commonName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoOrganismByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByCvtermId
	 *
	 */
	@Transactional
	public VGoOrganism findVGoOrganismByCvtermId(Integer cvtermId) throws DataAccessException {

		return findVGoOrganismByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCvtermId
	 *
	 */

	@Transactional
	public VGoOrganism findVGoOrganismByCvtermId(Integer cvtermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGoOrganismByCvtermId", startResult, maxRows, cvtermId);
			return (VGoOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGoOrganismByCvtermContaining
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvtermContaining(String cvterm) throws DataAccessException {

		return findVGoOrganismByCvtermContaining(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvtermContaining(String cvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGoOrganismByCvtermContaining", startResult, maxRows, cvterm);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VGoOrganism entity) {
		return true;
	}

	@Override
	public List getAllTerms() {

		List list = new ArrayList();
		list.addAll(this.findAllVGoOrganisms());
		return list;
	}
	/*
	 * @Override public List getAllTermsByOrganism(String organism) { // TODO
	 * Auto-generated method stub
	 * 
	 * Set setTerms = new TreeSet(); Iterator<VGoOrganism> itgoorg =
	 * findVGoOrganismByCommonName(organism).iterator(); while(itgoorg.hasNext())
	 * setTerms.add( itgoorg.next().getCvterm() );
	 * 
	 * List list=new ArrayList(); list.addAll(setTerms ); return list; }
	 */
	// @Override
	// public List getAllTerms(String organism) {
	//
	//
	// Set setTerms = new TreeSet();
	// Iterator<VGoOrganism> itgoorg =
	// findVGoOrganismByCommonName(organism).iterator();
	// while(itgoorg.hasNext())
	// setTerms.add( itgoorg.next().getCvterm() );
	//
	// List list=new ArrayList();
	// list.addAll(setTerms );
	// return list;
	// }

	// @Override
	// public List getAllTerms(String cv, String organism) {
	//
	// Set setTerms = new TreeSet();
	// Iterator<VGoOrganism> itgoorg =
	// findVGoOrganismByCvCommonName(cv,organism,-1,-1).iterator();
	// while(itgoorg.hasNext())
	// setTerms.add( itgoorg.next().getCvterm() );
	//
	// List list=new ArrayList();
	// list.addAll(setTerms );
	// return list;
	// }

	@Override
	public List getAllTerms(BigDecimal cv, BigDecimal organism) {

		// Set setTerms = new TreeSet();

		List list = new ArrayList();
		list.addAll(findVGoOrganismByCvOrganism(cv, organism, -1, -1));
		return list;

		/*
		 * Iterator<VGoOrganism> itgoorg =
		 * findVGoOrganismByCvOrganism(cv,organism,-1,-1).iterator();
		 * while(itgoorg.hasNext()) setTerms.add( itgoorg.next().getCvterm() );
		 * 
		 * List list=new ArrayList(); list.addAll(setTerms ); return list;
		 */
	}

	@Override
	public List getLocusByDescription(String goterm, Integer organismId, Integer cvId) {

		try {
			// Query query = createNamedQuery("findVGoOrganismByCvtermCvOrganism", -1,-1,
			// cvId, goterm, "molecular_function", organismId);
			Query query = createNamedQuery("findVGoOrganismByCvtermCvOrganism", -1, -1, goterm, cvId, organismId);
			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public List<Locus> filterLocusWithCvterm(Collection<Locus> colLoc, Integer organismId, Integer cvId) {
		return null;
	}

}
