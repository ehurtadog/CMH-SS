package mx.com.stsystems.cmh.beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.cmh.beta.dto.Beneficio;

public class BeneficioMapper implements RowMapper<Beneficio> {

	@Override
	public Beneficio mapRow(ResultSet rsBeneficio, int rowNum) throws SQLException {
		Beneficio beneficio = new Beneficio();
		beneficio.setIdBeneficio(rsBeneficio.getString("IDBENEFICIO"));
		beneficio.setDescripcion(rsBeneficio.getString("DESCRIPCION"));
		beneficio.setValor(rsBeneficio.getString("VALOR"));
		beneficio.setOrden(rsBeneficio.getInt("ORDEN"));
		beneficio.setFechaHoraRegistro(rsBeneficio.getTimestamp("FECHAHORAREGISTRO"));
		beneficio.setFechaHoraModificacion(rsBeneficio.getTimestamp("FECHAHORAMODIFICACION"));
		beneficio.setFechaHoraBaja(rsBeneficio.getTimestamp("FECHAHORABAJA"));

		return beneficio;
	}
	
}
