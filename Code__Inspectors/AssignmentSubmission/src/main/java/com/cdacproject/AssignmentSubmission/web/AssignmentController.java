package com.cdacproject.AssignmentSubmission.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdacproject.AssignmentSubmission.entities.Assignment;
import com.cdacproject.AssignmentSubmission.entities.User;
import com.cdacproject.AssignmentSubmission.service.AssignmentService;

@RestController
@RequestMapping("api/assignments")
public class AssignmentController {
	
	@Autowired
	AssignmentService assignmentService;
	
	@PostMapping("")
	public ResponseEntity<?> createAssignment(@AuthenticationPrincipal User user){
		
		Assignment newAssignment = assignmentService.save(user);
		return ResponseEntity.ok(newAssignment);
		
	}

}
