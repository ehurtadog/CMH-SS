package mx.com.stsystems.cmh.beta.dto;

import java.io.Serializable;

public class TipoSangre implements Serializable {
	private static final long serialVersionUID = 5302784848278863032L;

	private String idTipoSangre;
	private String descripcion;
	private int orden;

	public String getIdTipoSangre() {
		return idTipoSangre;
	}

	public void setIdTipoSangre(String idTipoSangre) {
		this.idTipoSangre = idTipoSangre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoSangre [idTipoSangre=");
		builder.append(idTipoSangre);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", orden=");
		builder.append(orden);
		builder.append("]");
		return builder.toString();
	}
}
