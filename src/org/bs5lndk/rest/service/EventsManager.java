package org.bs5lndk.rest.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bs5lndk.persistent.beans.EventsBean;
import org.bs5lndk.persistent.service.DBEventsManager;
import org.codehaus.jackson.map.ObjectMapper;
 
@Path("/manage")
public class EventsManager {
 
	@GET
    @Path("/{resource}")
    public Response getEvents(@PathParam("resource") String resource,
            @DefaultValue("") @QueryParam("param") String param) {
    	String output = "";
		final OutputStream out = new ByteArrayOutputStream();
	    final ObjectMapper mapper = new ObjectMapper();
	    try{
	    	mapper.writeValue(out, DBEventsManager.getInstance().listEvents());
	    	output = out.toString();
	    }catch(IOException ioe){
	    	System.out.println("Exception Occured: " + ioe.getMessage().toString());
	    	ioe.printStackTrace();
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    }
        return Response.status(200).entity(output).build();
    }
	
	
    @GET
    @Path("/{resource}/{index}")
    public Response getEvents(@PathParam("resource") String resource, @PathParam("index") String index,
            @DefaultValue("") @QueryParam("param") String param) {
    	String output = "";
		final OutputStream out = new ByteArrayOutputStream();
	    final ObjectMapper mapper = new ObjectMapper();
	    try{
	    	mapper.writeValue(out, DBEventsManager.getInstance().listEvent(index));
	    	output = out.toString();
	    }catch(IOException ioe){
	    	System.out.println("Exception Occured: " + ioe.getMessage().toString());
	    	ioe.printStackTrace();
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    }
        return Response.status(200).entity(output).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{resource}/{data}")
	public Response updateEvent(String inputData, @PathParam("data") String data) {
		String output = "";
		String index = data;
		if(index.length() > 0){
			ObjectMapper mapper = new ObjectMapper();
			try{
		    	EventsBean hmvc = mapper.readValue(inputData, EventsBean.class);
		    	output = DBEventsManager.getInstance().updateEvent(hmvc, index);
		    }catch(IOException ioe){
		    	System.out.println("Exception Occured: " + ioe.getMessage().toString());
		    	ioe.printStackTrace();
		    }catch(Exception e){
		    	System.out.println("Exception Occured: " + e.getMessage().toString());
		    	e.printStackTrace();
		    }
		}
		return Response.status(200).entity(output).build();
	}
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{resource}")
	public Response addNewEvent(String inputData) {
		String output = "";
		if(inputData.length() > 0){
			ObjectMapper mapper = new ObjectMapper();
			try{
		    	EventsBean hmvc = mapper.readValue(inputData, EventsBean.class);
		    	output = DBEventsManager.getInstance().makeNewEvent(hmvc);
		    }catch(IOException ioe){
		    	System.out.println("Exception Occured: " + ioe.getMessage().toString());
		    	ioe.printStackTrace();
		    }catch(Exception e){
		    	System.out.println("Exception Occured: " + e.getMessage().toString());
		    	e.printStackTrace();
		    }
		}
		return Response.status(200).entity(output).build();
	}
    
    @DELETE
    //@Consumes(MediaType.APPLICATION_JSON)
    @Path("/{resource}")
	public Response deleteEvent() {
		String output = "";
		try{
	    	output = DBEventsManager.getInstance().deleteEvents();
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    }
		return Response.status(200).entity(output).build();
	}
    
    @DELETE
    @Path("/{resource}/{index}")
	public Response deleteEvent(@PathParam("index") String index) {
    	System.out.println("index: " + index);
		String output = "";
		try{
	    	System.out.println("index 2: " + index);
	    	output = DBEventsManager.getInstance().deleteEvent(index);
	    	System.out.println("index 3: " + index);
	    }catch(Exception e){
	    	System.out.println("Exception Occured: " + e.getMessage().toString());
	    	e.printStackTrace();
	    }
		return Response.status(200).entity(output).build();
	}
 }
