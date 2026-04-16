package org.irri.iric.ds.chado.dao.impl;

import java.util.Arrays;
import java.util.Collection;
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

import org.irri.iric.ds.chado.dao.VLocusIntxnRicenetv2DAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VLocusIntxnRicenetv2;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusIntxnRicenetv2 entities.
 * 
 */
@Repository("VLocusIntxnRicenetv2DAO")
@Transactional
public class VLocusIntxnRicenetv2DAOImpl extends AbstractJpaDao<VLocusIntxnRicenetv2>
		implements VLocusIntxnRicenetv2DAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VLocusIntxnRicenetv2.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusIntxnRicenetv2DAOImpl
	 *
	 */
	public VLocusIntxnRicenetv2DAOImpl() {
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
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigNameContaining(String contigName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigNameContaining(String contigName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByContigNameContaining", startResult, maxRows,
				contigName);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureId(Integer qfeatureId)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByQfeatureId(qfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureId(Integer qfeatureId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByQfeatureId", startResult, maxRows, qfeatureId);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByCommonNameContaining(String commonName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByCommonNameContaining", startResult, maxRows,
				commonName);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFmax
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByFmax(java.math.BigDecimal fmax)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByFmax(java.math.BigDecimal fmax, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByName(String name) throws DataAccessException {

		return findVLocusIntxnRicenetv2ByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByOrganismId(Integer organismId)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByOrganismId(Integer organismId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFmin
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByFmin(java.math.BigDecimal fmin)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByFmin(java.math.BigDecimal fmin, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByScore
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByScore(java.math.BigDecimal score)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByScore(score, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByScore(java.math.BigDecimal score, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByScore", startResult, maxRows, score);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNotes
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNotes(String notes) throws DataAccessException {

		return findVLocusIntxnRicenetv2ByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByStrand
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByStrand(java.math.BigDecimal strand)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByStrand(java.math.BigDecimal strand, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusIntxnRicenetv2s
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findAllVLocusIntxnRicenetv2s() throws DataAccessException {

		return findAllVLocusIntxnRicenetv2s(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusIntxnRicenetv2s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findAllVLocusIntxnRicenetv2s(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusIntxnRicenetv2s", startResult, maxRows);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigName(String contigName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigName(String contigName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByCommonName(String commonName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByCommonName(String commonName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNotesContaining(String notes)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNotesContaining(String notes, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNameContaining(String name) throws DataAccessException {

		return findVLocusIntxnRicenetv2ByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigId
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigId(Integer contigId) throws DataAccessException {

		return findVLocusIntxnRicenetv2ByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByContigId(Integer contigId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFeatureId
	 *
	 */
	@Transactional
	public VLocusIntxnRicenetv2 findVLocusIntxnRicenetv2ByFeatureId(Integer featureId) throws DataAccessException {

		return findVLocusIntxnRicenetv2ByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByFeatureId
	 *
	 */

	@Transactional
	public VLocusIntxnRicenetv2 findVLocusIntxnRicenetv2ByFeatureId(Integer featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusIntxnRicenetv2ByFeatureId", startResult, maxRows, featureId);
			return (VLocusIntxnRicenetv2) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureNameContaining(String qfeatureName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByQfeatureNameContaining(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureNameContaining(String qfeatureName,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByQfeatureNameContaining", startResult, maxRows,
				qfeatureName);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusIntxnRicenetv2 findVLocusIntxnRicenetv2ByPrimaryKey(Integer featureId) throws DataAccessException {

		return findVLocusIntxnRicenetv2ByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusIntxnRicenetv2 findVLocusIntxnRicenetv2ByPrimaryKey(Integer featureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusIntxnRicenetv2ByPrimaryKey", startResult, maxRows, featureId);
			return (VLocusIntxnRicenetv2) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByRank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByRank(Integer rank) throws DataAccessException {

		return findVLocusIntxnRicenetv2ByRank(rank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByRank(Integer rank, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByRank", startResult, maxRows, rank);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByDenserank
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByDenserank(Integer denserank) throws DataAccessException {

		return findVLocusIntxnRicenetv2ByDenserank(denserank, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByDenserank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByDenserank(Integer denserank, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByDenserank", startResult, maxRows, denserank);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureName
	 *
	 */
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureName(String qfeatureName)
			throws DataAccessException {

		return findVLocusIntxnRicenetv2ByQfeatureName(qfeatureName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusIntxnRicenetv2ByQfeatureName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusIntxnRicenetv2> findVLocusIntxnRicenetv2ByQfeatureName(String qfeatureName, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByQfeatureName", startResult, maxRows, qfeatureName);
		return new LinkedHashSet<VLocusIntxnRicenetv2>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusIntxnRicenetv2 entity) {
		return true;
	}

	@Override
	public List<Locus> getInteractingFeatures(Collection<Locus> colLocus, int type, Integer max) {

		Set locisId = new TreeSet();
		Iterator<Locus> itLoc = colLocus.iterator();
		while (itLoc.hasNext())
			locisId.add(itLoc.next().getFeatureId());

		// TODO check bypass
		// if (!AppContext.isBypassViews()) {
		Query query = createNamedQuery("findVLocusIntxnRicenetv2ByQfeatureIdIn", -1, -1, locisId);
		if (max != null)
			query.setMaxResults(max);
		return query.getResultList();
		// } else {
		//
		// String sql = "SELECT DISTINCT o.common_name,fsrc.feature_id AS
		// contig_id,fsrc.uniquename AS contig_name,f.name,f.feature_id,
		// fl.fmin,fl.fmax,fl.strand,o.organism_id, substring(fp.value, 1000) AS
		// notes,fq.feature_id AS qfeature_id,fq.name AS
		// qfeature_name,fint.score,fint.rank,fint.denserank,ev.max AS maxscore,ev.count
		// AS maxrank FROM feature fq, tmp_intxn_score fint,tmp_intxn_evidence
		// ev,organism o,feature fsrc,featureloc fl, (select unnest(ARRAY"
		// + locisId
		// + ") feature_id) fquery, feature f LEFT JOIN featureprop fp ON f.feature_id =
		// fp.feature_id AND (fp.type_id IN ( SELECT cvterm.cvterm_id FROM cvterm WHERE
		// cvterm.name = 'Note')) WHERE ( (fint.gene1_id = fquery.feature_id AND
		// fint.gene2_id = f.feature_id) OR (fint.gene2_id = fquery.feature_id AND
		// fint.gene1_id = f.feature_id)) AND fint.intxn_evidence_id = 1 AND
		// f.feature_id = fl.feature_id AND f.organism_id = o.organism_id AND
		// fl.srcfeature_id = fsrc.feature_id AND ev.intxn_evidence_id =
		// fint.intxn_evidence_id "
		// + " and fquery.feature_id=fq.feature_id " + (max != null ? " limit " + max :
		// "");
		// return AppContext.executeSQL(entityManager, VLocusIntxnRicenetv2.class, sql);
		// }

	}

}
