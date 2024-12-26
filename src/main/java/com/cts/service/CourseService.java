package com.cts.service;

import java.util.List;
import com.cts.entity.Course;

public interface CourseService {
	
    List<Course> getAllCourses();
    
    Course createCourse(Course course);
    
    Course updateCourse(Long id, Course course);
    
    void deleteCourse(Long id);
}

