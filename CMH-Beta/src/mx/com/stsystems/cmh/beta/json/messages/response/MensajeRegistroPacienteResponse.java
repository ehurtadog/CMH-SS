package mx.com.stsystems.cmh.beta.json.messages.response;

import java.io.Serializable;

public class MensajeRegistroPacienteResponse implements Serializable {
	private static final long serialVersionUID = -7185453463930881204L;

	private int estatus;
	private String descripcion;
	private String comentarios;
	
	private long idFiliacion;

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public long getIdFiliacion() {
		return idFiliacion;
	}

	public void setIdFiliacion(long idFiliacion) {
		this.idFiliacion = idFiliacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MensajeRegistroPacienteResponse [estatus=");
		builder.append(estatus);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", comentarios=");
		builder.append(comentarios);
		builder.append(", idFiliacion=");
		builder.append(idFiliacion);
		builder.append("]");
		return builder.toString();
	}
}
