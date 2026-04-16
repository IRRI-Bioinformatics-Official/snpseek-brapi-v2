package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VIricstockPhenotypeQualval;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockPhenotypeQualval entities.
 * 
 */
public interface VIricstockPhenotypeQualvalDAO extends JpaDao<VIricstockPhenotypeQualval>, CvTermUniqueValuesDAO {

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeQualval findVIricstockPhenotypeQualvalByPrimaryKey(String qualValue,
			BigDecimal phenotypeId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeQualval findVIricstockPhenotypeQualvalByPrimaryKey(String qualValue,
			BigDecimal phenotypeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValueContaining
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValueContaining(String qualValue_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValueContaining
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValueContaining(String qualValue_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByPhenotypeId(BigDecimal phenotypeId_1,
			String dataset) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByPhenotypeId(BigDecimal phenotypeId_1,
			String dataset, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQualvals
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findAllVIricstockPhenotypeQualvals() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQualvals
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findAllVIricstockPhenotypeQualvals(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValue
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValue(String qualValue_2)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValue
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValue(String qualValue_2,
			int startResult, int maxRows) throws DataAccessException;

}