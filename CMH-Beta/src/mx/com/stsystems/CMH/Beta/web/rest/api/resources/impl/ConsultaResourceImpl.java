package mx.com.stsystems.CMH.Beta.web.rest.api.resources.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mx.com.stsystems.CMH.Beta.dto.Telefono;
import mx.com.stsystems.CMH.Beta.dto.Track;
import mx.com.stsystems.CMH.Beta.web.controller.service.impl.ServiceController;

@Path("/Music")
public class ConsultaResourceImpl {
	private Map<Integer, Track> trackMap = new HashMap<Integer, Track>();
	private Track track = new Track();
	private static ServiceController serviceController;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		serviceController = (ServiceController) context.getBean("DAO");
	}
	
	@POST
	@Path("/Genre")
	@Produces(MediaType.APPLICATION_JSON)
	public String postGenre() {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;

		Telefono telefono = serviceController.solicitaTelefono("1");
		
		try {
			jsonInString = mapper.writeValueAsString(telefono);
			System.out.println("VAR: telefono: " + jsonInString);
		} catch (IOException e) {
			System.err.println("Error de conversion de JSON");
		}
		
		return jsonInString;
	}
	
	@GET
	@Path("/byId/{song}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrackInJSON(@PathParam("song") Integer song, @HeaderParam("XNebula-User") String XNebulaUser, 
			@Context SecurityContext sc, @Context final HttpServletResponse response) {
		boolean isAuthenticated = false;
		
		if (!sc.isUserInRole("admin"))
			System.err.println("User not in rol admin");
		
		if (XNebulaUser != null) {
			System.out.println("Authenticated User.");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			isAuthenticated = true;
		} else {
			System.out.println("Unauthenticated User.");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		
		populate(); 
   
		try {
	        response.flushBuffer();
	    }catch(Exception e){System.err.println("Error: " + e.getMessage());}
		
		if (isAuthenticated) {
			if (trackMap.get(song) == null) {
				System.out.println("Title not found.");
				
				return Response.ok().status(400).entity(trackMap.get(99)).build();
//				return trackMap.get(99);
			} else {
				return Response.ok().status(200).entity(trackMap.get(song)).build();
//				return trackMap.get(song);
			}
		} else
			return Response.ok().status(403).entity(trackMap.get(403)).build();
//			return trackMap.get(403);
	}
	
	private void populate() {
		track.setId(1);
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");
		trackMap.put(track.getId(), track);
		track = new Track();
		track.setId(2);
		track.setTitle("Thrill is Gone");
		track.setSinger("BB King");
		trackMap.put(track.getId(), track);
		track = new Track();
		track.setId(3);
		track.setTitle("Love and Affection");
		track.setSinger("Def Leppard");
		trackMap.put(track.getId(), track);
		track = new Track();
		track.setId(4);
		track.setTitle("Tea for One");
		track.setSinger("Led Zeppelin");
		trackMap.put(track.getId(), track);
		track = new Track();
		track.setId(4);
		track.setTitle("Tea for One");
		track.setSinger("Led Zeppelin");
		trackMap.put(track.getId(), track);
		track = new Track();
		track.setId(99);
		track.setTitle("Not Found");
		track.setSinger("Not Found");
		trackMap.put(track.getId(), track);
		track = new Track();
		track.setId(403);
		track.setTitle("Forbbiden");
		track.setSinger("Forbbiden");
		trackMap.put(track.getId(), track);
	}
	
}