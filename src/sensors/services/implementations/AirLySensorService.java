package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import sensors.handler.SensorsHandlerInterface;
import sensors.objects.WeatherSensor;
import sensors.services.AbstractSensorInterface;

public class AirLySensorService extends AbstractSensorInterface<WeatherSensor> {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	/**
	 * Constants
	 */
	private static final String SENSOR_NAME = "AirLy Service sensor";
	private static final String API_KEY_NAME = "apikey";
	/**
	 * Variables' declarations
	 */
	private WeatherSensor sensor;
	private HttpHeaders headers;
	
	/**
	 * Constructor
	 * @param ip
	 * @param sensorHandler
	 */
	public AirLySensorService(String ip, SensorsHandlerInterface sensorHandler) {
		super(ip, sensorHandler, SENSOR_NAME);
		logger.info("Create " + AirLySensorService.SENSOR_NAME + " object with IP: " + ip);
		sensor = new WeatherSensor();
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add(API_KEY_NAME, "FcqXU0gaJvfZM5g7x93j3GzWWGgYTp76");
	}

	@Override
	public HttpHeaders getHeader() {
		return headers;
	}

	@Override
	public WeatherSensor getSensor() {
		logger.debug("Prepare sensor data for " + this.getSensorName());
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			logger.debug(mapper.getDateAsString());
		}
		return null;
	}
}
