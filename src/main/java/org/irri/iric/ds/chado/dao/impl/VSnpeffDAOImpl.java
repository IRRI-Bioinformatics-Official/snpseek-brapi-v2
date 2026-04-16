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
import org.irri.iric.ds.chado.dao.VSnpeffDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImpl;
import org.irri.iric.ds.chado.domain.model.VSnpeff;
import org.irri.iric.ds.utils.DbUtils;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpeff entities.
 * 
 */
// @Repository("VSnpeffDAO")
@Repository("SnpsEffectDAO")
@Transactional
public class VSnpeffDAOImpl extends AbstractJpaDao<VSnpeff> implements VSnpeffDAO {

	private Logger log = Logger.getLogger(VSnpeffDAOImpl.class.getName());
	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VSnpeff.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpeffDAOImpl
	 *
	 */
	public VSnpeffDAOImpl() {
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
	 * JPQL Query - findVSnpeffByPosition
	 *
	 */
	@Transactional
	public Set<VSnpeff> findVSnpeffByPosition(java.math.BigDecimal position) throws DataAccessException {

		return findVSnpeffByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpeffByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpeff> findVSnpeffByPosition(java.math.BigDecimal position, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpeffByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpeff>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpeffByAnnotationContaining
	 *
	 */
	@Transactional
	public Set<VSnpeff> findVSnpeffByAnnotationContaining(String annotation) throws DataAccessException {

		return findVSnpeffByAnnotationContaining(annotation, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpeffByAnnotationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpeff> findVSnpeffByAnnotationContaining(String annotation, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpeffByAnnotationContaining", startResult, maxRows, annotation);
		return new LinkedHashSet<VSnpeff>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpeffBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpeff findVSnpeffBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException {

		return findVSnpeffBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpeffBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpeff findVSnpeffBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpeffBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (VSnpeff) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpeffByChromosome
	 *
	 */
	@Transactional
	public Set<VSnpeff> findVSnpeffByChromosome(Integer chromosome) throws DataAccessException {

		return findVSnpeffByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpeffByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpeff> findVSnpeffByChromosome(Integer chromosome, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpeffByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<VSnpeff>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpeffByAnnotation
	 *
	 */
	@Transactional
	public Set<VSnpeff> findVSnpeffByAnnotation(String annotation) throws DataAccessException {

		return findVSnpeffByAnnotation(annotation, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpeffByAnnotation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpeff> findVSnpeffByAnnotation(String annotation, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVSnpeffByAnnotation", startResult, maxRows, annotation);
		return new LinkedHashSet<VSnpeff>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpeffs
	 *
	 */
	@Transactional
	public Set<VSnpeff> findAllVSnpeffs() throws DataAccessException {

		return findAllVSnpeffs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpeffs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpeff> findAllVSnpeffs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpeffs", startResult, maxRows);
		return new LinkedHashSet<VSnpeff>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpeffByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpeff findVSnpeffByPrimaryKey(BigDecimal snpFeatureId) throws DataAccessException {

		return findVSnpeffByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpeffByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpeff findVSnpeffByPrimaryKey(BigDecimal snpFeatureId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpeffByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (VSnpeff) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpeff entity) {
		return true;
	}

	private List executeSQL(String sql) {
		// TODO removed oracle option
		// if (DbUtils.isLocalhost())
		// log.info("executing :" + sql);
		// // log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(VSnpeff.class).list();
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public Set<SnpsEffect> getSNPsIn(String chr, Collection posset, Set ds) throws DataAccessException {

		// TODO removed oracle option
		// if (DbUtils.isOracle())
		// return getSNPsInOracle(chr, posset);
		// else if (DbUtils.isPostgres())
		return getSNPsInPostgres(chr, posset);

	}

	private Set<SnpsEffect> getSNPsInOracle(String chr, Collection posset) {

		if (chr.toLowerCase().equals("any")) {

			long poscount = 0;
			List listPresent = new ArrayList();
			// log.info("checking " + posset.size() + " snp positions");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			String sql = "select * from ( "; // select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL ,
												// ALLELE_INDEX from " + DbUtils.getDefaultSchema() +
												// ".V_SNP_REFPOSINDEX srp WHERE 1=1 and (";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection setPos = (Collection) mapChr2Pos.get(contigstr);
				poscount += setPos.size();

				setPos = DbUtils.convertPos2Position(setPos);

				Set slicedset[] = DbUtils.setSlicer(new TreeSet(setPos), 900);
				for (int iset = 0; iset < slicedset.length; iset++) {
					sql += " select * from public.v_snpeff where chromosome=" + contig
							+ " and exists (select column_value from table(sys.odcinumberlist("
							+ slicedset[iset].toString().replace("[", "").replace("]", "")
							+ " )) t where t.column_value=position) ";
					if (iset < slicedset.length - 1)
						sql += " union ";
				}
				if (itContig.hasNext())
					sql += " union ";
			}
			;

			sql += ") order by CHROMOSOME, POSITION";

			/*
			 * with names (fname,lname) as ( values ('John','Smith'), ('Mary','Jones') )
			 * select city from user inner join names on fname=firstName and lname=lastName;
			 * 
			 */
			// log.info("querying V_SNPEFF with " + poscount + " positions");
			return new LinkedHashSet(executeSQL(sql));
		} else if (chr.toLowerCase().equals("loci")) {

			long poscount = 0;
			// log.info("checking " + posset.size() + " loci");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);

			Set allresults = new LinkedHashSet();

			String sql = "select * from (";
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

			sql += ") order by CHROMOSOME, POSITION";

			// log.info("querying V_SNPEFF with " + poscount + " loci");

			return new LinkedHashSet(executeSQL(sql));
		} else {

			String sql = "select * from ( ";
			String contig = chr.toUpperCase().replace("CHR0", "").replace("CHR", "");

			posset = DbUtils.convertPos2Position(posset);
			Set slicedset[] = DbUtils.setSlicer(new TreeSet(posset), 900);
			for (int iset = 0; iset < slicedset.length; iset++) {
				sql += " select * from public.v_snpeff where chromosome=" + contig
						+ " and exists (select column_value from table(sys.odcinumberlist("
						+ slicedset[iset].toString().replace("[", "").replace("]", "")
						+ " )) t where t.column_value=position) ";
				if (iset < slicedset.length - 1)
					sql += " union ";
			}
			sql += ") order by CHROMOSOME, POSITION";
			return new LinkedHashSet(executeSQL(sql));
		}
	}

	private Set<SnpsEffect> getSNPsInPostgres(String chr, Collection posset) {

		if (chr.toLowerCase().equals("any")) {

			long poscount = 0;
			List listPresent = new ArrayList();
			log.info("checking " + posset.size() + " snp positions");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			String sql = "select * from ( ";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				String contig = contigstr.toUpperCase().replace("CHR0", "").replace("CHR", "");
				Collection setPos = (Collection) mapChr2Pos.get(contigstr);
				poscount += setPos.size();

				setPos = DbUtils.convertPos2Position(setPos);

				Set slicedset[] = DbUtils.setSlicer(new TreeSet(setPos), 900);
				for (int iset = 0; iset < slicedset.length; iset++) {
					sql += " select * from public.v_snpeff where chromosome=" + contig
							+ " and exists ( select t.column_value from (select unnest(ARRAY["
							+ slicedset[iset].toString().replace("[", "").replace("]", "")
							+ "])column_value)  t where t.column_value=position)  ";
					if (iset < slicedset.length - 1)
						sql += " union ";
				}
				if (itContig.hasNext())
					sql += " union ";
			}
			;

			sql += ") foo2 order by CHROMOSOME, POSITION";

			log.info("querying  V_SNPEFF with " + poscount + " positions");
			return new LinkedHashSet(executeSQL(sql));
		} else if (chr.toLowerCase().equals("loci")) {

			long poscount = 0;
			log.info("checking " + posset.size() + " loci");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);

			Set allresults = new LinkedHashSet();

			String sql = "select * from (";
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

			sql += ") foo order by CHROMOSOME, POSITION";

			log.info("querying  V_SNPEFF with " + poscount + " loci");

			return new LinkedHashSet(executeSQL(sql));
		} else {

			String sql = "select * from ( ";
			String contig = chr.toUpperCase().replace("CHR0", "").replace("CHR", "");

			posset = DbUtils.convertPos2Position(posset);
			Set slicedset[] = DbUtils.setSlicer(new TreeSet(posset), 900);
			for (int iset = 0; iset < slicedset.length; iset++) {
				sql += " select * from public.v_snpeff where chromosome=" + contig
						+ " and exists (  select t.column_value from (select unnest(ARRAY["
						+ slicedset[iset].toString().replace("[", "").replace("]", "")
						+ "])column_value) t where t.column_value=position) ";
				if (iset < slicedset.length - 1)
					sql += " union ";
			}
			sql += ") foo order by CHROMOSOME, POSITION";
			return new LinkedHashSet(executeSQL(sql));
		}
	}

	@Override
	public Set<SnpsEffect> getSNPsBetween(String chr, Integer start, Integer end, Set ds) throws DataAccessException {

		// Query query = createNamedQuery("findVSnpeffByPositionBetween", -1, -1,
		// Integer.valueOf(DbUtils.guessChrFromString(chr)),
		// BigDecimal.valueOf(start), BigDecimal.valueOf(end));
		Query query = createNamedQuery("findVSnpeffByPositionBetweenSnpsets", -1, -1,
				Integer.valueOf(DbUtils.guessChrFromString(chr)), BigDecimal.valueOf(start), BigDecimal.valueOf(end),
				ds);
		return new LinkedHashSet(query.getResultList());
	}

	@Override
	public Set<SnpsEffect> getSNPsByFeatureidIn(Collection featureid) throws DataAccessException {
		// if (DbUtils.isOracle())
		// return getSNPsByFeatureidInOracle(featureid);
		// if (DbUtils.isPostgres())
		return getSNPsByFeatureidInPostgres(featureid);
		// return null;
	}

	private Set<SnpsEffect> getSNPsByFeatureidInOracle(Collection featureid) {

		String sql = "select * from (";

		Set slicedset[] = DbUtils.setSlicer(new TreeSet(featureid), 900);
		for (int iset = 0; iset < slicedset.length; iset++) {
			sql += " select * from public.v_snpeff where exists (select column_value from table(sys.odcinumberlist("
					+ slicedset[iset].toString().replace("[", "").replace("]", "")
					+ " )) t where t.column_value=snp_feature_id) ";
			if (iset < slicedset.length - 1)
				sql += " union ";
		}
		sql += ") order by chromosome, position";

		return new LinkedHashSet(executeSQL(sql));
	}

	private Set<SnpsEffect> getSNPsByFeatureidInPostgres(Collection featureid) {

		String sql = "select * from (";

		Set slicedset[] = DbUtils.setSlicer(new TreeSet(featureid), 900);
		for (int iset = 0; iset < slicedset.length; iset++) {
			sql += " select * from public.v_snpeff where exists (   select t.column_value from (select unnest(ARRAY["
					+ slicedset[iset].toString().replace("[", "").replace("]", "")
					+ "])column_value) t where t.column_value=snp_feature_id) ";
			if (iset < slicedset.length - 1)
				sql += " union ";
		}
		sql += ") foo order by chromosome, position";

		return new LinkedHashSet(executeSQL(sql));
	}

	@Override
	public Set<SnpsEffect> getSNPsIn(String chr, Collection posset) throws DataAccessException {

		// if (DbUtils.isOracle())
		// return getSNPsInOracle(chr, posset);
		// else if (DbUtils.isPostgres())
		return getSNPsInPostgres(chr, posset);
		// return null;

	}
	
	@Override
	public Collection getAnnotChrPos(String chr, List<SnpsAllvarsPos> listPos) {
		
		String contig = chr.toUpperCase().replace("CHR0", "").replace("CHR", "");
		
		List pos =new ArrayList<BigDecimal>();
		Iterator<SnpsAllvarsPos> itPos = listPos.iterator();
		
		while (itPos.hasNext()) {
			SnpsAllvarsPos item = itPos.next();
			pos.add(item.getPosition().toString());
			
		}
		
		String allPosition = String.join(",", pos);
		
		String sql = "select * from public.v_snpeff2 where chromosome="+contig+" and position in ("+ allPosition+");";
		
		return new LinkedHashSet(executeSQL(sql));
	}
}
