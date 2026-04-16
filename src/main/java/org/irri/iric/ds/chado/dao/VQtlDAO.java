package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VQtl;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VQtl entities.
 * 
 */
public interface VQtlDAO extends JpaDao<VQtl>, QtlDAO {

	/**
	 * JPQL Query - findVQtlByTraitNameContaining
	 *
	 */
	public Set<VQtl> findVQtlByTraitNameContaining(String traitName) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByTraitNameContaining
	 *
	 */
	public Set<VQtl> findVQtlByTraitNameContaining(String traitName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByStart
	 *
	 */
	public Set<VQtl> findVQtlByStart(BigDecimal start) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByStart
	 *
	 */
	public Set<VQtl> findVQtlByStart(BigDecimal start, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByQtlId
	 *
	 */
	public VQtl findVQtlByQtlId(BigDecimal qtlId) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByQtlId
	 *
	 */
	public VQtl findVQtlByQtlId(BigDecimal qtlId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVQtls
	 *
	 */
	public Set<VQtl> findAllVQtls() throws DataAccessException;

	/**
	 * JPQL Query - findAllVQtls
	 *
	 */
	public Set<VQtl> findAllVQtls(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByNotesContaining
	 *
	 */
	public Set<VQtl> findVQtlByNotesContaining(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByNotesContaining
	 *
	 */
	public Set<VQtl> findVQtlByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByDbId
	 *
	 */
	public Set<VQtl> findVQtlByDbId(BigDecimal dbId) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByDbId
	 *
	 */
	public Set<VQtl> findVQtlByDbId(BigDecimal dbId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByNameContaining
	 *
	 */
	public Set<VQtl> findVQtlByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByNameContaining
	 *
	 */
	public Set<VQtl> findVQtlByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByTraitName
	 *
	 */
	public Set<VQtl> findVQtlByTraitName(String traitName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByTraitName
	 *
	 */
	public Set<VQtl> findVQtlByTraitName(String traitName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByNotes
	 *
	 */
	public Set<VQtl> findVQtlByNotes(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByNotes
	 *
	 */
	public Set<VQtl> findVQtlByNotes(String notes_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByEnd
	 *
	 */
	public Set<VQtl> findVQtlByEnd(BigDecimal end) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByEnd
	 *
	 */
	public Set<VQtl> findVQtlByEnd(BigDecimal end, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByName
	 *
	 */
	public Set<VQtl> findVQtlByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByName
	 *
	 */
	public Set<VQtl> findVQtlByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByChromosome
	 *
	 */
	public Set<VQtl> findVQtlByChromosome(Integer chromosome) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByChromosome
	 *
	 */
	public Set<VQtl> findVQtlByChromosome(Integer chromosome, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByPrimaryKey
	 *
	 */
	public VQtl findVQtlByPrimaryKey(BigDecimal qtlId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVQtlByPrimaryKey
	 *
	 */
	public VQtl findVQtlByPrimaryKey(BigDecimal qtlId_1, int startResult, int maxRows) throws DataAccessException;

}