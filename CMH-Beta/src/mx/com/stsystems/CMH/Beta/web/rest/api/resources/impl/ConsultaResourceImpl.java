package mx.com.stsystems.CMH.Beta.web.rest.api.resources.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import mx.com.stsystems.CMH.Beta.dto.Antecedente;
import mx.com.stsystems.CMH.Beta.dto.CodigoPostal;
import mx.com.stsystems.CMH.Beta.dto.Hospital;
import mx.com.stsystems.CMH.Beta.dto.Paciente;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeCodigPostal;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeEstado;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeRegistroPaciente;
import mx.com.stsystems.CMH.Beta.web.controller.service.ServiceController;
import mx.com.stsystems.CMH.Beta.web.controller.service.impl.ServiceControllerImpl;

@Path("/Consulta")
public class ConsultaResourceImpl {

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
	@Path("/Paciente/idFiliacion/{idFiliacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public String postPacientePorIdFiliacion(@PathParam("idFiliacion") long idFiliacion) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		ServiceController serviceController = new ServiceControllerImpl();
		Paciente paciente = null;
		Antecedente antecedente = null;
		
		mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		
		try {
			System.out.println("VAR: idFiliacion: " + idFiliacion);

			paciente = serviceController.solicitaPacientePorIdFiliacion(idFiliacion);
			antecedente = serviceController.solicitaAntecedentePorIdPaciente(paciente.getIdPaciente());
		} catch (SumarSaludException sse) {
			System.err.println("Error en la consulta del paciente.");
		}
		
		if (paciente == null) {
			paciente = new Paciente();
			paciente.setIdFiliacion(0);
			paciente.setNombres("PACIENTE INEXISTENTE");
			paciente.setApellidoPaterno("PACIENTE INEXISTENTE");
			paciente.setApellidoMaterno("PACIENTE INEXISTENTE");
			antecedente = new Antecedente();
		} 
		
		try {
			MensajeRegistroPaciente registroPaciente = new MensajeRegistroPaciente(paciente, antecedente);
			jsonInString = mapper.writeValueAsString(registroPaciente);
			
			System.out.println("VAR: jsonInString: " + jsonInString);
		} catch (IOException ioe) {
			System.err.println("Error de conversion de JSON");
		}
		
		return jsonInString;
	}
	
	@POST
	@Path("/Paciente/correoElectronico/{correoElectronico}")
	@Produces(MediaType.APPLICATION_JSON)
	public String postPacientePorCorreoElectronico(@PathParam("correoElectronico") String correoElectronico) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		ServiceController serviceController = new ServiceControllerImpl();
		Paciente paciente = null;
		Antecedente antecedente = null;
		
		mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		
		try {
			System.out.println("VAR: correoElectronico: " + correoElectronico);

			paciente = serviceController.solicitaPacientePorCorreoElectronico(correoElectronico);
			antecedente = serviceController.solicitaAntecedentePorIdPaciente(paciente.getIdPaciente());
		} catch (SumarSaludException sse) {
			System.err.println("Error en la consulta del paciente.");
		}
		
		if (paciente == null) {
			paciente = new Paciente();
			paciente.setIdFiliacion(0);
			paciente.setNombres("PACIENTE INEXISTENTE");
			paciente.setApellidoPaterno("PACIENTE INEXISTENTE");
			paciente.setApellidoMaterno("PACIENTE INEXISTENTE");
			antecedente = new Antecedente();
		} 
		
		try {
			MensajeRegistroPaciente registroPaciente = new MensajeRegistroPaciente(paciente, antecedente);
			jsonInString = mapper.writeValueAsString(registroPaciente);
			
			System.out.println("VAR: jsonInString: " + jsonInString);
		} catch (IOException ioe) {
			System.err.println("Error de conversion de JSON");
		}
		
		return jsonInString;
	}	
	

	

}
