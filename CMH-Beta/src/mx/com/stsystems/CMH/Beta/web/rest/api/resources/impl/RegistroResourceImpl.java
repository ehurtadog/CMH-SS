package mx.com.stsystems.CMH.Beta.web.rest.api.resources.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import mx.com.stsystems.CMH.Beta.dto.Paciente;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeRegistroPaciente;
import mx.com.stsystems.CMH.Beta.json.messages.response.MensajeRegistroPacienteResponse;
import mx.com.stsystems.CMH.Beta.web.controller.service.ServiceController;
import mx.com.stsystems.CMH.Beta.web.controller.service.impl.ServiceControllerImpl;

@Path("/Registro")
public class RegistroResourceImpl {
	
	@POST
	@Path("/Paciente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postUsuario(String registroUsuario) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		ServiceController serviceController = new ServiceControllerImpl();
		MensajeRegistroPaciente mensajeRegistroPaciente = null;
		MensajeRegistroPacienteResponse mensajeRegistroPacienteResponse = new MensajeRegistroPacienteResponse();
		long idFiliacion = 0;
		
		mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		
		try {
			System.out.println("VAR: registroUsuario: " + registroUsuario);

			mensajeRegistroPaciente = mapper.readValue(registroUsuario, MensajeRegistroPaciente.class);
			System.out.println("VAR: mensajeRegistroUsuario: " + mensajeRegistroPaciente);
		} catch (IOException ioe) {
			System.err.println("Error de conversion de JSON: " + ioe);
		}
		
		try {
			Paciente paciente = new Paciente(mensajeRegistroPaciente);
			idFiliacion = serviceController.registraPaciente(paciente);
			
			mensajeRegistroPacienteResponse.setEstatus(0);
			mensajeRegistroPacienteResponse.setIdFiliacion(idFiliacion);
			mensajeRegistroPacienteResponse.setDescripcion("PACIENTE REGISTRADO CORRECTAMENTE");
			mensajeRegistroPacienteResponse.setComentarios("PACIENTE NUEVO");
		} catch (SumarSaludException sse) {
			mensajeRegistroPacienteResponse.setEstatus(1);
			mensajeRegistroPacienteResponse.setIdFiliacion(0);
			mensajeRegistroPacienteResponse.setDescripcion("ERROR AL REGISTRAR AL PACIENTE");
			mensajeRegistroPacienteResponse.setComentarios(sse.toString());
		}
		
		try {
			System.out.println("VAR: mensajeRegistroPacienteResponse: " + mensajeRegistroPacienteResponse);

			jsonInString = mapper.writeValueAsString(mensajeRegistroPacienteResponse);
			System.out.println("VAR: jsonInString: " + jsonInString);
		} catch (IOException ioe) {
			System.err.println("Error de conversion de JSON: " + ioe);
		}
		
		return jsonInString;
	}
}
