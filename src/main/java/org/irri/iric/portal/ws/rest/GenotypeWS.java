package org.irri.iric.portal.ws.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.irri.iric.ds.chado.dao.VGenotypeRunDAO;
import org.irri.iric.ds.chado.dao.access.OrganismDAO;
import org.irri.iric.ds.chado.domain.model.Organism;
import org.irri.iric.ds.chado.domain.model.VGenotypeRun;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jettison.json.JSONException;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.VariantTableArray;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
//import org.irri.iric.portal.genotype.zkui.SNPAllvarsRowRenderer;
//import org.irri.iric.portal.genotype.zkui.SNPListItemRenderer;
//import org.irri.iric.portal.genotype.zkui.SNPMatrixRenderer;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.ws.entity.VariantTableWS;
//import org.irri.iric.portal.ws.entity.VariantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("GenotypeWebService")
@Path("/genotype")
public class GenotypeWS {

	@Autowired
	@Qualifier("GenotypeFacade")
	private GenotypeFacade genotype;
	
	@Autowired
	private WorkspaceFacade workspace;

	@Autowired
	@Qualifier("GenotypeRunPlatformDAO")
	private VGenotypeRunDAO genotyperundao;

	@Autowired
	private VarietyFacade variety;

	private String dataset = VarietyFacade.DATASET_SNPINDELV2_IUPAC;

	private Map<String, String> mapVarReplace = new HashMap();

	private OrganismDAO organismDAO;

	public GenotypeWS() {
		super();
		// TODO Auto-generated constructor stub
		genotype = (GenotypeFacade) AppContext.checkBean(genotype, "GenotypeFacade");
		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
		workspace = (WorkspaceFacade) AppContext.checkBean(workspace, "WorkspaceFacade");
		organismDAO = (OrganismDAO) AppContext.checkBean(organismDAO, "OrganismDAO");
		
		AppContext.debug("GenotypeWS started");

		// rename some variable names
		mapVarReplace.put("iricStockId", "varietyId");
		mapVarReplace.put("irisUniqueId", "irisId");
		mapVarReplace.put("oriCountry", "country");

	}

	/*
	 * private String getDataset() { return dataset; }
	 */

	private Set getDataset() {
		Set s = new HashSet();
		s.add(dataset);
		return s;
	}

