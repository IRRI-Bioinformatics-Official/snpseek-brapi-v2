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
import org.irri.iric.ds.chado.dao.VSnpSplicedonorDAO;
import org.irri.iric.ds.chado.domain.SnpsSpliceDonor;
import org.irri.iric.ds.chado.domain.model.VSnpSplicedonor;
import org.irri.iric.ds.utils.DbUtils;
import org.irri.iric.ds.utils.SnpsPropertyDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpSplicedonor entities.
 * 
 */
@Repository("VSnpSplicedonorDAO")
@Transactional
// public class VSnpSplicedonorDAOImpl extends AbstractJpaDao<VSnpSplicedonor>
// implements VSnpSplicedonorDAO {
public class VSnpSplicedonorDAOImpl extends SnpsPropertyDAO<VSnpSplicedonor> // AbstractJpaDao<VSnpSpliceacceptor>
		implements VSnpSplicedonorDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VSnpSplicedonor.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpSplicedonorDAOImpl
	 *
	 */
	public VSnpSplicedonorDAOImpl() {
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
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position) throws DataAccessException {

		return findVSnpSplicedonorByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(java.math.BigDecimal organismId)
			throws DataAccessException {

		return findVSnpSplicedonorByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(java.math.BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException {
		// Query query = createNamedQuery("findVSnpSplicedonorByOrganismId",
		// startResult, maxRows, organismId);
		// return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
		return null;
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Integer snpFeatureId) throws DataAccessException {

		return findVSnpSplicedonorByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSplicedonorByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (VSnpSplicedonor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpSplicedonorBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSplicedonorBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (VSnpSplicedonor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId) throws DataAccessException {

		return findVSnpSplicedonorBySrcfeatureId(srcfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows)
			throws DataAccessException {
		// Query query = createNamedQuery("findVSnpSplicedonorBySrcfeatureId",
		// startResult, maxRows, srcfeatureId);
		// return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
		return null;
	}

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors() throws DataAccessException {

		return findAllVSnpSplicedonors(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpSplicedonors", startResult, maxRows);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChrContaining
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChrContaining(String chr) throws DataAccessException {

		return findVSnpSplicedonorByChrContaining(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChrContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChrContaining(String chr, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByChrContaining", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(String chr) throws DataAccessException {

		return findVSnpSplicedonorByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(String chr, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpSplicedonor entity) {
		return true;
	}

	// @Override
	public Set<SnpsSpliceDonor> findSnpSpliceDonorByChrPosBetween(String chr, Integer start, Integer end)
			throws DataAccessException {
		

		Query query = createNamedQuery("findVSnpSplicedonorByChrPositionBetween2", -1, -1,
				BigDecimal.valueOf(Long.valueOf(DbUtils.guessChrFromString(chr))), BigDecimal.valueOf(start),
				BigDecimal.valueOf(end));
		return new LinkedHashSet<SnpsSpliceDonor>(query.getResultList());

	}

	// //@Override
	// public Set<SnpsSpliceDonor> findSnpSpliceDonorByChrPosIn(String chr,
	// Collection listpos) throws DataAccessException {
	// 
	// Query query = createNamedQuery("findVSnpSplicedonorByChrPositionIn2", -1, -1,
	// BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chr))),
	// listpos);
	// return new LinkedHashSet<SnpsSpliceDonor>(query.getResultList());
	//
	// }
	//
	// @Override
	// public Set<SnpsSpliceDonor> getSNPsBetween(String chr, Integer start,
	// Integer end) throws DataAccessException {
	// 
	// return findSnpSpliceDonorByChrPosBetween(chr,start,end);
	// }
	//
	// @Override
	// public Set<SnpsSpliceDonor> getSNPsByFeatureidIn(
	// Collection featureid)
	// throws DataAccessException {
	// 
	// Set sets[]=AppContext.setSlicer((Set)featureid);
	// Set setAll=new LinkedHashSet();
	// for(int iset=0; iset<sets.length; iset++) {
	// Query query = createNamedQuery("findVSnpSplicedonorBySnpFeatureIdIn", -1, -1,
	// sets[iset]);
	// setAll.addAll(query.getResultList());
	// }
	// return setAll;
	//
	// }
	//
	// @Override
	// public Set<SnpsSpliceDonor> getSNPsIn(String chr, Collection listpos)
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
	// //setSNP.addAll(findSnpSpliceDonorByChrPosIn(cont,mapContig2Pos.get(cont)));
	//
	// Set sets[] = AppContext.setSlicer(mapContig2Pos.get(cont));
	// for(int iset=0; iset<sets.length; iset++) {
	// setSNP.addAll(findSnpSpliceDonorByChrPosIn(cont, sets[iset]));
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
	// //return findSnpSpliceDonorByChrPosIn(chr,listpos);
	// Set retSet = new TreeSet();
	// Set sets[] = AppContext.setSlicer(new HashSet(listpos));
	// for(int iset=0; iset<sets.length; iset++) {
	// retSet.addAll(findSnpSpliceDonorByChrPosIn(chr, sets[iset]));
	// }
	// return retSet;
	// }
	// }

	@Override
	public Set<SnpsSpliceDonor> getSNPsIn(Integer organismId, String chr, Collection listpos, Set dataset) throws DataAccessException {
		return findSnpsPropertyByChrPosIn(organismId,chr, listpos, dataset, "splice_donor_variant", "VSnpSplicedonor", null,
				VSnpSplicedonor.class);

	}

	@Override
	public Set<SnpsSpliceDonor> getSNPsBetween(Integer organismId, String chr, Integer start, Integer end, Set dataset)
			throws DataAccessException {
		
		return findSnpsPropertyByChrPosBetween(organismId,chr, start, end, dataset, "splice_donor_variant", "VSnpSplicedonor",
				null, VSnpSplicedonor.class);
	}

	@Override
	public Set<SnpsSpliceDonor> getSNPsByFeatureidIn(Integer organismId, Collection featureid) throws DataAccessException {
		
		return findSnpsPropertyByFeatureidIn(organismId,featureid, "splice_donor_variant", "VSnpSplicedonor", null,
				VSnpSplicedonor.class);

	}

}
