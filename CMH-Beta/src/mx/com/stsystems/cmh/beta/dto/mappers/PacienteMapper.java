package mx.com.stsystems.cmh.beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.cmh.beta.dto.Paciente;

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
		paciente.setDonadorSangre(rsPaciente.getString("DONADORSANGRE"));
		paciente.setCorreoElectronico(rsPaciente.getString("CORREOELECTRONICO"));
		paciente.setIdEstadoCivil(rsPaciente.getString("IDESTADOCIVIL"));
		paciente.setIdTipoSangre(rsPaciente.getString("IDTIPOSANGRE"));
		paciente.setTipoSangre(rsPaciente.getString("TIPOSANGRE"));
		paciente.setFechaHoraRegistro(rsPaciente.getTimestamp("FECHAHORAREGISTRO"));
		paciente.setFechaHoraModificacion(rsPaciente.getTimestamp("FECHAHORAMODIFICACION"));
		paciente.setFechaHoraBaja(rsPaciente.getTimestamp("FECHAHORABAJA"));

		return paciente;
	}
	
}
