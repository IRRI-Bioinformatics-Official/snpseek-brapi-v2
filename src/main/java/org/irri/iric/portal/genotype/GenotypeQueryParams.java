package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.irri.iric.ds.chado.domain.MultiReferenceLocus;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.VarietyPlus;
import org.irri.iric.ds.chado.domain.VarietyPlusPlus;
import org.irri.iric.ds.chado.domain.impl.MultiReferenceLocusImpl;
import org.irri.iric.ds.chado.domain.model.Organism;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.Query;
import org.irri.iric.portal.variety.VarietyFacade;

/**
 * Genotype query parameters
 * 
 * @author lmansueto
 *
 */

public class GenotypeQueryParams extends Query  {

	/*
	 * public static String SNP_ALL="all_snp"; public static String
	 * SNP_BASE="base_snp"; public static String SNP_FILTERED="filtered_snp"; public
	 * static String SNP_CORE="core_snp"; public static String
	 * SNP_BIALL="biallelic_snp";
	 */
	/*
	 * <listitem value="all_snp" label="All (32M)"/> <listitem value="base_snp"
	 * label="Base (18M)"/> <listitem value="filtered_snp" selected="true"
	 * label="Filtered (4.8M)"/> <listitem value="core_snp" label="Core (404k)"/>
	 */

	public void setSetRun(Set setRun) {
		this.setRun = setRun;
	}

	public Set getSetRun() {
		return setRun;
	}

	public Set getDataset() {
		return setDataset;
	}

	public void setDataset(Set setDataset) {
		this.setDataset = setDataset;
	}

	// reference organism
	private Organism organism;
	// list of variety IDs from variety list
	private Collection colVarIds;
	// contig name
	private String sChr;
	// start bp position
	private Long lStart = -1L;
	// end base position
	private Long lEnd = -1L;
	// include SNPs
	private boolean bSNP;
	// include indels
	private boolean bIndel;
	// include hetero
	private boolean bHeteroIndel;

	// use core SNPs only
	/*
	 * private boolean bCoreonly; private boolean bFilteredonly; private boolean
	 * bBaseonly; private boolean bBialleliconly;
	 */

	// private String sSnpSet=SNP_ALL;
	private Set setSnp = new LinkedHashSet();

	// query mismatch only
	private boolean bMismatchonly;
	// list of positions from SNP list
	private Collection poslist;
	// return only varieties in subpopulation
	private String sSubpopulation;
	// query locus region
	private String sLocus;
	// query regions on list of loci, from Locus list
	private Collection colLoci;
	// query is 2-variety comparison
	private boolean bPairwiseComparison = false;
	private boolean bPairwiseComparisonAccession = true;
	// align the indels
	private boolean bAlignIndels = true;
	// delimiter in table download
	private String delimiter;

	// color table alleles base on mismatch with reference
	private boolean bColorByMismatch = true;
	// color alleles by value
	private boolean bColorByAllele = false;
	// result is fasta format, don't display table
	private boolean bFastaSequence = false;
	// count missing as 0.5
	private boolean bCountMissingAs05 = false;
	// download without displaying
	private boolean bDownloadOnly = false;

	// async query
	private boolean bWaitResult = true;

	private Integer[] varidStartEnd;

	// variety genotype filter
	private BigDecimal varAlleleFilter = null;

	private VariantStringData variantdata;

	private boolean bGenerateHapmap = false;

	// for the following options, query is for all snps and option
	// affects only the display, change of option after first query
	// only redraws the table
	// display all snps
	// private boolean bAllSnps=false;

	// private String reqstr;

	public boolean isbDownloadOnly() {
		return bDownloadOnly;
	}

	public boolean hasPreviousData() {
		return variantdata != null;
	}

	public void setbDownloadOnly(boolean bDownloadOnly) {
		this.bDownloadOnly = bDownloadOnly;
	}

