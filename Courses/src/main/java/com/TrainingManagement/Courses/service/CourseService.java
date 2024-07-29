package com.TrainingManagement.Courses.service;

import java.util.List;

import com.TrainingManagement.Courses.entity.Course;

public interface CourseService {

	Course addCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(String courseId);
    Course updateCourse(Course course);
    void deleteCourse(String courseId);
}
