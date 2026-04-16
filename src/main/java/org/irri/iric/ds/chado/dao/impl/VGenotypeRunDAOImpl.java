
package org.irri.iric.ds.chado.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.AppContext;
import org.irri.iric.ds.chado.dao.VGenotypeRunDAO;
import org.irri.iric.ds.chado.domain.model.VGenotypeRun;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VGenotypeRun entities.
 * 
 */
@Repository("GenotypeRunPlatformDAO")

@Transactional
public class VGenotypeRunDAOImpl extends AbstractJpaDao<VGenotypeRun> implements VGenotypeRunDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VGenotypeRun.class }));

	/**
	 * EntityManager injected by Spring for persistence unit snpseekv3
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VGenotypeRunDAOImpl
	 *
	 */
	public VGenotypeRunDAOImpl() {
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
	 * JPQL Query - findVGenotypeRunByDsDescriptionContaining
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDsDescriptionContaining(String dsDescription)
			throws DataAccessException {

		return findVGenotypeRunByDsDescriptionContaining(dsDescription, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDsDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDsDescriptionContaining(String dsDescription, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDsDescriptionContaining", startResult, maxRows,
				dsDescription);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByVariantsetContaining
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVariantsetContaining(String variantset) throws DataAccessException {

		return findVGenotypeRunByVariantsetContaining(variantset, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByVariantsetContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVariantsetContaining(String variantset, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByVariantsetContaining", startResult, maxRows, variantset);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByDataset
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDataset(String dataset) throws DataAccessException {

		return findVGenotypeRunByDataset(dataset, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDataset
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDataset(String dataset, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDataset", startResult, maxRows, dataset);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByVariantTypeId
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVariantTypeId(Integer variantTypeId) throws DataAccessException {

		return findVGenotypeRunByVariantTypeId(variantTypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByVariantTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVariantTypeId(Integer variantTypeId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByVariantTypeId", startResult, maxRows, variantTypeId);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByPlatformId
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByPlatformId(Integer platformId) throws DataAccessException {

		return findVGenotypeRunByPlatformId(platformId, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByPlatformId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByPlatformId(Integer platformId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByPlatformId", startResult, maxRows, platformId);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByDsDescription
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDsDescription(String dsDescription) throws DataAccessException {

		return findVGenotypeRunByDsDescription(dsDescription, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDsDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDsDescription(String dsDescription, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDsDescription", startResult, maxRows, dsDescription);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByDataLocation
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDataLocation(String dataLocation) throws DataAccessException {

		return findVGenotypeRunByDataLocation(dataLocation, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDataLocation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDataLocation(String dataLocation, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDataLocation", startResult, maxRows, dataLocation);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformedBefore
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformedBefore(java.util.Calendar datePerformed)
			throws DataAccessException {

		return findVGenotypeRunByDatePerformedBefore(datePerformed, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformedBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformedBefore(java.util.Calendar datePerformed, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDatePerformedBefore", startResult, maxRows, datePerformed);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByVariantsetId
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVariantsetId(Integer variantsetId) throws DataAccessException {

		return findVGenotypeRunByVariantsetId(variantsetId, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByVariantsetId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVariantsetId(Integer variantsetId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByVariantsetId", startResult, maxRows, variantsetId);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByVariantset
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVariantset(String variantset) throws DataAccessException {

		return findVGenotypeRunByVariantset(variantset, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByVariantset
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVariantset(String variantset, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByVariantset", startResult, maxRows, variantset);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformed
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformed(java.util.Calendar datePerformed)
			throws DataAccessException {

		return findVGenotypeRunByDatePerformed(datePerformed, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformed
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformed(java.util.Calendar datePerformed, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDatePerformed", startResult, maxRows, datePerformed);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByGenotypeRunId
	 *
	 */
	@Transactional
	public VGenotypeRun findVGenotypeRunByGenotypeRunId(Integer genotypeRunId) throws DataAccessException {

		return findVGenotypeRunByGenotypeRunId(genotypeRunId, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByGenotypeRunId
	 *
	 */

	@Transactional
	public VGenotypeRun findVGenotypeRunByGenotypeRunId(Integer genotypeRunId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGenotypeRunByGenotypeRunId", startResult, maxRows, genotypeRunId);
			return (VGenotypeRun) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGenotypeRunByVsDescriptionContaining
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVsDescriptionContaining(String vsDescription)
			throws DataAccessException {

		return findVGenotypeRunByVsDescriptionContaining(vsDescription, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByVsDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVsDescriptionContaining(String vsDescription, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByVsDescriptionContaining", startResult, maxRows,
				vsDescription);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatasetContaining
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatasetContaining(String dataset) throws DataAccessException {

		return findVGenotypeRunByDatasetContaining(dataset, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatasetContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatasetContaining(String dataset, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDatasetContaining", startResult, maxRows, dataset);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformedAfter
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformedAfter(java.util.Calendar datePerformed)
			throws DataAccessException {

		return findVGenotypeRunByDatePerformedAfter(datePerformed, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformedAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformedAfter(java.util.Calendar datePerformed, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDatePerformedAfter", startResult, maxRows, datePerformed);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVGenotypeRuns
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findAllVGenotypeRuns() throws DataAccessException {

		return findAllVGenotypeRuns(-1, -1);
	}

	/**
	 * JPQL Query - findAllVGenotypeRuns
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findAllVGenotypeRuns(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVGenotypeRuns", startResult, maxRows);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByVsDescription
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVsDescription(String vsDescription) throws DataAccessException {

		return findVGenotypeRunByVsDescription(vsDescription, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByVsDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByVsDescription(String vsDescription, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByVsDescription", startResult, maxRows, vsDescription);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByPrimaryKey
	 *
	 */
	@Transactional
	public VGenotypeRun findVGenotypeRunByPrimaryKey(Integer genotypeRunId) throws DataAccessException {

		return findVGenotypeRunByPrimaryKey(genotypeRunId, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByPrimaryKey
	 *
	 */

	@Transactional
	public VGenotypeRun findVGenotypeRunByPrimaryKey(Integer genotypeRunId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGenotypeRunByPrimaryKey", startResult, maxRows, genotypeRunId);
			return (VGenotypeRun) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGenotypeRunByDataLocationContaining
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDataLocationContaining(String dataLocation) throws DataAccessException {

		return findVGenotypeRunByDataLocationContaining(dataLocation, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDataLocationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDataLocationContaining(String dataLocation, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDataLocationContaining", startResult, maxRows, dataLocation);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatasetId
	 *
	 */
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatasetId(Integer datasetId) throws DataAccessException {

		return findVGenotypeRunByDatasetId(datasetId, -1, -1);
	}

	/**
	 * JPQL Query - findVGenotypeRunByDatasetId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGenotypeRun> findVGenotypeRunByDatasetId(Integer datasetId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVGenotypeRunByDatasetId", startResult, maxRows, datasetId);
		return new LinkedHashSet<VGenotypeRun>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VGenotypeRun entity) {
		return true;
	}

	@Override
	public Set getPlatforms(String type) {
		// return findAllVGenotypeRuns();

		Set platforms = new LinkedHashSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			if (type == null || run.getVariantType().toUpperCase().equals(type.toUpperCase()))
				platforms.add(run);
		}
		return platforms;

		// return null;
	}

	@Override
	public Set getPlatforms(Set setds, Set setvs, String type) {
		
		// return findAllVGenotypeRuns();

		Set platforms = new LinkedHashSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			if (type == null || run.getVariantType().toUpperCase().equals(type.toUpperCase())) {
				if (setds.contains(run.getDataset()) && setvs.contains(run.getVariantset()))
					platforms.add(run);
			}

		}
		return platforms;

		// return null;
	}

	@Override
	public Set getPlatformRuns(Integer platformId) {
		
		Set platforms = new LinkedHashSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			if (run.getPlatformId().equals(platformId))
				platforms.add(run);
		}
		return platforms;
	}
	
	@Override
	public List getRunsByType(String type) {
		
		List set = new  ArrayList();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			if (type == null || run.getVariantType().toUpperCase().equals(type.toUpperCase()))
				set.add(run);
		}
		return set;
	}

	@Override
	public Set getDatasets(String type) {
		
		Set set = new TreeSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			if (type == null || run.getVariantType().toUpperCase().equals(type.toUpperCase()))
				set.add(run.getDataset());
		}
		return set;
	}
	
	@Override
	public Set getDatasets(String type,String reference) {
		
		Set set = new TreeSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			if (type == null || run.getVariantType().toUpperCase().equals(type.toUpperCase())
				&&
				(reference == null || run.getCommonname().toUpperCase().equals(reference.toUpperCase())))
			
			set.add(run.getDataset());
		}
		
		AppContext.debug("getDatasets("+type+" , "+reference+")=" + set);
		return set;
	}


	@Override
	public Set getDatasets() {
		
		Set set = new TreeSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			set.add(run.getDataset());
		}
		return set;
	}

	@Override
	public Set getVariantsets() {
		Set set = new TreeSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			set.add(run.getVariantset());
		}
		return set;
	}

	@Override
	public Set getVariantsets(String dataset) {
		Set set = new TreeSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			if (run.getDataset().equals(dataset))
				set.add(run.getVariantset());
		}
		return set;
	}

	@Override
	public Set getVariantsets(Set dataset, String type) {
		Set set = new TreeSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			if (dataset.contains(run.getDataset()) && run.getVariantType().compareToIgnoreCase(type) == 0)
				set.add(run.getVariantset());
		}
		return set;
	}

	@Override
	public Set getVariantsets(String dataset, String type) {
		Set set = new TreeSet();
		for (VGenotypeRun run : findAllVGenotypeRuns()) {
			if (dataset.equals(run.getDataset()) && run.getVariantType().compareToIgnoreCase(type) == 0)
				set.add(run.getVariantset());
		}
		return set;
	}

}
