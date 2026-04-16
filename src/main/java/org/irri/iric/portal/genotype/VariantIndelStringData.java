package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.ds.chado.domain.IndelsAllvarsPos;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.impl.SnpsAllvarsPosImpl;
import org.irri.iric.ds.chado.domain.object.SnpsAllvarsPosSorter;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genotype.service.IndelStringHDF5nRDBMSHybridService;

public class VariantIndelStringData extends VariantStringData {

	private Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indel;
	private VariantIndelStringData alignedVariantIndelStringData;
	private Set<Position> setPosGapRegion;
	private Set<Position> setPosDeletionRegion;
	// private Set<Integer> setGapIdx;
	private Map<Position, String> mapIndelpos2Refnuc;
	// private Map<Integer,String> mapIndelIdx2Refnuc;
	private String sequence;
	private Map<Position, Set<String>> mapPos2Alleleset;

	public VariantIndelStringData() {
		super();
		// TODO Auto-generated constructor stub
		setPosGapRegion = new TreeSet();
		setPosDeletionRegion = new TreeSet();
		listPos = new ArrayList();
		listVariantsString = new ArrayList();
		// setGapIdx = new HashSet();
		sequence = "";
	}

	@Override
	public void clearVarietyData() {
		
		super.clearVarietyData();
		if (alignedVariantIndelStringData != null)
			alignedVariantIndelStringData.clearVarietyData();
	}

