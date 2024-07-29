package com.TrainingManagement.DailyUpdates.entity;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.Binary;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private String employeeId;
	private String employeeEmail;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeGrade;
	@JsonFormat(pattern ="dd-MM-yyyy")
	private LocalDate employeeBirthDate;
	@JsonFormat(pattern ="dd-MM-yyyy")
	private LocalDate employeeJoiningDate;
	private String batchId;
	private List<String> courseIds;
	private Binary image;
}
