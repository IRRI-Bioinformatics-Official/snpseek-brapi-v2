package org.irri.iric.portal.ws.rest.brapiv1;

import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;

public class Markers {
	String markerId;
	String markerName;
	Long location;

	public Markers(SnpsAllvarsPos pos) {
		super();
		
		markerId = pos.getContig() + "-" + location; // pos.getSnpFeatureId().toString();
		location = pos.getPosition().longValue();
		markerName = pos.getContig() + "-" + location;
	}

}
