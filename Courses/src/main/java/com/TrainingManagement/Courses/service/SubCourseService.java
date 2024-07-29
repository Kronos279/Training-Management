package com.TrainingManagement.Courses.service;

import java.util.List;

import com.TrainingManagement.Courses.entity.SubCourse;

public interface SubCourseService {
	
	SubCourse addSubCourse(String courseId, SubCourse subCourse);
    SubCourse updateSubCourse(String courseId, SubCourse subCourse);
    void deleteSubCourse(String courseId, String subCourseId);
    List<SubCourse> getSubCourses(String courseId);

}
