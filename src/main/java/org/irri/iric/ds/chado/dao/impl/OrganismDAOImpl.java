package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.ds.chado.dao.OrganismDAO;
import org.irri.iric.ds.chado.domain.model.Organism;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Organism entities.
 * 
 */
@Repository("OrganismDAO")
@Transactional
public class OrganismDAOImpl extends AbstractJpaDao<Organism> implements OrganismDAO {

	// Map<String,org.irri.iric.portal.domain.Organism> mapName2Organism;

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Organism.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new OrganismDAOImpl
	 *
	 */
	public OrganismDAOImpl() {
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
	 * JPQL Query - findOrganismByGenusContaining
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByGenusContaining(String genus) throws DataAccessException {

		return findOrganismByGenusContaining(genus, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByGenusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByGenusContaining(String genus, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findOrganismByGenusContaining", startResult, maxRows, genus);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByGenus
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByGenus(String genus) throws DataAccessException {

		return findOrganismByGenus(genus, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByGenus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByGenus(String genus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOrganismByGenus", startResult, maxRows, genus);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismBySpeciesContaining
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismBySpeciesContaining(String species) throws DataAccessException {

		return findOrganismBySpeciesContaining(species, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismBySpeciesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismBySpeciesContaining(String species, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findOrganismBySpeciesContaining", startResult, maxRows, species);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByAbbreviationContaining
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByAbbreviationContaining(String abbreviation) throws DataAccessException {

		return findOrganismByAbbreviationContaining(abbreviation, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByAbbreviationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByAbbreviationContaining(String abbreviation, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findOrganismByAbbreviationContaining", startResult, maxRows, abbreviation);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByCommonNameContaining(String commonName) throws DataAccessException {

		return findOrganismByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByCommonNameContaining(String commonName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findOrganismByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllOrganisms
	 *
	 */
	@Transactional
	public Set<Organism> findAllOrganisms() throws DataAccessException {

		return findAllOrganisms(-1, -1);
	}

	/**
	 * JPQL Query - findAllOrganisms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findAllOrganisms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllOrganisms", startResult, maxRows);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByOrganismId
	 *
	 */
	@Transactional
	public Organism findOrganismByOrganismId(BigDecimal organismId) throws DataAccessException {

		return findOrganismByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByOrganismId
	 *
	 */

	@Transactional
	public Organism findOrganismByOrganismId(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findOrganismByOrganismId", startResult, maxRows, organismId);
			return (org.irri.iric.ds.chado.domain.model.Organism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOrganismByAbbreviation
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByAbbreviation(String abbreviation) throws DataAccessException {

		return findOrganismByAbbreviation(abbreviation, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByAbbreviation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByAbbreviation(String abbreviation, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findOrganismByAbbreviation", startResult, maxRows, abbreviation);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismBySpecies
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismBySpecies(String species) throws DataAccessException {

		return findOrganismBySpecies(species, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismBySpecies
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismBySpecies(String species, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findOrganismBySpecies", startResult, maxRows, species);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * JPQL Query - findOrganismByPrimaryKey
	 *
	 */
	@Override
	@Transactional
	public Organism findOrganismByPrimaryKey(BigDecimal organismId) throws DataAccessException {

		return findOrganismByPrimaryKey(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByPrimaryKey
	 *
	 */

	@Transactional
	public Organism findOrganismByPrimaryKey(BigDecimal organismId, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findOrganismByPrimaryKey", startResult, maxRows, organismId);
			return (org.irri.iric.ds.chado.domain.model.Organism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOrganismByCommonName
	 *
	 */
	@Transactional
	public Set<Organism> findOrganismByCommonName(String commonName) throws DataAccessException {

		return findOrganismByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findOrganismByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Organism> findOrganismByCommonName(String commonName, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findOrganismByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<Organism>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Organism entity) {
		return true;
	}

	public Map<String, Organism> getMapName2Organism() {

		Map<String, Organism> mapName2Organism = new LinkedHashMap();
		Iterator<Organism> itOrg = this.findAllOrganisms().iterator();
		while (itOrg.hasNext()) {
			Organism org = itOrg.next();
			mapName2Organism.put(org.getName(), org);
		}
		return mapName2Organism;
	}

	public Organism getOrganismByID(Integer id) {

		return findOrganismByOrganismId(BigDecimal.valueOf(id));
	}

}
