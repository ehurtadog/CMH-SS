package mx.com.stsystems.CMH.Beta.dto;

import java.io.Serializable;

public class CodigoPostal implements Serializable {
	private static final long serialVersionUID = -2383406365170447949L;

	private String codigoPostal;
	private String asentamiento;
	private String tipoAsentamiento;
	private String municipio;
	private String estado;
	private String ciudad;
    private String codigoPostalAdministracion;
    private String idEstado;
    private String idTipoAsentamiento;
    private String idMunicipio;
    private String idAsentamiento;
    private String zonaAsentamiento;
    private String idCiudad;

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getAsentamiento() {
		return asentamiento;
	}

	public void setAsentamiento(String asentamiento) {
		this.asentamiento = asentamiento;
	}

	public String getTipoAsentamiento() {
		return tipoAsentamiento;
	}

	public void setTipoAsentamiento(String tipoAsentamiento) {
		this.tipoAsentamiento = tipoAsentamiento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostalAdministracion() {
		return codigoPostalAdministracion;
	}

	public void setCodigoPostalAdministracion(String codigoPostalAdministracion) {
		this.codigoPostalAdministracion = codigoPostalAdministracion;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getIdTipoAsentamiento() {
		return idTipoAsentamiento;
	}

	public void setIdTipoAsentamiento(String idTipoAsentamiento) {
		this.idTipoAsentamiento = idTipoAsentamiento;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdAsentamiento() {
		return idAsentamiento;
	}

	public void setIdAsentamiento(String idAsentamiento) {
		this.idAsentamiento = idAsentamiento;
	}

	public String getZonaAsentamiento() {
		return zonaAsentamiento;
	}

	public void setZonaAsentamiento(String zonaAsentamiento) {
		this.zonaAsentamiento = zonaAsentamiento;
	}

	public String getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(String idCiudad) {
		this.idCiudad = idCiudad;
	}

	@Override
	public String toString() {
		return "CodigoPostal [codigoPostal=" + codigoPostal + ", asentamiento=" + asentamiento + ", tipoAsentamiento="
				+ tipoAsentamiento + ", municipio=" + municipio + ", estado=" + estado + ", ciudad=" + ciudad
				+ ", codigoPostalAdministracion=" + codigoPostalAdministracion + ", idEstado=" + idEstado
				+ ", idTipoAsentamiento=" + idTipoAsentamiento + ", idMunicipio=" + idMunicipio + ", idAsentamiento="
				+ idAsentamiento + ", zonaAsentamiento=" + zonaAsentamiento + ", idCiudad=" + idCiudad + "]";
	}
}
