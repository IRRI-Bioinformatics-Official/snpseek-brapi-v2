package org.irri.iric.portal.ws.rest.brapiv1;

public class Metadata {
	Pagination pagination;
	String[] status;

	public Metadata(Pagination pagination, String[] status) {
		super();
		this.pagination = pagination;
		this.status = status;
	}

}
