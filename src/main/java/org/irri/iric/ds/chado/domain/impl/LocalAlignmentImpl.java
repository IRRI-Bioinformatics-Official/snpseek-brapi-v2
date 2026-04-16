package org.irri.iric.ds.chado.domain.impl;

import org.irri.iric.ds.chado.domain.LocalAlignment;

/**
 * Implementation of LocalAlignment
 * 
 * @author LMansueto
 *
 */
public class LocalAlignmentImpl implements LocalAlignment {

	String qacc;
	String sacc;
	Double evalue;
	Long qstart;
	Long qend;
	Long sstart;
	Long send;
	Integer sstrand;

	Double rawScore;
	Long alignmentLength;
	Double percentMatches;
	Long matches;
	Long mismatches;
	String ssequence;
	String qsequence;

	public LocalAlignmentImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalAlignmentImpl(String qacc, String sacc, Double evalue, Long qstart, Long qend, Long sstart, Long send) {
		super();
		this.qacc = qacc;
		this.sacc = sacc;
		this.evalue = evalue;
		this.qstart = qstart;
		this.qend = qend;
		this.sstart = sstart;
		this.send = send;
	}

	public LocalAlignmentImpl(String qacc, String sacc, Double evalue, Integer qstart, Integer qend, Integer sstart,
			Integer send) {
		super();
		this.qacc = qacc;
		this.sacc = sacc;
		this.evalue = evalue;
		this.qstart = Long.valueOf(qstart);
		this.qend = Long.valueOf(qend);
		this.sstart = Long.valueOf(sstart);
		this.send = Long.valueOf(send);
	}

	public LocalAlignmentImpl(String qacc, String sacc, Double evalue, Long qstart, Long qend, Long sstart, Long send,
			Integer sstrand, Double rawScore, Long alignmentLength, Double percentMatches, Long matches,
			Long mismatches) {
		super();
		this.qacc = qacc;
		this.sacc = sacc;
		this.evalue = evalue;
		this.qstart = qstart;
		this.qend = qend;
		this.sstart = sstart;
		this.send = send;
		this.sstrand = sstrand;
		this.rawScore = rawScore;
		this.alignmentLength = alignmentLength;
		this.percentMatches = percentMatches;
		this.matches = matches;
		this.mismatches = mismatches;
	}

	@Override
	public String getQacc() {
		return qacc;
	}

	@Override
	public String getSacc() {
		return sacc;
	}

	@Override
	public Double getEvalue() {
		return evalue;
	}

	@Override
	public Long getQstart() {
		return qstart;
	}

	@Override
	public Long getQend() {
		return qend;
	}

	@Override
	public Long getSstart() {
		return sstart;
	}

	@Override
	public Long getSend() {
		return send;
	}

	@Override
	public Double getRawScore() {

		return rawScore;
	}

	@Override
	public Long getAlignmentLength() {

		return alignmentLength;
	}

	@Override
	public Double getPercentMatches() {

		return percentMatches;
	}

	@Override
	public Long getMatches() {

		return matches;
	}

	@Override
	public Long getMismatches() {

		return mismatches;
	}

	@Override
	public Integer getSstrand() {
		return sstrand;
	}

	public void setSstrand(Integer sstrand) {
		this.sstrand = sstrand;
	}

	public void setQacc(String qacc) {
		this.qacc = qacc;
	}

	public void setSacc(String sacc) {
		this.sacc = sacc;
	}

	public void setEvalue(Double evalue) {
		this.evalue = evalue;
	}

	public void setQstart(Long qstart) {
		this.qstart = qstart;
	}

	public void setQend(Long qend) {
		this.qend = qend;
	}

	public void setSstart(Long sstart) {
		this.sstart = sstart;
	}

	public void setSend(Long send) {
		this.send = send;
	}

	public void setRawScore(Double rawScore) {
		this.rawScore = rawScore;
	}

	public void setAlignmentLength(Long alignmentLength) {
		this.alignmentLength = alignmentLength;
	}

	public void setPercentMatches(Double percentMatches) {
		this.percentMatches = percentMatches;
	}

	public void setMatches(Long matches) {
		this.matches = matches;
	}

	public void setMismatches(Long mismatches) {
		this.mismatches = mismatches;
	}

	@Override
	public String getQSequence() {

		return qsequence;
	}

	@Override
	public String getSSequence() {

		return ssequence;
	}

	@Override
	public void setQSequence(String seq) {

		qsequence = seq;
	}

	@Override
	public void setSSequence(String seq) {

		ssequence = seq;
	}

}
