package com.noggin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		String message = "omfg";
		return new ModelAndView("index", "message", message);
	}
	
}
