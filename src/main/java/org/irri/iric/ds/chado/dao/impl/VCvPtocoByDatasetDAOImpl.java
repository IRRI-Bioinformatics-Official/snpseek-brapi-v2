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

import org.irri.iric.ds.chado.dao.CvTermByDatasetDAO;
import org.irri.iric.ds.chado.domain.model.VCvPtocoByDataset;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VCvPtoco entities.
 * 
 */
@Repository("VCvPtocoByDatasetDAO")
@Transactional
public class VCvPtocoByDatasetDAOImpl extends AbstractJpaDao<VCvPtocoByDataset> implements CvTermByDatasetDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VCvPtocoByDataset.class }));

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
	public VCvPtocoByDatasetDAOImpl() {
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
	public Set<VCvPtocoByDataset> findVCvPtocoByDb(String db) throws DataAccessException {

		return findVCvPtocoByDb(db, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByDb
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByDb(String db, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByDbDataset", startResult, maxRows, db);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByDefinition
	 *
	 */
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByDefinition(String definition) throws DataAccessException {

		return findVCvPtocoByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByDefinitionDataset", startResult, maxRows, definition);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVCvPtocos
	 *
	 */
	@Transactional
	public Set<VCvPtocoByDataset> findAllVCvPtocos(String dataset) throws DataAccessException {

		return findAllVCvPtocos(dataset, -1, -1);
	}

	/**
	 * JPQL Query - findAllVCvPtocos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findAllVCvPtocos(String dataset, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVCvPtocosDataset", startResult, maxRows, dataset);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findAllVCvPtocosAll(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVCvPtocosAllDataset", startResult, maxRows);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByCvtermContaining
	 *
	 */
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByCvtermContaining(String cvterm) throws DataAccessException {

		return findVCvPtocoByCvtermContaining(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByCvtermContaining(String cvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByCvtermContainingDataset", startResult, maxRows, cvterm);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByPrimaryKey
	 *
	 */
	@Transactional
	public VCvPtocoByDataset findVCvPtocoByPrimaryKey(BigDecimal cvtermId) throws DataAccessException {

		return findVCvPtocoByPrimaryKey(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByPrimaryKey
	 *
	 */

	@Transactional
	public VCvPtocoByDataset findVCvPtocoByPrimaryKey(BigDecimal cvtermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPtocoByPrimaryKeyDataset", startResult, maxRows, cvtermId);
			return (VCvPtocoByDataset) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPtocoByAccessionContaining
	 *
	 */
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByAccessionContaining(String accession) throws DataAccessException {

		return findVCvPtocoByAccessionContaining(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByAccessionContaining(String accession, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByAccessionContainingDataset", startResult, maxRows, accession);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByCvterm
	 *
	 */
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByCvterm(String cvterm) throws DataAccessException {

		return findVCvPtocoByCvterm(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByCvterm(String cvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByCvtermDataset", startResult, maxRows, cvterm);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByDbContaining
	 *
	 */
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByDbContaining(String db) throws DataAccessException {

		return findVCvPtocoByDbContaining(db, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByDbContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByDbContaining(String db, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByDbContainingDataset", startResult, maxRows, db);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByAccession
	 *
	 */
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByAccession(String accession) throws DataAccessException {

		return findVCvPtocoByAccession(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByAccession(String accession, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByAccessionDataset", startResult, maxRows, accession);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByDefinitionContaining(String definition) throws DataAccessException {

		return findVCvPtocoByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPtocoByDataset> findVCvPtocoByDefinitionContaining(String definition, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPtocoByDefinitionContainingDataset", startResult, maxRows, definition);
		return new LinkedHashSet<VCvPtocoByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPtocoByCvtermId
	 *
	 */
	@Transactional
	public VCvPtocoByDataset findVCvPtocoByCvtermId(BigDecimal cvtermId) throws DataAccessException {

		return findVCvPtocoByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPtocoByCvtermId
	 *
	 */

	@Transactional
	public VCvPtocoByDataset findVCvPtocoByCvtermId(BigDecimal cvtermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPtocoByCvtermIdDataset", startResult, maxRows, cvtermId);
			return (VCvPtocoByDataset) query.getSingleResult();
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
	public boolean canBeMerged(VCvPtocoByDataset entity) {
		return true;
	}

	@Override
	public List getAllTerms() {

		List list = new ArrayList();
		list.addAll(findAllVCvPtocosAll(-1, -1));
		return list;

	}

	@Override
	public List getAllTerms(BigDecimal cvByName, BigDecimal organismByName) {

		return null;
	}

	@Override
	public List getAllTermsByDataset(String dataset) {

		List list = new ArrayList();
		list.addAll(findAllVCvPtocos(dataset));
		return list;
	}

	@Override
	public List getAllTermsByDataset(BigDecimal cvByName, BigDecimal organismByName, String dataset) {

		return null;
	}

}
