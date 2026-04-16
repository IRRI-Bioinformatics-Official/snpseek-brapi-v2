package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.ds.chado.dao.VSnpSpliceacceptorDAO;
import org.irri.iric.ds.chado.domain.SnpsSpliceAcceptor;
import org.irri.iric.ds.chado.domain.model.VSnpSpliceacceptor;
import org.irri.iric.ds.utils.DbUtils;
import org.irri.iric.ds.utils.SnpsPropertyDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpSpliceacceptor entities.
 * 
 */
@Repository("VSnpSpliceacceptorDAO")
@Transactional
public class VSnpSpliceacceptorDAOImpl extends SnpsPropertyDAO<VSnpSpliceacceptor> // AbstractJpaDao<VSnpSpliceacceptor>
		implements VSnpSpliceacceptorDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VSnpSpliceacceptor.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpSpliceacceptorDAOImpl
	 *
	 */
	public VSnpSpliceacceptorDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position) throws DataAccessException {

		return findVSnpSpliceacceptorByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpSpliceacceptorBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSpliceacceptorBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (VSnpSpliceacceptor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChrContaining
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChrContaining(String chr) throws DataAccessException {

		return findVSnpSpliceacceptorByChrContaining(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChrContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChrContaining(String chr, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByChrContaining", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Integer snpFeatureId) throws DataAccessException {

		return findVSnpSpliceacceptorByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSpliceacceptorByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (VSnpSpliceacceptor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(java.math.BigDecimal organismId)
			throws DataAccessException {

		return findVSnpSpliceacceptorByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(java.math.BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException {
		// Query query = createNamedQuery("findVSnpSpliceacceptorByOrganismId",
		// startResult, maxRows, organismId);
		// return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
		return null;
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(String chr) throws DataAccessException {

		return findVSnpSpliceacceptorByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(String chr, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId)
			throws DataAccessException {

		return findVSnpSpliceacceptorBySrcfeatureId(srcfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId, int startResult,
			int maxRows) throws DataAccessException {
		// Query query = createNamedQuery("findVSnpSpliceacceptorBySrcfeatureId",
		// startResult, maxRows, srcfeatureId);
		// return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
		return null;
	}

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors() throws DataAccessException {

		return findAllVSnpSpliceacceptors(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpSpliceacceptors", startResult, maxRows);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpSpliceacceptor entity) {
		return true;
	}

	// @Override
	public Set<SnpsSpliceAcceptor> findSnpSpliceAcceptorByChrPosBetween(String chr, Integer start, Integer end)
			throws DataAccessException {
		
		// Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionBetween2",
		// -1,-1, "Chr" + chr, BigDecimal.valueOf(start), BigDecimal.valueOf(end) );
		Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionBetween2", -1, -1,
				BigDecimal.valueOf(Long.valueOf(DbUtils.guessChrFromString(chr))), BigDecimal.valueOf(start),
				BigDecimal.valueOf(end));

		return new LinkedHashSet<SnpsSpliceAcceptor>(query.getResultList());
	}

	// @Override
	public Set<SnpsSpliceAcceptor> findSnpSpliceAcceptorByChrPosIn(String chr, Collection listpos)
			throws DataAccessException {
		
		// Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionIn2", -1,
		// -1, "Chr" + chr, listpos);
		Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionIn2", -1, -1,
				BigDecimal.valueOf(Long.valueOf(DbUtils.guessChrFromString(chr))), listpos);
		return new LinkedHashSet<SnpsSpliceAcceptor>(query.getResultList());
	}
	//
	// @Override
	// public Set<SnpsSpliceAcceptor> getSNPsBetween(String chr, Integer start,
	// Integer end) throws DataAccessException {
	// 
	// return findSnpSpliceAcceptorByChrPosBetween(chr,start,end);
	// }
	//
	//
	//
	// @Override
	// public Set<SnpsSpliceAcceptor> getSNPsByFeatureidIn(
	// Collection featureid)
	// throws DataAccessException {
	// 
	// Set sets[]=AppContext.setSlicer((Set)featureid);
	// Set setAll=new LinkedHashSet();
	// for(int iset=0; iset<sets.length; iset++) {
	// Query query = createNamedQuery("findVSnpSpliceacceptorBySnpFeatureIdIn", -1,
	// -1, sets[iset]);
	// setAll.addAll(query.getResultList());
	// }
	// return setAll;
	//
	// }
	//
	//
	// @Override
	// public Set<SnpsSpliceAcceptor> getSNPsIn(String chr, Collection listpos)
	// throws DataAccessException {
	// 
	// if(chr.toLowerCase().equals("any")) {
	//
	// Set setSNP = new TreeSet();
	// Map<String,Set<BigDecimal>> mapContig2Pos =
	// MultiReferencePositionImpl.getMapContig2SNPPos(listpos);
	// Iterator<String> itCont = mapContig2Pos.keySet().iterator();
	// while(itCont.hasNext()) {
	// String cont = itCont.next();
	//
	// Set sets[] = AppContext.setSlicer(mapContig2Pos.get(cont));
	// for(int iset=0; iset<sets.length; iset++) {
	// setSNP.addAll(findSnpSpliceAcceptorByChrPosIn(cont, sets[iset]));
	// }
	// }
	// return setSNP;
	// }
	// else if(chr.toLowerCase().equals("loci")) {
	// Iterator<Locus> it = listpos.iterator();
	// //StringBuffer buff = new StringBuffer();
	// Set setPos = new TreeSet();
	// while(it.hasNext()) {
	// Locus loc=it.next();
	// setPos.addAll( getSNPsBetween(loc.getContig(), loc.getFmin(), loc.getFmax())
	// );
	// }
	// return setPos;
	// } else {
	// //return findSnpSpliceAcceptorByChrPosIn(chr,listpos);
	// Set setSNP = new TreeSet();
	// Set sets[] = AppContext.setSlicer(new HashSet(listpos));
	// for(int iset=0; iset<sets.length; iset++) {
	// setSNP.addAll(findSnpSpliceAcceptorByChrPosIn(chr, sets[iset]));
	// }
	// return setSNP;
	// }
	// }

	@Override
	public Set<SnpsSpliceAcceptor> getSNPsIn(Integer organismId, String chr, Collection listpos, Set variantset)
			throws DataAccessException {
		return findSnpsPropertyByChrPosIn(organismId,  chr, listpos, variantset, "splice_acceptor_variant", "VSnpSpliceacceptor",
				null, VSnpSpliceacceptor.class);

	}

	@Override
	public Set<SnpsSpliceAcceptor> getSNPsBetween(Integer organismId, String chr, Integer start, Integer end, Set variantset)
			throws DataAccessException {
		
		return findSnpsPropertyByChrPosBetween(organismId, chr, start, end, variantset, "splice_acceptor_variant",
				"VSnpSpliceacceptor", null, VSnpSpliceacceptor.class);
	}

	@Override
	public Set<SnpsSpliceAcceptor> getSNPsByFeatureidIn(Integer organismId, Collection featureid) throws DataAccessException {
		
		return findSnpsPropertyByFeatureidIn(organismId, featureid, "splice_acceptor_variant", "VSnpSpliceacceptor", null,
				VSnpSpliceacceptor.class);

	}

}
