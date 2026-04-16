package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.Cv;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Cv entities.
 * 
 */
public interface CvDAO extends JpaDao<Cv>{

	/**
	 * JPQL Query - findCvByPrimaryKey
	 *
	 */
	public Cv findCvByPrimaryKey(BigDecimal cvId) throws DataAccessException;

	/**
	 * JPQL Query - findCvByPrimaryKey
	 *
	 */
	public Cv findCvByPrimaryKey(BigDecimal cvId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCvs
	 *
	 */
	public Set<Cv> findAllCvs() throws DataAccessException;

	/**
	 * JPQL Query - findAllCvs
	 *
	 */
	public Set<Cv> findAllCvs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCvByCvId
	 *
	 */
	public Cv findCvByCvId(BigDecimal cvId_1) throws DataAccessException;

	/**
	 * JPQL Query - findCvByCvId
	 *
	 */
	public Cv findCvByCvId(BigDecimal cvId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCvByNameContaining
	 *
	 */
	public Set<Cv> findCvByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findCvByNameContaining
	 *
	 */
	public Set<Cv> findCvByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCvByName
	 *
	 */
	public Set<Cv> findCvByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findCvByName
	 *
	 */
	public Set<Cv> findCvByName(String name_1, int startResult, int maxRows) throws DataAccessException;
	
	
	public Map<String, Cv> getMapName2Cv();

}