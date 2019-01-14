package sensors.restServices;


import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import sensors.handler.SensorIteratorInterface;
import sensors.handler.SensorsHandlerInterface;
import sensors.services.SensorInterface;

/**
 * Class to fetch datas using REST Template
 * @author blach
 *
 */
@Component
public class RESTHandler {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	/**
	 * Definitions of variables
	 */
	private RestTemplate rest;
	private HttpEntity<Object> entity;
	private ResponseEntity<String> resp;
	private SensorIteratorInterface sensorIterator;
	
    @Autowired
    private SensorsHandlerInterface sensorHandler;
    
    @Autowired
    private ApplicationContext appCtx;
		
	private final int TIMEOUT = 10000;
	private final int DELAY_TIMEOUT = 20000;
	private static int timeoutIter = 10;
	
	/**
	 * Constructor
	 */
	public RESTHandler() {
		logger.info("Create REST Hanlder object.");
		rest = new RestTemplate();	
	}
	
	/**
	 * Method for sensor iterator initialization just after all objects have created
	 */
	@PostConstruct
	public void init( ) {
		logger.debug("Initialize sensor iterator");
		/* get sensor iterator */
		sensorIterator = sensorHandler.getSensorInterfaceIterator();
		/* Create object to read data 
		while(sensorIterator.isLastSensorInterface()) {
	
		}*/
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = TIMEOUT, initialDelay = DELAY_TIMEOUT)
	private void fetchDatas() {		
		logger.debug("Fetch datas start");
		while(sensorIterator.isLastSensorInterface()) {
			RESTService rest = appCtx.getBean(RESTService.class);
			rest.init(sensorIterator.getSensorInterface());
			rest.start();
		}
		/*
		SensorInterface sensorInterface;
		 iterate over sensors using iterator 
		while(sensorIterator.isLastSensorInterface()) {
			 get sensor object 
			sensorInterface = sensorIterator.getSensorInterface();
			if ((checkMeasurement(sensorInterface.getTimeout())) || (sensorInterface.getLastResponseCode() != 200)) {
				entity = new HttpEntity<Object>(sensorInterface.getHeader());
				logger.info("Sensor fetched: " + sensorInterface.getSensorName() + " from IP: " + sensorInterface.getIP());
				try {
					resp = rest.exchange(sensorInterface.getIP(), HttpMethod.GET, entity, String.class);
					sensorInterface.setLastResponseCode(resp.getStatusCodeValue());
					logger.debug("Received response code " + resp.getStatusCodeValue() + " for sensor: " + sensorInterface.getSensorName());	
				} catch (Exception e) {
					resp = null;
					logger.warn("Unable to fetch datas from ip " + sensorInterface.getIP() + " caused by exception: " + e);		
				} finally {
					sensorInterface.setResponse(resp);	
				}
			}
		}*/
		timeoutIter = timeoutIter + 10;
		if (timeoutIter > 120) {
			timeoutIter = 10;
		}
	}
	
	private boolean checkMeasurement(int sensorTimeout) {
		boolean ret = false;
		if ((timeoutIter % sensorTimeout) == 0) {
			ret = true;
		}
		logger.debug("Check measurement active. TimeoutIter: " + timeoutIter + " sensorTimeout: " + sensorTimeout + " returned value:" + ret);
		return ret;
	}
}
