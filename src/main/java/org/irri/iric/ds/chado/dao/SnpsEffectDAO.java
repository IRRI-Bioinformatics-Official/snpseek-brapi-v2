package org.irri.iric.ds.chado.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsEffect;
import org.springframework.dao.DataAccessException;

public interface SnpsEffectDAO {

	/*
	 * public Set<SnpsEffect> getSNPsIn(String chr, Collection listpos) throws
	 * DataAccessException; public Set<SnpsEffect> getSNPsBetween(String chr,
	 * Integer start, Integer end) throws DataAccessException;
	 */
	public Set<SnpsEffect> getSNPsIn(String chr, Collection listpos, Set variantset) throws DataAccessException;

	public Set<SnpsEffect> getSNPsBetween(String chr, Integer start, Integer end, Set variantset)
			throws DataAccessException;

	public Set<SnpsEffect> getSNPsByFeatureidIn(Collection featureid) throws DataAccessException;

	public Object getSNPsIn(String contig, Collection colPos);
	
	public Collection getAnnotChrPos(String chr, List<SnpsAllvarsPos> listPos);

}
