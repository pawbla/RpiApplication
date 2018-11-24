package sensors.handler;

import org.springframework.stereotype.Component;

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
	public <T> void addSensorService(SensorInterface<T> sensorService);
	/**
	 * Abstract method to get iterator
	 * @return
	 */
	SensorIteratorInterface getSensorInterfaceIterator();
}
