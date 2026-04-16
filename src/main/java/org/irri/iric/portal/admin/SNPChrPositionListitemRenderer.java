package org.irri.iric.portal.admin;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class SNPChrPositionListitemRenderer implements ListitemRenderer {

	private String chromosome;
	
	
	
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}


	@Override
	public void render(Listitem arg0, Object arg1, int arg2) throws Exception {
		
		if(chromosome.equalsIgnoreCase("ANY"))
			arg0.setLabel(arg1.toString());
		else arg0.setLabel(chromosome + " : " +  arg1 );
	}

}
