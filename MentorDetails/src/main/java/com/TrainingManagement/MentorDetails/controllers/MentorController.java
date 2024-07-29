package com.TrainingManagement.MentorDetails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TrainingManagement.MentorDetails.entity.CourseAssignmentResponse;
import com.TrainingManagement.MentorDetails.entity.Mentor;
import com.TrainingManagement.MentorDetails.service.MentorService;

@RestController
@RequestMapping("/mentors")
@CrossOrigin
public class MentorController {

	@Autowired
    private MentorService mentorService;

    @GetMapping
    public List<Mentor> getAllMentors() {
        return mentorService.getAllMentors();
    }

    @GetMapping("/mentorById")
    public ResponseEntity<Mentor> getMentorByMentoremployeeId(@RequestParam String mentoremployeeId) {
        Mentor mentor = mentorService.getMentorByMentoremployeeId(mentoremployeeId);
        return ResponseEntity.ok().body(mentor);
    }

    @PostMapping("/addMentor")
    public Mentor createMentor(@RequestBody Mentor mentor) {
        return mentorService.createMentor(mentor);
    }

    @PutMapping("/UpdateMentor")
    public ResponseEntity<Mentor> updateMentor(@RequestParam String mentorId, @RequestBody Mentor mentorDetails) {
        Mentor updatedMentor = mentorService.updateMentor(mentorId, mentorDetails);
        return ResponseEntity.ok(updatedMentor);
    }

    @DeleteMapping("/DeleteMentor")
    public ResponseEntity<Void> deleteMentor(@RequestParam String mentorId) {
        mentorService.deleteMentor(mentorId);
        return ResponseEntity.noContent().build();
    }
  
    
    @PostMapping("/{mentorId}/employees/{employeeId}/courses")
    public ResponseEntity<CourseAssignmentResponse> addCourseToEmployee(@PathVariable String mentorId, @PathVariable String employeeId, @RequestParam String courseId) {
    	 CourseAssignmentResponse response = mentorService.addCourseToEmployee(mentorId, employeeId, courseId);
         return ResponseEntity.ok(response);
    }
}