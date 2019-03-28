package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;

import sensors.objects.AirPolutionSensor;
import sensors.objects.Sensor;
import sensors.objects.WeatherSensor;
import sensors.services.AbstractSensorInterface;

public class AirLySensorService extends AbstractSensorInterface {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	/**
	 * Constants
	 */
	private static final String SENSOR_NAME = "AirLy";
	private static final String API_KEY_NAME = "apikey";
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

	private final int TIMEOUT = 120000;
	private final int DELAY_TIMEOUT = 20000;
	
	/**
	 * Constructor
	 * @param ip
	 * @param sensorHandler
	 */
	public AirLySensorService(String ip, String apiKey) {
		super(ip, SENSOR_NAME);
		logger.info("Create " + AirLySensorService.SENSOR_NAME + " object with IP: " + ip);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add(API_KEY_NAME, apiKey);
	    this.setHeader(headers);
	}

	@Override
	public <T extends Sensor> T getSensor(T sensor) {
		logger.debug("Prepare service data for " + this.getSensorName());
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			JSONObject jsonObject = mapper.getJSONObject().getJSONObject(CURRENT_KEY);
			JSONArray jsonArrayValues = jsonObject.getJSONArray(VALUES_KEY);
			sensor.setDate(mapper.getDateAsString());
			if (sensor instanceof AirPolutionSensor) {
				JSONArray jsonArrayIndexes = jsonObject.getJSONArray(INDEXES_KEY);
				JSONArray jsonArrayStandards = jsonObject.getJSONArray(STANDARDS_KEY);
				((AirPolutionSensor)sensor).setPm1(String.format("%.0f", jsonArrayValues.getJSONObject(PM1_POS).getDouble(VALUE_KEY)));
				((AirPolutionSensor)sensor).setPm10(String.format("%.0f", jsonArrayValues.getJSONObject(PM10_POS).getDouble(VALUE_KEY)));
				((AirPolutionSensor)sensor).setPm25(String.format("%.0f", jsonArrayValues.getJSONObject(PM25_POS).getDouble(VALUE_KEY)));
				((AirPolutionSensor)sensor).setCaqi(String.format("%.0f", jsonArrayIndexes.getJSONObject(CAQI_POS).getDouble(VALUE_KEY)));
				((AirPolutionSensor)sensor).setCaqiColor(jsonArrayIndexes.getJSONObject(CAQI_POS).getString(COLOR_KEY));
				((AirPolutionSensor)sensor).setPm10percent(String.format("%.0f", jsonArrayStandards.getJSONObject(PM10_PERCENT_POS).getDouble(PERCENT_KEY)));
				((AirPolutionSensor)sensor).setPm25percent(String.format("%.0f", jsonArrayStandards.getJSONObject(PM25_PERCENT_POS).getDouble(PERCENT_KEY)));
			} else {
				((WeatherSensor)sensor).setHumidity(String.format("%.0f", jsonArrayValues.getJSONObject(HUMIDITY_POS).getDouble(VALUE_KEY)));
				((WeatherSensor)sensor).setPressure(String.format("%.0f", jsonArrayValues.getJSONObject(PRESSURE_POS).getDouble(VALUE_KEY)));
				((WeatherSensor)sensor).setTemperature(String.format("%.0f", jsonArrayValues.getJSONObject(TEMPERATURE_POS).getDouble(VALUE_KEY)));
			}

		}
		logger.debug("Fetched: " + mapper.getResponseCode());
		sensor.setStatusCode(mapper.getResponseCode());
		return sensor;
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = TIMEOUT, initialDelay = DELAY_TIMEOUT)
	private void fetchDatas() {		
		this.getRestData();
	}

}
