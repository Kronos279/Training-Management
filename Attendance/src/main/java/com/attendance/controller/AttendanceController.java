package com.attendance.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.attendance.entities.Attendance;
import com.attendance.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceService;
	
	
	@PostMapping("/markin")
	public ResponseEntity<?> markInTime(@RequestParam("markin") LocalTime markin,@RequestParam("image") MultipartFile image,
			@RequestParam("employeeId") String employeeId,@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
			Attendance attendance = attendanceService.markInTime(markin, date, image, employeeId);
			Map<String, Object> responseBody = new HashMap<>();
		    responseBody.put("message", "Marked in successfully");
		    responseBody.put("id", attendance.getId());
		    responseBody.put("attendance", attendance);
		    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
   
	}
	
	@PatchMapping("/markout")
	public ResponseEntity<?> markOutTime(@RequestParam("markout") LocalTime markout,@RequestParam("image") MultipartFile image,
			@RequestParam("employeeId") String employeeId,@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		Attendance attendance=attendanceService.markOutTime(markout,image,employeeId,date);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("message", "Marked out successfully");
		responseBody.put("id", attendance.getId());
		responseBody.put("attendance", attendance);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
		
	}
	
	@GetMapping("/markInPicture")
	public ResponseEntity<?> markInPicture(@RequestParam("employeeId") String employeeId,
			@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		Attendance attendance=attendanceService.getAttendanceByEmployeeId(employeeId, date);
		
		 if (attendance == null || attendance.getInPicture() == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	     }
		 
		 HttpHeaders headers=new HttpHeaders();
		 headers.setContentType(MediaType.IMAGE_JPEG);
		 
		
		return new ResponseEntity<>(attendance.getInPicture(),headers,HttpStatus.OK);
		
	}
	@GetMapping("/markOutPicture")
	public ResponseEntity<?> markOutPicture(@RequestParam("employeeId") String employeeId,
			@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		Attendance attendance=attendanceService.getAttendanceByEmployeeId(employeeId, date);
		
		 if (attendance == null || attendance.getInPicture() == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	     }
		 
		 HttpHeaders headers=new HttpHeaders();
		 headers.setContentType(MediaType.IMAGE_JPEG);
		 
		
		return new ResponseEntity<>(attendance.getOutPicture(),headers,HttpStatus.OK);
		
	}
	
	@GetMapping("/getByEmployeeId")
	public ResponseEntity<?> getAllByEmployeeId(@RequestParam("employeeId") String employeeId){
		
		List<Attendance> list=new ArrayList<Attendance>();
		list=attendanceService.getAttendanceByEmployeeId(employeeId);
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/getByDate")
	public ResponseEntity<?> getAllByDate(@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		
		List<Attendance> list=new ArrayList<Attendance>();
		list=attendanceService.getByDate(date);
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
		
	}
}
