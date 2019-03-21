package sensors.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import sensors.objects.Sensor;

/**
 * Sensor interface
 * @author blach
 *
 */
public interface SensorInterface {
	public <T extends Sensor> T getSensor(T t);
	public String getIP();
	public String getSensorName();
	public void setResponse(ResponseEntity<String> resp);
	public HttpHeaders getHeader();
	public HttpEntity<Object> getEntity();
	public int getTimeout();
	public void setLastResponseCode(int respCode);
	public int getLastResponseCode();
}