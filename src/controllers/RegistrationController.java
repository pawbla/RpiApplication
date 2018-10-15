package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import users.ManageUserDB;
import users.User;


@Controller
public class RegistrationController {
	private static final Logger logger = LogManager.getLogger(RegistrationController.class);
	
	@Autowired
	private ManageUserDB db;
	
	@RequestMapping(value = "/registration", method=GET)
	public String Registration(Model model) {
		logger.trace("Registration controller Test start");
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping(value="/registration", method=POST)
	public String ProcessRegistration(User user, Model model) {
		//db.loadUsers();
		model.addAttribute("userRegistered", db.addUser(user));
		return "registration";
	}
	
	@RequestMapping(value="/registrationRest", method=POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseEntity<?> RegistrationRest (@RequestBody User user) {
		HttpStatus status;
		System.out.println("OKOK");
		System.out.println("User: " + user.getUsername());
		//db.loadUsers();
		if(db.addUser(user)) {
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.CONFLICT;
		}
		
		return new ResponseEntity<String>(status);
	}
	// example http://localhost:8080/registrationCheck/a
	@RequestMapping(value = "/registrationCheck/{userName}", method=GET)
	public @ResponseBody ResponseEntity<?> RegistrationCheck (@PathVariable(value="userName") String userName) {
		logger.trace("Registration Check Android user " + userName);
		return new ResponseEntity<String>(db.checkUserStatus(userName));
	}
}