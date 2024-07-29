package com.TrainingManagement.Courses.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrainingManagement.Courses.entity.Course;
import com.TrainingManagement.Courses.entity.SubCourse;
import com.TrainingManagement.Courses.repository.CourseRepository;
import com.TrainingManagement.Courses.service.SubCourseService;

@Service
public class SubCourseServiceImpl implements SubCourseService {

	@Autowired
    private CourseRepository courseRepository;
	
	@Override
	public SubCourse addSubCourse(String courseId, SubCourse subCourse) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
		subCourse.setStatus("InComplete");
        course.getSubCourses().add(subCourse);
        courseRepository.save(course);
        return subCourse;
	}

	@Override
	public SubCourse updateSubCourse(String courseId, SubCourse subCourse) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        List<SubCourse> subCourses = course.getSubCourses();
        for (int i = 0; i < subCourses.size(); i++) {
            if (subCourses.get(i).getSubCourseId().equals(subCourse.getSubCourseId())) {
                subCourses.set(i, subCourse);
                break;
            }
        }
        courseRepository.save(course);
        return subCourse;
	}

	@Override
	public void deleteSubCourse(String courseId, String subCourseId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        course.getSubCourses().removeIf(subCourse -> subCourse.getSubCourseId().equals(subCourseId));
        courseRepository.save(course);
	}

	@Override
	public List<SubCourse> getSubCourses(String courseId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return course.getSubCourses();
	}

}
