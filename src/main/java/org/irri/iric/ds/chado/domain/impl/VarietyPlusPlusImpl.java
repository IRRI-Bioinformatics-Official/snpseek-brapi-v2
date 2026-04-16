package org.irri.iric.ds.chado.domain.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.VarietyPlus;
import org.irri.iric.ds.chado.domain.VarietyPlusPlus;

/**
 * Implementation of VarietyPlusPlus
 * 
 * @author LMansueto
 *
 */
public class VarietyPlusPlusImpl implements VarietyPlusPlus {

	private Variety varplus;
	private Map mapValues;

	public VarietyPlusPlusImpl(Variety variety, String val2name, Object value) {
		super();

		this.varplus = variety;
		mapValues = new LinkedHashMap();
		mapValues.put(val2name, value);
	}

	public VarietyPlusPlusImpl(VarietyPlus varietyplus2, String val2name) {
		super();
		this.varplus = varietyplus2;
		mapValues = new LinkedHashMap();
		mapValues.put(val2name, varietyplus2.getValue());
	}

	public VarietyPlusPlusImpl(Variety varietyplus1, VarietyPlus varietyplus2, String val2name) {
		super();
		// TODO Auto-generated constructor stub
		this.varplus = varietyplus1;
		mapValues = new LinkedHashMap();
		// mapValues.put( varietyplus1.getValueName(), varietyplus1.getValue());
		mapValues.put(val2name, varietyplus2.getValue());
	}

	@Override
	public Object getValue() {

		return mapValues.get("value"); // .values();
	}

	/*
	 * @Override public String getValueName() { return
	 * mapValues.keySet().toString(); }
	 */
	@Override
	public Map getValueMap() {

		return mapValues;
	}

	public Object getValue(String field) {

		return mapValues.get(field);
	}

	public void setValue(Object value) {

		mapValues.put("value", value);
	}

	@Override
	public BigDecimal getVarietyId() {

		return varplus.getVarietyId();
	}

	@Override
	public String getName() {

		return varplus.getName();
	}

	@Override
	public String getIrisId() {

		return varplus.getIrisId();
	}

	@Override
	public String getCountry() {

		return varplus.getCountry();
	}

	@Override
	public String getSubpopulation() {

		return varplus.getSubpopulation();
	}

	@Override
	public void setCountry(String country) {

		varplus.setCountry(country);

	}

	@Override
	public void setSubpopulation(String subpopulation) {

		varplus.setSubpopulation(subpopulation);

	}

	/*
	 * @Override public void setValueName(String valuename) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */
	@Override
	public void addValue(String name, Object value) {

		mapValues.put(name, value);
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

		StringBuffer buff = new StringBuffer();
		// buff.append(this.getName() + delimiter + irisid + delimiter + subpop +
		// delimiter + cntr);
		buff.append("\"" + this.getName() + "\"" + delimiter + "\"" + irisid + "\"" + delimiter + "\"" + acc + "\""
				+ delimiter + "\"" + subpop + "\"" + delimiter + "\"" + cntr + "\"");
		Iterator<String> itName = mapValues.keySet().iterator();
		if (itName.hasNext())
			buff.append(delimiter);
		while (itName.hasNext()) {
			buff.append(mapValues.get(itName.next()));
			if (itName.hasNext())
				buff.append(delimiter);
		}
		return buff.toString();

	}

	@Override
	public String getBoxCode() {

		return varplus.getBoxCode();
	}

	@Override
	public String getAccession() {

		return varplus.getAccession();
	}

	@Override
	public String getDataset() {

		return varplus.getDataset();
	}

	@Override
	public void setName(String name) {

		varplus.setName(name);
	}

	@Override
	public void setAccession(String accession) {

		varplus.setAccession(accession);
	}

	@Override
	public BigDecimal getStockSampleId() {
		// TODO Auto-generated method stub
		return null;
	}

}
