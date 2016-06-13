package com.scmspain.learning.controller;

import com.scmspain.learning.controller.request.UserLoginRequest;
import com.scmspain.learning.entities.User;
import com.scmspain.learning.security.UserSession;
import com.scmspain.learning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {
    private UserSession userSession;
    private UserService userService;

    @Autowired
    public UserController(UserSession userSession, UserService userService) {
        this.userSession = userSession;
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String viewLoginPage() {
        if (userSession == null || userSession.getId() == null) {
            return "login/login";
        } else {
            return "redirect:/framework/list";
        }
    }

    @RequestMapping(value = "/login.check", method = POST)
    public String doLogin(@ModelAttribute UserLoginRequest request, ModelMap model) {
        User user = userService.getUserByUsername(request.getUser());
        if (user == null) {
            model.put("error", "User does not exist");
            return "login/login";
        }

        if (!user.getPassword().equals(request.getPassword())) {
            model.put("error", "User password does not match");
            return "login/login";
        }

        userSession.setId(user.getId());
        userSession.setToken(UUID.randomUUID().toString());

        return "redirect:/framework/list";
    }
}
