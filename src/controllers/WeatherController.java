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
import controllers.renderers.SimplyMessageRenderer;
import controllers.renderers.UserDetailsRenderer;
import controllers.renderers.UsersListRenderer;
import dao.entities.Users;
import dao.service.ManageUsersService;
import exceptions.RemoveAllAdminsException;
import exceptions.UpdatePasswordException;
import model.PasswordUpdate;

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
	
	@Autowired
	private SimplyMessageRenderer simplyMessage;
	
	@GetMapping(value = "/weather", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
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
		userService.addUser(user);
		return ResponseEntity.ok().body(simplyMessage.getJSON("user stored succesfully"));
	}
	
	@GetMapping(value = "/users", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getUsers() {
		return ResponseEntity.ok().body(userListRenderer.getJSON());
	}
	
	@DeleteMapping("deleteUser/{user_id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int user_id) throws RemoveAllAdminsException {
		try {
			userService.removeUser(user_id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RemoveAllAdminsException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@PutMapping("updateUser/{user_id}")
	public ResponseEntity<String> updateUser(@PathVariable int user_id, @RequestBody Users user) throws RemoveAllAdminsException {
		try {
			userService.updateUser(user_id, user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RemoveAllAdminsException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("changepass/{user_id}")
	public  ResponseEntity<String> updatePassword (@PathVariable int user_id, @RequestBody PasswordUpdate passwordUpdate) throws UpdatePasswordException {
		try {
			userService.updatePassword(user_id, passwordUpdate.getOldPassword(), passwordUpdate.getNewPassword());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UpdatePasswordException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
 }