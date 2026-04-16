package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

public interface SnpsAllvarsMultirefsPos extends SnpsAllvarsPos {

	/**
	 * Organism ID
	 * 
	 * @return
	 */
	BigDecimal getOrganism();

	/**
	 * HDF5 File ID. Defined in SnpsAllvarsMultirefsPosDAO.MAP_TYPE2HDF5FILE
	 * 
	 * @return
	 */
	BigDecimal getFileId();

	/**
	 * Contig name in organism
	 * 
	 * @param organism
	 * @return
	 */
	String getContigName(String organism);

	/**
	 * Position in organism
	 */
	BigDecimal getPosition(String organism);

	/**
	 * Number of aligned regions in organism
	 * 
	 * @param organism
	 * @return
	 */
	Integer getAlignCount(String organism);

	/**
	 * Get allele for reference
	 * 
	 * @param organism
	 * @return
	 */
	String getAllele(String organism);

}
