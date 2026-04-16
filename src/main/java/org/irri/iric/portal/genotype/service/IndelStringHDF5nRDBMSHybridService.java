package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.ds.chado.dao.IndelsAllvarsPosDAO;
import org.irri.iric.ds.chado.dao.SequenceDAO;
import org.irri.iric.ds.chado.domain.GenotypeRunPlatform;
import org.irri.iric.ds.chado.domain.IndelsAllvarsPos;
import org.irri.iric.ds.chado.domain.IndelsStringAllvars;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.impl.IndelsAllvarsPosAlleleImpl;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.IndelStringDAO;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantIndelStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("IndelService")
public class IndelStringHDF5nRDBMSHybridService implements VariantStringService {

	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsdao;

	@Autowired
	@Qualifier("FeatureDAO")
	private SequenceDAO indelSequenceDAO;

	@Autowired
	@Qualifier("IndelsAllvarsPosDAO")
	private IndelsAllvarsPosDAO indelsAllvarsPosDAO;

	private static BigDecimal bdHundred = BigDecimal.valueOf(100);

	private int getOrganismId(String org) {
		listitemsdao = (ListItemsDAO) AppContext.checkBean(listitemsdao, "ListItems");
		return listitemsdao.getOrganismByName(org).getOrganismId().intValue();
	}

	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params) throws Exception {
		

		return getIndels(params, params.getColVarIds(), params.getsChr(), params.getlStart(), params.getlEnd(),
				params.getPoslist(), params.isbMismatchonly(), params.getColLoci(),
				params.getOrganism().getOrganismId().intValue());

	}

	@Override
	public List<SnpsEffect> getSnpsEffects(List positions) {
		return null;
	}

	@Override
	public List checkSNPsInChromosome(Integer organismId, String chr, Collection posset, Set type) {
		
		throw new RuntimeException("checkSNPsInChromosome not applicable for indels");
	}

	@Override
	public List<SnpsAllvarsPos> getSNPPoslist(GenotypeQueryParams params) {
		return null;
	}

	@Override
	public long countVariantString(GenotypeQueryParams params) {
		
		return -1;
	}

	@Override
	public long countSNPPoslist(GenotypeQueryParams params) {
		
		return -1;
	}

	/**
	 * get indels for the params
	 * 
	 * @param params
	 * @param limitVarIds
	 * @param chr
	 * @param start
	 * @param end
	 * @param setPositions
	 * @param mismatchOnly
	 * @param colLoci
	 * @param organismId
	 * @return
	 * @throws Exception
	 */
	private VariantStringData getIndels(GenotypeQueryParams params, Collection limitVarIds, String chr, Long start,
			Long end, Collection setPositions, boolean mismatchOnly, Collection colLoci, int organismId)
			throws Exception { // , boolean exactMismatch, int firstRow, int maxRows) {

		if (!params.getOrganism().getName().equals(AppContext.JAPONICA_NIPPONBARE)) {
			// not nipponbare coordinate. convert coordinates

			throw new RuntimeException("Indels for other reference genomes not yet available");

			// multiplerefconvertdao =
			// (MultipleReferenceConverterDAO)AppContext.checkBean(multiplerefconvertdao,
			// "MultipleReferenceConverterDAO");
			//
			// MultiReferenceLocus locusQueried = new
			// MultiReferenceLocusImpl(params.getOrganism(), params.getsChr(),
			// params.getlStart().intValue(), params.getlEnd().intValue(), 1);
			//
			// MultiReferenceLocus locusNipponbare = multiplerefconvertdao.convertLocus(
			// locusQueried , AppContext.getDefaultOrganism(), null);
			// MultiReferenceLocus origMultiReferenceLocus =
			// params.setNewPosition(locusNipponbare);
			//
			//
			// //VariantStringData variantindelsdataNPB = _getIndelsNPB( limitVarIds, chr,
			// start, end, setPositions, mismatchOnly);
			//
			// if(setPositions!=null) throw new Exception("Convert positionlits to " +
			// locusNipponbare + " coordinates");
			// VariantStringData variantindelsdataNPB = _getIndelsNPB( limitVarIds,
			// locusNipponbare.getContig(), locusNipponbare.getFmin().longValue(),
			// locusNipponbare.getFmin().longValue(), setPositions, mismatchOnly, colLoci,
			// organismId);
			//
			// String toContig = null;
			// if(params.isLimitToQueryContig()) {
			// toContig = locusQueried.getContig();
			// }
			//
			//
			// variantindelsdataNPB.setMessage( variantindelsdataNPB.getMessage() + "\nIndel
			// Query " + locusQueried + " aligned with " + locusNipponbare);
			// params.setNewPosition(origMultiReferenceLocus);
			//
			//
			// /*
			// String subseq = "";
			// try {
			// //subseq = indelSequenceDAO.getSubSequence(BigDecimal.valueOf(chr+2),
			// indelstringdao.getListPos().get(0).getPos().longValue() , end+100);
			// subseq = indelSequenceDAO.getSubSequence(chr,
			// indelstringdao.getListPos().get(0).getPos().longValue() , end+100,
			// organismId);
			// AppContext.debug( "indelsequence length=" + subseq.length() );
			// } catch(Exception ex) {
			// ex.printStackTrace();
			// }
			// */
			//
			// //VariantIndelStringData indelstringdata =
			// (VariantIndelStringData)variantindelsdataNPB;
			// return multiplerefconvertdao.convertReferencePositions(variantindelsdataNPB,
			// locusNipponbare, locusQueried, toContig, false);
			// //return
			// multiplerefconvertdao.convertReferencePositions(indelstringdata.getAlignedIndels(),
			// locusNipponbare, locusQueried, toContig);
			// //return variantstringdataNPB;

		} else {

			return _getIndelsNPB(params, limitVarIds, chr, start, end, setPositions, mismatchOnly, colLoci, organismId);
		}

	}

	/**
	 * Get indel for Nipponbare reference
	 * 
	 * @param limitVarIds
	 * @param chr
	 * @param start
	 * @param end
	 * @param setPositions
	 * @param mismatchOnly
	 * @param colLoci
	 * @param organismId
	 * @return
	 */
	private VariantStringData _getIndelsNPB(GenotypeQueryParams params, Collection limitVarIds, String chr, Long start,
			Long end, Collection setPositions, boolean mismatchOnly, Collection colLoci, Integer organismId) {

		// if(params.isbIndel()

		if (params.hasVaridRange()) {
			if (limitVarIds == null)
				limitVarIds = new TreeSet();
			Integer[] varidrange = params.getVaridStartEnd();
			for (int i = varidrange[0]; i <= varidrange[1]; i++)
				limitVarIds.add(BigDecimal.valueOf(i));
		}

		

		// indelsAllvarsPosDAO =
		// (IndelsAllvarsPosDAO)AppContext.checkBean(indelsAllvarsPosDAO,
		// "IndelsAllvarsPosDAO");
		// indelsAllvarsDAO = (IndelsAllvarsDAO)AppContext.checkBean( indelsAllvarsDAO ,
		// "IndelAllvarsDAO");
		// indelstringdao=(IndelStringNormalizedDAOImpl)AppContext.checkBean(
		// indelstringdao , "IndelStringNormalizedDAO");
		// IndelStringNormalizedDAOImpl indelstringnormalizeddao=indelstringdao;

		indelSequenceDAO = (SequenceDAO) AppContext.checkBean(indelSequenceDAO, "FeatureDAO");

		GenotypeRunPlatform run = (GenotypeRunPlatform) params.getSetRun().iterator().next();
		// indelstringdao=(IndelStringDAO)AppContext.checkBean( indelstringdao ,
		// "IndelStringNormalizedDAO");
		listitemsdao = (ListItemsDAO) AppContext.checkBean(listitemsdao, "ListItems");
		AppContext.debug("using HDF5");

		IndelStringDAO indelstringdao = new IndelStringNormalizedDAOImpl(indelsAllvarsPosDAO, run,
				listitemsdao.getMapIdx2Sample(params));
		AppContext.debug("indelstringdao=" + indelstringdao.getClass());
		IndelStringDAO indelstringnormalizeddao = indelstringdao;

		// read IndelString for varieties
		String msg = "";
		Map<BigDecimal, IndelsStringAllvars> mapVar2Indelstring = null;

		if (setPositions != null && !setPositions.isEmpty()) {
			if (limitVarIds != null && !limitVarIds.isEmpty()) {
				AppContext.resetTimer("using readIndelString3 start");
				mapVar2Indelstring = indelstringdao.readSNPString(organismId, (Set) limitVarIds, chr, setPositions);
				AppContext.resetTimer("using readIndelString3 end");
			} else {
				AppContext.resetTimer("using readIndelString4 start");
				mapVar2Indelstring = indelstringdao.readSNPString(organismId, chr, setPositions);
				AppContext.resetTimer("using readIndelString4 end");
			}
		} else if (colLoci != null && !colLoci.isEmpty()) {
			if (limitVarIds != null && !limitVarIds.isEmpty()) {
				AppContext.resetTimer("using readIndelString5 start");
				mapVar2Indelstring = indelstringdao.readSNPString(organismId, (Set) limitVarIds, chr, colLoci);
				AppContext.resetTimer("using readIndelString5 end");
			} else {
				AppContext.resetTimer("using readIndelString6 start");
				mapVar2Indelstring = indelstringdao.readSNPString(organismId, chr, colLoci);
				AppContext.resetTimer("using readIndelString6 end");
			}
		} else {
			if (limitVarIds != null && !limitVarIds.isEmpty()) {

				AppContext.resetTimer("using readIndelString1 start");
				mapVar2Indelstring = indelstringdao.readSNPString(organismId, (Set) limitVarIds, chr, start.intValue(),
						end.intValue());
				AppContext.resetTimer("using readIndelString1 end");

			} else {
				AppContext.resetTimer("using readIndelString2 start");
				mapVar2Indelstring = indelstringdao.readSNPString(organismId, chr, start.intValue(), end.intValue());
				AppContext.resetTimer("using readIndelString2 end");
			}
		}

		if (mapVar2Indelstring.size() == 0)
			return new VariantIndelStringData();

		// get reference sequence of the region
		String subseq = "";
		try {
			subseq = indelSequenceDAO.getSubSequence(chr,
					indelstringnormalizeddao.getListPos().get(0).getPosition().longValue(), end + 10000, organismId);
			AppContext.debug("indelsequence length=" + subseq.length());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// get distinct alleles for each position
		Map mapIndelId2Indel = indelstringnormalizeddao.getMapAlleleId2Indel();
		Map<Position, Set<String>> mapPos2Allele = new HashMap();
		Iterator<IndelsAllvarsPos> itIndels = mapIndelId2Indel.values().iterator();
		while (itIndels.hasNext()) {
			IndelsAllvarsPos indelpos = itIndels.next();
			Set setalleles = mapPos2Allele.get(indelpos.getPosition());
			if (setalleles == null) {
				setalleles = new TreeSet();
				mapPos2Allele.put(indelpos, setalleles);
			}
			setalleles.add(getIndelAlleleString(indelpos));
		}

		VariantIndelStringData indelstrdata = new VariantIndelStringData(
				indelstringnormalizeddao.getMapVariety2Mismatch(), indelstringnormalizeddao.getMapVariety2Order(),
				indelstringnormalizeddao.getListPos(), indelstringnormalizeddao.getListResult(), mapPos2Allele);
		indelstrdata.setMapIndelId2Indel(mapIndelId2Indel);
		indelstrdata.setSequence(subseq);
		indelstrdata.setMessage(msg);

		return indelstrdata;
	}

	/**
	 * Create descriptive format indel string
	 */

	public static String[] createVarietyString(SnpsStringAllvars snpstr, VariantStringData varstringdata) {

		// AppContext.debug("HDF5 createVarietyString: " + snpstr.getClass() + " " +
		// snpstr);

		int cols = varstringdata.getListPos().size();

		Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indels = null;
		// Map<Integer, Integer> mapMergedIdx2SnpIdx=null;
		if (varstringdata.hasIndel()) {
			mapIndelId2Indels = varstringdata.getIndelstringdata().getMapIndelId2Indel();
		}

		Map<Position, Character> mapPos2allele2 = snpstr.getMapPos2Allele2();

		// create allele for each position

		String allelesstr[] = new String[cols];
		for (int iCols = 0; iCols < cols; iCols++) {

			StringBuffer buff = new StringBuffer();
			Position pos = varstringdata.convertMergedIdx2Pos(iCols);

			/*
			 * if(pos.getPosition().intValue()==38382527 &&
			 * snpstr.getVar().intValue()==2292) AppContext.debug( snpstr.toString() );
			 */
			if (snpstr instanceof IndelsStringAllvars) {

				Integer j = iCols;
				if (pos != null) {

					IndelsStringAllvars indelstring = (IndelsStringAllvars) snpstr;

					if (indelstring.getVarnuc() != null) {

						// has SNPs

						// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if
						// merged but not in mapMergedIdx2SnpIdx add using mapMergedIdx2SnpIdx.get(j)
						j = varstringdata.convertMergedIdx2SnpIdx(iCols);
						if (j != null) {

							char element = indelstring.getVarnuc().substring(j, j + 1).charAt(0);
							if (element != '0' && element != '.' && element != ' ' && element != '*'
									&& element != '?') {

								if (mapPos2allele2 != null && mapPos2allele2.get(pos) != null) {
									char element2 = mapPos2allele2.get(pos);
									if (element2 != '0' && element2 != '.' && element2 != ' ' && element2 != '*'
											&& element2 != element) {
										SnpsAllvarsPos snppos = varstringdata.getListPos().get(iCols);
										if (snppos.getRefnuc().charAt(0) == element)
											buff.append(element).append("/").append(element2);
										else if (snppos.getRefnuc().charAt(0) == element2)
											buff.append(element2).append("/").append(element);
										else {
											if (element2 > element)
												buff.append(element).append("/").append(element2);
											else
												buff.append(element2).append("/").append(element);
										}
									} else
										buff.append(element);
								} else
									buff.append(element);
							}
						}
					}

					BigDecimal alleleid = null; // indelstring.getAllele1( pos );
					IndelsAllvarsPos indelpos = null; // apIndelId2Indels.get(alleleid);

					try {
						alleleid = indelstring.getAllele1(pos);
						indelpos = mapIndelId2Indels.get(alleleid);
					} catch (Exception ex) {
						// AppContext.debug("pos=" + pos + " alleleid=" + alleleid + " indelpos=" +
						// indelpos );
					}

					if (alleleid != null && indelpos != null) {
						buff.append(IndelStringHDF5nRDBMSHybridService.getIndelAlleleString(indelpos));
						BigDecimal alleleid2 = indelstring.getAllele2(pos);
						indelpos = mapIndelId2Indels.get(alleleid2);

						if (alleleid2 != null && !alleleid2.equals(alleleid)) {
							alleleid = alleleid2;
							indelpos = mapIndelId2Indels.get(alleleid);
							if (indelpos != null) {
								buff.append("/")
										.append(IndelStringHDF5nRDBMSHybridService.getIndelAlleleString(indelpos));
							}
						}
					}

				}

			} else {

				// SNPs only
				int j = iCols;
				// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if
				// merged but not in mapMergedIdx2SnpIdx add using mapMergedIdx2SnpIdx.get(j)

				j = varstringdata.convertMergedIdx2SnpIdx(iCols);

				char element = snpstr.getVarnuc().substring(j, j + 1).charAt(0);
				if (element != '0' && element != '.' && element != ' ' && element != '*' && element != '?') {

					if (mapPos2allele2 != null && mapPos2allele2.get(pos) != null) {
						char element2 = mapPos2allele2.get(pos);
						if (element2 != '0' && element2 != '.' && element2 != ' ' && element2 != '*'
								&& element2 != element && element2 != '?') {

							SnpsAllvarsPos snppos = varstringdata.getListPos().get(iCols);
							if (snppos.getRefnuc().charAt(0) == element)
								buff.append(element).append("/").append(element2);
							else if (snppos.getRefnuc().charAt(0) == element2)
								buff.append(element2).append("/").append(element);
							else {
								if (element2 > element)
									buff.append(element).append("/").append(element2);
								else
									buff.append(element2).append("/").append(element);
							}

						} else
							buff.append(element);
					} else
						buff.append(element);
				}
			}
			allelesstr[iCols] = buff.toString();
		}

		return allelesstr;
	}

	// public static int isfirstrow=0;

	/**
	 * Create MSA format indel string
	 * 
	 * @param snpstr
	 * @param varstringdata
	 * @return
	 */
	public static String[] createVarietyStringAligned(SnpsStringAllvars snpstr, VariantStringData varstringdata,
			String varname) {

		int cols = varstringdata.getListPos().size();
		Map<Integer, Position> mapMergedIdx2Pos = varstringdata.getMapIdx2Pos();

		Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indels = null;
		if (varstringdata.hasIndel()) {
			mapIndelId2Indels = varstringdata.getIndelstringdata().getMapIndelId2Indel();
		}

		String allele1Indelnucs = null;
		String allele2Indelnucs = null;
		int allele1Gapcount = 0;
		Integer allele2Gapcount = 0;

		List<SnpsAllvarsPos> listpos = varstringdata.getListPos();

		Map<Position, Character> mapPos2allele2 = snpstr.getMapPos2Allele2();

		// create indel string for each position
		String allelesstr[] = new String[cols];
		boolean hasIndel2Missing = false;
		boolean hasIndel1Missing = false;

		Set setIndel1MissingWithFinalAllalle = new LinkedHashSet();

		for (int iCols = 0; iCols < cols; iCols++) {

			StringBuffer buffGap = new StringBuffer();
			StringBuffer buffSNP = new StringBuffer();
			StringBuffer buffSNPOnly = new StringBuffer();
			StringBuffer buff = new StringBuffer();

			Position pos = mapMergedIdx2Pos.get(iCols);

			/*
			 * if(isfirstrow==0) { AppContext.debug("POS: " + pos);
			 * AppContext.debug("getSetPosDeletionRegion=" +
			 * varstringdata.getIndelstringdata().getSetPosDeletionRegion());
			 * AppContext.debug("getSetPosGapRegion=" +
			 * varstringdata.getIndelstringdata().getSetPosGapRegion()); }
			 */

			if (snpstr instanceof IndelsStringAllvars) {

				// InsdelString
				Integer j = iCols;
				if (pos != null) {

					int insertOffset = pos.getPosition().remainder(BigDecimal.ONE).multiply(bd100).intValue();

					if (varstringdata == null)
						throw new RuntimeException("varstringdata");
					if (varstringdata.getIndelstringdata() == null)
						throw new RuntimeException(" varstringdata.getIndelstringdata()");
					if (varstringdata.getIndelstringdata().getSetPosGapRegion() == null)
						throw new RuntimeException("varstringdata.getIndelstringdata().getSetPosGapRegion()");
					if (pos == null)
						throw new RuntimeException("pos");

					if (!hasIndel1Missing && varstringdata.getIndelstringdata().getSetPosGapRegion().contains(pos)) {

						// insertion region
						String alleles1 = "";
						if (allele1Indelnucs != null && allele1Indelnucs.length() >= insertOffset) {
							// buff.append( allele1Indelnucs.charAt(insertOffset-1));
							alleles1 = String.valueOf(allele1Indelnucs.charAt(insertOffset - 1));
							// AppContext.debug("i"+ allele1Indelnucs.charAt(insertOffset-1) + "(" + pos
							// +")");
						} else {
							allele1Indelnucs = null;
							// buff.append(INDEL_GAP);
							alleles1 = INDEL_GAP;
							// AppContext.debug("i-"+ "(" + pos +")");
						}

						if (allele2Indelnucs != null && allele2Indelnucs.length() >= insertOffset) {
							String alleles2 = String.valueOf(allele2Indelnucs.charAt(insertOffset - 1));
							// buff.append("/").append( allele2Indelnucs.charAt(insertOffset-1));
							if (alleles1.equals(alleles2)) {
								buffGap.append(alleles1);
							} else {
								SnpsAllvarsPos snppos = varstringdata.getListPos().get(iCols);
								if (snppos.getRefnuc().equals(alleles1))
									buffGap.append(alleles1).append("/").append(alleles2);
								else if (snppos.getRefnuc().equals(alleles2))
									buffGap.append(alleles2).append("/").append(alleles1);
								else {
									if (alleles2.compareTo(alleles1) > 0)
										buffGap.append(alleles1).append("/").append(alleles2);
									else
										buffGap.append(alleles2).append("/").append(alleles1);
								}
							}
						} else {
							allele2Indelnucs = null;
							buffGap.append(alleles1);
						}

					} else if (!hasIndel1Missing
							&& varstringdata.getIndelstringdata().getSetPosDeletionRegion().contains(pos)) {
						// deletion region
						String alleles1 = "";
						if (allele1Gapcount > 0) {
							// buff.append(INDEL_GAP);
							alleles1 = INDEL_GAP;
							allele1Gapcount--;

							// AppContext.debug("d-"+ "(" + pos +")");

						} // else buff.append(INDEL_REFCONSENSUS);
						else {
							// buff.append(listpos.get(j).getRefnuc());

							// alleles1=listpos.get(j).getRefnuc();
							alleles1 = listpos.get(j).getRefnuc().substring(0, 1); // lmansueto
							// AppContext.debug("snpstr instanceof IndelsStringAllvars: d" +
							// listpos.get(j).getRefnuc() + "(" + pos.getPosition() +") allele1=" + alleles1
							// +";refnuc=" +listpos.get(j).getRefnuc());

						}

						if (allele2Gapcount != null) {

							if (allele2Gapcount > 0) {

								if (!alleles1.equals(INDEL_GAP)) {
									SnpsAllvarsPos snppos = varstringdata.getListPos().get(iCols);
									if (snppos.getRefnuc().substring(0, 1).equals(alleles1))
										buffGap.append(alleles1).append("/").append(INDEL_GAP);
									else if (snppos.getRefnuc().substring(0, 1).equals(INDEL_GAP))
										buffGap.append(INDEL_GAP).append("/").append(alleles1);
									else {
										if (alleles1.compareTo(INDEL_GAP) > 0)
											buffGap.append(INDEL_GAP).append("/").append(alleles1);
										else
											buffGap.append(alleles1).append("/").append(INDEL_GAP);
									}

								} else
									buffGap.append(alleles1);

								/*
								 * //buff.append("/").append(INDEL_GAP); if(!alleles1.equals(INDEL_GAP)) {
								 * SnpsAllvarsPos snppos = varstringdata.getListPos().get(iCols);
								 * if(snppos.getRefnuc().equals(alleles1) )
								 * buffGap.append(alleles1).append("/").append(INDEL_GAP); else
								 * if(snppos.getRefnuc().equals(INDEL_GAP) )
								 * buffGap.append(INDEL_GAP).append("/").append(alleles1); else {
								 * if(alleles1.compareTo(INDEL_GAP)>0)
								 * buffGap.append(INDEL_GAP).append("/").append(alleles1); else
								 * buffGap.append(alleles1).append("/").append(INDEL_GAP); }
								 * 
								 * } else buffGap.append(alleles1);
								 */

								allele2Gapcount--;
							} else if (allele2Gapcount == 0) {
								allele2Gapcount = null;
								buffGap.append(alleles1);
							}

						} else {
							buffGap.append(alleles1);
						}
					}

					if (true) {

						IndelsStringAllvars indelstring = (IndelsStringAllvars) snpstr;

						buffSNP = new StringBuffer();

						String snpAllele1 = "";
						String snpAllele2 = "";
						String indelAllele1 = "";
						String indelAllele2 = "";

						char element = '\0';
						char element2 = '\0';

						String snpElements = "";

						String indelElements = "";
						boolean hasSnp1Missing = false;
						boolean hasSnp2Missing = false;

						if (indelstring.getVarnuc() != null) {

							// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if
							// merged but not in mapMergedIdx2SnpIdx add using mapMergedIdx2SnpIdx.get(j)
							Integer objj = varstringdata.convertMergedIdx2SnpIdx(iCols);

							if (objj != null) { // both in snp and indel
								j = objj;
								element = indelstring.getVarnuc().substring(j, j + 1).charAt(0);
								if (element != '0' && element != '.' && element != ' ' && element != '*'
										&& element != '?') {
									snpAllele1 = String.valueOf(element);
								} else {
									hasSnp1Missing = true;
								}
								snpElements += "1=" + element + ";";

								// if(pos.getPosition().intValue()==38382527 &&
								// snpstr.getVar().intValue()==2292)
								// AppContext.debug("allele1= " + element + " mapPos2allele2=" +
								// mapPos2allele2);

								if (mapPos2allele2 != null && mapPos2allele2.get(pos) != null) {

									element2 = mapPos2allele2.get(pos);
									if (element2 != '0' && element2 != '.' && element2 != ' ' && element2 != '*'
											&& element2 != '?') {
										snpAllele2 = String.valueOf(element2);
									} else {
										hasSnp2Missing = true;
									}
									snpElements += "2=" + element2 + ";";
								}

								if (!snpAllele1.isEmpty() && !snpAllele2.isEmpty() && !snpAllele1.equals(snpAllele2)) {

									SnpsAllvarsPos snppos = varstringdata.getListPos().get(iCols);
									if (snppos.getRefnuc().equals(snpAllele1))
										buffSNP.append(snpAllele1).append("/").append(snpAllele2);
									else if (snppos.getRefnuc().equals(snpAllele2))
										buffSNP.append(snpAllele2).append("/").append(snpAllele1);
									else {
										if (snpAllele1.compareTo(snpAllele2) > 0)
											buffSNP.append(snpAllele2).append("/").append(snpAllele1);
										else
											buffSNP.append(snpAllele1).append("/").append(snpAllele2);
									}

								} else if (!snpAllele1.isEmpty()) {
									buffSNP.append(snpAllele1);
								} else if (!snpAllele2.isEmpty()) {
									buffSNP.append(snpAllele2);
								}
							}

						}

						StringBuffer buffIndel = new StringBuffer();
						// if(buffSNP.length()>=0) {
						if (buffSNP.length() >= 0) { // lmansueto

							// snp/insertion/deletion anchor point
							// hasIndel1Missing=false;
							// hasIndel2Missing=false;
							BigDecimal alleleid = null; // indelstring.getAllele1( pos );
							IndelsAllvarsPos indelpos = null; // mapIndelId2Indels.get(alleleid);

							try {
								alleleid = indelstring.getAllele1(pos);
								indelpos = mapIndelId2Indels.get(alleleid);
							} catch (Exception ex) {
								// ex.printStackTrace();
							}

							String alleles1 = "";
							String alleles1Aligned = "";

							if (alleleid != null && indelpos != null) {
								int indeltype = IndelStringHDF5nRDBMSHybridService.getIndelType(indelpos);

								// if(indelpos.getPosition().equals(BigDecimal.valueOf(69957)))
								// AppContext.debug( "indelpos=" + indelpos + "; indeltype=" + indeltype );

								allele1Indelnucs = null;
								allele1Gapcount = 0;
								hasIndel1Missing = false;
								switch (indeltype) {
								case INDELTYPE_INSERTION:
									allele1Indelnucs = indelpos.getInsString();
									// alleles1 =
									// varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
									// alleles1 =
									// varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos).substring(0,1);
									// //lmansueto
									alleles1 = allele1Indelnucs.substring(0, 1);

									// if(isfirstrow==0) AppContext.debug("insertion at " + pos + "; allele1=" +
									// alleles1);
									if (alleles1 == null) {
										AppContext.debug("INDELTYPE_INSERTION cant find " + pos + " IN "
												+ varstringdata.getIndelstringdata().getMapIndelpos2Refnuc());
										throw new RuntimeException("cant find " + pos);
									}
									break;
								case INDELTYPE_SNP:
									alleles1 = indelpos.getInsString();
									alleles1Aligned = alleles1;
									break;

								case INDELTYPE_DELETION:
									allele1Gapcount = Integer.valueOf(indelpos.getInsString());
									// alleles1 =
									// varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
									alleles1 = varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos)
											.substring(0, 1); // lmansueto

									// if(isfirstrow==0) AppContext.debug("deletion at " + pos + "; allele1=" +
									// alleles1);

									if (alleles1 == null) {
										AppContext.debug("INDELTYPE_DELETION cant find " + pos + " IN "
												+ varstringdata.getIndelstringdata().getMapIndelpos2Refnuc());
										throw new RuntimeException("cant find " + pos);
									}
									break;
								case INDELTYPE_EXTENDDELETION:
									allele1Gapcount = Integer.valueOf(indelpos.getInsString().replace("-", ""));
									alleles1 = INDEL_GAP;
									alleles1Aligned = alleles1;
									break;
								case INDELTYPE_REFERENCE:
									alleles1 = varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos)
											.substring(0, 1); // lmansueto;

									if (alleles1 == null) {
										AppContext.debug("INDELTYPE_REFERENCE cant find " + pos + " IN "
												+ varstringdata.getIndelstringdata().getMapIndelpos2Refnuc());
										throw new RuntimeException("cant find " + pos);
									}
									break;
								case INDELTYPE_MISSING:
									alleles1 = "";
									hasIndel1Missing = true;
									// AppContext.debug("allele1 INDELTYPE_MISSING for pos" + pos);
									break;
								}

								indelElements += "1=" + indelpos.getInsString() + ";";
								if (alleles1 == null)
									throw new RuntimeException("alleles1=null,  indeltype=" + indeltype
											+ " indelpos.getInsString()=" + indelpos.getInsString());
								indelAllele1 = alleles1;
								allele2Gapcount = null;
								BigDecimal alleleid2 = indelstring.getAllele2(pos);
								indelpos = mapIndelId2Indels.get(alleleid2);
								String alleles2 = "";
								if (alleleid2 != null && !alleleid2.equals(alleleid)) {
									alleleid = alleleid2;
									indelpos = mapIndelId2Indels.get(alleleid);
									if (indelpos != null) {

										int indeltype2 = IndelStringHDF5nRDBMSHybridService.getIndelType(indelpos);

										hasIndel2Missing = false;
										switch (indeltype2) {
										case INDELTYPE_INSERTION:
											allele2Indelnucs = indelpos.getInsString();
											alleles2 = indelpos.getRefnuc().substring(0, 1);
											break;
										case INDELTYPE_SNP:
											alleles2 = indelpos.getInsString();
											if (alleles2.compareTo(alleles1Aligned) > 0)
												buffIndel.append(alleles1Aligned).append("/").append(alleles2);
											else
												buffIndel.append(alleles2).append("/").append(alleles1Aligned);
											break;
										case INDELTYPE_DELETION:
											allele2Gapcount = indelpos.getMaxDellength();
											alleles2 = indelpos.getRefnuc().substring(0, 1);
											break;
										case INDELTYPE_EXTENDDELETION:
											allele2Gapcount = Integer.valueOf(indelpos.getInsString().replace("-", ""));
											buffIndel.append(alleles1Aligned).append('/').append(INDEL_GAP);
											alleles2 = INDEL_GAP;
											break;
										case INDELTYPE_REFERENCE:
											alleles2 = indelpos.getRefnuc().substring(0, 1);
											break;

										case INDELTYPE_MISSING:
											alleles2 = "";
											hasIndel2Missing = true;
											// AppContext.debug("allele2 INDELTYPE_MISSING for pos" + pos);
											break;
										}
										indelElements += "2=" + indelpos.getInsString() + ";";

										if (alleles2 == null)
											throw new RuntimeException("alleles2=null,  indeltype=" + indeltype
													+ " indelpos.getInsString()=" + indelpos.getInsString());
										indelAllele2 = alleles2;
									}
								} else {
									// hasIndel2Missing=true;
									buffIndel.append(alleles1Aligned);

								}

								if (indeltype == INDELTYPE_INSERTION && (indelAllele1.isEmpty()))
									AppContext.debug("indeltype==INDELTYPE_INSERTION &&  indelAllele12.isEmpty() at "
											+ indelpos + "; indelAllele1=" + indelAllele1 + "; alleles1=" + alleles1
											+ "; indelAllele2" + indelAllele2 + ";  alleles2=" + alleles2);
								if (indeltype == INDELTYPE_DELETION && (indelAllele1.isEmpty()))
									AppContext.debug("indeltype==INDELTYPE_DELETION &&  indelAllele12.isEmpty() at "
											+ indelpos + "; indelAllele1=" + indelAllele1 + "; alleles1=" + alleles1
											+ "; indelAllele2" + indelAllele2 + ";  alleles2=" + alleles2);

							} else {

								// no indel at this pos

								// AppContext.debug("no indel at " + pos);
								// AppContext.debug("at pos=" + pos + "; alleleid=" + alleleid + "; indelpos=" +
								// indelpos + "; indelstring.getAllele1( pos )=" + indelstring.getAllele1( pos
								// ));
								// indelpos = mapIndelId2Indels.get(alleleid);

								// alleleid!=null && indelpos!=null

								// hasIndel1Missing=true;
								// hasIndel2Missing=true;
							}

						}

						if (buffSNP.length() > 0) {
							// if has SNP allele, use SNPs
							buff.append(buffSNP.toString().trim());
						} else if (buffGap.length() > 0) {
							// if has gap use gap
							buff.append(buffGap.toString().trim());
						} else {
							// use indel

							// if (type_id )indelAllele1

							String al1 = "";
							if (!snpAllele1.isEmpty())
								al1 = snpAllele1;
							else
								al1 = indelAllele1;
							String al2 = "";
							if (!snpAllele2.isEmpty())
								al2 = snpAllele2;
							else
								al2 = indelAllele2;

							if (al1 == null)
								throw new RuntimeException("al1==null");
							if (al2 == null)
								throw new RuntimeException("al2==null");
							String all12 = "";
							if (al1.compareTo(al2) < 0)
								all12 = al1 + "/" + al2;
							else if (al1.compareTo(al2) > 0)
								all12 = al2 + "/" + al1;
							else
								all12 = al1;

							all12 = all12.trim();

							if ((all12.length() > 0 && all12.charAt(0) == '/') || all12.endsWith("/")) {
								// throw new RuntimeException( "al1="+ al1 + ";al2="+ al2);
								all12 = all12.replace("/", "").trim();
							}

							if (all12.length() > 0) {
								buff.append(all12);
							}
						}

					}
				} else {
					throw new RuntimeException("pos==null: Impossible!");
				}

			} else {

				// SNP String only

				Integer j = iCols;
				buffSNPOnly = new StringBuffer();

				if (pos != null) {
					int insertOffset = pos.getPosition().remainder(BigDecimal.ONE).multiply(bd100).intValue();
					if (insertOffset > 0) {
						buffSNPOnly.append(INDEL_GAP);
					} else if (listpos.get(iCols).getRefnuc().equals(INDEL_REFCONSENSUS)) {
						buffSNPOnly.append(INDEL_REFCONSENSUS);
					} else {
						// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if
						// merged but not in mapMergedIdx2SnpIdx add using mapMergedIdx2SnpIdx.get(j)

						j = varstringdata.convertMergedIdx2SnpIdx(j);

						if (j != null) {
							char element = snpstr.getVarnuc().substring(j, j + 1).charAt(0);
							String element1 = "";
							if (element != '0' && element != '.' && element != ' ' && element != '*'
									&& element != '?') {
								element1 = String.valueOf(element);
								if (mapPos2allele2 != null && mapPos2allele2.get(pos) != null) {
									char element2 = mapPos2allele2.get(pos);

									SnpsAllvarsPos snppos = varstringdata.getListPos().get(iCols);
									if (snppos.getRefnuc().charAt(0) == element)
										buffSNPOnly.append(element).append("/").append(element2);
									else if (snppos.getRefnuc().charAt(0) == element2)
										buffSNPOnly.append(element2).append("/").append(element);
									else {

										if (element2 > element)
											buffSNPOnly.append(element).append("/").append(element2);
										else
											buffSNPOnly.append(element2).append("/").append(element);
									}
								} else
									buffSNPOnly.append(element1);
							}
						}
					}
				} else
					throw new RuntimeException("pos==null: Imposible!");
				buff.append(buffSNPOnly);
			}

			String finalallele = buff.toString().trim();

			if (hasIndel1Missing && finalallele.length() > 0) {
				// AppContext.debug("hasIndel1Missing=true but finalallele=" + finalallele + ";
				// buffSNP=" + buffSNP + "; buffSNPOnly=" + buffSNPOnly + "; buffGap=" + buffGap
				// + "; at pos=" + pos);
				setIndel1MissingWithFinalAllalle.add(pos.getPosition());
			}

			if (!hasIndel1Missing && finalallele.length() == 0) {
				VariantIndelStringData indelstrdata = varstringdata.getIndelstringdata();
				if (indelstrdata != null && indelstrdata.getSetPosGapRegion().contains(pos)) {
					finalallele = pos.getRefnuc();
					AppContext.debugIterate(
							"var=" + snpstr.getVar() + " gap at pos=" + pos.getPosition() + " buff=" + buff);
				}
				if (indelstrdata != null && indelstrdata.getSetPosDeletionRegion().contains(pos)) {
					finalallele = pos.getRefnuc().substring(0, 1); // lmansueto
					AppContext.debugIterate(
							"var=" + snpstr.getVar() + " deletion at pos=" + pos.getPosition() + " buff=" + buff);
				}
			}

			allelesstr[iCols] = finalallele;
		}

		if (setIndel1MissingWithFinalAllalle.size() > 0)
			AppContext.debug("hasIndel1Missing=true but finalallele.length>0 at: " + setIndel1MissingWithFinalAllalle
					+ ", var=" + varname);

		// isfirstrow++;
		return allelesstr;
	}

	/**
	 * Get descriptive allele for indel
	 * 
	 * @param ip
	 * @return
	 */
	public static String getIndelAlleleString(IndelsAllvarsPos ip) {

		IndelsAllvarsPosAlleleImpl indelpos = (IndelsAllvarsPosAlleleImpl) ip;

		if (indelpos.getInsString().length() > 0 && indelpos.getInsString().charAt(0) == '-') {
			return "extdel " + indelpos.getInsString().replace("-", "").trim();
		}

		try {
			Integer.valueOf(indelpos.getInsString());
			return "del " + indelpos.getInsString();
		} catch (Exception ex) {
			if (indelpos.getInsString().equals("."))
				return "ref";
			else if (indelpos.getInsString().equals("?"))
				return "?";
			else
				return indelpos.getInsString();
		}
	}

	/**
	 * Get type of Indel
	 * 
	 * @param allele
	 * @return
	 */
	public static int getIndelType(String allele) {

		int ret = -1;
		if (allele.equals(".") || allele.equals("ref"))
			ret = INDELTYPE_REFERENCE;
		else if (allele.equals("?") || allele.equals(" ") || allele.isEmpty())
			ret = INDELTYPE_MISSING;
		else if (allele.contains("->"))
			ret = INDELTYPE_SUBSTITUTION;
		else if (allele.startsWith("del")) {
			ret = INDELTYPE_DELETION;
		} else if (allele.startsWith("extdel") || (allele.length() > 0 && allele.charAt(0) == '-')) {
			ret = INDELTYPE_EXTENDDELETION;
		} else {
			try {
				Integer.valueOf(allele);
				ret = INDELTYPE_DELETION;
			} catch (Exception ex) {
				ret = INDELTYPE_INSERTION;
			}
		}
		return ret;
	}

	public static int getIndelType(IndelsAllvarsPos ip) {

		IndelsAllvarsPosAlleleImpl indelpos = (IndelsAllvarsPosAlleleImpl) ip;
		int ret = -1;

		return getIndelType(indelpos.getInsString());
	}

}
