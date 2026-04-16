package org.irri.iric.ds.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

public interface IricstockPassportDAO {

	/**
	 * Get passport data for variety
	 * 
	 * @param id
	 * @return
	 */
	// public Set findIricstockPassportByIricStockId(BigDecimal id);

	public Set getPassportByStockId(BigDecimal id)  throws Exception;
	public Set getPassportByAccession(String acc) throws Exception;
}
