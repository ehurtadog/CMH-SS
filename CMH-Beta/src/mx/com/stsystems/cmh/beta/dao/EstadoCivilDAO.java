package mx.com.stsystems.cmh.beta.dao;

import mx.com.stsystems.cmh.beta.dto.EstadoCivil;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;

public interface EstadoCivilDAO {
	public EstadoCivil consultaEstadoCivilPorDescripcion(String descripcion) throws SumarSaludException;
}
