package mx.com.stsystems.cmh.beta.test.client;

import java.io.FileOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import mx.com.stsystems.cmh.beta.json.messages.response.MensajePhotoResponse;

public class PhotoRestClient {
	private final static Logger LOGGER = LoggerFactory.getLogger(PhotoRestClient.class);

	public static void main(String[] args) {
		try {
			String idFiliacion = "1705000038";

			Client client = Client.create();
			WebResource webResource = client.resource("https://appsumarsalud.com/CMHBeta/rest/Consulta/Foto/" + idFiliacion);
			webResource.accept("application/json");
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, null);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String jsonRequest = response.getEntity(String.class);
			LOGGER.debug("[VAR] jsonRequest: " + jsonRequest);

			ObjectMapper mapper = new ObjectMapper();
			MensajePhotoResponse mensajePhotoResponse = mapper.readValue(jsonRequest, MensajePhotoResponse.class);

			byte[] imageByteArray = decodeImage(mensajePhotoResponse.getFoto());

			LOGGER.debug("[VAR] imageByteArray.length: " + imageByteArray.length);

			if (imageByteArray.length > 0) {
				FileOutputStream imageOutFile;
				imageOutFile = new FileOutputStream("/tmp/" + idFiliacion + ".png");
				imageOutFile.write(imageByteArray);
				imageOutFile.close();
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Connection timed out")) {
				LOGGER.error("Connection timed out.");
			} else {
				LOGGER.error("Exception: ", e);
			}
		}
	}

	public static byte[] decodeImage(String imageDataString) {
		byte[] step = Base64.decodeBase64(imageDataString);

		LOGGER.debug("[VAR] imageDataString(decodeImage): " + imageDataString);
		LOGGER.debug("[VAR] step.length: " + step.length);

		return step;
	}
}
