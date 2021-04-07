package controllers;

import controllers.renderers.*;
import dao.entities.Notification;
import dao.entities.Users;
import exceptions.RemoveAllAdminsException;
import exceptions.UpdatePasswordException;
import model.PasswordUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.FollowersService;
import services.ManageUsersService;
import services.NotificationService;

import java.util.List;
import java.util.Map;

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
	
	@Autowired
	private ConnectorDetailsRenderer connectorsDetails;

	@Autowired
	private FollowedNotificationsRenderer followedNotifications;

	@Autowired
	private NotificationsRenderer notificationsRenderer;

	@Autowired
	private FollowersService followersService;

	@Autowired
	private NotificationService notificationService;
	
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
	
	@GetMapping(value = "/connectors", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getConnectorsDetails() {
		return ResponseEntity.ok().body(connectorsDetails.getJSON());
	}

	@GetMapping(value = "/followednotifications", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getFollowedNotifications(@RequestParam("user_id") int user_id) {
		return ResponseEntity.ok().body(followedNotifications.getJSON(user_id));
	}

	@PatchMapping(value = "/followednotifications/{user_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateFollowedNotifications(
			@PathVariable("user_id") String user_id,
			@RequestBody Map<String, Boolean> changedEntity) {
			followersService.updateFollowedEntities(Integer.parseInt(user_id), changedEntity);
			return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/notifications/list", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getNotificationsList(@RequestParam("user_id") int user_id) {
		return ResponseEntity.ok().body(notificationsRenderer.getJSON(user_id));
	}

	@GetMapping(value = "/notifications/size", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getNotificationsSize(@RequestParam("user_id") int user_id) {
		return ResponseEntity.ok().body(notificationsRenderer.getSizeJson(user_id));
	}

	@PatchMapping(value = "/notifications", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> updateReadStatus (@RequestBody List<Notification> notifications) {
		notifications.forEach(notification -> {
			notificationService.changeReadStatus(notification.getId(), notification.isRead());
		});
		return new ResponseEntity<>(HttpStatus.OK);
	}

}