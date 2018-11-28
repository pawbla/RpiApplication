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
	
	protected JSONMapper mapper;
	
	public AbstractSensorInterface(String ip, SensorsHandlerInterface sensorHandler) {
		this.ip = ip;
		mapper = new JSONMapper();
		sensorHandler.addSensorService(this);
	}
	
	public abstract T getSensor();
	public abstract String getSensorName();
	
	public String getIP() {
		return ip;
	}
	
	public void setResponse(ResponseEntity<String> resp) {
		this.mapper.setResponse(resp);
	}
}
