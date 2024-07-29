package com.TrainingManagement.MentorDetails.exception;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private Integer errorCode;
	private String errorDesc;
	private Date date;

}
