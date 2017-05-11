package mx.com.stsystems.cmh.beta.json.messages.response;

import java.io.Serializable;
import java.util.List;

import mx.com.stsystems.cmh.beta.dto.Hospital;

public class MensajeHospitalResponse implements Serializable {
	private static final long serialVersionUID = 5683373715309948858L;

	private List<Hospital> hospitales;
	private int estatus;
	private String mensaje;

	public List<Hospital> getHospitales() {
		return hospitales;
	}

	public void setHospitales(List<Hospital> hospitales) {
		this.hospitales = hospitales;
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
		builder.append("MensajeHospitalesResponse [hospitales=");
		builder.append(hospitales);
		builder.append(", estatus=");
		builder.append(estatus);
		builder.append(", mensaje=");
		builder.append(mensaje);
		builder.append("]");
		return builder.toString();
	}
}
