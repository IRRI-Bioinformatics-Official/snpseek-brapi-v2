package org.irri.iric.portal.variety;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.ds.chado.domain.Phenotype;
import org.irri.iric.ds.chado.domain.StockSample;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.portal.AppContext;

/**
 * API functions used mostly by Variety Query
 * 
 * @author LMansueto
 *
 */

public interface VarietyFacade {

	public static String COMPARATOR_EQUALS = "=";
	public static String COMPARATOR_LESSTHAN = "<=";
	public static String COMPARATOR_GREATERTHAN = ">=";
	public static String COMPARATOR_LIKE = "like";

	// variety object query methods

	/*
	 * public static String DATASET_3K = "3k"; public static String DATASET_HDRA =
	 * "hdra";
	 */

	public static String DATASET_DEFAULT = AppContext.getDefaultDataset(); // "reference set"; // "3k";
	public static String DATASET_SNP_ALL = "all";

	public static String DATASET_SNPINDELV1 = "SNP v1";
	public static String DATASET_SNPINDELV2 = "SNP v2";
	public static String DATASET_SNPINDELV2_IUPAC = AppContext.getDefaultDataset(); // "3k";
	public static String DATASET_SNP_HDRA = "hdra";
	public static String DATASET_SNP_WISSUWA = "wissuwa";
	public static String DATASET_SNP_GOPAL92 = "gq92";
	public static String DATASET_SNP_3KGOPAL92 = "3k+gq92";
	public static String DATASET_SNP_3KHDRA = "3k+hdra";
	public static String DATASET_SNP_3KWISSUWA = "3k+wissuwa";

	public static String DATASET_SNPPOS_INTERSECT = "intersect";
	public static String DATASET_SNPPOS_UNION = "union";

	// public static String DATASET_SNPINDELV2_IUPAC="SNP v2 IUPAC";
	// public static String DATASET_SNP_HDRA="SNP HDRA";

	public List getDatasets();
	
	public List getDatasets(String type, String reference);

	/**
	 * Get list of variety names
	 * 
	 * @return
	 */
	// public java.util.List getVarietyNames();

	public List<String> getVarietyAccessions(String dataset);

	/**
	 * Get Variety object from name
	 * 
	 * @param name
	 * @return
	 */
	// public Variety getGermplasmByName(String name);
	// public List<Variety> getGermplasmByName(String name, String dataset);
	public Variety getGermplasmById(BigDecimal id, String dataset);

	// public List<Variety> getGermplasmByNames(Collection names, String dataset);
	public List<Variety> getGermplasmByNames(Collection names, Set dataset);

	/**
	 * 
	 * Get Variety object from name like
	 * 
	 * @param name
	 * @return
	 */
	// public Variety getGermplasmByNameLike(String name);
	// public List getGermplasmByNameLike(String name, String dataset);
	public List<Variety> getGermplasmByNamesLike(Collection names, String dataset);

	/**
	 * Get variety object from IRIS ID
	 * 
	 * @param name
	 * @return
	 */
	public Variety getGermplasmByIrisId(String name, String dataset);

	// public List<Variety> getGermplasmByIrisIds(Collection names, String dataset);
	public List<Variety> getGermplasmByIrisIds(Collection names, Set dataset);

	/**
	 * 
	 * @param value
	 * @param dataset
	 * @return
	 */
	public Variety getGermplasmByAccession(String value, String dataset);

	/**
	 * Get varieties from country
	 * 
	 * @param country
	 * @return
	 */
	// public java.util.Set getGermplasmByCountry(String country);
	public java.util.Set getGermplasmByCountry(String country, Set dataset);

	/**
	 * Get all varieties
	 * 
	 * @return
	 */
	// public java.util.Set getGermplasm();
	public java.util.Set getGermplasm(Set set);

	/**
	 * Get varieties in subpopulation
	 * 
	 * @param subpopulation
	 * @return
	 */
	// public java.util.Set getGermplasmBySubpopulation(String subpopulation);
	public java.util.Set getGermplasmBySubpopulation(String subpopulation, String dataset);

	/**
	 * Get variety matching properties in example
	 * 
	 * @param germplasm
	 * @return
	 */
	// public Set getGermplasmByExample(Variety germplasm);

	/**
	 * map variety name to variety object
	 * 
	 * @return
	 */
	public java.util.Map<String, Variety> getMapVarname2Variety(String dataset);

	/**
	 * map variety ID to variety object
	 * 
	 * @return
	 */
	public Map<BigDecimal, Variety> getMapId2Variety(String dataset);

