package connectors.accuWeatherConnector;

import java.awt.Color;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import connectors.AbstractHandler;
import connectors.models.Response;

@Component
public class AccuWeatherHandler  extends AbstractHandler {
	
	/**
	 * Constants
	 */
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
	private static final String VIOLET_HEX = "#B803FF";
	
	/*
	 * Variables
	 */
	private String weatherText;
	private int weatherIcon;
	private String windDirection;
	private int windDirectionDeg;
	private String windSpeed;
	private int uvIndexValue;
	private String uvIndexDescription;
	private String visibility;
	private int cloudCover;
	private int ceiling;
	
	public AccuWeatherHandler() {
		weatherText = "";
		weatherIcon = 0;
		windDirection = "";
		windDirectionDeg = 0;
		windSpeed = "";
		uvIndexValue = 0;
		uvIndexDescription = "";
		visibility = "";
		cloudCover = 0;
		ceiling = 0;
	}

	@Override
	protected void parser(Response response) throws JSONException {
		JSONObject mainObj = (JSONObject) new JSONArray(response.getBody()).get(0);
		/* General */
		weatherText = mainObj.getString(WEATHER_TEXT_KEY);
		weatherIcon = mainObj.getInt(WEATHER_ICON_KEY);
		/* Wind */
		JSONObject wind = mainObj.getJSONObject(WIND_KEY);
		JSONObject direction = wind.getJSONObject(WIND_DIRECTION_KEY);
		windDirection = direction.getString(WIND_LOCALIZED_KEY);
		windDirectionDeg = direction.getInt(WIND_DEGREE_KEY);
		windSpeed = String.format("%.0f", wind.getJSONObject(WIND_SPEED_KEY).getJSONObject(METRIC_KEY).getDouble(VALUE_KEY));
		/* UV indexes and visibility */
		uvIndexValue = mainObj.getInt(UV_INDEX_KEY);
		uvIndexDescription = mainObj.getString(UV_INDEX_TEXT_KEY);
		visibility = String.format("%.0f", mainObj.getJSONObject(VISIBILITY_KEY).getJSONObject(METRIC_KEY).getDouble(VALUE_KEY));
		/* Cloud cover and ceiling */
		cloudCover = mainObj.getInt(CLOUD_COVER_KEY);
		ceiling = mainObj.getJSONObject(CEILING_KEY).getJSONObject(METRIC_KEY).getInt(VALUE_KEY);	
	}

	public String getWeatherText() {
		return weatherText;
	}

	public int getWeatherIcon() {
		return weatherIcon;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public int getWindDirectionDeg() {
		return windDirectionDeg;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public int getUvIndexValue() {
		return uvIndexValue;
	}
	
	public String getUvIndexColor() {
		Color color = Color.GREEN;
		if (uvIndexValue >= 3 && uvIndexValue <= 5) {
			color = Color.YELLOW;
		} else if (uvIndexValue >= 6 && uvIndexValue <= 7 ) {
			color = Color.ORANGE;
		} else if (uvIndexValue >= 8 && uvIndexValue <= 10 ) {
			color = Color.RED;
		} else if (uvIndexValue >= 11 ) {
			color = Color.decode(VIOLET_HEX);
		}		
		return "#"+Integer.toHexString(color.getRGB()).substring(2);
	}

	public String getUvIndexDescription() {
		return uvIndexDescription;
	}

	public String getVisibility() {
		return visibility;
	}

	public int getCloudCover() {
		return cloudCover;
	}

	public int getCeiling() {
		return ceiling;
	}
}
