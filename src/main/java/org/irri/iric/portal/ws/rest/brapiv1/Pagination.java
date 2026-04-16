package org.irri.iric.portal.ws.rest.brapiv1;

public class Pagination {

	int pageSize;
	int currentPage;
	int totalCount;
	int totalPages;

	public Pagination(int pageSize, int currentPage, int totalCount, int totalPages) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.totalPages = totalPages;
	}

}
