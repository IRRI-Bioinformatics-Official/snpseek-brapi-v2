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
import org.irri.iric.ds.chado.domain.model.VCvPhenotypeByDataset;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VCvPhenotype entities.
 * 
 */
@Repository("VCvPhenotypeByDatasetDAO")
@Transactional
public class VCvPhenotypeDAOByDatasetImpl extends AbstractJpaDao<VCvPhenotypeByDataset> implements CvTermByDatasetDAO {

	private String defaultdataset = "3k";
	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VCvPhenotypeByDataset.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VCvPhenotypeByDatasetDAOImpl
	 *
	 */
	public VCvPhenotypeDAOByDatasetImpl() {
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
	 * JPQL Query - findVCvPhenotypeByDefinition
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByDataset> findVCvPhenotypeByDefinition(String definition) throws DataAccessException {

		return findVCvPhenotypeByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByDataset> findVCvPhenotypeByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByDefinitionDataset", startResult, maxRows, definition,
				defaultdataset);
		return new LinkedHashSet<VCvPhenotypeByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByName
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByDataset> findVCvPhenotypeByName(String name) throws DataAccessException {

		return findVCvPhenotypeByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByDataset> findVCvPhenotypeByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByNameDataset", startResult, maxRows, name, defaultdataset);
		return new LinkedHashSet<VCvPhenotypeByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByNameContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByDataset> findVCvPhenotypeByNameContaining(String name) throws DataAccessException {

		return findVCvPhenotypeByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByDataset> findVCvPhenotypeByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByNameContainingDataset", startResult, maxRows, name,
				defaultdataset);
		return new LinkedHashSet<VCvPhenotypeByDataset>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByCvTermId
	 *
	 */
	@Transactional
	public VCvPhenotypeByDataset findVCvPhenotypeByCvTermId(Integer cvTermId) throws DataAccessException {

		return findVCvPhenotypeByCvTermId(cvTermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByCvTermId
	 *
	 */

	@Transactional
	public VCvPhenotypeByDataset findVCvPhenotypeByCvTermId(Integer cvTermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPhenotypeByCvTermIdDataset", startResult, maxRows, cvTermId,
					defaultdataset);
			return (VCvPhenotypeByDataset) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllVCvPhenotypes
	 *
	 */
	@Transactional
	public List<VCvPhenotypeByDataset> findAllVCvPhenotypes() throws DataAccessException {

		return findAllVCvPhenotypes(-1, -1);
	}

	/**
	 * JPQL Query - findAllVCvPhenotypes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public List<VCvPhenotypeByDataset> findAllVCvPhenotypes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVCvPhenotypesDataset", startResult, maxRows);
		return query.getResultList();
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPrimaryKey
	 *
	 */
	@Transactional
	public VCvPhenotypeByDataset findVCvPhenotypeByPrimaryKey(Integer cvTermId) throws DataAccessException {

		return findVCvPhenotypeByPrimaryKey(cvTermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPrimaryKey
	 *
	 */

	@Transactional
	public VCvPhenotypeByDataset findVCvPhenotypeByPrimaryKey(Integer cvTermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPhenotypeByPrimaryKeyDataset", startResult, maxRows, cvTermId,
					defaultdataset);
			return (VCvPhenotypeByDataset) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPhenotypeByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByDataset> findVCvPhenotypeByDefinitionContaining(String definition)
			throws DataAccessException {

		return findVCvPhenotypeByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByDataset> findVCvPhenotypeByDefinitionContaining(String definition, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByDefinitionContainingDataset", startResult, maxRows,
				definition, defaultdataset);
		return new LinkedHashSet<VCvPhenotypeByDataset>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VCvPhenotypeByDataset entity) {
		return true;
	}

	@Override
	public List getAllTerms() {

		return this.findAllVCvPhenotypes();
	}

	@Override
	public List getAllTerms(BigDecimal cvByName, BigDecimal organismByName) {
		return null;
	}

	@Override
	public List getAllTermsByDataset(String dataset) {

		Query query = createNamedQuery("findAllVCvPhenotypesDatasetByDataset", -1, -1, dataset);
		return query.getResultList();

	}

	@Override
	public List getAllTermsByDataset(BigDecimal cvByName, BigDecimal organismByName, String dataset) {

		return null;
	}

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

}
