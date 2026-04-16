package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.ds.chado.domain.GenotypeRunPlatform;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;

public interface SnpsStringDAO {

	/**
	 * Get variants for all varieties the return is a map of variety_id to variants
	 * string Map<BigDecimalId, SnpString>
	 * 
	 */

	/**
	 * region in chr
	 * 
	 * @param chr
	 * @param startIdx
	 * @param endIdx
	 * @return
	 */
	Map readSNPString(Integer organismId, String chr, int startIdx, int endIdx); // throws Exception;

	/**
	 * list of positions in chr
	 * 
	 * @param chr
	 * @param posIdxs
	 * @return
	 */
	Map readSNPString(Integer organismId, String chr, int[] posIdxs); // throws Exception;

	Map readSNPString(Integer organismId, Set<BigDecimal> colVarids, String chr, int[] posIdxs); // throws Exception;

	Map readSNPString(Integer organismId, Set<BigDecimal> colVarids, String chr, int startIdx, int endIdx); // throws Exception;

	Map readSNPString(Set colVarids, String chr, int[][] posidxstartend);

	Map readSNPString(String chr, int[][] posidxstartend);

	// Map readSNPString(String chr, int[][] posstartendIdxs, int starvarid, int
	// endvarid);
	Map readSNPString(String chr, int starvarid, int endvarid, int[][] posstartendIdxs);

	Map readSNPString(String chr, int[] posIdxs, int starvarid, int endvarid);

	Map[] readSNPString(List<SnpsAllvarsPos> listpos);

	Map[] readSNPString(GenotypeRunPlatform run, String chr, List<SnpsAllvarsPos> listpos);

	Map[] readSNPString(GenotypeRunPlatform run, Set<BigDecimal> colVarids, String chr, List<SnpsAllvarsPos> listpos);

	Map<Integer, BigDecimal> getIdx2SampleMapping();
	
	Map<BigDecimal, Integer> getSample2IdxMapping();
}
