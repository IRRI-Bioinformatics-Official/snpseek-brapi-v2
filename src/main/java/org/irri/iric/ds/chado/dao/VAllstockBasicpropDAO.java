package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VAllstockBasicprop;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VAllstockBasicprop entities.
 * 
 */
public interface VAllstockBasicpropDAO extends JpaDao<VAllstockBasicprop>, VarietyDAO {

	/**
	 * JPQL Query - findAllVAllstockBasicprops
	 *
	 */
	public Set<VAllstockBasicprop> findAllVAllstockBasicprops() throws DataAccessException;

	/**
	 * JPQL Query - findAllVAllstockBasicprops
	 *
	 */
	public Set<VAllstockBasicprop> findAllVAllstockBasicprops(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByIrisUniqueIdContaining
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByIrisUniqueIdContaining(String irisUniqueId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByIrisUniqueIdContaining
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByIrisUniqueIdContaining(String irisUniqueId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByOriCountryContaining
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByOriCountryContaining(String oriCountry)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByOriCountryContaining
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByOriCountryContaining(String oriCountry, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByBoxCode
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByBoxCode(String boxCode) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByBoxCode
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByBoxCode(String boxCode, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByNameContaining
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByNameContaining
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByBoxCodeContaining
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByBoxCodeContaining(String boxCode_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByBoxCodeContaining
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByBoxCodeContaining(String boxCode_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByPrimaryKey
	 *
	 */
	public VAllstockBasicprop findVAllstockBasicpropByPrimaryKey(BigDecimal allStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByPrimaryKey
	 *
	 */
	public VAllstockBasicprop findVAllstockBasicpropByPrimaryKey(BigDecimal allStockId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByIrisUniqueId
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByIrisUniqueId(String irisUniqueId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByIrisUniqueId
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByIrisUniqueId(String irisUniqueId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByOriCountry
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByOriCountry(String oriCountry_1) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByOriCountry
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByOriCountry(String oriCountry_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByallStockId
	 *
	 */
	public VAllstockBasicprop findVAllstockBasicpropByallStockId(BigDecimal allStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByallStockId
	 *
	 */
	public VAllstockBasicprop findVAllstockBasicpropByallStockId(BigDecimal allStockId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByName
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVAllstockBasicpropByName
	 *
	 */
	public Set<VAllstockBasicprop> findVAllstockBasicpropByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

}