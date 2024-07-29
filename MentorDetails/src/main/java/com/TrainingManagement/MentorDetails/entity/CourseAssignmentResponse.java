package com.TrainingManagement.MentorDetails.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseAssignmentResponse {

	private String employeeId;
    private String mentorId;
    private List<String> courseIds;
    private String message;
}
