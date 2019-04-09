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
	
	//@PostConstruct
	private void initMeasurement() {
		getRestData();
	}
	
	protected void getRestData() {
		logger.debug("Get REST data");
		RESTService rest = appCtx.getBean(RESTService.class);
		mapper.setResponse(rest.getRest(this.ip, this.entity, this.sensorName));
	}
}
