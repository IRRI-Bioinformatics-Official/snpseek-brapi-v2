package org.irri.iric.portal.genotype.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.irri.iric.ds.chado.dao.SnpsAllvarsPosDAO;
import org.irri.iric.ds.chado.dao.SnpsEffectDAO;
import org.irri.iric.ds.chado.dao.SnpsNonsynAllvarsDAO;
import org.irri.iric.ds.chado.dao.SnpsSpliceAcceptorDAO;
import org.irri.iric.ds.chado.dao.SnpsSpliceDonorDAO;
import org.irri.iric.ds.chado.dao.SnpsStringDAO;
import org.irri.iric.ds.chado.domain.GenotypeRunPlatform;
import org.irri.iric.ds.chado.domain.MultiReferenceLocus;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.irri.iric.ds.chado.domain.SnpsNonsynAllele;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.SnpsSynAllele;
import org.irri.iric.ds.chado.domain.StockSample;
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
import org.irri.iric.portal.hdf5.H5ReadCharTransMatrix;
import org.irri.iric.portal.hdf5.H5ReadCharmatrix;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("SnpsStringNPBService")
public class SnpsStringHDF5nRDBMSHybridService implements VariantStringService {

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
	private ListItemsDAO listitemdao;

	@Override
	public List checkSNPsInChromosome(Integer organismId, String chr, Collection posset, Set type) {

		/*
		 * if(! (type.equals(SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2) ||
		 * type.equals(SnpsAllvarsPosDAO.TYPE_3KCORESNP_HDF5_V2)) ) throw new
		 * RuntimeException("!type.equals(SnpsAllvarsPosDAO.TYPE_3KCORESNP_HDF5_V2");
		 */

		SnpsAllvarsPosDAO snpstringallvarsposDAO = null;
		snpstringallvarsposDAO = (SnpsAllvarsPosDAO) AppContext.checkBean(snpstringallvarsposDAO, "VSnpRefposindexDAO");
		return snpstringallvarsposDAO.getSNPsInChromosome(organismId, chr, posset, type);
	}

	@Override
	public List<SnpsEffect> getSnpsEffects(List positions) {

		snpseffDAO = (SnpsEffectDAO) AppContext.checkBean(snpseffDAO, "SnpsEffectDAO");
		// group gy contig
		List listEffs = new ArrayList();
		listEffs.addAll(snpseffDAO.getSNPsIn("any", positions, null));
		return listEffs;

	}

