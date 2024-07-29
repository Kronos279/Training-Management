package com.TrainingManagement.BatchDetails.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
	
	private String announcementId;
	private LocalDate creationDate;
    private String announcement;
}
