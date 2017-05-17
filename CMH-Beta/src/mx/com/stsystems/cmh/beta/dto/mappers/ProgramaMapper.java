package mx.com.stsystems.cmh.beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.cmh.beta.dto.Programa;

public class ProgramaMapper implements RowMapper<Programa> {

	@Override
	public Programa mapRow(ResultSet rsPrograma, int rowNum) throws SQLException {
		Programa programa = new Programa();
		programa.setIdPrograma(rsPrograma.getString("IDPROGRAMA"));
		programa.setDescripcion(rsPrograma.getString("DESCRIPCION"));
		programa.setOrden(rsPrograma.getInt("ORDEN"));
		programa.setFechaHoraRegistro(rsPrograma.getTimestamp("FECHAHORAREGISTRO"));
		programa.setFechaHoraModificacion(rsPrograma.getTimestamp("FECHAHORAMODIFICACION"));
		programa.setFechaHoraBaja(rsPrograma.getTimestamp("FECHAHORABAJA"));

		return programa;
	}
}
