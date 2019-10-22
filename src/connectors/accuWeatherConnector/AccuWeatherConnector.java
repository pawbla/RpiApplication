package connectors.accuWeatherConnector;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import connectors.AbstractRestConnector;
import connectors.RequestBuilder;
import connectors.models.Request;

/**
 * Connector to fetch datas from AccuWeather service
 * using AccuWeather API
 * @author blach
 *
 */
@Component
@Qualifier("accuWeatherConnector")
public class AccuWeatherConnector extends AbstractRestConnector {
	
	public AccuWeatherConnector(@Value("${custom.urlAccuWeather}") String url) {
		Request request = new RequestBuilder()
				.setURL(url)
				.setHttpMethod(HttpMethod.GET)
				.addContentType(MediaType.APPLICATION_JSON)
				.build();
		this.setRequest(request);
	}

}
