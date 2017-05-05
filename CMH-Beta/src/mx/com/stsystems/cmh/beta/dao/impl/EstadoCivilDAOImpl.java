package mx.com.stsystems.cmh.beta.dao.impl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.cmh.beta.dao.EstadoCivilDAO;
import mx.com.stsystems.cmh.beta.dto.EstadoCivil;
import mx.com.stsystems.cmh.beta.dto.mappers.EstadoCivilMapper;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;

public class EstadoCivilDAOImpl implements EstadoCivilDAO {
	private JdbcTemplate jdbcTemplate;
	private final static Logger LOGGER = LoggerFactory.getLogger(EstadoCivilDAOImpl.class);

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
    		String mensajeDeError = "Ocurrió un error al consultar el estado civil por descripción: " + dae.getMessage();
    		LOGGER.error(mensajeDeError, dae);
    		throw new SumarSaludException(mensajeDeError);
    	}
    	
    	return estadoCivil;
    }
}
