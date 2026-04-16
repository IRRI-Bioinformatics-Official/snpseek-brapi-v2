package org.irri.iric.ds.chado.dao;

import java.util.Collection;
import java.util.Set;

import org.irri.iric.ds.chado.domain.SnpsSpliceDonor;
import org.springframework.dao.DataAccessException;

/**
 * Get splice-donor site SNPs
 * 
 * @author LMansueto
 *
 */
public interface SnpsSpliceDonorDAO {

	public Set<SnpsSpliceDonor> getSNPsBetween(Integer organismId, String chr, Integer start, Integer end, Set variantset)
			throws DataAccessException;

	public Set<SnpsSpliceDonor> getSNPsIn(Integer organismId, String chr, Collection listpos, Set variantset) throws DataAccessException;

	// public Set<SnpsSpliceDonor> getSNPsBetween(String chr, Integer start, Integer
	// end, BigDecimal dataset) throws DataAccessException;
	// public Set<SnpsSpliceDonor> getSNPsIn(String chr, Collection listpos,
	// BigDecimal dataset) throws DataAccessException;

	// public Set<SnpsSpliceDonor> getSNPsIn(String chr, Collection listpos) throws
	// DataAccessException;
	Set<SnpsSpliceDonor> getSNPsByFeatureidIn(Integer organismId,Collection featureid) throws DataAccessException;

}
