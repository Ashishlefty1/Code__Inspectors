package com.cdacproject.AssignmentSubmission.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cdacproject.AssignmentSubmission.filter.JwtFilter;
import com.cdacproject.AssignmentSubmission.util.CustomPasswordEncoder;

@EnableWebSecurity // to enable spring security support
@Configuration // to tell sc below given class contains @Bean annotated method
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private  CustomPasswordEncoder customPasswordEncoder;
	
	@Autowired
	private  UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Override @Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		
		return super.authenticationManagerBean();
		
	} 

	@Override // this method for authentication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder.getPasswordEncoder());
	}

	@Override // This method is for authorization
	protected void configure(HttpSecurity http) throws Exception {
		
		http = http.csrf().disable().cors().disable();
		http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		
		//AuthenticationEntryPoint auth;
		
		http = http.exceptionHandling().authenticationEntryPoint((request,response,ex)->{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage());
		}).and();
		
		http.authorizeRequests()
		.antMatchers("/api/auth/**").permitAll()
		.antMatchers("/api/**").permitAll()
		
		.anyRequest().authenticated();
		
//		http.authorizeRequests().anyRequest().authenticated();
		
		http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class); 
	
	}

  
//	@Bean 
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//		
//	}
 

  }







	
		
	
	
   
