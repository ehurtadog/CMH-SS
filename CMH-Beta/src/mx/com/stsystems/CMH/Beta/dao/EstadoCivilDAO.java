package mx.com.stsystems.CMH.Beta.dao;

import mx.com.stsystems.CMH.Beta.dto.EstadoCivil;
import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;

public interface EstadoCivilDAO {
	public EstadoCivil consultaEstadoCivilPorDescripcion(String descripcion) throws SumarSaludException;
}
