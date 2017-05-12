package mx.com.stsystems.cmh.beta.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Hospital implements Serializable {
	private static final long serialVersionUID = 5034578151108791483L;
	
	@JsonIgnore
	private String idHospital;
	private String descripcion;
	private String url;
	@JsonIgnore
	private int idCiudad;
	@JsonIgnore
	private int idMunicipio;
	@JsonIgnore
	private int idEstado;
	private double latitud;
	private double longitud;

	public String getIdHospital() {
		return idHospital;
	}

	public void setIdHospital(String idHospital) {
		this.idHospital = idHospital;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hospital [idHospital=");
		builder.append(idHospital);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", url=");
		builder.append(url);
		builder.append(", idCiudad=");
		builder.append(idCiudad);
		builder.append(", idMunicipio=");
		builder.append(idMunicipio);
		builder.append(", idEstado=");
		builder.append(idEstado);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append("]");
		return builder.toString();
	}

}
