package com.TrainingManagement.BatchDetails.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.TrainingManagement.BatchDetails.entity.Employee;


@FeignClient(name="EMPLOYEEDETAILS")
public interface EmployeeClient {

	
	@GetMapping("/employeedetails/batch/{batchId}")
	public List<Employee> getEmployeesByBatchId(@PathVariable String batchId);
	
	@GetMapping("/employeedetails/employeeCount")
    public Integer getEmployeeCountInBatch(@RequestParam String batchId);
	
}
