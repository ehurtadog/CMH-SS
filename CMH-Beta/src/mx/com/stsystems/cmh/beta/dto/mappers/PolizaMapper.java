package mx.com.stsystems.cmh.beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.cmh.beta.dto.Poliza;

public class PolizaMapper implements RowMapper<Poliza> {

	@Override
	public Poliza mapRow(ResultSet rsPoliza, int rowNum) throws SQLException {
		Poliza poliza = new Poliza();
		poliza.setIdPoliza(rsPoliza.getString("IDPOLIZA"));
		poliza.setIdAseguradora(rsPoliza.getString("IDASEGURADORA"));
		poliza.setNumeroPoliza(rsPoliza.getLong("NUMEROPOLIZA"));
		poliza.setCobertura(rsPoliza.getLong("COBERTURA"));
		poliza.setDeducible(rsPoliza.getLong("DEDUCIBLE"));
		poliza.setCopago(rsPoliza.getLong("COPAGO"));
		poliza.setVigencia(rsPoliza.getInt("VIGENCIA"));
		poliza.setAseguradora(rsPoliza.getString("ASEGURADORA"));

		return poliza;
	}
	
}
