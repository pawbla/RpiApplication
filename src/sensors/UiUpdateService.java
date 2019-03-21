package sensors;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import sensors.objects.WeatherSensor;
import sensors.services.SensorInterface;
import ui.indicators.weather.InHumidityIndicator;
import ui.indicators.weather.InTemperatureIndicator;
import ui.indicators.weather.OutHumidityIndicator;
import ui.indicators.weather.OutTemperatureIndicator;
import ui.indicators.weather.PressureIndicator;

@Service
public class UiUpdateService {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	/**
	 * Constants
	 */
	private final int TIMEOUT = 10000;
	private final int DELAY_TIMEOUT = 20000;
	
	/**
	 * Variables
	 */
	private WeatherSensor inSensor;
	private WeatherSensor outSensor;
	
	/**
	 * Sensors
	 */
	@Autowired
	@Qualifier("internal")
	private SensorInterface inWeatherSensor;
	
	@Autowired
	@Qualifier("airLy")
	private SensorInterface extWeatherSensor;
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = TIMEOUT, initialDelay = DELAY_TIMEOUT)
	private void refreshUI() {		
		logger.debug("Refresh UI");
		/** Internal sensors*/
		InTemperatureIndicator.getInstance().setText(inWeatherSensor.getSensor(new WeatherSensor()).getTemperature());
		InHumidityIndicator.getInstance().setText(inWeatherSensor.getSensor(new WeatherSensor()).getHumidity());
		
		/** External sensors */
		PressureIndicator.getInstance().setText(extWeatherSensor.getSensor(new WeatherSensor()).getPressure());
		OutTemperatureIndicator.getInstance().setText(extWeatherSensor.getSensor(new WeatherSensor()).getTemperature());
		OutHumidityIndicator.getInstance().setText(extWeatherSensor.getSensor(new WeatherSensor()).getHumidity());
	}
}
