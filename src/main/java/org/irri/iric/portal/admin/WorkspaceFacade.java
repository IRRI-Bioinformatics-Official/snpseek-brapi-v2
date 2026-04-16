package org.irri.iric.portal.admin;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * API for user interactions
 * @author LMansueto
 *
 */
public interface WorkspaceFacade {
	
	
	//public Set getLocusListNames();

	/**
	 * Variety list functions
	 */
	public Set getVarietylistNames() ;
	public Set getVarietyQuantPhenotypelistNames(String dataset) ;
	public Set getVarietyCatPhenotypelistNames(String dataset) ;
	public Set getVarieties(String listname);
	
	//boolean addVarietyList(String name, Set varietylist, boolean hasPhenotype);
	// detects if has Phenotype value, if instaceof VarietyPlus or VarietyPlusPlus
	//public boolean addVarietyList(String name, Set setVarieties, String dataset , int hasPhenotype, List phenname, Map<BigDecimal,Object[]> mapVarid2Phen);
	//public boolean addVarietyList(String name, Set varietylist,  String dataset );
	public boolean addVarietyList(String name, Set setVarieties, Set dataset , int hasPhenotype, List phenname, Map<BigDecimal,Object[]> mapVarid2Phen);
	//public boolean addVarietyList(String name, Set varietylist,  Set dataset );

	public void deleteVarietyList(String listname);
	

	/**
	 * SNP list functions
	 * @return
	 */
	public Set getSnpPositionListNames();
	public Set getSnpPositions(String contig, String name);
	public Set getSnpPositionAlleleListNames();
	public Set getSnpPositionPvalueListNames();
	boolean addSnpPositionList(String contig, String name, Set poslist, boolean hasAllele, boolean hasPvalue);
	public boolean SNPListhasAllele(String listname);
	public boolean SNPListhasPvalue(String listname);
	void deleteSNPList(String chromosome, String listname);


	/**
	 * Locus list functions
	 * @return
	 */
	public Set getLocuslistNames();
	public Set getLoci(String listname);
	boolean addLocusList(String name, Set varietylist);
	void deleteLocusList(String listname);


	
	/**
	 * List in string format
	 * @return
	 */
	public String getMyLists();

	
	/**
	 * Download/Upload list
	 */
	public void downloadLists();
	boolean uploadLists(String mylist) throws Exception;
	String getMyListsCookie();
	void setMyListsCookie(String mylist);
	
	// temp
	public void queryIric();
	public Collection getVarietyQuantPhenotypelistNames();
	public Collection getVarietyCatPhenotypelistNames();
	public Map getVarietylistPhenotypeValues(String phenotype, String dataset);
	public boolean addVarietyList(String trim, Set setVarieties, Set dataset);
	
	

	
	
	
}
