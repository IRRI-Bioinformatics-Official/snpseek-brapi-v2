package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VIricstocksByPassport;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstocksByPassport entities.
 * 
 */
public interface VIricstocksByPassportDAO extends JpaDao<VIricstocksByPassport>, VarietyByPassportDAO {

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulation
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulation(String subpopulation)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulation
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulation(String subpopulation, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueIdContaining(String irisUniqueId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueIdContaining(String irisUniqueId,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByPrimaryKey
	 *
	 */
	public VIricstocksByPassport findVIricstocksByPassportByPrimaryKey(Integer iricStockpropId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByPrimaryKey
	 *
	 */
	public VIricstocksByPassport findVIricstocksByPassportByPrimaryKey(Integer iricStockpropId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueId(String irisUniqueId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueId(String irisUniqueId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulationContaining(String subpopulation_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulationContaining(String subpopulation_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByName
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByName
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPassports
	 *
	 */
	public Set<VIricstocksByPassport> findAllVIricstocksByPassports() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPassports
	 *
	 */
	public Set<VIricstocksByPassport> findAllVIricstocksByPassports(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByValue
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValue(String value) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByValue
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValue(String value, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByValueContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValueContaining(String value_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByValueContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValueContaining(String value_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIricStockId(Integer iricStockId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIricStockId(Integer iricStockId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByTypeId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByTypeId(java.math.BigDecimal typeId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByTypeId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByTypeId(BigDecimal typeId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountry
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountry(String oriCountry)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountry
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountry(String oriCountry, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByNameContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByNameContaining(String name_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByNameContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByNameContaining(String name_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountryContaining(String oriCountry_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountryContaining(String oriCountry_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockpropId
	 *
	 */
	public VIricstocksByPassport findVIricstocksByPassportByIricStockpropId(Integer iricStockpropId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockpropId
	 *
	 */
	public VIricstocksByPassport findVIricstocksByPassportByIricStockpropId(Integer iricStockpropId_1, int startResult,
			int maxRows) throws DataAccessException;

}