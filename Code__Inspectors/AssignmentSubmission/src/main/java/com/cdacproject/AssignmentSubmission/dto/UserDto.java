package com.cdacproject.AssignmentSubmission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
	@JsonProperty("name")
	private String name;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}