package com.TrainingManagement.Courses.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="subcourses")
public class SubCourse {
	private String subCourseId;
    private String subCourseName;
    private String status;
}
