package com.example.test.task.controller;

import com.example.test.task.service.PasswordStrengthService;
import com.example.test.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordStrengthService passwordStrengthService;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView defaultPage(ModelAndView model) {
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView protectedPage(ModelAndView model) {
        model.addObject("title", "Hello world");
        model.addObject("message", "Hello world");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("hello");

        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "registered", required = false) String registered,
                              ModelAndView model) {
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        if (registered != null) {
            model.addObject("msg", "User registered. Please log in.");
        }

        model.setViewName("login");

        return model;

    }


}