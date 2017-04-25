package mx.com.stsystems.CMH.Beta.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.CMH.Beta.dao.EstadoCivilDAO;
import mx.com.stsystems.CMH.Beta.dto.EstadoCivil;
import mx.com.stsystems.CMH.Beta.dto.mappers.EstadoCivilMapper;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;

public class EstadoCivilDAOImpl implements EstadoCivilDAO {
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public EstadoCivilDAOImpl(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public EstadoCivil consultaEstadoCivilPorDescripcion(String descripcion) throws SumarSaludException {
    	EstadoCivil estadoCivil = null;
    	StringBuilder QRY_BUSCA_ESTADO_CIVIL_POR_DESCRIPCION = new StringBuilder()
    		.append("SELECT IDESTADOCIVIL, ORDEN, DESCRIPCION ")
    		.append(" FROM estadocivil ")
    		.append(" WHERE DESCRIPCION = ? ");
    	
    	try {
    		estadoCivil = jdbcTemplate.queryForObject(QRY_BUSCA_ESTADO_CIVIL_POR_DESCRIPCION.toString(), 
    			new Object[] { descripcion }, new EstadoCivilMapper());
    	} catch (DataAccessException dae) {
    		String mensajeDeError = "Ocurrió un error al consultar el estado civil por descripción: " + dae;
    		System.err.println(mensajeDeError);
    		throw new SumarSaludException(mensajeDeError);
    	}
    	
    	return estadoCivil;
    }
}
