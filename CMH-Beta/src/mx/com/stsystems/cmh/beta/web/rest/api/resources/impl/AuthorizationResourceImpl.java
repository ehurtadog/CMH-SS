package mx.com.stsystems.cmh.beta.web.rest.api.resources.impl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/Auth")
public class AuthorizationResourceImpl {
	private final static Logger LOGGER = LoggerFactory.getLogger(AuthorizationResourceImpl.class);
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public HttpServletResponse getLogin(@HeaderParam("XSS-User") String XSSUser, 
			@Context SecurityContext sc, @Context final HttpServletResponse response) {
		boolean isAuthenticated = false;
		
		if (!sc.isUserInRole("admin"))
			LOGGER.error("User not in rol admin");
		
		if (XSSUser != null) {
			LOGGER.debug("Authenticated User.");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			response.addHeader("XSS-Token", "UNTOKENZito");
			isAuthenticated = true;
		} else {
			LOGGER.debug("Unauthenticated User.");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		try {
	        response.flushBuffer();
	    }catch(Exception e){LOGGER.error("Error: " + e.getMessage());}
		
		if (isAuthenticated) {
			return response;
		} else
			return response;
	}
}
