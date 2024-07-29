package com.TrainingManagement.MentorDetails.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Batch {
	private String batchId;
    private String mentorId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String description;
    private List<String> menteeIds;
}
