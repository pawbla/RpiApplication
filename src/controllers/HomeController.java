package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sensors.objects.AccuWeatherSensor;
import sensors.services.SensorInterface;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {
	
	@Autowired
	@Qualifier("AccuWeatherService")
	private SensorInterface sens;

	@RequestMapping(method=GET)
	public String home(Model model) {
		sens.getSensor(new AccuWeatherSensor());
		return "home";
	}
}