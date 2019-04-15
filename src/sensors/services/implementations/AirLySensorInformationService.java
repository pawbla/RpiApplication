package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sensors.objects.Sensor;
import sensors.objects.ServiceInformation;
import sensors.services.AbstractSensorInterface;

@Component
@Qualifier("airLyInfo")
public class AirLySensorInformationService extends AbstractSensorInterface {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	public static final String SENSOR_NAME = "AirLy Installation";
	private static final String API_KEY_NAME = "apikey";
	private static final String ADDRESS_KEY = "address";
	private static final String COUNTRY_KEY = "country";
	private static final String CITY_KEY = "city"; 
	private static final String STREET_KEY = "street";
	private final int TIMEOUT = 120000;
	private final int DELAY_TIMEOUT = 20000;
	
	public AirLySensorInformationService(@Value("${custom.ipAirLyInstallation}") String ip, @Value("${custom.apiKeyAirLy}") String apiKey) {
		super(ip, SENSOR_NAME);
		logger.info("Create " + AirLySensorInformationService.SENSOR_NAME + " object with IP: " + ip);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add(API_KEY_NAME, apiKey);
	    this.setHeader(headers);
	    
	}

	@Override
	public <T extends Sensor> T getSensor(T serviceInformation) {
		logger.debug("Prepare sensor information data for " + this.getSensorName());
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			JSONObject jsonArray = mapper.getJSONObject().getJSONObject(ADDRESS_KEY);
			logger.debug("JSON Object:" + jsonArray.toString());
			((ServiceInformation)serviceInformation).setCountry(jsonArray.getString(COUNTRY_KEY));
			((ServiceInformation)serviceInformation).setCity(jsonArray.getString(CITY_KEY));
			((ServiceInformation)serviceInformation).setStreet(jsonArray.getString(STREET_KEY));
		}
		return serviceInformation;
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = TIMEOUT, initialDelay = DELAY_TIMEOUT)
	private void fetchDatas() {		
		this.getRestData();
	}
}
