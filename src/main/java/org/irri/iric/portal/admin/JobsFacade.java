package org.irri.iric.portal.admin;

import java.util.List;

public interface JobsFacade {
	
	public static String JOBSTATUS_CANCELLED="CANCELLED";
	public static String JOBSTATUS_ERROR="ERROR";
	public static String JOBSTATUS_DONE="DONE";
	public static String JOBSTATUS_SUBMITTED="QUEUED";
	//public static String JOBSTATUS_STARTING="STARTING";
	public static String JOBSTATUS_STARTING="RUNNING";
	public static String JOBSTATUS_REFUSED="REFUSED";
	public static String JOBSTATUS_DOWNLOADED="DOWNLOADED";
	public static String JOBSTATUS_MISSING="MISSING";

	public String getJobStatus(String jobid) throws Exception;

	public boolean addJob(AsyncJob job);

	public boolean doneSubmitter(String ip);
	public boolean errorSubmitter(String ip);
	public boolean errorSubmitter(String ip, String filename, String msg);
	public boolean startSubmitter(String ip);

	//public boolean checkSubmitter(String ip);
	public boolean downloadJob(String job);

	public boolean saveJobs();
	//public boolean loadJobs();
	void clearDownloadedJobs(boolean deletefiles);
	void clearAllJobs();
	public List<AsyncJob> getAllJobs(boolean active, boolean done,  boolean downloaded);

	boolean cancelJob(String ip);

	public AsyncJob getRunningJobByIp(String ip);
	public AsyncJob getRunningJobById(String jobid);

	String getAsyncStatus();

	int getMapSession2AttsSize();

	boolean checkSubmitter(String ip);

	public void setStatus(String filename, String msg);
	
	public void setLogDest(boolean forcelocal, boolean forces3);

	//protected boolean useS3();

	public byte[] getS3Reader(String jobid);

	public int setCorePoolSize(int size);
	public int setMaxPoolSize(int size);

	void setQueueCapacity(int size);

	boolean copyToS3Error(String filename);

	String setBucket(String b);

	public void setLogLevel(String string);

	public boolean useS3();

	public void setLocalUser(boolean b);
	
}
