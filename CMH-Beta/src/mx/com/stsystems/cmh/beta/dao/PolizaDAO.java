package mx.com.stsystems.cmh.beta.dao;

import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;
import mx.com.stsystems.cmh.beta.json.messages.response.MensajePolizaMembresia;

public interface PolizaDAO {
	public boolean buscaPolizaPorIdFiliacion(long idFiliacion);
	public MensajePolizaMembresia consultaPolizaPorIdFiliacion(long idFiliacion) throws SumarSaludException;
}
