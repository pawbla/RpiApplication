package sensors.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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
	public WeatherInternalSensorService(String ip) {
		super(ip);
		logger.info("Create " + WeatherInternalSensorService.SENSOR_NAME + " object with IP: " + ip);
		inSensor = new WeatherSensor();
	}
	
	/**
	 * Method to get and parse data into WeatherSensor object
	 */
	public WeatherSensor getSensor() {
		/*
		inSensor.setHumidity(map.get("humidity"));
		inSensor.setTemperature(map.get("temperature"));
		inSensor.setPressure(map.get("pressure"));
		inSensor.setDate(dateFormat.format(date));*/
		return inSensor;
	}
	
	public String getSensorName() {
		return WeatherInternalSensorService.SENSOR_NAME;
	}
}
