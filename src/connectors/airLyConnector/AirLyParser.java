package connectors.airLyConnector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.Values;
import connectors.airLyConnector.AirLyParser.AirLyValues;
import connectors.models.Response;
import connectors.parser.AbstractParser;

@Component
@Qualifier("AirLy")
public class AirLyParser extends AbstractParser {
	
	public enum AirLyValues implements Values {
		
		TEMPERATURE("temperature"),
		HUMIDITY("humidity"),
		PRESSURE("pressure"),
		PM_1("pm1"),
		PM_10("pm10"),
		PM_25("pm25"),
		CAQI("caqi"),
		CAQI_COLOR("caqiColor"),
		PM_10_PERCENT("pm10percent"),
		PM_25_PERCENT("pm25percent");
		

		public final String value;
		
		private AirLyValues(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
		
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
		
	@Override
	public void parse (Response response) throws JSONException {
		JSONObject jsonObject = new JSONObject(response.getBody()).getJSONObject(CURRENT_KEY);
		JSONArray jsonArrayValues = jsonObject.getJSONArray(VALUES_KEY);
		JSONArray jsonArrayIndexes = jsonObject.getJSONArray(INDEXES_KEY);
		JSONArray jsonArrayStandards = jsonObject.getJSONArray(STANDARDS_KEY);
		this.addParsed(AirLyValues.PM_1, String.format("%.0f", jsonArrayValues.getJSONObject(PM1_POS).getDouble(VALUE_KEY)));
		this.addParsed(AirLyValues.PM_10, String.format("%.0f", jsonArrayValues.getJSONObject(PM10_POS).getDouble(VALUE_KEY)));
		this.addParsed(AirLyValues.PM_25, String.format("%.0f", jsonArrayValues.getJSONObject(PM25_POS).getDouble(VALUE_KEY)));
		this.addParsed(AirLyValues.CAQI, String.format("%.0f", jsonArrayIndexes.getJSONObject(CAQI_POS).getDouble(VALUE_KEY)));
		this.addParsed(AirLyValues.CAQI_COLOR, jsonArrayIndexes.getJSONObject(CAQI_POS).getString(COLOR_KEY));
		this.addParsed(AirLyValues.PM_10_PERCENT, String.format("%.0f", jsonArrayStandards.getJSONObject(PM10_PERCENT_POS).getDouble(PERCENT_KEY)));
		this.addParsed(AirLyValues.PM_25_PERCENT, String.format("%.0f", jsonArrayStandards.getJSONObject(PM25_PERCENT_POS).getDouble(PERCENT_KEY)));
		this.addParsed(AirLyValues.HUMIDITY, String.format("%.0f", jsonArrayValues.getJSONObject(HUMIDITY_POS).getDouble(VALUE_KEY)));
		this.addParsed(AirLyValues.PRESSURE, String.format("%.0f", jsonArrayValues.getJSONObject(PRESSURE_POS).getDouble(VALUE_KEY)));
		this.addParsed(AirLyValues.TEMPERATURE, String.format("%.0f", jsonArrayValues.getJSONObject(TEMPERATURE_POS).getDouble(VALUE_KEY)));
	}

}
