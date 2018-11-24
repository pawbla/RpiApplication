package sensors.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	@Autowired
	private SensorsHandlerInterface sensorHandler;
	
	/**
	 * Constructor
	 */
	public WeatherExternalSensorService(String ip) {
		super(ip);
		logger.info("Create " + WeatherExternalSensorService.SENSOR_NAME + " object with IP: " + ip);
		sensorHandler.addSensorService(this);
	}
	
	public WeatherSensor getSensor() {
		return null;
	}
	
	public String getSensorName() {
		return WeatherExternalSensorService.SENSOR_NAME;
	}
}
