package com.mytest.billapp.config;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.mytest.billapp.dto.SessionUser;
import com.mytest.billapp.service.UserService;

@Component 
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>{

	@Autowired
    private HttpSession session;
	
	@Autowired
	UserService userService;
	
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent evt) {

        // if you just need the login
        String login = evt.getAuthentication().getName();
        System.out.println(login + " has just logged in");

        // if you need to access full user (ie only roles are interesting -- the rest is already verified as login is successful)
        User user = (User) evt.getAuthentication().getPrincipal();
        System.out.println(user.getUsername() + " has just logged in");
        

        if(user.getUsername() == null || user.getUsername().equals("anonymousUser")) return;
        SessionUser sessionUser = userService.getUserDetails(user.getUsername());
		session.setAttribute("sessionUser", sessionUser);

    } 
}
