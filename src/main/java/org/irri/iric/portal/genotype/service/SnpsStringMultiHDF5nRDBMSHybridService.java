package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.ds.chado.dao.SnpsAllvarsMultirefsPosDAO;
import org.irri.iric.ds.chado.dao.SnpsAllvarsPosDAO;
import org.irri.iric.ds.chado.dao.SnpsEffectDAO;
import org.irri.iric.ds.chado.dao.SnpsNonsynAllvarsDAO;
import org.irri.iric.ds.chado.dao.SnpsSpliceAcceptorDAO;
import org.irri.iric.ds.chado.dao.SnpsSpliceDonorDAO;
import org.irri.iric.ds.chado.dao.SnpsStringDAO;
import org.irri.iric.ds.chado.domain.MultiReferenceLocus;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.Snp;
import org.irri.iric.ds.chado.domain.SnpsAllvarsMultirefsPos;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.irri.iric.ds.chado.domain.SnpsNonsynAllele;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.SnpsSynAllele;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.impl.MultiReferenceLocusImpl;
import org.irri.iric.ds.chado.domain.impl.MultiReferencePositionImplAllelePvalue;
import org.irri.iric.ds.chado.domain.impl.SnpsStringAllvarsImpl;
import org.irri.iric.ds.chado.domain.model.Organism;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.dao.MultipleReferenceConverterDAO;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantSnpsStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantStringService;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.irri.iric.portal.variety.VarietyFacade;
//import org.irri.iric.portal.flatfile.dao.SnpcoreRefposindexDAO;
//import org.irri.iric.portal.flatfile.domain.VSnpRefposindex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("SnpsStringService")
public class SnpsStringMultiHDF5nRDBMSHybridService implements VariantStringService {

	@Override
	public List checkSNPsInChromosome(Integer organismId, String chr, Collection posset, Set variantset) {
		return null;
	}

	static Set<Character> charMissing = new HashSet<Character>(Arrays.asList('0', '.', '?'));
	static Set<Character> charSameasAllele1 = new HashSet<Character>(Arrays.asList('*'));
	static Set<Character> charSymbols = new HashSet<Character>(Arrays.asList('0', '.', '?', '*'));

	// Other refs HDF5 DAOs
	private Map<BigDecimal, H5Dataset> mapFileId2DatasetDAO = new HashMap();

	/*
	 * @Autowired
	 * 
	 * @Qualifier("H5SNPUniIUPACV2DAO") private H5Dataset nballsnpsdao;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("H5SNPCoreIUPACV2DAO") private H5Dataset nbcoresnpsdao;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("H5SNPCoreIUPACV2TransposedDAO") private H5Dataset
	 * nbcoresnpstransdao;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("H5SNPUniIUPACV2TransposedDAO") private H5Dataset
	 * nballsnpstransdao;
	 */

	/*
	 * @Autowired
	 * 
	 * @Qualifier("VSnpRefposindexDAO") // snps reference, allele position file
	 * index private SnpsAllvarsPosDAO snpstringallvarsNPBposDAO;
	 */

	@Autowired
	// @Qualifier("VConvertposAny2allrefsDAO") // snps reference, allele position
	// file index
	@Qualifier("MultipleReferenceConverterDAO") // snps reference, allele position file index
	private SnpsAllvarsPosDAO snpstringallvarsposDAO;
	// private SnpsAllvarsMultirefsPosDAO snpstringallvarsposDAO;

	@Autowired
	@Qualifier("VSnpNonsynallelePosDAO")
	private SnpsNonsynAllvarsDAO snpsnonsynDAO;
	// @Autowired
	// @Qualifier("VSnpSynallelePosDAO")
	// private SnpsSynAllvarsDAO snpssynDAO;
	@Autowired
	private SnpsEffectDAO snpseffDAO;

	@Autowired
	private SnpsSpliceAcceptorDAO snpsspliceacceptorDAO;
	@Autowired
	private SnpsSpliceDonorDAO snpssplicedonorDAO;

	@Autowired
	@Qualifier("MultipleReferenceConverterDAO")
	// @Qualifier("MultipleReferenceConverterPrecompDAO")
	private MultipleReferenceConverterDAO multiplerefconvertdao;

	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsdao;

	// these DAOs will be assigned based on selected datatype

	// @Autowired
	// @Qualifier("H5SNPUniAllele1DAO")
	private SnpsStringDAO snpstrAllsnpsAllele1AllvarsDAO;

	// @Autowired
	// @Qualifier("H5SNPUniAllele2DAO")
	private SnpsStringDAO snpstrAllsnpsAllele2AllvarsDAO;

	// @Autowired
	// @Qualifier("H5SNPCoreAllele1DAO")
	private SnpsStringDAO snpstrCoresnpsAllele1AllvarsDAO;

	// @Autowired
	// @Qualifier("H5SNPCoreAllele2DAO")
	private SnpsStringDAO snpstrCoresnpsAllele2AllvarsDAO;

	/*
	 * @Override public List checkSNPsInChromosome(String chr, Collection posset,
	 * BigDecimal type) { //snpstringallvarsposDAO =
	 * (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO,
	 * "VSnpRefposindexDAO") ; //AppContext.debug("reading VSnpRefposindexDAO");
	 * //return snpstringallvarsposDAO.getSNPsInChromosome(chr, posset, type);
	 * return null; }
	 */

	@Override
	public List<SnpsEffect> getSnpsEffects(List positions) {

		snpseffDAO = (SnpsEffectDAO) AppContext.checkBean(snpseffDAO, "SnpsEffectDAO");
		List listFeatureid = new ArrayList();
		Iterator<Snp> itPos = positions.iterator();
		while (itPos.hasNext()) {
			listFeatureid.add(itPos.next().getSnpFeatureId());
		}
		List listEffs = new ArrayList();
		listEffs.addAll(snpseffDAO.getSNPsByFeatureidIn(listFeatureid));
		return listEffs;
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
	 * Query SNPs
	 */
	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params) throws Exception { // , boolean exactMismatch,
																								// int firstRow, int
																								// maxRows) {

		// if reference is not Nipponbare
		// convert positions to other references
		// if show all reference alleles
		// if get other reference alleles at positions
		// convert positions to other references
		//

		VariantStringData vardata = null;
		MultiReferenceLocus locusNipponbare = null;
		if (!params.getOrganism().getName().equals(AppContext.JAPONICA_NIPPONBARE)) {
			// not nipponbare coordinate. convert coordinates

			if (params.getsChr() != null && params.getlStart() != null && params.getlEnd() != null) {

				VariantStringData variantstringdataNPB = _getSNPsStringQueryReference(params);

				MultiReferenceLocus locusQueried = new MultiReferenceLocusImpl(params.getOrganism().getName(), params.getsChr(),
						params.getlStart().intValue(), params.getlEnd().intValue(), 1);
				if (params.isbShowAllRefAlleles()) {
					vardata = multiplerefconvertdao.convertReferencePositions(variantstringdataNPB, locusNipponbare,
							locusQueried, null, false);
				}
				/*
				 * if(params.isbShowAllRefAlleles()) { vardata =
				 * convertReferencePositionsToAllReferences(params, vardata, locusNipponbare);
				 * AppContext.debug( "show other alllele refs = " +
				 * vardata.getMapOrg2MSU7Pos2ConvertedPos().keySet()); }
				 */

				// multiplerefconvertdao =
				// (MultipleReferenceConverterDAO)AppContext.checkBean(multiplerefconvertdao,
				// "MultipleReferenceConverterDAO");
				//
				// MultiReferenceLocus locusQueried = new
				// MultiReferenceLocusImpl(params.getOrganism(), params.getsChr(),
				// params.getlStart().intValue(), params.getlEnd().intValue(), 1);
				// locusNipponbare = multiplerefconvertdao.convertLocus( locusQueried ,
				// AppContext.getDefaultOrganism(), null);
				// MultiReferenceLocus origMultiReferenceLocus =
				// params.setNewPosition(locusNipponbare);
				//
				//
				// VariantStringData variantstringdataNPB = _getSNPsStringNipponbare(params);
				//
				// String toContig = null;
				// if(params.isLimitToQueryContig()) {
				// toContig = locusQueried.getContig();
				// }
				//
				// variantstringdataNPB.setMessage( variantstringdataNPB.getMessage() + "\nSNP
				// Query " + locusQueried + " aligned with " + locusNipponbare);
				// params.setNewPosition(origMultiReferenceLocus);
				// vardata =
				// multiplerefconvertdao.convertReferencePositions(variantstringdataNPB,
				// locusNipponbare, locusQueried, toContig, false);
				//
				//
				// if(params.isbShowAllRefAlleles()) {
				// vardata = convertReferencePositionsToAllReferences(params, vardata,
				// locusNipponbare);
				// AppContext.debug( "show other alllele refs = " +
				// vardata.getMapOrg2MSU7Pos2ConvertedPos().keySet());
				// }

			} else
				throw new RuntimeException("Multiple reference query not yet allowed for SNP/locus lists");

		} else {

			vardata = _getSNPsStringQueryReference(params);

			// if(!params.isLocusList() && !params.isSNPList() &&
			// params.isbShowAllRefAlleles()) {
			if (params.isbShowAllRefAlleles()) {

				if (params.getsChr() != null && params.getlStart() != null && params.getlEnd() != null) {
					locusNipponbare = new MultiReferenceLocusImpl(params.getOrganism().getName(), params.getsChr(),
							params.getlStart().intValue(), params.getlEnd().intValue(), 1);
					vardata = convertReferencePositionsToAllReferences(params, vardata, locusNipponbare);
					AppContext.debug("show other alllele refs = " + vardata.getMapOrg2MSU7Pos2ConvertedPos().keySet());

				} else {
					// throw new RuntimeException("Multiple reference query not yet allowed for
					// SNP/locus lists");

					if (params.hasSnpList()) {
						vardata = convertReferencePositionsToAllReferences(params, vardata, null);
						/*
						 * Iterator<Position> itPos=params.getPoslist().iterator();
						 * while(itPos.hasNext()) { Position ipos=itPos.next(); locusNipponbare = new
						 * MultiReferenceLocusImpl(params.getOrganism(), ipos.getContig(),
						 * ipos.getPosition().intValue(), ipos.getPosition().intValue(), 1); vardata =
						 * convertReferencePositionsToAllReferences(params, vardata, locusNipponbare); }
						 */
					} else
						throw new RuntimeException("Multiple reference query not yet allowed for locus lists");

				}
			}

		}

		return vardata;

	}

	/**
	 * Convert reference position from Nipponbare to other references
	 * 
	 * @param params
	 * @param variantstringdata
	 * @param npbMultirefLocus
	 * @return
	 * @throws Exception
	 */

	private VariantStringData convertReferencePositionsToAllReferences(GenotypeQueryParams params,
			VariantStringData variantstringdata, MultiReferenceLocus npbMultirefLocus) throws Exception {

		// return multiplerefconvertdao.convertReferencePositions(variantstringdata,
		// npbMultirefLocus, null, null, false);
		return multiplerefconvertdao.convertReferencePositions(variantstringdata, npbMultirefLocus, null, null, false);

	}

