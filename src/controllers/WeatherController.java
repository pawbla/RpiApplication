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

import sensors.*;

@Controller
public class WeatherController {
	
	@Autowired
	@Qualifier("internal")
	private Sensor sens;
	
	private Map<String,Object> sensors;
	
	public WeatherController() {
		sensors = new HashMap<String,Object>();
	}
	
	private Map<String,Object> setSensors() {
		sensors.put("inSensor", sens.getData());
		sensors.put("outSensor", sens.getData());
		return sensors;
	}
	
	@RequestMapping(value = "/weather", method=GET)
	public String settings(Model model) {
		model.addAllAttributes(setSensors());
		return "weather";
	}
	
	@RequestMapping(value = "/weatherRest",method=GET, produces="application/json")
	public @ResponseBody Map<String,Object> l (Principal user) {
		return setSensors();
	}
	
	
}