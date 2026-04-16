package org.irri.iric.ds.chado.dao;

import java.util.List;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VCvPassport;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VCvPassport entities.
 * 
 */
public interface VCvPassportDAO extends JpaDao<VCvPassport>, CvTermDAO {

	/**
	 * JPQL Query - findVCvPassportByPrimaryKey
	 *
	 */
	public VCvPassport findVCvPassportByPrimaryKey(Integer cvTermId) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByPrimaryKey
	 *
	 */
	public VCvPassport findVCvPassportByPrimaryKey(Integer cvTermId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByDefinition
	 *
	 */
	public Set<VCvPassport> findVCvPassportByDefinition(String definition) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByDefinition
	 *
	 */
	public Set<VCvPassport> findVCvPassportByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByNameContaining
	 *
	 */
	public Set<VCvPassport> findVCvPassportByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByNameContaining
	 *
	 */
	public Set<VCvPassport> findVCvPassportByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByDefinitionContaining
	 *
	 */
	public Set<VCvPassport> findVCvPassportByDefinitionContaining(String definition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByDefinitionContaining
	 *
	 */
	public Set<VCvPassport> findVCvPassportByDefinitionContaining(String definition_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPassports
	 *
	 */
	public List<VCvPassport> findAllVCvPassports() throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPassports
	 *
	 */
	public List<VCvPassport> findAllVCvPassports(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByName
	 *
	 */
	public Set<VCvPassport> findVCvPassportByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByName
	 *
	 */
	public Set<VCvPassport> findVCvPassportByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByCvTermId
	 *
	 */
	public VCvPassport findVCvPassportByCvTermId(Integer cvTermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByCvTermId
	 *
	 */
	public VCvPassport findVCvPassportByCvTermId(Integer cvTermId_1, int startResult, int maxRows)
			throws DataAccessException;

}