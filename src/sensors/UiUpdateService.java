package sensors;

import java.awt.Color;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import connectors.accuWeatherConnector.AccuWeatherHandler;
import connectors.airLyConnector.AirLyHandler;
import connectors.internalConnector.InternalHandler;
import connectors.sunRiseSetConnector.SunRiseSetHandler;
import ui.indicators.WeatherIconIndicator;
import ui.indicators.accuWeather.CeilingIndicator;
import ui.indicators.accuWeather.CloudCoverIndicator;
import ui.indicators.accuWeather.UVIndexIndicator;
import ui.indicators.accuWeather.UVTextIndicator;
import ui.indicators.accuWeather.WeatherTextIndicator;
import ui.indicators.accuWeather.WindDirectionIndicator;
import ui.indicators.accuWeather.WindSpeedIndicator;
import ui.indicators.airpolution.CAQiIndicator;
import ui.indicators.airpolution.PM10Percent_Indicator;
import ui.indicators.airpolution.PM10_Indicator;
import ui.indicators.airpolution.PM1_Indicator;
import ui.indicators.airpolution.PM25Percent_Indicator;
import ui.indicators.airpolution.PM25_Indicator;
import ui.indicators.sun.DayLengthIndicator;
import ui.indicators.sun.SunRiseIndicator;
import ui.indicators.sun.SunSetIndicator;
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
	private final int DELAY_TIMEOUT = 10000;
		
	/**
	 * Sensors
	 */
	@Autowired
	private InternalHandler internal;
	
	@Autowired
	public AirLyHandler airLy;
	
	@Autowired
	private SunRiseSetHandler sunRiseSet;
	
	@Autowired
	private AccuWeatherHandler accuWeather;
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(fixedRate = TIMEOUT, initialDelay = DELAY_TIMEOUT)
	private void refreshUI() {		
		logger.debug("Refresh UI");
		/** Internal sensors*/
		InTemperatureIndicator.getInstance().setText(internal.getTemperature());
		InHumidityIndicator.getInstance().setText(internal.getHumidity());
		
		/** AirLy sensors */
		PressureIndicator.getInstance().setText(airLy.getPressure());
		OutTemperatureIndicator.getInstance().setText(airLy.getTemperature());
		OutHumidityIndicator.getInstance().setText(airLy.getHumidity());			
		CAQiIndicator.getInstance().setText(airLy.getCaqi());
		CAQiIndicator.getInstance().setForeground(Color.decode(airLy.getCaqiColor()));
		PM1_Indicator.getInstance().setText(airLy.getPm1());
		PM10_Indicator.getInstance().setText(airLy.getPm10());
		PM25_Indicator.getInstance().setText(airLy.getPm25());
		PM10Percent_Indicator.getInstance().setText(airLy.getPm10percent());
		PM25Percent_Indicator.getInstance().setText(airLy.getPm25percent());
		
		/** Sun rise and sun set */
		SunRiseIndicator.getInstance().setText(sunRiseSet.getSunRiseTime());
		SunSetIndicator.getInstance().setText(sunRiseSet.getSunSetTime());
		DayLengthIndicator.getInstance().setText(sunRiseSet.getDayLengthTime());
		
		/** Accu weather sensor */
		WeatherTextIndicator.getInstance().setText(accuWeather.getWeatherText());
		WindSpeedIndicator.getInstance().setText(accuWeather.getWindSpeed());
		WindDirectionIndicator.getInstance().setText(accuWeather.getWindDirection());
		WeatherIconIndicator.getInstance().setIconByNumber(accuWeather.getWeatherIcon());
		CloudCoverIndicator.getInstance().setText(Integer.toString(accuWeather.getCloudCover()));
		CeilingIndicator.getInstance().setText(Integer.toString(accuWeather.getCeiling()));
		UVIndexIndicator.getInstance().setText(accuWeather.getUvIndexValue());
		UVTextIndicator.getInstance().setText(accuWeather.getUvIndexDescription());
	}
}
