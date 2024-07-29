package com.TrainingManagement.MentorDetails.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.TrainingManagement.MentorDetails.entity.Batch;

@FeignClient(name="BATCHDETAILS")
public interface BatchClient {

	@GetMapping("/batch/mentor/{mentorId}")
    List<Batch> getBatchesByMentorId(@PathVariable("mentorId") String mentorId);
}
