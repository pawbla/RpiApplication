package sensors.services;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
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
		
	private final long timeout = 20000;
	
	/**
	 * Constructor
	 */
	public RESTService() {
		logger.info("Create REST Service object.");
		rest = new RestTemplate();	    
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = timeout)
	private void fetchDatas() {		
		sensorIterator = sensorHandler.getSensorInterfaceIterator();
		SensorInterface<?> sensorInterface;
		while(sensorIterator.isLastSensorInterface()) {
			sensorInterface = sensorIterator.getSensorInterface();
			entity = new HttpEntity<Object>(sensorInterface.getHeader());
			logger.info("Sensor fetched: " + sensorInterface.getSensorName() + " from IP: " + sensorInterface.getIP());
			try {
				resp = rest.exchange("http://" + sensorInterface.getIP(), HttpMethod.GET, entity, String.class);
				logger.debug("Received response code " + resp.getStatusCodeValue() + " for sensor: " + sensorInterface.getSensorName());
				sensorInterface.setResponse(resp);
			} catch (Exception e) {
				logger.warn("Unable to fetch datas from ip " + ip + " caused by exception: " + e);		
			}
		}
	}
}
