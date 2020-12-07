package connectors.accuWeatherConnector;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import connectors.ConnectorBuilder;
import connectors.ConnectorInterface;
import connectors.RequestBuilder;
import connectors.models.Connector;
import connectors.models.Request;

/**
 * Connector to fetch datas from AccuWeather service
 * using AccuWeather API
 * @author blach
 *
 */
@Component
@Qualifier("accuWeather")
public class AccuWeatherConnector implements ConnectorInterface {
	
	/* Connector values */
	private static final String NAME = "accuweather";
	private static final String PROVIDER = "AccuWeather";
	private static final String LINK = "https://www.accuweather.com/";
	
	/*Request values */
	private static final String ACCEPTED_LANG_KEY = "Accept-Language";
	private static final String ACCEPTED_LANG_VALUE = "pl-PL";
	
	private Connector connector;
	
	public AccuWeatherConnector(@Value("${custom.urlAccuWeather}") String url) {
		this.connector = this.buildConnector(this.buildRequest(url));
	}

	@Override
	public Connector getConnector() {
		return connector;
	}
	
	private Request buildRequest(String url) {
		Request request = new RequestBuilder()
				.setURL(url)
				.setHttpMethod(HttpMethod.GET)
				.addContentType(MediaType.APPLICATION_JSON)
				.addHeader(ACCEPTED_LANG_KEY, ACCEPTED_LANG_VALUE)
				.build();
		return request;
	}
	
	private Connector buildConnector(Request request) {
		Connector connector = new ConnectorBuilder()
				.addName(NAME)
				.addProvider(PROVIDER)
				.addLinkToProvider(LINK)
				.addRequest(request)
				.build();
		return connector;
	}

}
