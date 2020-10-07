package connectors.internalConnector;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.Values;
import connectors.models.Response;
import connectors.parser.AbstractParser;

@Component
@Qualifier("internal")
public class InternalParser extends AbstractParser {
	
	public enum InternalValues implements Values {
		
		TEMPERATURE("temperature"),
		HUMIDITY("humidity"),
		PRESSURE("pressure");

		public final String value;
		
		private InternalValues(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}

	/**
	 * Constants
	 */
	private static final String TEMPERATURE_SENSOR_KEY = "Temperature";
	private static final String HUMIDITY_SENSOR_KEY = "Humidity";
	private static final String PRESSURE_SENSOR_KEY = "Pressure";
	
	@Override
	public void parse(Response response) throws JSONException {
		JSONObject jsonObject = new JSONObject(response.getBody());
		this.addParsed(InternalValues.HUMIDITY, jsonObject.getString(HUMIDITY_SENSOR_KEY));
		this.addParsed(InternalValues.TEMPERATURE, jsonObject.getString(TEMPERATURE_SENSOR_KEY));
		this.addParsed(InternalValues.PRESSURE, jsonObject.getString(PRESSURE_SENSOR_KEY));
	}
}
