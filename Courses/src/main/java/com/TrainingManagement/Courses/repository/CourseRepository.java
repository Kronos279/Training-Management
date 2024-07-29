package com.TrainingManagement.Courses.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.TrainingManagement.Courses.entity.Course;

public interface CourseRepository extends MongoRepository<Course, String>{

}
