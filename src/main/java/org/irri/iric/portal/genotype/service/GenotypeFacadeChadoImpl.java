package org.irri.iric.portal.genotype.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.irri.iric.ds.chado.dao.LocusDAO;
import org.irri.iric.ds.chado.dao.SequenceDAO;
import org.irri.iric.ds.chado.domain.Gene;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.Scaffold;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.VarietyDistance;
import org.irri.iric.ds.chado.domain.model.Organism;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.CreateZipMultipleFiles;
import org.irri.iric.portal.admin.AsyncJobReport;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.HaplotypeImageService;
import org.irri.iric.portal.genotype.PhylotreeQueryParams;
import org.irri.iric.portal.genotype.PhylotreeService;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.VariantTableArray;
import org.irri.iric.portal.genotype.VarietiesGenotypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Filedownload;

@Service("GenotypeFacade")
public class GenotypeFacadeChadoImpl implements GenotypeFacade {

	@Override
	public List getVarietysets() {

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		List l = new ArrayList();
		l.addAll(listitemsDAO.getDatasets("SNP"));
		return l;

	}
	
	@Override
	public List getGenotyperuns() {
		

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		List l = new ArrayList();
		l.addAll(listitemsDAO.getRunsByType("SNP"));
		return l;

	}
	
		
	@Override
	public List getVarietysets(String reference) {
			listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		List l = new ArrayList();
		l.addAll(listitemsDAO.getDatasets("SNP",reference));
		return l;

	}


	@Override
	public List getVariantsets(Set varietyset, String type) {

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		List l = new ArrayList();
		l.addAll(listitemsDAO.getSnpsets(varietyset, type));
		return l;
	}

	@Override
	public List getVariantsets(String varietyset, String type) {

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		List l = new ArrayList();
		l.addAll(listitemsDAO.getSnpsets(varietyset, type));
		return l;
	}

	@Override
	public boolean hasNonsyn(Integer organismId, Set vs) {

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.hasNonsynData(organismId, vs);
	}

	private static final Log log = LogFactory.getLog(GenotypeFacadeChadoImpl.class);

	@Autowired
	@Qualifier("FeatureDAO")
	private SequenceDAO sequenceDAO;

	// @Autowired
	// @Qualifier("VarietyFacade")
	// private VarietyFacade varietyfacade;

	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsDAO;

	@Autowired
	@Qualifier("VarietiesGenotypeService")
	private VarietiesGenotypeService genotypeservice;

	@Autowired
	@Qualifier("VarietiesGenotypeAsyncService")
	VarietiesGenotypeService genotypeasyncservice;

	@Autowired
	@Qualifier("PhylotreeService")
	private PhylotreeService phyloservice;

	@Autowired
	@Qualifier("LocusNotesDAO")
	private LocusDAO locusDAO;

	public GenotypeFacadeChadoImpl() {
		super();
		AppContext.debug("created GenotypeFacadeChadoImpl: " + this);
	}

	// ************************************* Methods for UI Listboxes
	// *******************************************************

	@Override
	public List getGenotyperuns(String type) {
		List l = new ArrayList();
		l.addAll(listitemsDAO.getPlatforms(type));
		return l;
	}

	@Override
	public List getGenotyperuns(Set setds, Set setvs, String type) {
		List l = new ArrayList();
		l.addAll(listitemsDAO.getPlatforms(setds, setvs, type));
		return l;
	}

	@Override
	public Set getVarietiesForSubpopulation(String subpopulation, String dataset) {

		return listitemsDAO.getGermplasmBySubpopulation(subpopulation, dataset);
	}

	@Override
	public Set getVarietiesForSubpopulation(String subpopulation, Set dataset) {

		return listitemsDAO.getGermplasmBySubpopulation(subpopulation, dataset); // .getGermplasmBySubpopulation(subpopulation,
																					// dataset);
	}

