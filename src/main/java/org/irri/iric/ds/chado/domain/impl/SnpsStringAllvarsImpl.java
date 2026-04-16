package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.utils.DbUtils;



/**
 * Implementation of SnpsStringAllvars
 * 
 * @author LMansueto
 *
 */

// @Component("SnpsStringAllvarsImpl")
public class SnpsStringAllvarsImpl implements SnpsStringAllvars {

	private String dataset;
	private BigDecimal var;
	// private Long chr;
	private Long chr;
	private String contig;
	private BigDecimal pos;
	private String refnuc;
	private String varnuc;
	private BigDecimal mismatch;
	// private boolean isnonsyn[];
	// private Set nonsynInonsynPossetdxset;
	private Set<Position> synPosset;
	private Set<Position> nonsynPosset;
	private Map<Position, Character> mapPos2Allele2;
	private String allele2;

	private Set acceptorPosset;
	private Set donorPosset;

	public SnpsStringAllvarsImpl(String dataset, BigDecimal var, String chr, String varnuc, BigDecimal mismatch,
			Map mapPos2Allele2, Set nonsynPosset, Set synPosset, Set donorPoset, Set acceptorPosset) {
		super();
		this.dataset = dataset;
		this.var = var;
		try {
			this.chr = Long.valueOf(DbUtils.guessChrFromString(chr));
		} catch (Exception ex) {

		}
		this.contig = chr;

		this.varnuc = varnuc;
		this.mismatch = mismatch;
		// this.isnonsyn = isnonsyn;
		// this.nonsynIdxset = nonsynIdxset;
		this.nonsynPosset = nonsynPosset;
		// this.mapPosIdx2Allele2 = mapPosIdx2Allele2;
		this.mapPos2Allele2 = mapPos2Allele2;
		this.synPosset = synPosset;

		this.acceptorPosset = acceptorPosset;
		this.donorPosset = donorPosset;
	}

	public SnpsStringAllvarsImpl(BigDecimal var, String chr, String varnuc, BigDecimal mismatch, Map mapPos2Allele2,
			Set nonsynPosset, Set synPosset, Set donorPoset, Set acceptorPosset) {
		super();
		this.dataset = dataset;
		this.var = var;
		try {
			this.chr = Long.valueOf(DbUtils.guessChrFromString(chr));
		} catch (Exception ex) {

		}
		this.contig = chr;

		this.varnuc = varnuc;
		this.mismatch = mismatch;
		// this.isnonsyn = isnonsyn;
		// this.nonsynIdxset = nonsynIdxset;
		this.nonsynPosset = nonsynPosset;
		// this.mapPosIdx2Allele2 = mapPosIdx2Allele2;
		this.mapPos2Allele2 = mapPos2Allele2;
		this.synPosset = synPosset;

		this.acceptorPosset = acceptorPosset;
		this.donorPosset = donorPosset;
	}

	@Override
	public Long getChr() {

		return chr;
	}

	// @Override
	// public BigDecimal getPosition() {
	//
	// return pos;
	// }
	//
	// @Override
	// public String getRefcall() {
	//
	// return refnuc;
	// }

	@Override
	public String getVarnuc() {

		return varnuc;
	}

	@Override
	public BigDecimal getVar() {

		return var;
	}

	@Override
	public BigDecimal getMismatch() {

		return mismatch;
	}

	/*
	 * @Override public boolean[] getIsnonsyn() { return isnonsyn; }
	 */

	@Override
	public Map<Position, Character> getMapPos2Allele2() {
		return mapPos2Allele2;
	}

	@Override
	public Set getNonsynPosset() {
		return nonsynPosset;
	}

	@Override
	public Set getSynPosset() {
		return synPosset;
	}

	@Override
	public boolean equals(Object obj) {

		SnpsStringAllvarsImpl o = (SnpsStringAllvarsImpl) obj;
		return var.equals(o.getVar());
	}

	@Override
	public Set getDonorPosset() {

		return this.donorPosset;
	}

	@Override
	public Set getAcceptorPosset() {

		return this.acceptorPosset;
	}

	@Override
	public String getContig() {

		return this.contig;
	}

	@Override
	public SnpsStringAllvars copy() {

		return new SnpsStringAllvarsImpl(dataset, var, (chr == null ? null : chr.toString()), varnuc, mismatch,
				(mapPos2Allele2 == null ? null : new HashMap(mapPos2Allele2)),
				(nonsynPosset == null ? null : new HashSet(nonsynPosset)),
				(synPosset == null ? null : new HashSet(synPosset)),
				(donorPosset == null ? null : new HashSet(donorPosset)),
				(acceptorPosset == null ? null : new HashSet(acceptorPosset)));
		/*
		 * { super(); this.var = var; try { this.chr =
		 * Long.valueOf(AppContext.guessChrFromString(chr)); } catch(Exception ex) {
		 * 
		 * } this.contig=chr;
		 * 
		 * this.varnuc = varnuc; this.mismatch = mismatch; //this.isnonsyn = isnonsyn;
		 * //this.nonsynIdxset = nonsynIdxset; this.nonsynPosset = nonsynPosset;
		 * //this.mapPosIdx2Allele2 = mapPosIdx2Allele2; this.mapPos2Allele2 =
		 * mapPos2Allele2;
		 * 
		 * this.acceptorPosset=acceptorPosset; this.donorPosset = donorPosset;
		 */
	}

	@Override
	public void setMismatch(BigDecimal mismatch) {

		this.mismatch = mismatch;
	}

	@Override
	public void setVarnuc(String varnuc) {

		this.varnuc = varnuc;
	}

	@Override
	public String getDataset() {
		return dataset;
	}

}
