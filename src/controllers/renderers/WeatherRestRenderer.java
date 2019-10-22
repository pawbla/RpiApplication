package controllers.renderers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.accuWeatherConnector.AccuWeatherHandler;
import connectors.airLyConnector.AirLyHandler;
import connectors.internalConnector.InternalHandler;
import connectors.sunRiseSetConnector.SunRiseSetHandler;

@Component
@Qualifier("WeatherRenderer")
public class WeatherRestRenderer implements RestRespRenderer {
	
	private static final String INTERNAL_KEY = "internal";
	private static final String EXTERNAL_KEY = "external";
	private static final String AIR_POLUTION_KEY = "airPolution";
	private static final String SUN_KEY = "sun";
	private static final String UV_KEY = "uv";
	
	private JSONObject response;
	
	@Autowired
	private InternalHandler internal;
	
	@Autowired
	public AirLyHandler airLy;
	
	@Autowired
	private SunRiseSetHandler sunRiseSet;
	
	@Autowired
	private AccuWeatherHandler accuWeather;
	
	private JSONArray getInternal() {
		JSONArray arrInternal = new JSONArray();
		JSONObject sensor = new JSONObject()
				.put("temperature", internal.getTemperature())
				.put("humidity", internal.getHumidity())
				.put("isError", false)
				.put("date", internal.getResponse().getDate());
		return arrInternal.put(sensor);
	}
	
	private JSONObject getExternal() {
		JSONObject airLyJSON = new JSONObject()
				.put("temperature", airLy.getTemperature())
				.put("humidity", airLy.getHumidity())
				.put("pressure", airLy.getPressure())
				.put("isError", false)
				.put("date", airLy.getResponse().getDate());
		
		JSONObject conditions = new JSONObject()
				.put("icon", accuWeather.getWeatherIcon())
				.put("description", accuWeather.getWeatherText())
				.put("cloudCover", accuWeather.getCloudCover())
				.put("ceiling", accuWeather.getCeiling())
				.put("visibility", accuWeather.getVisibility());
		
		JSONObject wind = new JSONObject()
				.put("direction", accuWeather.getWindDirection())
				.put("degrees", accuWeather.getWindDirectionDeg())
				.put("speed", accuWeather.getWindSpeed());
		
		JSONObject accuJSON = new JSONObject()
				.put("conditions", conditions)
				.put("wind", wind)
				.put("isError", false)
				.put("date", accuWeather.getResponse().getDate());
		
		JSONObject external = new JSONObject()
				.put("airly", airLyJSON)
				.put("accu", accuJSON);
		return external;
	}
	
	private JSONObject getAirPolution() {
		JSONObject air = new JSONObject()
				.put("caqi", airLy.getCaqi())
				.put("caqiColor", airLy.getCaqiColor())
				.put("pm1", airLy.getPm1())
				.put("pm10", airLy.getPm10())
				.put("pm10per", airLy.getPm10percent())
				.put("pm25", airLy.getPm25())
				.put("pm25per", airLy.getPm25percent())
				.put("isError", false)
				.put("date", airLy.getResponse().getDate());
		return air;
	}

	private JSONObject getSunRiseSet() {
		JSONObject sun = new JSONObject()
				.put("rise", sunRiseSet.getSunRiseTime())
				.put("set", sunRiseSet.getSunSetTime())
				.put("dayLength", sunRiseSet.getDayLengthTime())
				.put("isError", false)
				.put("date", sunRiseSet.getResponse().getDate());
		return sun;
	}
	
	private JSONObject getUV() {
		JSONObject uv = new JSONObject()
				.put("value", accuWeather.getUvIndexValue())
				.put("text", accuWeather.getUvIndexDescription())
				.put("isError", false)
				.put("date", accuWeather.getResponse().getDate());
		return uv;
	}

	@Override
	public String getJSON() {
		response = new JSONObject()
				.put(INTERNAL_KEY, getInternal())
				.put(EXTERNAL_KEY, getExternal())
				.put(AIR_POLUTION_KEY, getAirPolution())
				.put(SUN_KEY, getSunRiseSet())
				.put(UV_KEY, getUV());
		return response.toString();
	}
}
