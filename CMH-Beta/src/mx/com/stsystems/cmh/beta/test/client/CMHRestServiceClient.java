package mx.com.stsystems.cmh.beta.test.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CMHRestServiceClient {
	private final static Logger LOGGER = LoggerFactory.getLogger(CMHRestServiceClient.class);
	
	public static void main(String[] args) {
		try {
			MensajeEstado mensaje = new MensajeEstado();
			mensaje.setEstado("CHIHUAHUITA");
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(mensaje);
			LOGGER.debug("VAR: jsonString: " + jsonString);
			// Step2: Now pass JSON File Data to REST Service
			try {
				URL url = new URL("https://localhost:8181/CMHBeta/rest/Music/ConsultaHospitalesPorEstado");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestProperty("Accept", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonString);
				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				while (in.readLine() != null) {
				}
				LOGGER.debug("\nREST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				LOGGER.error("\nError while calling REST Service", e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MensajeEstado {
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