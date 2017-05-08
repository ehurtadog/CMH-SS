package mx.com.stsystems.cmh.beta.dto;

import java.io.Serializable;

import mx.com.stsystems.cmh.beta.json.messages.request.MensajeRegistroPaciente;

public class Antecedente implements Serializable {
	private static final long serialVersionUID = 371792434244520196L;
	
	private String idPaciente;
	private String hipertension;
	private String diabetes;
	private String hipertiroidismo;
	private String tabaquismo;
	private int tabaquismoFrecuencia;
	private String alcoholismo;
	private String alcoholismoFrecuencia;
	private String ejercicio;
	private String ejercicioFrecuencia;
	private String hipertensionFamiliar;
	private String diabetesFamiliar;
	private String hipertiroidismoFamiliar;
	private String cancerFamiliar;
	private String infartoFamiliar;
	
	public Antecedente() {
	}

	public Antecedente(String idPaciente, MensajeRegistroPaciente registroPaciente) {
		this.idPaciente = idPaciente;
		this.hipertension = registroPaciente.getHipertension();
		this.diabetes = registroPaciente.getDiabetes();
		this.hipertiroidismo = registroPaciente.getHipertiroidismo();
		this.tabaquismo = registroPaciente.getFumas();
		this.tabaquismoFrecuencia = registroPaciente.getFrecuenciaFuma();
		this.alcoholismo = registroPaciente.getTomAlcohol();
		this.alcoholismoFrecuencia = registroPaciente.getFrecuenciaTomAlcohol();
		this.ejercicio = registroPaciente.getEjercicio();
		this.ejercicioFrecuencia = registroPaciente.getFrecuenciaEjercicio();
		this.hipertensionFamiliar = registroPaciente.getHipertensionFam();
		this.diabetesFamiliar = registroPaciente.getDiabetesFam();
		this.hipertiroidismoFamiliar = registroPaciente.getHipertiroidismoFam();
		this.cancerFamiliar = registroPaciente.getCancerFam();
		this.infartoFamiliar = registroPaciente.getInfartoFam();
	}
	
	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getHipertension() {
		return hipertension;
	}

	public void setHipertension(String hipertension) {
		this.hipertension = hipertension;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	public String getHipertiroidismo() {
		return hipertiroidismo;
	}

	public void setHipertiroidismo(String hipertiroidismo) {
		this.hipertiroidismo = hipertiroidismo;
	}

	public String getTabaquismo() {
		return tabaquismo;
	}

	public void setTabaquismo(String tabaquismo) {
		this.tabaquismo = tabaquismo;
	}

	public int getTabaquismoFrecuencia() {
		return tabaquismoFrecuencia;
	}

	public void setTabaquismoFrecuencia(int tabaquismoFrecuencia) {
		this.tabaquismoFrecuencia = tabaquismoFrecuencia;
	}

	public String getAlcoholismo() {
		return alcoholismo;
	}

	public void setAlcoholismo(String alcoholismo) {
		this.alcoholismo = alcoholismo;
	}

	public String getAlcoholismoFrecuencia() {
		return alcoholismoFrecuencia;
	}

	public void setAlcoholismoFrecuencia(String alcoholismoFrecuencia) {
		this.alcoholismoFrecuencia = alcoholismoFrecuencia;
	}

	public String getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	public String getEjercicioFrecuencia() {
		return ejercicioFrecuencia;
	}

	public void setEjercicioFrecuencia(String ejercicioFrecuencia) {
		this.ejercicioFrecuencia = ejercicioFrecuencia;
	}

	public String getHipertensionFamiliar() {
		return hipertensionFamiliar;
	}

	public void setHipertensionFamiliar(String hipertensionFamiliar) {
		this.hipertensionFamiliar = hipertensionFamiliar;
	}

	public String getDiabetesFamiliar() {
		return diabetesFamiliar;
	}

	public void setDiabetesFamiliar(String diabetesFamiliar) {
		this.diabetesFamiliar = diabetesFamiliar;
	}

	public String getHipertiroidismoFamiliar() {
		return hipertiroidismoFamiliar;
	}

	public void setHipertiroidismoFamiliar(String hipertiroidismoFamiliar) {
		this.hipertiroidismoFamiliar = hipertiroidismoFamiliar;
	}

	public String getCancerFamiliar() {
		return cancerFamiliar;
	}

	public void setCancerFamiliar(String cancerFamiliar) {
		this.cancerFamiliar = cancerFamiliar;
	}

	public String getInfartoFamiliar() {
		return infartoFamiliar;
	}

	public void setInfartoFamiliar(String infartoFamiliar) {
		this.infartoFamiliar = infartoFamiliar;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Antecedente [idPaciente=");
		builder.append(idPaciente);
		builder.append(", hipertension=");
		builder.append(hipertension);
		builder.append(", diabetes=");
		builder.append(diabetes);
		builder.append(", hipertiroidismo=");
		builder.append(hipertiroidismo);
		builder.append(", tabaquismo=");
		builder.append(tabaquismo);
		builder.append(", tabaquismoFrecuencia=");
		builder.append(tabaquismoFrecuencia);
		builder.append(", alcoholismo=");
		builder.append(alcoholismo);
		builder.append(", alcoholismoFrecuencia=");
		builder.append(alcoholismoFrecuencia);
		builder.append(", ejercicio=");
		builder.append(ejercicio);
		builder.append(", ejercicioFrecuencia=");
		builder.append(ejercicioFrecuencia);
		builder.append(", hipertensionFamiliar=");
		builder.append(hipertensionFamiliar);
		builder.append(", diabetesFamiliar=");
		builder.append(diabetesFamiliar);
		builder.append(", hipertiroidismoFamiliar=");
		builder.append(hipertiroidismoFamiliar);
		builder.append(", cancerFamiliar=");
		builder.append(cancerFamiliar);
		builder.append(", infartoFamiliar=");
		builder.append(infartoFamiliar);
		builder.append("]");
		return builder.toString();
	}
}
