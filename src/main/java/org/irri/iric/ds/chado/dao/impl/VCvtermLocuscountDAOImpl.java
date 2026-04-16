package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
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
import org.irri.iric.ds.chado.dao.VCvtermLocuscountDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VCvtermLocuscount;
import org.irri.iric.ds.utils.DbUtils;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * DAO to manage VCvtermLocuscount entities.
 * 
 */
// @Repository("VCvtermLocuscountDAO")
@Repository("CvTermLocusCountDAO")

@Transactional
public class VCvtermLocuscountDAOImpl extends AbstractJpaDao<VCvtermLocuscount> implements VCvtermLocuscountDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VCvtermLocuscount.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VCvtermLocuscountDAOImpl
	 *
	 */
	public VCvtermLocuscountDAOImpl() {
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
	 * JPQL Query - findVCvtermLocuscountByCvTermContaining
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvTermContaining(String cvTerm) throws DataAccessException {

		return findVCvtermLocuscountByCvTermContaining(cvTerm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvTermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvTermContaining(String cvTerm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvtermLocuscountByCvTermContaining", startResult, maxRows, cvTerm);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvtermId
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvtermId(Integer cvtermId) throws DataAccessException {

		return findVCvtermLocuscountByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvtermId(Integer cvtermId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvtermLocuscountByCvtermId", startResult, maxRows, cvtermId);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvTerm
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvTerm(String cvTerm) throws DataAccessException {

		return findVCvtermLocuscountByCvTerm(cvTerm, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvTerm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvTerm(String cvTerm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvtermLocuscountByCvTerm", startResult, maxRows, cvTerm);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvName
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvName(String cvName) throws DataAccessException {

		return findVCvtermLocuscountByCvName(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvName(String cvName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvtermLocuscountByCvName", startResult, maxRows, cvName);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVCvtermLocuscounts
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findAllVCvtermLocuscounts() throws DataAccessException {

		return findAllVCvtermLocuscounts(-1, -1);
	}

	/**
	 * JPQL Query - findAllVCvtermLocuscounts
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findAllVCvtermLocuscounts(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVCvtermLocuscounts", startResult, maxRows);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByLocusCount
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByLocusCount(Integer locusCount) throws DataAccessException {

		return findVCvtermLocuscountByLocusCount(locusCount, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByLocusCount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByLocusCount(Integer locusCount, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvtermLocuscountByLocusCount", startResult, maxRows, locusCount);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvAccContaining
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvAccContaining(String cvAcc) throws DataAccessException {

		return findVCvtermLocuscountByCvAccContaining(cvAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvAccContaining(String cvAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvtermLocuscountByCvAccContaining", startResult, maxRows, cvAcc);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvNameContaining
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvNameContaining(String cvName) throws DataAccessException {

		return findVCvtermLocuscountByCvNameContaining(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvNameContaining(String cvName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvtermLocuscountByCvNameContaining", startResult, maxRows, cvName);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByPrimaryKey
	 *
	 */
	@Transactional
	public VCvtermLocuscount findVCvtermLocuscountByPrimaryKey(Integer organismId, Integer cvtermId)
			throws DataAccessException {

		return findVCvtermLocuscountByPrimaryKey(organismId, cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByPrimaryKey
	 *
	 */

	@Transactional
	public VCvtermLocuscount findVCvtermLocuscountByPrimaryKey(Integer organismId, Integer cvtermId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvtermLocuscountByPrimaryKey", startResult, maxRows, organismId,
					cvtermId);
			// return (org.irri.iric.portal.chado.domain.VCvtermLocuscount)
			// query.getSingleResult();
			return (VCvtermLocuscount) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvAcc
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvAcc(String cvAcc) throws DataAccessException {

		return findVCvtermLocuscountByCvAcc(cvAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByCvAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByCvAcc(String cvAcc, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvtermLocuscountByCvAcc", startResult, maxRows, cvAcc);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByOrganismId
	 *
	 */
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByOrganismId(Integer organismId) throws DataAccessException {

		return findVCvtermLocuscountByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermLocuscountByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermLocuscount> findVCvtermLocuscountByOrganismId(Integer organismId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVCvtermLocuscountByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VCvtermLocuscount>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VCvtermLocuscount entity) {
		return true;
	}

	private List<VCvtermLocuscount> executeSQL(String sql) {
		// System.out.println("executing :" + sql);
		// log.info("executing :" + sql);
		// AppContext.debug("executing sql: " + sql);
		return getSession().createSQLQuery(sql).addEntity(VCvtermLocuscount.class).list();
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List getCvTermLocusCount(BigDecimal orgId, Collection loci, String cv) {

		Set lociname = new HashSet();
		Iterator itLoci = loci.iterator();
		while (itLoci.hasNext()) {
			Object loc = itLoci.next();
			if (loc instanceof Locus) {
				lociname.add(((Locus) loc).getUniquename().toUpperCase());
			} else
				lociname.add(loc.toString().toUpperCase());
		}
		/*
		 * String sqllocusnames = "("; Iterator<String> itLociName =
		 * AppContext.setStringSlicer(new HashSet(lociname), true, true).iterator();
		 * while(itLociName.hasNext()) { sqllocusnames += " upper(f.uniquename) in (" +
		 * itLociName.next() + ")"; if(itLociName.hasNext()) sqllocusnames += " or "; }
		 * sqllocusnames +=")";
		 */

		if (cv == null || cv.isEmpty())
			cv = "'molecular_function','biological_process','cellular_component'";
		else if (!cv.startsWith("'"))
			cv = "'" + cv + "'";

		// (select distinct column_value uniquename from table(sys.odciVarchar2List(

		String sql = "SELECT distinct  \"ORGANISM_ID\", \"CV_NAME\",\"CVTERM_ID\",\"CV_ACC\",\"CV_TERM\" , COUNT(DISTINCT \"UNIQUENAME\") LOCUS_COUNT FROM (";
		Iterator<String> itLociName = DbUtils.setStringSlicer(new HashSet(lociname), true, true).iterator();
		while (itLociName.hasNext()) {

			String locinames = itLociName.next();

			sql += " select f.organism_id,  c.name cv_name, cv.cvterm_id, db.accession cv_acc, cv.name cv_term, f.uniquename "
					+ " from  public.feature_cvterm fc, public.cvterm cv , public.dbxref db, public.cv c, public.feature f, (select distinct column_value uniquename from table(sys.odciVarchar2List("
					+ locinames + "))) loci "
					+ " where fc.feature_id=f.feature_id and  fc.cvterm_id=cv.cvterm_id  and f.organism_id=" + orgId
					+ " and c.cv_id=cv.cv_id and c.name in(" + cv + ")" + " and cv.dbxref_id=db.dbxref_id"
					+ " and f.type_id=865" // -- ; -- gene
					+ " and upper(f.uniquename)=loci.uniquename "

					+ " UNION"
					// -- locus from feature_cvterm->subj_id->cvtermpath->obj_id
					+ " select f.organism_id,  c.name cv_name, cv_obj.cvterm_id, db_obj.accession cv_acc, cv_obj.name cv_term,  f.uniquename"
					+ " from public.feature_cvterm fc, public.cvterm cv_subj , public.dbxref db_subj, public.cv c, public.cvterm cv_obj , public.dbxref db_obj, public.cvtermpath cvtp, public.feature f, (select distinct column_value uniquename from table(sys.odciVarchar2List("
					+ locinames + "))) loci " + " where fc.feature_id=f.feature_id and f.organism_id=" + orgId
					+ " and fc.cvterm_id=cv_subj.cvterm_id " + " and c.cv_id=cv_subj.cv_id and c.name in(" + cv + ")"
					+ " and cv_subj.dbxref_id=db_subj.dbxref_id"
					+ " and cv_subj.cvterm_id=cvtp.subject_id and cv_obj.cvterm_id=cvtp.object_id_"
					+ " and cvtp.pathdistance>-1" + " and cv_obj.dbxref_id=db_obj.dbxref_id" + " and f.type_id=865" // --;
																													// --gene
					+ " and upper(f.uniquename)=loci.uniquename "

					+ " UNION"

					+ " select f.organism_id,  c.name cv_name, cv_obj.cvterm_id, db_obj.accession cv_acc, cv_obj.name cv_term,  f.uniquename "
					+ " from  public.feature_cvterm fc, public.cvterm cv_subj public.dbxref db_subj, public.cv c, public.cvterm cv_obj , public.dbxref db_obj, lmansueto.cvterm_transclosure cvtp, public.feature f, (select distinct column_value uniquename from table(sys.odciVarchar2List("
					+ locinames + "))) loci " + " where fc.feature_id=f.feature_id and f.organism_id=" + orgId
					+ " and fc.cvterm_id=cv_subj.cvterm_id" + " and c.cv_id=cv_subj.cv_id and c.name in(" + cv + ")"
					+ " and cv_subj.dbxref_id=db_subj.dbxref_id" + " and cv_obj.dbxref_id=db_obj.dbxref_id"
					+ " and 'GO:' || db_subj.accession = cvtp.subject" + " and 'GO:' || db_obj.accession = cvtp.object "
					+ " and f.type_id=865" // -- ; --gene
					+ " and upper(f.uniquename)=loci.uniquename ";

			if (itLociName.hasNext())
				sql += " UNION ";
		}

		/*
		 * String sql = ""; sql +=
		 * "SELECT distinct  \"ORGANISM_ID\", \"CV_NAME\",\"CVTERM_ID\",\"CV_ACC\",\"CV_TERM\" , COUNT(DISTINCT \"UNIQUENAME\") LOCUS_COUNT FROM "
		 * +
		 * "(select f.organism_id,  c.name cv_name, cv.cvterm_id, db.accession cv_acc, cv.name cv_term, f.uniquename "
		 * + " from  " + AppContext.getDefaultSchema() + ".feature_cvterm fc, " +
		 * AppContext.getDefaultSchema() + ".cvterm cv , " +
		 * AppContext.getDefaultSchema() + ".dbxref db, " +
		 * AppContext.getDefaultSchema() + ".cv c, " + AppContext.getDefaultSchema() +
		 * ".feature f" +
		 * " where fc.feature_id=f.feature_id and  fc.cvterm_id=cv.cvterm_id  and f.organism_id="
		 * + orgId + " and c.cv_id=cv.cv_id and c.name in(" + cv + ")" +
		 * " and cv.dbxref_id=db.dbxref_id" + " and f.type_id=865" // -- ; -- gene +
		 * " and " + sqllocusnames
		 * 
		 * + " UNION" //-- locus from feature_cvterm->subj_id->cvtermpath->obj_id +
		 * " select f.organism_id,  c.name cv_name, cv_obj.cvterm_id, db_obj.accession cv_acc, cv_obj.name cv_term,  f.uniquename"
		 * + " from " + AppContext.getDefaultSchema() + ".feature_cvterm fc, " +
		 * AppContext.getDefaultSchema() + ".cvterm cv_subj , " +
		 * AppContext.getDefaultSchema() + ".dbxref db_subj, " +
		 * AppContext.getDefaultSchema() + ".cv c, " + AppContext.getDefaultSchema() +
		 * ".cvterm cv_obj , " + AppContext.getDefaultSchema() + ".dbxref db_obj, " +
		 * AppContext.getDefaultSchema() + ".cvtermpath cvtp, " +
		 * AppContext.getDefaultSchema() + ".feature f" +
		 * " where fc.feature_id=f.feature_id and f.organism_id=" + orgId +
		 * " and fc.cvterm_id=cv_subj.cvterm_id " +
		 * " and c.cv_id=cv_subj.cv_id and c.name in(" + cv + ")" +
		 * " and cv_subj.dbxref_id=db_subj.dbxref_id" +
		 * " and cv_subj.cvterm_id=cvtp.subject_id and cv_obj.cvterm_id=cvtp.object_id_"
		 * + " and cvtp.pathdistance>-1" + " and cv_obj.dbxref_id=db_obj.dbxref_id" +
		 * " and f.type_id=865" // --; --gene + " and " + sqllocusnames
		 * 
		 * + " UNION"
		 * 
		 * +
		 * " select f.organism_id,  c.name cv_name, cv_obj.cvterm_id, db_obj.accession cv_acc, cv_obj.name cv_term,  f.uniquename "
		 * + " from  " + AppContext.getDefaultSchema() + ".feature_cvterm fc, " +
		 * AppContext.getDefaultSchema() + ".cvterm cv_subj , " +
		 * AppContext.getDefaultSchema() + ".dbxref db_subj, " +
		 * AppContext.getDefaultSchema() + ".cv c, " + AppContext.getDefaultSchema() +
		 * ".cvterm cv_obj , " + AppContext.getDefaultSchema() +
		 * ".dbxref db_obj, lmansueto.cvterm_transclosure cvtp, " +
		 * AppContext.getDefaultSchema() + ".feature f" +
		 * " where fc.feature_id=f.feature_id and f.organism_id=" + orgId +
		 * " and fc.cvterm_id=cv_subj.cvterm_id" +
		 * " and c.cv_id=cv_subj.cv_id and c.name in(" + cv + ")" +
		 * " and cv_subj.dbxref_id=db_subj.dbxref_id" +
		 * " and cv_obj.dbxref_id=db_obj.dbxref_id" +
		 * " and 'GO:' || db_subj.accession = cvtp.subject" +
		 * " and 'GO:' || db_obj.accession = cvtp.object " + " and f.type_id=865" // --
		 * ; --gene + " and " + sqllocusnames + " )"
		 */

		/*
		 * + " where ("; Iterator<String> itLociName = AppContext.setStringSlicer(new
		 * HashSet(lociname), true, true).iterator(); while(itLociName.hasNext()) { sql
		 * += " upper(UNIQUENAME) in (" + itLociName.next() + ")";
		 * if(itLociName.hasNext()) sql += " or "; } //+
		 * " where UNIQUENAME in ('LOC_Os01g01010','LOC_Os01g01120','LOC_Os01g01060','LOC_Os01g01369','LOC_Os01g01689') "
		 * sql += ") "
		 */

		sql += ") group by \"ORGANISM_ID\", \"CV_NAME\",\"CVTERM_ID\",\"CV_ACC\",\"CV_TERM\" "
				+ " ORDER BY LOCUS_COUNT DESC";

		return executeSQL(sql);
	}

}
