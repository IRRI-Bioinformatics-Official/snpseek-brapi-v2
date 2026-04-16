package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

/**
 * Container entity for SNP position
 * 
 * @author lmansueto
 *
 */
public interface SnpsAllvarsPos extends Position, Snp {

	public void setRefnuc(String refnuc);

	public void setAltnuc(String altnuc);

	public String getAltnuc();

	/**
	 * column index of position in HDF5 matrix
	 * 
	 * @return
	 */
	public BigDecimal getAlleleIndex();

}
