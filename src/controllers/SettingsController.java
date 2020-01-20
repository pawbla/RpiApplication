package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import users.ManageUserDB;
import users.User;

//@Controller
public class SettingsController {
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	private ManageUserDB db;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	@RequestMapping(value = "/settings", method=GET)
	public String settings(Model model) {
		db.loadUsers();
		model.addAttribute("user", new User());	
		model.addAttribute("userDatas", db);
		return "settings";
	}

	@RequestMapping(value = "/settingsPass", method=POST)
	public String changePassword (User user, Model model, RedirectAttributes redirectAttributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    user.setUsername(auth.getName());
		if (user.getNewPass1().length() < 8 || user.getNewPass1().length() > 40) {
			logger.debug("Incorrect password length");
			redirectAttributes.addFlashAttribute("passLengthError", true);			
		} else {
		    if ((user.getNewPass1().equals(user.getNewPass2())) && (passwordEncoder.matches(user.getOldPass(), db.getPassword(user)))) {
				db.setPassword(passwordEncoder.encode(user.getNewPass1()), auth.getName());
				redirectAttributes.addFlashAttribute("confirmPassChange", true);
			} else {
				redirectAttributes.addFlashAttribute("passError", true);
			}
		}
	    user.setOldPass(null);
	    user.setNewPass1(null);
	    user.setNewPass1(null);
		return "redirect:/settings";
	}
	
	@RequestMapping(value = "/settingsEmail", method=POST)
	public String changeEmail (User user, Model model, RedirectAttributes redirectAttributes) {
		if (!user.getEmail().isEmpty() && !db.validadeEMail(user.getEmail())) {
			redirectAttributes.addFlashAttribute("userEmailError", true);
			return "redirect:/settings";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    user.setUsername(auth.getName());
	    db.setEmail(user);
	    redirectAttributes.addFlashAttribute("confirmEmailChange", true);
	    return "redirect:/settings";
	}
	
	@RequestMapping(value = "/settingsUsers", method=POST)
	public String setUsers (@ModelAttribute ManageUserDB userDatas, User user, Model model, RedirectAttributes redirectAttributes) {
		String msg = db.updateEnabled(userDatas.getUsers());
		redirectAttributes.addFlashAttribute("confirmUsersChange", true);
		redirectAttributes.addFlashAttribute("confirmUsrChMsg", msg);
	    return "redirect:/settings";
	}	
	
	@RequestMapping(value = "/settingsDeleteUsers", method=POST)
	public String deleteUser (User user, RedirectAttributes redirectAttributes) {
		logger.debug("Delete user: " + user.getUsername());
		db.removeUser(user);
		redirectAttributes.addFlashAttribute("deleteUsersChange", true);
		redirectAttributes.addFlashAttribute("deleteUsrChMsg", "Użytkownik '" + user.getUsername() + "' został usunięty.");
		return "redirect:/settings";
	}
}