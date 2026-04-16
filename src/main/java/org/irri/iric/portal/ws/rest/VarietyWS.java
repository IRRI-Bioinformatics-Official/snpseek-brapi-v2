package org.irri.iric.portal.ws.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.irri.iric.ds.chado.domain.Phenotype;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jettison.json.JSONException;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.variety.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("VarietyWebService")
@Path("/variety")
public class VarietyWS {

	@Autowired
	private VarietyFacade variety;
	private Map mapVarReplace;
	private String dataset = VarietyFacade.DATASET_SNPINDELV2_IUPAC;

	public VarietyWS() {
		super();
		
		variety = (VarietyFacade) AppContext.checkBean(variety, "VarietyFacade");
		mapVarReplace = new HashMap();

		AppContext.debug("VarietyWS started");

		// rename some variables
		mapVarReplace.put("iricStockId", "varietyId");
		mapVarReplace.put("irisUniqueId", "irisId");
		mapVarReplace.put("oriCountry", "country");
	}

	@GET
	@Path("/")
	@Produces("application/json")

	@ResponseBody
	public Response getVarieties() throws JSONException {

		try {
			Set vars = variety.getGermplasm(dataset);

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity(AppContext.replaceString(mapper.writeValueAsString(vars), mapVarReplace))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@Path("/{id}")
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

	@Path("/subpopulation/{subpop}")
	@GET
	@Produces("application/json")
	public Response getVarietiesBySubpopulation(@PathParam("subpop") String sSubpop) throws JSONException {

		try {
			return Response.status(200)
					.entity(AppContext.replaceString(new ObjectMapper()
							.writeValueAsString(variety.getGermplasmBySubpopulation(sSubpop, dataset)), mapVarReplace))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@Path("/subpopulation")
	@GET
	@Produces("application/json")
	public Response getVarietiesSubpopulation() throws JSONException {

		try {
			return Response.status(200)
					.entity(new ObjectMapper().writeValueAsString(variety.getSubpopulations(dataset))).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@Path("/country/{country}")
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

	@Path("/country")
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

	@Path("/name")
	@GET
	@Produces("application/json")
	public Response getVarietiesNames() throws JSONException {

		try {
			Set s = new HashSet();
			s.add(dataset);
			return Response.status(200).entity(new ObjectMapper().writeValueAsString(variety.getVarietyNames(s)))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@Path("/namelike/{name}")
	@GET
	@Produces("application/json")
	public Response getVarietiesNameLike(@PathParam("name") String sName) throws JSONException {

		try {
			Set s = new HashSet();
			s.add(dataset);

			return Response.status(200)
					.entity(new ObjectMapper().writeValueAsString(variety.getGermplasmByNameLike(sName + "%", s)))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}

	}

	@GET
	@Path("/phenotypes")
	@Produces("application/json")
	public Response getPhenotypes() throws JSONException {

		try {
			Map pnenotypeId = variety.getPhenotypeDefinitions(dataset);

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(pnenotypeId), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/COterms/trait")
	@Produces("application/json")
	public Response getCoTerms() throws JSONException {

		try {
			Set s = new HashSet();
			s.add(dataset);

			Map<String, BigDecimal> coMap = variety.getTraits(s, false);

			Map<String, String> finalList = new HashMap<>();
			for (Map.Entry<String, BigDecimal> entry : coMap.entrySet()) {
				String[] phenotype = entry.getKey().split("::");
				finalList.put(phenotype[0], phenotype[1]);
			}

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(finalList), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/phenotypes/{phenid}")
	@Produces("application/json")
	public Response getPhenotypes4AllVarieties(@PathParam("phenid") String sPhenId) throws JSONException {

		try {
			List vars = variety.getVarietyByPhenotype(sPhenId, dataset);

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity(AppContext.replaceString(mapper.writeValueAsString(vars), mapVarReplace))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/COterms/trait/{coTerm}")
	@Produces("application/json")
	public Response getCOterms4AllVarieties(@PathParam("coTerm") String coTerm) throws JSONException {

		try {
			Set s = new HashSet();
			s.add(dataset);

			BigDecimal sPhenId = variety.getPhenotypeId(coTerm, dataset);
			List vars = variety.getVarietyByPhenotype(sPhenId.toString(), dataset);

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200).entity(AppContext.replaceString(mapper.writeValueAsString(vars), mapVarReplace))
					.build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/all/phenotypes/{phenid}")
	@Produces("application/json")
	public Response getVarietyPhenotype(@PathParam("phenid") String sPhenId) throws JSONException {

		try {
			List pnenotypes = variety.getPhenotypesByGermplasm(sPhenId, dataset);
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(pnenotypes), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/all/COterms/trait/{coTerm}")
	@Produces("application/json")
	public Response getVarietyCOterm(@PathParam("coTerm") String coTerm) throws JSONException {

		List pnenotypes;

		try {
			BigDecimal sPhenId = variety.getPhenotypeId(coTerm, dataset);

			if (sPhenId != null && !sPhenId.toString().equals(""))
				pnenotypes = variety.getPhenotypesByGermplasm(sPhenId.toString(), dataset);
			else
				pnenotypes = new ArrayList<>();

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(pnenotypes), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/{varid}/phenotypes/{phenid}")
	@Produces("application/json")
	public Response getVarietyPhenotypes(@PathParam("varid") String sVarId, @PathParam("phenid") String sPhenId)
			throws JSONException {

		try {
			Phenotype pnenotypes = variety.getPhenotypesByGermplasm(
					variety.getMapId2Variety(dataset).get(BigDecimal.valueOf(Long.valueOf(sVarId))), dataset, sPhenId);
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(pnenotypes), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/{varid}/COterms/trait/{coTerm}")
	@Produces("application/json")
	public Response getVarietyCOterms(@PathParam("varid") String sVarId, @PathParam("coTerm") String coTerm)
			throws JSONException {

		try {

			Phenotype pnenotypes;

			BigDecimal phenId = variety.getPhenotypeId(coTerm, dataset);

			if (phenId != null && !phenId.toString().equals(""))
				pnenotypes = variety.getPhenotypesByGermplasm(
						variety.getMapId2Variety(dataset).get(BigDecimal.valueOf(Long.valueOf(sVarId))), dataset,
						phenId.toString());
			else
				pnenotypes = null;

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(pnenotypes), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/{varid}/phenotypes")
	@Produces("application/json")
	public Response getVarietyPhenotypes(@PathParam("varid") String sVarId) throws JSONException {

		try {
			Set s = new HashSet();
			s.add(dataset);

			List pnenotypes = variety.getPhenotypesByGermplasm(
					variety.getMapId2Variety(dataset).get(BigDecimal.valueOf(Long.valueOf(sVarId))), s);

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(pnenotypes), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/{varid}/COterms")
	@Produces("application/json")
	public Response getVarietyCOterms(@PathParam("varid") String sVarId) throws JSONException {

		try {
			Set s = new HashSet();
			s.add(dataset);

			List pnenotypes = variety.getPhenotypesByGermplasm(
					variety.getMapId2Variety(dataset).get(BigDecimal.valueOf(Long.valueOf(sVarId))), s);

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(pnenotypes), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/passports")
	@Produces("application/json")
	public Response getPassports() throws JSONException {

		try {
			Map passportId = variety.getPassportDefinitions(dataset);
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(passportId), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	@GET
	@Path("/{varid}/passports")
	@Produces("application/json")
	public Response getVarietyPassports(@PathParam("varid") String sVarId) throws JSONException {

		try {
			// List passports = variety.getPhenotypesByGermplasm(
			// variety.getMapId2Variety().get( BigDecimal.valueOf(Long.valueOf(sVarId)) ) );
			List passports = new ArrayList();
			Set s = new HashSet();
			s.add(dataset);

			passports.addAll(variety.getPassportByVarietyid(BigDecimal.valueOf(Long.valueOf(sVarId))));

			ObjectMapper mapper = new ObjectMapper();
			return Response.status(200)
					.entity(AppContext.replaceString(mapper.writeValueAsString(passports), mapVarReplace)).build();
		} catch (Exception ex) {
			throw new JSONException(ex);
		}
	}

	// @GET
	// @Path("/passports/{passid}")
	// @Produces("application/json")
	// public Response getPassport4AllVarieties(@PathParam("passid") String sPassId)
	// throws JSONException {
	//
	// try {
	// List vars = variety.getVarietyByPassport( sPassId);
	//
	// ObjectMapper mapper = new ObjectMapper();
	// return Response.status(200).entity(
	// AppContext.replaceString(mapper.writeValueAsString(vars), mapVarReplace
	// )).build();
	// } catch(Exception ex)
	// {
	// throw new JSONException(ex);
	// }
	// }

}
