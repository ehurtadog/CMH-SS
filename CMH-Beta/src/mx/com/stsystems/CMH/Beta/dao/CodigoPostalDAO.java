package mx.com.stsystems.CMH.Beta.dao;

import java.util.List;

import mx.com.stsystems.CMH.Beta.dto.CodigoPostal;

public interface CodigoPostalDAO {
	public List<CodigoPostal> consultaAsentamientosPorCodigoPostal(String codigoPostal); 
}
