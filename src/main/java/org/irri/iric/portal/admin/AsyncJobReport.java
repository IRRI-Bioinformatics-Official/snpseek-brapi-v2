package org.irri.iric.portal.admin;

import java.util.concurrent.Future;

public class AsyncJobReport {

	private String jobid;
	private String message;
	private String urlProgress;
	private Future future;
	
	public AsyncJobReport(String jobid, String message, String urlProgress, Future future) {
		super();
		this.jobid = jobid;
		this.message = message;
		this.urlProgress = urlProgress;
		this.future = future;
	}

	public String getJobid() {
		return jobid;
	}

	public String getMessage() {
		return message;
	}

	public String getUrlProgress() {
		return urlProgress;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Future getFuture() {
		return future;
	}
	
	
	
}
