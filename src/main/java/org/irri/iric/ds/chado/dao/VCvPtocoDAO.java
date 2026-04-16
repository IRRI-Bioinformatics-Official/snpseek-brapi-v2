package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VCvPtoco;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VCvPtoco entities.
 * 
 */
public interface VCvPtocoDAO extends JpaDao<VCvPtoco>, CvTermDAO {

	/**
	 * JPQL Query - findVCvPtocoByDb
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByDb(String db) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByDb
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByDb(String db, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByDefinition
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByDefinition(String definition) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByDefinition
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPtocos
	 *
	 */
	public Set<VCvPtoco> findAllVCvPtocos() throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPtocos
	 *
	 */
	public Set<VCvPtoco> findAllVCvPtocos(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByCvtermContaining
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByCvtermContaining(String cvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByCvtermContaining
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByCvtermContaining(String cvterm, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByPrimaryKey
	 *
	 */
	public VCvPtoco findVCvPtocoByPrimaryKey(BigDecimal cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByPrimaryKey
	 *
	 */
	public VCvPtoco findVCvPtocoByPrimaryKey(BigDecimal cvtermId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByAccessionContaining
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByAccessionContaining(String accession) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByAccessionContaining
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByAccessionContaining(String accession, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByCvterm
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByCvterm(String cvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByCvterm
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByCvterm(String cvterm_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByDbContaining
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByDbContaining(String db_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByDbContaining
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByDbContaining(String db_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByAccession
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByAccession(String accession_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByAccession
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByAccession(String accession_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByDefinitionContaining
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByDefinitionContaining(String definition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByDefinitionContaining
	 *
	 */
	public Set<VCvPtoco> findVCvPtocoByDefinitionContaining(String definition_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByCvtermId
	 *
	 */
	public VCvPtoco findVCvPtocoByCvtermId(BigDecimal cvtermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPtocoByCvtermId
	 *
	 */
	public VCvPtoco findVCvPtocoByCvtermId(BigDecimal cvtermId_1, int startResult, int maxRows)
			throws DataAccessException;

}