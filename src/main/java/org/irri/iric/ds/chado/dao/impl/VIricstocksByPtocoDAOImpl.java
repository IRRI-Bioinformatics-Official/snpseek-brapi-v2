package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VIricstocksByPtocoDAO;
import org.irri.iric.ds.chado.domain.model.VIricstocksByPtoco;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstocksByPtoco entities.
 * 
 */
@Repository("VIricstocksByPtocoDAO")
@Transactional
public class VIricstocksByPtocoDAOImpl extends AbstractJpaDao<VIricstocksByPtoco> implements VIricstocksByPtocoDAO {

	@Override
	public List findVarietyByQuanPhenotypeLessThan(BigDecimal object, Set dataset, double value) {
		return null;
	}

	@Override
	public List findVarietyByQuanPhenotypeGreaterThan(BigDecimal object, Set dataset, double value) {
		return null;
	}

	@Override
	public List findVarietyByQuanPhenotypeEquals(BigDecimal object, Set dataset, double value) {
		return null;
	}

	@Override
	public List findVarietyByQualPhenotypeEquals(BigDecimal object, Set dataset, String value) {
		return null;
	}

	@Override
	public List findVarietyByQualPhenotypeLessThan(BigDecimal object, Set dataset, String value) {
		return null;
	}

	@Override
	public List findVarietyByQualPhenotypeGreaterThan(BigDecimal object, Set dataset, String value) {
		return null;
	}

