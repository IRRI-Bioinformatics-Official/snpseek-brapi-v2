package org.irri.iric.ds.chado.domain;

public interface LocusPromoter extends MergedLoci {

	String getPromoterName();

	String getPromoterType();

	String getPromoterDB();

	int getPromoterStart();

	int getPromoterEnd();

	int getDistanceFromTSS();

}
