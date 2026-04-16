package org.irri.iric.ds.chado.domain.object;

import org.codehaus.jettison.json.JSONObject;
import org.irri.iric.ds.chado.domain.Passport;

public class BrapiPassport extends BrapiObject implements Passport {

	String name;
	String definition;
	String value;
	
	public BrapiPassport(String name, String definition, String value) {
		super();
		this.name = name;
		this.definition = definition;
		this.value = value;
	}

	public BrapiPassport(JSONObject json) {
		super(json);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		//return getName();
		return definition;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		/*
		try {
			return "defaultDisplayName";
		} catch(Exception ex) {
			return null;
		}
		*/
		return name;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		/*
		try {
			return json.getString(getName());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
		*/
		return value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:" + getName() + ",value:" + getValue();
	}


}
