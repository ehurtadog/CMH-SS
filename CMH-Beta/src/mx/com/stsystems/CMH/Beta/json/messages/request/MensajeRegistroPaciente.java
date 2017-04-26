package mx.com.stsystems.CMH.Beta.json.messages.request;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

import mx.com.stsystems.CMH.Beta.dto.Antecedente;
import mx.com.stsystems.CMH.Beta.dto.Paciente;

public class MensajeRegistroPaciente implements Serializable {
	private static final long serialVersionUID = -7609391023543295988L;

	private String numCliente;
	private String password;
	private String nombresCliente;
	private String apellidosCliente;
	private String correo;
	private String genero;
	private int tseguro;
	private Date fechaNacimiento;
	private float peso;
	private float altura;
	private String hipertension;
	private String diabetes;
	private String hipertiroidismo;
	private String fumas;
	private int frecuenciaFuma;
	private String tomAlcohol;
	private int frecuenciaTomAlcohol;
	private String ejercicio;
	private int frecuenciaEjercicio;
	private String hipertensionFam;
	private String diabetesFam;
	private String hipertiroidismoFam;
	private String cancerFam;
	private String infartoFam;
	private int tipoAcceso;
	
	public MensajeRegistroPaciente() {
	}
	
	public MensajeRegistroPaciente(Paciente paciente, Antecedente antecedente) {
		this.numCliente = String.valueOf(paciente.getIdFiliacion());
		this.password = "SIN PASSWORD";
		this.nombresCliente = paciente.getNombres();
		this.apellidosCliente = paciente.getApellidoPaterno() + " " + paciente.getApellidoMaterno();
		this.correo = paciente.getCorreoElectronico();
		this.genero = paciente.getSexo();
		this.tseguro = 0;
		this.fechaNacimiento = paciente.getFechaNacimiento();
		this.peso = paciente.getPeso();
		this.altura = paciente.getAltura();
		this.hipertension = antecedente.getHipertension();
		this.diabetes = antecedente.getDiabetes();
		this.hipertiroidismo = antecedente.getHipertiroidismo();
		this.fumas = antecedente.getTabaquismo();
		this.frecuenciaFuma = antecedente.getTabaquismoFrecuencia();
		this.tomAlcohol = antecedente.getAlcoholismo();
		this.frecuenciaTomAlcohol = antecedente.getAlcoholismoFrecuencia();
		this.ejercicio = antecedente.getEjercicio();
		this.frecuenciaEjercicio = antecedente.getEjercicioFrecuencia();
		this.hipertensionFam = antecedente.getHipertensionFamiliar();
		this.diabetesFam = antecedente.getDiabetesFamiliar();
		this.hipertiroidismoFam = antecedente.getHipertiroidismoFamiliar();
		this.cancerFam = antecedente.getCancerFamiliar();
		this.infartoFam = antecedente.getInfartoFamiliar();
		this.tipoAcceso = 0;
	}

	public String getNumCliente() {
		return this.numCliente;
	}

	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombresCliente() {
		return this.nombresCliente;
	}

	public void setNombresCliente(String nombresCliente) {
		this.nombresCliente = nombresCliente;
	}

	public String getApellidosCliente() {
		return this.apellidosCliente;
	}

	public void setApellidosCliente(String apellidosCliente) {
		this.apellidosCliente = apellidosCliente;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getTseguro() {
		return this.tseguro;
	}

	public void setTseguro(int tseguro) {
		this.tseguro = tseguro;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	@JsonIgnore
	public String getFechaNacimientoAsString() {
		if (this.fechaNacimiento != null) {
			return new SimpleDateFormat("dd-MM-YYYY").format(this.fechaNacimiento);
		} else {
			return null;
		}
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public float getPeso() {
		return this.peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return this.altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public String getHipertension() {
		return this.hipertension;
	}

	public void setHipertension(String hipertension) {
		this.hipertension = hipertension;
	}

	public String getDiabetes() {
		return this.diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	public String getHipertiroidismo() {
		return this.hipertiroidismo;
	}

	public void setHipertiroidismo(String hipertiroidismo) {
		this.hipertiroidismo = hipertiroidismo;
	}

	public String getFumas() {
		return this.fumas;
	}

	public void setFumas(String fumas) {
		this.fumas = fumas;
	}

	public int getFrecuenciaFuma() {
		return this.frecuenciaFuma;
	}

	public void setFrecuenciaFuma(int frecuenciaFuma) {
		this.frecuenciaFuma = frecuenciaFuma;
	}

	public String getTomAlcohol() {
		return this.tomAlcohol;
	}

	public void setTomAlcohol(String tomAlcohol) {
		this.tomAlcohol = tomAlcohol;
	}

	public int getFrecuenciaTomAlcohol() {
		return this.frecuenciaTomAlcohol;
	}

	public void setFrecuenciaTomAlcohol(int frecuenciaTomAlcohol) {
		this.frecuenciaTomAlcohol = frecuenciaTomAlcohol;
	}

	public String getEjercicio() {
		return this.ejercicio;
	}

	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	public int getFrecuenciaEjercicio() {
		return this.frecuenciaEjercicio;
	}

	public void setFrecuenciaEjercicio(int frecuenciaEjercicio) {
		this.frecuenciaEjercicio = frecuenciaEjercicio;
	}

	public String getHipertensionFam() {
		return this.hipertensionFam;
	}

	public void setHipertensionFam(String hipertensionFam) {
		this.hipertensionFam = hipertensionFam;
	}

	public String getDiabetesFam() {
		return this.diabetesFam;
	}

	public void setDiabetesFam(String diabetesFam) {
		this.diabetesFam = diabetesFam;
	}

	public String getHipertiroidismoFam() {
		return this.hipertiroidismoFam;
	}

	public void setHipertiroidismoFam(String hipertiroidismoFam) {
		this.hipertiroidismoFam = hipertiroidismoFam;
	}

	public String getCancerFam() {
		return this.cancerFam;
	}

	public void setCancerFam(String cancerFam) {
		this.cancerFam = cancerFam;
	}

	public String getInfartoFam() {
		return this.infartoFam;
	}

	public void setInfartoFam(String infartoFam) {
		this.infartoFam = infartoFam;
	}

	public int getTipoAcceso() {
		return this.tipoAcceso;
	}

	public void setTipoAcceso(int tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}

	@Override
	public String toString() {
		return "MensajeRegistroUsuario [numCliente=" + numCliente + ", password=" + password + ", nombresCliente="
				+ nombresCliente + ", \napellidosCliente=" + apellidosCliente + ", correo=" + correo + ", genero="
				+ genero + ", \ntseguro=" + tseguro + ", fechaNacimiento=" + getFechaNacimientoAsString() + ", peso=" + peso
				+ ", \naltura=" + altura + ", hipertension=" + hipertension + ", diabetes=" + diabetes
				+ ", \nhipertiroidismo=" + hipertiroidismo + ", fumas=" + fumas + ", frecuenciaFuma=" + frecuenciaFuma
				+ ", \ntomAlcohol=" + tomAlcohol + ", frecuenciaTomAlcohol=" + frecuenciaTomAlcohol + ", ejercicio="
				+ ejercicio + ", \nfrecuenciaEjercicio=" + frecuenciaEjercicio + ", hipertensionFam=" + hipertensionFam
				+ ", diabetesFam=" + diabetesFam + ", \nhipertiroidismoFam=" + hipertiroidismoFam + ", cancerFam="
				+ cancerFam + ", infartoFam=" + infartoFam + ", \ntipoAcceso=" + tipoAcceso + "]";
	}
}
