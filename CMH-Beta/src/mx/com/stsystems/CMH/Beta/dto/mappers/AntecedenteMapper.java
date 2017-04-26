package mx.com.stsystems.CMH.Beta.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.stsystems.CMH.Beta.dto.Antecedente;

public class AntecedenteMapper implements RowMapper<Antecedente> {

	@Override
	public Antecedente mapRow(ResultSet rsAntecedente, int rowNum) throws SQLException {
		Antecedente antecedente = new Antecedente();
		antecedente.setIdPaciente(rsAntecedente.getString("IDPACIENTE"));
		antecedente.setHipertension(rsAntecedente.getString("HIPERTENSION"));
		antecedente.setDiabetes(rsAntecedente.getString("DIABETES"));
		antecedente.setHipertiroidismo(rsAntecedente.getString("HIPERTIROIDISMO"));
		antecedente.setTabaquismo(rsAntecedente.getString("TABAQUISMO"));
		antecedente.setTabaquismoFrecuencia(rsAntecedente.getInt("TABAQUISMOFREQ"));
		antecedente.setAlcoholismo(rsAntecedente.getString("ALCOHOLISMO"));
		antecedente.setAlcoholismoFrecuencia(rsAntecedente.getInt("ALCOHOLISMOFREQ"));
		antecedente.setEjercicio(rsAntecedente.getString("EJERCICIO"));
		antecedente.setEjercicioFrecuencia(rsAntecedente.getInt("EJERCICIOFREQ"));
		antecedente.setHipertensionFamiliar(rsAntecedente.getString("HIPERTENSIONFAM"));
		antecedente.setHipertiroidismoFamiliar(rsAntecedente.getString("HIPERTIROIDISMOFAM"));
		antecedente.setCancerFamiliar(rsAntecedente.getString("CANCERFAM"));
		antecedente.setInfartoFamiliar(rsAntecedente.getString("INFARTOFAM"));

		return antecedente;
	}
	
}
