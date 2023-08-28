package com.cdacproject.AssignmentSubmission.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdacproject.AssignmentSubmission.entities.User;
import com.cdacproject.AssignmentSubmission.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository  userRepo;
	

	public Optional<User> findUserByUsername(String username) {
		
		return userRepo.findByusername(username);
		
		
		
		
	}
}
