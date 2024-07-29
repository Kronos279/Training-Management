package com.attendance.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AttendanceNotPresent extends RuntimeException {
	private String msg;
}
