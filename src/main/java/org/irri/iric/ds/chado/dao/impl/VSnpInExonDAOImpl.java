package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.SnpsInExonDAO;
import org.irri.iric.ds.chado.dao.VSnpInExonDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.MultiReferencePosition;
import org.irri.iric.ds.chado.domain.model.VSnpInExon;
import org.irri.iric.ds.utils.DbUtils;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpInExon entities.
 * 
 */
@Repository("SnpInExonDAO")
@Transactional
public class VSnpInExonDAOImpl extends AbstractJpaDao<VSnpInExon> implements VSnpInExonDAO, SnpsInExonDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VSnpInExon.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpInExonDAOImpl
	 *
	 */
	public VSnpInExonDAOImpl() {
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
	 * JPQL Query - findVSnpInExonByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpInExon findVSnpInExonByPrimaryKey(Integer snpFeatureId) throws DataAccessException {

		return findVSnpInExonByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpInExonByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpInExon findVSnpInExonByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpInExonByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (VSnpInExon) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpInExonBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpInExon findVSnpInExonBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpInExonBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpInExonBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpInExon findVSnpInExonBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpInExonBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (VSnpInExon) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllVSnpInExons
	 *
	 */
	@Transactional
	public Set<VSnpInExon> findAllVSnpInExons() throws DataAccessException {

		return findAllVSnpInExons(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpInExons
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpInExon> findAllVSnpInExons(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpInExons", startResult, maxRows);
		return new LinkedHashSet<VSnpInExon>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpInExon entity) {
		return true;
	}

	@Override
	public Set getSnps(String chr, Integer start, Integer end) {
		try {
			Query query = createNamedQuery("findVSnpInExonBySnpFeatureIdBetween", -1, -1,
					DbUtils.convertRegion2Snpfeatureid(chr, start), DbUtils.convertRegion2Snpfeatureid(chr, end));
			return new LinkedHashSet<VSnpInExon>(query.getResultList());
		} catch (NoResultException nre) {
			nre.printStackTrace();
			return null;
		}

	}

	@Override
	public Set getSnps(String chr, Collection poslist) {
		try {

			if (chr.toLowerCase().equals("any")) {
				Set setSnpfeatureid = new TreeSet();
				Iterator<MultiReferencePosition> it = poslist.iterator();
				StringBuffer buff = new StringBuffer();
				while (it.hasNext()) {
					MultiReferencePosition pos = it.next();
					BigDecimal snpfearueid = DbUtils.convertRegion2Snpfeatureid(pos.getContig(),
							pos.getPosition().longValue());
					setSnpfeatureid.add(snpfearueid);
					buff.append(snpfearueid + ", ");
				}
				// AppContext.debug(" snpfeatureid in " + buff);

				Set setAll = new HashSet();
				Set sets[] = DbUtils.setSlicer(setSnpfeatureid);
				for (int iset = 0; iset < sets.length; iset++) {
					// Query query = createNamedQuery("findVSnpInExonBySnpFeatureIdIn", -1,
					// -1,setSnpfeatureid);
					Query query = createNamedQuery("findVSnpInExonBySnpFeatureIdIn", -1, -1, sets[iset]);
					setAll.addAll(query.getResultList());
				}
				return setAll;

				// Query query = createNamedQuery("findVSnpInExonBySnpFeatureIdIn", -1,
				// -1,setSnpfeatureid);
				// return new HashSet<SnpsNonsynAllele>(query.getResultList());
			} else if (chr.toLowerCase().equals("loci")) {
				Iterator<Locus> it = poslist.iterator();
				// StringBuffer buff = new StringBuffer();
				Set setPos = new TreeSet();
				while (it.hasNext()) {
					Locus loc = it.next();
					setPos.addAll(getSnps(loc.getContig(), loc.getFmin(), loc.getFmax()));
				}
				return setPos;
			} else {
				
				Set setAll = new HashSet();
				Set sets[] = DbUtils.setSlicer(new HashSet(poslist));
				for (int iset = 0; iset < sets.length; iset++) {
					// Query query = createNamedQuery("findVSnpInExonBySnpFeatureIdIn", -1,
					// -1,setSnpfeatureid);
					Query query = createNamedQuery("findVSnpInExonBySnpFeatureIdIn", -1, -1,
							DbUtils.convertRegion2Snpfeatureid(chr, sets[iset]));
					setAll.addAll(query.getResultList());
				}
				return setAll;
			}

		} catch (NoResultException nre) {
			nre.printStackTrace();
			return null;
		}

	}

}
