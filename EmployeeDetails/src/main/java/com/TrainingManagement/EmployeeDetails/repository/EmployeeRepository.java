package com.TrainingManagement.EmployeeDetails.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TrainingManagement.EmployeeDetails.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Employee findByEmployeeId(String employeeId);
	List<Employee> findByBatchId(String batchId);
}
