package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Future;

import org.irri.iric.ds.chado.dao.LocusDAO;
import org.irri.iric.ds.chado.domain.Gene;
import org.irri.iric.ds.chado.domain.GenotypeRunPlatform;
import org.irri.iric.ds.chado.domain.Organism;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImplAllelePvalue;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantIndelStringData;
import org.irri.iric.portal.genotype.VariantSnpsStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantStringService;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.VarietiesGenotypeService;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("VarietiesGenotypeService")
public class VarietiesGenotypeSNPsIndelServiceImpl implements VarietiesGenotypeService {

	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsdao;

	@Autowired
	@Qualifier("LocusNotesDAO")
	private LocusDAO locusDAO;

	@Autowired
	@Qualifier("SnpsStringService")
	VariantStringService allsnpsService;

	@Autowired
	@Qualifier("SnpsStringNPBService")
	VariantStringService allnpbsnpsService;

	@Autowired
	@Qualifier("IndelService")
	// @Qualifier("IndelServiceOracle")
	VariantStringService indelsService;

	private VariantStringService getAllSNPService() {
		return (VariantStringService) AppContext.checkBean(allsnpsService, "SnpsStringService");
	}

	private VariantStringService getAllNPBSNPService() {
		return (VariantStringService) AppContext.checkBean(allnpbsnpsService, "SnpsStringNPBService");
	}

	private VariantStringService getIndelsService() {
		return (VariantStringService) AppContext.checkBean(indelsService, "IndelService");

	}

	@Override
	public List<SnpsAllvarsPos> getSNPPoslist(GenotypeQueryParams params) {

		allnpbsnpsService = (VariantStringService) AppContext.checkBean(allnpbsnpsService, "SnpsStringNPBService");
		return allnpbsnpsService.getSNPPoslist(params);
	}

	@Override
	public long countSNPPoslist(GenotypeQueryParams params) {
		
		allnpbsnpsService = (VariantStringService) AppContext.checkBean(allnpbsnpsService, "SnpsStringNPBService");
		return allnpbsnpsService.countSNPPoslist(params);
	}

	@Override
	public long countVariantStringData(GenotypeQueryParams params) {
		
		return -1;
	}

	@Override
	public List<SnpsEffect> getSnpEffects(List poslist) {
		

		return getAllNPBSNPService().getSnpsEffects(poslist);
	}

