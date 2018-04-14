package com.mytest.billapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
 
    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homePage(Model model) {
        return "homePage";
    }
 
     
    @RequestMapping(value = { "/contactus" }, method = RequestMethod.POST)
    public String contactusPage(Model model) {
        model.addAttribute("address", "Vietnam");
        model.addAttribute("phone", "...");
        model.addAttribute("email", "...");
        return "contactusPage";
    }
    
    @RequestMapping(value = { "/error" }, method = RequestMethod.POST)
    public String error(Model model) {
        model.addAttribute("message", "Error");
        return "error";
    }
    
    @RequestMapping(value = { "/error" }, method = RequestMethod.GET)
    public String error1(Model model) {
        model.addAttribute("message", "Error");
        return "error";
    }
}
