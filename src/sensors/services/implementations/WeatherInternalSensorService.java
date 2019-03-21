package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import sensors.handler.SensorsHandlerInterface;
import sensors.objects.Sensor;
import sensors.objects.WeatherSensor;
import sensors.services.AbstractHandledSensorInterface;
import sensors.services.AbstractSensorInterface;

public class WeatherInternalSensorService extends AbstractHandledSensorInterface {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	private static final int TIMEOUT = 20;
	private static final String SENSOR_NAME = "Internal Weather Sensor";
	private static final String TEMPERATURE_SENSOR_KEY = "Temperature";
	private static final String HUMIDITY_SENSOR_KEY = "Humidity";
	private static final String PRESSURE_SENSOR_KEY = "Pressure";
	
	/**
	 * Variables' declarations
	 */
	private HttpHeaders headers;
	private HttpEntity<Object> entity;
	
	@Value("${custom.intSensorPassword}")
	private String pass;
	
	/**
	 * Constructor
	 * @param ip of service
	 */
	public WeatherInternalSensorService(String ip, SensorsHandlerInterface sensorHandler, String password) {
		super(ip, sensorHandler, SENSOR_NAME, TIMEOUT);
		logger.info("Create " + WeatherInternalSensorService.SENSOR_NAME + " object with IP: " + ip);
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authentication", password);
	    this.entity = new HttpEntity<Object>(headers);
	}
	
	/**
	 * Method to get and parse data into WeatherSensor object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Sensor> T getSensor(T inSensor) {
		logger.debug("Prepare sensor data for " + this.getSensorName());
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			((WeatherSensor)inSensor).setHumidity(mapper.getJSONObject().getString(HUMIDITY_SENSOR_KEY));
			((WeatherSensor)inSensor).setTemperature(mapper.getJSONObject().getString(TEMPERATURE_SENSOR_KEY));
			((WeatherSensor)inSensor).setPressure(mapper.getJSONObject().getString(PRESSURE_SENSOR_KEY));
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

	@Override
	public HttpEntity<Object> getEntity() {
		return this.entity;
	}
}
