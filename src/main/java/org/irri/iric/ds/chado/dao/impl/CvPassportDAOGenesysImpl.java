package org.irri.iric.ds.chado.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.genesys2.client.oauth.GenesysClient;
import org.genesys2.client.oauth.GenesysTokens;
import org.irri.iric.ds.chado.dao.CvTermDAO;


public class CvPassportDAOGenesysImpl implements CvTermDAO {

	
	
	@Override
	public List getAllTerms() {
		// TODO Auto-generated method stub
		

		
		return null;
	}

	@Override
	public List getAllTerms(BigDecimal cvByName, BigDecimal organismByName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	void init() {
		// Set server URL
		//String baseUrl = "https://www.genesys-pgr.org";
		String baseUrl = "https://api.sandbox.genesys-pgr.org";
		String clientId = "dLCiR.MzwkNha18ImEcw0ADli0@sandbox.genesys-pgr.org";
		String clientSecret = "wGmRAhVplWLEVWlqiMT712PMnaqOa8FN";
		String callbackUrl = "oob";
		String scope = "read"; //"write"; // write scope is required to manage data on Genesys
		GenesysClient genesysClient = new GenesysClient(baseUrl, clientId, clientSecret, callbackUrl, scope);

		if (false) {
			String authorizationUrl = genesysClient.getAuthorizationUrl();
			System.out.println("authorizationUrl=" + authorizationUrl);
			
			
			String verifierCode = "5fOvhT"; // whatever user provides
			try {
				genesysClient.authenticate(verifierCode);
				// Test it with /me
				genesysClient.me();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			System.out.println("accesstoken=" + genesysClient.getTokens().getAccessToken());
			System.out.println("refreshtoken=" + genesysClient.getTokens().getRefreshToken());
		}
		
		String accesstoken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njg5OTMyNjUsInVzZXJfbmFtZSI6ImwubWFuc3VldG9AaXJyaS5vcmciLCJhdXRob3JpdGllcyI6WyJST0xFX0VWRVJZT05FIiwiUk9MRV9WQUxJREFURURVU0VSIiwiUk9MRV9VU0VSIl0sImp0aSI6Ijg1ZTExYmI5LWQ5OTctNDdjMy1iN2UxLTNlZjk1MmE1OTM2ZiIsImNsaWVudF9pZCI6ImRMQ2lSLk16d2tOaGExOEltRWN3MEFEbGkwQHNhbmRib3guZ2VuZXN5cy1wZ3Iub3JnIiwic2NvcGUiOlsicmVhZCJdfQ.a6aU9Kj4jJK8VU8jaqZRIzuzhsS_p52CRqIOlI7PfXM";
		String refreshtoken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJsLm1hbnN1ZXRvQGlycmkub3JnIiwic2NvcGUiOlsicmVhZCJdLCJhdGkiOiI4NWUxMWJiOS1kOTk3LTQ3YzMtYjdlMS0zZWY5NTJhNTkzNmYiLCJleHAiOjE1NzE1NDIwNjUsImF1dGhvcml0aWVzIjpbIlJPTEVfRVZFUllPTkUiLCJST0xFX1ZBTElEQVRFRFVTRVIiLCJST0xFX1VTRVIiXSwianRpIjoiZThkMmZlZDYtYTI3ZS00YmZmLWJhODItZTY0MGVkOGI0MGVlIiwiY2xpZW50X2lkIjoiZExDaVIuTXp3a05oYTE4SW1FY3cwQURsaTBAc2FuZGJveC5nZW5lc3lzLXBnci5vcmcifQ.uAspkKefkPjQvWWjZ6k1E4N7OHpgGlUwi80wHmXF910";

		GenesysTokens genesysTokens = new GenesysTokens();
		genesysTokens.setAccessToken(accesstoken);
		genesysTokens.setRefreshToken(refreshtoken);
		genesysClient.setTokens(genesysTokens);

		try {
			String ret=genesysClient.query("https://api.sandbox.genesys-pgr.org/brapi/v1/commoncropnames?page=0&pageSize=2");
			System.out.println(ret);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new CvPassportDAOGenesysImpl().init();
		System.out.println("...done");
	}
	

}
