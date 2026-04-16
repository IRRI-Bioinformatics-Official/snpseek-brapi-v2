package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
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
import org.irri.iric.ds.chado.domain.model.VCvPassportByDataset;
//import org.irri.iric.portal.dao.CvTermDAO;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VCvPassportByDataset entities.
 * 
 */
@Repository("VCvPassportByDatasetDAO")
@Transactional
public class VCvPassportDAOByDatasetImpl extends AbstractJpaDao<VCvPassportByDataset> implements CvTermByDatasetDAO {

	private String defaultdataset = "3k";

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VCvPassportByDataset.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VCvPassportByDatasetDAOImpl
	 *
	 */
	public VCvPassportDAOByDatasetImpl() {
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
	 * JPQL Query - findVCvPassportByDatasetByPrimaryKey
	 *
	 */
	@Transactional
	public VCvPassportByDataset findVCvPassportByDatasetByPrimaryKey(Integer cvTermId) throws DataAccessException {

		return findVCvPassportByDatasetByPrimaryKey(cvTermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByPrimaryKey
	 *
	 */

	@Transactional
	public VCvPassportByDataset findVCvPassportByDatasetByPrimaryKey(Integer cvTermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPassportByPrimaryKeyDataset", startResult, maxRows, cvTermId,
					defaultdataset);
			return (VCvPassportByDataset) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByDefinition
	 *
	 */
	@Transactional
	public Set<VCvPassportByDataset> findVCvPassportByDatasetByDefinition(String definition)
			throws DataAccessException {

		return findVCvPassportByDatasetByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPassportByDataset> findVCvPassportByDatasetByDefinition(String definition, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPassportByDefinitionDataset", startResult, maxRows, definition,
				defaultdataset);
		return new LinkedHashSet<VCvPassportByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByNameContaining
	 *
	 */
	@Transactional
	public Set<VCvPassportByDataset> findVCvPassportByDatasetByNameContaining(String name) throws DataAccessException {

		return findVCvPassportByDatasetByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPassportByDataset> findVCvPassportByDatasetByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPassportByNameContainingDataset", startResult, maxRows, name,
				defaultdataset);
		return new LinkedHashSet<VCvPassportByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VCvPassportByDataset> findVCvPassportByDatasetByDefinitionContaining(String definition)
			throws DataAccessException {

		return findVCvPassportByDatasetByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPassportByDataset> findVCvPassportByDatasetByDefinitionContaining(String definition, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPassportByDefinitionContainingDataset", startResult, maxRows, definition,
				defaultdataset);
		return new LinkedHashSet<VCvPassportByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVCvPassportByDatasets
	 *
	 */
	@Transactional
	public List<VCvPassportByDataset> findAllVCvPassportByDatasets() throws DataAccessException {

		return findAllVCvPassportByDatasets(-1, -1);
	}

	/**
	 * JPQL Query - findAllVCvPassportByDatasets
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public List<VCvPassportByDataset> findAllVCvPassportByDatasets(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVCvPassportsDataset", startResult, maxRows);

		return query.getResultList();
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByName
	 *
	 */
	@Transactional
	public Set<VCvPassportByDataset> findVCvPassportByDatasetByName(String name) throws DataAccessException {

		return findVCvPassportByDatasetByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPassportByDataset> findVCvPassportByDatasetByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPassportByNameDataset", startResult, maxRows, name, defaultdataset);
		return new LinkedHashSet<VCvPassportByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByCvTermId
	 *
	 */
	@Transactional
	public VCvPassportByDataset findVCvPassportByDatasetByCvTermId(Integer cvTermId) throws DataAccessException {

		return findVCvPassportByDatasetByCvTermId(cvTermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPassportByDatasetByCvTermId
	 *
	 */

	@Transactional
	public VCvPassportByDataset findVCvPassportByDatasetByCvTermId(Integer cvTermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPassportByCvTermIdDataset", startResult, maxRows, cvTermId,
					defaultdataset);
			return (VCvPassportByDataset) query.getSingleResult();
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
	public boolean canBeMerged(VCvPassportByDataset entity) {
		return true;
	}

	@Override
	public List getAllTerms() {
		
		return this.findAllVCvPassportByDatasets();
	}
	//
	// @Override
	// public List getAllTerms(String organism) {
	// 
	// return null;
	// }
	//
	// @Override
	// public List getAllTerms(String cv, String organism) {
	// 
	// return null;
	// }

	@Override
	public List getAllTerms(BigDecimal cvByName, BigDecimal organismByName) {
		return null;
	}

	@Override
	public List getAllTermsByDataset(String dataset) {
		
		Query query = createNamedQuery("findAllVCvPassportDatasetByDataset", -1, -1, dataset);
		return query.getResultList();
	}

	@Override
	public List getAllTermsByDataset(BigDecimal cvByName, BigDecimal organismByName, String dataset) {
		return null;
	}

}
