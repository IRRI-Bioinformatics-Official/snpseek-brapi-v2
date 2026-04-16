package org.irri.iric.portal.ws.rest.brapiv1;

public class BrAPIResponse {
	Metadata metadata;
	Result result;

	public BrAPIResponse(Metadata metadata, Result result) {
		super();
		this.metadata = metadata;
		this.result = result;
	}

}
