package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VCvPhenotypeByPtocoPathDAO;
import org.irri.iric.ds.chado.domain.CvTerm;
import org.irri.iric.ds.chado.domain.model.VCvPhenotypeByPtocoPath;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * DAO to manage VCvPhenotypeByPtocoPath entities.
 * 
 */
@Repository("VCvPhenotypeByPtocoPathDAO")
@Transactional
public class VCvPhenotypeByPtocoPathDAOImpl extends AbstractJpaDao<VCvPhenotypeByPtocoPath>
		implements VCvPhenotypeByPtocoPathDAO {

	private String dataset = "3k";

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VCvPhenotypeByPtocoPath.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VCvPhenotypeByPtocoPathDAOImpl
	 *
	 */
	public VCvPhenotypeByPtocoPathDAOImpl() {
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
	 * JPQL Query - findAllVCvPhenotypeByPtocoPaths
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findAllVCvPhenotypeByPtocoPaths() throws DataAccessException {

		return findAllVCvPhenotypeByPtocoPaths(-1, -1);
	}

	/**
	 * JPQL Query - findAllVCvPhenotypeByPtocoPaths
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findAllVCvPhenotypeByPtocoPaths(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVCvPhenotypeByPtocoPaths", startResult, maxRows);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDefinition
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDefinition(String subjDefinition)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjDefinition(subjDefinition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDefinition(String subjDefinition,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjDefinition", startResult, maxRows,
				subjDefinition);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDb
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDb(String subjDb) throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjDb(subjDb, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDb
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDb(String subjDb, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjDb", startResult, maxRows, subjDb);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjDbContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjDbContaining(String objDb)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjDbContaining(objDb, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjDbContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjDbContaining(String objDb, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjDbContaining", startResult, maxRows, objDb);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvnameContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvnameContaining(String subjCvname)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjCvnameContaining(subjCvname, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvnameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvnameContaining(String subjCvname,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjCvnameContaining", startResult, maxRows,
				subjCvname);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvid
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvid(java.math.BigDecimal objCvid)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjCvid(objCvid, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvid(java.math.BigDecimal objCvid,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjCvid", startResult, maxRows, objCvid);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvterm
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvterm(String subjCvterm)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjCvterm(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvterm(String subjCvterm, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjCvterm", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvname
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvname(String subjCvname)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjCvname(subjCvname, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvname
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvname(String subjCvname, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjCvname", startResult, maxRows, subjCvname);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvtermContaining(String objCvterm)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjCvtermContaining(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvtermContaining(String objCvterm,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjCvtermContaining", startResult, maxRows,
				objCvterm);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvterm
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvterm(String objCvterm)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjCvterm(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvterm(String objCvterm, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjCvterm", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvtermContaining(String subjCvterm)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjCvtermContaining(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvtermContaining(String subjCvterm,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjCvtermContaining", startResult, maxRows,
				subjCvterm);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDbContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDbContaining(String subjDb)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjDbContaining(subjDb, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDbContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDbContaining(String subjDb, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjDbContaining", startResult, maxRows, subjDb);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByPathdistance
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByPathdistance(BigDecimal pathdistance)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByPathdistance(pathdistance, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByPathdistance
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByPathdistance(BigDecimal pathdistance,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByPathdistance", startResult, maxRows, pathdistance);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQualValueContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQualValueContaining(String qualValue)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByQualValueContaining(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQualValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQualValueContaining(String qualValue,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByQualValueContaining", startResult, maxRows,
				qualValue);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvid
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvid(java.math.BigDecimal subjCvid)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjCvid(subjCvid, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvid(java.math.BigDecimal subjCvid,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjCvid", startResult, maxRows, subjCvid);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQualValue
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQualValue(String qualValue)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByQualValue(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQualValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQualValue(String qualValue, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByQualValue", startResult, maxRows, qualValue);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjAccContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjAccContaining(String subjAcc)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjAccContaining(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjAccContaining(String subjAcc, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjAccContaining", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjAccContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjAccContaining(String objAcc)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjAccContaining(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjAccContaining(String objAcc, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjAccContaining", startResult, maxRows, objAcc);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjAcc
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjAcc(String subjAcc)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjAcc(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjAcc(String subjAcc, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjAcc", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjDb
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjDb(String objDb) throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjDb(objDb, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjDb
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjDb(String objDb, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjDb", startResult, maxRows, objDb);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvtermId
	 *
	 */
	@Transactional
	public VCvPhenotypeByPtocoPath findVCvPhenotypeByPtocoPathBySubjCvtermId(BigDecimal subjCvtermId)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjCvtermId(subjCvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvtermId
	 *
	 */

	@Transactional
	public VCvPhenotypeByPtocoPath findVCvPhenotypeByPtocoPathBySubjCvtermId(BigDecimal subjCvtermId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjCvtermId", startResult, maxRows,
					subjCvtermId);
			return (VCvPhenotypeByPtocoPath) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjAcc
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjAcc(String objAcc) throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjAcc(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjAcc(String objAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjAcc", startResult, maxRows, objAcc);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvname
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvname(String objCvname)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjCvname(objCvname, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvname
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvname(String objCvname, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjCvname", startResult, maxRows, objCvname);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDefinitionContaining(String subjDefinition)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathBySubjDefinitionContaining(subjDefinition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDefinitionContaining(String subjDefinition,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathBySubjDefinitionContaining", startResult, maxRows,
				subjDefinition);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQuanValue
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQuanValue(java.math.BigDecimal quanValue)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByQuanValue(quanValue, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQuanValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQuanValue(java.math.BigDecimal quanValue,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByQuanValue", startResult, maxRows, quanValue);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvnameContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvnameContaining(String objCvname)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjCvnameContaining(objCvname, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvnameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvnameContaining(String objCvname,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjCvnameContaining", startResult, maxRows,
				objCvname);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByPrimaryKey
	 *
	 */
	@Transactional
	public VCvPhenotypeByPtocoPath findVCvPhenotypeByPtocoPathByPrimaryKey(BigDecimal subjCvtermId)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByPrimaryKey(subjCvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByPrimaryKey
	 *
	 */

	@Transactional
	public VCvPhenotypeByPtocoPath findVCvPhenotypeByPtocoPathByPrimaryKey(BigDecimal subjCvtermId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByPrimaryKey", startResult, maxRows,
					subjCvtermId);
			return (VCvPhenotypeByPtocoPath) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvtermId
	 *
	 */
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvtermId(java.math.BigDecimal objCvtermId)
			throws DataAccessException {

		return findVCvPhenotypeByPtocoPathByObjCvtermId(objCvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvtermId(java.math.BigDecimal objCvtermId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjCvtermId", startResult, maxRows, objCvtermId);
		return new LinkedHashSet<VCvPhenotypeByPtocoPath>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VCvPhenotypeByPtocoPath entity) {
		return true;
	}

	@Override
	public List getAncestors(String cv, String term) {
		return null;
	}

	@Override
	public List getDescendants(String cv, String term, Set dataset) {

		List list = new ArrayList();
		Set set = new TreeSet();

		Query query = createNamedQuery("findVCvPhenotypeByPtocoPathByObjCvtermPosDist", -1, -1, cv, term, dataset);
		List resultlist = query.getResultList();

		// String sql = "select * from " + AppContext.getDefaultSchema() +
		// ".V_CV_PHENOTYPE_BY_PTOCO_PATH where obj_cvname = '" + cv + "' and
		// obj_cvterm='" + term + "' and (PATHDISTANCE>0 or PATHDISTANCE is null)";
		// List resultlist=executeSQL(sql);
		// Iterator<VCvPhenotypeByPtocoPath> itPtoco = resultlist.iterator();
		Iterator<CvTerm> itPtoco = resultlist.iterator();
		while (itPtoco.hasNext()) {
			CvTerm cvterm = itPtoco.next();
			set.add(cvterm.getDefinition());
			// VCvPhenotypeByPtocoPath obj=itPtoco.next();
			// set.add(obj.getSubjDefinition());
			// list.add(itGO.next().getSubjCvterm() );
			// AppContext.debug( cvterm.toString());
		}
		list.addAll(set);
		// AppContext.debug(
		// resultlist.size() + ", " + list.size() + " descendants for " + cv + " - " +
		// term + " - " + dataset);
		return list;
	}

	@Override
	public Set getUniqueValues(BigDecimal typeId, Set dataset) {

		return null;
	}
	/*
	 * @Override public List getDescendants(String cv, String term) { // TODO
	 * Auto-generated method stub return null; }
	 */

}
