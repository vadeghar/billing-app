//package com.mytest.billapp.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	public void configureGloba(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.inMemoryAuthentication()
//		.withUser("devuser").password("{noop}dev").authorities("ROLE_USER")
//		.and()
//		.withUser("adminuser").password("{noop}admin").authorities("ROLE_USER","ROLE_ADMIN");
//	}
//	
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/*/**").hasAnyRole("ADMIN","USER")
//		.and().formLogin()  //login configuration
//        .loginPage("/WEB-INF/pages/login.jsp")
//        .loginProcessingUrl("/appLogin")
//        .usernameParameter("app_username")
//        .passwordParameter("app_password")
//        .defaultSuccessUrl("/home")	
//		.and().logout()    //logout configuration
//		.logoutUrl("/appLogout") 
//		.logoutSuccessUrl("/WEB-INF/pages/login.jsp")
//		.and().exceptionHandling() //exception handling configuration
//		.accessDeniedPage("/WEB-INF/pages/accessDenied.jsp");
//		/*http
//		.authorizeRequests().antMatchers("/admin*").hasRole("ADMIN")
//		.antMatchers("/user*").hasRole("USER")
//		.antMatchers("/**").permitAll()
//		.and()
//		.httpBasic();*/
//	}
//
//}
