package com.example.test.task.controller;

import com.example.test.task.domain.Strength;
import com.example.test.task.service.PasswordStrengthService;
import com.example.test.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordStrengthService passwordStrengthService;

    @Value("${regerror.emptyuser}")
    private String emptyUserMsg;

    @Value("${regerror.emptypassword}")
    private String emptyPasswordMsg;

    @Value("${regerror.badpassword}")
    private String badPasswordMsg;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerForm(ModelAndView model) {
        model.addObject("str", "low");
        model.setViewName("register");

        return model;
    }

    @RequestMapping(value = "/password-str", method = RequestMethod.POST)
    @ResponseBody
    public String checkStrength(@RequestParam(value = "password", required = true) String password) {
        return passwordStrengthService.checkStrenght(password).toString().toLowerCase();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam(value = "username", required = false) String username,
                                 @RequestParam(value = "password", required = false) String password,
                                 ModelAndView model) {

        if( username==null || "".equals(username) ) {
            model.addObject("error", emptyUserMsg);
            model.setViewName("register");
        } else if( password==null || "".equals(password) ) {
                model.addObject("error", emptyPasswordMsg);
                model.setViewName("register");
        } else if( !Strength.HIGH.equals(passwordStrengthService.checkStrenght(password)) ) {
            model.addObject("error", badPasswordMsg);
            model.setViewName("register");
        } else {
            userService.createNewUser(username, password);
            model.addObject("msg", "User registered. Please log in.");
            model.setViewName("login");
        }

        return model;
    }

}