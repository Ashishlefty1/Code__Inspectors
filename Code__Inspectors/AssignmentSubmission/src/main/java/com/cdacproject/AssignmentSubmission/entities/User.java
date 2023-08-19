package com.cdacproject.AssignmentSubmission.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate cohortStartDate;
	private String userName;
	private String password;
	public Long getId() {
		return id;
	}
	public User(Long id, LocalDate cohortStartDate, String userName, String password, List<Assignment> assignment) {
		super();
		this.id = id;
		this.cohortStartDate = cohortStartDate;
		this.userName = userName;
		this.password = password;
		
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getCohortStartDate() {
		return cohortStartDate;
	}
	public void setCohortStartDate(LocalDate cohortStartDate) {
		this.cohortStartDate = cohortStartDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", cohortStartDate=" + cohortStartDate + ", userName=" + userName + "]";
	}
	
}
