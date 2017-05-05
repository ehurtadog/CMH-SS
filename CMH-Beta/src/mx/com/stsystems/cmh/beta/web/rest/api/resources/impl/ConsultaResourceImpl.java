package mx.com.stsystems.cmh.beta.web.rest.api.resources.impl;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.stsystems.cmh.beta.dto.Antecedente;
import mx.com.stsystems.cmh.beta.dto.CodigoPostal;
import mx.com.stsystems.cmh.beta.dto.Hospital;
import mx.com.stsystems.cmh.beta.dto.Paciente;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeCodigPostal;
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeEstado;
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeRegistroPaciente;
import mx.com.stsystems.cmh.beta.web.controller.service.ServiceController;
import mx.com.stsystems.cmh.beta.web.controller.service.impl.ServiceControllerImpl;

@Path("/Consulta")
public class ConsultaResourceImpl {
	private final static Logger LOGGER = LoggerFactory.getLogger(ConsultaResourceImpl.class);

	@POST
	@Path("/HospitalesPorEstado")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postHospitalesPorEstado(String estado) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		ServiceController serviceController = new ServiceControllerImpl();
		
		try {
			LOGGER.debug("VAR: mensaje: " + estado);

			MensajeEstado mensajeEstado = mapper.readValue(estado, MensajeEstado.class);
			LOGGER.debug("VAR: mensajeEstado: " + mensajeEstado);
			
			List<Hospital> hospitales = serviceController.solicitaHopitalesPorEstado(mensajeEstado.getEstado());
			
			jsonInString = mapper.writeValueAsString(hospitales);
			LOGGER.debug("VAR: hospitales: " + jsonInString);
		} catch (IOException e) {
			LOGGER.error("Error de conversion de JSON");
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
			LOGGER.debug("VAR: codigoPostal: " + codigoPostal);
			
			MensajeCodigPostal mensajeCodigoPostal = mapper.readValue(codigoPostal, MensajeCodigPostal.class);
			LOGGER.debug("VAR: mensajeCodigoPostal: " + mensajeCodigoPostal);
			
			List<CodigoPostal> codigosPostales = serviceController.solicitaAsentamientosPorCodigoPostal(mensajeCodigoPostal.getCodigoPostal());
			
			jsonInString = mapper.writeValueAsString(codigosPostales);
		} catch (IOException ioe) {
			LOGGER.error("Error de conversion de JSON");
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
			LOGGER.debug("VAR: idFiliacion: " + idFiliacion);

			paciente = serviceController.solicitaPacientePorIdFiliacion(idFiliacion);
			antecedente = serviceController.solicitaAntecedentePorIdPaciente(paciente.getIdPaciente());
		} catch (SumarSaludException sse) {
			LOGGER.error("Error en la consulta del paciente.");
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
			
			LOGGER.debug("VAR: jsonInString: " + jsonInString);
		} catch (IOException ioe) {
			LOGGER.error("Error de conversion de JSON");
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
			LOGGER.debug("VAR: correoElectronico: " + correoElectronico);

			paciente = serviceController.solicitaPacientePorCorreoElectronico(correoElectronico);
			antecedente = serviceController.solicitaAntecedentePorIdPaciente(paciente.getIdPaciente());
		} catch (SumarSaludException sse) {
			LOGGER.error("Error en la consulta del paciente.");
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
			
			LOGGER.debug("VAR: jsonInString: " + jsonInString);
		} catch (IOException ioe) {
			LOGGER.error("Error de conversion de JSON");
		}
		
		return jsonInString;
	}	
	

	

}
