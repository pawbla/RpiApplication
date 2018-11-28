package sensors.handler;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import homeSystem.EmbeddedApp;
import sensors.services.SensorInterface;

/**
 * Class for constructs iterator over SensorsInterface list 
 * @author blach
 *
 */
@Component
public class SensorIterator implements SensorIteratorInterface {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private SensorInterface<?> sensorInterface;
	private List<SensorInterface<?>> sensorList;
	int position;
	
	/**
	 * Constructor constructs object contains sensor interfaces' list
	 */
	
	public SensorIterator (List<SensorInterface<?>> sensorList) {
		logger.debug("Create SensorInterface object");
		this.sensorList = sensorList;
	}
	

	/**
	 * Method allows to get Sensor Interface over iterable list
	 */
	@Override
	public SensorInterface<?> getSensorInterface() {
		logger.debug("Get SensorInterface from position: " + position);
		sensorInterface = (SensorInterface<?>) sensorList.get(position);
		position++;
		return  sensorInterface ;
	}

	/**
	 * Method for checking if list contain more elements
	 */
	@Override
	public boolean isLastSensorInterface() {
		logger.debug("Check iterator contion");
		/* Return true if condition is true, else return false */
		return (position < sensorList.size()) ? true : false  ;
	}

}
