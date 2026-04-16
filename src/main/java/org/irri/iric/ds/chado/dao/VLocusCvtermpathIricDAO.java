package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VLocusCvtermpathIric;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusCvtermpathIric entities.
 * 
 */
public interface VLocusCvtermpathIricDAO extends JpaDao<VLocusCvtermpathIric>, LocusCvTermDAO { // , LocusDAO, CvTermDAO
																								// {

	/**
	 * JPQL Query - findVLocusCvtermpathIricByContigName
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByContigName(String contigName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByContigName
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByContigName(String contigName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByNotes
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByNotes(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByNotes
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByNotes(String notes, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByDb
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByDb(String db) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByDb
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByDb(String db, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByName
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByName
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByOrganismId
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByOrganismId(java.math.BigDecimal organismId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByOrganismId
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCvName
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvName(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCvName
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvName(String cvName, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFmax
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFmax(java.math.BigDecimal fmax)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFmax
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFmax(BigDecimal fmax, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCommonNameContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCommonNameContaining(String commonName)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCommonNameContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCommonNameContaining(String commonName, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByRapRepContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapRepContaining(String rapRep)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByRapRepContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapRepContaining(String rapRep, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByIric
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByIric(String iric) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByIric
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByIric(String iric, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByObjCvterm
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjCvterm(String objCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByObjCvterm
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjCvterm(String objCvterm, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByContigNameContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByContigNameContaining(String contigName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByContigNameContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByContigNameContaining(String contigName_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByMsu7Containing
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByMsu7Containing(String msu7) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByMsu7Containing
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByMsu7Containing(String msu7, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByStrand
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByStrand(java.math.BigDecimal strand)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByStrand
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByStrand(BigDecimal strand, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByDbContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByDbContaining(String db_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByDbContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByDbContaining(String db_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFgenesh
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFgenesh(String fgenesh) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFgenesh
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFgenesh(String fgenesh, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByRapRep
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapRep(String rapRep_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByRapRep
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapRep(String rapRep_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCvNameContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvNameContaining(String cvName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCvNameContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvNameContaining(String cvName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCommonName
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCommonName(String commonName_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCommonName
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCommonName(String commonName_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByRapPredContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapPredContaining(String rapPred)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByRapPredContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapPredContaining(String rapPred, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCvtermId
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvtermId(java.math.BigDecimal cvtermId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCvtermId
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvtermId(BigDecimal cvtermId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFgeneshContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFgeneshContaining(String fgenesh_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFgeneshContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFgeneshContaining(String fgenesh_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByNameContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByNameContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByNameContaining(String name_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByPrimaryKey
	 *
	 */
	public VLocusCvtermpathIric findVLocusCvtermpathIricByPrimaryKey(Integer featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByPrimaryKey
	 *
	 */
	public VLocusCvtermpathIric findVLocusCvtermpathIricByPrimaryKey(Integer featureId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByNotesContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByNotesContaining(String notes_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByNotesContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByNotesContaining(String notes_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFeatureId
	 *
	 */
	public VLocusCvtermpathIric findVLocusCvtermpathIricByFeatureId(Integer featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFeatureId
	 *
	 */
	public VLocusCvtermpathIric findVLocusCvtermpathIricByFeatureId(Integer featureId_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByIricContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByIricContaining(String iric_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByIricContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByIricContaining(String iric_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricBySubjCvterm
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjCvterm(String subjCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricBySubjCvterm
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjCvterm(String subjCvterm, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricBySubjAccContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjAccContaining(String subjAcc)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricBySubjAccContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjAccContaining(String subjAcc, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByObjAccContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjAccContaining(String objAcc)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByObjAccContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjAccContaining(String objAcc, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjCvtermContaining(String subjCvterm_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjCvtermContaining(String subjCvterm_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCvId
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvId(java.math.BigDecimal cvId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByCvId
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByCvId(BigDecimal cvId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByMsu7
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByMsu7(String msu7_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByMsu7
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByMsu7(String msu7_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByContigId
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByContigId(java.math.BigDecimal contigId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByContigId
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByContigId(BigDecimal contigId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvtermpathIrics
	 *
	 */
	public Set<VLocusCvtermpathIric> findAllVLocusCvtermpathIrics() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvtermpathIrics
	 *
	 */
	public Set<VLocusCvtermpathIric> findAllVLocusCvtermpathIrics(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByRapPred
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapPred(String rapPred_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByRapPred
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByRapPred(String rapPred_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByPathdistance
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByPathdistance(Integer pathdistance)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByPathdistance
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByPathdistance(Integer pathdistance, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricBySubjAcc
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjAcc(String subjAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricBySubjAcc
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricBySubjAcc(String subjAcc_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByObjAcc
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjAcc(String objAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByObjAcc
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjAcc(String objAcc_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFmin
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFmin(java.math.BigDecimal fmin)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByFmin
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByFmin(BigDecimal fmin, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjCvtermContaining(String objCvterm_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathIricByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermpathIric> findVLocusCvtermpathIricByObjCvtermContaining(String objCvterm_1, int startResult,
			int maxRows) throws DataAccessException;

}