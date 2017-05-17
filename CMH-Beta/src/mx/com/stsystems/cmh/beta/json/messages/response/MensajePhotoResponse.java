package mx.com.stsystems.cmh.beta.json.messages.response;

import java.io.Serializable;

public class MensajePhotoResponse implements Serializable {
	private static final long serialVersionUID = 8282196635245249905L;
	
	private String foto;
	private int estatus;
	private String mensaje;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
		builder.append("MensajePhotoResponse [foto=");
		builder.append(foto);
		builder.append(", estatus=");
		builder.append(estatus);
		builder.append(", mensaje=");
		builder.append(mensaje);
		builder.append("]");
		return builder.toString();
	}
}
