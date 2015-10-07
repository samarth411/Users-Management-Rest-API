package com.java.users.rest;

/**
 * Service class for rest API's
 */
 
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
 

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import com.java.users.database.DatabaseService;
import com.sun.jersey.api.core.HttpContext;
//import com.sun.jersey.core.util.Base64;

 

@Path("/users")

public class UsersManagementService {

	@Context HttpContext context;
 
	
	public static String decode(String auth) {
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(auth);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usernameAndPassword;
    }
	
	/**
	 * Create a new user
	 * @param user
	 * @return
	 */
	@POST
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {

		JsonMessage message = null;
		Response response = null;
				
		if (DatabaseService.validateDuplicateUser(user) == true)
		{
			 message = new JsonMessage("User with user name " + user.getUserName() + " already exists");
			 response = Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		}
		else
		{
			DatabaseService.insertUser(user);
			DatabaseService.insertUserInfo(user);
			message = new JsonMessage("User with user name " + user.getUserName() + " created ");
			response = Response.status(Response.Status.CREATED).entity(message).build();
		}
		
		return response;
		
	}
	
	
	/**
	 * Get the existing users
	 * @param user
	 * @return
	 */
	@GET
	@Produces("application/json")
	public Response getUsers() {
		
		String auth = context.getRequest().getHeaderValue(HttpHeaders.AUTHORIZATION);
		
		JsonMessage message = null;
		Response response = null;
		
		if (auth == null)
		{
			 message = new JsonMessage("Invalid or empty user credentials");
			 response = Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
			 return response;
		}
		auth = auth.replaceFirst("Basic" + " ", "");
		
		String userNamePassword = decode(auth);
	
		StringTokenizer tokenizer = new StringTokenizer( userNamePassword, ":");
		String username = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
				
		if (DatabaseService.validateRootUser(username, password) == false)
		{
			 message = new JsonMessage("Invalid user credentials");
			 response = Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
		}
		else
		{
			List<String> usersList = DatabaseService.getUsers();
			
			UsersList users = new UsersList(usersList);
			
			response = Response.status(Response.Status.OK).entity(users).build();
		}
		
		return response;
		
	}
	

	/**
	 * Update user info
	 * @param user
	 * @return
	 */
	@PUT
	@Path("information")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateuserInfo(UserBasicInfo userInfo) {

		
		String auth = context.getRequest().getHeaderValue(HttpHeaders.AUTHORIZATION);
		auth = auth.replaceFirst("Basic" + " ", "");
		
		String userNamePassword = decode(auth);
		
		
		StringTokenizer tokenizer = new StringTokenizer( userNamePassword, ":");
		String userName = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		JsonMessage message = null;
		Response response = null;
				
		if (DatabaseService.authenticateUser(new User(userName, password)) == false)
		{
			 message = new JsonMessage("Invalid user credentials");
			 response = Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
		}
		else
		{
			DatabaseService.insertUserBasicInfo(userInfo, userName);
			message = new JsonMessage("Updated information for the user ");
			response = Response.status(Response.Status.OK).entity(message).build();
		}
		
		return response;
		
	}
	
	
	/**
	 * Get userInfo
	 * @param user
	 * @return
	 */
	@GET
	@Path("information")
	@Produces("application/json")
	public Response getUserInfo() {

		
		String auth = context.getRequest().getHeaderValue(HttpHeaders.AUTHORIZATION);
		auth = auth.replaceFirst("Basic" + " ", "");
		
		String userNamePassword = decode(auth);
		
		
		StringTokenizer tokenizer = new StringTokenizer( userNamePassword, ":");
		String userName = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		JsonMessage message = null;
		Response response = null;
				
		if (DatabaseService.authenticateUser(new User(userName, password)) == false)
		{
			 message = new JsonMessage("Invalid user credentials");
			 response = Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
		}
		else
		{
			UserBasicInfoLinks userInfo = DatabaseService.getUserInfo(userName, null);
			
			response = Response.status(Response.Status.OK).entity(userInfo).build();
		}
		
		return response;
		
	}
	
	
	/**
	 * Get userInfo for a given elementid
	 * @param user
	 * @return
	 */
	@GET
	@Path("information/{elementid}")
	@Produces("application/json")
	public Response getUserInfoElement(@PathParam("elementid") String elementId) {

		
		String auth = context.getRequest().getHeaderValue(HttpHeaders.AUTHORIZATION);
		auth = auth.replaceFirst("Basic" + " ", "");
		
		String userNamePassword = decode(auth);
		
		
		StringTokenizer tokenizer = new StringTokenizer( userNamePassword, ":");
		String userName = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		JsonMessage message = null;
		Response response = null;
				
		if (DatabaseService.authenticateUser(new User(userName, password)) == false)
		{
			 message = new JsonMessage("Invalid user credentials");
			 response = Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
		}
		else
		{
			UserBasicInfoLinks userInfo = DatabaseService.getUserInfo(userName, elementId);
			
			response = Response.status(Response.Status.OK).entity(userInfo).build();
		}
		
		return response;
		
	}
	
	
	/**
	 * Delete userInfo element
	 * @param user
	 * @return
	 */
	@DELETE
	@Path("information/{elementid}")
	@Produces("application/json")
	public Response deleteUserInfoElement(@PathParam("elementid") String elementId) {

		String auth = context.getRequest().getHeaderValue(HttpHeaders.AUTHORIZATION);
		auth = auth.replaceFirst("Basic" + " ", "");
		
		String userNamePassword = decode(auth);
		
		StringTokenizer tokenizer = new StringTokenizer( userNamePassword, ":");
		String userName = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		JsonMessage message = null;
		Response response = null;
				
		if (DatabaseService.authenticateUser(new User(userName, password)) == false)
		{
			 message = new JsonMessage("Invalid user credentials");
			 response = Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
		}
		else
		{
			DatabaseService.deleteUserInfo(userName, elementId);
			message = new JsonMessage("Information field value of "+ elementId + " deleted successfully. ");
			
			response = Response.status(Response.Status.OK).entity(message).build();
		}
		
		return response;
		
    }
	
	
	
	/**
	 * Update userInfo element
	 * @param user
	 * @return
	 */
	@PUT
	@Path("information/{elementid}")
	@Produces("application/json")
	public Response updateUserInfoElement(UserBasicInfo userInfo, @PathParam("elementid") String elementId) {

		String auth = context.getRequest().getHeaderValue(HttpHeaders.AUTHORIZATION);
		auth = auth.replaceFirst("Basic" + " ", "");
		
		String userNamePassword = decode(auth);
		
		StringTokenizer tokenizer = new StringTokenizer( userNamePassword, ":");
		String userName = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		JsonMessage message = null;
		Response response = null;
				
		if (DatabaseService.authenticateUser(new User(userName, password)) == false)
		{
			 message = new JsonMessage("Invalid user credentials");
			 response = Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
		}
		else
		{
			DatabaseService.updateUserInfo(userName, userInfo, elementId);
			message = new JsonMessage("Information field value of "+ elementId + " updated successfully. ");
			
			response = Response.status(Response.Status.OK).entity(message).build();
		}
		
		return response;
		
	}
	
	
	

	
}
