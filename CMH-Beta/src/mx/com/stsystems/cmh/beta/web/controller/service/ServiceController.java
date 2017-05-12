package mx.com.stsystems.cmh.beta.web.controller.service;

import java.util.List;

import mx.com.stsystems.cmh.beta.dto.Antecedente;
import mx.com.stsystems.cmh.beta.dto.CodigoPostal;
import mx.com.stsystems.cmh.beta.dto.EstadoCivil;
import mx.com.stsystems.cmh.beta.dto.Hospital;
import mx.com.stsystems.cmh.beta.dto.Paciente;
import mx.com.stsystems.cmh.beta.dto.TipoSangre;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeRegistroPaciente;
import mx.com.stsystems.cmh.beta.json.messages.response.MensajePolizaMembresia;

public interface ServiceController {
	public List<Hospital> solicitaHospitalesPorEstado(String estado) throws SumarSaludException;
	public List<Hospital> solicitaHospitalesPorCiudad(String ciudad) throws SumarSaludException;
	public List<Hospital> solicitaHospitalesPorLatitudLongitud(double latitud, double longitud) throws SumarSaludException;
	
	public List<CodigoPostal> solicitaAsentamientosPorCodigoPostal(String codigoPostal);
	
	public long registraPaciente(MensajeRegistroPaciente registroPaciente) throws SumarSaludException;
	public boolean existePacientePorCorreoElectronico(String correoElectronico);
	public boolean existePacientePorIdFiliacion(long idFiliacion);
	public Paciente solicitaPacientePorIdFiliacion(long idFiliacion) throws SumarSaludException;
	public Paciente solicitaPacientePorCorreoElectronico(String correoElectronico) throws SumarSaludException;
	
	public Antecedente solicitaAntecedentePorIdPaciente(String idPaciente) throws SumarSaludException;
	
	public boolean existePolizaPorIdFiliacion(long idFiliacion);
	public MensajePolizaMembresia solicitaPolizaPorIdFiliacion(long idFiliacion) throws SumarSaludException;
	
	public EstadoCivil solicitaEstadoCivilPorDescripcion(String descripcion) throws SumarSaludException;
	public TipoSangre solicitaTipoSangrePorDescripcion(String descripcion) throws SumarSaludException;
}
