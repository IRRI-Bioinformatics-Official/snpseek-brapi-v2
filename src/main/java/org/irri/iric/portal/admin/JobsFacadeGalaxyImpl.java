package org.irri.iric.portal.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
//import org.irri.iric.galaxy.service.GalaxyFacade;
import org.irri.iric.portal.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

@Service("GalaxyJobsFacade")
public class JobsFacadeGalaxyImpl implements  JobsFacade {
	
	
	private Map mapJobid2Historyid=new HashMap();
	
	private String activedonejobkey = "jobs-key-galaxy";
	private AmazonS3 s3 = null;
	@Autowired
	private AsyncJobExecutorConfig executor;
	
//	@Autowired
//	private GalaxyFacade galaxy;

	private boolean localUser;
	
	
	public JobsFacadeGalaxyImpl() {
		super();
		initS3();
	}

	

	@Override
	public String getJobStatus(String jobid) throws Exception {
//		galaxy=(GalaxyFacade)AppContext.checkBean(galaxy,"GalaxyFacade");
//		
//		String histid=(String)mapJobid2Historyid.get(jobid);
//		//getS3BufferedReader(String filename)
//		
//		return galaxy.updateStatus(jobid,histid); 
		return null;
	}

	@Override
	public boolean addJob(AsyncJob job) {
		// TODO job is added by sending a file to S3 with filename=jobid, content=galaxy_history_id,  last_status
		try {
				BufferedWriter bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() + job.getJobId() ));
				bw.append((String)job.getParams() + "\t" + job.getUrl() + '\t' + JobsFacade.JOBSTATUS_SUBMITTED);
				bw.close();
				sendToS3(new File(AppContext.getTempDir() + job.getJobId() ));
				mapJobid2Historyid.put(job.getJobId()  , job.getParams());
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}


	@Override
	public void setLogDest(boolean forcelocal, boolean forces3) {
	}

	@Override
	public int getMapSession2AttsSize() {
		return AppContext.getMapSession2Atts().size();
	}
	
	@Override
	public int setCorePoolSize(int size) {
		executor=(AsyncJobExecutorConfig)AppContext.checkBean(executor, "AsyncJobExecutorConfig");
		ThreadPoolTaskExecutor ex=(ThreadPoolTaskExecutor)executor.getThreadPoolTaskExecutor();
		int ret= ex.getCorePoolSize();
		ex.setCorePoolSize(size);
		return ret;
	}

	@Override
	public int setMaxPoolSize(int size) {
		executor=(AsyncJobExecutorConfig)AppContext.checkBean(executor, "AsyncJobExecutorConfig");
		ThreadPoolTaskExecutor ex=(ThreadPoolTaskExecutor)executor.getThreadPoolTaskExecutor();
		int ret=ex.getMaxPoolSize();
		ex.setMaxPoolSize(size);
		return ret;
	}
	@Override
	public void setQueueCapacity(int size) {
		executor=(AsyncJobExecutorConfig)AppContext.checkBean(executor, "AsyncJobExecutorConfig");
		ThreadPoolTaskExecutor ex=(ThreadPoolTaskExecutor)executor.getThreadPoolTaskExecutor();
		ex.setQueueCapacity(size);
	}

	@Override
	public void setLogLevel(String string) {
		
		if(string==null) string="info";
		AppContext.setLog_level(string);
	}
	
	
	@Override
	public boolean doneSubmitter(String ip) {
		return false;
	}

	@Override
	public boolean errorSubmitter(String ip) {
		return false;
	}

	@Override
	public boolean errorSubmitter(String ip, String filename, String msg) {
		return false;
	}

	@Override
	public boolean startSubmitter(String ip) {
		return false;
	}

	@Override
	public boolean downloadJob(String job) {
		return false;
	}

	@Override
	public boolean saveJobs() {
		return false;
	}

	@Override
	public void clearDownloadedJobs(boolean deletefiles) {
		
	}

	@Override
	public void clearAllJobs() {
		
	}

	@Override
	public List<AsyncJob> getAllJobs(boolean active, boolean done, boolean downloaded) {
		return null;
	}

	@Override
	public boolean cancelJob(String ip) {
		return false;
	}

	@Override
	public AsyncJob getRunningJobByIp(String ip) {
		return null;
	}

	@Override
	public AsyncJob getRunningJobById(String jobid) {
		return null;
	}

	@Override
	public String getAsyncStatus() {
		return null;
	}


	@Override
	public boolean checkSubmitter(String ip) {
		return false;
	}

	@Override
	public void setStatus(String filename, String msg) {
		
	}

	@Override
	public boolean useS3() {
		return false;
	}

	@Override
	public byte[] getS3Reader(String filename) {
		 
		try {
			S3Object object = s3.getObject(getBucket(), filename); 
			return IOUtils.toByteArray(object.getObjectContent());
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private BufferedReader getS3BufferedReader(String filename) throws Exception {
		try {
			S3Object object = s3.getObject(new GetObjectRequest(getBucket(), filename));
			return new BufferedReader(new InputStreamReader(object.getObjectContent()));
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean copyToS3Error(String filename) {
		return false;
	}

	@Override
	public String setBucket(String b) {
		return null;
	}

	
	private void log(int level,String msg) {
		StringBuffer s=new StringBuffer();
		s.append("GalaxyJob ");
		for(int i=0; i<level;i++) s.append("  ");
		s.append(level + ": " + msg);
		AppContext.debug(s.toString());
		//AppContext.debug(s.toString());
	}
	
	private void initS3() {

		if(s3!=null) return;
		//ClassLoader classLoader = this.getClass().getClassLoader();
		//URL resource = classLoader.getResource(" org/apache/http/conn/ssl/SSLConnectionSocketFactory.class");
		//log(0,"classloader " + resource);

		/*
		 * AWSCredentials credentials = null; try { //credentials = new
		 * ProfileCredentialsProvider("default").getCredentials();
		 * credentials = new BasicAWSCredentials("REDACTED",
		 * "REDACTED"); } catch (Exception
		 * e) { throw new AmazonClientException(
		 * "Cannot load the credentials from the credential profiles file. "
		 * +
		 * "Please make sure that your credentials file is at the correct "
		 * +
		 * "location (C:\\Users\\lmansueto\\.aws\\credentials), and is in valid format."
		 * , e); } //s3 = new AmazonS3Client(credentials); s3 =
		 * AmazonS3ClientBuilder.standard().withCredentials( new
		 * AWSStaticCredentialsProvider(credentials)).build(); //Region
		 * usWest2 = Region.getRegion(Regions.US_WEST_2); Region usWest2 =
		 * Region.getRegion(Regions.AP_SOUTHEAST_1); s3.setRegion(usWest2);
		 */
		AWSCredentials credentials = null;
		try {
			// credentials = new
			// ProfileCredentialsProvider("default").getCredentials();
			credentials = new BasicAWSCredentials("INSERT_CRENDENTIALS","DATA");
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (C:\\Users\\lmansueto\\.aws\\credentials), and is in valid format.", e);
		}
		s3 = new AmazonS3Client(credentials);
		// AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(
		// new AWSStaticCredentialsProvider(credentials)).build();
		// Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		Region usWest2 = Region.getRegion(Regions.AP_SOUTHEAST_1);
		s3.setRegion(usWest2);

	}

	
	private void sendToS3(File file) {
		file.deleteOnExit();
		PutObjectResult res = s3.putObject(new PutObjectRequest(getBucket(), activedonejobkey, file));
		
	}
	/**
	 * Deprecated: use getBucketName in AppContext
	 * no longer used 
	 * @return
	 */
	private String getBucket() {
		// UNUSED
		//		if(AppContext.isAWSBeanstalkDev())
		//			return "snp-seek-galaxy-jobs-dev";
		//		else if(AppContext.isAWSBeanstalk()) return "snp-seek-galaxy-jobs";
		//		else return "snp-seek-galaxy-jobs-dev";
		return null;
	}
	private String getErrorFolder() {
		return "error/";
	}
	private String getDownloadedFolder() {
		return "downloaded/";
	}



	@Override
	public void setLocalUser(boolean localUser) {
		this.localUser = localUser;
		
	}


}
