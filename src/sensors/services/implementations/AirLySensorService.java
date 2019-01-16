package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import sensors.handler.SensorsHandlerInterface;
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
	private static final int TIMEOUT = 120;
	private static final String SENSOR_NAME = "AirLy Service sensor";
	private static final String API_KEY_NAME = "apikey";
	private static final String POWERED_BY = "Powered by airly.eu";
	private static final String CURRENT_KEY = "current";
	private static final String VALUES_KEY = "values";
	private static final String VALUE_KEY = "value";
	private static final int TEMPERATURE_POS = 5;
	private static final int HUMIDITY_POS = 4;
	private static final int PRESSURE_POS = 3;
	
	/**
	 * Variables' declarations
	 */
	private WeatherSensor sensor;
	private HttpHeaders headers;
	private HttpEntity<Object> entity;
	
	/**
	 * Constructor
	 * @param ip
	 * @param sensorHandler
	 */
	public AirLySensorService(String ip, SensorsHandlerInterface sensorHandler, String apiKey) {
		super(ip, sensorHandler, SENSOR_NAME, TIMEOUT);
		logger.info("Create " + AirLySensorService.SENSOR_NAME + " object with IP: " + ip);
		sensor = new WeatherSensor();
		sensor.setPoweredBy(POWERED_BY);
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add(API_KEY_NAME, apiKey);
	    this.entity = new HttpEntity<Object>(headers);
	}

	@Override
	public HttpHeaders getHeader() {
		return headers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public WeatherSensor getSensor() {
		logger.debug("Prepare sensor data for " + this.getSensorName());
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			JSONArray jsonArray = mapper.getJSONObject().getJSONObject(CURRENT_KEY).getJSONArray(VALUES_KEY);
			sensor.setDate(mapper.getDateAsString());
			sensor.setHumidity(String.format("%.0f", jsonArray.getJSONObject(HUMIDITY_POS).getDouble(VALUE_KEY)));
			sensor.setPressure(String.format("%.0f", jsonArray.getJSONObject(PRESSURE_POS).getDouble(VALUE_KEY)));
			sensor.setTemperature(String.format("%.0f", jsonArray.getJSONObject(TEMPERATURE_POS).getDouble(VALUE_KEY)));
			
			logger.debug(jsonArray.getJSONObject(5).getDouble("value"));
		}
		logger.debug("Fetched: " + mapper.getResponseCode());
		sensor.setStatusCode(mapper.getResponseCode());
		return sensor;
	}

	@Override
	public HttpEntity<Object> getEntity() {
		return this.entity;
	}
	
	/* CjsonArray response example
	 * {"name":"PM1","value":10.75}
	 * {"name":"PM25","value":14.42}
	 * {"name":"PM10","value":26.64}
	 * {"name":"PRESSURE","value":1004.85}
	 * {"name":"HUMIDITY","value":96.2}
	 * {"name":"TEMPERATURE","value":-0.31}
	 */
	 

}