	@Override
	public List findVarietyByPhenotype(BigDecimal phen, Set dataset) {
		return null;
	}

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VIricstocksByPtoco.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstocksByPtocoDAOImpl
	 *
	 */
	public VIricstocksByPtocoDAOImpl() {
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
	 * JPQL Query - findVIricstocksByPtocoByNameContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByNameContaining(String name) throws DataAccessException {

		return findVIricstocksByPtocoByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByIrisUniqueIdContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIrisUniqueIdContaining(String irisUniqueId)
			throws DataAccessException {

		return findVIricstocksByPtocoByIrisUniqueIdContaining(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByIrisUniqueIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIrisUniqueIdContaining(String irisUniqueId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByIrisUniqueIdContaining", startResult, maxRows,
				irisUniqueId);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByDbContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDbContaining(String db) throws DataAccessException {

		return findVIricstocksByPtocoByDbContaining(db, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByDbContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDbContaining(String db, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByDbContaining", startResult, maxRows, db);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByQuanValue
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQuanValue(java.math.BigDecimal quanValue)
			throws DataAccessException {

		return findVIricstocksByPtocoByQuanValue(quanValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByQuanValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQuanValue(java.math.BigDecimal quanValue, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByQuanValue", startResult, maxRows, quanValue);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByOriCountry
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByOriCountry(String oriCountry) throws DataAccessException {

		return findVIricstocksByPtocoByOriCountry(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByOriCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByOriCountry(String oriCountry, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByOriCountry", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvterm
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvterm(String cvterm) throws DataAccessException {

		return findVIricstocksByPtocoByCvterm(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvterm(String cvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByCvterm", startResult, maxRows, cvterm);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByQualValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQualValueContaining(String qualValue)
			throws DataAccessException {

		return findVIricstocksByPtocoByQualValueContaining(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByQualValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQualValueContaining(String qualValue, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByQualValueContaining", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDefinitionContaining(String definition)
			throws DataAccessException {

		return findVIricstocksByPtocoByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDefinitionContaining(String definition, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByDefinitionContaining", startResult, maxRows,
				definition);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByAccessionContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByAccessionContaining(String accession)
			throws DataAccessException {

		return findVIricstocksByPtocoByAccessionContaining(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByAccessionContaining(String accession, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByAccessionContaining", startResult, maxRows, accession);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByAccession
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByAccession(String accession) throws DataAccessException {

		return findVIricstocksByPtocoByAccession(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByAccession(String accession, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByAccession", startResult, maxRows, accession);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoBySubpopulationContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoBySubpopulationContaining(String subpopulation)
			throws DataAccessException {

		return findVIricstocksByPtocoBySubpopulationContaining(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoBySubpopulationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoBySubpopulationContaining(String subpopulation,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoBySubpopulationContaining", startResult, maxRows,
				subpopulation);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvtermContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvtermContaining(String cvterm) throws DataAccessException {

		return findVIricstocksByPtocoByCvtermContaining(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvtermContaining(String cvterm, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByCvtermContaining", startResult, maxRows, cvterm);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstocksByPtocos
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findAllVIricstocksByPtocos() throws DataAccessException {

		return findAllVIricstocksByPtocos(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstocksByPtocos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findAllVIricstocksByPtocos(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstocksByPtocos", startResult, maxRows);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByName
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByName(String name) throws DataAccessException {

		return findVIricstocksByPtocoByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByName", startResult, maxRows, name);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByIrisUniqueId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIrisUniqueId(String irisUniqueId)
			throws DataAccessException {

		return findVIricstocksByPtocoByIrisUniqueId(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByIrisUniqueId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIrisUniqueId(String irisUniqueId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByIrisUniqueId", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstocksByPtoco findVIricstocksByPtocoByPrimaryKey(BigDecimal iricStockPhenotypeId)
			throws DataAccessException {

		return findVIricstocksByPtocoByPrimaryKey(iricStockPhenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstocksByPtoco findVIricstocksByPtocoByPrimaryKey(BigDecimal iricStockPhenotypeId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstocksByPtocoByPrimaryKey", startResult, maxRows,
					iricStockPhenotypeId);
			return (VIricstocksByPtoco) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByPhenotypeId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByPhenotypeId(java.math.BigDecimal phenotypeId)
			throws DataAccessException {

		return findVIricstocksByPtocoByPhenotypeId(phenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByPhenotypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByPhenotypeId(java.math.BigDecimal phenotypeId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByPhenotypeId", startResult, maxRows, phenotypeId);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByQualValue
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQualValue(String qualValue) throws DataAccessException {

		return findVIricstocksByPtocoByQualValue(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByQualValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByQualValue(String qualValue, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByQualValue", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByOriCountryContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByOriCountryContaining(String oriCountry)
			throws DataAccessException {

		return findVIricstocksByPtocoByOriCountryContaining(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByOriCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByOriCountryContaining(String oriCountry, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByOriCountryContaining", startResult, maxRows,
				oriCountry);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByDb
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDb(String db) throws DataAccessException {

		return findVIricstocksByPtocoByDb(db, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByDb
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDb(String db, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByDb", startResult, maxRows, db);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByIricStockId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIricStockId(BigDecimal iricStockId)
			throws DataAccessException {

		return findVIricstocksByPtocoByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByIricStockId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByIricStockId(BigDecimal iricStockId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByIricStockId", startResult, maxRows, iricStockId);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoBySubpopulation
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoBySubpopulation(String subpopulation)
			throws DataAccessException {

		return findVIricstocksByPtocoBySubpopulation(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoBySubpopulation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoBySubpopulation(String subpopulation, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoBySubpopulation", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByDefinition
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDefinition(String definition) throws DataAccessException {

		return findVIricstocksByPtocoByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByDefinition(String definition, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByDefinition", startResult, maxRows, definition);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvtermId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvtermId(BigDecimal cvtermId) throws DataAccessException {

		return findVIricstocksByPtocoByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPtoco> findVIricstocksByPtocoByCvtermId(BigDecimal cvtermId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPtocoByCvtermId", startResult, maxRows, cvtermId);
		return new LinkedHashSet<VIricstocksByPtoco>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByIricStockPhenotypeId
	 *
	 */
	@Transactional
	public VIricstocksByPtoco findVIricstocksByPtocoByIricStockPhenotypeId(BigDecimal iricStockPhenotypeId)
			throws DataAccessException {

		return findVIricstocksByPtocoByIricStockPhenotypeId(iricStockPhenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPtocoByIricStockPhenotypeId
	 *
	 */

	@Transactional
	public VIricstocksByPtoco findVIricstocksByPtocoByIricStockPhenotypeId(BigDecimal iricStockPhenotypeId,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstocksByPtocoByIricStockPhenotypeId", startResult, maxRows,
					iricStockPhenotypeId);
			return (VIricstocksByPtoco) query.getSingleResult();
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
	public boolean canBeMerged(VIricstocksByPtoco entity) {
		return true;
	}

	/*
	 * findVIricstocksByPhenotypeByPhenotypeIdQuanLessthan
	 * findVIricstocksByPhenotypeByPhenotypeIdQuanGreaterthan
	 * findVIricstocksByPhenotypeByPhenotypeIdQuanEquals
	 * findVIricstocksByPhenotypeByPhenotypeIdQualEquals
	 */

	//
	// @Override
	// public List findVarietyByQuanPhenotypeLessThan(BigDecimal phen,
	// String dataset, double value) {
	// 
	// return null;
	// }
	//
	// @Override
	// public List findVarietyByQuanPhenotypeGreaterThan(BigDecimal phen,
	// String dataset, double value) {
	// 
	// return null;
	// }
	//
	// @Override
	// public List findVarietyByQuanPhenotypeEquals(BigDecimal phen,
	// String dataset, double value) {
	// 
	// return null;
	// }
	//
	// @Override
	// public List findVarietyByQualPhenotypeEquals(BigDecimal phen,
	// String dataset, String value) {
	// 
	// return null;
	// }
	//
	// @Override
	// public List findVarietyByPhenotype(BigDecimal phen, String dataset) {
	// 
	// return null;
	// }
	//
	// @Override
	// public List findVarietyByQualPhenotypeLessThan(BigDecimal phen, String
	// dataset, String value) {
	// 
	// return null;
	// }
	//
	// @Override
	// public List findVarietyByQualPhenotypeGreaterThan(BigDecimal phen, String
	// dataset, String value) {
	// 
	// return null;
	// }
	//
	//

}
