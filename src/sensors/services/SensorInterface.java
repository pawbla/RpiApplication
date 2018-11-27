package sensors.services;

import java.util.HashMap;
import java.util.List;

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
}

//open weather map link:
//http://api.openweathermap.org/data/2.5/weather?id=3094802&appid=687d050dcb5e9bec0e31d3a59ca8113c