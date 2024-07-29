package com.TrainingManagement.BatchDetails.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="batches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Batch {
	 	@Id
	    private String batchId;
	    private String mentorId;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private String status;
	    private String description;
	    private List<String> menteeIds;
	    private List<Announcement> announcements = new ArrayList<>();
//	    private List<String> courseIds = new ArrayList<>();
	    
}


