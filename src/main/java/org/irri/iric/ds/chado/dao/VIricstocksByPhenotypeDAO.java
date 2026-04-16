package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VIricstocksByPhenotype;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstocksByPhenotype entities.
 * 
 */
public interface VIricstocksByPhenotypeDAO extends JpaDao<VIricstocksByPhenotype>, VarietyByPhenotypeDAO {

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByName
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByName
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPrimaryKey
	 *
	 */
	public VIricstocksByPhenotype findVIricstocksByPhenotypeByPrimaryKey(BigDecimal iricStockPhenotypeId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPrimaryKey
	 *
	 */
	public VIricstocksByPhenotype findVIricstocksByPhenotypeByPrimaryKey(BigDecimal iricStockPhenotypeId,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountry
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountry(String oriCountry)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountry
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountry(String oriCountry, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockId(Integer iricStockId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockId(Integer iricStockId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockPhenotypeId
	 *
	 */
	public VIricstocksByPhenotype findVIricstocksByPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockPhenotypeId
	 *
	 */
	public VIricstocksByPhenotype findVIricstocksByPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulation
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulation(String subpopulation)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulation
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulation(String subpopulation, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPhenotypeId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByPhenotypeId(java.math.BigDecimal phenotypeId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPhenotypeId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByPhenotypeId(BigDecimal phenotypeId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValue
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValue(String qualValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValue
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValue(String qualValue, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValueContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValueContaining(String qualValue_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValueContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValueContaining(String qualValue_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueId(String irisUniqueId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueId(String irisUniqueId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQuanValue
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQuanValue(Integer quanValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQuanValue
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQuanValue(Integer quanValue, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountryContaining(String oriCountry_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountryContaining(String oriCountry_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueIdContaining(String irisUniqueId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueIdContaining(String irisUniqueId_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByNameContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByNameContaining(String name_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByNameContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByNameContaining(String name_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulationContaining(String subpopulation_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulationContaining(String subpopulation_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPhenotypes
	 *
	 */
	public Set<VIricstocksByPhenotype> findAllVIricstocksByPhenotypes() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPhenotypes
	 *
	 */
	public Set<VIricstocksByPhenotype> findAllVIricstocksByPhenotypes(int startResult, int maxRows)
			throws DataAccessException;

}