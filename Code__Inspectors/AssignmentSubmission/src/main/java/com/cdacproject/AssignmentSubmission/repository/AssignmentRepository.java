package com.cdacproject.AssignmentSubmission.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cdacproject.AssignmentSubmission.entities.Assignment;
import com.cdacproject.AssignmentSubmission.entities.User;
import com.cdacproject.AssignmentSubmission.enums.AssignmentStatusEnum;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	
	 final String str =  AssignmentStatusEnum.SUBMITTED.getStatus();
	
	Set<Assignment>findByUser(User user);

	@Query("select a from Assignment a "+
	"where a.status = 'submitted' " + "or a.codeReviewer = :codeReviewer")
	Set<Assignment> findByCodeReviewer(User codeReviewer);


	
	

}
