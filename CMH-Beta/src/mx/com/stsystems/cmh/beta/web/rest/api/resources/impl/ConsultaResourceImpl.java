package mx.com.stsystems.cmh.beta.web.rest.api.resources.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
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
import mx.com.stsystems.cmh.beta.json.messages.response.MensajeHospitalResponse;
import mx.com.stsystems.cmh.beta.json.messages.response.MensajePolizaMembresia;
import mx.com.stsystems.cmh.beta.util.Constantes;
import mx.com.stsystems.cmh.beta.web.controller.service.ServiceController;
import mx.com.stsystems.cmh.beta.web.controller.service.impl.ServiceControllerImpl;

@Path("/Consulta")
public class ConsultaResourceImpl implements Constantes {
	private final static Logger LOGGER = LoggerFactory.getLogger(ConsultaResourceImpl.class);

	@SuppressWarnings("incomplete-switch")
	@POST
	@Path("/HospitalesPorEstado")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postHospitalesPorEstado(String estado) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		ServiceController serviceController = new ServiceControllerImpl();
		MensajeHospitalResponse mensajeHospitalResponse = new MensajeHospitalResponse();
		
		try {
			LOGGER.debug("VAR: mensaje: " + estado);

			MensajeEstado mensajeEstado = mapper.readValue(estado, MensajeEstado.class);
			LOGGER.debug("VAR: mensajeEstado: " + mensajeEstado);
			
			try {
				List<Hospital> hospitales = serviceController.solicitaHopitalesPorEstado(mensajeEstado.getEstado());
				mensajeHospitalResponse.setHospitales(hospitales);
				mensajeHospitalResponse.setEstatus(EstatusConsultaHospital.OK.getCodigo());
				mensajeHospitalResponse.setMensaje(EstatusConsultaHospital.OK.getMensaje());
			} catch (SumarSaludException sse) {
				switch (sse.getEstatusConsultaHospital()) {
					case NO_EXISTEN_ELEMENTOS:
						mensajeHospitalResponse.setEstatus(EstatusConsultaHospital.NO_EXISTEN_ELEMENTOS.getCodigo());
						mensajeHospitalResponse.setMensaje(EstatusConsultaHospital.NO_EXISTEN_ELEMENTOS.getMensaje());
						break;
					case ERROR_EN_CONSULTA_HOSPITAL:
						mensajeHospitalResponse.setEstatus(EstatusConsultaHospital.ERROR_EN_CONSULTA_HOSPITAL.getCodigo());
						mensajeHospitalResponse.setMensaje(EstatusConsultaHospital.ERROR_EN_CONSULTA_HOSPITAL.getMensaje());
						break;
				}
			}
			
			jsonInString = mapper.writeValueAsString(mensajeHospitalResponse);
			LOGGER.debug("VAR: jsonInString: " + jsonInString);
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
	
	@POST
	@Path("/Poliza/idFiliacion/{idFiliacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public String postPolizaPorIdFiliacion(@PathParam("idFiliacion") long idFiliacion) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		ServiceController serviceController = new ServiceControllerImpl();
		MensajePolizaMembresia mensajePolizaMembresia = new MensajePolizaMembresia();
		mensajePolizaMembresia.setIdFiliacion(idFiliacion);
		
		mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		
		try {
			LOGGER.debug("VAR: idFiliacion: " + idFiliacion);
			
			if (serviceController.existePacientePorIdFiliacion(idFiliacion)) {
				if (serviceController.existePolizaPorIdFiliacion(idFiliacion)) {
					mensajePolizaMembresia = serviceController.solicitaPolizaPorIdFiliacion(idFiliacion);
					mensajePolizaMembresia.setEstatus(EstatusConsultaPoliza.OK.getCodigo());
					mensajePolizaMembresia.setMensaje(EstatusConsultaPoliza.OK.getMensaje());
				} else {
					mensajePolizaMembresia.setEstatus(EstatusConsultaPoliza.NO_EXISTE_POLIZA_ASOCIADA.getCodigo());
					mensajePolizaMembresia.setMensaje(EstatusConsultaPoliza.NO_EXISTE_POLIZA_ASOCIADA.getMensaje());
				}
			} else {
				mensajePolizaMembresia.setEstatus(EstatusConsultaPoliza.NO_EXISTE_ID_FILIACION.getCodigo());
				mensajePolizaMembresia.setMensaje(EstatusConsultaPoliza.NO_EXISTE_ID_FILIACION.getMensaje());
			}
		} catch (SumarSaludException sse) {
			LOGGER.error("Error en la consulta de la membresia.");
			mensajePolizaMembresia.setEstatus(EstatusConsultaPoliza.ERROR_EN_CONSULTA_MEMBRESIA.getCodigo());
			mensajePolizaMembresia.setMensaje(EstatusConsultaPoliza.ERROR_EN_CONSULTA_MEMBRESIA.getMensaje());
		}
		
		try {
			jsonInString = mapper.writeValueAsString(mensajePolizaMembresia);
			
			LOGGER.debug("VAR: jsonInString: " + jsonInString);
		} catch (IOException ioe) {
			LOGGER.error("Error de conversion de JSON");
		}
		
		return jsonInString;
	}
	
	@POST
	@Path("/Foto/idFiliacion/{idFiliacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public String postFotoPorIdFiliacion(@PathParam("idFiliacion") long idFiliacion, @Context ServletContext sc) {
		try {
//			ClassLoader classLoader = getClass().getClassLoader();
			System.out.println("ResourcePaths: " + sc.getResourcePaths("/resources/images"));
			URL resource = sc.getResource("/resources/images/1705000001.png");
			System.out.println("resource.getPath(): " + resource.getPath());
			File file = Paths.get(resource.toURI()).toFile();
//			File file = new File("/resources/images/1705000001.png");
//			File file = new File(classLoader.getResource("/resources/images/1705000001.png").getFile());
//			File file = new File(sc.getResource("/resources/images/1705000001.png").getFile());
	
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			
			String imageDataString = encodeImage(imageData);
			
			System.out.println("Image Data String size: " + imageDataString.length());
			System.out.println("Image Data String excerpt: " + imageDataString.substring(0, 500));
			
			byte[] imageByteArray = decodeImage(imageDataString);
			
			FileOutputStream imageOutFile = new FileOutputStream("d:\\1705000001-3.png");
			
			imageOutFile.write(imageByteArray);
			
			imageInFile.close();
			imageOutFile.close();
			
			System.out.println("Image successfully Manipulated!");
		} catch (FileNotFoundException fnfe) {
			System.err.println("Image not found: " + fnfe);
		} catch (IOException ioe) {
			System.err.println("Exception while reading the image: " + ioe);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}
	
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}
}
