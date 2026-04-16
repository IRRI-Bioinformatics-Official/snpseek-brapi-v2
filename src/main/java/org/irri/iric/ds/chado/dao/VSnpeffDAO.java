package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VSnpeff;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpeff entities.
 * 
 */
public interface VSnpeffDAO extends JpaDao<VSnpeff>, SnpsEffectDAO {

	/**
	 * JPQL Query - findVSnpeffByPosition
	 *
	 */
	public Set<VSnpeff> findVSnpeffByPosition(java.math.BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffByPosition
	 *
	 */
	public Set<VSnpeff> findVSnpeffByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffByAnnotationContaining
	 *
	 */
	public Set<VSnpeff> findVSnpeffByAnnotationContaining(String annotation) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffByAnnotationContaining
	 *
	 */
	public Set<VSnpeff> findVSnpeffByAnnotationContaining(String annotation, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffBySnpFeatureId
	 *
	 */
	public VSnpeff findVSnpeffBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffBySnpFeatureId
	 *
	 */
	public VSnpeff findVSnpeffBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffByChromosome
	 *
	 */
	public Set<VSnpeff> findVSnpeffByChromosome(Integer chromosome) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffByChromosome
	 *
	 */
	public Set<VSnpeff> findVSnpeffByChromosome(Integer chromosome, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffByAnnotation
	 *
	 */
	public Set<VSnpeff> findVSnpeffByAnnotation(String annotation_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffByAnnotation
	 *
	 */
	public Set<VSnpeff> findVSnpeffByAnnotation(String annotation_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpeffs
	 *
	 */
	public Set<VSnpeff> findAllVSnpeffs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpeffs
	 *
	 */
	public Set<VSnpeff> findAllVSnpeffs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffByPrimaryKey
	 *
	 */
	public VSnpeff findVSnpeffByPrimaryKey(BigDecimal snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpeffByPrimaryKey
	 *
	 */
	public VSnpeff findVSnpeffByPrimaryKey(BigDecimal snpFeatureId_1, int startResult, int maxRows)
			throws DataAccessException;

}