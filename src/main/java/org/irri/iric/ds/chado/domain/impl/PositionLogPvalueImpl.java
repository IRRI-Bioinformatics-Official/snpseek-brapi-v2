package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;

import org.irri.iric.ds.chado.domain.PositionLogPvalue;
import org.irri.iric.ds.utils.DbUtils;


public class PositionLogPvalueImpl extends PositionImpl implements PositionLogPvalue {

	private Double logpvalue;

	public PositionLogPvalueImpl(String contig, BigDecimal position, Integer chr, Double minuslogp) {
		super(contig, position, null, chr);
		logpvalue = minuslogp;
	}

	@Override
	public Double getMinusLogPvalue() {

		return logpvalue;
	}

	@Override
	public void setMinusLogPvalue(Double pval) {

		logpvalue = pval;
	}

	@Override
	public String toString() {

		if (super.getRefnuc() == null)
			if (logpvalue == null)
				return "(" + contig + "," + position + ")";
			else
				return "(" + contig + "," + position + "," + DbUtils.decf.format(logpvalue) + ")";
		else {
			if (logpvalue == null)
				return "(" + contig + "," + position + "," + super.getRefnuc() + ")";
			else
				return "(" + contig + "," + position + "," + super.getRefnuc() + "," + DbUtils.decf.format(logpvalue)
						+ ")";
		}

	}

}
