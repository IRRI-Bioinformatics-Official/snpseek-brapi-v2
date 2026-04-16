package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.ds.chado.domain.model.VCvPhenotypeByPtocoPath;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VCvPhenotypeByPtocoPath entities.
 * 
 */
public interface VCvPhenotypeByPtocoPathDAO extends JpaDao<VCvPhenotypeByPtocoPath>, CvTermPathDAO {

	/**
	 * JPQL Query - findAllVCvPhenotypeByPtocoPaths
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findAllVCvPhenotypeByPtocoPaths() throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPhenotypeByPtocoPaths
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findAllVCvPhenotypeByPtocoPaths(int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDefinition
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDefinition(String subjDefinition)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDefinition
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDefinition(String subjDefinition,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDb
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDb(String subjDb) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDb
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDb(String subjDb, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjDbContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjDbContaining(String objDb)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjDbContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjDbContaining(String objDb, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvnameContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvnameContaining(String subjCvname)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvnameContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvnameContaining(String subjCvname,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvid
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvid(java.math.BigDecimal objCvid)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvid
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvid(BigDecimal objCvid, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvterm
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvterm(String subjCvterm)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvterm
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvterm(String subjCvterm, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvname
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvname(String subjCvname_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvname
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvname(String subjCvname_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvtermContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvtermContaining(String objCvterm)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvtermContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvtermContaining(String objCvterm,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvterm
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvterm(String objCvterm_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvterm
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvterm(String objCvterm_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvtermContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvtermContaining(String subjCvterm_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvtermContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvtermContaining(String subjCvterm_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDbContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDbContaining(String subjDb_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDbContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDbContaining(String subjDb_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByPathdistance
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByPathdistance(BigDecimal pathdistance)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByPathdistance
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByPathdistance(BigDecimal pathdistance,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQualValueContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQualValueContaining(String qualValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQualValueContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQualValueContaining(String qualValue,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvid
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvid(java.math.BigDecimal subjCvid)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvid
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjCvid(BigDecimal subjCvid, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQualValue
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQualValue(String qualValue_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQualValue
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQualValue(String qualValue_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjAccContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjAccContaining(String subjAcc)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjAccContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjAccContaining(String subjAcc, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjAccContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjAccContaining(String objAcc)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjAccContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjAccContaining(String objAcc, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjAcc
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjAcc(String subjAcc_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjAcc
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjAcc(String subjAcc_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjDb
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjDb(String objDb_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjDb
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjDb(String objDb_1, int startResult, int maxRows)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvtermId
	 *
	 */
	public VCvPhenotypeByPtocoPath findVCvPhenotypeByPtocoPathBySubjCvtermId(BigDecimal subjCvtermId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjCvtermId
	 *
	 */
	public VCvPhenotypeByPtocoPath findVCvPhenotypeByPtocoPathBySubjCvtermId(BigDecimal subjCvtermId, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjAcc
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjAcc(String objAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjAcc
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjAcc(String objAcc_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvname
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvname(String objCvname)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvname
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvname(String objCvname, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDefinitionContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDefinitionContaining(String subjDefinition_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathBySubjDefinitionContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathBySubjDefinitionContaining(String subjDefinition_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQuanValue
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQuanValue(java.math.BigDecimal quanValue)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByQuanValue
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByQuanValue(BigDecimal quanValue, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvnameContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvnameContaining(String objCvname_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvnameContaining
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvnameContaining(String objCvname_1,
			int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByPrimaryKey
	 *
	 */
	public VCvPhenotypeByPtocoPath findVCvPhenotypeByPtocoPathByPrimaryKey(BigDecimal subjCvtermId_1)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByPrimaryKey
	 *
	 */
	public VCvPhenotypeByPtocoPath findVCvPhenotypeByPtocoPathByPrimaryKey(BigDecimal subjCvtermId_1, int startResult,
			int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvtermId
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvtermId(java.math.BigDecimal objCvtermId)
			throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPtocoPathByObjCvtermId
	 *
	 */
	public Set<VCvPhenotypeByPtocoPath> findVCvPhenotypeByPtocoPathByObjCvtermId(BigDecimal objCvtermId,
			int startResult, int maxRows) throws DataAccessException;

}