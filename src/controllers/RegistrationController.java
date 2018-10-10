package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private Map<String,String> ret;
	private Map<String,String> registrationCheck;
	
	public RegistrationController () {
		ret = new HashMap<String,String>();
		registrationCheck = new HashMap<String,String>();
	}
	
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
	public @ResponseBody Map<String,String> RegistrationRest (@RequestBody User user) {
		System.out.println("OKOK");
		System.out.println("User: " + user.getUsername());
		//db.loadUsers();
		if(db.addUser(user)) {
			ret.put("status", "ok");
		} else {
			ret.put("status", "nok");
		}
		
		return ret;
	}
	// example http://localhost:8080/registrationCheck/a
	@RequestMapping(value = "/registrationCheck/{userName}", method=GET)
	public @ResponseBody Map<String,String> RegistrationCheck (@PathVariable(value="userName") String userName) {
		logger.trace("Registration Check Android user " + userName);
		System.out.println("Registration Check Android user " + userName);
		registrationCheck.put("status", db.checkUserStatus(userName));
		return registrationCheck;
	}
}