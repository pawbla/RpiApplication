package connectors.sunRiseSetConnector;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import connectors.AbstractRestConnector;
import connectors.RequestBuilder;
import connectors.models.Request;

/**
 * Connector to fetch datas from sun rise service
 * using SunRise API
 * @author blach
 *
 */
@Component
@Qualifier("sunRiseSet")
public class SunRiseSetConnector extends AbstractRestConnector {
	
	public SunRiseSetConnector(@Value("${custom.ipSunSetRise}") String url) {
		Request request = new RequestBuilder()
				.setURL(url)
				.setHttpMethod(HttpMethod.GET)
				.addContentType(MediaType.APPLICATION_JSON)
				.build();
		this.setRequest(request);
	}

}
