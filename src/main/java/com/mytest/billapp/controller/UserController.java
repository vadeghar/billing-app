package com.mytest.billapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.User;
import com.mytest.billapp.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/userList")
	public String getAllUser(Model model) {
		model.addAttribute("userList", userService.findAll());
	    return "userList";
	}
	
	//@PostMapping("/saveUser")
	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute User user, Model model) {
		System.out.println("DFDFD");
		model.addAttribute("user", userService.save(user));
	    return "user";
	}
	
	@GetMapping("/user/{id}")
	public String getUserById(@PathVariable(value = "id") Long id, Model model) {
		if(id == null || id.intValue() == 0)
			model.addAttribute("user", new User());
		model.addAttribute("user", userService.findById(id));
	    /*return noteService.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));*/
		return "user";
	}
	
	@PutMapping("/user/update/{id}")
	public String updateUser(@PathVariable(value = "id") Long id,  @Valid @ModelAttribute User user, Model model) {

	    User user1 = userService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    //user1.setTitle(user.getTitle());
	    //user1.setContent(user.getContent());
	    model.addAttribute("user",userService.save(user1));
	    return "user";
	}
	
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id, Model model) {
	    User user = userService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	    userService.delete(user);
	    return getAllUser(model);
	}
}
