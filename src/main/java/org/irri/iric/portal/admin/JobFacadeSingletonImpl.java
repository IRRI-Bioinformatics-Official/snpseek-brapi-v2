package org.irri.iric.portal.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.irri.iric.portal.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.util.EC2MetadataUtils;

@Service("JobsFacade")
public class JobFacadeSingletonImpl implements JobsFacade {

	private Map<String, AsyncJob> mapIp2RunningJob = new LinkedHashMap();
	private Map<String, AsyncJob> doneJob = new LinkedHashMap();
	private Set<AsyncJob> downloadedJob = new LinkedHashSet();

	private AmazonS3 s3 = null;
	private boolean forcelocal = false;
	private boolean forces3 = false;

	private String activedonejobkey = "jobs-key";
	private String downlaodedjobkey = "downloaded-key";
	private String maxwaitmins = "60";
	private String bucket = null;

	// @Autowired
	// private AsyncConfigurer asynconf;

	/*
	 * @Bean(name="threadPoolTaskExecutor") public Executor
	 */

	/*
	 * @Autowired
	 * 
	 * @Qualifier("threadPoolTaskExecutor") private ThreadPoolTaskExecutor
	 * threadPoolTaskExecutor;
	 */
	@Autowired
	private AsyncJobExecutorConfig executor;

	private boolean LocalUser;

	public JobFacadeSingletonImpl() {
		super();
		AppContext.debug("JobFacadeSingletonImpl started.. loading jobs");

		if (useS3()) {

			ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();

			ClassLoader classLoader = this.getClass().getClassLoader();
			URL resource = classLoader.getResource(" org/apache/http/conn/ssl/SSLConnectionSocketFactory.class");
			log(0, "classloader " + resource);

			s3 = AmazonS3ClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
					.withRegion(Regions.AP_SOUTHEAST_1).build();

		} else
			loadJobs(0, false, false);
	}

	@Override
	public String setBucket(String b) {
		String old = getBucket();
		bucket = b;
		return old;
	}

	/**
	 * Deprecated, use bucket based on AppContext
	 * 
	 * @return
	 */
	private String getBucket() {

		/*
		 * REMOVED deprecated method to set bucket based on AppContext
		 * 
		 * @return
		 * 
		 * unused bucket variable
		 */
			//		if(AppContext.isAWSBeanstalkDev())
			//			return "snp-seek-jobs-dev";
			//		else if(AppContext.isAWSBeanstalk()) return "snp-seek-jobs";
			//		else return "snp-seek-jobs-dev";*/
		return null;
	}

	private String getErrorFolder() {
		return "error/";
	}

