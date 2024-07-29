package com.TrainingManagement.EmployeeDetails.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.TrainingManagement.EmployeeDetails.entity.Course;
import com.TrainingManagement.EmployeeDetails.entity.Employee;
import com.TrainingManagement.EmployeeDetails.entity.SubCourse;

public interface EmployeeDetailsService {

	public Employee addEmployee(Employee employee);
	public Employee addImageToEmployee(String employeeId, MultipartFile image) throws IOException;
	public List<Employee> getEmployees();
	public Employee getEmployeeById(String employeeId);
	public Employee updateEmployeeDetails(Employee employee);
	public List<Employee> getEmployeesByBatchId(String batchId);
	public Employee addCourseToEmployee(String employeeId, String courseId);
	public List<Course> getCoursesByEmployeeId(String employeeId);
	public String getImageContentType(String id) throws IOException;
	public Employee changeCourseStatus(String employeeId, String courseId, SubCourse newSubCourse);
	public int getEmployeeCountInBatch(String batchId);
	public Map<String, Double> calculateTotalCompletionStatusByBatchId(String batchId);
}
