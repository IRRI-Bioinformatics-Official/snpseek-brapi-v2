package org.irri.iric.ds.chado.domain.object;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class WebsiteQuery {
	public static String RicenetNetworkNGA = "Ricenet Neighbors";
	public static String RicenetContextHub = "Ricenet Context";
	public static String StringDBPPI = "String-DB";
	public static String CarmoAnnotation = "Carmo Annotation";

	public static String CarmoAnnotation_PARAM_ENRICH = "ENRICH";

	public static String PantherDB = "PantherDB";
	public static String PlantGSEA = "PlantGSEA";
	public static String PlantGSEA_PARAM_CLUSTER = "CLUSTER";

	public static String AgriGO = "AgriGO";
	public static String IC4RExpr_RAP = "IC4R Expression";
	public static String IC4RCoexpr_RAP = "IC4R CoExpression";

	public static String IC4RCoexpr_RAP_PARAM_CUTOFF = "CUTOFF";

	private List site;
	private Collection genes;
	private String panthompath;
	private Map mapParams;

	public WebsiteQuery(List<String> site, Collection genes, String panthompath) {
		super();
		this.site = site;
		this.genes = genes;
		this.panthompath = panthompath;
	}

	public List getSite() {
		return site;
	}

	public Collection getGenes() {
		return genes;
	}

	public String getPanthompath() {
		return panthompath;
	}

	public Map getMapParams() {
		return mapParams;
	}

	public void setMapParams(Map mapParams) {
		this.mapParams = mapParams;
	}

}
