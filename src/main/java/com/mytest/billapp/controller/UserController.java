package com.mytest.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.User;
import com.mytest.billapp.service.UserService;

@Controller
@RequestMapping(value="admin/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "userList", method = RequestMethod.POST)
	public String getAllUser(Model model) {
		model.addAttribute("userList", userService.findAll());
		model.addAttribute("user", new User());
		model.addAttribute("selectedId","");
	    return "user";
	}
	
	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute User user, Model model) {
		try {
			userService.save(user);
			model.addAttribute("user", new User());
			model.addAttribute("userList", userService.findAll());
			model.addAttribute("message", "Succesfully Saved.");
			model.addAttribute("selectedId","");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "user";
		}
	    return "user";
	}
	
	
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		if(selectedId == null || selectedId.intValue() == 0)
			model.addAttribute("user", new User());
		try {
			model.addAttribute("user", userService.findById(selectedId));
			model.addAttribute("userList", userService.findAll());
			model.addAttribute("selectedId","");
			model.addAttribute("message", "");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "user";
		}
		return "user";
	}
	
	
	@RequestMapping(value = "deleteUser", method = RequestMethod.POST)
	public String deleteUser(@ModelAttribute("selectedId") Long selectedId, Model model) {
	    try {
			User user = userService.findById(selectedId);
			        //.orElseThrow(() -> new ResourceNotFoundException("Note", "selectedId", selectedId));
			userService.delete(user);
			model.addAttribute("userList", userService.findAll());
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return getAllUser(model);
		}
	    return addUser(0l, model);
	}
}
