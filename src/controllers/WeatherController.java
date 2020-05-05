package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import controllers.renderers.RestRespRenderer;
import controllers.renderers.UserDetailsRenderer;
import controllers.renderers.UsersListRenderer;
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
	private UsersListRenderer userListRenderer;
	
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
	public ResponseEntity<String> registerUser(@RequestBody Users user) {
		System.out.println("USER " + user.getUserName());
		userService.addUser(user);
		return ResponseEntity.ok().body("user stored successfully");
	}
	
	@GetMapping(value = "/users", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getUsers() {
		return ResponseEntity.ok().body(userListRenderer.getJSON());
	}
	
	@DeleteMapping("deleteUser/{user_id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int user_id) {
		userService.removeUser(user_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("updateUser/{user_id}")
	public ResponseEntity<String> updateUser(@PathVariable int user_id, @RequestBody Users user) {
		System.out.println("USER " + user.getUserName());
		userService.updateUser(user_id, user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
 }