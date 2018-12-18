package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sysInfo.ErrorMessage;

@Controller
@RequestMapping({"sysinfo"})
public class SysInfoController {
	
	@Autowired
	private ErrorMessage errors;

	@RequestMapping(method=GET)
	public String home(Model model) {
		model.addAttribute("errorsList", errors.getWarnigs(40));
		return "sysinfo";
	}	
}
