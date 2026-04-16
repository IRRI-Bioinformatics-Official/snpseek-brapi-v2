package org.irri.iric.portal.genotype;

import org.irri.iric.ds.chado.domain.Position;

public interface VariantTableArray extends VariantTable {

	public Position[] getPosition();

	public String[] getReference();

	public Object[][] getVaralleles();

	public Long[] getVarid();

	public String[] getVarname();

	public Double[] getVarmismatch();

	// String[] getSNPGenomicAnnotation();

	String[] getSNPGenomicAnnotation(GenotypeQueryParams genotypeQueryParams);

}
