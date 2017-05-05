package mx.com.stsystems.CMH.Beta.dao;

import mx.com.stsystems.CMH.Beta.dto.Paciente;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeRegistroPaciente;

public interface PacienteDAO {
	public long registraPaciente(MensajeRegistroPaciente registroPaciente) throws SumarSaludException;
	public boolean buscaPacientePorCorreoElectronico(String correoElectronico);
	public Paciente consultaPacientePorIdFiliacion(long idFiliacion) throws SumarSaludException;
	public Paciente consultaPacientePorCorreoElectronico(String correoElectronico) throws SumarSaludException;
}
