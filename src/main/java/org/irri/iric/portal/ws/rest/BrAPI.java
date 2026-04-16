package org.irri.iric.portal.ws.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.irri.iric.ds.chado.domain.CvTermUniqueValues;
import org.irri.iric.ds.chado.domain.Passport;
import org.irri.iric.ds.chado.domain.Phenotype;
import org.irri.iric.ds.chado.domain.Scaffold;
import org.irri.iric.ds.chado.domain.SnpsAllvarsPos;
import org.irri.iric.ds.chado.domain.SnpsStringAllvars;
import org.irri.iric.ds.chado.domain.Variety;
import org.irri.iric.ds.chado.domain.model.Organism;

//import org.springframework.web.bind.annotation.*;

//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

/*
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
*/

//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.util.JSONPObject;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/*
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
*/

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("BrapiWebService")
// @RestController
// @Scope("session")
@Path("/brapi/v1")
public class BrAPI {

	@Autowired
	@Qualifier("GenotypeFacade")
	private GenotypeFacade genotype;

	@Autowired
	@Qualifier("VarietyFacade")
	private VarietyFacade variety;

	@Autowired
	@Qualifier("GenomicsFacade")
	private GenomicsFacade genomics;

	@Autowired
	@Qualifier("ListItems")
	private ListItemsDAO listitemsDAO;

	private String dataset = VarietyFacade.DATASET_SNPINDELV2_IUPAC;
	// private String snpset=GenotypeQueryParams.SNP_CORE;
	// private Set numericTraits=new HashSet();

	private Map<String, int[]> mapMap2GroupsMarkers;
	private Map<String, String[]> mapMap2Names;
	private static Map<String, String[]> mapUsername2PassTokenName;

	private static boolean bStreaming = true;
	private static boolean bAuthenticate = true;
	private static boolean bTSVGzipped = true;

	// private Map<String, Long> mapUser2StartTime=new HashMap();
	// private Map<String, Long> mapBearer2StartTime= AppContext. new HashMap();

	private long deafultTokenLifetimeSec = 900; // 15 mins

	public BrAPI() {
		super();
		// TODO Auto-generated constructor stub

		genotype = (GenotypeFacade) AppContext.checkBean(genotype, "GenotypeFacade");
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");

		mapMap2GroupsMarkers = new HashMap();
		/*
		 * mapMap2GroupsMarkers.put( "3kfiltered", new int[]{12, 4800000});
		 * mapMap2GroupsMarkers.put( "3kcore", new int[]{12, 404000});
		 * mapMap2GroupsMarkers.put( "3kbase", new int[]{12, 18000000});
		 * mapMap2GroupsMarkers.put( "3kall", new int[]{12, 32180725});
		 * mapMap2GroupsMarkers.put( "hdra", new int[]{12, 700000});
		 */
		mapMap2GroupsMarkers.put("3kfiltered", new int[] { 12, 4817964 });
		mapMap2GroupsMarkers.put("3kcore", new int[] { 12, 404388 });
		mapMap2GroupsMarkers.put("3kbase", new int[] { 12, 18128777 });
		mapMap2GroupsMarkers.put("3kall", new int[] { 12, 32064217 });
		mapMap2GroupsMarkers.put("hdra", new int[] { 12, 700000 });

		mapMap2Names = new HashMap();
		mapMap2Names.put("3kfiltered", new String[] { "3k Rice Genome Project filtered SNPs (4.8M SNPs)" });
		mapMap2Names.put("3kcore", new String[] { "3k Rice Genome Project core SNPs (404k SNPs)" });
		mapMap2Names.put("3kbase", new String[] { "3k Rice Genome Project base SNPs (18M SNPs)" });
		mapMap2Names.put("3kall", new String[] { "3k Rice Genome Project all SNPs (32M SNPs)" });
		mapMap2Names.put("hdra", new String[] { "High Density Rice Array HDRA (700k SNPs)" });

		loadToken();
	}