	//
	//
	// private VariantStringData
	// convertReferencePositionsToAllReferencesOld(GenotypeQueryParams params,
	// VariantStringData variantstringdata, MultiReferenceLocus npbMultirefLocus)
	// throws Exception {
	//
	// // convert to other references except org in origMultiReferenceLocus
	// // if origMultiReferenceLocus==null, convert to all 4 others
	//
	// multiplerefconvertdao =
	// (MultipleReferenceConverterDAO)AppContext.checkBean(multiplerefconvertdao,
	// "MultipleReferenceConverterDAO");
	// listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItems");
	// Iterator<Organism> itOrg = listitemsdao.getOrganisms().iterator();
	// while(itOrg.hasNext()) {
	// Organism org = itOrg.next();
	//
	// if(org.getName().equals( params.getOrganism() )) continue; // selected
	// organism already shown
	// if(org.getName().equals( AppContext.getDefaultOrganism()) &&
	// params.isbShowNPBPositions() ) continue;
	//
	// AppContext.debug("converting from " + npbMultirefLocus + " to organism " +
	// org.getName());
	//
	// try {
	// MultiReferenceLocus locusThisOrg = multiplerefconvertdao.convertLocus(
	// npbMultirefLocus , org.getName(), null);
	// AppContext.debug("converting from " + npbMultirefLocus + " to " +
	// locusThisOrg);
	// variantstringdata.setMessage(variantstringdata.getMessage() + "\n" +
	// npbMultirefLocus + " aligned with " + locusThisOrg);
	// variantstringdata =
	// multiplerefconvertdao.convertReferencePositions(variantstringdata,
	// npbMultirefLocus, locusThisOrg, null, true);
	// } catch(Exception ex) {
	// //ex.getMessage()
	// ex.printStackTrace();
	// variantstringdata.setMessage(variantstringdata.getMessage() + "\n" +
	// ex.getMessage());
	// throw ex;
	// }
	// }
	//
	// return variantstringdata;
	//
	// }
	//

	/**
	 * Contains nucleotide string sequences for each variety based on query criteria
	 * before mismatch (reference or pairwise) counting
	 * 
	 * @author lmansueto
	 *
	 */
	class SNPsStringData {
		private List<SnpsAllvarsPos> listSnpsPos = new ArrayList();
		private String strRef = "";
		private Map<BigDecimal, String> mapVarid2Snpsstr = new HashMap();
		private Map<BigDecimal, Map<Integer, Character>> mapVarid2SnpsAllele2str = new HashMap();
		private Map<Position, Set<Character>> mapPos2NonsynAlleles = new TreeMap();
		private Map<Position, Set<Character>> mapPos2Alleles = new TreeMap();
		private Map<Position, Set<Character>> mapPos2SynAlleles = new TreeMap();
		// private Set<Position> setSnpInExonPos=new TreeSet();
		private Set<Position> setSnpSpliceDonorPos = new TreeSet();
		private Set<Position> setSnpSpliceAcceptorPos = new TreeSet();
		private String message = "";

		public SNPsStringData() {
			super();
			// TODO Auto-generated constructor stub
		}

		public SNPsStringData(List listSnpsPos, String strRef, Map mapVarid2Snpsstr, Map mapVarid2SnpsAllele2str,
				Map mapPos2Alleles, Map mapPos2NonsynAlleles, Map mapPos2SynAlleles, Set setSnpSpliceDonorPos,
				Set setSnpSpliceAcceptorPos, String message) {
			super();

			this.strRef = strRef;
			this.mapVarid2Snpsstr = mapVarid2Snpsstr;
			this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
			this.mapPos2NonsynAlleles = mapPos2NonsynAlleles;
			this.mapPos2SynAlleles = mapPos2SynAlleles;
			// this.setSnpInExonPos = setSnpInExonPos;
			this.listSnpsPos = listSnpsPos;
			this.setSnpSpliceAcceptorPos = setSnpSpliceAcceptorPos;
			this.setSnpSpliceDonorPos = setSnpSpliceDonorPos;
			this.message = message;
		}

		public String getStrRef() {
			return strRef;
		}

		public Map<BigDecimal, String> getMapVarid2Snpsstr() {
			return mapVarid2Snpsstr;
		}

		public Map getMapVarid2SnpsAllele2str() {
			return mapVarid2SnpsAllele2str;
		}

		public Map getMapPos2NonsynAlleles() {
			return mapPos2NonsynAlleles;
		}

		public Map getMapPos2SynAlleles() {
			return mapPos2SynAlleles;
		}

		public List<SnpsAllvarsPos> getListSnpsPos() {
			return listSnpsPos;
		}
	}

	/**
	 * Query Nipponbare SNPs
	 * 
	 * @param params
	 * @return
	 */
	private VariantStringData _getSNPsStringQueryReference(GenotypeQueryParams params) { // , boolean exactMismatch, int
																							// firstRow, int maxRows) {

		Collection colVarids = params.getColVarIds();
		// Integer chr= Integer.valueOf( params.getsChr() );
		String chr = params.getsChr();
		BigDecimal start = null;
		if (params.getlStart() != null)
			start = BigDecimal.valueOf(params.getlStart());
		BigDecimal end = null;
		if (params.getlEnd() != null)
			end = BigDecimal.valueOf(params.getlEnd());
		Collection setPositions = params.getPoslist();
		Collection colLocus = params.getColLoci();

		// query variants
		SNPsStringData snpstrdata = null;
		// snpstrdata=getSNPsStringDataOtherRefs(params);

		if (params.getOrganism().getName().equals(AppContext.JAPONICA_NIPPONBARE) && !params.isbShowAllRefAlleles())
			// snpstrdata=getSNPsStringData(params, colVarids, chr, start, end,
			// setPositions, colLocus);
			// else snpstrdata=getSNPsStringDataOtherRefs(params, colVarids, chr, start,
			// end, setPositions, colLocus);
			throw new RuntimeException("Unexpected case _getSNPsStringQueryReference: organism=" + params.getOrganism().getName()
					+ "  isbShowAllRefAlleles=" + params.isbShowAllRefAlleles());
		else
			snpstrdata = getSNPsStringDataOtherRefs(params);

		if (snpstrdata == null) {
			throw new RuntimeException("getSNPsString:  snpstrdata==null");
		}

		Map<String, Double> mapRef2Match = null;
		String querystring = null;
		// get match with reference
		if (params.isbAlleleFilter() && params.getOrganism().getName().equals(AppContext.JAPONICA_NIPPONBARE)) {

			StringBuffer buffQuery = new StringBuffer();
			Iterator<SnpsAllvarsPos> itPosAllele = params.getPoslist().iterator();
			while (itPosAllele.hasNext()) {
				MultiReferencePositionImplAllelePvalue multipos = (MultiReferencePositionImplAllelePvalue) itPosAllele
						.next();
				String allele = multipos.getAllele();
				if (allele != null && !allele.isEmpty())
					buffQuery.append(multipos.getAllele());
				else
					buffQuery.append("?");
			}

			itPosAllele = snpstrdata.getListSnpsPos().iterator();
			StringBuffer buffNb = new StringBuffer();
			StringBuffer buff9311 = new StringBuffer();
			StringBuffer buffIR64 = new StringBuffer();
			StringBuffer buffDJ123 = new StringBuffer();
			StringBuffer buffKas = new StringBuffer();

			while (itPosAllele.hasNext()) {
				// MultiReferencePositionImplAllelePvalue multipos=itPosAllele.next();
				SnpsAllvarsMultirefsPos multipos = (SnpsAllvarsMultirefsPos) itPosAllele.next();
				String allele = multipos.getAllele(AppContext.REFERENCE_NIPPONBARE());
				if (allele != null && !allele.isEmpty())
					buffNb.append(allele);
				else
					buffNb.append("?");
				allele = multipos.getAllele(Organism.REFERENCE_9311);
				if (allele != null && !allele.isEmpty())
					buff9311.append(allele);
				else
					buff9311.append("?");
				allele = multipos.getAllele(Organism.REFERENCE_DJ123);
				if (allele != null && !allele.isEmpty())
					buffDJ123.append(allele);
				else
					buffDJ123.append("?");
				allele = multipos.getAllele(Organism.REFERENCE_IR64);
				if (allele != null && !allele.isEmpty())
					buffIR64.append(allele);
				else
					buffIR64.append("?");
				allele = multipos.getAllele(Organism.REFERENCE_KASALATH);
				if (allele != null && !allele.isEmpty())
					buffKas.append(allele);
				else
					buffKas.append("?");
			}
			querystring = buffQuery.toString();
			mapRef2Match = new HashMap();
			double mismatchCount[] = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(
					snpstrdata.getListSnpsPos(), buffNb.toString(), querystring, true, null, null,
					snpstrdata.getMapPos2NonsynAlleles(), new HashSet(),
					params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(), params.isbCountMissingAs05()); // .isbExcludeSynonymous()
																												// );
			mapRef2Match.put(AppContext.REFERENCE_NIPPONBARE(), mismatchCount[1]);
			mismatchCount = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(snpstrdata.getListSnpsPos(),
					buff9311.toString(), querystring, true, null, null, snpstrdata.getMapPos2NonsynAlleles(),
					new HashSet(), params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(),
					params.isbCountMissingAs05()); // .isbExcludeSynonymous() );
			mapRef2Match.put(Organism.REFERENCE_9311, mismatchCount[1]);
			mismatchCount = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(snpstrdata.getListSnpsPos(),
					buffDJ123.toString(), querystring, true, null, null, snpstrdata.getMapPos2NonsynAlleles(),
					new HashSet(), params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(),
					params.isbCountMissingAs05()); // .isbExcludeSynonymous() );
			mapRef2Match.put(Organism.REFERENCE_DJ123, mismatchCount[1]);
			mismatchCount = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(snpstrdata.getListSnpsPos(),
					buffIR64.toString(), querystring, true, null, null, snpstrdata.getMapPos2NonsynAlleles(),
					new HashSet(), params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(),
					params.isbCountMissingAs05()); // .isbExcludeSynonymous() );
			mapRef2Match.put(Organism.REFERENCE_IR64, mismatchCount[1]);
			mismatchCount = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(snpstrdata.getListSnpsPos(),
					buffKas.toString(), querystring, true, null, null, snpstrdata.getMapPos2NonsynAlleles(),
					new HashSet(), params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(),
					params.isbCountMissingAs05()); // .isbExcludeSynonymous() );
			mapRef2Match.put(Organism.REFERENCE_KASALATH, mismatchCount[1]);

		}

		Map mapVarid2Snpsstr = snpstrdata.getMapVarid2Snpsstr();
		if (mapVarid2Snpsstr == null) {
			mapVarid2Snpsstr = new HashMap();
		}

		Set setNonsynPos = new HashSet();

		Set sortedVarieties = new TreeSet(
				new SnpsStringAllvarsImplSorter(params.isbDownloadOnly(), params.getDataset()));

		Iterator itVar = mapVarid2Snpsstr.keySet().iterator();

		// compare variety mismatch then sort
		while (itVar.hasNext()) {
			Object ovar = itVar.next();
			BigDecimal var = null;
			if (ovar instanceof BigDecimal)
				var = (BigDecimal) ovar;
			else if (ovar instanceof Variety)
				var = ((Variety) ovar).getVarietyId();

			String snpstr = (String) mapVarid2Snpsstr.get(var);
			Set<Position> varNonsynPos = new HashSet();
			Set<Position> varSynPos = new HashSet();

			double mismatchCount[] = null;
			if (querystring != null) {
				mismatchCount = countVarpairMismatch(snpstrdata.getListSnpsPos(), querystring, snpstr, false, null,
						(Map) snpstrdata.getMapVarid2SnpsAllele2str().get(var), snpstrdata.getMapPos2NonsynAlleles(),
						varNonsynPos, params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(),
						params.isbCountMissingAs05()); // .isbExcludeSynonymous() );
			} else
				mismatchCount = countVarpairMismatch(snpstrdata.getListSnpsPos(), snpstrdata.getStrRef(), snpstr, true,
						null, (Map) snpstrdata.getMapVarid2SnpsAllele2str().get(var),
						snpstrdata.getMapPos2NonsynAlleles(), varNonsynPos,
						params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(), params.isbCountMissingAs05()); // .isbExcludeSynonymous()
																													// );

			double score = (params.isbAlleleFilter() ? mismatchCount[1] : mismatchCount[0]);
			if (!params.isbMismatchonly() || score > 0 || params.isbPairwiseComparison() || params.hasLocusList()) { // ||
																														// params.hasLocusList())
																														// {
				sortedVarieties.add(new SnpsStringAllvarsImpl(var, chr, snpstr, BigDecimal.valueOf(score),
						(Map) snpstrdata.getMapVarid2SnpsAllele2str().get(var), varNonsynPos, varSynPos,
						snpstrdata.setSnpSpliceDonorPos, snpstrdata.setSnpSpliceAcceptorPos));

				// AppContext.debug("var=" + var + " mis=" + mismatchCount[0] + ", match="+
				// mismatchCount[1] + ", score=" + score);
			}
		}

		List<SnpsStringAllvars> listResult = new ArrayList();
		// sort included varieties
		Map mapVariety2Order = new HashMap();
		Map mapVariety2Mismatch = new HashMap();
		int ordercount = 0;
		Iterator itSorVars = sortedVarieties.iterator();
		while (itSorVars.hasNext()) {
			SnpsStringAllvars snpstrvar = (SnpsStringAllvars) itSorVars.next();
			listResult.add(snpstrvar);
			mapVariety2Order.put(snpstrvar.getVar(), ordercount);
			mapVariety2Mismatch.put(snpstrvar.getVar(), snpstrvar.getMismatch());
			ordercount++;
		}

		List listsortedVarieties = new ArrayList();
		listsortedVarieties.addAll(sortedVarieties);

		VariantStringData vardata = new VariantSnpsStringData(mapVariety2Mismatch, mapVariety2Order,
				snpstrdata.getListSnpsPos(), listsortedVarieties, snpstrdata.getStrRef(),
				snpstrdata.getMapVarid2SnpsAllele2str(), snpstrdata.getMapPos2NonsynAlleles(),
				snpstrdata.getMapPos2SynAlleles(), snpstrdata.setSnpSpliceDonorPos, snpstrdata.setSnpSpliceAcceptorPos,
				mapRef2Match, params.getDataset());
		vardata.setMessage(snpstrdata.message);

		return vardata;
	}

