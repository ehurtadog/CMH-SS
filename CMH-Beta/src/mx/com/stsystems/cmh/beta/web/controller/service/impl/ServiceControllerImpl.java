package mx.com.stsystems.cmh.beta.web.controller.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mx.com.stsystems.cmh.beta.dao.AntecedenteDAO;
import mx.com.stsystems.cmh.beta.dao.CodigoPostalDAO;
import mx.com.stsystems.cmh.beta.dao.EstadoCivilDAO;
import mx.com.stsystems.cmh.beta.dao.HospitalDAO;
import mx.com.stsystems.cmh.beta.dao.PacienteDAO;
import mx.com.stsystems.cmh.beta.dao.PolizaDAO;
import mx.com.stsystems.cmh.beta.dao.TipoSangreDAO;
import mx.com.stsystems.cmh.beta.dto.Antecedente;
import mx.com.stsystems.cmh.beta.dto.CodigoPostal;
import mx.com.stsystems.cmh.beta.dto.EstadoCivil;
import mx.com.stsystems.cmh.beta.dto.Hospital;
import mx.com.stsystems.cmh.beta.dto.Paciente;
import mx.com.stsystems.cmh.beta.dto.TipoSangre;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeRegistroPaciente;
import mx.com.stsystems.cmh.beta.json.messages.response.MensajePolizaMembresia;
import mx.com.stsystems.cmh.beta.web.controller.service.ServiceController;

public class ServiceControllerImpl implements ServiceController {
	private static HospitalDAO hospitalDAO;
	private static CodigoPostalDAO codigoPostalDAO;
	private static PacienteDAO pacienteDAO;
	private static AntecedenteDAO antecedenteDAO;
	private static EstadoCivilDAO estadoCivilDAO;
	private static TipoSangreDAO tipoSangreDAO;
	private static PolizaDAO polizaDAO;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		hospitalDAO = (HospitalDAO) context.getBean("hospitalDAO");
		codigoPostalDAO = (CodigoPostalDAO) context.getBean("codigoPostalDAO");
		pacienteDAO = (PacienteDAO) context.getBean("pacienteDAO");
		antecedenteDAO = (AntecedenteDAO) context.getBean("antecedenteDAO");
		estadoCivilDAO = (EstadoCivilDAO) context.getBean("estadoCivilDAO");
		tipoSangreDAO = (TipoSangreDAO) context.getBean("tipoSangreDAO");
		polizaDAO = (PolizaDAO) context.getBean("polizaDAO");
	}
	
	@Override
	public List<Hospital> solicitaHopitalesPorEstado(String estado) {
		return hospitalDAO.consultaHospitalesPorEstado(estado);
	}
	
	@Override
	public List<CodigoPostal> solicitaAsentamientosPorCodigoPostal(String codigoPostal) {
		return codigoPostalDAO.consultaAsentamientosPorCodigoPostal(codigoPostal);
	}
	
	@Override
	public long registraPaciente(MensajeRegistroPaciente registroPaciente) throws SumarSaludException {
		return pacienteDAO.registraPaciente(registroPaciente);
	}
	
	@Override
	public boolean existePacientePorCorreoElectronico(String correoElectronico) {
		return pacienteDAO.buscaPacientePorCorreoElectronico(correoElectronico);
	}
	
	@Override
	public boolean existePacientePorIdFiliacion(long idFiliacion) {
		return pacienteDAO.buscaPacientePorIdFiliacion(idFiliacion);
	}
	
	@Override
	public Paciente solicitaPacientePorIdFiliacion(long idFiliacion) throws SumarSaludException {
		return pacienteDAO.consultaPacientePorIdFiliacion(idFiliacion);
	}
	
	@Override
	public Paciente solicitaPacientePorCorreoElectronico(String correoElectronico) throws SumarSaludException {
		return pacienteDAO.consultaPacientePorCorreoElectronico(correoElectronico);
	}
	
	@Override
	public Antecedente solicitaAntecedentePorIdPaciente(String idPaciente) throws SumarSaludException {
		return antecedenteDAO.consultaAntecedentePorIdPaciente(idPaciente);
	}
	
	@Override
	public boolean existePolizaPorIdFiliacion(long idFiliacion) {
		return polizaDAO.buscaPolizaPorIdFiliacion(idFiliacion);
	}
	
	@Override
	public MensajePolizaMembresia solicitaPolizaPorIdFiliacion(long idFiliacion) throws SumarSaludException {
		return polizaDAO.consultaPolizaPorIdFiliacion(idFiliacion);
	}
	
	@Override 
	public EstadoCivil solicitaEstadoCivilPorDescripcion(String descripcion) throws SumarSaludException {
		return estadoCivilDAO.consultaEstadoCivilPorDescripcion(descripcion);
	}
	
	@Override
	public TipoSangre solicitaTipoSangrePorDescripcion(String descripcion) throws SumarSaludException {
		return tipoSangreDAO.consultaTipoSangrePorDescripcion(descripcion);
	}
}
