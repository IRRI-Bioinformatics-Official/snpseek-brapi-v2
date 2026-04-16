package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;

public interface GWASRun {

	BigDecimal getGwasRunId();

	String getTrait();

	String getSubpopulation();

	String getCoterm();

	BigDecimal getCotermId();

	String getCodefinition();

}
