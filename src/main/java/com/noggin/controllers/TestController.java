package com.noggin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/welcome")
	public String helloWorld(Model model) {
		model.addAttribute("message", "Hello World!");
		return "index";
	}

}
