package controllers.renderers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.accuWeatherConnector.AccuWeatherHandler;
import connectors.airLyConnector.AirLyHandler;
import connectors.internalConnector.InternalHandler;
import connectors.models.Response;
import connectors.sunRiseSetConnector.SunRiseSetHandler;

@Component
@Qualifier("WeatherRenderer")
public class WeatherRestRenderer implements RestRespRenderer {
		
	private JSONObject response;
	
	@Autowired
	private InternalHandler internal;
	
	@Autowired
	public AirLyHandler airLy;
	
	@Autowired
	private SunRiseSetHandler sunRiseSet;
	
	@Autowired
	private AccuWeatherHandler accuWeather;
	
	@Override
	public String getJSON() {
		response = new JSONObject()
			.put("in", new JSONObject()
				.put("temperature", setMeasureObj(internal.getTemperature(), 
						internal.getResponse()))
				.put("humidity", setMeasureObj(internal.getHumidity(), 
						internal.getResponse())))
			.put("out", new JSONObject()
				.put("temperature", setMeasureObj(airLy.getTemperature(), 
						airLy.getResponse()))
				.put("humidity", setMeasureObj(airLy.getHumidity(), 
						airLy.getResponse())))
			.put("weather", new JSONObject()
				.put("pressure", setMeasureObj(airLy.getPressure(), 
						airLy.getResponse()))			
				.put("weatherIcon", setMeasureObj(accuWeather.getWeatherIcon(), 
						accuWeather.getResponse()))
				.put("weatherText", setMeasureObj(accuWeather.getWeatherText(), 
						accuWeather.getResponse()))
				.put("cloudCover", setMeasureObj(accuWeather.getCloudCover(), 
						accuWeather.getResponse()))
				.put("ceiling", setMeasureObj(accuWeather.getCeiling(), 
						accuWeather.getResponse()))
				.put("visibility", setMeasureObj(accuWeather.getVisibility(), 
						accuWeather.getResponse()))
				.put("windDirection", setMeasureObj(accuWeather.getWindDirection(), 
						accuWeather.getResponse()))
				.put("windDirectionDeg", setMeasureObj(accuWeather.getWindDirectionDeg(), 
						accuWeather.getResponse()))
				.put("windSpeed", setMeasureObj(accuWeather.getWindSpeed(), 
						accuWeather.getResponse()))
				.put("uvIndexDescr", setMeasureObj(accuWeather.getUvIndexDescription(), 
						accuWeather.getResponse()))
				.put("uvIndexValue", setMeasureObj(accuWeather.getUvIndexValue(), 
						accuWeather.getResponse()))
				.put("uvIndexColor", setMeasureObj(accuWeather.getUvIndexColor(), 
						accuWeather.getResponse())))
			.put("airPolution", new JSONObject()
				.put("caqi", setMeasureObj(airLy.getCaqi(), 
						accuWeather.getResponse()))
				.put("caqiColor", setMeasureObj(airLy.getCaqiColor(), 
						accuWeather.getResponse()))
				.put("pm1", setMeasureObj(airLy.getPm1(), 
						accuWeather.getResponse()))
				.put("pm10", setMeasureObj(airLy.getPm10(), 
						accuWeather.getResponse()))
				.put("pm10percent", setMeasureObj(airLy.getPm10percent(), 
						accuWeather.getResponse()))
				.put("pm25", setMeasureObj(airLy.getPm25(), 
						accuWeather.getResponse()))
				.put("pm25percent", setMeasureObj(airLy.getPm25percent(), 
						accuWeather.getResponse())))
			.put("sun", new JSONObject()
				.put("rise", setMeasureObj(sunRiseSet.getSunRiseTime(), 
						accuWeather.getResponse()))
				.put("set", setMeasureObj(sunRiseSet.getSunSetTime(), 
						accuWeather.getResponse()))
				.put("dayLength", setMeasureObj(sunRiseSet.getDayLengthTime(), 
						accuWeather.getResponse())));
		return response.toString();
	}
	
	private JSONObject setMeasureObj(String value, Response response) {
		JSONObject obj = new JSONObject()
				.put("value", value)
				.put("isError", response.isError())
				.put("date", response.getDate());		
		return obj;
	}
	
	private JSONObject setMeasureObj(int value, Response response) {
		JSONObject obj = new JSONObject()
				.put("value", value)
				.put("isError", response.isError())
				.put("date", response.getDate());	
		return obj;
	}
}
