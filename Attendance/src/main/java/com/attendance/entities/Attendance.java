package com.attendance.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name="date")
	private LocalDate date;
	
	 @Column(name = "in_time")
	 private LocalTime inTime;

	 @Lob
	 @Column(name = "in_picture", columnDefinition = "LONGBLOB")
	 private byte[] inPicture;

	 @Column(name = "out_time")
	 private LocalTime outTime;

	 @Lob
	 @Column(name = "out_picture", columnDefinition = "LONGBLOB")
	 private byte[] outPicture;
	
	
	
	
}