	/**
	 * Reformat query results to table view
	 * 
	 */
	@Override
	public VariantTable fillVariantTable(VariantTable table, VariantStringData origdata, GenotypeQueryParams params)
			throws Exception {

		VariantStringData variantMerged = new VariantStringData();

		if (origdata.hasSnp()) {
			// if(params.isbExcludeSynonymous())
			if (params.isbNonsynSnps())
				variantMerged.setSnpstringdata(origdata.getSnpstringdata().getNonsynSnps());
			else if (params.isbNonsynPlusSpliceSnps())
				variantMerged.setSnpstringdata(origdata.getSnpstringdata().getNonsynPlusSpliceSnps());
			else
				variantMerged.setSnpstringdata(origdata.getSnpstringdata());

			try {
				AppContext.debug("SNP fillVariantTable:" + variantMerged.getSnpstringdata().getListPos().size()
						+ " SNP positions");
			} catch (Exception ex) {
				ex.printStackTrace();
				AppContext.debug((variantMerged == null ? "null"
						: (variantMerged.getSnpstringdata() == null ? "getSnpstringdata()==null"
								: (variantMerged.getSnpstringdata().getListPos() == null ? "getListPos()==null"
										: variantMerged.getSnpstringdata().getListPos().size() + " SNPs positions"))));

			}
		}

		if (origdata.hasIndel()) {
			if (params.isbAlignIndels()) {
				VariantIndelStringData indeldata = origdata.getIndelstringdata().getAlignedIndels();
				indeldata.setMapMSU7Pos2ConvertedPos(origdata.getIndelstringdata().getMapMSU7Pos2ConvertedPos(),
						origdata.getIndelstringdata().getNpbContig());
				indeldata.setMessage(origdata.getIndelstringdata().getMessage());
				variantMerged.setIndelstringdata(indeldata); // .retainWithinLoci(listCDS));

				// variantMerged.setMapMSU7Pos2ConvertedPos(origdata.getIndelstringdata().getMapMSU7Pos2ConvertedPos());
				try {
					AppContext.debug("origdata.getIndelstringdata().getMapMSU7Pos2ConvertedPos()="
							+ origdata.getIndelstringdata().getMapMSU7Pos2ConvertedPos());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				variantMerged.setIndelstringdata(origdata.getIndelstringdata());
			}
			try {
				AppContext.debug("Indel fillVariantTable:" + variantMerged.getIndelstringdata().getListPos().size()
						+ " Indel positions");
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		// variantMerged.setMapMSU7Pos2ConvertedPos(origdata.getMapMSU7Pos2ConvertedPos());
		variantMerged.merge(params.isbDownloadOnly(), params.getDataset());

		// AppContext.debug("fillVariantTable
		// variantMerged.getMapMSU7Pos2ConvertedPos()=" +
		// variantMerged.getMapMSU7Pos2ConvertedPos().size() + ": " +
		// variantMerged.getMapMSU7Pos2ConvertedPos() );
		// AppContext.debug("fillVariantTable variantMerged.getListPos():" +
		// variantMerged.getListPos().size() + ": "+ variantMerged.getListPos() );

		if (params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) {

			// origdata.getIndelstringdata().getCDSIndels(listCDS); //
			// .getSnpstringdata().getNonsynSnps()

			List listCDS = new ArrayList();
			locusDAO = (LocusDAO) AppContext.checkBean(locusDAO, "LocusNotesDAO");
			if (params.hasChrPosRange()) {
				listCDS = locusDAO.getLocusByRegion(params.getsChr(), params.getlStart(), params.getlEnd(),
						params.getOrganismName(), GenomicsFacade.GENEMODEL_MSU7, GenomicsFacade.FEATURETYPE_CDS);
			} else if (params.hasSnpList()) {
				listCDS = locusDAO.getLocusByContigPositions(params.getsChr(), params.getPoslist(),
						params.getOrganismName(), 0, GenomicsFacade.GENEMODEL_MSU7, GenomicsFacade.FEATURETYPE_CDS);
			}
			if (listCDS.size() == 0)
				AppContext.debug("No " + GenomicsFacade.FEATURETYPE_CDS + " in region " + params.getsChr() + " "
						+ params.getlStart() + " " + params.getlEnd() + " for " + GenomicsFacade.GENEMODEL_MSU7);
			table.setVariantStringData(variantMerged, params, listCDS);
		} else
			table.setVariantStringData(variantMerged, params);

		// AppContext.debug("fillVariantTable:
		// table.getVariantStringData().getMapMSU7Pos2ConvertedPos()=" +
		// table.getVariantStringData().getMapMSU7Pos2ConvertedPos() );

		table.setMessage(variantMerged.getMessage());
		// table.setMessage(origdata.getMessage());

		AppContext.logSystemStatus();
		return table;
	}

	@Override
	public VariantStringData compare2VariantStrings(BigDecimal var1, BigDecimal var2, GenotypeQueryParams params)
			throws Exception {
		// Set colVarIds = new HashSet();
		Set colVarIds = new LinkedHashSet();
		colVarIds.add(var1);
		colVarIds.add(var2);
		params.setColVarIds(colVarIds);
		params.setsSubpopulation(null);

		// params.setbMismatchonly(false);

		return queryVariantStringData(params);
	}

	@Override
	public List checkSNPsInChromosome(Integer organismId, String chr, Collection posset, Set variantset) {
		
		return getAllNPBSNPService().checkSNPsInChromosome(organismId,chr, posset, variantset);
	}

	@Override
	public VariantStringData queryVariantStringData(GenotypeQueryParams params) throws Exception {
		

		// AppContext.debug("Querying " + params);
		// System.out.print(params);
		AppContext.logQuery(params.toString());

		listitemsdao = (ListItemsDAO) AppContext.checkBean(listitemsdao, "ListItems");

		if (params.isVarAlleleFilter()) {

			BigDecimal filtervar = params.getVarAlleleFilter();
			Collection colVarIds = params.getColVarIds();
			params.setVarAlleleFilter(null);
			Set setVar = new HashSet();
			setVar.add(filtervar);
			params.setColVarIds(setVar);

			VariantStringData filtervardata = null;
			if (params.getDataset().size() > 1) {
				// String sets[]=params.getDataset().split("\\+");
				Set origdataset = params.getDataset();
				Iterator<String> itds = params.getDataset().iterator();
				while (itds.hasNext()) {
					String ds = itds.next();
					if (listitemsdao.getMapId2Variety(ds).containsKey(filtervar)) {
						params.setDataset(ds);
						filtervardata = queryVariantStringData(params);
						params.setDataset(origdataset);
						break;
					}
				}
			} else
				filtervardata = queryVariantStringData(params);

			SnpsStringAllvars snpstring = filtervardata.getListVariantsString().get(0);

			List listpos = filtervardata.getListPos();

			String varnuc = snpstring.getVarnuc();
			Map<Position, Character> pos2allele2 = snpstring.getMapPos2Allele2();
			Set setPos = new TreeSet();

			for (int ipos = 0; ipos < varnuc.length(); ipos++) {
				SnpsAllvarsPos pos = (SnpsAllvarsPos) listpos.get(ipos);
				String alelle = varnuc.substring(ipos, ipos + 1);
				if (pos2allele2 != null) {
					Character al2 = pos2allele2.get(pos);
					if (al2 != null)
						alelle += "/" + al2;
				}
				setPos.add(new MultiReferencePositionImplAllelePvalue(params.getOrganismName(), pos.getContig(),
						pos.getPosition(), alelle, null));
			}

			params.setPoslist("any", setPos);
			params.setbAlleleFilter(true);
			params.setColVarIds(null);

		}

		Collection colVarIds = params.getColVarIds();
		String sChr = params.getsChr();
		Long lStart = params.getlStart();
		Long lEnd = params.getlEnd();
		// boolean bSNP = params.isbSNP();
		// boolean bIndel = params.isbIndel();
		// boolean bCoreonly = params.isbCoreonly();
		// boolean bMismatchonly = params.isbMismatchonly();
		Collection poslist = params.getPoslist();
		Collection locuslist = params.getColLoci();
		String sSubpopulation = params.getsSubpopulation();
		String sLocus = params.getsLocus();

		boolean bAllvars = colVarIds == null;

		String genename = "";
		if (sLocus != null)
			genename = sLocus.toUpperCase();
		String msgbox = "";

		// validate parameters.. duplicated for the Webapp, but useful for the Web
		// Service
		if (poslist == null && locuslist == null) {

			if (!genename.isEmpty()) {
				Gene gene2 = listitemsdao.findGeneFromName(genename, params.getOrganismName());
				lStart = Long.valueOf(gene2.getFmin());
				lEnd = Long.valueOf(gene2.getFmax());
				// selectChr.setSelectedIndex( gene2.getChr()-1 );
				// sChr =
				// sChr = gene2.getChr().toString();
				sChr = gene2.getContig();

				msgbox = "SEARCHING: in locus " + genename;

				params.setlStart(lStart);
				params.setlEnd(lEnd);
				params.setsChr(sChr);
			} else
				msgbox = "SEARCHING: in chromosome " + sChr + " [" + lStart + "-" + lEnd + "]";

			int chrlen = listitemsdao.getFeatureLength(sChr, params.getOrganismName()).intValue();
			
			
			if (lEnd > chrlen || lStart > chrlen) {
				throw new Exception("Positions should be less than length, lEnd=" + lEnd + ", lStart=" + lStart
						+ ",chrlen=" + chrlen);
			}
			if (lEnd < 1 || lStart < 1) {
				throw new Exception("Positions should be positive integer, lEnd=" + lEnd + ", lStart=" + lStart);
			}
			if (lEnd < lStart) {
				throw new Exception(
						"End should be greater than or equal to start, lEnd=" + lEnd + ", lStart=" + lStart);
			}

			int maxlength = -1;
			String limitmsg = "";

			maxlength = AppContext.getMaxlengthUniDownload(params.getSnpSet());
			limitmsg = "Limit to " + (maxlength / 1000) + " kbp range for " + params.getSnpSet()
					+ " SNPs, All Varieties query.";

			long querylength = lEnd - lStart;
			// if(!checkboxCoreSNP.isChecked() && querylength > maxlength )
			if (querylength > maxlength) {
				throw new Exception(limitmsg);
			}

		} else {
			if (poslist != null && locuslist != null)
				msgbox = "SEARCHING: in chromosome " + sChr + " " + poslist.size() + " SNPs, " + locuslist.size()
						+ " loci";
			else if (poslist != null)
				msgbox = "SEARCHING: in chromosome " + sChr + " " + poslist.size() + " SNPs";
			if (locuslist != null)
				msgbox = "SEARCHING: in " + locuslist.size() + " loci";
		}

		Set setVarieties = null;
		if (colVarIds == null) {
			// mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		} else {
			setVarieties = new HashSet(colVarIds);
			// mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			msgbox += ", " + setVarieties.size() + " varieties";
		}
		if (sSubpopulation != null && !sSubpopulation.isEmpty()) {
			// mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			setVarieties = listitemsdao.getGermplasmBySubpopulation(sSubpopulation, params.getDataset());
			msgbox += ", " + sSubpopulation + " subpopulation";
		}

		AppContext.startTimer();

		VariantStringData variantMerged = null;
		if ((params.getOrganismName().equals(AppContext.REFERENCE_NIPPONBARE()) || params.getOrganismName().equals(AppContext.MH63())|| params.getOrganismName().equals(AppContext.AZUCENA)|| params.getOrganismName().equals(AppContext.IR64))  && !params.isbShowAllRefAlleles())
			variantMerged = queryVariantStringDataNipponbare(params);
		else
			variantMerged = queryVariantStringDataAllrefs(params);

		variantMerged.setMessage(msgbox + variantMerged.getMessage());

		AppContext.debug("queryVariantStringData msg=" + variantMerged.getMessage());
		AppContext.resetTimer("queryVariantstring");
		return variantMerged;

	}

	/**
	 * Query variants from Nipponbare reference
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private VariantStringData queryVariantStringDataNipponbare(GenotypeQueryParams params) throws Exception {

		VariantStringData variantssnps = null;

		AppContext.debug("queryVariantStringDataNipponbare. dataset=" + params.getDataset());

		if (params.isbSNP()) {
			// include SNPs

			Set origDS = params.getDataset();
			Set origVS = params.getSnpSet();
			Set origRun = params.getSetRun();

			Set setRun = new HashSet();
			/*
			 * if(params.getDataset().contains(VarietyFacade.DATASET_SNP_ALL)) {
			 * setRun=listitemsdao.getPlatforms(); }
			 */

			Iterator itrun = origRun.iterator();
			Set setDS = new HashSet();
			Set setVS = new HashSet();
			AppContext.debug("origRun SIZE= " + origRun.size());
			AppContext.debug("origRun= " + origRun);

			String prevds = null;
			while (itrun.hasNext()) {
				GenotypeRunPlatform r = (GenotypeRunPlatform) itrun.next();
				if (r.getVariantType().toUpperCase().equals("SNP")) {
					setDS.add(r.getDataset());
					setVS.add(r.getVariantset());
					setRun.add(r);
					params.setDataset(setDS);
					params.setSnpSet(setVS);
					params.setSetRun(setRun);
					variantssnps = new VariantStringData();
					variantssnps
							.setSnpstringdata((VariantSnpsStringData) getAllNPBSNPService().getVariantString(params));
					prevds = r.getDataset();
					break;
				}
			}
			while (itrun.hasNext()) {
				GenotypeRunPlatform r = (GenotypeRunPlatform) itrun.next();
				if (r.getVariantType().toUpperCase().equals("SNP")) {
					setDS = new HashSet();
					setVS = new HashSet();
					setRun = new HashSet();
					setDS.add(r.getDataset());
					setVS.add(r.getVariantset());
					setRun.add(r);
					params.setDataset(setDS);
					params.setSnpSet(setVS);
					params.setSetRun(setRun);
					variantssnps.addSnpstringdata(
							(VariantSnpsStringData) getAllNPBSNPService().getVariantString(params),
							params.getDatasetPosOps().equals(VarietyFacade.DATASET_SNPPOS_UNION), false,
							params.isbDownloadOnly(), false, params.isbCountMissingAs05(), prevds, r.getDataset());
					prevds = "";
				}
			}
			params.setDataset(origDS);
			params.setSnpSet(origVS);
			params.setSetRun(origRun);
			variantssnps = variantssnps.getSnpstringdata();
		}

		VariantStringData variantsindels = null;

		// if(params.isbIndel() && !params.isSNPList() ) {
		if (params.isbIndel()) {
			// include Indels

			Iterator itrun = params.getSetRun().iterator();
			while (itrun.hasNext()) {
				GenotypeRunPlatform r = (GenotypeRunPlatform) itrun.next();
				if (r.getVariantType().toUpperCase().equals("INDEL")) {
					Set origVS = new HashSet(params.getSnpSet());
					Set origDS = new HashSet(params.getDataset());
					Set origRun = new HashSet(params.getSetRun());
					Set setDS = new HashSet();
					Set setVS = new HashSet();
					Set setRun = new HashSet();
					setDS.add(r.getDataset());
					setVS.add(r.getVariantset());
					setRun.add(r);
					params.setDataset(setDS);
					params.setSnpSet(setVS);
					params.setSetRun(setRun);
					variantsindels = getIndelsService().getVariantString(params);
					params.setDataset(origDS);
					params.setSnpSet(origVS);
					params.setSetRun(origRun);
					break;
				}
			}

		}

		VariantStringData variantMerged = new VariantStringData();
		variantMerged.setSnpstringdata((VariantSnpsStringData) variantssnps);
		variantMerged.setIndelstringdata((VariantIndelStringData) variantsindels);
		variantMerged.merge(params.getDataset());
		return variantMerged;
	}

	/**
	 * Query variants from all reference genomes
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private VariantStringData queryVariantStringDataAllrefs(GenotypeQueryParams params) throws Exception {
		VariantStringData variantssnps = null;
		/*
		 * if(!params.isbIndel()) { return getAllSNPService().getVariantString(params);
		 * }
		 */

		if (params.isbSNP()) {
			// include SNPs
			variantssnps = getAllSNPService().getVariantString(params);
		}
		VariantStringData variantMerged = new VariantStringData();
		variantMerged.setSnpstringdata((VariantSnpsStringData) variantssnps);
		variantMerged.merge(params.getDataset());
		return variantMerged;

	}

