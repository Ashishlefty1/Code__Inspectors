package com.cdacproject.AssignmentSubmission.service;

import java.util.Optional;
import java.util.Set;

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
	public Set<Assignment> findByUser(User user){
		
		return assignmentRepo.findByUser(user);
	}
	
	public Optional<Assignment> findById(Long assignmentId) {
		return assignmentRepo.findById(assignmentId);
		
	}
	
	public Assignment save(Assignment assignment) {
		
	return 	assignmentRepo.save(assignment);
		
		
	}

}
