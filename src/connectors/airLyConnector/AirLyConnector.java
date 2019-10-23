package connectors.airLyConnector;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import connectors.AbstractRestConnector;
import connectors.RequestBuilder;
import connectors.models.Request;

/**
 * Connector to fetch datas from AirLy service
 * using AirLy API
 * @author blach
 *
 */
@Component
@Qualifier("airLyConnector")
public class AirLyConnector extends AbstractRestConnector {
	
	private static final String API_KEY_NAME = "apikey";
	
	public AirLyConnector(@Value("${custom.ipAirLy}") String ip, @Value("${custom.apiKeyAirLy}") String apiKey) {
		Request request = new RequestBuilder()
				.setURL(ip)
				.setHttpMethod(HttpMethod.GET)
				.addContentType(MediaType.APPLICATION_JSON)
				.addHeader(API_KEY_NAME, apiKey)
				.build();
		this.setRequest(request);
	}
}
