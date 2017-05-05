package mx.com.stsystems.cmh.beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.cmh.beta.dto.EstadoCivil;

public class EstadoCivilMapper implements RowMapper<EstadoCivil> {

	@Override
	public EstadoCivil mapRow(ResultSet rsEstadoCivil, int rowNum) throws SQLException {
		EstadoCivil estadoCivil = new EstadoCivil();
		estadoCivil.setIdEstadoCivil(rsEstadoCivil.getString("IDESTADOCIVIL"));
		estadoCivil.setOrden(rsEstadoCivil.getInt("ORDEN"));
		estadoCivil.setDescripcion(rsEstadoCivil.getString("DESCRIPCION"));

		return estadoCivil;
	}
}
