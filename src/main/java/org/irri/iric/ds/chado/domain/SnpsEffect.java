package org.irri.iric.ds.chado.domain;

import java.util.List;

import org.irri.iric.ds.chado.domain.object.SnpEffectAnn;

public interface SnpsEffect extends Locus {

	String[] getANN();

	String[] getLOF();

	String[] getNMD();

	String getAnnotation();

	Double getScore();

	List<SnpEffectAnn> getANNObj();

	String getSnpset();

}
