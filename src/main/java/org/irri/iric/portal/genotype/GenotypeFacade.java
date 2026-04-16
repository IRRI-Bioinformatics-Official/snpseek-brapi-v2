package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import org.irri.iric.ds.chado.domain.Gene;
import org.irri.iric.ds.chado.domain.GenotypeRunPlatform;
import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.chado.domain.Scaffold;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.irri.iric.ds.chado.domain.VarietyDistance;
import org.irri.iric.ds.chado.domain.model.Organism;
import org.irri.iric.portal.admin.AsyncJobReport;

/**
 * API functions used mostly by SNP Query Controller
 * 
 * @author LMansueto
 *
 */

public interface GenotypeFacade {

	/**
	 * Query mode SNPQUERY_VARIETIES query and compare two varieties
	 * SNPQUERY_ALLREFPOS all snp positions in the reference
	 * SNPQUERY_ALLVARIETIESPOS query for all varieties
	 * 
	 * @author lmansueto
	 *
	 */
	public static enum snpQueryMode {
		SNPQUERY_VARIETIES, SNPQUERY_ALLVARIETIESPOS
	} // SNPQUERY_ALLREFPOS, }

	public static final String strBlank = "";
	public static final String strGap = "-";
	public static final String strN = "N";
	public static final String strMissing = "?";;

	// Queries for UI list item values

	/**
	 * Get list of variety names
	 * 
	 * @return
	 */
	// public java.util.List<String> getVarnames();
	public java.util.List<String> getVarnames(String dataset);

	public java.util.List<String> getVaraccessions(String dataset);

	/**
	 * Get variety subpopulations
	 * 
	 * @return
	 */
	// public java.util.List<String> getSubpopulations();
	public List<String> getSubpopulations(String dataset);

	/**
	 * Get list of gene names
	 * 
	 * @return
	 */
	public java.util.List<String> getGenenames();

	/**
	 * Get list of chromosome names
	 * 
	 * @return
	 */
	public java.util.List<String> getChromosomes();

	// Query for Variety instances from constraints

	/**
	 * Gets varieties in a subpopulation
	 * 
	 * @param subpopulation
	 * @return
	 */
	// public Set getVarietiesForSubpopulation(String subpopulation);
	public Set getVarietiesForSubpopulation(String subpopulation, String dataset);

	// Query for Gene instance
	/**
	 * Get Gene object with name
	 * 
	 * @param name
	 * @return
	 */
	public Gene getGeneFromName(String name, String organism);

	public List<Gene> getGeneFromNames(Collection names, String organism);

	// Methods for phylogenetic tree construction based on allele mismatches
	/**
	 * Construct phylogenetic tree based on mismatches within the region
	 * 
	 * @param scale
	 * @param chr
	 * @param start
	 * @param end
	 * @return
	 */
	// public Object[] constructPhylotree(String scale, String chr, int start, int
	// end, String requestid);
	Object[] constructPhylotree(VariantStringData dataset, PhylotreeQueryParams params);

	Object[] constructPhylotree(PhylotreeQueryParams params, String requestid);

	Object[] constructPhylotree(String scale, String chr, int start, int end, String requestid, Set dataset);

