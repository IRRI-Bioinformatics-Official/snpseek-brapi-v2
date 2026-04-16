package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VIricstocksByPtoco;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstocksByPtoco entities.
 * 
 */
public interface VIricstocksByPtocoDAO extends JpaDao<VIricstocksByPtoco>, VarietyByPhenotypeDAO {

	/**
	 * JPQL Query - findVIricstocksByPtocoByNameContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByNameContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIrisUniqueIdContaining(String irisUniqueId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIrisUniqueIdContaining(String irisUniqueId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByDbContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDbContaining(String db) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByDbContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDbContaining(String db, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByQuanValue
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQuanValue(java.math.BigDecimal quanValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByQuanValue
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQuanValue(BigDecimal quanValue, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByOriCountry
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByOriCountry(String oriCountry) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByOriCountry
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByOriCountry(String oriCountry, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvterm
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvterm(String cvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvterm
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvterm(String cvterm, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByQualValueContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQualValueContaining(String qualValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByQualValueContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQualValueContaining(String qualValue, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByDefinitionContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDefinitionContaining(String definition)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByDefinitionContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDefinitionContaining(String definition, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByAccessionContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByAccessionContaining(String accession)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByAccessionContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByAccessionContaining(String accession, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByAccession
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByAccession(String accession_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByAccession
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByAccession(String accession_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoBySubpopulationContaining(String subpopulation)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoBySubpopulationContaining(String subpopulation,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvtermContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvtermContaining(String cvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvtermContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvtermContaining(String cvterm_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPtocos
	 *
	 */
	public Set<VIricstocksByPtoco> findAllVIricstocksByPtocos() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPtocos
	 *
	 */
	public Set<VIricstocksByPtoco> findAllVIricstocksByPtocos(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByName
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByName
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIrisUniqueId(String irisUniqueId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIrisUniqueId(String irisUniqueId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByPrimaryKey
	 *
	 */
	public VIricstocksByPtoco findVIricstocksByPtocoByPrimaryKey(BigDecimal iricStockPhenotypeId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByPrimaryKey
	 *
	 */
	public VIricstocksByPtoco findVIricstocksByPtocoByPrimaryKey(BigDecimal iricStockPhenotypeId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByPhenotypeId
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByPhenotypeId(java.math.BigDecimal phenotypeId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByPhenotypeId
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByPhenotypeId(BigDecimal phenotypeId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByQualValue
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQualValue(String qualValue_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByQualValue
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQualValue(String qualValue_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByOriCountryContaining(String oriCountry_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByOriCountryContaining(String oriCountry_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByDb
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDb(String db_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByDb
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDb(String db_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByIricStockId
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIricStockId(BigDecimal iricStockId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByIricStockId
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIricStockId(BigDecimal iricStockId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoBySubpopulation
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoBySubpopulation(String subpopulation_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoBySubpopulation
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoBySubpopulation(String subpopulation_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByDefinition
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDefinition(String definition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByDefinition
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDefinition(String definition_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvtermId
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvtermId(BigDecimal cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvtermId
	 *
	 */
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvtermId(BigDecimal cvtermId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByIricStockPhenotypeId
	 *
	 */
	public VIricstocksByPtoco findVIricstocksByPtocoByIricStockPhenotypeId(BigDecimal iricStockPhenotypeId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPtocoByIricStockPhenotypeId
	 *
	 */
	public VIricstocksByPtoco findVIricstocksByPtocoByIricStockPhenotypeId(BigDecimal iricStockPhenotypeId_1,
			int startResult, int maxRows) throws DataAccessException;

}