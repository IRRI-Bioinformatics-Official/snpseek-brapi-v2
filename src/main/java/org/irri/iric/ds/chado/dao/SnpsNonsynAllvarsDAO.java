package org.irri.iric.ds.chado.dao;

import java.util.Collection;
import java.util.Set;

import org.irri.iric.ds.chado.domain.SnpsNonsynAllele;
import org.springframework.dao.DataAccessException;

/**
 * Get nonsynonymous SNPs from region
 * @author LMansueto
 *
 */
public interface SnpsNonsynAllvarsDAO {
	
/*	
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosBetween(String chr, Integer start, Integer end, BigDecimal dataset) throws DataAccessException;
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosIn(String chr, Collection listpos, BigDecimal dataset)  throws DataAccessException;
	*/
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosBetween(Integer organismId,String chr, Integer start, Integer end, Set  variantset) throws DataAccessException;
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosIn(Integer organismId, String chr, Collection listpos,  Set  variantset)  throws DataAccessException;

	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByFeatureidIn(Integer organismId, Collection featureid)  throws DataAccessException;
	public Boolean hasData(String ds, Integer organismId);

	
	
}
