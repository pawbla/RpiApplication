package sensors.services.implementations;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sensors.objects.Sensor;
import sensors.services.AbstractSensorInterface;

@Component
@Qualifier("AccuWeatherService")
public class AccuWeatherService extends AbstractSensorInterface {

	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	public static final String SERVICE_NAME = "AccuWeather service";
	
	public AccuWeatherService (@Value("${custom.urlAccuWeather}") String url) {
		super(url, SERVICE_NAME);
		logger.info("Create " + AccuWeatherService.SERVICE_NAME + " object with URL: " + url);
		HttpHeaders headers = new HttpHeaders();
	    //headers.add("Accept-Encoding", "gzip");
	    this.setHeader(headers);
	}

	@Override
	public <T extends Sensor> T getSensor(T t) {
		logger.debug("Prepare sensor information data for " + this.getSensorName());
		mapper.prepareDatas();
		if (mapper.getResponseCode() == 200) {
			JSONObject jsonArray = mapper.getJSONObject();
			logger.debug("JSON Object:" + jsonArray.toString());
		}
		return null;
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(cron="0 0 * * * *", zone="Europe/Warsaw")
	private void fetchDatas() {		
		this.getRestData();
	}
	
	@PostConstruct
	private void initialMeasurement() {
		this.getRestData();
	}
}
