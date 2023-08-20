package com.cdacproject.AssignmentSubmission.config;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.cdacproject.AssignmentSubmission.util.CustomPasswordEncoder;

@EnableWebSecurity // to enable spring security support
@Configuration // to tell sc below given class contains @Bean annotated method
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private  CustomPasswordEncoder customPasswordEncoder;
	
	@Autowired
	private  UserDetailsService userDetailsService;

	@Override // this method for authentication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder.getPasswordEncoder());
	}

	@Override // This method is for authorization
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		super.configure(httpSecurity);

	}

  
//	@Bean 
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//		
//	}
 

  }
 
