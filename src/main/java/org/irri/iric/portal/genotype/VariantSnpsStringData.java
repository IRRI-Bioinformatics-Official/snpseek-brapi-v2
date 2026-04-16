package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.impl.SnpsStringAllvarsImpl;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genotype.service.SnpsStringAllvarsImplSorter;
import org.irri.iric.portal.genotype.service.SnpsStringMultiHDF5nRDBMSHybridService;

public class VariantSnpsStringData extends VariantStringData {

	private VariantSnpsStringData nonsynVariantSnpsStringData;

	private Set dataset;
	private String strRef;
	private Map<BigDecimal, Map<Position, Character>> mapVarid2SnpsAllele2str;
	// private Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles;
	private Map<Position, Set<Character>> mapPos2NonsynAlleles;
	private Map<Position, Set<Character>> mapPos2SynAlleles;
	// private Set<Position> setSnpInExonPos;
	// private Map<Integer,Integer> mapMergedIdx2SnpIdx;
	private Set<Position> setSnpSpliceDonorPos;
	private Set<Position> setSnpSpliceAcceptorPos;
	private GenotypeQueryParams param;

	private Map<Integer, BigDecimal> id2SampleMapping;

	public VariantSnpsStringData(Map<BigDecimal, Double> mapVariety2Mismatch, Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos,
			// Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString, Map<String, Double> mapReference2Mismatch, Set dataset) {

		this(mapVariety2Mismatch, mapVariety2Order, listPos, listVariantsString, null, null, null, null, null, null,
				mapReference2Mismatch, dataset);
	}

	public VariantSnpsStringData(Map<BigDecimal, Double> mapVariety2Mismatch, Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos,
			// Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString, String strRef,

			// Map<BigDecimal, Map<Integer, Character>> mapVarid2SnpsAllele2str,
			Map<BigDecimal, Map<Position, Character>> mapVarid2SnpsAllele2str,

			Map<Position, Set<Character>> mapPos2NonsynAlleles, Map<Position, Set<Character>> mapPos2SynAlleles,
			// Set<Position> setSnpInExonPos,
			// Map<Integer, Integer> mapMergedIdx2SnpIdx, Map<BigDecimal,Set<String>>
			// mapPos2Alleleset,
			Set<Position> setSnpSpliceDonorPos, Set<Position> setSnpSpliceAcceptorPos,
			Map<String, Double> mapReference2Mismatch, Set dataset, Map<Integer, BigDecimal> map) {
		super();

		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		// this.mapIdx2Pos = mapIdx2Pos;
		this.listVariantsString = listVariantsString;

		this.strRef = strRef;
		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
		this.mapPos2NonsynAlleles = mapPos2NonsynAlleles;
		this.mapPos2SynAlleles = mapPos2SynAlleles;

		// this.setSnpInExonPos = setSnpInExonPos;
		// this.mapMergedIdx2SnpIdx = mapMergedIdx2SnpIdx;
		// this.mapPos2Alleleset = mapPos2Alleleset;

		this.setSnpSpliceAcceptorPos = setSnpSpliceAcceptorPos;
		this.setSnpSpliceDonorPos = setSnpSpliceDonorPos;
		this.mapReference2Mismatch = mapReference2Mismatch;
		this.dataset = dataset;
		this.id2SampleMapping = map;
		// param=params;

	}
	
	public VariantSnpsStringData(Map<BigDecimal, Double> mapVariety2Mismatch, Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos,
			// Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString, String strRef,

			// Map<BigDecimal, Map<Integer, Character>> mapVarid2SnpsAllele2str,
			Map<BigDecimal, Map<Position, Character>> mapVarid2SnpsAllele2str,

			Map<Position, Set<Character>> mapPos2NonsynAlleles, Map<Position, Set<Character>> mapPos2SynAlleles,
			// Set<Position> setSnpInExonPos,
			// Map<Integer, Integer> mapMergedIdx2SnpIdx, Map<BigDecimal,Set<String>>
			// mapPos2Alleleset,
			Set<Position> setSnpSpliceDonorPos, Set<Position> setSnpSpliceAcceptorPos,
			Map<String, Double> mapReference2Mismatch, Set dataset) {
		super();

		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		// this.mapIdx2Pos = mapIdx2Pos;
		this.listVariantsString = listVariantsString;

		this.strRef = strRef;
		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
		this.mapPos2NonsynAlleles = mapPos2NonsynAlleles;
		this.mapPos2SynAlleles = mapPos2SynAlleles;

		// this.setSnpInExonPos = setSnpInExonPos;
		// this.mapMergedIdx2SnpIdx = mapMergedIdx2SnpIdx;
		// this.mapPos2Alleleset = mapPos2Alleleset;

		this.setSnpSpliceAcceptorPos = setSnpSpliceAcceptorPos;
		this.setSnpSpliceDonorPos = setSnpSpliceDonorPos;
		this.mapReference2Mismatch = mapReference2Mismatch;
		this.dataset = dataset;
		
		// param=params;

	}

