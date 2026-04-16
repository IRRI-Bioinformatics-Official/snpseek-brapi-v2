package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VCvPhenotypeByPtoco;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VCvPhenotypeByPtoco entities.
 * 
 */
public interface VCvPhenotypeByPtocoDAO extends JpaDao<VCvPhenotypeByPtoco>, CvTermPathDAO {

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjAccContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjAccContaining(String objAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjAccContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjAccContaining(String objAcc, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvname
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvname(String subjCvname) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvname
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvname(String subjCvname, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjDb
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjDb(String objDb) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjDb
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjDb(String objDb, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByPrimaryKey
	 *
	 */
	public VCvPhenotypeByPtoco findVCvPhenotypeByPtocoByPrimaryKey(BigDecimal objCvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByPrimaryKey
	 *
	 */
	public VCvPhenotypeByPtoco findVCvPhenotypeByPtocoByPrimaryKey(BigDecimal objCvtermId, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvnameContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvnameContaining(String objCvname)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvnameContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvnameContaining(String objCvname, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjAccContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjAccContaining(String subjAcc)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjAccContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjAccContaining(String subjAcc, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDefinition
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDefinition(String subjDefinition)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDefinition
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDefinition(String subjDefinition, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvtermContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvtermContaining(String subjCvterm)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvtermContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvtermContaining(String subjCvterm, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvname
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvname(String objCvname_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvname
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvname(String objCvname_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPhenotypeByPtocos
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findAllVCvPhenotypeByPtocos() throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPhenotypeByPtocos
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findAllVCvPhenotypeByPtocos(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDbContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDbContaining(String subjDb) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDbContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDbContaining(String subjDb, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvterm
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvterm(String subjCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvterm
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvterm(String subjCvterm_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvid
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvid(BigDecimal objCvid) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvid
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvid(BigDecimal objCvid, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQualValueContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQualValueContaining(String qualValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQualValueContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQualValueContaining(String qualValue, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvtermId
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvtermId(BigDecimal subjCvtermId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvtermId
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvtermId(BigDecimal subjCvtermId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByPathdistance
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByPathdistance(BigDecimal pathdistance)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByPathdistance
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByPathdistance(BigDecimal pathdistance, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQualValue
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQualValue(String qualValue_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQualValue
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQualValue(String qualValue_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvtermId
	 *
	 */
	public VCvPhenotypeByPtoco findVCvPhenotypeByPtocoByObjCvtermId(BigDecimal objCvtermId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvtermId
	 *
	 */
	public VCvPhenotypeByPtoco findVCvPhenotypeByPtocoByObjCvtermId(BigDecimal objCvtermId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjAcc
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjAcc(String subjAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjAcc
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjAcc(String subjAcc_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvid
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvid(BigDecimal subjCvid) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvid
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvid(BigDecimal subjCvid, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvnameContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvnameContaining(String subjCvname_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjCvnameContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjCvnameContaining(String subjCvname_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQuanValue
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQuanValue(java.math.BigDecimal quanValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByQuanValue
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByQuanValue(BigDecimal quanValue, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvtermContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvtermContaining(String objCvterm)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvtermContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvtermContaining(String objCvterm, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDb
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDb(String subjDb_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDb
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDb(String subjDb_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDefinitionContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDefinitionContaining(String subjDefinition_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoBySubjDefinitionContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoBySubjDefinitionContaining(String subjDefinition_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjAcc
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjAcc(String objAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjAcc
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjAcc(String objAcc_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvterm
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvterm(String objCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjCvterm
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjCvterm(String objCvterm_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjDbContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjDbContaining(String objDb_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoByObjDbContaining
	 *
	 */
	public Set<VCvPhenotypeByPtoco> findVCvPhenotypeByPtocoByObjDbContaining(String objDb_1, int startResult,
			int maxRows) throws DataAccessException;

}