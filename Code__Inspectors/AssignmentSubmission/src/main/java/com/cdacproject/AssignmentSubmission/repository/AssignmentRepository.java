package com.cdacproject.AssignmentSubmission.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdacproject.AssignmentSubmission.entities.Assignment;
import com.cdacproject.AssignmentSubmission.entities.User;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	
	Set<Assignment>findByUser(User user);
	
	

}
