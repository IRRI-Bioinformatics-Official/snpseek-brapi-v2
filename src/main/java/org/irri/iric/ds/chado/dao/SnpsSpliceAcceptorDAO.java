package org.irri.iric.ds.chado.dao;

import java.util.Collection;
import java.util.Set;

import org.irri.iric.ds.chado.domain.SnpsSpliceAcceptor;
import org.springframework.dao.DataAccessException;

/**
 * Get splice acceptor site SNPs
 * 
 * @author LMansueto
 *
 */
public interface SnpsSpliceAcceptorDAO {

	/*
	 * public Set<SnpsSpliceAcceptor> getSNPsIn(String chr, Collection listpos,
	 * BigDecimal dataset) throws DataAccessException; public
	 * Set<SnpsSpliceAcceptor> getSNPsBetween(String chr, Integer start, Integer
	 * end, BigDecimal dataset) throws DataAccessException;
	 */
	public Set<SnpsSpliceAcceptor> getSNPsIn(Integer organismId, String chr, Collection listpos, Set variantset) throws DataAccessException;

	public Set<SnpsSpliceAcceptor> getSNPsBetween(Integer organismId, String chr, Integer start, Integer end, Set variantset)
			throws DataAccessException;

	Set<SnpsSpliceAcceptor> getSNPsByFeatureidIn(Integer organismId, Collection featureid) throws DataAccessException;

}
