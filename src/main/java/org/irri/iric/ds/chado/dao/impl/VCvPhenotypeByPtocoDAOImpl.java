package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.ds.chado.dao.VCvPhenotypeByPtocoDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VCvPhenotypeByPtoco;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * DAO to manage VCvPhenotypeByPtoco entities.
 * 
 */
@Repository("VCvPhenotypeByPtocoDAO")
@Transactional
public class VCvPhenotypeByPtocoDAOImpl extends AbstractJpaDao<VCvPhenotypeByPtoco> implements VCvPhenotypeByPtocoDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VCvPhenotypeByPtoco.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VCvPhenotypeByPtocoDAOImpl
	 *
	 */
	public VCvPhenotypeByPtocoDAOImpl() {
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
	 * JPQL Query - findVCvPhenotypeByPtocoByObjAccContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjAccContaining(String objAcc)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjAccContaining(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjAccContaining(String objAcc, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjAccContaining", startResult, maxRows, objAcc);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvname
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvname(String subjCvname) throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjCvname(subjCvname, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvname
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvname(String subjCvname, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjCvname", startResult, maxRows, subjCvname);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjDb
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjDb(String objDb) throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjDb(objDb, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjDb
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjDb(String objDb, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjDb", startResult, maxRows, objDb);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByPrimaryKey
	 *
	 */
	@Transactional
	public VCvPhenotypeByPtoco findVCvPhenotypeByPtocoByPrimaryKey(BigDecimal objCvtermId) throws DataAccessException {

		return findVCvPhenotypeByPtocoByPrimaryKey(objCvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByPrimaryKey
	 *
	 */

	@Transactional
	public VCvPhenotypeByPtoco findVCvPhenotypeByPtocoByPrimaryKey(BigDecimal objCvtermId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPhenotypeByPtocoByPrimaryKey", startResult, maxRows, objCvtermId);
			return (VCvPhenotypeByPtoco) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvnameContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvnameContaining(String objCvname)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjCvnameContaining(objCvname, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvnameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvnameContaining(String objCvname, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjCvnameContaining", startResult, maxRows, objCvname);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjAccContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjAccContaining(String subjAcc)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjAccContaining(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjAccContaining(String subjAcc, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjAccContaining", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDefinition
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDefinition(String subjDefinition)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjDefinition(subjDefinition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDefinition(String subjDefinition, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjDefinition", startResult, maxRows, subjDefinition);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvtermContaining(String subjCvterm)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjCvtermContaining(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvtermContaining(String subjCvterm, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjCvtermContaining", startResult, maxRows,
				subjCvterm);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvname
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvname(String objCvname) throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjCvname(objCvname, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvname
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvname(String objCvname, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjCvname", startResult, maxRows, objCvname);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVCvPhenotypeByPtocos
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findAllVCvPhenotypeByPtocos() throws DataAccessException {

		return findAllVCvPhenotypeByPtocos(-1, -1);
	}

	/**
	 * JPQL Query - findAllVCvPhenotypeByPtocos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findAllVCvPhenotypeByPtocos(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVCvPhenotypeByPtocos", startResult, maxRows);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDbContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDbContaining(String subjDb)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjDbContaining(subjDb, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDbContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDbContaining(String subjDb, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjDbContaining", startResult, maxRows, subjDb);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvterm
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvterm(String subjCvterm) throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjCvterm(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvterm(String subjCvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjCvterm", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvid
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvid(BigDecimal objCvid) throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjCvid(objCvid, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvid(BigDecimal objCvid, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjCvid", startResult, maxRows, objCvid);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQualValueContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQualValueContaining(String qualValue)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoByQualValueContaining(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQualValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQualValueContaining(String qualValue, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByQualValueContaining", startResult, maxRows, qualValue);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvtermId
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvtermId(BigDecimal subjCvtermId)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjCvtermId(subjCvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvtermId(BigDecimal subjCvtermId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjCvtermId", startResult, maxRows, subjCvtermId);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByPathdistance
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByPathdistance(BigDecimal pathdistance)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoByPathdistance(pathdistance, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByPathdistance
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByPathdistance(BigDecimal pathdistance, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByPathdistance", startResult, maxRows, pathdistance);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQualValue
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQualValue(String qualValue) throws DataAccessException {

		return findVCvPhenotypeByPtocoByQualValue(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQualValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQualValue(String qualValue, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByQualValue", startResult, maxRows, qualValue);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvtermId
	 *
	 */
	@Transactional
	public VCvPhenotypeByPtoco findVCvPhenotypeByPtocoByObjCvtermId(BigDecimal objCvtermId) throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjCvtermId(objCvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvtermId
	 *
	 */

	@Transactional
	public VCvPhenotypeByPtoco findVCvPhenotypeByPtocoByObjCvtermId(BigDecimal objCvtermId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjCvtermId", startResult, maxRows, objCvtermId);
			return (VCvPhenotypeByPtoco) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjAcc
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjAcc(String subjAcc) throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjAcc(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjAcc(String subjAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjAcc", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvid
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvid(BigDecimal subjCvid) throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjCvid(subjCvid, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvid(BigDecimal subjCvid, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjCvid", startResult, maxRows, subjCvid);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvnameContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvnameContaining(String subjCvname)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjCvnameContaining(subjCvname, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvnameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvnameContaining(String subjCvname, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjCvnameContaining", startResult, maxRows,
				subjCvname);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQuanValue
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQuanValue(java.math.BigDecimal quanValue)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoByQuanValue(quanValue, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQuanValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQuanValue(java.math.BigDecimal quanValue, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByQuanValue", startResult, maxRows, quanValue);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvtermContaining(String objCvterm)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjCvtermContaining(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvtermContaining(String objCvterm, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjCvtermContaining", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDb
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDb(String subjDb) throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjDb(subjDb, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDb
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDb(String subjDb, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjDb", startResult, maxRows, subjDb);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDefinitionContaining(String subjDefinition)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoBySubjDefinitionContaining(subjDefinition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDefinitionContaining(String subjDefinition,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoBySubjDefinitionContaining", startResult, maxRows,
				subjDefinition);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjAcc
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjAcc(String objAcc) throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjAcc(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjAcc(String objAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjAcc", startResult, maxRows, objAcc);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvterm
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvterm(String objCvterm) throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjCvterm(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvterm(String objCvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjCvterm", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjDbContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjDbContaining(String objDb) throws DataAccessException {

		return findVCvPhenotypeByPtocoByObjDbContaining(objDb, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjDbContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjDbContaining(String objDb, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjDbContaining", startResult, maxRows, objDb);
		return new LinkedHashSet<VCvPhenotypeByPtoco>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VCvPhenotypeByPtoco entity) {
		return true;
	}

	@Override
	public List getAncestors(String cv, String term) {

		Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjCvtermNegDist", -1, -1, cv, term);
		List list = new ArrayList();
		Set set = new LinkedHashSet();
		Iterator<VCvPhenotypeByPtoco> itPtoco = query.getResultList().iterator();
		while (itPtoco.hasNext())
			set.add(itPtoco.next().getSubjCvterm());
		// list.add(itGO.next().getSubjCvterm() );
		list.addAll(set);
		// AppContext.debug(list.size() + " ancestors for " + cv + " - " + term );
		return list;
	}

	@Override
	public List getDescendants(String cv, String term, Set dataset) {

		List list = new ArrayList();
		Set set = new LinkedHashSet();

		/*
		 * Query query = createNamedQuery("findVCvPhenotypeByPtocoByObjCvtermPosDist",
		 * -1,-1, cv, term); List resultlist = query.getResultList();
		 */

		String sql = "select * from  public.V_CV_PHENOTYPE_BY_PTOCO_PATH where obj_cvname = '" + cv
				+ "' and obj_cvterm='" + term + "' and (PATHDISTANCE>0 or PATHDISTANCE is null)";
		List resultlist = executeSQL(sql);
		Iterator<VCvPhenotypeByPtoco> itPtoco = resultlist.iterator();
		while (itPtoco.hasNext()) {
			VCvPhenotypeByPtoco obj = itPtoco.next();
			set.add(obj.getSubjDefinition());
		}
		list.addAll(set);

		return list;

	}

	@Override
	public Set getUniqueValues(BigDecimal typeId, Set dataset) {
		return null;
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	private List<Locus> executeSQL(String sql) {
		// AppContext.debug("executing " + sql);

		List listResult = null;
		try {
			listResult = getSession().createSQLQuery(sql).addEntity(VCvPhenotypeByPtoco.class).list();
		} catch (Exception ex) {
			// AppContext.debug(ex.getMessage());
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		// AppContext.debug("result " + listResult.size() + " cvterms");
		return listResult;
	}

}