	public String getStrRef() {
		return strRef;
	}

	public void setStrRef(String strRef) {
		this.strRef = strRef;
	}

	public Map<BigDecimal, Map<Position, Character>> getMapVarid2SnpsAllele2str() {
		return mapVarid2SnpsAllele2str;
	}

	public void setMapVarid2SnpsAllele2str(Map<BigDecimal, Map<Position, Character>> mapVarid2SnpsAllele2str) {
		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
	}

	@Override
	public void clearVarietyData() {
		
		super.clearVarietyData();
		mapVarid2SnpsAllele2str = null;
		if (nonsynVariantSnpsStringData != null)
			nonsynVariantSnpsStringData.clearVarietyData();
	}

	public Map<Position, Set<Character>> getMapPos2NonsynAlleles() {
		return mapPos2NonsynAlleles;
	}

	/*
	 * public Map<Position, Set<Character>> getMapPos2SynAlleles() { return
	 * mapPos2SynAlleles; }
	 */

	/*
	 * public Set<Position> getSetSnpInExonPos() { return setSnpInExonPos; }
	 */

	public Set<Position> getSetSnpSpliceDonorPos() {
		return setSnpSpliceDonorPos;
	}

	public Set<Position> getSetSnpSpliceAcceptorPos() {
		return setSnpSpliceAcceptorPos;
	}

	/*
	 * @Override public Map<BigDecimal, Set<String>> getMapPos2Alleleset() {
	 * if(mapPos2Alleleset==null) { mapPos2Alleleset=new HashMap(); Set setNuc = new
	 * TreeSet(); setNuc.add("A");setNuc.add("T");setNuc.add("G");setNuc.add("C");
	 * Iterator<BigDecimal> itpos = this.mapIdx2Pos.values().iterator();
	 * while(itpos.hasNext()) { mapPos2Alleleset.put(itpos.next(), setNuc); } }
	 * return mapPos2Alleleset; }
	 */
	public VariantSnpsStringData getNonsynPlusSpliceSnps() {
		return getNonsynSpliceSnps(true, false, dataset);
	}

	/**
	 * Non-synonysmous = non-synonymous AND within_exon
	 * 
	 * @return
	 */
	public VariantSnpsStringData getNonsynSnps() {
		return getNonsynSpliceSnps(false, true, dataset);
	}