	@GET
	@Path("/variety")
	@Produces("application/json")
	public Response getVarieties() throws JSONException {

		try {
			// Set vars = variety.getGermplasm(getDataset()) ;

			List vars = variety.getVarietyNames(getDataset());

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity(AppContext.replaceString(mapper.writeValueAsString(vars), mapVarReplace))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@Path("/variety/{id}")
	@GET
	@Produces("application/json")
	public Response getVarietiesById(@PathParam("id") long lId) throws JSONException {

		try {
			return Response.status(200).entity(AppContext.replaceString(new ObjectMapper()
					.writeValueAsString(variety.getMapId2Variety(dataset).get(BigDecimal.valueOf(lId))), mapVarReplace))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@Path("/variety/subpopulation/{subpop}")
	@GET
	@Produces("application/json")
	public Response getVarietiesBySubpopulation(@PathParam("subpop") String sSubpop) throws JSONException {

		try {
			return Response.status(200).entity(AppContext.replaceString(
					new ObjectMapper().writeValueAsString(variety.getGermplasmBySubpopulation(sSubpop, getDataset())),
					mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@Path("/variety/subpopulation")
	@GET
	@Produces("application/json")
	public Response getVarietiesSubpopulation() throws JSONException {

		try {
			return Response.status(200)
					.entity(new ObjectMapper().writeValueAsString(variety.getSubpopulations(getDataset()))).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@Path("/variety/country/{country}")
	@GET
	@Produces("application/json")
	public Response getVarietiesByCountry(@PathParam("country") String sCountry) throws JSONException {

		try {
			Set s = new HashSet();
			s.add(dataset);
			return Response.status(200)
					.entity(AppContext.replaceString(
							new ObjectMapper().writeValueAsString(variety.getGermplasmByCountry(sCountry, s)),
							mapVarReplace))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@Path("/variety/country")
	@GET
	@Produces("application/json")
	public Response getVarietiesCountry() throws JSONException {

		try {
			return Response.status(200).entity(new ObjectMapper().writeValueAsString(variety.getCountries(dataset)))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@Path("/variety/name")
	@GET
	@Produces("application/json")
	public Response getVarietiesNames() throws JSONException {

		try {
			return Response.status(200)
					.entity(new ObjectMapper().writeValueAsString(variety.getVarietyNames(getDataset()))).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@Path("/variety/namelike/{name}")
	@GET
	@Produces("application/json")
	public Response getVarietiesNameLike(@PathParam("name") String sName) throws JSONException {

		try {
			return Response.status(200).entity(
					new ObjectMapper().writeValueAsString(variety.getGermplasmByNameLike(sName + "%", getDataset())))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@GET
	@Path("/snp/list")
	@Produces("application/json")
	@ResponseBody
	public Response getGeneLists() throws JSONException {

		try {
			List listnames = new ArrayList();
			listnames.addAll(workspace.getSnpPositionListNames());
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(listnames), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@GET
	@Path("/gene/list/{name}")
	@Produces("application/json")
	@ResponseBody
	public Response getGeneInLists(@PathParam("name") String listname) throws JSONException {

		try {
			List listloc = new ArrayList();
			listloc.addAll(workspace.getSnpPositions("any", listname));
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(listloc), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@Path("/gettable")
	@GET
	@Produces("application/json")
	public Response getVariantByVarietyId(@DefaultValue("9") @QueryParam("organismId") Integer organismId, @DefaultValue("all") @QueryParam("varid") String sVarids,
			@QueryParam("chr") String sChr, @QueryParam("start") Long lStart, @QueryParam("end") Long lEnd,
			@DefaultValue("true") @QueryParam("snp") boolean bSNP,
			@DefaultValue("false") @QueryParam("indel") boolean bIndel,
			@DefaultValue("false") @QueryParam("coreonly") boolean bCoreonly,
			@DefaultValue("false") @QueryParam("mismatchonly") boolean bMismatchonly,
			@QueryParam("poslist") String sSnppos, @QueryParam("subpopulation") String sSubpopulation,
			@QueryParam("locus") String sLocus, @DefaultValue("false") @QueryParam("alignindels") boolean bAlignIndels)
			throws JSONException {

		// if(sChr==null) throw new JSONException("parameters chr is required");
		if (sChr == null && sLocus == null && sSnppos == null)
			throw new JSONException("parameters (chr,start,end) OR locus OR (chr AND poslist) is required");

		if ((sSnppos == null || sSnppos.isEmpty()) && (lStart == null || lEnd == null) && sLocus == null)
			throw new JSONException("parameters (start AND end) OR poslist OR locus is required");
		if (sSnppos != null && !sSnppos.isEmpty() && lStart != null && lEnd != null)
			throw new JSONException("only one of either (start AND end) OR poslist is required");

		// if(!sVarids.equals("all") && sSubpopulation==null) throw new
		// JSONException("parameter varid OR subpopulation is required");
		if (!sVarids.equals("all") && sSubpopulation != null)
			throw new JSONException("only one of either varid OR subpopulation is required");

		Collection<BigDecimal> colVarIds = null;
		if (!sVarids.equals("all")) {
			String vaidss[] = sVarids.split(",");
			colVarIds = new HashSet();
			for (int ivar = 0; ivar < vaidss.length; ivar++) {
				colVarIds.add(BigDecimal.valueOf(Long.valueOf(vaidss[ivar])));
			}
		}

		Collection<BigDecimal> colPos = null;
		if (sSnppos != null) {
			String pos[] = sSnppos.split(",");
			colPos = new HashSet();
			for (int ipos = 0; ipos < pos.length; ipos++) {
				colPos.add(BigDecimal.valueOf(Long.valueOf(pos[ipos])));
			}
		}

		AppContext.debug("params=" + colVarIds + " " + sChr + " " + lStart + " " + lEnd + " " + bSNP + " " + bIndel
				+ " " + bCoreonly + " " + bMismatchonly + " " + colPos + " " + sSubpopulation + " " + sLocus);

		try {
			VariantTable table = getVariantTable(colVarIds, sChr, lStart, lEnd, bSNP, bIndel, bCoreonly, bMismatchonly,
					colPos, sSubpopulation, sLocus, bAlignIndels, organismDAO.getOrganismByID(organismId));
			if (table == null)
				throw new JSONException("VariantTable is null");

			return Response.status(200).entity(new ObjectMapper().writeValueAsString(table)).build();

		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@Path("/posttable")
	@POST
	@Produces("application/json")
	public Response postVariantByVarietyId(@FormParam("organismId") Integer organismId, @FormParam("varid") List<Long> lVarids, @FormParam("chr") String sChr,
			@FormParam("start") Long lStart, @FormParam("end") Long lEnd,
			@DefaultValue("true") @FormParam("snp") boolean bSNP,
			@DefaultValue("false") @FormParam("indel") boolean bIndel,
			@DefaultValue("false") @FormParam("coreonly") boolean bCoreonly,
			@DefaultValue("false") @FormParam("mismatchonly") boolean bMismatchonly,
			@FormParam("poslist") List<Long> lSnppos, @FormParam("subpopulation") String sSubpopulation,
			@FormParam("locus") String sLocus, @DefaultValue("false") @FormParam("alignindel") boolean bAlignIndels)
			throws JSONException {

		if (sChr == null && sLocus == null && lSnppos.isEmpty())
			throw new JSONException("parameters (chr,start,end) OR locus OR (chr AND poslist) is required");

		if (lSnppos.isEmpty() && (lStart == null || lEnd == null) && sLocus == null)
			throw new JSONException("parameters (start AND end) OR poslist is required");
		if (!lSnppos.isEmpty() && lStart != null && lEnd != null)
			throw new JSONException("only one of either (start AND end) OR poslist is required");

		// if(lVarids.isEmpty() && sSubpopulation==null) throw new
		// JSONException("parameter varid OR subpopulation is required");
		if (!lVarids.isEmpty() && sSubpopulation != null)
			throw new JSONException("only one of either varid OR subpopulation is required");

		Collection<BigDecimal> colVarIds = null;
		if (lVarids != null && !lVarids.isEmpty()) {
			Iterator<Long> itvarids = lVarids.iterator();
			while (itvarids.hasNext()) {
				colVarIds.add(BigDecimal.valueOf(itvarids.next()));
			}
		}

		Organism organism = organismDAO.getOrganismByID(organismId);
		
		Collection<BigDecimal> colPos = null;
		if (lSnppos != null && !lSnppos.isEmpty()) {
			Iterator<Long> itpos = lSnppos.iterator();
			while (itpos.hasNext()) {
				colVarIds.add(BigDecimal.valueOf(itpos.next()));
			}
		}

		AppContext.debug("params=" + colVarIds + " " + sChr + " " + lStart + " " + lEnd + " " + bSNP + " " + bIndel
				+ " " + bCoreonly + " " + bMismatchonly + " " + colPos + " " + sSubpopulation + " " + sLocus);

		try {
			VariantTable table = getVariantTable(colVarIds, sChr, lStart, lEnd, bSNP, bIndel, bCoreonly, bMismatchonly,
					colPos, sSubpopulation, sLocus, bAlignIndels, organism);

			if (table == null)
				throw new JSONException("VariantTable is null");

			return Response.status(200).entity(new ObjectMapper().writeValueAsString(table)).build();

		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	private VariantTable getVariantTable(Collection colVarIds, String sChr, Long lStart, Long lEnd, boolean bSNP,
			boolean bIndel, boolean bCoreonly, boolean bMismatchonly, Collection poslist, String sSubpopulation,
			String sLocus, boolean bAlignIndels, Organism organism) throws Exception {

		boolean showAllRefsAllele = false;
		// GenotypeQueryParams params = new GenotypeQueryParams(colVarIds,sChr, lStart,
		// lEnd, bSNP, bIndel,
		// (bCoreonly?GenotypeQueryParams.SNP_CORE:GenotypeQueryParams.SNP_FILTERED) ,
		// bMismatchonly, poslist, sSubpopulation, sLocus, bAlignIndels,
		// showAllRefsAllele );

		Set sVS = new HashSet();
		if (bCoreonly)
			sVS.add("3kcore");
		else
			sVS.add("3kfiltered");
		Set sVar = new HashSet();
		sVar.add("3k");
		Set sRun = new HashSet();
//		sRun.add("3kfiltered");

		genotyperundao = (VGenotypeRunDAO) AppContext.checkBean(genotyperundao, "GenotypeRunPlatformDAO");
		VGenotypeRun vrun = genotyperundao.findVGenotypeRunByGenotypeRunId(2);
		sRun.add(vrun);

		
		
		GenotypeQueryParams params = new GenotypeQueryParams(colVarIds, sChr, lStart, lEnd, bSNP, bIndel, sVS, sVar,
				sRun, bMismatchonly, poslist, sSubpopulation, sLocus, bAlignIndels, showAllRefsAllele, organism);

		params.setDataset(dataset);
		VariantTableArray varianttable = new VariantAlignmentTableArraysImpl();

		AppContext.logQuery("WS " + params.toString());

		VariantStringData queryRawResult = genotype.queryGenotype(params);
		varianttable = new VariantTableWS(
				(VariantTableArray) genotype.fillGenotypeTable(varianttable, queryRawResult, params));

		return varianttable;

	}

	/*
	 * @POST
	 * 
	 * @Path("/sendemail")
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public Response sendEmail(@FormParam("email")
	 * String email) { AppContext.debug(email); return Response.ok("email=" +
	 * email).build(); }
	 */

	// The resource look like this
	/*
	 * @Path("/variants")
	 * 
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public void setJsonl(String array){
	 * JSONArray o = new JSONArray(last_data); AppContext.debug(o.toString()); }
	 */

}