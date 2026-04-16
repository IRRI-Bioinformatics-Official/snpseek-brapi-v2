
package org.irri.iric.ds.chado.dao;

import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VAllsampleBasicprop;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VAllsampleBasicprop entities.
 * 
 */
public interface VAllsampleBasicpropDAO extends JpaDao<VAllsampleBasicprop>, StockSampleDAO {

	/**
	 * JPQL Query - findVAllsampleBasicpropByAssay
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByAssay(String assay) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByAssay
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByAssay(String assay, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByOriCountry
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByOriCountry(String oriCountry) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByOriCountry
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByOriCountry(String oriCountry, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByPrimaryKey
	 *
	 */
	public VAllsampleBasicprop findVAllsampleBasicpropByPrimaryKey(Integer stockSampleId) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByPrimaryKey
	 *
	 */
	public VAllsampleBasicprop findVAllsampleBasicpropByPrimaryKey(Integer stockSampleId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByNameContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByNameContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByStockSampleId
	 *
	 */
	public VAllsampleBasicprop findVAllsampleBasicpropByStockSampleId(Integer stockSampleId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByStockSampleId
	 *
	 */
	public VAllsampleBasicprop findVAllsampleBasicpropByStockSampleId(Integer stockSampleId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByAssayContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByAssayContaining(String assay_1) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByAssayContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByAssayContaining(String assay_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropBySubpopulationContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropBySubpopulationContaining(String subpopulation)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropBySubpopulationContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropBySubpopulationContaining(String subpopulation,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByGsAccession
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByGsAccession(String gsAccession) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByGsAccession
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByGsAccession(String gsAccession, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByStockId
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByStockId(Integer stockId) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByStockId
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByStockId(Integer stockId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropBySubpopulation
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropBySubpopulation(String subpopulation_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropBySubpopulation
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropBySubpopulation(String subpopulation_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVAllsampleBasicprops
	 *
	 */
	public Set<VAllsampleBasicprop> findAllVAllsampleBasicprops() throws DataAccessException;

	/**
	 * JPQL Query - findAllVAllsampleBasicprops
	 *
	 */
	public Set<VAllsampleBasicprop> findAllVAllsampleBasicprops(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByDataset
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByDataset(String dataset) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByDataset
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByDataset(String dataset, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByHdf5Index
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByHdf5Index(Integer hdf5Index) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByHdf5Index
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByHdf5Index(Integer hdf5Index, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByBoxCodeContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByBoxCodeContaining(String boxCode)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByBoxCodeContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByBoxCodeContaining(String boxCode, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByName
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByName
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByGsAccessionContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByGsAccessionContaining(String gsAccession_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByGsAccessionContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByGsAccessionContaining(String gsAccession_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByOriCountryContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByOriCountryContaining(String oriCountry_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByOriCountryContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByOriCountryContaining(String oriCountry_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByDatasetContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByDatasetContaining(String dataset_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByDatasetContaining
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByDatasetContaining(String dataset_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByBoxCode
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByBoxCode(String boxCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findVAllsampleBasicpropByBoxCode
	 *
	 */
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByBoxCode(String boxCode_1, int startResult, int maxRows)
			throws DataAccessException;

}