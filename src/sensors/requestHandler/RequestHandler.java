package sensors.requestHandler;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import sensors.services.SensorInterface;

@Component
public class RequestHandler {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Variables
	 */
	private static Map<String, SensorInterface> sensorMap;
	
	public RequestHandler() {
		sensorMap = new HashMap<>();
	}
	
	public void addHandledSensor(String name, SensorInterface sensor) {
		logger.debug("Add handled sensor: " + name);
		sensorMap.put(name, sensor);
	}
	
	public SensorInterface getSensorDatas(String name) {
		logger.debug("Get handled sensor: " + name);
		return sensorMap.get(name);
	}
}
