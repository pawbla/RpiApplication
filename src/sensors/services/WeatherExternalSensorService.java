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
	private static final String OBJECT_SENSOR_KEY = "main";
	private static final String TEMPERATURE_SENSOR_KEY = "temp";
	private static final String HUMIDITY_SENSOR_KEY = "humidity";
	/**
	 * Variables' declarations
	 */
	private WeatherSensor extSensor;
	
	/**
	 * Constructor
	 */
	public WeatherExternalSensorService(String ip, SensorsHandlerInterface sensorHandler) {
		super(ip, sensorHandler);
		logger.info("Create " + WeatherExternalSensorService.SENSOR_NAME + " object with IP: " + ip);
		extSensor = new WeatherSensor();
		extSensor.setHumidity("--.-");
		extSensor.setTemperature("--.-");
		extSensor.setDate("----");
	}
	
	public WeatherSensor getSensor() {
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			extSensor.setHumidity(Integer.toString(mapper.getJSONObject().getJSONObject(OBJECT_SENSOR_KEY).getInt(HUMIDITY_SENSOR_KEY)));
			/* Get double value in Kelvin and convert to Celcius*/
			extSensor.setTemperature(Double.toString(mapper.getJSONObject().getJSONObject(OBJECT_SENSOR_KEY).getDouble(TEMPERATURE_SENSOR_KEY) - 273.15));
			extSensor.setDate(mapper.getDateAsString());
		}
		extSensor.setStatusCode(mapper.getResponseCode());
		logger.debug("Fetched: " + mapper.getResponseCode());
		return extSensor;
	}
	
	public String getSensorName() {
		return WeatherExternalSensorService.SENSOR_NAME;
	}
}
