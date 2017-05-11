package mx.com.stsystems.cmh.beta.exceptions;

import mx.com.stsystems.cmh.beta.util.Constantes.EstatusConsultaHospital;

public class SumarSaludException extends Exception {
	private static final long serialVersionUID = -5949178434954894068L;
	
	private EstatusConsultaHospital estatusConsultaHospital;

	public SumarSaludException(String message) {
		super(message);
	}
	
	public SumarSaludException(EstatusConsultaHospital estatusConsultaHospital) {
		this.estatusConsultaHospital = estatusConsultaHospital;
	}

	public EstatusConsultaHospital getEstatusConsultaHospital() {
		return this.estatusConsultaHospital;
	}

	public void setEstatusConsultaHospital(EstatusConsultaHospital estatusConsultaHospital) {
		this.estatusConsultaHospital = estatusConsultaHospital;
	}
	
}
