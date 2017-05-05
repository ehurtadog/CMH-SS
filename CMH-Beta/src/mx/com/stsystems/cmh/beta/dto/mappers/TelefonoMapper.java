package mx.com.stsystems.cmh.beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.cmh.beta.dto.Telefono;

public class TelefonoMapper implements RowMapper<Telefono> {

	@Override
	public Telefono mapRow(ResultSet rsTelefono, int rowNum) throws SQLException {
		Telefono telefono = new Telefono();
		telefono.setIdTelefono(rsTelefono.getString("IDTELEFONO"));
		telefono.setNumero(rsTelefono.getString("NUMERO"));
		telefono.setDescripcion(rsTelefono.getString("DESCRIPCION"));
		telefono.setIdTipoTelefono(rsTelefono.getString("IDTIPOTELEFONO"));

		return telefono;
	}

}
