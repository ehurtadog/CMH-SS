package mx.com.stsystems.cmh.beta.web.rest.api.resources.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeRegistroPaciente;
import mx.com.stsystems.cmh.beta.json.messages.response.MensajeRegistroPacienteResponse;
import mx.com.stsystems.cmh.beta.web.controller.service.ServiceController;

@Path("/Registro")
public class RegistroResourceImpl {
	private final static Logger LOGGER = LoggerFactory.getLogger(RegistroResourceImpl.class);
	private static ServiceController serviceController;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		serviceController = (ServiceController) context.getBean("serviceController");
	}
	
	@POST
	@Path("/Paciente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postUsuario(String registroPaciente) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		MensajeRegistroPaciente mensajeRegistroPaciente = null;
		MensajeRegistroPacienteResponse mensajeRegistroPacienteResponse = new MensajeRegistroPacienteResponse();
		long idFiliacion = 0;
		
		mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		
		try {
			LOGGER.debug("VAR: registroPaciente: " + registroPaciente);

			mensajeRegistroPaciente = mapper.readValue(registroPaciente, MensajeRegistroPaciente.class);
			LOGGER.debug("VAR: mensajeRegistroPaciente: " + mensajeRegistroPaciente);
		} catch (IOException ioe) {
			LOGGER.error("Error de conversion de JSON: ", ioe);
		}
		
		if (!serviceController.existePacientePorCorreoElectronico(mensajeRegistroPaciente.getCorreo())) {
			try {
				
				idFiliacion = serviceController.registraPaciente(mensajeRegistroPaciente);
				
				try {
					byte[] imageByteArray = decodeImage(mensajeRegistroPaciente.getFoto());
					FileOutputStream imageOutFile;
					imageOutFile = new FileOutputStream("/var/opt/appsumarsalud.com/resources/photos/" + idFiliacion + ".png");
					imageOutFile.write(imageByteArray);
					imageOutFile.close();
					
					mensajeRegistroPacienteResponse.setEstatus(0);
					mensajeRegistroPacienteResponse.setIdFiliacion(idFiliacion);
					mensajeRegistroPacienteResponse.setDescripcion("PACIENTE REGISTRADO CORRECTAMENTE");
					mensajeRegistroPacienteResponse.setComentarios("PACIENTE NUEVO");
				} catch (IOException ioe) {
					LOGGER.warn("NO SE PUDO GUARDAR LA FOTO", ioe);
					
					mensajeRegistroPacienteResponse.setEstatus(2);
					mensajeRegistroPacienteResponse.setIdFiliacion(idFiliacion);
					mensajeRegistroPacienteResponse.setDescripcion("SE REGISTRO AL PACIENTE PERO SIN LA FOTO");
					mensajeRegistroPacienteResponse.setComentarios("PACIENTE NUEVO");
				}
				

			} catch (SumarSaludException sse) {
				mensajeRegistroPacienteResponse.setEstatus(1);
				mensajeRegistroPacienteResponse.setIdFiliacion(0);
				mensajeRegistroPacienteResponse.setDescripcion("ERROR AL REGISTRAR AL PACIENTE");
				mensajeRegistroPacienteResponse.setComentarios(sse.toString());
			}
		} else {
			mensajeRegistroPacienteResponse.setEstatus(1);
			mensajeRegistroPacienteResponse.setIdFiliacion(0);
			mensajeRegistroPacienteResponse.setDescripcion("ERROR AL REGISTRAR AL PACIENTE");
			mensajeRegistroPacienteResponse.setComentarios("EL PACIENTE YA ESTA REGISTRADO EN LA BASE DE DATOS");
		}
			
		try {
			LOGGER.debug("VAR: mensajeRegistroPacienteResponse: " + mensajeRegistroPacienteResponse);

			jsonInString = mapper.writeValueAsString(mensajeRegistroPacienteResponse);
			LOGGER.debug("VAR: jsonInString: " + jsonInString);
		} catch (IOException ioe) {
			LOGGER.error("Error de conversion de JSON: ", ioe);
		}
		
		return jsonInString;
	}
	
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}
}
