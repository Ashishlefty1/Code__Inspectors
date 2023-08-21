package com.cdacproject.AssignmentSubmission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdacproject.AssignmentSubmission.entities.Assignment;
import com.cdacproject.AssignmentSubmission.entities.User;
import com.cdacproject.AssignmentSubmission.repository.AssignmentRepository;

@Service
public class AssignmentService {
	
	@Autowired
	private AssignmentRepository assignmentRepo; 
	
	public Assignment save(User user) {
		Assignment assignment =new Assignment();
		assignment.setStatus("Pending");
		assignment.setUser(user);
		
		return assignmentRepo.save(assignment);
	}

}
