package org.irri.iric.ds.chado.dao;

import java.util.Collection;
import java.util.List;

import org.irri.iric.ds.chado.domain.Locus;



public interface FeatureInteractionDAO {

	public static int INTERACTION_RICENETV2 = 1;
	public static int INTERACTION_RICENETV1 = 2;
	public static int INTERACTION_PRINEXPT = 3;
	public static int INTERACTION_PRINPRED = 4;

	List<Locus> getInteractingFeatures(Collection<Locus> colLocus, int type, Integer maxResults);

}
