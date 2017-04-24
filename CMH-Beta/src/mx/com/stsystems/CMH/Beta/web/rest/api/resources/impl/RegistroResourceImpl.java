package mx.com.stsystems.CMH.Beta.web.rest.api.resources.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import mx.com.stsystems.CMH.Beta.json.messages.MensajeRegistroUsuario;

@Path("/Registro")
public class RegistroResourceImpl {
	
	@POST
	@Path("/Usuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postUsuario(String registroUsuario) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "{\"numCliente\":\"100233871\"";
//		ServiceController serviceController = new ServiceControllerImpl();
		
		mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		
		try {
			System.out.println("VAR: registroUsuario: " + registroUsuario);

			MensajeRegistroUsuario mensajeRegistroUsuario = mapper.readValue(registroUsuario, MensajeRegistroUsuario.class);
			System.out.println("VAR: mensajeRegistroUsuario: " + mensajeRegistroUsuario);
		} catch (IOException ioe) {
			System.err.println("Error de conversion de JSON: " + ioe);
		}
		
		return jsonInString;
	}
}
