package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sensors.objects.AirPolutionSensor;
import sensors.objects.WeatherSensor;
import sensors.restJsonRenderer.RestRespRenderer;
import sensors.services.SensorInterface;

@Controller
public class WeatherController {
	
	@Autowired
	private RestRespRenderer response;
	
	@RequestMapping(value = "/weather", method=GET, produces="application/json")
	@ResponseBody
	public String settings() {	
		return response.getJSON();
	}
}