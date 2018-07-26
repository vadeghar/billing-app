package com.mytest.billapp.config;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
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
     public void configure(WebSecurity web) throws Exception {
         web.ignoring()
             .antMatchers("/static/**")
             .antMatchers(HttpMethod.GET, "/public/**")
             .antMatchers(HttpMethod.GET, "/index.html");
     }
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin*").hasAnyRole("ADMIN","USER")
		.antMatchers("/user*").hasAnyRole("USER")
		.and()
		.formLogin() .loginPage("/login").failureUrl("/login?error").permitAll()
      //  .loginProcessingUrl("/appLogin")
        .usernameParameter("app_username")
        .passwordParameter("app_password")
        //.defaultSuccessUrl("/home")	
		.and()
		 .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
		.and()
		.exceptionHandling().accessDeniedPage("/accessDenied");
	
	}
	 protected static class MyUserDetailsService implements UserDetailsService {
	        private Log log = LogFactory.getLog(this.getClass()); 

	        @Override
	        public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	            log.info("Getting user details for username: " + username);
	            
	            final UserDetails user = new UserDetails() {
	                private static final long serialVersionUID = 2175883491891532704L;

	                @Override
	                public Collection<? extends GrantedAuthority> getAuthorities() {
	                    log.info("asked for authorities of user: " + getUsername());
	                    final Collection<GrantedAuthority> ga = new HashSet<GrantedAuthority>();
	                    if ("mark".equalsIgnoreCase(username)) {
	                        log.info("This user is an administrator!");
	                        ga.add(ADMIN_ROLE);
	                    }
	                    ga.add(USER_ROLE);
	                    return ga;
	                }

	                @Override
	                public String getPassword() {
	                    return "password1";
	                }

	                @Override
	                public String getUsername() {
	                    return username;
	                }

	                @Override
	                public boolean isAccountNonExpired() {
	                    return true;
	                }

	                @Override
	                public boolean isAccountNonLocked() {
	                    return true;
	                }

	                @Override
	                public boolean isCredentialsNonExpired() {
	                    return true;
	                }

	                @Override
	                public boolean isEnabled() {
	                    return true;
	                }
	                
	            };

	            return user;
	        }
	    }
	 private static final GrantedAuthority ADMIN_ROLE = new GrantedAuthority() {
	        private static final long serialVersionUID = 8023713081696379175L;
	        private Log log = LogFactory.getLog(this.getClass());

	        @Override
	        public String getAuthority() {
	            log.info("Returning ROLE_ADMIN");
	            return "ROLE_ADMIN";
	        }
	    };

	    private static final GrantedAuthority USER_ROLE = new GrantedAuthority() {
	        private static final long serialVersionUID = 226992918792674250L;
	        private Log log = LogFactory.getLog(this.getClass());

	        @Override
	        public String getAuthority() {
	            log.info("Returning ROLE_USER");
	            return "ROLE_USER";
	        }
	    };
}
