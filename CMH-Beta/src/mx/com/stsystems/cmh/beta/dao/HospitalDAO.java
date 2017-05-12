package mx.com.stsystems.cmh.beta.dao;

import java.util.List;

import mx.com.stsystems.cmh.beta.dto.Hospital;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;

public interface HospitalDAO {
	public List<Hospital> consultaHospitalesPorEstado(String estado) throws SumarSaludException;
	public List<Hospital> consultaHospitalesPorCiudad(String ciudad) throws SumarSaludException;
	public List<Hospital> consultaHospitalesPorLatitudLongitud(double latitud, double longitud) throws SumarSaludException;
}