	// display all snps, highlight the non-synonymous
	private boolean bHighlightNonsynSnps = false;
	// display only non-synonymous
	private boolean bNonsynSnps = false;
	// display only non-synonymous plus splice sites
	private boolean bNonsynPlusSpliceSnps = false;

	// for multiple reference query
	// return only contigs aligning with the query contigs
	private boolean limitToQueryContig = true;
	// if reference is not Nipponbare, display the Nipponbare coordinates
	private boolean bShowNPBPositions = false;
	// show alleles for all reference genomes
	private boolean bShowAllRefAlleles = false;
	// has allele filters in poslist
	private boolean bAlleleFilter = false;

	// display phenotype in table
	private String phenotype = null;

	// dataset to use,
	private Set setDataset = new LinkedHashSet();

	// genotyping run
	private Set setRun = new LinkedHashSet();

	// dataset position ops
	private String datasetPosOps = VarietyFacade.DATASET_SNPPOS_INTERSECT;

	private Date date = new Date();

	private Long page;
	private Long pageSize;
	
	private String organismName;

	public void setbAlignIndels(boolean bAlignIndels) {
		this.bAlignIndels = bAlignIndels;
	}

	/**
	 * 
	 * @param colVarIds
	 *            Collection of variety IDs
	 * @param sChr
	 *            Contig name
	 * @param lStart
	 *            Start bp position
	 * @param lEnd
	 *            End bp position
	 * @param bSNP
	 *            Query SNPs
	 * @param bIndel
	 *            Query INDELs
	 * @param bCoreonly
	 *            Use core SNPs omly
	 * @param bMismatchonly
	 *            Include only varieties with mismatch in region
	 * @param poslist
	 *            Position list
	 * @param sSubpopulation
	 *            Query only subpopulation
	 * @param sLocus
	 *            Query within locus
	 * @param bAlignIndels
	 *            Display INDels as multiple sequence alignment
	 * @param bShowAllRefAlleles
	 *            Show alleles for all reference genomes
	 */
	public GenotypeQueryParams(Collection colVarIds, String sChr, Long lStart, Long lEnd, boolean bSNP, boolean bIndel,
			Set snpset, Set dataset, Set genotyperun, boolean bMismatchonly, Collection poslist, String sSubpopulation,
			String sLocus, boolean bAlignIndels, boolean bShowAllRefAlleles, Organism organism) {
		super();

		// setDataset.add(AppContext.getSNPSet());
		// setDataset.add(SnpsAllvarsPosDAO.DATASET_SNPINDELV1);

		this.organism = organism;
		this.organismName = organism.getName();
		this.sChr = sChr;
		this.lStart = lStart;
		this.lEnd = lEnd;
		this.bSNP = bSNP;
		this.bIndel = bIndel;
		this.bMismatchonly = bMismatchonly;
		this.poslist = poslist;
		this.sSubpopulation = sSubpopulation;
		this.sLocus = sLocus;
		this.bAlignIndels = bAlignIndels;
		this.bShowAllRefAlleles = bShowAllRefAlleles;
		this.colVarIds = colVarIds;
		// setSnpSet(snpset);
		this.setSnp = snpset;
		this.setDataset = dataset;
		this.setRun = genotyperun;

		if (colVarIds != null && !colVarIds.isEmpty()) {
			Set varids = new HashSet();
			Iterator itvarids = colVarIds.iterator();
			while (itvarids.hasNext()) {
				Object objvar = itvarids.next();
				if (objvar instanceof BigDecimal)
					varids.add(objvar);
				else if (objvar instanceof VarietyPlusPlus) {
					VarietyPlusPlus vpp = (VarietyPlusPlus) objvar;
					varids.add(vpp.getVarietyId());
				}
				else if (objvar instanceof Variety)
					varids.add(((Variety) objvar).getStockSampleId());
				else if (objvar instanceof Long)
					varids.add(BigDecimal.valueOf((Long) objvar));
				else if (objvar instanceof Integer)
					varids.add(BigDecimal.valueOf((Integer) objvar));
			}
			this.colVarIds = varids;
		}

		AppContext.info(toString());

		// this.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);

		toString();
	}

