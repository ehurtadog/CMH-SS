package mx.com.stsystems.CMH.Beta.web.controller.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mx.com.stsystems.CMH.Beta.dao.HospitalDAO;
import mx.com.stsystems.CMH.Beta.dto.Hospital;

public class ServiceControllerImpl {
	private static HospitalDAO hospitalDAO;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		hospitalDAO = (HospitalDAO) context.getBean("hospitalDAO");
	}
	
	public List<Hospital> solicitaHopitalesPorEstado(String estado) {
		return hospitalDAO.consultaHospitalesPorEstado(estado);
	}
}