	private VariantSnpsStringData getNonsynSpliceSnps(boolean includeSpliceSites, boolean includeNoncoding,
			Set dataset) {
		// if(nonsynVariantSnpsStringData==null) {

		AppContext.debug(
				"in private VariantSnpsStringData getNonsynSpliceSnps(boolean includeSpliceSites, boolean includeNoncoding)");

		if (true) {
			if (this.listPos.size() == 0)
				new VariantStringData(); // return null;

			List snpsposlist = this.listPos;

			List listNonsynPos = new ArrayList();
			Iterator<SnpsAllvarsPos> itSnppos = snpsposlist.iterator();
			StringBuffer buffRef = new StringBuffer();

			Set setInExonAndNonsyn = new TreeSet(this.mapPos2NonsynAlleles.keySet());
			// setInExonAndNonsyn.retainAll(setSnpInExonTableIdx);
			// AppContext.debug("setInExonAndNonsyn:"+ setInExonAndNonsyn.size() + ":" +
			// setInExonAndNonsyn);

			int includeidx = 0;
			// Map<BigDecimal, Map<Integer,Character>> mapVar2Idx2Allele2=new HashMap();

			Set<Integer> setIncludeIdx = new TreeSet();
			while (itSnppos.hasNext()) {
				// VSnpRefposindex snppos = (VSnpRefposindex)itSnppos.next();
				SnpsAllvarsPos snppos = itSnppos.next();
				boolean includethis = false;
				// if(setInExonAndNonsyn.contains( snppos.getPos())) {
				if (setInExonAndNonsyn.contains(snppos)) {
					listNonsynPos.add(snppos);
					includethis = true;
				}

				if (includeSpliceSites) {
					// include if in splice SNP site
					// if(setSnpSpliceAcceptorPos.contains( snppos.getPos()) ||
					// setSnpSpliceDonorPos.contains( snppos.getPos()) )
					if (setSnpSpliceAcceptorPos.contains(snppos) || setSnpSpliceDonorPos.contains(snppos)) { // include
																												// this
						listNonsynPos.add(snppos);
						includethis = true;
					}
				}

				if (includethis) {
					buffRef.append(snppos.getRefnuc());
					setIncludeIdx.add(includeidx);
				}
				includeidx++;
			}

			String nonsynRef = buffRef.toString();

			// List listNonsynVariantsString=new ArrayList();
			Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter(dataset));

			Iterator<SnpsStringAllvars> itAllvars = listVariantsString.iterator();
			while (itAllvars.hasNext()) {
				SnpsStringAllvars varstr = itAllvars.next();
				String varnuc = varstr.getVarnuc();
				String ds = varstr.getDataset();
				if (varnuc.length() != snpsposlist.size())
					throw new RuntimeException(
							"varnuc.length()!=snpsposlist.size(): " + varnuc.length() + " ; " + snpsposlist.size());
				StringBuffer buffNuc = new StringBuffer();

				Map<Position, Character> mapPos2Allele2 = getMapVarid2SnpsAllele2str().get(varstr.getVar());
				Map<Integer, Character> mapIdx2Allele2 = null;
				int includecount = 0;
				Iterator<Integer> itInclude = setIncludeIdx.iterator();
				while (itInclude.hasNext()) {
					int includei = itInclude.next();
					buffNuc.append(varnuc.substring(includei, includei + 1));
				}

				String varNuc = buffNuc.toString();
				// List listpos, String var1, String var2, boolean var1isref,
				// Map<Integer,Character> var1allele2str, Map<Integer,Character> var2allele2str,
				// Map<Position,Set<Character>> mapPos2NonsynAlleles, Set<Position> setInExon,
				// Set<Position> setNonsynPos, boolean isNonsynOnly

				double mismatchCount[] = null;

				/*
				 * double misCount =
				 * SnpsStringHDF5nRDBMSHybridService.countVarpairMismatch(listNonsynPos,
				 * nonsynRef, varNuc, true, null, varstr.getMapPos2Allele2() ,
				 * //(Map)snpstrdata.getMapIdx2NonsynAlleles(),
				 * snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,
				 * params.isbExcludeSynonymous() ); this.getMapPos2NonsynAlleles() ,
				 * this.getSetSnpInExonPos() , varstr.getNonsynPosset(), true ); //
				 * .isbExcludeSynonymous() );
				 */

				mismatchCount = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(listNonsynPos, nonsynRef,
						varNuc, true, null, varstr.getMapPos2Allele2(),
						// (Map)snpstrdata.getMapIdx2NonsynAlleles(),
						// snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,
						// params.isbExcludeSynonymous() );
						this.getMapPos2NonsynAlleles(), varstr.getNonsynPosset(), true, false); // .isbExcludeSynonymous()
																								// );

				sortedVarieties.add(new SnpsStringAllvarsImpl(ds, varstr.getVar(), varstr.getContig(), varNuc,
						// BigDecimal.valueOf(misCount), varstr.getMapPos2Allele2(),
						// varstr.getNonsynPosset(), varstr.getDonorPosset(), varstr.getAcceptorPosset()
						// ));
						BigDecimal.valueOf(mismatchCount[0]), mapPos2Allele2, varstr.getNonsynPosset(),
						varstr.getSynPosset(), varstr.getDonorPosset(), varstr.getAcceptorPosset()));

			}

			List<SnpsStringAllvars> listResult = new ArrayList();
			// sort included varieties
			Map mapVariety2Order = new LinkedHashMap();
			Map mapVariety2Mismatch = new LinkedHashMap();
			int ordercount = 0;
			Iterator itSorVars = sortedVarieties.iterator();
			while (itSorVars.hasNext()) {
				SnpsStringAllvars snpstrvar = (SnpsStringAllvars) itSorVars.next();
				listResult.add(snpstrvar);
				mapVariety2Order.put(snpstrvar.getVar(), ordercount);
				mapVariety2Mismatch.put(snpstrvar.getVar(), snpstrvar.getMismatch().intValue());
				ordercount++;
			}

			nonsynVariantSnpsStringData = new VariantSnpsStringData(mapVariety2Mismatch, mapVariety2Order,
					listNonsynPos, listResult, buffRef.toString(), mapVarid2SnpsAllele2str, mapPos2NonsynAlleles,
					mapPos2SynAlleles,
					// setSnpInExonPos,
					setSnpSpliceDonorPos, setSnpSpliceAcceptorPos, mapReference2Mismatch, dataset);

		}

		return nonsynVariantSnpsStringData;
	}

	/**
	 * Non-synonymous = non-synonymous
	 * 
	 * @return
	 */
	public VariantSnpsStringData getNonsynSnpsPlusNoncoding() {
		return getNonsynSpliceSnps(true, true, dataset);
	}

	public Map<String, Double> getMapReference2Mismatch() {
		return mapReference2Mismatch;
	}
	
	public Map<Integer, BigDecimal> getIdxSample() {
		return id2SampleMapping;
	}

}
