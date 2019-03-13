package sensors.services;

import sensors.handler.SensorsHandlerInterface;

public abstract class AbstractHandledSensorInterface extends AbstractSensorInterface {
	
	/**
	 * Variable
	 */
	private int timeout;

	public AbstractHandledSensorInterface(String ip, SensorsHandlerInterface sensorHandler, String sensorName, int timeout) {
		super(ip, sensorName);
		this.timeout = timeout;
		sensorHandler.addSensorService(this);
	}
	
	public int getTimeout() {
		return timeout;
	}
}
