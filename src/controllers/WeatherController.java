package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import controllers.renderers.RestRespRenderer;

@Controller
@RequestMapping(value = "/api/v1")
public class WeatherController {
	
	@Autowired
	private RestRespRenderer response;
	
	@RequestMapping(value = "/weather", method=GET, produces="application/json")
	@ResponseBody
	public String settings() {	
		return response.getJSON();
	}
}