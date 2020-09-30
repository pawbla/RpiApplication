package controllers.renderers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.handler.HandlerInterface;
import connectors.internalConnector.InternalHandler;
import connectors.models.Response;

@Component
@Qualifier("WeatherRenderer")
public class WeatherRestRenderer implements RestRespRenderer {
		
	private JSONObject response;
	
	private HandlerInterface internal;
	private HandlerInterface airLy;
	private HandlerInterface sunRiseSet;
	private HandlerInterface accuWeather;
	
	@Autowired
	public WeatherRestRenderer(@Qualifier("internal") HandlerInterface internal, 
			@Qualifier("AirLy") HandlerInterface airLy, @Qualifier("sunRiseSet") HandlerInterface sunRiseSet, 
			@Qualifier("accuWeather") HandlerInterface accuWeather) {
		this.internal = internal;
		this.airLy = airLy;
		this.sunRiseSet = sunRiseSet;
		this.accuWeather = accuWeather;
	}
	
	@Override
	public String getJSON() {
		response = new JSONObject()
			.put("in", new JSONObject()
				.put("temperature", setMeasureObj(internal.getResponseValue("temperature"), 
						internal.getResponse()))
				.put("humidity", setMeasureObj(internal.getResponseValue("humidity"), 
						internal.getResponse())))
			.put("out", new JSONObject()
				.put("temperature", setMeasureObj(airLy.getResponseValue("temperature"), 
						airLy.getResponse()))
				.put("humidity", setMeasureObj(airLy.getResponseValue("humidity"), 
						airLy.getResponse())))
			.put("weather", new JSONObject()
				.put("pressure", setMeasureObj(airLy.getResponseValue("pressure"), 
						airLy.getResponse()))			
				.put("weatherIcon", setMeasureObj(accuWeather.getResponseValue("weatherIcon"), 
						accuWeather.getResponse()))
				.put("weatherText", setMeasureObj(accuWeather.getResponseValue("weatherText"), 
						accuWeather.getResponse()))
				.put("cloudCover", setMeasureObj(accuWeather.getResponseValue("cloudCover"), 
						accuWeather.getResponse()))
				.put("ceiling", setMeasureObj(accuWeather.getResponseValue("ceiling"), 
						accuWeather.getResponse()))
				.put("visibility", setMeasureObj(accuWeather.getResponseValue("visibility"), 
						accuWeather.getResponse()))
				.put("windDirection", setMeasureObj(accuWeather.getResponseValue("windDirection"), 
						accuWeather.getResponse()))
				.put("windDirectionDeg", setMeasureObj(accuWeather.getResponseValue("windDirectionDeg"), 
						accuWeather.getResponse()))
				.put("windSpeed", setMeasureObj(accuWeather.getResponseValue("windSpeed"), 
						accuWeather.getResponse()))
				.put("uvIndexDescr", setMeasureObj(accuWeather.getResponseValue("uvIndexDescr"), 
						accuWeather.getResponse()))
				.put("uvIndexValue", setMeasureObj(accuWeather.getResponseValue("uvIndexValue"), 
						accuWeather.getResponse()))
				.put("uvIndexColor", setMeasureObj(accuWeather.getResponseValue("uvIndexColor"), 
						accuWeather.getResponse())))
			.put("airPolution", new JSONObject()
				.put("caqi", setMeasureObj(airLy.getResponseValue("caqi"), 
						accuWeather.getResponse()))
				.put("caqiColor", setMeasureObj(airLy.getResponseValue("caqiColor"), 
						accuWeather.getResponse()))
				.put("pm1", setMeasureObj(airLy.getResponseValue("pm1"), 
						accuWeather.getResponse()))
				.put("pm10", setMeasureObj(airLy.getResponseValue("pm10"), 
						accuWeather.getResponse()))
				.put("pm10percent", setMeasureObj(airLy.getResponseValue("pm10percent"), 
						accuWeather.getResponse()))
				.put("pm25", setMeasureObj(airLy.getResponseValue("pm25"), 
						accuWeather.getResponse()))
				.put("pm25percent", setMeasureObj(airLy.getResponseValue("pm25percent"), 
						accuWeather.getResponse())))
			.put("sun", new JSONObject()
				.put("rise", setMeasureObj(sunRiseSet.getResponseValue("rise"), 
						accuWeather.getResponse()))
				.put("set", setMeasureObj(sunRiseSet.getResponseValue("set"), 
						accuWeather.getResponse()))
				.put("dayLength", setMeasureObj(sunRiseSet.getResponseValue("dayLength"), 
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
}
