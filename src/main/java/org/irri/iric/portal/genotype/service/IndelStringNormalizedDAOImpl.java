package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.ds.chado.dao.IndelsAllvarsDAO;
import org.irri.iric.ds.chado.dao.IndelsAllvarsPosDAO;
import org.irri.iric.ds.chado.domain.GenotypeRunPlatform;
import org.irri.iric.ds.chado.domain.IndelsAllvars;
import org.irri.iric.ds.chado.domain.IndelsAllvarsPos;
import org.irri.iric.ds.chado.domain.IndelsAllvarsStr;
import org.irri.iric.ds.chado.domain.IndelsStringAllvars;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.impl.IndelsAllvarsPosAlleleImpl;
import org.irri.iric.ds.chado.domain.impl.IndelsStringAllvarsImpl;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.IndelStringDAO;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.irri.iric.portal.hdf5.H5ReadStringmatrix;
import org.irri.iric.portal.hdf5.dao.IndelAllvarsHDF5DAOImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Normalize the indel matrix, into IndelID and variety to position to indelId..
 * This converts the data similar to the normalized RDBMS indel data model using
 * IndelAllele and IndelCall tables
 * 
 * @author LMansueto
 *
 */
// @Repository("IndelStringNormalizedDynamicDAO")
@Repository("IndelStringNormalizedDAO")
@Scope(value = "prototype")
// @Scope("value=session")
// @Scope(value="session", proxyMode =ScopedProxyMode.TARGET_CLASS)
public class IndelStringNormalizedDAOImpl implements IndelStringDAO {

	private IndelsAllvarsPosDAO indelsAllvarsPosDAO;

	private Map<BigDecimal, IndelsAllvarsPos> mapAlleleId2Indel;
	// private Map<Integer, BigDecimal> mapIdx2Pos;
	private Map<Integer, Position> mapIdx2Pos;
	private Map<BigDecimal, Integer> mapVariety2Order;
	private Map<BigDecimal, Double> mapVariety2Mismatch;
	// private Set<BigDecimal> sortedPos;
	private Set<Position> sortedPos;
	private List<SnpsAllvarsPos> listPos;
	private List<SnpsStringAllvars> listResult;
	private boolean isMismatchOnly = false;

	private Map mapIdx2Sample;
	private GenotypeRunPlatform run;

	// use positions instead on index
	public IndelStringNormalizedDAOImpl() {
		super();

	}

	public IndelStringNormalizedDAOImpl(IndelsAllvarsPosDAO indelsAllvarsPosDAO, GenotypeRunPlatform run,
			Map mapIdx2Sample) {
		super();
		this.run = run;
		this.indelsAllvarsPosDAO = indelsAllvarsPosDAO;
		this.mapIdx2Sample = mapIdx2Sample;
	}

	/*
	 * public IndelStringNormalizedDAOImpl() { super(); // TODO Auto-generated
	 * constructor stub }
	 */

