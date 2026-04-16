package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VIricstockPassportValues;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockPassportValues entities.
 * 
 */
public interface VIricstockPassportValuesDAO extends JpaDao<VIricstockPassportValues>, CvTermUniqueValuesDAO {

	/**
	 * JPQL Query - findVIricstockPassportValuesByTypeId
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(BigDecimal typeId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByTypeId
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(BigDecimal typeId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByPrimaryKey
	 *
	 */
	public VIricstockPassportValues findVIricstockPassportValuesByPrimaryKey(String value, BigDecimal typeId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByPrimaryKey
	 *
	 */
	public VIricstockPassportValues findVIricstockPassportValuesByPrimaryKey(String value, BigDecimal typeId_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByValueContaining
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValueContaining(String value_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByValueContaining
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValueContaining(String value_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPassportValuess
	 *
	 */
	public Set<VIricstockPassportValues> findAllVIricstockPassportValuess() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPassportValuess
	 *
	 */
	public Set<VIricstockPassportValues> findAllVIricstockPassportValuess(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByValue
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValue(String value_2) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByValue
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValue(String value_2, int startResult,
			int maxRows) throws DataAccessException;

}