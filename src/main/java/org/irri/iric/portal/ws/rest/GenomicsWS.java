package org.irri.iric.portal.ws.rest; 


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.irri.iric.ds.chado.domain.MergedLoci;
import org.irri.iric.ds.utils.TextSearchOptions;
//import org.codehaus.jackson.map.ObjectMapper;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.ws.entity.LocusRegionWS;
//import org.irri.iric.portal.ws.rest.GenotypeWS.RequestLocus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("GenomicsWebService")
@Path("/genomics")
public class GenomicsWS {
	
	@Autowired
	private GenomicsFacade genomics;
	@Autowired
	private WorkspaceFacade workspace;
	private Map mapVarReplace;
	private String dataset=VarietyFacade.DATASET_SNPINDELV2_IUPAC;

	
	
	public GenomicsWS() {
		super();
		
		genomics = (GenomicsFacade)AppContext.checkBean(genomics, "GenomicsFacade");
		workspace = (WorkspaceFacade)AppContext.checkBean(workspace, "WorkspaceFacade");
		mapVarReplace = new HashMap();
		
		
		AppContext.debug("GenomicsWS started");

		// rename some variables
		//mapVarReplace.put("iricStockId", "varietyId");
		//mapVarReplace.put("irisUniqueId", "irisId");
		//mapVarReplace.put("oriCountry", "country");
		/*
		mapVarReplace.put("\"rappredName\":null,", "");
		mapVarReplace.put("\"rappredName\":null", "");
		mapVarReplace.put("\"organismId\":9,","");
		mapVarReplace.put("\"contigId\":3,","");
		mapVarReplace.put("\"contigId\":4,","");
		mapVarReplace.put("\"contigId\":5,","");
		mapVarReplace.put("\"contigId\":6,","");
		mapVarReplace.put("\"contigId\":7,","");
		mapVarReplace.put("\"contigId\":8,","");
		mapVarReplace.put("\"contigId\":9,","");
		mapVarReplace.put("\"contigId\":10,","");
		mapVarReplace.put("\"contigId\":11,","");
		mapVarReplace.put("\"contigId\":12,","");
		mapVarReplace.put("\"contigId\":13,","");
		mapVarReplace.put("\"contigId\":14,","");
		*/
	}

	
	
	  @GET
	  @Path("/gene/osnippo/{contig}")
	  @Produces("application/json")
	  @ResponseBody
	  public Response getGeneByRegion(@PathParam("contig") String contig, @QueryParam("start") long start,  @QueryParam("end") long end, 
			  @DefaultValue("msu7only") @QueryParam("model") String model ) throws JSONException {
		  
		  try {
			  
			  List listloc=new ArrayList();
			  Iterator itLoci =  genomics.getLociByRegion(contig, start, end, AppContext.getDefaultOrganism(), model).iterator();
			  while(itLoci.hasNext()) {
				  listloc.add( new LocusRegionWS((MergedLoci)itLoci.next()));
			  }
			  
		
			  ObjectMapper mapper = new ObjectMapper();
			  return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(listloc), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
		
	  }
	  
	  @GET
	  @Path("/gene/osnippo")
	  @Produces("application/json")
	  @ResponseBody
	  public Response getGeneByRegions(@QueryParam("region") String regions, 
			  @DefaultValue("msu7only") @QueryParam("model") String model ) throws JSONException {
		  
		  AppContext.debug("getGeneByRegions:" + regions);
		  
		  try {
			  List listloc=new ArrayList();
			  String region[]=regions.split(",");
			  for(int i=0;i<region.length; i++) {
				  String reg[]=region[i].split("\\-");
				  Iterator itLoci =  genomics.getLociByRegion(reg[0], Long.valueOf(reg[1]) ,Long.valueOf(reg[2]), AppContext.getDefaultOrganism(), model).iterator();
				  while(itLoci.hasNext()) {
					  listloc.add( new LocusRegionWS((MergedLoci)itLoci.next()));
				  }
			  }
			  AppContext.debug(listloc.size() + " genes found");
			  ObjectMapper mapper = new ObjectMapper();
			  return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(listloc), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
		
	  }
	  
	  @GET
	  @Path("/gene/list")
	  @Produces("application/json")
	  @ResponseBody
	  public Response getGeneLists() throws JSONException {
		  
		  try {
			  List listnames=new ArrayList();
			  listnames.addAll(workspace.getLocuslistNames());
			  ObjectMapper mapper = new ObjectMapper();
			  return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(listnames), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
		  
	  }
	  
	  @GET
	  @Path("/gene/list/{name}")
	  @Produces("application/json")
	  @ResponseBody
	  public Response getGeneInLists(@PathParam("name") String listname) throws JSONException {
		  
		  try {
			  List listloc=new ArrayList();
			  listloc.addAll(workspace.getLoci(listname));
			  ObjectMapper mapper = new ObjectMapper();
			  return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(listloc), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
		  
	  }
	  
	  public static class RequestLocus {
			String chromosome;
			long start;
			long end;
			public String getChromosome() {
				return chromosome;
			}
			public void setChromosome(String chromosome) {
				this.chromosome = chromosome;
			}
			public long getStart() {
				return start;
			}
			public void setStart(long start) {
				this.start = start;
			}
			public long getEnd() {
				return end;
			}
			public void setEnd(long end) {
				this.end = end;
			}
			
			
		}
		
		public static class RequestLocusList {
			List<RequestLocus> listLocus;

			public List<RequestLocus> getListLocus() {
				return listLocus;
			}

			public void setListLocus(List<RequestLocus> listLocus) {
				this.listLocus = listLocus;
			}
			
		}
/*		
		  @Path("/allelematrix-search")
		  @POST
		  //@Consumes({"multipart/form-data", "application/json","application/x-www-form-urlencoded"})
		  //@Consumes("application/x-www-form-urlencoded")
		  @Consumes("application/json")
		  @Produces({"application/json", "text/plain"})
*/
		  
	  @GET
	  @Path("/gene/osnippo/search/{matchtype}/{keyword}")
	  @Produces("application/json")
	  
	  @ResponseBody
	  public Response getGeneByRegion(@PathParam("keyword") String keyword, @PathParam("matchtype") String matchtype,
			  @DefaultValue("msu7") @QueryParam("model") String model ) throws JSONException {
		  
		  try {
			  List listloc=new ArrayList();
			  TextSearchOptions t=null;
			  if(matchtype.toLowerCase().equals("exact")) t=new TextSearchOptions(keyword, false, false,true);
			  else if(matchtype.toLowerCase().equals("regex")) t=new TextSearchOptions(keyword, true, false,false);
			  else if(matchtype.toLowerCase().equals("word")) t=new TextSearchOptions(keyword, false, true,false);
			  else throw new JSONException("Invalid match type " + matchtype);
			  
			  Iterator itLoci =  genomics.getLociByDescription(t,AppContext.getDefaultOrganism(), model).iterator();
			  
			  while(itLoci.hasNext()) {
				  listloc.add( new LocusRegionWS((MergedLoci)itLoci.next()));
			  }
			  ObjectMapper mapper = new ObjectMapper();
			  return Response.status(200).entity( AppContext.replaceString(mapper.writeValueAsString(listloc), mapVarReplace )).build();
		  } catch(Exception ex)
		  {
			  throw new JSONException(ex);
		  }
	  }
	  
}