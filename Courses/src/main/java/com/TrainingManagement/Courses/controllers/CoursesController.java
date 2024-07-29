package com.TrainingManagement.Courses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TrainingManagement.Courses.entity.Course;
import com.TrainingManagement.Courses.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CoursesController {

	
	 @Autowired
	    private CourseService courseService;

	    @PostMapping
	    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
	        Course savedCourse = courseService.addCourse(course);
	        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
	    }

	    @GetMapping
	    public ResponseEntity<List<Course>> getAllCourses() {
	        List<Course> courses = courseService.getAllCourses();
	        return new ResponseEntity<>(courses, HttpStatus.OK);
	    }

	    @GetMapping("/{courseId}")
	    public ResponseEntity<Course> getCourseById(@PathVariable String courseId) {
	        Course course = courseService.getCourseById(courseId);
	        return new ResponseEntity<>(course, HttpStatus.OK);
	    }

	    @PutMapping
	    public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
	        Course updatedCourse = courseService.updateCourse(course);
	        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
	    }

	    @DeleteMapping("/{courseId}")
	    public ResponseEntity<Void> deleteCourse(@PathVariable String courseId) {
	        courseService.deleteCourse(courseId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
