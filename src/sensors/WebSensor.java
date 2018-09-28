package sensors;

import homeSystem.EmbeddedApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

//import scala.annotation.meta.setter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class WebSensor implements Sensor {
	private static final Logger logger = LogManager.getLogger(EmbeddedApp.class);
	
	private String ip;
	private RestTemplate rest;
	private HashMap<String,String> map;
	private ObjectMapper mapper;
	private ResponseEntity<String> resp;
	private HttpEntity<Object> entity;
	private WeatherSensor inSensor;
	private Date date;
	private DateFormat dateFormat;
	
	private final long timeout = 10000;
	
	public WebSensor(String ip) {
		logger.info("Create WebSensor object");
		this.ip = ip;
		rest = new RestTemplate();
		map = new HashMap<String,String>();
		mapper = new ObjectMapper();
		inSensor = new WeatherSensor();
		date = new Date();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    entity = new HttpEntity<Object>(headers);		
	}
	
	@Scheduled(fixedRate = timeout)
	private void fetchDatas() {
		logger.info("Fetch datas from ip: " + ip);
		try {
			//resp = rest.getForObject("http://" + ip, String.class);	
			resp = rest.exchange("http://" + ip, HttpMethod.GET, entity, String.class);
		} catch (Exception e) {
			logger.warn("Unable to fetch datas from ip " + ip + " caused by exception: " + e);		
		}
	}	
	
	public WeatherSensor getData() {
		logger.info("GetDatas from sensor with ip: " + ip);
		//update only when status code 200, unless do not update
		try {
			if (resp.getStatusCodeValue() == 200) {
				try { 
					map = mapper.readValue(resp.getBody(), new TypeReference<HashMap<String,String>>(){});
				} catch (Exception e) {
					logger.warn("Unable to get datas collected from sensor's ip " + ip + " caused by exception: " + e);
				}	
				inSensor.setHumidity(map.get("humidity"));
				inSensor.setTemperature(map.get("temperature"));
				inSensor.setPressure(map.get("pressure"));
				inSensor.setDate(dateFormat.format(date));
			}
			inSensor.setStatusCode(resp.getStatusCodeValue());
		} catch (Exception e) {
			inSensor.setStatusCode(110);
			logger.warn("Unable to read Status Code Value. Exception" + e);		
		}
		return inSensor;
	}	
}
