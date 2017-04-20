package mx.com.stsystems.CMH.Beta.web.controller.service.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ServiceController {
	
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    
//    public DataSource getDataSource() {
//    	return this.dataSource;
//    }
 
    @Autowired
    public ServiceController(DataSource dataSource) {
    	this.dataSource = dataSource;
    	this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }
	
	public void solicitaTelefono(String idTelefono) {
		StringBuilder QRY_SOLICITA_TELEFONO = new StringBuilder()
			.append("SELECT NUMERO ")
			.append(" FROM telefono ")
			.append(" WHERE IDTELEFONO = ? ");
		
		String telefono = jdbcTemplate.queryForObject(QRY_SOLICITA_TELEFONO.toString(), new Object[] { idTelefono }, String.class);
		
		System.out.println("VAR: telefono: " + telefono);
	}
}
