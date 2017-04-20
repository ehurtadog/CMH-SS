package mx.com.stsystems.CMH.Beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.CMH.Beta.dto.Hospital;

public class HospitalMapper implements RowMapper<Hospital> {

	@Override
	public Hospital mapRow(ResultSet rsHospital, int rowNum) throws SQLException {
		Hospital hospital = new Hospital();
		hospital.setIdHospital(rsHospital.getString("IDHOSPITAL"));
		hospital.setDescripcion(rsHospital.getString("DESCRIPCION"));
		hospital.setUrl(rsHospital.getString("URL"));
		hospital.setIdAsentamiento(rsHospital.getInt("IDASENTAMIENTO"));
		hospital.setIdMunicipio(rsHospital.getInt("IDMUNICIPIO"));
		hospital.setIdEstado(rsHospital.getInt("IDESTADO"));
		hospital.setIdCiudad(rsHospital.getInt("IDCIUDAD"));

		return hospital;
	}

}