	public Map getIrisId2Variety();

	/**
	 * Get varieties with passport = value
	 * 
	 * @param typeId
	 * @param value
	 * @return
	 */
	public List getVarietyByPassport(String definition, Set set, String value);

	/**
	 * Get varieties that satistfy the phenotype values
	 * 
	 * @param definition
	 * @param comparator
	 * @param value
	 * @param phenotype_type
	 *            values can be
	 *            (org.irri.iric.portal.dao.ListItemsDAO.PHENOTYPETYPE_NONE,
	 *            PHENOTYPETYPE_QUAL, PHENOTYPETYPE_QUAN)
	 * @return
	 */
	public List getVarietyByPhenotype(String definition, Set set, String comparator, String value, int phenotype_type);

	public List getVarietyByPhenotype(String phenId, String dataset);

	// User interface listbox items query methods

	/**
	 * Get list of IRIS IDs
	 * 
	 * @return
	 */
	// public java.util.List getIrisIds();
	public java.util.List getIrisIds(String datatset);

	/**
	 * Get list of countries
	 * 
	 * @return
	 */
	// public java.util.List getCountries();
	public java.util.List getCountries(String dataset);

	/**
	 * Getg list of subpopulations
	 * 
	 * @return
	 */
	// public java.util.List getSubpopulations();
	public java.util.List getSubpopulations(String dataset);

	/**
	 * Get list of accessions
	 * 
	 * @return
	 */
	// public java.util.List getAccessions();
	public java.util.List getAccessions(String dataset);

	/**
	 * Construct phylogenetic tree from comma separated ID list of variety IDs
	 * 
	 * @param varnames
	 * @param scale
	 * @return
	 */

	/**
	 * Controlled terms/definitions for passport, phenotypes
	 * 
	 * @return
	 */
	public Map<String, BigDecimal> getPassportDefinitions(String dataset);

	public Map<String, BigDecimal> getPhenotypeDefinitions(String dataset);

	// Phenotypes query methods

	/**
	 * Get list of phenotypes/values for variety
	 * 
	 * @param var
	 * @return
	 */

	public List getPhenotypesByGermplasm(Variety var, Set dataset);

	/**
	 * Get list of phenotypes/values for variety
	 * 
	 * @param var
	 * @return
	 */

	// public Phenotype getPhenotypesByGermplasm(Variety var, String phenId);

	/**
	 * Get phenotypes/values for all varieties
	 * 
	 * @param var
	 * @return
	 */

	public List getPhenotypesByGermplasm(String phenId, String dataset);

	/**
	 * Get list of phenotypes/values for variety with name
	 * 
	 * @param name
	 * @return
	 */
	public List getPhenotypesByGermplasmName(String name);

	/**
	 * 
	 * @param definition
	 * @return Object[0] is a Set of unique values Object[1] is an Integer with
	 *         values (org.irri.iric.portal.dao.ListItemsDAO.PHENOTYPETYPE_NONE,
	 *         PHENOTYPETYPE_QUAL, PHENOTYPETYPE_QUAN)
	 */
	// public Object[] getPhenotypeUniqueValues(String definition, String dataset);

	/**
	 * Get phenotype values for all germplasms
	 * 
	 * @param phenotype
	 * @return
	 */
	public Map<BigDecimal, Object> getPhenotypeValues(String phenotype, String dataset);

	// Passport query methods

	/**
	 * Get list of passport/values for vartiety ID
	 * 
	 * @param id
	 * @return
	 */
	public Set getPassportByVarietyid(BigDecimal id);

	/**
	 * Get unique values from all varieties for passport,phenotype
	 * 
	 * @param definition
	 * @return
	 */
	public Set getPassportUniqueValues(String definition, Set dataset);

	// phylogenetic tree construction methods
	// public String constructPhylotree(String ids, String scale, String requestid);

	// public String constructPhylotree(Set<BigDecimal> germplasms, String scale,
	// String requestid);

	public String[] constructPhylotree(String varids, String scale, String topN, String requestid);

	public String[] constructPhylotree(String varids, String scale, String topN, String requestid, String dataset);

	public String[] constructPhylotree(Set<BigDecimal> germplasms, String scale, String requestid, String dataset);

	// MDS Plot construction methods

