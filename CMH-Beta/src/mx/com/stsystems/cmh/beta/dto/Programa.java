package mx.com.stsystems.cmh.beta.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Programa implements Serializable {
	private static final long serialVersionUID = -2911642033903342766L;
	
	@JsonIgnore
	private String idPrograma;
	private String descripcion;
	@JsonIgnore
	private int orden;
	@JsonIgnore
	private Timestamp fechaHoraRegistro;
	@JsonIgnore
	private Timestamp fechaHoraModificacion;
	
	@JsonIgnore
	private Timestamp fechaHoraBaja;

	public String getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(String idPrograma) {
		this.idPrograma = idPrograma;
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

	public Timestamp getFechaHoraRegistro() {
		return fechaHoraRegistro;
	}

	public void setFechaHoraRegistro(Timestamp fechaHoraRegistro) {
		this.fechaHoraRegistro = fechaHoraRegistro;
	}

	public Timestamp getFechaHoraModificacion() {
		return fechaHoraModificacion;
	}

	public void setFechaHoraModificacion(Timestamp fechaHoraModificacion) {
		this.fechaHoraModificacion = fechaHoraModificacion;
	}

	public Timestamp getFechaHoraBaja() {
		return fechaHoraBaja;
	}

	public void setFechaHoraBaja(Timestamp fechaHoraBaja) {
		this.fechaHoraBaja = fechaHoraBaja;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Programa [idPrograma=");
		builder.append(idPrograma);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", orden=");
		builder.append(orden);
		builder.append(", fechaHoraRegistro=");
		builder.append(fechaHoraRegistro);
		builder.append(", fechaHoraModificacion=");
		builder.append(fechaHoraModificacion);
		builder.append(", fechaHoraBaja=");
		builder.append(fechaHoraBaja);
		builder.append("]");
		return builder.toString();
	}
}
