package com.TrainingManagement.EmployeeDetails.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.TrainingManagement.EmployeeDetails.Client.CourseClient;
import com.TrainingManagement.EmployeeDetails.entity.Course;
import com.TrainingManagement.EmployeeDetails.entity.Employee;
import com.TrainingManagement.EmployeeDetails.entity.SubCourse;
import com.TrainingManagement.EmployeeDetails.exceptions.ResourceNotFoundException;
import com.TrainingManagement.EmployeeDetails.repository.EmployeeRepository;

@Component
public class EmployeeDetailsDAOImpl implements EmployeeDetailsDAO {

	@Autowired
	EmployeeRepository emprepo;
	
	@Autowired
	CourseClient courseClient;
	
	@Override
	public Employee addEmployee(Employee employee) {
		return emprepo.save(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> list = emprepo.findAll();
		if(!list.isEmpty()) {
			return list;
		}
		else {
			throw new ResourceNotFoundException("No Entries Yet");
		}
		
	}

	@Override
	public Employee getEmployeeById(String employeeId) throws RuntimeException {
		Employee employee =  emprepo.findByEmployeeId(employeeId);
		if(employee == null) {
			throw new ResourceNotFoundException("No Employee Found With this EmployeeId");
		}
		else {
			return employee;
		}
	}

	@Override
	public Employee UpdateEmployeeDetails(Employee employee) {
		return emprepo.save(employee);
	}

	@Override
	public List<Employee> getEmployeesByBatchId(String batchId) {
		return emprepo.findByBatchId(batchId);
	}

	
	
	@Override
	public Employee addCourseToEmployee(String employeeId, String courseId) {
		 Employee employee = emprepo.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
		 List<Course> courses = employee.getCourse();
	        List<String> courseIds = employee.getCourseIds();
	        if (courseIds == null) {
	            courseIds = new ArrayList<>(); // Initialize the list if it is null
	        }
	        if (!courseIds.contains(courseId)) {
	            courseIds.add(courseId);
	            employee.setCourseIds(courseIds);
	            courses.add(courseClient.getCourseById(courseId));
	            employee.setCourse(courses);
	        }
	        else{
	        	for(int i=0;i<courses.size();i++) {
	        		if(courses.get(i).getCourseId().equals(courseId)) {
	        			courses.set(i, courseClient.getCourseById(courseId));
	        		}
	        	}
	        	
	        }
	        return emprepo.save(employee);
	}
	
	@Override
	public Employee changeCourseStatus(String employeeId, String courseId, SubCourse newSubCourse) {
		
		Optional<Employee> employeeOpt = emprepo.findById(employeeId);

	    if (employeeOpt.isPresent()) {
	        Employee employee = employeeOpt.get();
	        List<Course> empCourses = employee.getCourse();

	        if (empCourses != null) {
	            empCourses.stream()
	                .filter(course -> courseId.equals(course.getCourseId()))
	                .flatMap(course -> course.getSubCourses().stream())
	                .filter(subCourse -> newSubCourse.getSubCourseId().equals(subCourse.getSubCourseId()))
	                .findFirst()
	                .ifPresent(subCourse -> subCourse.setStatus(newSubCourse.getStatus()));
	            return emprepo.save(employee);
	        }
	    }
		throw new RuntimeException("Eployee, Course, Or SubCourse not found");
		
	}
	
	@Override
	public List<Course> getCoursesByEmployeeId(String employeeId) {
        Employee employee = emprepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employee.getCourse();
    }

	@Override
	public Employee addImageToEmployee(Employee employee) {
		return emprepo.save(employee);
	}

	@Override
	public byte[] getImageById(String employeeId) {
		Optional<Employee> employeeopt = emprepo.findById(employeeId);
		if(employeeopt.isEmpty()) {
			return null;
		}
		Employee employee = employeeopt.get();
		return employee.getImage().getData();
	}

	@Override
	public int getEmployeeCountInBatch(String batchId) {
		List<Employee>emp = emprepo.findByBatchId(batchId);
		return emp.size();
	}

	@Override
	public List<Employee> calculateTotalCompletionStatusByBatchId(String batchId) {
		return emprepo.findByBatchId(batchId);
	}
	
	
	
	

}
