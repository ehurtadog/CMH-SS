package mx.com.stsystems.cmh.beta.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import mx.com.stsystems.cmh.beta.dao.PacienteDAO;
import mx.com.stsystems.cmh.beta.dto.Antecedente;
import mx.com.stsystems.cmh.beta.dto.Paciente;
import mx.com.stsystems.cmh.beta.dto.mappers.PacienteMapper;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;
import mx.com.stsystems.cmh.beta.json.messages.request.MensajeRegistroPaciente;

public class PacienteDAOImpl implements PacienteDAO {
	private JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager transactionManager;
	private final static Logger LOGGER = LoggerFactory.getLogger(PacienteDAOImpl.class);

    @Autowired
    public PacienteDAOImpl(DataSource dataSource, PlatformTransactionManager transactionManager) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    	this.transactionManager = transactionManager;
    }
    
	@Override
	public long registraPaciente(MensajeRegistroPaciente registroPaciente) throws SumarSaludException {
		final Paciente paciente = new Paciente(registroPaciente);
		final Antecedente antecedente = new Antecedente(paciente.getIdPaciente(), registroPaciente);
		long idFiliacion = 0;
		StringBuilder QRY_OBTIENE_MAX_NUMERO_FILIACION = new StringBuilder()
			.append("SELECT COALESCE(MAX(IDFILIACION), 0) AS IDFILIACION FROM paciente ");
		StringBuilder QRY_REGISTRA_PACIENTE = new StringBuilder()
			.append("INSERT INTO paciente (")
			.append("   IDPACIENTE, IDFILIACION, NOMBRES, APELLIDOPATERNO, APELLIDOMATERNO, SEXO, ")
			.append("   FECHANACIMIENTO, PESO, ALTURA, DONADORSANGRE, CORREOELECTRONICO, IDESTADOCIVIL, ")
			.append("   IDTIPOSANGRE, FECHAHORAREGISTRO, FECHAHORAMODIFICACION, FECHAHORABAJA) ")
			.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE(), SYSDATE(), NULL) ");
		StringBuilder QRY_REGISTRA_ANTECEDENTE = new StringBuilder()
				.append("INSERT INTO antecedente (")
				.append("	IDPACIENTE, HIPERTENSION, DIABETES, HIPERTIROIDISMO, TABAQUISMO, TABAQUISMOFREQ, " )
				.append("	ALCOHOLISMO, ALCOHOLISMOFREQ, EJERCICIO, EJERCICIOFREQ, HIPERTENSIONFAM, DIABETESFAM, ")
				.append("	HIPERTIROIDISMOFAM, CANCERFAM, INFARTOFAM) ")
				.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			idFiliacion = jdbcTemplate.queryForObject(QRY_OBTIENE_MAX_NUMERO_FILIACION.toString(), long.class);
			
			if (idFiliacion == 0) {
				String fil = new SimpleDateFormat("yyMM").format(new Date()) + "000001";
				idFiliacion = Long.valueOf(fil);
			} else {
				String strIdFiliacion = String.valueOf(idFiliacion);
				String consecutivo = strIdFiliacion.substring(4);
				String fil = new SimpleDateFormat("yyMM").format(new Date()) + consecutivo;
				idFiliacion = Long.valueOf(fil) + 1;
			}
			
			paciente.setIdFiliacion(idFiliacion);
			
			jdbcTemplate.update(QRY_REGISTRA_PACIENTE.toString(), new Object[] { paciente.getIdPaciente(),
				paciente.getIdFiliacion(), paciente.getNombres(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(),
				paciente.getSexo(), paciente.getFechaNacimiento(), paciente.getPeso(), paciente.getAltura(),
				paciente.getDonadorSangre(), paciente.getCorreoElectronico(), paciente.getIdEstadoCivil(), 
				paciente.getIdTipoSangre() });
			
			jdbcTemplate.update(QRY_REGISTRA_ANTECEDENTE.toString(), new Object[] { antecedente.getIdPaciente(),
				antecedente.getHipertension(), antecedente.getDiabetes(), antecedente.getHipertiroidismo(),
				antecedente.getTabaquismo(), antecedente.getTabaquismoFrecuencia(), antecedente.getAlcoholismo(), 
				antecedente.getAlcoholismoFrecuencia(), antecedente.getEjercicio(), antecedente.getEjercicioFrecuencia(),
				antecedente.getHipertensionFamiliar(), antecedente.getDiabetesFamiliar(), antecedente.getHipertiroidismoFamiliar(),
				antecedente.getCancerFamiliar(), antecedente.getInfartoFamiliar() });
			
			transactionManager.commit(status);
		} catch (DataAccessException dae) {
			String mensajeDeError = "Ocurrió un error al registrar al paciente: " + dae.getMessage();
			LOGGER.error(mensajeDeError, dae);
			transactionManager.rollback(status);
			throw new SumarSaludException(mensajeDeError);
		}
			
		return idFiliacion;
	}
	
	@Override
	public boolean buscaPacientePorCorreoElectronico(String correoElectronico) {
		boolean resultado = true;
		StringBuilder QRY_BUSCA_PACIENTE_POR_CORREO = new StringBuilder()
			.append("SELECT COUNT(1) ")
			.append(" FROM paciente ")
			.append(" WHERE CORREOELECTRONICO = ? ");
		
		try {
			int contador = jdbcTemplate.queryForObject(QRY_BUSCA_PACIENTE_POR_CORREO.toString(), 
				new Object[] { correoElectronico }, Integer.class);
			
			if (contador == 0) {
				resultado = false;
			}
		} catch (DataAccessException dae) {
			LOGGER.error("Ocurrió un error al consultar el paciente por correo electrónico: ", dae);
		}
		
		return resultado;
	}
	
	@Override
	public boolean buscaPacientePorIdFiliacion(long idFiliacion) {
		boolean resultado = true;
		StringBuilder qryBuscaPacientePorIdFiliacion = new StringBuilder()
			.append("SELECT COUNT(1) ")
			.append(" FROM paciente ")
			.append(" WHERE IDFILIACION = ? ");
		
		try {
			int contador = jdbcTemplate.queryForObject(qryBuscaPacientePorIdFiliacion.toString(), 
				new Object[] { idFiliacion }, Integer.class);
			
			if (contador == 0) {
				resultado = false;
			}
		} catch (DataAccessException dae) {
			LOGGER.error("Ocurrió un error al consultar el paciente por id de filiacion: ", dae);
		}
		
		return resultado;
	}
	
	@Override
	public Paciente consultaPacientePorIdFiliacion(long idFiliacion) throws SumarSaludException {
		Paciente paciente = null;
		StringBuilder QRY_CONSULTA_PACIENTE_POR_IDFILIACION = new StringBuilder()
			.append("SELECT ")
			.append("   p.IDPACIENTE AS IDPACIENTE, p.IDFILIACION AS IDFILIACION, p.NOMBRES AS NOMBRES, ")
			.append("   p.APELLIDOPATERNO AS APELLIDOPATERNO, p.APELLIDOMATERNO AS APELLIDOMATERNO, ")
			.append("   p.SEXO AS SEXO, p.FECHANACIMIENTO AS FECHANACIMIENTO, p.PESO AS PESO, p.ALTURA AS ALTURA, ")
			.append("   p.DONADORSANGRE AS DONADORSANGRE, p.CORREOELECTRONICO AS CORREOELECTRONICO, ")
			.append("   p.IDESTADOCIVIL AS IDESTADOCIVIL, p.IDTIPOSANGRE AS IDTIPOSANGRE, ts.DESCRIPCION AS TIPOSANGRE, ")
			.append("   p.FECHAHORAREGISTRO AS FECHAHORAREGISTRO, p.FECHAHORAMODIFICACION AS FECHAHORAMODIFICACION, ")
			.append("   p.FECHAHORABAJA AS FECHAHORABAJA ")
			.append(" FROM paciente p ")
			.append("  LEFT JOIN tiposangre ts ")
			.append("   ON ts.IDTIPOSANGRE = p.IDTIPOSANGRE ")
			.append(" WHERE IDFILIACION = ? ");
		
		try {
			paciente = jdbcTemplate.queryForObject(QRY_CONSULTA_PACIENTE_POR_IDFILIACION.toString(), new Object[] { idFiliacion }, 
				new PacienteMapper());
		} catch (DataAccessException dae) {
			String mensajeDeError = "Ocurrió un error al consultar un paciente por el id de filiación: " + dae.getMessage();
			LOGGER.error(mensajeDeError, dae);
			throw new SumarSaludException(mensajeDeError);
		}
		
		return paciente;
	}
	
	@Override
	public Paciente consultaPacientePorCorreoElectronico(String correoElectronico) throws SumarSaludException {
		Paciente paciente = null;
		StringBuilder QRY_CONSULTA_PACIENTE_POR_CORREO_ELECTRONICO = new StringBuilder()
			.append("SELECT ")
			.append("   p.IDPACIENTE AS IDPACIENTE, p.IDFILIACION AS IDFILIACION, p.NOMBRES AS NOMBRES, ")
			.append("   p.APELLIDOPATERNO AS APELLIDOPATERNO, p.APELLIDOMATERNO AS APELLIDOMATERNO, ")
			.append("   p.SEXO AS SEXO, p.FECHANACIMIENTO AS FECHANACIMIENTO, p.PESO AS PESO, p.ALTURA AS ALTURA, ")
			.append("   p.DONADORSANGRE AS DONADORSANGRE, p.CORREOELECTRONICO AS CORREOELECTRONICO, ")
			.append("   p.IDESTADOCIVIL AS IDESTADOCIVIL, p.IDTIPOSANGRE AS IDTIPOSANGRE, ts.DESCRIPCION AS TIPOSANGRE, ")
			.append("   p.FECHAHORAREGISTRO AS FECHAHORAREGISTRO, p.FECHAHORAMODIFICACION AS FECHAHORAMODIFICACION, ")
			.append("   p.FECHAHORABAJA AS FECHAHORABAJA ")
			.append(" FROM paciente p ")
			.append("  LEFT JOIN tiposangre ts ")
			.append("   ON ts.IDTIPOSANGRE = p.IDTIPOSANGRE ")
			.append(" WHERE CORREOELECTRONICO = ? ");
		
		try {
			paciente = jdbcTemplate.queryForObject(QRY_CONSULTA_PACIENTE_POR_CORREO_ELECTRONICO.toString(), new Object[] { correoElectronico }, 
				new PacienteMapper());
		} catch (DataAccessException dae) {
			String mensajeDeError = "Ocurrió un error al consultar un paciente por el correo electrónico: " + dae.getMessage();
			LOGGER.error(mensajeDeError, dae);
			throw new SumarSaludException(mensajeDeError);
		}
		
		return paciente;
	}

}
