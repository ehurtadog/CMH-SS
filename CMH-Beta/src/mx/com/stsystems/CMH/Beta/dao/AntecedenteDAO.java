package mx.com.stsystems.CMH.Beta.dao;

import mx.com.stsystems.CMH.Beta.dto.Antecedente;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;

public interface AntecedenteDAO {
	public void registraAntecedente(Antecedente antecedente) throws SumarSaludException;
}
