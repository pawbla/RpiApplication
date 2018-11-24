package sensors.handler;

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
	<T> SensorInterface<T> getSensorInterface();
	
	/**
	 * Abstract method return true if list contains more elements
	 * @return
	 */
	boolean isLastSensorInterface();
}
