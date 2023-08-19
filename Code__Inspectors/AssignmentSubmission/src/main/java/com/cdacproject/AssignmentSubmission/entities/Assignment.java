package com.cdacproject.AssignmentSubmission.entities;

import javax.persistence.*;

@Entity
public class Assignment {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String status;
	private String gitHubUrl;
	private String branch;
	private String codeReviewVideoUrl;
	@ManyToOne(optional=false)
	private User user;
	

	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getGitHubUrl() {
		return gitHubUrl;
	}




	public void setGitHubUrl(String gitHubUrl) {
		this.gitHubUrl = gitHubUrl;
	}




	public String getBranch() {
		return branch;
	}




	public void setBranch(String branch) {
		this.branch = branch;
	}




	public String getCodeReviewVideoUrl() {
		return codeReviewVideoUrl;
	}




	public void setCodeReviewVideoUrl(String codeReviewVideoUrl) {
		this.codeReviewVideoUrl = codeReviewVideoUrl;
	}
	
	
	@Override
	public String toString() {
		return "Assignment [branch=" + branch + ", codeReviewVideoUrl=" + codeReviewVideoUrl + ", gitHubUrl="
				+ gitHubUrl + ", status=" + status + "]";
	}
	
	
	
}
