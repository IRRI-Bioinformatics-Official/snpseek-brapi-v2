package org.irri.iric.ds.chado.dao;

import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VOracleSessions;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VOracleSessions entities.
 * 
 */
public interface VOracleSessionsDAO extends JpaDao<VOracleSessions> {

	/**
	 * JPQL Query - findVOracleSessionsByInstId
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByInstId(Integer instId) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByInstId
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByInstId(Integer instId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsBySpidContaining
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsBySpidContaining(String spid) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsBySpidContaining
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsBySpidContaining(String spid, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByProgramContaining
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByProgramContaining(String program) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByProgramContaining
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByProgramContaining(String program, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsBySpid
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsBySpid(String spid_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsBySpid
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsBySpid(String spid_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVOracleSessionss
	 *
	 */
	public Set<VOracleSessions> findAllVOracleSessionss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVOracleSessionss
	 *
	 */
	public Set<VOracleSessions> findAllVOracleSessionss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsBySid
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsBySid(Integer sid) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsBySid
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsBySid(Integer sid, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByUsernameContaining
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByUsernameContaining(String username) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByUsernameContaining
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByUsernameContaining(String username, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByProgram
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByProgram(String program_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByProgram
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByProgram(String program_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByAction
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByAction(String action) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByAction
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByAction(String action, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByUsername
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByUsername(String username_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByUsername
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByUsername(String username_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByPrimaryKey
	 *
	 */
	public VOracleSessions findVOracleSessionsByPrimaryKey(Integer sid_1, Integer serial_) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByPrimaryKey
	 *
	 */
	public VOracleSessions findVOracleSessionsByPrimaryKey(Integer sid_1, Integer serial_, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsBySerial_
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsBySerial_(Integer serial__1) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsBySerial_
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsBySerial_(Integer serial__1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByActionContaining
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByActionContaining(String action_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOracleSessionsByActionContaining
	 *
	 */
	public Set<VOracleSessions> findVOracleSessionsByActionContaining(String action_1, int startResult, int maxRows) throws DataAccessException;

}