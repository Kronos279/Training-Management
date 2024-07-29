package com.TrainingManagement.MentorDetails.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.TrainingManagement.MentorDetails.entity.CourseAssignmentResponse;
import com.TrainingManagement.MentorDetails.entity.Mentor;

public interface MentorService {
	
	public List<Mentor> getAllMentors();
	public Mentor getMentorByMentoremployeeId(String mentoremployeeId);
	public Mentor createMentor(Mentor mentor);
	public Mentor updateMentor(String id, Mentor mentorDetails);
	public void deleteMentor(String id);
	public CourseAssignmentResponse  addCourseToEmployee(String mentorId, String employeeId, String courseId);

}
