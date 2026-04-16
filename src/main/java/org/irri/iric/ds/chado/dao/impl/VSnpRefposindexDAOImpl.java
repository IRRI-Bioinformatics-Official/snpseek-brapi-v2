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
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.ds.chado.dao.VSnpRefposindexDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImpl;
import org.irri.iric.ds.chado.domain.model.Organism;
import org.irri.iric.ds.chado.domain.model.VSnpRefposindex;
import org.irri.iric.ds.utils.DbUtils;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpRefposindex entities.
 * 
 */
@Repository("VSnpRefposindexDAO")
@Transactional
public class VSnpRefposindexDAOImpl extends AbstractJpaDao<VSnpRefposindex> implements VSnpRefposindexDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VSnpRefposindex.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpRefposindexDAOImpl
	 *
	 */
	public VSnpRefposindexDAOImpl() {
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
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findAllVSnpRefposindexs() throws DataAccessException {

		return findAllVSnpRefposindexs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */

	public Query createNamedQuery(String queryName, Integer firstResult, Integer maxResults, Object... parameters) {

		// TODO check if IRRILAN
		// if (AppContext.isIRRILAN()) {
		//
		// StringBuffer buff = new StringBuffer();
		// if (parameters != null) {
		// for (int i = 0; i < parameters.length; i++) {
		// buff.append(parameters[i].toString() + ", ");
		// }
		// }
		// }

		return super.createNamedQuery(queryName, firstResult, maxResults, parameters);

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findAllVSnpRefposindexs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpRefposindexs", startResult, maxRows);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall) throws DataAccessException {

		return findVSnpRefposindexByRefcallContaining(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByRefcallContaining", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException {

		return findVSnpRefposindexBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexBySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(BigDecimal typeId) throws DataAccessException {

		return findVSnpRefposindexByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(BigDecimal typeId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(java.math.BigDecimal chromosome)
			throws DataAccessException {

		return findVSnpRefposindexByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(java.math.BigDecimal chromosome, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall) throws DataAccessException {

		return findVSnpRefposindexByRefcall(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByRefcall", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(java.math.BigDecimal position)
			throws DataAccessException {

		return findVSnpRefposindexByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(java.math.BigDecimal position, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(BigDecimal snpFeatureId, BigDecimal typeId)
			throws DataAccessException {

		return findVSnpRefposindexByPrimaryKey(snpFeatureId, typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(BigDecimal snpFeatureId, BigDecimal typeId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpRefposindexByPrimaryKey", startResult, maxRows, snpFeatureId,
					typeId);
			return (VSnpRefposindex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(java.math.BigDecimal alleleIndex)
			throws DataAccessException {

		return findVSnpRefposindexByAlleleIndex(alleleIndex, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(java.math.BigDecimal alleleIndex, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByAlleleIndex", startResult, maxRows, alleleIndex);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpRefposindex entity) {
		return true;
	}

	//
	// @Override
	// public List getSNPs(String chromosome, Integer startPos, Integer endPos,
	// BigDecimal type) {
	//
	// return getSNPs( chromosome, startPos, endPos, type, -1, -1);
	// }
	//
	//
	// @Override
	// public List getSNPs(String chr, Integer startPos, Integer endPos,
	// BigDecimal type, int firstRow, int maxRows) {
	//
	// BigDecimal bdChr=null;
	// try {
	// chr = chr.toUpperCase().replace("CHR0","").replace("CHR","");
	// try {
	// bdChr = BigDecimal.valueOf(Long.valueOf(chr));
	// if(startPos!=null && endPos!=null) {
	// //AppContext.debug("findVSnpRefposindexByChrPosBetween: " + bdChr + " " +
	// startPos + " " + endPos + " " + type);
	//
	// if(AppContext.isBypassViews()) {
	//
	// String sqldirect="";
	// sqldirect+="SELECT cast(mv_snp_refposindex.snp_feature_id as numeric)
	// snp_feature_id, mv_snp_refposindex.type_id, mv_snp_refposindex.chromosome,
	// mv_snp_refposindex.position + 1 AS \"position\", mv_snp_refposindex.refcall,
	// mv_snp_refposindex.altcall, ";
	// sqldirect+="mv_snp_refposindex.allele_index FROM " +
	// AppContext.getDefaultSchema() + ".mv_snp_refposindex where
	// mv_snp_refposindex.chromosome=" + bdChr ;
	// sqldirect+=" and mv_snp_refposindex.position+1 between " + startPos + " and "
	// + endPos + " and mv_snp_refposindex.type_id=" + type + " order by
	// mv_snp_refposindex.position";
	// return executeSQL(sqldirect);
	// } else {
	// Query query = createNamedQuery("findVSnpRefposindexByChrPosBetween",
	// firstRow, maxRows, bdChr, BigDecimal.valueOf(startPos),
	// BigDecimal.valueOf(endPos), type);
	// return query.getResultList();
	// }
	// } else {
	// //AppContext.debug("findVSnpRefposindexByChr: " + bdChr + " " + type);
	// Query query = createNamedQuery("findVSnpRefposindexByChr", firstRow, maxRows,
	// bdChr, type);
	// return query.getResultList();
	//
	// }
	// } catch(Exception ex) {
	// //AppContext.debug("findVSnpRefposindexByTypeId: " + type);
	// Query query = createNamedQuery("findVSnpRefposindexByTypeId", firstRow,
	// maxRows, type);
	// return query.getResultList();
	// }
	// } catch ( Exception ex) {
	// ex.printStackTrace();
	// }
	// return new ArrayList();
	// }

	@Override
	public List getSNPs(Integer organismId, String chr, Integer startPos, Integer endPos, Set variantset) {
		return getSNPs(organismId, chr, startPos, endPos, variantset, -1, -1);
	}

	// QUERY: Active
	@Override
	public List getSNPs(Integer organismId, String chr, Integer startPos, Integer endPos, Set variantset, int firstRow, int maxRows) {

		System.out.println("GET SNPS.." );
		
		
		BigDecimal bdChr = null;
		try {

			System.out.println("Setting CHR.." + chr);

			chr = chr.toUpperCase().replace("CHROMOSOME0", "").replace("CHROMOSOME", "").replace("CHR0", "")
					.replace("CHR", "");
			
			System.out.println("CHR.." + chr);

			try {
				bdChr = BigDecimal.valueOf(Long.valueOf(chr));
				if (startPos != null && endPos != null) {
					// TIMER HERE DAGS
					// TODO check bypass
					// if (AppContext.isBypassViews()) {

					String sqldirect = "";
					sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + DbUtils.chr2srcfeatureidOffset(organismId)
							+ " AS chromosome, sfl.position + 1 AS \"position\", sfl.refcall, '' AS altcall, ";
					sqldirect += " vvs.hdf5_index AS allele_index, v.variantset_id AS type_id, v.name AS variantset FROM "
							+ "public.snp_featureloc sfl, " + "public.variant_variantset vvs, "
							+ "public.variantset v ";
					sqldirect += " WHERE sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
					// sqldirect+=" and NULLIF(replace(lower(srcf.name::text), 'chr'::text,
					// ''::text), ''::text)::integer=" + bdChr;
					sqldirect += " and sfl.organism_id=" + organismId + " and sfl.srcfeature_id="
							+ bdChr + "+" + DbUtils.chr2srcfeatureidOffset(organismId);
					sqldirect += " and sfl.position between " + startPos + "-1 and " + endPos + "-1 and v.name in ("
							+ DbUtils.toCSVquoted(variantset, "'") + ") order by sfl.position";

					System.out.println("RUNNING: " + sqldirect);
					return executeSQL(sqldirect);
					// TODO removed and commented because if bypass
					// } else {
					// Query query =
					// createNamedQuery("findVSnpRefposindexByChrPosBetweenVariantset", firstRow,
					// maxRows, bdChr, BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos),
					// variantset);
					// return query.getResultList();
					// }
				} else {
					System.out.println("ELSE.." + chr);

					if (startPos != null && endPos != null) {
						// TODO check bypass
						// if (AppContext.isBypassViews()) {
						String sqldirect = "";
						sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id- " + DbUtils.chr2srcfeatureidOffset(organismId)
								+ " AS chromosome, sfl.position + 1 AS \"position\", sfl.refcall, ''  AS altcall, ";
						sqldirect += " vvs.hdf5_index AS allele_index, v.variantset_id AS type_id, v.name AS variantset FROM "
								+ "public.snp_featureloc sfl, " + "public.variant_variantset vvs, "
								+ "public.feature fsrc," + "public.variantset v ";
						sqldirect += " WHERE sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id and fsrc.feature_id=sfl.srcfeature_id ";
						// sqldirect+=" and NULLIF(replace(lower(srcf.name::text), 'chr'::text,
						// ''::text), ''::text)::integer=" + bdChr;
						sqldirect += " and sfl.organism_id=" + DbUtils.getDefaultOrganismId()
								+ " and upper(fsrc.name)='" + chr.toUpperCase() + "' ";
						sqldirect += " and sfl.position between " + startPos + "-1 and " + endPos + "-1 and v.name in ("
								+ DbUtils.toCSVquoted(variantset, "'") + ") order by sfl.position";
						return executeSQL(sqldirect);
						// TODO remove becase of bypass
						// } else {
						//
						// Query query =
						// createNamedQuery("findVSnpRefposindexByChrPosBetweenVariantset", firstRow,
						// maxRows, bdChr, BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos),
						// variantset);
						// return query.getResultList();
						// }
					} else {
						// AppContext.debug("findVSnpRefposindexByChr: " + bdChr + " " + type);
						System.out.println("findVSnpRefposindexByChrVariantset querying");

						Query query = createNamedQuery("findVSnpRefposindexByChrVariantset", firstRow, maxRows, bdChr,
								variantset);
						return query.getResultList();
					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
				if (startPos != null && endPos != null) {
					Query query = createNamedQuery("findVSnpRefposindexByChrPosBetweenVariantset", firstRow, maxRows,
							chr, BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), variantset);
					return query.getResultList();
				} else {
					Query query = createNamedQuery("findVSnpRefposindexByChrVariantset", firstRow, maxRows, bdChr,
							variantset);
					return query.getResultList();
				}

			}
		} catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return new ArrayList();
	}

	@Override
	public long countSNPs(String chr, Integer startPos, Integer endPos, Set variantset) {

		BigDecimal bdChr = null;
		try {
			chr = chr.toUpperCase().replace("CHR0", "").replace("CHR", "");
			try {
				bdChr = BigDecimal.valueOf(Long.valueOf(chr));

				if (startPos != null && endPos != null) {
					Query query = createNamedQuery("countVSnpRefposindexByChrPosBetweenVariantset", -1, -1, bdChr,
							BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), variantset);
					return ((Number) query.getSingleResult()).longValue();
				} else {
					Query query = createNamedQuery("countVSnpRefposindexByChrVariantset", -1, -1, bdChr, variantset);
					return ((Number) query.getSingleResult()).longValue();
				}

			} catch (Exception ex) {
				Query query = createNamedQuery("countVSnpRefposindexByVariantset", -1, -1, variantset);
				return ((Number) query.getSingleResult()).longValue();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	// @Override
	// public long countSNPsInChromosome(String chr, Collection posset, BigDecimal
	// type) {
	// return _countSNPsInChromosome(chr, posset, type);
	// }

	@Override
	public long countSNPsInChromosome(String chr, Collection posset, Set variantset) {
		return _countSNPsInChromosome(chr, posset, variantset);
	}

	// @Override
	// public List getSNPsInChromosome(String chr, Collection posset, BigDecimal
	// type) {
	// return _getSNPsInChromosome(chr, posset, type);
	//
	// /*
	// Set slicedset[] = AppContext.setSlicer(new TreeSet(posset));
	// List listPresent = new ArrayList();
	// for(int iset=0; iset<slicedset.length; iset++) {
	// listPresent.addAll( _getSNPsInChromosome(chr, slicedset[iset], type) );
	// }
	// return listPresent;
	// */
	// }

	@Override
	public List getSNPsInChromosome(Integer organismId, String chr, Collection posset, Set variantset) {
		return _getSNPsInChromosome(organismId, chr, posset, variantset);
	}

	// private long _countSNPsInChromosome(String chr, Collection posset, BigDecimal
	// type) {
	//
	//
	// BigDecimal bdChr=null;
	// if(chr.toLowerCase().equals("any")) {
	//
	// long poscount=0;
	// long totalcount=-1;
	// List listPresent = new ArrayList();
	// AppContext.debug("checking " + posset.size() + " snp positions");
	// Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
	// String sql = "select count(*) from ( "; //select SNP_FEATURE_ID, TYPE_ID ,
	// CHROMOSOME, POSITION , REFCALL , ALLELE_INDEX from " +
	// AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX_V2_V2 srp WHERE 1=1 and
	// (";
	// Iterator<String> itContig= mapChr2Pos.keySet().iterator();
	// while(itContig.hasNext()) {
	// String contigstr = itContig.next();
	// String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
	// Collection setPos = (Collection)mapChr2Pos.get(contigstr);
	// poscount+=setPos.size();
	//
	// Set slicedset[] = AppContext.setSlicer(new TreeSet(setPos), 900);
	// for(int iset=0; iset<slicedset.length; iset++) {
	//
	// //sql += " select 1 from ( select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME,
	// POSITION , REFCALL , '' ALTCALL, ALLELE_INDEX from " +
	// AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX_V2 srp WHERE 1=1 and (";
	// sql += " select 1 from ( select SNP_FEATURE_ID from " +
	// AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX_V2 srp WHERE 1=1 and (";
	//
	// //Set slicedset[] = AppContext.setSlicer(new TreeSet(setPos));
	// if(AppContext.isOracle())
	// sql+= "( srp.TYPE_ID=" + type + " and srp.chromosome=" + contig + " and
	// exists (select column_value from table(sys.odcinumberlist(" +
	// slicedset[iset].toString().replace("[", "").replace("]", "") + " )) t where
	// t.column_value=srp.position ))) ) ";
	// else if (AppContext.isPostgres())
	// sql+= "( srp.TYPE_ID=" + type + " and srp.chromosome=" + contig + " and
	// exists ( select t.column_value from (select unnest(ARRAY[" +
	// slicedset[iset].toString().replace("[", "").replace("]", "") + "])
	// column_value) t where t.column_value=srp.position ))) ) ";
	//
	//
	// if(iset<slicedset.length-1) sql += contigstr +"_" + iset + " union ";
	// }
	// if(itContig.hasNext())
	// sql += " contigall union ";
	// };
	//
	// sql+=")";
	//
	//
	// /*
	// with names (fname,lname) as (
	// values
	// ('John','Smith'),
	// ('Mary','Jones')
	// )
	// select city from user
	// inner join names on
	// fname=firstName and
	// lname=lastName;
	//
	// */
	// AppContext.debug("counting V_SNP_REFPOSINDEX_V2 with " + poscount + "
	// positions");
	// return executeCountSQL(sql);
	// }
	// else if(chr.toLowerCase().equals("loci")) {
	//
	// long poscount=0;
	// AppContext.debug("checking " + posset.size() + " loci");
	// Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);
	// //String sql = "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION ,
	// REFCALL , '' ALTCALL, ALLELE_INDEX from " + AppContext.getDefaultSchema() +
	// ".V_SNP_REFPOSINDEX_V2 WHERE 1=1 and (";
	// String sql = "select count(*) from (select 1 from " +
	// AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX_V2 WHERE 1=1 and (";
	// Iterator<String> itContig= mapChr2Pos.keySet().iterator();
	// while(itContig.hasNext()) {
	// String contigstr = itContig.next();
	// String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
	// Collection<Locus> setLocus = (Collection)mapChr2Pos.get(contigstr);
	// poscount+=setLocus.size();
	// Iterator<Locus> itLocus=setLocus.iterator();
	// while(itLocus.hasNext()) {
	// Locus loc=itLocus.next();
	// sql+= "( chromosome=" + contig + " and position between " + loc.getFmin() + "
	// and " + loc.getFmax() + ") ";
	// if(itLocus.hasNext())
	// sql += " or ";
	// }
	// if(itContig.hasNext())
	// sql += " or ";
	//
	// };
	//
	// sql += ") and TYPE_ID=" + type + ")";
	//
	// AppContext.debug("counting V_SNP_REFPOSINDEX_V2 with " + poscount + " loci");
	//
	// return executeCountSQL(sql);
	// }
	// else {
	//
	// AppContext.debug("counting V_SNP_REFPOSINDEX_V2 with " + posset.size() + "
	// positions");
	// try {
	// chr = chr.toUpperCase().replace("CHR0","").replace("CHR","");
	// bdChr = BigDecimal.valueOf(Long.valueOf(chr));
	//
	//
	// Set slicedset[] = AppContext.setSlicer(new TreeSet(posset));
	// long lcount=0;
	// for(int iset=0; iset<slicedset.length; iset++) {
	//
	// //Query query = createNamedQuery("findVSnpRefposindexByChrPosIn", -1, -1,
	// bdChr , slicedset[iset], type);
	// Query query = createNamedQuery("countVSnpRefposindexByChrPosIn", -1, -1,
	// bdChr , slicedset[iset], type);
	// lcount+= ((Long)query.getSingleResult()).longValue();
	// }
	// return lcount;
	//
	// } catch ( Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	// return -1;
	// }

	private long _countSNPsInChromosome(String chr, Collection posset, Set variantset) {

		BigDecimal bdChr = null;
		if (chr.toLowerCase().equals("any")) {

			long poscount = 0;
			long totalcount = -1;
			List listPresent = new ArrayList();

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			String sql = "select count(*) from ( ";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection setPos = (Collection) mapChr2Pos.get(contigstr);
				poscount += setPos.size();

				Set slicedset[] = DbUtils.setSlicer(new TreeSet(setPos), 900);
				for (int iset = 0; iset < slicedset.length; iset++) {

					sql += " select 1 from ( select SNP_FEATURE_ID from "
							+ "public.V_SNP_REFPOSINDEX_V2 srp WHERE 1=1 and (";

					// if (AppContext.isOracle())
					// sql += "( srp.VARIANTSET in (" + AppContext.toCSVquoted(variantset, "'")
					// + ") and srp.chromosome=" + contig
					// + " and exists (select column_value from table(sys.odcinumberlist("
					// + slicedset[iset].toString().replace("[", "").replace("]", "")
					// + " )) t where t.column_value=srp.position ))) ) ";
					// else if (AppContext.isPostgres())
					sql += "( srp.VARIANTSET in (" + DbUtils.toCSVquoted(variantset, "'") + ") and srp.chromosome="
							+ contig + " and exists (  select t.column_value from (select unnest(ARRAY["
							+ slicedset[iset].toString().replace("[", "").replace("]", "")
							+ "]) column_value) t where t.column_value=srp.position ))) )   ";

					if (iset < slicedset.length - 1)
						sql += contigstr + "_" + iset + " union ";
				}
				if (itContig.hasNext())
					sql += " contigall union ";
			}
			;

			sql += ")";

			/*
			 * with names (fname,lname) as ( values ('John','Smith'), ('Mary','Jones') )
			 * select city from user inner join names on fname=firstName and lname=lastName;
			 * 
			 */

			return executeCountSQL(sql);
		} else if (chr.toLowerCase().equals("loci")) {

			long poscount = 0;

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);

			String sql = "select count(*) from (select 1 from " + "public.V_SNP_REFPOSINDEX_V2 WHERE 1=1 and (";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection<Locus> setLocus = (Collection) mapChr2Pos.get(contigstr);
				poscount += setLocus.size();
				Iterator<Locus> itLocus = setLocus.iterator();
				while (itLocus.hasNext()) {
					Locus loc = itLocus.next();
					sql += "( chromosome=" + contig + " and position between " + loc.getFmin() + " and " + loc.getFmax()
							+ ") ";
					if (itLocus.hasNext())
						sql += " or ";
				}
				if (itContig.hasNext())
					sql += " or ";

			}
			;

			sql += ") and VARIANTSET in (" + DbUtils.toCSVquoted(variantset, "'") + ") )";

			return executeCountSQL(sql);
		} else {

			try {
				chr = chr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				bdChr = BigDecimal.valueOf(Long.valueOf(chr));

				Set slicedset[] = DbUtils.setSlicer(new TreeSet(posset));
				long lcount = 0;
				for (int iset = 0; iset < slicedset.length; iset++) {

					// Query query = createNamedQuery("findVSnpRefposindexByChrPosIn", -1, -1, bdChr
					// , slicedset[iset], type);
					Query query = createNamedQuery("countVSnpRefposindexByChrPosInVariantset", -1, -1, bdChr,
							slicedset[iset], variantset);
					lcount += ((Long) query.getSingleResult()).longValue();
				}
				return lcount;

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return -1;
	}

	// private List _getSNPsInChromosome(String chr, Collection posset, BigDecimal
	// type) {
	// if(AppContext.isOracle()) return _getSNPsInChromosomeOracle( chr, posset,
	// type);
	// else if(AppContext.isPostgres()) {
	// if(AppContext.isBypassViews())
	// return _getSNPsInChromosomePostgresBypass( chr, posset, type);
	// else return _getSNPsInChromosomePostgres( chr, posset, type);
	// }
	// return null;
	// }

	private List _getSNPsInChromosome(Integer organismId, String chr, Collection posset, Set variantset) {

		// TODO check if bypass
		// else if (AppContext.isPostgres()) {
		// if (AppContext.isBypassViews())
		return _getSNPsInChromosomePostgresBypass(organismId, chr, posset, variantset);
		// else
		// return _getSNPsInChromosomePostgres(chr, posset, variantset);
		// }
		// return null;
	}

	private List _getSNPsInChromosomeOracle(String chr, Collection posset, BigDecimal type) {

		BigDecimal bdChr = null;

		String sqldirect = "";
		sqldirect += "SELECT mv_snp_refposindex.snp_feature_id, mv_snp_refposindex.type_id, mv_snp_refposindex.chromosome, mv_snp_refposindex.\"position\" + 1 AS \"position\", mv_snp_refposindex.refcall,  mv_snp_refposindex.altcall, ";
		sqldirect += "mv_snp_refposindex.allele_index FROM " + "public.mv_snp_refposindex where (";

		if (chr.toLowerCase().equals("any")) {

			long poscount = 0;
			List listPresent = new ArrayList();

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			String sql = ""; // select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL ,
								// ALLELE_INDEX from " + AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX_V2
								// srp WHERE 1=1 and (";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection setPos = (Collection) mapChr2Pos.get(contigstr);
				poscount += setPos.size();

				Set slicedset[] = DbUtils.setSlicer(new TreeSet(setPos), 900);
				for (int iset = 0; iset < slicedset.length; iset++) {

					sql += " select * from (";
					// TODO check for byppass
//					if (AppContext.isBypassViews())
					sql += sqldirect;
//					else
//						sql += "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , '' ALTCALL,  ALLELE_INDEX from "
//								+ "public.V_SNP_REFPOSINDEX_V2 srp WHERE 1=1 and (";

					// Set slicedset[] = AppContext.setSlicer(new TreeSet(setPos));
					sql += "( srp.TYPE_ID=" + type + " and srp.chromosome=" + contig
							+ " and exists (select column_value from table(sys.odcinumberlist("
							+ slicedset[iset].toString().replace("[", "").replace("]", "")
							+ " )) t where t.column_value=srp.position ))) order by srp.position) ";

					if (iset < slicedset.length - 1)
						sql += " union ";
				}
				if (itContig.hasNext())
					sql += " union ";
			}
			;

			sql += ") order by 3,4";

			/*
			 * with names (fname,lname) as ( values ('John','Smith'), ('Mary','Jones') )
			 * select city from user inner join names on fname=firstName and lname=lastName;
			 * 
			 */

			return executeSQL(sql);
		} else if (chr.toLowerCase().equals("loci")) {

			long poscount = 0;

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);

			String sql = "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , '' ALTCALL,  ALLELE_INDEX from "
					+ "public.V_SNP_REFPOSINDEX_V2 WHERE 1=1 and (";

			// TODO check for bypass
			// if (AppContext.isBypassViews())
			sql = sqldirect;

			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection<Locus> setLocus = (Collection) mapChr2Pos.get(contigstr);
				poscount += setLocus.size();
				Iterator<Locus> itLocus = setLocus.iterator();
				while (itLocus.hasNext()) {
					Locus loc = itLocus.next();
					sql += "( chromosome=" + contig + " and position between " + loc.getFmin() + " and " + loc.getFmax()
							+ ") ";
					if (itLocus.hasNext())
						sql += " or ";
				}
				if (itContig.hasNext())
					sql += " or ";

			}
			;

			sql += ") and TYPE_ID=" + type + " order by CHROMOSOME, POSITION";

			return executeSQL(sql);
		} else {

			String contigstr = chr.toUpperCase().replace("CHR0", "").replace("CHR", ""); // itContig.next();
			String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
			Collection setPos = posset; // (Collection)mapChr2Pos.get(contigstr);

			Set slicedset[] = DbUtils.setSlicer(new TreeSet(setPos), 900);
			String sql = "";
			for (int iset = 0; iset < slicedset.length; iset++) {

				sql += " select * from (";

				// TODO check for the bypass
				// if (AppContext.isBypassViews())
				sql += sqldirect;
				// else
				// sql += "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ''
				// ALTCALL, ALLELE_INDEX from "
				// + AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX_V2 srp WHERE 1=1 and
				// (";

				sql += "( srp.TYPE_ID=" + type + " and srp.chromosome=" + contig
						+ " and exists (select column_value from table(sys.odcinumberlist("
						+ slicedset[iset].toString().replace("[", "").replace("]", "")
						+ " )) t where t.column_value=srp.position ))) order by srp.position) ";

				if (iset < slicedset.length - 1)
					sql += " union ";
			}

			sql += " order by 3,4";
			return executeSQL(sql);
			/*
			 * try { chr = chr.toUpperCase().replace("CHR0","").replace("CHR",""); bdChr =
			 * BigDecimal.valueOf(Long.valueOf(chr));
			 * 
			 * 
			 * Set slicedset[] = AppContext.setSlicer(new TreeSet(posset)); List listPresent
			 * = new ArrayList(); for(int iset=0; iset<slicedset.length; iset++) {
			 * 
			 * Query query = createNamedQuery("findVSnpRefposindexByChrPosIn", -1, -1, bdChr
			 * , slicedset[iset], type); listPresent.addAll( query.getResultList() ); }
			 * return listPresent;
			 * 
			 * } catch ( Exception ex) { ex.printStackTrace(); }
			 */
		}

		// return new ArrayList();
	}

	private List _getSNPsInChromosomePostgres(String chr, Collection posset, Set variantset) {

		BigDecimal bdChr = null;
		if (chr.toLowerCase().equals("any")) {

			long poscount = 0;
			List listPresent = new ArrayList();

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			String sql = ""; // select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL ,
								// ALLELE_INDEX from " + AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX_V2
								// srp WHERE 1=1 and (";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection setPos = (Collection) mapChr2Pos.get(contigstr);
				poscount += setPos.size();

				Set slicedset[] = DbUtils.setSlicer(new TreeSet(setPos), 900);
				for (int iset = 0; iset < slicedset.length; iset++) {

					sql += " select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , '' ALTCALL,  ALLELE_INDEX from ( select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , cast('' as text) ALTCALL,  ALLELE_INDEX from "
							+ "public.V_SNP_REFPOSINDEX_V2 srp WHERE 1=1 and (";

					// Set slicedset[] = AppContext.setSlicer(new TreeSet(setPos));
					sql += "( srp.variantset in (" + DbUtils.toCSVquoted(variantset, "'") + ") and srp.chromosome="
							+ contig + " and exists (  select t.column_value from (select unnest(ARRAY["
							+ slicedset[iset].toString().replace("[", "").replace("]", "")
							+ "]) column_value) t where t.column_value=srp.position ))) )   ";

					if (iset < slicedset.length - 1)
						sql += contigstr + "_" + iset + " union ";
				}
				if (itContig.hasNext())
					sql += contigstr + " union ";
			}
			;

			sql += " contall order by 3,4";

			/*
			 * with names (fname,lname) as ( values ('John','Smith'), ('Mary','Jones') )
			 * select city from user inner join names on fname=firstName and lname=lastName;
			 * 
			 */

			return executeSQL(sql);
		} else if (chr.toLowerCase().equals("loci")) {

			long poscount = 0;

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);
			String sql = "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ''  ALTCALL,  ALLELE_INDEX from "
					+ "public.V_SNP_REFPOSINDEX_V2 WHERE 1=1 and (";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection<Locus> setLocus = (Collection) mapChr2Pos.get(contigstr);
				poscount += setLocus.size();
				Iterator<Locus> itLocus = setLocus.iterator();
				while (itLocus.hasNext()) {
					Locus loc = itLocus.next();
					sql += "( chromosome=" + contig + " and position between " + loc.getFmin() + " and " + loc.getFmax()
							+ ") ";
					if (itLocus.hasNext())
						sql += " or ";
				}
				if (itContig.hasNext())
					sql += " or ";

			}
			;

			sql += ") and variantset in (" + DbUtils.toCSVquoted(variantset, "'") + ") order by CHROMOSOME, POSITION";

			return executeSQL(sql);
		} else {

			String contigstr = chr; // itContig.next();
			String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
			Collection setPos = posset; // (Collection)mapChr2Pos.get(contigstr);
			// poscount+=setPos.size();

			Set slicedset[] = DbUtils.setSlicer(new TreeSet(setPos), 900);
			String sql = "";
			for (int iset = 0; iset < slicedset.length; iset++) {

				sql += " select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , '' ALTCALL,  ALLELE_INDEX from ( select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , cast('' as text) ALTCALL,  ALLELE_INDEX from "
						+ "public.V_SNP_REFPOSINDEX_V2 srp WHERE 1=1 and (";

				// Set slicedset[] = AppContext.setSlicer(new TreeSet(setPos));
				sql += "( srp.variantset in (" + DbUtils.toCSVquoted(variantset, "'") + ") and srp.chromosome=" + contig
						+ " and exists (  select t.column_value from (select unnest(ARRAY["
						+ slicedset[iset].toString().replace("[", "").replace("]", "")
						+ "]) column_value) t where t.column_value=srp.position ))) )   ";

				if (iset < slicedset.length - 1)
					sql += contigstr + "_" + iset + " union ";
			}

			sql += " order by POSITION";
			return executeSQL(sql);

		}

	}

	private List _getSNPsInChromosomePostgresBypass(Integer organismId, String chr, Collection posset, Set variantset) {

		String sqldirect = "";
		sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + DbUtils.chr2srcfeatureidOffset(organismId)
				+ " AS chromosome, sfl.position + 1 AS \"position\", sfl.refcall, '' AS altcall, ";
		sqldirect += " vvs.hdf5_index AS allele_index, v.variantset_id AS type_id, v.name AS variantset FROM "
				+ "public.snp_featureloc sfl, " + "public.variant_variantset vvs, public.variantset v ";
		sqldirect += " WHERE sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id and (";

		BigDecimal bdChr = null;
		// if(chr.toLowerCase().equals("any")) {
		if (!chr.toLowerCase().equals("loci")) {

			long poscount = 0;
			List listPresent = new ArrayList();

			Map mapChr2Pos = null;
			if (chr.toLowerCase().equals("any"))
				mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			else {
				mapChr2Pos = new TreeMap();
				mapChr2Pos.put(chr.toLowerCase(), posset);
			}

			String sql = ""; // sqldirect; // ""; //select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION ,
								// REFCALL , ALLELE_INDEX from " + AppContext.getDefaultSchema() +
								// ".V_SNP_REFPOSINDEX_V2 srp WHERE 1=1 and (";
			sqldirect = "";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection setPos = (Collection) mapChr2Pos.get(contigstr);
				poscount += setPos.size();

				sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + DbUtils.chr2srcfeatureidOffset(organismId)
						+ " AS chromosome, sfl.position + 1 AS \"position\", sfl.refcall, '' AS altcall, ";
				sqldirect += " vvs.hdf5_index AS allele_index, v.variantset_id AS type_id, v.name AS variantset FROM "
						+ "public.snp_featureloc sfl, " + "public.variant_variantset vvs, public.variantset v, ";
				sqldirect += "(select unnest(ARRAY" + setPos + ") column_value) t where sfl.srcfeature_id=" + contig
						+ "+" + DbUtils.chr2srcfeatureidOffset(organismId) + " and v.name in ("
						+ DbUtils.toCSVquoted(variantset, "'") + ") and t.column_value-1=sfl.position ";
				sqldirect += " and v.variantset_id=vvs.variantset_id and vvs.variant_feature_id=sfl.snp_feature_id ";

				// sqldirect+=" WHERE sfl.snp_feature_id = vvs.variant_feature_id AND
				// vvs.variantset_id = v.variantset_id and (";

				if (itContig.hasNext())
					sqldirect += " union ";
			}
			;

			sqldirect += " order by 3,4";

			sql = sqldirect;

			return executeSQL(sql);
		} else {

			long poscount = 0;

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);
			String sql = sqldirect; // "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ''
									// ALTCALL, ALLELE_INDEX from " + AppContext.getDefaultSchema() +
									// ".V_SNP_REFPOSINDEX_V2 WHERE 1=1 and (";

			sql += " v.name in (" + DbUtils.toCSVquoted(variantset, "'") + ") and sfl.organism_id="
					+ DbUtils.getDefaultOrganismId() + " and (";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				// String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
				Collection<Locus> setLocus = (Collection) mapChr2Pos.get(contigstr);
				poscount += setLocus.size();
				Iterator<Locus> itLocus = setLocus.iterator();
				while (itLocus.hasNext()) {
					Locus loc = itLocus.next();
					sql += "(  sfl.srcfeature_id=" + loc.getChr() + "+" + DbUtils.chr2srcfeatureidOffset(organismId)
							+ " and sfl.position between " + (loc.getFmin() - 1) + " and " + (loc.getFmax() - 1) + ") ";

					if (itLocus.hasNext())
						sql += " or ";
				}
				if (itContig.hasNext())
					sql += " or ";

			}
			sql += ")";
			;

			// sql += ") order by CHROMOSOME, POSITION";
			sql += ") order by sfl.srcfeature_id, sfl.POSITION";

			return executeSQL(sql);
		}
	}

	private List _getSNPsInChromosomePostgresBypass(String chr, Collection posset, BigDecimal type) {

		String sqldirect = "";
		sqldirect += "SELECT cast(mv_snp_refposindex.snp_feature_id as numeric) snp_feature_id, mv_snp_refposindex.type_id, mv_snp_refposindex.chromosome, mv_snp_refposindex.\"position\" + 1 AS \"position\", mv_snp_refposindex.refcall,  mv_snp_refposindex.altcall, ";
		sqldirect += "mv_snp_refposindex.allele_index FROM " + "public.mv_snp_refposindex where (";

		BigDecimal bdChr = null;
		// if(chr.toLowerCase().equals("any")) {
		if (!chr.toLowerCase().equals("loci")) {

			long poscount = 0;
			List listPresent = new ArrayList();

			Map mapChr2Pos = null;
			if (chr.toLowerCase().equals("any"))
				mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			else {
				mapChr2Pos = new TreeMap();
				mapChr2Pos.put(chr.toLowerCase(), posset);
			}

			String sql = ""; // sqldirect; // ""; //select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION ,
								// REFCALL , ALLELE_INDEX from " + AppContext.getDefaultSchema() +
								// ".V_SNP_REFPOSINDEX_V2 srp WHERE 1=1 and (";
			sqldirect = "";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection setPos = (Collection) mapChr2Pos.get(contigstr);
				poscount += setPos.size();

				sqldirect += "SELECT cast(mv_snp_refposindex.snp_feature_id as numeric) snp_feature_id, mv_snp_refposindex.type_id, mv_snp_refposindex.chromosome, mv_snp_refposindex.\"position\" + 1 AS \"position\", mv_snp_refposindex.refcall,  mv_snp_refposindex.altcall, ";
				sqldirect += "mv_snp_refposindex.allele_index FROM " + "public.mv_snp_refposindex, ";
				sqldirect += "(select unnest(ARRAY" + setPos + ") column_value) t where chromosome=" + contig
						+ " and type_id=" + type + " and t.column_value-1=position ";

				if (itContig.hasNext())
					sqldirect += " union ";
				/*
				 * Set slicedset[] = AppContext.setSlicer(new TreeSet(setPos), 900); for(int
				 * iset=0; iset<slicedset.length; iset++) {
				 * 
				 * sql+=sqldirect; sql+= "(  CHROMOSOME=" + contig + "  and TYPE_ID=" + type +
				 * " and exists (  select t.column_value from (select unnest(ARRAY[" +
				 * slicedset[iset].toString().replace("[", "").replace("]", "") +
				 * "]) column_value) t where t.column_value-1=POSITION ))   ";
				 * 
				 * //if(iset<slicedset.length-1) sql += contigstr + "_" + iset + " union ";
				 * if(iset<slicedset.length-1) sql += " or "; } if(itContig.hasNext()) //sql +=
				 * contigstr + " union "; sql += " union ";
				 */

			}
			;

			// sql += " contall order by 3,4";
			sqldirect += " order by 3,4";

			sql = sqldirect;

			/*
			 * with names (fname,lname) as ( values ('John','Smith'), ('Mary','Jones') )
			 * select city from user inner join names on fname=firstName and lname=lastName;
			 * 
			 */

			return executeSQL(sql);
		}
		// else if(chr.toLowerCase().equals("loci")) {
		else {

			long poscount = 0;

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);
			String sql = sqldirect; // "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ''
									// ALTCALL, ALLELE_INDEX from " + AppContext.getDefaultSchema() +
									// ".V_SNP_REFPOSINDEX_V2 WHERE 1=1 and (";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				// String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
				Collection<Locus> setLocus = (Collection) mapChr2Pos.get(contigstr);
				poscount += setLocus.size();
				Iterator<Locus> itLocus = setLocus.iterator();
				while (itLocus.hasNext()) {
					Locus loc = itLocus.next();
					sql += "( TYPE_ID=" + type + " and chromosome=" + loc.getChr() + " and position between "
							+ (loc.getFmin() - 1) + " and " + (loc.getFmax() - 1) + ") ";
					if (itLocus.hasNext())
						sql += " or ";
				}
				if (itContig.hasNext())
					sql += " or ";

			}
			;

			sql += ") order by CHROMOSOME, POSITION";

			return executeSQL(sql);
		}

	}

	private List executeSQL(String sql) {

		try {
			List l = getSession().createSQLQuery(sql).addEntity(VSnpRefposindex.class).list();

			return l;
		} catch (Exception ex) {

			ex.printStackTrace();
			throw ex;
		}
	}

	private long executeCountSQL(String sql) {
		try {
			return (Long) (getSession().createSQLQuery(sql).addEntity(Long.class).list().get(0));
		} catch (Exception ex) {

			ex.printStackTrace();
			throw ex;
		}
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	
}
