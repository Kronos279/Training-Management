package com.TrainingManagement.MentorDetails.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "EmployeeDetails")
public interface EmployeeClient {

	@PutMapping("/employeedetails/{employeeId}/courses")
	void addCourseToEmployee(@PathVariable("employeeId") String employeeId, @RequestParam("courseId") String courseId);
	@GetMapping("/employeedetails/{employeeId}/courses")
    List<String> getCoursesByEmployeeId(@PathVariable("employeeId") String employeeId);
}
