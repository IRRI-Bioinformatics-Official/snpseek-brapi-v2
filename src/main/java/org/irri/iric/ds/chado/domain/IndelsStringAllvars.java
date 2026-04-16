package org.irri.iric.ds.chado.domain;

import java.math.BigDecimal;
import java.util.Map;

public interface IndelsStringAllvars extends SnpsStringAllvars { // SnpsAllvarsRefMismatch, IndelsAllvars {

	public BigDecimal getAllele1(Position pos);

	public BigDecimal getAllele2(Position pos);

	public Map<Position, IndelsAllvars> getMapPos2Indels();

	public void delegateSnpString(SnpsStringAllvars snpstring);

}
