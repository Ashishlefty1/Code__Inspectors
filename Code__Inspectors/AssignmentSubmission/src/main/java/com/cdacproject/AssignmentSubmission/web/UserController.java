package com.codereview.AssignmentSubmissionApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codereview.AssignmentSubmissionApp.domain.User;
import com.codereview.AssignmentSubmissionApp.dto.UserDto;
import com.codereview.AssignmentSubmissionApp.service.UserService;
import com.codereview.AssignmentSubmissionApp.util.JwtUtil;

@RestController
@RequestMapping("/api/users")

public class UserController {
	@Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

	 @PostMapping("/register")
	    private ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
	        User newUser = userService.createUser(userDto);

//	        try {
//	            Authentication authenticate = authenticationManager
//	                    .authenticate(
//	                            new UsernamePasswordAuthenticationToken(
//	                                    userDto.getUsername(), userDto.getPassword()
//	                            )
//	                    );
//
//	            User user = (User) authenticate.getPrincipal();
//	            user.setPassword(null);
//	            return ResponseEntity.ok()
//	                    .header(
//	                            HttpHeaders.AUTHORIZATION,
//	                            jwtUtil.generateToken(user)
//	                    )
//	                    .body(user);
//	        } catch (BadCredentialsException ex) {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	        }
	        
	        //return ResponseEntity.ok(newUser);
	       // System.out.println("new user created" + newUser  ); 
	        
	        return ResponseEntity.ok(userDto);
	    }
	
}
