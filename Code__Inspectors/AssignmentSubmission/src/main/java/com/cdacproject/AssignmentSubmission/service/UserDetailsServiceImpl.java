package com.cdacproject.AssignmentSubmission.service;


//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.cdacproject.AssignmentSubmission.entities.User;
import com.cdacproject.AssignmentSubmission.repository.UserRepository;
//import com.cdacproject.AssignmentSubmission.util.CustomPasswordEncoder;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
//	@Autowired
//	private CustomPasswordEncoder passwordEncoder;
	
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByusername(username).orElseThrow(()->new UsernameNotFoundException("Invalid credentials"));
		
	}

}
 