package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.irri.iric.ds.chado.domain.IndelsAllvars;
import org.irri.iric.ds.chado.domain.IndelsStringAllvars;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;

public class IndelsStringAllvarsImpl implements IndelsStringAllvars {

	private BigDecimal mismatch;
	private Map<Position, IndelsAllvars> mapPos2IndelCalls;
	private BigDecimal var;
	// private String varnuc;
	// private Map mapPosidx2Allele2;
	private Long chr;

	private String contig;

	private SnpsStringAllvars snpstring;
	private String dataset;

	public IndelsStringAllvarsImpl(String dataset, BigDecimal var, Map<Position, IndelsAllvars> mapPos2IndelCalls,
			BigDecimal mismatch, String contig) {
		super();
		this.dataset = dataset;
		this.var = var;
		this.mismatch = mismatch;
		this.mapPos2IndelCalls = mapPos2IndelCalls;

		try {
			this.chr = Long.valueOf(contig);
		} catch (Exception ex) {
			this.contig = contig;
		}
	}

	@Override
	public BigDecimal getAllele1(Position pos) {
		
		IndelsAllvars indel = mapPos2IndelCalls.get(pos);

		/*
		 * if(pos.getPosition().equals(BigDecimal.valueOf(69957))) { if(indel==null)
		 * AppContext.debug("getAllele1(): indel=null ; mapPos2IndelCalls=" +
		 * mapPos2IndelCalls); else if (indel.getAllele1()==null) {
		 * AppContext.debug("getAllele1(): indel.getAllele1()=null ; indel=" + indel); }
		 * }
		 */

		if (indel != null)
			return indel.getAllele1();
		return null;
	}

	@Override
	public BigDecimal getAllele2(Position pos) {
		
		IndelsAllvars indel = mapPos2IndelCalls.get(pos);
		if (indel != null)
			return indel.getAllele2();
		return null;
	}

	@Override
	public Long getChr() {
		

		return this.chr;
	}
	//
	// @Override
	// public BigDecimal getPosition() {
	// 
	// if(snpstring==null) return null;
	// return snpstring.getPosition();
	// }
	//
	// @Override
	// public String getRefcall() {
	// 
	// if(snpstring==null) return null;
	// return snpstring.getRefcall();
	// }

	@Override
	public String getVarnuc() {
		
		if (snpstring == null)
			return null;
		return snpstring.getVarnuc();
	}

	@Override
	public BigDecimal getVar() {
		
		return this.var;
	}

	@Override
	public BigDecimal getMismatch() {
		
		if (snpstring == null)
			return mismatch;
		return snpstring.getMismatch().add(mismatch);
	}

	/*
	 * @Override public Map<Integer, Character> getMapPosIdx2Allele2() { // TODO
	 * Auto-generated method stub if(snpstring==null) return null; return
	 * snpstring.getMapPosIdx2Allele2(); }
	 * 
	 * 
	 * @Override public Set getNonsynIdxset() { 
	 * if(snpstring==null) return null; return snpstring.getNonsynIdxset(); }
	 */

	@Override
	public Map<Position, IndelsAllvars> getMapPos2Indels() {
		

		return mapPos2IndelCalls;
	}

	// @Override
	// public void setVarnuc(String varnuc) {
	// 
	// this.varnuc = varnuc;
	//
	// }
	//
	//
	// @Override
	// public void setMismatch(BigDecimal mismatch) {
	// 
	// this.mismatch = mismatch;
	// }
	//
	//
	// @Override
	// public void setMapPosIdx2Allele2(Map mapPosidx2Allele2) {
	// 
	// this.mapPosidx2Allele2 = mapPosidx2Allele2;
	// }

	@Override
	public void delegateSnpString(SnpsStringAllvars snpstring) {
		
		this.snpstring = snpstring;
	}

	@Override
	public Set getDonorPosset() {
		
		if (snpstring == null)
			return null;
		return snpstring.getDonorPosset();
	}

	@Override
	public Set getAcceptorPosset() {
		
		if (snpstring == null)
			return null;
		return snpstring.getAcceptorPosset();
	}

	@Override
	public Map<Position, Character> getMapPos2Allele2() {
		
		if (snpstring == null)
			return null;
		return snpstring.getMapPos2Allele2();
	}

	@Override
	public Set getNonsynPosset() {
		
		if (snpstring == null)
			return null;
		return snpstring.getNonsynPosset();
	}

	@Override
	public String getContig() {
		
		if (chr == null)
			return contig;
		if (getChr() > 9)
			return "chr" + getChr();
		else
			return "chr0" + getChr();
	}

	@Override
	public String toString() {
		
		return var.toString() + ", " + getContig() + " " + getChr() + " " + mapPos2IndelCalls;
	}

	@Override
	public SnpsStringAllvars copy() {
		
		return new IndelsStringAllvarsImpl(dataset, var, new HashMap(mapPos2IndelCalls), mismatch, contig);
	}

	@Override
	public void setVarnuc(String varnuc) {
	
	}

	@Override
	public void setMismatch(BigDecimal mismatch) {
	
	}

	@Override
	public Set getSynPosset() {
		
		if (snpstring == null)
			return null;
		return snpstring.getSynPosset();
	}

	@Override
	public String getDataset() {
		return dataset;
	};

}