	@Override
	public Future queryVariantStringDataAsync(GenotypeQueryParams params) throws Exception {
		return null;
	}

	// private VariantStringData queryVariantStringDataAllRefs( GenotypeQueryParams
	// params) throws Exception {
	//
	//
	//
	//
	// if(!params.getOrganism().equals( AppContext.getDefaultOrganism() )) {
	// // not nipponbare coordinate. convert coordinates
	//
	// multiplerefconvertdao =
	// (MultipleReferenceConverterDAO)AppContext.checkBean(multiplerefconvertdao,
	// "MultipleReferenceConverterDAO");
	//
	// MultiReferenceLocus locusQueried = new
	// MultiReferenceLocusImpl(params.getOrganism(), params.getsChr(),
	// params.getlStart(), params.getlEnd(), 1L);
	//
	// MultiReferenceLocus locusNipponbare = multiplerefconvertdao.convertLocus(
	// locusQueried , AppContext.getDefaultOrganism(), null);
	// MultiReferenceLocus origMultiReferenceLocus =
	// params.setNewPosition(locusNipponbare);
	//
	// VariantStringData variantstringdataNPB =
	// queryVariantStringDataNipponbare(params);
	//
	// String toContig = null;
	// if(params.isLimitToQueryContig()) {
	// toContig = locusQueried.getContig();
	// }
	//
	//
	//
	// variantstringdataNPB.setMessage( variantstringdataNPB.getMessage() + "\nQuery
	// " + locusQueried + " aligned with " + locusNipponbare);
	//
	// AppContext.debug( variantstringdataNPB.toString() );
	// AppContext.debug( variantstringdataNPB.getMessage() );
	//
	// VariantStringData convertedSNPPositions =
	// multiplerefconvertdao.convertReferencePositions(variantstringdataNPB,
	// locusNipponbare, locusQueried, toContig);
	// params.setNewPosition( origMultiReferenceLocus );
	//
	// AppContext.debug( convertedSNPPositions.toString() );
	// AppContext.debug("convertedSNPPositions msg=" +
	// convertedSNPPositions.getMessage() );
	//
	// return convertedSNPPositions;
	//
	//
	//
	// //return variantstringdataNPB;
	//
	// } else {
	// return queryVariantStringDataNipponbare(params);
	// }
	//
	// }

}
