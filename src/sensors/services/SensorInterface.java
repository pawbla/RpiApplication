package sensors.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

/**
 * Sensor interface
 * @author blach
 *
 */
public interface SensorInterface<T> {
	public T getSensor();
	public String getIP();
	public String getSensorName();
	public void setResponse(ResponseEntity<String> resp);
	public HttpHeaders getHeader();
	public int getTimeout();
}