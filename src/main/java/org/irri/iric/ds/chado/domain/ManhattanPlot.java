package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

public interface ManhattanPlot extends Position, PositionLogPvalue {

	BigDecimal getMarkerId();
	// BigDecimal getMinusLogP();

}
