package org.bs5lndk.rest.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
 
@Path("/events")
public class EventsManager {
 
    @GET
    @Path("/{parameter}")
    public Response responseMsg( @PathParam("pathParam") String pathParam,
            @DefaultValue("") @QueryParam("queryParam") String queryParam) {
 
        String output = "Events List: <Fetch from RDBMS>	-	TBD";
 
        return Response.status(200).entity(output).build();
    }
}

