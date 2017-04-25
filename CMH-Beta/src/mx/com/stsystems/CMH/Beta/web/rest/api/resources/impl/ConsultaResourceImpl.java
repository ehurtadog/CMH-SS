package mx.com.stsystems.CMH.Beta.web.rest.api.resources.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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

import mx.com.stsystems.CMH.Beta.dto.CodigoPostal;
import mx.com.stsystems.CMH.Beta.dto.Hospital;
import mx.com.stsystems.CMH.Beta.dto.Paciente;
import mx.com.stsystems.CMH.Beta.dto.Track;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeCodigPostal;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeEstado;
import mx.com.stsystems.CMH.Beta.web.controller.service.ServiceController;
import mx.com.stsystems.CMH.Beta.web.controller.service.impl.ServiceControllerImpl;

@Path("/Consulta")
public class ConsultaResourceImpl {
	private Map<Integer, Track> trackMap = new HashMap<Integer, Track>();
	private Track track = new Track();
	
	
	@POST
	@Path("/HospitalesPorEstado")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postHospitalesPorEstado(String estado) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		ServiceController serviceController = new ServiceControllerImpl();
		
		try {
			System.out.println("VAR: mensaje: " + estado);

			MensajeEstado mensajeEstado = mapper.readValue(estado, MensajeEstado.class);
			System.out.println("VAR: mensajeEstado: " + mensajeEstado);
			
			List<Hospital> hospitales = serviceController.solicitaHopitalesPorEstado(mensajeEstado.getEstado());
			
			jsonInString = mapper.writeValueAsString(hospitales);
			System.out.println("VAR: hospitales: " + jsonInString);
		} catch (IOException e) {
			System.err.println("Error de conversion de JSON");
		}
		
		return jsonInString;
	}
	
	@POST
	@Path("/AsentamientosPorCodigoPostal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postAsentamientosPorCodigoPostal(String codigoPostal) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		ServiceController serviceController = new ServiceControllerImpl();
		
		try {
			System.out.println("VAR: codigoPostal: " + codigoPostal);
			
			MensajeCodigPostal mensajeCodigoPostal = mapper.readValue(codigoPostal, MensajeCodigPostal.class);
			System.out.println("VAR: mensajeCodigoPostal: " + mensajeCodigoPostal);
			
			List<CodigoPostal> codigosPostales = serviceController.solicitaAsentamientosPorCodigoPostal(mensajeCodigoPostal.getCodigoPostal());
			
			jsonInString = mapper.writeValueAsString(codigosPostales);
		} catch (IOException ioe) {
			System.err.println("Error de conversion de JSON");
		}
		
		return jsonInString;
	}
	
	@POST
	@Path("/Paciente/{idFiliacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public String postPacientePorIdFiliacion(@PathParam("idFiliacion") long idFiliacion) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		ServiceController serviceController = new ServiceControllerImpl();
		Paciente paciente = null;
		
		try {
			System.out.println("VAR: idFiliacion: " + idFiliacion);

			paciente = serviceController.solicitaPacientePorIdFiliacion(idFiliacion);
		} catch (SumarSaludException sse) {
			System.err.println("Error en la consulta del paciente.");
		}
		
		if (paciente == null) {
			paciente = new Paciente();
			paciente.setIdFiliacion(0);
			paciente.setNombres("PACIENTE INEXISTENTE");
			paciente.setApellidoPaterno("PACIENTE INEXISTENTE");
			paciente.setApellidoMaterno("PACIENTE INEXISTENTE");
		}
		
		try {
			jsonInString = mapper.writeValueAsString(paciente);
		} catch (IOException ioe) {
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
