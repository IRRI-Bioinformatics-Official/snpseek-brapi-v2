package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.ds.chado.dao.VLocusPromoterDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImpl;
import org.irri.iric.ds.chado.domain.model.VLocusPromoter;
import org.irri.iric.ds.utils.DbUtils;
import org.irri.iric.ds.utils.TextSearchOptions;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusPromoter entities.
 * 
 */
// @Repository("VLocusPromoterDAO")
@Repository("LocusPromoterDAO")
@Transactional
public class VLocusPromoterDAOImpl extends AbstractJpaDao<VLocusPromoter> implements VLocusPromoterDAO {

	private Logger log = Logger.getLogger(VLocusPromoterDAOImpl.class.getName());
	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VLocusPromoter.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusPromoterDAOImpl
	 *
	 */
	public VLocusPromoterDAOImpl() {
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
	 * JPQL Query - findVLocusPromoterByPfeatureNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureNameContaining(String pfeatureName)
			throws DataAccessException {

		return findVLocusPromoterByPfeatureNameContaining(pfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureNameContaining(String pfeatureName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureNameContaining", startResult, maxRows,
				pfeatureName);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByContigId
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByContigId(BigDecimal contigId) throws DataAccessException {

		return findVLocusPromoterByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByContigId(BigDecimal contigId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusPromoters
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findAllVLocusPromoters() throws DataAccessException {

		return findAllVLocusPromoters(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusPromoters
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findAllVLocusPromoters(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusPromoters", startResult, maxRows);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByCommonNameContaining(String commonName) throws DataAccessException {

		return findVLocusPromoterByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByCommonNameContaining(String commonName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByNameContaining(String name) throws DataAccessException {

		return findVLocusPromoterByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByNotesContaining(String notes) throws DataAccessException {

		return findVLocusPromoterByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByNotesContaining(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureName
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureName(String pfeatureName) throws DataAccessException {

		return findVLocusPromoterByPfeatureName(pfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureName(String pfeatureName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureName", startResult, maxRows, pfeatureName);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureTypeContaining
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureTypeContaining(String pfeatureType)
			throws DataAccessException {

		return findVLocusPromoterByPfeatureTypeContaining(pfeatureType, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureTypeContaining(String pfeatureType, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureTypeContaining", startResult, maxRows,
				pfeatureType);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureEnd
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureEnd(Integer pfeatureEnd) throws DataAccessException {

		return findVLocusPromoterByPfeatureEnd(pfeatureEnd, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureEnd
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureEnd(Integer pfeatureEnd, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureEnd", startResult, maxRows, pfeatureEnd);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByNotes
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByNotes(String notes) throws DataAccessException {

		return findVLocusPromoterByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByGeneOverlaps
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByGeneOverlaps(String geneOverlaps) throws DataAccessException {

		return findVLocusPromoterByGeneOverlaps(geneOverlaps, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByGeneOverlaps
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByGeneOverlaps(String geneOverlaps, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByGeneOverlaps", startResult, maxRows, geneOverlaps);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByOrganismId(BigDecimal organismId) throws DataAccessException {

		return findVLocusPromoterByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByFmax
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByFmax(java.math.BigDecimal fmax) throws DataAccessException {

		return findVLocusPromoterByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByFmax(java.math.BigDecimal fmax, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByName
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByName(String name) throws DataAccessException {

		return findVLocusPromoterByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureChrContaining
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureChrContaining(String pfeatureChr)
			throws DataAccessException {

		return findVLocusPromoterByPfeatureChrContaining(pfeatureChr, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureChrContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureChrContaining(String pfeatureChr, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureChrContaining", startResult, maxRows, pfeatureChr);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureStart
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureStart(Integer pfeatureStart) throws DataAccessException {

		return findVLocusPromoterByPfeatureStart(pfeatureStart, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureStart
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureStart(Integer pfeatureStart, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureStart", startResult, maxRows, pfeatureStart);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByFeatureId
	 *
	 */
	@Transactional
	public VLocusPromoter findVLocusPromoterByFeatureId(BigDecimal featureId) throws DataAccessException {

		return findVLocusPromoterByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByFeatureId
	 *
	 */

	@Transactional
	public VLocusPromoter findVLocusPromoterByFeatureId(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusPromoterByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.ds.chado.domain.model.VLocusPromoter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusPromoterByContigName
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByContigName(String contigName) throws DataAccessException {

		return findVLocusPromoterByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByContigName(String contigName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByCommonName(String commonName) throws DataAccessException {

		return findVLocusPromoterByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByCommonName(String commonName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByFmin
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByFmin(java.math.BigDecimal fmin) throws DataAccessException {

		return findVLocusPromoterByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByFmin(java.math.BigDecimal fmin, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByStrand
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByStrand(java.math.BigDecimal strand) throws DataAccessException {

		return findVLocusPromoterByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByStrand(java.math.BigDecimal strand, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusPromoter findVLocusPromoterByPrimaryKey(BigDecimal featureId) throws DataAccessException {

		return findVLocusPromoterByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusPromoter findVLocusPromoterByPrimaryKey(BigDecimal featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusPromoterByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.ds.chado.domain.model.VLocusPromoter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusPromoterByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByContigNameContaining(String contigName) throws DataAccessException {

		return findVLocusPromoterByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByContigNameContaining(String contigName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByContigNameContaining", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByGeneOverlapsContaining
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByGeneOverlapsContaining(String geneOverlaps)
			throws DataAccessException {

		return findVLocusPromoterByGeneOverlapsContaining(geneOverlaps, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByGeneOverlapsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByGeneOverlapsContaining(String geneOverlaps, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByGeneOverlapsContaining", startResult, maxRows,
				geneOverlaps);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureDb
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureDb(BigDecimal pfeatureDb) throws DataAccessException {

		return findVLocusPromoterByPfeatureDb(pfeatureDb, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureDb
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureDb(BigDecimal pfeatureDb, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureDb", startResult, maxRows, pfeatureDb);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureChr
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureChr(String pfeatureChr) throws DataAccessException {

		return findVLocusPromoterByPfeatureChr(pfeatureChr, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureChr(String pfeatureChr, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureChr", startResult, maxRows, pfeatureChr);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureId
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureId(BigDecimal pfeatureId) throws DataAccessException {

		return findVLocusPromoterByPfeatureId(pfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureId(BigDecimal pfeatureId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureId", startResult, maxRows, pfeatureId);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureType
	 *
	 */
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureType(String pfeatureType) throws DataAccessException {

		return findVLocusPromoterByPfeatureType(pfeatureType, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusPromoterByPfeatureType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusPromoter> findVLocusPromoterByPfeatureType(String pfeatureType, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusPromoterByPfeatureType", startResult, maxRows, pfeatureType);
		return new LinkedHashSet<VLocusPromoter>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusPromoter entity) {
		return true;
	}

	@Override
	public Set<Locus> getLociWithPromoters(String contig, Collection posset, int db, String organism, Integer plusminus,
			String genemodel) {

		List listLoci = new ArrayList();
		if (contig.toLowerCase().equals("any")) {
			Map<String, Set<BigDecimal>> mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			Iterator<String> itChr = mapChr2Pos.keySet().iterator();
			while (itChr.hasNext()) {
				String chr = itChr.next();
				listLoci.addAll(getLocusPromoterByContigPositions(chr, mapChr2Pos.get(chr), db, organism, plusminus,
						genemodel));
			}

		} else {
			listLoci.addAll(getLocusPromoterByContigPositions(contig, posset, db, organism, plusminus, genemodel));
		}
		return new LinkedHashSet(listLoci);
	}

	@Override
	public List<Locus> getLocusByName(String name) {
		return null;
	}

	@Override
	public Collection getLocusByName(Collection<String> name) {
		return null;
	}

	/*
	 * @Override public List<Locus> getLocusByRegion(String contig, Long start, Long
	 */
	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel) {
		return null;
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus) {

		// return getLocusPromoterByContigPositions(contig, posset, -1, organism,
		// plusminus, null);
		return null;
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel) {
		return null;
		// return getLocusByContigPositions(contig, posset, -1, organism, plusminus,
		// genemodel);

	}

	private List getLocusPromoterByContigPositions(String contig, Collection posset, int db, String organism,
			Integer plusminus, String genemodel) {
//		if (AppContext.isOracle())
//			return getLocusPromoterByContigPositionsOracle(contig, posset, db, organism, plusminus, genemodel);
//		else if (AppContext.isPostgres())
		return getLocusPromoterByContigPositionsPostgres(contig, posset, db, organism, plusminus, genemodel);
//		return null;
	}

	private List getLocusPromoterByContigPositionsOracle(String contig, Collection posset, int db, String organism,
			Integer plusminus, String genemodel) {

		posset = DbUtils.convertPos2Position(posset);

		String mpm = "";
		String ppm = "";
		if (plusminus != null && plusminus.intValue() != 0) {
			mpm = "-" + plusminus;
			ppm = "+" + plusminus;
		}

		String anddb = "";
		if (db > 0)
			anddb = " and pf.db=" + db + " ";

		List pos = new ArrayList();

		Set setpos[] = DbUtils.setSlicer(new TreeSet(posset), 900);
		for (int i = 0; i < setpos.length; i++) {

			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT distinct o.common_name, fsrc.feature_id AS contig_id, fsrc.uniquename AS contig_name,f.name AS name,f.feature_id,fl.fmin,fl.fmax,fl.strand,")
					.append("o.organism_id,dbms_lob.substr(pf.note, 1000) AS notes,pf.startpos pfeature_start,pf.endpos pfeature_end,pf.chrstr pfeature_chr,pf.promoter_id pfeature_id,")
					.append("pf.name pfeature_name,pf.db pfeature_db,ct.name pfeature_type,pf.gene_overlaps ")
					.append("FROM public.feature f, public" + ".feature fsrc, public.featureloc fl, "
							+ "public.TMP_PROMOTER_1 pf, public" + ".organism o, public.cvterm ct, ")

					.append(" (select distinct column_value pos from table(sys.odcinumberlist("
							+ DbUtils.toCSV(setpos[i]) + "))) postable ")

					.append("WHERE pf.gene_id=f.feature_id and pf.type_id=ct.cvterm_id and f.feature_id = fl.feature_id AND f.organism_id = o.organism_id AND fl.srcfeature_id = fsrc.feature_id ")
					.append(" and pf.chrstr='" + contig.replace("chr0", "").replace("chr", "")
							+ "' and (postable.pos between (pf.startpos " + mpm + ") and (pf.endpos " + ppm + "))")
					.append(anddb);

			pos.addAll(executeSQL(sql.toString()));
		}
		return pos;

	}

	private List getLocusPromoterByContigPositionsPostgres(String contig, Collection posset, int db, String organism,
			Integer plusminus, String genemodel) {

		posset = DbUtils.convertPos2Position(posset);

		String mpm = "";
		String ppm = "";
		if (plusminus != null && plusminus.intValue() != 0) {
			mpm = "-" + plusminus;
			ppm = "+" + plusminus;
		}

		String anddb = "";
		if (db > 0)
			anddb = " and pf.db=" + db + " ";

		List pos = new ArrayList();

		Set setpos[] = DbUtils.setSlicer(new TreeSet(posset), 900);
		for (int i = 0; i < setpos.length; i++) {

			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT distinct o.common_name, fsrc.feature_id AS contig_id, fsrc.uniquename AS contig_name,f.name AS name,f.feature_id,fl.fmin,fl.fmax,fl.strand,")
					.append("o.organism_id, substring(pf.note,1, 1000) AS notes,pf.startpos pfeature_start,pf.endpos pfeature_end,pf.chrstr pfeature_chr,pf.promoter_id pfeature_id,")
					.append("pf.name pfeature_name,pf.db pfeature_db,ct.name pfeature_type,pf.gene_overlaps ")
					.append("FROM public.feature f, public" + ".feature fsrc, public.featureloc fl, "
							+ "public.TMP_PROMOTER_1 pf, public" + ".organism o, public.cvterm ct, ")

					// .append( " (select distinct column_value pos from table(sys.odcinumberlist("
					// + AppContext.toCSV(setpos[i])+ "))) postable ")

					.append(" (select unnest(ARRAY[" + DbUtils.toCSV(setpos[i]) + "]) pos)  postable ")

					.append("WHERE pf.gene_id=f.feature_id and pf.type_id=ct.cvterm_id and f.feature_id = fl.feature_id AND f.organism_id = o.organism_id AND fl.srcfeature_id = fsrc.feature_id ")
					.append(" and pf.chrstr='" + contig.replace("chr0", "").replace("chr", "")
							+ "' and (postable.pos between (pf.startpos " + mpm + ") and (pf.endpos " + ppm + "))")
					.append(anddb);

			pos.addAll(executeSQL(sql.toString()));
		}
		return pos;

	}

	@Override
	public List<Locus> getLocusByDescription(TextSearchOptions description, String organism) {
		return null;
	}

	@Override
	public List<Locus> getLocusByDescription(TextSearchOptions description, String organism, String genemodel) {
		return null;
	}

	@Override
	public List<Locus> getLocusBySynonyms(TextSearchOptions synonym, String organism) {
		return null;
	}

	@Override
	public List<Locus> getLocusBySynonyms(TextSearchOptions synonym, String organism, String genemodel) {
		return null;
	}

	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel,
			String featuretype) {
		return null;
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel, String featuretype) {
		return null;
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	private List<Locus> executeSQL(String sql) {
		// System.out.println("executing :" + sql);
		// log.info("executing :" + sql);
		log.info("executing " + sql);
		List listResult = getSession().createSQLQuery(sql).addEntity(VLocusPromoter.class).list();
		log.info("result " + listResult.size() + " loci");
		return listResult;
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel, Set featuretype) {
		return null;
	}

	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel,
			Set featuretype) {
		return null;
	}

}