	public BigDecimal getVarAlleleFilter() {
		return varAlleleFilter;
	}

	public void setVarAlleleFilter(BigDecimal varAlleleFilter) {
		this.varAlleleFilter = varAlleleFilter;
	}

	public boolean isVarAlleleFilter() {
		return varAlleleFilter != null;
	}

	public void addSnpSet(String snpset) {
		setSnp.add(snpset);
	}

	public void setSnpSet(Set snpset) {
		setSnp = snpset;
		/*
		 * bAllSnps=false; bCoreonly=false; bFilteredonly=false; bBaseonly=false;
		 * bBialleliconly=false;
		 * 
		 * if(snpset.equals(SNP_ALL)) { bAllSnps=true; } else
		 * if(snpset.equals(SNP_CORE)) { bCoreonly=true; } else
		 * if(snpset.equals(SNP_FILTERED)) { bFilteredonly=true; } else
		 * if(snpset.equals(SNP_BASE)) { bBaseonly=true; } else
		 * if(snpset.equals(SNP_BIALL)) { bBialleliconly=true; }
		 */
	}

	@Override
	public String toString() {
		String posvarids = ";submitter=" + this.submitter + ";downloadonly=" + this.bDownloadOnly + ";bAlleleFilter="
				+ bAlleleFilter + ";bCountMissingAs05=" + bCountMissingAs05;
		if (poslist != null)
			posvarids += ";poslist=" + poslist.size();
		if (colVarIds != null)
			posvarids += ";varlist=" + colVarIds.size();
		if (this.colLoci != null)
			posvarids += ";colLoci=" + colLoci.size();
		posvarids += ";dataset=" + this.setDataset + ";genotyperun=" + this.setRun;
		posvarids += ";filename=" + filename;

		/*
		 * if(reqstr==null) { Object req = null; if(Executions.getCurrent()!=null) req=
		 * Executions.getCurrent().getNativeRequest(); reqstr=""; if(req !=null && req
		 * instanceof HttpServletRequest) { HttpServletRequest servreq=
		 * (HttpServletRequest)req; //reqstr="-"+ servreq.getRemoteAddr() + "-" +
		 * servreq.getRemoteHost();
		 * 
		 * String forwardedfor= servreq.getHeader("x-forwarded-for");
		 * if(forwardedfor!=null) reqstr=forwardedfor; //"-" + forwardedfor; else
		 * reqstr= servreq.getRemoteAddr() + "-" + servreq.getRemoteHost(); } }
		 */

		String snpset = "";
		/*
		 * if(this.isbBaseonly()) snpset="base"; else if(this.isbAllSnps())
		 * snpset="all"; else if(this.isbCoreonly()) snpset="core"; else
		 * if(this.isbFilteredonly()) snpset="filtered";
		 */

		String str = "GenotypeQueryParams: " + super.toString() + ";chr=" + sChr + ";start=" + lStart + ";end=" + lEnd
				+ ";snp=" + bSNP + ";indel=" + bIndel + ";mismatchonly=" + bMismatchonly
				+ (sSubpopulation != null ? ";subpupulation=" + sSubpopulation : "")
				+ (sLocus != null ? ";locus=" + sLocus : "") + ";reference=" + this.organismName + posvarids + ";snpset="
				+ snpset + ";hapmap=" + this.bGenerateHapmap + "\n" + AppContext.getSystemStatus();

		/*
		 * if(Sessions.getCurrent()!=null) { str="QUERY time:" +
		 * AppContext.getDateFormat().format(date) + ";local host=" +
		 * Sessions.getCurrent().getLocalAddr() + "; " +
		 * Sessions.getCurrent().getLocalName() + ";" + ";remote host=" +
		 * Sessions.getCurrent().getRemoteAddr() + "; " +
		 * Sessions.getCurrent().getRemoteHost() + ";chr=" + sChr +";start=" + lStart +
		 * ";end=" + lEnd + ";snp=" + bSNP + ";indel=" + bIndel + ";iscore=" + bCoreonly
		 * + ";mismatchonly=" + bMismatchonly + ";subpupulation=" + sSubpopulation +
		 * ";locus=" + sLocus + posvarids; } else { str="QUERY time:" +
		 * AppContext.getDateFormat().format(date) + ";;;;;;" + "chr=" + sChr +";start="
		 * + lStart + ";end=" + lEnd + ";snp=" + bSNP + ";indel=" + bIndel + ";iscore="
		 * + bCoreonly + ";mismatchonly=" + bMismatchonly + ";subpupulation=" +
		 * sSubpopulation + ";locus=" + sLocus + posvarids ; }
		 */

		return str;
	}

