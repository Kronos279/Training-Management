package com.attendance.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AlreadyMarkedAttendance extends RuntimeException {
	private String msg;
	
}
