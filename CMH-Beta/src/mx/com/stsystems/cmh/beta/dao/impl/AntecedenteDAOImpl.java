package mx.com.stsystems.cmh.beta.dao.impl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.cmh.beta.dao.AntecedenteDAO;
import mx.com.stsystems.cmh.beta.dto.Antecedente;
import mx.com.stsystems.cmh.beta.dto.mappers.AntecedenteMapper;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;

public class AntecedenteDAOImpl implements AntecedenteDAO {
	private JdbcTemplate jdbcTemplate;
	private final static Logger LOGGER = LoggerFactory.getLogger(AntecedenteDAOImpl.class);
    
    @Autowired
    public AntecedenteDAOImpl(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	@Override
	public void registraAntecedente(Antecedente antecedente) throws SumarSaludException {
		StringBuilder QRY_REGISTRA_ANTECEDENTE = new StringBuilder()
			.append("INSERT INTO antecedente (")
			.append("	IDPACIENTE, HIPERTENSION, DIABETES, HIPERTIROIDISMO, TABAQUISMO, TABAQUISMOFREQ, " )
			.append("	ALCOHOLISMO, ALCOHOLISMOFREQ, EJERCICIO, EJERCICIOFREQ, HIPERTENSIONFAM, DIABETESFAM, ")
			.append("	HIPERTIROIDISMOFAM, CANCERFAM, INFARTOFAM) ")
			.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		try {
			jdbcTemplate.update(QRY_REGISTRA_ANTECEDENTE.toString(), new Object[] { antecedente.getIdPaciente(),
				antecedente.getHipertension(), antecedente.getDiabetes(), antecedente.getHipertiroidismo(),
				antecedente.getTabaquismo(), antecedente.getTabaquismoFrecuencia(), antecedente.getAlcoholismo(), 
				antecedente.getAlcoholismoFrecuencia(), antecedente.getEjercicio(), antecedente.getEjercicioFrecuencia(),
				antecedente.getHipertensionFamiliar(), antecedente.getDiabetesFamiliar(), antecedente.getHipertiroidismoFamiliar(),
				antecedente.getCancerFamiliar(), antecedente.getInfartoFamiliar() });
		} catch (DataAccessException dae) {
			String mensajeDeError = "Ocurrió un error al registrar los antecedentes del paciente: " + dae.getMessage();
			LOGGER.error(mensajeDeError, dae);
			throw new SumarSaludException(mensajeDeError);
		}
	}

	@Override
	public Antecedente consultaAntecedentePorIdPaciente(String idPaciente) throws SumarSaludException {
		Antecedente antecedente;
		StringBuilder QRY_CONSULTA_ANTECEDENTE_POR_ID_PACIENTE = new StringBuilder()
			.append("SELECT IDPACIENTE, HIPERTENSION, DIABETES, HIPERTIROIDISMO, TABAQUISMO, TABAQUISMOFREQ, " )
			.append("		ALCOHOLISMO, ALCOHOLISMOFREQ, EJERCICIO, EJERCICIOFREQ, HIPERTENSIONFAM, DIABETESFAM, ")
			.append("		HIPERTIROIDISMOFAM, CANCERFAM, INFARTOFAM ")
			.append(" FROM antecedente ")
			.append(" WHERE IDPACIENTE = ? ");
		
		try {
			antecedente = jdbcTemplate.queryForObject(QRY_CONSULTA_ANTECEDENTE_POR_ID_PACIENTE.toString(), new Object[] { idPaciente }, new AntecedenteMapper());
		} catch (DataAccessException dae) {
			String mensajeDeError = "Ocurrió un error en la consulta de los antecedentes del paciente: " + dae.getMessage();
			LOGGER.error(mensajeDeError, dae);
			throw new SumarSaludException(mensajeDeError);
		}
			
		return antecedente;
	}

}
