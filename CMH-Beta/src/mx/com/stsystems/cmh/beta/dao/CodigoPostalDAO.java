package mx.com.stsystems.cmh.beta.dao;

import java.util.List;

import mx.com.stsystems.cmh.beta.dto.CodigoPostal;

public interface CodigoPostalDAO {
	public List<CodigoPostal> consultaAsentamientosPorCodigoPostal(String codigoPostal); 
}
