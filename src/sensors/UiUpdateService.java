package sensors;

import java.awt.Color;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import sensors.objects.AirPolutionSensor;
import sensors.objects.WeatherSensor;
import sensors.services.SensorInterface;
import ui.indicators.airpolution.CAQiIndicator;
import ui.indicators.airpolution.PM10Percent_Indicator;
import ui.indicators.airpolution.PM10_Indicator;
import ui.indicators.airpolution.PM1_Indicator;
import ui.indicators.airpolution.PM25Percent_Indicator;
import ui.indicators.airpolution.PM25_Indicator;
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
	 * Sensors
	 */
	@Autowired
	@Qualifier("internal")
	private SensorInterface inWeatherSensor;
	
	@Autowired
	@Qualifier("airLy")
	private SensorInterface airLySensor;
	
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
		PressureIndicator.getInstance().setText(airLySensor.getSensor(new WeatherSensor()).getPressure());
		OutTemperatureIndicator.getInstance().setText(airLySensor.getSensor(new WeatherSensor()).getTemperature());
		OutHumidityIndicator.getInstance().setText(airLySensor.getSensor(new WeatherSensor()).getHumidity());
		
		/** AirPolution sensors*/
		CAQiIndicator.getInstance().setText(airLySensor.getSensor(new AirPolutionSensor()).getCaqi());
		CAQiIndicator.getInstance().setForeground(Color.decode(airLySensor.getSensor(new AirPolutionSensor()).getCaqiColor()));
		PM1_Indicator.getInstance().setText(airLySensor.getSensor(new AirPolutionSensor()).getPm1());
		PM10_Indicator.getInstance().setText(airLySensor.getSensor(new AirPolutionSensor()).getPm10());
		PM25_Indicator.getInstance().setText(airLySensor.getSensor(new AirPolutionSensor()).getPm25());
		PM10Percent_Indicator.getInstance().setText(airLySensor.getSensor(new AirPolutionSensor()).getPm10percent());
		PM25Percent_Indicator.getInstance().setText(airLySensor.getSensor(new AirPolutionSensor()).getPm25percent());
	}
}
