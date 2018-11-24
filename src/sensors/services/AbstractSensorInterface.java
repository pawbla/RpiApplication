package sensors.services;

import java.util.HashMap;

import sensors.handler.SensorsHandlerInterface;

/**
 * Abstract sensor interface
 * @author blach
 *
 * @param <T>
 */
public abstract class AbstractSensorInterface<T> implements SensorInterface<T> {
	
	private String ip;
	protected int responseCode;
	protected String currentDate;
	protected HashMap<String,String> map;
	
	
	public AbstractSensorInterface(String ip, SensorsHandlerInterface sensorHandler ) {
		this.ip = ip;
		sensorHandler.addSensorService(this);
	}
	
	public abstract T getSensor();
	public abstract String getSensorName();
	
	public String getIP() {
		return ip;
	}
	public void setMap(HashMap<String,String> map) {
		this.map = map;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public void setMeasureDate(String currentDate) {
		this.currentDate = currentDate;
	}
}
