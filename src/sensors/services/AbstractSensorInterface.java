package sensors.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;

import sensors.MeasuredValuesMapper;
import sensors.handler.SensorsHandlerInterface;

/**
 * Abstract sensor interface
 * @author blach
 *
 * @param <T>
 */
public abstract class AbstractSensorInterface<T> implements SensorInterface<T> {
	
	private String ip;
	
	@Autowired
	protected MeasuredValuesMapper mapper;
	
	public AbstractSensorInterface(String ip, SensorsHandlerInterface sensorHandler ) {
		this.ip = ip;
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
