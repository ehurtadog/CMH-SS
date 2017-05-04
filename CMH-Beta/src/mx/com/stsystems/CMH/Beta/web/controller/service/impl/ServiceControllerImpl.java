package mx.com.stsystems.CMH.Beta.web.controller.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mx.com.stsystems.CMH.Beta.dao.AntecedenteDAO;
import mx.com.stsystems.CMH.Beta.dao.CodigoPostalDAO;
import mx.com.stsystems.CMH.Beta.dao.EstadoCivilDAO;
import mx.com.stsystems.CMH.Beta.dao.HospitalDAO;
import mx.com.stsystems.CMH.Beta.dao.PacienteDAO;
import mx.com.stsystems.CMH.Beta.dao.TipoSangreDAO;
import mx.com.stsystems.CMH.Beta.dto.Antecedente;
import mx.com.stsystems.CMH.Beta.dto.CodigoPostal;
import mx.com.stsystems.CMH.Beta.dto.EstadoCivil;
import mx.com.stsystems.CMH.Beta.dto.Hospital;
import mx.com.stsystems.CMH.Beta.dto.Paciente;
import mx.com.stsystems.CMH.Beta.dto.TipoSangre;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeRegistroPaciente;
import mx.com.stsystems.CMH.Beta.web.controller.service.ServiceController;

public class ServiceControllerImpl implements ServiceController {
	private static HospitalDAO hospitalDAO;
	private static CodigoPostalDAO codigoPostalDAO;
	private static PacienteDAO pacienteDAO;
	private static AntecedenteDAO antecedenteDAO;
	private static EstadoCivilDAO estadoCivilDAO;
	private static TipoSangreDAO tipoSangreDAO;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		hospitalDAO = (HospitalDAO) context.getBean("hospitalDAO");
		codigoPostalDAO = (CodigoPostalDAO) context.getBean("codigoPostalDAO");
		pacienteDAO = (PacienteDAO) context.getBean("pacienteDAO");
		antecedenteDAO = (AntecedenteDAO) context.getBean("antecedenteDAO");
		estadoCivilDAO = (EstadoCivilDAO) context.getBean("estadoCivilDAO");
		tipoSangreDAO = (TipoSangreDAO) context.getBean("tipoSangreDAO");
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
	public Paciente solicitaPacientePorIdFiliacion(long idFiliacion) throws SumarSaludException {
		return pacienteDAO.consultaPacientePorIdFiliacion(idFiliacion);
	}
	
	@Override
	public Antecedente solicitaAntecedentePorIdPaciente(String idPaciente) throws SumarSaludException {
		return antecedenteDAO.consultaAntecedentePorIdPaciente(idPaciente);
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
