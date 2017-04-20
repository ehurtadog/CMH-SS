package mx.com.stsystems.CMH.Beta.dto;

import java.io.Serializable;

public class Hospital implements Serializable {
	private static final long serialVersionUID = 5034578151108791483L;
	
	private String idHospital;
	private String descripcion;
	private String url;
	private int idAsentamiento;
	private int idMunicipio;
	private int idEstado;
	private int idCiudad;

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

	public int getIdAsentamiento() {
		return idAsentamiento;
	}

	public void setIdAsentamiento(int idAsentamiento) {
		this.idAsentamiento = idAsentamiento;
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

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	@Override
	public String toString() {
		return "Hospital [idHospital=" + idHospital + ", descripcion=" + descripcion + ", url=" + url
				+ ", idAsentamiento=" + idAsentamiento + ", idMunicipio=" + idMunicipio + ", idEstado=" + idEstado
				+ ", idCiudad=" + idCiudad + "]";
	}
}