	private void log(int level, String msg) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < level; i++)
			s.append("  ");
		s.append(level + ": " + msg);
		AppContext.debug(s.toString());
		// AppContext.debug(s.toString());
	}
	/*
	 * private void log(String msg) { AppContext.debug(msg); }
	 */

	@Override
	public boolean useS3() {
		// return true;

		if (forces3)
			return true;
		if (forcelocal)
			return false;
		if (LocalUser)
			return false;
		// return AppContext.isAWSBeanstalk()|| AppContext.isPollux() ||
		// AppContext.isAWSBeanstalkDev();
		return true;

	}

	@Override
	public void setLogDest(boolean forcelocal, boolean forces3) {

		this.forcelocal = forcelocal;
		this.forces3 = forces3;
	}

	/**
	 * copy jobs from S3 to localmap, wait if locked by others, then lock so others
	 * cant modify
	 * 
	 * @param level
	 * @return
	 */
	private boolean synchIn(int level, boolean lock) {
		log(level, "synchIn " + lock);
		// waitAndLockJobs(level+1,lock);
		return loadJobs(level + 1, false, false);
	}

	private boolean synchInDownloaded(int level, boolean lock) {
		log(level, "synchInDownloaded " + lock);
		// waitAndLockJobs(level+1,lock);
		return loadDownloadedJobs(level + 1, false, false);
	}

	/*
	 * private boolean synchIn(int level) { return synchIn(level,true); }
	 */

	/**
	 * copy jobs from localmap to S3, unlock if finish
	 * 
	 * @param level
	 * @return
	 */
	private boolean synchOut(int level, boolean synchin, boolean unlock) {
		log(level, "syncOut " + synchin + " " + unlock);
		saveJobs(level + 1, synchin, false);
		if (unlock)
			return unlockJobs(level + 1);
		return true;
	}

	private boolean synchOutDownloaded(int level, boolean synchin, boolean unlock) {
		log(level, "syncOutDownloaded " + synchin + " " + unlock);
		saveDownloadedJobs(level + 1, synchin, false);
		if (unlock)
			return unlockDownloadedJobs(level + 1);
		return true;
	}

	@Override
	public boolean cancelJob(String ip) {
		return cancelJob(0, ip, true);
	}

	@Override
	public String getJobStatus(String jobid) throws Exception {
		return getJobStatus(0, jobid, true);
	}

	@Override
	public boolean addJob(AsyncJob job) {

		return addJob(0, job, true);
	}

	@Override
	public boolean checkSubmitter(String ip) {

		return checkSubmitter(0, ip, true);
	}

	@Override
	public boolean doneSubmitter(String ip) {

		return doneSubmitter(0, ip, true);
	}

	@Override
	public boolean errorSubmitter(String ip) {

		return errorSubmitter(0, ip, true);
	}

	@Override
	public boolean errorSubmitter(String ip, String filename, String msg) {
		return errorSubmitter(0, ip, filename, msg, true);
	}

	@Override
	public boolean startSubmitter(String ip) {
		return startSubmitter(0, ip, true);
	}

	@Override
	public AsyncJob getRunningJobById(String jobid) {
		return getRunningJobById(0, jobid, true);
	}

	@Override
	public AsyncJob getRunningJobByIp(String ip) {
		return getRunningJobByIp(0, ip, true);
	}

	@Override
	public List<AsyncJob> getAllJobs(boolean active, boolean done, boolean downloaded) {

		return getAllJobs(0, active, done, downloaded, true, false);
	}

	@Override
	public String getAsyncStatus() {

		SimpleAsyncTaskExecutor ex = null;
		ex = (SimpleAsyncTaskExecutor) AppContext.checkBean(ex, "simpleAsyncTaskExecutor");
		String str = "ThreadNamePrefix=" + ex.getThreadNamePrefix() + ";ConcurrencyLimit=" + ex.getConcurrencyLimit()
				+ ";ThrottleActive=" + ex.isThrottleActive() + ":ThreadPriority=" + ex.getThreadPriority() + ";Daemon="
				+ ex.isDaemon() + ";" + ex.toString();

		ThreadPoolTaskExecutor ex2 = null;
		ex2 = (ThreadPoolTaskExecutor) AppContext.checkBean(ex2, "threadPoolTaskExecutor");
		return str + "\n" + "ThreadNamePrefix=" + ex2.getThreadNamePrefix() + ";getActiveCount=" + ex2.getActiveCount()
				+ ";getPoolSize=" + ex2.getPoolSize() + ":getCorePoolSize=" + ex2.getCorePoolSize() + ";getMaxPoolSize="
				+ ex2.getMaxPoolSize() + ";getKeepAliveSeconds=" + ex2.getKeepAliveSeconds() + ";" + ex2.toString();

	}

	private boolean addJob(int level, AsyncJob job, boolean sync) {
		log(level, "add " + job.getJobId() + " " + sync);

		if (sync)
			synchIn(level + 1, true);
		if (checkSubmitter(level + 1, job.getIpaddress(), false)) {
			if (sync)
				synchOut(level + 1, false, true);
			return false;
		}

		// job.setWorkerId(AppContext.getInstance());
		job.setWorkerId("-1");
		mapIp2RunningJob.put(job.getIpaddress(), job);
		if (sync)
			synchOut(level + 1, false, true);
		return true;
	}

	private boolean cancelJob(int level, String ip, boolean sync) {
		log(level, "cancel " + ip + " " + sync);

		if (sync)
			synchIn(level + 1, true);
		if (mapIp2RunningJob.containsKey(ip)) {
			AsyncJob job = mapIp2RunningJob.get(ip);
			job.setStatus(JOBSTATUS_DOWNLOADED);
			job.setTermination(JOBSTATUS_CANCELLED);
			downloadedJob.add(job);
			mapIp2RunningJob.remove(ip);

			if (sync)
				synchOut(level + 1, false, true);
			return true;
		}

		if (sync)
			synchOut(level + 1, false, true);
		return false;

	}

	private boolean lock(int level, String filename) {

		if (!useS3())
			return true;
		log(level, "lock " + filename);
		try {
			filename = filename.replace("/", "");
			PutObjectResult res = s3
					.putObject(new PutObjectRequest(getBucket(), filename + ".lock", createSampleFile("")));
			log(level, AppContext.getInstance() + ":locked " + filename);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean unlock(int level, String filename) {
		if (!useS3())
			return true;
		log(level, "unlock " + filename);
		try {
			filename = filename.replace("/", "");
			s3.deleteObject(getBucket(), filename + ".lock");
			log(level, AppContext.getInstance() + ":unlocked " + filename);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private boolean waitAndLock(int level, String file, boolean lock) {

		if (!useS3())
			return true;
		int lockcount = 0;
		file = file.replace("/", ".");
		log(level, "waitAndLock " + file + " " + lock);
		while (s3.doesObjectExist(getBucket(), file + ".lock") && lockcount < 60) {
			try {
				log(level, AppContext.getInstance() + ":waiting locked " + file);
				TimeUnit.SECONDS.sleep(5);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			lockcount += 5;
		}
		if (lockcount >= 60) {
			// throw new RuntimeException("Too long to wait locked file " + file);
			log(level, "Too long to wait locked file " + file + ", unlocking");
			log(level, "locked " + file + " unlocked");
			return unlock(level + 1, file);
		}
		log(level, "locked " + file + " unlocked");
		if (lock)
			return lock(level + 1, file);
		else
			return true;
	}

	@Override
	public boolean saveJobs() {
		return saveJobs(0, true, true);
	}

	private boolean saveJobs(int level, boolean synchin, boolean synchout) {

		log(level, "savejobs " + synchin + ", " + synchout);
		if (useS3()) {
			try {

				log(level, "writing aws-java-sdk.txt");

				// File file = File.createTempFile("aws-java-sdk-" +
				// AppContext.createTempFilename(), ".txt");
				File file = File.createTempFile("aws-java-sdk", ".txt");
				file.deleteOnExit();
				Writer writer = new OutputStreamWriter(new FileOutputStream(file));
				Iterator<AsyncJob> itjobs = getAllJobs(level + 1, true, true, true, synchin, false).iterator();
				while (itjobs.hasNext()) {
					AsyncJob job = itjobs.next();
					writer.append(job.toString() + "\n");
				}
				writer.close();
				// if(waitAndLock(activedonejobkey)) {
				log(level, "writing aws-java-sdk.txt to " + activedonejobkey);
				PutObjectResult res = s3.putObject(new PutObjectRequest(getBucket(), activedonejobkey, file));
				// unlock(activedonejobkey);

				return true;
				// }
				// return false;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		} else {
			try {
				log(level, "writing " + AppContext.getTempDir() + "jobs.log");
				BufferedWriter bw = new BufferedWriter(new FileWriter(AppContext.getTempDir() + "jobs.log"));
				Iterator<AsyncJob> itjobs = getAllJobs(level + 1, true, true, true, false, false).iterator();
				while (itjobs.hasNext()) {
					AsyncJob job = itjobs.next();
					bw.append(job.toString() + "\n");
				}
				bw.close();
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
	}

	private boolean saveDownloadedJobs(int level, boolean synchin, boolean synchout) {

		log(level, "saveDownloadedjobs " + synchin + ", " + synchout);
		if (useS3()) {
			try {

				log(level, "writing aws-java-sdk.txt");

				// File file = File.createTempFile("aws-java-sdk-" +
				// AppContext.createTempFilename(), ".txt");
				File file = File.createTempFile("aws-java-sdk", ".txt");
				file.deleteOnExit();
				Writer writer = new OutputStreamWriter(new FileOutputStream(file));
				Iterator<AsyncJob> itjobs = getAllJobs(level + 1, true, true, true, synchin, false).iterator();
				while (itjobs.hasNext()) {
					AsyncJob job = itjobs.next();
					writer.append(job.toString() + "\n");
				}
				writer.close();
				// if(waitAndLock(activedonejobkey)) {
				log(level, "writing aws-java-sdk.txt to " + downlaodedjobkey);
				PutObjectResult res = s3.putObject(new PutObjectRequest(getBucket(), downlaodedjobkey, file));
				// unlock(activedonejobkey);

				return true;
				// }
				// return false;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		} else {
			try {
				log(level, "writing " + AppContext.getTempDir() + "downloadedjobs.log");
				BufferedWriter bw = new BufferedWriter(new FileWriter(AppContext.getTempDir() + "downloadedjobs.log"));
				Iterator<AsyncJob> itjobs = getAllJobs(level + 1, true, true, true, false, false).iterator();
				while (itjobs.hasNext()) {
					AsyncJob job = itjobs.next();
					bw.append(job.toString() + "\n");
				}
				bw.close();
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
	}

	private boolean deleteFile(int level, String filename) {
		log(level, "delete " + filename);
		try {
			if (useS3()) {
				filename = filename.replace("/", "");
				if (waitAndLock(level + 1, filename, true)) {
					log(level, "deleting " + filename + " from S3\n");
					s3.deleteObject(getBucket(), filename);
					unlock(level + 1, filename);
					return true;
				}
				return false;
			} else {
				File file = new File(AppContext.getTempDir() + filename);
				if (file.exists()) {
					file.delete();
				} else
					AppContext.debug("did not find " + file.getAbsolutePath());
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/*
	 * private boolean saveFile(String filename) {
	 * 
	 * 
	 * reurn false; }
	 */

	@Override
	public void clearDownloadedJobs(boolean delete) {

		log(0, "clearDownloadedJobs " + delete);
		synchIn(0, true);
		if (delete) {
			Iterator<AsyncJob> itjob = downloadedJob.iterator();
			while (itjob.hasNext()) {
				AsyncJob job = itjob.next();
				deleteFile(0, job.getJobId() + ".zip");
				/*
				 * File file = new File(AppContext.getTempDir() + job.getJobId() +".zip");
				 * if(file.exists()) { file.delete(); } else AppContext.debug("did not find " +
				 * file.getAbsolutePath());
				 */
			}
		}
		downloadedJob.clear();
		synchOut(0, false, true);
	}

	@Override
	public void clearAllJobs() {

		log(0, "clearAllJobs");
		synchIn(0, true);
		mapIp2RunningJob.clear();
		doneJob.clear();
		downloadedJob.clear();
		synchOut(0, false, true);
	}

	/**
	 * Displays the contents of the specified input stream as text.
	 *
	 * @param input The input stream to display as text.
	 *
	 * @throws IOException
	 */
	private static void displayTextInputStream(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;

			// log(level," " + line);
		}
		// AppContext.debug();
	}

	private static File createSampleFile(String data) throws IOException {
		File file = File.createTempFile(AppContext.getTempDir() + "aws-java-sdk-" + AppContext.createTempFilename(),
				".txt");
		file.deleteOnExit();
		Writer writer = new OutputStreamWriter(new FileOutputStream(file));
		writer.write(data);
		/*
		 * writer.write("abcdefghijklmnopqrstuvwxyz\n");
		 * writer.write("01234567890112345678901234\n");
		 * writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
		 * writer.write("01234567890112345678901234\n");
		 * writer.write("abcdefghijklmnopqrstuvwxyz\n");
		 */
		writer.close();

		return file;
	}

	/*
	 * private Writer getWriter() throws Exception { if(useS3()) {
	 * AppContext.debug("Writing object");
	 * 
	 * AppContext.debug("Uploading a new object to S3 from a file\n"); File file =
	 * File.createTempFile("aws-java-sdk-", ".txt"); file.deleteOnExit(); Writer
	 * writer = new OutputStreamWriter(new FileOutputStream(file)); return writer; }
	 * try { BufferedWriter bw=new BufferedWriter(new FileWriter(
	 * AppContext.getTempDir() + "jobs.log" )); return bw; } catch(Exception ex) {
	 * ex.printStackTrace(); } return null; }
	 */

	private BufferedReader getFileReader(int level, String filename, boolean create) throws Exception {
		log(level, "getFileReader " + filename);
		if (useS3()) {
			filename = filename.replace("/", ".");
			if (s3.doesObjectExist(getBucket(), filename)) {
				S3Object object = s3.getObject(new GetObjectRequest(getBucket(), filename));
				return new BufferedReader(new InputStreamReader(object.getObjectContent()));
			}
			if (create) {
				PutObjectResult res = s3.putObject(new PutObjectRequest(getBucket(), filename, createSampleFile("")));
				S3Object object = s3.getObject(new GetObjectRequest(getBucket(), filename));
				return new BufferedReader(new InputStreamReader(object.getObjectContent()));
			}
			return null;
		}
		if (!new File(AppContext.getTempDir() + filename).exists()) {
			if (create) {
				try {
					new File(AppContext.getTempDir() + filename).createNewFile();
				} catch (Exception ex) {
					ex.printStackTrace();
					return null;
				}
				return new BufferedReader(new FileReader(AppContext.getTempDir() + filename));
			} else
				return null;
		} else
			return new BufferedReader(new FileReader(AppContext.getTempDir() + filename));

	}

	@Override
	public byte[] getS3Reader(String filename) {

		try {
			S3Object object = s3.getObject(getBucket(), filename);
			return IOUtils.toByteArray(object.getObjectContent());
			// return getFileReader(0,filename, false);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private boolean waitAndLockJobs(int level, boolean lock) {
		log(level, "waitAndLockJobs " + lock);
		return waitAndLock(level + 1, activedonejobkey, lock);
	}

	private boolean waitAndLockDownloadedJobs(int level, boolean lock) {
		log(level, "waitAndLockDownloadedJobs " + lock);
		return waitAndLock(level + 1, downlaodedjobkey, lock);
	}

	private boolean unlockJobs(int level) {
		log(level, "unlockJobs");
		return unlock(level + 1, activedonejobkey);
	}

	private boolean unlockDownloadedJobs(int level) {
		log(level, "unlockDownloadedJobs");
		return unlock(level + 1, downlaodedjobkey);
	}

	private Date getDateCreated(int level, String key) {
		try {
			if (useS3()) {
				log(level, "S3 getDateCreated " + key);
				String fname = null;
				if (key.startsWith("vcf2fasta"))
					fname = key + "/status";
				else
					fname = key + ".status";
				try {
					S3Object object = s3.getObject(new GetObjectRequest(getBucket(), fname));
					return object.getObjectMetadata().getLastModified();
				} catch (Exception ex) {
					return null;
				}
			} else {
				log(level, "file getDateCreated " + key);
				String fname = null;
				if (key.startsWith("vcf2fasta"))
					fname = AppContext.getTempDir() + key + "/status";
				else
					fname = AppContext.getTempDir() + key + ".status";

				BasicFileAttributes attr = Files.readAttributes(Paths.get(fname), BasicFileAttributes.class);
				return new Date(attr.creationTime().toMillis());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	private BufferedReader getJobsReader(int level) throws Exception {
		log(level, "getJobsReader ");
		if (useS3()) {
			log(level, "Downloading an object " + activedonejobkey);
			S3Object object = s3.getObject(new GetObjectRequest(getBucket(), activedonejobkey));
			// AppContext.debug("Content-Type: " +
			// object.getObjectMetadata().getContentType());
			log(level, "S3 reader " + activedonejobkey);
			return new BufferedReader(new InputStreamReader(object.getObjectContent()));
		}

		if (!new File(AppContext.getTempDir() + "jobs.log").exists()) {
			try {
				new File(AppContext.getTempDir() + "jobs.log").createNewFile();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		log(level, "local reader jobs.log");
		return new BufferedReader(new FileReader(AppContext.getTempDir() + "jobs.log"));
	}

	private BufferedReader getDownloadedJobsReader(int level) throws Exception {
		log(level, "getDownloadedJobsReader ");
		if (useS3()) {
			log(level, "Downloading an object " + downlaodedjobkey);
			S3Object object = s3.getObject(new GetObjectRequest(getBucket(), downlaodedjobkey));
			// AppContext.debug("Content-Type: " +
			// object.getObjectMetadata().getContentType());
			log(level, "S3 reader " + downlaodedjobkey);
			return new BufferedReader(new InputStreamReader(object.getObjectContent()));
		}

		if (!new File(AppContext.getTempDir() + "downloadedjobs.log").exists()) {
			try {
				new File(AppContext.getTempDir() + "downloadedjobs.log").createNewFile();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		log(level, "local reader downloadedjobs.log");
		return new BufferedReader(new FileReader(AppContext.getTempDir() + "downloadedjobs.log"));
	}

	// @Override
	/**
	 * Atomic load, no synch inside
	 * 
	 * @param level
	 * @param lock
	 * @param unlockAfter
	 * @return
	 */
	private boolean loadDownloadedJobs(int level, boolean lock, boolean unlockAfter) {
		// return loadJobs( level, lock, unlockAfter, activedonejobkey);
		log(level, "loadDownloadedJobs " + lock + ", " + unlockAfter);

		downloadedJob = new LinkedHashSet();

		if (useS3()) {

			// waitAndLock(activedonejobkey);
			boolean exists = s3.doesObjectExist(getBucket(), downlaodedjobkey);
			boolean success = false;
			if (!exists) {
				try {
					s3.putObject(new PutObjectRequest(getBucket(), downlaodedjobkey, createSampleFile("")));
					success = true;
					log(level, "putObject " + getBucket() + " " + downlaodedjobkey + " .. SUCCESS");
				} catch (Exception ex) {
					ex.printStackTrace();
					log(level, "putObject " + getBucket() + " " + downlaodedjobkey + " .. FAILED");
					return false;
				}
			}
			// unlock(activedonejobkey);
			// return success;

		} else {
			if (!new File(AppContext.getTempDir() + "downloadedjobs.log").exists()) {
				try {
					new File(AppContext.getTempDir() + "downloadedjobs.log").createNewFile();
				} catch (Exception ex) {
					ex.printStackTrace();
					return false;
				}
			}
		}

		String line = null;
		try {
			// BufferedReader br=new BufferedReader(new FileReader(
			// AppContext.getTempDir() + "jobs.log" ));
			waitAndLockDownloadedJobs(level + 1, lock);
			BufferedReader br = getDownloadedJobsReader(level + 1);
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				AsyncJob job = new AsyncJobImpl(line);
				if (job.getDateCreated() == null) {
					job.setDateCreated(getDateCreated(level, job.getJobId()));
				}
				if (job.getStatus().equals(JOBSTATUS_DOWNLOADED))
					downloadedJob.add(job);
				else
					log(level, "downloaded job expected for " + job.getJobId());
			}
			br.close();
			if (lock && unlockAfter)
				unlockDownloadedJobs(level + 1);
			log(level, " getDownloadedJobsReader ...sucess");
			return true;
		} catch (Exception ex) {
			AppContext.error("line=" + line);
			ex.printStackTrace();
			if (lock && unlockAfter)
				unlockDownloadedJobs(level + 1);
			log(level, " getDownloadedJobsReader ...false");
			return false;
		}
	}

	protected boolean loadJobs(int level, boolean lock, boolean unlockAfter) {

		// check if exists, if not create

		log(level, "loadJobs " + lock + ", " + unlockAfter);

		doneJob = new LinkedHashMap();
		downloadedJob = new LinkedHashSet();
		mapIp2RunningJob = new LinkedHashMap();

		if (useS3()) {

			// waitAndLock(activedonejobkey);
			boolean exists = s3.doesObjectExist(getBucket(), activedonejobkey);
			boolean success = false;
			if (!exists) {
				try {
					s3.putObject(new PutObjectRequest(getBucket(), activedonejobkey, createSampleFile("")));
					success = true;
					log(level, "putObject " + getBucket() + " " + activedonejobkey + " .. SUCCESS");
				} catch (Exception ex) {
					ex.printStackTrace();
					log(level, "putObject " + getBucket() + " " + activedonejobkey + " .. FAILED");
					return false;
				}
			}
			// unlock(activedonejobkey);
			// return success;

		} else {
			if (!new File(AppContext.getTempDir() + "jobs.log").exists()) {
				try {
					new File(AppContext.getTempDir() + "jobs.log").createNewFile();
				} catch (Exception ex) {
					ex.printStackTrace();
					return false;
				}
			}
		}

		String line = null;
		try {
			// BufferedReader br=new BufferedReader(new FileReader(
			// AppContext.getTempDir() + "jobs.log" ));
			waitAndLockJobs(level + 1, lock);
			BufferedReader br = getJobsReader(level + 1);
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				if (line.startsWith("snp3kvars") || line.startsWith("vcf2fasta"))
					;
				else
					continue;
				AsyncJob job = new AsyncJobImpl(line);
				if (job.getDateCreated() == null) {
					job.setDateCreated(getDateCreated(level, job.getJobId()));
				}
				if (job.getStatus().equals(JOBSTATUS_DONE))
					doneJob.put(job.getJobId(), job);
				else if (job.getStatus().equals(JOBSTATUS_DOWNLOADED))
					downloadedJob.add(job);
				else
					mapIp2RunningJob.put(job.getIpaddress(), job);
			}
			br.close();
			if (lock && unlockAfter)
				unlockJobs(level + 1);
			log(level, " getJobsReader ...sucess");
			return true;
		} catch (Exception ex) {
			AppContext.error("line=" + line);
			ex.printStackTrace();
			if (lock && unlockAfter)
				unlockJobs(level + 1);
			log(level, " getJobsReader ...false");
			return false;
		}
	}

	@Override
	public void setStatus(String filename, String msg) {

		writeStatus(0, filename, msg, false);
	}

	private String getJobStatus(int level, String jobid, boolean sync) throws Exception {

		/*
		 * File fstatus = null; if(jobid.startsWith("vcf2fasta")) { fstatus = new File(
		 * AppContext.getTempDir() + jobid +"/status"); } else fstatus = new File(
		 * AppContext.getTempDir() + jobid+".status");
		 * 
		 * String msg=""; if(fstatus.exists()) { BufferedReader br=new
		 * BufferedReader(new FileReader(fstatus)); msg=br.readLine(); br.close(); }
		 * else { msg= JOBSTATUS_SUBMITTED; // "No running job " + jobid; }
		 */
		log(level, "getJobStatus " + jobid + ",  " + sync);
		if (sync)
			synchIn(level + 1, true);

		String msg = updateJobStatus(level + 1, jobid, false);

		if (msg.equals(JOBSTATUS_DONE)) {
			AsyncJob job = getRunningJobById(level + 1, jobid, false);
			if (job != null)
				doneSubmitter(level + 1, job.getIpaddress(), false);
		}
		synchOut(level + 1, false, true);
		return msg;
	}

	/**
	 * Check if ip has a queued/running job
	 * 
	 * @param level
	 * @param ip
	 * @param sync
	 * @return
	 */
	private boolean checkSubmitter(int level, String ip, boolean sync) {

		log(level, "checkSubmitter " + ip + " " + sync);
		if (sync)
			synchIn(level + 1, false);
		return mapIp2RunningJob.containsKey(ip);
	}

	@Override
	public boolean copyToS3Error(String filename) {
		if (!useS3())
			return true;
		try {
			log(0, "copytos3/error " + filename);
			File f = new File(AppContext.getTempDir() + filename);
			if (f.exists()) {
				String fname = new File(filename).getName();
				PutObjectResult res = s3.putObject(new PutObjectRequest(getBucket(), getErrorFolder() + fname, f));
				// PutObjectResult res = s3.putObject(new PutObjectRequest(getBucket(),
				// getErrorFolder() + filename.replace("/", ""), f));
				log(0, "putObject " + getBucket() + " " + getErrorFolder() + filename.replace("/", "") + " .. sucess");
			} else {
				AppContext.error("did not find file " + filename + " to copy to S3/error");
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private boolean copyToS3(String filename) {
		if (!useS3())
			return true;
		try {
			TimeUnit.SECONDS.sleep(2);
			File f = new File(AppContext.getTempDir() + filename);
			log(0, "copytos3 " + f.getAbsolutePath());
			if (f.exists()) {
				String fname = new File(filename).getName();
				PutObjectResult res = s3.putObject(new PutObjectRequest(getBucket(), fname, f));
				// PutObjectResult res = s3.putObject(new PutObjectRequest(getBucket(),
				// filename.replace("/", ""), f));
				log(0, "putObject " + getBucket() + " " + f.getAbsolutePath() + " .. sucess");
			} else {
				log(0, "putObject " + getBucket() + " " + f.getAbsolutePath() + " .. failed");
				AppContext.error("did not find file " + f.getAbsolutePath() + " to copy to S3");
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private boolean doneSubmitter(int level, String ip, boolean sync) {

		log(level, "done " + ip + ", " + sync);
		if (sync)
			synchIn(level + 1, true);
		if (checkSubmitter(level + 1, ip, false)) {
			AsyncJob job = mapIp2RunningJob.get(ip);
			job.setStatus(JOBSTATUS_DONE);
			doneJob.put(job.getJobId(), job);
			mapIp2RunningJob.remove(ip);
			copyToS3(job.getJobId() + ".zip");
			if (sync)
				synchOut(level + 1, false, true);
			return true;
		}
		if (sync)
			synchOut(level + 1, false, true);
		return false;
	}

	private boolean errorSubmitter(int level, String ip, boolean sync) {

		log(level, "errorSubmitter " + ip + " " + sync);
		if (sync)
			synchIn(level + 1, true);
		if (checkSubmitter(level + 1, ip, false)) {
			AsyncJob job = mapIp2RunningJob.get(ip);
			job.setStatus(JOBSTATUS_ERROR);
			doneJob.put(job.getJobId(), job);
			mapIp2RunningJob.remove(ip);
			copyToS3(job.getJobId() + ".error");
			if (sync)
				synchOut(level + 1, false, true);
			return true;
		}
		if (sync)
			synchOut(level + 1, false, true);
		return false;
	}

	private boolean errorSubmitter(int level, String ip, String filename, String msg, boolean sync) {
		log(level, "errorSubmitter " + ip + " " + filename + " " + sync);

		if (sync)
			synchIn(level + 1, true);
		if (errorSubmitter(level + 1, ip, false)) {
			writeError(level + 1, filename, msg, sync);
			if (sync)
				synchOut(level + 1, false, true);
			return true;
		}
		if (sync)
			synchOut(level + 1, false, true);
		return false;
	}

	/*
	 * private void writeFile(String filename, String msg, boolean sync) {
	 * if(useS3()) { try { filename=filename.replace("/","");
	 * if(waitAndLock(filename)) { s3.putObject(new
	 * PutObjectRequest(getBucket(),filename,createSampleFile(msg)));
	 * unlock(filename); } } catch(Exception ex) { ex.printStackTrace(); } } else {
	 * while(true) { try { BufferedWriter bw=new BufferedWriter(new
	 * FileWriter(filename + ".error")); bw.append(msg); bw.close(); break; }
	 * catch(Exception ex) { ex.printStackTrace(); try { Thread.sleep(100); }
	 * catch(Exception ex2) { ex2.printStackTrace(); } } } }
	 * 
	 * }
	 */
	private void writeError(int level, String filename, String msg, boolean sync) {
		log(level, "writeError " + msg + " " + filename + " " + sync);
		while (true) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(filename + ".error"));
				bw.append(msg);
				bw.close();
				break;
			} catch (Exception ex) {
				ex.printStackTrace();
				try {
					Thread.sleep(100);
				} catch (Exception ex2) {
					ex2.printStackTrace();
				}
			}
		}

		if (useS3()) {
			try {
				filename = filename.replace("/error", ".error");
				String[] filenames = filename.split("\\/");
				if (filenames.length > 1) {
					filename = filenames[filenames.length - 1];
				}
				if (!filename.endsWith(".error"))
					filename = filename + ".error";

				/*
				 * if(filenames.length>1) { filename=filenames[filenames.length-1]+".error"; }
				 * else filename = filename.replace("/", "") + ".error";
				 */
				String fname = new File(filename).getName();
				s3.putObject(new PutObjectRequest(getBucket(), fname, createSampleFile(msg)));
				log(level, "writeError PutObjectRequest " + getBucket() + " " + fname + " ... success");
			} catch (Exception ex) {
				ex.printStackTrace();
				log(level, "writeError PutObjectRequest " + getBucket() + " " + filename + " ... failed");
			}
		}
	}

	private void writeStatus(int level, String filename, String msg, boolean sync) {
		log(level, "writeStatus " + msg + " " + filename + " " + sync);

		if (useS3()) {
			try {
				filename = filename.replace("/status", ".status");
				String[] filenames = filename.split("\\/");
				if (filenames.length > 1) {
					filename = filenames[filenames.length - 1];
				}
				if (!filename.endsWith(".status"))
					filename = filename + ".status";
				if (sync)
					waitAndLock(level + 1, filename, true);
				s3.putObject(new PutObjectRequest(getBucket(), filename, createSampleFile(msg)));
				if (sync)
					unlock(level + 1, filename);
				log(level, "PutObjectRequest " + getBucket() + " " + filename + " ... success");
			} catch (Exception ex) {
				ex.printStackTrace();
				log(level, "PutObjectRequest " + getBucket() + " " + filename + " ... failed");
			}
		} else {

			while (true) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
					bw.append(msg);
					bw.close();
					break;
				} catch (Exception ex) {
					ex.printStackTrace();
					try {
						Thread.sleep(500);
					} catch (Exception ex2) {
						ex2.printStackTrace();
					}
				}
			}

		}
	}

	// private void writeError(String filename, String msg, boolean sync) {
	//
	// if(useS3()) {
	// s3.putObject(new PutObjectRequest(getBucket(),filename),msg);
	//
	// } else {
	// while(true) {
	// try {
	// BufferedWriter bw=new BufferedWriter(new FileWriter(filename +
	// ".error"));
	// bw.append(msg);
	// bw.close();
	// break;
	// } catch(Exception ex) {
	// ex.printStackTrace();
	// try {
	// Thread.sleep(100);
	// } catch(Exception ex2) {
	// ex2.printStackTrace();
	// }
	// }
	// }
	// }
	// }

	private boolean startSubmitter(int level, String ip, boolean sync) {
		log(level, "startSubmitter " + ip + " " + sync);

		if (sync)
			synchIn(level + 1, true);
		if (checkSubmitter(level + 1, ip, false)) {
			AsyncJob job = mapIp2RunningJob.get(ip);
			job.setStatus(JOBSTATUS_STARTING);

			if (sync)
				synchOut(level + 1, false, true);
			return true;
		}
		if (sync)
			synchOut(level + 1, false, true);
		return false;
	}

	private AsyncJob getRunningJobById(int level, String jobid, boolean sync) {
		log(level, "getRunningJobById " + jobid + " " + sync);
		if (sync)
			synchIn(level + 1, false);

		Iterator<String> itIp = mapIp2RunningJob.keySet().iterator();
		String ipjob = null;
		while (itIp.hasNext()) {
			String ip = itIp.next();
			if (mapIp2RunningJob.get(ip).getJobId().equals(jobid)) {
				ipjob = ip;
				return mapIp2RunningJob.get(ip);
			}
		}
		return null;
	}

	private AsyncJob getRunningJobByIp(int level, String ip, boolean sync) {

		log(level, "getRunningJobByIp " + ip + " " + sync);
		if (sync)
			synchIn(level + 1, false);
		return mapIp2RunningJob.get(ip);
	}

	@Override
	public boolean downloadJob(String jobid) {

		log(0, "downloadJob " + jobid);
		/*
		 * Iterator<String> itIp=doneJob.keySet().iterator(); String ipjob=null;
		 * while(itIp.hasNext()) { String ip=itIp.next();
		 * if(doneJob.get(ip).getJobId().equals(jobid)) { ipjob=ip; break; } }
		 */
		synchIn(0, true);
		AsyncJob job = doneJob.get(jobid);
		if (job == null) {
			synchOut(0, false, true);
			return false;
		}

		job.setStatus(JOBSTATUS_DOWNLOADED);
		doneJob.remove(jobid);
		synchOut(0, false, true);

		synchInDownloaded(0, true);
		downloadedJob.add(job);
		synchOutDownloaded(0, false, true);

		return true;

		/*
		 * if(doneJob.containsKey(ipjob)) { AsyncJob job = doneJob.get(ipjob);
		 * job.setStatus(JOBSTATUS_DOWNLOADED); downloadedJob.add(job);
		 * doneJob.remove(ipjob); return true; } return false;
		 */
	}

	private String updateJobStatus(int level, String jobid, boolean sync) {
		log(level, "updateJobStatus " + jobid + " " + sync);
		if (sync)
			synchIn(level + 1, true);
		Iterator<AsyncJob> itjob = getAllJobs(level + 1, true, true, false, false, false).iterator();
		while (itjob.hasNext()) {
			AsyncJob job = itjob.next();
			if (job.getJobId().equals(jobid)) {
				return updateJobStatus(level + 1, job);
			}
		}
		itjob = downloadedJob.iterator();
		while (itjob.hasNext()) {
			AsyncJob job = itjob.next();
			if (job.getJobId().equals(jobid)) {
				return job.getStatus();
			}
		}
		if (sync)
			synchOut(level + 1, false, true);
		return JOBSTATUS_MISSING;
	}

	private String updateJobStatus(int level, AsyncJob job) {
		log(level, "updateJobStatus " + job.getJobId());
		String status = JOBSTATUS_MISSING;
		try {

			String filename = null;
			if (job.getJobId().startsWith("vcf2fasta"))
				filename = job.getJobId() + "/status";
			else
				filename = job.getJobId().replace(".tmp", "") + ".status";

			if (waitAndLock(level + 1, filename, false)) {
				BufferedReader br = getFileReader(level, filename, false);
				if (br == null) {
					status = JOBSTATUS_SUBMITTED;
				} else {
					status = br.readLine();
					br.close();
				}
				// unlock(level+1,filename);
				log(level, "getFileReader .. success.. status=" + status);
			}

			/*
			 * BufferedReader br=null; if(job.getJobId().startsWith("vcf2fasta")) br=new
			 * BufferedReader(new FileReader( AppContext.getTempDir() + job.getJobId() +
			 * "/status" )); else br=new BufferedReader(new FileReader(
			 * AppContext.getTempDir() + job.getJobId().replace(".tmp","") +".status" ));
			 * status=br.readLine(); br.close();
			 * 
			 * try { boolean haserror=false; if(job.getJobId().startsWith("vcf2fasta"))
			 * haserror=new File( AppContext.getTempDir() + job.getJobId() + "/error"
			 * ).exists(); else haserror=new File( AppContext.getTempDir() +
			 * job.getJobId().replace(".tmp","") +".error" ).exists(); if(haserror)
			 * status=JOBSTATUS_ERROR; } catch(Exception ex) {};
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
			// return ex.getMessage();
			status = JOBSTATUS_SUBMITTED; // "No running job " + job.getJobId();
			log(level, "getFileReader .. fauiled.. status=" + status);
		}
		job.setStatus(status);
		return status;
	}

	private List<AsyncJob> getAllJobs(int level, boolean active, boolean done, boolean downloaded, boolean synchin,
			boolean synchout) {

		log(level, "getAllJobs " + synchin + " " + synchout);

		if (synchin)
			synchIn(level + 1, synchout);
		List listJobs = new ArrayList();
		if (active || done) {
			Set<String> setjustFinishedJobs = new LinkedHashSet();
			// update jobs status
			Iterator<String> itIp = mapIp2RunningJob.keySet().iterator();
			while (itIp.hasNext()) {
				String ip = itIp.next();
				AsyncJob job = mapIp2RunningJob.get(ip);
				job.setStatus(updateJobStatus(level + 1, job));

				if (job.getStatus().equals(JOBSTATUS_DONE) || job.getStatus().equals(JOBSTATUS_ERROR)
						|| job.getStatus().equals(JOBSTATUS_CANCELLED)) {
					setjustFinishedJobs.add(ip);

					job.setTermination(job.getStatus());
				}
				/*
				 * if(job.getStatus().equals(JOBSTATUS_DONE) ||
				 * job.getStatus().equals(JOBSTATUS_ERROR) ) { setjustFinishedJobs.add(ip);
				 * job.setTermination(job.getStatus()); } else
				 * if(job.getStatus().equals(JOBSTATUS_CANCELLED)){ downloadedJob.add(job);
				 * mapIp2RunningJob.remove(ip); }
				 */
			}
			itIp = setjustFinishedJobs.iterator();
			while (itIp.hasNext()) {
				String ip = itIp.next();
				AsyncJob job = mapIp2RunningJob.get(ip);
				doneJob.put(job.getJobId(), job);
				mapIp2RunningJob.remove(ip);
			}
			if (active)
				listJobs.addAll(mapIp2RunningJob.values());
			if (done)
				listJobs.addAll(doneJob.values());
		}

		if (downloaded) {
			if (synchin) {
				synchInDownloaded(level + 1, synchout);
				listJobs.addAll(downloadedJob);
			}
		}

		if (synchout) {
			synchOut(level + 1, false, true);
			if (downloaded)
				synchOutDownloaded(level + 1, false, true);
		}

		return listJobs;
	}

	@Override
	public int getMapSession2AttsSize() {
		return AppContext.getMapSession2Atts().size();
	}

	@Override
	public int setCorePoolSize(int size) {
		executor = (AsyncJobExecutorConfig) AppContext.checkBean(executor, "AsyncJobExecutorConfig");
		ThreadPoolTaskExecutor ex = (ThreadPoolTaskExecutor) executor.getThreadPoolTaskExecutor();
		int ret = ex.getCorePoolSize();
		ex.setCorePoolSize(size);
		return ret;
	}

	@Override
	public int setMaxPoolSize(int size) {
		executor = (AsyncJobExecutorConfig) AppContext.checkBean(executor, "AsyncJobExecutorConfig");
		ThreadPoolTaskExecutor ex = (ThreadPoolTaskExecutor) executor.getThreadPoolTaskExecutor();
		int ret = ex.getMaxPoolSize();
		ex.setMaxPoolSize(size);
		return ret;
	}

	@Override
	public void setQueueCapacity(int size) {
		executor = (AsyncJobExecutorConfig) AppContext.checkBean(executor, "AsyncJobExecutorConfig");
		ThreadPoolTaskExecutor ex = (ThreadPoolTaskExecutor) executor.getThreadPoolTaskExecutor();
		ex.setQueueCapacity(size);
	}

	@Override
	public void setLogLevel(String string) {

		if (string == null)
			string = "info";
		AppContext.setLog_level(string);
	}

	@Override
	public void setLocalUser(boolean localuser) {
		this.LocalUser = localuser;

	}
}
