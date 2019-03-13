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
public abstract class AbstractSensorInterface implements SensorInterface {
	
	private String ip;
	private String sensorName;
	private int lastRespCode;
	
	protected JSONMapper mapper;
	
	public AbstractSensorInterface(String ip, String sensorName) {
		this.ip = ip;
		mapper = new JSONMapper();
		this.sensorName = sensorName;
		lastRespCode = 0;
	}
	
	public abstract <T> T getSensor();
	
	public String getSensorName() {
		return sensorName;
	}
	
	public String getIP() {
		return ip;
	}
	
	public void setResponse(ResponseEntity<String> resp) {
		this.mapper.setResponse(resp);
	}

	
	public void setLastResponseCode(int respCode) {
		this.lastRespCode = respCode;
	}
	public int getLastResponseCode() {
		return this.lastRespCode;
	}
	
	void setHeader() {
		
	}
	
	@Override
	public int getTimeout() {
		return 0;
	}
}
