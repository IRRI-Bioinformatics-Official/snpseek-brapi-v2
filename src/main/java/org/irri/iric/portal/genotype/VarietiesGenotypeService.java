package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;

/**
 * Get genotypes for all or list of varieties
 * 
 * @author LMansueto
 *
 */
public interface VarietiesGenotypeService {

	/**
	 * Check if SNPs are present in positions in chromosome, for given data type
	 * 
	 * @param chr
	 * @param posset
	 * @param type
	 * @return
	 */
	// public List checkSNPsInChromosome(String chr, Collection posset, BigDecimal
	// type);
	public List checkSNPsInChromosome(Integer organismId,String chr, Collection posset, Set variantset);

	/**
	 * Query genotypes based on params
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public VariantStringData queryVariantStringData(GenotypeQueryParams params) throws Exception;

	public Future queryVariantStringDataAsync(GenotypeQueryParams params) throws Exception;

	/**
	 * Query genotypes for two varieties based on params
	 * 
	 * @param var1
	 * @param var2
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public VariantStringData compare2VariantStrings(BigDecimal var1, BigDecimal var2, GenotypeQueryParams params)
			throws Exception;

	/**
	 * Fill the variant table with the data, format depends on the type of table
	 * 
	 * @param table
	 * @param params
	 * @return
	 * @throws Exception
	 */

	public VariantTable fillVariantTable(VariantTable table, VariantStringData data, GenotypeQueryParams params)
			throws Exception;

	/**
	 * 
	 * @param poslist
	 * @return
	 */
	public List<SnpsEffect> getSnpEffects(List poslist);

	public List<SnpsAllvarsPos> getSNPPoslist(GenotypeQueryParams params);

	public long countSNPPoslist(GenotypeQueryParams params);

	public long countVariantStringData(GenotypeQueryParams params);

}
