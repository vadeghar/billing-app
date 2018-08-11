package com.mytest.billapp.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mytest.billapp.dto.SessionUser;
import com.mytest.billapp.model.User;
import com.mytest.billapp.service.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/* @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
		public ModelAndView login(){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("login");
			return modelAndView;
		}
	 */
	 
	@RequestMapping(value={"/", "/login"},  method = RequestMethod.GET)
    public String login() {
		String page = "login";
		String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal == null) return page;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        if(userName == null || userName.equals("anonymousUser"))
        	return page;
        else {
        	return "home";
        }
    }
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			String encodePwd = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodePwd);
			userService.save(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="logout",  method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
		httpSession.removeAttribute("sessionUser");
        return "login";
    }
	
	@RequestMapping(value="accessDenied",  method = RequestMethod.GET)
    public String accessDenied() {
        return "accessDenied";
    }
}
