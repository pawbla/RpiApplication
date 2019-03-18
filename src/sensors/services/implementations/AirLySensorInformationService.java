package sensors.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import sensors.objects.ServiceInformation;
import sensors.restServices.RESTHandler;
import sensors.services.AbstractSensorInterface;

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
	
	/**
	 * Variables' declarations
	 */
	private ServiceInformation serviceInformation;
	private HttpHeaders headers;
	private HttpEntity<Object> entity;
	private RESTHandler restHandler;
	
	public AirLySensorInformationService(String ip, RESTHandler restHandler, String apiKey) {
		super(ip, SENSOR_NAME);
		logger.info("Create " + AirLySensorInformationService.SENSOR_NAME + " object with IP: " + ip);
		serviceInformation = new ServiceInformation();
		this.restHandler = restHandler;
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add(API_KEY_NAME, apiKey);
	    this.entity = new HttpEntity<Object>(headers);
	    serviceInformation.setName(SENSOR_NAME);
	}

	@Override
	public HttpHeaders getHeader() {
		return headers;
	}

	@Override
	public HttpEntity<Object> getEntity() {
		return this.entity;
	}

	@Override
	public ServiceInformation getSensor() {
		logger.debug("Prepare sensor information data for " + this.getSensorName());
		this.restHandler.requestRestDatas(this);
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			JSONObject jsonArray = mapper.getJSONObject().getJSONObject(ADDRESS_KEY);
			logger.debug("JSON Object:" + jsonArray.toString());
			serviceInformation.setCountry(jsonArray.getString(COUNTRY_KEY));
			serviceInformation.setCity(jsonArray.getString(CITY_KEY));
			serviceInformation.setStreet(jsonArray.getString(STREET_KEY));
		}
		return serviceInformation;
	}
}
