package com.TrainingManagement.BatchDetails.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Employee {

	private String employeeId;
	private String employeeEmail;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeGrade;
	private String employeeGender;
	@JsonFormat(pattern ="dd-MM-yyyy")
	private LocalDate employeeBirthDate;
	@JsonFormat(pattern ="dd-MM-yyyy")
	private LocalDate employeeJoiningDate;
	private String batchId;
	private List<Course> course = new ArrayList<>();
}
