package sensors.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import homeSystem.EmbeddedApp;
import sensors.services.SensorInterface;

/**
 * Class to handle sensor interfaces
 * @author blach
 *
 */
@Component
public class SensorHandler implements SensorsHandlerInterface {
	
	/**
	 * Logger
	 */
	private static final Logger logger = LogManager.getLogger(EmbeddedApp.class);

	private List<SensorInterface<?>> sensorList;
	
	/**
	 * Constructor constructs sensor interfaces' list
	 */
	public SensorHandler() {
		sensorList = new ArrayList<SensorInterface<?>>();
		logger.debug("Sensor handler constructor has created.");
	}
	
	/**
	 * Mehtod to register sensor service
	 */
	@Override
	public <T> void addSensorService(SensorInterface<T> sensorService) {
		logger.debug("Register SensorInterface " + sensorService.getSensorName());
		sensorList.add(sensorService);
	}
	
	/**
	 * Method allows to get SensorInterface iterator
	 */
	@Override
	public SensorIteratorInterface getSensorInterfaceIterator() {	
		return new SensorIterator(sensorList);
	}

}
