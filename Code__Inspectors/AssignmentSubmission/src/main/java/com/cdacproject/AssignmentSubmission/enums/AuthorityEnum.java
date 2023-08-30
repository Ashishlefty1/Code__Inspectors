package com.cdacproject.AssignmentSubmission.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorityEnum {
	
	ROLE_STUDENT,
	ROLE_CODE_REVIEWER,
	ROLE_ADMIN;
	
	

}
