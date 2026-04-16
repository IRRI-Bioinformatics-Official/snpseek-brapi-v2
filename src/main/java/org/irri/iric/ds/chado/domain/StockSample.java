package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

public interface StockSample extends Variety {

	BigDecimal getSampleVarietySetId();

	BigDecimal getStockSampleId();

	String getAssay();

	Integer getHdf5Index();

}
