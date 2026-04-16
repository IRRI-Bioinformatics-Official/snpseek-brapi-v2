package org.irri.iric.ds.chado.dao;

import java.util.List;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VCvPhenotype;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VCvPhenotype entities.
 * 
 */
public interface VCvPhenotypeDAO extends JpaDao<VCvPhenotype>, CvTermDAO {

	/**
	 * JPQL Query - findVCvPhenotypeByDefinition
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByDefinition(String definition) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByDefinition
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByName
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByName
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByNameContaining
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByNameContaining
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByNameContaining(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByCvTermId
	 *
	 */
	public VCvPhenotype findVCvPhenotypeByCvTermId(Integer cvTermId) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByCvTermId
	 *
	 */
	public VCvPhenotype findVCvPhenotypeByCvTermId(Integer cvTermId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPhenotypes
	 *
	 */
	public List<VCvPhenotype> findAllVCvPhenotypes() throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPhenotypes
	 *
	 */
	public List<VCvPhenotype> findAllVCvPhenotypes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPrimaryKey
	 *
	 */
	public VCvPhenotype findVCvPhenotypeByPrimaryKey(Integer cvTermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPrimaryKey
	 *
	 */
	public VCvPhenotype findVCvPhenotypeByPrimaryKey(Integer cvTermId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByDefinitionContaining
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByDefinitionContaining(String definition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByDefinitionContaining
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByDefinitionContaining(String definition_1, int startResult, int maxRows)
			throws DataAccessException;

}