	@Override
	public Map<BigDecimal, IndelsStringAllvars> readSNPString(Integer organismId, String chr, int startPos, int endPos) {

		return readIndelsAllvars(organismId, chr, BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), null, null);
	}

	@Override
	public Map<BigDecimal, IndelsStringAllvars> readSNPString(Integer organismId, String chr, int[] pos) {

		List<BigDecimal> listpos = new ArrayList();
		for (int i = 0; i < pos.length; i++)
			listpos.add(BigDecimal.valueOf(pos[i]));
		return readIndelsAllvars(organismId, chr, null, null, null, listpos);
	}

	@Override
	public Map<BigDecimal, IndelsStringAllvars> readSNPString(Integer organismId, Set colVarids, String chr, Collection colpos) {

		return readIndelsAllvars(organismId, chr, null, null, colVarids, colpos);
	}

	@Override
	public Map<BigDecimal, IndelsStringAllvars> readSNPString(Integer organismId, String chr, Collection colpos) {

		return readIndelsAllvars(organismId, chr, null, null, null, colpos);
	}

	@Override
	public Map<BigDecimal, IndelsStringAllvars> readSNPString(Integer organismId, Set colVarids, String chr, int[] pos) {

		List<BigDecimal> listpos = new ArrayList();
		for (int i = 0; i < pos.length; i++)
			listpos.add(BigDecimal.valueOf(pos[i]));

		return readIndelsAllvars(organismId, chr, null, null, colVarids, listpos);
	}

	@Override
	public Map<BigDecimal, IndelsStringAllvars> readSNPString(Integer organismId, Set colVarids, String chr, int startPos, int endPos) {

		return readIndelsAllvars(organismId,chr, BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), colVarids, null);
	}

	@Override
	public Map<BigDecimal, SnpsStringAllvars> readSNPString(Set colVarids, String chr, int[][] posstartend) {

		throw new RuntimeException(
				"readSNPString(Set colVarids, String chr, int[][] posstartend)  Not implemented for Indels");
		// return null;

	}

	@Override
	public Map<BigDecimal, SnpsStringAllvars> readSNPString(String chr, int[][] posstartend) {

		// return null;
		throw new RuntimeException(" readSNPString(String chr, int[][] posstartend) Not implemented for Indels");
	}

	public Map<BigDecimal, IndelsAllvarsPos> getMapAlleleId2Indel() {
		return mapAlleleId2Indel;
	}

	/*
	 * public Map<Integer, BigDecimal> getMapIdx2Pos() { return mapIdx2Pos; }
	 */
	public Map<Integer, Position> getMapIdx2Pos() {
		return mapIdx2Pos;
	}

	public Map<BigDecimal, Integer> getMapVariety2Order() {
		return mapVariety2Order;
	}

	public Set getSortedPos() {
		return sortedPos;
	}

	public List<SnpsStringAllvars> getListResult() {
		return listResult;
	}

	public Map<BigDecimal, Double> getMapVariety2Mismatch() {
		return mapVariety2Mismatch;
	}

	public List<SnpsAllvarsPos> getListPos() {
		return listPos;
	}

	/**
	 * Normalize the HDF5 matrix
	 * 
	 * @param chr
	 * @param start
	 * @param end
	 * @param varList
	 * @param posList
	 * @return
	 */
	private Map<BigDecimal, IndelsStringAllvars> readIndelsAllvars(Integer organismId, String chr, BigDecimal start, BigDecimal end,
			Collection varList, Collection posList) {

		// indelsAllvarsDAO = (IndelsAllvarsDAO)AppContext.checkBean(indelsAllvarsDAO,
		// "IndelAllvarsDAOHDF5");

		IndelsAllvarsDAO indelsAllvarsDAO = new IndelAllvarsHDF5DAOImpl(
				new H5Dataset(run.getLocation() + "_1", new H5ReadStringmatrix(), mapIdx2Sample),
				new H5Dataset(run.getLocation() + "_2", new H5ReadStringmatrix(), mapIdx2Sample));

		Map<BigDecimal, Map<Position, IndelsAllvars>> mapVar2AlleleCall;

		AppContext.debug("getting indelpos in " + chr + " [" + start + "-" + end + "]");

		Set setSnps = null;
		if (start != null && end != null) {

			// given chr, start end

			Set vs = new HashSet();
			vs.add(run.getVariantset());
			// ds.add( SnpsAllvarsPosDAO.TYPE_3KALLINDEL_V2 );
			// ds.add( "3kindel" );
			listPos = indelsAllvarsPosDAO.getSNPs(organismId, chr, start.intValue(), end.intValue(), vs);

			if (listPos.size() == 0)
				return new HashMap();

			BigDecimal startIdx = listPos.get(0).getAlleleIndex();
			BigDecimal endIdx = listPos.get(listPos.size() - 1).getAlleleIndex();

			// indelsAllvarsDAO
			// if(varList!=null && varList.size()<1000)
			if (varList != null) {
				AppContext.debug("getting indelcallsbyvar in " + chr + " [" + start + " idx" + startIdx + " - " + end
						+ " idx " + endIdx + "]");
				setSnps = indelsAllvarsDAO.findIndelAllvarsByVarChrPosBetween(organismId, varList, null, startIdx, endIdx, listPos);
			} else {
				AppContext.debug("getting indelcalls in " + chr + " [" + start + " idx" + startIdx + " - " + end
						+ " idx " + endIdx + "]");
				setSnps = indelsAllvarsDAO.findIndelAllvarsByChrPosBetween(organismId, null, startIdx, endIdx, listPos);
			}

		} else if (posList != null) {

			// given list of positions

			Set ds = new HashSet();
			ds.add(run.getDataset());
			// ds.add("3kindel");
			// ds.add( SnpsAllvarsPosDAO.TYPE_3KALLINDEL_V2 );
			// SnpsAllvarsPosDAO.TYPE_3KALLINDEL_V2
			listPos = indelsAllvarsPosDAO.getSNPsInChromosome(organismId, chr, posList, ds);
			if (listPos.size() == 0)
				return new HashMap();

			// optimize HDF5 query, by joining adjacent positions into range

			int indxs[] = new int[listPos.size()];
			List<int[]> listStartStop = new ArrayList();
			int indxscount = 0;
			Map<Integer, BigDecimal> mapIdx2Pos = new HashMap();
			Map<BigDecimal, Integer> mapSnpid2TableIdx = new HashMap();
			Map<Integer, SnpsAllvarsPos> mapIdx2SnpRefposindex = new HashMap();

			Iterator<SnpsAllvarsPos> itSnppos = listPos.iterator(); // posList.iterator();
			List<String> listRef = new ArrayList();

			Map<BigDecimal, IndelsAllvarsPos> mapIdx2Indelpos = new HashMap();
			int previdx = -100;
			int laststart = -100;
			while (itSnppos.hasNext()) {
				IndelsAllvarsPos snppos = (IndelsAllvarsPos) itSnppos.next();
				mapIdx2Indelpos.put(snppos.getAlleleIndex(), snppos);
				listRef.add(snppos.getRefnuc());
				indxs[indxscount] = snppos.getAlleleIndex().intValue();
				mapSnpid2TableIdx.put(snppos.getSnpFeatureId(), indxscount);
				mapIdx2Pos.put(indxscount, snppos.getPosition());
				mapIdx2SnpRefposindex.put(indxscount, snppos);

				if (indxs[indxscount] == (previdx + 1)) {
					previdx = indxs[indxscount];
				} else {
					if (laststart >= 0 && previdx >= 0) {
						listStartStop.add(new int[] { laststart, previdx });
					}
					laststart = indxs[indxscount];
					previdx = indxs[indxscount];
				}
				indxscount++;
			}
			listStartStop.add(new int[] { laststart, previdx });

			int intStartStopIdx[][] = new int[listStartStop.size()][2];
			Iterator<int[]> itStartStop = listStartStop.iterator();
			int sscount = 0;
			while (itStartStop.hasNext()) {
				intStartStopIdx[sscount] = itStartStop.next();
				AppContext.debug("idxrange " + intStartStopIdx[sscount][0] + "-" + intStartStopIdx[sscount][1]);
				sscount++;
			}

			if (varList != null) {
				AppContext.debug("getting indelcallsbyvar in " + chr + "  " + posList.size());
				setSnps = indelsAllvarsDAO.findIndelAllvarsByVarChrPosIn(varList, null, intStartStopIdx, listPos);
			} else {
				AppContext.debug("getting indelcalls in " + chr + "  " + posList.size());
				setSnps = indelsAllvarsDAO.findIndelAllvarsByChrPosIn(null, intStartStopIdx, listPos);
			}

		}

		if (varList != null)
			AppContext.debug("limiting to " + varList.size() + " varieties");

		if (setSnps == null)
			AppContext.logger("setSnps==null");
		else
			AppContext.logger("setSnps.size = " + setSnps.size());

		if (listPos == null)
			AppContext.logger("listPos==null");
		else
			AppContext.logger("listPos.size = " + listPos.size());

		Map<String, SnpsAllvarsPos> mappos2indelpos = new HashMap();
		Iterator<SnpsAllvarsPos> itmapAlleleId2Indel = listPos.iterator();
		while (itmapAlleleId2Indel.hasNext()) {
			SnpsAllvarsPos pos = itmapAlleleId2Indel.next();
			mappos2indelpos.put(pos.getContig() + "-" + pos.getPosition(), pos);
		}

		// normalize indel and indel calls to save memory

		Map<BigDecimal, IndelsAllvarsPos> newmapAlleleId2Indel = new TreeMap();
		Map<IndelsAllvarsPos, BigDecimal> newmapIndel2AlleleId = new HashMap();

		Map<BigDecimal, Integer> mapVar2MismatchCount = new HashMap();

		mapVar2AlleleCall = new HashMap();
		sortedPos = new TreeSet();
		Iterator itIndelcalls = setSnps.iterator();
		long alleleIdcount = 0;
		while (itIndelcalls.hasNext()) {
			IndelsAllvarsStr indelcall = (IndelsAllvarsStr) itIndelcalls.next();

			/*
			 * if(indelcall.getPosition().intValue()==69957) {
			 * AppContext.debug(this.getClass().getCanonicalName() + "; pos=69957: " +
			 * indelcall); }
			 */

			if (varList != null && !varList.contains(indelcall.getVar()))
				continue;
			// if(posList!=null && !posList.contains(indelcall.getPos())) continue;
			IndelsAllvarsPos posidx = (IndelsAllvarsPos) mappos2indelpos
					.get(indelcall.getContig() + "-" + indelcall.getPosition());
			IndelsAllvarsPos newIndelAllele1 = new IndelsAllvarsPosAlleleImpl(indelcall.getPosition(),
					posidx.getAlleleIndex(), posidx.getSnpFeatureId(), posidx.getMaxDellength(), posidx.getRefnuc(),
					posidx.getContig(), indelcall.getAllele1Str(), posidx.getMaxInsLength());
			BigDecimal allId = newmapIndel2AlleleId.get(newIndelAllele1);
			if (allId == null) {
				alleleIdcount++;
				allId = BigDecimal.valueOf(alleleIdcount);
				newmapIndel2AlleleId.put(newIndelAllele1, allId);
				newmapAlleleId2Indel.put(allId, newIndelAllele1);
			}
			IndelsAllvarsPos newIndelAllele2 = new IndelsAllvarsPosAlleleImpl(indelcall.getPosition(),
					posidx.getAlleleIndex(), posidx.getSnpFeatureId(), posidx.getMaxDellength(), posidx.getRefnuc(),
					posidx.getContig(), indelcall.getAllele2Str(), posidx.getMaxInsLength());
			BigDecimal allId2 = newmapIndel2AlleleId.get(newIndelAllele2);
			if (allId2 == null) {
				alleleIdcount++;
				allId2 = BigDecimal.valueOf(alleleIdcount);
				newmapIndel2AlleleId.put(newIndelAllele2, allId2);
				newmapAlleleId2Indel.put(allId2, newIndelAllele2);
			}
			indelcall.setAllele1(allId);
			indelcall.setAllele2(allId2);

			Map<Position, IndelsAllvars> var2calls = mapVar2AlleleCall.get(indelcall.getVar());
			if (var2calls == null) {
				var2calls = new TreeMap();
				mapVar2AlleleCall.put(indelcall.getVar(), var2calls);
			}
			var2calls.put(posidx, indelcall);

			/*
			 * if(posidx.getPosition().intValue()==69957) {
			 * AppContext.debug("pos=chr01-69957; indelcall=" + indelcall +";  posidx=" +
			 * posidx ); } if(posidx.getPosition().intValue()==69778) {
			 * AppContext.debug("pos=chr01-69778; indelcall=" + indelcall +";  posidx=" +
			 * posidx ); }
			 */

			Integer dellen1 = null;
			Integer inslen1 = null;
			try {
				dellen1 = Integer.valueOf(indelcall.getAllele1Str());
			} catch (Exception ex) {
				if (indelcall.getAllele1Str().equals(".") || indelcall.getAllele1Str().equals("?")) {
				} else {
					inslen1 = indelcall.getAllele1Str().length();
				}
			}

			Integer dellen2 = null;
			Integer inslen2 = null;
			try {
				dellen2 = Integer.valueOf(indelcall.getAllele2Str());
			} catch (Exception ex) {
				if (indelcall.getAllele1Str().equals(".") || indelcall.getAllele1Str().equals("?")) {
				} else {
					inslen2 = indelcall.getAllele2Str().length();
				}
			}

			int indelmismatch = 0;
			if (dellen1 != null)
				indelmismatch = dellen1;
			if (inslen1 != null)
				indelmismatch = Math.max(indelmismatch, inslen1);

			int indelmismatch2 = 0;
			if (dellen2 != null)
				indelmismatch2 = Math.max(indelmismatch2, dellen2);
			if (inslen2 != null)
				indelmismatch2 = Math.max(indelmismatch2, inslen2);

			Integer prevcount = mapVar2MismatchCount.get(indelcall.getVar());
			if (prevcount == null)
				mapVar2MismatchCount.put(indelcall.getVar(), Integer.valueOf(Math.max(indelmismatch, indelmismatch2)));
			else
				mapVar2MismatchCount.put(indelcall.getVar(), prevcount + Math.max(indelmismatch, indelmismatch2));

			// sortedPos.add(indelcall.getPosition());
			sortedPos.add(indelcall);
		}

		mapAlleleId2Indel = newmapAlleleId2Indel;
		mapIdx2Pos = new TreeMap();

		/*
		 * Iterator<BigDecimal> itIdx = sortedPos.iterator(); int idxcount=0;
		 * while(itIdx.hasNext()) { BigDecimal pos = itIdx.next();
		 * mapIdx2Pos.put(idxcount , pos); idxcount++; }
		 */

		Iterator<Position> itIdx = sortedPos.iterator();
		int idxcount = 0;
		while (itIdx.hasNext()) {
			Position pos = itIdx.next();
			mapIdx2Pos.put(idxcount, pos);
			idxcount++;
		}

		mapVariety2Mismatch = new LinkedHashMap();
		Set sortedVarieties = new TreeSet(new IndelsStringAllvarsImplSorter());
		Iterator<BigDecimal> itVar = mapVar2AlleleCall.keySet().iterator();
		while (itVar.hasNext()) {
			BigDecimal var = itVar.next();

			if (isMismatchOnly && mapVar2MismatchCount.get(var) == 0)
				continue;
			Map<Position, IndelsAllvars> mapPos2Indelallvars = mapVar2AlleleCall.get(var);

			if (listPos.size() != mapPos2Indelallvars.size())
				new RuntimeException("listPos.size()!=mapPos2Indelallvars.size():" + listPos.size() + ", "
						+ mapPos2Indelallvars.size() + " for var " + var);
			sortedVarieties.add(new IndelsStringAllvarsImpl(run.getDataset(), var, mapPos2Indelallvars,
					BigDecimal.valueOf(mapVar2MismatchCount.get(var)), chr));
			mapVariety2Mismatch.put(var, Double.valueOf(mapVar2MismatchCount.get(var)));
		}

		// sort varieties by sumof lengths of ins and dels
		listResult = new ArrayList();
		// sort included varieties
		mapVariety2Order = new LinkedHashMap();
		Map mapVarId2IndelsStringAllvars = new LinkedHashMap();
		int ordercount = 0;
		Iterator itSorVars = sortedVarieties.iterator();
		while (itSorVars.hasNext()) {
			IndelsStringAllvars snpstrvar = (IndelsStringAllvars) itSorVars.next();
			listResult.add(snpstrvar);
			mapVariety2Order.put(snpstrvar.getVar(), ordercount);
			mapVarId2IndelsStringAllvars.put(snpstrvar.getVar(), snpstrvar);
			ordercount++;
		}

		/*
		 * StringBuffer buffia=new StringBuffer(); Iterator<BigDecimal> itIndelAllele =
		 * newmapAlleleId2Indel.keySet().iterator(); while(itIndelAllele.hasNext()) {
		 * BigDecimal iaid= itIndelAllele.next(); buffia.append(iaid + " " +
		 * newmapAlleleId2Indel.get(iaid) + "\n"); }
		 * AppContext.debug("new indelalleles:\n"); AppContext.debug(buffia.toString());
		 */

		return mapVarId2IndelsStringAllvars;
	}

	@Override
	public Map readSNPString(String chr, int starvarid, int endvarid, int[][] posstartendIdxs) {
		return null;
	}

	@Override
	public Map readSNPString(String chr, int[] posIdxs, int starvarid, int endvarid) {
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

	/**
	 * Sorts variety by mismatch desc, subpopulation, then country, then id Used in
	 * Mismatch ordering for the same number of mismatch, assuming variety from same
	 * subpopulation, then country will be closer relative than random
	 * 
	 * @author lmansueto
	 *
	 */
	class IndelsStringAllvarsImplSorter implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {

			IndelsStringAllvarsImpl s1 = (IndelsStringAllvarsImpl) o1;
			IndelsStringAllvarsImpl s2 = (IndelsStringAllvarsImpl) o2;
			int ret = -s1.getMismatch().compareTo(s2.getMismatch());
			if (ret == 0) {
				return s1.getVar().compareTo(s2.getVar());
				/*
				 * Variety v1 =varietyfacade.getMapId2Variety().get(s1.getVar()); Variety v2
				 * =varietyfacade.getMapId2Variety().get(s2.getVar());
				 * if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null) {
				 * ret=v1.getSubpopulation().compareTo(v2.getSubpopulation()); if( ret==0 ) {
				 * if(v1.getCountry()!=null && v2.getCountry()!=null) { ret =
				 * v1.getCountry().compareTo(v2.getCountry()); if(ret==0) return
				 * v1.getVarietyId().compareTo(v2.getVarietyId()); else return ret; } } else
				 * return ret; } else if(v1.getCountry()!=null && v2.getCountry()!=null) { ret =
				 * v1.getCountry().compareTo(v2.getCountry()); if(ret==0) return
				 * v1.getVarietyId().compareTo(v2.getVarietyId()); else return ret; } return
				 * v1.getVarietyId().compareTo(v2.getVarietyId());
				 */

			} else
				return ret;
		}
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
