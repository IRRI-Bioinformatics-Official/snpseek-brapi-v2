package org.irri.iric.portal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.irri.iric.ds.chado.domain.Position;
import org.irri.iric.ds.chado.domain.StockSample;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.model.Organism;
import org.irri.iric.ds.utils.DbUtils;
import org.irri.iric.portal.galaxy.zkui.GalaxyCustomController;
import org.irri.portal.properties.ApplicationConstants;
import org.irri.portal.properties.ConfigConstants;
//import org.irri.iric.portal.galaxy.zkui.DefaultGalaxyController;
//import org.irri.iric.portal.galaxy.zkui.GalaxyCustomController;
//import org.python.util.PythonInterpreter;
import org.springframework.context.ApplicationContext;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listitem;

import user.ui.module.util.constants.UserConstants;

/**
 * This class provides application-wide access to the Spring ApplicationContext.
 * The ApplicationContext is injected by the class "ApplicationContextProvider".
 * 
 * Used to define global parameters, will be updated from *.xml file later
 * 
 */

public class AppContext {

	private static final Log log = LogFactory.getLog(AppContext.class);
	private static Logger logger = Logger.getLogger(AppContext.class.getName());

	private static ApplicationContext ctx;

	private static Connection jdbccon;
	private static Map mapVariant2Order = null;
	private static int RAMfactor = 1; // 5;

	public static String SEED_NO_ORDER = "You have not place any orders.";

	private static Map mapBearer2StartTime = new HashMap();

	public static enum RDMS {
		ORACLE, POSTGRES
	};

	public static enum WEBSERVER {
		LOCALHOST, SCIENCE_CLOUD, BRS, SCIENCE_CLOUD_1K1, TWENTY_K
	};

	public static enum OS {
		WINDOWS, LINUX
	};

	public static enum COMPILETYPE {
		PROD, DEV, TEST
	};

	/**
	 * random number generator
	 */
	private static final java.util.Random rand = new java.util.Random();
	public static DateFormat datef = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	public static DecimalFormat decf = new DecimalFormat("#.##");

	private static boolean isWS = false;

	// set target webserver and compile type

	// static WEBSERVER webserver = AppContext.WEBSERVER.AWS;
	static WEBSERVER webserver; // = AppContext.WEBSERVER.LOCALHOST;
	// static WEBSERVER webserver = AppContext.WEBSERVER.BEANSTALK;
	// static WEBSERVER webserver = AppContext.WEBSERVER.BEANSTALKDEV;
	// static WEBSERVER webserver = AppContext.WEBSERVER.ASTI;
	// static WEBSERVER webserver = AppContext.WEBSERVER.POLLUX;

	static COMPILETYPE compiletype = AppContext.COMPILETYPE.PROD;
	// static COMPILETYPE compiletype = AppContext.COMPILETYPE.TEST;
	// static COMPILETYPE compiletype = AppContext.COMPILETYPE.DEV;

	static OS operatingSytem;

	static RDMS rdms = AppContext.RDMS.POSTGRES;

	private static boolean bBypassViews = true;

	// private static boolean bBypassViews=false;
	// static RDMS rdms = AppContext.RDMS.ORACLE;

	private static String log_level = "debug";

	/**
	 * is using limited (uploaded for sharing) data Source code and data downloaded
	 * from Bitbucket repository should set this to true
	 */
	static boolean isSharedData = false;

	/**
	 * config from XML file has been loaded
	 */
	static boolean configloaded = false;

	/**
	 * using the Chado Schema?
	 */
	private static final boolean isChado = true;
	public static final String SNP = "SNP";
	public static final CharSequence EMAIL_IRRI = "irri.org";
	private static final Integer STRING_LENGTH = 20;
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * used for timing processes
	 */
	private static long startTime = 0;
	private static long startTimeDate = 0;

	static String defaultorganism;
	static String flatfilesdir;
	static String hostdirectory;
	static String tempdir;
	static String hostname;

	static String pathtolocalblast;
	static String pathtolocalblastdata;
	static String webappdirectory;

	public static Pattern patRemoveZeroes = Pattern.compile("\\.?0*");
	public static Pattern patStartNumber = Pattern.compile("^\\d+?");

	private static Map<String, BigDecimal> mapVarnames2Varid = null;

	private static String ec2tagname = null;

	private static String galaxyInstanceId = null;

	private static Properties configProp;

	private static Properties webProp;

	private static Properties anonymousContent;
	private static boolean dockerize;
	
	// private static AWSInstanceCountdown counter;
	// private static AWSTimer counter;

