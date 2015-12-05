package com.noggin.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session) {
		System.out.println("login triggered");
		session.setAttribute("t1key", "t1value");
		System.out.println("*** Session data ***");
		  Enumeration<String> e = session.getAttributeNames();
		  while (e.hasMoreElements()){
			String s = e.nextElement();
			System.out.println(s);
			System.out.println("**" + session.getAttribute(s));
		  }
		return "index";
	}
	
}
