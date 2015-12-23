package com.noggin.rest;

import com.noggin.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login() {

        User user = null;

        user = new User();
        user.setUsername("filip01");
        user.setPassword("nh2b09825");

        return user;
    }

}
