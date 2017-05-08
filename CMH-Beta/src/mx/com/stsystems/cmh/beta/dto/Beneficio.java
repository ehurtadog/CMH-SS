package mx.com.stsystems.cmh.beta.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Beneficio implements Serializable {
	private static final long serialVersionUID = -569598603685542389L;
	
	@JsonIgnore
	private String idBeneficio;
	private String descripcion;
	private String valor;
	@JsonIgnore
	private int orden;
	@JsonIgnore
	private Timestamp fechaHoraRegistro;
	@JsonIgnore
	private Timestamp fechaHoraModificacion;
	@JsonIgnore
	private Timestamp fechaHoraBaja;

	public String getIdBeneficio() {
		return idBeneficio;
	}

	public void setIdBeneficio(String idBeneficio) {
		this.idBeneficio = idBeneficio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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
		builder.append("Beneficio [idBeneficio=");
		builder.append(idBeneficio);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", valor=");
		builder.append(valor);
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
