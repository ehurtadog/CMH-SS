package mx.com.stsystems.CMH.Beta.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.CMH.Beta.dao.AntecedenteDAO;
import mx.com.stsystems.CMH.Beta.dto.Antecedente;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;

public class AntecedenteDAOImpl implements AntecedenteDAO {
	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public AntecedenteDAOImpl(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	@Override
	public void registraAntecedente(Antecedente antecedente) throws SumarSaludException {
		StringBuilder QRY_REGISTRA_ANTECEDENTE = new StringBuilder()
			.append("INSERT INTO antecedente (")
			.append("	IDPACIENTER, HIPERTENSION, DIABETES, HIPERTIROIDISMO, TABAQUISMO, TABAQUISMOFREQ, " )
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
			String mensajeDeError = "Ocurri� un error al registrar los antecedentes del paciente: " + dae;
			System.err.println(mensajeDeError);
			throw new SumarSaludException(mensajeDeError);
		}
	}

}