	public VariantIndelStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order, List<SnpsAllvarsPos> listPos,
			// Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString, Map<Position, Set<String>> mapPos2Alleleset) {
		this();
		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		// this.mapIdx2Pos = mapIdx2Pos;
		this.listVariantsString = listVariantsString;
		this.mapPos2Alleleset = mapPos2Alleleset;
	}

	public Map<BigDecimal, IndelsAllvarsPos> getMapIndelId2Indel() {
		return mapIndelId2Indel;
	}

	public void setMapIndelId2Indel(Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indel) {
		this.mapIndelId2Indel = mapIndelId2Indel;
	}

	public VariantIndelStringData getAlignedIndels() {
		return getAlignedIndels(true);
	}

	public VariantIndelStringData getAlignedIndels(boolean alignallvars) {
		// AppContext.debug("VariantIndelStringData getAlignedIndels:
		// alignedVariantIndelStringData=" + alignedVariantIndelStringData);
		if (alignedVariantIndelStringData == null) {

			if (this.listPos.size() == 0)
				return new VariantIndelStringData();

			// String contig= listPos.get(0).getContig();
			// mapIndelIdx2Refnuc = new HashMap();
			mapIndelpos2Refnuc = new TreeMap();

			Map<BigDecimal, String> mapBdpos2Refnuc = new HashMap();

			Iterator<SnpsAllvarsPos> itPos2 = listPos.iterator();

			int firstPos = '\0';
			while (itPos2.hasNext()) {
				SnpsAllvarsPos pos2 = itPos2.next();
				if (pos2.getRefnuc() == null)
					throw new RuntimeException("refnuc==null:" + pos2.getClass().getCanonicalName() + ":  " + pos2);
				mapBdpos2Refnuc.put(pos2.getPosition(), pos2.getRefnuc());
				mapIndelpos2Refnuc.put(pos2, pos2.getRefnuc());
			}

			/*
			 * int firstPos = this.listPos.get(0).getPosition().intValue();
			 * while(itPos2.hasNext()) { SnpsAllvarsPos pos2 = itPos2.next();
			 * if(pos2.getRefnuc()==null || pos2.getRefnuc().isEmpty()) { String refnuc=
			 * sequence.substring(pos2.getPosition().intValue() - firstPos,
			 * pos2.getPosition().intValue() - firstPos +1 );
			 * AppContext.debugIterate("setting indelpos ref at " + pos2.getPosition() +
			 * " to "+ refnuc); pos2.setRefnuc( refnuc); } mapIndelpos2Refnuc.put(pos2,
			 * String.valueOf( sequence.charAt( (pos2.getPosition().intValue() - firstPos )
			 * ))); }
			 */

			// get maximum insertion length for each indel positions
			Map<Position, Integer> mapPos2Maxinsertlen = new TreeMap();
			Map<Position, Integer> mapPos2Maxdeletelen = new TreeMap();

			if (alignallvars) {
				itPos2 = listPos.iterator();
				while (itPos2.hasNext()) {
					IndelsAllvarsPos indelpos = (IndelsAllvarsPos) itPos2.next();
					mapPos2Maxinsertlen.put(indelpos, indelpos.getMaxInsLength());
					mapPos2Maxdeletelen.put(indelpos, indelpos.getMaxDellength());
				}
			} else {
				Iterator<SnpsAllvarsPos> itPos = new TreeSet(mapPos2Alleleset.keySet()).iterator();
				int idxcount = 0;
				while (itPos.hasNext()) {
					Position pos = itPos.next();

					// mapIndelIdx2Refnuc.put(idxcount, String.valueOf( sequence.charAt(
					// pos.intValue() - firstPos ) ));
					mapIndelpos2Refnuc.put(pos,
							String.valueOf(sequence.charAt(pos.getPosition().intValue() - firstPos)));
					idxcount++;

					Iterator<String> itAlleles = mapPos2Alleleset.get(pos).iterator();
					while (itAlleles.hasNext()) {
						String allele = itAlleles.next();
						// int indeltype = IndelStringService.getIndelType(allele);
						int indeltype = IndelStringHDF5nRDBMSHybridService.getIndelType(allele);
						Integer maxlen;

						// AppContext.debug("VariantIndelStringData getAlignedIndels: indeltype " +
						// pos.getClass().getName() + " " + pos.getPosition() + " " + allele + "=" +
						// indeltype);

						if (indeltype == VariantStringService.INDELTYPE_INSERTION) {
							maxlen = mapPos2Maxinsertlen.get(pos);
							if (maxlen == null) {
								mapPos2Maxinsertlen.put(pos, allele.length());
							} else {
								if (allele.length() > maxlen)
									mapPos2Maxinsertlen.put(pos, allele.length());
							}
						} else if (indeltype == VariantStringService.INDELTYPE_DELETION) {
							maxlen = mapPos2Maxdeletelen.get(pos);
							int dellen = Integer.valueOf(allele.replace("del", "").trim());

							if (dellen > 51 || dellen < 0)
								throw new RuntimeException(allele + "-> dellen=" + dellen);

							if (maxlen == null) {
								mapPos2Maxdeletelen.put(pos, dellen);
							} else {
								if (dellen > maxlen.intValue())
									mapPos2Maxdeletelen.put(pos, dellen);
							}
						} else if (indeltype == VariantStringService.INDELTYPE_EXTENDDELETION) {

							maxlen = mapPos2Maxdeletelen.get(pos);
							int dellen = Integer.valueOf(allele.replace("extdel", "").replace("-", "").trim());

							if (dellen > 51 || dellen < 0)
								throw new RuntimeException(allele + "-> dellen=" + dellen);

							// if(dellen>0 || maxlen==null) {
							if (maxlen == null) {
								mapPos2Maxdeletelen.put(pos, dellen);
							} else {
								if (dellen > maxlen.intValue())
									mapPos2Maxdeletelen.put(pos, dellen);
							}

						}

					}
				}
			}

			// AppContext.debug("mapPos2Maxinsertlen=" + mapPos2Maxinsertlen);

			setPosGapRegion = new TreeSet();
			Set setAnchorInsertPos = new TreeSet();
			// create gap positions
			// List<SnpsAllvarsPos> gapPositions = new ArrayList();
			Iterator<Position> itPos3 = mapPos2Maxinsertlen.keySet().iterator();
			while (itPos3.hasNext()) {
				Position pos = itPos3.next();
				setAnchorInsertPos.add(pos);
				Integer maxlen = mapPos2Maxinsertlen.get(pos);
				for (int i = 1; i <= maxlen; i++) {
					BigDecimal bdGappos = BigDecimal.valueOf(pos.getPosition().longValue() + i * 1.0 / 100.0);
					SnpsAllvarsPos gappos = new SnpsAllvarsPosImpl(bdGappos, VariantStringService.INDEL_GAP,
							pos.getContig());
					// setPosGapRegion.add(bdGappos );
					// gapPositions.add(gappos);
					setPosGapRegion.add(gappos);
				}
			}

			// fill refnucs
			// Iterator<SnpsAllvarsPos> itPosAllvars = this.listPos.iterator();
			// while(itPosAllvars.hasNext()) {
			// SnpsAllvarsPos posallvars = itPosAllvars.next();
			// if(posallvars.getRefnuc().isEmpty()) {
			// posallvars.setRefnuc( String.valueOf( sequence.charAt(
			// posallvars.getPos().intValue() - firstPos ) ));
			// }
			// }

			// AppContext.debug(" subseq length=" + sequence.length() + ", firstPos=" +
			// firstPos);

			setPosDeletionRegion = new TreeSet();
			Set setAnchorDeletionPos = new TreeSet();
			// create deletion reference positions
			List<SnpsAllvarsPos> refconsensusPositions = new ArrayList();
			itPos3 = mapPos2Maxdeletelen.keySet().iterator();
			while (itPos3.hasNext()) {
				Position pos = itPos3.next();
				// setAnchorDeletionPos.add(new SnpsAllvarsPosImpl( pos.getPosition(),
				// mapBdpos2Refnuc.get( pos.getPosition()).substring(0,1) , pos.getContig()));
				setAnchorDeletionPos.add(pos);

				Integer maxlen = mapPos2Maxdeletelen.get(pos);
				for (int i = 1; i <= maxlen; i++) {

					try {
						BigDecimal bddelpos = BigDecimal.valueOf(pos.getPosition().longValue() + i);
						// BigDecimal bddelpos = BigDecimal.valueOf( pos.getPosition().longValue());
						SnpsAllvarsPos refpos = new SnpsAllvarsPosImpl(bddelpos,
								mapBdpos2Refnuc.get(pos.getPosition()).substring(i, i + 1), pos.getContig());
						// SnpsAllvarsPos refpos = new SnpsAllvarsPosImpl( BigDecimal.valueOf(
						// pos.longValue() + i), IndelStringService.INDEL_REFCONSENSUS );
						refconsensusPositions.add(refpos);
						// AppContext.debug( "pos.intValue()+i=" + pos.intValue()+i);
						// setPosDeletionRegion.add(pos.longValue() + i);
						setPosDeletionRegion.add(refpos);

					} catch (Exception ex) {
						ex.printStackTrace();

						AppContext.debug("pos=" + pos + " ;maxdelen=" + maxlen);
						AppContext.debug("seqlength=" + sequence.length() + ";pos.intValue()+i - firstPos="
								+ (pos.getPosition().intValue() + i - firstPos) + "; pos.intValue()="
								+ pos.getPosition().intValue() + " ;i=" + i + "  ;firstpos=" + firstPos);

						AppContext.debug("i=" + i + "; pos.getPosition().longValue()=" + pos.getPosition()
								+ "; pos.getPosition().longValue() + i-1=" + (pos.getPosition().longValue() + i - 1)
								+ "; mapBdpos2Refnuc=" + mapBdpos2Refnuc);

						throw new RuntimeException(ex);
					}

				}

				// ((SnpsAllvarsPos)pos).setRefnuc(
				// ((SnpsAllvarsPos)pos).getRefnuc().substring(0,1) );
			}

			Set setAlignedPos = new TreeSet(new SnpsAllvarsPosSorter());

			setAlignedPos.addAll(this.listPos);
			// setAlignedPos.removeAll(setAnchorDeletionPos);
			// setAlignedPos.removeAll(setAnchorInsertPos);
			setAlignedPos.addAll(setPosGapRegion);
			setAlignedPos.addAll(setPosDeletionRegion);

			List listAlignedPos = new ArrayList();
			listAlignedPos.addAll(setAlignedPos);

			alignedVariantIndelStringData = new VariantIndelStringData(mapVariety2Mismatch, mapVariety2Order,
					listAlignedPos, listVariantsString, mapPos2Alleleset);
			alignedVariantIndelStringData.setMapIndelId2Indel(this.mapIndelId2Indel);
			alignedVariantIndelStringData.setSetPosDeletionRegion(setPosDeletionRegion);
			alignedVariantIndelStringData.setSetPosGapRegion(setPosGapRegion);
			// alignedVariantIndelStringData.setSetGapIdx(newsetIdxGap);
			// alignedVariantIndelStringData.setMapIndelIdx2Refnuc(mapIndelIdx2Refnuc);
			alignedVariantIndelStringData.setMapIndelpos2Refnuc(mapIndelpos2Refnuc);
			alignedVariantIndelStringData.setSequence(sequence);
			alignedVariantIndelStringData.setMessage(msgbox);
			// alignedVariantIndelStringData.setMapMSU7Pos2ConvertedPos(mapMSU7Pos2ConvertedPos);

			if (AppContext.isLocalhost()) {
				AppContext.debug("mapIndelpos2Refnuc=" + mapIndelpos2Refnuc.size());
				AppContext.debug("setPosGapRegion=" + setPosGapRegion.size());
				AppContext.debug("setPosDeletionRegion=" + setPosDeletionRegion.size());
			}

		}

		return alignedVariantIndelStringData;
	}

	/**
	 * Non-synonysmous = non-synonymous AND within_exon
	 * 
	 * @return
	 */
	// public VariantSnpsStringData getNonsynSnps() {
	// return getNonsynSpliceSnps(false, true, dataset);
	// }
	//
	//
	// //private VariantSnpsStringData filterNonsynonymous(boolean
	// includeSpliceSites, boolean includeNoncoding, String dataset) {
	// public VariantSnpsStringData retainWithinLoci(List listLocus) {
	// //if(nonsynVariantSnpsStringData==null) {
	//
	// AppContext.debug("retainWithinLoci");
	//
	// VariantIndelStringData alinedindels = getAlignedIndels();
	// alinedindels.getListPos();
	//
	// if(true) {
	// if(this.listPos.size()==0) new VariantStringData(); //return null;
	//
	// List snpsposlist=this.listPos;
	//
	// List listNonsynPos=new ArrayList();
	// Iterator<SnpsAllvarsPos> itSnppos =snpsposlist.iterator();
	// StringBuffer buffRef = new StringBuffer();
	//
	// //setInExonAndNonsyn.retainAll(setSnpInExonTableIdx);
	// //AppContext.debug("setInExonAndNonsyn:"+ setInExonAndNonsyn.size() + ":" +
	// setInExonAndNonsyn);
	//
	// int includeidx=0;
	// //Map<BigDecimal, Map<Integer,Character>> mapVar2Idx2Allele2=new HashMap();
	//
	// Set<Integer> setIncludeIdx=new TreeSet();
	// while(itSnppos.hasNext()) {
	// //VSnpRefposindex snppos = (VSnpRefposindex)itSnppos.next();
	// SnpsAllvarsPos snppos = itSnppos.next();
	// boolean includethis=false;
	// //if(setInExonAndNonsyn.contains( snppos.getPos())) {
	//
	// Iterator<Locus> itLoc=listLocus.iterator();
	// while(itLoc.hasNext()) {
	// Locus loc=itLoc.next();
	// if(loc.getChr()==snppos.getChr() ||
	// loc.getContig().equals(snppos.getContig())) {
	// long lpos=snppos.getPosition().longValue();
	// if(loc.getFmin()<=lpos && loc.getFmax()>=lpos) {
	// includethis=true;
	// break;
	// }
	// }
	// }
	//
	// if(includethis) {
	// buffRef.append( snppos.getRefnuc());
	// setIncludeIdx.add(includeidx);
	// }
	// includeidx++;
	// }
	//
	// String nonsynRef= buffRef.toString();
	//
	// //List listNonsynVariantsString=new ArrayList();
	// //Set sortedVarieties = new TreeSet(new
	// SnpsStringAllvarsImplSorter(dataset));
	// Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
	//
	//
	//
	// Iterator<SnpsStringAllvars> itAllvars = listVariantsString.iterator();
	// while(itAllvars.hasNext()) {
	// SnpsStringAllvars varstr = itAllvars.next();
	// String varnuc = varstr.getVarnuc();
	// if(varnuc.length()!=snpsposlist.size()) throw new
	// RuntimeException("varnuc.length()!=snpsposlist.size(): " + varnuc.length() +
	// " ; " + snpsposlist.size());
	// StringBuffer buffNuc=new StringBuffer();
	//
	// //Map<Position,Character>
	// mapPos2Allele2=getMapVarid2SnpsAllele2str().get(varstr.getVar());
	// //Map<Integer,Character> mapIdx2Allele2=null;
	// int includecount=0;
	// Iterator<Integer> itInclude = setIncludeIdx.iterator();
	// while(itInclude.hasNext()) {
	// int includei=itInclude.next();
	// buffNuc.append( varnuc.substring(includei, includei+1 ));
	// }
	//
	// String varNuc=buffNuc.toString();
	// //List listpos, String var1, String var2, boolean var1isref,
	// Map<Integer,Character> var1allele2str, Map<Integer,Character> var2allele2str,
	// //Map<Position,Set<Character>> mapPos2NonsynAlleles, Set<Position> setInExon,
	// Set<Position> setNonsynPos, boolean isNonsynOnly
	//
	//
	//
	// double mismatchCount[] = null;
	//
	// /*
	// double misCount =
	// SnpsStringHDF5nRDBMSHybridService.countVarpairMismatch(listNonsynPos,
	// nonsynRef, varNuc, true, null, varstr.getMapPos2Allele2() ,
	// //(Map)snpstrdata.getMapIdx2NonsynAlleles(),
	// snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,
	// params.isbExcludeSynonymous() );
	// this.getMapPos2NonsynAlleles() , this.getSetSnpInExonPos() ,
	// varstr.getNonsynPosset(), true ); // .isbExcludeSynonymous() );
	// */
	//
	// mismatchCount =
	// SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(listNonsynPos,
	// nonsynRef, varNuc, true, null, varstr.getMapPos2Allele2() ,
	// //(Map)snpstrdata.getMapIdx2NonsynAlleles(),
	// snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,
	// params.isbExcludeSynonymous() );
	// this.getMapPos2NonsynAlleles() , this.getSetSnpInExonPos() ,
	// varstr.getNonsynPosset(), true , false); // .isbExcludeSynonymous() );
	//
	//
	// sortedVarieties.add( new SnpsStringAllvarsImpl(varstr.getVar(),
	// varstr.getContig(), varNuc,
	// //BigDecimal.valueOf(misCount), varstr.getMapPos2Allele2(),
	// varstr.getNonsynPosset(), varstr.getDonorPosset(), varstr.getAcceptorPosset()
	// ));
	// BigDecimal.valueOf(mismatchCount[0]), mapPos2Allele2,
	// varstr.getNonsynPosset(), varstr.getDonorPosset(), varstr.getAcceptorPosset()
	// ));
	// }
	//
	//
	// List<SnpsStringAllvars> listResult = new ArrayList();
	// // sort included varieties
	// Map mapVariety2Order = new LinkedHashMap();
	// Map mapVariety2Mismatch = new LinkedHashMap();
	// int ordercount = 0;
	// Iterator itSorVars = sortedVarieties.iterator();
	// while(itSorVars.hasNext()) {
	// SnpsStringAllvars snpstrvar = (SnpsStringAllvars)itSorVars.next();
	// listResult.add( snpstrvar );
	// mapVariety2Order.put(snpstrvar.getVar() ,ordercount);
	// mapVariety2Mismatch.put( snpstrvar.getVar(),
	// snpstrvar.getMismatch().intValue());
	// ordercount++;
	// }
	//
	// nonsynVariantSnpsStringData = new VariantSnpsStringData(mapVariety2Mismatch,
	// mapVariety2Order,
	// listNonsynPos,
	// listResult,
	// buffRef.toString(),
	// mapVarid2SnpsAllele2str,
	// mapPos2NonsynAlleles,
	// setSnpInExonPos,
	// setSnpSpliceDonorPos,
	// setSnpSpliceAcceptorPos,mapReference2Mismatch,dataset);
	//
	// }
	//
	// return nonsynVariantSnpsStringData;
	// }
	//

	public Set<Position> getSetPosGapRegion() {
		return setPosGapRegion;
	}

	public void setSetPosGapRegion(Set<Position> setPosGapRegion) {
		this.setPosGapRegion = setPosGapRegion;
	}

	public Set<Position> getSetPosDeletionRegion() {
		return setPosDeletionRegion;
	}

	public void setSetPosDeletionRegion(Set<Position> setPosDeletionRegion) {
		this.setPosDeletionRegion = setPosDeletionRegion;
	}

	/**
	 * First position is the first entry in SNP Pos List
	 * 
	 * @return
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * First position is the first entry in SNP Pos List
	 * 
	 * @return
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	// public Map<Integer, String> getMapIndelIdx2Refnuc() {
	// return mapIndelIdx2Refnuc;
	// }
	//
	//
	//
	// public void setMapIndelIdx2Refnuc(Map<Integer, String> mapIndelIdx2Refnuc) {
	// this.mapIndelIdx2Refnuc = mapIndelIdx2Refnuc;
	// }
	//

	public Map<Position, String> getMapIndelpos2Refnuc() {
		return mapIndelpos2Refnuc;
	}

	public void setMapIndelpos2Refnuc(Map<Position, String> mapIndelpos2Refnuc) {
		this.mapIndelpos2Refnuc = mapIndelpos2Refnuc;
	}

	/*
	 * public Set<Integer> getSetGapIdx() { return setGapIdx; }
	 * 
	 * public void setSetGapIdx(Set<Integer> setGapIdx) { this.setGapIdx =
	 * setGapIdx; }
	 */

}
