package com.TrainingManagement.EmployeeDetails.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	
	private String courseId;
    private String courseName;
    private List<SubCourse> subCourses = new ArrayList<>();

}
