 package com.cdacproject.AssignmentSubmission.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdacproject.AssignmentSubmission.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByusername(String username);

}
