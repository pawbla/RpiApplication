package sensors.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import sensors.handler.SensorsHandlerInterface;
import sensors.objects.WeatherSensor;

public class WeatherInternalSensorService extends AbstractSensorInterface<WeatherSensor>  {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	private static final String SENSOR_NAME = "Internal Weather Sensor";
	private static final String TEMPERATURE_SENSOR_KEY = "Temperature";
	private static final String HUMIDITY_SENSOR_KEY = "Humidity";
	private static final String PRESSURE_SENSOR_KEY = "Pressure";
	
	/**
	 * Variables' declarations
	 */
	private WeatherSensor inSensor;
	private HttpHeaders headers;
	
	@Value("${custom.intSensorPassword}")
	private String pass;
	
	/**
	 * Constructor
	 * @param ip of service
	 */
	public WeatherInternalSensorService(String ip, SensorsHandlerInterface sensorHandler) {
		super(ip, sensorHandler);
		logger.info("Create " + WeatherInternalSensorService.SENSOR_NAME + " object with IP: " + ip);
		inSensor = new WeatherSensor();
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authentication", "04122018pass");
	}
	
	/**
	 * Method to get and parse data into WeatherSensor object
	 */
	public WeatherSensor getSensor() {
		logger.debug("Prepare sensor data for " + this.getSensorName());
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			inSensor.setHumidity(mapper.getJSONObject().getString(HUMIDITY_SENSOR_KEY));
			inSensor.setTemperature(mapper.getJSONObject().getString(TEMPERATURE_SENSOR_KEY));
			inSensor.setPressure(mapper.getJSONObject().getString(PRESSURE_SENSOR_KEY));
			inSensor.setDate(mapper.getDateAsString());
		}
		inSensor.setStatusCode(mapper.getResponseCode());
		logger.debug("Fetched: " + mapper.getResponseCode());
		return inSensor;
	}
	
	public String getSensorName() {
		return WeatherInternalSensorService.SENSOR_NAME;
	}

	@Override
	public HttpHeaders getHeader() {
		return headers;
	}
}
