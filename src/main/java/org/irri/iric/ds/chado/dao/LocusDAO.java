package org.irri.iric.ds.chado.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.irri.iric.ds.chado.domain.Locus;
import org.irri.iric.ds.utils.TextSearchOptions;

public interface LocusDAO {

	/**
	 * Get locis by name
	 * 
	 * @param name
	 * @return
	 */
	public List<Locus> getLocusByName(String name);

	/**
	 * Get loci with collection of (capitalized) names
	 * 
	 * @param name
	 * @return
	 */
	public Collection getLocusByName(Collection<String> name);

	/**
	 * Get loci within region
	 * 
	 * @param contig
	 * @param start
	 * @param end
	 * @param organism
	 * @return
	 */
	// public List<Locus> getLocusByRegion(String contig, Long start, Long end,
	// String organism);
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel);

	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel,
			String featuretype);

	/**
	 * Get loci from list of positions
	 * 
	 * @param contig
	 * @param posset
	 * @param organism
	 * @return
	 */
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus);

	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel);

	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel, String featuretype);

	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel,
			Set featuretype);

	/**
	 * Get loci with description
	 * 
	 * @param desc
	 * @param organism
	 * @return
	 */
	public List<Locus> getLocusByDescription(TextSearchOptions description, String organism);

	public List<Locus> getLocusByDescription(TextSearchOptions description, String organism, String genemodel);

	/**
	 * Get loci with name/synonym
	 * 
	 * @param desc
	 * @param organism
	 * @return
	 */
	public List<Locus> getLocusBySynonyms(TextSearchOptions synonym, String organism);

	public List<Locus> getLocusBySynonyms(TextSearchOptions synonym, String organism, String genemodel);

	List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus,
			String genemodel, Set featuretype);

}
