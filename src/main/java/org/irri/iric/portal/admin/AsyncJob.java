package org.irri.iric.portal.admin;

import java.util.Date;
import java.util.concurrent.Future;

public interface AsyncJob {

	
	public String getJobId();
	public Object getParams();
	public String getStatus();
	public void setStatus(String status);
	public String getIpaddress();
	public void setFuture(Future future);
	public Future getFuture();
	public String getUrl();
	public String getTermination();
	public String setTermination(String term);
	public Date getDateCreated();
	public void setDateCreated(Date date);
	public Date getDateStarted();
	public Date getDateDone();
	public void setWorkerId(String instanceid);
	public String getWorkerId();
	
}
