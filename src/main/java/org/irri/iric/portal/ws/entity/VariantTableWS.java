package org.irri.iric.portal.ws.entity;

import java.util.List;

import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTableArray;

public class VariantTableWS implements org.irri.iric.portal.genotype.VariantTableArray {

	// private String message;
	// private Long position[];
	// private String reference[];
	// private Object varalleles[][];
	// private Long varid[];
	// private String varname[];
	// private Double varmismatch[];

	private VariantTableArray table;

	public VariantTableWS(VariantTableArray table) {
		super();
		this.table = table;
	}

	@Override
	public String getMessage() {
		return table.getMessage();
	}

	@Override
	public void setMessage(String message) {
		table.setMessage(message);
	}

	@Override
	public Position[] getPosition() {
		return table.getPosition();
	}

	@Override
	public String[] getReference() {
		return table.getReference();
	}

	@Override
	public Object[][] getVaralleles() {
		return table.getVaralleles();
	}

	@Override
	public Long[] getVarid() {
		return table.getVarid();
	}

	@Override
	public String[] getVarname() {
		return table.getVarname();
	}

	@Override
	public Double[] getVarmismatch() {
		return table.getVarmismatch();
	}

	@Override
	public void setVariantStringData(VariantStringData data, GenotypeQueryParams params) throws Exception {
	
	}

	@Override
	public void setVariantStringData(VariantStringData data, GenotypeQueryParams params, List listCDS)
			throws Exception {
	
	}

	@Override
	public VariantStringData getVariantStringData() {
		return null;
	}

	@Override
	public String[] getContigs() {
		return table.getContigs();
	}

	@Override
	public String[] getSNPGenomicAnnotation(GenotypeQueryParams params) {
		return null;
	}

}
