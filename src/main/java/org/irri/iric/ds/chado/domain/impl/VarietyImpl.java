package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;

import org.irri.iric.ds.chado.domain.Variety;

/**
 * Implementation of Variety
 * 
 * @author LMansueto
 *
 */
public class VarietyImpl implements Variety {

	protected BigDecimal id;
	protected String name;
	protected String irisId;
	protected String country;
	protected String subpopulation;
	protected String accession;

	@Override
	public String getName() {

		return name;
	}

	@Override
	public String getIrisId() {

		return irisId;
	}

	@Override
	public String getCountry() {

		return country;
	}

	@Override
	public String getSubpopulation() {

		return subpopulation;
	}

	@Override
	public void setCountry(String country) {

		this.country = country;
	}

	@Override
	public void setSubpopulation(String subpopulation) {

		this.subpopulation = subpopulation;
	}

	@Override
	public BigDecimal getVarietyId() {

		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((getVarietyId() == null) ? 0 : getVarietyId().hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Variety)) // VIricstockBasicprop))
			return false;
		// VIricstockBasicprop equalCheck = (VIricstockBasicprop) obj;
		Variety equalCheck = (Variety) obj;

		// return iricStockId.equals(equalCheck.getIricStockId());

		if ((getVarietyId() == null && equalCheck.getVarietyId() != null)
				|| (getVarietyId() != null && equalCheck.getVarietyId() == null))
			return false;
		if (getVarietyId() != null && !getVarietyId().equals(equalCheck.getVarietyId()))
			return false;
		return true;

	}

	@Override
	public int compareTo(Object o) {

		return getName().compareTo(((Variety) o).getName());
	}

	@Override
	public String printFields(String delimiter) {

		String irisid = getIrisId();
		if (irisid == null)
			irisid = "";
		String subpop = getSubpopulation();
		if (subpop == null)
			subpop = "";
		String cntr = getCountry();
		if (cntr == null)
			cntr = "";
		String acc = this.getAccession();
		if (acc == null)
			acc = "";

		// return this.getName() + delimiter + irisid + delimiter + subpop + delimiter +
		// cntr;
		return "\"" + this.getName() + "\"" + delimiter + "\"" + irisid + "\"" + delimiter + "\"" + acc + "\""
				+ delimiter + "\"" + subpop + "\"" + delimiter + "\"" + cntr + "\"";
	}

	@Override
	public String getBoxCode() {
		return null;
	}

	@Override
	public String getAccession() {

		return accession;
	}

	@Override
	public String getDataset() {
		return null;
	}

	@Override
	public void setName(String name) {

		this.name = name;
	}

	@Override
	public void setAccession(String accession) {

		this.accession = accession;

	}
	
	@Override
	public BigDecimal getStockSampleId() {
		// TODO Auto-generated method stub
		return null;
	}

}
