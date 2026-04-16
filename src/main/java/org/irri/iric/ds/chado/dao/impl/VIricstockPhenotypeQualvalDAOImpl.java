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

import org.irri.iric.ds.chado.dao.VIricstockPhenotypeQualvalDAO;
import org.irri.iric.ds.chado.domain.model.VIricstockPhenotypeQualval;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockPhenotypeQualval entities.
 * 
 */
// @Repository("VIricstockPhenotypeQualvalDAO")
@Repository("VCvPhenotypeQualValuesDAO")
@Transactional
public class VIricstockPhenotypeQualvalDAOImpl extends AbstractJpaDao<VIricstockPhenotypeQualval>
		implements VIricstockPhenotypeQualvalDAO {

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VIricstockPhenotypeQualval.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockPhenotypeQualvalDAOImpl
	 *
	 */
	public VIricstockPhenotypeQualvalDAOImpl() {
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
	 * JPQL Query - findVIricstockPhenotypeQualvalByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockPhenotypeQualval findVIricstockPhenotypeQualvalByPrimaryKey(String qualValue,
			BigDecimal phenotypeId) throws DataAccessException {

		return findVIricstockPhenotypeQualvalByPrimaryKey(qualValue, phenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockPhenotypeQualval findVIricstockPhenotypeQualvalByPrimaryKey(String qualValue,
			BigDecimal phenotypeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPhenotypeQualvalByPrimaryKey", startResult, maxRows,
					qualValue, phenotypeId);
			return (VIricstockPhenotypeQualval) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValueContaining(String qualValue)
			throws DataAccessException {

		return findVIricstockPhenotypeQualvalByQualValueContaining(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValueContaining(String qualValue,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQualvalByQualValueContaining", startResult, maxRows,
				qualValue);
		return new LinkedHashSet<VIricstockPhenotypeQualval>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPhenotypeId
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByPhenotypeId(BigDecimal phenotypeId,
			String dataset) throws DataAccessException {

		return findVIricstockPhenotypeQualvalByPhenotypeId(phenotypeId, dataset, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPhenotypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByPhenotypeId(BigDecimal phenotypeId,
			String dataset, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQualvalByPhenotypeId", startResult, maxRows, phenotypeId,
				dataset);
		return new LinkedHashSet<VIricstockPhenotypeQualval>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByPhenotypeIdDataset(BigDecimal phenotypeId,
			Set dataset, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQualvalByPhenotypeIdDataset", startResult, maxRows,
				phenotypeId, dataset);
		return new LinkedHashSet<VIricstockPhenotypeQualval>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQualvals
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQualval> findAllVIricstockPhenotypeQualvals() throws DataAccessException {

		return findAllVIricstockPhenotypeQualvals(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQualvals
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQualval> findAllVIricstockPhenotypeQualvals(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockPhenotypeQualvals", startResult, maxRows);
		return new LinkedHashSet<VIricstockPhenotypeQualval>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValue
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValue(String qualValue)
			throws DataAccessException {

		return findVIricstockPhenotypeQualvalByQualValue(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValue(String qualValue, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQualvalByQualValue", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstockPhenotypeQualval>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockPhenotypeQualval entity) {
		return true;
	}

	@Override
	public Set getUniqueValues(BigDecimal typeId, Set dataset) {
		
		return this.findVIricstockPhenotypeQualvalByPhenotypeIdDataset(typeId, dataset, -1, -1);
	}

}
