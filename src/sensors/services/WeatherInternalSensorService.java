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
	private static final Logger logger = LogManager.getLogger(EmbeddedApp.class);
	
	/**
	 * Constants
	 */
	private static final String SENSOR_NAME = "Internal Weather Sensor";
	
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
	}
	
	/**
	 * Method to get and parse data into WeatherSensor object
	 */
	public WeatherSensor getSensor() {
		inSensor.setHumidity(mapper.getMap().get("humidity"));
		inSensor.setTemperature(mapper.getMap().get("temperature"));
		inSensor.setPressure(mapper.getMap().get("pressure"));
		inSensor.setDate(mapper.getDateAsString());
		return inSensor;
	}
	
	public String getSensorName() {
		return WeatherInternalSensorService.SENSOR_NAME;
	}
}
