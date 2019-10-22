package connectors.internalConnector;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import connectors.AbstractHandler;
import connectors.models.Response;

@Component
public class InternalHandler extends AbstractHandler {
	
	/**
	 * Constants
	 */
	private static final String TEMPERATURE_SENSOR_KEY = "Temperature";
	private static final String HUMIDITY_SENSOR_KEY = "Humidity";
	private static final String PRESSURE_SENSOR_KEY = "Pressure";
	
	/**
	 * Variables
	 */
	private String temperature;
	private String humidity;
	private String pressure;
	
	public InternalHandler() {
		temperature = "0";
		humidity = "0";
		pressure = "0";		
	}

	@Override
	protected void parser(Response response) throws JSONException {
		JSONObject jsonObject = new JSONObject(response.getBody());
		humidity = jsonObject.getString(HUMIDITY_SENSOR_KEY);
		temperature = jsonObject.getString(TEMPERATURE_SENSOR_KEY);
		pressure = jsonObject.getString(PRESSURE_SENSOR_KEY);
	}

	public String getTemperature() {
		return temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getPressure() {
		return pressure;
	}
}
