package com.noggin.rest;

import com.noggin.dao.repositories.IUser;
import com.noggin.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AuthController {

    @Autowired
    private IUser iu;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public User login(@RequestBody User user, HttpSession session) {
        User finalUser = null;
        for (User dbUser : iu.findAll()) {
            if (dbUser.getUsername().equals(user.getUsername()) && dbUser.getPassword().equals(user.getPassword())) {
                // start session
                dbUser.setPassword("");
                finalUser = dbUser;
                session.setAttribute("auth", finalUser);
                break;
            }
        }
        return finalUser;
    }

    @RequestMapping(value = "/login-check", method = RequestMethod.GET, produces = "application/json")
    public String loginCheck(HttpSession session) {
        User user = (User) session.getAttribute("auth");
        if (user != null) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json")
    public void logout(HttpSession session) {
        session.removeAttribute("auth");
    }

}
