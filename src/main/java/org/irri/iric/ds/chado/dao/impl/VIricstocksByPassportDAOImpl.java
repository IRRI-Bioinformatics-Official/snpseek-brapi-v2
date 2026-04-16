package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VIricstocksByPassportDAO;
import org.irri.iric.ds.chado.domain.model.VIricstocksByPassport;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstocksByPassport entities.
 * 
 */
// @Repository("VIricstocksByPassportDAO")
@Repository("VarietyByPassportDAO")

@Transactional
public class VIricstocksByPassportDAOImpl extends AbstractJpaDao<VIricstocksByPassport>
		implements VIricstocksByPassportDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VIricstocksByPassport.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstocksByPassportDAOImpl
	 *
	 */
	public VIricstocksByPassportDAOImpl() {
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
	 * JPQL Query - findVIricstocksByPassportBySubpopulation
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulation(String subpopulation)
			throws DataAccessException {

		return findVIricstocksByPassportBySubpopulation(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulation(String subpopulation, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportBySubpopulation", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueIdContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueIdContaining(String irisUniqueId)
			throws DataAccessException {

		return findVIricstocksByPassportByIrisUniqueIdContaining(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueIdContaining(String irisUniqueId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByIrisUniqueIdContaining", startResult, maxRows,
				irisUniqueId);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstocksByPassport findVIricstocksByPassportByPrimaryKey(Integer iricStockpropId)
			throws DataAccessException {

		return findVIricstocksByPassportByPrimaryKey(iricStockpropId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstocksByPassport findVIricstocksByPassportByPrimaryKey(Integer iricStockpropId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstocksByPassportByPrimaryKey", startResult, maxRows,
					iricStockpropId);
			return (VIricstocksByPassport) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueId(String irisUniqueId)
			throws DataAccessException {

		return findVIricstocksByPassportByIrisUniqueId(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueId(String irisUniqueId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByIrisUniqueId", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulationContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulationContaining(String subpopulation)
			throws DataAccessException {

		return findVIricstocksByPassportBySubpopulationContaining(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulationContaining(String subpopulation,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportBySubpopulationContaining", startResult, maxRows,
				subpopulation);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByName
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByName(String name) throws DataAccessException {

		return findVIricstocksByPassportByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByName", startResult, maxRows, name);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstocksByPassports
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findAllVIricstocksByPassports() throws DataAccessException {

		return findAllVIricstocksByPassports(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstocksByPassports
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findAllVIricstocksByPassports(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstocksByPassports", startResult, maxRows);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByValue
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValue(String value) throws DataAccessException {

		return findVIricstocksByPassportByValue(value, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValue(String value, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByValue", startResult, maxRows, value);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValueContaining(String value)
			throws DataAccessException {

		return findVIricstocksByPassportByValueContaining(value, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValueContaining(String value, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByValueContaining", startResult, maxRows, value);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIricStockId(Integer iricStockId)
			throws DataAccessException {

		return findVIricstocksByPassportByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIricStockId(Integer iricStockId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByIricStockId", startResult, maxRows, iricStockId);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByTypeId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByTypeId(java.math.BigDecimal typeId)
			throws DataAccessException {

		return findVIricstocksByPassportByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByTypeId(java.math.BigDecimal typeId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountry
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountry(String oriCountry)
			throws DataAccessException {

		return findVIricstocksByPassportByOriCountry(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountry(String oriCountry, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByOriCountry", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByNameContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByNameContaining(String name)
			throws DataAccessException {

		return findVIricstocksByPassportByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByNameContaining(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountryContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountryContaining(String oriCountry)
			throws DataAccessException {

		return findVIricstocksByPassportByOriCountryContaining(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountryContaining(String oriCountry,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPassportByOriCountryContaining", startResult, maxRows,
				oriCountry);
		return new LinkedHashSet<VIricstocksByPassport>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockpropId
	 *
	 */
	@Transactional
	public VIricstocksByPassport findVIricstocksByPassportByIricStockpropId(Integer iricStockpropId)
			throws DataAccessException {

		return findVIricstocksByPassportByIricStockpropId(iricStockpropId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockpropId
	 *
	 */

	@Transactional
	public VIricstocksByPassport findVIricstocksByPassportByIricStockpropId(Integer iricStockpropId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstocksByPassportByIricStockpropId", startResult, maxRows,
					iricStockpropId);
			return (VIricstocksByPassport) query.getSingleResult();
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
	public boolean canBeMerged(VIricstocksByPassport entity) {
		return true;
	}

	@Override
	@Transactional
	public List findVarietyByPassportEquals(BigDecimal type_id, Set dataset, String value) {
		
		Query query = createNamedQuery("findVIricstocksByPassportByTypeIdValueEquals", -1, -1, type_id, dataset, value);
		return query.getResultList();
	}

	// @Override
	// @Transactional
	// public List findVIricstocksByPassportByTypeIdValueContaining(BigDecimal
	// type_id, String value) {
	// 
	// Query query =
	// createNamedQuery("findVIricstocksByPassportByTypeIdValueContaining", -1, -1 ,
	// type_id, value);
	// return query.getResultList();
	// }

	@Override
	public List findVarietyByPassport(String sPassId) {
		

		List listvars = new ArrayList();
		listvars.addAll(findVIricstocksByPassportByTypeId(BigDecimal.valueOf(Long.valueOf(sPassId))));
		return listvars;
	}

}
