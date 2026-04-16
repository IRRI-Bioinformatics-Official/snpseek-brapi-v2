package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VIricstockPassportValuesDAO;
import org.irri.iric.ds.chado.domain.model.VIricstockPassportValues;
import org.irri.iric.ds.utils.DbUtils;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * DAO to manage VIricstockPassportValues entities.
 * 
 */
// @Repository("VIricstockPassportValuesDAO")
// @Repository("CvTermUniqueValuesDAO")
@Repository("VCvPassportValuesDAO")
@Transactional
public class VIricstockPassportValuesDAOImpl extends AbstractJpaDao<VIricstockPassportValues>
		implements VIricstockPassportValuesDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VIricstockPassportValues.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockPassportValuesDAOImpl
	 *
	 */
	public VIricstockPassportValuesDAOImpl() {
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
	 * JPQL Query - findVIricstockPassportValuesByTypeId
	 *
	 */
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(BigDecimal typeId, Set dataset)
			throws DataAccessException {

		return findVIricstockPassportValuesByTypeId(typeId, dataset, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(BigDecimal typeId, Set dataset,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportValuesByTypeId", startResult, maxRows, typeId, dataset);
		return new LinkedHashSet<VIricstockPassportValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockPassportValues findVIricstockPassportValuesByPrimaryKey(String value, BigDecimal typeId)
			throws DataAccessException {

		return findVIricstockPassportValuesByPrimaryKey(value, typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockPassportValues findVIricstockPassportValuesByPrimaryKey(String value, BigDecimal typeId,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPassportValuesByPrimaryKey", startResult, maxRows, value,
					typeId);
			return (VIricstockPassportValues) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValueContaining(String value)
			throws DataAccessException {

		return findVIricstockPassportValuesByValueContaining(value, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValueContaining(String value, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportValuesByValueContaining", startResult, maxRows, value);
		return new LinkedHashSet<VIricstockPassportValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstockPassportValuess
	 *
	 */
	@Transactional
	public Set<VIricstockPassportValues> findAllVIricstockPassportValuess() throws DataAccessException {

		return findAllVIricstockPassportValuess(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockPassportValuess
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassportValues> findAllVIricstockPassportValuess(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockPassportValuess", startResult, maxRows);
		return new LinkedHashSet<VIricstockPassportValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByValue
	 *
	 */
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValue(String value) throws DataAccessException {

		return findVIricstockPassportValuesByValue(value, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValue(String value, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportValuesByValue", startResult, maxRows, value);
		return new LinkedHashSet<VIricstockPassportValues>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockPassportValues entity) {
		return true;
	}

	//
	// @Override
	// public Set getUniqueValues(BigDecimal typeId, Set dataset) {
	//
	// if(AppContext.isOracle())
	// return this.findVIricstockPassportValuesByTypeId(typeId, dataset);
	// else {
	//
	// String sql="SELECT DISTINCT sp.value, sp.type_id, db.name as dataset FROM " +
	// AppContext.getDefaultSchema() + ".stockprop sp, " +
	// AppContext.getDefaultSchema() + ".dbxref dx, " +
	// AppContext.getDefaultSchema() +
	// ". db where sp.dbxref_id=dx.dbxref_id and dx.db_id=db.db_id and sp.type_id="
	// + typeId + " and db.name='" + dataset + "' ORDER BY 1";
	//
	// /*
	// String sql="";
	// if(dataset.equals("3k"))
	// sql="SELECT DISTINCT iric_stockprop.value, iric_stockprop.type_id, '3k' AS
	// dataset FROM public.iric_stockprop where type_id=" + typeId + " ORDER BY 1";
	// else if(dataset.equals("hdra"))
	// sql="SELECT DISTINCT hdra_stockprop.value, hdra_stockprop.type_id, 'hdra' AS
	// dataset FROM public.hdra_stockprop where type_id=" + typeId + " ORDER BY 1";
	// else if(dataset.equals("gq92"))
	// sql="SELECT DISTINCT gopal_stockprop.value, gopal_stockprop.type_id, 'gq92'
	// AS dataset FROM public.gopal_stockprop where type_id=" + typeId + " ORDER BY
	// 1";
	// */
	//
	// return new LinkedHashSet(AppContext.executeSQL( getEntityManager() ,
	// VIricstockPassportValues.class, sql));
	// }
	//
	// }

	@Override
	public Set getUniqueValues(BigDecimal typeId, Set dataset) {

		String sql = "SELECT DISTINCT sp.value, sp.type_id, db.name as dataset FROM public.stock_sample ss,public.stockprop sp, public.dbxref dx, public.db where ss.stock_id=sp.stock_id and ss.dbxref_id=dx.dbxref_id and dx.db_id=db.db_id and sp.type_id="
				+ typeId + " and db.name in (" + DbUtils.toCSVquoted(dataset, "'") + ") ORDER BY 1";

		// SELECT DISTINCT sp.value, sp.type_id, db.name as dataset FROM
		// public.stockprop sp, stock_sample ss, public.dbxref dx, public. db where
		// ss.stock_id=sp.stock_id and ss.dbxref_id=dx.dbxref_id and dx.db_id=db.db_id
		// and sp.type_id=67042 and db.name in ('3k') ORDER BY 1;

		/*
		 * String sql=""; if(dataset.equals("3k"))
		 * sql="SELECT DISTINCT iric_stockprop.value, iric_stockprop.type_id, '3k' AS dataset FROM public.iric_stockprop where type_id="
		 * + typeId + " ORDER BY 1"; else if(dataset.equals("hdra"))
		 * sql="SELECT DISTINCT hdra_stockprop.value, hdra_stockprop.type_id, 'hdra' AS dataset FROM public.hdra_stockprop  where type_id="
		 * + typeId + " ORDER BY 1"; else if(dataset.equals("gq92"))
		 * sql="SELECT DISTINCT gopal_stockprop.value, gopal_stockprop.type_id, 'gq92' AS dataset FROM public.gopal_stockprop  where type_id="
		 * + typeId + " ORDER BY 1";
		 */

		return new LinkedHashSet(DbUtils.executeSQL(getEntityManager(), VIricstockPassportValues.class, sql));

	}

	@Override
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(BigDecimal typeId)
			throws DataAccessException {
		return null;
	}

	@Override
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(BigDecimal typeId, int startResult,
			int maxRows) throws DataAccessException {
		return null;
	}

}
