
package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.VAllsampleBasicpropDAO;
import org.irri.iric.ds.chado.domain.StockSample;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.model.VAllsampleBasicprop;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VAllsampleBasicprop entities.
 * 
 */
@Repository("VarietyStockDAO")

@Transactional
public class VAllsampleBasicpropDAOImpl extends AbstractJpaDao<VAllsampleBasicprop> implements VAllsampleBasicpropDAO {

	@Override
	public Set<Variety> getIRGCStocks() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Set<StockSample> getSamples(Set dataset) {

		Query query = createNamedQuery("findVAllsampleBasicpropByDatasetIn", -1, -1, dataset);
		return new LinkedHashSet<StockSample>(query.getResultList());
	}

	@Override
	public Set<StockSample> getSamplesById(Set sampleid) {
		return null;
	}

	@Override
	public Set<StockSample> getSamplesBySampleIdInDataset(Set dataset, Set sampleid) {
		Query query = createNamedQuery("findVAllsampleBasicpropByDatasetInAndSampleId", -1, -1, sampleid, dataset);
		return new LinkedHashSet<StockSample>(query.getResultList());
	}

	@Override
	public Set<StockSample> getSamplesByStock(Set stock, Set dataset) {
		return null;
	}

	@Override
	public Set<StockSample> getSamplesByStock(Set stock) {
		return null;
	}

	@Override
	public Set<StockSample> getSamplesByAccession(Set accessions, Set dataset) {
		return null;
	}

	@Override
	public Set<StockSample> getSamplesByAccession(Set accessions) {
		return null;
	}

	@Override
	public Set<StockSample> getSamplesByVarnames(Set names, Set dataset) {
		return null;
	}

	@Override
	public Set<StockSample> getSamplesByVarnames(Set names) {
		return null;
	}

	@Override
	public Set findAllVariety() {
		return null;
	}

	@Override
	public Set findAllVariety(Set dataset) {
		return null;
	}

	@Override
	public Set findAllVarietyByCountry(String country) {
		return null;
	}

	@Override
	public Set findAllVarietyByCountry(String country, Set dataset) {
		return null;
	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation) {
		return null;
	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation, Set dataset) {
		return null;
	}

	@Override
	public Set findAllVarietyByExample(Variety germplasm) {
		return null;
	}

	@Override
	public Set findAllVarietyByExample(Variety germplasm, Set dataset) {
		return null;
	}

