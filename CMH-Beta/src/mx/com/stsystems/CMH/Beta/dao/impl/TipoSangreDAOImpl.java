package mx.com.stsystems.CMH.Beta.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.CMH.Beta.dao.TipoSangreDAO;
import mx.com.stsystems.CMH.Beta.dto.TipoSangre;
import mx.com.stsystems.CMH.Beta.dto.mappers.TipoSangreMapper;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;

public class TipoSangreDAOImpl implements TipoSangreDAO {
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public TipoSangreDAOImpl(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public TipoSangre consultaTipoSangrePorDescripcion(String descripcion) throws SumarSaludException {
    	TipoSangre tipoSangre = null;
    	StringBuilder QRY_BUSCA_TIPO_SANGRE_POR_DESCRIPCION = new StringBuilder()
    		.append("SELECT IDTIPOSANGRE, DESCRIPCION, ORDEN ")
    		.append(" FROM tiposangre ")
    		.append(" WHERE DESCRIPCION = ? ");
    	
    	try {
    		tipoSangre = jdbcTemplate.queryForObject(QRY_BUSCA_TIPO_SANGRE_POR_DESCRIPCION.toString(), 
    			new Object[] { descripcion }, new TipoSangreMapper());
    	} catch (DataAccessException dae) {
    		String mensajeDeError = "Ocurrió un error al consultar el tipo de sangre por descripción: " + dae;
    		System.err.println(mensajeDeError);
    		throw new SumarSaludException(mensajeDeError);
    	} 
//    	catch (EmptyResultDataAccessException erdae) {
//    		
//    	}
    	
    	return tipoSangre;
    }

}
