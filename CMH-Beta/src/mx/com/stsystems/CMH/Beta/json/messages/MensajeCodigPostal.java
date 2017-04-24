package mx.com.stsystems.CMH.Beta.json.messages;

public class MensajeCodigPostal {
	private String codigoPostal;

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
	public String toString() {
		return "MensajeCodigPostal [codigoPostal=" + codigoPostal + "]";
	}
}
