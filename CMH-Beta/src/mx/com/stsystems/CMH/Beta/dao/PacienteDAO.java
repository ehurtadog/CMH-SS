package mx.com.stsystems.CMH.Beta.dao;

import mx.com.stsystems.CMH.Beta.dto.Paciente;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;

public interface PacienteDAO {
	public long registraPaciente(Paciente paciente) throws SumarSaludException;
	public boolean buscaPacientePorCorreoElectronico(String correoElectronico);
	public Paciente consultaPacientePorIdFiliacion(long idFiliacion) throws SumarSaludException;
}
