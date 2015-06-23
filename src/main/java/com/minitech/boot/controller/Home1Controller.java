package com.minitech.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class Home1Controller {

	@RequestMapping("/home")
	String home() {
		return "home";

	}
	
	@RequestMapping("/welcome")
	String welcome() {
		return "welcome";

	}
}
