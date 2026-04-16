package org.irri.iric.ds.utils;

import static org.toilelibre.libe.curl.Curl.curl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.irri.iric.ds.chado.domain.Passport;
import org.irri.iric.ds.chado.domain.object.BrapiPassport;

public class GenesysAPI {

	String access_token = null;
	String refresh_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJsLm1hbnN1ZXRvQGlycmkub3JnIiwic2NvcGUiOlsicmVhZCJdLCJhdGkiOiI0Mjg2YzdlMS0yNGJlLTQ0NDktYTEwNS1lNTQwOWM3ZjE3YTMiLCJleHAiOjE1NzE3MzM2MjksImF1dGhvcml0aWVzIjpbIlJPTEVfRVZFUllPTkUiLCJST0xFX1ZBTElEQVRFRFVTRVIiLCJST0xFX1VTRVIiXSwianRpIjoiMjg1OTI3MjEtNjljZS00YmQ5LTljMDgtM2Q3ZWI3OTg0ODM5IiwiY2xpZW50X2lkIjoiZExDaVIuTXp3a05oYTE4SW1FY3cwQURsaTBAc2FuZGJveC5nZW5lc3lzLXBnci5vcmcifQ.Z87sX254X7FTdNRBIs6L68teKhG5gpdJu5uLN1DD9Kg";
	String client_id = "dLCiR.MzwkNha18ImEcw0ADli0@sandbox.genesys-pgr.org";
	String client_secret = "wGmRAhVplWLEVWlqiMT712PMnaqOa8FN";
	String basepath = "https://api.sandbox.genesys-pgr.org/api/v1/";
	boolean brapi = false;
	Object returnclass;

	static GenesysAPI instance;
	
	private static Logger log = Logger.getLogger(GenesysAPI.class.getName());

