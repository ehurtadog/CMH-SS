package mx.com.stsystems.cmh.beta.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.cmh.beta.dao.HospitalDAO;
import mx.com.stsystems.cmh.beta.dto.Hospital;
import mx.com.stsystems.cmh.beta.dto.mappers.HospitalMapper;

public class HospitalDAOImpl implements HospitalDAO {
	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public HospitalDAOImpl(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	@Override
	public List<Hospital> consultaHospitalesPorEstado(String estado) {
		StringBuilder QRY_CONSULTA_HOPITAL_POR_ESTADO = new StringBuilder()
				.append("SELECT IDHOSPITAL, DESCRIPCION, URL, IDASENTAMIENTO, IDMUNICIPIO, IDESTADO, IDCIUDAD ")
				.append(" FROM hospital ")
				.append(" WHERE IDESTADO = ( ")
				.append("  SELECT IDESTADO ")
				.append("   FROM estado ")
				.append("   WHERE DESCRIPCION = ?)");
			
		List<Hospital> hospitales = jdbcTemplate.query(QRY_CONSULTA_HOPITAL_POR_ESTADO.toString(), 
			new Object[] { estado }, new HospitalMapper());
		
		return hospitales;
	}

}
