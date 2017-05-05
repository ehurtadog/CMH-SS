package mx.com.stsystems.cmh.beta.dao;

import mx.com.stsystems.cmh.beta.dto.TipoSangre;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;

public interface TipoSangreDAO {
	public TipoSangre consultaTipoSangrePorDescripcion(String descripcion) throws SumarSaludException;
}
