package sensors.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import sensors.handler.SensorsHandlerInterface;
import sensors.objects.WeatherSensor;

public class WeatherExternalSensorService extends AbstractSensorInterface<WeatherSensor> {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	private static final String SENSOR_NAME = "External Weather Sensor";
	
	/**
	 * Constructor
	 */
	public WeatherExternalSensorService(String ip, SensorsHandlerInterface sensorHandler) {
		super(ip, sensorHandler);
		logger.info("Create " + WeatherExternalSensorService.SENSOR_NAME + " object with IP: " + ip);
	}
	
	public WeatherSensor getSensor() {
		return null;
	}
	
	public String getSensorName() {
		return WeatherExternalSensorService.SENSOR_NAME;
	}
}
