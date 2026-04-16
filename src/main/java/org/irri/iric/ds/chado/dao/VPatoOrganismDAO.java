package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VPatoOrganism;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VPatoOrganism entities.
 * 
 */
public interface VPatoOrganismDAO extends JpaDao<VPatoOrganism>, CvTermDAO {

	// /**
	// * JPQL Query - findVPatoOrganismByCommonName
	// *
	// */
	// public Set<VPatoOrganism> findVPatoOrganismByCommonName(String commonName)
	// throws DataAccessException;
	//
	// /**
	// * JPQL Query - findVPatoOrganismByCommonName
	// *
	// */
	// public Set<VPatoOrganism> findVPatoOrganismByCommonName(String commonName,
	// int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByPrimaryKey
	 *
	 */
	public VPatoOrganism findVPatoOrganismByPrimaryKey(BigDecimal cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByPrimaryKey
	 *
	 */
	public VPatoOrganism findVPatoOrganismByPrimaryKey(BigDecimal cvtermId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByOrganismId
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByOrganismId(BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByOrganismId
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvtermContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvtermContaining(String cvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvtermContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvtermContaining(String cvterm, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvterm
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvterm(String cvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvterm
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvterm(String cvterm_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVPatoOrganisms
	 *
	 */
	public Set<VPatoOrganism> findAllVPatoOrganisms() throws DataAccessException;

	/**
	 * JPQL Query - findAllVPatoOrganisms
	 *
	 */
	public Set<VPatoOrganism> findAllVPatoOrganisms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByAccession
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByAccession(String accession) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByAccession
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByAccession(String accession, int startResult, int maxRows)
			throws DataAccessException;
	//
	// /**
	// * JPQL Query - findVPatoOrganismByCvNameContaining
	// *
	// */
	// public Set<VPatoOrganism> findVPatoOrganismByCvNameContaining(String cvName)
	// throws DataAccessException;
	//
	// /**
	// * JPQL Query - findVPatoOrganismByCvNameContaining
	// *
	// */
	// public Set<VPatoOrganism> findVPatoOrganismByCvNameContaining(String cvName,
	// int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByAccessionContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByAccessionContaining(String accession_1) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByAccessionContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByAccessionContaining(String accession_1, int startResult, int maxRows)
			throws DataAccessException;

	// /**
	// * JPQL Query - findVPatoOrganismByCvName
	// *
	// */
	// public Set<VPatoOrganism> findVPatoOrganismByCvName(String cvName_1) throws
	// DataAccessException;
	//
	// /**
	// * JPQL Query - findVPatoOrganismByCvName
	// *
	// */
	// public Set<VPatoOrganism> findVPatoOrganismByCvName(String cvName_1, int
	// startResult, int maxRows) throws DataAccessException;
	//
	// /**
	// * JPQL Query - findVPatoOrganismByCommonNameContaining
	// *
	// */
	// public Set<VPatoOrganism> findVPatoOrganismByCommonNameContaining(String
	// commonName_1) throws DataAccessException;
	//
	// /**
	// * JPQL Query - findVPatoOrganismByCommonNameContaining
	// *
	// */
	// public Set<VPatoOrganism> findVPatoOrganismByCommonNameContaining(String
	// commonName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvtermId
	 *
	 */
	public VPatoOrganism findVPatoOrganismByCvtermId(BigDecimal cvtermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvtermId
	 *
	 */
	public VPatoOrganism findVPatoOrganismByCvtermId(BigDecimal cvtermId_1, int startResult, int maxRows)
			throws DataAccessException;

}