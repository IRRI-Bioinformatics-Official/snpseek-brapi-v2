package org.irri.iric.ds.chado.domain;

import java.util.Calendar;

public interface GenotypeRunPlatform {

	Integer getGenotypeRunId();

	Integer getPlatformId();

	String getDataset();

	String getVariantset();

	String getVariantType();

	Calendar getDatePerformed();

	String getLocation();

	// int getVaridOffset();
	String getMethod();

	boolean useRDBMS();

	boolean useHDF5();
}
