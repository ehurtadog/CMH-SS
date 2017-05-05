package mx.com.stsystems.cmh.beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.cmh.beta.dto.CodigoPostal;

public class CodigoPostalMapper implements RowMapper<CodigoPostal> {
	
	@Override
	public CodigoPostal mapRow(ResultSet rsCodigoPostal, int rowNum) throws SQLException {
		CodigoPostal codigoPostal = new CodigoPostal();
		codigoPostal.setCodigoPostal(rsCodigoPostal.getString("CODIGOPOSTAL"));
		codigoPostal.setAsentamiento(rsCodigoPostal.getString("ASENTAMIENTO"));
		codigoPostal.setTipoAsentamiento(rsCodigoPostal.getString("TIPOASENTAMIENTO"));
		codigoPostal.setMunicipio(rsCodigoPostal.getString("MUNICIPIO"));
		codigoPostal.setEstado(rsCodigoPostal.getString("ESTADO"));
		codigoPostal.setCiudad(rsCodigoPostal.getString("CIUDAD"));
		codigoPostal.setCodigoPostalAdministracion(rsCodigoPostal.getString("CODIGOPOSTALADMON"));
		codigoPostal.setIdEstado(rsCodigoPostal.getString("IDESTADO"));
		codigoPostal.setIdTipoAsentamiento(rsCodigoPostal.getString("IDTIPOASENTAMIENTO"));
		codigoPostal.setIdMunicipio(rsCodigoPostal.getString("IDMUNICIPIO"));
		codigoPostal.setIdAsentamiento(rsCodigoPostal.getString("IDASENTAMIENTO"));
		codigoPostal.setZonaAsentamiento(rsCodigoPostal.getString("ZONAASENTAMIENTO"));
		codigoPostal.setIdCiudad(rsCodigoPostal.getString("IDCIUDAD"));

		return codigoPostal;
	}
}
