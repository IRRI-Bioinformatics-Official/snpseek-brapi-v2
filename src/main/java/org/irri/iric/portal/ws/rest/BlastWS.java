package org.irri.iric.portal.ws.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jettison.json.JSONException;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.LocalAlignmentDAO;
import org.irri.iric.portal.genomics.LocalAlignmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("BlastWebService")
@Path("/blast")
public class BlastWS {

	@Autowired
	@Qualifier("LocalAlignmentDAO")
	private LocalAlignmentDAO localblastdao;

	private List alignWithDB(LocalAlignmentQuery query) throws Exception {

		localblastdao = (LocalAlignmentDAO) AppContext.checkBean(localblastdao, "LocalAlignmentDAO");
		// setup arguments
		return localblastdao.alignWithDB(query);
	}

	@Path("/postblast")
	@POST
	@Produces("application/json")
	public Response postBlast(@FormParam("sequence") String sequence, @FormParam("program") String program,
			@FormParam("db") String dbname, @DefaultValue("10") @FormParam("maxe") Double maxevalue)
			throws JSONException {

		if (sequence == null || program == null || dbname == null)
			throw new JSONException("parameters sequence AND program AND db are required");

		try {
			LocalAlignmentQuery query = new LocalAlignmentQuery(sequence, dbname, program);
			query.setEvalue(maxevalue);

			AppContext.logQuery("WS " + query.toString());

			List listResult = alignWithDB(query);
			return Response.status(200).entity(new ObjectMapper().writeValueAsString(listResult)).build();

		} catch (JSONException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new JSONException(ex);
		}

	}

	@Path("/getblast")
	@GET
	@Produces("application/json")
	public Response getBlast(@QueryParam("sequence") String sequence, @QueryParam("program") String program,
			@QueryParam("db") String dbname, @DefaultValue("10") @QueryParam("maxe") Double maxevalue)
			throws JSONException {

		if (sequence == null || program == null || dbname == null)
			throw new JSONException("parameters sequence AND program AND db are required");

		try {
			LocalAlignmentQuery query = new LocalAlignmentQuery(sequence, dbname, program);
			query.setEvalue(maxevalue);

			AppContext.logQuery("WS " + query.toString());

			List listResult = alignWithDB(query);
			return Response.status(200).entity(new ObjectMapper().writeValueAsString(listResult)).build();

		} catch (JSONException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new JSONException(ex);
		}

	}

}
