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

import org.irri.iric.ds.chado.dao.VCvPtocoDAO;
import org.irri.iric.ds.chado.domain.model.VCvPtoco;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VCvPtoco entities.
 * 
 */
@Repository("VCvPtocoDAO")
@Transactional
public class VCvPtocoDAOImpl extends AbstractJpaDao<VCvPtoco> implements VCvPtocoDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VCvPtoco.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VCvPtocoDAOImpl
	 *
	 */
	public VCvPtocoDAOImpl() {
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
	 * JPQL Query - findVCvPtocoByDb
	 *
	 */
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByDb(String db) throws DataAccessException {

		return findVCvPtocoByDb(db, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByDb
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByDb(String db, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByDb", startResult, maxRows, db);
		return new LinkedHashSet<VCvPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByDefinition
	 *
	 */
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByDefinition(String definition) throws DataAccessException {

		return findVCvPtocoByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByDefinition", startResult, maxRows, definition);
		return new LinkedHashSet<VCvPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVCvPtocos
	 *
	 */
	@Transactional
	public Set<VCvPtoco> findAllVCvPtocos() throws DataAccessException {

		return findAllVCvPtocos(-1, -1);
	}

	/**
	 * JPQL Query - findAllVCvPtocos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtoco> findAllVCvPtocos(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVCvPtocos", startResult, maxRows);
		return new LinkedHashSet<VCvPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByCvtermContaining
	 *
	 */
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByCvtermContaining(String cvterm) throws DataAccessException {

		return findVCvPtocoByCvtermContaining(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByCvtermContaining(String cvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByCvtermContaining", startResult, maxRows, cvterm);
		return new LinkedHashSet<VCvPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByPrimaryKey
	 *
	 */
	@Transactional
	public VCvPtoco findVCvPtocoByPrimaryKey(BigDecimal cvtermId) throws DataAccessException {

		return findVCvPtocoByPrimaryKey(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByPrimaryKey
	 *
	 */

	@Transactional
	public VCvPtoco findVCvPtocoByPrimaryKey(BigDecimal cvtermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPtocoByPrimaryKey", startResult, maxRows, cvtermId);
			return (VCvPtoco) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPtocoByAccessionContaining
	 *
	 */
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByAccessionContaining(String accession) throws DataAccessException {

		return findVCvPtocoByAccessionContaining(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByAccessionContaining(String accession, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByAccessionContaining", startResult, maxRows, accession);
		return new LinkedHashSet<VCvPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByCvterm
	 *
	 */
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByCvterm(String cvterm) throws DataAccessException {

		return findVCvPtocoByCvterm(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByCvterm(String cvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByCvterm", startResult, maxRows, cvterm);
		return new LinkedHashSet<VCvPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByDbContaining
	 *
	 */
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByDbContaining(String db) throws DataAccessException {

		return findVCvPtocoByDbContaining(db, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByDbContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByDbContaining(String db, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByDbContaining", startResult, maxRows, db);
		return new LinkedHashSet<VCvPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByAccession
	 *
	 */
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByAccession(String accession) throws DataAccessException {

		return findVCvPtocoByAccession(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByAccession(String accession, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByAccession", startResult, maxRows, accession);
		return new LinkedHashSet<VCvPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByDefinitionContaining(String definition) throws DataAccessException {

		return findVCvPtocoByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtoco> findVCvPtocoByDefinitionContaining(String definition, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByDefinitionContaining", startResult, maxRows, definition);
		return new LinkedHashSet<VCvPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByCvtermId
	 *
	 */
	@Transactional
	public VCvPtoco findVCvPtocoByCvtermId(BigDecimal cvtermId) throws DataAccessException {

		return findVCvPtocoByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByCvtermId
	 *
	 */

	@Transactional
	public VCvPtoco findVCvPtocoByCvtermId(BigDecimal cvtermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPtocoByCvtermId", startResult, maxRows, cvtermId);
			return (VCvPtoco) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VCvPtoco entity) {
		return true;
	}

	@Override
	public List getAllTerms() {
		List list = new ArrayList();
		list.addAll(findAllVCvPtocos());
		return list;
	}

	@Override
	public List getAllTerms(BigDecimal cvByName, BigDecimal organismByName) {
		return null;
	}

}
