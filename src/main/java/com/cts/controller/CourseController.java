package com.cts.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.Course;
import com.cts.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController 
{

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	@GetMapping("/")
	public List<Course> getAllCourses() 
	{
		logger.info("Fetching all courses");
		List<Course> courses = courseService.getAllCourses();
		logger.info("Retrieved {} courses", courses.size());
		return courses;
	}

	@PostMapping("/addCourse")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Course createCourse(@RequestBody Course course) {
		logger.info("Creating course with details: {}", course);
		Course createdCourse = courseService.createCourse(course);
		logger.info("Course created successfully with ID: {}", createdCourse.getId());
		return createdCourse;
	}

	@PutMapping("/updateCourse/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Course updateCourse(@PathVariable Long id, @RequestBody Course course) 
	{
		logger.info("Updating course with ID: {}", id);
		Course updatedCourse = courseService.updateCourse(id, course);
		logger.info("Updated course details: {}", updatedCourse);
		return updatedCourse;
	}

	@DeleteMapping("/deleteCourse/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteCourse(@PathVariable Long id) 
	{
		logger.info("Received request to delete course with ID: {}", id);
		courseService.deleteCourse(id);
		logger.info("Course with ID: {} deleted successfully", id);
		return new ResponseEntity<>("Course is Deleted Successfully", HttpStatus.OK);
	}
}
