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

import com.TrainingManagement.Courses.entity.SubCourse;
import com.TrainingManagement.Courses.service.SubCourseService;

@RestController
@RequestMapping("/courses/{courseId}/subcourses")
public class SubCoursesController {

	 	@Autowired
	    private SubCourseService subCourseService;

	    @PostMapping
	    public ResponseEntity<SubCourse> addSubCourse(@PathVariable String courseId, @RequestBody SubCourse subCourse) {
	        SubCourse savedSubCourse = subCourseService.addSubCourse(courseId, subCourse);
	        return new ResponseEntity<>(savedSubCourse, HttpStatus.CREATED);
	    }

	    @PutMapping
	    public ResponseEntity<SubCourse> updateSubCourse(@PathVariable String courseId, @RequestBody SubCourse subCourse) {
	        SubCourse updatedSubCourse = subCourseService.updateSubCourse(courseId, subCourse);
	        return new ResponseEntity<>(updatedSubCourse, HttpStatus.OK);
	    }

	    @DeleteMapping("/{subCourseId}")
	    public ResponseEntity<Void> deleteSubCourse(@PathVariable String courseId, @PathVariable String subCourseId) {
	        subCourseService.deleteSubCourse(courseId, subCourseId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    @GetMapping
	    public ResponseEntity<List<SubCourse>> getSubCourses(@PathVariable String courseId) {
	        List<SubCourse> subCourses = subCourseService.getSubCourses(courseId);
	        return new ResponseEntity<>(subCourses, HttpStatus.OK);
	    }
}
