package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.ds.chado.domain.Snps2VarsCountmismatch;
import org.irri.iric.ds.chado.domain.VarietyDistance;

/**
 * Phylogenetic tree construction service
 * 
 * @author LMansueto
 *
 */
public interface PhylotreeService {

	public static String PHYLOTREE_METHOD_TOPN = "topn";
	public static String PHYLOTREE_METHOD_MINDIST = "mindist";

	/**
	 * Construct phylogenetic tree
	 * 
	 * @param params
	 * @param requestid
	 * @return
	 */
	Object[] constructPhylotree(PhylotreeQueryParams params, String requestid);

	// Object[] constructPhylotree(String scale, String chr, int start, int end,
	// String requestid);

	/**
	 * Get map of variety Id to DFS (depth first search) order in the tree
	 * 
	 * @param tmpfile
	 * @return
	 */
	// Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile);
	// Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, String
	// newick);
	Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, String newick, Set dataset);

	Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile, Set dataset);

	Object[] constructPhylotree(String scale, String chr, int start, int end, String requestid, Set dataset);

	Object[] constructPhylotree(VariantStringData data, PhylotreeQueryParams params);

	// Object[] constructMDS(VariantStringData dataset, PhylotreeQueryParams
	// params);

	List<Snps2VarsCountmismatch> calculateDistancePair(VariantStringData data, PhylotreeQueryParams params);

	Object[] constructPhylotree(List<Snps2VarsCountmismatch> listmis, int n, PhylotreeQueryParams params);

	double[][] constructMDS(Map<BigDecimal, Integer> mapVarid2Row, List<VarietyDistance> listdist, String scale);

	double[][] constructMDS(Map<BigDecimal, Integer> mapVarid2Row, VariantStringData data, PhylotreeQueryParams params);

}
