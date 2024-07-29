package com.attendance.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.attendance.entities.Attendance;
import com.attendance.repository.AttendanceRepository;

@Service
public class AttendanceDaoImpl implements AttendanceDao {
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Override
	public Attendance markInTime(Attendance attendance) {
		// TODO Auto-generated method stub
		return attendanceRepository.save(attendance);
	}

	@Override
	public Attendance markOutTime(Attendance attendance) {
		// TODO Auto-generated method stub
		return attendanceRepository.save(attendance);
	}

	@Override
	public Attendance getAttendanceByEmployeeIdAndOutTimeAndDate(String employeeId, LocalTime outTime,LocalDate date) {
		// TODO Auto-generated method stub
		return attendanceRepository.findByEmployeeIdAndOutTimeAndDate(employeeId, outTime,date);
	}

	@Override
	public Attendance getAttendanceByEmployeeIdAndDate(String employeeId, LocalDate date) {
		// TODO Auto-generated method stub
		return attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
	}

	@Override
	public List<Attendance> getAttendanceByEmployeeId(String employeeId) {
		// TODO Auto-generated method stub
		return attendanceRepository.findByEmployeeId(employeeId);
	}

	@Override
	public List<Attendance> getByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return attendanceRepository.findByDate(date);
	}
	
	

}
