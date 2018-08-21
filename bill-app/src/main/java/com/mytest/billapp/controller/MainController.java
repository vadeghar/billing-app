package com.mytest.billapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.dto.SessionUser;
import com.mytest.billapp.model.User;
import com.mytest.billapp.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
 
    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String homePage(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		
		model.addAttribute("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
		if(user.hasRole("ADMIN"))
			return "dashboard1Home";
		else
			return "userHome";
    }
    
    /*@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}*/
    
    @RequestMapping(value = { "contactus" }, method = RequestMethod.POST)
    public String contactusPage(Model model) {
    	model.addAttribute("name", "Lakshman.V");
        model.addAttribute("address", "Hyderabad");
        model.addAttribute("phone", "...");
        model.addAttribute("email", "...");
        return "contactusPage";
    }
    
    @RequestMapping(value = { "error" }, method = RequestMethod.POST)
    public String error(Model model) {
        model.addAttribute("message", "Error");
        return "error";
    }
    
    @RequestMapping(value = { "error" }, method = RequestMethod.GET)
    public String error1(Model model) {
        model.addAttribute("message", "Error");
        return "error";
    }
}
