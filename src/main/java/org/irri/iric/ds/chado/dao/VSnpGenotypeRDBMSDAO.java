package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VSnpGenotypeRDBMS;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpGenotypeRDBMS entities.
 * 
 */
public interface VSnpGenotypeRDBMSDAO extends JpaDao<VSnpGenotypeRDBMS>, SnpsStringDAO {

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRDBMSStockId
	 *
	 */
	public VSnpGenotypeRDBMS findVSnpGenotypeRDBMSByRDBMSStockId(BigDecimal RDBMSStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRDBMSStockId
	 *
	 */
	public VSnpGenotypeRDBMS findVSnpGenotypeRDBMSByRDBMSStockId(BigDecimal RDBMSStockId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRefcallContaining
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByRefcallContaining(String refcall) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRefcallContaining
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByRefcallContaining(String refcall, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpGenotypeRDBMSs
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findAllVSnpGenotypeRDBMSs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpGenotypeRDBMSs
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findAllVSnpGenotypeRDBMSs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele2Containing
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele2Containing(String allele2) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele2Containing
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele2Containing(String allele2, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByMismatch
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByMismatch(BigDecimal mismatch) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByMismatch
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByMismatch(BigDecimal mismatch, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele1Containing
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele1Containing(String allele1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele1Containing
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele1Containing(String allele1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele2
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele2(String allele2_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele2
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele2(String allele2_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRefcall
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByRefcall(String refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRefcall
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByRefcall(String refcall_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByPrimaryKey
	 *
	 */
	public VSnpGenotypeRDBMS findVSnpGenotypeRDBMSByPrimaryKey(BigDecimal RDBMSStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByPrimaryKey
	 *
	 */
	public VSnpGenotypeRDBMS findVSnpGenotypeRDBMSByPrimaryKey(BigDecimal RDBMSStockId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele1
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele1(String allele1_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele1
	 *
	 */
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele1(String allele1_1, int startResult, int maxRows)
			throws DataAccessException;

}