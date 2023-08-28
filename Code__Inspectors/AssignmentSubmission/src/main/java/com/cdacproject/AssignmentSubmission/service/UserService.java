package com.codereview.AssignmentSubmissionApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codereview.AssignmentSubmissionApp.domain.Authority;
import com.codereview.AssignmentSubmissionApp.domain.User;
import com.codereview.AssignmentSubmissionApp.dto.UserDto;
import com.codereview.AssignmentSubmissionApp.repository.AuthorityRepository;
import com.codereview.AssignmentSubmissionApp.repository.UserRepository;
import com.codereview.AssignmentSubmissionApp.util.CustomPasswordEncoder;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepo;
   
    @Autowired
    private AuthorityRepository authorityRepo;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
	
		public Optional<User> findUserByUsername(String username) {
			return userRepo.findByUsername(username);
		}
		
		 public User createUser(UserDto userDto) {
		        User newUser = new User();
		        newUser.setUsername(userDto.getUsername());
		        newUser.setName(userDto.getName());
		        String encodedPassword = customPasswordEncoder.getPasswordEncoder().encode(userDto.getPassword());
		        newUser.setPassword(encodedPassword);
		       User persistUser = userRepo.save(newUser);
		        Authority authority = new Authority();
		        
		        if(userDto.getRole().equals("ROLE_STUDENT")) {
		        	authority.setAuthority("ROLE_STUDENT");
		        	}
		        
		        else {authority.setAuthority("ROLE_CODE_REVIEWER");}
		        
		       
		        
		        authority.setUser(newUser);
		        authorityRepo.save(authority);
		        
		        return  persistUser;

		    }
		
}
