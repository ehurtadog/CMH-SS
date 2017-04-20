package mx.com.stsystems.CMH.Beta.web.controller.bean.backing;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="applicationView")
@ApplicationScoped
public class ApplicationBean {
	private String urlBase = "http://localhost:8080/CMH-Beta";
	private String mensaje = "Bienvenido";
	
	public String getUrlBase() {
		return this.urlBase;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
}
