package mx.com.stsystems.CMH.Beta.dto;

public class Telefono {
	private String idTelefono;
	private String numero;
	private String descripcion;
	
	private String idTipoTelefono;

	public String getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(String idTelefono) {
		this.idTelefono = idTelefono;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdTipoTelefono() {
		return idTipoTelefono;
	}

	public void setIdTipoTelefono(String idTipoTelefono) {
		this.idTipoTelefono = idTipoTelefono;
	}

	@Override
	public String toString() {
		return "Telefono [idTelefono=" + idTelefono + ", numero=" + numero + ", descripcion=" + descripcion
				+ ", idTipoTelefono=" + idTipoTelefono + "]";
	}
}