	/**
	 * Get Gene object from name
	 */
	@Override
	public Gene getGeneFromName(String name, String organism) {

		return listitemsDAO.findGeneFromName(name, organism);
	}

	@Override
	public List<Gene> getGeneFromNames(Collection names, String organism) {

		return listitemsDAO.findGeneFromName(names, organism);
	}

	/**
	 * Get varietry names
	 */

	@Override
	public List<String> getVarnames(String dataset) {
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getVarietyNames(dataset);

	}

	@Override
	public List getVarnames(Set dataset) {

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getVarietyNames(dataset);
	}

	@Override
	public List<String> getVaraccessions(String dataset) {

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getAccessions(dataset);

	}

	@Override
	public List getVaraccessions(Set dataset) {

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getAccessions(dataset); // . .getVarietyAccessions(dataset);
	}

	@Override
	public List<String> getSubpopulations(String dataset) {
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getSubpopulations(dataset);
	}

	/**
	 * Get all gene names
	 */
	@Override
	public List<String> getGenenames() {
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return listitemsDAO.getGenenames();
	}

	@Override
	public Collection getLociForReference(String organism) {

		if (organism.equals(AppContext.getDefaultOrganism()))
			return getGenenames();

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		return AppContext.createUniqueUpperLowerStrings(listitemsDAO.getGenenames(organism), true, false);
	}

	/**
	 * Get chromosome names for organism
	 */

	@Override
	public java.util.List<String> getChromosomes() {

		return getContigsForReference(AppContext.getDefaultOrganism());
	}

	/**
	 * Get length of features (ex. chromosome) from name, mocked! replace with DB
	 * read later
	 */

	@Override
	public Integer getFeatureLength(String feature, String organism) {
		return this.listitemsDAO.getFeatureLength(feature, organism).intValue();
	}

	@Override
	public Scaffold getFeature(String feature, String organism) {
		return this.listitemsDAO.getFeature(feature, organism);
	}

	@Override
	public List getReferenceGenomes() throws Exception {

		Iterator<Organism> it = listitemsDAO.getOrganisms().iterator();
		List organism = new ArrayList();
		while (it.hasNext()) {
			organism.add((Organism) it.next());
		}
		return organism;
	}

	@Override
	public List getContigsForReference(String reference) {

		return listitemsDAO.getContigs(reference);
	}

	// ************************************* Methods for Phylogenetic tree
	// construction
	// ********************************************************************************

	@Override
	public Object[] constructPhylotree(String scale, String chr, int start, int end, String requestid, Set dataset) {

		phyloservice = (PhylotreeService) AppContext.checkBean(phyloservice, "PhylotreeService");
		return phyloservice.constructPhylotree(scale, chr, start, end, requestid, dataset);
	}

	@Override
	public Object[] constructMDS(Map mapVarid2Row, VariantStringData dataset, PhylotreeQueryParams params) {

		phyloservice = (PhylotreeService) AppContext.checkBean(phyloservice, "PhylotreeService");
		List listdist = phyloservice.calculateDistancePair(dataset, params);
		return new Object[] { phyloservice.constructMDS(mapVarid2Row, listdist, "1"), listdist };
	}

	@Override
	// public double[][] constructMDS(Map<BigDecimal, Integer> mapVarid2Row,
	// List<VarietyDistance> listdist,
	public Object[] constructMDS(Map<BigDecimal, Integer> mapVarid2Row, List<VarietyDistance> listdist, String scale) {

		phyloservice = (PhylotreeService) AppContext.checkBean(phyloservice, "PhylotreeService");

		return new Object[] { phyloservice.constructMDS(mapVarid2Row, listdist, scale), listdist };
	}

	/*
	 * @Override public boolean displayHapotypeImage(String pedfilenameonly, String
	 * imageformat, boolean genomecoord, GenotypeQueryParams params) { // TODO
	 * Auto-generated method stub return displayHapotypeImage( pedfilenameonly,
	 * imageformat, genomecoord, params, 0.85, 100, 0, 0, "pamk","2"); }
	 */

