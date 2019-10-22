package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
@RequestMapping({"/", "/index"})
public class HomeController {
	
	/*@Autowired
	@Qualifier("AccuWeatherService")
	private SensorInterface sens;*/

	/*@RequestMapping(method=GET)
	public String home(Model model) {
		sens.getSensor(new AccuWeatherSensor());
		return "home";
	}
	
	@RequestMapping(method=GET)
    public ModelAndView getdata() {
        ModelAndView model = new ModelAndView("index");
        return model;
    }*/
}