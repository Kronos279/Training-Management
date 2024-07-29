package com.attendance.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.attendance.entities.Attendance;

public interface AttendanceDao {
	public Attendance markInTime(Attendance attendance);
	
	public Attendance markOutTime(Attendance attendance);
	
	Attendance getAttendanceByEmployeeIdAndOutTimeAndDate(String employeeId, LocalTime outTime,LocalDate date);
	
	Attendance getAttendanceByEmployeeIdAndDate(String employeeId,LocalDate date);
	
	List<Attendance> getAttendanceByEmployeeId(String employeeId);
	
	List<Attendance> getByDate(LocalDate date);
}
