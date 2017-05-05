package mx.com.stsystems.CMH.Beta.web.rest.api.resources.impl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/Auth")
public class AuthorizationResource {
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public HttpServletResponse getLogin(@HeaderParam("XSS-User") String XSSUser, 
			@Context SecurityContext sc, @Context final HttpServletResponse response) {
		boolean isAuthenticated = false;
		
		if (!sc.isUserInRole("admin"))
			System.err.println("User not in rol admin");
		
		if (XSSUser != null) {
			System.out.println("Authenticated User.");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			response.addHeader("XSS-Token", "UNTOKENZito");
			isAuthenticated = true;
		} else {
			System.out.println("Unauthenticated User.");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		try {
	        response.flushBuffer();
	    }catch(Exception e){System.err.println("Error: " + e.getMessage());}
		
		if (isAuthenticated) {
			return response;
		} else
			return response;
	}
}
