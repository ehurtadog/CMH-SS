package mx.com.stsystems.CMH.Beta.web.controller.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mx.com.stsystems.CMH.Beta.dao.CodigoPostalDAO;
import mx.com.stsystems.CMH.Beta.dao.HospitalDAO;
import mx.com.stsystems.CMH.Beta.dto.CodigoPostal;
import mx.com.stsystems.CMH.Beta.dto.Hospital;
import mx.com.stsystems.CMH.Beta.web.controller.service.ServiceController;

public class ServiceControllerImpl implements ServiceController {
	private static HospitalDAO hospitalDAO;
	private static CodigoPostalDAO codigoPostalDAO;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		hospitalDAO = (HospitalDAO) context.getBean("hospitalDAO");
		codigoPostalDAO = (CodigoPostalDAO) context.getBean("codigoPostalDAO");
	}
	
	public List<Hospital> solicitaHopitalesPorEstado(String estado) {
		return hospitalDAO.consultaHospitalesPorEstado(estado);
	}
	
	public List<CodigoPostal> solicitaAsentamientosPorCodigoPostal(String codigoPostal) {
		return codigoPostalDAO.consultaAsentamientosPorCodigoPostal(codigoPostal);
	}
}
