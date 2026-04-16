package org.irri.iric.ds.chado.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public interface StockByPhenotype extends VarietyPlus, Serializable {

	public BigDecimal getStockId();

}
