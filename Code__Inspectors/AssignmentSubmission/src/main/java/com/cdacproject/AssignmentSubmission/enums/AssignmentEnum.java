package com.cdacproject.AssignmentSubmission.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentEnum {
	
	ASSIGNMENT_1(1,"Html Assignment"),
	ASSIGNMENT_2(2,"Guessing Game"),
	ASSIGNMENT_3(3,"User Login"),
	ASSIGNMENT_4(4,"Student Course List"),
	ASSIGNMENT_5(5,"Custom Array List"),
	ASSIGNMENT_6(6,"Unit Testing"),
	ASSIGNMENT_8(8,"Multi Threading"),
	ASSIGNMENT_9(9, "spring MVC"),
	ASSIGNMENT_10(10,"RESTful API"),
	ASSIGNMENT_11(11,"Full-Stack with thyme Leaf"),
	ASSIGNMENT_12(12, "Reports with SQL"),
	ASSIGNMENT_13(13,"Online Bank"),
	ASSIGNMENT_14(14,"Chatting with JS");

	
	private int assignmentNum;
	private String assignmentName;
	
	private AssignmentEnum (int assignmentNum, String assignmentName) {
		
		this.assignmentNum = assignmentNum;
		this.assignmentName = assignmentName;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public int getAssignmentNum() {
		return assignmentNum;
	}

}