	/**
	 * 
	 * @param germplasms
	 *            list of variety Ids (include even if showAll=true because it's
	 *            used in ordering)
	 * @param scale
	 * @param showAll
	 *            all varieties are used
	 * @return
	 */
	// public double[][] constructMDSPlot(List<BigDecimal> germplasms, String scale,
	// boolean showAll);
	// public double[][] constructMDSPlot(String ids, String scale, boolean
	// showAll);
	public double[][] constructMDSPlot(List<BigDecimal> germplasms, String scale, boolean isAll, String dataset);

	public double[][] constructMDSPlot(String varids, String scale, boolean isAll, String dataset);

	/**
	 * Get map of variety to property
	 * 
	 * @param propname
	 * @return
	 */
	public Map getVarietyExternalURL(String propname);

	/**
	 * Check varities in database
	 * 
	 * @param varstrs
	 * @return
	 */
	// public Set checkVariety(String varstrs);

	/**
	 * TO/CO terms to ID
	 * 
	 * @param dataset
	 * @return
	 */
	public Map<String, BigDecimal> getPtocoDefinitions(String dataset);

	/**
	 * Get GRIMS phenotype from CV (plant_trait_ontology/rice_trait) term
	 * 
	 * @param cv
	 * @param term
	 * @param dataset
	 * @return
	 */
	public List getPhenotypeByPtoco(String cv, String term, Set dataset);

	public Collection<? extends Variety> getGermplasmByExample(Variety example, Set set);

	Set checkVariety(String varstrs, String variety);

	// public Set getGermplasmsByName(String varname, String dataset);
	// public Variety getGermplasmsByAccession(String accession, String dataset);
	// public Set getGermplasmsByAccessions(Collection accession, String dataset);
	public Set getGermplasmsByNameAccession(String varname, String accession, String dataset);

	public Phenotype getPhenotypesByGermplasm(Variety var, String dataset, String phenId);

	public Set getQuantTraits(String dataset);

	public List getVarietyNames(Set dataset);

	public List getIRGCVarietyNames();

	public Set getQuantTraits(Set dataset);

	public Map getPhenotypeValues(String sPhenotype, Set dataset);

	// public Set<Variety> getGermplasmByAccession(String trim, Set dataset);
	public Variety getGermplasmByAccession(String trim, Set dataset);

	public List<Variety> getGermplasmByName(String var1, Set dataset);

	public Map<String, Variety> getMapVarname2Variety(Set dataset);

	public Collection getGermplasmsByName(String varname, Set dataset);

	public Variety getGermplasmsByAccession(String varname, Set dataset);
	
	public List<Variety> getGermplasmsByAccession(Collection varname, Set dataset);

	public Map getMapId2Variety(Set dataset);

	public Map getMapId2Sample(Set dataset);

	public Collection getSubpopulations(Set dataset);

	public List getAccessions(Set dataset);

	public List getIRGCAccessions();

	public Map getPhenotypeDefinitions(Set dataset);

	public Object[] getPhenotypeUniqueValues(String phenname, Set dataset);

	public Map getUIforDataset(Set dataset);

	public Set getGermplasmBySubpopulation(String subpopulation, Set dataset);

	public Map getPassportDefinitions(Set dataset);

	public Map getPtocoDefinitions(Set dataset);

	public List getVarietyAccessions(Set dataset);

	public List getIrisIds(Set dataset);

	public List getCountries(Set dataset);

	public Set checkVariety(String varstr, Set dataset);

	public Variety getGermplasmByIrisId(String name, Set dataset);

	public Collection getGermplasmsByNameAccession(String varname, String accession, Set dataset);

	public double[][] constructMDSPlot(List<BigDecimal> ids, String scale, boolean isAll, Set dataset);

	public String[] constructPhylotree(String varids, String scale, Object topN, Object requestid, Set dataset);

	List getGermplasmByNameLike(String name, Set dataset);

	public Map<String, StockSample> getMapAssay2Sample(Set dataset);

	public Set getGermplasm(String dataset);

	List getVarietyByPhenotype(String phenId, Set dataset);

	// List getVarietyByCoTerm(String coTerm, Set dataset);
	
	public List getGenotyperuns();

	public Map<String, BigDecimal> getTraits(Set<String> dataset, boolean legacy);

	public BigDecimal getPhenotypeId(String coTerm, String s);

	public Map<BigDecimal, String> getAllTraits(Set<String> stringValues, boolean b);

	public Collection getPassportByAccession(String value);

	public List<Variety> getGermplasmByNamesWithSpace(Collection names, Set dataset);

	// public List getVarietyByPassport(String sPassId);

}