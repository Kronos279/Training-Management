package com.TrainingManagement.Courses.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="courses")
public class Course {
	
	@Id
    private String courseId;
    private String courseName;
    private List<SubCourse> subCourses;

}
