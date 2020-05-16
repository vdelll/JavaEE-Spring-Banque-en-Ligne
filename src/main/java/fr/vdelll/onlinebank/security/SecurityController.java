package fr.vdelll.onlinebank.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	
	@RequestMapping(value = "/")
	public String home() {
		return "redirect:/operations";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

}
