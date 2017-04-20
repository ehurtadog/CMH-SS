package mx.com.stsystems.CMH.Beta.dao;

import java.util.List;

import mx.com.stsystems.CMH.Beta.dto.Hospital;

public interface HospitalDAO {
	public List<Hospital> consultaHospitalesPorEstado(String estado);
}