	/**
	 * Get sorted variety based on the phylogenetic tree result
	 * 
	 * @param tmpfile
	 * @param newick
	 * @return
	 */
	public Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, String newick, Set dataset);

	public Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, Set dataset);

	// Query for MyList definition
	/**
	 * Verify the snps in setSNP and return only the valid ones
	 * 
	 * @param chr
	 * @param setSNP
	 * @param type
	 * @return
	 */
	// public Set checkSNPInChromosome(String chr, Set setSNP, BigDecimal type);
	public Set checkSNPInChromosome(Integer organismId, String chr, Set setSNP, Set type);

	/**
	 * Get the indel type (reference, insertion, deletion, snp) for the given allele
	 * format
	 * 
	 * @param allele
	 * @return
	 */
	String getIndelType(String allele);

	/**
	 * Query using GenotypeQueryParams class
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	VariantStringData queryGenotype(GenotypeQueryParams params) throws Exception;

	public List<SnpsAllvarsPos> getSNPPoslist(GenotypeQueryParams params);

	public long countSNPPoslist(GenotypeQueryParams params);

	/**
	 * Download the genotype (SNP/Indels) for the entire genome
	 * 
	 * @param params
	 * @throws Exception
	 */
	void downloadGenotypeGenome(GenotypeQueryParams params) throws Exception;

	/**
	 * Query and download genotype asynchronously
	 */
	public Future<AsyncJobReport> querydownloadGenotypeAsync(GenotypeQueryParams params) throws Exception;

	/**
	 * Populate the variant table VariantTable with query result VariantStringData
	 * 
	 * @param table
	 * @param data
	 * @param params
	 * @return
	 * @throws Exception
	 */
	VariantTable fillGenotypeTable(VariantTable table, VariantStringData data, GenotypeQueryParams params)
			throws Exception;

	/**
	 * Get list of reference genomes
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Organism> getReferenceGenomes() throws Exception;

	/**
	 * Get contigs for reference
	 * 
	 * @param reference
	 * @return
	 */
	public List<String> getContigsForReference(String reference);

	/**
	 * Get length of sequence feature in organism
	 * 
	 * @param feature
	 * @param organism
	 * @return
	 */
	Integer getFeatureLength(String feature, String organism);

	/**
	 * Get feature in organism
	 * 
	 * @param feature
	 * @param organism
	 * @return
	 */
	Scaffold getFeature(String feature, String organism);

	/**
	 * Get gene loci for reference
	 * 
	 * @param organism
	 * @return
	 */
	public Collection getLociForReference(String organism);

	/**
	 * Query genotype for two varieties
	 * 
	 * @param var1
	 * @param var2
	 * @param params
	 * @return
	 * @throws Exception
	 */
	VariantStringData compare2Varieties(BigDecimal var1, BigDecimal var2, GenotypeQueryParams params) throws Exception;

	/**
	 * Download FASTA generated from aligned SNP/Indel data, and reference sequence
	 * 
	 * @param param
	 * @param loc
	 * @param locusfilename
	 * @throws Exception
	 */
	void downloadFastaMSAPerLocus(GenotypeQueryParams param, Locus loc, String locusfilename) throws Exception;

	public List<SnpsEffect> getSnpEffects(List poslist);

	long countGenotype(GenotypeQueryParams params) throws Exception;

	// Object[] constructMDS(VariantStringData dataset, PhylotreeQueryParams
	// params);
	public Object[] constructMDS(Map mapVarid2Row, VariantStringData dataset, PhylotreeQueryParams params);

	public Object[] constructMDS(Map<BigDecimal, Integer> mapVarid2Row, List<VarietyDistance> listdist, String scale);

	// boolean displayHapotypeImage(String pedfilenameonly, String imageformat);
	boolean displayHapotypeImage(String pedfilenameonly, String imageformat, boolean genome_coords,
			GenotypeQueryParams params, double localWeight, double resFactor, int kgroups, int kheight,
			String autogroup, String imagesize);

	// boolean displayHapotypeImage(String pedfilenameonly, String imageformat,
	// boolean genome_coords, GenotypeQueryParams params, double localWeight, double
	// resFactor, int kgroups, int kheight, String autogroup);
	boolean displayHapotypeTreeImage(String haplofilename, String string, double kheight, Integer imagesize);

	public double getMaxLog2treeheight(String haplofilename);

	public double[] getMinMaxLog2treeheight(String string);

	public List<GenotypeRunPlatform> getGenotyperuns(String type);

	public Set getVarietiesForSubpopulation(String label, Set dataset);

	public List getVaraccessions(Set dataset);

	public List getVarnames(Set dataset);

	public boolean hasNonsyn(Integer organismId, Set vs);

	public List<String> getVarietysets();
	
	public List getVarietysets(String reference);
	
	public java.util.List<String> getGenenames(String reference);


	// public List getVariantsets(Set varietyset);
	public List getVariantsets(Set varietyset, String type);

	public Collection getGenotyperuns(Set setds, Set setvs, String varianttype);
	
	public List getGenotyperuns();

	public List getVariantsets(String dsi, String type);

	boolean sendGenotypeToGalaxy(String pedfilenameonly, GenotypeQueryParams params);

}
