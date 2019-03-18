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
	
	@PostConstruct
	public void init() {
		this.inSensor = inWeatherSensor.getSensor();
		this.outSensor = extWeatherSensor.getSensor();
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = TIMEOUT, initialDelay = DELAY_TIMEOUT)
	private void refreshUI() {		
		logger.debug("Refresh UI");
		/** Internal sensors*/
		InTemperatureIndicator.getInstance().setText(this.inSensor.getTemperature());
		InHumidityIndicator.getInstance().setText(this.inSensor.getHumidity());
		
		/** External sensors */
		PressureIndicator.getInstance().setText(this.outSensor.getPressure());
		OutTemperatureIndicator.getInstance().setText(this.outSensor.getTemperature());
		OutHumidityIndicator.getInstance().setText(this.outSensor.getHumidity());
	}
}
