package org.irri.iric.portal.admin;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.Future;

import org.irri.iric.portal.AppContext;

public class AsyncJobImpl implements AsyncJob {

	private String status;
	private String jobid;
	private Object params;
	private String ipaddress;
	private Future future;
	private String termination;
	private Date dateCreated;
	private Date dateDone;
	private Date dateStarted;
	private String workerid;

	public AsyncJobImpl(String jobid, String histid) {
		this.jobid=jobid;
		this.params=histid;
		
	}
	
	public AsyncJobImpl(String saveline) {
		super();
		AppContext.debug("AsyncJobImpl saveline=" + saveline);
		String[] fields = null;
		try {
			fields = saveline.split("\t");
			this.jobid = fields[0];
			if (fields[1] != null)
				this.status = fields[1];
			if (fields[2] != null)
				this.ipaddress = fields[2];
			if (fields[3] != null)
				this.params = fields[3];
			// this.termination=fields[4];
		} catch (Exception ex) {
			AppContext.error(saveline);
			ex.printStackTrace();
		}

		if (fields.length > 4 && fields[4] != null && !fields[4].isEmpty()) {
			try {
				this.termination = fields[4];
			} catch (Exception ex) {
				AppContext.error(saveline);
				ex.printStackTrace();
			}
		}

		if (fields.length > 5 && fields[5] != null && !fields[5].isEmpty()) {
			try {
				dateCreated = AppContext.getDateFormat().parse(fields[5]);
			} catch (Exception ex) {
				AppContext.error(saveline);
				ex.printStackTrace();
			}
		}

		// if(dateCreated==null)
		if (false) {
			/*
			 * try{ File f=null; if(jobid.startsWith("vcf2fasta")) f=new
			 * File(AppContext.getTempDir() + jobid + "/status"); else f=new
			 * File(AppContext.getTempDir() + jobid + ".status");
			 * 
			 * dateCreated=new Date(f.lastModified()); } catch(Exception ex) {
			 * ex.printStackTrace(); }
			 */

			try {
				String fname = null;
				if (jobid.startsWith("vcf2fasta"))
					fname = AppContext.getTempDir() + jobid + "/status";
				else
					fname = AppContext.getTempDir() + jobid + ".status";

				BasicFileAttributes attr = Files.readAttributes(Paths.get(fname), BasicFileAttributes.class);
				dateCreated = new Date(attr.creationTime().toMillis());
			} catch (Exception ex) {
				AppContext.error(saveline);
				ex.printStackTrace();
			}

		}

		if (fields.length > 6 && fields[6] != null && !fields[6].isEmpty()) {
			try {
				dateStarted = AppContext.getDateFormat().parse(fields[6]);
			} catch (Exception ex) {
				AppContext.error(saveline);
				ex.printStackTrace();
			}
		}

		if (fields.length > 7 && fields[7] != null && !fields[7].isEmpty()) {
			try {
				dateDone = AppContext.getDateFormat().parse(fields[7]);
			} catch (Exception ex) {
				AppContext.error(saveline);
				ex.printStackTrace();
			}
		}

		if (fields.length > 8 && fields[8] != null && !fields[8].isEmpty()) {
			try {
				workerid = fields[8];
			} catch (Exception ex) {
				AppContext.error(saveline);
				ex.printStackTrace();
			}

		}

	}

	public AsyncJobImpl(String jobid, Object params, String ipaddress) {
		super();
		this.jobid = jobid;
		this.params = params;
		this.ipaddress = ipaddress;
		dateCreated = new Date();
	}

	@Override
	public String getIpaddress() {
		return ipaddress;
	}

	@Override
	public String getJobId() {

		return jobid;
	}

	@Override
	public Object getParams() {

		return params;
	}

	@Override
	public String getStatus() {

		return status;
	}

	@Override
	public void setFuture(Future future) {

		this.future = future;
	}

	@Override
	public Future getFuture() {
		return future;
	}

	@Override
	public void setStatus(String status) {

		this.status = status;
		if (status.equals(JobsFacade.JOBSTATUS_SUBMITTED))
			dateCreated = new Date();
		if (status.equals(JobsFacade.JOBSTATUS_STARTING))
			dateStarted = new Date();
		if (status.equals(JobsFacade.JOBSTATUS_DONE) || status.equals(JobsFacade.JOBSTATUS_CANCELLED)
				|| status.equals(JobsFacade.JOBSTATUS_ERROR))
			dateDone = new Date();

	}

	@Override
	public String getUrl() {

		if (AppContext.getHostDirectory().isEmpty())
			return AppContext.getHostname() + "/_jobs.zul?jobid=" + jobid;
		else
			return AppContext.getHostname() + "/" + AppContext.getHostDirectory() + "/_jobs.zul?jobid=" + jobid;
	}

	@Override
	public String getTermination() {

		if (termination != null)
			return termination;

		if (future == null)
			return "UNKNOWN";
		if (future.isDone())
			return "DONE";
		if (future.isCancelled())
			return "CANCELLED";
		return "";
	}

	@Override
	public String toString() {

		// return super.toString();
		String datedone = "";
		String datestarted = "";
		if (termination == null)
			termination = "UNKNOWN";
		if (workerid == null)
			workerid = "";
		if (dateDone != null)
			datedone = AppContext.getDateFormat().format(dateDone);
		if (dateStarted != null)
			datestarted = AppContext.getDateFormat().format(dateStarted);
		return getJobId() + "\t" + getStatus() + "\t" + getIpaddress() + "\t" + getParams() + "\t" + termination + "\t"
				+ AppContext.getDateFormat().format(dateCreated) + "\t" + datestarted + "\t" + datedone + "\t"
				+ workerid;
	}

	@Override
	public String setTermination(String term) {

		return this.termination = term;
	}

	@Override
	public Date getDateCreated() {

		return this.dateCreated;
	}

	@Override
	public Date getDateDone() {

		return this.dateDone;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateDone(Date dateDone) {
		this.dateDone = dateDone;
	}

	@Override
	public void setWorkerId(String instanceid) {

		workerid = instanceid;
	}

	@Override
	public String getWorkerId() {

		return workerid;
	}

}
