package mx.com.stsystems.cmh.beta.json.messages.request;

public class MensajeConsultaHospital {
	private String estado;
	private String ciudad;
	private double latitud;
	private double longitud;

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
		builder.append("MensajeConsultaHospital [estado=");
		builder.append(estado);
		builder.append(", ciudad=");
		builder.append(ciudad);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append("]");
		return builder.toString();
	}
}
