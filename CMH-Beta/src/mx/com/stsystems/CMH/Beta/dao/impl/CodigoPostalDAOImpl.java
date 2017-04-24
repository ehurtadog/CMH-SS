package mx.com.stsystems.CMH.Beta.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.CMH.Beta.dao.CodigoPostalDAO;
import mx.com.stsystems.CMH.Beta.dto.CodigoPostal;
import mx.com.stsystems.CMH.Beta.dto.mappers.CodigoPostalMapper;

public class CodigoPostalDAOImpl implements CodigoPostalDAO {
	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public CodigoPostalDAOImpl(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	@Override
	public List<CodigoPostal> consultaAsentamientosPorCodigoPostal(String codigoPostal) {
		StringBuilder QRY_CONSULTA_ASENTAMIENTOS_POR_CODIGO_POSTAL = new StringBuilder()
				.append("SELECT CODIGOPOSTAL, ASENTAMIENTO, TIPOASENTAMIENTO, MUNICIPIO, ESTADO, CIUDAD, ")
				.append("		CODIGOPOSTALADMON, IDESTADO, IDTIPOASENTAMIENTO, IDMUNICIPIO, IDASENTAMIENTO, ")
				.append("		ZONAASENTAMIENTO, IDCIUDAD ")
				.append(" FROM codigopostal ")
				.append(" WHERE CODIGOPOSTAL = ? ");
			
		List<CodigoPostal> codigosPostales = jdbcTemplate.query(QRY_CONSULTA_ASENTAMIENTOS_POR_CODIGO_POSTAL.toString(), 
			new Object[] { codigoPostal }, new CodigoPostalMapper());
		
		return codigosPostales;
	}

}
