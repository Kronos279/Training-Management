package com.attendance.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.attendance.dao.AttendanceDao;
import com.attendance.entities.Attendance;
import com.attendance.exception.AlreadyMarkedAttendance;
import com.attendance.exception.AttendanceNotPresent;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	private AttendanceDao attendanceDao;
	
	@Override
	public Attendance markInTime(LocalTime markin,LocalDate date, MultipartFile file,String employeeId) {
		// TODO Auto-generated method stub
		if(attendanceDao.getAttendanceByEmployeeIdAndDate(employeeId, date)!=null && attendanceDao.getAttendanceByEmployeeIdAndDate(employeeId, date).getInTime()!=null) {
			throw new AlreadyMarkedAttendance("Mark In is alrady perfomered");
		}
		Attendance attendance=new Attendance();
		attendance.setInTime(markin);
		attendance.setEmployeeId(employeeId);
		attendance.setDate(date);
		try {
			byte[] inPicture=file.getBytes();
			attendance.setInPicture(inPicture);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attendanceDao.markInTime(attendance) ;
	}

	@Override
	public Attendance markOutTime(LocalTime markout, MultipartFile file,String employeeId,LocalDate date) {
		// TODO Auto-generated method stub
		Attendance attendance=attendanceDao.getAttendanceByEmployeeIdAndOutTimeAndDate(employeeId,null,date);
		if(attendance==null) {
			throw new AttendanceNotPresent("Mark in attendance is not present");
		}
		if(attendance.getOutTime()!=null) {
			throw new AlreadyMarkedAttendance("Mark Out is already performed");
		}
		attendance.setOutTime(markout);
		
		try {
			byte[] outPicture=file.getBytes();
			attendance.setOutPicture(outPicture);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return attendanceDao.markOutTime(attendance);
	}

	@Override
	public Attendance getAttendanceByEmployeeId(String employeeId, LocalDate date) {
		// TODO Auto-generated method stub
		return attendanceDao.getAttendanceByEmployeeIdAndDate(employeeId, date);
	}

	@Override
	public List<Attendance> getAttendanceByEmployeeId(String employeeId) {
		// TODO Auto-generated method stub
		return attendanceDao.getAttendanceByEmployeeId(employeeId);
	}

	@Override
	public List<Attendance> getByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return attendanceDao.getByDate(date);
	}

}
