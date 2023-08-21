package com.cdacproject.AssignmentSubmission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdacproject.AssignmentSubmission.entities.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	
	

}
