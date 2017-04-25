package mx.com.stsystems.CMH.Beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.CMH.Beta.dto.Paciente;

public class PacienteMapper implements RowMapper<Paciente> {

	@Override
	public Paciente mapRow(ResultSet rsPaciente, int rowNum) throws SQLException {
		Paciente paciente = new Paciente();
		paciente.setIdPaciente(rsPaciente.getString("IDPACIENTE"));
		paciente.setIdFiliacion(rsPaciente.getLong("IDFILIACION"));
		paciente.setNombres(rsPaciente.getString("NOMBRES"));
		paciente.setApellidoPaterno(rsPaciente.getString("APELLIDOPATERNO"));
		paciente.setApellidoMaterno(rsPaciente.getString("APELLIDOMATERNO"));
		paciente.setSexo(rsPaciente.getString("SEXO"));
		paciente.setFechaNacimiento(rsPaciente.getDate("FECHANACIMIENTO"));
		paciente.setPeso(rsPaciente.getFloat("PESO"));
		paciente.setAltura(rsPaciente.getFloat("ALTURA"));
		paciente.setCorreoElectronico(rsPaciente.getString("CORREOELECTRONICO"));
		paciente.setIdEstadoCivil(rsPaciente.getString("IDESTADOCIVIL"));
		paciente.setIdTipoSangre(rsPaciente.getString("IDTIPOSANGRE"));

		return paciente;
	}
	
}
