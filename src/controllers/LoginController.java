package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	 private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	  @RequestMapping("/login")
	  public String login() {
	    return "login";
	  }
	  
	  @RequestMapping("/login-error")
	  public String loginError(Model model) {
		logger.warn("Failed login procedure");
	    model.addAttribute("loginError", true);
	    return "login";
	  }
}
