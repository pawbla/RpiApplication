package sensors.restServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import sensors.services.SensorInterface;

@Component
@Scope("prototype")
public class RESTService extends Thread {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	/**
	 * Definitions of variables
	 */
	private ResponseEntity<String> resp;
	private RestTemplate rest;
	private SensorInterface sensorInterface;
	
	/**
	 * Constructor
	 */
	public RESTService() {
		logger.info("Create REST Service object.");
		rest = new RestTemplate();
		resp = null;
	}
	
	public void init(SensorInterface sensorInterface) {
		logger.debug("Initilize REST Service with sensor interface: " + sensorInterface.getSensorName());
		this.sensorInterface = sensorInterface;
	}

	@Override
	public void run() {
		logger.debug("Start thread for sensor " + sensorInterface.getSensorName() + ", thread name: "+ Thread.currentThread().getName());
		try {
			resp = rest.exchange(sensorInterface.getIP(), HttpMethod.GET, sensorInterface.getEntity(), String.class);
			sensorInterface.setLastResponseCode(resp.getStatusCodeValue());
			logger.debug("Received response code " + resp.getStatusCodeValue() + " for sensor: " + sensorInterface.getSensorName());	
		} catch (Exception e) {
			resp = null;
			logger.warn("Unable to fetch datas from ip " + sensorInterface.getIP() + " caused by exception: " + e);		
		} finally {
			sensorInterface.setResponse(resp);	
		}
	}
	
	
}
