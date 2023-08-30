package com.cdacproject.AssignmentSubmission.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdacproject.AssignmentSubmission.dto.UserDto;
//import com.cdacproject.AssignmentSubmission.entities.User;
import com.cdacproject.AssignmentSubmission.service.UserService;
//import com.cdacproject.AssignmentSubmission.util.JwtUtil;

@RestController
@RequestMapping("/api/users")

public class UserController {
	@Autowired
    private UserService userService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private JwtUtil jwtUtil;

	 @PostMapping("/register")
	    private ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
	       
		userService.createUser(userDto);
   
	        
	        return ResponseEntity.ok(userDto);
	    }
	
}
