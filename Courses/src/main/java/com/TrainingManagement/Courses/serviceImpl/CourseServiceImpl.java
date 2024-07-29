package com.TrainingManagement.Courses.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrainingManagement.Courses.entity.Course;
import com.TrainingManagement.Courses.repository.CourseRepository;
import com.TrainingManagement.Courses.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	
	@Autowired
    private CourseRepository courseRepository;

	@Override
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(String courseId) {
		 return courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
	}

	@Override
	public Course updateCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public void deleteCourse(String courseId) {
		courseRepository.deleteById(courseId);
	}

}
