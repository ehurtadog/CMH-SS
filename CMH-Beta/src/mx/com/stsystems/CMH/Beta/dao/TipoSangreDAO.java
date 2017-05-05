package mx.com.stsystems.CMH.Beta.dao;

import mx.com.stsystems.CMH.Beta.dto.TipoSangre;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;

public interface TipoSangreDAO {
	public TipoSangre consultaTipoSangrePorDescripcion(String descripcion) throws SumarSaludException;
}
