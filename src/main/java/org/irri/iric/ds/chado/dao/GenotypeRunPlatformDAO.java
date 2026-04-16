package org.irri.iric.ds.chado.dao;

import java.util.List;
import java.util.Set;

public interface GenotypeRunPlatformDAO {

	public Set getPlatforms(String type);

	public Set getPlatformRuns(Integer platformId);

	public Set getDatasets(String type);

	public List getRunsByType(String type);

	public Set getDatasets(String type, String reference);

	public Set getDatasets();

	public Set getVariantsets();

	public Set getVariantsets(String dataset);

	public Set getVariantsets(Set dataset, String type);

	public Set getPlatforms(Set setds, Set setvs, String type);

	public Set getVariantsets(String dataset, String type);

}