	@Override
	public List<SnpsAllvarsPos> getSNPPoslist(GenotypeQueryParams params) {

		SnpsAllvarsPosDAO snpstringallvarsposDAO = null;
		snpstringallvarsposDAO = (SnpsAllvarsPosDAO) AppContext.checkBean(snpstringallvarsposDAO, "VSnpRefposindexDAO");

		Set snptype = params.getSnpSet();

		Organism organism = params.getOrganism();

		List<SnpsAllvarsPos> snpsposlist = null;
		List listpos = null;

		if (params.hasPreviousData()) {
			// snpsposlist=params.getVariantdata().getListPos();
			snpsposlist = params.getVariantdata().getSnpstringdata().getListPos();
			if (params.hasSnpList()) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(params.getPoslist()));
			}
		} else {

			int firstRow = -1;
			int maxRows = -1;
			if (params.hasPaging()) {
				firstRow = params.getPage().intValue() * params.getPageSize().intValue();
				maxRows = params.getPageSize().intValue();
			}

			if (params.hasSnpList()) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(params.getPoslist()));
				AppContext.resetTimer("getSNPsString start1");
				snpsposlist = snpstringallvarsposDAO.getSNPsInChromosome(organism.getOrganismId().intValue(),
						params.getsChr(), listpos, snptype);
			} else if (params.hasLocusList()) {
				AppContext.resetTimer("getSNPsString start1b");
				snpsposlist = snpstringallvarsposDAO.getSNPsInChromosome(organism.getOrganismId().intValue(),
						params.getsChr(), params.getColLoci(), snptype);
			} else if (params.hasChrPosRange()) {
				AppContext.resetTimer("getSNPsString start2");
				snpsposlist = snpstringallvarsposDAO.getSNPs(organism.getOrganismId().intValue(), params.getsChr(),
						params.getlStart().intValue(), params.getlEnd().intValue(), snptype, firstRow, maxRows);
				// snpsposlist = snpstringallvarsposDAO.getSNPs(params.getsChr(),
				// params.getlStart(), params.getlEnd(), snptype, firstRow, maxRows);
			} else if (params.hasChrNoPosRange()) {
				AppContext.resetTimer("getSNPsString start2b");
				snpsposlist = snpstringallvarsposDAO.getSNPs(organism.getOrganismId().intValue(), params.getsChr(),
						null, null, snptype, firstRow, maxRows);
				// snpsposlist = snpstringallvarsposDAO.getSNPs(params.getsChr(),
				// params.getlStart(), params.getlEnd(), snptype, firstRow, maxRows);
			} else {
				AppContext.resetTimer("getSNPsString start2c");
				snpsposlist = snpstringallvarsposDAO.getSNPs(organism.getOrganismId().intValue(), "", null, null,
						snptype, firstRow, maxRows);
				// snpsposlist = snpstringallvarsposDAO.getSNPs(params.getsChr(),
				// params.getlStart(), params.getlEnd(), snptype, firstRow, maxRows);
			}

			if (snpsposlist == null)
				throw new RuntimeException("snpsposlist==null");

			if (snpsposlist.isEmpty())
				return new ArrayList();

			Set setlist = new TreeSet(snpsposlist);
			snpsposlist.clear();
			snpsposlist.addAll(setlist);
		}

		return snpsposlist;
	}

	@Override
	public long countVariantString(GenotypeQueryParams params) {

		return -1;
	}

	@Override
	public long countSNPPoslist(GenotypeQueryParams params) {

		Set snptype = params.getSnpSet();

		// get SNP positions and HDF5 column index mapping
		// depends on dataset, version, core or all

		SnpsAllvarsPosDAO snpstringallvarsposDAO = null;
		snpstringallvarsposDAO = (SnpsAllvarsPosDAO) AppContext.checkBean(snpstringallvarsposDAO, "VSnpRefposindexDAO");

		long countSNPPoslist = -1;

		if (params.hasSnpList()) {
			List listpos = new ArrayList();
			listpos.addAll(new TreeSet(params.getPoslist()));
			countSNPPoslist = snpstringallvarsposDAO.countSNPsInChromosome(params.getsChr(), listpos, snptype);
		} else if (params.hasLocusList()) {
			countSNPPoslist = snpstringallvarsposDAO.countSNPsInChromosome(params.getsChr(), params.getColLoci(),
					snptype);
		} else if (params.hasChrPosRange()) {
			countSNPPoslist = snpstringallvarsposDAO.countSNPs(params.getsChr(), params.getlStart().intValue(),
					params.getlEnd().intValue(), snptype);
			// snpsposlist = snpstringallvarsposDAO.getSNPs(params.getsChr(),
			// params.getlStart(), params.getlEnd(), snptype, firstRow, maxRows);
		} else if (params.hasChrNoPosRange()) {
			countSNPPoslist = snpstringallvarsposDAO.countSNPs(params.getsChr(), null, null, snptype);
			// snpsposlist = snpstringallvarsposDAO.getSNPs(params.getsChr(),
			// params.getlStart(), params.getlEnd(), snptype, firstRow, maxRows);
		} else {
			countSNPPoslist = snpstringallvarsposDAO.countSNPs("", null, null, snptype);
		}

		if (countSNPPoslist < 0) {
			Class actualclass = AopUtils.getTargetClass(snpstringallvarsposDAO);
			AppContext.debug("countSNPPoslist=" + countSNPPoslist + " for " + actualclass + " "
					+ actualclass.getCanonicalName());

			Advised advised = (Advised) snpstringallvarsposDAO;
			Class<?> cls = advised.getTargetSource().getTargetClass();
			throw new RuntimeException(
					"countSNPPoslist=" + countSNPPoslist + " for " + actualclass + "; " + cls.getCanonicalName());

		}

		return countSNPPoslist;

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

		AppContext.debug("Querying " + params.getDataset() + "  " + params.getOrganism().getName());

		VariantStringData vardata = null;
		MultiReferenceLocus locusNipponbare = null;

		AppContext.debug("Params Organism: " + params.getDataset() + " - Default Organism " + params.getOrganismName());
		if (!params.getOrganism().getName().equals(AppContext.JAPONICA_NIPPONBARE)
				&& !params.getOrganism().getName().equals(AppContext.MH63)
				&& !params.getOrganism().getName().equals(AppContext.AZUCENA)
				&& !params.getOrganism().getName().equals(AppContext.IR64)) {
			// not nipponbare coordinate. convert coordinates

			if (params.getsChr() != null && params.getlStart() != null && params.getlEnd() != null) {

				multiplerefconvertdao = (MultipleReferenceConverterDAO) AppContext.checkBean(multiplerefconvertdao,
						"MultipleReferenceConverterDAO");

				MultiReferenceLocus locusQueried = new MultiReferenceLocusImpl(params.getOrganism().getName(),
						params.getsChr(), params.getlStart().intValue(), params.getlEnd().intValue(), 1);
				locusNipponbare = multiplerefconvertdao.convertLocus(locusQueried, AppContext.getDefaultOrganism(),
						null);
				MultiReferenceLocus origMultiReferenceLocus = params.setNewPosition(locusNipponbare);

				VariantStringData variantstringdataNPB = _getSNPsStringNipponbare(params);

				String toContig = null;
				if (params.isLimitToQueryContig()) {
					toContig = locusQueried.getContig();
				}

				variantstringdataNPB.setMessage(variantstringdataNPB.getMessage() + "\nSNP Query " + locusQueried
						+ " aligned with " + locusNipponbare);
				params.setNewPosition(origMultiReferenceLocus);
				vardata = multiplerefconvertdao.convertReferencePositions(variantstringdataNPB, locusNipponbare,
						locusQueried, toContig, false);

				if (params.isbShowAllRefAlleles()) {
					vardata = convertReferencePositionsToAllReferences(params, vardata, locusNipponbare);
					AppContext.debug("show other alllele refs = " + vardata.getMapOrg2MSU7Pos2ConvertedPos().keySet());
				}
			} else
				throw new RuntimeException("Multiple reference query not yet allowed for SNP/locus lists");

		} else {

			vardata = _getSNPsStringNipponbare(params);

			// if(!params.isLocusList() && !params.isSNPList() &&
			// params.isbShowAllRefAlleles()) {
			if (params.isbShowAllRefAlleles()) {

				if (params.getsChr() != null && params.getlStart() != null && params.getlEnd() != null) {
					locusNipponbare = new MultiReferenceLocusImpl(params.getOrganism().getName(), params.getsChr(),
							params.getlStart().intValue(), params.getlEnd().intValue(), 1);
					vardata = convertReferencePositionsToAllReferences(params, vardata, locusNipponbare);
					AppContext.debug("show other alllele refs = " + vardata.getMapOrg2MSU7Pos2ConvertedPos().keySet());
				} else
					throw new RuntimeException("Multiple reference query not yet allowed for SNP/locus lists");
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

		return multiplerefconvertdao.convertReferencePositions(variantstringdata, npbMultirefLocus, null, null, false);

	}

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
		private Set<Position> setSnpInExonPos = new TreeSet();
		private Set<Position> setSnpSpliceDonorPos = new TreeSet();
		private Set<Position> setSnpSpliceAcceptorPos = new TreeSet();
		private SnpsStringDAO snpstrSnpsAllele1AllvarsDAO;

		public SNPsStringData() {
			super();
			// TODO Auto-generated constructor stub
		}

		public SNPsStringData(List listSnpsPos, String strRef, Map mapVarid2Snpsstr, Map mapVarid2SnpsAllele2str,
				Map mapPos2Alleles, Map mapPos2NonsynAlleles, Map mapPos2SynAlleles, Set setSnpSpliceDonorPos,
				Set setSnpSpliceAcceptorPos, SnpsStringDAO snpstrSnpsAllele1AllvarsDAO) {
			super();

			if (strRef.length() != listSnpsPos.size())
				throw new RuntimeException(
						"strRef.length()!=listSnpsPos.size(), " + strRef.length() + "," + listSnpsPos.size());

			this.strRef = strRef;
			this.mapVarid2Snpsstr = mapVarid2Snpsstr;
			this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
			this.mapPos2NonsynAlleles = mapPos2NonsynAlleles;
			this.mapPos2SynAlleles = mapPos2SynAlleles;
			this.setSnpInExonPos = setSnpInExonPos;
			this.listSnpsPos = listSnpsPos;
			this.setSnpSpliceAcceptorPos = setSnpSpliceAcceptorPos;
			this.setSnpSpliceDonorPos = setSnpSpliceDonorPos;
			this.snpstrSnpsAllele1AllvarsDAO = snpstrSnpsAllele1AllvarsDAO;
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

		public Set getSetSnpInExonPos() {
			return setSnpInExonPos;
		}

		public List<SnpsAllvarsPos> getListSnpsPos() {
			return listSnpsPos;
		}

		public Map<Position, Set<Character>> getMapPos2Alleles() {
			return mapPos2Alleles;
		}

		public Map<Position, Set<Character>> getMapPos2SynAlleles() {
			return mapPos2SynAlleles;
		}

	}

	/**
	 * Query Nipponbare SNPs
	 * 
	 * @param params
	 * @return
	 */
	private VariantStringData _getSNPsStringNipponbare(GenotypeQueryParams params) { // , boolean exactMismatch, int
																						// firstRow, int maxRows) {

		String dataset = (String) params.getDataset().iterator().next();
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
		// SNPsStringData snpstrdata = getSNPsStringData(params, colVarids, chr, start,
		// end, setPositions, colLocus);
		SNPsStringData snpstrdata = getSNPsStringData(params);
		if (snpstrdata == null) {
			throw new RuntimeException("getSNPsString:  snpstrdata==null");
		}

		Map<BigDecimal, String> mapVarid2Snpsstr = snpstrdata.getMapVarid2Snpsstr();
		if (mapVarid2Snpsstr == null) {
			mapVarid2Snpsstr = new HashMap();
		}

		Set setNonsynPos = new HashSet();

		Set sortedVarieties = new TreeSet(
				new SnpsStringAllvarsImplSorter(params.isbDownloadOnly(), params.getDataset()));

		Iterator itVar = mapVarid2Snpsstr.keySet().iterator();

		Map<String, Double> mapRef2Match = null;

		String strAlleleFilter = "";
		if (params.isbAlleleFilter()) {

			// note.. use only pos in query with position

			Set hasPos = new HashSet(params.getPoslist());
			hasPos.retainAll(snpstrdata.getListSnpsPos());
			Map mapPos2AlleleMatching = new HashMap();
			Iterator<MultiReferencePositionImplAllelePvalue> itPosAllele = new TreeSet(hasPos).iterator();
			StringBuffer buffAlleles = new StringBuffer();
			while (itPosAllele.hasNext()) {
				MultiReferencePositionImplAllelePvalue pos = itPosAllele.next();
				buffAlleles.append(pos.getAllele());
				mapPos2AlleleMatching.put(pos.getContig() + "-" + pos.getPosition(), pos.getAllele());
			}
			strAlleleFilter = buffAlleles.toString();

			// AppContext.debug("matching mapPos2AlleleMatching:" + mapPos2AlleleMatching);
			// AppContext.debug("matching strAlleleFilter: " + strAlleleFilter);

			double mismatchCount[] = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(
					snpstrdata.getListSnpsPos(), snpstrdata.getStrRef(), strAlleleFilter, true, null, null,
					snpstrdata.getMapPos2NonsynAlleles(), new HashSet(),
					params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(), params.isbCountMissingAs05()); // .isbExcludeSynonymous()
																												// );
			mapRef2Match = new HashMap();
			mapRef2Match.put(params.getOrganism().getName(), mismatchCount[1]);
		}

		// AppContext.debug("snpstrdata.getMapPos2NonsynAlleles()=" +
		// snpstrdata.getMapPos2NonsynAlleles());
		// AppContext.debug("snpstrdata.getSetSnpInExonPos()=" +
		// snpstrdata.getSetSnpInExonPos());

		// compare variety mismatch then sort
		while (itVar.hasNext()) {
			Object ovar = itVar.next();
			BigDecimal var = null;

			if (ovar instanceof BigDecimal) {
				var = (BigDecimal) ovar;
				// System.out.println("VAR ID: " +var);
			} else if (ovar instanceof StockSample)
				var = ((StockSample) ovar).getStockSampleId();
			else if (ovar instanceof Variety)
				var = ((Variety) ovar).getVarietyId();
			else if (ovar instanceof Number)
				var = BigDecimal.valueOf(Long.valueOf(ovar.toString()));
			else if (ovar == null) {
				AppContext.debug("ovar=null");
				continue;
			} else
				AppContext.debug("cannot identifi varid " + ovar.getClass() + "  " + ovar);

			String snpstr = (String) mapVarid2Snpsstr.get(var);
			Set<Position> varNonsynPos = new HashSet();
			Set<Position> varSynPos = new HashSet();

			double mismatchCount[] = null;
			if (params.isbAlleleFilter()) {
				mismatchCount = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(snpstrdata.getListSnpsPos(),
						strAlleleFilter, snpstr, false, null, (Map) snpstrdata.getMapVarid2SnpsAllele2str().get(var),
						snpstrdata.getMapPos2NonsynAlleles(), varNonsynPos,
						params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(), params.isbCountMissingAs05()); // .isbExcludeSynonymous()
																													// );
			} else
				mismatchCount = SnpsStringMultiHDF5nRDBMSHybridService.countVarpairMismatch(snpstrdata.getListSnpsPos(),
						snpstrdata.getStrRef(), snpstr, true, null,
						(Map) snpstrdata.getMapVarid2SnpsAllele2str().get(var), snpstrdata.getMapPos2NonsynAlleles(),
						varNonsynPos, params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps(),
						params.isbCountMissingAs05()); // .isbExcludeSynonymous() );

			double score = (params.isbAlleleFilter() ? mismatchCount[1] : mismatchCount[0]);
			if (!params.isbMismatchonly() || score > 0 || params.isbPairwiseComparison() || params.hasLocusList()) { // ||
																														// params.hasLocusList())
																														// {
				sortedVarieties.add(new SnpsStringAllvarsImpl(dataset, var, chr, snpstr, BigDecimal.valueOf(score),
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
				snpstrdata.getMapPos2SynAlleles(),
				// snpstrdata.getSetSnpInExonPos() ,
				snpstrdata.setSnpSpliceDonorPos, snpstrdata.setSnpSpliceAcceptorPos, mapRef2Match, params.getDataset(),
				snpstrdata.snpstrSnpsAllele1AllvarsDAO.getIdx2SampleMapping());

		return vardata;
	}

	/**
	 * query SNPsStringData
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

	private SNPsStringData getSNPsStringData(GenotypeQueryParams params) { // , boolean exactMismatch, int firstRow, int
																			// maxRows) {

		// Collection colVarids, String chr, BigDecimal start, BigDecimal end,
		// Collection setPositions, Collection colLocus

		SnpsAllvarsPosDAO snpstringallvarsposDAO = null;
		SnpsStringDAO snpstrAllsnpsAllele1AllvarsDAO = null;
		SnpsStringDAO snpstrAllsnpsAllele2AllvarsDAO = null;
		SnpsStringDAO snpstrCoresnpsAllele1AllvarsDAO = null;
		SnpsStringDAO snpstrCoresnpsAllele2AllvarsDAO = null;

		SnpsStringDAO snpstrSnpsAllele1AllvarsDAO = null;
		SnpsStringDAO snpstrSnpsAllele2AllvarsDAO = null;

		Organism organism = params.getOrganism();
		// BigDecimal snptype=null;

		if (params.getSetRun().size() > 1)
			throw new RuntimeException(
					"SnpsStringHDF5nRDBMSHybridService.getSNPsStringData: getSetRun " + params.getSetRun() + " size>1");

		Set snptype = params.getSnpSet();

		GenotypeRunPlatform run = (GenotypeRunPlatform) params.getSetRun().iterator().next();

		snpstringallvarsposDAO = (SnpsAllvarsPosDAO) AppContext.checkBean(snpstringallvarsposDAO, "VSnpRefposindexDAO");

		List<SnpsAllvarsPos> snpsposlist = null;
		List listpos = null;

		if (params.hasPreviousData()) {
			// snpsposlist=params.getVariantdata().getListPos();
			snpsposlist = params.getVariantdata().getSnpstringdata().getListPos();
			if (params.hasSnpList()) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(params.getPoslist()));
			}
		} else {

			int firstRow = -1;
			int maxRows = -1;
			if (params.hasPaging()) {
				firstRow = params.getPage().intValue() * params.getPageSize().intValue();
				maxRows = params.getPageSize().intValue();
			}

			// TODO: DAGS for slicing
			if (params.hasSnpList()) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(params.getPoslist()));
				AppContext.resetTimer("get poslist hasSnpList");
				snpsposlist = snpstringallvarsposDAO.getSNPsInChromosome(organism.getOrganismId().intValue(),
						params.getsChr(), listpos, snptype);
			} else if (params.hasLocusList()) {
				AppContext.resetTimer("get poslist hasLocusList");
				snpsposlist = snpstringallvarsposDAO.getSNPsInChromosome(organism.getOrganismId().intValue(),
						params.getsChr(), params.getColLoci(), snptype);
			} else {
				AppContext.resetTimer("get poslist has chr range");
				snpsposlist = snpstringallvarsposDAO.getSNPs(organism.getOrganismId().intValue(), params.getsChr(),
						params.getlStart().intValue(), params.getlEnd().intValue(), snptype, firstRow, maxRows);
			}

			if (snpsposlist == null)
				throw new RuntimeException("snpsposlist==null");
			if (snpsposlist.isEmpty()) {
				AppContext.resetTimer("No SNP position found");
				return new SNPsStringData();
			}

			Set setlist = new TreeSet(snpsposlist);
			snpsposlist.clear();
			snpsposlist.addAll(setlist);
		}

		AppContext.debug("snptype=" + snptype);

		// get allele column indices from start to end positions
		SnpsAllvarsPos startpos = snpsposlist.get(0);
		SnpsAllvarsPos endpos = snpsposlist.get(snpsposlist.size() - 1);
		AppContext.debug(
				snpsposlist.size() + " snpposlist, pos between " + startpos.getPosition() + "-" + endpos.getPosition()
						+ "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
		// if(!
		// (snptype.equals(SnpsAllvarsPosDAO.TYPE_HDRASNP)||snptype.equals(SnpsAllvarsPosDAO.TYPE_WISSUWASNP))
		// && startpos.getAlleleIndex().longValue()>endpos.getAlleleIndex().longValue())
		// {
		if (run.useHDF5() && startpos.getAlleleIndex().longValue() > endpos.getAlleleIndex().longValue()) {
			AppContext.debug("startpos.getAlleleIndex().longValue()>endpos.getAlleleIndex().longValue()");
			Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
			StringBuffer buff = new StringBuffer();
			while (itPos.hasNext()) {
				SnpsAllvarsPos ipos = itPos.next();
				buff.append(ipos.getSnpFeatureId() + "\t" + ipos.getContig() + "\t" + ipos.getPosition() + "\t"
						+ ipos.getAlleleIndex() + "\n");
			}
			AppContext.debug(buff.toString());
		}

		listitemdao = (ListItemsDAO) AppContext.checkBean(listitemdao, "ListItems");

		// generate column indexes to query HDF5
		String strRef = null;
		int refLength = -1;

		// get snpstring for each varieties
		// get allele2 for heterozygous varieties
		Map mapVarid2Snpsstr = null;
		Map mapVarid2Snpsstr_allele2 = null;
		Iterator<SnpsAllvarsPos> itSnppos = null;
		Map<BigDecimal, Position> mapSnpid2Pos = null;
		Map<BigDecimal, Map> mapVarid2SnpsAllele2str = null;
		// if(snptype.equals(SnpsAllvarsPosDAO.TYPE_HDRASNP) ||
		// snptype.equals(SnpsAllvarsPosDAO.TYPE_WISSUWASNP)) {
		if (run.useRDBMS()) {

			// using RDBMS
			snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO) AppContext.checkBean(snpstrAllsnpsAllele1AllvarsDAO,
					"VSnpGenotypeRDBMSDAO");

			Map mapvaris2alleles[] = snpstrSnpsAllele1AllvarsDAO.readSNPString(
					(GenotypeRunPlatform) params.getSetRun().iterator().next(), (Set) params.getColVarIds(),
					params.getsChr(), snpsposlist);

			mapVarid2Snpsstr = mapvaris2alleles[0];
			mapVarid2Snpsstr_allele2 = mapvaris2alleles[1];

			AppContext.debug("using RDBMS vars allele1=" + mapVarid2Snpsstr.size());
			AppContext.debug("allele2=" + mapVarid2Snpsstr_allele2.size());

			mapSnpid2Pos = new HashMap();

			StringBuffer buffRef = new StringBuffer();
			Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
			while (itPos.hasNext()) {
				SnpsAllvarsPos pos = itPos.next();
				buffRef.append(pos.getRefnuc());
				mapSnpid2Pos.put(pos.getSnpFeatureId(), pos);
			}
			strRef = buffRef.toString();
			refLength = strRef.length();

			mapVarid2SnpsAllele2str = new HashMap();
			Iterator itVarid = mapVarid2Snpsstr_allele2.keySet().iterator();
			while (itVarid.hasNext()) {
				BigDecimal varid = (BigDecimal) itVarid.next();

				// if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {
				// revised for SNPv2 (allele2!=0 for allele1==allele2)
				String allele2str = (String) mapVarid2Snpsstr_allele2.get(varid);
				String allele1str = (String) mapVarid2Snpsstr.get(varid);

				if (allele2str.length() != allele1str.length())
					throw new RuntimeException("allele2str.length()!=allele1str.length(): " + allele2str.length() + ","
							+ allele1str.length());

				StringBuffer buffNewStr = new StringBuffer();
				StringBuffer buffNewStr2 = new StringBuffer();
				for (int iStr = 0; iStr < allele2str.length(); iStr++) {
					// char allele2i =allele2str.charAt(iStr);
					char allele1i = allele1str.charAt(iStr);
					char allele2i = allele2str.charAt(iStr);
					if (allele1i == 'A' || allele1i == 'T' || allele1i == 'G' || allele1i == 'C') {
						if (allele2i != allele1i) {
							Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get(varid);
							if (mapTableidx2Nuc == null) {
								mapTableidx2Nuc = new HashMap();
								mapVarid2SnpsAllele2str.put(varid, mapTableidx2Nuc);
							}
							mapTableidx2Nuc.put(snpsposlist.get(iStr), allele2i);
						}
					}
				}
			}
		} else {
			System.gc();
			long hdf5_startTime = System.nanoTime();

			AppContext.debug("using HDF5");

			int intSampleStartStopIdx[][] = null;

			AppContext.debug("using hasVarlist:" + params.hasVarlist());

			AppContext.debug("using hasVarIDRange:" + params.hasVaridRange());

			if (params.hasVarlist() || params.hasVaridRange()) {
				snpstrSnpsAllele1AllvarsDAO = new H5Dataset(run.getLocation(), new H5ReadCharmatrix(),
						listitemdao.getMapIdx2Sample(params)); // , run.getVaridOffset());

			} else {
				String locationH5 = AppContext.getFlatfilesDir() + run.getLocation().replace(".h5", "_trans.h5");
				File h5File = new File(locationH5);
				AppContext.debug("File " + locationH5);
				AppContext.debug("File " + h5File.exists());
				if (h5File.exists())
					snpstrSnpsAllele1AllvarsDAO = new H5Dataset(run.getLocation().replace(".h5", "_trans.h5"),
							new H5ReadCharTransMatrix(), listitemdao.getMapIdx2Sample(params)); // ,
																								// run.getVaridOffset());
				else
					snpstrSnpsAllele1AllvarsDAO = new H5Dataset(run.getLocation(), new H5ReadCharmatrix(),
							listitemdao.getMapIdx2Sample(params));
			}

			int indxs[] = new int[snpsposlist.size()];
			List<int[]> listStartStop = new ArrayList();
			int indxscount = 0;
			mapSnpid2Pos = new HashMap();

			itSnppos = snpsposlist.iterator();
			StringBuffer buffRef = new StringBuffer();

			int previdx = -100;
			int laststart = -100;
			AppContext.debug("listStartStopIdx:");
			while (itSnppos.hasNext()) {
				SnpsAllvarsPos snppos = itSnppos.next();
				buffRef.append(snppos.getRefnuc());
				indxs[indxscount] = snppos.getAlleleIndex().intValue();

				mapSnpid2Pos.put(snppos.getSnpFeatureId(), snppos);

				// merge adjacent indexes into a range, for locus list
				if (indxs[indxscount] == (previdx + 1)) {
					previdx = indxs[indxscount];
				} else {
					if (laststart >= 0 && previdx >= 0) {
						listStartStop.add(new int[] { laststart, previdx });
						AppContext.debug(laststart + "-" + previdx);
					}
					laststart = indxs[indxscount];
					previdx = indxs[indxscount];
				}
				indxscount++;
			}
			listStartStop.add(new int[] { laststart, previdx });
			AppContext.debug(laststart + "-" + previdx);

			int intStartStopIdx[][] = new int[listStartStop.size()][2];
			Iterator<int[]> itStartStop = listStartStop.iterator();
			int sscount = 0;
			long startTime = System.nanoTime();
			long endTime = System.nanoTime();

			long totalTime = endTime - startTime;

			System.out.println("TIME: " + TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS));

			// TODO
			while (itStartStop.hasNext()) {
				intStartStopIdx[sscount] = itStartStop.next();
				// AppContext.debug("idxrange " + intStartStopIdx[sscount][0] + "-" +
				// intStartStopIdx[sscount][1]);
				sscount++;
			}

			// get sampleid 2 hdf5idx mappings
			// convert sample ids to hdf5 index
			if (params.getVaridStartEnd() != null) {

			} else if (params.hasVarlist()) {

			}

			strRef = buffRef.toString();
			refLength = strRef.length();

			if (params.hasSnpList() || params.hasLocusList()) {
				// if( !(params.hasVarlist() || params.hasVaridRange()) ) {

				// position list or locus list
				if (params.hasSnpList()) {
					AppContext.debug("params.hasSnpList()");
					if (params.getVaridStartEnd() != null) {
						AppContext.debug("params.getVaridStartEnd()");
						AppContext.resetTimer("using readSNPString3 start");
						Integer startendidx[] = params.getVaridStartEnd();
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(params.getsChr(), indxs,
								startendidx[0], startendidx[1]);
						if (snpstrSnpsAllele2AllvarsDAO != null)
							mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(params.getsChr(),
									indxs, startendidx[0], startendidx[1]);
						AppContext.resetTimer("using readSNPString3 end");
					} else if (params.hasVarlist()) {
						AppContext.debug("params.hasVarlist()");
						AppContext.resetTimer("using readSNPString1 start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(
								organism.getOrganismId().intValue(), (Set) params.getColVarIds(), params.getsChr(),
								indxs);
						if (snpstrSnpsAllele2AllvarsDAO != null)
							mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(
									organism.getOrganismId().intValue(), (Set) params.getColVarIds(), params.getsChr(),
									indxs);
						AppContext.resetTimer("using readSNPString1 end");
					} else {
						AppContext.debug("all vars");
						AppContext.resetTimer("using readSNPString2 start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO
								.readSNPString(organism.getOrganismId().intValue(), params.getsChr(), indxs);
						if (snpstrSnpsAllele2AllvarsDAO != null)
							mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO
									.readSNPString(organism.getOrganismId().intValue(), params.getsChr(), indxs);
						AppContext.resetTimer("using readSNPString2 end");
					}
				} else if (params.hasLocusList()) {
					AppContext.debug("params.hasLocusList()");
					if (params.getVaridStartEnd() != null) {
						AppContext.debug("params.getVaridStartEnd()");
						AppContext.resetTimer("using readSNPString3b start");
						Integer startendidx[] = params.getVaridStartEnd();
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(params.getsChr(), startendidx[0],
								startendidx[1], intStartStopIdx);
						if (snpstrSnpsAllele2AllvarsDAO != null)
							mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(params.getsChr(),
									startendidx[0], startendidx[1], intStartStopIdx);
						AppContext.resetTimer("using readSNPString3b end");
					} else if (params.hasVarlist()) {
						AppContext.debug("params.hasVarlist()");
						AppContext.resetTimer("using readSNPString1b start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set) params.getColVarIds(),
								params.getsChr(), intStartStopIdx);
						if (snpstrSnpsAllele2AllvarsDAO != null)
							mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO
									.readSNPString((Set) params.getColVarIds(), params.getsChr(), intStartStopIdx);
						AppContext.resetTimer("using readSNPString1b end");
					} else {
						AppContext.debug("all vars");
						AppContext.resetTimer("using readSNPString2b start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(params.getsChr(), intStartStopIdx);
						if (snpstrSnpsAllele2AllvarsDAO != null)
							mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(params.getsChr(),
									intStartStopIdx);
						AppContext.resetTimer("using readSNPString2b end");
					}
				}

			} else {

				AppContext.debug(" ! (params.hasSnpList() || params.hasLocusList()) ");

				if (params.getVaridStartEnd() != null) {
					AppContext.debug("params.getVaridStartEnd()");
					AppContext.resetTimer("using readSNPString3c start");
					int startendposidx[][] = new int[][] {
							{ startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue() } };
					mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(params.getsChr(),
							params.getVaridStartEnd()[0], params.getVaridStartEnd()[1], startendposidx);
					if (snpstrSnpsAllele2AllvarsDAO != null)
						mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(params.getsChr(),
								params.getVaridStartEnd()[0], params.getVaridStartEnd()[1], startendposidx);
					AppContext.resetTimer("using readSNPString3c end");
				} else if (params.hasVarlist()) {
					AppContext.debug("params.hasVarlist()");
					AppContext.resetTimer("using readSNPString3 start");
					mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(organism.getOrganismId().intValue(),
							(Set) params.getColVarIds(), params.getsChr(), startpos.getAlleleIndex().intValue(),
							endpos.getAlleleIndex().intValue());
					if (snpstrSnpsAllele2AllvarsDAO != null)
						mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(
								organism.getOrganismId().intValue(), (Set) params.getColVarIds(), params.getsChr(),
								startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
					AppContext.resetTimer("using readSNPString3 end");
				} else {
					AppContext.debug("all vars");
					AppContext.resetTimer("using readSNPString4 start");
					mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(organism.getOrganismId().intValue(),
							params.getsChr(), startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
					if (snpstrSnpsAllele2AllvarsDAO != null)
						mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(
								organism.getOrganismId().intValue(), params.getsChr(),
								startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
				}

			}

			mapVarid2SnpsAllele2str = new HashMap();

			if (mapVarid2Snpsstr_allele2 != null) {
				AppContext.resetTimer("to read allele2 database..");
				if (params.includeDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC))
					throw new RuntimeException("Unexpected case mapVarid2Snpsstr_allele2!=null for IUPAC");

				Iterator itVarid = mapVarid2Snpsstr_allele2.keySet().iterator();
				while (itVarid.hasNext()) {
					BigDecimal varid = (BigDecimal) itVarid.next();

					if (params.includeDataset(VarietyFacade.DATASET_SNPINDELV1)) {

						String allele2str = (String) mapVarid2Snpsstr_allele2.get(varid);
						for (int iStr = 0; iStr < allele2str.length(); iStr++) {
							char allele2i = allele2str.charAt(iStr);
							if (allele2i != '*' && allele2i != '0' && allele2i != '.' && allele2i != ' ') {

								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get(varid);
								if (mapTableidx2Nuc == null) {
									mapTableidx2Nuc = new HashMap();
									mapVarid2SnpsAllele2str.put(varid, mapTableidx2Nuc);
								}
								mapTableidx2Nuc.put(iStr, allele2i);
							}
						}

					} else if (params.includeDataset(VarietyFacade.DATASET_SNPINDELV2)) {
						// revised for SNPv2 (allele2!=0 for allele1==allele2)
						String allele1str = (String) mapVarid2Snpsstr.get(varid);
						String allele2str = (String) mapVarid2Snpsstr_allele2.get(varid);

						if (allele1str.length() != allele2str.length())
							throw new RuntimeException("allele1str.length()!=allele2str.length(): "
									+ allele1str.length() + ", " + allele2str.length());

						for (int iStr = 0; iStr < allele2str.length(); iStr++) {
							char allele2i = allele2str.charAt(iStr);
							char allele1i = allele1str.charAt(iStr);
							if (allele1i != '0' && allele2i != '0' && allele1i != allele2i) {
								// if(allele2i!='*' && allele2i!='0' && allele2i!='.' && allele2i!=' ') {

								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get(varid);
								if (mapTableidx2Nuc == null) {
									mapTableidx2Nuc = new HashMap();
									mapVarid2SnpsAllele2str.put(varid, mapTableidx2Nuc);
								}
								// mapTableidx2Nuc.put( iStr, allele2i);

								mapTableidx2Nuc.put(snpsposlist.get(iStr), allele2i);
							}
						}
					}
				}
			} else if (mapVarid2Snpsstr_allele2 == null) {
				// && (params.includeDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC)
				// || params.includeDataset(VarietyFacade.DATASET_SNP_GOPAL92))) {

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

				}

				mapVarid2Snpsstr = mapVarid2SnpsstrSplitIUPAC;

			} // else throw new RuntimeException("heteroSnps==null and
				// mapVarid2Snpsstr_allele2==null ... no allele2 data");

			long hdf5_endTime = System.nanoTime();
			long hdf5_totalTime = hdf5_endTime - hdf5_startTime;

			// More precise version
			double totalSeconds2 = hdf5_totalTime / 1_000_000_000.0;
			long minutes2 = (long) totalSeconds2 / 60;
			double seconds2 = totalSeconds2 % 60;
			System.out.printf("HDF5 TIME (precise): %d minutes %.2f seconds%n", minutes2, seconds2);

		}

		AppContext.resetTimer("to read indels");

		// generate pos2alleles

		Map<Position, Set<Character>> mapPos2Allele = new TreeMap();
		Map<Position, Set<Character>> mapPos2AlleleHetero = new TreeMap();

		Iterator<BigDecimal> itVar = mapVarid2Snpsstr.keySet().iterator();
		while (itVar.hasNext()) {
			BigDecimal varid = itVar.next();
			String allele1str = (String) mapVarid2Snpsstr.get(varid);
			String allele2str = (String) mapVarid2Snpsstr_allele2.get(varid);
			itSnppos = snpsposlist.iterator();
			int icount = 0;
			while (itSnppos.hasNext()) {
				SnpsAllvarsPos snppos = itSnppos.next();

				Set setAlleles = mapPos2Allele.get(snppos);
				if (setAlleles == null) {
					setAlleles = new HashSet();
					mapPos2Allele.put(snppos, setAlleles);
				}
				// hdf5 format v2
				try {
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

				} catch (Exception ex) {
					AppContext.debug("snpsposlist.len=" + snpsposlist.size() + "   icount=" + icount
							+ "  allele1str.len=" + allele1str.length() + "  allele2str.len=" + allele2str.length());
					throw ex;
				}

				icount++;
			}
		}
		AppContext.resetTimer("to generate hetero alleles");

		// Set heteroSnps = null;
		Set nonsynAllele = null;
		Set synAllele = new HashSet(); // null;
		// Set inexonSnps = null;

		// non-synonymous alleles for positions
		Map<Position, Set<Character>> mapPos2NonsynAlleles = null;
		Map<Position, Set<Character>> mapPos2SynAlleles = null;
		Set<Position> setSynPos = null;
		// get splice variants
		Set setSpliceAcceptorsPos = null;
		Set setSpliceDonorsPos = null;

		Set setSpliceAcceptors = null;
		Set setSpliceDonors = null;

		if (params.hasPreviousData()) {
			VariantStringData prevdata = params.getVariantdata().getSnpstringdata();
			strRef = prevdata.getRefnuc();
			mapPos2NonsynAlleles = prevdata.getMapPos2NonsynAlleles();
			mapPos2SynAlleles = prevdata.getMapPos2NonsynAlleles();
			// setSnpInExonPos=prevdata.getSetSnpInExonPos();
			setSpliceDonorsPos = prevdata.getSetSnpSpliceDonorPos();
			setSpliceAcceptorsPos = prevdata.getSetSnpSpliceAcceptorPos();

		} else {

			snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "VSnpNonsynallelePosDAO");
			// snpssynDAO = (SnpsSynAllvarsDAO) AppContext.checkBean(snpssynDAO,
			// "VSnpSynalleleDAO");
			// snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO,
			// "SnpsInExonDAO");
			// snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO,
			// "SnpsHeteroAllvarsDAO");

			Set<BigDecimal> setSnpFeatureId = new TreeSet();
			Iterator<SnpsAllvarsPos> itSnpFId = snpsposlist.iterator();
			while (itSnpFId.hasNext())
				setSnpFeatureId.add(itSnpFId.next().getSnpFeatureId());

			if (params.hasSnpList() || params.hasLocusList()) {

				// nonsynAllele =
				// snpsnonsynDAO.findSnpNonsynAlleleByFeatureidIn(setSnpFeatureId, snptype);
				nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByFeatureidIn(organism.getOrganismId().intValue(),
						setSnpFeatureId);
				// synAllele = snpssynDAO.findSnpSynAlleleByFeatureidIn(setSnpFeatureId,
				// snptype);

			} else {
				nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(organism.getOrganismId().intValue(),
						params.getsChr(), startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
				// synAllele = snpssynDAO.findSnpSynAlleleByChrPosBetween(params.getsChr(),
				// startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
			}

			// nonsynAllele =
			// snpsnonsynDAO.findSnpNonsynAlleleByFeatureidIn(setSnpFeatureId, snptype);

			AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");

			mapPos2NonsynAlleles = new TreeMap();
			mapPos2SynAlleles = new TreeMap();
			// setSnpInExonPos = new TreeSet();

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

				// assuming all nonsyn are in exon
				// setSnpInExonPos.add( pos );

				Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
				if (setVarietyAlleles == null) {
					AppContext.debug("position " + pos.getContig() + " " + pos.getPosition()
							+ " from nonsyn-snpprop not in query snps set");
					continue;
					// throw new RuntimeException("setVarietyAlleles==null");
				}
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

			AppContext.resetTimer("to generate sys/nonsyn alleles");

			snpsspliceacceptorDAO = (SnpsSpliceAcceptorDAO) AppContext.checkBean(snpsspliceacceptorDAO,
					"SnpsSpliceAcceptorDAO");
			snpssplicedonorDAO = (SnpsSpliceDonorDAO) AppContext.checkBean(snpssplicedonorDAO, "SnpsSpliceDonorDAO");

			if (params.hasSnpList()) {
				// setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, setPositions);
				// setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, setPositions);

				setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsByFeatureidIn(organism.getOrganismId().intValue(),
						setSnpFeatureId);
				setSpliceDonors = snpssplicedonorDAO.getSNPsByFeatureidIn(organism.getOrganismId().intValue(),
						setSnpFeatureId);

			} else if (params.hasLocusList()) {
				// setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, colLocus);
				// setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, colLocus);
				setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsByFeatureidIn(organism.getOrganismId().intValue(),
						setSnpFeatureId);
				setSpliceDonors = snpssplicedonorDAO.getSNPsByFeatureidIn(organism.getOrganismId().intValue(),
						setSnpFeatureId);

			} else {
				setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsBetween(organism.getOrganismId().intValue(),
						params.getsChr(), params.getlStart().intValue(), params.getlEnd().intValue(), snptype);
				setSpliceDonors = snpssplicedonorDAO.getSNPsBetween(organism.getOrganismId().intValue(),
						params.getsChr(), params.getlStart().intValue(), params.getlEnd().intValue(), snptype);
			}

			// setSpliceAcceptors =
			// snpsspliceacceptorDAO.getSNPsByFeatureidIn(setSnpFeatureId);
			// setSpliceDonors = snpssplicedonorDAO.getSNPsByFeatureidIn(setSnpFeatureId);

			setSpliceAcceptorsPos = new HashSet(setSpliceAcceptors);
			setSpliceDonorsPos = new HashSet(setSpliceDonors);

			AppContext.resetTimer("to generate splice acceptor/donor");

		}

		if (AppContext.isLocalhost()) {
			AppContext.debug("mapPos2NonsynAlleles " + mapPos2NonsynAlleles.size());
			AppContext.debug("mapPos2SynAlleles " + mapPos2SynAlleles.size());
			AppContext.debug("mapPos2Allele " + mapPos2Allele.size());
			AppContext.debug("mapPos2AlleleHetero " + mapPos2AlleleHetero.size());
			AppContext.debug("setSpliceDonorsPos " + setSpliceDonorsPos.size());
			AppContext.debug("setSpliceAcceptorsPos " + setSpliceAcceptorsPos.size());
			// AppContext.debug( nonsynAllele.toString() );

			AppContext.debug("mapVarid2Snpsstr.size()=" + mapVarid2Snpsstr.size());
			AppContext.debug("mapVarid2SnpsAllele2str.size()=" + mapVarid2SnpsAllele2str.size());
			AppContext.debug("strRef.length()=" + strRef.length());
		}

		SNPsStringData snpstrdata = new SNPsStringData(snpsposlist, strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str,
				mapPos2Allele, mapPos2NonsynAlleles, mapPos2SynAlleles, setSpliceDonorsPos, setSpliceAcceptorsPos,
				snpstrSnpsAllele1AllvarsDAO);

		return snpstrdata;
	}

}