	@Override
	public boolean sendGenotypeToGalaxy(String pedfilenameonly, GenotypeQueryParams params) {

		List listCDS = new ArrayList();
		locusDAO = (LocusDAO) AppContext.checkBean(locusDAO, "LocusNotesDAO");
		if (params.hasChrPosRange()) {
			Organism organism = params.getOrganism();
			listCDS = locusDAO.getLocusByRegion(params.getsChr(), params.getlStart(), params.getlEnd(),
					params.getOrganismName(), GenomicsFacade.GENEMODEL_MSU7_ONLY, GenomicsFacade.FEATURETYPE_GENE);
			listCDS.addAll(locusDAO.getLocusByRegion(params.getsChr(), params.getlStart(), params.getlEnd(),
					params.getOrganismName(), GenomicsFacade.GENEMODEL_MSU7_ONLY, GenomicsFacade.FEATURETYPE_CDS));
		} else if (params.hasSnpList()) {
			listCDS = null;
			// listCDS=locusDAO.getLocusByContigPositions(params.getsChr(),
			// params.getPoslist(), params.getOrganism(), 0,
			// GenomicsFacade.GENEMODEL_MSU7,GenomicsFacade.FEATURETYPE_GENE);
			// listCDS.addAll(locusDAO.getLocusByContigPositions(params.getsChr(),
			// params.getPoslist(), params.getOrganism(), 0,
			// GenomicsFacade.GENEMODEL_MSU7,GenomicsFacade.FEATURETYPE_CDS));
		}
		AppContext.debug("genes and cds:" + listCDS);
		return true;

	}

	@Override
	public boolean displayHapotypeImage(String pedfilenameonly, String imageformat, boolean genomecoord,
			GenotypeQueryParams params, double localWeight, double resFactor, int kgroups, int kheight,
			String autogroup, String imagesize) {
		HaplotypeImageService hi = new HaplotypeImageRHeatmapServiceImpl(AppContext.getTempDir());

		List listCDS = new ArrayList();
		locusDAO = (LocusDAO) AppContext.checkBean(locusDAO, "LocusNotesDAO");
		if (params.hasChrPosRange()) {
			Organism organism = params.getOrganism();
			
			listCDS = locusDAO.getLocusByRegion(params.getsChr(), params.getlStart(), params.getlEnd(),
					params.getOrganismName(), GenomicsFacade.GENEMODEL_MSU7_ONLY, GenomicsFacade.FEATURETYPE_GENE);
			listCDS.addAll(locusDAO.getLocusByRegion(params.getsChr(), params.getlStart(), params.getlEnd(),
					params.getOrganismName(), GenomicsFacade.GENEMODEL_MSU7_ONLY, GenomicsFacade.FEATURETYPE_CDS));
		} else if (params.hasSnpList()) {
			listCDS = null;
			// listCDS=locusDAO.getLocusByContigPositions(params.getsChr(),
			// params.getPoslist(), params.getOrganism(), 0,
			// GenomicsFacade.GENEMODEL_MSU7,GenomicsFacade.FEATURETYPE_GENE);
			// listCDS.addAll(locusDAO.getLocusByContigPositions(params.getsChr(),
			// params.getPoslist(), params.getOrganism(), 0,
			// GenomicsFacade.GENEMODEL_MSU7,GenomicsFacade.FEATURETYPE_CDS));
		}
		AppContext.debug("genes and cds:" + listCDS);
		return hi.createImage(pedfilenameonly + ".ped", pedfilenameonly + ".map", pedfilenameonly + ".summary.txt",
				imageformat, listCDS, genomecoord, resFactor, localWeight, kgroups, kheight, autogroup, imagesize);
	}

	@Override
	public Object[] constructPhylotree(VariantStringData dataset, PhylotreeQueryParams params) {

		phyloservice = (PhylotreeService) AppContext.checkBean(phyloservice, "PhylotreeService");
		return phyloservice.constructPhylotree(dataset, params);
	}

