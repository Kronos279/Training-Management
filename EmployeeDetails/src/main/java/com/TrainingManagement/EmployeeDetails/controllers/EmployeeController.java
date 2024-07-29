package com.TrainingManagement.EmployeeDetails.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TrainingManagement.EmployeeDetails.entity.Course;
import com.TrainingManagement.EmployeeDetails.entity.Employee;
import com.TrainingManagement.EmployeeDetails.entity.SubCourse;
import com.TrainingManagement.EmployeeDetails.service.EmployeeDetailsService;

@RestController
@RequestMapping("employeedetails")
@CrossOrigin
public class EmployeeController {

	@Autowired
	EmployeeDetailsService service;

	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws IOException {
		return new ResponseEntity<>(service.addEmployee(employee), HttpStatus.CREATED);
	}

	@PutMapping("/uploadImage")
	public ResponseEntity<Employee> addImageToEmployee(@RequestParam("employeeId") String employeeId,
			@RequestParam("image") MultipartFile image) throws IOException {
		return new ResponseEntity<Employee>(service.addImageToEmployee(employeeId, image), HttpStatus.CREATED);

	}

	@GetMapping("/allEmployees")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employees = service.getEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/getEmployeeById")
	public ResponseEntity<Employee> getEmployeeById(@RequestParam String employeeId) {
		Employee employee = service.getEmployeeById(employeeId);
		return employee != null ? new ResponseEntity<>(employee, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployeeDetails(@RequestBody Employee employee) {
		Employee updatedEmployee = service.updateEmployeeDetails(employee);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}

	@GetMapping("/batch/{batchId}")
	public ResponseEntity<List<Employee>> getEmployeesByBatchId(@PathVariable String batchId) {
		return ResponseEntity.ok(service.getEmployeesByBatchId(batchId));
	}

	@PutMapping("/{employeeId}/courses")
	public ResponseEntity<Employee> addCourseToEmployee(@PathVariable String employeeId,
			@RequestParam String courseId) {
		return new ResponseEntity<Employee>(service.addCourseToEmployee(employeeId, courseId), HttpStatus.OK);
	}

	@GetMapping("/{employeeId}/courses")
	public ResponseEntity<List<Course>> getCoursesByEmployeeId(@PathVariable String employeeId) {
		List<Course> course = service.getCoursesByEmployeeId(employeeId);
		return ResponseEntity.ok(course);
	}

	@GetMapping("/getImage")
	public ResponseEntity<byte[]> getImageById(@RequestParam("employeeId") String employeeId) throws IOException {
		Employee employee = service.getEmployeeById(employeeId);
		if (employee != null && employee.getImage() != null) {
			byte[] image = employee.getImage().getData();
			String contentType = service.getImageContentType(employeeId);
			if (contentType != null) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.parseMediaType(contentType));
				return new ResponseEntity<>(image, headers, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/courses/subcourses")
	public Employee changeSubCourseStatus(@RequestParam String employeeId, @RequestParam String courseId,
			@RequestBody SubCourse subCourse) {
		return service.changeCourseStatus(employeeId, courseId, subCourse);
	}

	
	@GetMapping("/employeeCount")
    public ResponseEntity<Integer> getEmployeeCountInBatch(@RequestParam String batchId){
    	return new ResponseEntity<>(service.getEmployeeCountInBatch(batchId),HttpStatus.OK);
    }
	
	
	
	@GetMapping("/calculateTotalCompletionStatus")
	public ResponseEntity<Map<String,Double>> calculateTotalCompletionStatusByBatchId(@RequestParam String batchId){
		return new ResponseEntity<>(service.calculateTotalCompletionStatusByBatchId(batchId),HttpStatus.OK);
	}
	
	
	
	
	
}
