package mx.com.stsystems.cmh.beta.json.messages.request;

public class MensajeEstado {
	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "MensajeEstado [estado=" + estado + "]";
	}
}
