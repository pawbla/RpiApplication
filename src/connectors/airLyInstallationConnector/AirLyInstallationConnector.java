package connectors.airLyInstallationConnector;

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
//@Qualifier("airLyInstallationConnector")
public class AirLyInstallationConnector extends AbstractRestConnector {
	
	private static final String API_KEY_NAME = "apikey";
	private static final String ACCEPTED_LANG_KEY = "Accept-Language";
	private static final String ACCEPTED_LANG_VALUE = "pl";
	
	public AirLyInstallationConnector(@Value("${custom.ipAirLyInstallation}") String url, @Value("${custom.apiKeyAirLy}") String apiKey) {
		Request request = new RequestBuilder()
				.setURL(url)
				.setHttpMethod(HttpMethod.GET)
				.addContentType(MediaType.APPLICATION_JSON)
				.addHeader(API_KEY_NAME, apiKey)
				.addHeader(ACCEPTED_LANG_KEY, ACCEPTED_LANG_VALUE)
				.build();
		this.setRequest(request);
	}
}
