package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Synonymous SNPs
 * 
 * @author LMansueto
 *
 */
public interface SnpsSynAllele extends Snp {

	/**
	 * synonymous allele
	 * 
	 * @return
	 */
	char getAllele();

}
