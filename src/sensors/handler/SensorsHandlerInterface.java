package sensors.handler;

import sensors.services.SensorInterface;

/**
 * Interface to handle sensors 
 * @author blach
 *
 */
public interface SensorsHandlerInterface {
	/**
	 * Abstract method to register Sensor Interface
	 * @param sensorService
	 */
	public void addSensorService(SensorInterface<?> sensorService);
	/**
	 * Abstract method to get iterator
	 * @return
	 */
	SensorIteratorInterface getSensorInterfaceIterator();
}
