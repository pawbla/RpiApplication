package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import sensors.handler.SensorsHandlerInterface;
import sensors.objects.Sensor;
import sensors.objects.WeatherSensor;
import sensors.services.AbstractHandledSensorInterface;

/*
 * Implemented but not used due to using of AirLy
 */
public class OpenWeatherMapSensorService extends AbstractHandledSensorInterface {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	private static final int TIMEOUT = 60;
	private static final String SENSOR_NAME = "OpenWeatherMap";
	private static final String OBJECT_SENSOR_KEY = "main";
	private static final String TEMPERATURE_SENSOR_KEY = "temp";
	private static final String HUMIDITY_SENSOR_KEY = "humidity";
	/**
	 * Variables' declarations
	 */
	private HttpHeaders headers;
	private HttpEntity<Object> entity;
	
	/**
	 * Constructor
	 */
	public OpenWeatherMapSensorService(String ip, SensorsHandlerInterface sensorHandler) {
		super(ip, sensorHandler, SENSOR_NAME, TIMEOUT);
		logger.info("Create " + OpenWeatherMapSensorService.SENSOR_NAME + " object with IP: " + ip);
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    this.entity = new HttpEntity<Object>(headers);
	}
	
	public <T extends Sensor> T getSensor(T extSensor) {
		logger.debug("Prepare sensor data for " + this.getSensorName());
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			((WeatherSensor)extSensor).setHumidity(String.format("%d", mapper.getJSONObject().getJSONObject(OBJECT_SENSOR_KEY).getInt(HUMIDITY_SENSOR_KEY)));
			/* Get double value in Kelvin and convert to Celcius*/
			((WeatherSensor)extSensor).setTemperature(String.format("%.0f", mapper.getJSONObject().getJSONObject(OBJECT_SENSOR_KEY).getDouble(TEMPERATURE_SENSOR_KEY) - 273.15));
			((WeatherSensor)extSensor).setDate(mapper.getDateAsString());
		}
		extSensor.setStatusCode(mapper.getResponseCode());
		logger.debug("Fetched: " + mapper.getResponseCode());
		return extSensor;
	}
	
	public String getSensorName() {
		return OpenWeatherMapSensorService.SENSOR_NAME;
	}
	
	@Override
	public HttpHeaders getHeader() {
		return headers;
	}
	
	@Override
	public HttpEntity<Object> getEntity() {
		return this.entity;
	}
}