	@Override
	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation, Set dataset) {
		return null;
	}

	@Override
	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation) {
		return null;
	}

	@Override
	public Variety findVarietyByName(String name) {
		return null;
	}

	@Override
	public List<Variety> findVarietyByName(String name, Set dataset) {
		return null;
	}

	@Override
	public Variety findVarietyByNameLike(String name) {
		return null;
	}

	@Override
	public Variety findVarietyByNameLike(String name, Set dataset) {
		return null;
	}

	@Override
	public Variety findVarietyByIrisId(String name) {
		return null;
	}

	@Override
	public Variety findVarietyByIrisId(String name, Set dataset) {
		return null;
	}

	@Override
	public Variety findVarietyById(BigDecimal id) {
		return null;
	}

	@Override
	public Variety findVarietyById(BigDecimal id, Set dataset) {
		return null;
	}

	@Override
	public Variety findVarietyByAccession(String name) {
		return null;
	}

	@Override
	public Variety findVarietyByAccession(String name, Set dataset) {
		return null;
	}

	@Override
	public List<Variety> findVarietyByNamesLike(Collection names) {
		return null;
	}

	@Override
	public List<Variety> findVarietyByNamesLike(Collection names, Set dataset) {
		return null;
	}

	@Override
	public List<Variety> findVarietyByNames(Collection names) {
		return null;
	}

	@Override
	public List<Variety> findVarietyByNames(Collection names, Set dataset) {
		return null;
	}

	@Override
	public List<Variety> findVarietyByIrisIds(Collection names) {
		return null;
	}

	@Override
	public List<Variety> findVarietyByIrisIds(Collection names, Set dataset) {
		return null;
	}

	@Override
	public Set<Variety> findVarietiesByIrisId(String irisid) {
		return null;
	}

	@Override
	public Set<Variety> findVarietiesByIrisId(String irisid, Set dataset) {
		return null;
	}

	@Override
	public Set<Variety> findVarietiesByAccession(String accession) {
		return null;
	}

	/*
	 * @Override public Set<Variety> findVarietiesByAccession(String accession, Set
	 * dataset) {
	 * 
	 * return null; }
	 */
	@Override
	public Set<Variety> findVarietiesByName(String names) {
		return null;
	}

	@Override
	public Set<Variety> findVarietiesByName(String names, Set dataset) {
		return null;
	}

	@Override
	public Set<Variety> findVarietiesByNameAccession(String varname, String accession) {
		return null;
	}

	@Override
	public Set<Variety> findVarietiesByNameAccession(String varname, String accession, Set dataset) {
		return null;
	}

	@Override
	public Collection<Variety> findVarietyByIds(Set setQueryVarobj) {
		return null;
	}

	@Override
	public Collection<Variety> findVarietyByIds(Set setQueryVarobj, Set dataset) {
		return null;
	}

	@Override
	public Collection<Variety> findVarietiesByDataset(String dataset) {
		return null;
	}

	@Override
	public Collection<Variety> findVarietiesByDatasets(Set dataset) {
		return null;
	}

	@Override
	public Set findAllVarietyByCountryAndSubpopulationDatasets(String country, String subpopulation, Set dataset) {
		return null;
	}

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VAllsampleBasicprop.class }));

	/**
	 * EntityManager injected by Spring for persistence unit snpseekv3
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VAllsampleBasicpropDAOImpl
	 *
	 */
	public VAllsampleBasicpropDAOImpl() {
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
	 * JPQL Query - findVAllsampleBasicpropByAssay
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByAssay(String assay) throws DataAccessException {

		return findVAllsampleBasicpropByAssay(assay, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByAssay
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByAssay(String assay, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByAssay", startResult, maxRows, assay);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByOriCountry
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByOriCountry(String oriCountry) throws DataAccessException {

		return findVAllsampleBasicpropByOriCountry(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByOriCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByOriCountry(String oriCountry, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByOriCountry", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByPrimaryKey
	 *
	 */
	@Transactional
	public VAllsampleBasicprop findVAllsampleBasicpropByPrimaryKey(Integer stockSampleId) throws DataAccessException {

		return findVAllsampleBasicpropByPrimaryKey(stockSampleId, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByPrimaryKey
	 *
	 */

	@Transactional
	public VAllsampleBasicprop findVAllsampleBasicpropByPrimaryKey(Integer stockSampleId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findVAllsampleBasicpropByPrimaryKey", startResult, maxRows, stockSampleId);
			return (VAllsampleBasicprop) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByNameContaining
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByNameContaining(String name) throws DataAccessException {

		return findVAllsampleBasicpropByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByStockSampleId
	 *
	 */
	@Transactional
	public VAllsampleBasicprop findVAllsampleBasicpropByStockSampleId(Integer stockSampleId)
			throws DataAccessException {

		return findVAllsampleBasicpropByStockSampleId(stockSampleId, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByStockSampleId
	 *
	 */

	@Transactional
	public VAllsampleBasicprop findVAllsampleBasicpropByStockSampleId(Integer stockSampleId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVAllsampleBasicpropByStockSampleId", startResult, maxRows,
					stockSampleId);
			return (VAllsampleBasicprop) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByAssayContaining
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByAssayContaining(String assay) throws DataAccessException {

		return findVAllsampleBasicpropByAssayContaining(assay, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByAssayContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByAssayContaining(String assay, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByAssayContaining", startResult, maxRows, assay);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropBySubpopulationContaining
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropBySubpopulationContaining(String subpopulation)
			throws DataAccessException {

		return findVAllsampleBasicpropBySubpopulationContaining(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropBySubpopulationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropBySubpopulationContaining(String subpopulation,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropBySubpopulationContaining", startResult, maxRows,
				subpopulation);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByGsAccession
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByGsAccession(String gsAccession)
			throws DataAccessException {

		return findVAllsampleBasicpropByGsAccession(gsAccession, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByGsAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByGsAccession(String gsAccession, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByGsAccession", startResult, maxRows, gsAccession);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByStockId
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByStockId(Integer stockId) throws DataAccessException {

		return findVAllsampleBasicpropByStockId(stockId, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByStockId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByStockId(Integer stockId, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByStockId", startResult, maxRows, stockId);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropBySubpopulation
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropBySubpopulation(String subpopulation)
			throws DataAccessException {

		return findVAllsampleBasicpropBySubpopulation(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropBySubpopulation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropBySubpopulation(String subpopulation, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropBySubpopulation", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVAllsampleBasicprops
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findAllVAllsampleBasicprops() throws DataAccessException {

		return findAllVAllsampleBasicprops(-1, -1);
	}

	/**
	 * JPQL Query - findAllVAllsampleBasicprops
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findAllVAllsampleBasicprops(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVAllsampleBasicprops", startResult, maxRows);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByDataset
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByDataset(String dataset) throws DataAccessException {

		return findVAllsampleBasicpropByDataset(dataset, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByDataset
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByDataset(String dataset, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByDataset", startResult, maxRows, dataset);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByHdf5Index
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByHdf5Index(Integer hdf5Index) throws DataAccessException {

		return findVAllsampleBasicpropByHdf5Index(hdf5Index, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByHdf5Index
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByHdf5Index(Integer hdf5Index, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByHdf5Index", startResult, maxRows, hdf5Index);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByBoxCodeContaining
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByBoxCodeContaining(String boxCode)
			throws DataAccessException {

		return findVAllsampleBasicpropByBoxCodeContaining(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByBoxCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByBoxCodeContaining(String boxCode, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByBoxCodeContaining", startResult, maxRows, boxCode);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByName
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByName(String name) throws DataAccessException {

		return findVAllsampleBasicpropByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByName", startResult, maxRows, name);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByGsAccessionContaining
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByGsAccessionContaining(String gsAccession)
			throws DataAccessException {

		return findVAllsampleBasicpropByGsAccessionContaining(gsAccession, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByGsAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByGsAccessionContaining(String gsAccession, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByGsAccessionContaining", startResult, maxRows,
				gsAccession);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByOriCountryContaining
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByOriCountryContaining(String oriCountry)
			throws DataAccessException {

		return findVAllsampleBasicpropByOriCountryContaining(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByOriCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByOriCountryContaining(String oriCountry, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByOriCountryContaining", startResult, maxRows,
				oriCountry);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByDatasetContaining
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByDatasetContaining(String dataset)
			throws DataAccessException {

		return findVAllsampleBasicpropByDatasetContaining(dataset, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByDatasetContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByDatasetContaining(String dataset, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByDatasetContaining", startResult, maxRows, dataset);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByBoxCode
	 *
	 */
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByBoxCode(String boxCode) throws DataAccessException {

		return findVAllsampleBasicpropByBoxCode(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVAllsampleBasicpropByBoxCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VAllsampleBasicprop> findVAllsampleBasicpropByBoxCode(String boxCode, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findVAllsampleBasicpropByBoxCode", startResult, maxRows, boxCode);
		return new LinkedHashSet<VAllsampleBasicprop>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VAllsampleBasicprop entity) {
		return true;
	}

	@Override
	public List<Variety> findVarietyByAccession(Collection names, Set dataset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}

	@Override
	public List<Variety> findVarietyByNamesWithSpace(Collection names, Set dataset) {
		// TODO Auto-generated method stub
		return null;
	}

}
