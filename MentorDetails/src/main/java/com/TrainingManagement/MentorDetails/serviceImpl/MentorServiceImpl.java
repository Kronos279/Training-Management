package com.TrainingManagement.MentorDetails.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrainingManagement.MentorDetails.clients.BatchClient;
import com.TrainingManagement.MentorDetails.clients.EmployeeClient;
import com.TrainingManagement.MentorDetails.entity.Batch;
import com.TrainingManagement.MentorDetails.entity.CourseAssignmentResponse;
import com.TrainingManagement.MentorDetails.entity.Mentor;
import com.TrainingManagement.MentorDetails.exception.ResourceNotFoundException;
import com.TrainingManagement.MentorDetails.repository.MentorRepository;
import com.TrainingManagement.MentorDetails.service.MentorService;

@Service
public class MentorServiceImpl implements MentorService {
//	 	@Autowired
	    private MentorRepository mentorRepository;

	    @Autowired
	 	public void MentorServiceImpl(MentorRepository mentorRepository) {
	 		this.mentorRepository = mentorRepository;
	 	}
	 	
	 	
	 	@Autowired
	    private EmployeeClient employeeClient;

	 	@Autowired
	    private BatchClient batchClient;
	 	
	    public List<Mentor> getAllMentors() {
	    	List<Mentor> mentors = mentorRepository.findAll();
	    	if(!mentors.isEmpty()) {
	    		for(Mentor mentor : mentors) {
	    			Mentor loadmentor = mentorRepository.findById(mentor.getMentoremployeeId()).orElseThrow(() -> new RuntimeException("Mentor not found"));
	    	        List<Batch> batches = batchClient.getBatchesByMentorId(loadmentor.getMentoremployeeId());
	    	        List<String> batchIds = new ArrayList<>();
	    	        for(Batch batch:batches) {
	    	        	batchIds.add(batch.getBatchId());
	    	        }
	    	        loadmentor.setBatches(batchIds);
	    	        mentors.set(mentors.indexOf(mentor), loadmentor);
	    		}
	    		return mentors;
	    	}
	    	else {
	    		throw new RuntimeException("No Mentors Found");
	    	}
	        
	    }

	    public Mentor getMentorByMentoremployeeId(String mentoremployeeId) {
	    	Mentor mentor = mentorRepository.findById(mentoremployeeId).orElseThrow(() -> new RuntimeException("Mentor not found"));
	        List<Batch> batches = batchClient.getBatchesByMentorId(mentoremployeeId);
	        List<String> batchIds = new ArrayList<>();
	        for(Batch batch:batches) {
	        	batchIds.add(batch.getBatchId());
	        }
	        mentor.setBatches(batchIds);
	        return mentor;
	    }

	    public Mentor createMentor(Mentor mentor) {
	        return mentorRepository.save(mentor);
	    }

	    public Mentor updateMentor(String id, Mentor mentorDetails) {
	        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mentor not found with id: " + id));
	        mentor.setMentoremployeeId(mentorDetails.getMentoremployeeId());
	        mentor.setMentorEmail(mentorDetails.getMentorEmail());
	        mentor.setMentorFirstName(mentorDetails.getMentorFirstName());
	        mentor.setMentorLastName(mentorDetails.getMentorLastName());
	        return mentorRepository.save(mentor);
	    }

	    public void deleteMentor(String id) {
	        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mentor not found with id: " + id));
	        mentorRepository.delete(mentor);
	    }

		@Override
		public CourseAssignmentResponse addCourseToEmployee(String mentorId, String employeeId, String courseId) {
			CourseAssignmentResponse response = new CourseAssignmentResponse();
	        response.setEmployeeId(employeeId);
	        response.setMentorId(mentorId);
	        response.setCourseIds(employeeClient.getCoursesByEmployeeId(employeeId)); // Assume this method exists
	        response.setMessage("Course successfully added to employee.");
			employeeClient.addCourseToEmployee(employeeId, courseId);	
			return response;
		}
		
		

}
