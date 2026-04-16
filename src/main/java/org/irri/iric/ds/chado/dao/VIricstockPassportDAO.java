package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VIricstockPassport;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockPassport entities.
 * 
 */
public interface VIricstockPassportDAO extends JpaDao<VIricstockPassport>, IricstockPassportDAO {

	/**
	 * JPQL Query - findVIricstockPassportByName
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByName
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByDefinition
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByDefinition(String definition) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByDefinition
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByValue
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByValue(String value) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByValue
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByValue(String value, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByIricStockpropId
	 *
	 */
	public VIricstockPassport findVIricstockPassportByIricStockpropId(Integer iricStockpropId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByIricStockpropId
	 *
	 */
	public VIricstockPassport findVIricstockPassportByIricStockpropId(Integer iricStockpropId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByPrimaryKey
	 *
	 */
	public VIricstockPassport findVIricstockPassportByPrimaryKey(BigDecimal iricStockpropId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByPrimaryKey
	 *
	 */
	public VIricstockPassport findVIricstockPassportByPrimaryKey(BigDecimal iricStockpropId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByIricStockId
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByIricStockId(java.math.BigDecimal iricStockId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByIricStockId
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByIricStockId(BigDecimal iricStockId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByNameContaining
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByNameContaining
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByNameContaining(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByDefinitionContaining
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByDefinitionContaining(String definition_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByDefinitionContaining
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByDefinitionContaining(String definition_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByValueContaining
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByValueContaining(String value_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportByValueContaining
	 *
	 */
	public Set<VIricstockPassport> findVIricstockPassportByValueContaining(String value_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPassports
	 *
	 */
	public Set<VIricstockPassport> findAllVIricstockPassports() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPassports
	 *
	 */
	public Set<VIricstockPassport> findAllVIricstockPassports(int startResult, int maxRows) throws DataAccessException;

}