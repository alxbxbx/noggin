package com.noggin.rest;

import com.noggin.models.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public User login(@RequestBody User user) {
        if (user.getUsername().equals("firge") && user.getPassword().equals("firge")) {
            return user;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json")
    public User logout(@RequestBody User user) {
        return user;
        // else return null
    }

}
