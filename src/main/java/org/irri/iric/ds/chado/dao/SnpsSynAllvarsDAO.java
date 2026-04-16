package org.irri.iric.ds.chado.dao;

import java.util.Collection;
import java.util.Set;

import org.irri.iric.ds.chado.domain.SnpsSynAllele;
import org.springframework.dao.DataAccessException;

/**
 * Synonymous snps
 * 
 * @author LMansueto
 *
 */
public interface SnpsSynAllvarsDAO {

	/*
	 * public Set<SnpsSynAllele> findSnpSynAlleleByChrPosBetween(String chr, Integer
	 * start, Integer end, BigDecimal dataset) throws DataAccessException; public
	 * Set<SnpsSynAllele> findSnpSynAlleleByChrPosIn(String chr, Collection listpos,
	 * BigDecimal dataset) throws DataAccessException;
	 */
	// public Set findSnpSynAlleleByFeatureidIn(Set<BigDecimal> setSnpFeatureId,
	// BigDecimal snptype) throws DataAccessException;

	public Set<SnpsSynAllele> findSnpSynAlleleByChrPosBetween(String chr, Integer start, Integer end, Set variantset)
			throws DataAccessException;

	public Set<SnpsSynAllele> findSnpSynAlleleByChrPosIn(String chr, Collection listpos, Set variantset)
			throws DataAccessException;

}
