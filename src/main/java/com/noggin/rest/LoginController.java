package com.noggin.rest;

import com.noggin.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public User post() {

        User user = new User();
        user.setUsername("dick");
        user.setPassword("dick123");

        return user;
    }

}
