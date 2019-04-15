package sensors.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import sensors.JSONMapper;
import sensors.restServices.RESTService;

/**
 * Abstract sensor interface
 * @author blach
 *
 * @param <T>
 */
public abstract class AbstractSensorInterface implements SensorInterface {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
    @Autowired
    private ApplicationContext appCtx;
	
	/**
	 * Definitions of variables
	 */
	
	private String ip;
	private String sensorName;
	private HttpEntity<Object> entity;
	private boolean modifyFlag = false;
	
	protected JSONMapper mapper;
	
	public AbstractSensorInterface(String ip, String sensorName) {
		this.ip = ip;
		mapper = new JSONMapper();
		this.sensorName = sensorName;
	}
	
	public String getSensorName() {
		return sensorName;
	}
	
	protected void setHeader(final HttpHeaders header) {
		this.entity = new HttpEntity<Object>(header);
	}
	
	protected void getRestData() {
		logger.debug("Get REST data and set modify flag");
		modifyFlag = true;
		RESTService rest = appCtx.getBean(RESTService.class);
		mapper.setResponse(rest.getRest(this.ip, this.entity, this.sensorName));
	}
	
	/**
	 * Method for UI refreshing. Allows to check if value on UI should be refreshed
	 * just after has read. In case when current value is displayed flag should be set 
	 * to false;
	 */
	public boolean getModifyFlag() {
		logger.debug("Modify flag is set to: " + modifyFlag + " for service: " + this.sensorName);
		boolean retVal = false;
		if (modifyFlag) {
			retVal = true;
			modifyFlag = false;
		}
		return retVal;
	}
}
