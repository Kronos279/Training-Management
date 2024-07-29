package com.TrainingManagement.EmployeeDetails.DAO;

import java.util.List;
import java.util.Map;

import com.TrainingManagement.EmployeeDetails.entity.Course;
import com.TrainingManagement.EmployeeDetails.entity.Employee;
import com.TrainingManagement.EmployeeDetails.entity.SubCourse;

public interface EmployeeDetailsDAO {

	public Employee addEmployee(Employee employee);
	public List<Employee> getEmployees();
	public Employee getEmployeeById(String employeeId);
	public Employee UpdateEmployeeDetails(Employee employee);
	public List<Employee> getEmployeesByBatchId(String batchId);
	Employee addCourseToEmployee(String employeeId, String course);
	public List<Course> getCoursesByEmployeeId(String employeeId);
	public Employee addImageToEmployee(Employee employee);
	public byte[] getImageById(String employeeId);
	public Employee changeCourseStatus(String employeeId, String courseId, SubCourse newSubCourse);
	public int getEmployeeCountInBatch(String batchId);
	public List<Employee> calculateTotalCompletionStatusByBatchId(String batchId);
}
