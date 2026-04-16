package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.ds.chado.dao.VAllstockBasicpropDAO;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.model.VAllstockBasicprop;
import org.irri.iric.ds.utils.DbConstant;
import org.irri.iric.ds.utils.DbUtils;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VAllstockBasicprop entities.
 * 
 */
// @Repository("VAllstockBasicpropDAO")
// @Repository("VarietyBasicprop2DAO")
@Repository("VarietyDAO")
@Transactional
public class VAllstockBasicpropDAOImpl extends AbstractJpaDao<VAllstockBasicprop> implements VAllstockBasicpropDAO {

	private Logger log = Logger.getLogger(VAllstockBasicpropDAOImpl.class.getName());

	@Override
	public Set<Variety> getIRGCStocks() {

		Query query = createNamedQuery("findVAllstockBasicpropByGsaccessionLike", -1, -1, "IRGC %");
		List list = query.getResultList();
		Set s = new HashSet();
		for (Object o : list) {
			Variety v = (Variety) o;
			if (v.getAccession() != null && v.getAccession().startsWith("IRGC"))
				s.add(v);
		}
		return s;
	}

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VAllstockBasicprop.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VAllstockBasicpropDAOImpl
	 *
	 */
	public VAllstockBasicpropDAOImpl() {
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
	 * Get the entity manager that manages persistence unit
	 *
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findAllVAllstockBasicprops
	 *
	 */
	@Transactional
	public Set<VAllstockBasicprop> findAllVAllstockBasicprops() throws DataAccessException {

		return findAllVAllstockBasicprops(-1, -1);
	}

	/**
	 * JPQL Query - findAllVAllstockBasicprops
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findAllVAllstockBasicprops(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVAllstockBasicprops", startResult, maxRows);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByIrisUniqueIdContaining
	 *
	 */
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByIrisUniqueIdContaining(String irisUniqueId)
			throws DataAccessException {

		return findVAllstockBasicpropByIrisUniqueIdContaining(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByIrisUniqueIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByIrisUniqueIdContaining(String irisUniqueId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByIrisUniqueIdContaining", startResult, maxRows,
				irisUniqueId);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByOriCountryContaining
	 *
	 */
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByOriCountryContaining(String oriCountry)
			throws DataAccessException {

		return findVAllstockBasicpropByOriCountryContaining(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByOriCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByOriCountryContaining(String oriCountry, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByOriCountryContaining", startResult, maxRows,
				oriCountry);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByBoxCode
	 *
	 */
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByBoxCode(String boxCode) throws DataAccessException {

		return findVAllstockBasicpropByBoxCode(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByBoxCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByBoxCode(String boxCode, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByBoxCode", startResult, maxRows, boxCode);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByNameContaining
	 *
	 */
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByNameContaining(String name) throws DataAccessException {

		return findVAllstockBasicpropByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByBoxCodeContaining
	 *
	 */
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByBoxCodeContaining(String boxCode)
			throws DataAccessException {

		return findVAllstockBasicpropByBoxCodeContaining(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByBoxCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByBoxCodeContaining(String boxCode, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByBoxCodeContaining", startResult, maxRows, boxCode);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByPrimaryKey
	 *
	 */
	@Transactional
	public VAllstockBasicprop findVAllstockBasicpropByPrimaryKey(BigDecimal allStockId) throws DataAccessException {

		return findVAllstockBasicpropByPrimaryKey(allStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByPrimaryKey
	 *
	 */

	@Transactional
	public VAllstockBasicprop findVAllstockBasicpropByPrimaryKey(BigDecimal allStockId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVAllstockBasicpropByPrimaryKey", startResult, maxRows, allStockId);
			return (org.irri.iric.ds.chado.domain.model.VAllstockBasicprop) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public Collection findVarietyByIds(Set setQueryVarobj) {

		try {
			Query query = createNamedQuery("findVAllstockBasicpropByPrimaryKeys", -1, -1, setQueryVarobj);
			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByIrisUniqueId
	 *
	 */
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByIrisUniqueId(String irisUniqueId)
			throws DataAccessException {

		return findVAllstockBasicpropByIrisUniqueId(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByIrisUniqueId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByIrisUniqueId(String irisUniqueId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByIrisUniqueId", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByOriCountry
	 *
	 */
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByOriCountry(String oriCountry) throws DataAccessException {

		return findVAllstockBasicpropByOriCountry(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByOriCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByOriCountry(String oriCountry, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByOriCountry", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByallStockId
	 *
	 */
	@Transactional
	public VAllstockBasicprop findVAllstockBasicpropByallStockId(BigDecimal allStockId) throws DataAccessException {

		return findVAllstockBasicpropByallStockId(allStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByallStockId
	 *
	 */

	@Transactional
	public VAllstockBasicprop findVAllstockBasicpropByallStockId(BigDecimal allStockId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVAllstockBasicpropByallStockId", startResult, maxRows, allStockId);
			return (org.irri.iric.ds.chado.domain.model.VAllstockBasicprop) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByName
	 *
	 */
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByName(String name) throws DataAccessException {

		return findVAllstockBasicpropByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVAllstockBasicpropByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByName", startResult, maxRows, name);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VAllstockBasicprop entity) {
		return true;
	}

	@Override
	public Set findAllVariety() {

		return this.findAllVAllstockBasicprops();
	}

	@Override
	public Set findAllVarietyByCountry(String country) {

		return this.findVAllstockBasicpropByOriCountry(country);
	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation) {

		return this.findVAllstockBasicpropBySubpopulation(subpopulation);
	}

	@Override
	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation, Set dataset) {

		/*
		 * TODO: dataset is Set not a String anymore
		 * 
		 * There is some dataset that return null
		 * dataset.equals(VarietyFacade.DATASET_SNPINDELV2_IUPAC in allStockbasicpropdao
		 * 
		 */
		// if (!dataset.equals(VarietyFacade.DATASET_SNPINDELV2_IUPAC))
		// throw new RuntimeException(
		// "!dataset.equals(VarietyFacade.DATASET_SNPINDELV2_IUPAC in
		// allStockbasicpropdao");

		return this.findVAllstockBasicpropByOriCountrySubpopulation(country, subpopulation);

	}

	@Override
	public Variety findVarietyByIrisId(String name) {

		// return this.findVallStockBasicpropByIrisUniqueId(name);

		Set setVarieties = this.findVAllstockBasicpropByIrisUniqueId(name);
		if (setVarieties.size() > 1)
			throw new RuntimeException("Multiple varieties with IRIS ID " + name);
		else if (setVarieties.isEmpty())
			return null;
		return (Variety) setVarieties.iterator().next();

	}

	@Override
	public Variety findVarietyByName(String name) {

		Set setNames = this.findVAllstockBasicpropByName(name);
		if (setNames.size() > 1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if (setNames.isEmpty())
			return null;
		return (Variety) setNames.iterator().next();
	}

	@Override
	public Variety findVarietyById(BigDecimal id) {

		return this.findVAllstockBasicpropByPrimaryKey(id);
	}

	@Override
	public Variety findVarietyByNameLike(String name) {

		Set setNames = this.findVAllstockBasicpropByNameContaining(name);
		if (setNames.size() > 1)
			throw new RuntimeException("Multiple varieties with name like " + name);
		else if (setNames.isEmpty())
			return null;
		return (Variety) setNames.iterator().next();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropBySubpopulation(String subpopulation)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropBySubpopulation", -1, -1, subpopulation);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllstockBasicprop> findVAllstockBasicpropByOriCountrySubpopulation(String country, String subpopulation)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByOriCountrySubpopulation", -1, -1, country,
				subpopulation);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	@Override
	public Variety findVarietyByAccession(String name) {

		Set setNames = findVarietiesByAccession(name);
		if (setNames.size() > 1)
			throw new RuntimeException("Multiple varieties with accession " + name);
		else if (setNames.isEmpty())
			return null;
		return (Variety) setNames.iterator().next();

	}

	@Override
	public List<Variety> findVarietyByNamesLike(Collection names) {
		// TODO: Note Removed oracle option
		// if (AppContext.isOracle())
		// return findVarietyByNamesLikeOracle(names);
		// else if (AppContext.isPostgres())
		return findVarietyByNamesLikePostgres(names);

	}

	private List<Variety> findVarietyByNamesLikeOracle(Collection names) {

		List listVars = new ArrayList();
		Set setnames[] = DbUtils.setSlicer(new LinkedHashSet(names), 900);
		for (int i = 0; i < setnames.length; i++) {
			StringBuffer buffnames = new StringBuffer();
			Iterator<String> itNames = setnames[i].iterator();
			while (itNames.hasNext()) {
				String name = itNames.next();
				try {
					Integer.valueOf(name);
					buffnames.append("'").append(name).append("'");
				} catch (Exception ex) {
					buffnames.append("'%").append(name.replace("'", "''")).append("%'");
				}
				if (itNames.hasNext())
					buffnames.append(",");
			}
			String sql = "select v.* from  public.V_allStock_BASICPROP v,  table (sys.ODCIVarchar2List (" + buffnames
					+ ")) n where v.name like n.column_value";
			listVars.addAll(executeSQL(sql));
		}
		return listVars;
	}

	private List<Variety> findVarietyByNamesLikePostgres(Collection names) {

		List listVars = new ArrayList();
		Set setnames[] = DbUtils.setSlicer(new LinkedHashSet(names), 900);
		for (int i = 0; i < setnames.length; i++) {
			StringBuffer buffnames = new StringBuffer();
			Iterator<String> itNames = setnames[i].iterator();
			while (itNames.hasNext()) {
				String name = itNames.next().toUpperCase();
				try {
					Integer.valueOf(name);
					buffnames.append("'").append(name).append("'");
				} catch (Exception ex) {
					buffnames.append("'%").append(name.replace("'", "''")).append("%'");
				}
				if (itNames.hasNext())
					buffnames.append(",");
			}
			String sql = "select v.* from  public.V_allStock_BASICPROP v,   (select t.column_value from ( select unnest(ARRAY["
					+ buffnames + "]) column_value) t) n where upper(v.name) like n.column_value";
			listVars.addAll(executeSQL(sql));
		}
		return listVars;
	}

	@Override
	public List<Variety> findVarietyByNames(Collection names) {

		// TODO note Removed oracle Option
		// if (AppContext.isOracle())
		// return findVarietyByNamesOracle(names);
		// else if (AppContext.isPostgres())
		return findVarietyByNamesPostgres(names);

	}

	private List<Variety> findVarietyByNamesOracle(Collection names) {

		List listVars = new ArrayList();
		Set setnames[] = DbUtils.setSlicer(new LinkedHashSet(names), 900);
		for (int i = 0; i < setnames.length; i++) {
			StringBuffer buffnames = new StringBuffer();
			Iterator<String> itNames = setnames[i].iterator();
			while (itNames.hasNext()) {
				String name = itNames.next();
				if (name.startsWith("IRGC"))
					name = name.substring(4).trim();
				buffnames.append("'").append(name.replace("'", "''")).append("'");
				if (itNames.hasNext())
					buffnames.append(",");
			}
			// select v.* from public.V_allStock_BASICPROP v, (select t.column_value from (
			// select unnest(ARRAY['NIPPONBARE','IR 64']) column_value) t) n where
			// n.column_value=v.name or n.column_value=v.gs_accession;

			String sql = "select v.* from   public.V_allStock_BASICPROP v,  table (sys.ODCIVarchar2List (" + buffnames
					+ ")) n where n.column_value=v.name or n.column_value=v.gs_accession";
			// String sql = "select v.* from " + AppContext.getDefaultSchema() +
			// ".V_allStock_BASICPROP v, (select t.column_value from ( select unnest(ARRAY["
			// + buffnames + "]) column_value) t) n where n.column_value=v.name or
			// n.column_value=v.gs_accession";
			listVars.addAll(executeSQL(sql));
		}

		return listVars;

		/*
		 * select emp.* from emp, table (sys.ODCIVarchar2List
		 * ('SCOTT','ALLEN','KING','KINGDOM')) where column_value=ename
		 */
		// return null;
	}

	private List<Variety> findVarietyByNamesPostgres(Collection names) {

		List listVars = new ArrayList();
		Set setnames[] = DbUtils.setSlicer(new LinkedHashSet(names), 900);
		for (int i = 0; i < setnames.length; i++) {
			StringBuffer buffnames = new StringBuffer();
			Iterator<String> itNames = setnames[i].iterator();
			while (itNames.hasNext()) {
				String name = itNames.next().toUpperCase();
				if (name.startsWith("IRGC"))
					name = name.substring(4).trim();
				buffnames.append("'").append(name.replace("'", "''")).append("'");
				if (itNames.hasNext())
					buffnames.append(",");
			}
			String sql = "select v.* from   public.V_allStock_BASICPROP v,  (select t.column_value from ( select unnest(ARRAY["
					+ buffnames
					+ "]) column_value) t) n where n.column_value=upper(v.name) or n.column_value=upper(v.gs_accession)";
			listVars.addAll(executeSQL(sql));
		}

		return listVars;

	}

	@Override
	public List<Variety> findVarietyByIrisIds(Collection names) {

		// TODO: Note removed Oracle
		// if (AppContext.isOracle())
		// return findVarietyByIrisIdsOracle(names);
		// else if (AppContext.isPostgres())
		return findVarietyByIrisIdsPostgres(names);

	}

	private List<Variety> findVarietyByIrisIdsOracle(Collection names) {

		List listVars = new ArrayList();
		Set setnames[] = DbUtils.setSlicer(new LinkedHashSet(names), 900);
		for (int i = 0; i < setnames.length; i++) {
			StringBuffer buffnames = new StringBuffer();
			Iterator<String> itNames = setnames[i].iterator();
			while (itNames.hasNext()) {
				String name = itNames.next();
				name = "IRIS " + name.toUpperCase().replace("IRIS", "").replace("_", "").trim();
				buffnames.append("'").append(name.replace("'", "''")).append("'");
				if (itNames.hasNext())
					buffnames.append(",");
			}
			String sql = "select v.* from  public.V_allStock_BASICPROP v,  table (sys.ODCIVarchar2List (" + buffnames
					+ ")) n where n.column_value=v.IRIS_UNIQUE_ID";
			listVars.addAll(executeSQL(sql));
		}

		return listVars;

	}

	private List<Variety> findVarietyByIrisIdsPostgres(Collection names) {

		List listVars = new ArrayList();
		Set setnames[] = DbUtils.setSlicer(new LinkedHashSet(names), 900);
		for (int i = 0; i < setnames.length; i++) {
			StringBuffer buffnames = new StringBuffer();
			Iterator<String> itNames = setnames[i].iterator();
			while (itNames.hasNext()) {
				String name = itNames.next().toUpperCase();
				name = "IRIS " + name.toUpperCase().replace("IRIS", "").replace("_", "").trim();
				buffnames.append("'").append(name.replace("'", "''")).append("'");
				if (itNames.hasNext())
					buffnames.append(",");
			}
			String sql = "select v.* from  public.V_allStock_BASICPROP v,  (select t.column_value from ( select unnest(ARRAY["
					+ buffnames + "]) column_value) t) n where n.column_value=upper(v.IRIS_UNIQUE_ID)";
			listVars.addAll(executeSQL(sql));
		}

		return listVars;

	}

	private List executeSQL(String sql) {
		// TODO: why is there a session?
		return getSession().createSQLQuery(sql).addEntity(VAllstockBasicprop.class).list();
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public Set findVarietiesByIrisId(String irisid) {

		return findVAllstockBasicpropByIrisUniqueId(irisid);
	}

	@Override
	public Set findVarietiesByAccession(String accession) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByGsaccession", -1, -1,
				accession.toUpperCase().replace("IRGC", "").trim());
		List list = query.getResultList();
		if (list.size() == 0) {
			query = createNamedQuery("findVAllstockBasicpropByBoxCode", -1, -1, accession.toUpperCase().trim());
			list = query.getResultList();
		}
		return new LinkedHashSet<VAllstockBasicprop>(list);

	}

	public Set findVAllstockBasicpropByDataset(String dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByDataset", -1, -1, dataset);
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());

	}

	@Override
	public Set findVarietiesByName(String names) {

		return findVAllstockBasicpropByName(names);
	}

	@Override
	public Set findVarietiesByNameAccession(String varname, String accession) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByNameGsaccession", -1, -1, varname,
				accession.toUpperCase().replace("IRGC", "").trim());
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());

	}

	@Override
	public Set findAllVarietyByCountry(String country, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByOriCountryDataset", -1, -1, country,
				DbUtils.toUpperCase(dataset));
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropBySubpopulationDataset", -1, -1, subpopulation,
				DbUtils.toUpperCase(dataset));
		return new LinkedHashSet<VAllstockBasicprop>(query.getResultList());
	}

	/*
	 * @Override public Set findAllVarietyByCountryAndSubpopulation(String country,
	 * String subpopulation, Set dataset) { return null; }
	 */

	/*
	 * @Override public Variety findVarietyByName(String name, Set dataset) throws
	 * DataAccessException { Query query =
	 * createNamedQuery("findVAllstockBasicpropByNameDataset", -1,-1,name,
	 * AppContext.toUpperCase(dataset)); return
	 * (VAllstockBasicprop)query.getSingleResult(); }
	 */
	@Override
	public List<Variety> findVarietyByName(String name, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByNameDataset", -1, -1, name,
				DbUtils.toUpperCase(dataset));
		return query.getResultList();
	}

	@Override
	public Variety findVarietyByNameLike(String name, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByNameContainingDataset", -1, -1, name,
				DbUtils.toUpperCase(dataset));
		return (VAllstockBasicprop) query.getSingleResult();
	}

	@Override
	public Variety findVarietyByIrisId(String name, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByIrisUniqueIdDataset", -1, -1, name,
				DbUtils.toUpperCase(dataset));
		return (VAllstockBasicprop) query.getSingleResult();
	}

	@Override
	public Variety findVarietyByAccession(String name, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByGsaccessionDataset", -1, -1, name,
				DbUtils.toUpperCase(dataset));
		return (VAllstockBasicprop) query.getSingleResult();
	}

	@Override
	public List<Variety> findVarietyByNamesLike(Collection names, Set dataset) throws DataAccessException {
		Query query = createNamedQuery("findVAllstockBasicpropByNamesLikeDataset", -1, -1, DbUtils.toUpperCase(names),
				DbUtils.toUpperCase(dataset));
		return query.getResultList();
	}

	@Override
	public List<Variety> findVarietyByNames(Collection names, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByNamesDataset", -1, -1, DbUtils.toUpperCase(names),
				DbUtils.toUpperCase(dataset));
		return query.getResultList();
	}

	@Override
	public List<Variety> findVarietyByIrisIds(Collection names, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByIrisUniqueIdsDataset", -1, -1,
				DbUtils.toUpperCase(names), DbUtils.toUpperCase(dataset));
		return query.getResultList();
	}

	@Override
	public Set<Variety> findVarietiesByIrisId(String irisid, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByIrisUniqueIdDataset", -1, -1, irisid,
				DbUtils.toUpperCase(dataset));
		return new LinkedHashSet<Variety>(query.getResultList());
	}

	/*
	 * @Override public Variety findVarietyByAccession(String accession, Set
	 * dataset) throws DataAccessException { Query query =
	 * createNamedQuery("findVAllstockBasicpropByGsaccessionDataset",
	 * -1,-1,accession, AppContext.toUpperCase(dataset)); return new
	 * LinkedHashSet<Variety>(query.getResultList()); }
	 */
	@Override
	public Set<Variety> findVarietiesByName(String names, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByNameDataset", -1, -1, names,
				DbUtils.toUpperCase(dataset));
		return new LinkedHashSet<Variety>(query.getResultList());
	}

	@Override
	public Set<Variety> findVarietiesByNameAccession(String varname, String accession, Set dataset)
			throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByNameGsaccessionDataset", -1, -1, varname, accession,
				DbUtils.toUpperCase(dataset));
		return new LinkedHashSet<Variety>(query.getResultList());
	}

	@Override
	public Collection<Variety> findVarietyByIds(Set setQueryVarobj, Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findVAllstockBasicpropByPrimaryKeysDataset", -1, -1,
				DbUtils.toUpperCase(setQueryVarobj), DbUtils.toUpperCase(dataset));
		return new LinkedHashSet<Variety>(query.getResultList());
	}

	@Override
	public Collection<Variety> findVarietiesByDataset(String dataset) throws DataAccessException {

		log.info("findVarietiesByDataset(String dataset) :" + dataset);

		Query query;
		if (dataset.equals(DbConstant.DATASET_SNP_ALL))
			query = createNamedQuery("findAllVAllstockBasicprops", -1, -1);
		else
			query = createNamedQuery("findVAllstockBasicpropByDataset", -1, -1, dataset);

		List l = query.getResultList();
		log.info(l.size() + " samples: " + query.getClass());
		
		return l;

		// String sql="select * from v_allstock_basicprop where upper(dataset)='" +
		// dataset.toUpperCase() +"'";
		// return AppContext.executeSQL(entityManager, VAllstockBasicprop.class, sql);

	}

	@Override
	public Collection<Variety> findVarietiesByDatasets(Set dataset) throws DataAccessException {

		Query query = createNamedQuery("findAllVAllstockBasicpropsDatasets", -1, -1, DbUtils.toUpperCase(dataset));
		log.info("findVarietiesByDataset(Set dataset) :" + dataset + "  " + query.getClass());
		return new LinkedHashSet<Variety>(query.getResultList());
	}

	@Override
	public Set findAllVarietyByExample(Variety germplasm, Set dataset) {
		return null;
	}

	@Override
	public Set findAllVarietyByCountryAndSubpopulationDatasets(String country, String subpopulation, Set dataset) {

		Query query = createNamedQuery("findVAllstockBasicpropByOriCountrySubpopulationDataset", -1, -1, country, subpopulation,
				DbUtils.toUpperCase(dataset));
		return new LinkedHashSet<Variety>(query.getResultList());
	}

	@Override
	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation) {

		Query query = createNamedQuery("findVAllstockBasicpropByOriCountrySubpopulation", -1, -1, country, subpopulation);
		return new LinkedHashSet<Variety>(query.getResultList());
	}

	@Override
	public Variety findVarietyById(BigDecimal id, Set dataset) {
		Query query = createNamedQuery("findVAllstockBasicpropByPrimaryKeyDataset", -1, -1, id,
				DbUtils.toUpperCase(dataset));
		List<Variety> resultList = query.getResultList();
		if (resultList.size() > 1)
			throw new RuntimeException("Multiple varieties with id like " + id);

		if (resultList.size() == 1)
			return (Variety) resultList.get(0);
		return null;
	}

	@Override
	public Set findAllVariety(Set dataset) {
		Query query = createNamedQuery("findAllVAllstockBasicpropsDatasets", -1, -1, DbUtils.toUpperCase(dataset));
		log.info("findVarietiesByDataset(Set dataset) :" + dataset + "  " + query.getClass());
		return new LinkedHashSet<Variety>(query.getResultList());
	}

	@Override
	public Set findAllVarietyByExample(Variety germplasm) {
		return null;
	}

	@Override
	public List<Variety> findVarietyByAccession(Collection names, Set dataset) {
		Query query = createNamedQuery("findVAllstockBasicpropByAccessionsDataset", -1, -1, DbUtils.toUpperCase(names),
				DbUtils.toUpperCase(dataset));
		return query.getResultList();

	}

	@Override
	public List<Variety> findVarietyByNamesWithSpace(Collection names, Set dataset) {
		Query query = createNamedQuery("findVAllstockBasicpropByNamesDatasetWithSpace", -1, -1, DbUtils.toUpperCase(names),
				DbUtils.toUpperCase(dataset));
		return query.getResultList();
	}

}
