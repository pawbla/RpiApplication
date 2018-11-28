package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import users.ManageUserDB;
import users.User;


@Controller
public class RegistrationController {
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	private ManageUserDB db;
	
	/**
	 * Register user GET controller
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registration", method=GET)
	public String Registration(Model model) {
		logger.trace("Registration controller Test start");
		model.addAttribute("user", new User());
		return "registration";
	}
	
	/**
	 * Register user POST controller
	 * @param user
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/registration", method=POST)
	public String ProcessRegistration(User user, Model model, RedirectAttributes redirectAttributes) {
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			redirectAttributes.addFlashAttribute("userRegisteredError", true);
			return "redirect:/registration";
		} 
		if (db.addUser(user) == false) {
			logger.debug("User existed");
			redirectAttributes.addFlashAttribute("userExisted", true);
			return "redirect:/registration";
		}
		logger.debug("User add");
		redirectAttributes.addFlashAttribute("userRegistered", true);
		return "redirect:/login";
	}
	
	/**
	 * Register user REST endpoint for Android app
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/registrationRest", method=POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseEntity<?> RegistrationRest (@RequestBody User user) {
		HttpStatus status;
		logger.debug("User: " + user.getUsername());
		if(db.addUser(user)) {
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.CONFLICT;
		}
		
		return new ResponseEntity<String>(status);
	}
	
	/**
	 * Check available user REST controller for Android app
	 * @param userName
	 * @return
	 */
	// example http://localhost:8080/registrationCheck/a
	@RequestMapping(value = "/registrationCheck/{userName}", method=GET)
	public @ResponseBody ResponseEntity<?> RegistrationCheck (@PathVariable(value="userName") String userName) {
		logger.debug("UserTrace - Registration Check Android user " + userName);
		return new ResponseEntity<String>(db.checkUserStatus(userName));
	}
}