package mx.com.stsystems.cmh.beta.dao;

import mx.com.stsystems.cmh.beta.dto.Paciente;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeRegistroPaciente;

public interface PacienteDAO {
	public long registraPaciente(MensajeRegistroPaciente registroPaciente) throws SumarSaludException;
	public boolean buscaPacientePorCorreoElectronico(String correoElectronico);
	public boolean buscaPacientePorIdFiliacion(long idFiliacion);
	public Paciente consultaPacientePorIdFiliacion(long idFiliacion) throws SumarSaludException;
	public Paciente consultaPacientePorCorreoElectronico(String correoElectronico) throws SumarSaludException;
}