	/**
	 * Change the position
	 * 
	 * @param newPos
	 *            New position
	 * @return Old position
	 */
	public MultiReferenceLocus setNewPosition(MultiReferenceLocus newPos) {
		MultiReferenceLocus origPos = new MultiReferenceLocusImpl(this.getOrganismName(), this.sChr, this.lStart.intValue(),
				this.lEnd.intValue(), 1);
		this.sChr = newPos.getContig();
		this.lStart = newPos.getFmin().longValue();
		this.lEnd = newPos.getFmax().longValue();
		this.organismName = newPos.getOrganism();

		
		return origPos;
	}

	/**
	 * Gets loci collection
	 * 
	 * @return
	 */
	public Collection getColLoci() {
		return colLoci;
	}

	/**
	 * Sets loci collection
	 * 
	 * @param colLoci
	 */
	public void setColLoci(Collection colLoci) {
		if (colLoci != null && !colLoci.isEmpty())
			this.colLoci = colLoci;

	}

	/**
	 * Sets SNPs color scheme
	 * 
	 * @param bColorByMismatch
	 * @param bColorByAllele
	 */
	public void setColors(boolean bColorByMismatch, boolean bColorByAllele) {
		this.bColorByMismatch = bColorByMismatch;
		this.bColorByAllele = bColorByAllele;
		AppContext.info("QUERY COLOR mismatch=" + bColorByMismatch + ";allel=" + bColorByAllele);
	}

	/**
	 * Sets which SNP types to include
	 * 
	 * @param bAllSnps
	 *            All SNPs
	 * @param bHighlightNonsynSnps
	 *            All, highlight non-synonymous SNPs
	 * @param bNonsynSnps
	 *            Include non-synonymous SNPs only
	 * @param bNonsynPlusSpliceSnps
	 *            Include non-synonymous, splice site donor and acceptor SNPs
	 */
	public void setIncludedSnps(boolean bHighlightNonsynSnps, boolean bNonsynSnps, boolean bNonsynPlusSpliceSnps) {

		// setSnpSet(snpset);
		// addSnpSet(snpset);
		this.bHighlightNonsynSnps = bHighlightNonsynSnps;
		this.bNonsynSnps = bNonsynSnps;
		this.bNonsynPlusSpliceSnps = bNonsynPlusSpliceSnps;
		AppContext.info("QUERY SNP snpset=" + setSnp + ";highlightnonsyn=" + bHighlightNonsynSnps + ";nonsynonly="
				+ bNonsynSnps + ";nonsynplussplice" + bNonsynPlusSpliceSnps);
	}

	public Collection getColVarIds() {
		return colVarIds;
	}

	public String getsChr() {
		return sChr;
	}

	public Long getlStart() {
		return lStart;
	}

	public Long getlEnd() {
		return lEnd;
	}

	public boolean isbSNP() {
		return bSNP;
	}

