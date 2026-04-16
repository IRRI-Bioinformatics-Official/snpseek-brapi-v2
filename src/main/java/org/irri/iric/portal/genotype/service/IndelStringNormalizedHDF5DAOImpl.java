package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.ds.chado.domain.GenotypeRunPlatform;
import org.irri.iric.ds.chado.domain.IndelsStringAllvars;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.IndelStringDAO;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

//@Repository("IndelStringNormalizedDAO")
@Repository("IndelStringNormalizedNewDAO")
@Scope(value = "prototype")
public class IndelStringNormalizedHDF5DAOImpl implements IndelStringDAO {

	
	@Autowired
	private IndelStringDAO indelstringdynamicdao;

	@Autowired
	@Qualifier("H5IndelUniAllele12V2DAO")
	private H5Dataset indelallele12dao;

	private boolean isDynamic = true;

	public IndelStringNormalizedHDF5DAOImpl() {
		super();
		// TODO Auto-generated constructor stub
		indelstringdynamicdao = (IndelStringDAO) AppContext.checkBean(indelstringdynamicdao,
				"IndelStringNormalizedDAO");
		// indelsAllvarsPosDAO=(IndelsAllvarsPosDAO)AppContext.checkBean(indelsAllvarsPosDAO,
		// "IndelsAllvarsPosDAO");
		indelallele12dao = (H5Dataset) AppContext.checkBean(indelallele12dao, "H5IndelUniAllele12V2DAO");
	}

	@Override
	public Map readSNPString(Integer organismId, String chr, int startIdx, int endIdx) {
		
		isDynamic = false;
		return indelallele12dao.readSNPString(organismId, chr, startIdx, endIdx);
	}

	@Override
	public Map readSNPString(Integer organismId, String chr, int[] posIdxs) {
		
		return indelstringdynamicdao.readSNPString(organismId, chr, posIdxs);
	}

	@Override
	public Map readSNPString(Integer organismId,Set<BigDecimal> colVarids, String chr, int[] posIdxs) {
		
		return indelstringdynamicdao.readSNPString(organismId, colVarids, chr, posIdxs);
	}

	@Override
	public Map readSNPString(Integer organismId, Set<BigDecimal> colVarids, String chr, int startIdx, int endIdx) {
		
		return indelstringdynamicdao.readSNPString(organismId, colVarids, chr, startIdx, endIdx);

	}

	@Override
	public Map readSNPString(Set colVarids, String chr, int[][] posidxstartend) {
		
		return indelstringdynamicdao.readSNPString(colVarids, chr, posidxstartend);
	}

	@Override
	public Map readSNPString(String chr, int[][] posidxstartend) {
		
		return indelstringdynamicdao.readSNPString(chr, posidxstartend);
	}

	@Override
	public Map readSNPString(String chr, int starvarid, int endvarid, int[][] posstartendIdxs) {
		
		return indelstringdynamicdao.readSNPString(chr, starvarid, endvarid, posstartendIdxs);
	}

	@Override
	public Map readSNPString(String chr, int[] posIdxs, int starvarid, int endvarid) {
		
		return indelstringdynamicdao.readSNPString(chr, posIdxs, starvarid, endvarid);
	}

	@Override
	public Map readSNPString(Integer organismId,Set colVarids, String chr, Collection colpos) {
		
		return indelstringdynamicdao.readSNPString(organismId, colVarids, chr, colpos);
	}

	@Override
	public Map readSNPString(Integer organismId, String chr, Collection colpos) {
		
		return indelstringdynamicdao.readSNPString(organismId, chr, colpos);
	}

	@Override
	public Map getMapAlleleId2Indel() {
		
		// return indelstringdynamicdao.readSNPString();
		if (isDynamic)
			return indelstringdynamicdao.getMapAlleleId2Indel();
		return null;
	}

	@Override
	public Map<BigDecimal, Integer> getMapVariety2Order() {
		return null;
	}

	@Override
	public Map<BigDecimal, Double> getMapVariety2Mismatch() {
		return null;
	}

	@Override
	public List<SnpsAllvarsPos> getListPos() {
		return null;
	}

	@Override
	public List<SnpsStringAllvars> getListResult() {
		return null;
	}

	@Override
	public Map[] readSNPString(List<SnpsAllvarsPos> listpos) {
		return null;
	}

	@Override
	public Map[] readSNPString(GenotypeRunPlatform run, String chr, List<SnpsAllvarsPos> listpos) {
		return null;
	}

	@Override
	public Map[] readSNPString(GenotypeRunPlatform run, Set<BigDecimal> colVarids, String chr,
			List<SnpsAllvarsPos> listpos) {
		return null;
	}

	private Map<BigDecimal, IndelsStringAllvars> readIndelsAllvars(String chr, BigDecimal start, BigDecimal end,
			Collection varList, Collection posList) {
		/*
		 * 
		 * indelsAllvarsDAO = (IndelsAllvarsDAO)AppContext.checkBean(indelsAllvarsDAO,
		 * "IndelAllvarsDAOHDF5"); indelallele12.g
		 * 
		 * Map<BigDecimal, Map<Position, IndelsAllvars>> mapVar2AlleleCall;
		 * 
		 * AppContext.debug("getting indelpos in " + chr + " [" + start + "-" + end +
		 * "]");
		 * 
		 * Set setSnps=null; if(start!=null && end!=null) {
		 * 
		 * // given chr, start end
		 * 
		 * listPos = indelsAllvarsPosDAO.getSNPs(chr, start.intValue(), end.intValue(),
		 * SnpsAllvarsPosDAO.TYPE_3KALLINDEL_V2 );
		 * 
		 * if(listPos.size()==0) return new HashMap();
		 * 
		 * BigDecimal startIdx = listPos.get(0).getAlleleIndex(); BigDecimal endIdx =
		 * listPos.get(listPos.size()-1).getAlleleIndex();
		 * 
		 * //indelsAllvarsDAO //if(varList!=null && varList.size()<1000)
		 * if(varList!=null) { AppContext.debug("getting indelcallsbyvar in " + chr +
		 * " [" + start + " idx" + startIdx + " - " + end + " idx " + endIdx + "]");
		 * setSnps = indelsAllvarsDAO.findIndelAllvarsByVarChrPosBetween(varList, null,
		 * startIdx, endIdx, listPos); } else {
		 * AppContext.debug("getting indelcalls in " + chr + " [" + start + " idx" +
		 * startIdx + " - " + end + " idx " + endIdx + "]"); setSnps =
		 * indelsAllvarsDAO.findIndelAllvarsByChrPosBetween( null, startIdx, endIdx,
		 * listPos); }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * return null; }
		 */

		return null;
	}

	@Override
	public Map<Integer, BigDecimal> getIdx2SampleMapping() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<BigDecimal, Integer> getSample2IdxMapping() {
		// TODO Auto-generated method stub
		return null;
	}

}
