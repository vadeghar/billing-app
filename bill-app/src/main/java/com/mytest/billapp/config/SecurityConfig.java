package com.mytest.billapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public void configureGloba(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication()
		.withUser("devuser").password("{noop}dev").authorities("ROLE_USER")
		.and()
		.withUser("adminuser").password("{noop}admin").authorities("ROLE_USER","ROLE_ADMIN");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin*").hasAnyRole("ADMIN","USER")
		.antMatchers("/user*").hasAnyRole("USER")
		.and().formLogin()  //login configuration
        .loginPage("/login")
        .loginProcessingUrl("/appLogin")
        .usernameParameter("app_username")
        .passwordParameter("app_password")
        .defaultSuccessUrl("/home")	
		.and().logout()    //logout configuration
		.logoutUrl("/logout") 
		.logoutSuccessUrl("/login")
		.and().exceptionHandling() //exception handling configuration
		.accessDeniedPage("/accessDenied");
		/*http
		.authorizeRequests().antMatchers("/admin*").hasRole("ADMIN")
		.antMatchers("/user*").hasRole("USER")
		.antMatchers("/**").permitAll()
		.and()
		.httpBasic();*/
	}

}
