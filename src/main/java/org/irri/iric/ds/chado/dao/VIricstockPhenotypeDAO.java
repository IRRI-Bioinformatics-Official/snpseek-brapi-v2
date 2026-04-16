package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VIricstockPhenotype;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockPhenotype entities.
 * 
 */
public interface VIricstockPhenotypeDAO extends JpaDao<VIricstockPhenotype>, PhenotypeDAO {

	/**
	 * JPQL Query - findAllVIricstockPhenotypes
	 *
	 */
	public Set<VIricstockPhenotype> findAllVIricstockPhenotypes() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypes
	 *
	 */
	public Set<VIricstockPhenotype> findAllVIricstockPhenotypes(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByDefinitionContaining
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByDefinitionContaining(String definition)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByDefinitionContaining
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByDefinitionContaining(String definition, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByQualValue
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQualValue(String qualValue) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByQualValue
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQualValue(String qualValue, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByIricStockPhenotypeId
	 *
	 */
	public VIricstockPhenotype findVIricstockPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByIricStockPhenotypeId
	 *
	 */
	public VIricstockPhenotype findVIricstockPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByPrimaryKey
	 *
	 */
	public VIricstockPhenotype findVIricstockPhenotypeByPrimaryKey(BigDecimal bigDecimal) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByPrimaryKey
	 *
	 */
	public VIricstockPhenotype findVIricstockPhenotypeByPrimaryKey(BigDecimal iricStockPhenotypeId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByIricStockId
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByIricStockId(java.math.BigDecimal iricStockId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByIricStockId
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByIricStockId(BigDecimal iricStockId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByDefinition
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByDefinition(String definition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByDefinition
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByDefinition(String definition_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByName
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByName
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByNameContaining
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByNameContaining
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByNameContaining(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByQualValueContaining
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQualValueContaining(String qualValue_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByQualValueContaining
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQualValueContaining(String qualValue_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByQuanValue
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQuanValue(java.math.BigDecimal quanValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeByQuanValue
	 *
	 */
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQuanValue(BigDecimal quanValue, int startResult,
			int maxRows) throws DataAccessException;

}