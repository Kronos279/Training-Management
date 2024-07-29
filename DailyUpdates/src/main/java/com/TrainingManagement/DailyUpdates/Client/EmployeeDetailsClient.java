package com.TrainingManagement.DailyUpdates.Client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.TrainingManagement.DailyUpdates.entity.Employee;


@FeignClient(name="EmployeeDetails")
public interface EmployeeDetailsClient {

	@GetMapping("/allEmployees")
	public List<Employee> getEmployees();
}
