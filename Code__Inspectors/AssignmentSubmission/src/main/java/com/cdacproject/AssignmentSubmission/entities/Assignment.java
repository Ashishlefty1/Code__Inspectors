package com.cdacproject.AssignmentSubmission.entities;

import javax.persistence.*;

@Entity
public class Assignment {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer number;
	private String status;
	private String githubUrl;
	private String branch;
	private String codeReviewVideoUrl;
	@ManyToOne(optional=false)
	private User user;
	
	@ManyToOne(optional=true)
	private User codeReviewer;

	public User getCodeReviewer() {
		return codeReviewer;
	}

	public void setCodeReviewer(User codeReviewer) {
		this.codeReviewer = codeReviewer;
	}

	public String getStatus() {
		return status;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
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
	
	public Integer getNumber() {
		return number;
	}

	public void setName(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Assignment [id=" + id + ", status=" + status + ", gitHubUrl=" + githubUrl + ", branch=" + branch
				+ ", codeReviewVideoUrl=" + codeReviewVideoUrl + ", user=" + user + "]";
	}


	public void setUser(User user) {
	
		this.user = user;
		
	}
	
	
	
}
