package com.app.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.app.pojo.User;
import com.app.service.IWebServiceRestful;


public class WebServiceRestful implements IWebServiceRestful {

	@Context
    private UriInfo uriInfo;
    
    @Context
    private Request request;
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	@Path(value = "/sample")
    public String doGet() {
        return "this is get rest request";
    }
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/request/{param}")
    public String doRequest(@PathParam("param") String param, 
            @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) {
        System.out.println(servletRequest);
        System.out.println(servletResponse);
        System.out.println(param);
        System.out.println(servletRequest.getParameter("param"));
        System.out.println(servletRequest.getContentType());
        System.out.println(servletResponse.getCharacterEncoding());
        System.out.println(servletResponse.getContentType());
        return "success";
    }

	@Override
	@POST
	@Path("/postData")
	public User postData() throws IOException {
		System.out.println("postData");
		return null;
	}

	@Override
	@PUT
	@Path("/putData/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public User putData(int id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DELETE
	@Path("/removeData/{id}")
	public void deleteData(int id) {
		// TODO Auto-generated method stub
		
	}
    
	

}