	/**
	 * query SNPsStringData for other (not NB) reference genomes
	 * 
	 * @param params
	 * @param colVarids
	 * @param chr
	 * @param start
	 * @param end
	 * @param setPositions
	 * @param colLocus
	 * @return
	 */

	private SNPsStringData getSNPsStringDataOtherRefs(GenotypeQueryParams params) {

		AppContext.debug("reading getSNPsStringDataOtherRefs");

		Collection colVarids = params.getColVarIds();
		String chr = params.getsChr();
		Collection setPositions = params.getPoslist();
		Collection colLocus = params.getColLoci();
		Organism organism = params.getOrganism();

		BigDecimal start = null;
		BigDecimal end = null;
		if (params.getlStart() != null && params.getlEnd() != null) {
			start = BigDecimal.valueOf(params.getlStart());
			end = BigDecimal.valueOf(params.getlEnd());
		}

		snpstringallvarsposDAO = null;

		// snpstringallvarsposDAO =
		// (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO,
		// "VSnpRefposindexDAO") ;
		// snpstringallvarsposDAO =
		// (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO,
		// "VConvertposAny2allrefsDAO") ;

		// snpstringallvarsposDAO =
		// (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO,
		// "MultipleReferenceConverterDAO_VIEW") ;
		snpstringallvarsposDAO = (SnpsAllvarsPosDAO) AppContext.checkBean(snpstringallvarsposDAO,
				"MultipleReferenceConverterDAO");

		SnpsAllvarsMultirefsPosDAO snpstringallvarsmultirefsposDAO = (SnpsAllvarsMultirefsPosDAO) snpstringallvarsposDAO;

		AppContext.debug("using DAO" + snpstringallvarsposDAO.getClass().getCanonicalName());
		AppContext.debug("using DAO " + snpstringallvarsmultirefsposDAO.getClass().getName());

		// SnpsStringDAO snpstrSnpsAllele1AllvarsDAO=null;
		// SnpsStringDAO snpstrSnpsAllele2AllvarsDAO=null;
		// BigDecimal snptype=snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
		Set snptype = new HashSet();
		snptype.add(SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2);

		List<SnpsAllvarsPos> snpsposlist = null;
		List listpos = null;

		StringBuffer buffMsg = new StringBuffer();

		if (params.hasPreviousData()) {
			VariantStringData prevdata = params.getVariantdata();
			snpsposlist = prevdata.getListPos();
			if ((setPositions != null && !setPositions.isEmpty())) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(setPositions));
			}
		} else {

			if (colVarids == null || colVarids.isEmpty()) {
				if ((setPositions != null && !setPositions.isEmpty())) {
					listpos = new ArrayList();
					listpos.addAll(new TreeSet(setPositions));
					AppContext.resetTimer("getSNPsString start1");
					snpsposlist = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(), listpos, snptype,
							organism.getOrganismId(), buffMsg);
				} else if ((colLocus != null && !colLocus.isEmpty())) {
					AppContext.resetTimer("getSNPsString start1b");
					snpsposlist = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(), colLocus, snptype,
							organism.getOrganismId(), buffMsg);
				} else {
					AppContext.resetTimer("getSNPsString start2");
					snpsposlist = snpstringallvarsmultirefsposDAO.getSNPs(chr.toString(), start.intValue(),
							end.intValue(), snptype,
							organism.getOrganismId(), buffMsg);
				}
			} else {
				if (setPositions != null && !setPositions.isEmpty()) {
					listpos = new ArrayList();
					listpos.addAll(new TreeSet(setPositions));
					AppContext.resetTimer("getSNPsString start3");
					snpsposlist = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(), listpos, snptype,
							organism.getOrganismId(), buffMsg);
				} else if (colLocus != null && !colLocus.isEmpty()) {
					AppContext.resetTimer("getSNPsString start3b");
					snpsposlist = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(), colLocus, snptype,
							organism.getOrganismId(), buffMsg);
				} else {
					AppContext.resetTimer("getSNPsString start4");
					snpsposlist = snpstringallvarsmultirefsposDAO.getSNPs(chr.toString(), start.intValue(),
							end.intValue(), snptype,
							organism.getOrganismId(), buffMsg);
				}
				// AppContext.debug("colvarids=" + colVarids.toString());
			}

			if (snpsposlist == null)
				throw new RuntimeException("snpsposlist==null");
			if (snpsposlist.isEmpty())
				return new SNPsStringData();

			/*
			 * if(params.includeDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC)) {
			 * if(params.isbCoreonly()) { throw new RuntimeException("Only non-core " +
			 * VarietyFacade.DATASET_SNPINDELV2_IUPAC +
			 * " is available for other reference genomes"); } else {
			 * snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
			 * //snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(
			 * snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO"); } } else throw new
			 * RuntimeException("Only non-core " + VarietyFacade.DATASET_SNPINDELV2_IUPAC +
			 * " is available for other reference genomes");
			 */

			// snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(
			// snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniIUPACV2DAO");
			// if(snpstrSnpsAllele1AllvarsDAO==null) throw new
			// RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");
		}

		AppContext.debug("sorting out file-contig for " + snpsposlist.size() + " positions");

		// sort out which hdf5 files to read and ranges
		Map<String, BigDecimal> mapFileContig2StartIdx = null;
		Map<String, BigDecimal> mapFileContig2EndIdx = null;
		Map<String, SnpsAllvarsMultirefsPos> mapFileContig2StartPos = null;
		Map<String, SnpsAllvarsMultirefsPos> mapFileContig2EndPos = null;

		// Map<String,Integer> mapFileContig2IdxCount=new HashMap();
		// Map<Integer, Object[]> mapPosidx2FileContigIdx=new LinkedHashMap();
		// Map<Integer, Object[]> mapPosidx2FileContigStartEndIdx=new LinkedHashMap();
		// Map<Integer, BigDecimal[]> mapPosidx2FileContigStartEndIdx=new
		// LinkedHashMap();
		// Map<Integer, SnpsAllvarsMultirefsPos[]> mapPosidx2MultirefsPos=new
		// LinkedHashMap();
		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();

		int posidx = 0;
		SnpsAllvarsMultirefsPos lastpos = null;
		int lastposidx = 0;
		int prevposidx = 0;
		SnpsAllvarsMultirefsPos prevpos = null;
		String prevfilecontig = null;
		boolean firstpos = true;

		// get minimum start and maximum end column idx for each file-contig
		/*
		 * while(itPos.hasNext()) { SnpsAllvarsMultirefsPos
		 * ipos=(SnpsAllvarsMultirefsPos)itPos.next();
		 * 
		 * if(firstpos) { prevfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-"
		 * + lastposidx; lastpos = ipos; mapFileContig2StartIdx.put(prevfilecontig,
		 * lastpos.getAlleleIndex()); mapFileContig2StartPos.put(prevfilecontig,
		 * lastpos); AppContext.debug(posidx + ": min start for " + prevfilecontig +
		 * ": " + lastpos.getAlleleIndex()); firstpos=false; } String
		 * curfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + posidx; //+
		 * "-" + posidx;
		 * 
		 * if( ((prevpos!=null && (!prevpos.getFileId().equals(ipos.getFileId()) ||
		 * !prevpos.getContig().equals(ipos.getContig()) )) || !itPos.hasNext()) || (
		 * prevpos!=null && prevpos.getFileId().equals(ipos.getFileId()) &&
		 * prevpos.getContig().equals(ipos.getContig()) &&
		 * (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue())
		 * ) ) {
		 * 
		 * prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() + "-" +
		 * lastposidx ;
		 * 
		 * if(!mapFileContig2EndIdx.containsKey(prevfilecontig)) { if(!itPos.hasNext())
		 * {
		 * 
		 * if( prevpos!=null && prevpos.getFileId().equals(ipos.getFileId()) &&
		 * prevpos.getContig().equals(ipos.getContig()) &&
		 * (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue())
		 * ) { mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex() );
		 * mapFileContig2EndPos.put(prevfilecontig, prevpos); AppContext.debug(posidx +
		 * ": max end for " + prevfilecontig + ": " + prevpos.getAlleleIndex());
		 * 
		 * mapFileContig2StartIdx.put(curfilecontig, ipos.getAlleleIndex() );
		 * mapFileContig2StartPos.put(curfilecontig, ipos); AppContext.debug(posidx +
		 * ": min start for " + curfilecontig + ": " + ipos.getAlleleIndex());
		 * mapFileContig2EndIdx.put(curfilecontig, ipos.getAlleleIndex() );
		 * mapFileContig2EndPos.put(curfilecontig, ipos); AppContext.debug(posidx +
		 * ": max end for " + curfilecontig + ": " + ipos.getAlleleIndex()); } else {
		 * mapFileContig2EndIdx.put(prevfilecontig, ipos.getAlleleIndex() );
		 * mapFileContig2EndPos.put(prevfilecontig, ipos); AppContext.debug(posidx +
		 * ": max end for " + prevfilecontig + ": " + ipos.getAlleleIndex()); } } else {
		 * mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex() );
		 * mapFileContig2EndPos.put(prevfilecontig, prevpos); AppContext.debug(posidx +
		 * ": max end for " + prevfilecontig + ": " + prevpos.getAlleleIndex()); } }
		 * else {
		 * 
		 * throw new
		 * RuntimeException("Unexpected case: mapFileContig2EndIdx.containsKey(prevfilecontig)"
		 * ); }
		 * 
		 * if(itPos.hasNext()) { if(!mapFileContig2StartIdx.containsKey(curfilecontig))
		 * { mapFileContig2StartIdx.put(curfilecontig, ipos.getAlleleIndex() );
		 * mapFileContig2StartPos.put(curfilecontig, ipos); AppContext.debug(posidx +
		 * ": min start for " + curfilecontig + ": " + ipos.getAlleleIndex()); } else {
		 * if(mapFileContig2StartIdx.get(prevfilecontig).compareTo(
		 * prevpos.getAlleleIndex()) >= 0) { mapFileContig2StartIdx.put(prevfilecontig,
		 * prevpos.getAlleleIndex()); mapFileContig2StartPos.put(prevfilecontig,
		 * prevpos); AppContext.debug(posidx + ": new min start for " + prevfilecontig +
		 * ": " + prevpos.getAlleleIndex()); } } }
		 * 
		 * lastpos = ipos; lastposidx = posidx; }
		 * 
		 * prevposidx=posidx; prevpos=ipos; posidx++;
		 * 
		 * }
		 */
		int indxs[] = null;
		StringBuffer snplistbuffRef = null;
		BigDecimal snplisttypeid = null;

		if (params.hasSnpList()) {

			mapFileContig2StartPos = new LinkedHashMap();
			mapFileContig2EndPos = new LinkedHashMap();

			mapFileContig2StartIdx = new LinkedHashMap();
			mapFileContig2EndIdx = new LinkedHashMap();
			indxs = new int[snpsposlist.size()];
			int indxscount = 0;

			snplistbuffRef = new StringBuffer();
			while (itPos.hasNext()) {
				SnpsAllvarsMultirefsPos ipos = (SnpsAllvarsMultirefsPos) itPos.next();
				String curfilecontig = ipos.getFileId() + "-" + ipos.getContig() + "-" + posidx; // + "-" + posidx;
				snplisttypeid = ipos.getFileId();
				snplistbuffRef.append(ipos.getRefnuc());
				indxs[indxscount] = ipos.getAlleleIndex().intValue();
				mapFileContig2StartPos.put(curfilecontig, ipos);
				mapFileContig2EndPos.put(curfilecontig, ipos);
				mapFileContig2StartIdx.put(curfilecontig, ipos.getAlleleIndex());
				mapFileContig2EndIdx.put(curfilecontig, ipos.getAlleleIndex());

				indxscount++;
			}

		} else {

			mapFileContig2StartPos = new LinkedHashMap();
			mapFileContig2EndPos = new LinkedHashMap();

			mapFileContig2StartIdx = new LinkedHashMap();
			mapFileContig2EndIdx = new LinkedHashMap();

			while (itPos.hasNext()) {
				SnpsAllvarsMultirefsPos ipos = (SnpsAllvarsMultirefsPos) itPos.next();

				if (firstpos) {
					prevfilecontig = ipos.getFileId() + "-" + ipos.getContig() + "-" + lastposidx;
					lastpos = ipos;
					mapFileContig2StartIdx.put(prevfilecontig, lastpos.getAlleleIndex());
					mapFileContig2StartPos.put(prevfilecontig, lastpos);
					AppContext.debug(posidx + ": min start for " + prevfilecontig + ": " + lastpos.getAlleleIndex());
					firstpos = false;
				}
				String curfilecontig = ipos.getFileId() + "-" + ipos.getContig() + "-" + posidx; // + "-" + posidx;

				if (((prevpos != null && (!prevpos.getFileId().equals(ipos.getFileId())
						|| !prevpos.getContig().equals(ipos.getContig()))) || !itPos.hasNext())
						|| (prevpos != null && prevpos.getFileId().equals(ipos.getFileId())
								&& prevpos.getContig().equals(ipos.getContig())
								&& (prevpos.getAlleleIndex().longValue() + 1 != ipos.getAlleleIndex().longValue()))) {

					prevfilecontig = prevpos.getFileId() + "-" + prevpos.getContig() + "-" + lastposidx;

					if (!mapFileContig2EndIdx.containsKey(prevfilecontig)) {
						if (!itPos.hasNext()) {

							if (prevpos != null && prevpos.getFileId().equals(ipos.getFileId())
									&& prevpos.getContig().equals(ipos.getContig())
									&& (prevpos.getAlleleIndex().longValue() + 1 != ipos.getAlleleIndex()
											.longValue())) {
								mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex());
								mapFileContig2EndPos.put(prevfilecontig, prevpos);
								AppContext.debug(
										posidx + ": max end for " + prevfilecontig + ": " + prevpos.getAlleleIndex());

								mapFileContig2StartIdx.put(curfilecontig, ipos.getAlleleIndex());
								mapFileContig2StartPos.put(curfilecontig, ipos);
								AppContext.debug(
										posidx + ": min start for " + curfilecontig + ": " + ipos.getAlleleIndex());
								mapFileContig2EndIdx.put(curfilecontig, ipos.getAlleleIndex());
								mapFileContig2EndPos.put(curfilecontig, ipos);
								AppContext.debug(
										posidx + ": max end for " + curfilecontig + ": " + ipos.getAlleleIndex());
							} else {
								mapFileContig2EndIdx.put(prevfilecontig, ipos.getAlleleIndex());
								mapFileContig2EndPos.put(prevfilecontig, ipos);
								AppContext.debug(
										posidx + ": max end for " + prevfilecontig + ": " + ipos.getAlleleIndex());
							}
						} else {
							mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex());
							mapFileContig2EndPos.put(prevfilecontig, prevpos);
							AppContext.debug(
									posidx + ": max end for " + prevfilecontig + ": " + prevpos.getAlleleIndex());
						}
					} else {

						throw new RuntimeException("Unexpected case: mapFileContig2EndIdx.containsKey(prevfilecontig)");
					}

					if (itPos.hasNext()) {
						if (!mapFileContig2StartIdx.containsKey(curfilecontig)) {
							mapFileContig2StartIdx.put(curfilecontig, ipos.getAlleleIndex());
							mapFileContig2StartPos.put(curfilecontig, ipos);
							AppContext
									.debug(posidx + ": min start for " + curfilecontig + ": " + ipos.getAlleleIndex());
						} else {
							if (mapFileContig2StartIdx.get(prevfilecontig).compareTo(prevpos.getAlleleIndex()) >= 0) {
								mapFileContig2StartIdx.put(prevfilecontig, prevpos.getAlleleIndex());
								mapFileContig2StartPos.put(prevfilecontig, prevpos);
								AppContext.debug(posidx + ": new min start for " + prevfilecontig + ": "
										+ prevpos.getAlleleIndex());
							}
						}
					}

					lastpos = ipos;
					lastposidx = posidx;
				}

				prevposidx = posidx;
				prevpos = ipos;
				posidx++;

			}
		}

		AppContext.debug("mapFileContig2StartIdx:" + mapFileContig2StartIdx.toString());
		AppContext.debug("mapFileContig2EndIdx:" + mapFileContig2EndIdx.toString());

		posidx = 0;
		lastpos = null;
		lastposidx = 0;
		prevpos = null;
		prevfilecontig = null;
		firstpos = true;

		// if(snpstrSnpsAllele1AllvarsDAO==null) throw new
		// RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");

		StringBuffer strbuffMessage = new StringBuffer();

		AppContext.debug("buffMsg=" + buffMsg.toString());

		if (params.hasSnpList()) {
			strbuffMessage.append("Aligned positions in 5 reference genomes\n");
			strbuffMessage.append("Reference\tJaponica nipponbare\t9311\tIR64-21\tKasalath\tDJ123\n");
		}

		// AppContext.debug("mapFileContig2IdxCount:" +
		// mapFileContig2IdxCount.toString());
		// StringBuffer buff=new StringBuffer();
		itPos = snpsposlist.iterator();
		prevpos = null;
		posidx = 0;
		firstpos = true;
		// lastpos= (SnpsAllvarsMultirefsPos)itPos.next();
		// posidx++;
		while (itPos.hasNext()) {
			SnpsAllvarsMultirefsPos ipos = (SnpsAllvarsMultirefsPos) itPos.next();
			String curfilecontig = ipos.getFileId() + "-" + ipos.getContig() + "-" + posidx;

			if (mapFileContig2StartIdx.containsKey(curfilecontig)) {

				lastpos = mapFileContig2StartPos.get(curfilecontig);
				prevpos = mapFileContig2EndPos.get(curfilecontig);

				if (!lastpos.getContig().equals(prevpos.getContig())) {
					// AppContext.debug("!lastpos.getContig().equals(prevpos.getContig():" +
					// lastpos.getContig() + "," + prevpos.getContig());
					throw new RuntimeException("!lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContig()
							+ "," + prevpos.getContig());
				}

				if (params.hasSnpList())
					strbuffMessage.append(
							params.getOrganism().getName() + "-" + lastpos.getContig() + "-" + lastpos.getPosition() + "\t");
				else
					strbuffMessage.append(params.getOrganism().getName() + "-" + lastpos.getContig() + "[" + lastpos.getPosition()
							+ "-" + prevpos.getPosition() + "] aligned with ");

				StringBuffer noalignbuf = new StringBuffer();

				if (!lastpos.getContigName(AppContext.REFERENCE_NIPPONBARE()).isEmpty()) {
					int aligncount = ipos.getAlignCount(AppContext.REFERENCE_NIPPONBARE());
					String strcount = "";
					if (aligncount > 1)
						strcount = "(" + aligncount + " regions)";
					if (!params.hasSnpList()) {
						if (!lastpos.getContigName(AppContext.REFERENCE_NIPPONBARE())
								.equals(prevpos.getContigName(AppContext.REFERENCE_NIPPONBARE()))) // throw new
																								// RuntimeException("REFERENCE_NIPPONBARE
																								// !lastpos.getContig().equals(prevpos.getContig():"
																								// +
																								// lastpos.getContigName(Organism.REFERENCE_NIPPONBARE)
																								// + "," +
																								// prevpos.getContigName(Organism.REFERENCE_NIPPONBARE));
							strbuffMessage.append(AppContext.REFERENCE_NIPPONBARE() + "-["
									+ lastpos.getContigName(AppContext.REFERENCE_NIPPONBARE()) + " "
									+ lastpos.getPosition(AppContext.REFERENCE_NIPPONBARE()) + "-"
									+ prevpos.getContigName(AppContext.REFERENCE_NIPPONBARE()) + " "
									+ prevpos.getPosition(AppContext.REFERENCE_NIPPONBARE()) + "]" + strcount + ";");
						else
							strbuffMessage.append(AppContext.REFERENCE_NIPPONBARE() + "-"
									+ lastpos.getContigName(AppContext.REFERENCE_NIPPONBARE()) + "["
									+ lastpos.getPosition(AppContext.REFERENCE_NIPPONBARE()) + "-"
									+ prevpos.getPosition(AppContext.REFERENCE_NIPPONBARE()) + "]" + strcount + ";");
					} else
						strbuffMessage.append(lastpos.getContigName(AppContext.REFERENCE_NIPPONBARE()) + "-"
								+ lastpos.getPosition(AppContext.REFERENCE_NIPPONBARE()) + "\t");
				} else {
					if (params.hasSnpList())
						strbuffMessage.append("-\t");
					else
						noalignbuf.append(AppContext.REFERENCE_NIPPONBARE() + ",");
				}

				if (!lastpos.getContigName(Organism.REFERENCE_9311).isEmpty()) {
					int aligncount = ipos.getAlignCount(Organism.REFERENCE_9311);
					String strcount = "";
					if (aligncount > 1)
						strcount = "(" + aligncount + " regions)";
					if (!params.hasSnpList()) {
						if (!lastpos.getContigName(Organism.REFERENCE_9311)
								.equals(prevpos.getContigName(Organism.REFERENCE_9311))) // throw new
																							// RuntimeException("REFERENCE_9311
																							// !lastpos.getContig().equals(prevpos.getContig():"
																							// +
																							// lastpos.getContigName(Organism.REFERENCE_9311)
																							// + "," +
																							// prevpos.getContigName(Organism.REFERENCE_9311));
							strbuffMessage.append(
									Organism.REFERENCE_9311 + "-[" + lastpos.getContigName(Organism.REFERENCE_9311)
											+ " " + lastpos.getPosition(Organism.REFERENCE_9311) + "-"
											+ prevpos.getContigName(Organism.REFERENCE_9311) + " "
											+ prevpos.getPosition(Organism.REFERENCE_9311) + "]" + strcount + ";");
						else
							strbuffMessage.append(
									Organism.REFERENCE_9311 + "-" + lastpos.getContigName(Organism.REFERENCE_9311) + "["
											+ lastpos.getPosition(Organism.REFERENCE_9311) + "-"
											+ prevpos.getPosition(Organism.REFERENCE_9311) + "]" + strcount + ";");
					} else
						strbuffMessage.append(lastpos.getContigName(Organism.REFERENCE_9311) + "-"
								+ lastpos.getPosition(Organism.REFERENCE_9311) + "\t");
				} else {
					if (params.hasSnpList())
						strbuffMessage.append("-\t");
					else
						noalignbuf.append(Organism.REFERENCE_9311 + ",");
				}

				if (!lastpos.getContigName(Organism.REFERENCE_IR64).isEmpty()) {
					int aligncount = ipos.getAlignCount(Organism.REFERENCE_IR64);
					String strcount = "";
					if (aligncount > 1)
						strcount = "(" + aligncount + " regions)";
					if (!params.hasSnpList()) {
						if (!lastpos.getContigName(Organism.REFERENCE_IR64)
								.equals(prevpos.getContigName(Organism.REFERENCE_IR64))) // throw new
																							// RuntimeException("REFERENCE_IR64
																							// !lastpos.getContig().equals(prevpos.getContig():"
																							// +
																							// lastpos.getContigName(Organism.REFERENCE_IR64)
																							// + "," +
																							// prevpos.getContigName(Organism.REFERENCE_IR64));
							strbuffMessage.append(
									Organism.REFERENCE_IR64 + "-[" + lastpos.getContigName(Organism.REFERENCE_IR64)
											+ " " + lastpos.getPosition(Organism.REFERENCE_IR64) + "-"
											+ prevpos.getContigName(Organism.REFERENCE_IR64) + " "
											+ prevpos.getPosition(Organism.REFERENCE_IR64) + "]" + strcount + ";");
						else
							strbuffMessage.append(
									Organism.REFERENCE_IR64 + "-" + lastpos.getContigName(Organism.REFERENCE_IR64) + "["
											+ lastpos.getPosition(Organism.REFERENCE_IR64) + "-"
											+ prevpos.getPosition(Organism.REFERENCE_IR64) + "]" + strcount + ";");
					} else
						strbuffMessage.append(lastpos.getContigName(Organism.REFERENCE_IR64) + "-"
								+ lastpos.getPosition(Organism.REFERENCE_IR64) + "\t");
				} else {
					if (params.hasSnpList())
						strbuffMessage.append("-\t");
					else
						noalignbuf.append(Organism.REFERENCE_IR64 + ",");
				}

				if (!lastpos.getContigName(Organism.REFERENCE_KASALATH).isEmpty()) {
					int aligncount = ipos.getAlignCount(Organism.REFERENCE_KASALATH);
					String strcount = "";
					if (aligncount > 1)
						strcount = "(" + aligncount + " regions)";
					if (!params.hasSnpList()) {
						if (!lastpos.getContigName(Organism.REFERENCE_KASALATH)
								.equals(prevpos.getContigName(Organism.REFERENCE_KASALATH))) // throw new
																								// RuntimeException("REFERENCE_KASALATH
																								// !lastpos.getContig().equals(prevpos.getContig():"
																								// +
																								// lastpos.getContigName(Organism.REFERENCE_KASALATH)
																								// + "," +
																								// prevpos.getContigName(Organism.REFERENCE_KASALATH));
							strbuffMessage.append(Organism.REFERENCE_KASALATH + "-["
									+ lastpos.getContigName(Organism.REFERENCE_KASALATH) + " "
									+ lastpos.getPosition(Organism.REFERENCE_KASALATH) + "-"
									+ prevpos.getContigName(Organism.REFERENCE_KASALATH) + " "
									+ prevpos.getPosition(Organism.REFERENCE_KASALATH) + "]" + strcount + ";");
						else
							strbuffMessage.append(Organism.REFERENCE_KASALATH + "-"
									+ lastpos.getContigName(Organism.REFERENCE_KASALATH) + "["
									+ lastpos.getPosition(Organism.REFERENCE_KASALATH) + "-"
									+ prevpos.getPosition(Organism.REFERENCE_KASALATH) + "]" + strcount + ";");
					} else
						strbuffMessage.append(lastpos.getContigName(Organism.REFERENCE_KASALATH) + "-"
								+ lastpos.getPosition(Organism.REFERENCE_KASALATH) + "\t");
				} else {
					if (params.hasSnpList())
						strbuffMessage.append("-\t");
					else
						noalignbuf.append(Organism.REFERENCE_KASALATH + ",");
				}

				if (!lastpos.getContigName(Organism.REFERENCE_DJ123).isEmpty()) {
					int aligncount = ipos.getAlignCount(Organism.REFERENCE_DJ123);
					String strcount = "";
					if (aligncount > 1)
						strcount = "(" + aligncount + " regions)";
					if (!params.hasSnpList()) {
						if (!lastpos.getContigName(Organism.REFERENCE_DJ123)
								.equals(prevpos.getContigName(Organism.REFERENCE_DJ123))) // throw new
																							// RuntimeException("REFERENCE_DJ123
																							// !lastpos.getContig().equals(prevpos.getContig():"
																							// +
																							// lastpos.getContigName(Organism.REFERENCE_DJ123)
																							// + "," +
																							// prevpos.getContigName(Organism.REFERENCE_DJ123));
							strbuffMessage.append(
									Organism.REFERENCE_DJ123 + "-[" + lastpos.getContigName(Organism.REFERENCE_DJ123)
											+ " " + lastpos.getPosition(Organism.REFERENCE_DJ123) + "-"
											+ prevpos.getContigName(Organism.REFERENCE_DJ123) + " "
											+ prevpos.getPosition(Organism.REFERENCE_DJ123) + "]" + strcount + ";");
						else
							strbuffMessage.append(
									Organism.REFERENCE_DJ123 + "-" + lastpos.getContigName(Organism.REFERENCE_DJ123)
											+ "[" + lastpos.getPosition(Organism.REFERENCE_DJ123) + "-"
											+ prevpos.getPosition(Organism.REFERENCE_DJ123) + "]" + strcount + ";");
					} else
						strbuffMessage.append(lastpos.getContigName(Organism.REFERENCE_DJ123) + "-"
								+ lastpos.getPosition(Organism.REFERENCE_DJ123) + "\t");
				} else {
					if (params.hasSnpList())
						strbuffMessage.append("-\t");
					else
						noalignbuf.append(Organism.REFERENCE_DJ123 + ",");
				}

				if (noalignbuf.length() > 0)
					strbuffMessage.append(" No alignment with " + noalignbuf.substring(0, noalignbuf.length() - 1));
				strbuffMessage.append("\n");
				lastpos = prevpos;
			}

			prevpos = ipos;
			posidx++;
		}

		// AppContext.debug("mapPosidx2FileContigIdx:\nfileId, fromContigId,
		// fromPosition, filecontCount, filecolidx, filecontig\t\t\tfile:colidx
		// range[-]\tsnpstring:[-]\n" + buff);

		// initialize mapVar2SnpStringBuffer

		Map<BigDecimal, StringBuffer> mapVar2SnpStringBuffer = null;

		// Map<String, Map<BigDecimal, String>> mapFileContig2MapVar2Snpstring=new
		// HashMap();
		posidx = 0;
		lastpos = null;
		lastposidx = 0;
		prevposidx = 0;
		prevpos = null;
		prevfilecontig = null;
		firstpos = true;

		StringBuffer strRefbuff = new StringBuffer();

		Map<BigDecimal, Position> mapSnpid2Pos = new HashMap();

		AppContext.debug("constructing snpstring for " + snpsposlist.size() + " positions");
		int apendops = 0;

		if (params.hasSnpList()) {
			Map<BigDecimal, String> mapVar2SnpString = null;
			BigDecimal typeid = snplisttypeid;
			H5Dataset hdf5dao = mapFileId2DatasetDAO.get(typeid);

			/*
			 * if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KALLSNP_HDF5_V2 ) ) {
			 * nballsnpstransdao= (H5Dataset)AppContext.checkBean(nballsnpstransdao,
			 * "H5SNPUniIUPACV2TransposedDAO"); hdf5dao=nballsnpstransdao; } else if(
			 * typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KCORESNP_HDF5_V2 ) ) {
			 * nbcoresnpstransdao= (H5Dataset)AppContext.checkBean(nbcoresnpstransdao,
			 * "H5SNPCoreIUPACV2TransposedDAO"); hdf5dao=nbcoresnpsdao; }
			 */

			if (params.hasVarlist()) {
				AppContext.resetTimer("using multi readSNPString1 start");
				mapVar2SnpString = hdf5dao.readSNPString(organism.getOrganismId().intValue(), (Set) params.getColVarIds(), params.getsChr(), indxs);
				AppContext.resetTimer("using multi readSNPString1 end");
			} else if (params.getVaridStartEnd() != null) {
				AppContext.resetTimer("using multi readSNPString3 start");
				Integer startendidx[] = params.getVaridStartEnd();
				mapVar2SnpString = hdf5dao.readSNPString(params.getsChr(), indxs, startendidx[0], startendidx[1]);
				AppContext.resetTimer("using multi readSNPString3 end");
			} else {
				AppContext.resetTimer("using multi readSNPString2 start");
				mapVar2SnpString = hdf5dao.readSNPString(organism.getOrganismId().intValue(),params.getsChr(), indxs);
				AppContext.resetTimer("using multi readSNPString2 end");
			}
		} else {

			itPos = snpsposlist.iterator();
			while (itPos.hasNext()) {

				SnpsAllvarsMultirefsPos ipos = (SnpsAllvarsMultirefsPos) itPos.next();

				mapSnpid2Pos.put(ipos.getSnpFeatureId(), ipos);

				strRefbuff.append(ipos.getRefnuc());
				String curfilecontig = ipos.getFileId() + "-" + ipos.getContig() + "-" + posidx; // + "-" +
																									// ipos.getPosition();

				if (mapFileContig2StartIdx.containsKey(curfilecontig)) {

					// Map<BigDecimal,String> mapVar2SnpString = mapFileContig2MapVar2Snpstring.get(
					// curfilecontig );
					BigDecimal typeid = BigDecimal.valueOf(Long.valueOf(curfilecontig.split("\\-")[0]));
					if (!typeid.equals(ipos.getFileId()))
						throw new RuntimeException("!typeid.equals(ipos.getFileId()) at " + ipos);
					// if(mapVar2SnpString==null) {

					Map<BigDecimal, String> mapVar2SnpString = null;
					H5Dataset hdf5dao = null;
					if (params.hasSnpList()) {

						/*
						 * if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KALLSNP_HDF5_V2 ) ) {
						 * nballsnpstransdao= (H5Dataset)AppContext.checkBean(nballsnpstransdao,
						 * "H5SNPUniIUPACV2TransposedDAO"); hdf5dao=nballsnpstransdao; } else if(
						 * typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KCORESNP_HDF5_V2 ) ) {
						 * nbcoresnpstransdao= (H5Dataset)AppContext.checkBean(nbcoresnpstransdao,
						 * "H5SNPCoreIUPACV2TransposedDAO"); hdf5dao=nbcoresnpsdao; }
						 * 
						 * if( params.hasVarlist() ) {
						 * AppContext.resetTimer("using multi readSNPString1 start"); mapVar2SnpString =
						 * hdf5dao.readSNPString((Set)params.getColVarIds(), params.getsChr(), indxs);
						 * AppContext.resetTimer("using multi readSNPString1 end"); } else
						 * if(params.getVaridStartEnd()!=null) {
						 * AppContext.resetTimer("using multi readSNPString3 start"); Integer
						 * startendidx[]=params.getVaridStartEnd(); mapVar2SnpString =
						 * hdf5dao.readSNPString(params.getsChr(), indxs,
						 * startendidx[0],startendidx[1]);
						 * AppContext.resetTimer("using multi readSNPString3 end"); } else {
						 * AppContext.resetTimer("using multi readSNPString2 start"); mapVar2SnpString =
						 * hdf5dao.readSNPString(params.getsChr(), indxs);
						 * AppContext.resetTimer("using multi readSNPString2 end"); }
						 */

					} else {
						hdf5dao = mapFileId2DatasetDAO.get(typeid);
						/*
						 * if(hdf5dao==null) {
						 * 
						 * if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KALLSNP_HDF5_V2 ) ) {
						 * nballsnpsdao=
						 * (H5Dataset)AppContext.checkBean(nballsnpsdao,"H5SNPUniIUPACV2DAO");
						 * hdf5dao=nballsnpsdao; } else if( typeid.equals(
						 * SnpsAllvarsMultirefsPosDAO.TYPE_3KCORESNP_HDF5_V2 ) ) { nbcoresnpsdao=
						 * (H5Dataset)AppContext.checkBean(nbcoresnpsdao,"H5SNPCoreIUPACV2DAO");
						 * hdf5dao=nbcoresnpsdao; } else hdf5dao = new H5Dataset(
						 * SnpsAllvarsMultirefsPosDAO.MAP_TYPE2HDF5FILE.get(typeid ));
						 * mapFileId2DatasetDAO.put(typeid, hdf5dao); }
						 */
						mapVar2SnpString = hdf5dao.readSNPString(organism.getOrganismId().intValue(),null,
								mapFileContig2StartIdx.get(curfilecontig).intValue(),
								mapFileContig2EndIdx.get(curfilecontig).intValue());
					}
					// }

					// mapFileContig2MapVar2Snpstring.put(curfilecontig , mapVar2SnpString);
					// }

					// initialize string buffer for all vars
					if (mapVar2SnpStringBuffer == null) {
						mapVar2SnpStringBuffer = new HashMap();
						Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
						while (itVar.hasNext()) {
							mapVar2SnpStringBuffer.put(itVar.next(), new StringBuffer());
						}
					}

					int idxstart = mapFileContig2StartIdx.get(curfilecontig).intValue();
					int idxend = mapFileContig2EndIdx.get(curfilecontig).intValue();

					AppContext.debugIterate("at posidx=" + posidx + " appending from " + curfilecontig + "[" + idxstart
							+ "-" + idxend + "]");

					Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
					while (itVar.hasNext()) {
						BigDecimal varid = itVar.next();
						// {ipos.getFileId(), ipos.getContig(), mapFileContig2IdxCount.get(filecontig)}
						mapVar2SnpStringBuffer.get(varid).append(mapVar2SnpString.get(varid)); // .substring(idxstart ,
																								// idxend+1 ));

						if (!itVar.hasNext()) {
							if (prevpos != null)
								AppContext.debugIterate(
										"filling table: pos[" + prevpos.getPosition() + "-" + ipos.getPosition() + "]  "
												+ curfilecontig + " colidx=[" + idxstart + "-" + idxend + "]");
							AppContext.debugIterate(
									"mapVar2SnpString.get(varid).length=" + mapVar2SnpString.get(varid).length());
							AppContext.debugIterate(
									"mapVar2SnpStringBuffer length=" + mapVar2SnpStringBuffer.get(varid).length());
							/*
							 * if(posidx != mapVar2SnpStringBuffer.get(varid).length() && itPos.hasNext()) {
							 * AppContext.
							 * debug("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" +
							 * posidx); throw new
							 * RuntimeException("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx="
							 * + (posidx)); }
							 */
						}
					}
					lastpos = ipos;
					// lastPosIdx=posidx;
					lastposidx = posidx;
					apendops++;
				} else {
					AppContext.debugIterate(curfilecontig + " not in " + mapFileContig2StartIdx);
				}

				prevpos = ipos;
				posidx++;
			}

		}

		AppContext.debug(posidx + " positions done, " + apendops + " append operations");

		Map mapVarid2Snpsstr = new HashMap();
		Iterator<BigDecimal> itVar = mapVar2SnpStringBuffer.keySet().iterator();
		while (itVar.hasNext()) {
			BigDecimal ivar = itVar.next();
			mapVarid2Snpsstr.put(ivar, mapVar2SnpStringBuffer.get(ivar).toString());
		}

		// get allele column indices from start to end positions
		SnpsAllvarsPos startpos = snpsposlist.get(0);
		SnpsAllvarsPos endpos = snpsposlist.get(snpsposlist.size() - 1);

		String strRef = strRefbuff.toString();

		int refLength = -1;

		AppContext.debug(
				snpsposlist.size() + " snpposlist, pos between " + startpos.getPosition() + "-" + endpos.getPosition()
						+ "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());

		Map mapVarid2Snpsstr_allele2 = null;
		Map<BigDecimal, Map> mapVarid2SnpsAllele2str = new HashMap();

		if (mapVarid2Snpsstr_allele2 == null && params.includeDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC)) {

			Map mapVarid2SnpsstrSplitIUPAC = new LinkedHashMap();
			mapVarid2Snpsstr_allele2 = new LinkedHashMap();

			Iterator itVarid = mapVarid2Snpsstr.keySet().iterator();
			while (itVarid.hasNext()) {
				BigDecimal varid = (BigDecimal) itVarid.next();

				// if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {
				// revised for SNPv2 (allele2!=0 for allele1==allele2)
				String allele1str = (String) mapVarid2Snpsstr.get(varid);

				// String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
				// if(allele1str.length()!=allele2str.length()) throw new RuntimeException(
				// "allele1str.length()!=allele2str.length(): " + allele1str.length() + ", " +
				// allele2str.length());

				StringBuffer buffNewStr = new StringBuffer();
				StringBuffer buffNewStr2 = new StringBuffer();
				for (int iStr = 0; iStr < allele1str.length(); iStr++) {
					// char allele2i =allele2str.charAt(iStr);
					char allele1i = allele1str.charAt(iStr);
					String alleles12 = AppContext.getNucsFromIUPAC(allele1i);
					if (alleles12.length() == 2) {
						Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get(varid);
						if (mapTableidx2Nuc == null) {
							mapTableidx2Nuc = new HashMap();
							mapVarid2SnpsAllele2str.put(varid, mapTableidx2Nuc);
						}
						mapTableidx2Nuc.put(snpsposlist.get(iStr), alleles12.charAt(1));
						buffNewStr2.append(alleles12.charAt(1));
					} else
						buffNewStr2.append(alleles12.charAt(0));

					buffNewStr.append(alleles12.charAt(0));
				}

				mapVarid2SnpsstrSplitIUPAC.put(varid, buffNewStr.toString());
				mapVarid2Snpsstr_allele2.put(varid, buffNewStr2.toString());

				if (!itVarid.hasNext())
					AppContext.debug(varid + "  length=" + buffNewStr.length() + ", " + buffNewStr2.length());

				// if(!allele1str.equals(buffNewStr.toString())) {
				// AppContext.debug( "varid=" + varid);
				// AppContext.debug( "alleleIUPACstr=" + allele1str);
				// AppContext.debug( "allele1 str=" + buffNewStr);
				// AppContext.debug( "allele2 str=" + buffNewStr2);
				// }
				// }
			}

			mapVarid2Snpsstr = mapVarid2SnpsstrSplitIUPAC;

		} // else throw new RuntimeException("heteroSnps==null and
			// mapVarid2Snpsstr_allele2==null ... no allele2 data");

		AppContext.debug("snpsposlist length=" + snpsposlist.size());

		// generate pos2alleles

		Map<Position, Set<Character>> mapPos2Allele = new TreeMap();
		Map<Position, Set<Character>> mapPos2AlleleHetero = new TreeMap();

		itVar = mapVarid2Snpsstr.keySet().iterator();
		while (itVar.hasNext()) {
			BigDecimal varid = itVar.next();
			String allele1str = (String) mapVarid2Snpsstr.get(varid);
			String allele2str = (String) mapVarid2Snpsstr_allele2.get(varid);
			Iterator<SnpsAllvarsPos> itSnppos = snpsposlist.iterator();
			int icount = 0;
			while (itSnppos.hasNext()) {
				SnpsAllvarsPos snppos = itSnppos.next();

				Set setAlleles = mapPos2Allele.get(snppos);
				if (setAlleles == null) {
					setAlleles = new HashSet();
					mapPos2Allele.put(snppos, setAlleles);
				}
				// hdf5 format v2
				char chari1 = allele1str.charAt(icount);
				if (chari1 != '0' && chari1 != '?')
					setAlleles.add(chari1);
				char chari2 = allele2str.charAt(icount);
				if (chari2 != '0' && chari2 != '?')
					setAlleles.add(chari2);

				if (chari1 != chari2) {
					if (chari1 != snppos.getRefnuc().charAt(0)) {
						Set allele2 = mapPos2AlleleHetero.get(snppos);
						if (allele2 == null) {
							allele2 = new HashSet();
							mapPos2AlleleHetero.put(snppos, allele2);
						}
						allele2.add(chari1);
					}
					if (chari2 != snppos.getRefnuc().charAt(0)) {
						Set allele2 = mapPos2AlleleHetero.get(snppos);
						if (allele2 == null) {
							allele2 = new HashSet();
							mapPos2AlleleHetero.put(snppos, allele2);
						}
						allele2.add(chari2);
					}
				}
				icount++;
			}
		}

		Map<Position, Set<Character>> mapPos2NonsynAlleles;
		Map<Position, Set<Character>> mapPos2SynAlleles; // =new HashMap();
		Set setSpliceAcceptorsPos;
		// Set setSnpInExonPos;
		Set setSpliceDonorsPos;

		if (params.hasPreviousData()) {
			VariantStringData prevdata = params.getVariantdata();
			mapPos2NonsynAlleles = prevdata.getMapPos2NonsynAlleles();
			setSpliceAcceptorsPos = prevdata.getSetSnpSpliceAcceptorPos();
			setSpliceDonorsPos = prevdata.getSetSnpSpliceDonorPos();
			// setSnpInExonPos = prevdata.getSetSnpInExonPos();
			mapPos2SynAlleles = prevdata.getMapPos2SynAlleles();
			strbuffMessage = new StringBuffer();
			strbuffMessage.append(prevdata.getMessage());
		} else {

			// mapPos2NonsynAlleles, mapPos2SynAlleles, setSnpInExonPos, setSpliceDonorsPos,
			// setSpliceAcceptorsPos,
			setSpliceAcceptorsPos = new HashSet();
			setSpliceDonorsPos = new HashSet();
			mapPos2NonsynAlleles = new TreeMap();
			mapPos2SynAlleles = new TreeMap();
			// setSnpInExonPos = new TreeSet();

			if (params.getOrganism().getName().equals(AppContext.JAPONICA_NIPPONBARE)) {

				snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "VSnpNonsynallelePosDAO");
				// snpssynDAO = (SnpsSynAllvarsDAO) AppContext.checkBean(snpssynDAO,
				// "VSnpSynalleleDAO");
				// snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO,
				// "SnpsInExonDAO");
				// snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO,
				// "SnpsHeteroAllvarsDAO");

				Set setSnpid = new LinkedHashSet();
				Iterator<SnpsAllvarsPos> itSnp = snpsposlist.iterator();
				while (itSnp.hasNext()) {
					setSnpid.add(itSnp.next().getSnpFeatureId());
				}

				// Set heteroSnps = null;
				Set nonsynAllele = null;
				Set synAllele = new HashSet();
				// Set inexonSnps = null;

				// using hdf5
				if ((setPositions != null && !setPositions.isEmpty()) || (colLocus != null && !colLocus.isEmpty())) {

					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByFeatureidIn(organism.getOrganismId().intValue(),setSnpid); // , snptype);
					/*
					 * if(setPositions!=null && !setPositions.isEmpty()) { nonsynAllele =
					 * snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos, snptype); synAllele
					 * = snpssynDAO.findSnpSynAlleleByChrPosIn(chr, listpos, snptype); } else
					 * if(colLocus!=null && !colLocus.isEmpty()) { nonsynAllele =
					 * snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, colLocus, snptype);
					 * synAllele = snpssynDAO.findSnpSynAlleleByChrPosIn(chr, colLocus, snptype); }
					 */
					AppContext.resetTimer(
							"to read nonsynonymous allele, inexon  from  database using findSnpNonsynAlleleByFeatureidIn");
				} else {
					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(organism.getOrganismId().intValue(), chr,
							startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
					// synAllele = snpssynDAO.findSnpSynAlleleByChrPosBetween(chr,
					// startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
					AppContext.resetTimer(
							"to read nonsynonymous allele, inexon  from  database using findSnpNonsynAlleleByChrPosBetween");
				}

				// non-synonymous alleles for positions
				// setSnpInExonPos = new TreeSet();

				Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
				while (itNonsyn.hasNext()) {
					SnpsNonsynAllele nonsynallele = itNonsyn.next();

					if (mapSnpid2Pos == null)
						throw new RuntimeException("mapSnpid2Pos==null");
					if (nonsynallele == null)
						throw new RuntimeException("nonsynallele==null");
					if (nonsynallele.getSnpFeatureId() == null)
						throw new RuntimeException("nonsynallele.getSnp()==null");

					Position pos = mapSnpid2Pos.get(nonsynallele.getSnpFeatureId());

					if (pos == null) {
						AppContext.debug("nonsynallele.getSnpFeatureId():" + nonsynallele.getSnpFeatureId() + " "
								+ nonsynallele.getSnpFeatureId().getClass() + " not in mapSnpid2Pos . size="
								+ mapSnpid2Pos.size());
						AppContext.debug("mapSnpid2Pos.keySet()=" + mapSnpid2Pos.keySet());

						pos = mapSnpid2Pos
								.get(BigDecimal.valueOf(Long.valueOf(nonsynallele.getSnpFeatureId().longValue())));
						if (pos == null)
							throw new RuntimeException("nonsynallele.getSnpFeatureId() not found.. in both");

						AppContext.debug("found using :"
								+ BigDecimal.valueOf(Long.valueOf(nonsynallele.getSnpFeatureId().longValue())));

					}

					// assuming all nonsyn are in exon
					// setSnpInExonPos.add( pos );

					Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
					if (setVarietyAlleles == null)
						throw new RuntimeException("setVarietyAlleles==null");
					if (nonsynallele.getAllele() == '\0')
						throw new RuntimeException("nonsynallele.getAllele()==null");

					if (setVarietyAlleles.contains(nonsynallele.getAllele())) {

						Set<Character> alleles = mapPos2NonsynAlleles.get(pos);
						if (alleles == null) {
							alleles = new HashSet();
							mapPos2NonsynAlleles.put(pos, alleles);
						}
						alleles.add(nonsynallele.getAllele());
					}
				}

				Iterator<SnpsSynAllele> itSyn = synAllele.iterator();
				while (itSyn.hasNext()) {
					SnpsSynAllele synallele = itSyn.next();
					Position pos = mapSnpid2Pos.get(synallele.getSnpFeatureId());

					// assuming all syn are in exon
					// setSnpInExonPos.add( pos );

					Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);

					if (setVarietyAlleles.contains(synallele.getAllele())) {
						Set<Character> alleles = mapPos2SynAlleles.get(pos);
						if (alleles == null) {
							alleles = new HashSet();
							mapPos2SynAlleles.put(pos, alleles);
						}
						alleles.add(synallele.getAllele());
					}
				}

				if (nonsynAllele != null)
					AppContext.debug(nonsynAllele.size() + " non-synonymous alleles, " + mapPos2NonsynAlleles.size()
							+ " positions/idx,  " + mapPos2NonsynAlleles.size() + "  nonsys alleles positions");

				// get splice variants

				Set setSpliceAcceptors = new HashSet();
				Set setSpliceDonors = new HashSet();

				snpsspliceacceptorDAO = (SnpsSpliceAcceptorDAO) AppContext.checkBean(snpsspliceacceptorDAO,
						"SnpsSpliceAcceptorDAO");
				snpssplicedonorDAO = (SnpsSpliceDonorDAO) AppContext.checkBean(snpssplicedonorDAO,
						"SnpsSpliceDonorDAO");
				if (setPositions != null && !setPositions.isEmpty()) {
					/*
					 * setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, setPositions);
					 * setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, setPositions);
					 */
					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsByFeatureidIn(organism.getOrganismId().intValue(),setSnpid); // .getSNPsIn(chr,
																								// setPositions);
					setSpliceDonors = snpssplicedonorDAO.getSNPsByFeatureidIn(organism.getOrganismId().intValue(),setSnpid); // .getSNPsIn(chr,
																							// setPositions);

				} else if (colLocus != null && !colLocus.isEmpty()) {
					/*
					 * setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, colLocus);
					 * setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, colLocus);
					 */
					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsByFeatureidIn(organism.getOrganismId().intValue(),setSnpid); // .getSNPsIn(chr,
																								// setPositions);
					setSpliceDonors = snpssplicedonorDAO.getSNPsByFeatureidIn(organism.getOrganismId().intValue(),setSnpid); // .getSNPsIn(chr,
																							// setPositions);
				} else {
					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsBetween(organism.getOrganismId().intValue(),chr, start.intValue(), end.intValue(),
							snptype);
					setSpliceDonors = snpssplicedonorDAO.getSNPsBetween(organism.getOrganismId().intValue(),chr, start.intValue(), end.intValue(), snptype);
				}

				setSpliceAcceptorsPos = new HashSet(setSpliceAcceptors);
				setSpliceDonorsPos = new HashSet(setSpliceDonors);
				/*
				 * Iterator<SnpsSpliceAcceptor> itAcceptors = setSpliceAcceptors.iterator();
				 * while(itAcceptors.hasNext()) { SnpsSpliceAcceptor acc = itAcceptors.next();
				 * setSpliceAcceptorsPos.add( acc.getPos()); } Iterator<SnpsSpliceDonor> itDonor
				 * = setSpliceDonors.iterator(); while(itDonor.hasNext()) { SnpsSpliceDonor acc
				 * = itDonor.next(); setSpliceDonorsPos.add( acc.getPos()); }
				 */
			}

			if (AppContext.isLocalhost()) {
				AppContext.debug("mapPos2NonsynAlleles " + mapPos2NonsynAlleles.size()); // +": " +
																							// mapPos2NonsynAlleles.toString()
																							// );
				AppContext.debug("mapPos2SynAlleles " + mapPos2SynAlleles.size()); // + ": " +
																					// mapPos2SynAlleles.toString() );
				AppContext.debug("mapPos2Allele " + mapPos2Allele.size()); // + ": " + mapPos2Allele.toString() );
				AppContext.debug("mapPos2AlleleHetero " + mapPos2AlleleHetero.size()); // +": " +
																						// mapPos2AlleleHetero.toString()
																						// );
				AppContext.debug("setSpliceDonorsPos " + setSpliceDonorsPos.size()); // +": " +
																						// setSpliceDonorsPos.toString()
																						// );
				AppContext.debug("setSpliceAcceptorsPos " + setSpliceAcceptorsPos.size()); // +": " +
																							// setSpliceAcceptorsPos.toString()
																							// );
				// AppContext.debug( nonsynAllele.toString() );
			}

		}

		strbuffMessage.append("\n").append(buffMsg);

		SNPsStringData snpstrdata = new SNPsStringData(snpsposlist, strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str,
				mapPos2Allele, mapPos2NonsynAlleles, mapPos2SynAlleles, setSpliceDonorsPos, setSpliceAcceptorsPos,
				strbuffMessage.toString());

		return snpstrdata;
	}

	/**
	 * Count mismatch between nucelotide sequences, based on several criteria
	 * 
	 * @param var1                 variety 1 allele1 string
	 * @param var2                 variety 2 allele1 string
	 * @param var1isref            variety 1 is reference
	 * @param var1allele2str       variety 1 allele2 string
	 * @param var2allele2str       variety 2 allele2 string
	 * @param mapIdx2NonsynAlleles map table index 2 nonsysynonymous nucleotide set
	 * @param setSnpInExonTableIdx set of table indices in exon
	 * @param setNonsynIdx         set of table indices with nonsynonymous (return
	 *                             value)
	 * @param isNonsynOnly         include only nonsynonymous
	 * @param isColorSynGray       color nonsynonymous as gray
	 * @return
	 */

	public static double[] countVarpairMismatch(List listpos, String var1, String var2, boolean var1isref,
			Map<Position, Character> var1allele2str, Map<Position, Character> var2allele2str,
			Map<Position, Set<Character>> mapPos2NonsynAlleles, Set<Position> setNonsynPos, boolean isNonsynOnly,
			boolean countMissing05) {

		double misCount = 0;
		double matchCount = 0;
		for (int iStr = 0; iStr < var2.length(); iStr++) {
			char var1char = var1.charAt(iStr);
			char var2char = var2.charAt(iStr);

			// boolean snpInExon = false;
			// if(setSnpInExonTableIdx==null || (setSnpInExonTableIdx!=null &&
			// setSnpInExonTableIdx.contains(iStr))) snpInExon=true;

			Position pos = (Position) listpos.get(iStr);

			Boolean isNonsyn[] = new Boolean[2];
			isNonsyn[0] = false;
			isNonsyn[1] = false;
			Character var1allele2 = null;
			if (!var1isref && var1allele2str != null)
				var1allele2 = var1allele2str.get(iStr);

			Character var2allele2 = null;
			if (var2allele2str != null)
				var2allele2 = var2allele2str.get(pos);
			Set setNonsyns = mapPos2NonsynAlleles.get(pos);

			double mismatchCount[] = countVarpairMismatchNuc(var1.charAt(iStr), var2.charAt(iStr), var1isref,
					var1allele2, var2allele2, setNonsyns, isNonsyn, isNonsynOnly, countMissing05);

			// AppContext.debug( "var1=" + var1.charAt(iStr) +"/" + var1allele2 + ", var2="
			// + var2.charAt(iStr)+"/"+ var2allele2 + " mis=" + mismatchCount[0] + ",match="
			// + mismatchCount[1] + ", 0.5missing="+countMissing05);

			misCount += mismatchCount[0];
			matchCount += mismatchCount[1];

			if (isNonsyn[0] || isNonsyn[1])
				setNonsynPos.add(pos);
		}

		return new double[] { misCount, matchCount };
	}

	/**
	 * Compare two nucleotides, heterozygous are counted as 0.5 if one allele is
	 * mismatched
	 * 
	 * @param var1char
	 * @param var2char
	 * @param var1isref
	 * @param var1allele2
	 * @param var2allele2
	 * @param setNonsynAlleles
	 * @param snpInExon
	 * @param isNonsyn
	 * @param isNonsynOnly     (synonymous are treated as equal)
	 * @param countMissing05   count missing allele as 0.5
	 * @return
	 */
	/*
	 * public static double[] countVarpairMismatchNuc(char var1char, char var2char ,
	 * boolean var1isref,Character var1allele2, Character var2allele2,
	 * Set<Character> setNonsynAlleles, boolean snpInExon, Boolean isNonsyn[],
	 * boolean isNonsynOnly) { return countVarpairMismatchNuc( var1char, var2char ,
	 * var1isref, var1allele2, var2allele2, setNonsynAlleles, snpInExon, isNonsyn,
	 * isNonsynOnly, false); }
	 */
	// [mismatch,match]
	public static double[] countVarpairMismatchNuc(char var1char, char var2char, boolean var1isref,
			Character var1allele2, Character var2allele2, Set<Character> setNonsynAlleles, Boolean isNonsyn[],
			boolean isNonsynOnly, boolean countMissing05) {
		double misCount = 0;
		double matchCount = 0;

		Character var2allele1 = null;
		Character var1allele1 = null;

		isNonsyn[0] = false;
		isNonsyn[1] = false;
		// boolNonsyn = false;

		// initialize missing to null, * to homozygous allele
		if (!charMissing.contains(var1char))
			var1allele1 = var1char;
		if (!charMissing.contains(var2char))
			var2allele1 = var2char;

		if (var2allele2 != null) {
			if (charSameasAllele1.contains(var2allele2))
				var2allele2 = var2allele1;
			else if (charMissing.contains(var2allele2))
				var2allele2 = null;
		} else
			var2allele2 = var2allele1;

		if (var1allele2 != null) {
			if (charSameasAllele1.contains(var1allele2))
				var1allele2 = var1allele1;
			else if (charMissing.contains(var1allele2))
				var1allele2 = null;
		} else
			var1allele2 = var1allele1;

		// check for non-syn
		// if(isNonsynOnly) {
		if (true) {
			// reference comparison
			if (var1isref) {
				if (var2allele1 != null) {
					if (!charSymbols.contains(var2allele1)) {
						if (true) {
							// idx in exon
							if (setNonsynAlleles != null && (setNonsynAlleles.contains(var2allele1)))
								// var2 allele1 or allele2 in nonsynonymous
								isNonsyn[0] = true;
							if (var2allele2 != null && setNonsynAlleles != null && !charSymbols.contains(var2allele2)
									&& setNonsynAlleles.contains(var2allele2))
								// var2 allele1 or allele2 in nonsynonymous
								isNonsyn[1] = true;
						} else {
							// not in exon, OR no exon information, include in nonsynonymous
							// isNonsyn[0]=true;
							// isNonsyn[1]=true;
						}
					}

					// if allele1 and allele2 are both synonymous
					if (!isNonsyn[0] && !isNonsyn[1] && isNonsynOnly)
						return new double[] { 0, 1 };

				} else {
					if (countMissing05) {
						return new double[] { 0.5, 0.5 };
					} else {
						return new double[] { 0, 0 };
					}
				}
			} else {
				// pairwise comparison
				if (var1allele1 != null) {
					if (setNonsynAlleles != null && (setNonsynAlleles.contains(var1allele1)
							|| (var1allele2 != null && setNonsynAlleles.contains(var1allele2))))
						// var1 is not reference, and var1 allele1 or allele2 in nonsynonymous
						isNonsyn[0] = true;
				}

				if (var2allele1 != null) {
					if (setNonsynAlleles != null && (setNonsynAlleles.contains(var2allele1)
							|| (var2allele2 != null && setNonsynAlleles.contains(var2allele2))))
						// var1 is not reference, and var1 allele1 or allele2 in nonsynonymous
						isNonsyn[1] = true;
				}

				// if one or both are missing
				if (var1allele1 == null || var2allele1 == null) {
					if (countMissing05) {
						return new double[] { 0.5, 0.5 };
					} else {
						return new double[] { 0, 0 };
					}
				}

				// if allele1 and allele2 are both synonymous
				if (!isNonsyn[0] && !isNonsyn[1] && isNonsynOnly)
					return new double[] { 0, 1 };
			}
			// synonymous, not mismatch
			// if(!isNonsyn[0] && !isNonsyn[1]) return new double[]{0,1};
		}
		// if(isNonsynOnly && !isNonsyn[0] && !isNonsyn[1]) return 0;

		if (var1isref) {
			// compare with reference

			// assump: no 0 * . $ characters in reference
			// if homozygous, mismatch allele1, miscount +1
			// if heterozygous, match allele1 or allele2, miscount +0.5
			// if not nonsynonymos and isNonsynOnly , no count

			if (var2allele1 == null) {
				if (countMissing05) {
					misCount += 0.5;
					matchCount += 0.5;
				}
			} else {
				// var2allele1 IS NOT MISSING
				if (var1allele1 == var2allele1)
					matchCount += 0.5;
				else
					misCount += 0.5;

				if (var2allele2 != null) {
					if (var1allele1 == var2allele2)
						matchCount += 0.5;
					else
						misCount += 0.5;
				} else if (var2allele2 == null && var2allele1 != null) {
					throw new RuntimeException("Unexpected case: var2allele1!=null=" + var2allele1
							+ " ,var2allele2==null, var1allele1=" + var1allele1 + ", var1allele2=" + var1allele2);
				}
				// else throw new RuntimeException("Unexpected case:
				// var2allele1!=null,var2allele2==null");
			}

		} else {
			// pairwise comparison

			if (charMissing.contains(var1allele1) || charMissing.contains(var2allele1) || var1allele1 == null
					|| var2allele1 == null) {
				if (countMissing05) {
					misCount += 0.5;
					matchCount += 0.5;
				}
			} else {
				if ((var1allele1 == var2allele1 && var1allele2 == var2allele2)
						|| (var1allele1 == var2allele2 && var1allele2 == var2allele1)) {
					matchCount += 1;
				} else if ((var1allele1 == var2allele1 && var1allele2 != var2allele2)
						|| (var1allele1 == var2allele2 && var1allele2 != var2allele1)) {
					matchCount += 0.5;
					misCount += 0.5;
				} else {
					misCount += 1;
				}

			}

		}
		return new double[] { misCount, matchCount };
	}
}
