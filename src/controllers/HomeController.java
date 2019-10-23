package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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