package com.cdacproject.AssignmentSubmission.dto;




import com.cdacproject.AssignmentSubmission.entities.Assignment;
import com.cdacproject.AssignmentSubmission.enums.AssignmentEnum;
import com.cdacproject.AssignmentSubmission.enums.AssignmentStatusEnum;

public class AssignmentResponseDto {
	
	private Assignment assignment;
	private AssignmentEnum[] assignmentEnums =AssignmentEnum.values();
	private AssignmentStatusEnum[] statusEnums = AssignmentStatusEnum.values();
	
	
	
	
	
	public AssignmentResponseDto(Assignment assignment) {
		super();
		this.assignment = assignment;
		
//		Arrays.stream(AssignmentEnum.values())
//		.forEach(assignmentEnum ->{ assignmentEnums.add(new AssignmentEnumDto( assignmentEnum.getAssignmentName(),assignmentEnum.getAssignmentNum()));});
	}
	
	
	public Assignment getAssignment() {
		return assignment;
	}
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
	
	public AssignmentEnum[] getAssignmentEnums() {
		return assignmentEnums;
	}
	
	public AssignmentStatusEnum[] getStatusEnums() {
		return statusEnums;
	}
	
	
//	public List<AssignmentEnumDto> getAssignmentEnums() {
//		
//		return assignmentEnums;
//	}

}
