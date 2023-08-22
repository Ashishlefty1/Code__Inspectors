package com.cdacproject.AssignmentSubmission.web;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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
	
	@GetMapping("")
	public ResponseEntity<?> getAssignments(@AuthenticationPrincipal User user){
		
		Set<Assignment> assignmentByUser = assignmentService.findByUser(user);
		return ResponseEntity.ok(assignmentByUser); 
	}
	
	@GetMapping("{assignmentId}")
	public ResponseEntity<?> getAssignments( @PathVariable Long assignmentId , @AuthenticationPrincipal User user){
		Optional<Assignment> assignmentOpt = assignmentService.findById(assignmentId);
	
		return ResponseEntity.ok(assignmentOpt.orElse(new Assignment())); 
	}
	
	@PutMapping("{assignmentId}")
	public ResponseEntity<?> updateAssignments( @PathVariable Long assignmentId, @RequestBody Assignment assignment, @AuthenticationPrincipal User user){
		
	Assignment updatedAsignment =	assignmentService.save(assignment);
	
	return ResponseEntity.ok(updatedAsignment );
		
	}
	

}
