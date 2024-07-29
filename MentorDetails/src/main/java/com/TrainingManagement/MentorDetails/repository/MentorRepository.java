package com.TrainingManagement.MentorDetails.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TrainingManagement.MentorDetails.entity.Mentor;

@Repository
public interface MentorRepository extends MongoRepository<Mentor, String> {
	
	Mentor findByMentoremployeeId(String mentoremployeeId);
}