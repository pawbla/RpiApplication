package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import controllers.renderers.RestRespRenderer;
import controllers.renderers.UserDetailsRenderer;
import dao.entities.Users;
import dao.service.ManageUsersService;

@RestController
@RequestMapping(value = "/api/v1")
public class WeatherController {
	
	@Autowired
	@Qualifier("WeatherRenderer")
	private RestRespRenderer weather;
	
	@Autowired
	private UserDetailsRenderer userDetails;
	
	@Autowired
	private ManageUsersService userService;
	
	@GetMapping(value = "/weather", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> weather() {	
		return ResponseEntity.ok().body(weather.getJSON());
	}
	
	@GetMapping(value = "/user", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> loggedUserDatas(@RequestParam("login") String login) {	
		return ResponseEntity.ok().body(userDetails.getJSON(login));
	}
	
	@PostMapping(value = "/register", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> storeUser(@RequestBody Users user) {
		userService.addUser(user);
		return ResponseEntity.ok().body("user stored successfully");
	}
}