package com.attendance.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.attendance.entities.Attendance;

public interface AttendanceService {
	public Attendance markInTime(LocalTime markin,LocalDate date, MultipartFile file,String employeeId);
	
	public Attendance markOutTime(LocalTime markout, MultipartFile file,String employeeId,LocalDate date);
	
	public Attendance getAttendanceByEmployeeId(String employeeId,LocalDate date);
	
	List<Attendance> getAttendanceByEmployeeId(String employeeId);
	
	List<Attendance> getByDate(LocalDate date);
	
}
	