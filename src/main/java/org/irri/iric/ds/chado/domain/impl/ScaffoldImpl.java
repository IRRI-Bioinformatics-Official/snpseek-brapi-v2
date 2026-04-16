package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;

import org.irri.iric.ds.chado.domain.Scaffold;

public class ScaffoldImpl implements Scaffold {

	private BigDecimal featureId;
	private long length;
	private String name;
	private String uniquename;

	
	public ScaffoldImpl(BigDecimal featureId, long length, String name, String uniquename) {
		super();
		this.featureId = featureId;
		this.length = length;
		this.name = name;
		this.uniquename = uniquename;
	}

	@Override
	public BigDecimal getFeatureId() {
		
		return featureId;
	}

	@Override
	public long getLength() {
		
		return length;
	}

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public String getUniquename() {
		return uniquename;
	}

}
