package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.ds.chado.dao.QtlDAO;
import org.irri.iric.ds.chado.dao.VQtlDAO;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.model.VQtl;
import org.irri.iric.ds.utils.DbUtils;
import org.irri.iric.ds.utils.TextSearchOptions;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VQtl entities.
 * 
 */
@Repository("QtlDAO")
@Transactional
public class VQtlDAOImpl extends AbstractJpaDao<VQtl> implements VQtlDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VQtl.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VQtlDAOImpl
	 *
	 */
	public VQtlDAOImpl() {
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
	 * JPQL Query - findVQtlByTraitNameContaining
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByTraitNameContaining(String traitName) throws DataAccessException {

		return findVQtlByTraitNameContaining(traitName, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByTraitNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByTraitNameContaining(String traitName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVQtlByTraitNameContaining", startResult, maxRows, traitName);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByStart
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByStart(BigDecimal start) throws DataAccessException {

		return findVQtlByStart(start, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByStart
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByStart(BigDecimal start, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVQtlByStart", startResult, maxRows, start);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByQtlId
	 *
	 */
	@Transactional
	public VQtl findVQtlByQtlId(BigDecimal qtlId) throws DataAccessException {

		return findVQtlByQtlId(qtlId, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByQtlId
	 *
	 */

	@Transactional
	public VQtl findVQtlByQtlId(BigDecimal qtlId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVQtlByQtlId", startResult, maxRows, qtlId);
			return (VQtl) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllVQtls
	 *
	 */
	@Transactional
	public Set<VQtl> findAllVQtls() throws DataAccessException {

		return findAllVQtls(-1, -1);
	}

	/**
	 * JPQL Query - findAllVQtls
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findAllVQtls(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVQtls", startResult, maxRows);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByNotesContaining
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByNotesContaining(String notes) throws DataAccessException {

		return findVQtlByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVQtlByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByDbId
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByDbId(BigDecimal dbId) throws DataAccessException {

		return findVQtlByDbId(dbId, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByDbId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByDbId(BigDecimal dbId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVQtlByDbId", startResult, maxRows, dbId);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByNameContaining
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByNameContaining(String name) throws DataAccessException {

		return findVQtlByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVQtlByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByTraitName
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByTraitName(String traitName) throws DataAccessException {

		return findVQtlByTraitName(traitName, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByTraitName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByTraitName(String traitName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVQtlByTraitName", startResult, maxRows, traitName);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByNotes
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByNotes(String notes) throws DataAccessException {

		return findVQtlByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByNotes(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVQtlByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByEnd
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByEnd(BigDecimal end) throws DataAccessException {

		return findVQtlByEnd(end, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByEnd
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByEnd(BigDecimal end, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVQtlByEnd", startResult, maxRows, end);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByName
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByName(String name) throws DataAccessException {

		return findVQtlByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVQtlByName", startResult, maxRows, name);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByChromosome
	 *
	 */
	@Transactional
	public Set<VQtl> findVQtlByChromosome(Integer chromosome) throws DataAccessException {

		return findVQtlByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VQtl> findVQtlByChromosome(Integer chromosome, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVQtlByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<VQtl>(query.getResultList());
	}

	/**
	 * JPQL Query - findVQtlByPrimaryKey
	 *
	 */
	@Transactional
	public VQtl findVQtlByPrimaryKey(BigDecimal qtlId) throws DataAccessException {

		return findVQtlByPrimaryKey(qtlId, -1, -1);
	}

	/**
	 * JPQL Query - findVQtlByPrimaryKey
	 *
	 */

	@Transactional
	public VQtl findVQtlByPrimaryKey(BigDecimal qtlId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVQtlByPrimaryKey", startResult, maxRows, qtlId);
			return (VQtl) query.getSingleResult();
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
	public boolean canBeMerged(VQtl entity) {
		return true;
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
	 * end, String organism) { return null; }
	 */
	@Override
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel) {
		return null;
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus) {
		return null;
	}

	@Override
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel) {

		Set allset = new HashSet();
		Set sets[] = DbUtils.setSlicer(new HashSet(posset), 500);
		for (int iset = 0; iset < sets.length; iset++) {
			allset.addAll(_getLocusByContigPositions(contig, sets[iset], organism, genemodel, plusminus));
		}
		List listall = new ArrayList();
		listall.addAll(allset);
		return listall;
	}

	@Override
	public List<Locus> getLocusByDescription(TextSearchOptions desc, String organism) {
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

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	private List<Locus> executeSQLMerged(String sql) {
		// System.out.println("executing :" + sql);
		// log.info("executing :" + sql);
		// AppContext.debug("executing " + sql);
		List listResult = getSession().createSQLQuery(sql).addEntity(VQtl.class).list();
		// AppContext.debug("result " + listResult.size() + " qtls");
		return listResult;
	}

	private List _getLocusByContigPositions(String contig, Collection posset, String organism, String genemodel,
			Integer plusminus) {
		// TODO: Remove oracle options
		// if (AppContext.isOracle())
		// return _getLocusByContigPositionsOracle(contig, posset, organism, genemodel,
		// plusminus);
		// if (AppContext.isPostgres())
		return _getLocusByContigPositionsPostgres(contig, posset, organism, genemodel, plusminus);

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

	private List _getLocusByContigPositionsOracle(String contig, Collection posset, String organism, String genemodel,
			Integer plusminus) {

		/*
		 * List posset=new ArrayList(); Iterator<Position> itpos=possetobj.iterator();
		 * while(itpos.hasNext()) { posset.add( itpos.next().getPosition() ); }
		 */

		String locuspref = "";
		/*
		 * if(genemodel.equals( LocusCvTermDAO.QTL_ALL)) { //return
		 * _getLocusByContigPositions( contig, posset, organism, null); } else
		 * if(genemodel.equals( LocusCvTermDAO.QTL_QTARO)) { locuspref =
		 * " and q.db_id=1 "; } else if(genemodel.equals( LocusCvTermDAO.QTL_GRAMENE)) {
		 * locuspref = " and q.db_id=2 "; }
		 */

		// listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItems");

		posset = DbUtils.convertPos2Position(posset);

		StringBuffer sql = new StringBuffer();

		sql.append("select * from (");

		Set[] posseti = DbUtils.setSlicer(new TreeSet(posset), 900);

		for (int iset = 0; iset < posseti.length; iset++) {
			String qtlmodel = "";
			if (genemodel.equals(QtlDAO.QTL_QTARO))
				qtlmodel = " and q.db_id=1 ";
			else if (genemodel.equals(QtlDAO.QTL_GRAMENE))
				qtlmodel = " and q.db_id=2 ";

			String ppm = "";
			String mpm = "";
			if (plusminus != null && plusminus.intValue() != 0) {
				ppm = "+" + plusminus;
				mpm = "-" + plusminus;
			}

			sql.append("select pt.pos querypos, q.* from public.v_qtl q,");
			sql.append(" (select distinct column_value pos from table(sys.odcinumberlist("
					+ DbUtils.toCSV(posseti[iset]) + "))) pt");
			sql.append(" where q.chromosome=" + contig.replace("chr0", "").replace("chr", "").replace("Chr", ""));
			sql.append(" and  pt.pos between ( q.startpos " + mpm + ") and (q.endpos " + ppm + ") ");
			sql.append(qtlmodel);
			if (iset < posseti.length - 1)
				sql.append(" union ");
		}

		sql.append(") order by chromosome, startpos, endpos");

		// AppContext.debug("sql query: " + sql);
		List listqtl = new ArrayList();
		listqtl.addAll(new TreeSet(executeSQLMerged(sql.toString())));
		return listqtl;

	}

	private List _getLocusByContigPositionsPostgres(String contig, Collection posset, String organism, String genemodel,
			Integer plusminus) {

		String locuspref = "";

		posset = DbUtils.convertPos2Position(posset);

		StringBuffer sql = new StringBuffer();

		sql.append("select * from (");

		Set[] posseti = DbUtils.setSlicer(new TreeSet(posset), 900);

		for (int iset = 0; iset < posseti.length; iset++) {
			String qtlmodel = "";
			if (genemodel.equals(QtlDAO.QTL_QTARO))
				qtlmodel = " and q.db_id=1 ";
			else if (genemodel.equals(QtlDAO.QTL_GRAMENE))
				qtlmodel = " and q.db_id=2 ";

			String ppm = "";
			String mpm = "";
			if (plusminus != null && plusminus.intValue() != 0) {
				ppm = "+" + plusminus;
				mpm = "-" + plusminus;
			}

			sql.append("select pt.pos querypos, q.* from public.v_qtl q,");
			// sql.append( " (select distinct column_value pos from
			// table(sys.odcinumberlist(" + AppContext.toCSV(posseti[iset])+ "))) pt");

			// sql.append( " (select distinct column_value pos from
			// table(sys.odcinumberlist(" + AppContext.toCSV(posseti[iset])+ "))) pt");
			sql.append(" (select unnest(ARRAY[" + DbUtils.toCSV(posseti[iset]) + "]) pos)  pt");

			sql.append(" where q.chromosome=" + contig.replace("chr0", "").replace("chr", "").replace("Chr", ""));
			sql.append(" and  pt.pos between ( q.startpos " + mpm + ") and (q.endpos " + ppm + ") ");
			sql.append(qtlmodel);
			if (iset < posseti.length - 1)
				sql.append(" union ");
		}

		sql.append(") foo order by chromosome, startpos, endpos");

		// AppContext.debug("sql query: " + sql);
		List listqtl = new ArrayList();
		listqtl.addAll(new TreeSet(executeSQLMerged(sql.toString())));
		return listqtl;

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
