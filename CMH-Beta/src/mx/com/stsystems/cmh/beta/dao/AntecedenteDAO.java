package mx.com.stsystems.cmh.beta.dao;

import mx.com.stsystems.cmh.beta.dto.Antecedente;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;

public interface AntecedenteDAO {
	public void registraAntecedente(Antecedente antecedente) throws SumarSaludException;
	public Antecedente consultaAntecedentePorIdPaciente(String idPaciente) throws SumarSaludException;
}
