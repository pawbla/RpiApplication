package sensors.services;

import sensors.objects.Sensor;

/**
 * Sensor interface
 * @author blach
 *
 */
public interface SensorInterface {
	public <T extends Sensor> T getSensor(T t);
	public String getSensorName();
	public boolean getModifyFlag();
}