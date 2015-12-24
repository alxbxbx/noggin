package com.noggin.rest;

import com.noggin.dao.repositories.IUser;
import com.noggin.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private IUser iu;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public User login(@RequestBody User user) {
        User finalUser = null;
        for (User dbUser : iu.findAll()) {
            if (dbUser.getUsername().equals(user.getUsername()) && dbUser.getPassword().equals(user.getPassword())) {
                // start session
                dbUser.setPassword("");
                finalUser = dbUser;
                System.out.println("1) I was here.");
                break;
            }
        }
        System.out.println("2) I was here.");
        return finalUser;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json")
    public User logout(@RequestBody User user) {
        return user;
        // else return null
    }

}
