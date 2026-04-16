package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.ds.chado.dao.VIndelRefposindexDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImpl;
import org.irri.iric.ds.chado.domain.model.VIndelRefposindex;
import org.irri.iric.ds.utils.DbUtils;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage MvIndelRefposindex entities.
 * 
 */
// @Repository("MvIndelRefposindexDAO")
@Repository("IndelsAllvarsPosDAO")
@Transactional
public class VIndelRefposindexDAOImpl extends AbstractJpaDao<VIndelRefposindex> implements VIndelRefposindexDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VIndelRefposindex.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new MvIndelRefposindexDAOImpl
	 *
	 */
	public VIndelRefposindexDAOImpl() {
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
	 * JPQL Query - findMvIndelRefposindexByPosition
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByPosition(BigDecimal position) throws DataAccessException {

		return findMvIndelRefposindexByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByMaxInsertLen
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByMaxInsertLen(Integer maxInsertLen)
			throws DataAccessException {

		return findMvIndelRefposindexByMaxInsertLen(maxInsertLen, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByMaxInsertLen
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByMaxInsertLen(Integer maxInsertLen, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByMaxInsertLen", startResult, maxRows, maxInsertLen);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllMvIndelRefposindexs
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findAllMvIndelRefposindexs() throws DataAccessException {

		return findAllMvIndelRefposindexs(-1, -1);
	}

	/**
	 * JPQL Query - findAllMvIndelRefposindexs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findAllMvIndelRefposindexs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllMvIndelRefposindexs", startResult, maxRows);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByIndelFeatureId
	 *
	 */
	@Transactional
	public VIndelRefposindex findMvIndelRefposindexByIndelFeatureId(BigDecimal indelFeatureId)
			throws DataAccessException {

		return findMvIndelRefposindexByIndelFeatureId(indelFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByIndelFeatureId
	 *
	 */

	@Transactional
	public VIndelRefposindex findMvIndelRefposindexByIndelFeatureId(BigDecimal indelFeatureId, int startResult,
			int maxRows) throws DataAccessException {

		try {
			Query query = createNamedQuery("findMvIndelRefposindexByIndelFeatureId", startResult, maxRows,
					indelFeatureId);
			return (VIndelRefposindex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}

	/**
	 * JPQL Query - findMvIndelRefposindexByRefcall
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByRefcall(String refcall) throws DataAccessException {

		return findMvIndelRefposindexByRefcall(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByRefcall(String refcall, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByRefcall", startResult, maxRows, refcall);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByPrimaryKey
	 *
	 */
	@Transactional
	public VIndelRefposindex findMvIndelRefposindexByPrimaryKey(BigDecimal indelFeatureId) throws DataAccessException {

		return findMvIndelRefposindexByPrimaryKey(indelFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByPrimaryKey
	 *
	 */

	@Transactional
	public VIndelRefposindex findMvIndelRefposindexByPrimaryKey(BigDecimal indelFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findMvIndelRefposindexByPrimaryKey", startResult, maxRows, indelFeatureId);
			return (VIndelRefposindex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByRefcallContaining
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByRefcallContaining(String refcall) throws DataAccessException {

		return findMvIndelRefposindexByRefcallContaining(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByRefcallContaining(String refcall, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByRefcallContaining", startResult, maxRows, refcall);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByMaxDeleteLen
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByMaxDeleteLen(Integer maxDeleteLen)
			throws DataAccessException {

		return findMvIndelRefposindexByMaxDeleteLen(maxDeleteLen, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByMaxDeleteLen
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByMaxDeleteLen(Integer maxDeleteLen, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByMaxDeleteLen", startResult, maxRows, maxDeleteLen);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByAltcall
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByAltcall(String altcall) throws DataAccessException {

		return findMvIndelRefposindexByAltcall(altcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByAltcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByAltcall(String altcall, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByAltcall", startResult, maxRows, altcall);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByChromosome
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByChromosome(BigDecimal chromosome) throws DataAccessException {

		return findMvIndelRefposindexByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByChromosome(BigDecimal chromosome, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByAlleleIndex
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByAlleleIndex(BigDecimal alleleIndex)
			throws DataAccessException {

		return findMvIndelRefposindexByAlleleIndex(alleleIndex, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByAlleleIndex
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByAlleleIndex(BigDecimal alleleIndex, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByAlleleIndex", startResult, maxRows, alleleIndex);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByTypeId
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByTypeId(BigDecimal typeId) throws DataAccessException {

		return findMvIndelRefposindexByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByTypeId(BigDecimal typeId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByAltcallContaining
	 *
	 */
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByAltcallContaining(String altcall) throws DataAccessException {

		return findMvIndelRefposindexByAltcallContaining(altcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvIndelRefposindexByAltcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findMvIndelRefposindexByAltcallContaining(String altcall, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByAltcallContaining", startResult, maxRows, altcall);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIndelRefposindex entity) {
		return true;
	}

	// @Override
	// public Map getMapIndelId2Indels(String chromosome, Integer startPos,
	// Integer endPos) {
	//
	// return null;
	// }
	//
	// @Override
	// public Map getMapIndelId2Indels(String chromosome, Collection poslist) {
	//
	// return null;
	// }
	//
	// @Override
	// public List getSNPs(String chromosome, Integer startPos, Integer endPos,
	// BigDecimal type) {
	//
	// return null;
	// }
	//
	// @Override
	// public List getSNPs(String chromosome, Integer startPos, Integer endPos,
	// BigDecimal type, int firstRow, int maxRows) {
	//
	// return null;
	// }
	//
	// @Override
	// public List getSNPsInChromosome(String chr, Collection posset,
	// BigDecimal type) {
	//
	// return null;
	// }
	//

	// @NamedQuery(name = "findIndelAlleleByChromosomePosBetween", query = "select
	// myMvIndelRefposindex from MvIndelRefposindex myMvIndelRefposindex where
	// myMvIndelRefposindex.chromosome = ?1 and myMvIndelRefposindex.position
	// between ?2 and ?3"),
	// @NamedQuery(name = "findIndelAlleleByChromosomePosIn", query = "select
	// myMvIndelRefposindex from MvIndelRefposindex myMvIndelRefposindex where
	// myMvIndelRefposindex.chromosome = ?1 and myMvIndelRefposindex.position in
	// (?2)"),
	// @NamedQuery(name = "findMvIndelRefposindexByIndelFeatureId", query = "select
	// myMvIndelRefposindex from MvIndelRefposindex myMvIndelRefposindex where
	// myMvIndelRefposindex.indelFeatureId in (?1)"),

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findIndelAlleleByChrPosBetween(Integer chr, Integer posStart, Integer posStop,
			Set type) throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByChromosomePosBetweenVS", -1, -1,
				BigDecimal.valueOf(chr), BigDecimal.valueOf(posStart), BigDecimal.valueOf(posStop), type);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIndelRefposindex> findIndelAlleleByChrPosIn(Integer chr, Collection poslist, Set variantset)
			throws DataAccessException {
		Query query = createNamedQuery("findMvIndelRefposindexByChromosomePosInVS", -1, -1, BigDecimal.valueOf(chr),
				poslist, variantset);
		return new LinkedHashSet<VIndelRefposindex>(query.getResultList());
	}

	@Override
	public List getSNPs(Integer organismId, String chromosome, Integer startPos, Integer endPos, Set type) {

		// if(!type.contains(SnpsAllvarsPosDAO.TYPE_3KALLINDEL_V2)) throw new
		// RuntimeException("type should be SnpcoreRefposindexDAO.TYPE_3KALLINDEL_V2");

		List retlist = new ArrayList();
		// retlist.addAll(
		// findIndelAlleleByChrPosBetween(Integer.valueOf(AppContext.guessChrFromString(chromosome)),
		// startPos, endPos) );
		retlist.addAll(findIndelAlleleByChrPosBetween(Integer.valueOf(DbUtils.guessChrFromString(chromosome)),
				startPos, endPos, type));
		return retlist;
	}

	@Override
	public List getSNPs(Integer organismId, String chromosome, Integer startPos, Integer endPos, Set type, int firstRow, int maxRows) {

		return getSNPs(organismId, chromosome, startPos, endPos, type);
	}

	@Override
	public Map<BigDecimal, VIndelRefposindex> getMapIndelId2Indels(String chromosome, Integer startPos, Integer endPos,
			Set type) {

		Iterator<VIndelRefposindex> itIndels = findIndelAlleleByChrPosBetween(
				Integer.valueOf(DbUtils.guessChrFromString(chromosome)), startPos, endPos, type).iterator();

		Map mapIndelid2Indels = new LinkedHashMap();
		while (itIndels.hasNext()) {
			VIndelRefposindex indel = itIndels.next();
			mapIndelid2Indels.put(indel.getIndelFeatureId(), indel);
		}
		return mapIndelid2Indels;
	}

	private List<VIndelRefposindex> executeSQL(String sql) {
		
		// log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(VIndelRefposindex.class).list();
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public Map<BigDecimal, VIndelRefposindex> getMapIndelId2Indels(String chromosome, Collection poslist,
			Set variantset) {

		Iterator<VIndelRefposindex> itIndels = null;
		if (chromosome.toLowerCase().equals("any")) {
			Map<String, Set<BigDecimal>> mapcontig2pos = MultiReferencePositionImpl.getMapContig2SNPPos(poslist);
			Iterator<String> itCont = mapcontig2pos.keySet().iterator();
			StringBuffer buff = new StringBuffer();
			while (itCont.hasNext()) {
				String cont = itCont.next();
				Set pos = mapcontig2pos.get(cont);
				buff.append(
						" (CHROMOSOME=" + Integer.valueOf(DbUtils.guessChrFromString(cont)) + " and POSITION IN (");
				Iterator<BigDecimal> itPos = pos.iterator();
				while (itPos.hasNext()) {
					buff.append(itPos.next().longValue() - 1);
					if (itPos.hasNext())
						buff.append(",");
				}
				buff.append(")");
				buff.append(" and variantset in (" + DbUtils.toCSVquoted(variantset, "'") + ") ");

				buff.append(") ");
				if (itCont.hasNext())
					buff.append(" OR ");
			}
			buff.append("");
			String sql = "select * from public.V_INDEL_REFPOSINDEX where " + buff
					+ " order by CHROMOSOME, POSITION";
			itIndels = executeSQL(sql).iterator();
		} else if (chromosome.toLowerCase().equals("loci")) {
			Map<String, Set<Locus>> mapcontig2locus = MultiReferencePositionImpl.getMapContig2Loci(poslist);

			Iterator<String> itCont = mapcontig2locus.keySet().iterator();
			StringBuffer buff = new StringBuffer();
			while (itCont.hasNext()) {
				String cont = itCont.next();
				Set pos = mapcontig2locus.get(cont);
				buff.append(" (CHROMOSOME=" + Integer.valueOf(DbUtils.guessChrFromString(cont)) + " and (");
				Iterator<Locus> itPos = pos.iterator();
				while (itPos.hasNext()) {
					Locus loc = itPos.next();
					buff.append(" (POSITION between " + (loc.getFmin().longValue() - 1) + " and "
							+ (loc.getFmax().longValue() - 1) + ") ");
					buff.append(" and variantset in (" + DbUtils.toCSVquoted(variantset, "'") + ") ");
					if (itPos.hasNext())
						buff.append(" or ");
				}
				buff.append(")) ");
				if (itCont.hasNext())
					buff.append(" OR ");
			}
			buff.append("");
			String sql = "select * from public.V_INDEL_REFPOSINDEX where " + buff
					+ " order by CHROMOSOME, POSITION";
			itIndels = executeSQL(sql).iterator();
		} else {
			itIndels = findIndelAlleleByChrPosIn(Integer.valueOf(DbUtils.guessChrFromString(chromosome)), poslist,
					variantset).iterator();
		}

		Map mapIndelid2Indels = new LinkedHashMap();
		while (itIndels.hasNext()) {
			VIndelRefposindex indel = itIndels.next();
			mapIndelid2Indels.put(indel.getIndelFeatureId(), indel);
		}
		return mapIndelid2Indels;

	}

	/*
	 * @Override public Map getMapIndelId2IndelsByIndelId(Collection indelids) { //
	 * 
	 */

	@Override
	public List getSNPsInChromosome(Integer organismId, String chr, Collection posset, Set variantset) {

		// if(!type.contains(SnpsAllvarsPosDAO.TYPE_3KALLINDEL_V2)) throw new
		// RuntimeException("type should be SnpcoreRefposindexDAO.TYPE_3KALLINDEL_V2");
		List retlist = new ArrayList();
		retlist.addAll(
				findIndelAlleleByChrPosIn(Integer.valueOf(DbUtils.guessChrFromString(chr)), posset, variantset));
		return retlist;
	}

	@Override
	public long countSNPsInChromosome(String chr, Collection posset, Set type) {

		return -1;
	}

	@Override
	public long countSNPs(String chr, Integer startPos, Integer endPos, Set type) {

		return -1;
	}

}
