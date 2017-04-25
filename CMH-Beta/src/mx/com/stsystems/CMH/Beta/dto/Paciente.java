package mx.com.stsystems.CMH.Beta.dto;

import java.io.Serializable;
import java.util.Date;

import mx.com.stsystems.CMH.Beta.exceptions.SumarSaludException;
import mx.com.stsystems.CMH.Beta.json.messages.request.MensajeRegistroPaciente;
import mx.com.stsystems.CMH.Beta.util.Global;
import mx.com.stsystems.CMH.Beta.web.controller.service.ServiceController;
import mx.com.stsystems.CMH.Beta.web.controller.service.impl.ServiceControllerImpl;

public class Paciente implements Serializable {
	private static final long serialVersionUID = -4851381970808864769L;

	private String idPaciente;
	private long idFiliacion;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String sexo;
	private Date fechaNacimiento;
	private float peso;
	private float altura;
	private String correoElectronico;
	private String idEstadoCivil;
	private String idTipoSangre;
	
	public Paciente() {
		
	}
	
	public Paciente(MensajeRegistroPaciente mensaje) {
		ServiceController serviceController = new ServiceControllerImpl();
		
		this.idPaciente = Global.getUUID();
		this.idFiliacion = 0;
		this.nombres = mensaje.getNombresCliente();
		this.apellidoPaterno = mensaje.getApellidosCliente().split("[ ]+")[0];
		this.apellidoMaterno = mensaje.getApellidosCliente().split("[ ]+")[1];
		this.sexo = mensaje.getGenero();
		this.fechaNacimiento = mensaje.getFechaNacimiento();
		this.peso = mensaje.getPeso();
		this.altura = mensaje.getAltura();
		this.correoElectronico = mensaje.getCorreo();
		
		try {
			this.idEstadoCivil = serviceController.solicitaEstadoCivilPorDescripcion("SIN ESTADO CIVIL").getIdEstadoCivil();
		} catch (SumarSaludException sse) {
			this.idEstadoCivil = "7BAE03972A48DD59870C3FDA44B265FF";
		}
		
		this.idTipoSangre = "D6DCB896498918D2F006564303FE0C14";
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public long getIdFiliacion() {
		return idFiliacion;
	}

	public void setIdFiliacion(long idFiliacion) {
		this.idFiliacion = idFiliacion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(String idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public String getIdTipoSangre() {
		return idTipoSangre;
	}

	public void setIdTipoSangre(String idTipoSangre) {
		this.idTipoSangre = idTipoSangre;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paciente [idPaciente=");
		builder.append(idPaciente);
		builder.append(", idFiliacion=");
		builder.append(idFiliacion);
		builder.append(", nombres=");
		builder.append(nombres);
		builder.append(", apellidoPaterno=");
		builder.append(apellidoPaterno);
		builder.append(", apellidoMaterno=");
		builder.append(apellidoMaterno);
		builder.append(", sexo=");
		builder.append(sexo);
		builder.append(", fechaNacimiento=");
		builder.append(fechaNacimiento);
		builder.append(", peso=");
		builder.append(peso);
		builder.append(", altura=");
		builder.append(altura);
		builder.append(", correoElectronico=");
		builder.append(correoElectronico);
		builder.append(", idEstadoCivil=");
		builder.append(idEstadoCivil);
		builder.append(", idTipoSangre=");
		builder.append(idTipoSangre);
		builder.append("]");
		return builder.toString();
	}
}
