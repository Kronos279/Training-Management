package com.TrainingManagement.MentorDetails.entity;

import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mentors")
public class Mentor {

	@Id
	@Field("_id")
    private String mentoremployeeId;
    private String mentorEmail;
    private String mentorFirstName;
    private String mentorLastName;
//    private Binary image;
    
    private transient List<String> batches;
	
}



