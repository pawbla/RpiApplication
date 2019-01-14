package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
			logger.debug(mapper.getDateAsString());
		}
		return null;
	}

	@Override
	public HttpEntity<Object> getEntity() {
		return this.entity;
	}

}
