package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.List;

import org.irri.iric.ds.chado.domain.Scaffold;



public interface ScaffoldDAO {

	/**
	 * Get scaffolds/contigs for organism name
	 * 
	 * @param organism
	 * @return
	 */
	public List<Scaffold> getScaffolds(String organism);

	/**
	 * Get scaffolds/contigs for organism ID
	 * 
	 * @param organism
	 * @return
	 */

	public List<Scaffold> getScaffolds(BigDecimal organism);

	/**
	 * Get length of scaffolds/contigs for organism name
	 * 
	 * @param organism
	 * @return
	 */
	public Long getScaffoldLength(String scaffold, String organism);

	/**
	 * Get length of scaffolds/contigs for organism ID
	 * 
	 * @param organism
	 * @return
	 */
	public Long getScaffoldLength(String scaffold, BigDecimal organism);

	/**
	 * Get scaffold for scaffoldname, organism Id
	 * 
	 * @param scaffold
	 * @param organism
	 * @return
	 */
	public Scaffold getScaffold(String scaffold, BigDecimal organism);

	/**
	 * Get scaffold for scafoldname, organism name
	 * 
	 * @param scaffold
	 * @param organism
	 * @return
	 */
	public Scaffold getScaffold(String scaffold, String organism);

	/**
	 * Get scaffold by scaffold id
	 * 
	 * @param scaffoldId
	 * @return
	 */
	public Scaffold getScaffold(BigDecimal scaffoldId);

}
