package com.mytest.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.model.User;
import com.mytest.billapp.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
 
    @RequestMapping(value = { "/admin/home" }, method = RequestMethod.GET)
    public String homePage(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());
		model.addAttribute("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
        return "homePage";
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
    
    @RequestMapping(value = { "user/contactus" }, method = RequestMethod.POST)
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
