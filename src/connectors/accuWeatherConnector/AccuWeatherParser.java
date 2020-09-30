package connectors.accuWeatherConnector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.models.Response;
import connectors.parser.AbstractParser;

@Component
@Qualifier("accuWeather")
public class AccuWeatherParser extends AbstractParser {
	
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
	
	@Override
	public void parse (Response response) throws JSONException {
		JSONObject mainObj = (JSONObject) new JSONArray(response.getBody()).get(0);
		/* General */
		this.addParsed("weatherText", mainObj.getString(WEATHER_TEXT_KEY));
		this.addParsed("weatherIcon", Integer.toString(mainObj.getInt(WEATHER_ICON_KEY)));
		/* Wind */
		System.out.println("================" + mainObj.getInt(WEATHER_ICON_KEY));
		JSONObject wind = mainObj.getJSONObject(WIND_KEY);
		JSONObject direction = wind.getJSONObject(WIND_DIRECTION_KEY);
		this.addParsed("windDirection", direction.getString(WIND_LOCALIZED_KEY));
		this.addParsed("windDirectionDeg", Integer.toString(direction.getInt(WIND_DEGREE_KEY)));
		this.addParsed("windSpeed", String.format("%.0f", wind.getJSONObject(WIND_SPEED_KEY).getJSONObject(METRIC_KEY).getDouble(VALUE_KEY)));
		/* UV indexes and visibility */
		this.addParsed("uvIndexValue", Integer.toString(mainObj.getInt(UV_INDEX_KEY)));
		this.addParsed("uvIndexDescription", mainObj.getString(UV_INDEX_TEXT_KEY));
		this.addParsed("visibility", String.format("%.0f", mainObj.getJSONObject(VISIBILITY_KEY).getJSONObject(METRIC_KEY).getDouble(VALUE_KEY)));
		/* Cloud cover and ceiling */
		this.addParsed("cloudCover", Integer.toString(mainObj.getInt(CLOUD_COVER_KEY)));
		this.addParsed("ceiling", Integer.toString(mainObj.getJSONObject(CEILING_KEY).getJSONObject(METRIC_KEY).getInt(VALUE_KEY)));
	}

}
