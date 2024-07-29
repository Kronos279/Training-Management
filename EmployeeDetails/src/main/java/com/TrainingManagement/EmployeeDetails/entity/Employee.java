package com.TrainingManagement.EmployeeDetails.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee")
public class Employee {
	@Id
	@Field("_id")
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
	private List<String> courseIds = new ArrayList<>();
	private Binary image;
	private List<Course> course = new ArrayList<>();
}
