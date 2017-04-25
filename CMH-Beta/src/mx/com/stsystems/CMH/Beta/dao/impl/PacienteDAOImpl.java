package mx.com.stsystems.CMH.Beta.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.CMH.Beta.dao.PacienteDAO;
import mx.com.stsystems.CMH.Beta.dto.Paciente;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;

public class PacienteDAOImpl implements PacienteDAO {
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public PacienteDAOImpl(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	@Override
	public long registraPaciente(Paciente paciente) throws SumarSaludException {
		long idFiliacion = 1704000001;
		StringBuilder QRY_OBTIENE_MAX_NUMERO_FILIACION = new StringBuilder()
			.append("SELECT COALESCE(MAX(IDFILIACION), 0) AS IDFILIACION FROM paciente ");
		StringBuilder QRY_REGISTRA_PACIENTE = new StringBuilder()
			.append("INSERT INTO paciente (")
			.append("   IDPACIENTE, IDFILIACION, NOMBRES, APELLIDOPATERNO, APELLIDOMATERNO, SEXO, ")
			.append("   FECHANACIMIENTO, PESO, ALTURA, CORREOELECTRONICO, IDESTADOCIVIL, IDTIPOSANGRE) ")
			.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		
		try {
			idFiliacion = jdbcTemplate.queryForObject(QRY_OBTIENE_MAX_NUMERO_FILIACION.toString(), long.class);
			
			if (idFiliacion == 0) {
				String fil = new SimpleDateFormat("yyMM").format(new Date()) + "000001";
				idFiliacion = Long.valueOf(fil);
			} else {
				idFiliacion++;
			}
			
			paciente.setIdFiliacion(idFiliacion);
			
			jdbcTemplate.update(QRY_REGISTRA_PACIENTE.toString(), new Object[] { paciente.getIdPaciente(),
				paciente.getIdFiliacion(), paciente.getNombres(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(),
				paciente.getSexo(), paciente.getFechaNacimiento(), paciente.getPeso(), paciente.getAltura(),
				paciente.getCorreoElectronico(), paciente.getIdEstadoCivil(), paciente.getIdTipoSangre() });
		} catch (DataAccessException dae) {
			String mensajeDeError = "Ocurrió un error al registrar al paciente: " + dae;
			System.err.println(mensajeDeError);
			throw new SumarSaludException(mensajeDeError);
		}
			
		return idFiliacion;
	}

}
