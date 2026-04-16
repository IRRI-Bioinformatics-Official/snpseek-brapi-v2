package org.irri.iric.ds.chado.dao.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VOracleSessionsDAO;
import org.irri.iric.ds.chado.domain.model.VOracleSessions;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VOracleSessions entities.
 * 
 */
@Repository("VOracleSessionsDAO")
@Transactional
public class VOracleSessionsDAOImpl extends AbstractJpaDao<VOracleSessions> implements VOracleSessionsDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VOracleSessions.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VOracleSessionsDAOImpl
	 *
	 */
	public VOracleSessionsDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findVOracleSessionsByInstId
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByInstId(Integer instId) throws DataAccessException {

		return findVOracleSessionsByInstId(instId, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsByInstId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByInstId(Integer instId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsByInstId", startResult, maxRows, instId);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsBySpidContaining
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsBySpidContaining(String spid) throws DataAccessException {

		return findVOracleSessionsBySpidContaining(spid, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsBySpidContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsBySpidContaining(String spid, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsBySpidContaining", startResult, maxRows, spid);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsByProgramContaining
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByProgramContaining(String program) throws DataAccessException {

		return findVOracleSessionsByProgramContaining(program, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsByProgramContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByProgramContaining(String program, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsByProgramContaining", startResult, maxRows, program);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsBySpid
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsBySpid(String spid) throws DataAccessException {

		return findVOracleSessionsBySpid(spid, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsBySpid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsBySpid(String spid, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsBySpid", startResult, maxRows, spid);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVOracleSessionss
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findAllVOracleSessionss() throws DataAccessException {

		return findAllVOracleSessionss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVOracleSessionss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findAllVOracleSessionss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVOracleSessionss", startResult, maxRows);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsBySid
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsBySid(Integer sid) throws DataAccessException {

		return findVOracleSessionsBySid(sid, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsBySid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsBySid(Integer sid, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsBySid", startResult, maxRows, sid);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsByUsernameContaining
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByUsernameContaining(String username) throws DataAccessException {

		return findVOracleSessionsByUsernameContaining(username, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsByUsernameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByUsernameContaining(String username, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsByUsernameContaining", startResult, maxRows, username);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsByProgram
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByProgram(String program) throws DataAccessException {

		return findVOracleSessionsByProgram(program, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsByProgram
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByProgram(String program, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsByProgram", startResult, maxRows, program);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsByAction
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByAction(String action) throws DataAccessException {

		return findVOracleSessionsByAction(action, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsByAction
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByAction(String action, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsByAction", startResult, maxRows, action);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsByUsername
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByUsername(String username) throws DataAccessException {

		return findVOracleSessionsByUsername(username, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsByUsername
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByUsername(String username, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsByUsername", startResult, maxRows, username);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsByPrimaryKey
	 *
	 */
	@Transactional
	public VOracleSessions findVOracleSessionsByPrimaryKey(Integer sid, Integer serial_) throws DataAccessException {

		return findVOracleSessionsByPrimaryKey(sid, serial_, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsByPrimaryKey
	 *
	 */

	@Transactional
	public VOracleSessions findVOracleSessionsByPrimaryKey(Integer sid, Integer serial_, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVOracleSessionsByPrimaryKey", startResult, maxRows, sid, serial_);
			return (org.irri.iric.ds.chado.domain.model.VOracleSessions) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVOracleSessionsBySerial_
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsBySerial_(Integer serial_) throws DataAccessException {

		return findVOracleSessionsBySerial_(serial_, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsBySerial_
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsBySerial_(Integer serial_, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsBySerial#", startResult, maxRows, serial_);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * JPQL Query - findVOracleSessionsByActionContaining
	 *
	 */
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByActionContaining(String action) throws DataAccessException {

		return findVOracleSessionsByActionContaining(action, -1, -1);
	}

	/**
	 * JPQL Query - findVOracleSessionsByActionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VOracleSessions> findVOracleSessionsByActionContaining(String action, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVOracleSessionsByActionContaining", startResult, maxRows, action);
		return new LinkedHashSet<VOracleSessions>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VOracleSessions entity) {
		return true;
	}
}
