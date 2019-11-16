package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import controllers.renderers.RestRespRenderer;

@RestController
@RequestMapping(value = "/api/v1")
public class WeatherController {
	
	@Autowired
	private RestRespRenderer response;
	
	@GetMapping(value = "/weather",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> settings() {	
		return ResponseEntity.ok().body(response.getJSON());
	}
}