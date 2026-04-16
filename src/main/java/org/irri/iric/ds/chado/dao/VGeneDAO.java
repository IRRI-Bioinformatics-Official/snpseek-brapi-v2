package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VGene;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VGene entities.
 * 
 */
public interface VGeneDAO extends JpaDao<VGene>, GeneDAO{

	/**
	 * JPQL Query - findVGeneByPhase
	 *
	 */
	public Set<VGene> findVGeneByPhase(java.math.BigDecimal phase) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByPhase
	 *
	 */
	public Set<VGene> findVGeneByPhase(BigDecimal phase, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVGenes
	 *
	 */
	public Set<VGene> findAllVGenes() throws DataAccessException;

	/**
	 * JPQL Query - findAllVGenes
	 *
	 */
	public Set<VGene> findAllVGenes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByChr
	 *
	 */
	public Set<VGene> findVGeneByChr(java.math.BigDecimal chr) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByChr
	 *
	 */
	public Set<VGene> findVGeneByChr(BigDecimal chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByName
	 *
	 */
	public Set<VGene> findVGeneByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByName
	 *
	 */
	public Set<VGene> findVGeneByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByFmin
	 *
	 */
	public Set<VGene> findVGeneByFmin(java.math.BigDecimal fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByFmin
	 *
	 */
	public Set<VGene> findVGeneByFmin(BigDecimal fmin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByPrimaryKey
	 *
	 */
	public VGene findVGeneByPrimaryKey(Integer geneId) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByPrimaryKey
	 *
	 */
	public VGene findVGeneByPrimaryKey(Integer geneId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByFmax
	 *
	 */
	public Set<VGene> findVGeneByFmax(java.math.BigDecimal fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByFmax
	 *
	 */
	public Set<VGene> findVGeneByFmax(BigDecimal fmax, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByNameContaining
	 *
	 */
	public Set<VGene> findVGeneByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByNameContaining
	 *
	 */
	public Set<VGene> findVGeneByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByGeneId
	 *
	 */
	public VGene findVGeneByGeneId(Integer geneId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByGeneId
	 *
	 */
	public VGene findVGeneByGeneId(Integer geneId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByStrand
	 *
	 */
	public Set<VGene> findVGeneByStrand(java.math.BigDecimal strand) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByStrand
	 *
	 */
	public Set<VGene> findVGeneByStrand(BigDecimal strand, int startResult, int maxRows) throws DataAccessException;

}