package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;

import sensors.objects.Sensor;
import sensors.objects.WeatherSensor;
import sensors.services.AbstractSensorInterface;

/*
 * Implemented but not used due to using of AirLy
 */
public class OpenWeatherMapSensorService extends AbstractSensorInterface {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	private static final String SENSOR_NAME = "OpenWeatherMap";
	private static final String OBJECT_SENSOR_KEY = "main";
	private static final String TEMPERATURE_SENSOR_KEY = "temp";
	private static final String HUMIDITY_SENSOR_KEY = "humidity";
	private final int TIMEOUT = 120000;
	private final int DELAY_TIMEOUT = 20000;
	
	/**
	 * Constructor
	 */
	public OpenWeatherMapSensorService(String ip) {
		super(ip, SENSOR_NAME);
		logger.info("Create " + OpenWeatherMapSensorService.SENSOR_NAME + " object with IP: " + ip);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    this.setHeader(headers);
	}
	
	public <T extends Sensor> T getSensor(T extSensor) {
		logger.debug("Prepare sensor data for " + this.getSensorName());
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			((WeatherSensor)extSensor).setHumidity(String.format("%d", mapper.getJSONObject().getJSONObject(OBJECT_SENSOR_KEY).getInt(HUMIDITY_SENSOR_KEY)));
			/* Get double value in Kelvin and convert to Celcius*/
			((WeatherSensor)extSensor).setTemperature(String.format("%.0f", mapper.getJSONObject().getJSONObject(OBJECT_SENSOR_KEY).getDouble(TEMPERATURE_SENSOR_KEY) - 273.15));
			((WeatherSensor)extSensor).setDate(mapper.getDateAsString());
		}
		extSensor.setStatusCode(mapper.getResponseCode());
		logger.debug("Fetched: " + mapper.getResponseCode());
		return extSensor;
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = TIMEOUT, initialDelay = DELAY_TIMEOUT)
	private void fetchDatas() {		
		this.getRestData();
	}
}