	public static void main(String[] args) {

		try {
			Set s = GenesysAPI.getInstance(Passport.class, true).getPassportByAccession("IRGC 122151");
			log.info(s.toString());
			if (s.size() > 0) {
				log.info(s.iterator().next().getClass().toString());
			}

			s = GenesysAPI.getInstance(String.class, true).getAttributeCategories();
			log.info(s.toString());
			if (s.size() > 0) {
				log.info(s.iterator().next().getClass().toString());
			}

			s = GenesysAPI.getInstance(String.class, true).getStudies();
			log.info(s.toString());
			if (s.size() > 0) {
				log.info(s.iterator().next().getClass().toString());
			}
			s = GenesysAPI.getInstance(String.class, true).getStudyTypes();
			log.info(s.toString());
			if (s.size() > 0) {
				log.info(s.iterator().next().getClass().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public GenesysAPI() {
		this(String.class, false);
	}

	public GenesysAPI(Class returnclass) {
		this(returnclass, false);
	}

	public GenesysAPI(boolean brapi) {
		this(String.class, brapi);
	}

	public GenesysAPI(Object returnclass, boolean brapi) {
		super();
		// TODO Auto-generated constructor stub
		refreshToken();
		this.brapi = brapi;
		this.returnclass = returnclass;
		if (brapi)
			basepath = "https://api.sandbox.genesys-pgr.org/brapi/v1/";

	}

	public static GenesysAPI getInstance(boolean brapi) {
		return getInstance("", brapi);
	}

	public static GenesysAPI getInstance(Object returnclass, boolean brapi) {
		if (instance == null) {
			instance = new GenesysAPI(returnclass, brapi);
		}
		instance.setBrapi(brapi);
		instance.setClass(returnclass);
		return instance;
	}

	public Set getCrops() throws Exception {
		String url = "https://api.sandbox.genesys-pgr.org/api/v1/crops";
		if (brapi)
			url = basepath + "commoncropnames";
		return execCurl(url);
	}

	public Set getAccession(String acc) throws Exception {
		acc = urlencode(acc);
		String url = "https://api.sandbox.genesys-pgr.org/api/v1/accessionNumber/" + acc;

		// if(brapi)
		// url="https://api.sandbox.genesys-pgr.org/brapi/v1/germplasm?page=0&pageSize=2&germplasmName="
		// + acc + "&germplasmDbId=&germplasmPUI=&germplasmDbId=&germplasmPUI=";
		if (brapi)
			url = "https://api.sandbox.genesys-pgr.org/brapi/v1/germplasm?page=0&pageSize=2&germplasmName=" + acc;

		return execCurl(url);
	}

	public Set getPassportByAccession(String acc) throws Exception {
		// TODO Auto-generated method stub
		acc = urlencode(acc);
		String url = "https://api.sandbox.genesys-pgr.org/api/v1/accessionNumber/" + acc;
		// if(brapi)
		// url="https://api.sandbox.genesys-pgr.org/brapi/v1/germplasm?page=0&pageSize=2&germplasmName="
		// + acc + "&germplasmDbId=&germplasmPUI=&germplasmDbId=&germplasmPUI=";
		if (brapi)
			url = "https://api.sandbox.genesys-pgr.org/brapi/v1/germplasm?page=0&pageSize=2&germplasmName=" + acc;
		return execCurl(url);
	}

	public Set getAttributeCategories() throws Exception {
		String url = null;
		if (brapi)
			url = basepath + "attributes/categories";
		return execCurl(url);
	}

	public Set getAttributes(String categories) throws Exception {
		String url = null;
		if (brapi)
			url = basepath + "attributes";
		return execCurl(url);
	}

	public Set getStudyTypes() throws Exception {
		String url = null;
		if (brapi)
			url = basepath + "studyTypes";
		return execCurl(url);
	}

	public Set getStudies() throws Exception {
		String url = null;
		if (brapi)
			url = basepath + "studies-search";
		return execCurl(url);
	}

	public Set getStudies(String dbid) throws Exception {
		String url = null;
		if (brapi)
			url = basepath + "studies/" + dbid;
		return execCurl(url);
	}

	public Set getStudiesGermplasm(String dbid) throws Exception {
		String url = null;
		if (brapi)
			url = basepath + "studies/" + dbid + "/germplasm";
		return execCurl(url);
	}

	private void setBrapi(boolean brapi) {
		this.brapi = brapi;
	}

	private void setClass(Object returnclass) {
		this.returnclass = returnclass;
	}

	private String refreshToken() {
		String url = "-X POST https://api.sandbox.genesys-pgr.org/oauth/token?grant_type=refresh_token&client_id="
				+ client_id + "&client_secret=" + client_secret + "&redirect_uri=oob&refresh_token=" + refresh_token;
		log.info(url);
		HttpResponse response = curl(url);
		// System.out.println( response.toString());
		try {
			access_token = new JSONObject(EntityUtils.toString(response.getEntity())).getString("access_token");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	String urlencode(String val) {
		try {
			val = URLEncoder.encode(val, StandardCharsets.UTF_8.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return val;
	}

	private Set returnClass(String jsonstr) {
		Set s = new LinkedHashSet();
		System.out.println("jsonstr=" + jsonstr);
		JSONArray arr = null;
		try {
			arr = new JSONObject(jsonstr).getJSONObject("result").getJSONArray("data");
		} catch (Exception ex) {
			log.info("jsonstr=" + jsonstr);
			ex.printStackTrace();
		}

		// if(returnclass instanceof Passport) {
		// Class c=null;
		if (((Class) returnclass).isAssignableFrom(Passport.class)
				|| Passport.class.isAssignableFrom((Class) returnclass)) {
			if (arr.length() > 1) {
				log.info("more than 1 accestion for Passport query");
				throw new RuntimeException("More than 1 accession for Passport query");
			}
			for (int i = 0; i < arr.length(); i++) {
				try {
					JSONObject acc = arr.getJSONObject(i);
					Iterator keys = acc.keys();
					while (keys.hasNext()) {
						String attname = (String) keys.next();
						String attval = acc.getString(attname);
						if (attval == null || attval.isEmpty() || attval.equals("null") || attval.equals("[]"))
							continue;
						s.add(new BrapiPassport(attname, attname, attval));
					}
				} catch (Exception ex2) {
					ex2.printStackTrace();
				}
			}
		}
		// else if(returnclass instanceof String ) {
		else if (((Class) returnclass).isAssignableFrom(String.class)
				|| String.class.isAssignableFrom((Class) returnclass)) {

			for (int i = 0; i < arr.length(); i++) {
				try {
					String si = arr.getString(i);
					if (si == null || si.isEmpty())
						continue;
					s.add(si);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} else
			throw new RuntimeException("Class " + returnclass + " not handled");

		return s;
	}

	public Set execCurl(String url) throws Exception {
		// String access_token=
		// "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjkxODg4OTQsInVzZXJfbmFtZSI6ImwubWFuc3VldG9AaXJyaS5vcmciLCJhdXRob3JpdGllcyI6WyJST0xFX0VWRVJZT05FIiwiUk9MRV9WQUxJREFURURVU0VSIiwiUk9MRV9VU0VSIl0sImp0aSI6Ijk4NTk1MjI2LTUyZjEtNDU3MS1hY2E2LTMwNzlkNzk0MTM2YyIsImNsaWVudF9pZCI6ImRMQ2lSLk16d2tOaGExOEltRWN3MEFEbGkwQHNhbmRib3guZ2VuZXN5cy1wZ3Iub3JnIiwic2NvcGUiOlsicmVhZCJdfQ.JtXuqJISeDrbwMYB8WtZ4btgcgG18Exorw297lNlAfc\",\"token_type\":\"bearer\",\"refresh_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJsLm1hbnN1ZXRvQGlycmkub3JnIiwic2NvcGUiOlsicmVhZCJdLCJhdGkiOiI5ODU5NTIyNi01MmYxLTQ1NzEtYWNhNi0zMDc5ZDc5NDEzNmMiLCJleHAiOjE1NzE3MzM2MjksImF1dGhvcml0aWVzIjpbIlJPTEVfRVZFUllPTkUiLCJST0xFX1ZBTElEQVRFRFVTRVIiLCJST0xFX1VTRVIiXSwianRpIjoiMjg1OTI3MjEtNjljZS00YmQ5LTljMDgtM2Q3ZWI3OTg0ODM5IiwiY2xpZW50X2lkIjoiZExDaVIuTXp3a05oYTE4SW1FY3cwQURsaTBAc2FuZGJveC5nZW5lc3lzLXBnci5vcmcifQ.hHtuVYPGhI5FmwoUJ3byH2JlV3NsA5yutNtoD9R5pkE";
		try {
			// String cmd= URLEncoder.encode(url, StandardCharsets.UTF_8.toString()) + " -H
			// 'Accept: application/json' " + " -H 'Authorization: Bearer " + access_token +
			// "'";
			String cmd = url + " -H 'Accept: application/json' " + " -H 'Authorization: Bearer " + access_token + "'";
			log.info("curl " + cmd);
			HttpResponse response = curl(url);
			// System.out.println( response.toString());
			return returnClass(EntityUtils.toString(response.getEntity()));
		} catch (Exception ex) {
			ex.printStackTrace();
			refreshToken();
			try {
				String cmd = url + " -H 'Accept: application/json' " + " -H 'Authorization: Bearer " + access_token
						+ "'";
				log.info("curl " + cmd);
				HttpResponse response = curl(cmd);
				// return EntityUtils.toString(response.getEntity());
				return returnClass(EntityUtils.toString(response.getEntity()));
			} catch (Exception ex2) {
				ex2.printStackTrace();
				throw ex2;
			}
		}
	}

}
