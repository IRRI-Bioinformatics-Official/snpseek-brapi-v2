package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.ds.chado.dao.VSnpNonsynallelePosDAO;
import org.irri.iric.ds.chado.domain.SnpsNonsynAllele;
import org.irri.iric.ds.chado.domain.model.VSnpNonsynallelePos;
import org.irri.iric.ds.utils.SnpsPropertyDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpNonsynallelePos entities.
 * 
 */
@Repository("VSnpNonsynallelePosDAO")
@Transactional
public class VSnpNonsynallelePosDAOImpl extends SnpsPropertyDAO<VSnpNonsynallelePos> // AbstractJpaDao<VSnpNonsynallelePos>
		implements VSnpNonsynallelePosDAO {

	@Override
	public Boolean hasData(String ds, Integer organismId) {
		
		Set s = new HashSet();
		s.add(ds);
		Map m = variantset2snppropCount(organismId, "nonsynonymous_variant", s);
		return (Long) m.get(ds) > 0;
	}

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VSnpNonsynallelePos.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpNonsynallelePosDAOImpl
	 *
	 */
	public VSnpNonsynallelePosDAOImpl() {
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
	 * JPQL Query - findVSnpNonsynallelePosBySnpFeatureId
	 *
	 */
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosBySnpFeatureId(BigDecimal snpFeatureId)
			throws DataAccessException {

		return findVSnpNonsynallelePosBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosBySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosBySnpFeatureId(BigDecimal snpFeatureId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpNonsynallelePosBySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<VSnpNonsynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpNonsynallelePos findVSnpNonsynallelePosByPrimaryKey(BigDecimal snpFeatureId, BigDecimal typeId)
			throws DataAccessException {

		return findVSnpNonsynallelePosByPrimaryKey(snpFeatureId, typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpNonsynallelePos findVSnpNonsynallelePosByPrimaryKey(BigDecimal snpFeatureId, BigDecimal typeId,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpNonsynallelePosByPrimaryKey", startResult, maxRows, snpFeatureId,
					typeId);
			return (VSnpNonsynallelePos) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByPosition
	 *
	 */
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByPosition(BigDecimal position) throws DataAccessException {

		return findVSnpNonsynallelePosByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByPosition(BigDecimal position, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpNonsynallelePosByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpNonsynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpNonsynallelePoss
	 *
	 */
	@Transactional
	public Set<VSnpNonsynallelePos> findAllVSnpNonsynallelePoss() throws DataAccessException {

		return findAllVSnpNonsynallelePoss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpNonsynallelePoss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynallelePos> findAllVSnpNonsynallelePoss(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpNonsynallelePoss", startResult, maxRows);
		return new LinkedHashSet<VSnpNonsynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByTypeId
	 *
	 */
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByTypeId(BigDecimal typeId) throws DataAccessException {

		return findVSnpNonsynallelePosByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByTypeId(BigDecimal typeId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpNonsynallelePosByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VSnpNonsynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByAlleleNonsynContaining
	 *
	 */
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByAlleleNonsynContaining(String alleleNonsyn)
			throws DataAccessException {

		return findVSnpNonsynallelePosByAlleleNonsynContaining(alleleNonsyn, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByAlleleNonsynContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByAlleleNonsynContaining(String alleleNonsyn,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpNonsynallelePosByAlleleNonsynContaining", startResult, maxRows,
				alleleNonsyn);
		return new LinkedHashSet<VSnpNonsynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByAlleleNonsyn
	 *
	 */
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByAlleleNonsyn(String alleleNonsyn)
			throws DataAccessException {

		return findVSnpNonsynallelePosByAlleleNonsyn(alleleNonsyn, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByAlleleNonsyn
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByAlleleNonsyn(String alleleNonsyn, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpNonsynallelePosByAlleleNonsyn", startResult, maxRows, alleleNonsyn);
		return new LinkedHashSet<VSnpNonsynallelePos>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByChr
	 *
	 */
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByChr(BigDecimal chr) throws DataAccessException {

		return findVSnpNonsynallelePosByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpNonsynallelePosByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpNonsynallelePos> findVSnpNonsynallelePosByChr(BigDecimal chr, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpNonsynallelePosByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpNonsynallelePos>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpNonsynallelePos entity) {
		return true;
	}

	// @NamedQuery(name = "findVSnpNonsynallelePosByPositionBetween", query =
	// "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos
	// where myVSnpNonsynallelePos.chr = ?1 and myVSnpNonsynallelePos.position
	// between ?2 and ?3 and myVSnpNonsynallelePos.typeId=?4"),
	// @NamedQuery(name = "findVSnpNonsynallelePosByPositionIn", query = "select
	// myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where
	// myVSnpNonsynallelePos.chr = ?1 and myVSnpNonsynallelePos.position in(?2) and
	// myVSnpNonsynallelePos.typeId=?3"),
	// @NamedQuery(name = "findVSnpNonsynallelePosBySnpFeatureIdBetween", query =
	// "select myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos
	// where myVSnpNonsynallelePos.snpFeatureId between ?1 and ?2 and
	// myVSnpNonsynallelePos.typeId=?3"),
	// @NamedQuery(name = "findVSnpNonsynallelePosBySnpFeatureIdIn", query = "select
	// myVSnpNonsynallelePos from VSnpNonsynallelePos myVSnpNonsynallelePos where
	// myVSnpNonsynallelePos.snpFeatureId in (?1) and
	// myVSnpNonsynallelePos.typeId=?2"),

	//
	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	// private List<SnpsNonsynAllele> executeSQL(String sql) {
	// //System.out.println("executing :" + sql);
	// //log.info("executing :" + sql);
	// AppContext.debug("executing " + sql);
	// List listResult = null;
	// try {
	// listResult=
	// getSession().createSQLQuery(sql).addEntity(VSnpNonsynallelePos.class).list();
	// } catch(Exception ex) {
	// AppContext.debug(ex.getMessage());
	// ex.printStackTrace();
	// throw new RuntimeException(ex);
	// }
	// AppContext.debug("result " + listResult.size() + " SnpsNonsynAllele");
	// return listResult;
	// }

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosBetween(Integer organismId, String chr, Integer start, Integer end,
			BigDecimal dataset) throws DataAccessException {

		return findSnpsPropertyByChrPosBetween(organismId, chr, start, end, dataset, "nonsynonymous_variant", "VSnpNonsynallelePos",
				", sfp.value AS allele_nonsyn", VSnpNonsynallelePos.class);
		// if(AppContext.isBypassViews()) {
		//
		// //String sql = "SELECT cast(mv.snp_feature_id as numeric) snp_feature_id,
		// mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id,
		// sfp.value AS allele_nonsyn FROM mv_snp_refposindex mv, snp_featureprop sfp "
		// +
		// // " WHERE mv.TYPE_ID=" + dataset + " and mv.snp_feature_id =
		// sfp.snp_feature_id and mv.chromosome=" + AppContext.guessChrFromString(chr) +
		// " and mv.POSITION between " + start + " and " + end + " AND sfp.type_id in
		// (select cvterm_id from cvterm where name='non-synonymous')";
		// // return new LinkedHashSet(executeSQL(sql));
		// String sqldirect="";
		// sqldirect+="SELECT sfl.snp_feature_id, sfl.srcfeature_id-2 AS chromosome,
		// sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS
		// variantset, sfp.value AS allele_nonsyn ";
		// sqldirect+=" FROM " +AppContext.getDefaultSchema() +".snp_featureloc sfl, "
		// +AppContext.getDefaultSchema() +".variant_variantset vvs, " +
		// AppContext.getDefaultSchema() +".variantset v ";
		// sqldirect+=" , snp_featureprop sfp WHERE sfl.snp_feature_id =
		// sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND
		// vvs.variantset_id = v.variantset_id ";
		// sqldirect+=" AND sfp.type_id in (select cvterm_id from cvterm where
		// name='nonsynonymous_variant')";
		// sqldirect+=" and sfl.organism_id=9 and sfl.srcfeature_id=" +
		// AppContext.guessChrFromString(chr) + "+2 ";
		// sqldirect+=" and sfl.position between " + start + "-1 and " + end + "-1 and
		// v.variantset_id=" + dataset + " order by sfl.position";
		// return new LinkedHashSet(executeSQL(sqldirect, VSnpNonsynallelePos.class));
		//
		// }
		//
		// Query query = createNamedQuery("findVSnpNonsynallelePosByPositionBetween",
		// -1, -1, BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chr))),
		// BigDecimal.valueOf(start), BigDecimal.valueOf(end), dataset);
		// java.util.List list=query.getResultList();
		// java.util.Set set=new LinkedHashSet<SnpsNonsynAllele>(list);
		// AppContext.debug( "findSnpNonsynAlleleByChrPosBetween list=" + list.size() +
		// " set=" + set.size());
		// return set;
	}

	@Override
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosBetween(Integer organismId, String chr, Integer start, Integer end,
			Set variantset) throws DataAccessException {
		

		return findSnpsPropertyByChrPosBetween(organismId, chr, start, end, variantset, "nonsynonymous_variant",
				"VSnpNonsynallelePos", ", sfp.value AS allele_nonsyn", VSnpNonsynallelePos.class);

		// if(AppContext.isBypassViews()) {
		// //String sql = "SELECT cast(mv.snp_feature_id as numeric) snp_feature_id,
		// mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id,
		// sfp.value AS allele_nonsyn FROM mv_snp_refposindex mv, snp_featureprop sfp "
		// +
		// // " WHERE mv.variantset in (" + AppContext.toCSVquoted(variantset, "'") + ")
		// and mv.snp_feature_id = sfp.snp_feature_id and mv.chromosome=" +
		// AppContext.guessChrFromString(chr) + " and mv.POSITION between " + start + "
		// and " + end + " AND sfp.type_id in (select cvterm_id from cvterm where
		// name='non-synonymous')";
		// //return new LinkedHashSet(executeSQL(sql));
		//
		// String sqldirect="";
		// sqldirect+="SELECT sfl.snp_feature_id, sfl.srcfeature_id-2 AS chromosome,
		// sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS
		// variantset, sfp.value AS allele_nonsyn ";
		// sqldirect+=" FROM " +AppContext.getDefaultSchema() +".snp_featureloc sfl, "
		// +AppContext.getDefaultSchema() +".variant_variantset vvs, " +
		// AppContext.getDefaultSchema() +".variantset v ";
		// sqldirect+=" , snp_featureprop sfp WHERE sfl.snp_feature_id =
		// sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND
		// vvs.variantset_id = v.variantset_id ";
		// sqldirect+=" AND sfp.type_id in (select cvterm_id from cvterm where
		// name='nonsynonymous_variant')";
		// sqldirect+=" and sfl.organism_id=9 and sfl.srcfeature_id=" +
		// AppContext.guessChrFromString(chr) + "+2 ";
		// sqldirect+=" and sfl.position between " + start + "-1 and " + end + "-1 and
		// v.name in (" + AppContext.toCSVquoted(variantset,"'") + ") order by
		// sfl.position";
		// return new LinkedHashSet(executeSQL(sqldirect));
		//
		// }
		//
		// Query query =
		// createNamedQuery("findVSnpNonsynallelePosByPositionBetweenSnpset", -1, -1,
		// BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chr))),
		// BigDecimal.valueOf(start), BigDecimal.valueOf(end), variantset);
		// java.util.List list=query.getResultList();
		// java.util.Set set=new LinkedHashSet<SnpsNonsynAllele>(list);
		// AppContext.debug( "findVSnpNonsynallelePosByPositionBetweenSnpset list=" +
		// list.size() + " set=" + set.size());
		// return set;
	}

	// @Override
	// public Set<SnpsNonsynAllele> findSnpNonsynAlleleBySnpfeatureidIn(
	// Collection listFeatureids, BigDecimal dataset) throws DataAccessException {
	// 
	// System.out.println(" snpfeatureid in " + listFeatureids.size()); // +
	// AppContext.convertRegion2Snpfeatureid( chr, Long.valueOf(start)) + " and " +
	// AppContext.convertRegion2Snpfeatureid( chr, Long.valueOf(end)) );
	// Query query = createNamedQuery("findVSnpNonsynallelePosBySnpFeatureIdIn", -1,
	// -1, listFeatureids, dataset);
	// return new HashSet<SnpsNonsynAllele>(query.getResultList());
	// }
	//
	// @Override
	// public Set<SnpsNonsynAllele> findSnpNonsynAlleleBySnpfeatureidBetween(
	// BigDecimal start, BigDecimal end, BigDecimal dataset) {
	// 
	// System.out.println(" snpfeatureid between " + start + " and " + end );
	// Query query =
	// createNamedQuery("findVSnpNonsynallelePosBySnpFeatureIdBetween", -1, -1,
	// start, end , dataset);
	// return new HashSet<SnpsNonsynAllele>(query.getResultList());
	// }

	@Override
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByFeatureidIn(Integer organismId, Collection featureid) throws DataAccessException {

		return findSnpsPropertyByFeatureidIn(organismId, featureid, "nonsynonymous_variant", "VSnpNonsynallelePos",
				", sfp.value AS allele_nonsyn", VSnpNonsynallelePos.class);

		//
		// 
		// Set sets[]=AppContext.setSlicer((Set)featureid);
		// Set setAll=new LinkedHashSet();
		// for(int iset=0; iset<sets.length; iset++) {
		// if(AppContext.isBypassViews() && AppContext.isPostgres()) {
		//// String sql = "SELECT cast( mv.snp_feature_id as numeric) snp_feature_id,
		// mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id,
		// sfp.value AS allele_nonsyn FROM mv_snp_refposindex mv, snp_featureprop sfp "
		// +
		//// //" WHERE mv.TYPE_ID=" + dataset + " and mv.snp_feature_id =
		// sfp.snp_feature_id and " +
		//// " WHERE mv.snp_feature_id = sfp.snp_feature_id and " +
		//// " exists ( select t.column_value from (select unnest(ARRAY" + sets[iset] +
		// ")column_value) t where t.column_value=mv.snp_feature_id ) " +
		//// " AND sfp.type_id in (select cvterm_id from cvterm where
		// name='non-synonymous')";
		//// setAll.addAll(executeSQL(sql));
		//
		// String sqldirect="";
		// sqldirect+="SELECT sfl.snp_feature_id, sfl.srcfeature_id-2 AS chromosome,
		// sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS
		// variantset, sfp.value AS allele_nonsyn ";
		// sqldirect+=" FROM " +AppContext.getDefaultSchema() +".snp_featureloc sfl, "
		// +AppContext.getDefaultSchema() +".variant_variantset vvs, " +
		// AppContext.getDefaultSchema() +".variantset v ";
		// sqldirect+=" , snp_featureprop sfp WHERE sfl.snp_feature_id =
		// sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND
		// vvs.variantset_id = v.variantset_id ";
		// sqldirect+=" AND sfp.type_id in (select cvterm_id from cvterm where
		// name='nonsynonymous_variant')";
		// sqldirect+=" and sfl.organism_id=9";
		// sqldirect+=" and exists ( select t.column_value from (select unnest(ARRAY" +
		// sets[iset] + ")column_value) t where t.column_value=sfl.snp_feature_id ) ";
		// sqldirect+=" order by sfl.position";
		// setAll.addAll(executeSQL(sqldirect));
		//
		// }
		// else {
		// //Query query = createNamedQuery("findVSnpNonsynallelePosBySnpFeatureIdIn",
		// -1, -1, sets[iset], dataset);
		// Query query = createNamedQuery("findVSnpNonsynallelePosBySnpFeatureIdIn", -1,
		// -1, sets[iset]);
		// setAll.addAll(query.getResultList());
		// }
		// }
		// return setAll;

	}

	@Override
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosIn(Integer organismId, String chr, Collection poslist, Set dataset) {
		

		return findSnpsPropertyByChrPosIn(organismId, chr, poslist, dataset, "nonsynonymous_variant", "VSnpNonsynallelePos",
				", sfp.value AS allele_nonsyn", VSnpNonsynallelePos.class);

		// Set setSnpfeatureid = new HashSet();
		// if(chr.toLowerCase().equals("any")) {
		//
		//
		// Set setAll = new HashSet();
		//
		// Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(poslist);
		// Iterator<String> itChr=mapChr2Pos.keySet().iterator();
		// while(itChr.hasNext()) {
		// String chrstr=itChr.next();
		// Set sets[] = AppContext.setSlicer(new
		// LinkedHashSet((Set)mapChr2Pos.get(chrstr)));
		// for(int iset=0; iset<sets.length; iset++) {
		//
		// if(AppContext.isBypassViews() && AppContext.isPostgres()) {
		//// String sql = "SELECT cast(mv.snp_feature_id as numeric) snp_feature_id,
		// mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id,
		// sfp.value AS allele_nonsyn FROM mv_snp_refposindex mv, snp_featureprop sfp "
		// +
		//// //" WHERE mv.TYPE_ID=" + dataset + " and mv.snp_feature_id =
		// sfp.snp_feature_id and mv.chromosome=" +
		// AppContext.guessChrFromString(chrstr) + " and " +
		//// " WHERE mv.variantset in (" + AppContext.toCSVquoted( dataset,"'") + ") and
		// mv.snp_feature_id = sfp.snp_feature_id and mv.chromosome=" +
		// AppContext.guessChrFromString(chrstr) + " and " +
		//// " exists ( select t.column_value from (select unnest(ARRAY" + sets[iset] +
		// ")column_value) t where t.column_value-1=mv.POSITION ) " +
		//// " AND sfp.type_id in (select cvterm_id from cvterm where
		// name='non-synonymous')";
		//// setAll.addAll(executeSQL(sql));
		//
		// setAll.addAll( findSnpNonsynAlleleByChrPosIn( chr, sets[iset] , dataset));
		//
		// }
		// else {
		// //Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1,
		// -1,setSnpfeatureid);
		// Query query = createNamedQuery("findVSnpNonsynallelePosByPositionIn", -1, -1,
		// BigDecimal.valueOf(Long.valueOf( AppContext.guessChrFromString(chrstr))),
		// sets[iset], dataset);
		// setAll.addAll(query.getResultList());
		// }
		// }
		// }
		// //return new HashSet<SnpsNonsynAllele>(query.getResultList());
		// return setAll;
		//
		//
		// /*
		// Iterator<VSnpRefposindex> it = (Iterator<VSnpRefposindex>)poslist.iterator();
		// //StringBuffer buff = new StringBuffer();
		// while(it.hasNext()) {
		// VSnpRefposindex pos = it.next();
		// setSnpfeatureid.add( pos .getSnpFeatureId());
		// //buff.append(snpfearueid + ", " );
		// }
		// //AppContext.debug(" snpfeatureid in " + buff);
		//
		// Set setAll = new HashSet();
		// Set sets[] = AppContext.setSlicer(setSnpfeatureid);
		// for(int iset=0; iset<sets.length; iset++) {
		// //Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1,
		// -1,setSnpfeatureid);
		// Query query = createNamedQuery("findVSnpNonsynallelePosBySnpFeatureIdIn", -1,
		// -1,sets[iset]);
		// setAll.addAll(query.getResultList());
		// }
		// //return new HashSet<SnpsNonsynAllele>(query.getResultList());
		// return setAll;
		// */
		//
		// /*
		// AppContext.debug("checking " + posset.size() + " snp positions");
		// Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
		// String sql = "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL
		// , ALLELE_INDEX from " + AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX
		// WHERE 1=1 and (";
		// Iterator<String> itContig= mapChr2Pos.keySet().iterator();
		// while(itContig.hasNext()) {
		// String contigstr = itContig.next();
		// String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
		// Collection setPos = (Collection)mapChr2Pos.get(contigstr);
		// sql+= "( chromosome=" + contig + " and position in (" +
		// setPos.toString().replace("[", "").replace("]", "") + ")) ";
		// if(itContig.hasNext())
		// sql += " or ";
		// };
		//
		// sql += ") and TYPE_ID=" + type + " order by CHROMOSOME, POSITION";
		// return executeSQL(sql);
		// */
		// }
		// else if(chr.toLowerCase().equals("loci")) {
		// Iterator<Locus> it = poslist.iterator();
		// //StringBuffer buff = new StringBuffer();
		// Set setPos = new TreeSet();
		// while(it.hasNext()) {
		// Locus loc=it.next();
		// setPos.addAll( findSnpNonsynAlleleByChrPosBetween(loc.getContig(),
		// loc.getFmin(), loc.getFmax(), dataset) );
		// }
		// return setPos;
		// }
		// else {
		//
		// Set setAll = new HashSet();
		// Set sets[] = AppContext.setSlicer(new LinkedHashSet(poslist));
		// for(int iset=0; iset<sets.length; iset++) {
		// //Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1,
		// -1,setSnpfeatureid);
		//
		// if(AppContext.isBypassViews() && AppContext.isPostgres()) {
		//// String sql = "SELECT cast(mv.snp_feature_id as numeric) snp_feature_id,
		// mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id,
		// sfp.value AS allele_nonsyn FROM mv_snp_refposindex mv, snp_featureprop sfp "
		// +
		//// " WHERE mv.TYPE_ID=" + dataset + " and mv.snp_feature_id =
		// sfp.snp_feature_id and mv.chromosome=" + AppContext.guessChrFromString(chr) +
		// " and " +
		//// " exists ( select t.column_value from (select unnest(ARRAY" + sets[iset] +
		// ")column_value) t where t.column_value-1=mv.POSITION ) " +
		//// " AND sfp.type_id in (select cvterm_id from cvterm where
		// name='non-synonymous')";
		//// setAll.addAll(executeSQL(sql));
		////
		// String sqldirect="";
		// sqldirect+="SELECT sfl.snp_feature_id, sfl.srcfeature_id-2 AS chromosome,
		// sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS
		// variantset, sfp.value AS allele_nonsyn ";
		// sqldirect+=" FROM " +AppContext.getDefaultSchema() +".snp_featureloc sfl, "
		// +AppContext.getDefaultSchema() +".variant_variantset vvs, " +
		// AppContext.getDefaultSchema() +".variantset v ";
		// sqldirect+=" , snp_featureprop sfp WHERE sfl.snp_feature_id =
		// sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND
		// vvs.variantset_id = v.variantset_id ";
		// sqldirect+=" AND sfp.type_id in (select cvterm_id from cvterm where
		// name='nonsynonymous_variant')";
		// sqldirect+=" and sfl.organism_id=9 and sfl.srcfeature_id=" +
		// AppContext.guessChrFromString(chr) + "+2 ";
		// sqldirect+=" and exists ( select t.column_value from (select unnest(ARRAY" +
		// sets[iset] + ")column_value) t where t.column_value-1=sfl.position ) ";
		// sqldirect+=" order by sfl.position";
		// setAll.addAll(executeSQL(sqldirect));
		//
		//
		// }
		// else {
		// Query query = createNamedQuery("findVSnpNonsynallelePosByPositionIn", -1, -1,
		// BigDecimal.valueOf(Long.valueOf( AppContext.guessChrFromString(chr))),
		// sets[iset], dataset);
		// setAll.addAll(query.getResultList());
		// }
		// }
		// //return new HashSet<SnpsNonsynAllele>(query.getResultList());
		// return setAll;
		// }
	}

}
