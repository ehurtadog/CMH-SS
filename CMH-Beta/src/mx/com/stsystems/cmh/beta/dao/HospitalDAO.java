package mx.com.stsystems.cmh.beta.dao;

import java.util.List;

import mx.com.stsystems.cmh.beta.dto.Hospital;

public interface HospitalDAO {
	public List<Hospital> consultaHospitalesPorEstado(String estado);
}
