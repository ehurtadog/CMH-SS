package mx.com.stsystems.CMH.Beta.web.controller.service;

import java.util.List;

import mx.com.stsystems.CMH.Beta.dto.CodigoPostal;
import mx.com.stsystems.CMH.Beta.dto.Hospital;

public interface ServiceController {
	public List<Hospital> solicitaHopitalesPorEstado(String estado);
	public List<CodigoPostal> solicitaAsentamientosPorCodigoPostal(String codigoPostal);
}
