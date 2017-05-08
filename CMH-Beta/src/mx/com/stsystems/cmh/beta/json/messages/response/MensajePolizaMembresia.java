package mx.com.stsystems.cmh.beta.json.messages.response;

import java.io.Serializable;
import java.util.List;

import mx.com.stsystems.cmh.beta.dto.Beneficio;
import mx.com.stsystems.cmh.beta.dto.Poliza;

public class MensajePolizaMembresia implements Serializable{
	private static final long serialVersionUID = 8922879204582336457L;

	private long idFiliacion;
	private Poliza poliza;
	private List<Beneficio> beneficios;
	private int estatus;
	private String mensaje;
	
	public MensajePolizaMembresia() {
//		this.poliza = new Poliza();
//		this.beneficios = new ArrayList<Beneficio>();
	}

	public long getIdFiliacion() {
		return idFiliacion;
	}

	public void setIdFiliacion(long idFiliacion) {
		this.idFiliacion = idFiliacion;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public List<Beneficio> getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios = beneficios;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MensajePolizaMembresia [idFiliacion=");
		builder.append(idFiliacion);
		builder.append(", poliza=");
		builder.append(poliza);
		builder.append(", beneficios=");
		builder.append(beneficios);
		builder.append(", estatus=");
		builder.append(estatus);
		builder.append(", mensaje=");
		builder.append(mensaje);
		builder.append("]");
		return builder.toString();
	}
}