	public boolean isbIndel() {
		return bIndel;
	}
	/*
	 * public boolean isbCoreonly() { return sSnpSet.equals(SNP_CORE); }
	 */

	public boolean isbMismatchonly() {
		return bMismatchonly;
	}

	public Collection getPoslist() {
		return poslist;
	}

	public String getsSubpopulation() {
		return sSubpopulation;
	}

	public String getsLocus() {
		return sLocus;
	}

	public boolean isbAlignIndels() {
		return bAlignIndels;
	}

	public void setColVarIds(Collection colVarIds) {
		this.colVarIds = colVarIds;
	}

	public void setbMismatchonly(boolean bMismatchonly) {
		this.bMismatchonly = bMismatchonly;
	}

	public void setsSubpopulation(String sSubpopulation) {
		this.sSubpopulation = sSubpopulation;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public void setsChr(String sChr) {
		this.sChr = sChr;
	}

	public void setlStart(Long lStart) {
		this.lStart = lStart;
	}

	public void setlEnd(Long lEnd) {
		this.lEnd = lEnd;
	}

	public boolean isbHighlightNonsynSnps() {
		return bHighlightNonsynSnps;
	}

	public boolean isbNonsynSnps() {
		return bNonsynSnps;
	}

	public boolean isbNonsynPlusSpliceSnps() {
		return bNonsynPlusSpliceSnps;
	}

	public boolean isbColorByMismatch() {
		return bColorByMismatch;
	}

	public void setbColorByMismatch(boolean bColorByMismatch) {
		this.bColorByMismatch = bColorByMismatch;
	}

	public boolean isbColorByAllele() {
		return bColorByAllele;
	}

	public void setbColorByAllele(boolean bColorByAllele) {
		this.bColorByAllele = bColorByAllele;
	}

	/**
	 * Query is two-variety comparison
	 * 
	 * @param name1
	 * @param name2
	 */
	public void setPairwiseComparison(String name1, String name2, boolean useAccession) {

		AppContext.info("QUERY PAIRWISE var1=" + name1 + ";var2=" + name2);
		this.bPairwiseComparison = true;
		this.bPairwiseComparisonAccession = useAccession;
	}

	public boolean isbPairwiseComparison() {
		return bPairwiseComparison;
	}

	public boolean hasLocusList() {
		return this.colLoci != null && !this.colLoci.isEmpty();
	}

	public boolean hasSnpList() {
		return this.poslist != null && !this.poslist.isEmpty();
	}

	public String getOrganismName() {
		return organismName;
	}

	/**
	 * Sets the reference genome to query
	 * 
	 * @param organism
	 *            Organism common name
	 * @param bShowNPBPositions
	 *            For non-Nipponbare reference, show the Nipponbare positions
	 * @param limitToQueryContig
	 *            Query only this contig
	 */
	public void setOrganism(String organism, boolean bShowNPBPositions, boolean limitToQueryContig) {
		this.organismName = organism;
		this.bShowNPBPositions = bShowNPBPositions;
		this.limitToQueryContig = limitToQueryContig;
	}

	public boolean isbShowNPBPositions() {
		return bShowNPBPositions;
	}

	public boolean isLimitToQueryContig() {
		return limitToQueryContig;
	}

	public boolean isLocusList() {
		return this.colLoci != null && !colLoci.isEmpty();
	}

	public boolean isSNPList() {
		return this.poslist != null && !poslist.isEmpty();
	}

	public boolean isbShowAllRefAlleles() {
		return bShowAllRefAlleles;
	}

	public void addDataset(String setname) {
		this.setDataset.add(setname);
	}

	public boolean includeDataset(String setname) {
		return setDataset.contains(setname);
	}

	public boolean isbFastaSequence() {
		return bFastaSequence;
	}

	public void setbFastaSequence(boolean bFastaSequence) {
		this.bFastaSequence = bFastaSequence;
	}

	public String getPhenotype() {
		return phenotype;
	}

	public void setPhenotype(String phenotype) {
		this.phenotype = phenotype;
	}

	public void setbIndel(boolean bIndel) {
		this.bIndel = bIndel;
	}

	public boolean isbHeteroIndel() {
		return bHeteroIndel;
	}

	public void setbHeteroIndel(boolean bHeteroIndel) {
		this.bHeteroIndel = bHeteroIndel;
	}

	public boolean isbAlleleFilter() {
		return bAlleleFilter;
	}

	public void setbAlleleFilter(boolean bAlleleFilter) {
		this.bAlleleFilter = bAlleleFilter;
	}

	public void setVarietyAlleleFilter(BigDecimal varid) {
		this.bAlleleFilter = bAlleleFilter;
	}

	public boolean isbCountMissingAs05() {
		return bCountMissingAs05;
	}

	public void setbCountMissingAs05(boolean bCountMissingAs05) {
		this.bCountMissingAs05 = bCountMissingAs05;
	}

	public boolean isbWaitResult() {
		return bWaitResult;
	}

	public void setbWaitResult(boolean bWaitResult) {
		this.bWaitResult = bWaitResult;
	}

	public Integer[] getVaridStartEnd() {
		return varidStartEnd;
	}

	public boolean hasVaridRange() {
		return varidStartEnd != null;
	}

	public void setVaridStartEnd(Integer[] varidStartEnd) {
		this.varidStartEnd = varidStartEnd;
	}

	public VariantStringData getVariantdata() {
		return variantdata;
	}

	public void setVariantdata(VariantStringData variantdata) {
		this.variantdata = variantdata;
		if (variantdata != null) {
			variantdata.clearVarietyData();
		}
	}

	public boolean hasVarlist() {
		return this.colVarIds != null && !this.colVarIds.isEmpty();
	}

	public boolean isRegion() {
		return this.lEnd != null && this.lStart != null;
	}

	/*
	 * public String getDataset() { return
	 * (String)this.setDataset.iterator().next(); }
	 */

	public void setDataset(String dataset) {
		setDataset = new HashSet();
		setDataset.add(dataset);
	}

	public String getDatasetPosOps() {
		return datasetPosOps;
	}

	public void setDatasetPosOps(String datasetPosOps) {
		this.datasetPosOps = datasetPosOps;
	}

	/*
	 * public boolean isbAllSnps() { return sSnpSet.equals(SNP_ALL); }
	 * 
	 * public boolean isbFilteredonly() { return sSnpSet.equals(SNP_FILTERED); }
	 * 
	 * public boolean isbBaseonly() { return sSnpSet.equals(SNP_BASE); }
	 * 
	 * public boolean isbBialleliconly() { return sSnpSet.equals(SNP_BIALL); }
	 */

	/*
	 * public String getSnpSet() { return sSnpSet; }
	 */

	public Set getSnpSet() {
		return setSnp;
	}

	public void setPaging(long page, long pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}

	public Long getPage() {
		return page;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public boolean hasPaging() {
		return page != null && pageSize != null;
	}

	public boolean hasChrNoPosRange() {
		return this.sChr != null && !this.sChr.isEmpty()
				&& (this.lEnd == null || this.lEnd == -1 || this.lStart == null || this.lStart == -1);
	}

	public boolean hasChrPosRange() {
		return this.sChr != null && !this.sChr.isEmpty() && this.lEnd != null && this.lEnd > -1 && this.lStart != null
				&& this.lStart > -1;
	}

	public void setPoslist(String chr, Collection poslist) {
		this.poslist = poslist;
		this.sChr = chr;
	}

	public boolean isbGenerateHapmap() {
		return bGenerateHapmap;
	}

	public void setbGenerateHapmap(boolean bGenerateHapmap) {
		this.bGenerateHapmap = bGenerateHapmap;
	}

	public Organism getOrganism() {
		return organism;
	}

}
