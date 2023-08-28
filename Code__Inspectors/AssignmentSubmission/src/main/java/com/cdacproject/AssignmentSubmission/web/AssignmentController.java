package com.cdacproject.AssignmentSubmission.web;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdacproject.AssignmentSubmission.dto.AssignmentResponseDto;
import com.cdacproject.AssignmentSubmission.entities.Assignment;
import com.cdacproject.AssignmentSubmission.entities.User;
import com.cdacproject.AssignmentSubmission.enums.AuthorityEnum;
import com.cdacproject.AssignmentSubmission.service.AssignmentService;
import com.cdacproject.AssignmentSubmission.service.UserService;
import com.cdacproject.AssignmentSubmission.util.AuthorityUtil;

@RestController
@RequestMapping("api/assignments")
public class AssignmentController {
	
	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private UserService userService;
	
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
		
		return ResponseEntity.ok(new AssignmentResponseDto(assignmentOpt.orElse(new Assignment()))); 
	}
	
	@PutMapping("{assignmentId}")
	public ResponseEntity<?> updateAssignments( @PathVariable Long assignmentId, @RequestBody Assignment assignment, @AuthenticationPrincipal User user){
		
		//add the code reviewer to this assignment if it was claimed 
		if(assignment.getCodeReviewer() != null) {
			User codeReviewer = assignment.getCodeReviewer();
			
			codeReviewer = userService.findUserByUsername(codeReviewer.getUsername()).orElse(new User());
			if(AuthorityUtil.hasRole(AuthorityEnum.ROLE_CODE_REVIEWER.name(), codeReviewer)) {
				
				assignment.setCodeReviewer(codeReviewer);
			}
			
			
			
		}
	
	Assignment updatedAsignment =	assignmentService.save(assignment);
	
	return ResponseEntity.ok(updatedAsignment );
		
	}
	

}
