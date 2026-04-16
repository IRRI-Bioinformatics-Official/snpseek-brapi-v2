package org.irri.iric.portal.ws.entity;

import org.irri.iric.ds.chado.domain.LocalAlignment;
import org.irri.iric.ds.chado.domain.impl.LocalAlignmentImpl;

public class LocusLocalAlignmentWS implements LocalAlignment {

	private LocalAlignmentImpl localalignment;

	public LocusLocalAlignmentWS(LocalAlignmentImpl localalignment) {
		super();
		this.localalignment = localalignment;
	}

	@Override
	public String getQacc() {
		
		return localalignment.getQacc();
	}

	@Override
	public String getSacc() {
		
		return localalignment.getSacc();
	}

	@Override
	public Double getEvalue() {
		
		return localalignment.getEvalue();
	}

	@Override
	public Long getQstart() {
		
		return localalignment.getQstart();
	}

	@Override
	public Long getQend() {
		
		return localalignment.getQend();
	}

	@Override
	public Long getSstart() {
		
		return localalignment.getQstart();
	}

	@Override
	public Long getSend() {
		
		return localalignment.getSend();
	}

	@Override
	public Integer getSstrand() {
		
		return localalignment.getSstrand();
	}

	@Override
	public Double getRawScore() {
		
		return localalignment.getRawScore();
	}

	@Override
	public Long getAlignmentLength() {
		
		return localalignment.getAlignmentLength();
	}

	@Override
	public Double getPercentMatches() {
		
		return localalignment.getPercentMatches();
	}

	@Override
	public Long getMatches() {
		
		return localalignment.getMatches();
	}

	@Override
	public Long getMismatches() {
		
		return localalignment.getMismatches();
	}

	@Override
	public String getQSequence() {
		return null;
	}

	@Override
	public String getSSequence() {
		return null;
	}

	@Override
	public void setQSequence(String seq) {
		
	}

	@Override
	public void setSSequence(String seq) {
	
	}

	/*
	 * @Override public String getUniquename() { 
	 * return null; }
	 * 
	 * @Override public Integer getChr() {  return
	 * null; }
	 * 
	 * @Override public Integer getFmin() { 
	 * return null; }
	 * 
	 * @Override public Integer getFmax() { 
	 * return null; }
	 * 
	 * @Override public Integer getStrand() { 
	 * return null; }
	 * 
	 * @Override public String getContig() { 
	 * return null; }
	 * 
	 * /*
	 * 
	 * @Override public String getUniquename() { 
	 * return localalignment.getUniquename(); }
	 * 
	 * @Override public Integer getChr() {  return
	 * localalignment.getChr(); }
	 * 
	 * @Override public Integer getFmin() { 
	 * return localalignment.getFmin(); }
	 * 
	 * @Override public Integer getFmax() { 
	 * return localalignment.getFmax(); }
	 * 
	 * @Override public Integer getStrand() { 
	 * return localalignment.getSstrand(); }
	 * 
	 * @Override public String getContig() { 
	 * return localalignment.getContig(); }
	 * 
	 * 
	 */

}
