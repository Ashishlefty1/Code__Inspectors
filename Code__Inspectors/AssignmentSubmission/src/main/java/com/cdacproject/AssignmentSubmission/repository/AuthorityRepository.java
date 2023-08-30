package com.cdacproject.AssignmentSubmission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdacproject.AssignmentSubmission.entities.Authority;



@Repository
public interface AuthorityRepository  extends JpaRepository<Authority, Long> {

}
