package mx.com.stsystems.CMH.Beta.web.controller.service;

import java.util.List;

import mx.com.stsystems.CMH.Beta.dto.Antecedente;
import mx.com.stsystems.CMH.Beta.dto.CodigoPostal;
import mx.com.stsystems.CMH.Beta.dto.EstadoCivil;
import mx.com.stsystems.CMH.Beta.dto.Hospital;
import mx.com.stsystems.CMH.Beta.dto.Paciente;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeRegistroPaciente;

public interface ServiceController {
	public List<Hospital> solicitaHopitalesPorEstado(String estado);
	
	public List<CodigoPostal> solicitaAsentamientosPorCodigoPostal(String codigoPostal);
	
	public long registraPaciente(MensajeRegistroPaciente registroPaciente) throws SumarSaludException;
	public boolean existePacientePorCorreoElectronico(String correoElectronico);
	public Paciente solicitaPacientePorIdFiliacion(long idFiliacion) throws SumarSaludException;
	
	public Antecedente solicitaAntecedentePorIdPaciente(String idPaciente) throws SumarSaludException;
	
	public EstadoCivil solicitaEstadoCivilPorDescripcion(String descripcion) throws SumarSaludException;
}
