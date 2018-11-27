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
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    entity = new HttpEntity<Object>(headers);
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = timeout)
	private void fetchDatas() {
		sensorIterator = sensorHandler.getSensorInterfaceIterator();
		while(sensorIterator.isLastSensorInterface()) {
			logger.info("Sensor fetched: " + sensorIterator.getSensorInterface().getSensorName());
			logger.info("Fetch datas from ip: " + ip);
			try {
				resp = rest.exchange("http://" + ip, HttpMethod.GET, entity, String.class);
			} catch (Exception e) {
				logger.warn("Unable to fetch datas from ip " + ip + " caused by exception: " + e);		
			}
			sensorIterator.getSensorInterface().setResponse(resp);
		}
	}
}
