package connectors.airLyConnector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import connectors.AbstractHandler;
import connectors.models.Response;

@Component
public class AirLyHandler extends AbstractHandler {
	
	/**
	 * Constants
	 */
	private static final String CURRENT_KEY = "current";
	private static final String VALUES_KEY = "values";
	private static final String INDEXES_KEY = "indexes";
	private static final String STANDARDS_KEY = "standards";
	private static final String VALUE_KEY = "value";
	private static final String COLOR_KEY = "color";
	private static final String PERCENT_KEY = "percent";
	private static final int CAQI_POS = 0;
	private static final int PM1_POS = 0;
	private static final int PM25_PERCENT_POS = 0;
	private static final int PM10_PERCENT_POS = 1;
	private static final int PM25_POS = 1;
	private static final int PM10_POS = 2;
	private static final int PRESSURE_POS = 3;
	private static final int HUMIDITY_POS = 4;
	private static final int TEMPERATURE_POS = 5;
		
	/**
	 * Variables
	 */
	private String caqi;
	private String caqiColor;
	private String pm1;
	private String pm10;
	private String pm10percent;
	private String pm25;
	private String pm25percent;
	private String temperature;
	private String humidity;
	private String pressure;
	
	public AirLyHandler() {
		caqi = "0";
		caqiColor = "0";
		pm1 = "0";
		pm10 = "0";
		pm10percent = "0";
		pm25 = "0";
		pm25percent = "0";
		temperature = "0";
		humidity = "0";
		pressure = "0";
	}
	
	protected void parser(Response response) throws JSONException {
		JSONObject jsonObject = new JSONObject(response.getBody()).getJSONObject(CURRENT_KEY);
		JSONArray jsonArrayValues = jsonObject.getJSONArray(VALUES_KEY);
		JSONArray jsonArrayIndexes = jsonObject.getJSONArray(INDEXES_KEY);
		JSONArray jsonArrayStandards = jsonObject.getJSONArray(STANDARDS_KEY);
		pm1 = String.format("%.0f", jsonArrayValues.getJSONObject(PM1_POS).getDouble(VALUE_KEY));
		pm10 = String.format("%.0f", jsonArrayValues.getJSONObject(PM10_POS).getDouble(VALUE_KEY));
		pm25 = String.format("%.0f", jsonArrayValues.getJSONObject(PM25_POS).getDouble(VALUE_KEY));
		caqi = String.format("%.0f", jsonArrayIndexes.getJSONObject(CAQI_POS).getDouble(VALUE_KEY));
		caqiColor = jsonArrayIndexes.getJSONObject(CAQI_POS).getString(COLOR_KEY);
		pm10percent = String.format("%.0f", jsonArrayStandards.getJSONObject(PM10_PERCENT_POS).getDouble(PERCENT_KEY));
		pm25percent = String.format("%.0f", jsonArrayStandards.getJSONObject(PM25_PERCENT_POS).getDouble(PERCENT_KEY));
		humidity = String.format("%.0f", jsonArrayValues.getJSONObject(HUMIDITY_POS).getDouble(VALUE_KEY));
		pressure = String.format("%.0f", jsonArrayValues.getJSONObject(PRESSURE_POS).getDouble(VALUE_KEY));
		temperature = String.format("%.0f", jsonArrayValues.getJSONObject(TEMPERATURE_POS).getDouble(VALUE_KEY));
	}

	public String getCaqi() {
		return caqi;
	}

	public String getCaqiColor() {
		return caqiColor;
	}

	public String getPm1() {
		return pm1;
	}

	public String getPm10() {
		return pm10;
	}

	public String getPm10percent() {
		return pm10percent;
	}

	public String getPm25() {
		return pm25;
	}

	public String getPm25percent() {
		return pm25percent;
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
