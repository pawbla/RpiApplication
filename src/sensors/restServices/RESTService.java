package sensors.restServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import sensors.services.SensorInterface;

@Component
@Scope("prototype")
public class RESTService {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	/**
	 * Definitions of variables
	 */
	private ResponseEntity<String> resp;
	private RestTemplate rest;
	private String ip;
	private HttpEntity<Object> entity;
	private String sensorName;
	
	/**
	 * Constructor
	 */
	public RESTService() {
		logger.info("Create REST Service object.");
		rest = new RestTemplate();
		resp = null;
	}
	
	public void init(String ip, HttpEntity<Object> entity, String sensorName) {
		this.ip = ip;
		this.entity = entity;
		this.sensorName = sensorName;
	}

	public ResponseEntity<String> getRest(String ip, HttpEntity<Object> entity, String sensorName) {
		int iter = 0;
		while (iter < 3) {
			logger.debug("Get REST data for " + sensorName + " with ip " + ip + " iteration " + iter);
			try {
				resp = rest.exchange(ip, HttpMethod.GET, entity, String.class);
				if (resp.getStatusCodeValue() == 200) {
					break;
				}
			} catch (Exception e) {
				resp = null;
				logger.warn("Unable to fetch datas from ip " + ip + " caused by exception: " + e);		
			} 
			iter++;
		}	
		return resp;
	}
}
