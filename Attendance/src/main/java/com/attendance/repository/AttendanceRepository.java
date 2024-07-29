package com.attendance.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entities.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	
	Attendance findByEmployeeIdAndOutTimeAndDate(String employeeId,LocalTime outTime,LocalDate date); 
	Attendance findByEmployeeIdAndDate(String employeeId,LocalDate date);
	
	List<Attendance> findByEmployeeId(String employeeId);
	
	List<Attendance> findByDate(LocalDate date);
}
