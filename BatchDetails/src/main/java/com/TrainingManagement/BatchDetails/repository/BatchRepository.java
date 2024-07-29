package com.TrainingManagement.BatchDetails.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.TrainingManagement.BatchDetails.entity.Batch;

public interface BatchRepository extends MongoRepository<Batch, String> {

	List<Batch> findByMentorId(String mentorId);
	
}
