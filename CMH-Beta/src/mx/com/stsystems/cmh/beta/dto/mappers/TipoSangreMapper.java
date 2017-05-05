package mx.com.stsystems.cmh.beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.cmh.beta.dto.TipoSangre;

public class TipoSangreMapper implements RowMapper<TipoSangre> {

	@Override
	public TipoSangre mapRow(ResultSet rsTipoSangre, int rowNum) throws SQLException {
		TipoSangre tipoSangre = new TipoSangre();
		tipoSangre.setIdTipoSangre(rsTipoSangre.getString("IDTIPOSANGRE"));
		tipoSangre.setDescripcion(rsTipoSangre.getString("DESCRIPCION"));
		tipoSangre.setOrden(rsTipoSangre.getInt("ORDEN"));

		return tipoSangre;
	}
	
}