	private void loadToken() {
		mapUsername2PassTokenName = new HashMap();
		mapUsername2PassTokenName.put("snpseek-user",
				new String[] { "snpseek-user-pass", "hdllHJlduie_276ksekdcddlssflje8fjfbhns8", "Brapi Snpseeker" });

		try {
			BufferedReader br = new BufferedReader(new FileReader(AppContext.getFlatfilesDir() + "tokens"));
			String line = "";
			while ((line = br.readLine()) != null) {
				String tokens[] = line.split("\t");
				mapUsername2PassTokenName.put(tokens[0].trim(),
						new String[] { tokens[1].trim(), tokens[2].trim(), tokens[3].trim() });
			}
			br.close();
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
	}

	private Map createResponse(List listresult) {
		return createResponse(listresult, null, null, -1);
	}

	private Map createResponse(List listresult, String page, String pageSize, long totalCount) {
		Map mapMeta = new LinkedHashMap();
		Map mapPagi = new LinkedHashMap();
		List listReturn = null;

		if (page == null || pageSize == null) {
			mapPagi.put("pageSize", listresult.size());
			mapPagi.put("currentPage", 0);
			mapPagi.put("totalCount", listresult.size());
			mapPagi.put("totalPages", 1);
			listReturn = listresult;
		} else {
			PagedList pagelist = checkPaging(listresult, pageSize, page, totalCount);
			mapPagi = pagelist.getMapPaging();
			listReturn = pagelist.getListPaged();
		}

		// mapPagi.put("pageSize", listresult.size());mapPagi.put("currentPage",
		// 0);mapPagi.put("totalCount", listresult.size());mapPagi.put("totalPages", 1);

		// mapPagi.put("pageSize", listresult.size());mapPagi.put("currentPage",
		// 0);mapPagi.put("totalCount", listresult.size());mapPagi.put("totalPages", 1);

		mapMeta.put("pagination", mapPagi);

		mapMeta.put("status", new ArrayList());
		mapMeta.put("datafiles", new ArrayList());

		Map mapResult = new LinkedHashMap();
		mapResult.put("data", listReturn);
		Map mapResponse = new LinkedHashMap();
		mapResponse.put("metadata", mapMeta);
		mapResponse.put("result", mapResult);

		return mapResponse;
	}

	private Response createJSONTokenResponse(Map status, Map mapresult) {
		Map mapResponse = new LinkedHashMap();
		Map mapMeta = new LinkedHashMap();
		mapMeta.put("pagination", null);
		mapMeta.put("status", status);
		mapMeta.put("datafiles", new ArrayList());
		mapResponse.put("metadata", mapMeta);
		mapResponse.put("result", mapresult);

		String errormsg = "";
		try {
			return Response.status(200).entity(new ObjectMapper().writeValueAsString(mapResponse)).build();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errormsg = e.getMessage();
		}
		return Response.status(500).entity(errormsg).build();

	}

	private Map createResponse(Map mapresult) {
		return createResponse(mapresult, null, null);
	}

	private Map createResponse(Map mapresult, String page, String pageSize) {

		List listReturn = null;
		Map mapResponse = new LinkedHashMap();

		Object data = mapresult.get("result");
		if (data == null || !(data instanceof List)) {
			Map mapMeta = new LinkedHashMap();
			// mapMeta.put("pagination", new ArrayList());
			mapMeta.put("pagination", null);
			mapMeta.put("status", null);
			mapMeta.put("datafiles", new ArrayList());
			mapResponse.put("metadata", mapMeta);
			mapResponse.put("result", mapresult);

		} else {
			List listresult = (List) data;
			Map mapMeta = new LinkedHashMap();
			Map mapPagi = new LinkedHashMap();

			if (page == null || pageSize == null) {
				mapPagi.put("pageSize", listresult.size());
				mapPagi.put("currentPage", 0);
				mapPagi.put("totalCount", listresult.size());
				mapPagi.put("totalPages", 1);
				listReturn = listresult;
			} else {
				PagedList pagelist = checkPaging(listresult, pageSize, page);
				mapPagi = pagelist.getMapPaging();
				listReturn = pagelist.getListPaged();
			}

			mapresult.put("result", listReturn);
			mapMeta.put("pagination", mapPagi);

			mapMeta.put("status", null);
			mapMeta.put("datafiles", new ArrayList());
			mapResponse.put("metadata", mapMeta);
			mapResponse.put("result", mapresult);
		}

		/*
		 * Map mapResponse=new LinkedHashMap();
		 * 
		 * Object data= mapresult.get("result"); if(data==null || !(data instanceof
		 * List)) { Map mapMeta=new LinkedHashMap(); //mapMeta.put("pagination", new
		 * ArrayList()); mapMeta.put("pagination", null); mapMeta.put("status", null);
		 * mapMeta.put("datafiles", new ArrayList()); mapResponse.put("metadata",
		 * mapMeta); mapResponse.put("result", mapresult);
		 * 
		 * } else { List listdata=(List)data; Map mapMeta=new LinkedHashMap(); Map
		 * mapPagi=new LinkedHashMap(); mapPagi.put("pageSize",
		 * listdata.size());mapPagi.put("currentPage", 0);mapPagi.put("totalCount",
		 * listdata.size());mapPagi.put("totalPages", 1); mapMeta.put("pagination",
		 * mapPagi);
		 * 
		 * mapMeta.put("status", null); mapMeta.put("datafiles", new ArrayList());
		 * mapResponse.put("metadata", mapMeta); mapResponse.put("result", mapresult); }
		 */

		return mapResponse;
	}

	private Response createFilenameResponse(String filename) {
		Map mapResult = new LinkedHashMap();
		mapResult.put("data", new ArrayList());

		Map mapMeta = new LinkedHashMap();
		Map mapPagi = new LinkedHashMap();
		mapPagi.put("pageSize", 0);
		mapPagi.put("currentPage", 0);
		mapPagi.put("totalCount", 0);
		mapPagi.put("totalPages", 1);

		mapMeta.put("pagination", mapPagi);
		mapMeta.put("status", new ArrayList());
		List listUrl = new ArrayList();
		Map mapUrl = new HashMap();
		mapUrl.put("url", AppContext.getHostname() + "/" + AppContext.getTempFolder() + filename);
		listUrl.add(mapUrl);
		mapMeta.put("datafiles", listUrl);

		Map response = new LinkedHashMap();
		response.put("metadata", mapMeta);
		response.put("result", mapResult);

		String errormsg = "";
		try {
			return Response.status(200).entity(new ObjectMapper().writeValueAsString(createResponse(response))).build();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errormsg = e.getMessage();
		}

		return Response.status(500).entity(errormsg).build();

	}

	private Response createJSONResponse(Map response) {
		return createJSONResponse(response, null, null);
	}

	private Response createJSONResponse(Map response, String pageSize, String page) {

		String pagingstatus = validatePaging(pageSize, page);
		String errormsg = "";
		if (pagingstatus.equals(PAGING_NOTSET)) {

			if (response == null)
				return Response.status(501).build();
			try {
				return Response.status(200).entity(new ObjectMapper().writeValueAsString(createResponse(response)))
						.build();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errormsg = e.getMessage();
			}
			return Response.status(500).entity(errormsg).build();

		} else if (pagingstatus.equals(PAGING_INVALID)) {
			return Response.status(400).entity(PAGING_INVALID).build();
		}

		if (response == null)
			return Response.status(501).build();
		try {
			return Response.status(200)
					.entity(new ObjectMapper().writeValueAsString(createResponse(response, page, pageSize))).build();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errormsg = e.getMessage();
		}
		return Response.status(500).entity(errormsg).build();

		/*
		 * if(response==null) return Response.status(501).build(); String errormsg="";
		 * try { return Response.status(200).entity( new
		 * ObjectMapper().writeValueAsString( createResponse(response) )).build();
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); errormsg=e.getMessage(); } return
		 * Response.status(500).entity(errormsg).build();
		 */
	}

	private Response createJSONResponse(List listresult) {
		return createJSONResponse(listresult, null, null, -1);
	}

	private Response createJSONResponse(List listresult, String pageSize, String page) {
		return createJSONResponse(listresult, pageSize, page, -1);
	}

	private Response createJSONErrorResponse(String message) {
		return createJSONErrorResponse(500, message);
	}

	private Response createJSONErrorResponse(int code, String message) {
		return Response.status(code).entity(message).build();
	}

	private Response createJSONResponse(List listresult, String pageSize, String page, long totalCount) {

		String pagingstatus = validatePaging(pageSize, page);
		String errormsg = "";
		if (pagingstatus.equals(PAGING_NOTSET)) {
			try {
				return Response.status(200).entity(new ObjectMapper().writeValueAsString(createResponse(listresult)))
						.build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errormsg = e.getMessage();
			}

		} else if (pagingstatus.equals(PAGING_INVALID)) {
			return Response.status(400).entity(PAGING_INVALID).build();
		}

		try {
			return Response.status(200).entity(
					new ObjectMapper().writeValueAsString(createResponse(listresult, page, pageSize, totalCount)))
					.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errormsg = e.getMessage();
		}

		return Response.status(500).entity(errormsg).build();
	}

	// private Response createJSONResponse(Map mapResult) {
	// String errormsg="";
	// try {
	// return Response.status(200).entity( new ObjectMapper().writeValueAsString(
	// createResponse(mapResult) )).build();
	//
	// } catch (JsonGenerationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// errormsg=e.getMessage();
	// } catch (JsonMappingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// errormsg=e.getMessage();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// errormsg=e.getMessage();
	// }
	//
	// return Response.status(500).entity(errormsg).build();
	// }

	/*
	 * Get for identifier: /calls/allelematrix
	 * 
	 * Json response: { "metadata": { "pagination": { "pageSize": 1, "currentPage":
	 * 0, "totalCount": 1, "totalPages": 1 }, "status":[], }, "result" : { "data": [
	 * { "call": "allelematrix", "datatypes": ["tsv", "json"], "methods": ["POST",
	 * "GET"] } ] } }
	 */

	private Map createCall(String name, String[] methods, String[] types) {

		Map mapCall = new LinkedHashMap();
		mapCall.put("call", name);
		mapCall.put("datatypes", Arrays.asList(types));
		mapCall.put("methods", Arrays.asList(methods));
		return mapCall;
	}

	@Path("/updateUsers")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response updateUsers() {
		loadToken();
		String errormsg = "";
		try {
			Map map = new HashMap();
			map.put("users", mapUsername2PassTokenName.size());
			return Response.status(200).entity(new ObjectMapper().writeValueAsString(map)).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errormsg = e.getMessage();
		}

		return Response.status(500).entity(errormsg).build();
	}

	@Path("/setStreaming/{status}")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response setStreaming(@DefaultValue("") @PathParam("status") String streamval) {
		if (streamval.equals("on"))
			bStreaming = true;
		else if (streamval.equals("off"))
			bStreaming = false;
		return Response.status(200).entity("bStreaming=" + bStreaming).build();
	}

	@Path("/setAuthentication/{status}")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response setAuthentication(@DefaultValue("") @PathParam("status") String streamval) {
		if (streamval.equals("on"))
			bAuthenticate = true;
		else if (streamval.equals("off"))
			bAuthenticate = false;
		return Response.status(200).entity("bAuthenticate=" + bAuthenticate).build();
	}

	@Path("/setTSVGzipped/{status}")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response setTSVGzipped(@DefaultValue("") @PathParam("status") String streamval) {
		if (streamval.equals("on"))
			bTSVGzipped = true;
		else if (streamval.equals("off"))
			bTSVGzipped = false;
		return Response.status(200).entity("bTSVGzipped=" + bTSVGzipped).build();
	}

	@Path("/calls")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getCalls(@DefaultValue("") @QueryParam("datatype") String datatype) {

		List listAllCalls = new ArrayList();
		Map<String, List> mapType2Calls = new LinkedHashMap();
		mapType2Calls.put("json", new ArrayList());
		mapType2Calls.put("tsv", new ArrayList());
		Map<String, Map> mapName2Call = new LinkedHashMap();

		Map mapCall = createCall("calls", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("calls", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("token", new String[] { "POST", "DELETE" }, new String[] { "json" });
		mapName2Call.put("token", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("germplasm-search", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("germplasm-search", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("germplasm/{germplasmDbid}", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("germplasm/{germplasmDbid}", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("germplasm/{germplasmDbid}/markerprofiles", new String[] { "GET" },
				new String[] { "json" });
		mapName2Call.put("germplasm/{germplasmDbid}/markerprofiles", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("germplasm/{germplasmDbid}/attributes", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("germplasm/{germplasmDbid}/attributes", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("attributes/categories", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("attributes/categories", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("attributes", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("attributes", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("studyTypes", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("studyTypes", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("studies-search", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("studies-search", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("studies/{studiesDbid}", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("studies/{studiesDbid}", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("studies/{studiesDbid}/germplasm", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("studies/{studiesDbid}/germplasm", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("maps", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("maps", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("maps/{mapDbId}", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("maps/{mapDbId}", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("maps/{mapDbId}/positions", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("maps/{mapDbId}/positions", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("maps/{mapDbId}/positions/{linkageGroupId}", new String[] { "GET" },
				new String[] { "json" });
		mapName2Call.put("maps/{mapDbId}/positions{linkageGroupId}", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("markerprofiles", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("markerprofiles", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("markerprofiles/{markerprofileDbId}", new String[] { "GET" }, new String[] { "json" });
		mapName2Call.put("markerprofiles/{markerprofileDbId}", mapCall);
		mapType2Calls.get("json").add(mapCall);
		listAllCalls.add(mapCall);

		mapCall = createCall("allelematrix-search", new String[] { "POST", "GET" }, new String[] { "json", "tsv" });
		mapName2Call.put("allelematrix-search", mapCall);
		mapType2Calls.get("json").add(mapCall);
		mapType2Calls.get("tsv").add(mapCall);
		listAllCalls.add(mapCall);

		if (!datatype.isEmpty()) {
			listAllCalls = mapType2Calls.get(datatype);
		}

		return createJSONResponse(createResponse(listAllCalls));

		// Map mapResponse=new LinkedHashMap();
		// Map mapMeta=new LinkedHashMap();
		// Map mapPagi=new LinkedHashMap();
		// Map mapResult=new LinkedHashMap();
		// mapPagi.put("pageSize", 1);
		// mapPagi.put( "currentPage", 0);
		// mapPagi.put( "totalCount", 1);
		// mapPagi.put( "totalPages", 1);
		//
		//
		//
		//
		// Map mapData=new LinkedHashMap();
		// mapData.put("data", listdata);
		//
		//
		//
		// mapMeta.put( "pagination", mapPagi);
		// mapMeta.put("status",new ArrayList());
		// mapResponse.put("metadata",mapMeta);
		// mapResponse.put("result",mapData);
		//
		//
		// String errormsg="";
		// try {
		// return Response.status(200).entity( new
		// ObjectMapper().writeValueAsString(mapResponse )).build();
		// } catch (JsonGenerationException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// errormsg=e.getMessage();
		// } catch (JsonMappingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// errormsg=e.getMessage();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// errormsg=e.getMessage();
		// }
		//
		// return Response.status(500).entity(errormsg).build();
		//
	}

	// //
	// /brapi/v1/germplasm?name={name}&#38;matchMethod={matchMethod}&#38;include={synonyms}&#38;pageSize={pageSize}&#38;page={page}")
	// @Path("/germplasmOld")
	// @GET
	// @Produces({"application/json", "text/plain"})
	// public Response getGermplasmOld(@QueryParam("name") String name,
	// @DefaultValue("exact") @QueryParam("matchMethod") String matchMethod,
	// @QueryParam("include") String include,
	// @QueryParam("pageSize") String pageSize, @QueryParam("page") String page
	// ) { // throws JSONException {
	//
	// variety=(VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
	//
	// //Possible values are 'case_insensitive', 'exact' (case sensitive),
	// 'wildcard' (which is case insensitive). Wildcard uses both '*' and '%' for
	// any number of characters and '?' for one-character matching. Default is
	// exact.
	//
	// Set vars=null;
	// if(name==null || name.isEmpty()) {
	// vars=variety.getGermplasm(dataset);
	// } else {
	// if(matchMethod.equals("exact") || matchMethod.equals("case_insensitive"))
	// vars=variety.getGermplasmsByName(name, dataset);
	// }
	//
	// Iterator<Variety> itVar=vars.iterator();
	// List listvars=new ArrayList();
	// while(itVar.hasNext()) {
	// Variety var=itVar.next();
	//
	// Map germ=new HashMap();
	//
	// germ.put("germplasmDbId",var.getVarietyId());
	// germ.put("defaultDisplayName",var.getName());
	// germ.put("germplasmName", var.getName());
	// germ.put("accessionNumber", var.getAccession());
	// germ.put("germplasmPUI",
	// "http://oryzasnp.org/iric-portal/_variety.zul?irisid=" +
	// var.getIrisId().toLowerCase());
	// listvars.add(germ);
	// }
	//
	// return createJSONResponse(listvars);
	//
	// }
	//

	// ******* AUTHENTICATION ******************

	public static class RequestToken {

		private String grant_type = "password";
		private String username;
		private String password;
		private String client_id;

		public String getGrant_type() {
			return grant_type;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public String getClient_id() {
			return client_id;
		}

		public void setGrant_type(String grant_type) {
			this.grant_type = grant_type;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setClient_id(String client_id) {
			this.client_id = client_id;
		}

		@Override
		public String toString() {
			
			return grant_type + ", " + username + ", " + password + ", " + client_id;
		}

	}

	@Path("/token")
	@POST
	@Produces({ "application/json", "text/plain" })
	public Response postToken(@RequestBody RequestToken request) {

		String[] passtoken = mapUsername2PassTokenName.get(request.getUsername());
		if (passtoken == null) { // no user dont exists
			Map status = new HashMap();
			status.put("code", "unknown_user");
			return createJSONTokenResponse(status, new HashMap());
		}
		if (!passtoken[0].equals(request.getPassword())) { // wrong passord
			Map status = new HashMap();
			status.put("code", "wrong_password");
			return createJSONTokenResponse(status, new HashMap());
		}

		Map mapBearer2StartTime = AppContext.getMapBearer2StartTime();

		Map mapRep = new LinkedHashMap();
		mapRep.put("userDisplayName", passtoken[2]);
		mapRep.put("access_token", passtoken[1]);
		mapRep.put("expires_in", this.deafultTokenLifetimeSec);
		mapBearer2StartTime.put(passtoken[1], System.currentTimeMillis());

		AppContext.debug("users: " + mapUsername2PassTokenName + "\n/token " + request);
		AppContext.debug("active: " + mapBearer2StartTime);

		return createJSONTokenResponse(new HashMap(), mapRep);
	}

	@Path("/token")
	@DELETE
	@Produces({ "application/json", "text/plain" })
	// public Response deleteToken(@RequestBody RequestToken request) {
	// public Response deleteToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String
	// bearer, @HTTPRequest String req) {
	// public Response deleteToken(@RequestHeader(value = HttpHeaders.AUTHORIZATION,
	// required=false) String bearer) {
	public Response deleteToken(@HeaderParam(value = HttpHeaders.AUTHORIZATION) String bearer) {

		String tokenmsg = checkToken(bearer);
		Map mapStatus = new HashMap();
		if (tokenmsg.equals(OAUTH_ACTIVE)) {
			String token = bearer.replace("Bearer", "").trim();
			Map mapBearer2StartTime = AppContext.getMapBearer2StartTime();
			mapBearer2StartTime.remove(token);
			mapStatus.put("message", "User has been logged out successfully.");
		} else {
			mapStatus.put("code", tokenmsg);
		}
		return createJSONTokenResponse(mapStatus, new HashMap());
	}

	private static String OAUTH_NOTOKEN = "notoken";
	private static String OAUTH_NOTLOGGED = "notlogged";
	private static String OAUTH_ACTIVE = "active";
	private static String OAUTH_TIMEOUT = "timeout";

	private static String PAGING_NOTSUPPORTED = "paging_notsupported";
	private static String PAGING_NOTSET = "paging_notset";
	private static String PAGING_VALID = "paging_valid";
	private static String PAGING_INVALID_PAGESIZE = "paging_maxpagesize";
	private static String PAGING_INVALID = "paging_invalid";

	private String checkToken(String bearer) {

		if (!bAuthenticate)
			return OAUTH_ACTIVE;

		Map mapBearer2StartTime = AppContext.getMapBearer2StartTime();
		AppContext.debug("checking token " + bearer + " in " + mapBearer2StartTime);

		if (bearer == null)
			return OAUTH_NOTOKEN;

		bearer = bearer.replace("Bearer", "").trim();
		if (!mapBearer2StartTime.containsKey(bearer))
			return OAUTH_NOTLOGGED;
		long startms = (Long) mapBearer2StartTime.get(bearer);
		if (System.currentTimeMillis() <= startms + deafultTokenLifetimeSec * 1000) {
			// go
			return OAUTH_ACTIVE;
		} else { // timedout
			return OAUTH_TIMEOUT;
		}
	}

	// ************ GERMPLASM ********************

	private Map convertGermplasmResult(Variety var) {

		Map germ = new LinkedHashMap();
		germ.put("germplasmDbId", var.getVarietyId());
		germ.put("defaultDisplayName", var.getName());
		germ.put("germplasmName", var.getName());
		germ.put("accessionNumber", var.getAccession());
		germ.put("germplasmPUI", "https://snpseekv3.irri-e-extension.com/_variety.zul?irisid="
				+ var.getIrisId().toLowerCase().replace("iris", "").trim());
		germ.put("commonCropName", "rice");

		String set = dataset;
		if (var.getVarietyId().longValue() > 3024)
			set = VarietyFacade.DATASET_SNP_HDRA;
		Iterator<Passport> itpassport = variety.getPassportByVarietyid(var.getVarietyId()).iterator();
		while (itpassport.hasNext()) {
			Passport pass = itpassport.next();
			pass.getDefinition();
			pass.getValue();
		}

		return germ;
	}

	private class PagedList {

		long pageSize;
		long currentPage;
		long totalCount;
		long totalPages;
		List listPaged;
		String message = PAGING_VALID;

		public PagedList(String msg) {
			this.message = msg;
		}

		public PagedList(List listPaged, long pageSize, long currentPage, long totalCount, long totalPages) {
			super();
			this.pageSize = pageSize;
			this.currentPage = currentPage;
			this.totalCount = totalCount;
			this.totalPages = totalPages;
			this.listPaged = listPaged;
		}

		Map getMapPaging() {
			Map mapPagi = new LinkedHashMap();
			mapPagi.put("pageSize", pageSize);
			mapPagi.put("currentPage", currentPage);
			mapPagi.put("totalCount", totalCount);
			mapPagi.put("totalPages", totalPages);
			return mapPagi;
		}
		// mapPagi.put("pageSize", listresult.size());mapPagi.put("currentPage",
		// 0);mapPagi.put("totalCount", listresult.size());mapPagi.put("totalPages", 1);

		List getListPaged() {
			return listPaged;
		}

		String getMessage() {
			return message;
		}

	}

	private String validatePaging(String pageSize, String page) {
		return validatePaging(pageSize, page, -1);
	}

	private String validatePaging(String pageSize, String page, long maxsize) {

		AppContext.debug("validatePaging pageSize:" + pageSize + " page:" + page);
		if (pageSize != null && !pageSize.isEmpty() && page != null && !page.isEmpty()) {
			if (maxsize > 0 && Long.valueOf(pageSize) > maxsize)
				return PAGING_INVALID_PAGESIZE + " max:" + maxsize;
			return PAGING_VALID;
		}
		if (pageSize != null && !pageSize.isEmpty() && (page == null || page.isEmpty()))
			return PAGING_INVALID;
		else if (page != null && !page.isEmpty() && (pageSize == null || pageSize.isEmpty()))
			return PAGING_INVALID;
		return PAGING_NOTSET;

	}

	private PagedList checkPaging(List listAll, String pageSize, String page) {
		return checkPaging(listAll, pageSize, page, -1);
	}

	private PagedList checkPaging(List listAll, String pageSize, String page, long totalCount) {

		if (pageSize != null && !pageSize.isEmpty() && page != null && !page.isEmpty()) {

			if (totalCount > -1) {
				int lpagesize = Integer.valueOf(pageSize);
				int lpage = Integer.valueOf(page);
				int npages = (int) totalCount / lpagesize;
				int nlastcount = (int) totalCount % lpagesize;
				if (nlastcount > 0)
					npages++;

				AppContext
						.debug("total:" + totalCount + " pagesize:" + pageSize + " currentpage:" + page + "/" + npages);
				return new PagedList(listAll, listAll.size(), lpage, totalCount, npages);

			} else {
				// do paging
				int lpagesize = Integer.valueOf(pageSize);
				int lpage = Integer.valueOf(page);
				int npages = listAll.size() / lpagesize;
				int nlastcount = listAll.size() % lpagesize;
				if (nlastcount > 0)
					npages++;

				AppContext.debug(
						"total:" + listAll.size() + " pagesize:" + pageSize + " currentpage:" + page + "/" + npages);

				int startidx = lpage * lpagesize;
				int endidx = startidx + lpagesize - 1;

				List listPaged = null;
				if (endidx > listAll.size() - 1) {
					listPaged = listAll.subList(startidx, listAll.size());
				} else {
					listPaged = listAll.subList(startidx, endidx + 1);
				}
				return new PagedList(listPaged, listPaged.size(), lpage, listAll.size(), npages);
			}

		} else if (pageSize != null && !pageSize.isEmpty() && (page == null || page.isEmpty())) {
			return new PagedList(PAGING_INVALID);
		} else if (page != null && !page.isEmpty() && (pageSize == null || pageSize.isEmpty())) {
			return new PagedList(PAGING_INVALID);
		} else {
			// no paging, use default
			AppContext.debug("total:" + listAll.size() + " pagesize:" + listAll.size() + " currentpage: 0/1");

			return new PagedList(listAll, listAll.size(), 0, listAll.size(), 1);
		}

		// return PAGING_VALID;

	}

	/*
	 * 'germplasmPUI' : 'http://...', // (optional, text,
	 * `http://data.inra.fr/accession/234Col342`) ... The name or synonym of
	 * external genebank accession identifier 'germplasmDbId' : 986, // (optional,
	 * text, `986`) ... The name or synonym of external genebank accession
	 * identifier 'germplasmSpecies' : 'tomato', // (optional, text, `aestivum`) ...
	 * The name or synonym of genus or species ( merge with below ?)
	 * 'germplasmGenus' : 'Solanum lycopersicum', //(optional, text, `Triticum,
	 * Hordeum`) ... The name or synonym of genus or species 'germplasmName' :
	 * 'XYZ1', // (optional, text, `Triticum, Hordeum`) ... The name or synonym of
	 * the accession // the following need review (should be removed?)
	 * //'germplasmSubTaxa (optional, text, `cv. Charger, subsp. aestivum`) ... The
	 * name or synonym of MCPD subTaxa. Exact Match, abreviations must be MCPD
	 * compliant (�subsp.� for 'subspecies'; �convar.� for 'convariety' �var.� for
	 * variety; �f.� for 'form'; �Group� for �cultivar group�) //+ panel (optional,
	 * text, `breedingProgramPanel2011`) ... The name of a specific panel //+
	 * collection (optional, text, `BRCCollection_Wheat`) ... The name of a specific
	 * Collection 'pageSize' : 100, // (optional, integer, `1000`) ... The size of
	 * the pages to be returned. Default is `1000`. 'page':
	 */

	@Path("/germplasm-search")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getGermplasmSearch(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@QueryParam("germplasmName") String germplasmName, @QueryParam("germplasmPUI") String germplasmPUI,
			@QueryParam("germplasmDbId") String germplasmDbId, @QueryParam("pageSize") String pageSize,
			@QueryParam("page") String page) { // throws JSONException {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		String pagingstatus = validatePaging(pageSize, page);

		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");

		Set vars = null;
		if ((germplasmName == null || germplasmName.isEmpty()) && (germplasmPUI == null || germplasmPUI.isEmpty())
				&& (germplasmDbId == null || germplasmDbId.isEmpty())) {
			vars = variety.getGermplasm(dataset);
		} else {
			vars = new LinkedHashSet();
			if (germplasmPUI != null && !germplasmPUI.isEmpty()
					&& germplasmPUI.toLowerCase().contains("snp-seek.irri.org/_variety.zul?irisid=")) {
				String irisid = germplasmPUI.split("=")[1].trim();
				Set listNames = new HashSet();
				listNames.add("IRIS " + irisid);
				Set s = new HashSet();
				s.add(dataset);
				vars.addAll(variety.getGermplasmByIrisIds(listNames, s));
			} else if (germplasmName != null && !germplasmName.isEmpty()) {
				Set listNames = new HashSet();
				listNames.add(germplasmName);
				vars.addAll(variety.getGermplasmByNamesLike(listNames, dataset));
			} else if (germplasmDbId != null && !germplasmDbId.isEmpty()) {
				vars.add(variety.getGermplasmById(BigDecimal.valueOf(Long.valueOf(germplasmDbId)), dataset));
			}
		}

		Iterator<Variety> itVar = vars.iterator();
		List listvars = new ArrayList();
		while (itVar.hasNext()) {
			Variety var = itVar.next();
			listvars.add(convertGermplasmResult(var));
		}

		return createJSONResponse(listvars, pageSize, page);
	}

	@Path("/germplasm/{germplasmDbId}")
	@GET
	@Produces({ "application/json", "text/plain" })
	@RequestMapping(headers = HttpHeaders.AUTHORIZATION)
	public Response getGermplasmByDbId(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer, // @RequestHeader("Authorization")
																								// String bearer,
			// public Response getGermplasmByDbId(@RequestHeader Map<String, String> header,
			// //@RequestHeader("Authorization") String bearer,
			@PathParam("germplasmDbId") String germplasmDbId) { // throws JSONException {

		// AppContext.debug("headers=" + header );
		// String tokenmsg = checkToken(header.get(HttpHeaders.AUTHORIZATION));
		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
		Variety var = variety.getGermplasmById(BigDecimal.valueOf(Long.valueOf(germplasmDbId)), dataset);
		return createJSONResponse(convertGermplasmResult(var));

	}

	// GET
	// http://private-c2c4f-brapi.apiary-mock.com/brapi/v1/germplasm/id/markerprofiles
	@Path("/germplasm/{germplasmDbId}/markerprofiles")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getGermplasmMarkerprofiles(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@PathParam("germplasmDbId") String germplasmDbId) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		List list = new ArrayList();
		long id = Long.valueOf(germplasmDbId);
		if (id <= 3024) {
			list.add(Long.toString(id)); // filtered
			list.add(Long.toString(id + 10000)); // core
			list.add(Long.toString(id + 20000)); // base
			list.add(Long.toString(id + 30000)); // all
		} else
			list.add(Long.toString(id));
		return createJSONResponse(list, null, null);

	}

	@Path("/germplasm/{germplasmDbId}/attributes")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getGermplasmAttributes(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@PathParam("germplasmDbId") String germplasmDbId, @QueryParam("attributeList") String attrlist,
			@QueryParam("pageSize") String pageSize, @QueryParam("page") String page) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		String pagingstatus = validatePaging(pageSize, page);
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		List list = new ArrayList();

		long id = Long.valueOf(germplasmDbId);
		String set = dataset;
		if (id <= 3024) {
			set = dataset;
		} else
			set = VarietyFacade.DATASET_SNP_HDRA;

		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		Variety var = listitemsDAO.getMapId2Variety(set).get(BigDecimal.valueOf(id));

		List listPhen = null;
		if (attrlist == null) {
			;
			Set s = new HashSet();
			s.add(dataset);
			listPhen = variety.getPhenotypesByGermplasm(var, s);
		} else {
			String attrarr[] = attrlist.split(",");
			listPhen = new ArrayList();
			for (int i = 0; i < attrarr.length; i++) {
				listPhen.add(variety.getPhenotypesByGermplasm(var, set, attrarr[i]));
			}
		}

		List listJson = new ArrayList();
		Iterator<Phenotype> itPhen = listPhen.iterator();
		while (itPhen.hasNext()) {
			Phenotype phen = itPhen.next();
			Map map = new LinkedHashMap();
			map.put("attributeDbId", phen.getPhenotypeId());
			map.put("attributeName", phen.getDefinition());
			map.put("attributeCode", phen.getName());
			if (phen.getQualValue() != null)
				map.put("value", phen.getQualValue());
			else if (phen.getQuanValue() != null) {
				map.put("value", phen.getQuanValue());
			}

			listJson.add(map);
		}

		AppContext.debug(listPhen.size() + " phenotypes");

		Map mapResult = new LinkedHashMap();
		mapResult.put("germplasmDbId", germplasmDbId);
		mapResult.put("data", listJson);
		return createJSONResponse(mapResult, pageSize, page);

	}

	// ******************* GERMPLASM ATTRIBUTES

	@Path("/attributes/categories")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getAttributeCategories(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		Map map = new LinkedHashMap();
		List list = new ArrayList();
		map.put("attributeCategoryDbId", "1");
		map.put("name", "Morphological");
		list.add(map);
		return createJSONResponse(list);

	}

	@Path("/attributes")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getAttributeCategories(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@QueryParam("attributeCategoryDbId") String attributeCategoryDbId) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		try {
			if (attributeCategoryDbId == null || attributeCategoryDbId.equals("1")) {

				variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
				Map<String, BigDecimal> mapPhen2Id = variety.getPhenotypeDefinitions(dataset);

				List listPhen = new ArrayList();
				Iterator<String> itPhen = mapPhen2Id.keySet().iterator();
				while (itPhen.hasNext()) {
					String phen = itPhen.next();
					Map mapPhen = new LinkedHashMap();

					mapPhen.put("attributeDbId", mapPhen2Id.get(phen).toString());
					mapPhen.put("attributeCategoryDbId", attributeCategoryDbId);
					mapPhen.put("name", phen);

					int phenotypevalue_type = -1;
					Set s = new HashSet();
					s.add(dataset);
					Object retobj[] = variety.getPhenotypeUniqueValues(phen, s); // variety.getPhenotypeUniqueValues(
																					// listboxPhenotypes.getSelectedItem().getLabel()
																					// ).iterator();
					Iterator<CvTermUniqueValues> itValues = ((Set) retobj[0]).iterator();
					phenotypevalue_type = (Integer) retobj[1];
					List listValues = new ArrayList();
					while (itValues.hasNext()) {
						CvTermUniqueValues value = itValues.next();
						if (value == null) {
							AppContext.debug("null value");
							continue;
						}
						listValues.add(value.getValue());
					}
					if (variety.getQuantTraits(dataset).contains(phen))
						mapPhen.put("datatype", "Numeric");
					else
						mapPhen.put("datatype", "Categorical");

					mapPhen.put("values", listValues);

					listPhen.add(mapPhen);
				}

				return createJSONResponse(listPhen);

			}
		} catch (Exception ex) {
			return createJSONErrorResponse(ex.getMessage());
		}

		return createJSONResponse(new ArrayList());

	}

	// ************ MAPS ********************

	/*
	 * {"path":"/brapi/v1/maps","description":"","operations":[{
	 * "nickname":"GET - get all maps","method":"GET","summary":"get all maps",
	 * "notes":"get maps for all species","type":"string","produces":[
	 * "application/json"], "parameters":[
	 * {"name":"species","paramType":"query","allowMultiple":false,
	 * "description":"Species Default:rice", "required":false,"type":"string" },
	 * {"name":"pageSize","paramType":"query","allowMultiple":false,"required":false
	 * ,"type":"int"},
	 * {"name":"page","paramType":"query","allowMultiple":false,"required":false,
	 * "type":"int"} ]}]},
	 * 
	 * 
	 * {"path":"/brapi/v1/maps/{mapId}","description":"","operations":[{
	 * "nickname":"GET - get map linkage groups","method":"GET",
	 * "summary":"Get map linkage groups",
	 * "notes":"Get map linkage groups","type":"string","produces":[
	 * "application/json"],"parameters":[
	 * {"name":"mapId","paramType":"path","allowMultiple":false,"required":true,
	 * "type":"string" },
	 * {"name":"pageSize","paramType":"query","allowMultiple":false,"required":false
	 * ,"type":"int"},
	 * {"name":"page","paramType":"query","allowMultiple":false,"required":false,
	 * "type":"int"}
	 * 
	 * ]}]},
	 * 
	 * {"path":"/brapi/v1/maps/{mapId}/positions","description":"","operations":[{
	 * "nickname":"GET - get map markers","method":"GET","summary":"Get map markers"
	 * , "notes":"Get map markers","type":"string","produces":["application/json"],
	 * "parameters":[
	 * {"name":"mapId","paramType":"path","allowMultiple":false,"required":true,
	 * "type":"string" },
	 * {"name":"linkageGroupId","paramType":"query","allowMultiple":false,"required"
	 * :false,"type":"string" },
	 * {"name":"format","paramType":"query","allowMultiple":false,"required":false,
	 * "type":"string", "description":"result format [json (default), tsv]" },
	 * {"name":"pageSize","paramType":"query","allowMultiple":false,"required":false
	 * ,"type":"int"},
	 * {"name":"page","paramType":"query","allowMultiple":false,"required":false,
	 * "type":"int"} ]}]},
	 * 
	 * 
	 * {"path":"/brapi/v1/maps/{mapId}/positions/{linkageGroupId}","description":"",
	 * "operations":[{"nickname":"GET - getMarkersByRangePosition","method":"GET",
	 * "summary":"Get markers in linkage group in range of positions",
	 * "notes":"Get markers in linkage group in range of positions","type":"string",
	 * "produces":["application/json"],"parameters":[
	 * {"name":"mapId","paramType":"path","allowMultiple":false,"required":true,
	 * "type":"string", "description":"set to 9 (Rice Genome IRGSPv1 )" },
	 * {"name":"linkageGroupId","paramType":"path","allowMultiple":false,"required":
	 * true,"type":"string", "description":"chromosome [chr01, chr02, .. chr12]" },
	 * {"name":"min","paramType":"query","allowMultiple":false,"required":false,
	 * "type":"long", "description":"start bp" },
	 * {"name":"max","paramType":"query","allowMultiple":false,"required":false,
	 * "type":"long", "description":"stop bp" },
	 * {"name":"format","paramType":"query","allowMultiple":false,"required":false,
	 * "type":"string", "description":"result format [json (default), tsv]" },
	 * {"name":"pageSize","paramType":"query","allowMultiple":false,"required":false
	 * ,"type":"int"},
	 * {"name":"page","paramType":"query","allowMultiple":false,"required":false,
	 * "type":"int"}
	 * 
	 * ]}]},
	 */

	@Path("/maps")
	@GET
	// @Produces({"application/json", "text/plain"})
	@Produces({ "application/json", "text/plain" })
	public Response getMaps(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@QueryParam("pageSize") String pageSize, @QueryParam("page") String page) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		// genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		// genotype=(GenotypeFacade)AppContext.checkBean(genoM
		/*
		 * Map mapRef2Conts=new HashMap(); mapRef2Conts.put("Japonica nipponbare",12);
		 * mapRef2Conts.put("IR64-21",2919); mapRef2Conts.put("93-11",12730);
		 * mapRef2Conts.put("DJ123",2819); mapRef2Conts.put("Kasalath",13);
		 */

		// TODO: READ VARIANT SET TABLE

		List listMaps = new ArrayList();
		try {
			Iterator<String> itMap = mapMap2Names.keySet().iterator();
			while (itMap.hasNext()) {
				String mapname = itMap.next();

				Map mapMap = new HashMap();
				mapMap.put("mapDbId", mapname);
				mapMap.put("name", mapMap2Names.get(mapname)[0]);
				mapMap.put("species", "Oryza sativa");
				mapMap.put("type", "Physical");
				mapMap.put("unit", "bp");
				mapMap.put("markerCount", mapMap2GroupsMarkers.get(mapname)[1]); // 32180725
				mapMap.put("linkageGroupCount", mapMap2GroupsMarkers.get(mapname)[0]);
				mapMap.put("comments", mapMap2Names.get(mapname)[0] + ". SNPs called on IRGSP v1. Nipponbare genome");
				listMaps.add(mapMap);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return createJSONResponse(listMaps);

	}

	// GEThttp://private-c2c4f-brapi.apiary-mock.com/brapi/v1/maps/mapId
	@Path("/maps/{mapDbId}")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getMapDetails(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@PathParam("mapDbId") String mapId) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		int countidx = 0;

		if (mapId.equals("3kfiltered"))
			countidx = 3;
		else if (mapId.equals("3kall"))
			countidx = 0;
		else if (mapId.equals("3kcore"))
			countidx = 1;
		else if (mapId.equals("3kbase"))
			countidx = 4;
		else if (mapId.equals("hdra"))
			countidx = 2;

		Map mapN = new HashMap();
		// 3k all snps 3k core hdra filtered base
		mapN.put("chr01", new int[] { 3299626, 42466, 82248, 576146, 1911385 });
		mapN.put("chr02", new int[] { 2744926, 39922, 69886, 432353, 1541096 });
		mapN.put("chr03", new int[] { 2639545, 30024, 67500, 399231, 1459157 });
		mapN.put("chr04", new int[] { 3187893, 42598, 62651, 422737, 1803432 });
		mapN.put("chr05", new int[] { 2446560, 28299, 54788, 345153, 1275199 });
		mapN.put("chr06", new int[] { 2666928, 29966, 57569, 445466, 1523230 });
		mapN.put("chr07", new int[] { 2553359, 28476, 53534, 383360, 1430893 });
		mapN.put("chr08", new int[] { 2672286, 36221, 54705, 410405, 1481796 });
		mapN.put("chr09", new int[] { 2053103, 23801, 43002, 285477, 1156209 });
		mapN.put("chr10", new int[] { 2170855, 23548, 43570, 323654, 1206751 });
		mapN.put("chr11", new int[] { 2927740, 47497, 60213, 435654, 1735748 });
		mapN.put("chr12", new int[] { 2701396, 31570, 50334, 358328, 1603881 });

		// List<Scaffold> listConts = listitemsDAO.getContigs(org.getName());
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(AppContext.getDefaultOrganism());

		List listLG = new ArrayList();
		Iterator<Scaffold> itConts = listConts.iterator();
		while (itConts.hasNext()) {
			Scaffold sc = itConts.next();
			Map mapLG = new LinkedHashMap();
			mapLG.put("linkageGroupDbId", sc.getName());
			mapLG.put("maxPosition", sc.getLength());
			mapLG.put("numberMarkers", ((int[]) mapN.get(sc.getName()))[countidx]);
			listLG.add(mapLG);
		}

		Map mapMap = new LinkedHashMap();
		mapMap.put("mapDbId", mapId);
		mapMap.put("name", AppContext.getDefaultOrganism() + " reference genome");
		mapMap.put("species", AppContext.getDefaultOrganism());
		mapMap.put("type", "Physical");
		mapMap.put("unit", "bp");
		mapMap.put("linkageGroups", listLG);

		return createJSONResponse(mapMap);
		/*
		 * "result": { "mapId": "id", "name": "Some map", "type": "Genetic", "unit":
		 * "cM", "linkageGroups": [ { "linkageGroupId": 1, "numberMarkers": 100000,
		 * "maxPosition": 10000000 }, { "linkageGroupId": 2, "numberMarkers": 1247,
		 * "maxPostion": 12347889 } ] }
		 */
	}

	// GEThttp://private-c2c4f-brapi.apiary-mock.com/brapi/v1/maps?species=speciesId&pageSize=pageSize&page=page&type=type
	@Path("/maps/species")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getMapsSpecies(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		// genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		// genotype=(GenotypeFacade)AppContext.checkBean(genoM
		Map mapRef2Conts = new HashMap();
		mapRef2Conts.put("Japonica nipponbare", 12);
		mapRef2Conts.put("IR64-21", 2919);
		mapRef2Conts.put("93-11", 12730);
		mapRef2Conts.put("DJ123", 2819);
		mapRef2Conts.put("Kasalath", 13);

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		List listMaps = new ArrayList();
		try {
			List orgs = listitemsDAO.getOrganisms();

			Iterator<Organism> itOrgs = orgs.iterator();
			while (itOrgs.hasNext()) {
				Organism org = itOrgs.next();

				if (!org.getOrganismId().equals(BigDecimal.valueOf(9)))
					continue;
				// List listConts = listitemsDAO.getContigs(org.getName());

				Map mapMap = new HashMap();
				mapMap.put("mapDbId", org.getOrganismId());
				mapMap.put("name", org.getName() + " reference genome");
				mapMap.put("species", "Oryza sativa");
				mapMap.put("type", "Physical");
				mapMap.put("unit", "bp");

				if (org.getOrganismId().equals(BigDecimal.valueOf(9)))
					// mapMap.put("markerCount", 1055366); // 32180725
					mapMap.put("markerCount", 32180725); // 32180725
				mapMap.put("linkageGroupCount", mapRef2Conts.get(org.getName()));
				mapMap.put("comments", "This is the Nipponbare rice genome IRGSP v1");

				listMaps.add(mapMap);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return createJSONResponse(listMaps);

	}

	// GEThttp://private-c2c4f-brapi.apiary-mock.com/brapi/v1/maps/mapId
	@Path("/mapsOld/{mapDbId}")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getMapDetailsOld(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@PathParam("mapDbId") String mapId) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		// genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		int orgid = Integer.valueOf(mapId);
		Organism org = listitemsDAO.getOrganismById(orgid);

		Map mapN = new HashMap();
		// 3k all snps 3k core hdra filtered
		mapN.put("chr01", new int[] { 3299626, 42466, 82248, 576146, 119269 });
		mapN.put("chr02", new int[] { 2744926, 39922, 69886, 432353, 104188 });
		mapN.put("chr03", new int[] { 2639545, 30024, 67500, 399231, 92918 });
		mapN.put("chr04", new int[] { 3187893, 42598, 62651, 422737, 100586 });
		mapN.put("chr05", new int[] { 2446560, 28299, 54788, 345153, 79415 });
		mapN.put("chr06", new int[] { 2666928, 29966, 57569, 445466, 83968 });
		mapN.put("chr07", new int[] { 2553359, 28476, 53534, 383360, 78484 });
		mapN.put("chr08", new int[] { 2672286, 36221, 54705, 410405, 86889 });
		mapN.put("chr09", new int[] { 2053103, 23801, 43002, 285477, 63961 });
		mapN.put("chr10", new int[] { 2170855, 23548, 43570, 323654, 64391 });
		mapN.put("chr11", new int[] { 2927740, 47497, 60213, 435654, 102765 });
		mapN.put("chr12", new int[] { 2701396, 31570, 50334, 358328, 78532 });

		// List<Scaffold> listConts = listitemsDAO.getContigs(org.getName());
		List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());

		List listLG = new ArrayList();
		Iterator<Scaffold> itConts = listConts.iterator();
		while (itConts.hasNext()) {
			Scaffold sc = itConts.next();
			Map mapLG = new LinkedHashMap();
			mapLG.put("linkageGroupDbId", sc.getName());
			mapLG.put("maxPosition", sc.getLength());
			if (mapId.equals("9"))
				mapLG.put("numberMarkers", ((int[]) mapN.get(sc.getName()))[4]);

			listLG.add(mapLG);
		}

		Map mapMap = new LinkedHashMap();
		mapMap.put("mapDbId", mapId);
		mapMap.put("name", org.getName() + " reference genome");
		mapMap.put("species", org.getName());
		mapMap.put("type", "Physical");
		mapMap.put("unit", "bp");
		mapMap.put("linkageGroups", listLG);

		return createJSONResponse(mapMap);
		/*
		 * "result": { "mapId": "id", "name": "Some map", "type": "Genetic", "unit":
		 * "cM", "linkageGroups": [ { "linkageGroupId": 1, "numberMarkers": 100000,
		 * "maxPosition": 10000000 }, { "linkageGroupId": 2, "numberMarkers": 1247,
		 * "maxPostion": 12347889 } ] }
		 */
	}

	private GenotypeQueryParams setStudy(GenotypeQueryParams params, String studyId) {
		if (studyId != null) {
			Set s = new HashSet();
			s.add(studyId);
			params.setSnpSet(s);
			if (studyId.startsWith("3k"))
				params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
			else
				params.setDataset(studyId);

		} else {
			Set s = new HashSet();
			s.add("3kfiltered");
			params.setSnpSet(s);
			params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
		}
		AppContext.debug("BRAPI setDataset: " + params.getDataset());

		return params;
	}

	/*
	 * private GenotypeQueryParams setStudy(GenotypeQueryParams params, String
	 * studyId) { if(studyId!=null) { if(studyId.equals("3kfiltered")) {
	 * params.setSnpSet( GenotypeQueryParams.SNP_FILTERED);
	 * params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC); } else
	 * if(studyId.equals("3kcore")) { params.setSnpSet(
	 * GenotypeQueryParams.SNP_CORE);
	 * params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC); } else
	 * if(studyId.equals("3kbase")) { params.setSnpSet(
	 * GenotypeQueryParams.SNP_BASE);
	 * params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC); } else
	 * if(studyId.equals("3kall")) { params.setSnpSet( GenotypeQueryParams.SNP_ALL);
	 * params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC); } else
	 * if(studyId.equals("hdra")) { params.setSnpSet(
	 * GenotypeQueryParams.SNP_FILTERED);
	 * params.setDataset(VarietyFacade.DATASET_SNP_HDRA); } } else {
	 * 
	 * params.setSnpSet( GenotypeQueryParams.SNP_FILTERED);
	 * params.setDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC); }
	 * 
	 * AppContext.debug("BRAPI setDataset: " + params.getDataset());
	 * 
	 * return params; }
	 */

	private String createMarkerName(SnpsAllvarsPos pos) {
		return pos.getContig() + "_" + pos.getPosition();
	}

	/*
	 * private String getSnpset(String studyId) { if (studyId.equals("3kfiltered"))
	 * return GenotypeQueryParams.SNP_FILTERED; if (studyId.equals("3kbase")) return
	 * GenotypeQueryParams.SNP_BASE; if (studyId.equals("3kall")) return
	 * GenotypeQueryParams.SNP_ALL; if (studyId.equals("3kcore")) return
	 * GenotypeQueryParams.SNP_CORE; return GenotypeQueryParams.SNP_ALL;
	 * 
	 * }
	 */

	private Set getSnpset(String studyId) {
		Set s = new HashSet();
		s.add(studyId);
		return s;
	}

	private Set getDataset(String studyId) {
		Set s = new HashSet();
		if (studyId.startsWith("3k"))
			s.add(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
		else
			s.add(studyId);
		return s;
	}

	private Set getGenotypeRun(String studyId) {
		Set s = new HashSet();
		if (studyId.startsWith("3k"))
			s.add(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
		else
			s.add(studyId);
		return s;
	}

	@Path("/maps/{mapDbId}/positions")
	@GET
	@Produces({ "application/json", "text/plain" })
	/// maps/mapId/positions/linkageGroupId?min=min&max=max&pageSize=pageSize&pageNumber=pageNumber
	// "/maps/{mapId}/positions/{linkageGroupId}?min={min}&#38;max={max}&#38;pageSize={pageSize}&#38;pageNumber={pageNumber}")
	public Response getMarkers(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@PathParam("mapDbId") String mapId, @DefaultValue("3kfiltered") @QueryParam("studyDbId") String studyId,
			@DefaultValue("") @QueryParam("linkageGroupDbId") String linkageGroupDbId,
			// @DefaultValue("json") @QueryParam("format") String format,
			@DefaultValue("") @QueryParam("pageSize") String pageSize,
			@DefaultValue("") @QueryParam("page") String page) { // throws JSONException {

		String format = "json";

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		String pagingstatus = validatePaging(pageSize, page);
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		genotype = (GenotypeFacade) AppContext.checkBean(genotype, "GenotypeFacade");
		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		// genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		// int orgid=Integer.valueOf(mapId);
		Organism org = listitemsDAO.getOrganismById(9);
		

		// List<Scaffold> listConts = listitemsDAO.getContigs(org.getName());
		// List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());

		List listresult = new ArrayList();

		BufferedWriter bw = null;
		String tempfile = null;
		String delimiter = "\t";
		if (!format.equals("json")) {
			tempfile = "markers_" + AppContext.createTempFilename() + "." + format;
			try {
				bw = new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
			} catch (Exception ex) {
				if (format.equals("csv"))
					delimiter = ",";
				ex.printStackTrace();
			}
		}

		studyId = mapId;

		List listLG = new ArrayList();
		// Iterator<Scaffold> itConts = listConts.iterator();
		// while(itConts.hasNext()) {
		// Scaffold sc=itConts.next();

		GenotypeQueryParams params = new GenotypeQueryParams(
				// null, sc.getName(), 1L, sc.getLength(),
				null, null, null, null, true, false, getSnpset(studyId), getDataset(studyId), getGenotypeRun(studyId),
				false, null, null, null, false, false, org);

		params = setStudy(params, studyId);
		if (pagingstatus.equals(PAGING_VALID))
			params.setPaging(Long.valueOf(page), Long.valueOf(pageSize));

		if (linkageGroupDbId != null && !linkageGroupDbId.isEmpty())
			params.setsChr(linkageGroupDbId);

		long totalCount = -1;
		if (pagingstatus.equals(PAGING_VALID)) {
			totalCount = genotype.countSNPPoslist(params);
			if (totalCount < 0)
				throw new RuntimeException("countSNPPoslist not implemented");
		}

		Iterator<SnpsAllvarsPos> it = genotype.getSNPPoslist(params).iterator();
		while (it.hasNext()) {
			SnpsAllvarsPos pos = it.next();
			if (bw == null) {
				Map mapMarker = new LinkedHashMap();
				mapMarker.put("markerDbId", createMarkerName(pos));
				mapMarker.put("markerName", createMarkerName(pos));
				mapMarker.put("location", pos.getPosition());
				mapMarker.put("linkageGroup", pos.getContig());
				listresult.add(mapMarker);
			} else {
				try {
					bw.append(createMarkerName(pos)).append(delimiter).append(pos.getContig()).append(delimiter)
							.append(pos.getPosition().toString());
					if (it.hasNext())
						bw.append("\n");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
		// }

		String errormsg = "";
		if (bw == null)
			return createJSONResponse(listresult, pageSize, page, totalCount);
		else {
			try {
				bw.flush();
				bw.close();
				return createFilenameResponse(tempfile);
			} catch (Exception ex) {
				ex.printStackTrace();
				errormsg = ex.getMessage();
			}
		}

		return Response.status(500).entity(errormsg).build();

	}

	// public Response getMarkersOld(@HeaderParam(HttpHeaders.AUTHORIZATION) String
	// bearer, @PathParam("mapDbId") String mapId,
	// @DefaultValue("3kfiltered") @QueryParam("studyDbId") String studyId,
	// @DefaultValue("'") @QueryParam("linkageGroupDbId") String linkageGroupDbId,
	// //@DefaultValue("json") @QueryParam("format") String format,
	// @DefaultValue("") @QueryParam("pageSize") String pageSize,
	// @DefaultValue("") @QueryParam("page") String page
	// ) { // throws JSONException {
	//
	//
	// String format="json";
	//
	// String tokenmsg = checkToken(bearer);
	// if(!tokenmsg.equals(OAUTH_ACTIVE)) {
	// Map mapStatus=new LinkedHashMap();
	// mapStatus.put("code", tokenmsg);
	// return createJSONTokenResponse(mapStatus,new HashMap());
	// }
	//
	// String pagingstatus=validatePaging(pageSize,page);
	// if(! (pagingstatus.equals(PAGING_VALID) ||
	// pagingstatus.equals(PAGING_NOTSET))) return
	// Response.status(400).entity(pagingstatus).build();
	//
	//
	// genotype=(GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
	//
	// listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
	// //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
	// //int orgid=Integer.valueOf(mapId);
	// Organism org = listitemsDAO.getOrganismById( 9 );
	//
	//
	// //List<Scaffold> listConts = listitemsDAO.getContigs(org.getName());
	// List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());
	//
	// List listresult=new ArrayList();
	//
	// BufferedWriter bw=null;
	// String tempfile=null;
	// String delimiter="\t";
	// if(!format.equals("json") ) {
	// tempfile="markers_" + AppContext.createTempFilename() + "." + format;
	// try {
	// bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
	// } catch(Exception ex) {
	// if(format.equals("csv") ) delimiter=",";
	// ex.printStackTrace();
	// }
	// }
	//
	// studyId=mapId;
	//
	// List listLG=new ArrayList();
	// Iterator<Scaffold> itConts = listConts.iterator();
	// while(itConts.hasNext()) {
	// Scaffold sc=itConts.next();
	//
	// GenotypeQueryParams params=new GenotypeQueryParams(
	// null, sc.getName(), 1L, sc.getLength(),
	// true, false,false,
	// false, null, null, null, false, false);
	//
	// params = setStudy(params, studyId);
	//
	// if(linkageGroupDbId!=null && !linkageGroupDbId.isEmpty())
	// params.setsChr(linkageGroupDbId);
	//
	//
	// Iterator<SnpsAllvarsPos> it=genotype.getSNPPoslist(params).iterator();
	// while(it.hasNext()) {
	// SnpsAllvarsPos pos=it.next();
	// if(bw==null) {
	// Map mapMarker=new LinkedHashMap();
	// mapMarker.put("markerDbId", createMarkerName(pos));
	// mapMarker.put("markerName", createMarkerName(pos));
	// mapMarker.put("location", pos.getPosition());
	// listresult.add( mapMarker );
	// } else {
	// try {
	// bw.append(createMarkerName(pos)).append(delimiter).append( pos.getContig()
	// ).append(delimiter).append(pos.getPosition().toString() );
	// if(it.hasNext()) bw.append("\n");
	// } catch(Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	//
	// }
	// }
	//
	// String errormsg="";
	// if(bw==null)
	// return createJSONResponse(listresult, pageSize, page);
	// else {
	// try {
	// bw.flush();
	// bw.close();
	// return createFilenameResponse(tempfile);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// errormsg=ex.getMessage();
	// }
	// }
	//
	// return Response.status(500).entity(errormsg).build();
	//
	// }

	@Path("/maps/{mapDbId}/positions/{linkageGroupDbId}")
	@GET
	@Produces({ "application/json", "text/plain" })
	/// maps/mapId/positions/linkageGroupId?min=min&max=max&pageSize=pageSize&pageNumber=pageNumber
	// "/maps/{mapId}/positions/{linkageGroupId}?min={min}&#38;max={max}&#38;pageSize={pageSize}&#38;pageNumber={pageNumber}")
	public Response getMarkers(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@PathParam("mapDbId") String mapId, @PathParam("linkageGroupDbId") String linkageGroupId,
			@DefaultValue("0") @QueryParam("min") long min, @DefaultValue("0") @QueryParam("max") long max,
			@DefaultValue("3kfiltered") @QueryParam("studyDbId") String studyId,
			@DefaultValue("json") @QueryParam("format") String format,
			@DefaultValue("") @QueryParam("pageSize") String pageSize,
			@DefaultValue("") @QueryParam("page") String page) { // throws JSONException {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		String pagingstatus = validatePaging(pageSize, page);
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		genotype = (GenotypeFacade) AppContext.checkBean(genotype, "GenotypeFacade");

		studyId = mapId;

		/**
		 * 
		 * @param colVarIds
		 *            Collection of variety IDs
		 * @param sChr
		 *            Contig name
		 * @param lStart
		 *            Start bp position
		 * @param lEnd
		 *            End bp position
		 * @param bSNP
		 *            Query SNPs
		 * @param bIndel
		 *            Query INDELs
		 * @param bCoreonly
		 *            Use core SNPs omly
		 * @param bMismatchonly
		 *            Include only varieties with mismatch in region
		 * @param poslist
		 *            Position list
		 * @param sSubpopulation
		 *            Query only subpopulation
		 * @param sLocus
		 *            Query within locus
		 * @param bAlignIndels
		 *            Display INDels as multiple sequence alignment
		 * @param bShowAllRefAlleles
		 *            Show alleles for all reference genomes
		 */

		if (min == 0 && max == 0) {
			min = 1;

			listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
			// genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
			// int orgid=Integer.valueOf(mapId);
			Organism org = listitemsDAO.getOrganismById(9);

			List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());

			Iterator<Scaffold> itConts = listConts.iterator();
			while (itConts.hasNext()) {
				Scaffold sc = itConts.next();
				if (sc.getName().equals(linkageGroupId)) {
					max = sc.getLength();
					break;
				}
			}
		}

		GenotypeQueryParams params = new GenotypeQueryParams(null, linkageGroupId, min, max, true, false,
				getSnpset(studyId), getDataset(studyId), getGenotypeRun(studyId), false, null, null, null, false,
				false, null);

		params = setStudy(params, studyId);

		long totalCount = -1;
		if (pagingstatus.equals(PAGING_VALID)) {

			params.setPaging(Long.valueOf(page), Long.valueOf(pageSize));

			totalCount = genotype.countSNPPoslist(params);
			if (totalCount < 0)
				throw new RuntimeException("countSNPPoslist not implemented");
		}

		List listresult = new ArrayList();

		Iterator<SnpsAllvarsPos> it = genotype.getSNPPoslist(params).iterator();

		if (format.equals("json")) {
			while (it.hasNext()) {
				SnpsAllvarsPos pos = it.next();
				Map mapMarker = new LinkedHashMap();
				mapMarker.put("markerDbId", createMarkerName(pos));
				mapMarker.put("markerName", createMarkerName(pos));
				mapMarker.put("location", pos.getPosition());
				listresult.add(mapMarker);
			}
			return createJSONResponse(listresult, pageSize, page, totalCount);
		} else {
			String delimiter = "\t";
			if (format.equals("tsv"))
				delimiter = "\t";
			else if (format.equals("csv"))
				delimiter = ",";

			String tempfile = "markers_" + AppContext.createTempFilename() + "." + format;
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
				while (it.hasNext()) {
					SnpsAllvarsPos pos = it.next();
					bw.append(createMarkerName(pos)).append(delimiter).append(pos.getPosition().toString());
					if (it.hasNext())
						bw.append("\n");
				}
				bw.flush();
				bw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return createFilenameResponse(tempfile);
		}

	}

	//
	//
	// @Path("/maps/{mapDbId}/positions")
	// @GET
	// @Produces({"application/json", "text/plain"})
	// ///maps/mapId/positions/linkageGroupId?min=min&max=max&pageSize=pageSize&pageNumber=pageNumber
	// //"/maps/{mapId}/positions/{linkageGroupId}?min={min}&#38;max={max}&#38;pageSize={pageSize}&#38;pageNumber={pageNumber}")
	// public Response getMarkers(@PathParam("mapDbId") String mapId,
	// @DefaultValue("3kfiltered") @QueryParam("studyDbId") String studyId,
	// @DefaultValue("'") @QueryParam("linkageGroupDbId") String linkageGroupDbId,
	// @DefaultValue("json") @QueryParam("format") String format,
	// @DefaultValue("") @QueryParam("pageSize") String pageSize,
	// @DefaultValue("") @QueryParam("page") String page
	// ) { // throws JSONException {
	//
	// genotype=(GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
	//
	// listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
	// //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
	// int orgid=Integer.valueOf(mapId);
	// Organism org = listitemsDAO.getOrganismById( orgid );
	//
	//
	// //List<Scaffold> listConts = listitemsDAO.getContigs(org.getName());
	// List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());
	//
	// List listresult=new ArrayList();
	//
	// BufferedWriter bw=null;
	// String tempfile=null;
	// String delimiter="\t";
	// if(!format.equals("json") ) {
	// tempfile="markers_" + AppContext.createTempFilename() + "." + format;
	// try {
	// bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
	// } catch(Exception ex) {
	// if(format.equals("csv") ) delimiter=",";
	// ex.printStackTrace();
	// }
	// }
	//
	// List listLG=new ArrayList();
	// Iterator<Scaffold> itConts = listConts.iterator();
	// while(itConts.hasNext()) {
	// Scaffold sc=itConts.next();
	//
	// GenotypeQueryParams params=new GenotypeQueryParams(
	// null, sc.getName(), 1L, sc.getLength(),
	// true, false,false,
	// false, null, null, null, false, false);
	//
	// params = setStudy(params, studyId);
	//
	// if(linkageGroupDbId!=null && !linkageGroupDbId.isEmpty())
	// params.setsChr(linkageGroupDbId);
	//
	//
	// Iterator<SnpsAllvarsPos> it=genotype.getSNPPoslist(params).iterator();
	// while(it.hasNext()) {
	// SnpsAllvarsPos pos=it.next();
	// if(bw==null) {
	// Map mapMarker=new LinkedHashMap();
	// mapMarker.put("markerDbId", pos.getContig()+"_"+pos.getPosition());
	// mapMarker.put("markerName", pos.getContig()+"_"+pos.getPosition());
	// mapMarker.put("location", pos.getPosition());
	// listresult.add( mapMarker );
	// } else {
	// try {
	// bw.append(pos.getContig()+"_"+pos.getPosition()).append(delimiter).append(
	// pos.getContig() ).append(delimiter).append(pos.getPosition().toString() );
	// if(it.hasNext()) bw.append("\n");
	// } catch(Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	//
	// }
	// }
	//
	// String errormsg="";
	// if(bw==null)
	// return createJSONResponse(listresult);
	// else {
	// try {
	// bw.flush();
	// bw.close();
	// return createFilenameResponse(tempfile);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// errormsg=ex.getMessage();
	// }
	// }
	//
	// return Response.status(500).entity(errormsg).build();
	//
	// }
	//
	//
	// @Path("/maps/{mapDbId}/positions/{linkageGroupDbId}")
	// @GET
	// @Produces({"application/json", "text/plain"})
	// ///maps/mapId/positions/linkageGroupId?min=min&max=max&pageSize=pageSize&pageNumber=pageNumber
	// //"/maps/{mapId}/positions/{linkageGroupId}?min={min}&#38;max={max}&#38;pageSize={pageSize}&#38;pageNumber={pageNumber}")
	// public Response getMarkers(@PathParam("mapDbId") String mapId,
	// @PathParam("linkageGroupDbId") String linkageGroupId,
	// @QueryParam("min") long min, @QueryParam("max") long max,
	// @DefaultValue("3kfiltered") @QueryParam("studyDbId") String studyId,
	// @DefaultValue("json") @QueryParam("format") String format,
	// @DefaultValue("") @QueryParam("pageSize") String pageSize,
	// @DefaultValue("") @QueryParam("page") String page
	// ) { // throws JSONException {
	//
	// genotype=(GenotypeFacade)AppContext.checkBean(genotype, "GenotypeFacade");
	//
	//
	// /**
	// *
	// * @param colVarIds Collection of variety IDs
	// * @param sChr Contig name
	// * @param lStart Start bp position
	// * @param lEnd End bp position
	// * @param bSNP Query SNPs
	// * @param bIndel Query INDELs
	// * @param bCoreonly Use core SNPs omly
	// * @param bMismatchonly Include only varieties with mismatch in region
	// * @param poslist Position list
	// * @param sSubpopulation Query only subpopulation
	// * @param sLocus Query within locus
	// * @param bAlignIndels Display INDels as multiple sequence alignment
	// * @param bShowAllRefAlleles Show alleles for all reference genomes
	// */
	//
	//
	//
	// if(min==0 && max==0) {
	// min=1;
	//
	// listitemsDAO=(ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
	// //genomics=(GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
	// int orgid=Integer.valueOf(mapId);
	// Organism org = listitemsDAO.getOrganismById( orgid );
	//
	// List<Scaffold> listConts = listitemsDAO.getOrganismScaffolds(org.getName());
	//
	//
	// Iterator<Scaffold> itConts = listConts.iterator();
	// while(itConts.hasNext()) {
	// Scaffold sc=itConts.next();
	// if(sc.getName().equals(linkageGroupId)){
	// max=sc.getLength();
	// break;
	// }
	// }
	// }
	//
	// GenotypeQueryParams params=new GenotypeQueryParams(
	// null, linkageGroupId, min, max,
	// true, false,false,
	// false, null, null, null, false, false);
	//
	// params = setStudy(params, studyId);
	//
	// List listresult=new ArrayList();
	//
	// Iterator<SnpsAllvarsPos> it=genotype.getSNPPoslist(params).iterator();
	//
	// if(format.equals("json")) {
	// while(it.hasNext()) {
	// SnpsAllvarsPos pos=it.next();
	// Map mapMarker=new LinkedHashMap();
	// mapMarker.put("markerDbId", pos.getContig()+"_"+pos.getPosition());
	// mapMarker.put("markerName", pos.getContig()+"_"+pos.getPosition());
	// mapMarker.put("location", pos.getPosition());
	// listresult.add( mapMarker );
	// }
	// return createJSONResponse(listresult);
	// } else {
	// String delimiter="\t";
	// if(format.equals("tsv")) delimiter="\t";
	// else if(format.equals("csv")) delimiter=",";
	//
	// String tempfile="markers_" + AppContext.createTempFilename() + "." + format;
	// try {
	// BufferedWriter bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() +
	// tempfile));
	// while(it.hasNext()) {
	// SnpsAllvarsPos pos=it.next();
	// bw.append(pos.getContig()+"_"+pos.getPosition()
	// ).append(delimiter).append(pos.getPosition().toString());
	// if(it.hasNext()) bw.append("\n");
	// }
	// bw.flush();
	// bw.close();
	// } catch(Exception ex) {
	// ex.printStackTrace();
	// }
	// return createFilenameResponse(tempfile);
	// }
	//
	// }
	//

	// *************************** STUDY ***********************************
	/*
	 * mapCall=createCall("studyTypes",new String[]{"GET"}, new String[]{"json"});
	 * mapName2Call.put( "studyTypes", mapCall);
	 * mapType2Calls.get("json").add(mapCall); listAllCalls.add(mapCall);
	 * 
	 * mapCall=createCall("studies-search",new String[]{"GET"}, new
	 * String[]{"json"}); mapName2Call.put( "studies-search", mapCall);
	 * mapType2Calls.get("json").add(mapCall); listAllCalls.add(mapCall);
	 * 
	 * mapCall=createCall("studies/{studiesDbid}",new String[]{"GET"}, new
	 * String[]{"json"}); mapName2Call.put( "studies/{studiesDbid}", mapCall);
	 * mapType2Calls.get("json").add(mapCall); listAllCalls.add(mapCall);
	 * 
	 * mapCall=createCall("studies/{studiesDbid}/germplasm",new String[]{"GET"}, new
	 * String[]{"json"}); mapName2Call.put( "studies/{studiesDbid}/germplasm",
	 * mapCall); mapType2Calls.get("json").add(mapCall); listAllCalls.add(mapCall);
	 */

	// GEThttp://private-c2c4f-brapi.apiary-mock.com/brapi/v1/studies-search?studyType=studyType&seasonDbId=seasonDbId&locationDbId=locationDbId&programDbId=programDbId&germplasmDbIds=germplasmDbIds&observationVariableDbIds=observationVariableDbIds&pageSize=pageSize&page=page&active=active&sortBy=sortBy&sortOrder=sortOrder

	@Path("/studyTypes")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getStudyTypes(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer) {
		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		List listTypes = new ArrayList();
		Map mapType = new LinkedHashMap();
		mapType.put("name", "Genotype");
		mapType.put("description", "SNPs called from NextGen rice sequencing projects");
		listTypes.add(mapType);
		return createJSONResponse(listTypes);
	}

	private Map createStudy(String dbId, String name) {
		return createStudy(dbId, name, "");
	}

	private Map createStudy(String dbId, String name, String expand) {
		Map map = new LinkedHashMap();
		map.put("studyDbId", dbId);
		map.put("studyName", name);
		map.put("studyType", "Genotype");
		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");

		if (expand != null && expand.contains("germplasm")) {

			Set vars = new LinkedHashSet();
			if (dbId.equals("hdra")) {
				Set s = new HashSet();
				s.add(VarietyFacade.DATASET_SNP_HDRA);
				vars = variety.getGermplasm(s);
			} else if (dbId.startsWith("3k")) {
				Set s = new HashSet();
				s.add(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
				vars = variety.getGermplasm(s);
			}
			List listvars = new ArrayList();
			Iterator<Variety> itvar = vars.iterator();
			while (itvar.hasNext()) {
				listvars.add(this.convertGermplasmResult(itvar.next()));
			}
			map.put("germplasm", listvars);
		} else
			map.put("germplasm", "/brapi/v1/" + dbId + "/germplasm");

		return map;
	}

	@Path("/studies-search")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getStudies(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@DefaultValue("Genotype") @QueryParam("studyType") String studyType,
			@DefaultValue("") @QueryParam("pageSize") String pageSize,
			@DefaultValue("") @QueryParam("page") String page) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		String pagingstatus = validatePaging(pageSize, page);
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		List listStudies = new ArrayList();

		if (studyType.equals("Genotype")) {

			listStudies.add(createStudy("3kfiltered", "3k Rice Genome Project filtered SNPs (4.8M SNPs)"));
			listStudies.add(createStudy("3kcore", "3k Rice Genome Project core SNPs (404k SNPs)"));
			listStudies.add(createStudy("3kbase", "3k Rice Genome Project base SNPs (18M SNPs)"));
			listStudies.add(createStudy("3kall", "3k Rice Genome Project all SNPs (32M SNPs)"));
			listStudies.add(createStudy("hdra", "High Density Rice Array HDRA (700k SNPs)"));
		}

		/*
		 * { "studyDbId": 35, "name": "Earlygenerationtesting", "trialDbId": 1,
		 * "trialName": "InternationalTrialA", "studyType": "Trial", "seasons": [
		 * "2007 Spring", "2008 Fall" ], "locationDbId": 23, "locationName": "Kenya",
		 * "programDbId": 27, "programName": "Drought Resistance Program A",
		 * "startDate": "2007-06-01", "endDate": "2008-12-31", "active": "true",
		 * "additionalInfo": { "property1Name": "property1Value", "property2Name":
		 * "property2Value", "property3Name": "property3Value" } },
		 */

		return createJSONResponse(listStudies, pageSize, page);

	}

	@Path("/studies/{studyDbId}")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getStudiesById(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@PathParam("studyDbId") String studyDbId, @QueryParam("expand") String expand) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		if (studyDbId == null)
			return createJSONResponse(new ArrayList());

		if (studyDbId.equals("3kfiltered"))
			return createJSONResponse(
					createStudy("3kfiltered", "3k Rice Genome Project filtered SNPs (4.8M SNPs)", expand));
		if (studyDbId.equals("3kcore"))
			return createJSONResponse(createStudy("3kcore", "3k Rice Genome Project core SNPs (404k SNPs)", expand));
		if (studyDbId.equals("3kbase"))
			return createJSONResponse(createStudy("3kbase", "3k Rice Genome Project base SNPs (18M SNPs)", expand));
		if (studyDbId.equals("3kall"))
			return createJSONResponse(createStudy("3kall", "3k Rice Genome Project all SNPs (32M SNPs)", expand));
		if (studyDbId.equals("hdra"))
			return createJSONResponse(createStudy("hdra", "High Density Rice Array HDRA (700k SNPs)", expand));

		return createJSONResponse(new ArrayList());
	}

	@Path("/studies/{studyDbId}/germplasm")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getStudiesByIdGermplasm(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@PathParam("studyDbId") String studyDbId, @DefaultValue("") @QueryParam("pageSize") String pageSize,
			@DefaultValue("") @QueryParam("page") String page) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}
		String pagingstatus = validatePaging(pageSize, page);
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");

		if (studyDbId == null)
			return createJSONResponse(new ArrayList());

		if (studyDbId.equals("3kfiltered") || studyDbId.equals("3kcore") || studyDbId.equals("3kbase")
				|| studyDbId.equals("3kall")) {

			List listVars = new ArrayList();
			Iterator<Variety> itvars = variety.getGermplasm(VarietyFacade.DATASET_SNPINDELV2_IUPAC).iterator();
			while (itvars.hasNext()) {
				listVars.add(this.convertGermplasmResult(itvars.next()));
			}
			return createJSONResponse(listVars, pageSize, page);
		}
		if (studyDbId.equals("hdra")) {
			List listVars = new ArrayList();
			Iterator<Variety> itvars = variety.getGermplasm(VarietyFacade.DATASET_SNP_HDRA).iterator();
			while (itvars.hasNext()) {
				listVars.add(this.convertGermplasmResult(itvars.next()));
			}
			return createJSONResponse(listVars, pageSize, page);
		}

		return createJSONResponse(new ArrayList());
	}

	// **************** MARKERPROFILE **********************************

	private Map createMarkerprofile(long profiledbid, long germplasmdbid, String displayname, String sampleid,
			String extractid, String analysismethod, long resultcount) {

		Map map = new LinkedHashMap();

		map.put("markerProfileDbId", String.valueOf(profiledbid));
		map.put("germplasmDbId", String.valueOf(germplasmdbid));
		map.put("uniqueDisplayName", displayname);
		map.put("sampleDbId", sampleid);
		map.put("extractDbId", extractid);
		map.put("analysisMethod", analysismethod);
		map.put("resultCount", resultcount);
		return map;

	}

	// http://private-c2c4f-brapi.apiary-mock.com/brapi/v1/markerprofiles?germplasm=germplasmDbId&extract=extractDbId&method=methodDbId&pageSize=100&page=4
	@Path("/markerprofiles")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getMarkerprofiles(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@QueryParam("germplasmDbId") String germplasm, @QueryParam("sampleDbId") String sample,
			@QueryParam("extractDbId") String extract, @QueryParam("studyDbId") String studyId, // @QueryParam("method")
																								// String method,
			@QueryParam("pageSize") String pageSize, @QueryParam("page") String page) { // throws JSONException {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		String pagingstatus = validatePaging(pageSize, page);
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
		Map<BigDecimal, Variety> mapVar2Name = listitemsDAO.getMapId2Variety(VarietyFacade.DATASET_SNP_ALL);
		// Map<BigDecimal,Variety>
		// mapVar2Name=listitemsDAO.getMapId2Variety("3kfiltered");

		List listProfiles = new ArrayList();
		if (germplasm != null) {
			Long id = Long.valueOf(germplasm);
			BigDecimal bdId = BigDecimal.valueOf(id);

			if (id <= 3024) {
				String study = "3kfiltered";
				listProfiles.add(createMarkerprofile(id, id, mapVar2Name.get(bdId).getName() + ";" + study, null, null,
						"SNP calling", mapMap2GroupsMarkers.get(study)[1]));
				study = "3kcore";
				listProfiles.add(createMarkerprofile(id + 10000, id, mapVar2Name.get(bdId).getName() + ";" + study,
						null, null, "SNP calling", mapMap2GroupsMarkers.get(study)[1]));
				study = "3kbase";
				listProfiles.add(createMarkerprofile(id + 20000, id, mapVar2Name.get(bdId).getName() + ";" + study,
						null, null, "SNP calling", mapMap2GroupsMarkers.get(study)[1]));
				study = "3kall";
				listProfiles.add(createMarkerprofile(id + 30000, id, mapVar2Name.get(bdId).getName() + ";" + study,
						null, null, "SNP calling", mapMap2GroupsMarkers.get(study)[1]));

				/*
				 * listProfiles.add(Long.toString(id));
				 * listProfiles.add(Long.toString(id+10000));
				 * listProfiles.add(Long.toString(id+20000));
				 * listProfiles.add(Long.toString(id+30000));
				 */
			} else {
				String study = "hdra";
				listProfiles.add(createMarkerprofile(id, id, mapVar2Name.get(bdId).getName() + ";" + study, null, null,
						"SNP calling", mapMap2GroupsMarkers.get(study)[1]));
				// listProfiles.add(Long.toString(id));
			}

		} else if (studyId != null && studyId.startsWith("3k")) {
			long offset = 0;
			if (studyId.equals("3kfiltered"))
				;
			else if (studyId.equals("3kcore"))
				offset = 10000;
			else if (studyId.equals("3kbase"))
				offset = 20000;
			else if (studyId.equals("3kall"))
				offset = 30000;
			for (int i = 1; i <= 3024; i++) {
				listProfiles.add(createMarkerprofile(i + offset, i,
						mapVar2Name.get(BigDecimal.valueOf(i)).getName() + ";" + studyId, null, null, "SNP calling",
						mapMap2GroupsMarkers.get(studyId)[1]));

				// listProfiles.add( String.valueOf(i+offset));
			}
		} else if (studyId != null && studyId.equals("hdra")) {
			for (int i = 3025; i <= 4592; i++) {
				listProfiles
						.add(createMarkerprofile(i, i, mapVar2Name.get(BigDecimal.valueOf(i)).getName() + "-" + studyId,
								null, null, "SNP calling", mapMap2GroupsMarkers.get(studyId)[1]));
				// listProfiles.add( String.valueOf(i));
			}
		}
		;

		return createJSONResponse(listProfiles, pageSize, page);
	}

	@Path("/markerprofiles/{markerprofileDbId}")
	@GET
	@Produces({ "application/json", "text/plain" })
	public Response getMarkerprofilesDbId(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@PathParam("markerprofileDbId") String markerprofileId,
			// @DefaultValue("") @FormParam("markerDbId") String markerDbId,
			@DefaultValue("false") @QueryParam("expandHomozygotes") boolean expandHomozygotes,
			@DefaultValue("N") @QueryParam("unknownString") String unknownString,
			@DefaultValue("|") @QueryParam("sepPhased") String sepPhased,
			@DefaultValue("/") @QueryParam("sepUnphased") String sepUnphased,
			@DefaultValue("") @QueryParam("format") String format,
			@DefaultValue("") @QueryParam("pageSize") String pageSize,
			@DefaultValue("") @QueryParam("page") String page) { // throws JSONException {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		String pagingstatus = validatePaging(pageSize, page, AppContext.getMaxGenotypeElements());
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		String markerprofileDbIdstr[] = markerprofileId.split("[\n,]");
		List<String> listmarkerprofileDbId = new ArrayList();
		for (int i = 0; i < markerprofileDbIdstr.length; i++) {

			String s = markerprofileDbIdstr[i].trim();
			if (!s.isEmpty())
				listmarkerprofileDbId.add(s);
		}

		List<String> listmarkerDbId = new ArrayList();
		/*
		 * String markerDbIdstr[]=markerDbId.split("[\n,]"); List<String>
		 * listmarkerDbId=new ArrayList(); for(int i=0; i< markerDbIdstr.length; i++) {
		 * String s=markerDbIdstr[i].trim(); if(!s.isEmpty()) listmarkerDbId.add(s); }
		 */

		// Map mapData=allelematrix(listmarkerprofileDbId, listmarkerDbId ,
		// expandHomozygotes,unknownString,sepPhased,sepUnphased, format, false);

		try {

			AppContext.debug("listmarkerprofileDbId=" + listmarkerprofileDbId);

			return allelematrix(listmarkerprofileDbId, listmarkerDbId, expandHomozygotes, unknownString, sepPhased,
					sepUnphased, format, true, "markerprofile", pageSize, page, false);

			/*
			 * Object[] objresp= allelematrix(listmarkerprofileDbId, listmarkerDbId ,
			 * expandHomozygotes,unknownString,sepPhased,sepUnphased, format, true);
			 * if(objresp[1]!=null) { Map mapResp=(Map)objresp[1];
			 * 
			 * long profileid=Long.valueOf(markerprofileId); long germplasmid=profileid;
			 * String studyId="3kfiltered";
			 * 
			 * if(profileid>=1 && profileid<=3024) { studyId= "3kfiltered"; } else
			 * if(profileid>=10001 && profileid<=13024) { studyId= "3kcore";
			 * germplasmid-=10000; } else if(profileid>=20001 && profileid<=23024) {
			 * studyId= "3kbase";germplasmid-=20000; } else if(profileid>=30001 &&
			 * profileid<=33024) { studyId= "3kall";germplasmid-=30000; } else
			 * if(profileid>=3025 && profileid<=4592) studyId= "hdra";
			 * 
			 * mapResp.put("germplasmDbId" , germplasmid); mapResp.put("uniqueDisplayName" ,
			 * "germplasmDbId=" + germplasmid + ";studyDbId=" + studyId);
			 * mapResp.put("markerProfileDbId", profileid);
			 * mapResp.put("analysisMethod","SNP calling"); mapResp.put("extractDbId",null);
			 * return createJSONResponse(mapResp);
			 * 
			 * } else return (Response)objresp[0];
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}

		/*
		 * 
		 * if(germplasm!=null) { Long id=Long.valueOf(germplasm ); if(id<=3024) {
		 * listProfiles.add(Long.toString(id));
		 * listProfiles.add(Long.toString(id+10000));
		 * listProfiles.add(Long.toString(id+20000));
		 * listProfiles.add(Long.toString(id+30000)); } else
		 * listProfiles.add(Long.toString(id));
		 * 
		 * } else if(studyId!=null && studyId.startsWith("3k")) { long offset=0;
		 * if(studyId.equals("3kfiltered")); else if(studyId.equals("3kcore"))
		 * offset=10000; else if(studyId.equals("3kbase")) offset=20000; else
		 * if(studyId.equals("3kall")) offset=30000; for(int i=1; i<=3024; i++) {
		 * listProfiles.add( String.valueOf(i+offset)); } } else if(studyId!=null &&
		 * studyId.equals("hdra")) { for(int i=3025; i<=4592; i++) { listProfiles.add(
		 * String.valueOf(i)); } };
		 */
		// return createJSONResponse(listProfiles);
	}

	// ***************** ALLELE MATRIX ************************************

	public static class RequestAlleleMatrix {
		private List<String> markerprofileDbId;
		private List<String> markerDbId;
		private boolean expandHomozygotes = false;
		private String unknownString = "N";
		private String sepPhased = "|";
		private String sepUnphased = "/";
		private String format = "json";
		private String pageSize;
		private String page;

		public RequestAlleleMatrix() {
			super();
			// TODO Auto-generated constructor stub
		}

		public List<String> getMarkerprofileDbId() {
			return markerprofileDbId;
		}

		public void setMarkerprofileDbId(List<String> markerprofileDbId) {
			this.markerprofileDbId = markerprofileDbId;
		}

		public List<String> getMarkerDbId() {
			return markerDbId;
		}

		public void setMarkerDbId(List<String> markerDbId) {
			this.markerDbId = markerDbId;
		}

		public boolean isExpandHomozygotes() {
			return expandHomozygotes;
		}

		public void setExpandHomozygotes(boolean expandHomozygotes) {
			this.expandHomozygotes = expandHomozygotes;
		}

		public String getUnknownString() {
			return unknownString;
		}

		public void setUnknownString(String unknownString) {
			this.unknownString = unknownString;
		}

		public String getSepPhased() {
			return sepPhased;
		}

		public void setSepPhased(String sepPhased) {
			this.sepPhased = sepPhased;
		}

		public String getSepUnphased() {
			return sepUnphased;
		}

		public void setSepUnphased(String sepUnphased) {
			this.sepUnphased = sepUnphased;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public String getPageSize() {
			return pageSize;
		}

		public void setPageSize(String pageSize) {
			this.pageSize = pageSize;
		}

		public String getPage() {
			return page;
		}

		public void setPage(String page) {
			this.page = page;
		}

	}
	// @Path("/allelematrix-search2")
	// @POST
	// //@Consumes({"multipart/form-data",
	// "application/json","application/x-www-form-urlencoded"})
	// //@Consumes("application/x-www-form-urlencoded")
	// @Consumes({"application/json","application/x-www-form-urlencoded"})
	// @Produces({"application/json", "text/plain"})
	//
	//
	//
	//
	//
	// public Response postAlleleMatrix2(HttpServletRequest mapRequest ) { //
	//
	// AppContext.debug("mapRequest=" + mapRequest);
	//
	// String[] arrmarkerprofileDbId =
	// mapRequest.getParameterValues("markerprofileDbId");
	// String[] arrmarkerDbId = mapRequest.getParameterValues("markerDbId");
	//
	// List listmarkerprofileDbId=new ArrayList();
	// List listmarkerDbId=new ArrayList();
	// if(arrmarkerprofileDbId!=null) {
	// for(int i=0; i<arrmarkerprofileDbId.length; i++)
	// listmarkerprofileDbId.add(arrmarkerprofileDbId[i]);
	// }
	// if(arrmarkerDbId!=null) {
	// for(int i=0; i<arrmarkerDbId.length; i++)
	// listmarkerDbId.add(arrmarkerDbId[i]);
	// }
	//
	// boolean expandHomozygotes = false;
	// String unknownString="N";
	// String sepPhased="|";
	// String sepUnphased="/";
	// String format="json";
	//
	//
	// /*
	// public Response postAlleleMatrix2( MultivaluedMap<String, String> mapRequest)
	// { //
	//
	// AppContext.debug("mapRequest=" + mapRequest);
	//
	// List listmarkerprofileDbId = mapRequest.get("markerprofileDbId");
	// List listmarkerDbId= mapRequest.get("markerprofileDbId");
	//
	// boolean expandHomozygotes = false;
	// String unknownString="N";
	// String sepPhased="|";
	// String sepUnphased="/";
	// String format="json";
	// */
	// /*
	// public Response postAlleleMatrix2(
	// //@NotNull @QueryParam("markerprofileDbId") List<String>
	// listmarkerprofileDbId,
	// //@QueryParam("markerDbId") List<String> listmarkerDbId,
	// @NotNull @QueryParam("markerprofileDbId") String markerprofileDbId,
	// @QueryParam("markerDbId") String markerDbId,
	// @DefaultValue("false") @QueryParam("expandHomozygotes") boolean
	// expandHomozygotes,
	// @DefaultValue("N") @QueryParam("unknownString") String unknownString,
	// @DefaultValue("|") @QueryParam("sepPhased") String sepPhased,
	// @DefaultValue("/") @QueryParam("sepUnphased") String sepUnphased,
	// @DefaultValue("") @QueryParam("format") String format,
	// @DefaultValue("") @QueryParam("pageSize") String pageSize,
	// @DefaultValue("") @QueryParam("page") String page) { //
	//
	//
	// List listmarkerprofileDbId=new ArrayList();
	// List listmarkerDbId=new ArrayList();
	// if(markerprofileDbId!=null) {
	// String str[]=markerprofileDbId.split(",");
	// for(int i=0; i<str.length; i++)
	// listmarkerprofileDbId.add(str[i]);
	// }
	// if(markerDbId!=null) {
	// String str[]=markerDbId.split(",");
	// for(int i=0; i<str.length; i++)
	// listmarkerDbId.add(str[i]);
	// }
	// */
	//
	// /*{
	// @NotNull @FormParam("markerprofileDbId") List<String> listmarkerprofileDbId,
	// }
	// @DefaultValue("") @FormParam("markerDbId") List<String> listmarkerDbId,
	// @DefaultValue("false") @FormParam("expandHomozygotes") boolean
	// expandHomozygotes,
	// @DefaultValue("N") @FormParam("unknownString") String unknownString,
	// @DefaultValue("|") @FormParam("sepPhased") String sepPhased,
	// @DefaultValue("/") @FormParam("sepUnphased") String sepUnphased,
	// @DefaultValue("") @FormParam("format") String format,
	// @DefaultValue("") @FormParam("pageSize") String pageSize,
	// @DefaultValue("") @FormParam("page") String page) { // { // throws
	// JSONException {
	// */
	//
	// /*
	// public Response postAlleleMatrix(
	// @NotNull @RequestParam("markerprofileDbId") List<String>
	// listmarkerprofileDbId,
	// @RequestParam("markerDbId") List<String> listmarkerDbId,
	// @DefaultValue("false") @RequestParam("expandHomozygotes") boolean
	// expandHomozygotes,
	// @DefaultValue("N") @RequestParam("unknownString") String unknownString,
	// @DefaultValue("|") @RequestParam("sepPhased") String sepPhased,
	// @DefaultValue("/") @RequestParam("sepUnphased") String sepUnphased,
	// @DefaultValue("") @RequestParam("format") String format,
	// @DefaultValue("") @RequestParam("pageSize") String pageSize,
	// @DefaultValue("") @RequestParam("page") String page) { // { // throws
	// JSONException {
	// */
	//
	// AppContext.debug("postAlleleMatrix: markerDbId=" +
	// (listmarkerDbId!=null?listmarkerDbId.size():"null") + " markerprofileDbId=" +
	// (listmarkerprofileDbId!=null?listmarkerprofileDbId.size():"null"));
	// if(listmarkerDbId!=null && listmarkerDbId.size()<20)
	// AppContext.debug("listmarkerDbId=" + listmarkerDbId);
	// if(listmarkerprofileDbId!=null && listmarkerprofileDbId.size()<20)
	// AppContext.debug("listmarkerprofileDbId=" + listmarkerprofileDbId);
	//
	// /*
	// AppContext.debug("postAlleleMatrix: markerDbId=" +markerDbId + "
	// markerprofileDbId=" + markerprofileDbId);
	//
	// String markerprofileDbIdstr[]=markerprofileDbId.split("[\n,]");
	// List<String> listmarkerprofileDbId=new ArrayList();
	// for(int i=0; i< markerprofileDbIdstr.length; i++) {
	//
	// String s=markerprofileDbIdstr[i].trim();
	// if(!s.isEmpty()) listmarkerprofileDbId.add(s);
	// }
	//
	//
	// String markerDbIdstr[]=markerDbId.split("[\n,]");
	// List<String> listmarkerDbId=new ArrayList();
	// for(int i=0; i< markerDbIdstr.length; i++) {
	// String s=markerDbIdstr[i].trim();
	// if(!s.isEmpty()) listmarkerDbId.add(s);
	// }
	//
	// */
	// return allelematrix(listmarkerprofileDbId, listmarkerDbId ,
	// expandHomozygotes,unknownString,sepPhased,sepUnphased, format,
	// true,"allelematrix-search");
	//
	// }
	//
	//
	// @Path("/allelematrix-search3")
	// @POST
	// @Consumes({MediaType.APPLICATION_JSON})
	// @Produces({MediaType.APPLICATION_JSON})
	// public @ResponseBody ByteArrayOutputStream createGiftPass(
	// @RequestBody RequestAlleleMatrix request) throws IOException {
	//
	// AppContext.debug("request.getMarkerDbId()=" + request.getMarkerDbId());
	// AppContext.debug("request.getMarkerprofileDbId()=" +
	// request.getMarkerprofileDbId());
	// String success = "Success";
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// baos.write(success.getBytes());
	// return baos;
	// }
	//
	//

	@Path("/allelematrix-search")
	@POST
	// @Consumes({"multipart/form-data",
	// "application/json","application/x-www-form-urlencoded"})
	// @Consumes("application/x-www-form-urlencoded")
	@Consumes("application/json")
	@Produces({ "application/json", "text/plain" })

	/*
	 * public Response postAlleleMatrix(@RequestBody RequestAlleleMatrix mapRequest)
	 * { // throws JSONException { AppContext.debug("mapRequest=" + mapRequest );
	 * List<String> listmarkerprofileDbId = mapRequest.getMarkerprofileDbId();
	 * List<String> listmarkerDbId = mapRequest.getMarkerDbId();
	 * 
	 * boolean expandHomozygotes = mapRequest.isExpandHomozygotes(); String
	 * unknownString=mapRequest.getUnknownString(); String
	 * sepPhased=mapRequest.getSepPhased(); String
	 * sepUnphased=mapRequest.getSepUnphased(); String
	 * format=mapRequest.getFormat();
	 */
	/*
	 * public Response postAlleleMatrix(@RequestBody Map mapRequest) { // throws
	 * JSONException { AppContext.debug("mapRequest=" + mapRequest ); List<String>
	 * listmarkerprofileDbId = (List<String>)mapRequest.get("markerprofileDbId");
	 * List<String> listmarkerDbId = (List<String>)mapRequest.get("markerDbId");
	 * 
	 * boolean expandHomozygotes
	 * =Boolean.valueOf((String)mapRequest.get("expandHomozygotes")); String
	 * unknownString=(String)mapRequest.get("unknownString"); String
	 * sepPhased=(String)mapRequest.get("sepPhased"); String
	 * sepUnphased=(String)mapRequest.get("sepUnphased"); String
	 * format=(String)mapRequest.get("format");
	 */

	/*
	 * public Response postAlleleMatrix(@RequestBody Map mapRequest) { // throws
	 * JSONException { AppContext.debug("mapRequest=" + mapRequest ); List<String>
	 * listmarkerprofileDbId = (List<String>)mapRequest.get("markerprofileDbId");
	 * List<String> listmarkerDbId = (List<String>)mapRequest.get("markerDbId");
	 * 
	 * boolean expandHomozygotes
	 * =Boolean.valueOf((String)mapRequest.get("expandHomozygotes")); String
	 * unknownString=(String)mapRequest.get("unknownString"); String
	 * sepPhased=(String)mapRequest.get("sepPhased"); String
	 * sepUnphased=(String)mapRequest.get("sepUnphased"); String
	 * format=(String)mapRequest.get("format");
	 */
	/*
	 * public Response postAlleleMatrix(JSONObject mapRequest) { // throws
	 * JSONException { AppContext.debug("gson=" + mapRequest+ "  ;tostring=" +
	 * mapRequest.toString() ); List<String> listmarkerprofileDbId =
	 * (List<String>)mapRequest.get("markerprofileDbId"); List<String>
	 * listmarkerDbId = (List<String>)mapRequest.get("markerDbId");
	 * 
	 * boolean expandHomozygotes
	 * =Boolean.valueOf(mapRequest.get("expandHomozygotes").toString()); String
	 * unknownString=(String)mapRequest.get("unknownString"); String
	 * sepPhased=(String)mapRequest.get("sepPhased"); String
	 * sepUnphased=(String)mapRequest.get("sepUnphased"); String
	 * format=(String)mapRequest.get("format");
	 */
	/*
	 * public Response postAlleleMatrix(JsonObject mapRequest) { // throws
	 * JSONException { AppContext.debug("gson=" + mapRequest+ "  ;tostring=" +
	 * mapRequest.toString() ); List<String> listmarkerprofileDbId =
	 * (List<String>)mapRequest.get("markerprofileDbId"); List<String>
	 * listmarkerDbId = (List<String>)mapRequest.get("markerDbId");
	 * 
	 * boolean expandHomozygotes
	 * =Boolean.valueOf(mapRequest.get("expandHomozygotes").toString()); String
	 * unknownString=(String)mapRequest.get("unknownString").getAsString(); String
	 * sepPhased=(String)mapRequest.get("sepPhased").toString(); String
	 * sepUnphased=(String)mapRequest.get("sepUnphased").toString(); String
	 * format=(String)mapRequest.get("format").toString();
	 */

	/*
	 * public Response postAlleleMatrix(@NotNull @FormParam("markerprofileDbId")
	 * List<String> listmarkerprofileDbId,
	 * 
	 * @DefaultValue("") @FormParam("markerDbId") List<String> listmarkerDbId,
	 * 
	 * @DefaultValue("false") @FormParam("expandHomozygotes") boolean
	 * expandHomozygotes,
	 * 
	 * @DefaultValue("N") @FormParam("unknownString") String unknownString,
	 * 
	 * @DefaultValue("|") @FormParam("sepPhased") String sepPhased,
	 * 
	 * @DefaultValue("/") @FormParam("sepUnphased") String sepUnphased,
	 * 
	 * @DefaultValue("") @FormParam("format") String format,
	 * 
	 * @DefaultValue("") @FormParam("pageSize") String pageSize,
	 * 
	 * @DefaultValue("") @FormParam("page") String page) { // { // throws
	 * JSONException {
	 */

	/*
	 * public Response postAlleleMatrix(
	 * 
	 * @NotNull @RequestParam("markerprofileDbId") List<String>
	 * listmarkerprofileDbId,
	 * 
	 * @RequestParam("markerDbId") List<String> listmarkerDbId,
	 * 
	 * @DefaultValue("false") @RequestParam("expandHomozygotes") boolean
	 * expandHomozygotes,
	 * 
	 * @DefaultValue("N") @RequestParam("unknownString") String unknownString,
	 * 
	 * @DefaultValue("|") @RequestParam("sepPhased") String sepPhased,
	 * 
	 * @DefaultValue("/") @RequestParam("sepUnphased") String sepUnphased,
	 * 
	 * @DefaultValue("") @RequestParam("format") String format,
	 * 
	 * @DefaultValue("") @RequestParam("pageSize") String pageSize,
	 * 
	 * @DefaultValue("") @RequestParam("page") String page) { // { // throws
	 * JSONException {
	 */
	/*
	 * public Response postAlleleMatrix(@Context Request request,
	 * 
	 * @NotNull @RequestParam("markerprofileDbId") String arrstrmarkerprofileDbId,
	 * 
	 * @RequestParam("markerDbId") String arrstrmarkerDbId,
	 * 
	 * @DefaultValue("false") @RequestParam("expandHomozygotes") String
	 * strexpandHomozygotes,
	 * 
	 * @DefaultValue("N") @RequestParam("unknownString") String unknownString,
	 * 
	 * @DefaultValue("|") @RequestParam("sepPhased") String sepPhased,
	 * 
	 * @DefaultValue("/") @RequestParam("sepUnphased") String sepUnphased,
	 * 
	 * @DefaultValue("") @RequestParam("format") String format,
	 * 
	 * @DefaultValue("") @RequestParam("pageSize") String pageSize,
	 * 
	 * @DefaultValue("") @RequestParam("page") String page) { // { // throws
	 * JSONException {
	 */

	/*
	 * public Response postAlleleMatrix(
	 * 
	 * @NotNull @FormParam("markerprofileDbId") String arrstrmarkerprofileDbId,
	 * 
	 * @FormParam("markerDbId") String arrstrmarkerDbId,
	 * 
	 * @DefaultValue("false") @FormParam("expandHomozygotes") String
	 * strexpandHomozygotes,
	 * 
	 * @DefaultValue("N") @FormParam("unknownString") String unknownString,
	 * 
	 * @DefaultValue("|") @FormParam("sepPhased") String sepPhased,
	 * 
	 * @DefaultValue("/") @FormParam("sepUnphased") String sepUnphased,
	 * 
	 * @DefaultValue("") @FormParam("format") String format,
	 * 
	 * @DefaultValue("") @FormParam("pageSize") String pageSize,
	 * 
	 * @DefaultValue("") @FormParam("page") String page) { // { // throws
	 * JSONException {
	 */

	/*
	 * public Response postAlleleMatrix(@Context Request request,
	 * 
	 * @NotNull @QueryParam("markerprofileDbId") String arrstrmarkerprofileDbId,
	 * 
	 * @QueryParam("markerDbId") String arrstrmarkerDbId,
	 * 
	 * @DefaultValue("false") @QueryParam("expandHomozygotes") String
	 * strexpandHomozygotes,
	 * 
	 * @DefaultValue("N") @QueryParam("unknownString") String unknownString,
	 * 
	 * @DefaultValue("|") @QueryParam("sepPhased") String sepPhased,
	 * 
	 * @DefaultValue("/") @QueryParam("sepUnphased") String sepUnphased,
	 * 
	 * @DefaultValue("") @QueryParam("format") String format,
	 * 
	 * @DefaultValue("") @QueryParam("pageSize") String pageSize,
	 * 
	 * @DefaultValue("") @QueryParam("page") String page) { // { // throws
	 * JSONException {
	 */

	/*
	 * String[] arrmarkerprofileDbId=arrstrmarkerprofileDbId.split("[\n,]");
	 * String[] arrmarkerDbId=arrstrmarkerDbId.split("[\n,]"); List
	 * listmarkerprofileDbId=new ArrayList(); List listmarkerDbId=new ArrayList();
	 * for(int i=0; i< arrmarkerprofileDbId.length; i++)
	 * listmarkerprofileDbId.add(arrmarkerprofileDbId[i]); if(arrmarkerDbId!=null)
	 * for(int i=0; i< arrmarkerDbId.length; i++)
	 * listmarkerDbId.add(arrmarkerDbId[i]); boolean
	 * expandHomozygotes=Boolean.valueOf(strexpandHomozygotes);
	 * 
	 */

	/*
	 * public Response postAlleleMatrix(
	 * 
	 * @NotNull @QueryParam("markerprofileDbId") String[] arrmarkerprofileDbId,
	 * 
	 * @QueryParam("markerDbId") String[] arrmarkerDbId,
	 * 
	 * @DefaultValue("false") @QueryParam("expandHomozygotes") boolean
	 * expandHomozygotes,
	 * 
	 * @DefaultValue("N") @QueryParam("unknownString") String unknownString,
	 * 
	 * @DefaultValue("|") @QueryParam("sepPhased") String sepPhased,
	 * 
	 * @DefaultValue("/") @QueryParam("sepUnphased") String sepUnphased,
	 * 
	 * @DefaultValue("") @QueryParam("format") String format,
	 * 
	 * @DefaultValue("") @QueryParam("pageSize") String pageSize,
	 * 
	 * @DefaultValue("") @QueryParam("page") String page) { // { // throws
	 * JSONException {
	 * 
	 * List listmarkerprofileDbId=new ArrayList(); List listmarkerDbId=new
	 * ArrayList(); for(int i=0; i< arrmarkerprofileDbId.length; i++)
	 * listmarkerprofileDbId.add(arrmarkerprofileDbId[i]); if(arrmarkerDbId!=null)
	 * for(int i=0; i< arrmarkerDbId.length; i++)
	 * listmarkerDbId.add(arrmarkerDbId[i]);
	 * 
	 * boolean expandHomozygotes=Boolean.valueOf(strexpandHomozygotes);
	 * AppContext.debug("postAlleleMatrix: markerDbId=" +
	 * (listmarkerDbId!=null?listmarkerDbId.size():"null") + " markerprofileDbId=" +
	 * (listmarkerprofileDbId!=null?listmarkerprofileDbId.size():"null"));
	 * if(listmarkerDbId!=null && listmarkerDbId.size()<20)
	 * AppContext.debug("listmarkerDbId=" + listmarkerDbId);
	 * if(listmarkerprofileDbId!=null && listmarkerprofileDbId.size()<20)
	 * AppContext.debug("listmarkerprofileDbId=" + listmarkerprofileDbId);
	 * 
	 * /* public Response postAlleleMatrix(
	 * 
	 * @NotNull @RequestParam("markerprofileDbId") List<String>
	 * listmarkerprofileDbId,
	 * 
	 * @RequestParam("markerDbId") List<String> listmarkerDbId,
	 * 
	 * @DefaultValue("false") @RequestParam("expandHomozygotes") String
	 * strexpandHomozygotes,
	 * 
	 * @DefaultValue("N") @RequestParam("unknownString") String unknownString,
	 * 
	 * @DefaultValue("|") @RequestParam("sepPhased") String sepPhased,
	 * 
	 * @DefaultValue("/") @RequestParam("sepUnphased") String sepUnphased,
	 * 
	 * @DefaultValue("") @RequestParam("format") String format,
	 * 
	 * @DefaultValue("") @RequestParam("pageSize") String pageSize,
	 * 
	 * @DefaultValue("") @RequestParam("page") String page) { // { // throws
	 * JSONException {
	 * 
	 * boolean expandHomozygotes=Boolean.valueOf(strexpandHomozygotes);
	 * AppContext.debug("postAlleleMatrix: markerDbId=" +
	 * (listmarkerDbId!=null?listmarkerDbId.size():"null") + " markerprofileDbId=" +
	 * (listmarkerprofileDbId!=null?listmarkerprofileDbId.size():"null"));
	 * if(listmarkerDbId!=null && listmarkerDbId.size()<20)
	 * AppContext.debug("listmarkerDbId=" + listmarkerDbId);
	 * if(listmarkerprofileDbId!=null && listmarkerprofileDbId.size()<20)
	 * AppContext.debug("listmarkerprofileDbId=" + listmarkerprofileDbId);
	 */

	/*
	 * AppContext.debug("postAlleleMatrix: markerDbId=" +markerDbId +
	 * " markerprofileDbId=" + markerprofileDbId);
	 * 
	 * String markerprofileDbIdstr[]=markerprofileDbId.split("[\n,]"); List<String>
	 * listmarkerprofileDbId=new ArrayList(); for(int i=0; i<
	 * markerprofileDbIdstr.length; i++) {
	 * 
	 * String s=markerprofileDbIdstr[i].trim(); if(!s.isEmpty())
	 * listmarkerprofileDbId.add(s); }
	 * 
	 * 
	 * String markerDbIdstr[]=markerDbId.split("[\n,]"); List<String>
	 * listmarkerDbId=new ArrayList(); for(int i=0; i< markerDbIdstr.length; i++) {
	 * String s=markerDbIdstr[i].trim(); if(!s.isEmpty()) listmarkerDbId.add(s); }
	 * 
	 */

	public Response postAlleleMatrix(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@RequestBody RequestAlleleMatrix request) {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		List listmarkerprofileDbId = request.getMarkerprofileDbId();
		List listmarkerDbId = request.getMarkerDbId();
		boolean expandHomozygotes = request.isExpandHomozygotes();
		String unknownString = request.getUnknownString();
		String sepPhased = request.getSepPhased();
		String sepUnphased = request.getSepUnphased();
		String format = request.getFormat();
		String pageSize = request.getPageSize();
		String page = request.getPage();

		String pagingstatus = validatePaging(pageSize, page, AppContext.getMaxGenotypeElements());
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		return allelematrix(listmarkerprofileDbId, listmarkerDbId, expandHomozygotes, unknownString, sepPhased,
				sepUnphased, format, true, "allelematrix-search", pageSize, page, true);
	}

	@Path("/allelematrix")
	@GET
	@Produces({ "application/json", "text/plain" })
	// allelematrix?markerprofileDbId=100&markerprofileDbId=101&markerDbId=322&markerDbId=418&pageSize=&page=

	public Response getAlleleMatrix(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearer,
			@NotNull @QueryParam("markerprofileDbId") List<String> markerprofileDbId,
			@DefaultValue("") @QueryParam("markerDbId") List<String> markerDbId,
			@DefaultValue("false") @QueryParam("expandHomozygotes") boolean expandHomozygotes,
			@DefaultValue("N") @QueryParam("unknownString") String unknownString,
			@DefaultValue("|") @QueryParam("sepPhased") String sepPhased,
			@DefaultValue("/") @QueryParam("sepUnphased") String sepUnphased,
			@DefaultValue("json") @QueryParam("format") String format,
			@DefaultValue("") @QueryParam("pageSize") String pageSize,
			@DefaultValue("") @QueryParam("page") String page) { // throws JSONException {

		String tokenmsg = checkToken(bearer);
		if (!tokenmsg.equals(OAUTH_ACTIVE)) {
			Map mapStatus = new LinkedHashMap();
			mapStatus.put("code", tokenmsg);
			return createJSONTokenResponse(mapStatus, new HashMap());
		}

		String pagingstatus = validatePaging(pageSize, page, AppContext.getMaxGenotypeElements());
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		AppContext.debug("getAlleleMatrix: markerDbId=" + markerDbId + " markerprofileDbId=" + markerprofileDbId);

		if (markerprofileDbId.size() == 1) {
			String markerprofileDbIdstr[] = markerprofileDbId.get(0).split("[\n,]");
			List<String> listmarkerprofileDbId = new ArrayList();
			for (int i = 0; i < markerprofileDbIdstr.length; i++) {

				String s = markerprofileDbIdstr[i].trim();
				if (!s.isEmpty())
					listmarkerprofileDbId.add(s);
			}
			markerprofileDbId = listmarkerprofileDbId;
		}

		if (markerDbId.size() == 1) {
			String markerDbIdstr[] = markerDbId.get(0).split("[\n,]");
			List<String> listmarkerDbId = new ArrayList();
			for (int i = 0; i < markerDbIdstr.length; i++) {
				String s = markerDbIdstr[i].trim();
				if (!s.isEmpty())
					listmarkerDbId.add(s);
			}
			markerDbId = listmarkerDbId;
		}

		return allelematrix(markerprofileDbId, markerDbId, expandHomozygotes, unknownString, sepPhased, sepUnphased,
				format, true, "allelematrix", pageSize, page, true);

	}

	// //@RequestMapping(value = "/postallelematrix", method = RequestMethod.POST)
	// @Path("/postallelematrix")
	// @POST
	// //@ResponseBody
	// @Consumes({ MediaType.APPLICATION_FORM_URLENCODED ,
	// MediaType.MULTIPART_FORM_DATA})
	// @Produces({"application/json", "text/plain"})
	// public Response postallelematrix(HttpServletRequest request){
	//
	// String markerprofileDbIdstr[]=request.getParameter("markerprofileDbId"
	// ).split("\n");
	// List<String> listmarkerprofileDbId=new ArrayList();
	// for(int i=0; i< markerprofileDbIdstr.length; i++) {
	//
	// String s=markerprofileDbIdstr[i].trim();
	// if(!s.isEmpty()) listmarkerprofileDbId.add(s);
	// }
	//
	// String markerDbIdstr[]=request.getParameter("markerDbId" ).split("\n");
	// List<String> listmarkerDbId=new ArrayList();
	// for(int i=0; i< markerDbIdstr.length; i++) {
	// String s=markerDbIdstr[i].trim();
	// if(!s.isEmpty()) listmarkerDbId.add(s);
	// }
	//
	// return allelematrix(listmarkerprofileDbId,listmarkerDbId,
	// request.getParameter("format" )) ;
	// }
	//

	private String checkMarkerprofileDbId(Collection markerprofileDbId, Collection listlids) {
		String studyId = null;

		try {

			AppContext.debug("checkMarkerprofileDbId markerprofileDbId=" + markerprofileDbId.size() + " listlids="
					+ listlids.size());
			String study = null;
			long min = Long.MAX_VALUE;
			long max = -1;
			Iterator<String> itIds = markerprofileDbId.iterator();
			while (itIds.hasNext()) {
				Long lid = Long.valueOf(itIds.next().trim());
				if (lid < min)
					min = lid;
				if (lid > max)
					max = lid;
				listlids.add(lid);
			}
			if (min >= 1 && max <= 3024)
				studyId = "3kfiltered";
			else if (min >= 10001 && max <= 13024)
				studyId = "3kcore";
			else if (min >= 20001 && max <= 23024)
				studyId = "3kbase";
			else if (min >= 30001 && max <= 33024)
				studyId = "3kall";
			else if (min >= 3025 && max <= 4592)
				studyId = "hdra";

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		AppContext.debug("checkMarkerprofileDbId:" + studyId);
		return studyId;
	}

	private Response allelematrix(final List<String> markerprofileDbId, final List<String> markerDbId,
			final boolean expandHomozygotes, final String unknownString, final String sepPhased,
			final String sepUnphased, final String format, final boolean matrix, final String call,
			final String pageSize, final String page, boolean streaming) { // , Writer w, Long actualCount) {

		if (!streaming || !bStreaming || format.equals("tsv"))
			return _allelematrix(markerprofileDbId, markerDbId, expandHomozygotes, unknownString, sepPhased,
					sepUnphased, format, matrix, call, pageSize, page, null); // , null, null);

		StreamingOutput stream = new StreamingOutput() {
			@Override
			public void write(OutputStream os) throws IOException, WebApplicationException {
				Writer writer = new BufferedWriter(new OutputStreamWriter(os));

				Map mapResult = new LinkedHashMap();

				// mapResult.put("data", varmarkerAlleles);

				// writer.append("{\"result\":{\"data\":[\n");

				String valpaging = validatePaging(pageSize, page);

				// Long actualCount=-1L;
				// Long totalCount=-1L;

				// writer.write("{\n");

				_allelematrix(markerprofileDbId, markerDbId, expandHomozygotes, unknownString, sepPhased, sepUnphased,
						format, matrix, call, pageSize, page, writer); // , actualCount,totalCount);
				// writer.write("\n]},\n\"metadata\":");

				/*
				 * writer.write(",\n"); if(valpaging.equals(PAGING_NOTSET)) writer.write( new
				 * ObjectMapper().writeValueAsString( createMetaData(actualCount, 0, totalCount,
				 * (totalCount/actualCount)+1, new HashMap(), new ArrayList()) )); else
				 * if(valpaging.equals(PAGING_VALID)) {
				 * 
				 * writer.write( new ObjectMapper().writeValueAsString( createMetaData(
				 * Long.valueOf(pageSize), Long.valueOf(page), totalCount,
				 * (totalCount/Long.valueOf(pageSize))+1, new HashMap(), new ArrayList()) ));
				 * if(actualCount!=Long.valueOf(pageSize)) {
				 * AppContext.debug("actualCount!=Long.valueOf(pageSize) " +
				 * actualCount+","+pageSize); } }
				 */
				// writer.write("\n}");

				AppContext.resetTimer("allelematrix BrAPI format (StreamingOutput)..");

				writer.flush();
				// writer.close();

				// CommittingOutputStream cos=(CommittingOutputStream)os;
				AppContext.debug("os.class:" + os.getClass().getCanonicalName());

				// String strout=new String( ((ByteArrayOutputStream)os).toByteArray());
				// if(AppContext.isLocalhost()) AppContext.debug( "write(OutputStream os):" +
				// strout);
			}
		};

		// AppContext.debug( "stream.class:" + stream.getClass().getCanonicalName());

		return Response.ok(stream).build();

	}

	private long _countAllelematrix(List<String> markerprofileDbId, List<String> markerDbId, boolean expandHomozygotes,
			String unknownString, String sepPhased, String sepUnphased, String format, boolean matrix, String call,
			String pageSize, String page) {

		Collection listIds = new ArrayList();
		String studyId = checkMarkerprofileDbId(markerprofileDbId, listIds);
		if (studyId == null)
			Response.status(501).entity("markerprofileDbIds come from different studies").build();

		String sVarids = null;
		long offset = 0;
		Set<BigDecimal> setVarids = null;
		if (studyId.startsWith("3k")) {
			setVarids = new HashSet();
			if (studyId.equals("3kfiltered"))
				;
			else if (studyId.equals("3kcore"))
				offset = 10000;
			else if (studyId.equals("3kbase"))
				offset = 20000;
			else if (studyId.equals("3kall"))
				offset = 30000;

			if (listIds.size() == 3024)
				sVarids = "all";

			Iterator<Long> itProfileid = listIds.iterator();
			while (itProfileid.hasNext()) {
				setVarids.add(BigDecimal.valueOf(itProfileid.next() - offset));
			}

		} else if (studyId.equals("hdra")) {
			setVarids = new HashSet();
			if (listIds.size() == 1568)
				sVarids = "all";
			Iterator<Long> itProfileid = listIds.iterator();
			while (itProfileid.hasNext()) {
				setVarids.add(BigDecimal.valueOf(itProfileid.next() - offset));
			}

		}

		AppContext.debug("allelematrix for " + (markerDbId == null ? "0" : markerDbId.size()) + " markers, "
				+ markerprofileDbId.size() + " profiles, varids=" + (setVarids == null ? "0" : setVarids.size()));

		Map<String, Set> mapCont2Pos = null;
		Map<String, long[]> mapCont2Range = null;

		if (markerDbId == null || markerDbId.isEmpty()) {
			// all
			mapCont2Range = new TreeMap();
			listitemsDAO = (ListItemsDAO) AppContext.checkBean(this.listitemsDAO, "ListItems");
			Iterator<Scaffold> scaffs = listitemsDAO.getOrganismScaffolds(AppContext.getDefaultOrganism()).iterator();
			while (scaffs.hasNext()) {
				Scaffold scaf = scaffs.next();
				mapCont2Range.put(scaf.getName(), new long[] { 1L, scaf.getLength() });
			}

		} else {

			if (markerDbId.size() == 1) {
				String[] markerdbs = markerDbId.get(0).split(",");
				List listNewIds = new ArrayList();
				for (int i = 0; i < markerdbs.length; i++) {
					listNewIds.add(markerdbs[i]);
				}
				markerDbId = listNewIds;
			}

			Iterator<String> itMarker = markerDbId.iterator();
			mapCont2Pos = new TreeMap();
			while (itMarker.hasNext()) {
				String markerids[] = itMarker.next().split("_");

				Set setpos = mapCont2Pos.get(markerids[0].trim());
				if (setpos == null) {
					setpos = new TreeSet();
					mapCont2Pos.put(markerids[0].trim(), setpos);
				}
				setpos.add(BigDecimal.valueOf(Long.valueOf(markerids[1].trim())));
			}
		}

		AppContext.debug("params=varids " + setVarids.size() + " mapCont2Range="
				+ (mapCont2Range != null ? mapCont2Range.size() : "null") + " mapCont2Pos="
				+ (mapCont2Pos != null ? mapCont2Pos.size() : "null"));

		Map mapAllconts = new HashMap();
		if (matrix) {
			mapAllconts.put("allVars", new LinkedHashSet());
			mapAllconts.put("markerAlleles", new LinkedHashMap());
			mapAllconts.put("varmarkerAlleles", new ArrayList());
		} else {
			mapAllconts.put("data", new ArrayList());
		}

		long countAlleles = -1;

		try {
			if (mapCont2Range != null) {
				if (countAlleles < 0)
					countAlleles = 0;

				Iterator<String> itCont = mapCont2Range.keySet().iterator();
				while (itCont.hasNext()) {
					String cont = itCont.next();
					long range[] = mapCont2Range.get(cont);
					long cntallele = countVariantTable(setVarids, cont, range[0], range[1], true, false, false, false,
							null, null, null, false, format, expandHomozygotes, unknownString, sepPhased, sepUnphased,
							studyId, matrix);
					if (cntallele < 0) {
						AppContext.debug(
								"cntallele=" + cntallele + " for " + cont + " [" + range[0] + "-" + range[1] + "]");
						return cntallele;
					}
					countAlleles += cntallele;
				}

			} else if (mapCont2Pos != null) {
				if (countAlleles < 0)
					countAlleles = 0;
				Iterator<String> itCont = mapCont2Pos.keySet().iterator();
				while (itCont.hasNext()) {
					String cont = itCont.next();
					Set colPos = mapCont2Pos.get(cont);
					long cntallele = countVariantTable(setVarids, cont, null, null, true, false, false, false, colPos,
							null, null, false, format, expandHomozygotes, unknownString, sepPhased, sepUnphased,
							studyId, matrix);
					if (cntallele < 0) {
						AppContext.debug("cntallele=" + cntallele + " for " + cont + " (" + colPos + ")");
						return cntallele;
					}
					countAlleles += cntallele;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return countAlleles;

	}

	private Response _allelematrix(List<String> markerprofileDbId, List<String> markerDbId, boolean expandHomozygotes,
			String unknownString, String sepPhased, String sepUnphased, String format, boolean matrix, String call,
			String pageSize, String page, Writer w) { // , String sortorder) { //, Long actualSize, Long totalCount) {

		String pagingstatus = validatePaging(pageSize, page, AppContext.getMaxGenotypeElements());
		if (!(pagingstatus.equals(PAGING_VALID) || pagingstatus.equals(PAGING_NOTSET)))
			return Response.status(400).entity(pagingstatus).build();

		boolean nopaging = pagingstatus.equals(PAGING_NOTSET);
		long lpagesize = -1;
		long lpage = -1;
		if (pagingstatus.equals(PAGING_VALID)) {
			lpagesize = Long.valueOf(pageSize);
			lpage = Long.valueOf(page);
		}

		Collection listIds = new ArrayList();
		String studyId = checkMarkerprofileDbId(markerprofileDbId, listIds);
		if (studyId == null)
			Response.status(501).entity("markerprofileDbIds come from different studies").build();

		long maxregionlength = -1;

		String sVarids = null;
		long offset = 0;
		Set<BigDecimal> setVarids = null;

		maxregionlength = AppContext.getMaxlengthUniDownload(studyId);

		/*
		 * if(studyId.startsWith("3k")) { setVarids=new HashSet();
		 * if(studyId.equals("3kfiltered"))
		 * maxregionlength=AppContext.getMaxlengthCore()/10; //
		 * AppContext.getMaxlengthUni(); else if(studyId.equals("3kcore")) {
		 * offset=10000; maxregionlength=AppContext.getMaxlengthCore(); } else
		 * if(studyId.equals("3kbase")) { offset=20000;
		 * maxregionlength=AppContext.getMaxlengthUni("snp_all"); } else
		 * if(studyId.equals("3kall")) { offset=30000;
		 * maxregionlength=AppContext.getMaxlengthUni("snp_all"); }
		 * 
		 * if(listIds.size()==3024) sVarids="all";
		 * 
		 * Iterator<Long> itProfileid=listIds.iterator(); while(itProfileid.hasNext()) {
		 * setVarids.add(BigDecimal.valueOf( itProfileid.next()-offset )); }
		 * maxregionlength=maxregionlength*3024/setVarids.size();
		 * 
		 * } else if(studyId.equals("hdra")) { setVarids=new HashSet();
		 * if(listIds.size()==1568) sVarids="all"; Iterator<Long>
		 * itProfileid=listIds.iterator(); while(itProfileid.hasNext()) {
		 * setVarids.add(BigDecimal.valueOf( itProfileid.next()-offset )); }
		 * //maxregionlength=AppContext.getMaxlengthHdra()*1568/setVarids.size();
		 * maxregionlength=AppContext.getMaxlengthCore()*1568/(2*setVarids.size()); }
		 */

		long maxpossize = AppContext.getMaxGenotypeElements() / setVarids.size();

		AppContext.debug("allelematrix for " + (markerDbId == null ? "0" : markerDbId.size()) + " markers, "
				+ markerprofileDbId.size() + " profiles, varids=" + (setVarids == null ? "0" : setVarids.size()));

		Map<String, List<Set>> mapCont2Pos = null;
		// Map<String,long[]> mapCont2Range=null;
		// Map<String,List<long[]>> mapCont2Range=null;
		Map<String, long[]> mapCont2Range = null;
		Map<String, List<long[]>> mapCont2Ranges = null;
		// List<long[]> listRange=null;
		Map<String, Set> mapCont2AllPos = null;

		long lranges = 0;
		if (markerDbId == null || markerDbId.isEmpty()) {
			// all
			String url = null;
			String gzipped = "";
			if (bTSVGzipped)
				gzipped = ".gz";
			if (studyId.equals("3kcore")) {
				url = AppContext.getHostname() + "/" + AppContext.getTempFolder()
						+ "allelematrix-search_8391757687721752210.tsv" + gzipped;
			} else if (studyId.equals("3kfiltered")) {
				url = AppContext.getHostname() + "/" + AppContext.getTempFolder()
						+ "allelematrix-search_3511787879975240221.tsv" + gzipped;
			} else if (studyId.equals("3kbase")) {
				url = AppContext.getHostname() + "/" + AppContext.getTempFolder()
						+ "allelematrix-search_225756051809319271.tsv" + gzipped;
			} else if (studyId.equals("3kall")) {
				url = AppContext.getHostname() + "/" + AppContext.getTempFolder()
						+ "allelematrix-search_4300475393356737648.tsv" + gzipped;
			} /*
				 * else if(studyId.equals("hdra")) { url = AppContext.getHostname()
				 * +"/"+AppContext.getTempFolder() +
				 * "allelematrix-search_3943839937834847392.tsv"; }
				 */

			if (url != null) {
				Map mapResult = new LinkedHashMap();
				mapResult.put("makerprofileDbIds", new ArrayList());
				mapResult.put("data", new ArrayList());

				Map mapMeta = new LinkedHashMap();
				Map mapPagi = new LinkedHashMap();
				mapPagi.put("pageSize", 0);
				mapPagi.put("currentPage", 0);
				mapPagi.put("totalCount", 0);
				mapPagi.put("totalPages", 1);

				mapMeta.put("pagination", mapPagi);
				mapMeta.put("status", new ArrayList());
				List listUrl = new ArrayList();
				Map mapUrl = new HashMap();
				mapUrl.put("url", url);
				listUrl.add(mapUrl);
				mapMeta.put("datafiles", listUrl);

				Map response = new LinkedHashMap();
				response.put("metadata", mapMeta);
				response.put("result", mapResult);

				AppContext.resetTimer("allelematrix BrAPI format..");
				this.createJSONResponse(response);
			}

			/*
			 * mapCont2Range=new TreeMap();
			 * listitemsDAO=(ListItemsDAO)AppContext.checkBean(this.listitemsDAO,
			 * "ListItems"); Iterator<Scaffold> scaffs=
			 * listitemsDAO.getOrganismScaffolds(AppContext.getDefaultOrganism()).iterator()
			 * ; while(scaffs.hasNext()) { Scaffold scaf=scaffs.next(); long prevend=0;
			 * while(prevend<scaf.getLength()) { long curend= Long.min( scaf.getLength(),
			 * prevend+maxregionlength);
			 * 
			 * List contranges = mapCont2Range.get(scaf.getName()); if(contranges==null) {
			 * contranges=new ArrayList(); mapCont2Range.put( scaf.getName(), contranges); }
			 * contranges.add(new long[]{ (prevend+1), curend}); prevend=curend; } }
			 */

			GenotypeQueryParams params = new GenotypeQueryParams(
					// null, sc.getName(), 1L, sc.getLength(),
					null, null, null, null, true, false, getSnpset(studyId), getDataset(studyId),
					getGenotypeRun(studyId), false, null, null, null, false, false, null);

			params = setStudy(params, studyId);

			if (format.equals("json")) {

				mapCont2Range = new TreeMap();
				if (pagingstatus.equals(PAGING_VALID)) {
					params.setPaging(Long.valueOf(page), Long.valueOf(pageSize) / setVarids.size());
				} else if (pagingstatus.equals(PAGING_NOTSET)) {
					// nopaging
					params.setPaging(Long.valueOf(0), maxpossize); /// setVarids.size());
				}

				List<SnpsAllvarsPos> listpos = genotype.getSNPPoslist(params);
				// long lcount=genotype.countSNPPoslist(params);
				// if(lcount<0) throw new RuntimeException("countSNPPoslist not implemented");

				// positions in page only
				SnpsAllvarsPos posstart = listpos.get(0);
				SnpsAllvarsPos posend = listpos.get(listpos.size() - 1);
				if (posstart.getContig().equals(posend.getContig())) {
					mapCont2Range.put(posend.getContig(),
							new long[] { posstart.getPosition().longValue(), posend.getPosition().longValue() });
				} else {
					Iterator<SnpsAllvarsPos> itPos = listpos.iterator();
					SnpsAllvarsPos prevpos = null;
					while (itPos.hasNext()) {
						SnpsAllvarsPos curpos = itPos.next();
						if (prevpos != null && !curpos.getContig().equals(prevpos.getContig())) {
							//
							mapCont2Range.get(prevpos.getContig())[1] = prevpos.getPosition().longValue();
							mapCont2Range.put(curpos.getContig(), new long[] { curpos.getPosition().longValue(), -1 });
						} else if (prevpos == null) {
							mapCont2Range.put(curpos.getContig(), new long[] { curpos.getPosition().longValue(), -1 });
						}
						prevpos = curpos;
					}
					mapCont2Range.get(prevpos.getContig())[1] = prevpos.getPosition().longValue();
				}
			} else { // tsv
				long ltotalpos = genotype.countSNPPoslist(params);
				mapCont2Ranges = new TreeMap();
				listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");
				Iterator<Scaffold> itScaf = listitemsDAO.getOrganismScaffolds(AppContext.getDefaultOrganism())
						.iterator();
				while (itScaf.hasNext()) {
					Scaffold sc = itScaf.next();
					List listRanges = new ArrayList();

					long lstart = 1;
					for (lstart = 1; (lstart + maxregionlength - 1) < sc.getLength(); lstart += maxregionlength) {
						listRanges.add(new long[] { lstart, lstart + maxregionlength - 1 });
						lranges++;
					}
					if (sc.getLength() > lstart - maxregionlength) {
						long lstart1 = lstart - maxregionlength;
						if (lstart1 < 1)
							lstart1 = 1L;
						listRanges.add(new long[] { lstart1, sc.getLength() });
						lranges++;
					}
					mapCont2Ranges.put(sc.getName(), listRanges);
				}

				/*
				 * // positions all query SnpsAllvarsPos posstart = listpos.get(0);
				 * SnpsAllvarsPos posend = listpos.get(listpos.size()-1);
				 * if(posstart.getContig().equals(posend.getContig())) {
				 * mapCont2Range.put(posend.getContig() , new
				 * long[]{posstart.getPosition().longValue(),
				 * posend.getPosition().longValue()}); } else { Iterator<SnpsAllvarsPos>
				 * itPos=listpos.iterator(); SnpsAllvarsPos prevpos=null; while(itPos.hasNext())
				 * { SnpsAllvarsPos curpos=itPos.next(); if(prevpos!=null &&
				 * !curpos.getContig().equals(prevpos.getContig())) { //
				 * mapCont2Range.get(prevpos.getContig())[1]=prevpos.getPosition().longValue();
				 * mapCont2Range.put(curpos.getContig(),new
				 * long[]{curpos.getPosition().longValue(), -1 } ); } else if(prevpos==null) {
				 * mapCont2Range.put(curpos.getContig(),new
				 * long[]{curpos.getPosition().longValue(), -1 } ); } prevpos=curpos; }
				 * mapCont2Range.get(prevpos.getContig())[1]=prevpos.getPosition().longValue();
				 * }
				 */
			}

		} else {

			if (markerDbId.size() == 1) {
				String[] markerdbs = markerDbId.get(0).split(",");
				List listNewIds = new ArrayList();
				for (int i = 0; i < markerdbs.length; i++) {
					listNewIds.add(markerdbs[i]);
				}
				markerDbId = listNewIds;
			}

			mapCont2Pos = new TreeMap();
			Iterator<String> itMarker = new TreeSet(markerDbId).iterator();
			// Map<String,Set> mapCont2AllPos=new TreeMap();
			mapCont2AllPos = new TreeMap();
			while (itMarker.hasNext()) {
				String markerids[] = itMarker.next().split("_");

				Set setpos = mapCont2AllPos.get(markerids[0].trim());
				if (setpos == null) {
					setpos = new TreeSet();
					mapCont2AllPos.put(markerids[0].trim(), setpos);
				}
				setpos.add(BigDecimal.valueOf(Long.valueOf(markerids[1].trim())));
			}

			Iterator<String> itcont = mapCont2AllPos.keySet().iterator();
			while (itcont.hasNext()) {
				String cont = itcont.next();
				List listPosset = new ArrayList();
				mapCont2Pos.put(cont, listPosset);
				Set subset = new TreeSet();
				listPosset.add(subset);
				long setcnt = 0;
				Iterator<BigDecimal> itPos = mapCont2AllPos.get(cont).iterator();
				while (itPos.hasNext()) {
					BigDecimal pos = itPos.next();

					if (setcnt < maxpossize) {
						subset.add(pos);
						setcnt++;
					} else {
						subset = new TreeSet();
						listPosset.add(subset);
						subset.add(pos);
						setcnt = 1;
					}
				}
			}

		}

		if (AppContext.isLocalhost()) {
			AppContext.debug("params=varids " + setVarids.size() + " mapCont2Range="
					+ (mapCont2Range != null ? mapCont2Range.size() : "null") + " mapCont2Pos="
					+ (mapCont2Pos != null ? mapCont2Pos : "null") + " mapCont2Ranges="
					+ (mapCont2Ranges != null ? mapCont2Ranges.size() : "null"));
			if (mapCont2Range != null) {
				Iterator<String> itRanges = mapCont2Range.keySet().iterator();
				StringBuffer buf = new StringBuffer();
				while (itRanges.hasNext()) {
					String cont = itRanges.next();
					buf.append(cont + ":" + mapCont2Range.get(cont)[0] + "-" + mapCont2Range.get(cont)[1] + ",");
				}
				AppContext.debug(buf.toString());
			} else if (mapCont2Ranges != null) {
				Iterator<String> itRanges = mapCont2Ranges.keySet().iterator();
				StringBuffer buf = new StringBuffer();
				while (itRanges.hasNext()) {
					String cont = itRanges.next();
					Iterator<long[]> itrange = mapCont2Ranges.get(cont).iterator();
					buf.append(cont + ":");
					while (itrange.hasNext()) {
						long[] range = itrange.next();
						buf.append(range[0] + "-" + range[1] + ",");
					}
				}
				AppContext.debug(buf.toString());
			}
		}

		if (format.equals("tsv")) {

			if (mapCont2Ranges != null) {
				Map response = new LinkedHashMap();
				try {

					// AppContext.debug("count mapCont2Ranges=" + mapCont2Range);
					String tsvfile = call + "_" + AppContext.createTempFilename() + "." + format;
					BufferedWriter bw = new BufferedWriter(new FileWriter(AppContext.getTempDir() + tsvfile));

					Iterator<String> itCont = mapCont2Ranges.keySet().iterator();
					boolean isfirst = true;
					long lcntranges = 0;
					while (itCont.hasNext()) {
						String cont = itCont.next();

						Iterator<long[]> itRanges = mapCont2Ranges.get(cont).iterator();
						while (itRanges.hasNext()) {
							long range[] = itRanges.next(); // mapCont2Range.get(cont);

							lcntranges++;
							AppContext.debug("writing tsv range " + cont + " " + lcntranges + "/" + lranges);
							try {
								Map table = getVariantTable(setVarids, cont, range[0], range[1], true, false, false,
										false, null, null, null, false, format, expandHomozygotes, unknownString,
										sepPhased, sepUnphased, studyId, matrix);
								// createAlleleMatrixResponse(new TreeSet((List)table.get("allVars")),
								// (Map)table.get("markerAlleles") , (List)table.get("varmarkerAlleles"),
								// format,call,studyId);
								// createAlleleMatrixResponse(Set listVars, Map markerAlleles, List
								// varmarkerAlleles, String format, String call, String study)
								// Map<String,Collection<String>>
								// mapData=(Map<String,Collection<String>>)markerAlleles;
								createTsvAlleleMatrix(format, studyId, call, new TreeSet((List) table.get("allVars")),
										(Map) table.get("markerAlleles"), bw, isfirst);
								isfirst = false;
								bw.flush();
							} catch (Exception ex) {
								ex.printStackTrace();
							}

						}
					}

					bw.flush();
					bw.close();
					Map mapResult = new LinkedHashMap();
					mapResult.put("makerprofileDbIds", new ArrayList());
					mapResult.put("data", new ArrayList());

					Map mapMeta = new LinkedHashMap();
					Map mapPagi = new LinkedHashMap();
					mapPagi.put("pageSize", 0);
					mapPagi.put("currentPage", 0);
					mapPagi.put("totalCount", 0);
					mapPagi.put("totalPages", 1);

					mapMeta.put("pagination", mapPagi);
					mapMeta.put("status", new ArrayList());
					List listUrl = new ArrayList();
					Map mapUrl = new HashMap();
					mapUrl.put("url", AppContext.getHostname() + "/" + AppContext.getTempFolder() + tsvfile);
					listUrl.add(mapUrl);
					mapMeta.put("datafiles", listUrl);

					response.put("metadata", mapMeta);
					response.put("result", mapResult);

					AppContext.debug("tsv file written to " + AppContext.getTempDir() + tsvfile);

					return Response.status(200).entity(new ObjectMapper().writeValueAsString(response)).build();
				} catch (Exception ex) {
					ex.printStackTrace();
					return Response.status(500).entity(ex.getMessage()).build();
				}

			}

		}

		try {

			Map mapAllconts = new HashMap();
			if (matrix) {
				mapAllconts.put("allVars", new LinkedHashSet());
				mapAllconts.put("markerAlleles", new LinkedHashMap());
				mapAllconts.put("varmarkerAlleles", new ArrayList());
			} else {
				mapAllconts.put("data", new ArrayList());
			}

			// long countAlleles=-1;

			long countAlleles = -1;

			if (mapCont2Range != null) {
				if (countAlleles < 0)
					countAlleles = 0;

				AppContext.debug("count mapCont2Range=" + mapCont2Range);

				Iterator<String> itCont = mapCont2Range.keySet().iterator();
				while (itCont.hasNext()) {
					String cont = itCont.next();
					long range[] = mapCont2Range.get(cont); // mapCont2Range.get(cont);
					long curcount = countVariantTable(setVarids, cont, range[0], range[1], true, false, false, false,
							null, null, null, false, format, expandHomozygotes, unknownString, sepPhased, sepUnphased,
							studyId, matrix);
					countAlleles += curcount;
				}
			} else if (mapCont2Pos != null) {
				if (countAlleles < 0)
					countAlleles = 0;
				AppContext.debug("count mapCont2Pos=" + mapCont2Pos);
				Iterator<String> itCont = mapCont2Pos.keySet().iterator();
				while (itCont.hasNext()) {
					String cont = itCont.next();

					Iterator<Set> itlistPosset = mapCont2Pos.get(cont).iterator();
					while (itlistPosset.hasNext()) {

						Set colPos = itlistPosset.next();
						long curcount = countVariantTable(setVarids, cont, null, null, true, false, false, false,
								colPos, null, null, false, format, expandHomozygotes, unknownString, sepPhased,
								sepUnphased, studyId, matrix);
						if (curcount != colPos.size() * setVarids.size())
							AppContext.debug("curcount!=colPos.size()*setVarids.size() for " + cont + " varids="
									+ setVarids.size() + " colPos=" + colPos.size() + " curcount=" + curcount);
						countAlleles += curcount;
					}
				}
			}

			// counting all
			long ltotalCount = -1;
			if (markerDbId == null || markerDbId.isEmpty()) {
				ltotalCount = countVariantTable(setVarids, null, null, null, true, false, false, false, null, null,
						null, false, format, expandHomozygotes, unknownString, sepPhased, sepUnphased, studyId, matrix);
				// if(totalCount!=null) totalCount=ltotalCount;
			} else {
				AppContext.debug("count mapCont2PosAll=" + mapCont2Pos);
				Iterator<String> itCont = mapCont2AllPos.keySet().iterator();
				ltotalCount = 0;
				while (itCont.hasNext()) {
					String cont = itCont.next();
					Set posset = mapCont2AllPos.get(cont);
					long contallelecount = countVariantTable(setVarids, cont, null, null, true, false, false, false,
							posset, null, null, false, format, expandHomozygotes, unknownString, sepPhased, sepUnphased,
							studyId, matrix);
					if (contallelecount != posset.size() * setVarids.size())
						AppContext.debug("curcount!=colPos.size()*setVarids.size() for " + cont + " varids="
								+ setVarids.size() + " colPos=" + posset.size() + " curcount=" + contallelecount);
					ltotalCount += contallelecount;
				}
			}

			AppContext.debug("countAlleles=" + countAlleles + ", ltotalCount=" + ltotalCount);

			if (w != null) {
				w.append("{\"metadata\":");
				if (pagingstatus.equals(PAGING_NOTSET))
					w.write(new ObjectMapper().writeValueAsString(
							createMetaData(countAlleles, 0, ltotalCount, new HashMap(), new ArrayList())));
				else if (pagingstatus.equals(PAGING_VALID)) {

					w.write(new ObjectMapper().writeValueAsString(createMetaData(Long.valueOf(pageSize),
							Long.valueOf(page), ltotalCount, new HashMap(), new ArrayList())));
					if (countAlleles != Long.valueOf(pageSize) * setVarids.size()) {
						// AppContext.debug("actualCount!=Long.valueOf(pageSize) " +
						// actualCount+","+pageSize);
						AppContext.debug("countAlleles!=Long.valueOf(pageSize)*setVarids.size() at page " + page + "/"
								+ ((ltotalCount / Long.valueOf(pageSize)) + 1) + " " + countAlleles + ", "
								+ Long.valueOf(pageSize) * setVarids.size());
					}
				}
				w.append(",\n\"result\":{\"data\":[\n");
			}

			boolean bfirstallele = true;
			if (mapCont2Range != null) {
				if (countAlleles < 0)
					countAlleles = 0;

				AppContext.debug("mapCont2Range=" + mapCont2Range);

				Iterator<String> itCont = mapCont2Range.keySet().iterator();
				while (itCont.hasNext()) {
					String cont = itCont.next();

					// Iterator<long[]> itlistRanges=mapCont2Range.get(cont).iterator();
					// while(itlistRanges.hasNext()) {
					// long range[]=itlistRanges.next(); //mapCont2Range.get(cont);

					long range[] = mapCont2Range.get(cont); // mapCont2Range.get(cont);

					// long curcount=countVariantTable(setVarids, cont, range[0], range[1], true,
					// false, false, false, null, null, null, false, format,
					// expandHomozygotes, unknownString, sepPhased, sepUnphased, studyId, matrix);
					// countAlleles+=curcount;

					Map table = getVariantTable(setVarids, cont, range[0], range[1], true, false, false, false, null,
							null, null, false, format, expandHomozygotes, unknownString, sepPhased, sepUnphased,
							studyId, matrix);
					if (table == null)
						return Response.status(500).entity("table==null").build();

					if (!matrix) {
						((List) mapAllconts.get("data")).addAll((List) table.get("data"));
					} else {
						if (w != null) {
							// if(sortorder.equals("var,pos")) {
							if (true) {
								Iterator itAlleles = ((List) table.get("varmarkerAlleles")).iterator();
								// if(countAlleles>curcount && itAlleles.hasNext()) w.append(",\n");
								if (!bfirstallele && itAlleles.hasNext())
									w.append(",\n");
								bfirstallele = false;
								while (itAlleles.hasNext()) {
									// new String[]{ snpstr.getVar().toString(), marker, allele}
									String[] als = (String[]) itAlleles.next();

									StringBuffer buf = new StringBuffer();
									// buf.append("[\"").append(als[0]).append("\",\"").append(als[1]).append("\",\"").append(als[2]).append("\"]");
									buf.append("[\"").append(als[1]).append("\",\"").append(als[0]).append("\",\"")
											.append(als[2]).append("\"]");
									// AppContext.debug(buf.toString());
									// w.append("[\"").append(als[0]).append("\",\"").append(als[1]).append("\",\"").append(als[2]).append("\"]");
									if (itAlleles.hasNext())
										buf.append(",\n");
									w.write(buf.toString());
									// w.append(buf);
								}
							} else {
								/*
								 * int possize=
								 * ((String[])((List)table.get("varmarkerAlleles")).iterator().next()).length;
								 * List listvaralleles = ((List)table.get("varmarkerAlleles")); int
								 * varsize=listvaralleles.size(); for(int ipos=0; ipos<possize; ipos++) {
								 * for(int ivar=0; ivar<varsize; ivar++) {
								 * ((String[])listvaralleles.get(ivar))[ipos] } }
								 */
							}

							w.flush();
						} else {
							((Collection) mapAllconts.get("allVars")).addAll((List) table.get("allVars"));
							((Map) mapAllconts.get("markerAlleles")).putAll((Map) table.get("markerAlleles"));
							((Collection) mapAllconts.get("varmarkerAlleles"))
									.addAll((List) table.get("varmarkerAlleles"));
						}
					}

					// if(nopaging) break;
					// }
				}

			} else if (mapCont2Pos != null) {
				if (countAlleles < 0)
					countAlleles = 0;
				AppContext.debug("mapCont2Pos=" + mapCont2Pos);

				Iterator<String> itCont = mapCont2Pos.keySet().iterator();
				while (itCont.hasNext()) {
					String cont = itCont.next();

					Iterator<Set> itlistPosset = mapCont2Pos.get(cont).iterator();
					while (itlistPosset.hasNext()) {

						Set colPos = itlistPosset.next();
						// long curcount=countVariantTable(setVarids, cont, null, null, true, false,
						// false, false, colPos, null, null, false, format,
						// expandHomozygotes, unknownString, sepPhased, sepUnphased, studyId, matrix);
						// countAlleles+=curcount;
						Map table = getVariantTable(setVarids, cont, null, null, true, false, false, false, colPos,
								null, null, false, format, expandHomozygotes, unknownString, sepPhased, sepUnphased,
								studyId, matrix);
						if (table == null)
							return Response.status(500).entity("table==null").build();

						if (!matrix) {
							((List) mapAllconts.get("data")).addAll((List) table.get("data"));
						} else {

							if (w != null) {
								Iterator itAlleles = ((List) table.get("varmarkerAlleles")).iterator();
								// if(countAlleles>curcount && itAlleles.hasNext()) w.append(",\n");
								if (!bfirstallele && itAlleles.hasNext())
									w.append(",\n");
								bfirstallele = false;
								while (itAlleles.hasNext()) {
									// new String[]{ snpstr.getVar().toString(), marker, allele}
									String[] als = (String[]) itAlleles.next();
									StringBuffer buf = new StringBuffer();
									// buf.append("[\"").append(als[0]).append("\",\"").append(als[1]).append("\",\"").append(als[2]).append("\"]");
									buf.append("[\"").append(als[1]).append("\",\"").append(als[0]).append("\",\"")
											.append(als[2]).append("\"]");
									// AppContext.debug(buf.toString());

									if (itAlleles.hasNext())
										buf.append(",\n");
									w.write(buf.toString());
									// w.append(buf);
								}
								w.flush();
							} else {
								((Collection) mapAllconts.get("allVars")).addAll((Collection) table.get("allVars"));
								((Map) mapAllconts.get("markerAlleles")).putAll((Map) table.get("markerAlleles"));
								((Collection) mapAllconts.get("varmarkerAlleles"))
										.addAll((Collection) table.get("varmarkerAlleles"));
							}
						}

						// if(nopaging) break;

					}
				}
			}

			if (w != null)
				w.write("\n]}}");

			// if(actualSize!=null) actualSize=countAlleles;
			if (w != null)
				return null;

			if (!matrix) {
				return Response.status(200).entity(new ObjectMapper().writeValueAsString(mapAllconts)).build();
			} else {
				return createAlleleMatrixResponse((Set) mapAllconts.get("allVars"),
						(Map) mapAllconts.get("markerAlleles"), (List) mapAllconts.get("varmarkerAlleles"), format,
						call, studyId);
				// return new Object[]{Response.status(200).entity( new
				// ObjectMapper().writeValueAsString( table )).build(), table};
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}

		// return new Object[]{Response.status(500).entity("mapCont2Range==null and
		// mapCont2Pos==null").build(),null};

	}

	private long countVariantTable(Collection colVarIds, String sChr, Long lStart, Long lEnd, boolean bSNP,
			boolean bIndel, boolean bCoreonly, boolean bMismatchonly, Collection poslist, String sSubpopulation,
			String sLocus, boolean bAlignIndels, String format, boolean expandHomozygotes, String unknownString,
			String sepPhased, String sepUnphased, String studyId, boolean isMatrix) throws Exception {

		genotype = (GenotypeFacade) AppContext.checkBean(genotype, "GenotypeFacade");

		AppContext.resetTimer("allelequery..");

		boolean showAllRefsAllele = false;

		long varcount = -1;
		if (colVarIds == null) {
			if (studyId.startsWith("3k"))
				varcount = 3024;
			else if (studyId.startsWith("hdra"))
				varcount = 1568;
		} else if (colVarIds != null) {
			varcount = colVarIds.size();
			if (studyId.startsWith("3k") && colVarIds.size() == 3024) {
				colVarIds = null;
			} else if (studyId.startsWith("hdra") && colVarIds.size() == 1568) {
				colVarIds = null;
			}
		}

		GenotypeQueryParams params = new GenotypeQueryParams(colVarIds, sChr, lStart, lEnd, bSNP, bIndel,
				getSnpset(studyId), getDataset(studyId), getGenotypeRun(studyId), bMismatchonly, poslist,
				sSubpopulation, sLocus, bAlignIndels, showAllRefsAllele, null);

		// params.setbDownloadOnly(true);
		params = setStudy(params, studyId);
		long poscount = genotype.countSNPPoslist(params);
		if (poscount < 0 || varcount < 0) {
			AppContext.debug(params.toString());
			AppContext.debug("poscount<0 || varcount<0:" + poscount + "," + varcount);
			return -1;
		}

		AppContext.debug(params.toString());
		AppContext.debug("poscount=" + poscount + ", varcount=" + varcount);

		return poscount * varcount;
	}

	private Map getVariantTable(Collection colVarIds, String sChr, Long lStart, Long lEnd, boolean bSNP, boolean bIndel,
			boolean bCoreonly, boolean bMismatchonly, Collection poslist, String sSubpopulation, String sLocus,
			boolean bAlignIndels, String format, boolean expandHomozygotes, String unknownString, String sepPhased,
			String sepUnphased, String studyId, boolean isMatrix) throws Exception {

		genotype = (GenotypeFacade) AppContext.checkBean(genotype, "GenotypeFacade");

		AppContext.resetTimer("allelequery..");

		boolean showAllRefsAllele = false;

		if (colVarIds != null) {
			if (studyId.startsWith("3k") && colVarIds.size() == 3024)
				colVarIds = null;
			if (studyId.startsWith("hdra") && colVarIds.size() == 1568)
				colVarIds = null;
		}

		GenotypeQueryParams params = new GenotypeQueryParams(colVarIds, sChr, lStart, lEnd, bSNP, bIndel,
				getSnpset(studyId), getDataset(studyId), getGenotypeRun(studyId), bMismatchonly, poslist,
				sSubpopulation, sLocus, bAlignIndels, showAllRefsAllele, null);

		params.setbDownloadOnly(true);
		params = setStudy(params, studyId);

		if (!isMatrix) {
			// get position only
			List<SnpsAllvarsPos> listpos = genotype.getSNPPoslist(params);
			Iterator<SnpsAllvarsPos> itlistpos = listpos.iterator();
			List listMarkers = new ArrayList();
			while (itlistpos.hasNext()) {
				SnpsAllvarsPos pos = itlistpos.next();
				Map mapM = new LinkedHashMap();
				mapM.put(pos.getContig() + "_" + pos.getPosition(),
						pos.getRefnuc().trim() + sepUnphased + pos.getAltnuc().trim());
				listMarkers.add(mapM);
			}
			Map mapMP = new LinkedHashMap();
			mapMP.put("data", listMarkers);
			return mapMP;
		}

		AppContext.logQuery("BrAPI " + params.toString());
		VariantStringData queryRawResult = genotype.queryGenotype(params);
		// VariantStringData queryRawResult =
		// genotype.querydownloadGenotypeAsync(params)

		/*
		 * Call: /allelematrix?markerprofileDbId=100&...&format=tsv
		 * 
		 * Json response: { "metadata": { "pagination": { "pageSize": 0, "currentPage":
		 * 1, "totalCount": 0, "totalPages": 1 }, "status":[], "datafiles": [ {"url":
		 * "http://my-server/somethingelse.tsv"}, { "url": "..." } ] }, "result" : {
		 * "markerprofileDbIds": [], "data": [] } }
		 * 
		 * File: markerprofileDbIds markerprofileId1 markerprofileId2 ... markerId1 AB
		 * AA ... markerId2 AA AB ... ... ... ...
		 * 
		 * 
		 * Examples: - No format -> data in the json result - Format specified -> If
		 * server supports it, return the link to the exported data file - More than one
		 * format requested at a time -> Throw "not supported" error
		 */

		List listVars = new ArrayList();
		Iterator<SnpsStringAllvars> itSnpstr = queryRawResult.getListVariantsString().iterator();
		while (itSnpstr.hasNext()) {
			SnpsStringAllvars snpstr = itSnpstr.next();
			listVars.add(snpstr.getVar());
		}

		List listVarMarkerAlleles = new ArrayList();
		Map<String, List<String>> mapMarkerAlleles = new LinkedHashMap();

		Iterator<SnpsAllvarsPos> itSnppos = new TreeSet(queryRawResult.getListPos()).iterator();
		int snpposidx = 0;

		SnpsAllvarsPos prevpos = null;

		while (itSnppos.hasNext()) {
			SnpsAllvarsPos snppos = itSnppos.next();
			String marker = snppos.getContig() + "_" + snppos.getPosition();

			if (prevpos != null && (prevpos.getContig().compareTo(snppos.getContig()) > 0
					|| (prevpos.getContig().equals(snppos.getContig())
							&& prevpos.getPosition().longValue() >= snppos.getPosition().longValue()))) {
				throw new Exception("prevpos " + prevpos + " >= curpos " + snppos);
			}
			prevpos = snppos;
			// AppContext.debug(marker);

			List markerAlleles = mapMarkerAlleles.get(marker);
			if (markerAlleles == null) {
				// markerAlleles=new TreeSet();
				markerAlleles = new ArrayList();
				mapMarkerAlleles.put(marker, markerAlleles);
			}

			itSnpstr = queryRawResult.getListVariantsString().iterator();
			while (itSnpstr.hasNext()) {
				SnpsStringAllvars snpstr = itSnpstr.next();
				Character allele2 = null;
				if (snpstr.getMapPos2Allele2() != null)
					allele2 = snpstr.getMapPos2Allele2().get(snppos);
				Character allele1 = snpstr.getVarnuc().charAt(snpposidx);

				String allele = null;
				if (allele1 == '?' || allele1 == null) {
					allele = unknownString;
				} else {
					allele = String.valueOf(allele1);
					if (allele.isEmpty())
						allele = unknownString;
					else {
						// expandHomozygotes, String unknownString, String sepPhased, String sepUnphased
						if (expandHomozygotes) {
							if (allele2 != null) {
								allele += sepUnphased + allele2;
							} else {
								allele += sepUnphased + allele1;
							}
						} else {
							if (allele2 != null && !allele2.equals(allele1)) {
								allele += sepUnphased + allele2;
							}
						}
					}
				}

				// markerAlleles.add(allele.replace("?",unknownString));
				markerAlleles.add(allele);
				listVarMarkerAlleles.add(new String[] { snpstr.getVar().toString(), marker, allele });
			}
			snpposidx++;
		}

		Map mapResponse = new HashMap();
		mapResponse.put("allVars", listVars);
		mapResponse.put("markerAlleles", mapMarkerAlleles);
		mapResponse.put("varmarkerAlleles", listVarMarkerAlleles);

		return mapResponse;
		// return createAlleleMatrixResponse(listVars, format, mapData);

		// List listDataJson=new ArrayList();
		// if(format==null || format.isEmpty() || format.equals("json")) {
		//
		// Iterator<String> itMarker = mapData.keySet().iterator();
		// while(itMarker.hasNext()) {
		// String marker=itMarker.next();
		// Map mapDataJson=new HashMap();
		// mapDataJson.put(marker, mapData.get(marker));
		// listDataJson.add( mapDataJson );
		// }
		//
		// Map mapResult=new LinkedHashMap();
		// mapResult.put("makerprofileDbIds", listVars);
		// mapResult.put("data", listDataJson);
		//
		// Map mapMeta=new LinkedHashMap();
		// mapMeta.put("pagination", new ArrayList());
		// mapMeta.put("status", new ArrayList());
		//
		// Map response=new LinkedHashMap();
		// response.put("metadata", mapMeta);
		// response.put("result", mapResult);
		//
		// AppContext.resetTimer("allelematrix BrAPI format..");
		//
		// return response;
		//
		// } else {
		// String delimiter="\t";
		// if(format.equals("tsv")) {}
		// else if(format.equals("csv")) {
		// delimiter=",";
		// }
		//
		// /*
		// markerprofileDbIds markerprofileId1 markerprofileId2 ...
		// markerId1 AB AA ...
		// markerId2 AA AB ...
		// ... ... ...
		// */
		// String tempfile= "allelematrix_" +
		// AppContext.createTempFilename()+"."+format;
		// try {
		// BufferedWriter bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() +
		// tempfile));
		// bw.append("markerprofileDbIds").append(delimiter);
		// Iterator<BigDecimal> itVars = listVars.iterator();
		// while(itVars.hasNext()) {
		// bw.append( itVars.next().toString());
		// if(itVars.hasNext()) bw.append(delimiter);
		// }
		// bw.append("\n");
		//
		// Iterator<String> itMarker = mapData.keySet().iterator();
		// while(itMarker.hasNext()) {
		// String marker=itMarker.next();
		// bw.append(marker).append(delimiter);
		// List alleles = mapData.get(marker);
		// Iterator<String> itAl=alleles.iterator();
		// while(itAl.hasNext()) {
		// bw.append(itAl.next());
		// if(itAl.hasNext()) bw.append(delimiter);
		// }
		// if(itMarker.hasNext()) bw.append("\n");
		// }
		// bw.flush();
		// bw.close();
		//
		// AppContext.debug("filewritten to : " + AppContext.getTempDir() + tempfile);
		//
		// } catch(Exception ex) {
		// ex.printStackTrace();
		// }
		//
		// /*
		// * "metadata": {
		// "pagination": {
		// "pageSize": 0,
		// "currentPage": 1,
		// "totalCount": 0,
		// "totalPages": 1
		// },
		// "status":[],
		// "datafiles": [ {"url": "http://my-server/somethingelse.tsv"}, { "url": "..."
		// } ]
		// },
		// "result" : {
		// "markerprofileDbIds": [],
		// "data": []
		// }
		// */
		// Map mapResult=new LinkedHashMap();
		// mapResult.put("makerprofileDbIds", new ArrayList());
		// mapResult.put("data", new ArrayList());
		//
		// Map mapMeta=new LinkedHashMap();
		// Map mapPagi=new LinkedHashMap();
		// mapPagi.put("pageSize", 0);
		// mapPagi.put("currentPage", 0);
		// mapPagi.put("totalCount", 0);
		// mapPagi.put("totalPages", 1);
		//
		// mapMeta.put("pagination", mapPagi);
		// mapMeta.put("status", new ArrayList());
		// List listUrl=new ArrayList();
		// Map mapUrl=new HashMap();
		// mapUrl.put("url", AppContext.getHostname() + "/" + AppContext.getTempFolder()
		// + tempfile);
		// listUrl.add(mapUrl);
		// mapMeta.put("datafiles", listUrl);
		//
		// Map response=new LinkedHashMap();
		// response.put("metadata", mapMeta);
		// response.put("result", mapResult);
		//
		// AppContext.resetTimer("allelematrix BrAPI format..");
		//
		// return response;
		// }
		/*
		 * "result" : { "makerprofileDbIds":
		 * ["markerprofileId1","markerprofileId2","markerprofileId3"], "data": [ {
		 * "markerId1":["AB","AA","AA"] }, { "markerId2":["AA","AB","AA"] }, {
		 * "markerId3":["AB","AB","BB"] } ] }mapMeta
		 */

	}

	// private static Map createMetaData(long size, long current, long count, long
	// pages, Object status, Object datafiles) {
	private static Map createMetaData(long size, long current, long count, Object status, Object datafiles) {
		Map map = new LinkedHashMap();
		Map mapPagi = new LinkedHashMap();
		mapPagi.put("pageSize", size);
		mapPagi.put("currentPage", current);
		mapPagi.put("totalCount", count);
		long pages = count / size;
		if (count % size > 0)
			pages++;
		mapPagi.put("totalPages", pages);
		map.put("pagination", mapPagi);
		map.put("status", status);
		map.put("datafiles", datafiles);
		return map;

	}
	/*
	 * 
	 * @Path("/numbers") public class NumbersResource {
	 * 
	 * @GET public Response streamExample(){ StreamingOutput stream = new
	 * StreamingOutput() {
	 * 
	 * @Override public void write(OutputStream out) throws IOException,
	 * WebApplicationException { Writer writer = new BufferedWriter(new
	 * OutputStreamWriter(out)); for (int i = 0; i < 10000000 ; i++){ writer.write(i
	 * + " "); } writer.flush(); } }; return Response.ok(stream).build(); }
	 * 
	 * }
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN)
	 * 
	 * @Path("/{nodeId}/{depth}") public Response hello(@PathParam("nodeId") long
	 * nodeId, @PathParam("depth") int depth) { Node node =
	 * database.getNodeById(nodeId);
	 * 
	 * final Traverser paths = Traversal.description() .depthFirst()
	 * .relationships(DynamicRelationshipType.withName("whatever")) .evaluator(
	 * Evaluators.toDepth(depth) ) .traverse(node);
	 * 
	 * StreamingOutput stream = new StreamingOutput() {
	 * 
	 * @Override public void write(OutputStream os) throws IOException,
	 * WebApplicationException { Writer writer = new BufferedWriter(new
	 * OutputStreamWriter(os));
	 * 
	 * for (org.neo4j.graphdb.Path path : paths) { writer.write(path.toString() +
	 * "\n"); } writer.flush(); } };
	 * 
	 * return Response.ok(stream).build(); }
	 */
	/*
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public Response streamExample() {
	 * StreamingOutput stream = new StreamingOutput() {
	 * 
	 * @Override public void write(OutputStream os) throws IOException,
	 * WebApplicationException { Writer writer = new BufferedWriter(new
	 * OutputStreamWriter(os)); writer.write("test"); writer.flush(); } }; return
	 * Response.ok(stream).build(); }
	 */

	private Response createAlleleMatrixResponse(Set listVars, Map markerAlleles, List varmarkerAlleles, String format,
			String call, String study) {
		// createAlleleMatrixResponse(mapAllconts.get("listVars"),
		// mapAllconts.get("markerAlleles") , mapAllconts.get("varmarkerAlleles" ),
		// format);

		Map<String, Collection<String>> mapData = (Map<String, Collection<String>>) markerAlleles;
		Map response = new LinkedHashMap();

		if (format == null || format.isEmpty() || format.equals("json")) {
			List listDataJson = new ArrayList();
			if (call.equals("allelematrix") || call.equals("allelematrix-search")) {

				Iterator<String> itMarker = mapData.keySet().iterator();
				while (itMarker.hasNext()) {
					String marker = itMarker.next();
					Map mapDataJson = new HashMap();
					mapDataJson.put(marker, mapData.get(marker));
					listDataJson.add(mapDataJson);
				}

				Map mapResult = new LinkedHashMap();
				// mapResult.put("makerprofileDbIds", listVars);
				// mapResult.put("germplasmDbIds", listVars);
				// mapResult.put("markerAlleles", listDataJson);
				mapResult.put("data", varmarkerAlleles);

				response.put("metadata", this.createMetaData(varmarkerAlleles.size(), 0, varmarkerAlleles.size(),
						new HashMap(), new ArrayList()));
				response.put("result", mapResult);

				AppContext.resetTimer("allelematrix BrAPI format..");
			} else if (call.equals("markerprofile")) {
				Iterator<String> itMarker = mapData.keySet().iterator();
				while (itMarker.hasNext()) {
					String marker = itMarker.next();
					Map mapDataJson = new HashMap();
					mapDataJson.put(marker, mapData.get(marker).iterator().next());
					listDataJson.add(mapDataJson);
				}

				listitemsDAO = (ListItemsDAO) AppContext.checkBean(listitemsDAO, "ListItems");

				Long lVarid = Long.valueOf(listVars.iterator().next().toString());
				Map mapResult = new LinkedHashMap();
				String markerprofileDbId = lVarid.toString();
				if (study.equals("3kcore"))
					markerprofileDbId = Long.toString(lVarid + 10000);
				else if (study.equals("3kbase"))
					markerprofileDbId = Long.toString(lVarid + 20000);
				else if (study.equals("3kall"))
					markerprofileDbId = Long.toString(lVarid + 30000);

				mapResult.put("markerprofileDbIds", markerprofileDbId);
				mapResult.put("germplasmDbIds", lVarid.toString());
				mapResult.put("uniqueDisplayName", listitemsDAO.getMapId2Variety(VarietyFacade.DATASET_SNP_ALL)
						.get(BigDecimal.valueOf(lVarid)).getName() + ";" + study);
				mapResult.put("analysisMethod", "SNP calling");
				mapResult.put("data", listDataJson);

				response.put("metadata",
						createMetaData(listDataJson.size(), 0, listDataJson.size(), new HashMap(), new ArrayList()));
				response.put("result", mapResult);
			}

		} else {

			response = createTsvAlleleMatrix(format, study, call, listVars, (Map<String, Collection<String>>) mapData,
					null, true);
			// String delimiter="\t";
			// if(format.equals("tsv")) {}
			// else if(format.equals("csv")) {
			// delimiter=",";
			// }
			//
			// /*
			// markerprofileDbIds markerprofileId1 markerprofileId2 ...
			// markerId1 AB AA ...
			// markerId2 AA AB ...
			// ... ... ...
			// */
			//
			// String tempfile= call + "_" + AppContext.createTempFilename()+"."+format;
			//
			// long idoffset=0;
			// if(study.equals("3kcore")) idoffset= 10000;
			// else if(study.equals("3kbase")) idoffset= 20000;
			// else if(study.equals("3kall")) idoffset= 30000;
			//
			//
			// try {
			// if(call.equals("allelematrix") || call.equals("allelematrix-search")) {
			// BufferedWriter bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() +
			// tempfile));
			// bw.append("markerprofileDbIds").append(delimiter);
			// Iterator<BigDecimal> itVars = listVars.iterator();
			// while(itVars.hasNext()) {
			// bw.append( Long.valueOf(itVars.next().longValue() + idoffset).toString());
			// if(itVars.hasNext()) bw.append(delimiter);
			// }
			// bw.append("\n");
			//
			// Iterator<String> itMarker = mapData.keySet().iterator();
			// while(itMarker.hasNext()) {
			//
			// String marker=itMarker.next();
			// bw.append(marker).append(delimiter);
			// Collection alleles = mapData.get(marker);
			//
			// Iterator<String> itAl=alleles.iterator();
			// while(itAl.hasNext()) {
			// bw.append(itAl.next());
			// if(itAl.hasNext()) bw.append(delimiter);
			// }
			//
			// /*
			// List alleles = mapData.get(marker);
			// Iterator<String> itAl=alleles.iterator();
			// while(itAl.hasNext()) {
			// bw.append(itAl.next());
			// if(itAl.hasNext()) bw.append(delimiter);
			// }
			// */
			// if(itMarker.hasNext()) bw.append("\n");
			// }
			// bw.flush();
			// bw.close();
			// } else if(call.equals("markerprofile")) {
			//
			//
			// }
			//
			//
			// AppContext.debug("filewritten to : " + AppContext.getTempDir() + tempfile);
			//
			// } catch(Exception ex) {
			// ex.printStackTrace();
			// }
			//
			// /*
			// * "metadata": {
			// "pagination": {
			// "pageSize": 0,
			// "currentPage": 1,
			// "totalCount": 0,
			// "totalPages": 1
			// },
			// "status":[],
			// "datafiles": [ {"url": "http://my-server/somethingelse.tsv"}, { "url": "..."
			// } ]
			// },
			// "result" : {
			// "markerprofileDbIds": [],
			// "data": []
			// }
			// */
			// Map mapResult=new LinkedHashMap();
			// mapResult.put("makerprofileDbIds", new ArrayList());
			// mapResult.put("data", new ArrayList());
			//
			// Map mapMeta=new LinkedHashMap();
			// Map mapPagi=new LinkedHashMap();
			// mapPagi.put("pageSize", 0);
			// mapPagi.put("currentPage", 0);
			// mapPagi.put("totalCount", 0);
			// mapPagi.put("totalPages", 1);
			//
			// mapMeta.put("pagination", mapPagi);
			// mapMeta.put("status", new ArrayList());
			// List listUrl=new ArrayList();
			// Map mapUrl=new HashMap();
			// mapUrl.put("url", AppContext.getHostname() + "/" + AppContext.getTempFolder()
			// + tempfile);
			// listUrl.add(mapUrl);
			// mapMeta.put("datafiles", listUrl);
			//
			//
			// response.put("metadata", mapMeta);
			// response.put("result", mapResult);
			//
			// AppContext.resetTimer("allelematrix BrAPI format..");

		}

		String errormsg = "";
		try {
			// return Response.status(200).entity( new ObjectMapper().writeValueAsString(
			// createResponse(response) )).build();
			String sreturn = new ObjectMapper().writeValueAsString(response);
			if (AppContext.isLocalhost())
				AppContext.debug(sreturn);
			return Response.status(200).entity(sreturn).build();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errormsg = e.getMessage();
		}

		return Response.status(500).entity(errormsg).build();
	}

	private Map createTsvAlleleMatrix(String format, String study, String call, Set listVars,
			Map<String, Collection<String>> mapData) {
		return createTsvAlleleMatrix(format, study, call, listVars, mapData, null, true);
	}

	private Map createTsvAlleleMatrix(String format, String study, String call, Set listVars,
			Map<String, Collection<String>> mapData, Writer w, boolean isFirst) {
		String delimiter = "\t";
		if (format.equals("tsv")) {
		} else if (format.equals("csv")) {
			delimiter = ",";
		}

		/*
		 * markerprofileDbIds markerprofileId1 markerprofileId2 ... markerId1 AB AA ...
		 * markerId2 AA AB ... ... ... ...
		 */

		String tempfile = null;
		if (w == null)
			tempfile = call + "_" + AppContext.createTempFilename() + "." + format;

		long idoffset = 0;
		if (study.equals("3kcore"))
			idoffset = 10000;
		else if (study.equals("3kbase"))
			idoffset = 20000;
		else if (study.equals("3kall"))
			idoffset = 30000;

		try {
			if (call.equals("allelematrix") || call.equals("allelematrix-search")) {
				Writer bw = null;
				if (w == null)
					bw = new BufferedWriter(new FileWriter(AppContext.getTempDir() + tempfile));
				else
					bw = w;
				// BufferedWriter bw=new BufferedWriter(new FileWriter(AppContext.getTempDir() +
				// tempfile));
				if (isFirst) {
					bw.append("markerprofileDbIds").append(delimiter);
					Iterator<BigDecimal> itVars = listVars.iterator();
					while (itVars.hasNext()) {
						bw.append(Long.valueOf(itVars.next().longValue() + idoffset).toString());
						if (itVars.hasNext())
							bw.append(delimiter);
					}
					bw.append("\n");
				} else
					bw.append("\n");

				Iterator<String> itMarker = mapData.keySet().iterator();
				while (itMarker.hasNext()) {

					String marker = itMarker.next();
					bw.append(marker).append(delimiter);
					Collection alleles = mapData.get(marker);

					Iterator<String> itAl = alleles.iterator();
					while (itAl.hasNext()) {
						bw.append(itAl.next());
						if (itAl.hasNext())
							bw.append(delimiter);
					}

					/*
					 * List alleles = mapData.get(marker); Iterator<String> itAl=alleles.iterator();
					 * while(itAl.hasNext()) { bw.append(itAl.next()); if(itAl.hasNext())
					 * bw.append(delimiter); }
					 */
					if (itMarker.hasNext())
						bw.append("\n");
				}
				bw.flush();
				if (w == null)
					bw.close();
				AppContext.debug("filewritten to : " + AppContext.getTempDir() + tempfile);
			} else if (call.equals("markerprofile")) {

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/*
		 * "metadata": { "pagination": { "pageSize": 0, "currentPage": 1, "totalCount":
		 * 0, "totalPages": 1 }, "status":[], "datafiles": [ {"url":
		 * "http://my-server/somethingelse.tsv"}, { "url": "..." } ] }, "result" : {
		 * "markerprofileDbIds": [], "data": [] }
		 */
		Map mapResult = new LinkedHashMap();
		mapResult.put("makerprofileDbIds", new ArrayList());
		mapResult.put("data", new ArrayList());

		Map mapMeta = new LinkedHashMap();
		Map mapPagi = new LinkedHashMap();
		mapPagi.put("pageSize", 0);
		mapPagi.put("currentPage", 0);
		mapPagi.put("totalCount", 0);
		mapPagi.put("totalPages", 1);

		mapMeta.put("pagination", mapPagi);
		mapMeta.put("status", new ArrayList());
		List listUrl = new ArrayList();
		Map mapUrl = new HashMap();
		mapUrl.put("url", AppContext.getHostname() + "/" + AppContext.getTempFolder() + tempfile);
		listUrl.add(mapUrl);
		mapMeta.put("datafiles", listUrl);

		Map response = new LinkedHashMap();
		response.put("metadata", mapMeta);
		response.put("result", mapResult);

		AppContext.resetTimer("allelematrix BrAPI format..");
		return response;
	}

}
