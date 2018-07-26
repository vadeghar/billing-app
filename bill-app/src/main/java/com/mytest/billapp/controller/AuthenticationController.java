package com.mytest.billapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {
	
	@RequestMapping(value="login",  method = RequestMethod.GET)
    public String login() {
        return "login";
    }
	
	@RequestMapping(value="logout",  method = RequestMethod.GET)
    public String logout() {
        return "login";
    }
	
	@RequestMapping(value="accessDenied",  method = RequestMethod.GET)
    public String accessDenied() {
        return "login";
    }
}
