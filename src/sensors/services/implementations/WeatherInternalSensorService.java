package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;

import sensors.objects.Sensor;
import sensors.objects.WeatherSensor;
import sensors.services.AbstractSensorInterface;

public class WeatherInternalSensorService extends AbstractSensorInterface {
	
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
	private final int TIMEOUT = 10000;
	private final int DELAY_TIMEOUT = 20000;
	
	/**
	 * Variables' declarations
	 */
	
	@Value("${custom.intSensorPassword}")
	private String pass;
	
	/**
	 * Constructor
	 * @param ip of service
	 */
	public WeatherInternalSensorService(String ip, String password) {
		super(ip, SENSOR_NAME);
		logger.info("Create " + WeatherInternalSensorService.SENSOR_NAME + " object with IP: " + ip);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authentication", password);
	    this.setHeader(headers);
	}
	
	/**
	 * Method to get and parse data into WeatherSensor object
	 */
	@Override
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
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = TIMEOUT, initialDelay = DELAY_TIMEOUT)
	private void fetchDatas() {		
		this.getRestData();
	}

}
