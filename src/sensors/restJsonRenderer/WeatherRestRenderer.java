package sensors.restJsonRenderer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import sensors.objects.AccuWeatherSensor;
import sensors.objects.AirPolutionSensor;
import sensors.objects.SunRiseSetSensor;
import sensors.objects.WeatherSensor;
import sensors.services.SensorInterface;

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
	@Qualifier("internal")
	private SensorInterface internalSensor;
	
	@Autowired
	@Qualifier("airLy")
	private SensorInterface airlySensor;
	
	@Autowired
	@Qualifier("sunRiseSet")
	private SensorInterface sunRiseSetSensor;
	
	@Autowired
	@Qualifier("AccuWeatherService")
	private SensorInterface accuWeatherSensor;
	
	private JSONArray getInternal() {
		WeatherSensor internal = internalSensor.getSensor(new WeatherSensor());
		JSONArray arrInternal = new JSONArray();
		JSONObject sensor = new JSONObject()
				.put("temperature", internal.getTemperature())
				.put("humidity", internal.getHumidity())
				.put("isError", false)
				.put("date", internal.getDate());
		return arrInternal.put(sensor);
	}
	
	private JSONObject getExternal() {
		AccuWeatherSensor accuWeather = accuWeatherSensor.getSensor(new AccuWeatherSensor());
		WeatherSensor airLy = airlySensor.getSensor(new WeatherSensor());
		JSONObject airLyJSON = new JSONObject()
				.put("temperature", airLy.getTemperature())
				.put("humidity", airLy.getHumidity())
				.put("pressure", airLy.getPressure())
				.put("isError", false)
				.put("date", airLy.getDate());
		
		JSONObject conditions = new JSONObject()
				.put("icon", accuWeather.getWeatherIcon())
				.put("description", accuWeather.getWeatherText())
				.put("cloudCover", accuWeather.getCloudCover())
				.put("ceiling", accuWeather.getCeiling())
				.put("visibility", accuWeather.getVisibility());
		
		JSONObject wind = new JSONObject()
				.put("direction", accuWeather.getDirection())
				.put("degrees", accuWeather.getDegrees())
				.put("speed", accuWeather.getSpeed());
		
		JSONObject accuJSON = new JSONObject()
				.put("conditions", conditions)
				.put("wind", wind)
				.put("isError", false)
				.put("date", accuWeather.getDate());
		
		JSONObject external = new JSONObject()
				.put("airly", airLyJSON)
				.put("accu", accuJSON);
		return external;
	}
	
	private JSONObject getAirPolution() {
		AirPolutionSensor airPolution = airlySensor.getSensor(new AirPolutionSensor());
		JSONObject air = new JSONObject()
				.put("caqi", airPolution.getCaqi())
				.put("caqiColor", airPolution.getCaqiColor())
				.put("pm1", airPolution.getPm1())
				.put("pm10", airPolution.getPm10())
				.put("pm10per", airPolution.getPm10percent())
				.put("pm25", airPolution.getPm25())
				.put("pm25per", airPolution.getPm25percent())
				.put("isError", false)
				.put("date", airPolution.getDate());
		return air;
	}

	private JSONObject getSunRiseSet() {
		SunRiseSetSensor sunRiseSet = sunRiseSetSensor.getSensor(new SunRiseSetSensor());
		JSONObject sun = new JSONObject()
				.put("rise", sunRiseSet.getSunRiseTime())
				.put("set", sunRiseSet.getSunSetTime())
				.put("dayLength", sunRiseSet.getDayLengthTime())
				.put("isError", false)
				.put("date", sunRiseSet.getDate());
		return sun;
	}
	
	private JSONObject getUV() {
		AccuWeatherSensor accuWeather = accuWeatherSensor.getSensor(new AccuWeatherSensor());
		JSONObject uv = new JSONObject()
				.put("value", accuWeather.getUvIndex())
				.put("text", accuWeather.getUvIndexText())
				.put("isError", false)
				.put("date", accuWeather.getDate());
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
