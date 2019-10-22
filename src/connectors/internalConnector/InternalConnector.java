package connectors.internalConnector;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import connectors.AbstractRestConnector;
import connectors.RequestBuilder;
import connectors.models.Request;

/**
 * Connector to fetch datas from internal service

 * @author blach
 *
 */
@Component
@Qualifier("internalConnector")
public class InternalConnector extends AbstractRestConnector {
	
	
	public InternalConnector (@Value("${custom.ipInternalSensor}") String url, 
			@Value("${custom.intSensorPassword}") String password) {
		Request request = new RequestBuilder()
				.setURL(url)
				.setHttpMethod(HttpMethod.GET)
				.addContentType(MediaType.APPLICATION_JSON)
				.addHeader("Authentication", password)
				.build();
		this.setRequest(request);
	}
}
