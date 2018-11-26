package sensors.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import homeSystem.EmbeddedApp;
import sensors.handler.SensorIteratorInterface;
import sensors.handler.SensorsHandlerInterface;

/**
 * Class to fetch datas using REST Template
 * @author blach
 *
 */
@Component
public class RESTService {
	
	/**
	 * Logger
	 */
	private static final Logger logger = LogManager.getLogger(EmbeddedApp.class);
	/**
	 * Definitions of variables
	 */
	private String ip;
	private RestTemplate rest;
	private HttpEntity<Object> entity;
	private ResponseEntity<String> resp;
	private HashMap<String,String> map;
	private ObjectMapper mapper;
	private Date date;
	private DateFormat dateFormat;
	private SensorIteratorInterface sensorIterator;
	
    @Autowired
    private SensorsHandlerInterface sensorHandler;
		
	private final long timeout = 10000;
	
	/**
	 * Constructor
	 */
	public RESTService() {
		logger.info("Create REST Service object.");
		rest = new RestTemplate();
		date = new Date();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    entity = new HttpEntity<Object>(headers);	
		map = new HashMap<String,String>();
		mapper = new ObjectMapper();
		
		sensorHandler.getSensorInterfaceIterator();
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = timeout)
	private void fetchDatas() {
		while(!sensorIterator.isLastSensorInterface()) {
			logger.info("Sensor fetched: " + sensorIterator.getSensorInterface().getSensorName());
		}
		logger.info("Fetch datas from ip: " + ip);
		try {
			resp = rest.exchange("http://" + ip, HttpMethod.GET, entity, String.class);
		} catch (Exception e) {
			logger.warn("Unable to fetch datas from ip " + ip + " caused by exception: " + e);		
		}
	}
	
	protected List<Object> getData( ) {
		logger.info("GetDatas from internal weather sensor");
		//update only when status code 200, unless do not update
		try {
			if (resp.getStatusCodeValue() == 200) {
				try { 
					map = mapper.readValue(resp.getBody(), new TypeReference<HashMap<String,String>>(){});
				} catch (Exception e) {
					logger.warn("Unable to get datas collected from internal weather sensor caused by exception: " + e);
				}	
			}
			//inSensor.setStatusCode(resp.getStatusCodeValue());
		} catch (Exception e) {
			//inSensor.setStatusCode(110);
			map = null;
			logger.warn("Unable to read Status Code Value. Exception" + e);		
		}
		return null;
		/* return: DATA, MAP, STATUS CODE VALUE */
	}
}
