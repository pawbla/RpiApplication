package controllers;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import controllers.renderers.RestRespRenderer;

@RestController
@RequestMapping(value = "/api/v1")
public class WeatherController {
	
	@Autowired
	@Qualifier("WeatherRenderer")
	private RestRespRenderer weather;
	
	@GetMapping(value = "/weather", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> settings() {	
		return ResponseEntity.ok().body(weather.getJSON());
	}
	
	@GetMapping(value = "/user", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> loggedUserDatas(@RequestParam("login") String login) {	
		//below string only for test, should be replaced !!!!!!!!!!!!!!!!!!
		JSONObject response = new JSONObject()
				.put("login", login)
				.put("firstName", "userFirstName")
				.put("lastName", "userLastName")
				.put("role", "admin");

		return ResponseEntity.ok().body(response.toString());
	}
}