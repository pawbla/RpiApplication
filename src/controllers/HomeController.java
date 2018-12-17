package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sysInfo.ErrorMessage;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {

	@RequestMapping(method=GET)
	public String home(Model model) throws FileNotFoundException {
		return "home";
	}
}