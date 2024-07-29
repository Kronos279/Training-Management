package com.TrainingManagement.EmployeeDetails.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.TrainingManagement.EmployeeDetails.entity.Course;

@FeignClient("COURSES")
public interface CourseClient {

	 	@GetMapping("/courses/{courseId}")
	    public Course getCourseById(@PathVariable String courseId);
}
