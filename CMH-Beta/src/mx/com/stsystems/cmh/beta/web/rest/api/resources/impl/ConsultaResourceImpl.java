package mx.com.stsystems.cmh.beta.web.rest.api.resources.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeConsultaHospital;
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeRegistroPaciente;
import mx.com.stsystems.cmh.beta.json.messages.response.MensajeHospitalResponse;
import mx.com.stsystems.cmh.beta.json.messages.response.MensajePhotoResponse;
import mx.com.stsystems.cmh.beta.json.messages.response.MensajePolizaMembresia;
import mx.com.stsystems.cmh.beta.util.Constantes;
import mx.com.stsystems.cmh.beta.web.controller.service.ServiceController;
import mx.com.stsystems.cmh.beta.web.controller.service.impl.ServiceControllerImpl;

@Path("/Consulta")
public class ConsultaResourceImpl implements Constantes {
	private final static Logger LOGGER = LoggerFactory.getLogger(ConsultaResourceImpl.class);

	@SuppressWarnings("incomplete-switch")
	@POST
	@Path("/Hospitales/{criterio}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postHospitales(@PathParam("criterio") String criterio, String jsonRequest) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = null;
		ServiceController serviceController = new ServiceControllerImpl();
		MensajeHospitalResponse mensajeHospitalResponse = new MensajeHospitalResponse();
		
		try {
			LOGGER.debug("[VAR] jsonRequest: " + jsonRequest);

			MensajeConsultaHospital mensajeConsultaHospital = mapper.readValue(jsonRequest, MensajeConsultaHospital.class);
			LOGGER.debug("[VAR] mensajeConsultaHospital: " + mensajeConsultaHospital);
			
			try {
				List<Hospital> hospitales;
				switch (criterio) {
					case CONSULTA_HOSPITAL_CRITERIO_ESTADO:
						hospitales = serviceController.solicitaHospitalesPorEstado(mensajeConsultaHospital.getEstado());
						break;
					case CONSULTA_HOSPITAL_CRITERIO_CIUDAD:
						hospitales = serviceController.solicitaHospitalesPorCiudad(mensajeConsultaHospital.getCiudad());
						break;
					case CONSULTA_HOSPITAL_CRITERIO_COORDENADAS:
						hospitales = serviceController.solicitaHospitalesPorLatitudLongitud(mensajeConsultaHospital.getLatitud(), 
							mensajeConsultaHospital.getLongitud());
						break;
					default:
						LOGGER.warn(EstatusConsultaHospital.CRITERIO_CONSULTA_INVALIDO.getMensaje());
						throw new SumarSaludException(EstatusConsultaHospital.CRITERIO_CONSULTA_INVALIDO);
				}
				
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
					case CRITERIO_CONSULTA_INVALIDO:
						mensajeHospitalResponse.setEstatus(EstatusConsultaHospital.CRITERIO_CONSULTA_INVALIDO.getCodigo());
						mensajeHospitalResponse.setMensaje(EstatusConsultaHospital.CRITERIO_CONSULTA_INVALIDO.getMensaje());
						break;
				}
			}
			
			jsonResponse = mapper.writeValueAsString(mensajeHospitalResponse);
			LOGGER.debug("VAR: jsonResponse: " + jsonResponse);
		} catch (IOException e) {
			LOGGER.error(EstatusConsultaHospital.ERROR_CONVERSION_JSON.getMensaje());
			jsonResponse = CONSULTA_HOSPITAL_JSON_INVALIDO;
		} 
		
		return jsonResponse;
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
	@Path("/Foto/{idFiliacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public String postFotoPorIdFiliacion(@PathParam("idFiliacion") long idFiliacion, @Context ServletContext sc) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = null;
		MensajePhotoResponse mensajePhotoResponse = new MensajePhotoResponse();
		
		try {
			File file = new File("/var/opt/appsumarsalud.com/resources/photos/" + idFiliacion + ".png");
	
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			
			String imageDataString = encodeImage(imageData);
			
			LOGGER.debug("Tamaño de la foto: " + imageDataString.length());
			LOGGER.debug("[VAR] imageDataString: <inicio>" + imageDataString + "<fin>");
			
			imageInFile.close();
			
			mensajePhotoResponse.setFoto(imageDataString);
			mensajePhotoResponse.setEstatus(EstatusConsultaFoto.OK.getCodigo());
			mensajePhotoResponse.setMensaje(EstatusConsultaFoto.OK.getMensaje());
		} catch (FileNotFoundException fnfe) {
			LOGGER.info(EstatusConsultaFoto.NO_EXISTE_LA_FOTO.getMensaje(), fnfe);
			mensajePhotoResponse.setEstatus(EstatusConsultaFoto.NO_EXISTE_LA_FOTO.getCodigo());
			mensajePhotoResponse.setMensaje(EstatusConsultaFoto.NO_EXISTE_LA_FOTO.getMensaje());
		} catch (IOException ioe) {
			LOGGER.info(EstatusConsultaFoto.ERROR_LEER_FOTO.getMensaje(), ioe);
			mensajePhotoResponse.setEstatus(EstatusConsultaFoto.ERROR_LEER_FOTO.getCodigo());
			mensajePhotoResponse.setMensaje(EstatusConsultaFoto.ERROR_LEER_FOTO.getMensaje());
		} 

		try {
			jsonResponse = mapper.writeValueAsString(mensajePhotoResponse);
		} catch (IOException ioe) {
			LOGGER.warn(EstatusConsultaFoto.ERROR_CONVERSION_JSON.getMensaje(), ioe);
			jsonResponse = CONSULTA_FOTO_JSON_INVALIDO;
		}

		
		return jsonResponse;
	}
	
	public static String encodeImage(byte[] imageByteArray) {
//		return Base64.encodeBase64URLSafeString(imageByteArray);
		return Base64.encodeBase64String(imageByteArray);
	}
	
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}
}
