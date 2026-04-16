
package org.irri.iric.ds.chado.dao;

import java.util.Calendar;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VGenotypeRun;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VGenotypeRun entities.
 * 
 */
public interface VGenotypeRunDAO extends JpaDao<VGenotypeRun>, GenotypeRunPlatformDAO {

	/**
	 * JPQL Query - findVGenotypeRunByDsDescriptionContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDsDescriptionContaining(String dsDescription) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDsDescriptionContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDsDescriptionContaining(String dsDescription, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVariantsetContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVariantsetContaining(String variantset) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVariantsetContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVariantsetContaining(String variantset, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDataset
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDataset(String dataset) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDataset
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDataset(String dataset, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVariantTypeId
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVariantTypeId(Integer variantTypeId) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVariantTypeId
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVariantTypeId(Integer variantTypeId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByPlatformId
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByPlatformId(Integer platformId) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByPlatformId
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByPlatformId(Integer platformId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDsDescription
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDsDescription(String dsDescription_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDsDescription
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDsDescription(String dsDescription_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDataLocation
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDataLocation(String dataLocation) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDataLocation
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDataLocation(String dataLocation, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformedBefore
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformedBefore(java.util.Calendar datePerformed)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformedBefore
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformedBefore(Calendar datePerformed, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVariantsetId
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVariantsetId(Integer variantsetId) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVariantsetId
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVariantsetId(Integer variantsetId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVariantset
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVariantset(String variantset_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVariantset
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVariantset(String variantset_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformed
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformed(java.util.Calendar datePerformed_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformed
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformed(Calendar datePerformed_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByGenotypeRunId
	 *
	 */
	public VGenotypeRun findVGenotypeRunByGenotypeRunId(Integer genotypeRunId) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByGenotypeRunId
	 *
	 */
	public VGenotypeRun findVGenotypeRunByGenotypeRunId(Integer genotypeRunId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVsDescriptionContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVsDescriptionContaining(String vsDescription) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVsDescriptionContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVsDescriptionContaining(String vsDescription, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatasetContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatasetContaining(String dataset_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatasetContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatasetContaining(String dataset_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformedAfter
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformedAfter(java.util.Calendar datePerformed_2)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatePerformedAfter
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatePerformedAfter(Calendar datePerformed_2, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVGenotypeRuns
	 *
	 */
	public Set<VGenotypeRun> findAllVGenotypeRuns() throws DataAccessException;

	/**
	 * JPQL Query - findAllVGenotypeRuns
	 *
	 */
	public Set<VGenotypeRun> findAllVGenotypeRuns(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVsDescription
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVsDescription(String vsDescription_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByVsDescription
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByVsDescription(String vsDescription_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByPrimaryKey
	 *
	 */
	public VGenotypeRun findVGenotypeRunByPrimaryKey(Integer genotypeRunId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByPrimaryKey
	 *
	 */
	public VGenotypeRun findVGenotypeRunByPrimaryKey(Integer genotypeRunId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDataLocationContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDataLocationContaining(String dataLocation_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDataLocationContaining
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDataLocationContaining(String dataLocation_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatasetId
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatasetId(Integer datasetId) throws DataAccessException;

	/**
	 * JPQL Query - findVGenotypeRunByDatasetId
	 *
	 */
	public Set<VGenotypeRun> findVGenotypeRunByDatasetId(Integer datasetId, int startResult, int maxRows)
			throws DataAccessException;

}