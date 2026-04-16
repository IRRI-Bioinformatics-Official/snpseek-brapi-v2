package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.dao.CvTermDAO;
import org.irri.iric.ds.chado.dao.LocusCvTermDAO;
import org.irri.iric.ds.chado.dao.LocusDAO;
import org.irri.iric.ds.chado.domain.model.VLocusCvtermCvtermpath;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusCvtermCvtermpath entities.
 * 
 */
public interface VLocusCvtermCvtermpathDAO extends JpaDao<VLocusCvtermCvtermpath>, LocusCvTermDAO, LocusDAO, CvTermDAO {

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonName(String commonName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonName(String commonName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNameContaining(String name)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNameContaining(String name, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvtermContaining(String objCvterm)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvtermContaining(String objCvterm,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPrimaryKey
	 *
	 */
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByPrimaryKey(java.math.BigDecimal featureId,
			java.math.BigDecimal cvtermId, java.math.BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPrimaryKey
	 *
	 */
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByPrimaryKey(BigDecimal featureId, BigDecimal cvtermId,
			BigDecimal organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvName(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvName(String cvName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonNameContaining(String commonName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonNameContaining(String commonName_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmin
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmin(java.math.BigDecimal fmin)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmin
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmin(BigDecimal fmin, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByName(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigName(String contigName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigName(String contigName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAccContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAccContaining(String subjAcc)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAccContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAccContaining(String subjAcc, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotesContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotesContaining(String notes)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotesContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotesContaining(String notes, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotes
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotes(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotes
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotes(String notes_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvtermCvtermpaths
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findAllVLocusCvtermCvtermpaths() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvtermCvtermpaths
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findAllVLocusCvtermCvtermpaths(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigId(java.math.BigDecimal contigId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigId(BigDecimal contigId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByStrand
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByStrand(java.math.BigDecimal strand)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByStrand
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByStrand(BigDecimal strand, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigNameContaining(String contigName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigNameContaining(String contigName_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvterm
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvterm(String subjCvterm)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvterm
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvterm(String subjCvterm, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAccContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAccContaining(String objAcc)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAccContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAccContaining(String objAcc, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByOrganismId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByOrganismId(java.math.BigDecimal organismId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByOrganismId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByOrganismId(BigDecimal organismId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvtermContaining(String subjCvterm_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvtermContaining(String subjCvterm_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAcc
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAcc(String subjAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAcc
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAcc(String subjAcc_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvterm
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvterm(String objCvterm_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvterm
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvterm(String objCvterm_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAcc
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAcc(String objAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAcc
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAcc(String objAcc_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFeatureId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFeatureId(java.math.BigDecimal featureId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFeatureId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFeatureId(BigDecimal featureId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmax
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmax(java.math.BigDecimal fmax)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmax
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmax(BigDecimal fmax, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvNameContaining(String cvName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvNameContaining(String cvName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvtermId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvtermId(java.math.BigDecimal cvtermId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvtermId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvtermId(BigDecimal cvtermId_1, int startResult,
			int maxRows) throws DataAccessException;

}