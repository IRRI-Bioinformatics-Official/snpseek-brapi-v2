package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VIricstockPhenotypeQuanval;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockPhenotypeQuanval entities.
 * 
 */
public interface VIricstockPhenotypeQuanvalDAO extends JpaDao<VIricstockPhenotypeQuanval>, CvTermUniqueValuesDAO {

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByQuanValue
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByQuanValue(BigDecimal quanValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByQuanValue
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByQuanValue(BigDecimal quanValue,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeQuanval findVIricstockPhenotypeQuanvalByPrimaryKey(BigDecimal quanValue_1,
			BigDecimal phenotypeId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeQuanval findVIricstockPhenotypeQuanvalByPrimaryKey(BigDecimal quanValue_1,
			BigDecimal phenotypeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(BigDecimal phenotypeId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(BigDecimal phenotypeId_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQuanvals
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findAllVIricstockPhenotypeQuanvals() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQuanvals
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findAllVIricstockPhenotypeQuanvals(int startResult, int maxRows)
			throws DataAccessException;

}