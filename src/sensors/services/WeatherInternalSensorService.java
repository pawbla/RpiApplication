package sensors.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import homeSystem.EmbeddedApp;
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
	private static final String TEMPERATURE_SENSOR_KEY = "temperature";
	private static final String HUMIDITY_SENSOR_KEY = "humidity";
	private static final String PRESSURE_SENSOR_KEY = "pressure";
	
	/**
	 * Variables' declarations
	 */
	private WeatherSensor inSensor;
	
	/**
	 * Constructor
	 * @param ip of service
	 */
	public WeatherInternalSensorService(String ip, SensorsHandlerInterface sensorHandler) {
		super(ip, sensorHandler);
		logger.info("Create " + WeatherInternalSensorService.SENSOR_NAME + " object with IP: " + ip);
		inSensor = new WeatherSensor();
		/* Set initial values */
		inSensor.setHumidity("--.-");
		inSensor.setTemperature("--.-");
		inSensor.setPressure("--.-");
		inSensor.setDate("----");
	}
	
	/**
	 * Method to get and parse data into WeatherSensor object
	 */
	public WeatherSensor getSensor() {
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			inSensor.setHumidity(mapper.getJSONObject().getString(HUMIDITY_SENSOR_KEY));
			inSensor.setTemperature(mapper.getJSONObject().getString(TEMPERATURE_SENSOR_KEY));
			inSensor.setPressure(mapper.getJSONObject().getString(PRESSURE_SENSOR_KEY));
			inSensor.setDate(mapper.getDateAsString());
		}
		inSensor.setStatusCode(mapper.getResponseCode());
		return inSensor;
	}
	
	public String getSensorName() {
		return WeatherInternalSensorService.SENSOR_NAME;
	}
}
