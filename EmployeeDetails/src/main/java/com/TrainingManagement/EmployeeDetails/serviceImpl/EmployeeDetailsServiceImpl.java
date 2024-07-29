package com.TrainingManagement.EmployeeDetails.serviceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.Tika;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TrainingManagement.EmployeeDetails.Client.CourseClient;
import com.TrainingManagement.EmployeeDetails.DAO.EmployeeDetailsDAO;
import com.TrainingManagement.EmployeeDetails.entity.Course;
import com.TrainingManagement.EmployeeDetails.entity.Employee;
import com.TrainingManagement.EmployeeDetails.entity.SubCourse;
import com.TrainingManagement.EmployeeDetails.service.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	@Autowired
	EmployeeDetailsDAO empDAO;

	@Autowired
	CourseClient courseClient;

	private final Tika tika = new Tika();

	@Override
	public Employee addEmployee(Employee employee) {
		return empDAO.addEmployee(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		return empDAO.getEmployees();
	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		return empDAO.getEmployeeById(employeeId);
	}

	@Override
	public Employee updateEmployeeDetails(Employee employee) {
		return empDAO.UpdateEmployeeDetails(employee);
	}

	@Override
	public List<Employee> getEmployeesByBatchId(String batchId) {
		return empDAO.getEmployeesByBatchId(batchId);
	}

	@Override
	public Employee addCourseToEmployee(String employeeId, String courseId) {
		return empDAO.addCourseToEmployee(employeeId, courseId);
	}

	public List<Course> getCoursesByEmployeeId(String employeeId) {
		return empDAO.getCoursesByEmployeeId(employeeId);
	}

	@Override
	public Employee addImageToEmployee(String employeeId, MultipartFile image) throws IOException {
		Employee emp = empDAO.getEmployeeById(employeeId);
		if (image != null) {
			emp.setImage(new Binary(image.getBytes()));
		}

		return empDAO.addImageToEmployee(emp);
	}

	@Override
	public String getImageContentType(String id) throws IOException {
		Employee employee = getEmployeeById(id);
		if (employee != null && employee.getImage() != null) {
			return tika.detect(employee.getImage().getData());
		} else {
			return null;
		}
	}

	@Override
	public Employee changeCourseStatus(String employeeId, String courseId, SubCourse newSubCourse) {

		return empDAO.changeCourseStatus(employeeId, courseId, newSubCourse);
	}

	@Override
	public int getEmployeeCountInBatch(String batchId) {
		return empDAO.getEmployeeCountInBatch(batchId);
	}

	@Override
	public Map<String, Double> calculateTotalCompletionStatusByBatchId(String batchId) {
		List<Employee> employees = empDAO.calculateTotalCompletionStatusByBatchId(batchId);
		
		Map<String, Integer> totalSubCourses = new HashMap<>();
		Map<String, Integer> courseCompletionStatus = new HashMap<>();
		for (Employee employee : employees) {
			for (Course course : employee.getCourse()) {
				int completedSubCourses = 0; 
				int totalCount = course.getSubCourses().size(); //Total SubCourses In a single Course
				for (SubCourse subCourse : course.getSubCourses()) {
					if (subCourse.getStatus() == null) {
					} else {
						if (subCourse.getStatus().equalsIgnoreCase("Complete")) {
							completedSubCourses++;
						}
					}
				}
				totalSubCourses.merge(course.getCourseName(), totalCount, Integer::sum);
				courseCompletionStatus.merge(course.getCourseName(), completedSubCourses, Integer::sum);
			}
		}
		Map<String, Double> courseCompletionPercentage = new HashMap<>();
		 for (String courseName : totalSubCourses.keySet()) {
	            int total = totalSubCourses.get(courseName);
	            int completed = courseCompletionStatus.get(courseName);
	            double percentage = ((double) completed / total) * 100;
	            courseCompletionPercentage.put(courseName, percentage);
	        }
		return courseCompletionPercentage;
	}

}
