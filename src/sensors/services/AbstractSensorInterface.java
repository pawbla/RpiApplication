package sensors.services;

import org.springframework.http.ResponseEntity;

import sensors.JSONMapper;
import sensors.handler.SensorsHandlerInterface;

/**
 * Abstract sensor interface
 * @author blach
 *
 * @param <T>
 */
public abstract class AbstractSensorInterface<T> implements SensorInterface<T> {
	
	private String ip;
	private String sensorName;
	private int timeout;
	
	protected JSONMapper mapper;
	
	public AbstractSensorInterface(String ip, SensorsHandlerInterface sensorHandler, String sensorName, int timeout) {
		this.ip = ip;
		mapper = new JSONMapper();
		this.sensorName = sensorName;
		this.timeout = timeout;
		sensorHandler.addSensorService(this);
	}
	
	public abstract T getSensor();
	
	public String getSensorName() {
		return sensorName;
	}
	
	public String getIP() {
		return ip;
	}
	
	public void setResponse(ResponseEntity<String> resp) {
		this.mapper.setResponse(resp);
	}
	
	public int getTimeout() {
		return timeout;
	}
}