	static {
		try {

			
			configProp = new Properties();
			webProp = new Properties();
			anonymousContent = new Properties();

			InputStream is = AppContext.class.getResourceAsStream("/" + ConfigConstants.PROPERTYFILE);
			configProp.load(is);

			InputStream anon = AppContext.class
					.getResourceAsStream("/ACCESS_" + UserConstants.ANONYMOUS + ".properties");
			InputStream isWebProp = AppContext.class
					.getResourceAsStream("/" + configProp.getProperty(ConfigConstants.WEBSERVER) + ".properties");

			webserver = WEBSERVER.valueOf(configProp.get(ConfigConstants.WEBSERVER).toString().toUpperCase());
			operatingSytem = OS.valueOf(configProp.get(ConfigConstants.OPERATING_SYSTEM).toString().toUpperCase());

			if (configProp.get(ConfigConstants.DOCKERIZE).toString().equals("true"))
				dockerize = true;
			else
				dockerize = false;

			webProp.load(isWebProp);
			anonymousContent.load(anon);

			DbUtils.setWebProp(webProp);

//			galaxyInstanceId = "i-043b2e725d07ce079";
//			// galaxyInstanceId = "i-0c620d6cf53fe8afe";
//
//			counter = new AWSTimer(galaxyInstanceId);
//			counter.start();

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public AppContext() {
		super();
		InputStream is = AppContext.class.getResourceAsStream("/" + ConfigConstants.PROPERTYFILE);

		try {
			configProp.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		webserver = (WEBSERVER) configProp.get(ConfigConstants.WEBSERVER);
	}

	// ******************************** DEPLOYMENT-SPECIFIC SETTINGS (change
	// these to match server congiguration ) *********************************

	public static void logQuery(String query) {

		long endTime = System.currentTimeMillis();
		long endTimeDate = new Date().getTime();
		logger.info("TIME " + datef.format(endTime) + " : TIMER: " + (endTime - startTime) + " ms (system),  "
				+ (endTimeDate - startTimeDate) + " ms (date) : " + query);
		startTime = endTime;
		startTimeDate = endTimeDate;
	}

	public static boolean isPostgres() {
		// return rdms==AppContext.RDMS.POSTGRES;
		return true;
	}
	
	public static String getAppVersion() {
		return configProp.get(ConfigConstants.VERSION).toString();
	}
	
	public static String getGooglePropertyId() {
		return webProp.get(ApplicationConstants.GOOGLE_PROPERTY_ID).toString();
	}
	
	public static String getGoogleAnalyticsId() {
		return webProp.get(ApplicationConstants.GOOGLE_ANALYTICS).toString();
	}
	
	public static String getCookiePath() {
		return webProp.get(ApplicationConstants.COOKIE_PATH).toString();
	}
	
	public static boolean isEnableGoogleAnalytics() {
		boolean enableGA = Boolean.parseBoolean(webProp.get(ApplicationConstants.GOOGLE_ANALYTICS_ENABLED).toString());
		return enableGA;
	}
	
	public static boolean isOracle() {
		// return rdms==AppContext.RDMS.ORACLE;
		return false;
	}
	
	public static boolean isEnableChatbot() {
	    return Boolean.parseBoolean(webProp.get(ApplicationConstants.CHATBOT_ENABLED).toString());
	}

	public static String getChatbotApiUrl() {
	    return webProp.get(ApplicationConstants.CHATBOT_SERVER_URL).toString();
	}

	
	public static String getDefaultSchema() {
		if (isPostgres())
			return "public";
		if (isOracle())
			return "iric";
		return "";
	}

	/**
	 * Directory to write temp files accessible to the internet but using server
	 * file system ex. /path/to/tomcat/webapps/temp
	 * 
	 * @return
	 */

	public static boolean isWS() {
		return isWS;
	}

	public static boolean isDockerize() {
		return dockerize;
	}

	public static String getTempFolder() {
		if (isWindows())
			return "temp\\";
		return "temp/";
	}

	public static String getTomcatWebappsDir() {

		logger.info("TOMCAT DIRECTORY: " + webProp.getProperty(ApplicationConstants.TOMCAT_SERVER));

		String tomcatWebDir = webProp.getProperty(ApplicationConstants.TOMCAT_SERVER);

		/*
		 * if (isPollux()) return "/usr/share/apache-tomcat-7.0.42/webapps/"; else if
		 * (isAWS() || isAWSdev()) return "/usr/share/apache-tomcat-7.0.55/webapps/";
		 * else if (isASTI()) return "/srv/webapps/"; else if (isVMIRRI()) return
		 * "/opt/tomcat7/webapps/"; else if (isLocalhost()) return
		 * "E:/apache-tomcat-8.0.36-windows-x64/apache-tomcat-8.0.36/webapps/"; else if
		 * (isAWSBeanstalk() || isAWSBeanstalkDev()) return "/var/lib/tomcat8/webapps/";
		 */
		if (!tomcatWebDir.equals("."))
			return tomcatWebDir;
		return "";
	
	
	}

	public static String getRecaptcha() {

		logger.info("Recaptcah: " + webProp.getProperty(ApplicationConstants.RECAPTCHA));

		String recaptcha = webProp.getProperty(ApplicationConstants.RECAPTCHA);

		if (!recaptcha.equals("."))
			return recaptcha;
		return "";
	}

	public static String getTempDir() {

		/*
		 * if(isPollux()) return "/home/lmansueto/tomcattemp/"; else
		 * 
		 * if( isAWSBeanstalk()) return "/IRCstorage/temp/";
		 */

		if (tempdir != null)
			return tempdir;

		return getTomcatWebappsDir() + File.separator + getTempFolder();
	}

	// in server file system (where tomcat is deployed)
	public static String getHaploscriptsDir() {
		if (isScienceCloud() || isBRS() || isLocalhost())
			return getFlatfilesDir() + "haplo/";
		else
			return getTomcatWebappsDir() + getHostDirectory() + "/haplo/";
	}

	/**
	 * directory of SNP-Seek data files in the server (using server file system)
	 * 
	 * @return
	 */
	public static String getFlatfilesDir() {
		logger.info("FILE DIRECTORY FOR SNPSEEK DATA: " + webProp.getProperty(ApplicationConstants.FLATFILE_DIR));

		if (flatfilesdir != null)
			return flatfilesdir;

		String flatFiledir = webProp.getProperty(ApplicationConstants.FLATFILE_DIR);

		/*
		 * if (isPollux()) return "/home/lmansueto/iric-portal-files/"; else if
		 * (isLocalhost()) return "E:/My Document/Transfer/3kcore_alleles/"; else if
		 * (isAWS() || isAWSdev()) return "/data/lmansueto/iric-portal-files/"; else if
		 * (isASTI()) return "/srv/iric-portal-files/"; else if (isAWSBeanstalk() ||
		 * isAWSBeanstalkDev()) return "/IRCstorage/snpseekdata/current/";
		 */

		if (!flatFiledir.equals("."))
			return flatFiledir;
		return "";
	}

	public static String getVarietySet() {
		return anonymousContent.getProperty(ContentPropertyConstants.VARIETYSET);
	}

	public static String getContentFeature(String contentfeature) {
		return anonymousContent.getProperty(contentfeature);
	}

	public static String getWebclientPath() {

		return getFlatfilesDir() + "snpseek-webclient-log.jar";
	}

	public static String setHostname(String hd) {
		String old = hostname;
		hostname = hd;
		return old;
	}

	public static String setHostDirectory(String hd) {
		String old = hostdirectory;
		hostdirectory = hd;
		return old;
	}

	/**
	 * get host name (for use in embedded wepapps like JBrowse), or webserver IP
	 * address
	 * 
	 * @return
	 */
	public static String getHostname() {
		logger.info("HOST NAME FOR EMBEDDED WEBAPPS: " + webProp.getProperty(ApplicationConstants.HOST));
		if (hostname != null)
			return hostname;

		String hostname = webProp.getProperty(ApplicationConstants.HOST);

		/*
		 * if (isPollux()) return "http://172.29.4.215:8080"; else if (isAWS()) return
		 * "http://snp-seek.irri.org"; else if (isAWSdev()) return
		 * "http://54.255.100.88"; else if (isVMIRRI()) return
		 * "http://202.123.56.26:8080"; else if (isASTI()) return
		 * "http://202.90.159.218"; else if (isLocalhost()) return
		 * "http://localhost:8080"; else if (isAWSBeanstalkDev()) return
		 * "http://prodsnpseek01-env-test.ap-southeast-1.elasticbeanstalk.com"; else if
		 * (isAWSBeanstalk()) return "http://snp-seek.irri.org";
		 */
		if (!hostname.equals("."))
			return hostname;

		return "";
	}

	/**
	 * directory of webapp folder in host
	 * 
	 * @return
	 */
	public static String getHostDirectory() {
		logger.info("WEB APP FORLDER IN HOST: " + webProp.getProperty(ApplicationConstants.HOST_DIR));
		if (hostdirectory != null)
			return hostdirectory;

		String hostDir = webProp.getProperty(ApplicationConstants.HOST_DIR);

		/*
		 * if (isWS()) return "iric-ws"; if (isDev()) { return "iric-portal-dev"; } else
		 * if (isTest()) return "iric-portal-test"; else if (isPollux()) return
		 * "iric-portal"; else if (isLocalhost()) return "iric_portal"; else if (isAWS()
		 * || isAWSBeanstalk() || isAWSBeanstalkDev()) return "";
		 */
		if (!hostDir.equals("."))
			return hostDir;

		return "";
	}

	/**
	 * JBrowse host directory
	 * 
	 * @return
	 */
	public static String getJbrowseDir() {
		logger.info("JBROWSE HOST DIR: " + webProp.getProperty(ApplicationConstants.JBROWSE_DIR));

		String jbrowseDir = webProp.getProperty(ApplicationConstants.JBROWSE_DIR);

		if (!jbrowseDir.equals("."))
			return jbrowseDir;

		return AppContext.getHostname() +"/jbrowse";

	}

	/**
	 * JBrowse host directory
	 * 
	 * @return
	 */
	public static String getJbrowse2() {
		logger.info("JBROWSE2 HOST DIR: " + webProp.getProperty(ApplicationConstants.JBROWSE2_DIR));

		String jbrowseDir = webProp.getProperty(ApplicationConstants.JBROWSE2_DIR);

		if (!jbrowseDir.equals("."))
			jbrowseDir = "jbrowse2";

		return AppContext.getHostname() + "."+ jbrowseDir;

	}

	/**
	 * JBrowse host directory
	 * 
	 * @return
	 */
	public static String getLegacyUrl() {
		logger.info("Legacy URL: " + webProp.getProperty(ApplicationConstants.LEGACY_URL));

		String legacyURL = webProp.getProperty(ApplicationConstants.LEGACY_URL);

		if (legacyURL.equals("."))
			return "";

		return legacyURL;

	}

	public static String getEmbededDir(String app) {
		logger.info("EMBEDDED DIR" + webProp.getProperty(ApplicationConstants.EMBEDDED_SERVER));

		String embbededDir = webProp.getProperty(ApplicationConstants.EMBEDDED_SERVER);

		if (app.equals("jbrowse"))
			return getJbrowseDir();
		if (app.equals("ideogram"))
			return getTraitGeneIdeogramUrl();

		if (!embbededDir.equals("."))
			return embbededDir;
		return AppContext.getHostname() + "/" + "jbrowse-dev2";

		// if (isLocalhost()) {
		// return "http://172.29.4.215:8080/jbrowse-dev2";
		// } else {
		// if (isASTI())
		// return AppContext.getHostname() + "/" + "jbrowse";
		// else if (isPollux())
		// return AppContext.getHostname() + "/" + "jbrowse-dev2";
		// else if (isUsingsharedData() || isAWSBeanstalk() || isAWSBeanstalkDev())
		// return AppContext.getHostname() + "/" + "jbrowse";
		// else
		// return AppContext.getHostname() + "/" + "jbrowse-dev2";
		// }

	}

	private static String getTraitGeneIdeogramUrl() {
		logger.info("Ideogram HOST DIR: " + webProp.getProperty(ApplicationConstants.TRAIT_IDEOGRAM_DIR));

		String riceIdeogram = webProp.getProperty(ApplicationConstants.TRAIT_IDEOGRAM_DIR);

		if (!riceIdeogram.equals("."))
			riceIdeogram = "rice-ideogram";

		return AppContext.getHostname() +"/"+ riceIdeogram;
	}

	/**
	 * Path to BLAST program
	 * 
	 * @return
	 */
	public static String getPathToLocalBlast() {
		logger.info("PATH TO LOCAL BLAST PROGRAM: " + webProp.getProperty(ApplicationConstants.PATH_LOCAL_BLAST));
		if (pathtolocalblast != null)
			return pathtolocalblast;

		String path = webProp.getProperty(ApplicationConstants.PATH_LOCAL_BLAST);

		/*
		 * if (isAWS() || isAWSdev()) return "/home/ubuntu/lmansueto/ncbi-blast/bin/";
		 * else if (isPollux()) return "/home/lmansueto/ncbi-blast/bin/"; else if
		 * (isASTI()) return "/srv/ncbi-blast/bin"; else if (isAWSBeanstalk() ||
		 * isAWSBeanstalkDev()) return "/IRCstorage/blast/current/bin/";
		 */
		if (!path.equals("."))
			return path;

		return null;
	}

	/**
	 * Path to BLAST database
	 * 
	 * @return
	 */
	public static String getPathToLocalBlastData() {
		logger.info(
				"PATH TO LOCAL BLAST DATA: " + webProp.getProperty(ApplicationConstants.PATH_LOCAL_BLAST_DATA));

		if (pathtolocalblastdata != null)
			return pathtolocalblastdata;

		String pathBlastData = webProp.getProperty(ApplicationConstants.PATH_LOCAL_BLAST_DATA);

		// if (isAWS() || isAWSdev())
		// return "/home/ubuntu/lmansueto/ncbi-blast/iric-portal/";
		// else if (isPollux())
		// return "/home/lmansueto/ncbi-blast/iric-portal/";
		// else if (isASTI())
		// return "/srv/ncbi-blast/iric-portal/";
		// else if (isAWSBeanstalk() || isAWSBeanstalkDev())
		// return "/IRCstorage/blastdb/iric-portal/";

		if (!pathBlastData.equals("."))
			return pathBlastData;

		return null;
	}

	/**
	 * Path to BLAST server
	 * 
	 * @return
	 */
	public static String getBlastServer() {
		logger.info("BLAST SERVER: " + webProp.getProperty(ApplicationConstants.BLAST_SERVER));
		String blastServer = webProp.getProperty(ApplicationConstants.BLAST_SERVER);

		// if (isLocalhost() || isPollux())
		// return "http://pollux:8080/iric-portal-dev";
		// else if (isASTI())
		// return getHostname() + "/iric-portal";
		// else if (isAWS()) {
		// return "http://oryzasnp.org/iric-portal";
		// }

		if (!blastServer.equals("."))
			return blastServer;

		return "http://oryzasnp.org/iric-portal";
	}

	/**
	 * Path to VCF2Fasta converter program
	 * 
	 * @return
	 */
	public static String getPathToVCF2FastaGenerator() {
		return getFlatfilesDir() + "getvcfseq/getSeqVCFaws.pl";

	}

	public static String getPathToR() {
		logger.info("PATH TO R: " + webProp.getProperty(ApplicationConstants.PATH_R));

		String pathToR = webProp.getProperty(ApplicationConstants.PATH_R);

		System.out.println("PATH:" + pathToR.toUpperCase());
		if (pathToR.equals("."))
			return "/usr/bin/Rscript";
		return pathToR;

	}

	private static String galaxy_instance;
	private static GalaxyCustomController galaxy_controller;

//	private static GalaxyCustomController galaxy_controller;
//
	public static void setGalaxyInstance(String i, GalaxyCustomController c) {
		galaxy_instance = i;
		galaxy_controller = c;
	}

	public static String getGalaxyInstance() {
		return galaxy_instance;
	}

	public static String getGalaxyAddress() {
//		// fix
//		if (galaxy_instance == null) {
//			GalaxyCustomController customcontroller = new DefaultGalaxyController();
//			List l = customcontroller.getGalaxyInstances();
//			setGalaxyInstance((String) l.get(0), customcontroller);
//		}
//		//
//		return galaxy_controller.getGalaxyAddress(galaxy_instance);
		return null;
	}

//
	public static String getGalaxyKey() {
//		return galaxy_controller.getGalaxyKey(galaxy_instance);
		return null;
	}

//
//	public static String GALAXY_AWS_DEV="snpseek_dev";
//	public static String GALAXY_AWS="snpseek";
//	public static String GALAXY_POLLUX="pollux";
//	public static String GALAXY_RICEDEV="rice_dev";
//	public static String GALAXY_RICE="rice";
//	private static String galaxy_instance=GALAXY_AWS;
//	
	public static void setGalaxyInstance(String i) {
		galaxy_instance = i;
	}
//	public static String getGalaxyInstance() {
//		return galaxy_instance;
//	}
//
//	
//	public static String getGalaxyHistoryViewLink(String historyid) {
//		
//		if(getGalaxyInstance().equals(GALAXY_RICEDEV))
//			return AppContext.getGalaxyAddress() + "/history/view/" + historyid;
//		else if(getGalaxyInstance().equals(GALAXY_POLLUX))
//			return AppContext.getGalaxyAddress() + "/histories/view?id=" + historyid;
//		else if(getGalaxyInstance().equals(GALAXY_RICE))
//			return AppContext.getGalaxyAddress() + "/history/view/" + historyid;
//		else
//			return AppContext.getGalaxyAddress() + "/histories/view?id=" + historyid;
//	}
//
//
//	public static String getGalaxyAddress() {
//		
//		if(getGalaxyInstance().equals(GALAXY_RICEDEV))
//			return "http://13.229.124.30:8080";
//		else if(getGalaxyInstance().equals(GALAXY_POLLUX))
//			return "http://172.29.4.215:8080";
//		else if(getGalaxyInstance().equals(GALAXY_RICE))
//			return "http://13.250.174.27:8080";
//		else
//			return "http://13.229.217.149:8080"; 
//	}
//
//	public static String getGalaxyKey() {
//		if(getGalaxyInstance().equals(GALAXY_RICEDEV))
//			return "a80ef55feed828cdbe6500b2ba4f8bf7";
//		else if(getGalaxyInstance().equals(GALAXY_POLLUX))
//			return "0529d21031f8e190dc2ba26173627b92"; 
//		else if(getGalaxyInstance().equals(GALAXY_RICE))
//			return  "053435438eeca0c4393ad8ebf66be404"; 
//		else if (getGalaxyInstance().equals(GALAXY_AWS_DEV))
//			// return "dd7ecdf0096f104c0e3ac8fd7f8f8136";  // dev, with ts , admin
//			return "13d14be50c14abdfd035731d1d2f10db"; // ts
//		else
//			return "6a3612b1923b952e7d749b51eb5e0175";  // user snpseek
//	}

	public static String getPlinkDir() {
		return getFlatfilesDir() + "/plink/";
	}

	// ***************************************** DATA-RELATED CONIGURATION
	// *****************************************

	/**
	 * prefix of DB Views to use (legacy or iric)
	 * 
	 * @return
	 */
	public static String getViewPrefix() {
		if (isChado)
			return "V";
		else
			return "VL";
	}

	/**
	 * Ignore heterozygous Indels
	 * 
	 * @return
	 */
	public static boolean isIgnoreHeteroIndels() {
		return false;
	}

	/**
	 * Using only downloaded shared data. Will disable some functions that use the
	 * huge data.
	 * 
	 * @return
	 */
	public static boolean isUsingsharedData() {
		return isSharedData;
	}

	/**
	 * SNP data set to use
	 * 
	 * @return
	 */
	public static String getSNPSet() {
		// return SnpsAllvarsPosDAO.DATASET_SNPINDELV2;
		// return SnpsAllvarsPosDAO.DATASET_SNPINDELV1;
		// return "SNP v2 IUPAC"; //SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC;
		return null; // SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC;
	}

	/**
	 * Use DAO beans with given suffix
	 * 
	 * @return
	 */
	public static String getDAOBeanSuffix() {
		return suffixDAOBean;
	}

	// static String suffixDAOBean = "Postges";
	static String suffixDAOBean = "";
	private static Organism organism;

	/**
	 * Launch in AWS
	 * 
	 * @return
	 */
	public static boolean isScienceCloud() {
		return webserver == AppContext.WEBSERVER.SCIENCE_CLOUD;
	}

	public static boolean isBRS() {
		return webserver == AppContext.WEBSERVER.BRS;
	}

	/**
	 * localhost
	 * 
	 * @return
	 */
	public static boolean isLocalhost() {
		return webserver == AppContext.WEBSERVER.LOCALHOST;
	}

	/**
	 * is development version
	 */
	public static boolean isDev() {
		// return compiletype.equals(AppContext.COMPILETYPE.DEV);
		return compiletype == AppContext.COMPILETYPE.DEV;
	}

	/**
	 * is test version
	 * 
	 * @return
	 */
	public static boolean isTest() {
		// return compiletype.equals(AppContext.COMPILETYPE.TEST);
		return compiletype == AppContext.COMPILETYPE.TEST;
	}

	/**
	 * server is windows
	 * 
	 * @return
	 */
	public static boolean isWindows() {
		return operatingSytem == AppContext.OS.WINDOWS;
	}

	/**
	 * server in IRRI LAN
	 * 
	 * @return
	 */
	public static boolean isIRRILAN() {
		// return (isLocalhost() && !isUsingsharedData() ) || isPollux() ||
		// isVMIRRI();
		return isLocalhost() || isBRS();
	}

	/**
	 * generate a temporary file name
	 * 
	 * @return
	 */
	public static String createTempFilename() {

		return Long.toString(rand.nextLong()).replace("-", "");
	}

	/**
	 * start process timer
	 */
	public static void startTimer() {
		startTime = System.currentTimeMillis();
		startTimeDate = new Date().getTime();
	}

	/**
	 * default core/all SNP set
	 */
	public static boolean isCore() {
		return false;
	}

	/**
	 * Factor of region end-stop to include in gff
	 * 
	 * @return
	 */
	public static int getJBrowseMargin() {
		return 2;
	}

	/**
	 * write period since start/last reset and restart the timer
	 * 
	 * @param report
	 */
	public static void resetTimer(String report) {
		long endTime = System.currentTimeMillis();
		long endTimeDate = new Date().getTime();
		// debug( "TIMER: " + (endTime- startTime) + " ms (system), " + (
		// endTimeDate-startTimeDate) + " ms (date) : " + report);
		debug("TIMER: " + (endTime - startTime) + " ms (system),  " + (endTimeDate - startTimeDate) + " ms (date) : "
				+ report);
		startTime = endTime;
		startTimeDate = endTimeDate;
	}

	/**
	 * webapp directory
	 * 
	 * @return
	 */
	public static String getWebappdiectory() {

		// also check:
		// http://stackoverflow.com/questions/1521957/where-how-to-setup-configuration-resources-for-tomcat-war-files
		// http://archive.oreilly.com/pub/a/java/archive/tomcat-tips.html

		try {
			File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
			File propertyFile = new File(catalinaBase,
					"webapps/" + getHostDirectory() + "strsproperties/strs.properties");
			InputStream inputStream = new FileInputStream(propertyFile);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * Injected from the class "ApplicationContextProvider" which is automatically
	 * loaded during Spring-Initialization.
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		ctx = applicationContext;
	}

	/**
	 * Get access to the Spring ApplicationContext from everywhere in your
	 * Application.
	 *
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	/**
	 * Slice a large set into groups of 1000, (for oracle IN has limit 1000)
	 * 
	 * @param vars
	 * @return
	 */
	public static Set[] setSlicer(Set vars, int size) {
		int nSlices = vars.size() / size + 1;
		Set slicedsets[] = new LinkedHashSet[nSlices];
		for (int iset = 0; iset < nSlices; iset++) {
			slicedsets[iset] = new LinkedHashSet();
		}
		Iterator it = vars.iterator();

		int icount = 0;
		while (it.hasNext()) {
			slicedsets[icount / size].add(it.next());
			icount++;
		}
		return slicedsets;
	}

	public static Set[] setSlicer(Set vars) {
		if (vars.size() > 3000) {
			int nSlices = vars.size() / 1000 + 1;
			Set slicedsets[] = new LinkedHashSet[nSlices];
			for (int iset = 0; iset < nSlices; iset++) {
				slicedsets[iset] = new LinkedHashSet();
			}
			Iterator it = vars.iterator();

			int icount = 0;
			while (it.hasNext()) {
				slicedsets[icount / 1000].add(it.next());
				icount++;
			}
			return slicedsets;
		}

		if (vars.size() > 2000) {
			java.util.Set set1 = new HashSet();
			Iterator it = vars.iterator();
			for (int i = 0; i < 1000; i++)
				set1.add(it.next());
			java.util.Set set2 = new HashSet();
			for (int i = 0; i < 1000; i++)
				set2.add(it.next());
			java.util.Set set3 = new HashSet();
			while (it.hasNext())
				set3.add(it.next());

			return new Set[] { set1, set2, set3 };

		} else if (vars.size() > 1000) {
			java.util.Set set1 = new HashSet();
			Iterator it = vars.iterator();
			for (int i = 0; i < 1000; i++)
				set1.add(it.next());
			java.util.Set set2 = new HashSet();
			while (it.hasNext())
				set2.add(it.next());

			return new Set[] { set1, set2 };

		} else
			return new Set[] { vars };
	}

	public static List setSlicerIds(Set varIds) {
		List<String> listVaridSets = new ArrayList();
		if (varIds != null && !varIds.isEmpty()) {
			// create varids list
			Set[] sets = AppContext.setSlicer(varIds);

			for (int iset = 0; iset < sets.length; iset++) {

				StringBuffer buff = new StringBuffer();
				Iterator<BigDecimal> itSet = sets[iset].iterator();
				while (itSet.hasNext()) {
					buff.append(itSet.next());
					if (itSet.hasNext())
						buff.append(",");
				}
				listVaridSets.add(buff.toString());
			}

		}
		return listVaridSets;
	}

	public static List setStringSlicer(Set varIds, boolean isQuoted, boolean toUpper) {
		List<String> listVaridSets = new ArrayList();
		if (varIds != null && !varIds.isEmpty()) {
			// create varids list
			Set[] sets = AppContext.setSlicer(varIds);

			for (int iset = 0; iset < sets.length; iset++) {

				StringBuffer buff = new StringBuffer();
				Iterator<String> itSet = sets[iset].iterator();
				while (itSet.hasNext()) {
					String s = itSet.next();
					if (toUpper)
						s = s.toUpperCase();
					if (isQuoted)
						buff.append("'" + s + "'");
					else
						buff.append(s);
					if (itSet.hasNext())
						buff.append(",");
				}
				listVaridSets.add(buff.toString());
			}

		}
		return listVaridSets;
	}

	/**
	 * Convert collection to csv
	 * 
	 * @param col
	 * @return
	 */
	public static String toCSV(Collection col) {
		StringBuffer buff = new StringBuffer();
		Iterator itSet = col.iterator();
		while (itSet.hasNext()) {
			buff.append(itSet.next());
			if (itSet.hasNext())
				buff.append(",");
		}
		return buff.toString();

	}

	public static String toCSVquoted(Collection col, String quote) {
		StringBuffer buff = new StringBuffer();
		Iterator itSet = col.iterator();
		while (itSet.hasNext()) {

			buff.append(quote).append(itSet.next()).append(quote);
			if (itSet.hasNext())
				buff.append(",");
		}
		return buff.toString();

	}

	/**
	 * if #mismatch=#snps, dist=Inf. Use this value instead
	 * 
	 * @return
	 */
	public static int getMaxPhylodistance() {
		return 5;
	}

	/**
	 * Maximum region length in SNP Universe query
	 * 
	 * @return
	 */
	public static int getMaxlengthUni(String snpset) {
		/*
		 * if( (isDev()|| isTest()) && isLocalhost()) return 1000000; if(isLocalhost()
		 * || (isDev() && isAWS())) return 5000; else return 50000;
		 */
		// return 50000;
		int i = getMaxlengthUniDownload(snpset) / 100;
		AppContext.debug("getMaxlengthUni=" + i);
		return i;
	}

	public static int getMaxlengthUni(Set snpset) {
		/*
		 * if( (isDev()|| isTest()) && isLocalhost()) return 1000000; if(isLocalhost()
		 * || (isDev() && isAWS())) return 5000; else return 50000;
		 */
		// return 50000;
		int i = getMaxlengthUniDownload(snpset) / 100;
		AppContext.debug("getMaxlengthUni=" + i);
		return i;
	}

	public static long getMaxGenotypeElements() {
		return 5000 * 3024 * RAMfactor;
		// return 2000*3024*RAMfactor;
	}

	public static long getMaxGenotypeElementsDownload() {
		// return 20000*3024;
		return 1000000 * 50;
	}

	/*
	 * public static int getMaxlengthUniDownload() { return
	 * getMaxlengthUniDownload("snp_all"); }
	 */
	public static int getMaxlengthUniDownload(String snpset) {
		/*
		 * if(isIRRILAN()) return 50000000; else return 5000000;
		 */

		int i = 0;
		if (snpset.contains("all") || snpset.contains("base"))
			i = 5000000;
		else if (snpset.contains("filtered"))
			i = 10000000;
		else
			i = 50000000;

		// AppContext.debug("getMaxlengthUniDownload for " + snpset + " = " + i
		// );
		return i;
	}

	public static int getMaxlengthUniDownload(Set snpset) {
		/*
		 * if(isIRRILAN()) return 50000000; else return 5000000;
		 */

		int i = 0;
		if (snpset.contains("3kall") || snpset.contains("3kbase"))
			i = 5000000;
		else if (snpset.contains("3kfiltered"))
			i = 10000000;
		else
			i = 50000000;

		// AppContext.debug("getMaxlengthUniDownload for " + snpset + " = " + i
		// );
		return i;
	}

	public static int getMaxSNPListDownload(String snpset) {
		// return getMaxlengthUniDownload(snpset)/10;

		int i = getMaxlengthUniDownload(snpset) / 10;
		AppContext.debug("getMaxSNPListDownload=" + i);
		return i;

	}

	public static int getMaxLocusListDownload(String snpset) {
		int i = getMaxlengthUniDownload(snpset) / 5000;
		AppContext.debug("getMaxLocusListDownload=" + i);
		return i;
	}

	public static int getMaxSNPList(String snpset) {
		int i = getMaxlengthUni(snpset) / 10;
		AppContext.debug("getMaxSNPList=" + i);
		return i;
	}

	public static int getMaxLocusList(String snpset) {
		int i = getMaxlengthUni(snpset) / 5000;
		AppContext.debug("getMaxLocusList=" + i);
		return i;
	}

	public static int getMaxSNPListDownload(Set snpset) {
		// return getMaxlengthUniDownload(snpset)/10;

		int i = getMaxlengthUniDownload(snpset) / 10;
		AppContext.debug("getMaxSNPListDownload=" + i);
		return i;

	}

	public static int getMaxLocusListDownload(Set snpset) {
		int i = getMaxlengthUniDownload(snpset) / 5000;
		AppContext.debug("getMaxLocusListDownload=" + i);
		return i;
	}

	public static int getMaxSNPList(Set snpset) {
		int i = getMaxlengthUni(snpset) / 10;
		AppContext.debug("getMaxSNPList=" + i);
		return i;
	}

	public static int getMaxLocusList(Set snpset) {
		int i = getMaxlengthUni(snpset) / 5000;
		AppContext.debug("getMaxLocusList=" + i);
		return i;
	}

	public static int getMaxRows() {
		return 3024;
	}

	/**
	 * Maximum region length in Core SNPs query
	 * 
	 * @return
	 */
	// public static int getMaxlengthCore() {
	// //return getMaxlengthUni()*30;
	// //return getMaxlengthUni("snp_core")*20;
	// /*
	// if(isDev() || isTest())
	// return 10000000;
	// //return 2000000;
	// return 1000000;
	// */
	// int i= getMaxlengthUni("snp_core")*20;
	// AppContext.debug("getMaxlengthCore="+ i);
	// return i;
	// }
	//
	// public static int getMaxlengthHdra() {
	// //return getMaxlengthCore();
	// return getMaxlengthCore();
	// }
	//
	// public static int getMaxlengthCoreDownload() {
	// //return 10000000;
	//
	// //return getMaxlengthUniDownload()*30;
	// //return Integer.MAX_VALUE; // getMaxlengthUniDownload()*30;
	// int i= getMaxlengthUniDownload("snp_core");
	// AppContext.debug("getMaxlengthCoreDownload="+ i);
	// return i;
	// }
	//
	// public static int getMaxSNPListCoreDownload() {
	// int i= getMaxSNPListDownload("snp_core");
	// AppContext.debug("getMaxSNPListCoreDownload="+ i);
	// return i;
	// }
	// public static int getMaxSNPListCore() {
	// int i= getMaxSNPList("snp_core");
	// AppContext.debug("getMaxSNPListCore="+ i);
	// return i;
	// }
	//
	// public static int getMaxLocusListCoreDownload() {
	// int i= getMaxlengthCoreDownload()/5000;
	// AppContext.debug("getMaxLocusListCoreDownload="+ i);
	// return i;
	// }
	// public static int getMaxLocusListCore() {
	// int i= getMaxlengthCore()/5000;
	// AppContext.debug("getMaxLocusListCore="+ i);
	// return i;
	// }
	//
	//
	// public static int getMaxlengthHdraDownload() {
	// return getMaxlengthCoreDownload();
	// }
	//
	// public static int getMaxSNPListHdraDownload() {
	// return getMaxSNPListCoreDownload();
	//
	// }
	// public static int getMaxSNPListHdra() {
	// return getMaxSNPListCore();
	// }
	//
	// public static int getMaxLocusListHdraDownload() {
	// return getMaxLocusListCoreDownload();
	// }
	// public static int getMaxLocusListHdra() {
	// return getMaxLocusListCore();
	// }

	/**
	 * Maximum region length in variety comparison SNPs query
	 * 
	 * @return
	 */
	public static int getMaxlengthPairwise() {
		return Integer.MAX_VALUE;
	}

//	public static void setDefaultOrganism(Organism org) {
//		setOrganism = org.getCommonName();
//		organism = org;
//		AppContext.mydebug("default organism set to " + setOrganism);
//
//	}

	public static String REFERENCE_NIPPONBARE() {
		return "Japonica nipponbare";
	}

	public static BigDecimal REFERENCE_NIPPONBARE_ID() {
		return BigDecimal.valueOf(9);
	}

	/**
	 * Default organism
	 * 
	 * @return
	 */
	public static String getDefaultOrganism() {

		logger.info("TOMCAT DIRECTORY: " + webProp.getProperty(ApplicationConstants.TOMCAT_SERVER));

		defaultorganism = webProp.getProperty(ApplicationConstants.DEFAULT_ORGANISM_NAME);

		if (defaultorganism == null || defaultorganism.equals("."))
			return AppContext.JAPONICA_NIPPONBARE;

		return defaultorganism;

		// return "rice";
		// return "Japonica nipponbare";
		// return "sorghum bicolor";
		// return "Japonica Nipponbare";
		// return "Cassava";
	}

//	public static Integer getDefaultOrganismId() {
//		if (setOrganism != null) {
//			return organism.getOrganismId().intValue();
//		}
//		try {
//			return Integer.parseInt(webProp.get(WebserverPropertyConstants.DEFAULT_ORGANISM_ID).toString());
//		} catch (NumberFormatException ex) {
//			return 9;
//		}
//
//	}

	public static String getDefaultDataset() {
		return webProp.get(ApplicationConstants.DEFAULT_DATASET).toString(); // VarietyFacade.DATASET_SNPINDELV2_IUPAC;
		// return "refset" ; //VarietyFacade.DATASET_DEFAULT;

	}

	public static int chr2srcfeatureidOffset() {
		try {
			return Integer
					.parseInt(webProp.get(ApplicationConstants.DEFAULT_CHR2SRC_FEATURE_ID_OFFSET).toString());
		} catch (NumberFormatException ex) {
			return 2;
		}

		// return 3312; //0;
	}

	public static String getDefaultVariantset() {
		return webProp.get(ApplicationConstants.DEFAULT_VARIANT_SET).toString();
	}

	/**
	 * Message logger used by the webapp, for easy maintenance and change of loggers
	 * 
	 * @param msg
	 */
	public static void logger(StringBuffer msg) {
		logger(msg.toString());
	}

	public static void logger(String msg) {
		// logger.info(msg);
		// logger.info(msg);
		// log.debug(msg);
		log.error(msg);
	}

	// display on expected error
	public static void error(String msg) {
		// logger(msg);
		if (getLogLevel().equals("debug")) {
			long endTime = System.currentTimeMillis();
			long endTimeDate = new Date().getTime();
			// debug( "TIMER: " + (endTime- startTime) + " ms (system), " + (
			// endTimeDate-startTimeDate) + " ms (date) : " + report);
			String timedif = "ERROR TIMER: " + (endTime - startTime) + " ms (system),  " + (endTimeDate - startTimeDate)
					+ " ms (date) : ";
			startTime = endTime;
			startTimeDate = endTimeDate;
			// log.error(timedif + msg);
			logger.info(timedif + msg);
		}
		log.error(msg);
	}

	// display on expected warning
	public static void warning(String msg) {
		// logger(msg);
		log.warn(msg);

	}

	// display in debug mode
	public static void mydebug(String msg) {
		// logger.info(msg);
		System.out.println(msg);
	}

	// display in debug mode
	public static void debug(String msg) {
		// logger(msg);
		/*
		 * if(AppContext.isIRRILAN()) log.error(msg);
		 */
		/*
		 * if (getLogLevel().equals("debug")) { long endTime =
		 * System.currentTimeMillis(); long endTimeDate = new Date().getTime(); //
		 * debug( "TIMER: " + (endTime- startTime) + " ms (system), " + ( //
		 * endTimeDate-startTimeDate) + " ms (date) : " + report); String timedif =
		 * "DEBUG TIMER: " + (endTime - startTime) + " ms (system),  " + (endTimeDate -
		 * startTimeDate) + " ms (date) : "; startTime = endTime; startTimeDate =
		 * endTimeDate; // log.error(timedif + msg);
		 * 
		 * logger.info(timedif + msg); //System.out.println("TIME " + endTimeDate + "  "
		 * + timedif); if (msg == null || msg.replaceAll("\\s+", "").isEmpty()) {
		 * logger.info("empty/null msg"); } }
		 */

		// logger.info(msg);
		System.out.println(msg);
	}

	// display in debug mode
	public static void debugIterate(String msg) {
		// if(isLocalhost()) logger(msg);
	}

	// display in production mode
	public static void info(String msg) {

		if (getLogLevel().equals("info") || getLogLevel().equals("debug")) {
			long endTime = System.currentTimeMillis();
			long endTimeDate = new Date().getTime();
			// debug( "TIMER: " + (endTime- startTime) + " ms (system), " + (
			// endTimeDate-startTimeDate) + " ms (date) : " + report);
			String timedif = "INFO TIMER: " + (endTime - startTime) + " ms (system),  " + (endTimeDate - startTimeDate)
					+ " ms (date) : ";
			startTime = endTime;
			startTimeDate = endTimeDate;
			// logger.info(timedif + msg);
			logger.info(timedif + msg);
		}

		/*
		 * if(AppContext.isIRRILAN()) logger.info(msg);
		 */
	}

	/**
	 * Display the elements in a collection
	 * 
	 * @param name
	 * @param col
	 */
	public static void displayCollection(String name, Collection col) {
		StringBuffer buff = new StringBuffer();
		buff.append(name + ": ");
		col = new TreeSet(col);
		Iterator it = col.iterator();
		while (it.hasNext()) {
			buff.append(it.next());
			if (it.hasNext())
				buff.append(",");
		}
		logger(buff);
	}

	/**
	 * Guess the chromosome number from contig name
	 * 
	 * @param chr
	 * @return
	 */
	public static String guessChrFromString(String chr) {
		return chr.toUpperCase().replace("CHR0", "").replace("CHR", "");
	}

	public static String guessSrcfeataureidFromString(String chr) {
		return Integer.toString(AppContext.chr2srcfeatureidOffset()
				+ Integer.valueOf(chr.toUpperCase().replace("CHR0", "").replace("CHR", "")));
	}

	public static boolean isRice() {
		return getDefaultOrganism().equals("Japonica nipponbare");
	}

	public static boolean showGenotypeTrack() {
		return false; // isRice();
	}

	/**
	 * Generate list of uppercase, lowercase, blanks for use in UI listboxes
	 * 
	 * @param col        initial list
	 * @param upperlower create uppercase and lower case
	 * @param addBlank   add blank in first line
	 * @return
	 */
	public static List createUniqueUpperLowerStrings(Collection col, boolean upperlower, boolean addBlank) {

		List newlist = new ArrayList();
		if (addBlank)
			newlist.add("");
		Iterator<String> it = col.iterator();
		while (it.hasNext()) {
			String item = it.next();
			if (item == null)
				continue;

			if (upperlower) {
				if (patStartNumber.matcher(item).find()) {
					newlist.add(item);
				} else {
					newlist.add(item.toLowerCase());
					newlist.add(item.toUpperCase());
				}
			} else {
				newlist.add(item);
			}
		}
		return newlist;
	}

	/**
	 * Replace every occurrence of mapReplace.key in instr with mapReplace.value
	 * 
	 * @param instr
	 * @param mapReplace
	 * @return
	 */
	public static String replaceString(String instr, Map<String, String> mapReplace) {
		Iterator<String> itRep = mapReplace.keySet().iterator();
		while (itRep.hasNext()) {
			String rep = itRep.next();
			instr = instr.replaceAll(rep, mapReplace.get(rep));
		}
		return instr;
	}

	/**
	 * Convert SQL CLOB field to string
	 * 
	 * @param clb
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String clobStringConversion(Clob clb) throws IOException, SQLException {
		if (clb == null)
			return "";

		StringBuffer str = new StringBuffer();
		String strng;
		BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());
		while ((strng = bufferRead.readLine()) != null)
			str.append(strng);
		return str.toString();
	}

	/**
	 * Log the webserver status
	 */
	public static void logSystemStatus() {
		info(getSystemStatus());
	}

	public static String getSystemStatus() {

		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

		sb.append("SystemStatus: free memory kb: " + format.format(freeMemory / 1024) + ";");
		sb.append("allocated memory kb: " + format.format(allocatedMemory / 1024) + ";");
		sb.append("max memory kb: " + format.format(maxMemory / 1024) + ";");
		sb.append("total free memory kb: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + ";");
		sb.append("system load average: " + osBean.getSystemLoadAverage() + ";");
		sb.append("avail processors: " + osBean.getAvailableProcessors() + ";");
		sb.append("active threads: " + Thread.activeCount() + ";");

		org.zkoss.zk.ui.util.Statistic zkmonitor = new org.zkoss.zk.ui.util.Statistic();
		sb.append("active desktops: " + zkmonitor.getActiveDesktopCount() + ";");
		sb.append("active sessions: " + zkmonitor.getActiveSessionCount() + ";");
		sb.append("active update: " + zkmonitor.getActiveUpdateCount() + ";");
		// sb.append("instance: " + AppContext.getInstance() + "\n");

		return (sb.toString());

	}

	/**
	 * Utility to check if object is null (if autowired worked!) if null, use
	 * SpringUtil.getBean(name); then
	 * AppContext.getApplicationContext().getBean(name); then throw exception !
	 * 
	 * @param obj  object to check
	 * @param name bean name
	 * @return
	 */
	public static Object checkBean(Object obj, String name) {
		if (name.endsWith("DAO"))
			name = name + suffixDAOBean;

		if (obj == null) {
			log.debug(name + "==null using static");
			debug(name + "==null using getApplicationContext");
			obj = AppContext.getApplicationContext().getBean(name);
			
		}
		if (obj == null) {
			log.debug(name + "==null using SpringUtil");
			debug(name + "==null using SpringUtil");
			obj = SpringUtil.getBean(name);
		}

		if (obj == null)
			throw new RuntimeException(name + "==null");

		/*
		 * if (obj==null) { logger.info(name + "==null using new!"); log.debug(name +
		 * "==null using new!"); Class<?> c; try { c = Class.forName(name);
		 * Constructor<?> cons; try { cons = c.getConstructor(); try { obj =
		 * cons.newInstance(); } catch (InstantiationException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (IllegalAccessException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IllegalArgumentException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (InvocationTargetException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * } catch (NoSuchMethodException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (SecurityException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 * 
		 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 * 
		 * 
		 * if(obj==null) throw new RuntimeException(name + "==null");
		 * 
		 */

		return obj;
	}

	public static String getFastqcURL() {
		return "http://oryzasnp.org/3kfastqc/";
	}

	public static String getFastqURL() {
		return "http://www.ncbi.nlm.nih.gov/biosample/?term=";
	}

	public static String getBamURL(String boxcode) {
		return "https://s3.amazonaws.com/3kricegenome/Nipponbare/" + boxcode.trim().replace(" ", "_")
				+ ".realigned.bam";
	}

	public static String getVcfURL(String boxcode) {
		return "https://s3.amazonaws.com/3kricegenome/Nipponbare/" + boxcode.trim().replace(" ", "_") + ".snp.vcf.gz";
	}

	public static String getJbrowseContigSuffix() {
		return "";
	}

	/**
	 * Display array of strings
	 * 
	 * @param allelesstr
	 */
	public static void displayStringArray(String allelesstr[]) {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < allelesstr.length; i++)
			b.append(allelesstr[i]).append(", ");
		debug(b.toString());
	}

	/**
	 * Default JBrowse tracks in genome browser
	 * 
	 * @return
	 */
	public static String getJBrowseDefaulttracks(String variantset) {
		Set ds = new HashSet();
		ds.add(variantset);
		return getJBrowseDefaulttracks(ds);
	}

	public static String getJBrowseDefaulttracks(Set variantset) {

		// if(dataset.equals(VarietyFacade.DATASET_SNPINDELV2_IUPAC) ||
		// dataset.equals(VarietyFacade.DATASET_SNP_GOPAL92) ||
		// dataset.equals(VarietyFacade.DATASET_SNP_3KGOPAL92))
		// return "DNA,msu7gff,msu7snpsv2,msu7indelsv2";
		// else
		/*
		 * if(dataset.equals(VarietyFacade.DATASET_SNP_HDRA)) return
		 * "DNA,msu7gff,hdrasnp"; else if(dataset.equals(VarietyFacade.DATASET_SNP_ALL))
		 * return "DNA,msu7gff,msu7snpsv2,msu7indelsv2,hdrasnp"; else return
		 * "DNA,msu7gff,msu7snpsv2,msu7indelsv2";
		 */

		// return "DNA,msu7gff";

		// return "DNA,msu7gff,msu7snpsv2,msu7indelsv2,hdrasnp";

		String ret = "DNA,msu7gff";
		if (variantset.contains("3k"))
			ret += ",msu7snpsv2,msu7indelsv2";
		if (variantset.contains("hdra"))
			ret += ",hdrasnp";

		return ret;
	}

	/**
	 * Wait until file exists
	 * 
	 * @param string
	 */
	public static void waitForFile(String string) {
		File newfile = new File(string);
		while (!newfile.exists() && newfile.length() > 0) {
			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		AppContext.debug("file " + string + " ready");
	}

	/**
	 * get IUPAC symbol for nucleotides
	 * 
	 * @param alleles
	 * @return
	 */
	public static String getIUPAC(String alleles) {
		if (!alleles.contains("/")) {
			if (alleles.length() == 1)
				return alleles;
			else if (alleles.length() == 0)
				return "N";
			else
				throw new RuntimeException("Invalid Allele " + alleles);
		}
		if (alleles.equals("A/G") || alleles.equals("G/A"))
			return "R";
		else if (alleles.equals("A/T") || alleles.equals("T/A"))
			return "W";
		else if (alleles.equals("A/C") || alleles.equals("C/A"))
			return "M";
		else if (alleles.equals("C/G") || alleles.equals("G/C"))
			return "S";
		else if (alleles.equals("C/T") || alleles.equals("T/C"))
			return "Y";
		else if (alleles.equals("G/T") || alleles.equals("T/G"))
			return "K";
		else {
			// AppContext.debug( "Invalid Allele " + alleles + " , using ?" );
			// throw new RuntimeException("Invalid Allele " + alleles);
			return "?";
		}
	}

	/**
	 * get nucleotide values for IUPAC symbol
	 * 
	 * @param alleles
	 * @return
	 */
	public static String getNucsFromIUPAC(String alleles) {
		if (alleles.equals("R"))
			return "AG";
		else if (alleles.equals("W"))
			return "AT";
		else if (alleles.equals("M"))
			return "AC";
		else if (alleles.equals("S"))
			return "CG";
		else if (alleles.equals("Y"))
			return "CT";
		else if (alleles.equals("K"))
			return "GT";
		else if (alleles.equals("N"))
			return "N";
		else
			return alleles;
	}

	public static String getNucsFromIUPAC(char alleles) {
		return getNucsFromIUPAC(String.valueOf(alleles));
	}

	// **** conversion of region to snp_feature_id: TO BE REMOVED SOON AND
	// REPLACED WITH QUERY TO snp_featureloc table **************

	public static BigDecimal convertRegion2Indelalleleid(Integer chr, Long pos) {
		return convertRegion2Snpfeatureid(chr, pos);
	}

	public static BigDecimal convertRegion2Indelalleleid(Integer chr, BigDecimal pos) {
		return convertRegion2Snpfeatureid(chr, pos);
	}

	public static BigDecimal convertRegion2Indelalleleid(Integer chr, Integer pos) {
		return convertRegion2Snpfeatureid(chr, pos);
	}

	public static BigDecimal convertRegion2Snpfeatureid(String chr, Long pos) {
		chr = guessChrFromString(chr);
		return convertRegion2Snpfeatureid(Integer.valueOf(chr), pos);
	}

	public static BigDecimal convertRegion2Snpfeatureid(Integer chr, Long pos) {

		throw new RuntimeException("Dont use convertRegion2Snpfeatureid! Integer chr, Long pos");
		/*
		 * try {
		 * 
		 * return BigDecimal.valueOf( Long.valueOf( "1" + String.format("%02d" ,chr) +
		 * String.format("%08d" , pos) )); } catch (Exception ex) {
		 * ex.printStackTrace(); AppContext.debug("convertRegion2Snpfeatureid chr=" +
		 * chr + "  pos=" + pos); throw new
		 * RuntimeException("convertRegion2Snpfeatureid error"); }
		 */
	}

	public static BigDecimal convertRegion2Snpfeatureid(String chr, Integer pos) {
		chr = guessChrFromString(chr);
		return convertRegion2Snpfeatureid(Integer.valueOf(chr), pos);
	}

	public static BigDecimal convertRegion2Snpfeatureid(Integer chr, Integer pos) {
		throw new RuntimeException("Dont use convertRegion2Snpfeatureid! Integer chr, Integer pos");
		/*
		 * try { return BigDecimal.valueOf( Long.valueOf( "1" + String.format("%02d"
		 * ,chr) + String.format("%08d" , pos) )); } catch (Exception ex) {
		 * ex.printStackTrace(); AppContext.debug("convertRegion2Snpfeatureid chr=" +
		 * chr + "  pos=" + pos); throw new
		 * RuntimeException("convertRegion2Snpfeatureid error"); }
		 */

	}

	public static BigDecimal convertRegion2Snpfeatureid(String chr, BigDecimal pos) {
		chr = guessChrFromString(chr);
		return convertRegion2Snpfeatureid(Integer.valueOf(chr), pos);
	}

	public static BigDecimal convertRegion2Snpfeatureid(Integer chr, BigDecimal pos) {
		throw new RuntimeException("Dont use convertRegion2Snpfeatureid! Integer chr, BigDecimal pos");
		/*
		 * try { return BigDecimal.valueOf( Long.valueOf( "1" + String.format("%02d"
		 * ,chr) + String.format("%08d" , pos.longValue()) )); } catch (Exception ex) {
		 * ex.printStackTrace(); AppContext.debug("convertRegion2Snpfeatureid chr=" +
		 * chr + "  pos=" + pos); throw new
		 * RuntimeException("convertRegion2Snpfeatureid error"); }
		 */
	}

	public static Collection convertRegion2Snpfeatureid(String chr, Collection poslist) {

		chr = guessChrFromString(chr);
		return convertRegion2Snpfeatureid(Integer.valueOf(chr), poslist);

	}

	public static Collection convertRegion2Snpfeatureid(Integer chr, Collection poslist) {
		Set snpfeatureidSet = new TreeSet();
		Iterator<BigDecimal> it = poslist.iterator();
		while (it.hasNext()) {
			snpfeatureidSet.add(convertRegion2Snpfeatureid(chr, it.next()));
		}
		return snpfeatureidSet;
	}

	public static Collection convertRegion2SnpfeatureidLongcol(Integer chr, Collection poslist) {
		Set snpfeatureidSet = new TreeSet();
		Iterator<BigDecimal> it = poslist.iterator();
		while (it.hasNext()) {
			snpfeatureidSet.add(convertRegion2Snpfeatureid(chr, it.next()).longValue());
		}
		return snpfeatureidSet;
	}

	public static String convertParams2URL(Map map) {
		StringBuffer buff = new StringBuffer();
		if (map.size() > 0)
			buff.append("?");
		Iterator<String> itKey = map.keySet().iterator();
		while (itKey.hasNext()) {
			String key = itKey.next().toString();
			String[] vals = (String[]) map.get(key);
			buff.append(key).append("=");
			for (int i = 0; i < vals.length; i++) {
				buff.append(vals[i]);
				if (i + 1 < vals.length)
					buff.append(",");
			}
			if (itKey.hasNext())
				buff.append("&");
		}
		String params = buff.toString();
		if (params.length() > 0)
			debug(params);
		return params;
	}

	public static String getHrefMSU7(String locus) {
		if (locus == null || locus.isEmpty())
			return "http://rice.plantbiology.msu.edu/";
		else
			return "http://rice.plantbiology.msu.edu/cgi-bin/ORF_infopage.cgi?db=osa1r5&orf=" + locus;
	}

	public static String getHrefRAP(String locus) {
		if (locus == null || locus.isEmpty())
			return "http://rapdb.dna.affrc.go.jp/";
		else
			return "http://rapdb.dna.affrc.go.jp/viewer/gbrowse_details/irgsp1?name=" + locus;
	}

	public static String getHrefKEGG(String locus) {
		if (locus == null || locus.isEmpty())
			return "http://www.genome.jp/kegg/kegg2.html";
		else
			return "http://www.genome.jp/dbget-bin/www_bfind_sub?mode=bfind&max_hit=1000&locale=en&serv=kegg&dbkey=kegg&keywords="
					+ locus + "&page=1";
	}

	public static String getHrefPhytozome(String locus) {
		if (locus == null || locus.isEmpty())
			return "https://phytozome.jgi.doe.gov/pz/portal.html#!info?alias=Org_Osativa";
		else
			return "https://phytozome.jgi.doe.gov/pz/portal.html#!results?search=0&crown=1&star=1&method=5017&offset=0&searchText="
					+ locus;
	}

	public static String getHrefOryzabase(String locus) {
		if (locus == null || locus.isEmpty())
			return "http://shigen.nig.ac.jp/rice/oryzabase";
		else
			return "http://shigen.nig.ac.jp/rice/oryzabase/quicksearch/list?searchTarget=ALL&keyword=" + locus;
	}

	public static String getHrefGramene(String locus) {
		if (locus == null || locus.isEmpty())
			return "http://ensembl.gramene.org/Oryza_sativa/Info/Index";
		else
			return "http://ensembl.gramene.org/Oryza_sativa/Gene/Summary?g=" + locus;
	}

	public static String getHrefRiceCropOntology(String trait) {
		if (trait == null || trait.isEmpty())
			return "http://www.cropontology.org/terms/CO_320:ROOT/";
		else
			return "http://www.cropontology.org/terms/" + trait + "/";

		/*
		 * if(trait==null || trait.isEmpty()) return
		 * "http://www.cropontology.org/ontology/CO_320/Rice"; else return
		 * "http://www.cropontology.org/ontology/CO_320/Rice" + trait;
		 */

	}

	public static String getHrefPlantTraitOntology(String trait) {

		if (trait == null || trait.isEmpty())
			return "http://browser.planteome.org/amigo/term/TO:0000387";
		else
			return "http://browser.planteome.org/amigo/term/" + trait;

		/*
		 * if(trait==null || trait.isEmpty()) return
		 * "http://browser.planteome.org/amigo/search/ontology?q=TO:0000387"; else
		 * return "http://browser.planteome.org/amigo/search/ontology?q=" + trait;
		 */

		/*
		 * if(trait==null || trait.isEmpty()) return
		 * "http://palea.cgrb.oregonstate.edu/trait_ontology/cgi-bin/trait_amigo/term_details?term=TO:0000387";
		 * else return
		 * "http://palea.cgrb.oregonstate.edu/trait_ontology/cgi-bin/trait_amigo/term_details?term="
		 * + trait;
		 */
	}

	public static String getHrefUniprot(String locus) {
		if (locus == null || locus.isEmpty())
			return "http://www.uniprot.org";
		else
			return "http://www.uniprot.org/uniprot/?query=" + locus;
	}

	public static String getHrefNcbi(String locus) {
		if (locus == null || locus.isEmpty())
			return "http://www.ncbi.nlm.nih.gov/gene/";
		else
			return "http://www.ncbi.nlm.nih.gov/gene/?term=" + locus;
	}

	public static String getHrefIC4RLit(String locus) {
		if (locus == null || locus.isEmpty())
			return "http://literature.ic4r.org/article/";
		else
			return "http://literature.ic4r.org/gene/" + locus;
	}

	public static String getIRRIIp() {
		return "202.123.56.189";
	}

	public static DateFormat getDateFormat() {
		return datef;
	}

	public static long ints2long(int lower, int upper) {
		return ((long) upper << 32) | ((long) lower & 0xFFFFFFFL);
	}

	public static int[] long2ints(long ints) {
		return new int[] { (int) (ints >> 32), (int) ints };
	}

	public static Connection getJdbcConn() {
		return jdbccon;
	}

	public static void setJdbcConn(Connection con) {
		jdbccon = con;
	}

	public static void copyFile(File from, File to) throws IOException {
		Files.copy(from.toPath(), to.toPath());
	}

	public static void renameFile(File toBeRenamed, String new_name) {
		File fileWithNewName = new File(new_name);
		if (fileWithNewName.exists()) {
			throw new RuntimeException("file exists.");
		}
		// Rename file (or directory)
		boolean success = toBeRenamed.renameTo(fileWithNewName);
		if (!success) {
			// File was not successfully renamed
			debug(toBeRenamed.getName() + " not successfully renamed to " + new_name);
		}
	}

	public static boolean isEqual(float a, float b) {

		// http://stackoverflow.com/questions/6837007/comparing-float-double-values-using-operator
		return Math.abs(a / b - 1) < 5.96e-08;
	}

	public static boolean isEqual(double a, double b) {
		// http://stackoverflow.com/questions/6837007/comparing-float-double-values-using-operator
		return Math.abs(a / b - 1) < 1.11e-16;
	}

	public static Integer getMapVariantRank(String annot) {

		if (mapVariant2Order == null) {
			mapVariant2Order = new HashMap();
			mapVariant2Order.put("chromosome_number_variation", 1);
			mapVariant2Order.put("exon_loss_variant", 2);
			mapVariant2Order.put("frameshift_variant", 3);
			mapVariant2Order.put("stop_gained", 4);
			mapVariant2Order.put("stop_lost", 5);
			mapVariant2Order.put("start_lost", 6);
			mapVariant2Order.put("splice_acceptor_variant", 7);
			mapVariant2Order.put("splice_donor_variant", 8);
			mapVariant2Order.put("rare_amino_acid_variant", 9);
			mapVariant2Order.put("missense_variant", 10);
			mapVariant2Order.put("inframe_insertion", 11);
			mapVariant2Order.put("disruptive_inframe_insertion", 12);
			mapVariant2Order.put("inframe_deletion", 13);
			mapVariant2Order.put("disruptive_inframe_deletion", 14);
			mapVariant2Order.put("5_prime_UTR_truncation+exon_loss_variant", 15);
			mapVariant2Order.put("3_prime_UTR_truncation+exon_loss", 16);
			mapVariant2Order.put("splice_branch_variant", 17);
			mapVariant2Order.put("splice_region_variant", 18);
			mapVariant2Order.put("splice_branch_variant", 19);
			mapVariant2Order.put("stop_retained_variant", 20);
			mapVariant2Order.put("initiator_codon_variant", 21);
			mapVariant2Order.put("synonymous_variant", 22);
			mapVariant2Order.put("initiator_codon_variant+non_canonical_start_codon", 23);
			mapVariant2Order.put("stop_retained_variant", 24);
			mapVariant2Order.put("coding_sequence_variant", 25);
			mapVariant2Order.put("5_prime_UTR_variant", 26);
			mapVariant2Order.put("3_prime_UTR_variant", 27);
			mapVariant2Order.put("5_prime_UTR_premature_start_codon_gain_variant", 28);
			mapVariant2Order.put("upstream_gene_variant", 29);
			mapVariant2Order.put("downstream_gene_variant", 30);
			mapVariant2Order.put("TF_binding_site_variant", 31);
			mapVariant2Order.put("regulatory_region_variant", 32);
			mapVariant2Order.put("miRNA", 33);
			mapVariant2Order.put("custom", 34);
			mapVariant2Order.put("sequence_feature", 35);
			mapVariant2Order.put("conserved_intron_variant", 36);
			mapVariant2Order.put("intron_variant", 37);
			mapVariant2Order.put("intragenic_variant", 38);
			mapVariant2Order.put("conserved_intergenic_variant", 39);
			mapVariant2Order.put("intergenic_region", 40);
			mapVariant2Order.put("coding_sequence_variant", 41);
			mapVariant2Order.put("non_coding_exon_variant", 42);
			mapVariant2Order.put("nc_transcript_variant", 43);
			mapVariant2Order.put("gene_variant", 44);
			mapVariant2Order.put("chromosome", 45);
		}

		return (Integer) mapVariant2Order.get(annot);

	}

	public static Collection convertPos2Position(Collection possetobj) {

		if (possetobj == null || possetobj.isEmpty())
			return possetobj;

		String prevcont = null;
		List posset = new ArrayList();
		Iterator itpos = possetobj.iterator();
		while (itpos.hasNext()) {
			Object pos = itpos.next();
			if (pos instanceof Position) {
				posset.add(((Position) pos).getPosition());
				if (prevcont != null && !prevcont.equals(((Position) pos).getContig())) {
					throw new RuntimeException("Different contigs for " + prevcont + " and " + pos);
				}
				prevcont = ((Position) pos).getContig();
			} else
				posset.add((Number) pos);
		}
		return posset;
	}

	public static boolean showBlankAnnotations() {
		return true;
	}

	public static String getPhantomjsExe() {
		return getFlatfilesDir() + "phantomjs.exe";
	}

	public static int getSnpMatrixFrozenCols() {
		return 6; // with dataset, accessison
	}

	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	public static Map getMapBearer2StartTime() {
		return mapBearer2StartTime;
	}

	public static String getSubmitter() {

		Object req = Executions.getCurrent().getNativeRequest();
		String reqstr = "";
		if (req != null && req instanceof HttpServletRequest) {
			HttpServletRequest servreq = (HttpServletRequest) req;
			String forwardedfor = servreq.getHeader("x-forwarded-for");
			if (forwardedfor != null)
				reqstr = forwardedfor;
			else {
				reqstr = servreq.getRemoteAddr();
				if (reqstr == null)
					reqstr = servreq.getRemoteHost();
				else if (servreq.getRemoteHost() != null && !reqstr.equals(servreq.getRemoteHost())) {
					reqstr += "-" + servreq.getRemoteHost();
				}
			}

			/*
			 * String forwardedfor= servreq.getHeader("x-forwarded-for");
			 * if(forwardedfor!=null) reqstr+="-" + forwardedfor;
			 */
		}

		return ((AppContext.isIRRILAN() || reqstr.contains(AppContext.getIRRIIp()))
				? reqstr + "-" + AppContext.createTempFilename()
				: reqstr);

	}

	private static Map<Long, Map> mapSession2Atts = new HashMap();
	private static Navbar mainNavbar;
	private static Include mainContentInclude;
	public static String JAPONICA_NIPPONBARE = "Japonica nipponbare";
	public static String AZUCENA = "azucena";
	public static String IR64 = "IR64";
	public static String MH63 = "MH63";

	public static void addSessionAttr(Long session, String att, Object value) {

		Map<String, Object> mapAtt = mapSession2Atts.get(session);
		if (mapAtt == null) {
			mapAtt = new HashMap();
			mapSession2Atts.put(session, mapAtt);
		}
		mapAtt.put(att, value);
	}

	public static Object getSessionAttr(Long session, String att) {

		Map<String, Object> mapAtt = mapSession2Atts.get(session);
		if (mapAtt == null) {
			AppContext.debug("getSessionAttr(" + session + ")=null");
			return null;
		}

		Object ret = mapAtt.get(att);
		if (ret == null)
			AppContext.debug("getSessionAttr(" + session + "," + att + ")=null");
		return ret;
	}

	public static Object popSessionAttr(Long session, String att) {

		Map<String, Object> mapAtt = mapSession2Atts.get(session);
		if (mapAtt != null) {
			Object val = mapAtt.get(att);
			if (val != null)
				mapAtt.remove(att);
			if (mapAtt.size() == 0)
				mapSession2Atts.remove(session);

			if (val == null)
				AppContext.debug("popSessionAttr(" + session + "," + att + ")=null");
			return val;
		}
		AppContext.debug("popSessionAttr(" + session + ")=null");
		return null;
	}

	public static Map popSession(Long session) {

		Map mapAtt = mapSession2Atts.get(session);
		mapSession2Atts.remove(session);
		return mapAtt;
	}

	public static Map getMapSession2Atts() {

		return mapSession2Atts;
	}

	public static boolean isBypassViews() {
		return bBypassViews;
	}

	/** DEPRECATED
	public static List executeSQL(EntityManager entityManager, Class retclass, String sql) {
		if (AppContext.isLocalhost())
			AppContext.debug("executing :" + sql + "  retclass:" + retclass);
		// logger.info("executing :" + sql);
		try {
			Session ses = entityManager.unwrap(Session.class);
			List l = ses.createSQLQuery(sql).addEntity(retclass).list();
			AppContext.debug("execute sql:" + l.size() + " " + retclass);
			return l;
		} catch (Exception ex) {
			AppContext.error("error executing :" + sql + "  retclass:" + retclass);
			ex.printStackTrace();
			throw ex;
		}
	}*/

	
	/** @Deprecated 
	private static String getEC2Tag(String name) {

		if (ec2tagname != null)
			return ec2tagname;

		DescribeTagsRequest req = new DescribeTagsRequest();
		Collection filters = new LinkedList<>();
		LinkedList instanceList = new LinkedList<>();

		// Get instanceId of the current instance
		String instanceId = EC2MetadataUtils.getInstanceId();
		instanceList.add(instanceId);
		Filter filter = new Filter("resource-id", instanceList);
		filters.add(filter);
		req.setFilters(filters);

		AmazonEC2 client = AmazonEC2ClientBuilder.standard()
				.withCredentials(DefaultAWSCredentialsProviderChain.getInstance()).withRegion(Regions.AP_SOUTHEAST_1)
				.build();

		DescribeTagsResult tagResult = client.describeTags(req);
		if (tagResult != null) {
			for (TagDescription tag : tagResult.getTags()) {
				if (name.equals(tag.getKey()) || name.toLowerCase().equals(tag.getKey().toLowerCase())) {
					ec2tagname = tag.getValue();
					return ec2tagname;
				}
			}
		}
		return null;
	}
	*/

	public static String getInstance() {
		if (isLocalhost())
			return "localhost";
		else
			return "unknown";
	}

	private static Map<String, BigDecimal> getMapVarunique2Id(Map<BigDecimal, Variety> mapvarid2var, boolean accession,
			boolean irisid, boolean name) {
		if (mapVarnames2Varid == null) {
			mapVarnames2Varid = new HashMap();
			Iterator<BigDecimal> itVarid = mapvarid2var.keySet().iterator();
			while (itVarid.hasNext()) {
				BigDecimal varid = itVarid.next();
				Variety var = mapvarid2var.get(varid);
				if (accession) {
					if (!var.getAccession().isEmpty() && !var.getAccession().equals("NA")) {
						if (mapVarnames2Varid.containsKey(var.getAccession())
								&& !var.getVarietyId().equals(mapVarnames2Varid.get(var.getAccession()))) {
							AppContext.info("duplicate accession " + var.getAccession() + ": varids " + varid + ", "
									+ mapVarnames2Varid.get(var.getAccession()));
						} else
							mapVarnames2Varid.put(var.getAccession(), varid);
						if (mapVarnames2Varid.containsKey(var.getAccession().replace(" ", "_")) && !var.getVarietyId()
								.equals(mapVarnames2Varid.get(var.getAccession().replace(" ", "_")))) {
							AppContext.info("duplicate accession " + var.getAccession().replace(" ", "_") + ": varids "
									+ varid + ", " + mapVarnames2Varid.get(var.getAccession().replace(" ", "_")));
						} else
							mapVarnames2Varid.put(var.getAccession().replace(" ", "_"), varid);
					}
				}
				if (irisid) {
					if (!var.getIrisId().isEmpty() && !var.getIrisId().equals("NA")) {
						if (mapVarnames2Varid.containsKey(var.getIrisId())
								&& !var.getVarietyId().equals(mapVarnames2Varid.get(var.getIrisId()))) {
							AppContext.info("duplicate irisid " + var.getIrisId() + ": varids " + varid + ", "
									+ mapVarnames2Varid.get(var.getIrisId()));
						} else
							mapVarnames2Varid.put(var.getIrisId(), varid);

						if (mapVarnames2Varid.containsKey(var.getIrisId().replace(" ", "_")) && !var.getVarietyId()
								.equals(mapVarnames2Varid.get(var.getIrisId().replace(" ", "_")))) {
							AppContext.info("duplicate irisid " + var.getIrisId().replace(" ", "_") + ": varids "
									+ varid + ", " + mapVarnames2Varid.get(var.getIrisId().replace(" ", "_")));
						} else
							mapVarnames2Varid.put(var.getIrisId().replace(" ", "_"), varid);
					}

					if (!var.getBoxCode().isEmpty() && !var.getBoxCode().equals("NA")) {
						if (mapVarnames2Varid.containsKey(var.getBoxCode().replace(" ", "_")) && !var.getVarietyId()
								.equals(mapVarnames2Varid.get(var.getBoxCode().replace(" ", "_")))) {
							AppContext.info("duplicate getBoxCode " + var.getBoxCode().replace(" ", "_") + ": varids "
									+ varid + ", " + mapVarnames2Varid.get(var.getBoxCode().replace(" ", "_")));
						} else
							mapVarnames2Varid.put(var.getBoxCode().replace(" ", "_"), varid);
					}

				}
				if (name) {
					if (mapVarnames2Varid.containsKey(var.getName().toUpperCase().replace(" ", "_"))
							&& !var.getVarietyId()
									.equals(mapVarnames2Varid.get(var.getName().toUpperCase().replace(" ", "_")))) {
						AppContext.info("duplicate name " + var.getName().toUpperCase().replace(" ", "_") + ": varids "
								+ varid + ", " + mapVarnames2Varid.get(var.getName().toUpperCase().replace(" ", "_")));
					} else
						mapVarnames2Varid.put(var.getName().toUpperCase().replace(" ", "_"), varid);

					if (mapVarnames2Varid.containsKey(var.getName().toUpperCase())
							&& !var.getVarietyId().equals(mapVarnames2Varid.get(var.getName().toUpperCase()))) {
						AppContext.info("duplicate name " + var.getName().toUpperCase() + ": varids " + varid + ", "
								+ mapVarnames2Varid.get(var.getName().toUpperCase()));
					} else
						mapVarnames2Varid.put(var.getName().toUpperCase(), varid);
				}
			}
		}
		return mapVarnames2Varid;
	}

	private static Map<String, BigDecimal> getMapVarunique2Id(Map<BigDecimal, Variety> mapvarid2var, boolean varidid) {
		Map mapvarid = new HashMap();
		Iterator<BigDecimal> itVarid = mapvarid2var.keySet().iterator();
		while (itVarid.hasNext()) {
			BigDecimal varid = itVarid.next();
			Variety var = mapvarid2var.get(varid);
			if (!var.getAccession().isEmpty() && !var.getAccession().equals("NA")) {
				mapvarid.put(var.getAccession() + "_" + varid, varid);
				mapvarid.put(var.getAccession().replace(" ", "_") + "_" + varid, varid);
				mapvarid.put(var.getAccession(), varid);
				mapvarid.put(var.getAccession().replace(" ", "_"), varid);
			}
			if (!var.getIrisId().isEmpty() && !var.getIrisId().equals("NA")) {
				mapvarid.put(var.getIrisId() + "_" + varid, varid);
				mapvarid.put(var.getIrisId().replace(" ", "_") + "_" + varid, varid);
				mapvarid.put(var.getIrisId(), varid);
				mapvarid.put(var.getIrisId().replace(" ", "_"), varid);
			}
			if (!var.getBoxCode().isEmpty() && !var.getBoxCode().equals("NA")) {
				mapvarid.put(var.getBoxCode() + "_" + varid, varid);
				mapvarid.put(var.getBoxCode().replace(" ", "_") + "_" + varid, varid);
				mapvarid.put(var.getBoxCode(), varid);
				mapvarid.put(var.getBoxCode().replace(" ", "_"), varid);
			}

			mapvarid.put(var.getName().toUpperCase().replace(" ", "_") + "_" + varid, varid);
			mapvarid.put(var.getName().toUpperCase() + "_" + varid, varid);

		}
		return mapvarid;
	}

	public static BigDecimal getVarunique2Id(String unique, boolean accession, boolean irisid, boolean name,
			Map<BigDecimal, Variety> mapvarid2var) {
		if (mapVarnames2Varid == null) {
			mapVarnames2Varid = getMapVarunique2Id(mapvarid2var, accession, irisid, name);
		}
		BigDecimal id = mapVarnames2Varid.get(unique.toUpperCase());
		if (id == null)
			id = mapVarnames2Varid.get(unique);
		if (id == null) {
			mapVarnames2Varid.putAll(getMapVarunique2Id(mapvarid2var, accession, irisid, name));
			id = mapVarnames2Varid.get(unique);
			if (id == null)
				id = mapVarnames2Varid.get(unique.toUpperCase());
		}
		if (id == null) {
			mapVarnames2Varid.putAll(getMapVarunique2Id(mapvarid2var, true));
			id = mapVarnames2Varid.get(unique);
			if (id == null)
				id = mapVarnames2Varid.get(unique.toUpperCase());
		}

		AppContext.debug("mapVarnames2Varid.size()=" + mapVarnames2Varid.size());
		// AppContext.debug(mapVarnames2Varid.toString());
		return id;
	}

	public static Set<String> getDBQuanTraits() {
		Set numericTraits = new LinkedHashSet();

		numericTraits.add("Grain Length");
		numericTraits.add("Grain length (mm)");
		numericTraits.add("Ligule length (mm) - cultivated");
		numericTraits.add("Culm length (cm) at reproductive");
		numericTraits.add("Grain Width(mm)");
		numericTraits.add("Culm diameter (mm) of basal internode at repro.");
		numericTraits.add("100-grain weight (gm) - cultivated");
		numericTraits.add("Leaf length (cm) - cultivated");
		numericTraits.add("Leaf width (cm) - cultivated");
		numericTraits.add("Seedling height (cm)");
		numericTraits.add("Flagleaf length");
		numericTraits.add("Culm number (count) at reproductive - cultivated");
		numericTraits.add("Panicle length (cm) at post-harvest");
		numericTraits.add("Heading (days to 80% fully headed) - cultivated");
		return numericTraits;
	}

	public static boolean reloadFromDB(String entity) {

		return false;
		// return true;
		/*
		 * if(entity.equals("gene")) {
		 * 
		 * }
		 */

	}

	public static String createSampleUniqueName(StockSample var, Set dataset) {
		String indvid = var.getAssay().replaceAll(" ", "_");
		if (indvid.isEmpty() || indvid.equals("NA")) {
			if (var.getAccession() != null && !var.getAccession().isEmpty())
				indvid = var.getAccession().replaceAll(" ", "_");
		}
		if (indvid.isEmpty() || indvid.equals("NA")) {
			if (var.getName() != null && !var.getName().isEmpty())
				indvid = var.getName().replaceAll(" ", "_");
		}
		if (indvid.isEmpty() || indvid.equals("NA")) {
			indvid = "STOCK_SAMPLE_ID:" + var.getStockSampleId();
		}
		if (dataset.size() > 1)
			indvid = indvid + "__" + var.getDataset().replaceAll(" ", "_");
		return indvid;

	}

	/*
	 * public static void copyFile(File sourceFile, File destFile) throws
	 * IOException { if(!destFile.exists()) { destFile.createNewFile(); }
	 * 
	 * FileChannel source = null; FileChannel destination = null;
	 * 
	 * try { source = new FileInputStream(sourceFile).getChannel(); destination =
	 * new FileOutputStream(destFile).getChannel(); destination.transferFrom(source,
	 * 0, source.size()); } finally { if(source != null) { source.close(); }
	 * if(destination != null) { destination.close(); } } }
	 */
	// ************* PAST CODES RETAINED

	public static String getLogLevel() {
		if (isScienceCloud())
			return log_level;
		// if(isIRRILAN() || isAWS) return "debug";
		return "debug";
	}

	public static void setLog_level(String log_level) {
		AppContext.log_level = log_level;
	}

	public static Set toUpperCase(Collection dataset) {
		Set s = new LinkedHashSet();
		for (Object ds : dataset) {
			s.add(((String) ds).toUpperCase());
		}
		return s;
	}

	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static boolean showItem(String item) {
		if (webProp.getProperty(item).equals("true"))
			return true;
		return false;
	}

	public static Set<String> getStringValues(Set<Listitem> listItemBoxValues) {
		Set<String> strValues = new HashSet<String>();

		Iterator iter = listItemBoxValues.iterator();

		while (iter.hasNext()) {
			Listitem item = (Listitem) iter.next();
			strValues.add((String) item.getValue());
		}

		return strValues;
	}

	public static String readURL(String a) throws Exception {
		return readURL(a, null);
	}

	public static String readURL(String a, String key) throws Exception {

		URL url = new URL(a);
		URLConnection conn = url.openConnection();
		// conn.addRequestProperty("User-Agent", "Mozilla/4.0");
		conn.addRequestProperty("User-Agent", "");
		if (key != null)
			conn.setRequestProperty("Authorization", "Bearer " + key);

		// open the stream and put it into BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String inputLine;
		StringBuffer buff = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			buff.append(inputLine);
		}
		br.close();
		return buff.toString();
	}

	public static String readFile(String a) throws Exception {

		// open the stream and put it into BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(a));

		String inputLine;
		StringBuffer buff = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			buff.append(inputLine);
		}
		br.close();
		return buff.toString();
	}

	public static BufferedReader bufferedReadURL(String a, String key) throws Exception {

		URL url = new URL(a);
		URLConnection conn = url.openConnection();
		// conn.addRequestProperty("User-Agent", "Mozilla/4.0");
		conn.addRequestProperty("User-Agent", "");
		if (key != null)
			conn.setRequestProperty("Authorization", "Bearer " + key);

		// open the stream and put it into BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		return br;
	}

	public static boolean downloadURL(String uri, File destination) {

		try {
			FileUtils.copyURLToFile(new URL(uri), destination);
			System.out.println("File downloaded from " + uri + " to " + destination);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean downloadURL2(String uri, File destination) {

		try {
			FileUtils.copyURLToFile(new URL(uri), destination);
			// check if zip, if not, make text
			try {
				ZipFile zipFile = new ZipFile(destination);
				zipFile.close();
				AppContext.debug("downloaded zip file " + destination + " from " + uri);
			} catch (Exception ex) {
				destination.renameTo(new File(destination.getAbsolutePath().replace(".zip", ".txt")));
				AppContext.debug("downloaded txt file " + destination.getAbsolutePath().replace(".zip", ".txt")
						+ " from " + uri);
			}

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static Map<String, String> getFilextMime() {
		// TODO Auto-generated method stub
		Map m = new LinkedHashMap();
		m.put(".zip", "application/zip");
		m.put(".pdf", "application/pdf");
		m.put(".txt", "text/plain");
		m.put(".csv", "text/csv");
		m.put(".tsv", "text/tsv");
		m.put(".tabular", "text/tsv");
		return m;
	}

	public static String readURLContent(String ip) {
		try {
			URL url = new URL(ip);
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String l = null;
			StringBuffer b = new StringBuffer();
			while ((l = in.readLine()) != null) {
				// System.out.println(l);
				b.append(l);
			}
			in.close();
			return b.toString();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String uploadToServer(String serverdir, Media media) throws Exception {
		// org.zkoss.util.media.Media media = Fileupload.get();
		OutputStream outputStream = new FileOutputStream(new File(serverdir + media.getName()));
		InputStream inputStream = media.getStreamData();
		byte[] buffer = new byte[1024];
		for (int count; (count = inputStream.read(buffer)) != -1;) {
			outputStream.write(buffer, 0, count);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		return media.getName();
	}

	public static String sendPost(String url, Map<String, String> postParams, Map<String, String> header,
			String cookies) {

		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		// HttpClientBuilder clientBuilder = HttpClients.createDefault();
		// CloseableHttpClient httpclient = clientBuilder.build();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		; // clientBuilder.build();

		if (header != null) {
			Iterator<Entry<String, String>> itCabecera = header.entrySet().iterator();
			while (itCabecera.hasNext()) {
				Entry<String, String> entry = itCabecera.next();

				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}

		httpPost.setHeader("Cookie", cookies);

		if (postParams != null) {
			Iterator<Entry<String, String>> itParms = postParams.entrySet().iterator();
			while (itParms.hasNext()) {
				Entry<String, String> entry = itParms.next();

				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
		httpPost.setEntity(formEntity);

		String pageContent = null;
		try {
			CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				pageContent = EntityUtils.toString(entity);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return pageContent;
	}

//	public static void setUserContent() {
//		Properties contentProp = new Properties();
//		InputStream contentManager = AppContext.class.getResourceAsStream("/ACCESS_" + UserConstants.USER + ".properties");
//		try {
//
//			contentProp.load(contentManager);
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//
//	}

	/*
	 * public static void setSidebar(Navbar navbar, Include contentInclude) {
	 * mainNavbar = navbar; mainContentInclude = contentInclude;
	 * 
	 * }
	 * 
	 * public static void setSidebar(boolean toggle) { if (toggle) {
	 * mainNavbar.setCollapsed(false); mainContentInclude.setSclass("content"); }
	 * else { mainNavbar.setCollapsed(true);
	 * mainContentInclude.setSclass("content collapsed"); }
	 * 
	 * }
	 * 
	 */

	public static String MH63() {
		return "MH63";
	}

	public static void createUserDirectory(String username) {
		createDirectory(getFlatfilesDir() + "/users/");
		createDirectory(getFlatfilesDir() + "/users/" + username);
	}

	public static String getUserDirectory(String username) {
		createUserDirectory(username);
		return (getFlatfilesDir() + "/users/" + username);
	}

	public static String getUserSNPDirectory(String username) {
		String userDir = getUserDirectory(username);
		createDirectory(userDir + "/SNP");
		return (userDir + "/SNP");
	}

	public static void createDirectory(String path) {

		java.nio.file.Path dirPath = Paths.get(path);

		// Check if the directory exists
		if (Files.notExists(dirPath)) {
			try {
				// Create the directory, including any non-existent parent directories
				Files.createDirectories(dirPath);
				System.out.println("Directory created successfully.");
			} catch (IOException e) {
				System.out.println("An error occurred while creating the directory: " + e.getMessage());
			}
		} else {
			System.out.println("Directory already exists.");
		}

	}

	public static File createFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				boolean fileCreated = file.createNewFile();
				if (fileCreated) {
					System.out.println("File created: " + filePath);
				} else {
					System.out.println("Failed to create file: " + filePath);
					return null; // Exit if file creation fails
				}
			} catch (IOException e) {
				System.out.println("Error creating file: " + e.getMessage());
				return null;
			}
		}
		return file;

	}

	public static String getToken() {
		SecureRandom random = new SecureRandom();
		StringBuilder randomString = new StringBuilder(STRING_LENGTH);

		for (int i = 0; i < STRING_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			randomString.append(CHARACTERS.charAt(randomIndex));
		}

		return randomString.toString();
	}

	public static Timestamp getDateNow() {

		return null;
	}

	public static Timestamp getCurrentTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(localDateTime);

		return timestamp;
	}

	public static Timestamp getExpiredTime() {
		LocalDateTime localDateTime = LocalDateTime.of(2025, 1, 1, 0, 0);
		Timestamp timestamp = Timestamp.valueOf(localDateTime);

		return timestamp;
	}

	public static long compareTwoTimeStamps(java.sql.Timestamp logtime, java.sql.Timestamp tokenCreated) {
		long milliseconds1 = tokenCreated.getTime();
		long milliseconds2 = logtime.getTime();

		long diff = milliseconds2 - milliseconds1;
		long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffMinutes;
	}

	public static boolean checkIfExpired(Timestamp tokenvalidity, int minutes) {
		long duration = compareTwoTimeStamps(getCurrentTime(), tokenvalidity);

		if (duration > minutes)
			return true;

		return false;
	}

	public static String getNewlineFromSet(Set<Variety> setVarieties) {
		StringBuilder stringBuilder = new StringBuilder();

		// Iterate over the set of objects and append them to the StringBuilder
		for (Variety obj : setVarieties) {
			stringBuilder.append(obj.toString()).append(System.lineSeparator());
		}

		// Convert StringBuilder to String and return
		return stringBuilder.toString();
	}

	// Method to read a list of objects from a text file
	public static List<Variety> readObjectListFromFile(String filename) {
		List<Variety> objectList = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Convert each line back into an object (assuming the format is
				// "MyClass{name='Name', age=25}")
				String name = line.split(", ")[0].split("=")[1].replace("'", "");
				int age = Integer.parseInt(line.split(", ")[1].split("=")[1]);
				// Variety obj = new Variety(name, age);
//                objectList.add(obj);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objectList;
	}

	public static void writeListToUserList(String listname, String type, String list, String email) {
		File directory = new File(AppContext.getFlatfilesDir() + File.separator + "users" + File.separator + email
				+ File.separator + type);

		// Check if the directory exists; if not, create it
		if (!directory.exists()) {
			boolean isCreated = directory.mkdirs();
			if (isCreated) {
				System.out.println("Directory created: ");
			} else {
				System.out.println("Failed to create directory.");
				return;
			}
		}

		// Create the full file path
		File file = new File(directory + File.separator + listname);

		// Use BufferedWriter to write content to the file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(list); // Write the content to the file
			System.out.println("Content written to file: " + file.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

	}

	public static String ChrWithZERO(String value) {
		for (int i = 1; i <= 9; i++) {
			value = value.toLowerCase().replaceAll("\\bchr" + i + "\\b", "Chr0" + i);
		}
		return value;
	}

	

}