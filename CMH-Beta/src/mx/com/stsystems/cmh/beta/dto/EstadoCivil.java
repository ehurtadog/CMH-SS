package mx.com.stsystems.cmh.beta.dto;

import java.io.Serializable;

public class EstadoCivil implements Serializable {
	private static final long serialVersionUID = -4759789777480202202L;

	private String idEstadoCivil;
	private int orden;
	private String descripcion;

	public String getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(String idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EstadoCivil [idEstadoCivil=");
		builder.append(idEstadoCivil);
		builder.append(", orden=");
		builder.append(orden);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append("]");
		return builder.toString();
	}
}
