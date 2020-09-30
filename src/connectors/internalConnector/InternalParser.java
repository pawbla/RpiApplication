package connectors.internalConnector;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.models.Response;
import connectors.parser.AbstractParser;

@Component
@Qualifier("internal")
public class InternalParser extends AbstractParser {

	/**
	 * Constants
	 */
	private static final String TEMPERATURE_SENSOR_KEY = "Temperature";
	private static final String HUMIDITY_SENSOR_KEY = "Humidity";
	private static final String PRESSURE_SENSOR_KEY = "Pressure";
	
	@Override
	public void parse(Response response) throws JSONException {
		JSONObject jsonObject = new JSONObject(response.getBody());
		this.addParsed("humidity", jsonObject.getString(HUMIDITY_SENSOR_KEY));
		this.addParsed("temperature", jsonObject.getString(TEMPERATURE_SENSOR_KEY));
		this.addParsed("pressure", jsonObject.getString(PRESSURE_SENSOR_KEY));
	}
}
