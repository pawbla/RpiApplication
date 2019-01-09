package sensors.handler;


import java.util.List;

import sensors.services.SensorInterface;

/**
 * Interface to iterate over Sensor Interfaces
 * @author blach
 *
 */
public interface SensorIteratorInterface {
	
	/**
	 * abstract methods returns sensor interface
	 * @return sensor interface
	 */
	SensorInterface getSensorInterface();
	
	/**
	 * Abstract method return true if list contains more elements
	 * @return
	 */
	boolean isLastSensorInterface();
	
	public void setSensorList(List<SensorInterface> sensorList);
}
