package sensors.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

/**
 * Sensor interface
 * @author blach
 *
 */
public interface SensorInterface {
	public <T> T getSensor();
	public String getIP();
	public String getSensorName();
	public void setResponse(ResponseEntity<String> resp);
	public HttpHeaders getHeader();
	public int getTimeout();
	public void setLastResponseCode(int respCode);
	public int getLastResponseCode();
}