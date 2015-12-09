package com.noggin.controllers;

import com.noggin.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {

        User user = new User();
        user.setId(1);
        user.setFirstName("Johny");
        user.setLastName("Depp");
        user.setPassword("123");
        user.setUsername("johnydepp1");
        user.setCategory(null);

		request.getSession().setAttribute("loggedUser", user);

		return "redirect:/";
	}
	
}
