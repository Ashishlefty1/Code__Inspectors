package com.cdacproject.AssignmentSubmission.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cdacproject.AssignmentSubmission.entities.Assignment;
import com.cdacproject.AssignmentSubmission.enums.AssignmentEnum;

public class AssignmentResponseDto {
	
	private Assignment assignment;
	private AssignmentEnum[] assignmentEnums =AssignmentEnum.values()
;	//private List <AssignmentEnumDto> assignmentEnums = new ArrayList<>();
	
	
	public AssignmentResponseDto(Assignment assignment) {
		super();
		this.assignment = assignment;
		
//		Arrays.stream(AssignmentEnum.values())
//		.forEach(assignmentEnum ->{ assignmentEnums.add(new AssignmentEnumDto( assignmentEnum.getAssignmentName(),assignmentEnum.getAssignmentNum()));});
	}
	public AssignmentEnum[] getAssignmentEnums() {
		return assignmentEnums;
	}
	public Assignment getAssignment() {
		return assignment;
	}
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
	
	
//	public List<AssignmentEnumDto> getAssignmentEnums() {
//		
//		return assignmentEnums;
//	}

}
