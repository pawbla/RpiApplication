package sensors.services.implementations;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sensors.objects.AccuWeatherSensor;
import sensors.objects.Sensor;
import sensors.services.AbstractSensorInterface;

@Component
@Qualifier("AccuWeatherService")
public class AccuWeatherService extends AbstractSensorInterface {

	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	public static final String SERVICE_NAME = "AccuWeather service";
	private static final String WEATHER_TEXT_KEY = "WeatherText";
	private static final String WEATHER_ICON_KEY = "WeatherIcon";
	private static final String WIND_KEY = "Wind";
	private static final String WIND_DIRECTION_KEY = "Direction";
	private static final String WIND_LOCALIZED_KEY = "Localized";
	private static final String WIND_DEGREE_KEY = "Degrees";
	private static final String WIND_SPEED_KEY = "Speed";
	private static final String METRIC_KEY = "Metric";
	private static final String VALUE_KEY = "Value";
	private static final String UV_INDEX_KEY = "UVIndex";
	private static final String UV_INDEX_TEXT_KEY = "UVIndexText";
	private static final String VISIBILITY_KEY = "Visibility";
	private static final String CLOUD_COVER_KEY = "CloudCover";
	private static final String CEILING_KEY = "Ceiling";
	private static final String PRESSURE_KEY = "Pressure";
	private static final String PRESSURE_TENDENCY_KEY = "PressureTendency";
	private static final String PRESSURE_TENDENCY_LOCALIZED_KEY = "LocalizedText";
	private static final String PRESSURE_TENDENCY_CODE_KEY = "Code";
	
	public AccuWeatherService (@Value("${custom.urlAccuWeather}") String url) {
		super(url, SERVICE_NAME);
		logger.info("Create " + AccuWeatherService.SERVICE_NAME + " object with URL: " + url);
		HttpHeaders headers = new HttpHeaders();
	    //headers.add("Accept-Encoding", "gzip");
	    this.setHeader(headers);
	}

	@Override
	public <T extends Sensor> T getSensor(T sensor) {
		logger.debug("Prepare sensor information data for " + this.getSensorName());
		if (mapper.getResponseCode() == 200) {
			JSONObject mainObj = (JSONObject) mapper.getJSONArray().get(0);
			logger.debug("JSON Object:" + mainObj);
			/* General */
			((AccuWeatherSensor)sensor).setWeatherText(mainObj.getString(WEATHER_TEXT_KEY));
			((AccuWeatherSensor)sensor).setWeatherIcon(mainObj.getInt(WEATHER_ICON_KEY));
			/* Wind */
			JSONObject wind = mainObj.getJSONObject(WIND_KEY);
			JSONObject direction = wind.getJSONObject(WIND_DIRECTION_KEY);
			((AccuWeatherSensor)sensor).setDirection(direction.getString(WIND_LOCALIZED_KEY));
			((AccuWeatherSensor)sensor).setDegrees(direction.getInt(WIND_DEGREE_KEY));
			((AccuWeatherSensor)sensor).setSpeed(String.format("%.0f", wind.getJSONObject(WIND_SPEED_KEY).getJSONObject(METRIC_KEY).getDouble(VALUE_KEY)));
			/* UV indexes and visibility */
			((AccuWeatherSensor)sensor).setUvIndex(mainObj.getInt(UV_INDEX_KEY));
			((AccuWeatherSensor)sensor).setUvIndexText(mainObj.getString(UV_INDEX_TEXT_KEY));
			((AccuWeatherSensor)sensor).setVisibility(String.format("%.0f", mainObj.getJSONObject(VISIBILITY_KEY).getJSONObject(METRIC_KEY).getDouble(VALUE_KEY)));
			/* Cloud cover and ceiling */
			((AccuWeatherSensor)sensor).setCloudCover(mainObj.getInt(CLOUD_COVER_KEY));
			((AccuWeatherSensor)sensor).setCeiling(mainObj.getJSONObject(CEILING_KEY).getJSONObject(METRIC_KEY).getInt(VALUE_KEY));
			/* Pressure */
			((AccuWeatherSensor)sensor).setValue(mainObj.getJSONObject(PRESSURE_KEY).getJSONObject(METRIC_KEY).getInt(VALUE_KEY));
			((AccuWeatherSensor)sensor).setTendencyText(mainObj.getJSONObject(PRESSURE_TENDENCY_KEY).getString(PRESSURE_TENDENCY_LOCALIZED_KEY));
			((AccuWeatherSensor)sensor).setTendencyCode(mainObj.getJSONObject(PRESSURE_TENDENCY_KEY).getString(PRESSURE_TENDENCY_CODE_KEY));
			logger.debug("Fetched: " + mapper.getResponseCode());
			sensor.setDate(mapper.getDateAsString());
			sensor.setStatusCode(mapper.getResponseCode());
		}
		return sensor;
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(cron="0 0 * * * *", zone="Europe/Warsaw")
	private void fetchDatas() {		
		this.getRestData();
	}
	
	@PostConstruct
	private void initialMeasurement() {
		this.getRestData();
	}
}