	@Override
	public Object[] constructPhylotree(PhylotreeQueryParams params, String requestid) {

		phyloservice = (PhylotreeService) AppContext.checkBean(phyloservice, "PhylotreeService");
		return phyloservice.constructPhylotree(params, requestid);
	}

	@Override
	public Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, Set dataset) {
		phyloservice = (PhylotreeService) AppContext.checkBean(phyloservice, "PhylotreeService");
		return phyloservice.orderVarietiesFromPhylotree(tmpfile, dataset);
	}

	@Override
	public Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, String newick, Set dataset) {
		phyloservice = (PhylotreeService) AppContext.checkBean(phyloservice, "PhylotreeService");
		return phyloservice.orderVarietiesFromPhylotree(tmpfile, newick, dataset);
	}

	// ********************************************* Methods to query SNPs as
	// SNPString
	// *********************************************************************************

	@Override
	public String getIndelType(String allele) {
		if (allele.startsWith("ref"))
			return "reference";
		else if (allele.startsWith("snp"))
			return "snp";
		else if (allele.startsWith("del")) {
			if (allele.contains("->"))
				return "substitution";
			else
				return "deletion";
		} else
			return "insertion";
	}

	@Override
	public List<SnpsAllvarsPos> getSNPPoslist(GenotypeQueryParams params) {
		genotypeservice = (VarietiesGenotypeService) AppContext.checkBean(genotypeservice, "VarietiesGenotypeService");
		return genotypeservice.getSNPPoslist(params);
	}

	@Override
	public long countSNPPoslist(GenotypeQueryParams params) {
		genotypeservice = (VarietiesGenotypeService) AppContext.checkBean(genotypeservice, "VarietiesGenotypeService");
		return genotypeservice.countSNPPoslist(params);

	}

	@Override
	public VariantStringData queryGenotype(GenotypeQueryParams params) throws Exception {
		genotypeservice = (VarietiesGenotypeService) AppContext.checkBean(genotypeservice, "VarietiesGenotypeService");
		return genotypeservice.queryVariantStringData(params);
	}

	@Override
	public long countGenotype(GenotypeQueryParams params) throws Exception {
		genotypeservice = (VarietiesGenotypeService) AppContext.checkBean(genotypeservice, "VarietiesGenotypeService");
		return genotypeservice.countVariantStringData(params);
	}

	@Override
	public VariantTable fillGenotypeTable(VariantTable table, VariantStringData data, GenotypeQueryParams params)
			throws Exception {
		genotypeservice = (VarietiesGenotypeService) AppContext.checkBean(genotypeservice, "VarietiesGenotypeService");
		return genotypeservice.fillVariantTable(table, data, params);
	}

	@Override
	public List<SnpsEffect> getSnpEffects(List poslist) {

		genotypeservice = (VarietiesGenotypeService) AppContext.checkBean(genotypeservice, "VarietiesGenotypeService");
		return genotypeservice.getSnpEffects(poslist);
	}

	@Override
	public Future<AsyncJobReport> querydownloadGenotypeAsync(GenotypeQueryParams params) throws Exception {

		genotypeasyncservice = (VarietiesGenotypeService) AppContext.checkBean(genotypeasyncservice,
				"VarietiesGenotypeAsyncService");

		String finalfilename = params.getFilename();

		// params.setFilename(finalfilename+".tmp");

		// VarietiesGenotypeAsyncService
		// asyncserv=(VarietiesGenotypeAsyncService)genotypeasyncservice;
		// VarietiesGenotypeAsyncService
		// asyncserv=(VarietiesGenotypeAsyncService)genotypeasyncservice;
		// Future future = asyncserv.queryVariantStringDataAsync(params);

		// Future future = asyncserv.queryVariantStringDataAsync(params);

		return genotypeasyncservice.queryVariantStringDataAsync(params);

		/*
		 * 
		 * String fname=new File(finalfilename).getName();
		 * 
		 * 
		 * //String url= AppContext.getHostname() + "/" + AppContext.getHostDirectory()
		 * + "/_jobs.zul?jobid=" + fname; //return new AsyncJobReport(fname ,
		 * vardata.getMessage(), url, null);
		 * 
		 * /* //Thread.sleep(1000); String fname=new File(finalfilename).getName();
		 * //String url= future.getMessage(); // AppContext.getHostname() + "/" +
		 * AppContext.getHostDirectory() + "/" + AppContext.getTempFolder() + fname +
		 * ".zip"; //String url= f // AppContext.getHostname() + "/" +
		 * AppContext.getHostDirectory() + "/" + AppContext.getTempFolder() + fname +
		 * ".zip"; //AppContext.debug("VarietiesGenotypeAsyncService result=" +
		 * future.get());
		 * 
		 * 
		 * 
		 * AppContext.debug("genotypefacade, querydownloadGenotypeAsync vardata= " +
		 * (vardata==null?"null":vardata.getMessage()));
		 * 
		 * String url= AppContext.getHostname() + "/" + AppContext.getHostDirectory() +
		 * "/_jobs.zul?jobid=" + fname; AsyncJobReport resultReport=new
		 * AsyncJobReport(fname , JobsFacade.JOBSTATUS_DONE, url, null); return new
		 * AsyncResult<AsyncJobReport>(resultReport);
		 * 
		 */
	}

	@Override
	public VariantStringData compare2Varieties(BigDecimal var1, BigDecimal var2, GenotypeQueryParams params)
			throws Exception {

		genotypeservice = (VarietiesGenotypeService) AppContext.checkBean(genotypeservice, "VarietiesGenotypeService");
		return genotypeservice.compare2VariantStrings(var1, var2, params);
	}

	@Override
	public void downloadFastaMSAPerLocus(GenotypeQueryParams param, Locus loc, String locusfilename) throws Exception {

		sequenceDAO = (SequenceDAO) AppContext.checkBean(sequenceDAO, "FeatureDAO");
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItemsDAO");

		boolean showAllRefsAllele = false;
		BufferedWriter outfasta = new BufferedWriter(new FileWriter(locusfilename));
		BufferedWriter outcsv = new BufferedWriter(new FileWriter(locusfilename.replace(".fasta", ".csv")));

		String locusid = loc.getUniquename();
		if (locusid == null || locusid.isEmpty()) {
			locusid = loc.getChr() + ":" + loc.getFmin() + ".." + loc.getFmax();
		} else
			locusid = locusid + "|" + loc.getChr() + ":" + loc.getFmin() + ".." + loc.getFmax();

		// query genotype

		GenotypeQueryParams params = new GenotypeQueryParams(param.getColLoci(), loc.getChr().toString(),
				loc.getFmin().longValue(), loc.getFmax().longValue(), param.isbSNP(), param.isbIndel(),
				param.getSnpSet(), param.getDataset(), param.getSetRun(), param.isbMismatchonly(), param.getPoslist(),
				param.getsSubpopulation(), loc.getUniquename(), param.isbAlignIndels(), showAllRefsAllele, param.getOrganism());
		VariantStringData queryRawResult = queryGenotype(params);
		Set setvars = new TreeSet(queryRawResult.getMapVariety2Order().keySet());

		// format into table
		VariantTableArray varianttable = new VariantAlignmentTableArraysImpl();
		varianttable = (VariantTableArray) fillGenotypeTable(varianttable, queryRawResult, params);
		String locusseq = sequenceDAO.getSubSequence(loc.getChr().toString(), loc.getFmin().longValue(),
				loc.getFmax().longValue(),
				listitemsDAO.getOrganismByName(param.getOrganism().getName()).getOrganismId().intValue());

		if (varianttable.getPosition().length != varianttable.getReference().length)
			throw new RuntimeException();
		if (varianttable.getPosition().length != varianttable.getVaralleles()[0].length)
			throw new RuntimeException();

		// generate sequence between two variant positions
		Position pos[] = varianttable.getPosition();
		Map<BigDecimal, String> mapPos2NextSequence = new HashMap();
		long lfmin = loc.getFmin().longValue();

		long querystart = 0;
		long queryend = (pos[0].getPosition().longValue() - 1) - lfmin + 1;
		AppContext.debug(lfmin + "-" + (pos[0].getPosition().longValue() - 1) + "==>" + querystart + "-"
				+ (queryend - 1) + "/" + locusseq.length());
		mapPos2NextSequence.put(BigDecimal.valueOf(loc.getFmin()),
				locusseq.substring((int) querystart, (int) queryend));

		boolean contseq = true;
		for (int ipos = 0; ipos < pos.length && contseq; ipos++) {

			BigDecimal bdposi = pos[ipos].getPosition();
			BigDecimal bdposip1 = null;
			if (ipos == pos.length - 1) {
				bdposip1 = BigDecimal.valueOf(loc.getFmax() + 1);
			} else
				bdposip1 = pos[ipos + 1].getPosition();

			if (bdposip1.compareTo(BigDecimal.valueOf(loc.getFmax())) > 0) {
				bdposip1 = BigDecimal.valueOf(loc.getFmax() + 1);
				contseq = false;
			}

			if (bdposi.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
				// whole number
				long lposi = bdposi.longValue();
				long lposip1 = bdposip1.longValue();
				if (lposi + 1 == lposip1)
					mapPos2NextSequence.put(bdposi, strBlank);
				else if (lposi == lposip1)
					mapPos2NextSequence.put(bdposi, strBlank);
				else {
					querystart = lposi + 1 - lfmin;
					queryend = (lposip1) - lfmin;
					AppContext.debug((lposi + 1) + "-" + (lposip1 - 1) + "==>" + querystart + "-" + (queryend - 1) + "/"
							+ locusseq.length());
					mapPos2NextSequence.put(bdposi, locusseq.substring((int) querystart, (int) queryend));
				}
			} else {
				// insertion
				if (bdposip1.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
					// next is wholenum
					long lposi = bdposi.longValue();
					long lposip1 = bdposip1.longValue();
					querystart = lposi + 1 - lfmin;
					queryend = (lposip1) - lfmin;

					AppContext.debug((lposi + 1) + "-" + (lposip1 - 1) + "==>" + querystart + "-" + (queryend - 1) + "/"
							+ locusseq.length());

					mapPos2NextSequence.put(bdposi, locusseq.substring((int) querystart, (int) queryend));

				} else {
					mapPos2NextSequence.put(bdposi, strBlank);
				}
			}
		}

		// print table
		Object alleles[][] = varianttable.getVaralleles();
		Map<String, Integer> mapNames = new TreeMap();
		String varnames[] = varianttable.getVarname();
		for (int ivar = 0; ivar < varnames.length; ivar++) {
			mapNames.put(varnames[ivar], ivar);
		}

		String reference[] = varianttable.getReference();

		StringBuffer buff = new StringBuffer();
		StringBuffer buffCsv = new StringBuffer();
		buff.append(">REFERENCE_NIPPONBARE|" + locusid + "\n");
		buff.append(mapPos2NextSequence.get(BigDecimal.valueOf(loc.getFmin())).toLowerCase());
		buffCsv.append("VARIETYxPOSITION,SUBPOPULATION,");
		for (int ipos = 0; ipos < pos.length; ipos++) {
			BigDecimal bdposi = pos[ipos].getPosition();
			buffCsv.append(bdposi.toString().replaceAll(".00", ""));
			if (ipos < pos.length - 1)
				buffCsv.append(",");
		}
		buffCsv.append("\n");
		buffCsv.append("REFERENCE_NIPPONBARE,,");
		for (int ipos = 0; ipos < pos.length; ipos++) {
			BigDecimal bdposi = pos[ipos].getPosition();
			buff.append(AppContext.getIUPAC(reference[ipos]));
			if (mapPos2NextSequence.containsKey(bdposi))
				buff.append(mapPos2NextSequence.get(bdposi).toLowerCase());

			buffCsv.append(AppContext.getIUPAC(reference[ipos]));
			if (ipos < pos.length - 1)
				buffCsv.append(",");
		}
		buff.append("\n");
		buffCsv.append("\n");

		outfasta.append(buff);
		outcsv.append(buffCsv);

		Iterator<String> itNames = mapNames.keySet().iterator();
		while (itNames.hasNext()) {
			int ivar = mapNames.get(itNames.next());

			Object ivaralleles[] = alleles[ivar];

			Variety var = listitemsDAO.getMapVarname2Variety(params.getDataset()).get(varnames[ivar].toUpperCase());
			if (var == null)
				throw new RuntimeException("can't find variety " + varnames[ivar].toUpperCase());

			buff = new StringBuffer();
			buffCsv = new StringBuffer();
			buff.append(">" + varnames[ivar] + "|" + locusid + "\n");
			buff.append(mapPos2NextSequence.get(BigDecimal.valueOf(loc.getFmin())).toLowerCase());

			buffCsv.append("\"" + varnames[ivar] + "\"," + var.getSubpopulation() + ",");
			for (int ipos = 0; ipos < pos.length; ipos++) {
				BigDecimal bdposi = pos[ipos].getPosition();
				String iupac = AppContext.getIUPAC((String) ivaralleles[ipos]);
				buff.append(iupac);
				if (mapPos2NextSequence.containsKey(bdposi))
					buff.append(mapPos2NextSequence.get(bdposi).toLowerCase());
				buffCsv.append((String) ivaralleles[ipos]);
				if (ipos < pos.length - 1)
					buffCsv.append(",");
			}

			buffCsv.append("\n");
			buff.append("\n");
			outfasta.append(buff);
			outcsv.append(buffCsv);
		}

		outfasta.flush();
		outfasta.close();
		outcsv.flush();
		outcsv.close();

	}

	@Override
	public void downloadGenotypeGenome(GenotypeQueryParams params) throws Exception {

		if (!params.getOrganismName().equals(AppContext.JAPONICA_NIPPONBARE)) {
			throw new RuntimeException("Available only for reference Nipponbare");

		}

		boolean bSplitAllele2 = false;
		boolean hasRowHeader = true;
		boolean hasColHeader = true;
		String delimiter = params.getDelimiter();
		String filenames[] = new String[12];
		String tmpname = AppContext.createTempFilename();
		String fileext = ".txt";
		if (delimiter.equals(","))
			fileext = ".csv";
		boolean bIsPlink = false;
		boolean bIsHapmap = false;
		if (delimiter.equals("plink")) {
			fileext = ".ped";
			bSplitAllele2 = true;
			hasColHeader = false;
			hasRowHeader = false;
			bIsPlink = true;
			delimiter = "\t";
		}
		if (delimiter.equals("hapmap")) {
			fileext = ".ped";
			bSplitAllele2 = true;
			hasColHeader = false;
			hasRowHeader = false;
			bIsHapmap = true;
			delimiter = "\t";
		}

		AppContext.resetTimer("query whole genome start..");

		for (int ichr = 1; ichr <= 12; ichr++) {
			// Integer chrlen = getFeatureLength( Integer.toString(ichr));
			/*
			 * String chrstr= Integer.toString(ichr); if(ichr<10) chrstr = "0" +
			 * Integer.toString(ichr);
			 */
			Integer chrlen = getFeatureLength("chr" + ichr, params.getOrganism().getName());
			params.setlStart(1L);
			params.setlEnd(Long.valueOf(chrlen));
			// params.setsChr( String.valueOf(ichr));
			params.setsChr("chr" + ichr);

			VariantStringData varstrchr = queryGenotype(params);

			filenames[ichr - 1] = "chr-" + ichr + "-" + params.getFilename() + "-" + tmpname + fileext;

			if (bIsPlink) {
				StringBuffer buffMap = new StringBuffer();
				Iterator<SnpsAllvarsPos> itPos = varstrchr.getListPos().iterator();
				while (itPos.hasNext()) {
					SnpsAllvarsPos posnuc = itPos.next();
					buffMap.append(posnuc.getPosition());
				}

				// File file = new File(filenames[ichr-1] + ".map");
				FileWriter fw = new FileWriter(filenames[ichr - 1] + ".map");
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(buffMap.toString());
				bw.flush();
				bw.close();
			}

			FileWriter writer = new FileWriter(filenames[ichr - 1]);

			if (!bIsPlink && !bIsHapmap)
				writer.append("REGION: WHOLE CHR " + ichr + " " + 1 + ".." + chrlen + "\n");

			// newpagelist = genotype.getSNPStringInAllVarieties(0, chrlen,
			// Integer.valueOf(ichr)); //, true, -1, -1 );

			VariantTable filetable = fillGenotypeTable(
					new VariantTableSerialImpl(fileext, writer, bSplitAllele2, hasRowHeader, hasColHeader), varstrchr,
					params);
			writer.flush();
			writer.close();
			// updateAllvarsList(newpagelist,true,filenames[ichr-1] ,delimiter, "REGION:
			// WHOLE CHR " + ichr + " " + 1 + ".." + chrlen, false , Integer.valueOf(ichr));

		}
		String allzipfilenames[] = filenames;
		if (bIsPlink) {
			filenames = new String[24];
			for (int i = 1; i < 12; i++) {
				allzipfilenames[i - 1] = filenames[i - 1];
			}
			for (int i = 1; i < 12; i++) {
				allzipfilenames[i + 12 - 1] = filenames[i - 1] + ".map";
			}
		}
		new CreateZipMultipleFiles(AppContext.getTempDir() + params.getFilename() + "-" + tmpname + ".zip",
				allzipfilenames).create();
		Filedownload.save(new File(AppContext.getTempDir() + params.getFilename() + "-" + tmpname + ".zip"),
				"application/zip");
		AppContext.debug("File download complete! Saved to: " + params.getFilename());
		AppContext.resetTimer("query whole genome done..");

	}

	@Override
	public Set checkSNPInChromosome(Integer organismId, String chr, Set setSNP, Set type) {

		// snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(
		// snpstringallvarsposService, "VSnpRefposindexDAO");
		// snpstringallvarsposService.
		List listtmp = new ArrayList();
		listtmp.addAll(setSNP);
		// return new HashSet(snpstringallvarsposService.getSNPsInChromosome(chr,
		// listtmp, type));

		genotypeservice = (VarietiesGenotypeService) AppContext.checkBean(genotypeservice, "VarietiesGenotypeService");
		return new HashSet(genotypeservice.checkSNPsInChromosome(organismId, chr, listtmp, type));
	}

	@Override
	public boolean displayHapotypeTreeImage(String haplofilename, String format, double kheight, Integer imagesize) {

		HaplotypeImageService hi = new HaplotypeImageRHeatmapServiceImpl(AppContext.getTempDir());
		return hi.displayHapotypeTreeImage(haplofilename, format, kheight, imagesize);

	}

	@Override
	public double getMaxLog2treeheight(String haplofilename) {
		HaplotypeImageService hi = new HaplotypeImageRHeatmapServiceImpl(AppContext.getTempDir());
		return hi.getMaxLog2treeheight(haplofilename);

	}

	@Override
	public double[] getMinMaxLog2treeheight(String haplofilename) {
		HaplotypeImageService hi = new HaplotypeImageRHeatmapServiceImpl(AppContext.getTempDir());
		return hi.getMinMaxLog2treeheight(haplofilename);

	}

	@Override
	public List<String> getGenenames(String reference) {
		// TODO Auto-generated method stub
		return null;
	}

}
