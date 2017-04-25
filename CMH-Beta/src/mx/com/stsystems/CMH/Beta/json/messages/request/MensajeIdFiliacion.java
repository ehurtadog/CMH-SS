package mx.com.stsystems.CMH.Beta.json.messages.request;

import java.io.Serializable;

public class MensajeIdFiliacion implements Serializable {
	private static final long serialVersionUID = 3690135300193491126L;

	private long idFiliacion;

	public long getIdFiliacion() {
		return idFiliacion;
	}

	public void setIdFiliacion(long idFiliacion) {
		this.idFiliacion = idFiliacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MensajeIdFiliacion [idFiliacion=");
		builder.append(idFiliacion);
		builder.append("]");
		return builder.toString();
	}
}
